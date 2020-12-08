---
header-id: migrating-a-service-builder-module-from-spring-di-to-osgi-ds
---

# Migrating a Service Builder Module from Spring DI to OSGi DS

[TOC levels=1-4]

Prior to @product@ 7.2, Service Builder modules could only use Spring for
dependency injection (DI). Now 
[OSGi Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services) (DS) is
the default dependency injection mechanism for new Service Builder modules. It's
easier to learn and fosters loose coupling between services. If you have an
existing Service Builder module that uses Spring DI, you can modify it to use
DS. 

Here are the conversion steps:

1.  Prepare your project for DS 

2.  Update your Spring bean classes 

3.  Resolve any circular dependencies 

Now prepare your project. 

## Step 1: Prepare Your Project for DS

Prepare your project's metadata, dependencies, and `service.xml` for DS. 

1.  Enable the DS annotation option for your inherited dependencies by 
    adding this line to your `bnd.bnd` file:

    ```
    -dsannotations-options: inherit
    ```

2.  Since DS Service Builder modules use the AOP API, add it as a compile 
    dependency in `build.gradle`: 

    ```
    compileOnly group: "com.liferay:com.liferay.portal.aop.api", version: "1.0.0"
    ```

3.  Add the `dependency-injector="ds"` attribute to your `service.xml` 
    file's `<service-builder>` element: 

    ```xml
    <service-builder dependency-injector="ds" >
    ```

## Step 2: Update Your Spring Bean Classes

Some of your 
[non-generated Spring bean classes](/docs/7-2/appdev/-/knowledge_base/a/understanding-the-code-generated-by-service-builder)
must be updated to use DS. 

1.  Add the [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html)
    annotation to your `*LocalServiceImpl`, `*ServiceImpl`, and `*FinderImpl`
    classes. 

2.  If the class implements a `*Finder` interface, declare the component as 
    that service type. Example: 

    ```java
    @Component(service = MyFinder.class)
    ```

3.  If the class implements a remote or local service, declare the component
    as the `com.liferay.portal.aop.AopService` service type. Example:

    ```java
    @Component(service = AopService.class)
    ```

4.  If it's a remote service (i.e., `-ServiceImpl` instead of
    `-LocalServiceImpl`), enable JSON web services by setting these 
    properties in your `@Component` annotation:

    -   `json.web.service.context.name`
    -   `json.web.service.context.path`

    Set them to the same values as the properties in your remote service
    interface's
    [`@OSGiBeanProperties`](https://docs.liferay.com/ce/portal/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/spring/osgi/OSGiBeanProperties.html)
    annotation. 

5.  If it's a local service, enable [`PersistedModelLocalService`](https://docs.liferay.com/ce/portal/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/PersistedModelLocalService.html)
    service tracking by setting the `@Component` property
    `model.class.name` to the service entity's fully qualified class name. 

6.  Replace all the `@ServiceReference` and `@BeanReference` field annotations 
    with the DS
    [`@Reference`](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Reference.html)
    annotation. 

7.  Use the `@Reference` field annotation to access any other services you need.
 
8.  [Run Service Builder](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder)
    to regenerate the interfaces based on your implementation changes. 

9.  Replace the following methods:

    -   `afterPropertiesSet() {...}` &rarr; `activate() {...}` and annotate with
        [`@Activate`](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html).

    -   `destroy() {...}` &rarr; `deactivate() {...}` and annotate with 
        [`@Deactivate`](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html). 

Next, you'll work out any remaining references you need. 

## Step 3: Resolve Any Circular Dependencies

Circular dependencies occur in a module if two or more of its DS services refer
to each another (either directly or indirectly). A direct reference occurs, for
example, when service `A` references service `B`, and `B` references `A`. Here's
what the service components might look like:

`AImpl.java`:

```java
@Component(service = A.class)
public class AImpl implements A {
    @Reference
    private B _b;
}
```

`BImpl.java`:

```java
@Component(service = B.class)
public class BImpl implements B {
    @Reference
    private A _a;
} 
```

`AImpl` and `BImpl` directly depend on each other.  This circular dependency
prevents each service component from resolving. DS service activation requires
that all of a service's dependencies (references) be satisfied. 

**Note:** Service resolution is independent and separate from module (OSGi 
bundle) resolution:

-   Module resolution is determined by the module's manifest.
-   Modules resolve before any of their services become active.
-   Services inside a module cannot activate if the module cannot resolve.
-   A module can resolve even if none of its services activate.

The example above demonstrates a very small circle, composed of only two
classes, but a circle can compose more classes. For example, `A` references `B`,
`B` references `C`, `C` references `A`. Detecting and resolving such a
dependency can be complicated. 

There is no general, correct way to detect and resolve circular dependencies;
cases vary. However, Liferay provides tools that facilitate detecting circular
dependencies and examining the DS service components involved.

-   `system:check`: This
    [Gogo shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell)
    command provides several checks, including one that detects inactive
    service components whose required references are unresolved.

-   `scr:info [component]`: Execute this
    [Gogo shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell)
    command on an unresolved component to report its unresolved references. 

| **Note:** Service resolution in DS dependency injection (DI) is different 
| than in services that use Liferay's Spring DI. In the latter case, all Spring
| beans in the same module act as a single bundle of services that activate
| together and can bind together before activation. DS doesn't have this 
| feature. With DS, each component in a module is its own service and must
| resolve on its own. 

Congratulations on converting your service module to use Declarative Services. 

## Related Topics 

[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)

[Understanding the Code Service Builder Generates](/docs/7-2/appdev/-/knowledge_base/a/understanding-the-code-generated-by-service-builder)

[Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
