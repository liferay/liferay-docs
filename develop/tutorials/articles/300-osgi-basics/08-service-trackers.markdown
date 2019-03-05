# Service Trackers [](id=service-trackers)

Now that Liferay is promoting more modular plugins deployed into an OSGi
runtime, you have to consider how your own code, living in its own module, can
rely on services in other modules for functionality. You must account for the
possibility of service implementations being swapped out or removed entirely if
your module is to survive and thrive in an OSGi environment. It's easy for
@product-ver@ developers who need to
[call services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)
from their `@Component` classes. They just use another
[Declarative Services (DS)](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html)
annotation, `@Reference`, to get a service reference. The component activates
when the referenced service is available. 

If you're able to use DS and leverage the `@Component` and `@Reference`
annotations, you should. DS handles much of the complexity of handling service
dynamism for you transparently.

If you can't use DS to create a Component, keep reading to learn how to
implement a Service Tracker to look up services in the service registry. 

![Figure 1: Service implementations that are registered in the OSGi service registry can be accessed using Service Trackers.](../../images/service-registry.png)

+$$$

**Note:** When using Service Trackers in your WAR-style project, you must
configure the required `org.osgi.core` dependency carefully in your build file
(e.g., `build.gradle`, `pom.xml`, etc.) to avoid errors. Since it's included in
@product@ by default, it must be configured as `provided`. See the
[Third Party Packages Portal Exports](/develop/reference/-/knowledge_base/7-0/third-party-packages-portal-exports)
tutorial for more information.

$$$

What scenarios might require using a service tracker? Keep in mind we're
focusing on scenarios where DS *can't* be used. This typically involves a
non-native (to OSGi) Dependency Injection framework.

-   Calling OSGi services from a
    [Spring MVC portlet](/develop/tutorials/-/knowledge_base/7-0/spring-mvc)
-   Calling OSGi services from a
    [JSF portlet](/develop/tutorials/-/knowledge_base/7-0/jsf-portlets-with-liferay-faces)
-   Calling OSGi services from a
    [WAR-packaged portlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-plugins-to-liferay-7)
    that's been upgraded to run on @product-ver@, but not
    [fully modularized](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)
    and made into an OSGi module

+$$$

**Note:**  The static utility classes (e.g., `UserLocalServiceUtil`) that were 
useful in Liferay Portal 6.2 (and earlier) exist for compatibility but should
not be called, if possible.  Static utility classes cannot account for the OSGi
runtime's dynamic environment. Using a static class, for example, you might
attempt calling a service that has stopped or hasn't been deployed or
started--this could cause unrecoverable runtime errors. Service Tracker,
however, helps you make OSGi-friendly service calls.

$$$

Using a Service Tracker, your non-OSGi application can access any service
registered in the OSGi runtime, including your own
[Service Builder services](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
and the services published by Liferay's modules (like the popular
`UserLocalService`).

## Using Service Tracker Factory [](id=using-service-tracker-factory)

`com.liferay.osgi.util.ServiceTrackerFactory` is a convinient class for getting the service
tracker of a component. It can return a service tracker that is already opened and ready to use.

Here is a Groovy script example for retrieving the `JournalConverter`
service to parse a Web Content article (ID `91782`) into the `Fields` class, a nested map
easier to use than XPath:

    import com.liferay.journal.service.JournalArticleLocalServiceUtil
    import com.liferay.journal.model.JournalArticle
    import com.liferay.portal.kernel.xml.Document
    import com.liferay.journal.util.JournalConverter
    import com.liferay.dynamic.data.mapping.model.DDMStructure
    import com.liferay.dynamic.data.mapping.storage.Fields
    import com.liferay.osgi.util.ServiceTrackerFactory
    import org.osgi.util.tracker.ServiceTracker

    JournalArticle ja = JournalArticleLocalServiceUtil.getArticle(91782)
    DDMStructure struct = ja.getDDMStructure()
    Document doc = ja.getDocument()
    ServiceTracker jc = ServiceTrackerFactory.open(JournalConverter.class)
    Fields fields = jc.getService().getDDMFields(struct, doc)
    jc.close()

## Implementing a Service Tracker [](id=implementing-a-service-tracker)

Although you don't have the luxury of using DS to manage your service
dependencies, you can call services from the service registry with a little bit
of code.

To implement a service tracker you can do this:

    import org.osgi.framework.Bundle;
    import org.osgi.framework.FrameworkUtil;
    import org.osgi.util.tracker.ServiceTracker;

    Bundle bundle = FrameworkUtil.getBundle(this.getClass());
    BundleContext bundleContext = bundle.getBundleContext();
    ServiceTracker<SomeService, SomeService> serviceTracker =
        new ServiceTracker(bundleContext, SomeService.class, null);
    serviceTracker.open();
    SomeService someService = serviceTracker.waitForService(500);

