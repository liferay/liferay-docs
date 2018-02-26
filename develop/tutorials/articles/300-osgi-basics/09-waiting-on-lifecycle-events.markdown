# Waiting on Lifecycle Events [](id=waiting-on-lifecycle-events)

Liferay registers lifecycle events, such as portal and database initialization,
into the OSGi service registry. In your OSGi Component or non-component class,
you can listen for these events by way of their service registrations. The
[ModuleServiceLifecycle interface](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/module/framework/ModuleServiceLifecycle.html)
defines these names for the lifecycle event services:

-   [DATABASE_INITIALIZED](@platform-ref@/7.0/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.DATABASE_INITIALIZED)
-   [PORTAL_INITIALIZED](@platform-ref@/7.0/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.PORTAL_INITIALIZED)
-   [SPRING_INITIALIZED](@platform-ref@/7.0/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.SPRING_INITIALIZED)

Here you'll learn how to wait on lifecycle event services to act on them from
within a component or non-component class. 

## Taking action from a component [](id=taking-action-from-a-component)

Declarative Services (DS) facilitates waiting for OSGi services and acting on
them once their available. 

Here's a component whose `doSomething` method is invoked once the
`ModuleServiceLifecycle.PORTAL_INITIALIZED` lifecycle vent service and other
services are available. 

    @Component(immediate = true, service = XyzApi.class)
    public class MyXyz implements XyzApi {

        // Plain old OSGi service component
        @Reference
        SomeOsgiService _someOsgiService;

        // Service Builder generated service
        @Reference
        DDMStructureLocalService _ddmStructureLocalService;

        // Liferay lifecycle service
        @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
        ModuleServiceLifecycle _portalInitModuleServiceLifecycle;

        @Activate
        public void doSomething() {
            ...
        }
    }

The component above references these OSGi services: 

-   `SomeOsgiService`: Standard OSGi service 
-   `DDMStructureLocalService`: Service Builder generated OSGi service
-   `ModuleServiceLifecycle.PORTAL_INITIALIZED`: Liferay portal initialization
    lifecycle event service. 

Once ALL the fields are bound to service objects, the component's
`doSomething` method (annotated with `@Activate`) executes. 

Here's how to act on services in your component:

1.  For each lifecycle event service and OSGi service your component uses, add a
    field of that service type and add an `@Reference` annotation to that field. The OSGi framework binds the services to your fields. This field, for example, binds to a standard OSGi service.

        @Reference
        SomeOsgiService _someOsgiService;

2.  To bind to a particular lifecycle event service, target its name as the
    [ModuleServiceLifecycle interface](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/module/framework/ModuleServiceLifecycle.html)
    defines. This field, for example, targets @product@'s database
    initialization. 

        @Reference(target = ModuleServiceLifecycle.DATABASE_INITIALIZED)
        ModuleServiceLifecycle _dataInitModuleServiceLifecycle;

3.  Create a method from which to take action on the event(s) and add the
    `@Activate` annotation to that method. It is invoked once all the service
    objects are bound to the component's fields. 

Your component takes action (via its `@Activate` method) once all its service
dependencies resolve. DS components are the easiest way to act on lifecycle event services. 

## Taking action from a non-component class [](id=taking-action-from-a-class-thats-not-a-component)

Classes that aren't Declarative Services (DS) components can use an
`org.osgi.util.tracker.ServiceTracker` extension or
`org.osgi.util.tracker.ServiceTrackerCustomizer` extension as a
[service callback handler](/develop/tutorials/-/knowledge_base/7-0/service-trackers#implementing-a-callback-handler-for-services)
for the lifecycle event. If you depend on multiple services, add logic to your
`ServiceTracker` or `ServiceTrackerCustomizer` extension to coordinate taking
action when all the services are available. 

To target a lifecycle event service, create a service tracker that filters on
that service. Use `org.osgi.framework.FrameworkUtil` to create an
`org.osgi.framework.Filter` that specifies the service. Then pass that filter as
a parameter to the service tracker constructor. For example, this service
tracker filters on the lifecycle service
`ModuleServiceLifecycle.PORTAL_INITIALIZED`.

    new ServiceTracker<>(
        bundleContext, 
        FrameworkUtil.createFilter(
            "(&(objectClass=com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle)" + ModuleServiceLifecycle.PORTAL_INITIALIZED + ")"),
        null);

Acting on lifecycle event services in this way requires service callback
handling and some boilerplate code. Using DS components is easier and more
elegant, but at least service trackers provide a way to work with lifecycle
events outside of DS components. 

## Related Topics [](id=related-topics)

[Service Trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers)
