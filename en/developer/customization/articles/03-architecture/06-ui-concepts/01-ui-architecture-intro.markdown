---
header-id: ui-architecture
---

# UI Architecture

[TOC levels=1-4]

[@product@'s UI](/docs/7-2/user/-/knowledge_base/u/the-liferay-distinction) 
is a portal for adding sites, pages, widgets, and content. It helps 
people do work,
[collaborate](/docs/7-2/user/-/knowledge_base/u/collaboration), 
and
[share content](/docs/7-2/user/-/knowledge_base/u/web-experience-management). 

The UI comprises the following parts:

-   Content: Images, videos, and text. 

-   [Applications](/docs/7-2/appdev/-/knowledge_base/a/application-development): 
    Widgets and portlets that expose functionality for accomplishing tasks. 

-   [Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction): 
    Plugins that use CSS, FreeMarker templates, HTML, and JavaScript to provide
    a site's overall look and feel.

-   Product navigation sidebars and panels: Use these for administering sites. 

## Content

@product@'s built-in applications help you publish images, video, forms, markup text, and more to site pages.
[Documents and Media](/docs/7-2/user/-/knowledge_base/u/managing-documents-and-media)
stores images, videos, and documents to use throughout your site. The
[Web Experience Management](/docs/7-2/user/-/knowledge_base/u/web-experience-management)
suite helps you create, maintain, and organize content.
[Liferay Forms](/docs/7-2/user/-/knowledge_base/u/forms)
gives you robust form building capability.
[Message Boards](/docs/7-2/user/-/knowledge_base/u/creating-forums-with-message-boards)
facilitate lively discussions and 
[Blogs](/docs/7-2/user/-/knowledge_base/u/publishing-blogs)
let users express themselves with markup text and images. These are just a few
of the built-in applications for adding site content. 

## Applications

@product@ applications provide content and help users accomplish tasks. They're 
[developed the same way](/7-2/appdev/-/knowledge_base/a/web-front-ends)
as other web applications, except @product@ installs them as
[portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)---applications 
that run on a portion of a page. 

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

In the UI, applications are referred to as Widgets and categorized for users to
add to pages. Administrative applications are developed as portlets and made
available in the product menu panels. 

![Figure 4: Widget pages offer users functionality. Widgets are organized into a page template's rows and columns. This template has two columns: a smaller left column and larger right column. On this page, users select tags in the Tag Cloud widget and the matching tagged images show the Asset Publisher widget.](../../../images/architecture-ui-widgets.png)

## Themes

A
[theme](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
styles a site with a unique look and feel. It's developed as a WAR project that
includes CSS, JavaScript, HTML, and content. You can develop themes with
whatever tools you prefer, but @product@ offers Bootstrap-based components and
[theme tooling](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes) 
to create and deploy themes in no time. 

![Figure 6: You can select an attractive theme and apply it to your site.](../../../images/architecture-ui-themes.png)

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
Your theme is available to
[apply](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes)
to your site. It's just that easy! 

See the [Themes section](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) 
for more information on developing themes.

## Product Navigation Sidebars and Panels

The product navigation sidebars and panels enable administrators to build sites,
add pages, apply themes, and configure the portal. It's also where you can
provide administrative functionality for your custom applications. The
navigation sidebars and panels are customizable. 

![Figure 4: Liferay facilitates integrating custom administrative functionality through navigation menus and administrative applications.](../../../images/architecture-ui-menus-and-panel-app.png)

As you can see, @product@'s UI is highly flexible and customizable. Now that you
understand it at a high-level, read these articles to learn more: 

- [Theme Components](/docs/7-2/customization/-/knowledge_base/c/theme-components): 
  Explains available mechanisms and extensions for customizing and theming
  pages, content, and applications. 

- [Understanding the Page Layout](/docs/7-2/customization/-/knowledge_base/c/understanding-the-page-layout): 
  Describes how the page's UI is organized and introduces tools for populating
  and developing each section. 
