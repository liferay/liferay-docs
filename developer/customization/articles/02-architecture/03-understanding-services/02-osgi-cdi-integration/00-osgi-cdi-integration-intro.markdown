# OSGi CDI Integration [](id=osgi-cdi-integration)

[OSGi CDI Integration](https://osgi.org/specification/osgi.enterprise/7.0.0/service.cdi.html)
brings features and capabilities of
[Contexts and Dependency Injection (CDI)](http://docs.jboss.org/cdi/spec/2.0/cdi-spec.html)
to OSGi and makes OSGi services available to beans. Here you'll learn how to:

-   [Publish CDI beans as OSGi services](/developer/customization/-/knowledge_base/7-2/publishing-cdi-beans-as-osgi-services):
    Register CDI beans as services you can use to customize @product@
    components. 

-   [Use OSGi services in beans](/developer/customization/-/knowledge_base/7-2/using-osgi-services-in-a-bean):
    Leverage any OSGi service published on @product@ in any bean. 

The following use cases provide more detail. 

## Use Case: Registering a CDI bean as an OSGi service [](id=use-case-registering-a-cdi-bean-as-an-osgi-service)

As a CDI bean developer, you want to use a CDI bean to provide or override an
existing OSGi service (e.g., a Liferay service). By implementing the service in
your bean class and adding the integration's
`@org.osgi.service.cdi.annotations.Service` annotation to it, you register your
bean class as providing that OSGi service. In this way, service consumers can
use your service implementation (i.e., your bean). 

![Figure 1: OSGi Service Component Runtime (SCR) finds `MyBean` as the best (highest ranked) `S1` service provider and binds it to consumer component `C1`.](../../../../images/injecting-bean-osgi-service.png)

Once a CDI bean is registered as a service, components can use it as they would
any other OSGi service. 

## Use Case: Using an OSGi service in a bean [](id=use-case-using-an-osgi-service-in-a-bean)

As a developer, you want use an OSGi service (e.g., one of Liferay's services or
a third party service published in the service registry). 

![Here Liferay's `UserLocalService` is injected into a bean.](../../../../images/using-a-service-in-a-bean.png) 

These are the most common use cases, but you might have more.  Get started using
OSGi CDI integration to
[publish CDI beans as OSGi services](/developer/customization/-/knowledge_base/7-2/publishing-cdi-beans-as-osgi-services)! 
