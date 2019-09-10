---
header-id: creating-and-bundling-javascript-widgets-with-javascript-tooling
---

# Creating and Bundling JavaScript Widgets with JavaScript Tooling

[TOC levels=1-4]

Portlets are a Java standard, so you must have a knowledge and understanding of 
how Java works to write one. This can be quite the hurdle for front-end 
developers who want to use JavaScript frameworks in their widgets. Thanks to 
the Liferay JS Generator and 
[liferay-npm-bundler](/docs/7-2/reference/-/knowledge_base/r/liferay-npm-bundler), 
developers can easily create and develop JavaScript widgets in @product@ using 
pure JavaScript tooling. The Liferay JS Generator generates JavaScript widgets 
for @product@. It is just one of Liferay JS Toolkit's 
[tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages). 

| **Note:** To use the Liferay JS Generator, you must have the Liferay JS 
| Portlet Extender activated in your @product@ instance. It's activated by 
| default. You can confirm this by opening the Control Menu, navigating to the 
| *App Manager*, and searching for `com.liferay.frontend.js.portlet.extender`. 

![Figure 1: The JS Portlet Extender lets you use pure JavaScript tooling to write widgets.](../../../images/extender-lifecycle.png)

| **Note:** JavaScript Server-Side-Rendering is not supported out-of-the-box. To
| use JS frameworks for site rendering, you **must** set up your server-side
| (or search-crawler) rendering generation to support them.

Once you've 
[installed the Liferay JS Generator and generated a widget](/docs/7-2/reference/-/knowledge_base/r/installing-the-js-generator-and-generating-a-bundle), 
you can configure instance settings, system settings, and even provide 
localization for your widget. This section explains how to configure these 
options for your generated JS widget. 
