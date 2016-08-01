# Calling Services from Spring MVC [](id=calling-services-from-spring-mvc)

To call OSGi-based Service Builder services from your Spring MVC portlet, you
need a mechanism that gives you access to the OSGi service registry.

+$$$

**Note:** If you don't already have one, create a service builder project using [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

    springmvc-service-builder/
        build.gradle
        springmvc-service-builder-api/
            bnd.bnd
            build.gradle
        springmvc-service-builder-service/
            bnd.bnd
            build.gradle
            service.xml

Design your model entity and write your service layer as normal (see the
tutorials on Service Builder
[here](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)). After
that, add your service's API JAR as a dependency in your Spring MVC project. 

$$$

Since you can't look up a reference to the services published to the OSGi
runtime using Declarative Services, how do you call Service Builder services?
One way is by calling the static utility methods.

    FooServiceUtil.getFoosByGroupId()

While very simple, that's not the best way to call OSGi services because of the
dynamic nature of the OSGi runtime. Service implementations could be removed and
added at any time, and your plugin needs to be able to account for that.
Additionally, you need a mechanism that lets your portlet plugin react
gracefully to the possibility of the service implementation becoming unavailable
entirely. That's why you should open a Service Tracker when you want to call a
service that's in the OSGi service registry.

## Service Trackers [](id=service-trackers)

Since you don't have the luxury of using Declarative Services to manage your
service dependencies, you have a little bit of work to do if you want to gain
some of the benefits OSGi gives you:

-  Accounting for multiple service implementations, using the best service
   implementation available (taking into account the service ranking property)

-  Accounting for no service implementations

The static utility classes don't let you do that, and that's sad. But be happy,
because with a little code, you can regain those benefits.

To implement a service tracker you can do this:

    Bundle bundle = org.osgi.framework.FrameworkUtil.getBundle(this.getClass());
    BundleContext bundleContext = bundle.getBundleContext();
    org.osgi.util.tracker.ServiceTracker<SomeService, SomeService> serviceTracker = new 
    org.osgi.util.tracker.ServiceTracker(bundleContext, SomeService.class, null);

That's too wordy. To minimize the service tracker code you need to add to your
controllers, use a type-safe wrapper class that extends
`org.osgi.util.tracker.ServiceTracker`. Your `ServiceTracker` takes two generic
type parameters: the type of service being tracked, and the type of object being
produced. In the present use case, both types are the same.

    public class SomeServiceTracker extends 
        ServiceTracker<SomeService, SomeService> {     

        public SomeServiceTracker(Object host) {
            super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), 
                SomeService.class, null);     
        } 
    }

After extending the `ServiceTracker`, you just need to call the constructor, and
the service tracker is ready to use in your controller layer.

In your controllers, wherever you need to call the service of interest, open the
service tracker. An `init` method is a good place to put this initialization
code:

    @PostConstruct
    public void init() {
        someServiceTracker = new SomeServiceTracker(this);
        someServiceTracker.open();
    }

When you want to call the service in your controller’s method, you can make sure
the service tracker has something in it, and then get the service using the
Service Tracker API’s `getService` method. After that, use the service to do
something cool:

    if (!someServiceTracker.isEmpty()) {
        SomeService someService = someServiceTracker.getService();
        someService.doSomethingCool();
    }

Of course, where there’s an `if`, there can also be an `else`, and you can do
whatever you’d like in response to an empty service tracker.

When it’s time for the controller bean to be removed, you must close the service
tracker. A `destroy` method is an appropriate place to do this:

    @PreDestroy
    public void destroy() {
        someServiceTracker.close();
    }

Now you know how to use a service tracker to look up services in the service
registry, giving you a more robust way to call OSGi services. But there's more.

## Implementing a Service Tracker Customizer [](id=implementing-a-service-tracker-customizer)

If you want to employ a callback-like approach for reacting to service changes
at the time they occur, you can implement a
`org.osgi.util.tracker.ServiceTrackerCustomizer`. To illustrate how it works,
first consider a service tracker that sends an email each time a
service happens:

	public class SomeServiceTracker extends ServiceTracker<SomeService, SomeService> {

		public SomeServiceTracker(Object host) {
			super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), SomeService.class, null);
		}

		public SomeService addingService(ServiceReference<SomeService> reference) {
			sendAddingServiceEmail(reference);
			return super.addingService(reference);
		}

		public void modifiedService(ServiceReference<SomeService> reference, SomeService service) {
			super.modifiedService(reference, service);
			sendModifiedServiceEmail(reference);
		}

		public void removedService(ServiceReference<SomeService> reference, SomeService service) {
			super.removedService(reference, service);
			sendRemovedServiceEmail(reference);
		}
	}

The code discussed earlier for creating the service tracker is the same, but now
when service events happen, you're performing some logic (like sending an email)
on top of `ServiceTracker`'s implementation of the `ServiceTrackerCustomizer`
interface. To make things a bit more object oriented, create your own
implementation of `ServiceTrackerCustomizer`. Here's what the example logic
above looks like using this approach:

    public class EmailServiceTrackerCustomizer implements ServiceTrackerCustomizer<SomeService, MailState> {

        public EmailServiceTrackerCustomizer(MailContext mc) {
            _mc = mc;
        }

        public MailState addingService(ServiceReference<SomeService> reference) {
            MailState ms = new MailState(_mc);
            ms.sendAddingServiceEmail(reference);
            return ms;
        }

        public void modifiedService(ServiceReference<SomeService> reference, MailState ms) {
            ms.sendModifiedServiceEmail(reference);
        }

        public void removedService(ServiceReference<SomeService> reference, MailState ms) {
            ms.sendRemovedServiceEmail(reference);
        }

        private MailContext _mc;
    }

A small change to your service tracker initialization code is necessary when you
implement your own service tracker customizer: 

    public class SomeServiceTracker extends ServiceTracker<SomeService, MailState> {

        public SomeServiceTracker(Object host) {
            super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), SomeService.class,
                    new EmailServiceTrackerCustomizer());
        }
    }

There's now a different second type parameter (`MailState` in this example,
which is the object being produced) in the `extends` directive. You're also
replacing the `null` parameter in the constructor with `new
EmailServiceTrackerCustomizer()`. When this is null, you're declaring that the
service tracker will call the `SerivceTrackerCustomizer` methods on itself. By
instantiating your service tracker customizer instead, your service tracker is
now relying on your service tracker customizer implementation. 

Your service tracker customizer is called when a service is being added to a
service tracker. For any service your controller layer depends on, a service
tracker customizer lets you proactively participate in the events happening on
the service. A good use case is taking your application’s own functionality out
of service gracefully when a dependency can no longer be fulfilled.

As you can see, there's some boilerplate code required in order to enjoy the
benefits of the OSGi lifecycle. If you are not required to use a Spring MVC
portlet, consider using Liferay's MVC framework to design your portlets instead.
Then you can take advantage of the Declarative Services `@Component` and
`@Reference` annotations, which let you avoid the boilerplate code associated
with service trackers.
