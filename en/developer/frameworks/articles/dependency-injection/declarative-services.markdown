---
header-id: declarative-services
---

# Declarative Services

[TOC levels=1-4]

@product@'s OSGi framework registers objects as *services*. Each service
offers functionality and can leverage functionality other services provide. The
OSGi Services model supports a collaborative environment for objects.

Declarative Services (DS) provides a service component model on top of OSGi
Services. DS service components are marked with the
[`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html)
annotation and implement or extend a service class. Service components can refer
to and use each other's services. The Service Component Runtime (SCR) registers
component services and handles binding them to other components that reference
them. 

Here's how the "magic" happens:

1.  **Service registration:** On installing a module that contains a
    service component, the SCR creates a component configuration that associates
    the component with its specified service type and stores it in a service
    registry. 

2.  **Service reference handling:** On installing a module whose service
    component references another service type, the SCR searches the registry for
    a component configuration that matches the service type and on finding a
    match binds an instance of that service to the referring component.

It's publish, find, and bind at its best!

How do you use DS to register and bind services? Does it involve creating XML
files? No, it's much easier than that. You use two annotations: `@Component` and
`@Reference`. 

-   [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html):
    Add this annotation to a class definition to make the class a component--a
    service provider. 

-   [`@Reference`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Reference.html):
    Add this annotation to a field to inject it with a service that matches the
    field's type. 

The
[`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html)
annotation makes the class an OSGi component. Setting the annotation's `service`
attribute to a particular service type allows other components to reference the
service component by that service type.

For example, the following class is a service component of type `SomeApi.class`.

```java
@Component(
    service = SomeApi.class
)
public class Service1 implements SomeApi {
   ...
}
```

On deploying this class's module, the SCR creates a component configuration that
associates the class with the service type `SomeApi`.

Specifying a service reference is easy too. Applying the
[`@Reference`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Reference.html)
annotation to a field marks it to be injected with a service matching the
field's type.

```java
@Reference
SomeApi _someApi;
```

On deploying this class's module, the SCR finds a component configuration of the
class type `SomeApi` and binds the service to this referencing component class's
field `SomeApi _someApi`.

| **Note:** The `@Reference` annotation can only be used in a class that is
| annotated with `@Component` (i.e, a Declarative Services component ) or a bean
| class that uses OSGi CDI integration. 

| **Note:** At build time in modules created from
| [Liferay project templates](/docs/7-1/reference/-/knowledge_base/r/project-templates),
| bnd creates a *component description* file for each module's components
| automatically. The file specifies the component's services, dependencies, and
| activation characteristics. On module deployment, the OSGi framework reads the
| component description to create the component and manage its dependency on 
| other components.

The SCR stands ready to pair service components with each other. For each
referencing component, the SCR binds an instance of the targeted service to it.

As an improvement over dependency injection with Spring, OSGi Declarative
Services supports dynamic dependency injection. You can create and publish
service components for other classes to use. You can update the components and
even publish alternative component implementations for a service. This kind of
dynamism is a powerful part of @product@. 
