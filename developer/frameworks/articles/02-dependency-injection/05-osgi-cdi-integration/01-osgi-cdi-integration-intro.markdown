---
header-id: osgi-cdi-integration
---

# OSGi CDI Integration

[TOC levels=1-4]

@product@'s runtime environment consists of services (OSGi services). The OSGi
service registry and Service Component Runtime (SCR) facilitate providing and
consuming services.
[Contexts and Dependency Injection (CDI)](http://docs.jboss.org/cdi/spec/2.0/cdi-spec.html)
is a Java SE and EE standard for lifecycle events, stateful objects, and
dependency injection. 
[OSGi CDI Integration](https://osgi.org/specification/osgi.enterprise/7.0.0/service.cdi.html)
brings features and capabilities of CDI to OSGi and makes OSGi services available to
CDI beans. Here you'll learn how to

-   [Publish CDI beans as OSGi services](/developer/customization/-/knowledge_base/7-2/publishing-cdi-beans-as-osgi-services):
    Register CDI beans as services you can use to customize @product@
    components. 

-   [Use OSGi services in beans](/developer/customization/-/knowledge_base/7-2/using-osgi-services-in-a-bean):
    Leverage any OSGi service published on @product@ in any bean. 

The following use cases provide more detail. 

## Use Case: Registering a CDI bean as an OSGi service

@product@ extension points are implemented as OSGi services. If there's a piece
of functionality you must customize, you don't have to learn OSGi to do it: you
can write your extension/override as a CDI bean instead and use OSGi CDI
integration to publish your bean as an OSGi service. 

By implementing the service in your CDI bean class and adding the integration's
`@org.osgi.service.cdi.annotations.Service` annotation to it, your bean
registers as providing that OSGi service. In this way, service consumers can use
your service implementation (i.e., your CDI bean). 

For example, the Service Registry in figure 1 shows two implementations of an
OSGi service called `S1`: 

![Figure 1: OSGi Service Component Runtime (SCR) finds `MyBean` as the best (highest ranked) `S1` service provider and binds it to consumer component `C1`.](../../../images/injecting-bean-osgi-service.png)

- `MyBean` is a CDI bean whose service rank is `1000`. 

- `MySvcImpl` has a service rank of `0`. 

The Service Component Runtime (`SCR`) finds the matching, highest ranked `S1`
service provider and binds it to consumer `C1`. The fact that `MyBean` is a CDI
bean is transparent to the SCR. 


Once a CDI bean is registered as a service, components can use it as they would
any other OSGi service. 

## Use Case: Using an OSGi service in a bean

@product@ contains many development frameworks for all of its constructs, such
as Users, Sites, Documents, Comments, and the APIs for these assets are all
implemented as OSGi services. When developers write new applications using
Liferay's development frameworks, new assets become available and integrated
with the rest of the system. OSGi CDI integration enables your beans to access
these OSGi services. 

![Figure 2: Here how Liferay's `UserLocalService` is injected into a bean.](../../../images/using-a-service-in-a-bean.png) 

In figure 2, for example, CDI bean `SomeBean` uses the OSGi CDI integration
annotation `@org.osgi.service.cdi.annotations.Reference` (along with CDI
annotation `@Inject`) to inject the OSGi service `UserLocalService`. The Service
Component Runtime (`SCR`) finds the `UserLocalService` in the Service Registry
and binds it to `SomeBean`'s field `userSvc`. 



These are the most common use cases, but you might have more.  Now you can get
started using OSGi CDI integration to
[publish CDI beans as OSGi services](/developer/customization/-/knowledge_base/7-2/publishing-cdi-beans-as-osgi-services)! 
