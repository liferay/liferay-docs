# Reconfiguring Components to Use Your OSGi Service [](id=reconfiguring-components-to-use-your-service)

In many cases, assigning your
[custom service (service)](/develop/tutorials/-/knowledge_base/7-1/creating-a-custom-osgi-service)
a higher ranking convinces components to unbind from their current service and
bind to yours. In other cases, components keep using their current service. Why
is that? And how do you make components adopt your service?
The component's
[service reference policy option](/develop/tutorials/-/knowledge_base/7-1/examining-an-osgi-service-to-override#step-3-gather-reference-configuration-details-if-reconfiguration-is-needed)
is the key to determining the service. 

Here are the policy options:

`greedy`: The component uses the matching, highest ranking service as soon 
as it's available. 

`reluctant`: The component uses the matching, highest ranking service 
available in the following events:

-   the component is (re)activated
-   the component's existing referenced service becomes unavailable
-   the component's reference is modified so that it no longer matches the 
    existing bound service 

In short, references with greedy policy options adopt your higher ranking
service right away, while ones with reluctant policy options require particular
events. What's great is that  @product@'s Configuration Admin lets you use
configuration files (config files) or the API to swap in service reference
changes on the fly. Here you'll use a config file to reconfigure a service
reference to use your custom service immediately. 

This tutorial uses example modules `override-my-service-reference` and
`overriding-service-reference` to demonstrate reconfiguring a service reference,
binding the component to a different service. You can download the modules and
build them using Gradle (bundled with each module) or you can apply the tutorial
steps to configure your own customization. Executing `gradlew jar` in each
example module root generates the module JAR to the `build/libs` folder. 

-   `override-my-service-reference` 
    ([download](https://dev.liferay.com/documents/10184/656312/override-my-service-reference.zip)):
    This module's portlet component `OverrideMyServiceReferencePortlet`'s field
    `_someService` references a service of type `SomeService`. The reference's
    policy is static and reluctant. By default, it binds to an implementation
    called `SomeServiceImpl`. 

-   `overriding-service-reference`
    ([download](https://dev.liferay.com/documents/10184/656312/overriding-service-reference.zip)):
    Provides a custom `SomeService` implementation called `CustomServiceImpl`.
    The module's configuration file overrides
    `OverrideMyServiceReferencePortlet`'s `SomeService` reference so that it
    binds to `CustomServiceImpl`. 

You're ready to reconfigure a component's service reference to target your
custom service.

## Reconfiguring the Service Reference [](id=reconfiguring-the-service-reference)

@product@'s Configuration Admin lets you use configuration files to
swap in service references on the fly. 

1.  [Create a system configuration file](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)
    named after the referencing component. Follow the name convention 
    `[component].config`, replacing `[component]` with the
    [component name](/develop/tutorials/-/knowledge_base/7-1/examining-an-osgi-service-to-override#step-3-gather-reference-configuration-details-if-reconfiguration-is-needed). 
    The configuration file name for the example component `override.my.service.reference.portlet.OverrideMyServiceReferencePortlet`
    is: 

        override.my.service.reference.portlet.OverrideMyServiceReferencePortlet.config

2.  In the configuration file, add a reference target entry that filters on your
    custom service. Follow this format for the entry:

        [reference].target=[filter]

    Replace `[reference]` with the name of the reference you're overriding. 
    Replace `[filter]` with service properties that filter on your custom 
    service. 

    This example filters on the `component.name` service property:

        _someService.target="(component.name\=overriding.service.reference.service.CustomServiceImpl)" 

    This example filters on the `service.vendor` service property:

        _someService.target="(service.vendor\=Acme, Inc.)"

3.  Optionally, you can add a `cardinality.minimum` entry to specify the
    number of services the reference can use. Here's the format:

        [reference].cardinality.minimum=[int]

    Here's an example cardinality minimum:

        _someService.cardinality.minimum=1

4.  Deploy the configuration by copying the configuration file into the folder
    `[Liferay_Home]/osgi/configs`. 

Executing `scr:info` on your component shows that the custom service is now
bound to the reference. 

For example, executing
`scr:info override.my.service.reference.portlet.OverrideMyServiceReferencePortlet`
reports the following information:

    ...
    Component Description:
      Name: override.my.service.reference.portlet.OverrideMyServiceReferencePortlet
      ...
      Reference: _someService
        Interface Name: override.my.service.reference.service.api.SomeService
        Cardinality: 1..1
        Policy: static
        Policy option: reluctant
        Reference Scope: bundle
        ...
      Component Configuration:
        ComponentId: 2399
        State: active
        SatisfiedReference: _someService
          Target: (component.name=overriding.service.reference.CustomServiceImpl)
          Bound to:        6841
              Properties:
                _defaultService.target = (component.name=overriding.service.reference.service.CustomServiceImpl)
                component.id = 2398
                component.name = overriding.service.reference.service.CustomServiceImpl
                objectClass = [override.my.service.reference.service.api.SomeService]
                service.bundleid = 525
                service.id = 6841
                service.scope = bundle
          Component Configuration Properties:
            _someService.target = (component.name=overriding.service.reference.service.CustomServiceImpl)
            ...

The example component's `_someService` reference targets the custom service
component `overriding.service.reference.service.CustomServiceImpl`.
`CustomServiceImpl` references default service `SomeServiceImpl` to delegate
work to it. 

![Figure 1: Because the example component's service reference is overridden by the configuration file deployment, the portlet indicates it's calling the custom service.](../../../images/overriding-service-refs-result.png)

@product@ processed the configuration file and injected the service reference,
which in turn bound the custom service to the referencing component! 

## Related Topics [](id=related-topics)

[OSGi Services and Dependency Injection with Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)

[Finding Extension Points](/develop/tutorials/-/knowledge_base/7-1/finding-extension-points)

[Using Felix Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
