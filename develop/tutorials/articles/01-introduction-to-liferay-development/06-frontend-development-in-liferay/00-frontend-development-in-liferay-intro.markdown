# Introduction to Front-End Development [](id=introduction-to-frontend-development)

@product@ offers complete developer front-end freedom. Whether you like coding
JavaScript by hand, have used Liferay's front-end frameworks in the past, or
prefer jQuery, Lodash, or modules, you can use your front-end framework of
choice. 

Prior users of @product@ can continue to use Liferay's venerable Alloy UI, but
you are also free to use the front-end technologies you love the most:

-   EcmaScript ES2015+
-   [Metal.js](https://metaljs.com/) (developed by Liferay)
-   [AlloyUI](https://alloyui.com/) (developed by Liferay)
-   jQuery (included)
-   Lodash (included)

To load modules, you must know when they are needed, where they are located at
build time, if you want to bundle them together or load them independently, and
you must assemble them at runtime. Keeping track of these tasks can be a hassle.
@product@'s Loaders (YUI/AUI, AMD, and npm in AMD format) handle loading for
you, so you don't have to worry about all the details. Just provide a small bit
of information about your module and @product@'s loaders take care of the rest.

You can use npm in your portlets. To do this, you must create an OSGi 
bundle with all the npm dependencies extracted and modified to work with the 
Liferay AMD Loader. @product@'s `liferay-npm-bundler` is built for just 
this purpose, and even provides several presets for common module types 
(AMD, React, Angular JS,  etc.) to save you time. 

If you include the `liferay-npm-bundler` in your project, with just a small
amount of configuration, the `liferay-npm-bundler` bundles your portlet for OSGi
and transpiles your code for the Liferay AMD Loader, which lets you share
JavaScript modules and take advantage of semantic versioning among portlets that
use the same npm modules on the page.

## Lexicon and Clay [](id=lexicon)

@product@ uses its own design language, called 
[Lexicon](https://lexicondesign.io/docs/lexicon/), to provide a common framework 
for building consistent UIs and user experiences across the Liferay product 
ecosystem. The web implementation of Lexicon (CSS, JS, and HTML) is called 
[Clay](https://claycss.com/docs/clay/). It is automatically available to 
application developers through a set of CSS classes or our tag library. 

## Templates [](id=templates)

For templating, you can use Java EE's JSP as well as FreeMarker, but the
platform's modularity enables using Google's Soy (aka Closure Templates), or
whatever else you like. 

## Themes [](id=themes)

A @product@ Theme provides the overall look and feel for a site. Themes are a
combination of CSS, JavaScript, HTML, and FreeMarker templates. Although the
default themes are nice, you may wish to create your own look and feel for your
site.

From the 
[Theme Builder Gradle Plugin](/develop/reference/-/knowledge_base/7-1/theme-builder-gradle-plugin), 
to the 
[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator), 
to 
[@ide@](/develop/tutorials/-/knowledge_base/7-1/creating-themes-with-liferay-ide), 
to 
[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)'s 
[Theme Template](/develop/reference/-/knowledge_base/7-1/theme-template),
@product@ lets choose the development tools you're most comfortable with so you
can focus on creating a well designed theme.

## Front-End Extensions [](id=frontend-extensions)

@product@'s modularity has many benefits for the front-end developer, in the
form of development customizations and extension points. These extensions assure
the stability, conformity, and future evolution of your applications.

Below are some of the available front-end extensions:

- [Theme Contributors](/develop/tutorials/-/knowledge_base/7-1/theme-contributors)
- [Context Contributors](/develop/tutorials/-/knowledge_base/7-1/context-contributors)
- [Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/portlet-decorators)
- [Editor Config Contributors](/develop/tutorials/-/knowledge_base/7-1/modifying-an-editors-configuration)

Read on to learn more. 
