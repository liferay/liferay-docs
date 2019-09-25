# UI Architecture

[@product@'s UI](/docs/7-2/user/-/knowledge_base/u/the-liferay-distinction)
is a portal for adding sites, pages, widgets, and content, all styled by 
[themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction).
The portal helps people do work,
[collaborate](/docs/7-2/user/-/knowledge_base/u/collaboration),
and
[share and enjoy content](/docs/7-2/user/-/knowledge_base/u/web-experience-management).
The UI comprises the following parts:

-   Content: HTML, Images, videos, and text. 
-   [Applications](/docs/7-2/appdev/-/knowledge_base/a/application-development)
    (widgets and portlets) expose functionality to accomplish tasks.
-   [Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
    style sites with a unique look and feel
-   Product menu for administering sites. 

## Content 

You can add all kinds of content to site pages. The Web Experience Management
suite helps you create HTML content items and organize them. The Documents and
Media application stores your images, videos, and documents for you to use
throughout your site. And apps such as Blogs and Message Boards publish user
text and images too. @product@ has many useful built-in application for
publishing content, and you can add custom applications too. 

## Applications

Users get things done using @product@ applications. They're
[developed the same way](/7-2/appdev/-/knowledge_base/a/web-front-ends)
as other web applications, except @product@ installs them as
[portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets).

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

![Figure 4: Widget pages offer users functionality. Widgets are organized into the page template's rows and columns. On this page, users select tags in the Tags Navigation and Tag Cloud widgets to display images in an Asset Publisher widget.](../../images/architecture-ui-widgets.png)

## Themes

A
[theme](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
styles a site with a unique look and feel. It's developed as a WAR project that
includes CSS and content. You can develop themes however you like, but @product@
offers Bootstrap-based components and
[theme tooling](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes).

![Figure 6: Liferay makes it easy to develop and try out attractive site themes.](../../images/architecture-ui-themes.png)

## Product Menu

The product menu makes apps and widgets available to administrative users. The
[menus and navigation are customizable](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework)
too.

![Figure 5: Liferay facilitates integrating custom administrative functionality through navigation menus and administrative applications.](../../images/architecture-ui-menus-and-panel-app.png)

Here are some ways to customize @product@'s UI:

-   [Create themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
-   [Create widgets](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends)
-   [Create widget templates](/docs/7-2/customization/-/knowledge_base/c/customizing-widgets)
-   [Create administrative applications](/docs/7-2/tutorials/-/knowledge_base/t/writing-an-administrative-portlet)
-   [Override navigation](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework)
-   [Modify portlet JSP content](/docs/7-2/customization/-/knowledge_base/c/customizing-jsps)

As you can see, the @product@ architecture supports developing customizations,
services, and the UI. The Core's runtime framework makes components accessible
for examining and managing.
