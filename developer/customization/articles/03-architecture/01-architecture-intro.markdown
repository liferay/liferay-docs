---
header-id: architecture
---

# Architecture

[TOC levels=1-4]

The architecture comprises these main parts:  

**Core:** Bootstraps @product@ and provides a runtime environment for managing
services, UI components, and customizations.

**Services:** Functionality exposed via Java APIs and web APIs. 

**UI:** Portal for adding sites, pages, widgets, and content, all styled by 
themes. @product@'s administrative menus facilitate managing portal instances,
users, groups, and more. Note: the UI is optional. 

You can use the @product@ UI and services; or focus on the services via
[REST web APIs](/docs/7-2/frameworks/-/knowledge_base/f/headless-rest-apis). 

![Figure 1: @product@ portal instances provide UIs for people to interact with content and functionality. @product@ can also be used "headless"---without the UIs.](../../images/architecture-options.png)

The architecture meets these requirements:

- Supports using the best, most popular development technologies

- Leverages development standards

- Facilitates swapping implementations

- Starts up fast and performs well

- Its runtime is easy to configure and inspect

Liferay's core infrastructure supports UI and service deployments and
orchestrates wiring them together. 

## Core 

@product@ is a web application that installs to your application server. The
Core bootstraps the web application and provides a runtime framework for UI and
service components. Here are some component examples: 

-   Services
-   Service customizations
-   Portlets (portlet and controller classes, templates, and resources)
-   JavaScript applications (templates, routers, and resources)
-   Portlet JSP customization via a PortletFilters 
-   Themes
-   Shared Language Keys
-   Navigation components

The following figure shows these component types in a runtime environment. 

![Figure 2: The Core provides a runtime environment for UI and service components, such as the ones here. New component implementations can compete with existing implementations to replace or extend them.](../../images/component-runtime-environment.png)

In the component runtime, components can be added, replaced, or extended on the
fly. In Figure 2, for example if *ServiceC Impl 2*'s rank is higher, it replaces
*ServiceC Impl 1* (more on service rankings later).  Also, the *PortletA Filter*
intercepts and modifies requests to and responses from *PortletA* to affect
template content. 

Components are developed in WAR or module JAR projects and install as
[OSGi bundles](https://www.osgi.org/)
(modules). The
[WAB Generator](/docs/7-2/customization/-/knowledge_base/c/deploying-wars-wab-generator)
generates an OSGi bundle from a deployed WAR project automatically for
installing to @product@. The runtime framework defines the module lifecycle,
enforces dependencies, and provides an API and CLI
([Felix Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell))
for managing modules and components. 

The Core is configured via
[portal properties files](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
and
[Server Administration panels](/docs/7-2/user/-/knowledge_base/u/server-administration). 

## Services 

Liferay services provide functionality. Built-in services operate on Liferay
models such as users, roles, web content, documents and media, and more. Custom
services can use custom models and leverage other required services. 

UI applications invoke the services to do work. @product@ Java-based
applications can call services directly using the
[Java APIs](/docs/7-2/reference/-/knowledge_base/r/java-apis).
@product@ applications (Java and non-Java) and external applications can call
the services using the web APIs. The web APIs include
[headless REST APIs](/docs/7-2/appdev/-/knowledge_base/a/generating-apis-with-rest-builder)
that conform to the
[OpenAPI](https://swagger.io/docs/specification/about/)
standard and
[plain web/REST services](/docs/7-2/frameworks/-/knowledge_base/f/web-services).
The following figure shows how external clients and @product@ applications
invoke Liferay services. 

![Figure 3: Remote and @product@ applications can invoke services via REST web APIs. @product@ Java-based portlets can also invoke services via Java APIs.](../../images/apps-invoking-services.png) 

Liferay services are built using
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
and
[REST Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder).
Service Builder generates the models and the service classes that operate on and
[persist the models](/docs/7-2/appdev/-/knowledge_base/a/creating-the-service-xml-file)
to the
[@product@ data source](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install#preparing-a-database). 

Services are easy to
[override and extend](/docs/7-2/customization/-/knowledge_base/c/overriding-osgi-services)
too: 

1.  Implement the service your way

2.  Rank it higher than the current service
implementation

3.  [deploy](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project)
    it. 

Liferay UIs are a great way to provide functionality and content to people. 

## UI 

@product@'s UI enables people to do work, collaborate, and share and enjoy content. 

-   Applications (widgets and portlets) expose functionality to accomplish tasks
-   Product navigation helps privileged users develop sites
-   Themes style sites with a unique look and feel

### Applications 

Users get things done using @product@ applications. They're
[developed the same way](/7-2/appdev/-/knowledge_base/a/web-front-ends)
as other web front-ends, except @product@ installs them as
[portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets).

@product@ supports developing JavaScript-based applications using popular
front-end frameworks:

-   [Angular](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/developing-an-angular-application)
-   [React](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/developing-a-react-application)
-   [Vue](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/developing-a-vue-application)

Java-based portlet applications are developed using the latest portlet standards
and frameworks, including ones familiar to experienced Liferay portlet
developers: 

-   [Bean Portlet](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/bean-portlet)
-   [JSF Portlet](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/jsf-portlet)
-   [PortletMVC4Spring](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/portletmvc4spring)
-   [Liferay MVC Portlet](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)

Applications are categorized in the UI for users to add to pages as widgets.
Administrative applications are developed as portlets and made available to
privileged users via the product menu. 

![Figure 4: Widget pages offer users functionality. Widgets are organized into the page template's rows and columns. On this page, users select tags in the Tags Navigation and Tag Cloud widgets to display images in an Asset Publisher widget.](../../images/architecture-ui-widgets.png)

### Product Navigation

@product@'s navigation makes apps and widgets available to users. You can
customize the navigation and add to it. 

![Figure 5: Liferay facilitates integrating custom administrative functionality through navigation menus and administrative applications.](../../images/architecture-ui-menus-and-panel-app.png)

### Themes 

A theme styles a site with a unique look and feel. It's developed as a WAR
project that includes CSS and content. You can develop themes however you like,
but @product@ offers Bootstrap-based components and 
[theme tooling](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes). 

![Figure 6: Liferay makes it easy to develop and try out attractive site themes.](../../images/architecture-ui-themes.png)

@product@'s UI can be customized in many ways:

-   Create themes 
-   Create widgets 
-   Create widget templates 
-   Create administrative applications
-   Override navigation 
-   Modify portlet JSP content 

As you can see, the @product@ architecture supports developing services, UI
components, and customizations. The Core's runtime framework makes components
accessible for examining and managing. 

The architecture articles cover Core, service, and UI topics. The initial
articles dive deeper into the Core to describe class loading, modularity, and
more. But you can jump ahead to service and UI architecture topics if you like.
Enjoy exploring the @product@ architecture! 
