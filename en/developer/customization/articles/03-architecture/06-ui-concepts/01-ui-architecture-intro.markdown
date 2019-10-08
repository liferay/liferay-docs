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

![Figure 1: Widget pages offer users functionality. Widgets are organized into the page template's rows and columns. On this page, users select tags in the Tags Navigation and Tag Cloud widgets to display images in an Asset Publisher widget.](../../../images/architecture-ui-widgets.png)

## Themes

A [theme](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) styles a 
site with a unique look and feel. It's developed as a WAR project that includes 
CSS, JS, HTML, and content. You can develop themes with whatever tools you 
prefer, but @product@ offers Bootstrap-based components and [theme tooling](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes) 
to get you up and running in no time. 

![Figure 2: Liferay makes it easy to develop and try out attractive site themes.](../../../images/architecture-ui-themes.png)

To create a theme for @product@, generate a new theme with the [Theme Generator](/docs/7-2/reference/-/knowledge_base/r/theme-generator). The generated theme, by default, extends the [Styled base theme](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/frontend-theme/frontend-theme-styled), 
which provides everything you need to get your theme up and running (base 
styles, CSS, JS, and [theme templates](/docs/7-2/customization/-/knowledge_base/c/theme-components#theme-templates-and-utilities) 
for @product@). Note that these files are generated when the theme is built (on 
deployment or through the [build task](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/building-your-themes-files)).

The `portal_normal.ftl` theme template, included by default in the deployed WAR 
and `build` folder, acts as the hub for all other theme templates, similar to 
`index.html`. It provides the base markup that creates the framework for every 
page that the theme is applied to. Copy `portal_normal.ftl` over to your theme, 
and include your other templates and any markup that you want to apply to the 
pages that use the theme. An example `portal_normal.ftl` is shown below:

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
      <#include "${full_templates_path}/footer.ftl" />
  </body>
</html>
```

Add custom styling through `_custom.scss` (included in the theme by default). 
@product@ supports [Bootstrap](https://getbootstrap.com/), as well as [SASS](https://sass-lang.com/), 
so you can use Bootstrap utilities in your markup and SASS nesting, variables, 
etc. in your CSS files. The snippet below provides styles for the logo on the 
[portal.liferay.dev site](https://portal.liferay.dev/):

```sass
.logo {
  margin-left: 15px;

  img {
    height: auto;
  }

  @include media-breakpoint-down(md) {
    text-align: center;
    width: 100%;
  }
}
```

![Figure 3: You can provide custom styling through the theme's `_custom.scss` file.](../../../images/architecture-ui-portal-dev-logo.png)

If you have any custom JavaScript, copy `main.js` over from the `build` folder 
to your theme. Then deploy your theme to your app server and apply it to your 
site page(s).

See the [Themes section](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) 
for more information on developing themes. 

## Product Navigation Sidebars and Panels

The product navigation sidebars and panels make apps and widgets available to 
administrative users. The [menus and navigation are customizable](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework) 
too. 

![Figure 4: Liferay facilitates integrating custom administrative functionality through navigation menus and administrative applications.](../../../images/architecture-ui-menus-and-panel-app.png)

As you can see, @product@'s architecture has a highly flexible and customizable 
UI. Now that you have a high-level understanding of the UI, you can read the 
articles below to learn more:

- [Theme Components](/docs/7-2/customization/-/knowledge_base/c/theme-components): 
  An explanation of the available mechanisms and extensions for customizing 
  and theming pages, content, and applications. 
  
- [Understanding the Page Layout](/docs/7-2/customization/-/knowledge_base/c/understanding-the-page-layout): 
  An overview of how the page's UI is organized and the tools you have available 
  for populating and developing each section. 
