---
header-id: understanding-ui-concepts
---

# Understanding UI Concepts

[TOC levels=1-4]

You most likely have established workflows, tools, and frameworks that you rely
on to develop your UI. You can carry your toolset with you over to @product@. To
ensure that your transition is as smooth as possible, @product@ provides
first-class integration with these key frameworks: 
[React](/docs/7-2/appdev/-/knowledge_base/a/developing-a-react-application), 
[Angular](/docs/7-2/appdev/-/knowledge_base/a/developing-an-angular-application), 
and [Vue.js](/docs/7-2/appdev/-/knowledge_base/a/developing-a-vue-application). 
Liferay also provides our own tools to increase your productivity while 
developing for @product@. If you don't have an established workflow, we have 
you covered, from design principles to the CSS framework. 

## Design Philosophy

Liferay takes the [Atomic Design](http://atomicdesign.bradfrost.com/) approach 
to interface design, making it possible to create a cohesive system, built of 
modular components, that can respond to the various needs of the user. By doing 
so, we increase productivity, reduce production cost, and create a consistent
user experience across the board. This approach is the basis for their Design
Experience Language: [Lexicon](https://liferay.design/lexicon/). 

## Lexicon

Lexicon provides a common framework for building interfaces within the Liferay 
product ecosystem. It provides foundations, components, patterns, and use cases 
for creating consistent, cohesive, user-friendly user interfaces. It defines the
guidelines for everything from the base unit grid on which an element's
dimensions are designed to the design of the forms users use when choosing
a screen name and entering their email addresses. It is the common thread woven
throughout the entire UI. 

![Figure 1: The base grid is the main structural pattern that underlies the construction and positioning of all system components.](../../images/lexicon-grid-01.png)

![Figure 2: UI elements are arranged within the grid. ](../../images/lexicon-grid-02-form.jpg)

![Figure 3: Lexicon's base grid provides the foundation for an element's dimensions.](../../images/lexicon-grid-03-form.jpg)

## Clay

The web implementation of Lexicon, used to build the standard 
UI that you see throughout @product@, is called [Clay](https://clayui.com/docs/get-started/introduction.html). 
Clay is modeled after Lexicon's pattern library, ensuring that your UI has 
a consistent style and user experience when integrated with Liferay's 
out-of-the-box applications. Clay is a fully featured library of modular 
components, built on top of the world's most popular CSS framework: Bootstrap. 
If you've used Bootstrap, Clay should look familiar. Clay fills the front-end
gaps between Bootstrap and the needs of @product@, adding additional components,
such as Nameplates and Timelines, and providing helpful CSS patterns to save you
time, such as truncating text and nested vertical navigations. 

![Figure 4: Clay is based on the rules of Lexicon and built on the foundation of Bootstrap.](../../images/architecture-ui-clay.png)

Clay is implemented in various ways, depending on the needs of the developer:

- Markup: Like Bootstrap, Clay is available as [HTML markup and CSS classes](https://clayui.com/docs/components/alerts.html).

- Clay JS components: You can use Clay components in your JavaScript and JSX 
  files. Note that you must [manually install the npm packages](https://clayui.com/docs/get-started/importing-the-js-component.html) 
  to use them. All Clay components can be installed as a single npm package, or 
  each component can be installed individually, depending on the developer's stack. 

- Taglibs: Clay is [available as a set of taglibs](/docs/7-2/reference/-/knowledge_base/r/using-the-clay-taglib-in-your-portlets) 
  that can be used to create user interfaces inside the JSPs of Java-based
  portlets. These are also exposed as a set of macros that can be used in
  FreeMarker theme templates and web content templates to create UI. 

The true power of Clay lies in its flexibility. It can be used to create elegant 
solutions to common UI problems, from a simple button to a complex management 
toolbar, composed of multiple menu variations, buttons, tabs, tables, and more. 
The Clay components are the building blocks that, if you choose, you can use to
design your next masterpiece. 

![Figure 5: Clay can create basic UI, such as buttons.](../../images/architecture-ui-clay-button.png)

![Figure 6: Clay can create complex UI, composed of multiple UI components.](../../images/architecture-ui-clay-management-toolbar.png)

## Tooling

You can of course use your own tools to build your UI if you wish, but Liferay 
provides a fully-featured toolset designed for use with @product@ and built with
the developer in mind. Leverage our investments to save you time and increase
your productivity during development. The tools are fully integrated with npm
and yarn and run from the comfort of your CLI. 

These tools are available:

- Liferay JS Toolkit: While portlets are Java-based, the Liferay JS Toolkit 
  abstracts this for you and [creates JavaScript-based portlets](/docs/7-2/reference/-/knowledge_base/r/js-generator) 
  (Vanilla JS, Angular, React, Vue) with pure JavaScript tooling. No knowledge 
  of Java is required. You can specify System Settings, provide language keys, 
  and take advantage of automatic translation features. 

  ![Figure 7: The Liferay JS Toolkit creates portlets with pure JS tooling.](../../images/architecture-ui-liferay-js.png)

- Liferay Themes JS Toolkit: Contains everything you need to create themes to
  provide a custom look and feel for your site's pages. You can [generate
  themes](/docs/7-2/reference/-/knowledge_base/r/theme-generator) 
  (fully integrated with npm, so you can use all your favorite npm packages), 
  [create layouts](/docs/7-2/reference/-/knowledge_base/r/creating-layout-templates-with-the-themes-generator) 
  to specify how your elements can be arranged on the page, and 
  create modular code chunks, called [Themelets](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator), 
  that you can reuse in your themes and share with other developers. 

  ![Figure 8: The Liferay Themes JS Toolkit creates themes that are integrated with npm and gulp.](../../images/architecture-ui-theme-gen.png)

- Fragments Editor: Do you prefer to develop and test your UI in an online 
  editor? You can use Liferay's online editor to develop
  *fragments*---standalone pieces of CSS, HTML and JS. Once you've 
  [whipped up some fragments](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/page-fragments)
  that you're happy with, you can use them to build a content page on your site.
  Combine and arrange them to create multiple page configuration options. The 
  sky's the limit! 

  ![Figure 9: The Fragments editor is a set of tools that let you create independent pieces of code made of CSS, HTML, and JS.](../../images/architecture-ui-fragments.png)

Liferay provides the tools and freedom you need to create functional, 
user-friendly, and well-designed UIs. How you choose to implement and combine these 
tools---or whether you choose to use them at all---is up to you. The power is
yours! 
