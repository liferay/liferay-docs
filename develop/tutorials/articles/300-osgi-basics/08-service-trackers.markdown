# Service Trackers [](id=service-trackers)

Someone wise once said, "With increased modularity comes increased dynamism."
Okay, nobody ever said that, but it's true. Now that Liferay is promoting more
modular plugins deployed into an OSGi runtime, you have to consider how your
own code, living in its own module, can rely on services in other modules for
functionality. You need to account for the possibility of service
implementations being swapped out or removed entirely if your module is to
survive and thrive in the environment of OSGi. It's easy for @product-ver@
developers who need to [call services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)
from their `@Component` classes. They just use another Declarative Services (DS)
annotation, `@Reference`, to get a service reference. The Component activates when the referenced service is available. 

If you're able to use the Declarative Services component framework and leverage
the `@Component` and `@Reference` annotations, you should. If you're operating
outside the context of an OSGi module (i.e., you can't use Declarative
Services to create a Component), keep reading to learn about how to implement a
Service Tracker to look up services in the service registry. 

![Figure 1: Service implementations that are registered in the OSGi service registry can be accessed using Service Trackers.](../../images/service-registry.png)

What scenarios might require the use of a service tracker?

-  Calling OSGi services from a [Spring MVC portlet](/develop/tutorials/-/knowledge_base/7-0/spring-mvc)
-  Calling OSGi services from a [JSF portlet](/develop/tutorials/-/knowledge_base/7-0/jsf-portlets-with-liferay-faces)
-  Calling OSGi services from a [WAR-packaged portlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-plugins-to-liferay-7)
  that's been upgraded to run on @product-ver@, but not
  [fully modularized](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)
  and made into an OSGi module

+$$$

**Note:**  The static utility classes that were useful in previous versions of
Liferay (e.g., `UserLocalServiceUtil`) exist for compatibility but should not be
called, if possible.  Static utility classes cannot account for the OSGi
runtime's dynamic environment. Using a static class, for example, you might
attempt calling a service that has stopped or hasn't been deployed or started,
which is sad. But be happy, because with a Service Tracker, you can make
OSGi-friendly service calls.

$$$

Using a Service Tracker, your non-OSGi application can access any service
registered in the OSGi runtime, including your own
[Service Builder services](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
and the services published by Liferay's modules (like the popular
`UserLocalService`).

## Implementing a Service Tracker [](id=implementing-a-service-tracker)

Although you don't have the luxury of using Declarative Services to manage your
service dependencies, you can call services from the service registry with a
little bit of code.

To implement a service tracker you can do this:

    Bundle bundle = org.osgi.framework.FrameworkUtil.getBundle(this.getClass());
    BundleContext bundleContext = bundle.getBundleContext();
    org.osgi.util.tracker.ServiceTracker<SomeService, SomeService> serviceTracker = new 
    org.osgi.util.tracker.ServiceTracker(bundleContext, SomeService.class, null);

But in most cases, that's too wordy. To minimize the service tracker code, use a
type-safe wrapper class that extends `org.osgi.util.tracker.ServiceTracker`.
Your `ServiceTracker` takes two generic type parameters: the type of service
being tracked, and the type of object being produced. In the present use case,
both types are the same.

    public class SomeServiceTracker extends 
        ServiceTracker<SomeService, SomeService> {     

        public SomeServiceTracker(Object host) {
            super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), 
                SomeService.class, null);     
        } 
    }

From your class that uses the service, call the service tracker constructor. 

    ServiceTracker<SomeService, SomeService>
        someServiceTracker = new SomeServiceTracker(this);

In your class that uses the service, wherever you need to call the service of
interest, open the service tracker. 

    someServiceTracker.open();

Open the service tracker and make sure it has something in it. Then get the
service using the Service Tracker API's `getService` method. Before using the
service, make sure it's not null.  

    if (!someServiceTracker.isEmpty()) {
        SomeService someService = someServiceTracker.getService();

        if (someService != null) {
            someService.doSomethingCool();
        }
    }

Of course, where there's an `if`, there can also be an `else`, and you can do
whatever you'd like in response to an empty service tracker or null service
reference. 

To wrap things up, close the service tracker. 

    someServiceTracker.close(); 

## Implementing a Callback Handler for Services [](id=implementing-a-callback-handler-for-services)

If there's a strong possibility the service might not be available, register a
callback for it and access it through a callback handler. To do this, create a
`ServiceTracker` extension that overrides the `addingService` method. That
method's `ServiceReference` parameter references an active service object. 

Here's an example taken from the [OSGi Alliance's OSGi Core Release 7 specification](https://osgi.org/specification/osgi.core/7.0.0/util.tracker.html#d0e51991):

    new ServiceTracker<HttpService,MyServlet>(context, HttpService.class, null) {

        public MyServlet addingService(ServiceReference<HttpService> reference) {
            HttpService svc = context.getService(reference);
            MyServlet ms = new MyServlet(scv);   return ms;
        }

        public void removedService(ServiceReference<HttpService> reference,
            MyServlet ms) {
            ms.close();
            context.ungetService(reference);
        }
    }

When the `HttpService` is added to the OSGi registry, this` ServiceTracker`
creates a new wrapper class, `MyServlet`, to handle the newly added service.
When the service is removed from the registry, the `removedService` method
cleans up related resources, including the service reference. 

As an alternative to extending `ServiceTracker` to handle newly added and
removed services, create a `org.osgi.util.tracker.ServiceTrackerCustomizer`
extension and implement its `addedService` and `removedService` methods. 

    class MyServiceTrackerCustomizer implements ServiceTrackerCustomizer<SomeService, Void> {
        @Override
        public void addedService(
            ServiceReference<SomeService> serviceReference, Void v) {
            // handle the added service
        }

        @Override
        public void modifiedService(
            ServiceReference<SomeService> serviceReference, Void v) {
            // handle the modified service
        }

        @Override
        public void removedService(
            ServiceReference<SomeService> serviceReference, Void v) {
            // handle the removed service
        }
    }

 Then register the `ServiceTrackerCustomizer` with a new `ServiceTracker`.
 
    ServiceTrackerCustomizer serviceTrackerCustomizer =
        new ServiceTrackerCustomizer();

    ServiceTracker<SomeService> serviceTracker = new ServiceTracker<SomeService>(
        bundleContext, 
        SomeService.class,
        serviceTrackerCustomizer);

There's a little boilerplate code you need to produce, but now you can look up
services in the service registry, even if your plugins can't take advantage of
the Declarative Services component model. 
