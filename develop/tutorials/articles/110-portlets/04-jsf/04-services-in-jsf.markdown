# Services in JSF [](id=services-in-jsf)

Service Builder works the same in a JSF portlet as it would in any other
standard WAR-style MVC portlet. To learn more about how Service Builder works in
Liferay, visit the
[Business Logic and Data Access](/develop/tutorials/-/knowledge_base/7-0/business-logic-and-data-access)
tutorials. You can create a `service.xml` file and run Service Builder in your
JSF project to build services.

To call OSGi-based Service Builder services from your JSF portlet, you need a
mechanism that gives you access to the OSGi service registry, because you can't
look up services published to the OSGi runtime using Declarative Services.
Instead, you should open a [ServiceTracker](https://osgi.org/javadoc/r6/core/org/osgi/util/tracker/ServiceTracker.html)
when you want to call a service that's in the OSGi service registry.

To implement a service tracker in your JSF portlet, you can add a type-safe
wrapper class that extends `org.osgi.util.tracker.ServiceTracker`. For example,
this is done in a demo JSF portlet as follows

    public class UserLocalServiceTracker extends ServiceTracker<UserLocalService, UserLocalService> {

        public UserLocalServiceTracker(BundleContext bundleContext) {
            super(bundleContext, UserLocalService.class, null);
        }
    }

After extending the `ServiceTracker`, just call the constructor and the service
tracker is ready to use in your managed bean.

In a managed bean, whenever you need to call a service, open the service
tracker. For example, this is done in the same demo JSF portlet to open the
service tracker, using the
[@PostContruct](http://docs.oracle.com/javaee/7/api/javax/annotation/PostConstruct.html)
annotation:

    @PostConstruct
    public void postConstruct() {
        Bundle bundle = FrameworkUtil.getBundle(this.getClass());
        BundleContext bundleContext = bundle.getBundleContext();
        userLocalServiceTracker = new UserLocalServiceTracker(bundleContext);
        userLocalServiceTracker.open();
    }

Then the service can be called:

    UserLocalService userLocalService = userLocalServiceTracker.getService();
    ...

    userLocalService.updateUser(user);

When it's time for the managed bean to go out of scope, you must close the
service tracker using the
[@PreDestroy](http://docs.oracle.com/javaee/7/api/javax/annotation/PreDestroy.html)
annotation:

    @PreDestroy
    public void preDestroy() {
        userLocalServiceTracker.close();
    }

For more information on service trackers and how to use them in WAR-style
portlets, see the
[Service Trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers)
tutorial.

## Related Topics [](id=related-topics)

[Fundamentals](/develop/tutorials/-/knowledge_base/7-0/fundamentals)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)
