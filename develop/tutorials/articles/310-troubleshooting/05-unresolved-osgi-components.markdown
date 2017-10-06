# Detecting Unresolved OSGi Components [](id=detecting-unresolved-osgi-components)

@product@ includes 
[Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell) 
commands that come in handy when trying to diagnose a problem due to an 
unresolved OSGi component. The specific tools to use depend on the component 
framework of the unresolved component. Most @product@ components are developed 
using Declarative Services (DS), also known as SCR (Service Component Runtime). 
An exception to this is @product@'s Service Builder services, which are provided 
as Dependency Manager (DM) components. Both 
[Declarative Services](http://felix.apache.org/documentation/subprojects/apache-felix-service-component-runtime.html) 
and 
[Dependency Manager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager.html) 
are Apache Felix projects. 

Start with DS, since most @product@ components, apart from Service Builder
components, are DS components. Suppose one of your bundle's components has an
unsatisfied service reference. How can you detect this? Two ways: 

- You can enable an Declarative Services Unsatisfied Component Scanner to report
  unsatisfied references automatically or 

- You can check for them manually.

+$$$

**Note**: The Declarative Services Unsatisfied Component Scanner appears in DXP
Digital Enterprise Fix 7.0 Pack 31 and Liferay CE Portal 7.0 GA5.

$$$

Here are the steps for enabling the unsatisfied component scanner:

1.  Create a file
    `com.liferay.portal.osgi.debug.declarative.service.internal.configuration.UnsatisfiedComponentScannerConfiguration.cfg`. 

2.  Add the following file content:

        unsatisfiedComponentScanningInterval=5

3.  Copy the file into `[LIFERAY_HOME]/osgi/configs`.

The scanner detects and logs unsatisfied service component references. The log
message includes the class that contains the reference, the bundle's ID, and the
referenced component type. 

Here's an example scanner message: 

    11:18:28,881 WARN  [Declarative Service Unsatisfied Component Scanner][UnsatisfiedComponentScanner:91]
    Bundle {id: 631, name: com.liferay.blogs.web, version: 2.0.0}
        Declarative Service {id: 3333, name: com.liferay.blogs.web.internal.portlet.action.EditEntryMVCRenderCommand, unsatisfied references:
            {name: ItemSelectorHelper, target: null}
        }

The message above warns that service component
`com.liferay.blogs.web.internal.portlet.action.EditEntryMVCRenderCommand` has an
unsatisfied reference to a component of type `ItemSelectorHelper`. The
referencing component's ID (SCR ID) is `3333` and the component belongs to
bundle `631`. 

Another way to detect unsatisfied component references is to invoke the Gogo
shell command `ds:unsatisfied`. 

-   `ds:unsatisfied` shows all unsatisfied services
-   `ds:unsatisfied [BUNDLE_ID]` shows the bundle's unsatisfied services

+$$$

**Note**: The Gogo shell command `ds:unsatisfied` appears in DXP Digital
Enterprise Fix 7.0 Pack 31 and Liferay CE Portal 7.0 GA5.

$$$

To view more detailed information about the component with the unsatisfied 
reference, use the command `scr:info [component ID]`. For example, the following 
command does this for the component ID `1701`: 

    g! scr:info 1701
    *** Bundle: org.foo.bar.command (507)
    Component Description:
        Name: org.foo.bar.command
        Implementation Class: org.foo.bar.command.FooBarCommand
        Default State: enabled
        Activation: delayed
        Configuration Policy: optional
        Activate Method: activate
        Deactivate Method: deactivate
        Modified Method: -
        Configuration Pid: [org.foo.bar.command]
        Services:
            org.foo.bar.command.DuckQuackCommand
        Service Scope: singleton
        Reference: Duck
            Interface Name: org.foo.bar.api.Foo
            Cardinality: 1..1
            Policy: static
            Policy option: reluctant
            Reference Scope: bundle
        Component Description Properties:
            osgi.command.function = foo
            osgi.command.scope = bar
        Component Configuration:
            ComponentId: 1701
            State: unsatisfied reference
            UnsatisfiedReference: Foo
            Target: null
            (no target services)
            Component Configuration Properties:
            component.id = 1701
            component.name = org.foo.bar.command
            osgi.command.function = foo
            osgi.command.scope = bar

In the `Component Configuration` section, `UnsatisfiedReference` lists the
unsatisfied reference's type. This bundle's component isn't working because it's
missing a `Foo` service. Now you can focus on why `Foo` is unavailable. The
solution may be as simple as starting or deploying a bundle that provides the
`Foo` service. 

## Service Builder Components [](id=service-builder-components)

@product@'s Service Builder modules are implemented using Spring. @product@ uses
[the Apache Felix Dependency Manager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager.html) 
to manage Service Builder modules' OSGi components via the 
[Portal Spring Extender](https://github.com/liferay/com-liferay-portal/tree/master/portal-spring-extender) 
module.

When developing a Liferay Service Builder application, you might  encounter a
situation where your application has an unresolved Spring-related  OSGi
component. This could occur, for example, if you update your application's
database schema but forget to trigger an upgrade (for information on creating
database upgrade processes for your @product@ applications, see the tutorial 
[Creating an Upgrade Process for Your App](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app)). 

The Liferay Foundation application suite's Unavailable Component Scanner reports
missing components in modules that use Service Builder. Here are the steps for
enabling the scanner:

1.  Create configuration file
    `com.liferay.portal.osgi.debug.spring.extender.internal.configuration.UnavailableComponentScannerConfiguration.cfg`.

2.  In the configuration file, set the time interval (in seconds) between scans:

        unavailableComponentScanningInterval=5

3.  Copy the file into folder `[LIFERAY_HOME]/osgi/configs`.

The scanner reports Spring extender dependency manager component status on the
set interval. If all components are registered, the scanner sends a confirmation
message. 

    11:10:53,817 INFO  [Spring Extender Unavailable Component Scanner][UnavailableComponentScanner:166] All Spring extender dependency manager components are registered

If a component is unavailable, it reports an error like this one:

    11:13:08,851 WARN  [Spring Extender Unavailable Component Scanner][UnavailableComponentScanner:173] Found unavailable component in bundle com.liferay.screens.service_1.0.28 [516].
    Component ComponentImpl[null com.liferay.portal.spring.extender.internal.context.ModuleApplicationContextRegistrator@1541eee] is unavailable due to missing required dependencies: ServiceDependency[interface com.liferay.blogs.service.BlogsEntryService null].

Component unavailability, such as what's reported above, can occur when
declarative services components and Service Builder components are published and
used in the same module. We recommend you publish DS components and Service
Builder components in separate modules. 

+$$$

**Note**: The Spring Extender Unavailable Component Scanner appears in DXP
Digital Enterprise Fix 7.0 Pack 24 and Liferay CE Portal 7.0 GA5.

$$$

@product@'s logs report unresolved Service Builder components too. For example,
@product@ logs an error when a Service Proxy Factory can't create a new instance
of a Service Builder based entity because a service is unresolved. 

+$$$

**Note**: The Service Proxy Factory timeout logs appear in DXP Digital
Enterprise Fix 7.0 Pack 32 and Liferay CE Portal 7.0 GA5.

$$$

The following code demonstrates using a
[`ServiceProxyFactory` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ServiceProxyFactory.html)
to create a new entity instance:

    private static volatile MessageBus _messageBus =
        ServiceProxyFactory.newServiceTrackedInstance(
            MessageBus.class, MessageBusUtil.class, "_messageBus", true);
 
This message alerts you to the unavailable service: 

    11:07:35,139 ERROR [localhost-startStop-1][ServiceProxyFactory:265] Service "com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactory" is unavailable in 60000 milliseconds while setting field "_singleDestinationMessageSenderFactory" for class "com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactoryUtil", will retry...

Based on the message above, there's no bundle providing the service
`com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactory`.

To check your Service Builder modules for unresolved Spring components, you can
use the Dependency Manager's `dm` Gogo shell command, which is explained here: 

- [Dependency Manager - Leveraging the shell](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager/tutorials/leveraging-the-shell.html)

For example, to get diagnostic information about Service Builder components, use 
the `dependencymanager:dm` command. This command lists all Service Builder
components, their required services, and whether each required service is
available. 

## Related Topics [](id=related-topics)

[Calling Non-OSGi Code that Uses OSGi Services](/develop/tutorials/-/knowledge_base/7-0/calling-non-osgi-code-that-uses-osgi-services)

[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)

[OSGi Basics For Liferay Development](/develop/tutorials/-/knowledge_base/7-0/osgi-basics-for-liferay-development)