To simplify your code, you can create a class that extends
`org.osgi.util.tracker.ServiceTracker`.

    public class SomeServiceTracker
        extends ServiceTracker<SomeService, SomeService> {

        public SomeServiceTracker(Object host) {
            super(
                FrameworkUtil.getBundle(host.getClass()).getBundleContext(),
                SomeService.class, null);
        }
    }

From the initialization part of your logic that uses the service, call your
service tracker constructor. The `Object host` parameter is used to obtain your
own bundle context and in order to give accurate results must be an object from
your own bundle.

    ServiceTracker<SomeService, SomeService> someServiceTracker =
        new SomeServiceTracker(this);

Remember to open the service tracker before using it, typically as early as you
can. 

    someServiceTracker.open();

The most basic usage of a Service Tracker is to interrogate the service's state.
In your program logic, for example, check whether the service is `null` before
using it:

    SomeService someService = someServiceTracker.getService();

    if (someService == null) {
        _log.warn("The required service 'SomeService' is not available.");
    }
    else {
        someService.doSomethingCool();
    }

Service Trackers have several other utility functions for introspecting tracked
services.

Later when your application is being destroyed or undeployed, close the service
tracker. 

    someServiceTracker.close();

## Implementing a Callback Handler for Services [](id=implementing-a-callback-handler-for-services)

If there's a strong possibility the service might not be available, or if you
need to track multiple services, the Service Tracker API provides a callback
mechanism which operates on service *events*. To use this, override
`ServiceTracker`'s `addingService` and `removedService` methods. Their
`ServiceReference` parameter references an active service object. 

Here's an example `ServiceTracker` implementation from the
[OSGi Alliance's OSGi Core Release 7 specification](https://osgi.org/specification/osgi.core/7.0.0/util.tracker.html#d0e51991):

    new ServiceTracker<HttpService, MyServlet>(context, HttpService.class, null) {

        public MyServlet addingService(ServiceReference<HttpService> reference) {
            HttpService httpService = context.getService(reference);
            MyServlet myServlet = new MyServlet(httpService);
            return myServlet;
        }

        public void removedService(
            ServiceReference<HttpService> reference, MyServlet myServlet) {
            myServlet.close();
            context.ungetService(reference);
        }
    }

When the `HttpService` is added to the OSGi registry, this `ServiceTracker`
creates a new wrapper class, `MyServlet`, which uses the newly added service.
When the service is removed from the registry, the `removedService` method
cleans up related resources. 

As an alternative to directly overloading `ServiceTracker` methods, create a
`org.osgi.util.tracker.ServiceTrackerCustomizer`: 

    class MyServiceTrackerCustomizer 
        implements ServiceTrackerCustomizer<SomeService, MyWrapper> {
        
        private final BundleContext bundleContext;
        
        MyServiceTrackerCustomizer(BundleContext bundleContext) {
            this.bundleContext = bundleContext;
        }
        
        @Override
        public MyWrapper addedService(
            ServiceReference<SomeService> serviceReference) {
            
            // Determine if the service is one that's interesting to you.
            // The return type of this method is the `tracked` type. Its type 
            // is what is returned from `getService*` methods; useful for wrapping 
            // the service with your own type (e.g., MyWrapper).
            if (isInteresting(serviceReference)) {
                MyWrapper myWrapper = new MyWrapper(
                    serviceReference, bundleContext.getService());
                
                // trigger the logic that requires the available service(s)
                triggerServiceAddedLogic(myWrapper);
                
                return myWrapper;
            }
            
            // If the return is null, the tracker is effectively ignoring any further
            // events for the service reference
            return null;
        }

        @Override
        public void modifiedService(
            ServiceReference<SomeService> serviceReference, MyWrapper myWrapper) {
            // handle the modified service
        }

        @Override
        public void removedService(
            ServiceReference<SomeService> serviceReference, MyWrapper myWrapper) {

            // finally, trigger logic when the service is going away
            triggerServiceRemovedLogic(myWrapper);
    	}

    }

Register the `ServiceTrackerCustomizer` by passing it as the `ServiceTracker`
constructor's third parameter.

    ServiceTrackerCustomizer<SomeService, MyWrapper> serviceTrackerCustomizer =
        new MyServiceTrackerCustomizer();

    ServiceTracker<SomeService, MyWrapper> serviceTracker = 
        new ServiceTracker<>(
        	bundleContext, SomeService.class, serviceTrackerCustomizer);

There's a little boilerplate code you need to produce, but now you can look up
services in the service registry, even if your plugins can't take advantage of
the Declarative Services component model. 
