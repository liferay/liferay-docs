# Using Developer Mode with Themes [](id=using-developer-mode-with-themes)

Do you want to develop Liferay resources without having to redeploy to see your
portal modifications? Use Liferay Developer Mode! In Developer Mode, all caches
are removed, so any changes you make are visible right away. Also, you don't
have to reboot the server as often in Developer Mode.

How does Developer Mode let you see your changes more quickly? In Developer
Mode, there are several changes to the normal order of operations. Here is a
list of Developer Mode's key behavior changes and the
[portal property](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html)
override settings that trigger them:

- CSS files are loaded individually rather than being combined and loaded as a
  single CSS file (`theme.css.fast.load=false`).
- Layout template caching is disabled (`layout.template.cache.enabled=false`).
- The server does not launch a browser when starting (`browser.launcher.url=`).
- FreeMarker Templates for themes and web content are not cached, so changes
  are applied immediately
  (`freemarker.engine.resource.modification.check.interval=0`). 
- Minification of CSS and JavaScript resources is disabled
  (`minifier.enabled=false`).

Individual file loading of your styling and behaviors, combined with disabled
caching for layout and FreeMarker templates, lets you see your changes more 
quickly. 

As you're developing your theme plugin, you can use Developer Mode regardless of
whether you're developing it in Liferay IDE. First, you can explore how it's
done in IDE. 

## Setting Developer Mode for Your Server in Liferay IDE [](id=setting-developer-mode-for-your-server-in-liferay-ide)

For Liferay Portal servers of version 6.2 or greater, the *Liferay settings*
section of the server runtime environment creation wizard lets you select either
*Standard* or *Development(portal-developer.properties)* for the runtime's
*Server Mode*. The Standard server mode is selected by default. To enable
Development Mode, select *Development(portal-developer.properties)* and save the
runtime environment. The next time you start any Liferay server that is based on
this runtime environment, it starts in Development Mode. 

![Figure 1: You can set Developer Mode in the *Liferay settings* section of your runtime environment in Liferay IDE](../../images/set-develop-mode.png)

+$$$

**Warning:** Only change the Server Mode
from the runtime environment's Liferay settings section. On server
startup, if Standard mode is set in Liferay IDE, the
`portal-developer.properties` file is not included for overriding portal
properties. 

$$$

For Liferay Portal servers below version 6.2 (e.g., Liferay v6.1 CE
Server, Liferay v6.0 CE Server), Liferay IDE enables Developer Mode by
default. On starting your Liferay server for the first time, IDE creates
a `portal-ide.properties` file in your Liferay Portal directory. This properties
file has the property setting:
`include-and-override=portal-developer.properties`, which enables Developer
Mode. Next you can take a look at how you can set Developer Mode outside of IDE.

## Setting Developer Mode for Your Server Using portal-developer.properties [](id=setting-developer-mode-for-your-server-using-portal-developer-properties)

If you're not using Liferay IDE, you must add the `portal-developer.properties`
file to your application server's configuration file in order to enable
Developer Mode. Since each application server has a different configuration file
or UI to specify system properties, you must follow your application server's
specific method for adding the `portal-developer.properties` file's properties
to the system properties. 

For example, to deploy Liferay in Developer Mode on a Tomcat application server,
you'd add `-Dexternal-properties=portal-developer.properties` to the list of
options for your `CATALINA_OPTS` variable, in your `setenv.sh` file
(`setenv.bat` in Windows). 

+$$$

**Tip:** If you're already using the system
property `external-properties` to load other properties files, add
`portal-developer.properties` to the list and use a comma to separate it from
other entries. 

$$$

Great! You've set up your Liferay server for Developer Mode. Now, when you
modify your theme's `custom.css` file directly in your Liferay bundle, you can
see your changes applied immediately on redeploying your theme! Make sure you
copy any changes you make back into your `_diffs` folder, or they'll be
overwritten when you redeploy your theme.

## Related Topics [](id=related-topics)

[Designing a Layout Template](/develop/tutorials/-/knowledge_base/6-2/designing-a-layout-template)

[Developing Applications with Liferay Developer Studio](/develop/learning-paths/-/knowledge_base/6-2/developing-applications-with-liferay-developer-stu)
