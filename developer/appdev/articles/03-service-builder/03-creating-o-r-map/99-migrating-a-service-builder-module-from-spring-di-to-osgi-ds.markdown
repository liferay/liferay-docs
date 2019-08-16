---
header-id: migrating-a-service-builder-module-from-spring-di-to-osgi-ds
---

# Migrating a Service Builder Module from Spring DI to OSGi DS

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

1.  Add the [`@Component`](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Component.html)
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
 
7.  [Run Service Builder](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder)
    to regenerate the interfaces based on your implementation changes. 

8.  Replace the following methods:

    -   `afterPropertiesSet() {...}` &rarr; `activate() {...}` and annotate with
        [`@Activate`](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html).

    -   `destroy() {...}` &rarr; `deactivate() {...}` and annotate with 
        [`@Deactivate`](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html). 

Next, you'll work out any remaining references you need. 

## Step 3: Resolve Any Circular Dependencies

Service Builder avoids adding references that could cause circular
dependencies.

<!-- This is not explained well. Explain what a circular dependency is, what it
looks like, why it affects DS and not Spring, and how to resolve it. -Rich -->

1.  Use the `@Reference` DS annotation on fields as desired. 

2.  [Deploy](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project)
    your module. 

3.  Detect and resolve any circular dependencies. Local components failing to 
    resolve can be due to circular dependencies. If you have circular
    dependencies, they're most likely between `*LocalService` components. All
    circular dependencies originate from fields you annotate with `@Reference`
    in Step 3.1. 

    -   Run `system:check` in [Gogo
        Shell](/docs/7-2/reference/-/knowledge_base/r/gogo-shell) to detect
        obvious circular dependencies.

    -   Run `scr:info [component]` in Gogo Shell to examine components and 
        determine the best places to break circular dependencies. 

Congratulations on converting your service module to use Declarative Services. 

## Related Topics 

[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)

[Understanding the Code Service Builder Generates](/docs/7-2/appdev/-/knowledge_base/a/understanding-the-code-generated-by-service-builder)

[Spring Dependency Injection](/docs/7-2/frameworks/-/knowledge_base/f/spring-dependency-injection)

[Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
