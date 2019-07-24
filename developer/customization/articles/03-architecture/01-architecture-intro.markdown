---
header-id: architecture
---

# Architecture

[TOC levels=1-4]

Liferay's design goals are to give you all the tools to create exactly the web
presence you have in mind. To achieve this, the product does these things:

-   Provides a usable default configuration and interface
-   Ships with best-of-breed apps that you can use to build sites quickly
-   Makes the UI customizable at any level of detail from small tweaks to a 
    complete replacement
-   Makes apps customizable at any level of detail
-   Supports developing apps and customizations using popular, standards-based 
    technologies and frameworks
-   Provides a robust development platform upon which you can build and share 
    new best-of-breed apps 

![Figure 1: Liferay provides services and applications for many different kinds of clients.](../../images/liferay-architecture.png) 

Liferay achieves these goals through @product@'s:

- Modular architecture  
- UI Concepts

# Modular Architecture

@product@ provides an environment where every piece of functionality is an independent module. The figure below represents @product@'s architecture from a structural perspective. 

![Figure 2: @product@ is composed of the Core, foundational modules, independent application modules, and suites of application modules (Collaboration, Web Experience, Forms & Workflow, etc.).](../../images/from-liferay-6-core-suites-and-apps.png)

The modules declare three important things: 

-   The functionality they implement or define
-   Their dependency on other modules
-   Their priority relative to their functionality

Using this information, the module container can start all the modules that
fulfill their definitions, implementations, dependencies, and priorities. 

Anything you want to do is implemented or packaged as one or more modules. If
it's a [new
application](/docs/7-2/appdev/-/knowledge_base/a/application-development), that
application can leverage existing modules and  [define a
dependencies](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies)
on them. This enables you to use functionality that are already there without
rewriting them yourself for your app. If it's a customization, in many cases
it's just a simple matter of defining your customization with a higher priority
than the existing service. 

This is the power of a modular architecture. 

@product@'s applications comprise:

- Modules
- Services 
- Components  

### Modules

All new applications, extensions, and customizations are built in a modular way.
A module is the single unit of distribution and deployment in a modular
architecture. 

In the spirit of following existing standards, Liferay leverages
[OSGi](https://www.osgi.org/developer/). OSGi defines, among other things, how
modules can depend on each other and communicate. It also defines the packaging
format for modules: OSGi bundles. An OSGi module is just a typical JAR file,
familiar to Java developers as a ZIP file containing compiled code, templates,
resources, and some meta information.

### Services

Services are independently running pieces of code that provide specific
functionality when called. Liferay's services are standard services as defined
by the [OSGi Alliance](https://www.osgi.org/about-us/). Writing anything,
whether it be an application, an interface to a database, or even a "service" as
you define it, is easy to implement as an OSGi service, because they're both
powerful and easy to develop. If you understand Java interfaces and how they are
implemented--which is introductory Java material--you already understand more
than 90% of what you need to know. First, you define the interface, or contract
for the service: what it returns, and what it needs to return what it returns.
Next, you define an implementation class that implements the contract. 

In the services model, a class requests the service that provides the
functionality it needs. This functionality is provided (often injected) with the
right implementation automatically. It's similar to Spring or EJBs with one
important addition: implementations can be changed at runtime, without
restarting the system. This is achieved because when a service is deployed, it
becomes part of a service registry maintained by Liferay's OSGi container. The
container dynamically manages the lifecycle of the service and can start and
stop services when appropriate. 

The real power of services shines when they are extended. You can replace
existing implementations or in advanced use cases have several implementations
of a service. The developer can then choose to invoke all implementations or
just the one with the highest priority (specified with what is called the
service ranking). This means that if Liferay has a service that does something,
you can customize or override that service by implementing its interface
yourself and then deploying it with a higher ranking than the original service.
The container then instantiates your implementation when the service is called
by existing code. This simple, clean method is how most customizations are made
to Liferay 7. 

### Components

In OSGi, possibly the best and certainly the easiest way to create services is
through [Declarative
Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services). In
Declarative Services (aka DS), you create Components. A Component is a Java
class (marked with an
[`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html)
annotation) that provides an implementation of a Service (as described above)
and whose instantiation is handled automatically by DS. This is similar to what
you might be used to if you have used Spring Beans or EJBs. DS also provides
dependency injection using annotations
([`@Reference`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Reference.html)).
This is convenient because the "wiring" of components is done by the container
but can be changed while the server is running (unlike Spring). 

Modules may contain as many service declarations and as many components as
desired (or zero, of course). 

A component is the smallest building block of a larger application, and that
application is itself made up of many small components. This makes it easier to
develop an application because you only have to deal with small, well-defined,
bite-sized chunks of code at a time. 

The other main contributor to @product@'s success is its UI concepts. 

## UI Concepts

On @product@ you can develop UIs using well-known frameworks, including
[Angular](https://angular.io/), [React](https://reactjs.org/), and
[Vue](https://vuejs.org/), and using CSS based on Bootstrap. This encourages you
to create UIs in ways that might be familiar to you. If you don't already have a
workflow, Liferay's UI development principles, components, and tools facilitate
creating and customizing UIs. [Understanding UI
Concepts](/docs/7-2/customization/-/knowledge_base/c/understanding-ui-concepts)
explains more. 

The articles that follow dive deeper into the core, modules, and UI so that you
can customize @product@ and develop terrific digital experiences on it.    
