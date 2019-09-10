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
[themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction).
Administrative menus facilitate managing portal instances, users, groups, and
more. Note: the UI is optional. 

You can use the @product@ UI and services together; or focus solely on using
services via
[REST web APIs](/docs/7-2/frameworks/-/knowledge_base/f/headless-rest-apis). 

![Figure 1: @product@ portal instances provide UIs for people to interact with content and functionality. @product@ can also be used "headless"---without the UIs.](../../images/architecture-options.png)

This section shows how the architecture satisfies these requirements:

- Supports using the best, most popular development technologies

- Leverages development standards

- Facilitates swapping component implementations

- Starts up fast and performs well

- Its runtime is easy to configure and inspect

Liferay's core infrastructure supports UI and service deployments and
orchestrates wiring them together. 

## Core 

@product@ is a web application that runs on your application server. The Core
bootstraps the web application and provides a runtime framework for UI and
service components. Here are some component examples: 

-   [Services](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
-   [Service customizations](/docs/7-2/customization/-/knowledge_base/c/overriding-service-builder-services-service-wrappers)
-   [Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)
    (portlet and controller classes, templates, and resources)
-   [JavaScript applications](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends)
    (templates, routers, and resources)
-   [JSP customization via Portlet Filters](/docs/7-2/customization/-/knowledge_base/c/jsp-overrides-using-portlet-filters) 
-   [Theme]((/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction))
-   [Shared Language Keys](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-language-module)
-   [Navigation components](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework)

The following figure shows these component types in the component runtime
environment. 

![Figure 2: The Core provides a runtime environment for UI and service components, such as the ones here. New component implementations can extend or replace existing implementations dynamically.](../../images/component-runtime-environment.png)

The component runtime supports adding, replacing, and customizing components
on-the-fly. In Figure 2 shows examples of replacing and customizing components.

**Replacement:** If the `ServiceC Impl 2` component has a higher ranking than 
existing component `ServiceC Impl 1`, `ServiceC Impl 2` is used in place of
`ServiceC Impl 1`. 

**Customization:** The `PortletA Filter` intercepts and modifies requests to and
responses from `PortletA`, affecting content `PortletA` displays. 

Components are developed in WAR or module JAR projects and install as
[OSGi bundles](https://www.osgi.org/)
(modules). The OSGi runtime framework defines the module lifecycle, enforces
dependencies, defines the class loading structure, and provides an API and CLI
([Felix Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell))
for managing modules and components. The Core is configured via
[portal properties files](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
and
[Server Administration panels](/docs/7-2/user/-/knowledge_base/u/server-administration). 

## Services 

Liferay services provide functionality. Built-in services operate on Liferay
models such as users, roles, web content, documents and media, and more. Custom
services introduce new models and functionality. Services access each other via 
[dependency injection](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services). 

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
The following figure shows how @product@ applications and external clients
invoke Liferay services. 

![Figure 3: Remote and @product@ applications can invoke services via REST web APIs. @product@ Java-based portlets can also invoke services via Java APIs.](../../images/apps-invoking-services.png) 

Liferay services are built using
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
and
[REST Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder).
Service Builder generates the models and the service classes that operate on and
[persist the models](/docs/7-2/appdev/-/knowledge_base/a/creating-the-service-xml-file). 
Services are easy to
[override and extend](/docs/7-2/customization/-/knowledge_base/c/overriding-osgi-services)
too. 

The Liferay UI is a great way to provide content and functionality to people. 

## UI 

[@product@'s UI](/docs/7-2/user/-/knowledge_base/u/the-liferay-distinction)
helps people do work,
[collaborate](/docs/7-2/user/-/knowledge_base/u/collaboration),
and
[share and enjoy content](/docs/7-2/user/-/knowledge_base/u/web-experience-management). 

-   [Applications](/docs/7-2/appdev/-/knowledge_base/a/application-development)
    (widgets and portlets) expose functionality to accomplish tasks
-   Product navigation helps users develop sites
-   [Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
    style sites with a unique look and feel

### Applications 

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

### Product Navigation

@product@'s navigation makes apps and widgets available to users. The 
[navigation is customizable](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework)
too. 

![Figure 5: Liferay facilitates integrating custom administrative functionality through navigation menus and administrative applications.](../../images/architecture-ui-menus-and-panel-app.png)

### Themes 

A
[theme](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
styles a site with a unique look and feel. It's developed as a WAR project that
includes CSS and content. You can develop themes however you like, but @product@
offers Bootstrap-based components and 
[theme tooling](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes). 

![Figure 6: Liferay makes it easy to develop and try out attractive site themes.](../../images/architecture-ui-themes.png)

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

The architecture section covers Core, service, and UI topics. The initial
articles dive deeper into the Core to describe class loading, modularity, and
more. But you can jump ahead to any service or UI architecture topics, if you
like. Enjoy exploring the @product@ architecture! 
