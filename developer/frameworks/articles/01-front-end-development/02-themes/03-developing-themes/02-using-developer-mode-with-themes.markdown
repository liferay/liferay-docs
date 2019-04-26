---
header-id: using-developer-mode-with-themes
---

# Using Developer Mode with Themes

[TOC levels=1-4]

This article shows how to enable Developer Mode in your app server manually and 
through @ide@, as well as how to configure other settings that may benefit you 
during development. Each topic is explained in the relevant section below. 

## Enabling Developer Mode Manually

Follow these steps to enabled Developer Mode in your app server manually:

1.  Create a `portal-ext.properties` file in your server's root folder if it 
    doesn't exist.
    
2.  Add the line below to it:

    ```properties
    include-and-override=portal-developer.properties
    ```
    
    Alternatively, add the properties from the 
    [portal-developer.properties](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/portal-developer.properties) 
    file to your `portal-ext.properties` file that you want to use.
    
3.  Start your app server to apply the changes.

Read the next section to learn how to enable Developer Mode in @ide@. 

## Setting Developer Mode in @ide@

Follow these steps to enable Developer Mode for your app server in @ide@:
 
1.  Double-click on your server in the *Servers* window and open the 
    *Liferay Launch* section.
 
2.  Select *Custom Launch Settings* and check the *Use developer mode* option. 

3.  Save the changes and start your server. 

![Figure 1: The *Use developer mode* option lets you enable Developer Mode for your server in @ide@.](../../../../images/developer-mode-ide.png)

| **Warning:** Only change the Server settings from the runtime environment's 
| Liferay Launch section.

## Configuring FreeMarker System Settings

By default, FreeMarker theme templates and web content templates are cached. You 
can change this behavior through System Settings with the steps below:

1.  Open the Control Panel and go to *Configuration* &rarr; *System Settings*.

2.  Select *Template Engines* under the *PLATFORM* heading.

3.  By default, the *Resource modification check* (the time in milliseconds that
    the template is cached) is set to `60000`. Set this value to `0` to disable
    caching.

Your FreeMarker templates are ready for development. Next you can learn how to 
improve JavaScript file loading for development. 

## JavaScript Fast Loading

By default, JavaScript fast loading is enabled in Developer Mode 
(`javascript.fast.load=true`). This loads the packed version of files listed in 
the 
[Portal Properties](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#JavaScript) 
`javascript.barebone.files` or `javascript.everything.files`. You can, however, 
disable JavaScript fast loading for easier debugging for development. Just set 
`javascript.fast.load` to `false` in your `portal.properties`, or you can 
disable fast loading by setting the URL parameter `js_fast_load` to `0`.

| **Note:** JavaScript fast loading is retrieved from one of three places: the 
| request (determined by the current URL: 
| `http://localhost:8080/web/guest/home?js_fast_load=1`(on) or 
| `...?js_fast_load=0`(off)), the Session, or the Portal Property 
| (`javascript.fast.load=true`). Preference is given in the order of request, 
| session, and then Portal Properties. This lets you change `js_fast_load`'s 
| value from the default in `portal.properties` without having to manually 
| re-enter `js_fast_load` into the URL upon every new page load.

Great! You've set up your server for Developer Mode. Now, when you modify your 
theme's file directly in your bundle, you can see your changes applied 
immediately on redeploying your theme!

## Related Topics

- [Generating Layout Templates with the Theme Generator](/developer/reference/-/knowledge_base/7-2/creating-layout-templates-with-the-themes-generator)
- [Creating Themes with @ide@](/developer/reference/-/knowledge_base/7-2/creating-themes-with-liferay-ide)
