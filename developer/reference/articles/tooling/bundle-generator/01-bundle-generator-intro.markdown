---
header-id: bundle-generator
---

# Bundle Generator

[TOC levels=1-4]

The Liferay Bundle Generator generates JavaScript widgets for @product@. It is 
just one of Liferay JS Bundle Toolkit's 
[tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages). 

| **Note:** To use the Liferay Bundle Generator, you must have the Liferay JS 
| Portlet Extender activated in your @product@ instance. It's activated by 
| default. You can confirm this by opening the Control Menu, navigating to the 
| *App Manager*, and searching for `com.liferay.frontend.js.portlet.extender`.

Portlets are a Java standard, so you must have a knowledge and understanding of 
how Java works to write one. This can be quite the hurdle for front-end 
developers who want to use JavaScript frameworks in their portlets. Thanks to 
the JS Portlet Extender and 
[liferay-npm-bundler](/docs/7-2/reference/-/knowledge_base/r/liferay-npm-bundler), 
developers can easily create and develop JavaScript portlets in @product@ using 
pure JavaScript tooling. 

![Figure 1: The JS Portlet Extender lets you use pure JavaScript tooling to write portlets.](../../../images/extender-lifecycle.png)

| **Note:** JavaScript Server-Side-Rendering is not supported out-of-the-box. To
| use JS frameworks for site rendering, you **must** set up your server-side
| (or search-crawler) rendering generation to support them.

This section covers these topics:

- How to install the Liferay Bundle Generator and use it to create a JS portlet
- A reference list of available commands for the Liferay Bundle Generator
