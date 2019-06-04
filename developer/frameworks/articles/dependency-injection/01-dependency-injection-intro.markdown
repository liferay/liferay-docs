---
header-id: dependency-injection
---

# Dependency Injection

[TOC levels=1-4]

When you're using a object based on its interface, you don't have to concern
yourself with the implementation because it's abstracted from you. At runtime,
the implementation used depends on your environment and your app's
configuration. @product@ offers several standard ways to register
implementations and inject them into your applications. 

**Contexts and Dependency Injection (CDI):** Is the Java EE standard dependency 
injection mechanism. @product@'s CDI bean container makes an application's
concrete classes available as beans. Bean classes can user other beans by way of
injecting them into their fields that have the `@Inject` annotation. 

**OSGi Declarative Services:** @product@'s OSGi runtime framework allows 
components to register as service provides and other components can call on the
registry to bind the services to their fields that have the `@Reference`
annotation. @product@'s services and services you create using [Service
Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) are available as
OSGi Declarative Services. 

**Spring DI:** The Spring framework includes inversion of control (IoC) and 
dependency injection. It's available to applications that configure the Spring
framework.  

As an added bonus, @product@ provides [OSGi CDI
integration](/docs/7-2/frameworks/-/knowledge_base/f/osgi-cdi-integration). It
lets you publish CDI beans as OSGi services and consume OSGi services in your
CDI beans. Which dependency injection mechanism will you use? Read on to learn
more about them. 
