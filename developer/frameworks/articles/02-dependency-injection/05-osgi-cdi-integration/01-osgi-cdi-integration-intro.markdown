---
header-id=osgi-cdi-integration
---

# OSGi CDI Integration

@product@'s runtime environment consists of services (OSGi services). The OSGi
service registry and Service Component Runtime (SCR) facilitate providing and
consuming services.
[Contexts and Dependency Injection (CDI)](http://docs.jboss.org/cdi/spec/2.0/cdi-spec.html)
beans is a popular, powerful way to develop applications. 
[OSGi CDI Integration](https://osgi.org/specification/osgi.enterprise/7.0.0/service.cdi.html)
brings features and capabilities of to OSGi and makes OSGi services available to
beans. Here you'll learn how to

-   [Publish CDI beans as OSGi services](/developer/customization/-/knowledge_base/7-2/publishing-cdi-beans-as-osgi-services):
    Register CDI beans as services you can use to customize @product@
    components. 

-   [Use OSGi services in beans](/developer/customization/-/knowledge_base/7-2/using-osgi-services-in-a-bean):
    Leverage any OSGi service published on @product@ in any bean. 

The following use cases provide more detail. 

## Use Case: Registering a CDI bean as an OSGi service

@product@ extension points are implemented as OSGi services. If you write CDI
beans and you want to extend or override an OSGi service, you can write your
extension/override as a CDI bean and use OSGi CDI integration to publish your
bean as an OSGi service. 

By implementing the service in your CDI bean class and adding the integration's
`@org.osgi.service.cdi.annotations.Service` annotation to it, your bean
registers as providing that OSGi service. In this way, service consumers can use
your service implementation (i.e., your CDI bean). 

For example, the Service Registry in figure 1 shows two implementations of an
OSGi service called `S1`: 

- `MyBean` is a CDI bean whose service rank is `1000`. 

- `MySvcImpl` has a service rank of `0`. 

The Service Component Runtime (`SCR`) finds the matching, highest ranked `S1`
service provider and binds it to consumer `C1`. The fact that `MyBean` is a CDI
bean is transparent to the SCR. 

![Figure 1: OSGi Service Component Runtime (SCR) finds `MyBean` as the best (highest ranked) `S1` service provider and binds it to consumer component `C1`.](../../../images/injecting-bean-osgi-service.png)

Once a CDI bean is registered as a service, components can use it as they would
any other OSGi service. 

## Use Case: Using an OSGi service in a bean

@product@ facilitates providing and consuming OSGi services (services).
Liferay's services let you manipulate all kinds of entities (e.g., users, sites,
blog entries, and more). Marketplace apps installed on @product@ also provide
helpful services. OSGi CDI integration enables your beans to access OSGi
services on @product@. 

In figure 2, for example, CDI bean `SomeBean` uses the OSGi CDI integration
annotation `@org.osgi.service.cdi.annotations.Reference` (along with CDI
annotation `@Inject`) to inject the OSGi service `UserLocalService`. The Service
Component Runtime (`SCR`) finds the `UserLocalService` in the Service Registry
and binds it to `SomeBean`'s field `userSvc`. 


![Figure 2: Here how Liferay's `UserLocalService` is injected into a bean.](../../../images/using-a-service-in-a-bean.png) 

These are the most common use cases, but you might have more.  Get started using
OSGi CDI integration to
[publish CDI beans as OSGi services](/developer/customization/-/knowledge_base/7-2/publishing-cdi-beans-as-osgi-services)! 
