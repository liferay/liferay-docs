---
header-id: ui-architecture
---

# UI Architecture

[TOC levels=1-4]

[@product@'s UI](/docs/7-2/user/-/knowledge_base/u/the-liferay-distinction) 
is a portal for adding sites, pages, widgets, and content. The portal helps 
people do work, [collaborate](/docs/7-2/user/-/knowledge_base/u/collaboration), 
and [share and enjoy content](/docs/7-2/user/-/knowledge_base/u/web-experience-management). 

The UI comprises the following parts:

-   Content: Images, videos, and text. 
-   [Applications](/docs/7-2/appdev/-/knowledge_base/a/application-development) 
    (widgets and portlets) expose functionality to accomplish tasks. 
-   [Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) 
    provide the overall look and feel (HTML, CSS, and JS) for the site. Widgets, 
    content, and pages can have their own individual look and feel as well. 
-   Product menu for administering sites. 

## Content

You can add all kinds of content to site pages. The Web Experience Management 
suite helps you create, maintain, and organize content. The Documents and Media 
application stores your images, videos, and documents for you to use throughout 
your site. And apps such as Blogs and Message Boards publish user text and 
images too. @product@ has many useful built-in applications for publishing 
content, and you can add custom applications too. 

## Applications

Users get things done using @product@ applications. They're 
[developed the same way](/7-2/appdev/-/knowledge_base/a/web-front-ends) as other 
web applications, except @product@ installs them as [portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets). 

@product@ supports developing JavaScript-based applications using popular 
front-end frameworks:

-   [Angular](/docs/7-2/appdev/-/knowledge_base/a/developing-an-angular-application)
-   [React](/docs/7-2/appdev/-/knowledge_base/a/developing-a-react-application)
-   [Vue](/docs/7-2/appdev/-/knowledge_base/a/developing-a-vue-application)

Java-based portlet applications use the latest portlet standards and frameworks, 
including ones familiar to experienced Liferay portlet developers:

-   [Bean Portlet](/docs/7-2/appdev/-/knowledge_base/a/bean-portlet)
-   [JSF Portlet](/docs/7-2/appdev/-/knowledge_base/a/jsf-portlet)
-   [PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/portletmvc4spring)
-   [Liferay MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)

Applications are categorized in the UI for users to add to pages as widgets. 
Administrative applications are developed as portlets and made available in the 
product menu. 

![Figure 4: Widget pages offer users functionality. Widgets are organized into the page template's rows and columns. On this page, users select tags in the Tags Navigation and Tag Cloud widgets to display images in an Asset Publisher widget.](../../../images/architecture-ui-widgets.png)

## Themes

A [theme](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) styles a 
site with a unique look and feel. It's developed as a WAR project that includes 
CSS, JS, HTML, and content. You can develop themes with whatever tools you 
prefer, but @product@ offers Bootstrap-based components and [theme tooling](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes) 
to get you up and running in no time. 

![Figure 6: Liferay makes it easy to develop and try out attractive site themes.](../../../images/architecture-ui-themes.png)

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

See the [Themes section](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) 
for more information on developing themes. 

## Product Navigation Sidebars and Panels

The product navigation sidebars and panels make apps and widgets available to 
administrative users. The [menus and navigation are customizable](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework) 
too. 

![Figure 5: Liferay facilitates integrating custom administrative functionality through navigation menus and administrative applications.](../../../images/architecture-ui-menus-and-panel-app.png)

As you can see, @product@'s architecture has a highly flexible and customizable 
UI. Now that you have a high-level understanding of the UI, you can read the 
articles below to learn more:

- [Theme Components](/docs/7-2/customization/-/knowledge_base/c/theme-components): 
  An explanation of the available mechanisms and extensions for customizing 
  and theming pages, content, and applications. 
  
- [Understanding the Page Layout](/docs/7-2/customization/-/knowledge_base/c/understanding-the-page-layout): 
  An overview of how the page's UI is organized and the tools you have available 
  for populating and developing each section. 
