---
header-id: using-a-service-tracker
---

# Using a Service Tracker

[TOC levels=1-4]

Your non-OSGi and non-bean classes can access any service registered in the OSGi
runtime using a Service Tracker. It lets you access any OSGi services including
your own  [Service Builder
services](/docs/7-2/appdev/-/knowledge_base/a/service-builder) and the services
published by Liferay's modules (like the popular `UserLocalService`). 

You can create a service tracker in two ways: 

1.  Create a service tracker where you need it. 

2.  Create a class that extends `org.osgi.util.tracker.ServiceTracker`. 

3.  Create a service tracker that tracks service events using a callback 
    handler. 

Both ways depend on `org.osgi.core`, whose packages @product@ exports by
default. Configure it as `compileOnly` (Gradle) or `provided`  (Maven). See the
[Third Party Packages Portal
Exports](/docs/7-1/reference/-/knowledge_base/r/third-party-packages-portal-exports)
for more information. 

| **Note:**  The static utility classes (e.g., `UserLocalServiceUtil`) that were
| useful in Liferay Portal 6.2 (and earlier) exist for compatibility but should
| not be called, if possible.  Static utility classes cannot account for the 
| OSGi runtime's dynamic environment. If you use a static class, you might
| attempt calling a stopped service or one that hasn't been deployed or started.
| This could cause unrecoverable runtime errors. Service Trackers, however, help
| you make OSGi-friendly service calls. 

## Creating a New Service Tracker Where You Need It

To create it directly, do this: 

```java
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

Bundle bundle = FrameworkUtil.getBundle(this.getClass());
BundleContext bundleContext = bundle.getBundleContext();
ServiceTracker<SomeService, SomeService> serviceTracker =
    new ServiceTracker(bundleContext, SomeService.class, null);
serviceTracker.open();
SomeService someService = serviceTracker.waitForService(500);
```

## Create a Class That Extends ServiceTracker 

A better way is to create a class that extends
`org.osgi.util.tracker.ServiceTracker`, because this simplifies your code. 

1.  Create a class like this one that extends `ServiceTracker`: 

    ```java
    public class SomeServiceTracker
        extends ServiceTracker<SomeService, SomeService> {

        public SomeServiceTracker(Object host) {
            super(
                FrameworkUtil.getBundle(host.getClass()).getBundleContext(),
                SomeService.class, null);
        }
    }
    ```

2.  Construct a new instance of your service tracker where you need it. The 
    `Object host` parameter obtains your own bundle context and must be an
    object from your own bundle in order to give accurate results. 

    ```java
    ServiceTracker<SomeService, SomeService> someServiceTracker =
        new SomeServiceTracker(this);
    ```

3.  When you want to use the service tracker, open it, typically as early as
    you can. 

    ```java
    someServiceTracker.open();
    ```

4.  Before attempting to use a service, use the Service Tracker to interrogate
    the service's state. For example, check whether the service is `null`:

    ```java
    SomeService someService = someServiceTracker.getService();

    if (someService == null) {
        _log.warn("The required service 'SomeService' is not available.");
    }
    else {
        someService.doSomethingCool();
    }
    ```

Note, service trackers have several other utility functions for
introspecting tracked services.

5.  Later when your application is being destroyed or undeployed, close the
    service tracker. 

    ```java
    someServiceTracker.close();
    ```

If you need to track multiple services or their events, implement a service tracker that uses callback handlers. 

## Creating a Service Tracker that Tracks Service Events Using a Callback Handler

If there's a strong possibility the service might not be available or if you
need to track multiple services, the Service Tracker API provides a callback
mechanism that operates on service *events*. To use this, override
`ServiceTracker`'s `addingService` and `removedService` methods. Their
`ServiceReference` parameter references an active service object. 

Here's an example `ServiceTracker` implementation from the [OSGi Alliance's OSGi
Core Release 7
specification](https://osgi.org/specification/osgi.core/7.0.0/util.tracker.html#d0e51991):

```java
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
```

When the `HttpService` is added to the OSGi registry, this `ServiceTracker`
creates a new wrapper class, `MyServlet`, which uses the newly added service.
When the service is removed from the registry, the `removedService` method
cleans up related resources. 

As an alternative to directly overloading `ServiceTracker` methods, create a
`org.osgi.util.tracker.ServiceTrackerCustomizer`: 

```java
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
```

Register the `ServiceTrackerCustomizer` by passing it as the `ServiceTracker`
constructor's third parameter.

```java
ServiceTrackerCustomizer<SomeService, MyWrapper> serviceTrackerCustomizer =
    new MyServiceTrackerCustomizer();

ServiceTracker<SomeService, MyWrapper> serviceTracker = 
    new ServiceTracker<>(
    	bundleContext, SomeService.class, serviceTrackerCustomizer);
```

Using service trackers requires producing some boilerplate code, but now you can
look up services in the service registry, even if your plugins can't take
advantage of the Declarative Services component model. 
