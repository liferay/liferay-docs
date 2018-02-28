# Using Developer Mode with Themes [](id=using-developer-mode-with-themes)

Do you want to develop themes without having to redeploy to see your 
modifications? Use Developer Mode! In Developer Mode, all caches are removed, so 
any changes you make are visible right away. Also, you don't have to reboot the 
server as often in Developer Mode.

How does Developer Mode let you see your changes more quickly? By default,
@product@ is optimized for performance. Developer mode optimizes your
configuration for development instead. Here is a list of Developer Mode's key
behavior changes and the 
[Portal Property](https://docs.liferay.com/portal/7.1/propertiesdoc/portal.properties.html)
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
quickly. These developer settings are defined in the 
[`portal-developer.properties` file](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-impl/src/portal-developer.properties). 
To use these settings, you can include them in your `portal-ext.properties` file 
or copy them over to your `portal-ext.properties` file and override specific 
properties as needed. These configurations are covered in this tutorial.

First, you can explore how it's done in @ide@. 

## Setting Developer Mode for Your Server in @ide@ [](id=setting-developer-mode-for-your-server-in-liferay-ide)

To enable Developer Mode for your server in @ide@, follow these steps: 
 
1.  Double-click on your server in the *Servers* window and open the 
    *Liferay Launch* section.
 
2.  Select *Custom Launch Settings* and check the *Use developer mode* option. 

3.  Save the changes and start your server.

![Figure 1: The *Use developer mode* option lets you enable Developer Mode for your server in @ide@.](../../../images/developer-mode-ide.png)

+$$$

**Warning:** Only change the Server settings from the runtime environment's 
Liferay Launch section.

$$$

When starting your server for the first time, it creates a 
`portal-ext.properties` file in your server's directory. This properties file 
contains the property setting `include-and-override=portal-developer.properties`, 
which enables Developer Mode. Most of the configuration is provided by the 
`portal-developer.properties` file, but you still have to configure the 
FreeMarker template setting. Follow the steps in the 
[Configuring FreeMarker System Settings](/develop/tutorials/-/knowledge_base/7-1/using-developer-mode-with-themes#configuring-freemarker-system-settings) 
section to configure the FreeMarker template cache. 

If you're not using @ide@, manual configuration for Developer Mode is covered 
next.

## Setting Developer Mode for Your Server Using portal-developer.properties [](id=setting-developer-mode-for-your-server-using-portal-developer-properties)

To set Developer Mode manually, you must point to `portal-developer.properties` 
as shown in the last section. Add the `portal-ext.properties` file to the root 
folder of your app server's bundle and add the following line:

    include-and-override=portal-developer.properties
 
Developer Mode is enabled upon starting your app server. 
`portal-developer.properties` provides the majority of the settings you'll need 
for smooth development. To disable the cache for FreeMarker templates, you must 
update the System Setting covered in the next section. 

## Configuring FreeMarker System Settings [](id=configuring-freemarker-system-settings)

FreeMarker Templates for themes and web content are cached by default. 
Therefore, any changes you make to your FreeMarker theme templates aren't 
immediately displayed. You can change this behavior through System Settings. 
Follow these steps: 

1.  Open the Control Panel and go to *Configuration* &rarr; *System Settings*.

2.  Click the *Foundation* tab and select *FreeMarker Engine*.

3.  By default, the *Resource modification check* (the time in milliseconds that
    the template is cached) is set to `60`. Set this value to `0` to disable
    caching.

Your FreeMarker templates are ready for development. Next you can learn how you 
can improve JavaScript file loading for development. 

## JavaScript Fast Loading [](id=javascript-fast-loading)

By default, JavaScript fast loading is enabled in Developer Mode 
(`javascript.fast.load=true`). This loads the packed version of files listed in 
the 
[Portal Properties](https://docs.liferay.com/portal/7.1/propertiesdoc/portal.properties.html#JavaScript) 
`javascript.barebone.files` or `javascript.everything.files`. You can, however, 
disable JavaScript fast loading for easier debugging for development. Just set 
`javascript.fast.load` to `false` in your `portal.properties`, or you can 
disable fast loading by setting the URL parameter `js_fast_load` to `0`.

+$$$

**Note:** JavaScript fast loading is retrieved from one of three places: the 
request (determined by the current URL: 
`http://localhost:8080/web/guest/home?js_fast_load=1`(on) or 
`...?js_fast_load=0`(off)), the Session, or the Portal Property 
(`javascript.fast.load=true`). Preference is given in the order of request, 
session, and then Portal Properties. This lets you change `js_fast_load`'s value 
from the default in `portal.properties` without having to manually re-enter 
`js_fast_load` into the URL upon every new page load.

$$$

Great! You've set up your server for Developer Mode. Now, when you modify your 
theme's file directly in your bundle, you can see your changes applied 
immediately on redeploying your theme!

## Related Topics [](id=related-topics)

[Creating Layout Templates Manually](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually)

[Creating Themes with @ide@](/develop/tutorials/-/knowledge_base/7-1/creating-themes-with-liferay-ide)
