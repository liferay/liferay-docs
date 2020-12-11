---
header-id: service-trackers-for-osgi-services
---

# Service Trackers for OSGi Services

[TOC levels=1-4]

In an OSGi runtime ecosystem, you must consider how your apps can rely
on OSGi services in other modules for functionality. It's possible for service
implementations to be swapped out or removed entirely, and your app must not
just survive but thrive in this environment. 

If you want to [call OSGi services from an OSGi Declarative Services
`@Component`](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
classes, it's easy: you just use a [Declarative Services
(DS)](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html)
annotation,
[`@Reference`](ttps://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Reference.html),
to inject the service. The component activates when the referenced service is
available. 

| **Note:** The `@Reference` annotation can only be used in a class that is
| annotated with
| [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html).
| That is, only a Declarative Services component can use `@Reference` to bind to
| an OSGi service.

If you want to call an OSGi service from a bean, use [OSGi CDI
integration](/docs/7-2/frameworks/-/knowledge_base/f/using-osgi-services-in-a-bean). 

DS `@Reference` with `@Component`s and OSGi CDI integration with beans manage
much of the complexity of service dynamism for you transparently. If you can use
either of them, you should. Otherwise, read [implement a Service
Tracker](/docs/7-2/frameworks/-/knowledge_base/f/using-a-service-tracker) to
look up services in the service registry. 
