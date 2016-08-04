# Service Trackers [](id=service-trackers)

Someone wise once said, "With increased modularity comes increased dynamism."
Okay, nobody ever said that, but it's true. Now that Liferay is promoting more
modular plugins deployed into an OSGi runtime, you have to consider how your
own code, living in its own module, can rely on services in other modules for
functionality. You need to account for the possibility of service
implementations being swapped out or removed entirely if your module is to
survive and thrive in the environment of OSGi. It's easy for Liferay 7
developers who need to [call services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)
from their `@Component` classes. They just use another Declarative Services
annotation, `@Reference`, to get a service reference.

If you're able to use the Declarative Services component framework and leverage
the `@Component` and `@Reference` annotations, you should. If you're operating
outside the context of an OSGi module (i.e., you can't use Declarative
Services to create a Component), keep reading to learn about how to implement a
Service Tracker to look up services in the service registry. 

![Figure 1: Service implementations that are registered in the OSGi service registry can be accessed using Service Trackers.](../../../images/service-registry.png)

What scenarios might require the use of a service tracker?

-  Calling OSGi services from a [Spring MVC portlet](/develop/tutorials/-/knowledge_base/7-0/spring-mvc)
-  Calling OSGi services from a [JSF portlet](/develop/tutorials/-/knowledge_base/7-0/jsf-portlets-with-liferay-faces-intro)
-  Calling OSGi services from a [WAR-packaged portlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-plugins-to-liferay-7)
  that's been upgraded to run on Liferay 7, but not
  [fully modularized](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)
  and made into an OSGi module

+$$$

**Note:**  The static utility classes that were useful in previous versions of
Liferay (e.g., `UserLocalServiceUtil`) are there for compatibility but should
not be called, if possible.  There's no way to account for the dynamic
environment of the OSGi runtime with the static utility classes. This means you
could call a service that hasn't been deployed or started, which is sad. But be
happy, because with a Service Tracker, you can make OSGi-friendly service
calls.

$$$

Using a service tracker, you can access any service registered in the OSGi
runtime, including your own
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

After extending the `ServiceTracker`, you just need to call the constructor, and
the service tracker is ready to use in your business logic.

In your class, wherever you need to call the service of interest, open the
service tracker. An `init` method is a good place to put this initialization
code:

    @PostConstruct
    public void init() {
        someServiceTracker = new SomeServiceTracker(this);
        someServiceTracker.open();
    }

<!-- Please fix this. A @PostConstruct annotation is a Spring-specific thing.
This method also looks like it sets the ServiceTracker in a local variable
(where it would get garbage collected as soon as the init method is done)
instead of an instance variable. -Rich -->

When you want to call the service, make sure the service tracker has something
in it, and then get the service using the Service Tracker API's `getService`
method. After that, use the service to do something cool:

    if (!someServiceTracker.isEmpty()) {
        SomeService someService = someServiceTracker.getService();
        someService.doSomethingCool();
    }

Of course, where there's an `if`, there can also be an `else`, and you can do
whatever you'd like in response to an empty service tracker.

To wrap things up, make sure you close the service tracker. A `destroy` method
is an appropriate place to do this:

    @PreDestroy
    public void destroy() {
        someServiceTracker.close();
    }

<!-- Again, this is Spring-specific. If you're going to be specific, this
article should assume we're in an MVCPortlet. -Rich --> 

There's a little boilerplate code you need to produce, but now you can look up
services in the service registry, even if your plugins can't take advantage of
the Declarative Services component model. 

