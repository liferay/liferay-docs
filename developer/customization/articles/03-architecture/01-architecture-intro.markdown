---
header-id: architecture
---

# Architecture

[TOC levels=1-4]

@product@'s architecture helps you build sites that offer powerful functionality
and terrific styling. The following goals contributed to the architecture's
success: 

- Supports using the best, most popular development technologies

- Leverages development standards

- Facilitates swapping implementations

- Fast startup and performance

- Easy runtime inspection and configuration

The architecture comprises these components: 

**UIs:** Widgets, page content, navigation, admin applications, and themes. 

**Services:** Functionality exposed via Java APIs and web APIs. 

**Core:** Web application that bootstraps @product@ and provides a runtime 
framework for deploying components and customizations. 

@product@ and Liferay apps provide the UI and services that users and external
components interact with. The UIs and services are the parts you can customize
in @product@ and add to it. Liferay services do the "heavy lifting". 

## Services 

Services operate on models that include users, roles, web content, documents and
media items, and your application's models. They're built using [Service
Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) and [REST
Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder). 

The UIs invoke the services to do work. Local Java-based UIs call services
directly using the [Java
APIs](/docs/7-2/reference/-/knowledge_base/r/java-apis). Non-Java UIs and remote
components (apps and devices outside of the JVM) call the services using the web
APIs. The web APIs include [headless REST
APIs](/docs/7-2/appdev/-/knowledge_base/a/generating-apis-with-rest-builder)
that conform to the [OpenAPI](https://swagger.io/docs/specification/about/)
standard and [plain web/REST
services](/docs/7-2/frameworks/-/knowledge_base/f/web-services). The following
figure shows how UIs and clients interact with the services. 

![Figure 1: Device browsers access the services indirectly through the UIs and remote clients use web APIs (e.g., REST) to invoke the services.](../../images/uis-and-services.png) 

Liferay services are built using [Service
Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) and [REST
Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder). Service Builder
generates the models and the service classes that operate on and [persist the
models](/docs/7-2/appdev/-/knowledge_base/a/creating-the-service-xml-file) to
the [@product@ data
source](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install#preparing-a-database). 

Services are easy to [override and
extend](/docs/7-2/customization/-/knowledge_base/c/overriding-osgi-services)
too. Just create your own version of the service, rank it higher than the
current service, and
[deploy](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project) it. 

## UIs 

@product@ users interact with the site UIs. Product navigation and control panel
applications enable adding users, roles, permissions, page templates, widgets,
fragments, all kinds of content, and more. The widgets provide interactive
functionality and themes give a site its look and feel. 

Here are the main UI components you can develop: 

- Themes
- Widgets and Panel Applications
- Product navigation

### Themes 

A theme styles your site with a unique look and feel. It's developed as a plugin
that includes CSS and content. Bootstrap-based components are readily available
and Liferay's tooling facilitates [theme
development](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes), but you
can develop themes however you like. 

![Figure 2: Liferay makes it easy to develop and try out attractive site themes.](../../images/architecture-ui-themes.png)

### Widgets and Applications 

Users interact with widgets and applications (including control panel
applications) to get things done. They're [developed the same
way](/7-2/appdev/-/knowledge_base/a/web-front-ends) as other web front-ends.
The only difference is that @product@ installs them as
[portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets). But you need not
be a portlet expert as the conversion to a portlet is transparent. You develop
widgets the way you want. 

Widgets are categorized and available for users to add to site pages. Panel
applications typically reside in the control panel and are made available to
privileged administrative users. 

Widgets and apps leverage services that are available in @product@'s runtime
environment. 

![Figure 3: Widget pages offer users functionality. Widgets are organized into the page template's rows and columns. On this page, users select tags in the Tags Navigation and Tag Cloud widgets to display images in the Asset Publisher widget.](../../images/architecture-ui-widgets.png)

### Product Navigation

@product@'s navigation makes panels and widgets available to users. You can
customize and add to the navigation. 

![Figure 4: Liferay facilitates integrating custom application administrative functionality through navigation menus and panel applications.](../../images/architecture-ui-menus-and-panel-app.png)

Liferay's core infrastructure facilitates UI and service deployment and
orchestrates wiring them together. 

## Core 

The Core runs as a web application on your application server. It launches the
portal and manages deployments to the portal. Services and UIs are deployed to
@product@ as [OSGi bundles](https://www.osgi.org/) (modules). Each module is a
cohesive, easy to understand set of services or views. The core provides a
dynamic environment in which modules can come and go at any time. You can
introduce new functionality or replace existing functionality when you want.
@product@ uses an OSGi module framework that defines a module lifecycle,
enforces dependencies, and provides an API and CLI ([Felix Gogo
Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell))
for managing modules.  

@product@ supports developing WAR projects and module JAR projects but installs
all of them as OSGi bundles---the [WAB
Generator](/docs/7-2/customization/-/knowledge_base/c/deploying-wars-wab-generator)
generates a bundle from a WAR project automatically. @product@'s dynamic runtime
environment facilitates installing, uninstalling, and overriding modules. It
supports swapping implementations on the fly. @product@ is configured via the
Control Panel and configuration files such as a [portal properties
file](/docs/7-2/deploy/-/knowledge_base/d/portal-properties). 

This section's initial articles explain the Core, the class loading hierarchy,
and class loading from a module perspective. Enjoy exploring the @product@
architecture!
