# Waiting on Lifecycle Events [](id=waiting-on-lifecycle-events)

Liferay registers lifecycle events like portal and database initialization
into the OSGi service registry. Your OSGi Component or non-component class
can listen for these events by way of their service registrations. The 
[`ModuleServiceLifecycle` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/module/framework/ModuleServiceLifecycle.html)
defines these names for the lifecycle event services:

-   [DATABASE_INITIALIZED](@platform-ref@/7.1-latest/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.DATABASE_INITIALIZED)
-   [PORTAL_INITIALIZED](@platform-ref@/7.1-latest/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.PORTAL_INITIALIZED)
-   [SPRING_INITIALIZED](@platform-ref@/7.1-latest/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.SPRING_INITIALIZED)

Here you'll learn how to wait on lifecycle event services to act on them from
within a component or non-component class. 

## Taking action from a component [](id=taking-action-from-a-component)

[Declarative Services (DS)](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html)
facilitates waiting for OSGi services and acting on them once they're available.

Here's a component whose `doSomething` method is invoked once the
`ModuleServiceLifecycle.PORTAL_INITIALIZED` lifecycle event service and other
services are available. 

    @Component
    public class MyXyz implements XyzApi {

        // Plain old OSGi service
        @Reference
        private SomeOsgiService _someOsgiService;

        // Service Builder generated service
        @Reference
        private DDMStructureLocalService _ddmStructureLocalService;

        // Liferay lifecycle service
        @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
        private ModuleServiceLifecycle _portalInitialized;

        @Activate
        public void doSomething() {
            // `@Activate` method is only executed once all of
            // `_someOsgiService`,
            // `_ddmStructureLocalService` and
            // `_portalInitialized`
            // are set.
        }
    }

Here's how to act on services in your component:

1.  For each lifecycle event service and OSGi service your component uses, add a
    field of that service type and add an `@Reference` annotation to that field.
    The OSGi framework binds the services to your fields. This field, for
    example, binds to a standard OSGi service.

        @Reference
        SomeOsgiService _someOsgiService;

2.  To bind to a particular lifecycle event service, target its name as the 
    [`ModuleServiceLifecycle` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/module/framework/ModuleServiceLifecycle.html)
    defines. This field, for example, targets database initialization. 

        @Reference(target = ModuleServiceLifecycle.DATABASE_INITIALIZED)
        ModuleServiceLifecycle _dataInitialized;

3.  Create a method that's triggered on the event(s) and add the `@Activate`
    annotation to that method. It's invoked when all the service objects are
    bound to the component's fields. 

Your component fires (via its `@Activate` method) after all its service
dependencies resolve. DS components are the easiest way to act on lifecycle
event services. 

## Taking action from a non-component class [](id=taking-action-from-a-class-thats-not-a-component)

Classes that aren't DS components can use a
`org.osgi.util.tracker.ServiceTracker` or
`org.osgi.util.tracker.ServiceTrackerCustomizer` as a
[service callback handler](/develop/tutorials/-/knowledge_base/7-1/service-trackers#implementing-a-callback-handler-for-services)
for the lifecycle event. If you depend on multiple services, add logic to your
`ServiceTracker` or `ServiceTrackerCustomizer` to coordinate taking action when
all the services are available. 

To target a lifecycle event service, create a service tracker that filters on
that service. Use `org.osgi.framework.FrameworkUtil` to create an
`org.osgi.framework.Filter` that specifies the service. Then pass that filter as
a parameter to the service tracker constructor. For example, this service
tracker filters on the lifecycle service
`ModuleServiceLifecycle.PORTAL_INITIALIZED`.

    import org.osgi.framework.Filter;
    import org.osgi.framework.FrameworkUtil;

    Filter filter = FrameworkUtil.createFilter(
        String.format(
            "(&(objectClass=%s)%s)",
            ModuleServiceLifecycle.class.getName(),
            ModuleServiceLifecycle.PORTAL_INITIALIZED));

    new ServiceTracker<>(bundleContext, filter, null);

Acting on lifecycle event services in this way requires service callback
handling and some boilerplate code. Using DS components is easier and more
elegant, but at least service trackers provide a way to work with lifecycle
events outside of DS components. 

## Related Topics [](id=related-topics)

[Service Trackers](/develop/tutorials/-/knowledge_base/7-1/service-trackers)
