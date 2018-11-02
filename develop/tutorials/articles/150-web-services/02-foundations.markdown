# Foundations: JAX-RS, OSGi Whiteboard, and APIO [](id=foundations-jax-rs-osgi-whiteboard-and-apio)

+$$$

**Note:** Hypermedia REST APIs are currently only available as a limited private 
alpha for Liferay DXP 7.1 Fix Pack 3+. 

$$$

Hypermedia REST APIs in @product@ are built on these technologies:

-   JAX-RS
-   OSGi's JAX-RS Whiteboard Specification
-   APIO Architect

You don't need a detailed understanding of these, but you should understand what 
they are and how they make @product@'s Hypermedia REST APIs possible. 

## JAX-RS [](id=jax-rs)

Hypermedia REST APIs follow the 
[JAX-RS specification](http://download.oracle.com/otndocs/jcp/jaxrs-2_1-final-eval-spec/index.html). 
Developers create JAX-RS services via annotations that define the resources, 
endpoints, mappings, and all the elements required to publish a REST web 
service. Developers can therefore extend the API by 
[creating new JAX-RS services](https://docs.oracle.com/javaee/7/tutorial/jaxrs.htm). 

## OSGi's JAX-RS Whiteboard Specification [](id=osgi-jaxrs-whiteboard)

The REST endpoints that compose Hypermedia REST APIs are developed and deployed 
as OSGi modules. 
[OSGi's JAX-RS Whiteboard](https://osgi.org/specification/osgi.cmpn/7.0.0/service.jaxrs.html#service.jaxrs.whiteboard) 
is used to bootstrap and publish JAX-RS resources, register them as OSGi 
components, and resolve possible references to other OSGi components (or 
services) already deployed as part of other modules. The OSGI JAX-RS Whiteboard 
is a 
[whiteboard pattern](https://en.wikipedia.org/wiki/Whiteboard_Pattern) 
implementation that bootstraps the JAX-RS runtime with OSGi modules. This 
pattern better manages the lifecycle of components and their dependencies. 

To use Hypermedia REST APIs, you don't need to worry about the whiteboard 
pattern's implementation details. Just know that the API endpoints are JAX-RS 
resources developed and registered as OSGi services. 

## APIO Architect [](id=apio-architect)

Writing 
[evolvable](https://evolvable-apis.org/) 
hypermedia APIs isn't easy. The APIO project and its 
[APIO Architect](https://github.com/liferay/com-liferay-apio-architect) 
component aim to ease this burden. APIO Architect is a toolkit that facilitates 
an opinionated way of building hypermedia REST web services on top of JAX-RS and 
OSGi's JAX-RS Whiteboard. APIO Architect transparently adds support for several 
hypermedia formats (e.g., HAL, Siren, JSON-LD), content negotiation, resource 
discovery, collection patterns, and many other features that help build 
hypermedia REST APIs. 

## Related Topics [](id=related-topics)

[Consuming Web Services](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services)
