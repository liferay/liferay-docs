---
header-id: creating-and-bundling-javascript-portlets-with-javascript-tooling
---

# Creating and Bundling JavaScript Widgets with JavaScript Tooling

[TOC levels=1-4]

The Liferay JS Generator generates JavaScript widgets for @product@. It is 
just one of Liferay JS Bundle Toolkit's 
[tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages). 

| **Important:** To use the Liferay JS Generator, you must have the Liferay JS
| Portlet Extender installed in your @product@ instance. The JS Portlet Extender
| is a Labs application available from Liferay Marketplace for
| [Liferay Digital Enterprise 7.1](https://web.liferay.com/marketplace/-/mp/application/115543020)
| and
| [Liferay Portal CE 7.1](https://web.liferay.com/marketplace/-/mp/application/115542926).
| Apps designated as Labs are experimental and not supported by Liferay. They're
| released to accelerate the availability of useful and cutting-edge features.
| This status may change without notice. Please download and use Labs apps at your
| own discretion.

Portlets are a Java standard, so you must have a knowledge and understanding of 
how Java works to write one. This can be quite the hurdle for front-end 
developers who want to use JavaScript frameworks in their widgets. Thanks to 
the JS Portlet Extender and 
[liferay-npm-bundler](/docs/7-1/reference/-/knowledge_base/r/liferay-npm-bundler), 
developers can easily create and develop JavaScript widgets in @product@ using 
pure JavaScript tooling. 

![Figure 1: The JS Portlet Extender lets you use pure JavaScript tooling to write widgets.](../../../images/extender-lifecycle.png)

| **Note:** JavaScript Server-Side-Rendering is not supported out-of-the-box. To
| use JS frameworks for site rendering, you **must** set up your server-side
| (or search-crawler) rendering generation to support them.

This section explains how to configure these options for generated JS widgets: 

- Installing the Liferay JS Generator and generating a bundle
- Configuring system and instance settings
- Localization
- Setting portlet properties
- Using translation features
