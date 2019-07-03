---
header-id: creating-a-custom-osgi-service
---

# Creating a Custom OSGi Service

[TOC levels=1-4]

It's time to implement your OSGi service. Make sure to [examine the service and 
service reference details](/docs/7-2/customization/-/knowledge_base/c/examining-an-osgi-service-to-override), 
if you haven't done so already. Here you'll create a custom service that 
implements the service interface, declares it an OSGi service of that type, and 
makes it the best match for binding with other components. 

The example custom service `CustomServiceImpl` implements service interface
(from sample module
[`overriding-service-reference`](https://portal.liferay.dev/documents/113763090/114000186/overriding-service-reference.zip))
`SomeService`, declares itself an OSGi service of the `SomeService` service
type, and even delegates work to the existing service. Examine the example code
below as you follow the steps for creating your custom service:

```java
@Component(
    property = {
        "service.ranking:Integer=100"
    },
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
        target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
    )
    private SomeService _defaultService;
}
```

Here are the steps to create a custom OSGi service:

1.  [Create a module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project). 

2.  Create your custom service class so that it `implements` the 
    [service interface](/docs/7-2/customization/-/knowledge_base/c/examining-an-osgi-service-to-override#step-1-copy-the-service-interface-name)
    you want. In the example above, `CustomServiceImpl implements SomeService`.
    Step 5 (later) demonstrates implementing the interface methods. 

3.  Make your class a Declarative Services component that is the best match for 
    references to the service interface:

    - Use an `@Component` annotation and `service` attribute to make your 
      classes a Declarative Services (DS) component. This declares your class 
      to be an OSGi service that can be made available in the OSGi service 
      registry. The example class above is a DS service component of service 
      type `SomeService.class`. 

    - Use a `service.ranking:Integer` component property to rank your service 
      higher than existing services. The `"service.ranking:Integer=100"` 
      property above sets the example's ranking to `100`. 

4.  If you want to invoke the existing service implementation, declare a field 
    that uses a Declarative Services reference to the existing service. Use the 
    [`component.name` you copied when you examined the service](/docs/7-2/customization/-/knowledge_base/c/examining-an-osgi-service-to-override#step-2-copy-the-existing-service-name) 
    to target the existing service. The example above refers to an existing 
    service like this:

    ```java
    @Reference  (
        target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
    )
    private SomeService _defaultService;
    ```

    The field lets you invoke the existing service in your custom service. 

5.  Override the interface's methods. Optionally, delegate work to the existing 
    service implementation (see previous step). 

    The example custom service's `doSomething` method delegates work to the 
    original service implementation. 

6.  Register your custom service with the OSGi runtime framework by 
    [deploying your module](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project).

Components that reference the service type you implemented and whose reference 
policy option is `greedy` bind to your custom service immediately. Components 
bound to an existing service and whose reference policy option is `reluctant` 
can be dynamically reconfigured to use your service. That's demonstrated next. 

## Related Topics

[OSGi Services and Dependency Injection with Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
