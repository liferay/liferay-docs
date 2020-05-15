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

-   Content: images, videos, and text.

-   [Applications](/docs/7-2/appdev/-/knowledge_base/a/application-development):
    Widgets and portlets that expose functionality for accomplishing tasks.

-   [Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction):
    Plugins that use CSS, FreeMarker templates, HTML, and JavaScript to provide
    a site's overall look and feel.

-   Product navigation sidebars and panels: Use these for administering sites.

## Content

@product@'s built-in applications help you publish images, video, forms, markup
text, and more to site pages.
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
as other web applications, and @product@ can combine multiple applications on
one page.

@product@ supports developing JavaScript-based applications using popular
front-end frameworks:

-   [Angular](/docs/7-2/appdev/-/knowledge_base/a/developing-an-angular-application)
-   [React](/docs/7-2/appdev/-/knowledge_base/a/developing-a-react-application)
-   [Vue](/docs/7-2/appdev/-/knowledge_base/a/developing-a-vue-application)

Java-based portlet applications use the latest portlet standards and frameworks,
including ones familiar to experienced Liferay portlet developers:

-   [Liferay MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)
-   [PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/portletmvc4spring)
-   [JSF Portlet](/docs/7-2/appdev/-/knowledge_base/a/jsf-portlet)
<!---   [Bean Portlet](/docs/7-2/appdev/-/knowledge_base/a/bean-portlet) TODO uncomment when Bean Portlet is available. jhinkey -->

In the UI, applications are referred to as Widgets and categorized for users to
add to pages. Administrative applications are available in the product menu
panels.

![Figure 1: Widget pages offer users functionality. Widgets are organized into a page template's rows and columns. This template has two columns: a smaller left column and larger right column. On this page, users select tags in the Tag Cloud widget and the matching tagged images show the Asset Publisher widget.](../../../images/architecture-ui-widgets.png)

## Themes

A
[theme](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
styles a site with a unique look and feel. It's developed as a WAR project that
includes CSS, JavaScript, and markup content. You can develop themes using
whatever tools you prefer, but @product@ offers
[Bootstrap](https://getbootstrap.com/)-based
components and
[theme tooling](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes)
to create and deploy themes in no time.

![Figure 2: You can select an attractive theme and apply it to your site.](../../../images/architecture-ui-themes.png)

Here's a quick demonstration of developing a theme:

1.  Create a theme using the
    [Theme Generator](/docs/7-2/reference/-/knowledge_base/r/theme-generator).
    The theme extends the base theme you specified to the Theme
    Generator---Liferay's
    [Styled theme](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/frontend-theme/frontend-theme-styled)
    is the default.

2.  Run
    [`gulp build`](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/building-your-themes-files)
    to generate the base theme files to the `build` folder subfolders:

    -   `templates`: FreeMarker templates specify site page markup.
        `portal_normal.ftl` is the central file; it includes templates that
        define the page parts (e.g., header, navigation, footer). The
        `init.ftl` file defines default variables available to the templates.

    -  `css`: SCCS files that provide styling.

    -  `font`: Font Awesome and Glyphicons fonts.

    -  `js`: JavaScript files; `main.js` is the Styled theme's
        JavaScript.

    -  `images`: Image files.

3.  Override aspects of the base theme by copying relevant files from the
    `build` subfolders to folders of the same name in your `src` folder. The
    [Theme Anatomy Guide](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide)
    describes all the files. Here's an example of a customized
    `portal_normal.ftl`:

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

4.  Add custom styling using your theme's `_custom.scss` file (i.e.,
    `src/css/_custom.scss`).
    @product@ supports
    [Bootstrap](https://getbootstrap.com/),
    as well as
    [Sass](https://sass-lang.com/),
    so you can use Bootstrap utilities in your markup and Sass nesting,
    variables, and more in your CSS files. This snippet styles the logo:

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

![Figure 3: You can provide custom styling using the theme's `_custom.sccs` file.](../../../images/architecture-ui-portal-dev-logo.png)

5.  Deploy your theme by executing `gulp deploy`.

The theme is available to
[apply](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes)
to your site.


For details,
[Theme Components](/docs/7-2/customization/-/knowledge_base/c/theme-components)
breaks down a theme's parts, and the
[Themes section](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
provides theme development details.

## Product Navigation Sidebars and Panels

The product navigation sidebars and panels enable administrators to build sites,
add pages, apply themes, and configure the portal. It's also where you can
provide administrative functionality for your custom applications. The
navigation sidebars and panels are customizable.

![Figure 4: Liferay facilitates integrating custom administrative functionality through navigation menus and administrative applications.](../../../images/architecture-ui-menus-and-panel-app.png)

As you can see, @product@'s UI is highly flexible and customizable. Here's where
to learn more:

- [Theme Components](/docs/7-2/customization/-/knowledge_base/c/theme-components):
  Explains available mechanisms and extensions for customizing and theming
  pages, content, and applications.

- [Understanding the Page Structure](/docs/7-2/customization/-/knowledge_base/c/understanding-the-page-structure):
  Describes how the page's UI is organized and introduces tools for populating
  and developing each section.
