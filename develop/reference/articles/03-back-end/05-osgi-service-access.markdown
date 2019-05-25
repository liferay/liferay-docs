---
header-id: osgi-service-access
---

# OSGi Service Access

[TOC levels=1-4]

Accessing an OSGi service depends on the type of code (e.g., Spring Bean, OSGi
component, etc.) accessing it and the code's application context. 

| Accessing Code | Service Access Method |
| --- | --- |
| Service Builder code (a Spring Bean) in the service's application context | [an `@BeanReference`](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-from-service-builder-code#referencing-a-spring-bean-that-is-in-the-application-context) |
| Service Builder code (a Spring Bean) outside the service's application context | [an `@ServiceReference`](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-from-service-builder-code#referencing-an-osgi-service) |
| A class in a WAR outside the service's application context | [a `ServiceTracker`](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers) |
| An OSGi component outside the service's application context | [an `@Reference`](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services) |
