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

+$$$

**Note:** There are two known issues [LPS-71350](https://issues.liferay.com/browse/LPS-71350) 
and [LPS-70364](https://issues.liferay.com/browse/LPS-70364) that prevent CSS 
changes from being applied to the page and the Control Panel and Product Menu to
break when using Developer Mode. Both these issues are fixed in Liferay Portal
CE 7.0.3 GA4 and Liferay DXP Fixpack DE 13.

$$$

Individual file loading of your styling and behaviors, combined with disabled
caching for layout and FreeMarker templates, lets you see your changes more 
quickly. 

These developer settings are defined in the 
[`portal-developer.properties` file](https://github.com/liferay/liferay-portal/blob/7.0.x/portal-impl/src/portal-developer.properties). 
To use these settings, you can include them in your `portal-ext.properties` file 
or copy them over to your `portal-ext.properties` file and override specific 
properties as needed. These configurations are covered in this tutorial.

First, you can explore how it's done in IDE. 

## Setting Developer Mode for Your Server in @ide@ [](id=setting-developer-mode-for-your-server-in-liferay-ide)

To enable Developer Mode for your server in @ide@, follow these steps: 

1.  Create a `portal-ext.properties` file in your bundle's root folder (if you 
    haven't already) and add the following property to the file:

        include-and-override=portal-developer.properties
 
    This gives you access to the override settings provided by the
    `portal-developer.properties` file. 
 
2.  Double-click on your server in the *Servers* window and open the 
    *Liferay Launch* section.
 
3.  Select *Custom Launch Settings* and click the *Browse* button to select your 
    `portal-ext.properties` file. Save the changes.
 
+$$$

**Note:** The *Use developer mode* option isn't working at this time, but 
the steps above provide a viable work-around.

$$$

+$$$

**Warning:** Only change the Server settings from the runtime environment's 
Liferay Launch section.

$$$

For Liferay Portal servers below version 6.2 (e.g., Liferay v6.1 CE Server,
Liferay v6.0 CE Server), @ide@ enables Developer Mode by default. When
starting your Liferay server for the first time, it creates a
`portal-ide.properties` file in your Liferay Portal directory. This properties
file contains the property setting
`include-and-override=portal-developer.properties`, which enables Developer
Mode. 

Most of the configuration is provided by the `portal-developer.properties` file, 
but you still have to configure the FreeMarker template setting. Follow the 
steps in the [Configuring FreeMarker System Settings](/develop/tutorials/-/knowledge_base/7-0/using-developer-mode-with-themes#configuring-freemarker-system-settings) 
section to configure the FreeMarker template cache. 

If you're not using @ide@, next you'll to learn how to set Developer Mode in
your app server manually. 

## Setting Developer Mode for Your Server Using portal-developer.properties [](id=setting-developer-mode-for-your-server-using-portal-developer-properties)

To set Developer Mode manually, you must point to `portal-developer.properties` 
as shown in the last section. Add the `portal-ext.properties` file to your app 
server's bundle and add the following line:

    include-and-override=portal.developer.properties
 
`portal-developer.properties` provides the majority of the settings you'll need 
for smooth development. To disable the cache for FreeMarker templates, you must 
update the System Setting covered in the next section. 

## Configuring FreeMarker System Settings [](id=configuring-freemarker-system-settings)

FreeMarker Templates for themes and web content are cached by default. Therefore, 
any changes you make to your FreeMarker theme templates aren't immediately
displayed. You can change this behavior through System Settings. Follow these
steps: 

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

[Creating Themes with @ide@](/develop/tutorials/-/knowledge_base/7-0/creating-themes-with-liferay-ide)
