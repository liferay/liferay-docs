# Converting a Service Builder Module from Spring DI to OSGi DS

Prior to @product@ 7.2, Service Builder modules used Spring for dependency
injection (DI). Now OSGi Declarative Services (DS) is the default dependency
injection mechanism for new Service Builder modules. It's easier to learn and
fosters loose coupling between services. If you have an existing Service Builder
module that uses Spring DI, you can modify it to use DS. 

Here are the conversion steps:

- Step 1: Prepare your project for DS 

- Step 2: Update your Spring bean classes 

- Step 3: Resolve any circular dependencies 

Now prepare your project. 

## Step 1: Prepare your project for DS 

Setting up your project's metadata, dependencies, and `service.xml` is
straightforward. 

1.  Enable the DS annotation option for your inherited dependencies by adding 
    this line to your bnd File:

        -dsannotations-options: inherit
    
2.  Since DS Service Builder modules use the AOP API, add it as a compile 
    dependency: 

        com.liferay:com.liferay.portal.aop.api:1.0.0

3.  Add the `dependency-injector="ds"` attribute to your `service.xml` file's 
    `<service-builder>` element. 

## Step 2: Update your Spring bean classes

Service Builder regenerates your the standard interfaces automatically. You must update your non-generated Spring bean classes to use DS. Here are the steps: 

1.  If the class implements an interface, add the `@Component` class annotation 
    to it. 

    1.  If the class implements a `*Finder` interface, declare the component as 
        that service type. Example: 

            @Component(service = MyFinder.class) 
    
    2.  If the class implements a remote or local service, declare the component
        as the `AopService` service type. Example:

            @Component(service = AopService.class)

    3.  If it's a remote service, enable JSON web services by setting these 
        properties in your `@Component` annotation:

        -   `json.web.service.context.name`
        -   `json.web.service.context.path`
    
        Set them to the same values as the properties in your remote service
        interface's `@OSGiBeanProperties` annotation. 
    
    4.  If it's a local service, enable `PersistedModelLocalService` service 
        tracking by setting the `@Component` property `model.class.name` to the
        service entity's fully qualified class name. 

2.  Replace the `@ServiceReference` and `@BeanReference` annotations with the DS
    `@Reference` annotation for all fields. 
    
3.  [Run Service Builder](/developer/frameworks/-/knowledge_base/7-2/running-service-builder)
    to regenerate the interfaces based on your implementation changes. 

4.  Replace the following methods:

    -   `afterPropertiesSet() {...}` &rarr; `activate() {...}` and annotate with
        `@Activate`.

    -   `destroy() {...}` &rarr; `deactivate() {...}` and annotate with 
        `@Deactivate`. 

Lastly, you'll work out any remaining references you need. 

## Step 3: Resolve any circular dependencies

Service Builder refrains from adding references it suspects to cause circular 
    dependencies.
    
1.  Use the `@Reference` DS annotation on fields as desired. 

2.  Deploy your module and restart @product@. 

3.  Detect and resolve any circular dependencies. Local components failing to resolve can be due to circular dependencies. 

    -   Run `system:check` in Gogo Shell to detect obvious circular dependencies.
    
    -   Run `scr:info [component]` in Gogo Shell to examine components and determine the best places to break circular dependencies. 

Congratulations on converting your service module to use Declarative Services. 

## Related Topics 

[Service Builder](/developer/frameworks/-/knowledge_base/7-2/service-builder)

[Understanding the Code Service Builder Generates](/developer/frameworks/-/knowledge_base/7-2/understanding-the-code-generated-by-service-builder)

[Spring Dependency Injection](/developer/frameworks/-/knowledge_base/7-2/spring-dependency-injection)

[Declarative Services](/developer/frameworks/-/knowledge_base/7-2/declarative-services)
