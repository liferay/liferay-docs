# Using Developer Mode with Themes [](id=using-developer-mode-with-themes)

Do you want to develop Liferay themes without having to redeploy to see your
portal modifications? Use Liferay Developer Mode! In Developer Mode, all caches
are removed, so any changes you make are visible right away. Also, you don't
have to reboot the server as often in Developer Mode.

How does Developer Mode let you see your changes more quickly? By default,
@product@ is optimized for performance. Developer mode optimizes your
configuration for development instead. Here is a list of Developer Mode's key
behavior changes and the 
[portal property](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html)
override settings that trigger them (if applicable):

- CSS files are loaded individually rather than being combined and loaded as a
  single CSS file (`theme.css.fast.load=false`).
- Layout template caching is disabled (`layout.template.cache.enabled=false`).
- The server does not launch a browser when starting (`browser.launcher.url=`).
- FreeMarker Templates for themes and web content are not cached, so changes
  are applied immediately (via the system setting in your @product@ instance).
- Minification of CSS and JavaScript resources is disabled
  (`minifier.enabled=false`).

Individual file loading of your styling and behaviors, combined with disabled
caching for layout and FreeMarker templates, lets you see your changes more 
quickly. 

<!-- First, you can explore how it's done in IDE. 

## Setting Developer Mode for Your Server in Liferay IDE [](id=setting-developer-mode-for-your-server-in-liferay-ide)

To enable Development Mode for a server, access the *Liferay Launch*
section of your runtime environment by double-clicking on the server in
the *Servers* tab. 

![Figure 1: You can set Developer Mode in the *Liferay Launch* section of your runtime environment in Liferay IDE](../../images/set-develop-mode-7.x.png)

+$$$

**Warning:** Only change the Server Mode from the runtime environment's Liferay
Launch section. On server startup, if Development mode isn't set, the
`portal-developer.properties` file is not included for overriding portal
properties. 

$$$

For Liferay Portal servers below version 6.2 (e.g., Liferay v6.1 CE Server,
Liferay v6.0 CE Server), Liferay IDE enables Developer Mode by default. On
starting your Liferay server for the first time, IDE creates a
`portal-ide.properties` file in your Liferay Portal directory. This properties
file has the property setting:
`include-and-override=portal-developer.properties`, which enables Developer
Mode. 

Liferay IDE sets most of the changes needed for Developer mode


FreeMarker Templates for themes and web content are cached by default. Therefore, 
any changes you make to your FreeMarker theme templates won't show up 
immediately. You can change this behavior through System Settings. Follow these 
steps to update the system setting:

1.  Open the Control Panel and goto *Configuration* &rarr; *System Settings*.

2.  Click the *Foundation* tab and select *FreeMarker Engine*.

3.  By default, the *Resource modification check*, the time in milliseconds that 
    the template is cached, is set to 60. Set this value to 0 to disable caching. 
-->
Learn how to manually set Developer Mode next.

## Setting Developer Mode for Your Server Using portal-developer.properties [](id=setting-developer-mode-for-your-server-using-portal-developer-properties)

To set Developer Mode manually, you must add the `portal-developer.properties`
file to your application server's launch configuration. Since each application 
server has a different configuration file or UI to specify system properties, 
you must follow your application server's specific method for adding the 
`portal-developer.properties` file to the server's launch configuration. 

<!--
+$$$

**Note:** If you manually set Developer Mode via `portal-developer.properties`, 
you must still set the *Resource modification check* system setting in your 
@product instance, as described in the 

$$$
-->

For example, to deploy Liferay in Developer Mode on a Tomcat application server,
you'd add `-Dexternal-properties=portal-developer.properties` to the list of
options for your `CATALINA_OPTS` variable, in your `setenv.sh` file
(`setenv.bat` in Windows). 

+$$$

**Tip:** If you're already using the system property `external-properties` to
load other properties files, add `portal-developer.properties` to the list and
use a comma to separate it from other entries. 

$$$

You can configure your `portal-developer.properties` file next.

### Configuring Portal Developer Properties [](id=configuring-portal-developer-properties)

Once, you've configured your server, add the following properties to your 
`portal-developer.properties` file:

    browser.launcher.url=
    layout.template.cache.enabled=false
    minifier.enabled=false
    theme.css.fast.load=false
 
This takes care of most of the Developer Mode properties, but you still have one 
left to configure. This is covered next.

<!-- Hey Mike, I don't think these instructions are correct. If you look in a
bundle's tomcat[version]/webapps/ROOT/WEB-INF/classes folder, there's a
portal-developer.properties file in there, with all the configuration you need.
You shouldn't need to create a new one. It's on the classpath already. 

The other thing is that when I use the initBundle Gradle command to pull a
runtime into a Workspace, it pre-configures the portal-ext.properties file with
this property: 

include-and-override=portal-developer.properties

This would seem to indicate that the way to do this is the same as how you did
it for 6.2: by adding the above property to portal-ext.properties. 

So my question is, where did the above instructions come from? I'm not sure
they're right. Thanks! -Rich -->

### Configuring FreeMarker System Settings [](id=configuring-freemarker-system-settings)

FreeMarker Templates for themes and web content are cached by default. Therefore, 
any changes you make to your FreeMarker theme templates won't show up 
immediately. You can change this behavior through System Settings. Follow these 
steps to update the system setting:

1.  Open the Control Panel and go to *Configuration* &rarr; *System Settings*.

2.  Click the *Foundation* tab and select *FreeMarker Engine*.

3.  By default, the *Resource modification check* (the time in milliseconds that
    the template is cached) is set to `60`. Set this value to `0` to disable
    caching.

Great! You've set up your Liferay server for Developer Mode. Now, when you
modify your theme's `_custom.scss` file directly in your Liferay bundle, you can
see your changes applied immediately on redeploying your theme!

## Related Topics [](id=related-topics)

[Creating Layout Templates Manually](/develop/tutorials/-/knowledge_base/7-0/creating-layout-templates-manually)

[Creating Themes with Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/creating-themes-with-liferay-ide)
