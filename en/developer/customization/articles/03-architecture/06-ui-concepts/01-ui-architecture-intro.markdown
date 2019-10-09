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

TODO - demonstrate how easy it is to create a theme. 

Your theme is available to
[apply](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes)
to your site. It's just that easy! 

## Product Navigation Sidebars and Panels

The product navigation sidebars and panels enable administrators to build sites,
add pages, apply themes, and configure the portal. It's also where you can
provide administrative functionality for your custom applications. The
navigation sidebars and panels are customizable. 

![Figure 5: Liferay facilitates integrating custom administrative functionality through navigation menus and administrative applications.](../../../images/architecture-ui-menus-and-panel-app.png)

As you can see, @product@'s UI is highly flexible and customizable. Now that you
understand it at a high-level, read these articles to learn more: 

- [Theme Components](/docs/7-2/customization/-/knowledge_base/c/theme-components): 
  Explains available mechanisms and extensions for customizing and theming
  pages, content, and applications. 

- [Understanding the Page Layout](/docs/7-2/customization/-/knowledge_base/c/understanding-the-page-layout): 
  Describes how the page's UI is organized and introduces tools for populating
  and developing each section. 
