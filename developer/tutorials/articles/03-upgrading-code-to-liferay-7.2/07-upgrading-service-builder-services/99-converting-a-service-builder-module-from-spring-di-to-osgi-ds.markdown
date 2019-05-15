# Converting a Service Builder Module from Spring DI to OSGi DS

Prior to @product@ 7.2, Service Builder modules could only use Spring for
dependency injection (DI). Now OSGi Declarative Services (DS) is the default
dependency injection mechanism for new Service Builder modules. It's easier to
learn and fosters loose coupling between services. If you have an existing
Service Builder module that uses Spring DI, you can modify it to use DS. 

Here are the conversion steps:

1.  Prepare your project for DS 

2.  Update your Spring bean classes 

3.  Resolve any circular dependencies 

Now prepare your project. 

## Step 1: Prepare Your Project for DS 

Setting up your project's metadata, dependencies, and `service.xml` is
straightforward. 

1.  Enable the DS annotation option for your inherited dependencies by adding 
    this line to your bnd file:

    ```
    -dsannotations-options: inherit
    ```
    
2.  Since DS Service Builder modules use the AOP API, add it as a compile 
    dependency: 

    ```
    com.liferay:com.liferay.portal.aop.api:1.0.0
    ```

3.  Add the `dependency-injector="ds"` attribute to your `service.xml` file's 
    `<service-builder>` element. 

## Step 2: Update Your Spring Bean Classes

Service Builder regenerates your service interfaces automatically. You must
update some of your non-generated Spring bean classes to use DS. Here are the
steps: 

1.  Add the `org.osgi.service.component.annotations.Component` annotation to
    your `*LocalServiceImpl`, `*ServiceImpl`, and `*FinderImpl` classes. 

    1.  If the class implements a `*Finder` interface, declare the component as 
        that service type. For example,

        ```java
        @Component(service = MyFinder.class) 
        ```
    
    2.  If the class implements a remote or local service, declare the component
        as the `com.liferay.portal.aop.AopService` service type. For example,

        ```java
        @Component(service = AopService.class)
        ```

    3.  If it's a remote service, enable JSON web services by setting these 
        properties in your `@Component` annotation:

        -   `json.web.service.context.name`
        -   `json.web.service.context.path`
    
        Set them to the same values as the properties in your remote service
        interface's `@OSGiBeanProperties` annotation. 
    
    4.  If it's a local service, enable `PersistedModelLocalService` service 
        tracking by setting the `@Component` property `model.class.name` to the
        service entity's fully qualified class name. 

2.  Replace all the `@ServiceReference` and `@BeanReference` field annotations 
    with the DS `@Reference` annotation. 
    
3.  [Run Service Builder](/docs/frameworks/7-2/-/knowledge_base/f/running-service-builder)
    to regenerate the interfaces based on your implementation changes. 

4.  Replace the following methods:

    -   `afterPropertiesSet() {...}` &rarr; `activate() {...}` and annotate with
        `@Activate`.

    -   `destroy() {...}` &rarr; `deactivate() {...}` and annotate with 
        `@Deactivate`. 

Next, you'll work out any remaining references you need. 

## Step 3: Resolve Any Circular Dependencies

Service Builder avoids adding references that could cause circular
dependencies.
    
1.  Use the `@Reference` DS annotation on fields as desired. 

2.  Deploy your module. 

3.  Detect and resolve any circular dependencies. Local components failing to 
    resolve can be due to circular dependencies. If you have circular
    dependencies, they're most likely between `*LocalService` components. All
    circular dependencies originate from fields you annotate with `@Reference`
    in step 3.1. 

    - Run `system:check` in
      [Gogo Shell](/docs/customization/7-2/-/knowledge_base/c/felix-gogo-shell)
      to detect obvious circular dependencies.
    
    - Run `scr:info [component]` in the
      [Gogo Shell](/docs/customization/7-2/-/knowledge_base/c/felix-gogo-shell)
      to examine components and determine the best places to break circular
      dependencies.

Congratulations on converting your service module to use Declarative Services. 

## Related Topics 

[Service Builder](/docs/frameworks/7-2/-/knowledge_base/f/service-builder)

[Understanding the Code Service Builder Generates](/docs/frameworks/7-2/-/knowledge_base/f/understanding-the-code-generated-by-service-builder)

[Spring Dependency Injection](/docs/frameworks/7-2/-/knowledge_base/f/spring-dependency-injection)

[Declarative Services](/docs/frameworks/7-2/-/knowledge_base/f/declarative-services)
