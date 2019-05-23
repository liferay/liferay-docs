---
header-id: developing-themes
---

# Developing Themes

[TOC levels=1-4]

Theme projects created using the 
[Liferay Theme Generator](/docs/7-2/frameworks/-/knowledge_base/f/creating-themes-with-the-liferay-theme-generator) 
have access to several 
[gulp](https://www.npmjs.com/package/gulp) 
tasks you can execute to manage and develop your theme. This section covers the 
available actions that these tasks provide, as well as other information you may 
find useful while developing your theme. 

This section covers these topics:

- Using liferay theme tasks (build, deploy, extend, init, kickstart, status, and watch)
- Using Developer Mode
- Creating thumbnail previews for your theme
- Creating color schemes for your theme
- Making configurable theme settings

While developing your theme, you may notice that your theme's CSS and JS files 
are minified. This optimizes performance, but it can make debugging difficult 
during development. Developer Mode, disabled by default, optimizes development 
instead. For instance, it loads CSS and JS files individually for easier 
debugging. Also, you don't have to reboot the server as often in Developer Mode. 
Here is a list of Developer Mode's key behavior changes and the 
[Portal Property](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html)
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
[`portal-developer.properties` file](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/portal-developer.properties). 
See 
[Using Developer Mode with Themes](/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes) 
to learn how to enable Developer Mode in your app server. You can also use the 
[Gulp Watch task](/docs/7-2/frameworks/-/knowledge_base/f/automatically-deploying-theme-changes) 
to test theme changes on a proxy port before deploying your theme to your 
server. 
