---
header-id: front-end-development
---

# Front-End Development

[TOC levels=1-4]

You have complete front-end development freedom. You can use @product@'s 
front-end frameworks, along with the front-end technologies you love the most:

-   EcmaScript ES2015+
-   React, Angular, Vue, etc.
-   [Metal.js](https://metaljs.com/) (developed by Liferay)
-   [AlloyUI](https://alloyui.com/) (developed by Liferay)
-   jQuery (included)
-   Lodash (included, but disabled by default)

To load modules, you must know when they are needed, where they are at build
time, whether they should be bundled together or loaded independently, and
you must assemble them at runtime. Liferay's Loaders (YUI/AUI, AMD, and npm in
AMD format) handle loading for you. All you must do is provide a small bit of
information about your module. 

The Liferay JS Bundle Toolkit(the 
[JS Portlet Extender](https://web.liferay.com/marketplace/-/mp/application/115542926), 
[Liferay Bundle Generator](https://www.npmjs.com/package/generator-liferay-bundle), 
and 
[`liferay-npm-bundler`](/docs/7-2/reference/-/knowledge_base/r/liferay-npm-bundler)
) has the tools you need to create and develop JavaScript portlets with pure 
JavaScript tooling. You can use the 
[`liferay-npm-bundler`](/docs/7-2/reference/-/knowledge_base/r/liferay-npm-bundler)
to bundle npm packages in your applications. It even has several presets for 
common module types (AMD, React, Angular JS,  etc.) to save you time. It creates 
an OSGi bundle for you, extracts all npm dependencies, and transpiles your code 
for the Liferay AMD Loader. 

While developing JavaScript applications, you may need to access 
@product@-specific information or web services. The `Liferay` global JavaScript 
Object 
[exposes this information for you](/docs/7-2/frameworks/-/knowledge_base/f/liferay-javascript-apis), 
to use in your JavaScript applications. 

## Lexicon and Clay

Liferay uses its own design language, called 
[Lexicon](https://lexicondesign.io/docs/lexicon/), to provide a common framework 
for building consistent UIs and user experiences across the Liferay product 
ecosystem. The web implementation of Lexicon (CSS, JS, and HTML) is called 
[Clay](https://clayui.com/docs/get-started/introduction.html). 
It is automatically available to application developers through a set of CSS 
classes or our 
[tag library](/docs/7-2/frameworks/-/knowledge_base/f/using-the-clay-taglib-in-your-portlets). 

## Templates

For templating, you can use Java EE's JSP, FreeMarker, Google's 
[Soy (aka Closure Templates)](/docs/7-2/frameworks/-/knowledge_base/f/liferay-soy-portlet), 
or whatever else you like. 

## Themes

Themes use the standard components (CSS, JavaScript, and HTML) along with 
FreeMarker templates. Although the default themes are nice, you may wish to 
create your own look and feel for your site. The 
[Liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages) 
has all the tools you need to create and develop themes, but you can use the 
tools you prefer.

From the 
[Theme Builder Gradle Plugin](/docs/7-2/reference/-/knowledge_base/r/theme-builder-gradle-plugin), 
to the 
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme), 
to 
[@ide@](/docs/7-2/frameworks/-/knowledge_base/f/creating-themes-with-liferay-ide), 
to 
[Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli)'s 
[Theme Template](/docs/7-2/reference/-/knowledge_base/r/theme-template), you 
can choose the development tools you like best, so you can focus on creating 
a well designed theme. 

## Front-End Extensions

@product@'s modularity has many benefits for the front-end developer, in the 
form of development customizations and extension points. These extensions assure 
the stability, conformity, and future evolution of your applications. 

Below are some of the available front-end extensions:

- [Theme Contributors](/docs/7-2/frameworks/-/knowledge_base/f/packaging-independent-ui-resources-for-your-site)
- [Context Contributors](/docs/7-2/frameworks/-/knowledge_base/f/injecting-additional-context-variables-into-your-templates)
- [Creating Configurable Styles for Portlet Wrappers](/docs/7-2/frameworks/-/knowledge_base/f/creating-configurable-styles-for-portlet-wrappers)
- [Dynamic Includes](/docs/7-2/frameworks/-/knowledge_base/f/adding-new-behavior-to-an-editor)

See 
[Theme Components](/docs/7-2/frameworks/-/knowledge_base/f/theme-components) 
and 
[Understanding the Page Layout](/docs/7-2/frameworks/-/knowledge_base/f/understanding-the-page-layout) 
for more information. 
