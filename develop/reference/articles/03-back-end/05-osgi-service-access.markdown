# OSGi Service Access [](id=osgi-service-access)

Accessing an OSGi service depends on the type of code (e.g., Spring Bean, OSGi
component, etc.) accessing it and the code's application context. 

| Accessing Code | Service Access Method |
| --- | --- |
| Service Builder code (a Spring Bean) in the service's application context | [an `@BeanReference`](/develop/tutorials/-/knowledge_base/7-1/invoking-services-from-service-builder-code#referencing-a-spring-bean-that-is-in-the-application-context) |
| Service Builder code (a Spring Bean) outside the service's application context | [an `@ServiceReference`](/develop/tutorials/-/knowledge_base/7-1/invoking-services-from-service-builder-code#referencing-an-osgi-service) |
| A class in a WAR outside the service's application context | [a `ServiceTracker`](/develop/tutorials/-/knowledge_base/7-1/service-trackers) |
| An OSGi component outside the service's application context | [an `@Reference`](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services) |
