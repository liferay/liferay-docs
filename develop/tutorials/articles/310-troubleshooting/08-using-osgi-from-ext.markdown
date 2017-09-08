# Using OSGi Services from EXT Plugins [](id=using-osgi-services-from-ext-plugins)

Using OSGi services from an Ext plugin is done the same way that @product@'s 
core uses OSGi services: via the `com.liferay.registry` API provided by the 
`registry-api` bundle. All usages of this API in @product@'s core can serve as 
examples. Here's a very simple example: 

    Registry registry = RegistryUtil.getRegistry();
    UserService userService = registry.getService(UserService.class);
    If (userService != null) {
        User user = userService.getCurrentUser();
        System.out.println("Current user is " + user.getFirstName() + StringPool.BLANK + 
            user.getLastName());
    }

Remember that OSGi services can come and go at any time. @product@ services, 
including `UserService`, aren't an exception to this rule. Although it's 
unlikely that `UserService` becomes unavailable, you must still account for 
that possibility. @product@'s registry API provides `ServiceReference` and 
`ServiceTracker`, which you can use to simplify dealing with OSGi services. If 
you're familiar with OSGi development, you've heard of these classes because the
OSGi framework provides them. @product@'s versions of these classes wrap the
OSGi ones, so you can use them the same way. 

Here's a smarter version of the above example. Using service trackers takes away 
much of the pain of having to deal with services that can appear and disappear 
dynamically: 

    Registry registry = RegistryUtil.getRegistry();
    ServiceTracker<UserService, UserService> tracker = registry.trackServices(UserService.class);
    tracker.open();
    UserService userService = tracker.getService();
    if (userService != null) {
        User user = userService.getCurrentUser();
        System.out.println("Current user is " + user.getFirstName() + StringPool.SPACE + 
            user.getLastName());
    }
    tracker.close();

Remember to open your service trackers before use and close them after use. If 
you must use @product@'s OSGi services in a servlet, for example, it's a good 
idea to open your service trackers in `Servlet.init()` and close them in 
`Servlet.destroy()`. 

## Related Topics [](id=related-topics)

[Calling Non-OSGi Code that Uses OSGi Services](/develop/tutorials/-/knowledge_base/7-0/calling-non-osgi-code-that-uses-osgi-services)

[OSGi Basics For Liferay Development](/develop/tutorials/-/knowledge_base/7-0/osgi-basics-for-liferay-development)
