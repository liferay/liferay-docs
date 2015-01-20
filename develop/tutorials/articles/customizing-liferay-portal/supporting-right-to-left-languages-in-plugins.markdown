# Supporting Right-to-Left Languages in Plugins [](id=supporting-right-to-left-languages-in-plugins)

Middle Eastern languages such as Hebrew and Arabic are written right-to-left
(RTL). Many sites that use RTL languages are multilingual, requiring both RTL
and left-to-right (LTR) content. Modern browsers support [language and
direction](http://www.w3.org/TR/html401/struct/dirlang.html) HTML attributes to
adapt and align page content automatically. Sites, however, may consist of
elements that are absolutely positioned on the page by a style sheet: these
elements aren't automatically aligned by the browser. Instead, you must adapt
your style sheets to handle such elements. Since style sheets are usually
designed for LTR languages, the typical challenge is to create alternative
versions of the CSS for RTL languages. This can be an arduous task. 

Thankfully, Liferay's *Right to Left Language Support* app automatically adapts
Liferay Portal styles for RTL languages. When you deploy it, it mirrors
your site's content for RTL languages. The app is available on the Liferay
[Marketplace](http://www.liferay.com/marketplace). You can purchase, install,
and deploy the app as described in the
[Leveraging the Liferay Marketplace](/discover/portal/-/knowledge_base/6-2/leveraging-the-liferay-marketplace)
chapter of [Using Liferay Portal](/discover/portal). 

As a before-and-after example, the figure below shows a page displayed in
English, an LTR language. 

![Figure 1: Here is a site displaying English content left-to-right.](../../images/rtl-hook-001.png)

Compare it to the following figure of a page displayed in Hebrew, an RTL
language. 

![Figure 2: Here is a site displaying Hebrew content right-to-left using Right to Left Language Support.](../../images/rtl-hook-002.png)

You get the point, right? The Right to Left Language Support (RTL Support) app
does the heavy lifting of rendering the RTL content appropriately! 

Now that you've seen Liferay Portal and its apps rendered using RTL Support,
have you wondered how you might leverage RTL Support in your custom plugins?
You'll learn how to use RTL Support with your plugins next.

## Applying the RTL Support to Custom Plugins [](id=applying-the-rtl-support-to-custom-plugins)

You can use RTL Support with any plugin type, though theme plugins are the most
common. The following steps focus on using RTL Support in a custom theme, but
they also mention what's needed to make similar changes to support using RTL
Support in the other plugin types. 

**Step 1: Deploy the Right to Left Language Support app to your application server.** 
	
If upon initial deployment you don't notice any changes when switching to an
RTL language, reload the page to force a clean cache. 

Note: since dynamic generation of CSS from SASS is not yet supported in the
context of the RTL Support app, the hook loads the current theme's merged
CSS files, disregarding whether
[theme CSS fast loading](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Theme)
is disabled. Disabling CSS fast load (i.e., setting
[`theme.css.fast.load=false`](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Theme))
is commonly done while debugging for development. 

Now that you have deployed the RTL language support app, restart your
application server.

**Step 2: Extract the contents of the Liferay RTL Hook web application.** 

Copy the `rtl-hook.jar` and its dependencies, `ant.jar`, `jodd.jar`, `jruby.jar` 
and `rhino.jar`, from your RTL hook web app's `WEB-INF/lib` folder to the 
`docroot/WEB-INF/lib` folder of your plugin project. 

**Step 3: Modify Your web.xml and build.xml for RTL Language Support.** 

You'll have to add some code to your `web.xml` and `build.xml` in order for the 
RTL Language Support app to work. Here are some filtering elements that must 
be added to your `web.xml` before the closing `</web-app>` tag in your project's 
`docroot/WEB-INF/web.xml` file:

        <filter>
            <filter-name>Dynamic CSS Filter</filter-name>
            <filter-class>
                com.liferay.rtl.hook.filter.dynamiccss.DynamicCSSFilter
            </filter-class>
        </filter>
        <filter-mapping>
            <filter-name>Dynamic CSS Filter</filter-name>
            <url-pattern>*.css</url-pattern>
        </filter-mapping>

Some targets must be added to your `build.xml` so the RTL CSS files can be built
and cleaned. The following snippet is example code showing targets for a theme
plugin project's `build.xml`:

        <target name="build-css" 
                depends="clean-rtl-css, build-common-theme.build-css, build-rtl-css" 
        />

        <target name="build-rtl-css">
            <java
                classname="com.liferay.rtl.tools.RtlCssBuilder"
                classpathref="plugin.classpath"
                fork="true"
                newenvironment="true"
            >
                <jvmarg value="-Dliferay.lib.portal.dir=${app.server.lib.portal.dir}" />
                <arg value="sass.dir=/" />
                <arg value="sass.docroot.dir=${basedir}/docroot" />
            </java>
        </target>

        <target name="clean-rtl-css">
            <delete failonerror="false" includeemptydirs="true">
                <fileset dir="${basedir}/docroot" includes="**/.sass-cache/*_rtl.*" />
            </delete>
        </target>

If you're using the RTL Language Support app with another type of plugin project, 
rename the `build-common-theme.build-css` dependency target reference 
appropriately for your plugin type: 

*Portlet:* `build-common-portlet.build-css`

*Hook:* `build-common-hook.build-css`

*Ext:* `build-common-ext.build-css`

*Web App:* `build-common-web.build-css` 

**Step 4: Deploy your plugin to the portal.** 

After you've deployed your plugin, you'll notice that ant echoes 
*Generated RTL cache for ...* messages that mention the cache that the RTL hook 
generates in your plugin. In your plugin's `css/.sass-cache` folder, the hook 
creates `*_rtl.css` versions of each of your `*.css` files. 

You now know how to use the Right to Left Language Support app in your custom
plugins. Now it's time to learn how you can extend the Right to Left Language
Support app's style with your own custom CSS for RTL languages. 

+$$$ 

**Note:** You may need to run `ant deploy` from the command line for the
`css/.sass-cache` folder and `*_rtl.css` files to be generated. 

$$$

## Defining Custom CSS for RTL Languages [](id=defining-custom-css-for-rtl-languages)

As you learned in the previous section, the Right to Left Language Support (RTL
Support) app automatically generates RTL versions of your CSS files by applying
rules, such as changing `margin-left` to `margin-right`. You may, however, want
to extend the generated CSS by defining your own custom styles for RTL
languages. You can achieve this by following the steps below.

**Step 1: Create a CSS File to Extend the Original and Make Your Edits.**

You'll need to create a CSS file with the suffix `_rtl` in the same location as 
a CSS file that you want to extend. For example, create a file `main_rtl.css`
to extend a file named `main.css`. 

Once you have made the `_rtl` version of the CSS you want to extend, edit the 
`_rtl` file, adding *only* your custom styles for RTL languages.

**Step 2: Deploy your Plugin and Test It.**

After you have deployed your plugin, check its `css/.sass-cache` folder to see 
that the generated `_rtl.css` file not only contains the CSS from the original
file, but also contains your custom CSS code at the end. 

Add the Language portlet to a page and change the current language to an RTL 
language (e.g., Hebrew) to see your custom RTL styles.

In this tutorial, you've learned how to adapt your plugin styles for RTL
languages, and how to define custom styles for RTL languages. Whether you want
to adapt the portal and/or your custom plugins to RTL languages, the RTL Support
app makes it easy to offer your users the ideal viewing experience. 

## Related Topics [](id=related-topics)

As you can see, hooks are a useful way of modifying your portal. Here are some
other tutorials about hooks:

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

[Overriding Web Resources](/develop/tutorials/-/knowledge_base/6-2/overriding-web-resources)

[Customizing JSPs by Extending the Original](/develop/tutorials/-/knowledge_base/6-2/customizing-jsps-by-extending-the-original)

