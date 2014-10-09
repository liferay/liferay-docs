# Localizing Site Administration Portlets [](id=localizing-site-administration-portlets)

<!-- The beginning and ending test portlets can be found at the following:
Begin: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferay-mvc/prefs/begin/event-listing-portlet
End: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/loc/loc-sa-port/end/event-listing-portlet
-->

Your plugin project may have Site Administration portlets that are missing
super-fancy, must-have titles and descriptions that other admin portlets have.
Or if you've already provided titles and descriptions, you may be wondering how
to localize them, to cater to your users' differing language needs. Well,
Liferay makes it easy to implement these cool looking features to your portlets. 

To offer localized portlets within Site Administration, you can create specially
tailored descriptions and title keys in separate `Language.properties` files for
each portlet in your project. You use the `javax.portlet.title` and
`javax.portlet.description` language keys. The following figure displays an
example of localizing portlets to Spanish: 

![Figure 1: You can localize portlets' titles and descriptions in Site Administration to any language, including Spanish.](../../images/portlet-title-and-description-es.png)

Next, you'll configure your project's portlets to display in Site
Administration, and then you'll localize its title and description. You'll
create one resource bundle for the title and another one for the description. 

+$$$

**Note:** If your project only has one
portlet, it's best to put your resource bundle directly in the `content`
folder. Specifying your bundle in the file `content/Language.properties` lets
you leverage the Plugins SDK's language building capabilities, via
right-clicking on the `Language.properties` file &rarr; *Liferay* &rarr; *Build
Languages* in Developer Studio or executing `ant build-lang` from the terminal. 

$$$

Specify and localize a title and description for each of your project's portlets
by following these steps: 

1. Configure each portlet to display in Site Administration. For example,
   you can display a portlet in  Site Administration's Content category and
   you can give your portlet an arbitrary weight value for determining where
   it's to be placed in the column with respect to other portlets. You specify
   each portlet's category and weight values in the
   `<control-panel-entry-category/>` and `<control-panel-entry-weight/>`
   elements for each portlet in your project's `liferay-portlet.xml` file. 

    For example, the following XML from a `liferay-portlet.xml` file specifies
    *Content* as the portlet's category, making the portlet available in the
    Content section of the Site Administration menu. 

		<portlet>
			<portlet-name>yourportlet</portlet-name>
			<icon>/icon.png</icon>
			<control-panel-entry-category>site_administration.content</control-panel-entry-category>
			<control-panel-entry-weight>1.5</control-panel-entry-weight>
			...
		</portlet>

    ![Figure 2: Notice that the lower the weighted number, the higher the portlet is listed in the specified menu.](../../images/new-portlet-in-site-admin.png)

2. Create a namespaced folder to hold each portlet's resource bundle. It's a
   best practice to name each resource bundle folder based on each portlet's
   name.

    For example, you'd create a resource bundle folder
    `docroot/WEB-INF/src/content/yourportlet` if one of your project's portlet's
    names was *yourportlet*. 

3. Create a `Language.properties` file in each resource bundle folder you just
   created. Specify the `javax.portlet.title` and `javax.portlet.description`
   language key/values in each of these `Language.properties` files. 

    For example, you could add the following properties to each 
    `Language.properties` file and replace their values: 

        javax.portlet.title=Your Portlet
        javax.portlet.description=Your portlet's description.

4. Specify the resource bundle for each portlet in the portlet's
   `<resource-bundle>` element in the project's `portlet.xml` file. The code
   snippet below demonstrates specifying the resource bundle for a portlet named
   *yourportlet*: 

        <portlet>
            <portlet-name>yourportlet</portlet-name>
            ...
            <resource-bundle>content.yourportlet.Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

5. Redeploy your plugin project.

6. Go to *Site Administration* &rarr; *Content* and select one of your portlets.
   Notice that the portlet's title and description match what you specified in
   its `Language.properties` file.

    ![Figure 3: Notice that your portlet title and description are visible in Site Administration.](../../images/portlet-title-and-description.png)

7. To provide your portlet in Liferay's supported languages, you'll need to
   build your portlet's languages. To do this in Liferay IDE, right-click your
   portlet's `content/[PORTLET_NAME]/Language.properties` file and select
   *Liferay* &rarr; *Build Languages*. You can also accomplish the same thing
   from the terminal by executing `ant build-lang`.

    **Note:** In order to generate translations of resource
    bundles automatically, you must configure your environment to use
    Microsoft's Bing Translator. Otherwise, Liferay's `build-lang` target simply
    generates a `Language_*.properties` file copying the original contents of
    `Langauge.properties`, for each language that Liferay supports. In other words,
    unless you configure to use Bing Translator, you must manually translate the
    values in the generated `Language_*.properties` files. See the tutorial *Translating
    Languages Using the Bing Translator* for details on using the automatic
    translation services. 

    <!-- TODO set Bing Translator tutorial reference as a link -->

8. Now that you have the languages built, you can specify language key values
   for each of the supported language files that were generated. For example,
   the Spanish translation for the title and description of a portlet named
   *yourportlet* could be designated inside the `Language_es.properties` file as
   the following keys: 

        javax.portlet.title=Su portlet en Espanol
        javax.portlet.description=Descripcion del portlet en Espanol.

9. To switch the portal to a specific language, add the code of that language to
   the portal context in the URL. For example, to interface with the portal in
   Spanish, you'd add `es` to the portal context in the URL like this: 

        http://localhost:8080/es/group/control_panel/...

    The Site Administration page displays your portlet's localized title
    and description.  

    ![Figure 4: It's easy to localize titles and descriptions for multiple portlets in your project.](../../images/portlet-title-and-description-es.png)

You're becoming an expert localizer!

+$$$

**Tip:** Do you know how your portlet
title is processed? If your portlet doesn't define a resource bundle or
`javax.portlet.title`, the portal container next checks the `<portlet-info>`
and inner `<portlet-title>` node in the `portlet.xml` descriptor. If they're
missing too, the `<portlet-name>` node value is rendered as the portlet title. 

$$$

+$$$

**Note:** Be aware that using a Struts 
portlet and referring to a `StrutsResource` bundle in your `portlet.xml`
engages a different title and description algorithm. Titles and long titles are
pulled using the following respective keys:

- `javax.portlet.title`
- `javax.portlet.long-title` 

$$$

Terrific! You've configured your plugin's portlets to show in the Site
Administration page, created resource bundles specifically for each portlet,
built your portlets' language translation files, and have verified your
portlets' translated titles and descriptions! 

