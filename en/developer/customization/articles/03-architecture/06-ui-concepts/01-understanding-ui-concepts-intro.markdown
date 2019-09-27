---
header-id: understanding-ui-concepts
---

# Understanding UI Concepts

[TOC levels=1-4]

The page's User Interface is comprised of three key areas: The Theme, which
defines the overall look and feel for the page; the applications (sometimes
referred to as portlets or widgets) that provide additional functionality on the
page; and the content (the articles, images, etc.) on the page. @product@'s UI
architecture contains these components:

## Theme

- **A Powerful Markup Templating Language:** The theme's markup is written in
  [FreeMarker](https://freemarker.apache.org/), which combines standard HTML
  markup and FreeMarker syntax, so you can use variables and [macros](https://freemarker.apache.org/docs/ref_directive_macro.html)
  in your themes.
  [@product@'s default macros](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/product-freemarker-macros)
  give you access to common Java objects, Java [taglibs](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/freemarker-taglib-macros),
  theme resources, and even applications that you can include in your theme.

- Theme Templates
  @product@'s
  [default theme templates](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide)
  cover different functions and features of the page, from the main markup to
  the navigation markup, portlet markup, and custom variables you want to use.

- **Modular Theme Extensions:** Independent pieces of code that you can swap in
  and out or stack to create UI or add functionality. This includes small,
  reusable pieces published to npm called [Themelets](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator),
  permanent UI that affects every page, such as the [Product Navigation](/docs/7-2/frameworks/-/knowledge_base/f/understanding-the-page-layout#product-navigation-sidebars-and-panels),
  called [Theme Contributors](/docs/7-2/frameworks/-/knowledge_base/f/packaging-independent-ui-resources-for-your-site),
  and pieces of UI created in the Portal instance or CLI called [Page Fragments](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments).

- **Configurable Theme Features:** Rather than modify theme templates directly
  each time a change is required, an Administrator can [configure](/docs/7-2/user/-/knowledge_base/u/page-set-look-and-feel)
  aspects of the theme through the Portal instance. Some options are
  configurable by default, such as the [pages](/docs/7-2/user/-/knowledge_base/u/creating-and-managing-pages),
  logo and site title (if displayed), and [color schemes](/docs/7-2/frameworks/-/knowledge_base/f/creating-color-schemes-for-your-theme).
  The theme developer can also make portions of the theme's UI configurable
  via [Theme Settings](/docs/7-2/frameworks/-/knowledge_base/f/making-configurable-theme-settings), which improves the workflow and greatly reduces the time and
  maintenance required for the theme.

- **SASS Integration:** Themes are integrated with [SASS](https://sass-lang.com/),
  so you can use variables, as well as SASS syntactic features, such as nesting,
  in your CSS files.

## General

- **Global and Individual Application Styling:** You can style the app's UI
  through its code directly if you prefer, but there are also other options
  available to suite your needs and workflow. Administrators can
  [modify basic styles](/docs/7-2/user/-/knowledge_base/u/look-and-feel-configuration)
  like the margins, padding, and background through the app's configuration
  menu. You can give an Administrator styling options to choose from via
  [Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
  or [Application Decorators](/docs/7-2/frameworks/-/knowledge_base/f/theming-portlets#portlet-decorators).
  You can customize and extend the app's navigation with the
  [Screen Navigation Framework](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework).
  If you want to modify the look and feel for all applications, you can modify
  the [portlet theme template](/docs/7-2/frameworks/-/knowledge_base/f/theming-portlets)
  directly.

- **Configurable and Customizable Templating Options:** Once you create a page,
  layout, or site configuration that you like, you can create a template for it
  that you can use across all your pages and sites.

## Content

- **A Fully Featured Content Management System (CMS):** @product@'s CMS provides
  editors and applications for creating, displaying, and collaborating on
  content, such as web content articles, the Knowledge Base, Documents and
  Media, blogs, the Asset Publisher, wikis, message boards and many more. See
  the [User Guide]([User Guide](/docs/7-2/user) for more information on using
  @product@'s CMS.

## Theme and app Tooling

- **Choice of Tooling:** While you can use your preference of tooling for UI
  development, @product@'s tools are optimized to provide the best UI
  development experience for Portal:

  - **Liferay JS Toolkit:** @product@'s collection of Front-End build tools that
    you can use to [generate themes](/docs/7-2/reference/-/knowledge_base/r/theme-generator),
    [layout templates](/docs/7-2/reference/-/knowledge_base/r/creating-layout-templates-with-the-themes-generator),
    and [applications](/docs/7-2/reference/-/knowledge_base/r/js-generator).
    These tools use npm, Yeoman, and Gulp and offer first-class integration with
    [React](/docs/7-2/appdev/-/knowledge_base/a/developing-a-react-application),
    [Angular](/docs/7-2/appdev/-/knowledge_base/a/developing-an-angular-application),
    and [Vue.js](/docs/7-2/appdev/-/knowledge_base/a/developing-a-vue-application).

## Theme and app components

  - **Clay:** [Clay](https://clayui.com/) is @product@'s web toolkit that
    extends Bootstrap with additional components and CSS patterns that @product@
    requires.

## A Brief overview of Creating a Theme

Creating a theme involves these generalized steps:

1.  Generate a new theme with the [Theme Generator](/docs/7-2/reference/-/knowledge_base/r/theme-generator).

2.  Configure the Look and Feel XML file with any additional [theme settings](/docs/7-2/frameworks/-/knowledge_base/f/making-configurable-theme-settings)
    or [color schemes](/docs/7-2/frameworks/-/knowledge_base/f/creating-color-schemes-for-your-theme)
    your theme requires:

    ```xml
    <look-and-feel>
      <theme id="my-theme" name="My Theme">
        <settings>
          <setting key="show-footer" />
        </settings>
        <color-scheme id="01" name="Default">
          <css-class>default</css-class>
        </color-scheme>
        <color-scheme id="02" name="Red">
          <css-class>red</css-class>
        </color-scheme>
        <portlet-decorator id="barebone">
          <portlet-decorator-css-class>portlet-barebone</portlet-decorator-css-class>
        </portlet-decorator>
      </theme>
    </look-and-feel>
    ```

3.  Update or define variables, such as the `show-footer` theme setting, in the
    Init Custom FreeMarker template:

    ```markup
    <#assign header_css_class = "navbar navbar-expand-md navbar-dark flex-column flex-md-row bd-navbar" />
    <#assign logo_css_class = logo_css_class + " navbar-brand" />


    <#assign
    	show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer"))
    />
    ```

4.  Modify the Portal Normal (the index) FreeMarker template to include any
    other templates and use any variables that you need to include theme objects
    or display UI under certain conditions. For example, the `show_footer`
    variable shown below only displays the Footer when true:

    ```markup
    <html class="${root_css_class}">
    <head></head>
      <body class="${body_class}">
        <header class="${header_css_class}">
          <a class="${logo_css_class} href="${site_url}"><img src="${site_logo}"/></a>
          <#include "${full_templates_path}/navigation.ftl" />
        </header>
        <section>
          ${portlets}
        </section>
        <#if show_footer>
          <#include "${full_templates_path}/footer.ftl" />
        </#if>
      </body>
    </html>
    ```

5.  Add any styling through `_custom.scss` and JavaScript through `main.js`.
    Then build and deploy your theme to your app server and apply it to your
    site page(s).

## Where Do I Go from Here?

Now that you have a better understanding of the various components, tools, and
features that are used throughout UI development in @product@, you can visit
these sections to learn more:

- [Developing Front-End Applications](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends)
- [Developing Themes](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes)
- [Theme Components](/docs/7-2/frameworks/-/knowledge_base/f/theme-components)
- [Understanding the Page Layout](/docs/7-2/frameworks/-/knowledge_base/f/understanding-the-page-layout)
