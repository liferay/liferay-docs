# Creating a Custom OSGi Service [](id=creating-a-custom-osgi-service)

It's time to implement your custom OSGi service. Make sure to
[examine the service and service reference details](/develop/tutorials/-/knowledge_base/7-1/examining-an-osgi-service-to-override),
if you haven't done so already. Here you'll create a custom service that
implement the service interface, declare it an OSGi service of that type, and
make it the best match for other components to bind to. 

The example custom service `CustomServiceImpl` (from sample module [`overriding-service-reference`](https://dev.liferay.com/documents/10184/656312/overriding-service-reference.zip))
implements service interface `SomeService`, declares itself an OSGi service of
the `SomeService` service type, and even delegates work to the existing service.
Examine this example code as you follow the steps for creating your custom
service. 

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

Here are the steps to create a custom OSGi service:

1.  [Create a module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development). 

2.  Create your custom service class so that it `implements` the
    [service interface](/develop/tutorials/-/knowledge_base/7-1/examining-an-osgi-service-to-override#step-1-copy-the-service-interface-name)
    you want. In the example above, `CustomServiceImpl implements SomeService`.
    Step 5 (later) demonstrates implementing the interface methods. 

3.  Make your class a Declarative Services component that is the best match for 
    references to the service interface:

    - Use an `@Component` annotation and `service` attribute to make your 
    classes a Declarative Services (DS) component. This declares your class to
    be an OSGi service that can be made available in the OSGi service registry.
    The example class above is a DS service component of service type
    `SomeService.class`. 

    - Use a `service.ranking:Integer` component property to rank your service 
    higher than existing services. The `"service.ranking:Integer=100"` property
    above sets the example's ranking to `100`. 

4.  If you want to invoke the existing service implementation, 
    declare a field that uses a Declarative Services reference to the existing
    service. Use the
    [`component.name` you copied when you examined the service](/develop/tutorials/-/knowledge_base/7-1/examining-an-osgi-service-to-override#step-2-copy-the-existing-service-name)
    to target the existing service. The example above refers to an existing
    service like this:

        @Reference  (
            target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
        )
        private SomeService _defaultService;

    The field lets you invoke the existing service in your custom service. 

5.  Override the interface's methods. Optionally, delegate 
    work to the existing service implementation (see previous step). 

    The example custom service's `doSomething` method delegates work to the
    original service implementation.  

6.  Register your custom service with the OSGi runtime framework by
    [deploying your module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module).

Components that reference the service type you implemented and whose reference
policy option is `greedy` bind to your custom service immediately. Components
bound to an existing service and whose reference policy option is `reluctant`
can be dynamically reconfigured to use your service. That's demonstrated next. 

## Related Topics [](id=related-topics)

[OSGi Services and Dependency Injection with Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)
