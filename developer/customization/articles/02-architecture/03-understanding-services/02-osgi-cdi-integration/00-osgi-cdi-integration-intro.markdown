# OSGi CDI Integration 

OSGi CDI Integration brings features and capabilities of Contexts and Dependency
Injection (CDI) to OSGi and makes OSGi services available to beans.  Here's what
you'll learn how to do:

- Publish CDI beans as OSG services: Register CDI beans as services you can use 
to customize @product@ components. 

- Use OSGi services in beans: Leverage any OSGi service published on @product@ 
in any bean. 

The following use cases provide more detail. 

## Use Case: Registering a CDI bean as an OSGi service 

As a CDI bean developer, you want to use a CDI bean to customize or override an
existing OSGi service (e.g., a Liferay service). By implementing the service in
your bean class and adding the integration's `@Service` annotation to it, you
register your bean class as providing that OSGi service. In this way,
service consumers can use your service implementation (i.e., your bean). 

![Figure 1: OSGi Service Component Runtime (SCR) finds `MyBean` as the best (highest ranked) `S1` service provider and binds it to consumer component `C1`.](../../../../images/injecting-bean-osgi-service.png)

Once a CDI bean is registered as a service, components can use it as they would
any other OSGi service. 

## Use Case: Using an OSGi service in a bean 

As a developer, you want use an OSGi service (e.g., one of Liferay's services or
a third party service published in the service registry). 

![Here Liferay's `UserLocalService` is injected into a bean.](../../../../images/using-a-service-in-a-bean.png) 

These are the most common use cases, but you might have more.  Get started using
OSGi CDI integration! 
