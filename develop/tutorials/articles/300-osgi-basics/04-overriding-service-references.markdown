# Overriding Reluctant Service References [](id=overriding-service-references)

When there's an existing service that you want to customize or implement
differently, you can override the existing one. To do this, you create and
deploy a new, higher-ranked service implementation. But how do you replace a
component's service that's bound by a static and reluctant reference?
Reactivating the component would bind it to the new service but would render the
component temporarily inactive. To replace the service and keep the
component active, you can change the component's service reference to target
your new service.

Here are the steps for overriding a component's service reference:

1.  [Find the component and service details](#find-the-component-and-service-reference)
2.  [Create a custom service](#create-a-custom-service)
3.  [Configure the component to use the custom service](#configure-the-component-to-use-the-custom-service)

Throughout the tutorial, example modules `override-my-service-reference` and
`overriding-service-reference` are used. You can download them and build them
using Gradle (bundled with each module) or you can apply the tutorial steps to
configure your own customization. Executing `gradlew jar` in each example module
root generates the module JAR to the `build/libs` folder. 

-   `override-my-service-reference` 
    ([download](https://dev.liferay.com/documents/10184/656312/override-my-service-reference.zip)):
    This module's portlet component `OverrideMyServiceReference`'s field
    `_someService` references a service of type `SomeService`. The reference's
    policy is static and reluctant. By default, it binds to an implementation
    called `SomeServiceImpl`. 

-   `overriding-service-reference`
    ([download](https://dev.liferay.com/documents/10184/656312/overriding-service-reference.zip)):
    Provides a custom `SomeService` implementation called `CustomServiceImpl`.
    The module's configuration file overrides `OverrideMyServiceReference`'s
    `SomeService` reference so that it binds to `CustomServiceImpl`. 

![Figure 1: Prior to overriding the service reference in example portlet module `override-my-service-reference`, the portlet's message indicates it's calling the default service implementation `override.my.service.reference.service.impl.SomeServiceImpl`](../../images/overriding-service-refs-default-impl.png)

The first step to overriding a service reference is finding the name of the
component, service reference, and service interface. If you already have them,
you can skip the next section. 

## Find the Component and Service Reference [](id=find-the-component-and-service-reference)

You must have the following information to create a custom service and configure
the component to use it.

-   *Component name*: Name of the component whose service to replace.
-   *Reference name*: Name of the component field that references the service.
-   *Service interface*: Fully qualified name of the referenced service
    interface.

You can [find the component](/develop/tutorials/-/knowledge_base/7-0/finding-extension-points#locate-the-related-module-and-component)
using @product@'s
[Application Manager](/discover/portal/-/knowledge_base/7-0/managing-and-configuring-apps#using-the-app-manager)
and find the service reference information using
[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 

Gogo Shell's Service Component Runtime (SCR) commands help you inspect
components. The Gogo Shell command `scr:info [componentName]` lists the
component's attributes, including the services it uses. Execute the command
using
[Liferay Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli) or in
[Gogo Shell via telnet](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 

Here's an example of executing the `scr:info` command in a Gogo Shell telnet
session:

    scr:info override.my.service.reference.OverrideMyServiceReference 
 
The resulting SCR information includes the component's service references.
 
For example, the following information describes the component's reference to
service `SomeService`:

    ...
    Component Description:
      Name: override.my.service.reference.OverrideMyServiceReference
    ...
    Reference: _someService
        Interface Name: override.my.service.reference.SomeService
        Cardinality: 1..1
        Policy: static
        Policy option: reluctant
        Reference Scope: bundle
    ...

Copy the following values from the command results. You'll use them in the
custom service and service reference configuration you create later.

-   *Component*: The component name you passed to the `scr:info` command.
-   *Reference*: The *Reference* value.
-   *Interface*: The *Interface Name* in the *Reference* section.

Note that the example result confirm's that the reference's policy and policy
option are `static` and `reluctant`, respectively. 

Here are the values for the example:

-   *Component name*:
    `override.my.service.reference.OverrideMyServiceReference`
-   *Reference name*: `_someService`
-   *Service interface*:
    `override.my.service.reference.SomeService`

The `scr:info` result's component configuration describes the service component
implementation bound to the reference.

    ...
    Component Configuration:
    ComponentId: 2399
    State: active
    SatisfiedReference: _someService
      Target: null
      Bound to:        6840
          Properties:
            component.id = 2400
            component.name = override.my.service.reference.SomeServiceImpl
            objectClass = [override.my.service.reference.SomeService]
            service.bundleid = 524
            service.id = 6840
            service.scope = bundle
    ...

The example's reference is bound to a component named
`override.my.service.reference.SomeServiceImpl`. By the end of this tutorial,
the reference will be reconfigured to bind to a custom service implementation.

+$$$

**Note**: OSGi Configuration Admin makes all Declarative Services components
configurable, even if they don't explicitly declare anything about
configuration. Each `@Reference` annotation in the source code has a name
property, either *explicitly* set in the annotation or *implicitly* derived from
the name of the member on which the annotation is used.

-   If no reference name property is used and the `@Reference` is on a field,
    then the reference name is the field name. 
-   If the reference is on a method, then heuristics derive the reference name.
    Method name prefixes such as `set`, `add`, and `put` are ignored. If a
    reference is on a method called`setSearchEngine(SearchEngine se)`, for
    example, then the reference name is `SearchEngine`. 

$$$

Once you've found the referenced service component implementation, you can
implement a replacement for it. If you've already created one, you can skip this
section. 

## Create Your Service [](id=create-a-custom-service)

It's time to create your own service implementation. Refer to the appropriate
[app, app suite](@app-ref@), and [@product@
module](@platform-ref@/7.0-latest/javadocs/modules) Javadoc for service
interface details.

[Create a module](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/starting-module-development)
and implement your service in it. Use the `@Component` annotation to make
the service a Declarative Services component. 

The example custom implementation for service `SomeService` looks like this:

    @Component(
        immediate = true,
        service = SomeService.class
    )
    public class CustomServiceImpl implements SomeService {

        @Override
        public String doSomething() {

            StringBuilder sb = new StringBuilder();
            sb.append(this.getClass().getName());
            sb.append(", which delegates to ");
            sb.append(_defaultService.doSomething());

            return sb.toString();
        }

        @Reference  (
            unbind = "-",
            target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
        )
        private SomeService _defaultService;
    }

The service component above refers the default service so that it can
delegate work to it in its `doSomething` method. The reference targets
the default service by its component name
`override.my.service.reference.service.impl.SomeServiceImpl`.

To register your service with the @product@'s OSGi runtime framework,
[deploy its module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module).
To bind the component reference to your custom service, you must create and
deploy instructions that configure the component reference to target your custom
service. 

## Configure the Component to Use Your Service [](id=configure-the-component-to-use-the-custom-service)

You're ready to change the component's service reference to target your service.
@product@'s Configuration Admin lets you use configuration files to swap in
service references on the fly. 

1.  Create a configuration file named after the referencing component. Here's 
    the example component's configuration file name: 

        override.my.service.reference.portlet.OverrideMyServiceReferencePortlet.config
 
    +$$$

    **Note:** Liferay DXP DE 7.0 Fix Pack 8 and later, and Liferay CE Portal 7.0 
    GA4 and later support the Apache Felix ConfigAdmin implementation of OSGi 
    Configuration Admin files. Felix ConfigAdmin uses the file suffix `.config` 
    and supports additional types, such as arrays and vectors. The syntax for 
    `.config` and `.cfg` files can be found 
    [here](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html). 

    $$$

2.  In the configuration file, add a reference target entry that filters on your
    custom service. Follow this format for the entry:

        [reference].target=[filter]

    Replace `[reference]` with the name of the reference you're overriding. 
    Replace `[filter]` with service properties that filter on your custom 
    service. 

    +$$$

    **Tip**: You can use a `component.name` or `objectClass` reference to filter 
    on your custom implementation. 

    $$$

    A `.config` file reference target entry for the example looks like this:

        _someService.target="(component.name\=overriding.service.reference.service.CustomServiceImpl)"

    [The `.config` file syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#configuration-files-config) 
    requires surrounding the value in double quotes and escaping the value's
    equals sign. 
    
    A `.cfg` file entry for the example looks like this:
    
        _someService.target=(component.name=overriding.service.reference.service.CustomServiceImpl)

3.  Optionally, you can add a `cardinality.minimum` entry to specify the
    number of services the reference can use. Here's the format:

        [reference].cardinality.minimum=[int]

    Here's an example cardinality minimum:

        _someService.cardinality.minimum=1

4.  Deploy the configuration by copying the configuration file into the folder
    `[Liferay_Home]/osgi/configs`. 

Executing `scr:info` on your component shows that the custom service
implementation is now bound to the reference. 

For example, executing
`scr:info override.my.service.reference.OverrideMyServiceReference`
reports the following information:

    ...
    Component Description:
      Name: override.my.service.reference.OverrideMyServiceReference
      ...
      Reference: _someService
        Interface Name: override.my.service.reference.SomeService
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
                _defaultService.target = (component.name=override.my.service.reference.SomeServiceImpl)
                component.id = 2398
                component.name = overriding.service.reference.CustomServiceImpl
                objectClass = [override.my.service.reference.SomeService]
                service.bundleid = 525
                service.id = 6841
                service.scope = bundle
          Properties:
            _someService.target = (component.name=overriding.service.reference.CustomServiceImpl)
            ...

The example component's `_someService` reference targets custom service
component `overriding.service.reference.CustomServiceImpl`. `CustomServiceImpl`
references default service `SomeServiceImpl` to delegates work to it. 

![Figure 2: Because the example component's service reference is overriden by the configuration file deployment, the portlet indicates it's calling the custom service.](../../images/overriding-service-refs-result.png)

@product@ processed the configuration file and injected the service reference,
which in turn bound the custom service to the referencing component! 

## Related Topics [](id=related-topics)

- [Finding Extension Points](/develop/tutorials/-/knowledge_base/7-0/finding-extension-points)

- [Using Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
