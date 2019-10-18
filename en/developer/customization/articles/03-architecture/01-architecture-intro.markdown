---
header-id: architecture
---

# Architecture

[TOC levels=1-4]

@product@ architecture comprises these parts:

**Core:** Bootstraps @product@ and its frameworks. The Core provides a runtime
environment for managing services, UI components, and customizations.

**Services:** Liferay and custom functionality is exposed via Java APIs and web
APIs.

**UI:** The optional web application UI for adding portals, sites, pages,
widgets, and content.

You can use the @product@ UI and services together or focus solely on using
services via
[REST web APIs](/docs/7-2/frameworks/-/knowledge_base/f/headless-rest-apis).

![Figure 1: @product@ portals and Sites contain content and widgets. @product@ can also be used "headless"---without the UI.](../../images/architecture-options.png)

The architecture satisfies these requirements:

- Supports using common development technologies

- Leverages development standards

- Facilitates swapping components

- Starts fast and performs well

- Its runtime is easy to configure and inspect

The Core supports UI and service deployments and orchestrates wiring them
together.

## Core

@product@ is a web application that runs on your application server. The Core
bootstraps the application and
[Liferay's built-in frameworks](/docs/7-2/frameworks/-/knowledge_base/f/frameworks).

There are frameworks for these things and more:

- [Adaptive Media](/docs/7-2/frameworks/-/knowledge_base/f/adaptive-media)
- [Application Configuration](/docs/7-2/frameworks/-/knowledge_base/f/configurable-applications)
- [Application Security](/docs/7-2/frameworks/-/knowledge_base/f/application-security)
- [Asset Framework](/docs/7-2/frameworks/-/knowledge_base/f/asset-framework)
- [File Management](/docs/7-2/frameworks/-/knowledge_base/f/documents-and-media-api)
- [Localization](/docs/7-2/frameworks/-/knowledge_base/f/localization)
- [Search](/docs/7-2/frameworks/-/knowledge_base/f/search)
- [Segmentation and Personalization](/docs/7-2/frameworks/-/knowledge_base/f/segmentation-personalization)
- [Upgrade Processes](/docs/7-2/frameworks/-/knowledge_base/f/upgrade-processes)
- [Web Fragments](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments)
- [Workflow](/docs/7-2/frameworks/-/knowledge_base/f/the-workflow-framework)

The Core provides the component runtime environment for the frameworks,
services, and UI. Here are some component examples:

-   [Services](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
-   [Service customizations](/docs/7-2/customization/-/knowledge_base/c/overriding-service-builder-services-service-wrappers)
-   [Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)
    (templates, controllers, and resources)
-   [JavaScript applications](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends)
    (templates, routers, and resources)
-   [JSP customization via Portlet Filters](/docs/7-2/customization/-/knowledge_base/c/jsp-overrides-using-portlet-filters)
-   [Theme]((/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction))
-   [Shared Language Keys](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-language-module)
-   [Navigation components](/docs/7-2/frameworks/-/knowledge_base/f/screen-navigation-framework)

The following figure shows these component types in the runtime environment.

![Figure 2: The Core provides a runtime environment for components, such as the ones here. New component implementations can extend or replace existing implementations dynamically.](../../images/component-runtime-environment.png)

The runtime environment supports adding, replacing, and customizing components
on-the-fly. This makes the following scenarios possible:

**Replacement:** If the `ServiceC Impl 2` component has a higher ranking than
existing component `ServiceC Impl 1`, `ServiceC Impl 2` is used in its place.

**Customization:** The `PortletA Filter` intercepts and modifies requests to and
responses from `PortletA`, affecting the content `PortletA` displays.

Component WAR and module JAR projects install as
[OSGi bundles](https://www.osgi.org/)
(modules). @product@'s OSGi framework defines the module lifecycle, enforces
dependencies, defines the class loading structure, and provides an API and CLI
([Felix Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell))
for managing modules and components. The Core is configured via
[portal properties files](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
and
[Server Administration panels](/docs/7-2/user/-/knowledge_base/u/server-administration).

The service components provide business functionality.

## Services

Business logic is implemented in services deployed to the component runtime
environment. Built-in Core services and framework services operate on Liferay
models such as Users, Roles, Web Content, Documents and Media, and more. You can
write and deploy custom services to introduce new models and functionality.
Service components can access each other in @product@ via
[dependency injection](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services).

Front-end applications invoke the services to do work. You can deploy Java-based
applications that call services directly using the
[Java APIs](/docs/7-2/reference/-/knowledge_base/r/java-apis), and
any web-based (Java and non-Java) application, whether deployed on @product@ or
not, can use the web APIs, which include
[headless REST APIs](/docs/7-2/appdev/-/knowledge_base/a/generating-apis-with-rest-builder)
that conform to the
[OpenAPI](https://swagger.io/docs/specification/about/)
standard and include
[plain web/REST services](/docs/7-2/frameworks/-/knowledge_base/f/web-services).
The following figure shows @product@ applications and external clients invoking
Liferay services.

![Figure 3: Remote and @product@ applications can invoke services via REST web APIs. @product@ Java-based portlets can also invoke services via Java APIs.](../../images/apps-invoking-services.png)

Liferay services are built using
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
and made REST-ful using
[REST Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder).
The services are easy to
[override and extend](/docs/7-2/customization/-/knowledge_base/c/overriding-osgi-services)
too.

@product@ also provides a web-based UI, which makes content and service
functionality available in browsers.

## UI

[@product@'s UI](/docs/7-2/user/-/knowledge_base/u/the-liferay-distinction)
helps people do work,
[collaborate](/docs/7-2/user/-/knowledge_base/u/collaboration),
and
[enjoy content](/docs/7-2/user/-/knowledge_base/u/web-experience-management).
The UI consists of

-   [@product@ application](/docs/7-2/user/-/knowledge_base/u/the-liferay-distinction):
    The web application for managing Portals, Sites, Users, Pages, Widgets, and
    more. 

-   [Applications](/docs/7-2/appdev/-/knowledge_base/a/application-development):
    Widgets that provide a user interface for services already deployed. 

-   [Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction):
    Plugins for styling Sites with a unique look and feel.

The UI concepts article digs deeper into developing and customizing UI
components.

As you can see, the @product@ architecture supports developing services, UI
components, and customizations. The architecture section covers Core, service,
and UI topics. Next, we dive into the Core to describe class loading,
modularity, and more. But you can jump ahead to any service or UI architecture
topics, if you like. Enjoy exploring the @product@ architecture!
