# Overriding a Portal Service [](id=overriding-a-portal-service)

All the functionality provided by Liferay is enclosed in a layer of services
that are accessed by the controller layer in portlets. This is a standard
architecture, and it lets you change how a core portlet of Liferay behaves
without changing the portlet itself; you're customizing the backend services
that the portlet uses. You can leverage this architecture to customize portal
service behavior, and hook plugins are your tool for doing so. 

Liferay generates dummy wrapper classes for all its services. For example,
`UserLocalServiceWrapper` is created as a wrapper for `UserLocalService`, a
service for adding, removing, and retrieving user accounts. To modify the
functionality of `UserLocalService` from our hook, create a class that extends
`UserLocalServiceWrapper`, override the methods you want to modify, and instruct
Liferay to use your service class instead of the original. 

1.  Inside `example-hook/docroot/WEB-INF/src/com/liferay/sample/hook` create
    a new file called `MyUserLocalServiceImpl.java` with the following content:

        package com.liferay.sample.hook;
    
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.exception.SystemException;
        import com.liferay.portal.model.User;
        import com.liferay.portal.service.UserLocalService;
        import com.liferay.portal.service.UserLocalServiceWrapper;
    
        public class MyUserLocalServiceImpl extends UserLocalServiceWrapper {
    
            public MyUserLocalServiceImpl(UserLocalService userLocalService) {
                super(userLocalService);
            }
    
            public User getUserById(long userId)
                throws PortalException, SystemException {
    
                System.out.println(
                    "## MyUserLocalServiceImpl.getUserById(" + userId + ")");
    
                return super.getUserById(userId);
            }
        }

    +$$$

    **Note:** The wrapper class (`MyUserLocalServiceImpl` in this example) will
    be loaded in the hook's class loader. That means it will have access to any
    other class included in the same WAR file, but *not* the *internal* classes
    of Liferay. 

    $$$

2.  Edit `liferay-hook.xml`, located in the `example-hook/docroot/WEB-INF`
    directory, by adding the following after `</custom-jsp-dir>`:

        <service>
            <service-type>com.liferay.portal.service.UserLocalService</service-type>
            <service-impl>com.liferay.sample.hook.MyUserLocalServiceImpl</service-impl>
        </service>

Redeploy your hook and refresh your browser. In the terminal window running
Liferay, you should see *## MyUserLocalServiceImpl.getUserById(...)* messages
displayed by your hook. 

There are other Liferay services that you may need to extend to meet advanced
requirements:

- **OrganizationLocalService:** Adds, deletes and retrieves organizations. Also
  assigns users to organizations and retrieves the list of organizations of a
  given user. 
- **GroupLocalService:** Adds, deletes and retrieves sites. 
- **LayoutLocalService:** Adds, deletes, retrieves and manages pages of sites,
  organizations and users. 

For a complete list of available services and their methods check the [Liferay
Portal 6.1 Javadocs](http://docs.liferay.com/portal/6.1/javadocs/) or access the
Javadocs for your version of Liferay at
[http://docs.liferay.com/portal](http://docs.liferay.com/portal) and selecting
the *javadocs* link. 

Now that you know how to override a portal service, let's go a bit deeper and
override a `Language.properties` file.
