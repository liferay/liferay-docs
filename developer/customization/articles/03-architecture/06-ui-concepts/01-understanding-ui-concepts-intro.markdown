---
header-id: understanding-ui-concepts
---

# Understanding UI Concepts

[TOC levels=1-4]

The page's User Interface is comprised of three key pieces: 

- **The Theme:** Defines the overall look and feel of the site, its pages, 
  navigation, header, footer, etc
- **The Applications:** Applications (sometimes referred to as widgets or 
  portlets) that provide additional functionality on the page and each have 
  their own UI
- **The Content:** Web Content Articles, Knowledge Base articles, Blogs, Assets, 
  page fragments, or other content created with @product@'s Content Management 
  System (CMS) that forms the core content of the page

See [Understanding the Page Layout](/docs/7-2/frameworks/-/knowledge_base/f/understanding-the-page-layout) 
for more information on how the page is composed. You can of course use your own 
tools to build your UI if you wish, but Liferay provides a fully-featured 
toolset designed for use with @product@ and built with the developer in mind. 
Each key piece of the UI, along with the available tools, is explained below. 

### The Theme

Themes create the look and feel for the site and are primarily built with 
[FreeMarker](https://freemarker.apache.org/) templates, [SASS](https://sass-lang.com/), 
and JavaScript. 

FreeMarker templates combine standard HTML markup and FreeMarker 
syntax, so you can use variables and [macros](https://freemarker.apache.org/docs/ref_directive_macro.html) 
in your themes. [@product@'s default set](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/product-freemarker-macros) 
gives you access to common Java objects, Java [taglibs](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/freemarker-taglib-macros), 
theme resources, and even applications that you can include in your theme. You 
can extend this further by [injecting your own Java objects](/docs/7-2/frameworks/-/knowledge_base/f/injecting-additional-context-variables-and-functionality-into-your-theme) 
into the FreeMarker template's context. You can also [create theme settings](/docs/7-2/frameworks/-/knowledge_base/f/making-configurable-theme-settings) 
to give an administrator control from within the @product@ instance to disable, 
enable, or define values for specific UI elements. You can 
[create color schemes](/docs/7-2/frameworks/-/knowledge_base/f/making-configurable-theme-settings) 
for the theme as well, if the occasion calls for it. @product@'s 
[default set of theme templates](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide) 
cover different functions and features of the page, from the main markup to the 
navigation markup, portlet markup, and custom variables you want to use. 
Modularizing the theme templates this way provides a separation of concerns that 
lets you focus on each aspect of the theme individually. 

Themes are integrated with SASS, so you can use variables, as well as SASS 
syntactic features, such as nesting, in your CSS files. Liferay provides its own 
toolkit called [Clay](https://clayui.com/), which is built on Bootstrap, so you 
can use Bootstrap utilities as well. Clay fills the front-end gaps between 
Bootstrap and the needs of @product@, adding additional components that you can 
use to build UI in your themes and applications, such as Nameplates and 
Timelines, and providing helpful CSS patterns to save you time, such as 
truncating text and nested vertical navigations. 

![Figure 1: Clay is built on the foundation of Bootstrap.](../../../images/architecture-ui-clay.png)

While the FreeMarker theme templates provide the main look for all pages, the 
pages for the site are [created inside @product@'s configuration](/docs/7-2/user/-/knowledge_base/u/creating-and-managing-pages). 
The navigation theme template, by default, dynamically grabs the pages and 
populates the navigation. Other aspects of the look and feel for the site can 
also be configured within @product@. The logo and site title (if displayed) are, 
by default, modified through the [theme's configuration](/docs/7-2/user/-/knowledge_base/u/page-set-look-and-feel). 
You can also include additional CSS and JavaScript through this configuration 
menu to load after the theme is loaded. 

There are a couple mechanisms you can use to extend themes. You can install 
modular pieces of UI (CSS and JS) into your theme, called [Themelets](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator), 
that can then be reused in other themes, or you can deploy independent UI 
resources, called [Theme Contributors](/docs/7-2/frameworks/-/knowledge_base/f/packaging-independent-ui-resources-for-your-site), 
that affect every page regardless of the theme, such as the [Product Navigation](/docs/7-2/frameworks/-/knowledge_base/f/understanding-the-page-layout#product-navigation-sidebars-and-panels) 
that is visible on every page. The Product Navigation UI itself can be 
[customized](/docs/7-2/customization/-/knowledge_base/c/product-navigation) if 
you want it to match the look and feel of your theme. 

Liferay provides their own [Liferay Themes JS Toolkit](https://github.com/liferay/liferay-js-themes-toolkit) 
that you can use to develop themes, but you may use your own tools if you 
prefer. The Liferay Themes JS Toolkit contains everything you need to provide a 
custom look and feel for your site's pages. You can [generate themes](/docs/7-2/reference/-/knowledge_base/r/theme-generator) 
(fully integrated with npm, so you can use all your favorite npm packages), 
[create layouts](/docs/7-2/reference/-/knowledge_base/r/creating-layout-templates-with-the-themes-generator) 
to specify how your elements can be arranged on the page, and create [Themelets](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator) 
that you can reuse in your themes and share with other developers. 

![Figure 2: The Liferay Themes JS Toolkit creates themes that are integrated with npm and gulp.](../../../images/architecture-ui-theme-gen.png)

For more information on the components of a theme see [Theme Components](/docs/7-2/frameworks/-/knowledge_base/f/theme-components).

## The Applications

Applications (sometimes referred to as Portlets or Widgets) in @product@ are 
Java-based, and you can use java-based tooling, such as [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli), 
taglibs, and [JSPs to create them](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer) 
if you prefer, but @product@ provides Front-End tools that you can use to 
[build widgets](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends) as well. The 
[Liferay JS Toolkit](/docs/7-2/reference/-/knowledge_base/r/js-generator) 
abstracts the Java code for you and [creates JavaScript-based portlets](/docs/7-2/frameworks/-/knowledge_base/f/creating-and-bundling-javascript-widgets-with-javascript-tooling) 
with [pure JavaScript tooling](/docs/7-2/reference/-/knowledge_base/r/js-generator). 
No knowledge of Java is required. You can specify System Settings, provide 
language keys, and take advantage of automatic translation features for 
localization. @product@ provides first-class integration with [React](/docs/7-2/appdev/-/knowledge_base/a/developing-a-react-application), 
[Angular](/docs/7-2/appdev/-/knowledge_base/a/developing-an-angular-application), 
and [Vue.js](/docs/7-2/appdev/-/knowledge_base/a/developing-a-vue-application), 
so you can use the most popular frameworks to build your widget's UI. You can 
also use Liferay's Clay components to create elegant solutions to common UI 
problems, from a simple button to a complex management toolbar composed of 
multiple menu variations, buttons, tabs, tables, and more. 

![Figure 3: The Liferay JS Toolkit creates portlets with pure JS tooling.](../../../images/architecture-ui-liferay-js.png)

You can individually style a widget's UI through the app's code of course, but 
you can also use a [Widget Template](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates), 
or you can customize its navigation UI using the [Screen Navigation Framework](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework), 
or you can even [style the widget from within its configuration menu](/docs/7-2/user/-/knowledge_base/u/look-and-feel-configuration) 
in @product@. You can customize the exterior wrappers of widgets (the portlet 
header, footer, border, etc.) with [Application Decorators](/docs/7-2/frameworks/-/knowledge_base/f/theming-portlets#portlet-decorators). 
If you want to customize the look and feel for all widgets, you can modify the 
[portlet theme template](/docs/7-2/frameworks/-/knowledge_base/f/theming-portlets) 
directly. 

While most widgets are meant for the end-user, some widgets can only be 
accessed with the proper credentials, through the Product Navigation. Note that 
widget's are manually positioned on the page within the bounds of the 
[Layout defined for the page](/docs/7-2/frameworks/-/knowledge_base/f/layout-templates-intro). 
Widget positions can also be predefined with [page templates](/docs/7-2/user/-/knowledge_base/u/creating-widget-pages-from-templates) 
and [site templates](/docs/7-2/user/-/knowledge_base/u/creating-a-site-template). 

## The Content

Some of your pages, or perhaps all of them, will most likely be content-driven. 
While you can define the page's content in the theme, @product@'s Content 
Management System (CMS) provides inline editors and applications for creating, 
displaying, and collaborating on content, such as web content articles, the 
Knowledge Base, Documents and Media, blogs, the Asset Publisher, Wikis, Message 
Boards and many more. See the [User Guide]([User Guide](/docs/7-2/user) for more 
information on using @product@'s CMS. 

If you prefer to build UI components independent from a theme or its extensions 
(Themelets and Theme Contributors), you can [use @product@'s inline editor](/docs/7-2/frameworks/-/knowledge_base/f/creating-fragments), 
or your own [desktop tools along with the Liferay JS Toolkit CLI](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments-desktop-tools) 
if you prefer, to build [Page Fragments](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/page-fragments). 
Fragments are composed of HTML, CSS, and JS. Once you have a collection of 
fragments, you can combine, arrange, and edit them on the fly to build multiple 
page configurations. 

![Figure 4: The Fragments editor lets you create independent pieces of code made of CSS, HTML, and JS that you can combine to build a page.](../../../images/architecture-ui-fragments.png)

As you can see, @product@ provides multiple ways to create your site's UI, it 
just depends on your needs and preferred workflow as to what approach you take. 
