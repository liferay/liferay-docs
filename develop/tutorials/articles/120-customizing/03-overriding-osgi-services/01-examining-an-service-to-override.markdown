# Examining an OSGi Service to Override [](id=examining-an-osgi-service-to-override)

Creating and injecting a custom service in place of an existing service requires
understanding the service interface, the existing service, and the references to
the service. Your custom service must implement the service interface, match
references you want, and might need to invoke the existing service. Lastly,
getting components to adopt your custom service immediately can require
reconfiguring their references to the service. Here you'll flesh out service
details to make these decisions. 

Since component service references are extension points, start with following
the tutorial
[Finding Extension Points](/develop/tutorials/-/knowledge_base/7-1/finding-extension-points)
to determine the service you want to override and components that use that
service. 

Once know the service and components that use it, use Gogo Shell's Service
Component Runtime (SCR) to inspect the components and get the service and
reference details. The
[Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell) 
command `scr:info [componentName]` lists the component's attributes and service
references.  

Here's an example `scr:info` command and results (abbreviated with `...`) that
describe component `override.my.service.reference.OverrideMyServiceReference`
(from sample module 
[override-my-service-reference](https://dev.liferay.com/documents/10184/656312/override-my-service-reference.zip))
and its reference to a service of type
`override.my.service.reference.service.api.SomeService`:

    > scr:info override.my.service.reference.OverrideMyServiceReference 

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
      Target: null
      Bound to:        6840
          Properties:
            component.id = 2400
            component.name = override.my.service.reference.service.impl.SomeServiceImpl
            objectClass = [override.my.service.reference.service.api.SomeService]
            service.bundleid = 524
            service.id = 6840
            service.scope = bundle
    ...
    
The `scr:info` results, like the ones above, contain information relevant to
injecting a custom service. Here's what you'll do with the information: 

1.  [Copy the service interface name](#step-1-copy-the-service-interface-name) 

2.  [Copy the existing service name](#step-2-copy-the-existing-service-name) 

3.  [Gather reference configuration details (if reconfiguration is necessary)](#step-3-gather-reference-configuration-details-if-reconfiguration-is-needed)

Start with the service interface. 

## Step 1: Copy the Service Interface Name [](id=step-1-copy-the-service-interface-name)

The reference's *Interface Name* is the service interface's fully qualified
name.

    ...
    Reference: _someService
        Interface Name: override.my.service.reference.service.api.SomeService
        ...

**Copy and save the interface name**, as the type your custom service must
implement.

+$$$

Javadocs for @product@ service interfaces are found at these locations:

- [@product@ core Javadocs](@platform-ref@/7.1-latest/javadocs/)
- [@product@ app Javadocs](@app-ref@)
- [MVNRepository](https://mvnrepository.com/)
  and
  [Maven Central](https://search.maven.org/)
  (for Liferay and non-Liferay artifact Javadocs). 

$$$

## Step 2: Copy the Existing Service Name [](id=step-2-copy-the-existing-service-name)

If you want to invoke the existing service along with your custom service, get the existing service name. 

The `src:info` result's Component Configuration section lists the existing
service's fully qualified name. For example, the
`OverrideMyServiceReferencePortlet` component's references `_someService` is
bound to a service component whose fully qualified name is
`override.my.service.reference.service.impl.SomeServiceImpl`. 

    Component Configuration:
    ...
    SatisfiedReference: _someService
      ...
      Bound to:        6840
          Properties:
            ...
            component.name = override.my.service.reference.service.impl.SomeServiceImpl

**Copy the `component.name`** so you can reference the service in your 
[custom service](TODO).

Here's an example of referencing the service above. 

    @Reference  (
        target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
    )
    private SomeService _defaultService;

## Step 3: Gather Reference Configuration Details (if reconfiguration is needed) [](id=step-3-gather-reference-configuration-details-if-reconfiguration-is-needed)

The service reference's policy and policy option determine a component's conditions for adopting a particular service.

- If the reference's policy option is `greedy`, it binds to the matching, 
highest ranking service right away. The reference need not be reconfigured to
adopt your service. 

- If policy is `static` and its policy option is `reluctant`, however, the  
component requires one of the following conditions to switch from using the
existing service it's referencing to using the matching, highest ranking service
(i.e., you'll rank your custom service highest):

    1. The component is reactivated
    2. The component's existing referenced service is unavailable
    3. The component's reference is modified so that it does not match the existing service but matches your service

[Reconfiguring the reference](/develop/tutorials/-/knowledge_base/7-1/reconfiguring-components-to-use-your-service)
is straightforward and can be the quickest way for the component to adopt a new
service. 

**Gather these details:** 

- *Component name:* Find this at *Component Description* &rarr; *Name*. For example,

        Component Description:
            Name: override.my.service.reference.portlet.OverrideMyServiceReferencePortlet
            ...

- *Reference name:* The *Reference* value (e.g., `Reference: _someService`).

- *Cardinality:* Number of service instances the reference can bind to. 

+$$$

**Note**: OSGi Configuration Admin makes all Declarative Services components
configurable, even if they don't explicitly declare
configuration information. Each `@Reference` annotation in the source code has a name
property, either *explicitly* set in the annotation or *implicitly* derived from
the name of the member on which the annotation is used.

-   If no reference name property is used and the `@Reference` is on a field,
then the reference name is the field name. If `@Reference` is on a field
called `_someService`, for example, then the reference name is
`_someService`.
-   If the `@Reference` is on a method, then heuristics derive the reference
name. Method name suffix is used and prefixes such as `set`, `add`, and `put`
are ignored. If `@Reference` is on a method called `setSearchEngine(SearchEngine
se)`, for example, then the reference name is `SearchEngine`. 

$$$

After
[creating your custom service](/develop/tutorials/-/knowledge_base/7-1/creating-a-custom-osgi-service)
(next), you'll use the details you collected here to 
[configure the component to use your custom service](/develop/tutorials/-/knowledge_base/7-1/reconfiguring-components-to-use-your-service).

Congratulations on getting the details required for overriding the OSGi service! 

## Related Topics [](id=related-topics)

[OSGi Services and Dependency Injection with Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)

[Finding Extension Points](/develop/tutorials/-/knowledge_base/7-1/finding-extension-points)

[Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
