# Overriding a Portal Service Using a Hook

All the functionality provided by Liferay is enclosed in a layer of services
that are accessed by the controller layer in portlets; this architecture lets 
you change how a core portlet of Liferay behaves without changing the portlet 
itself. Hook plugins are your best tool for leveraging this architecture to 
customize portal service behavior. 

This tutorial shows you how to modify a portal service using a hook. At the end, 
you'll have a hook that overrides Liferay's `UserLocalService` to print out the 
user Id.

## Implementing the Portal Service Override

When extending Liferay Portal with hooks, you should try to avoid implementing
the portal's interfaces directly. In some cases, patches are added to the
interfaces in fix packs to fix an issue (e.g., adding a new method to a
service). If you implement the API directly, a patch can break your
customization. However, if you extend the basic implementation, a patch won't
break your customization. Therefore, the best practice is to extend the Liferay
Portal's base implementations. For example, if you'd like to modify the
implementation of the `UserLocalService` interface, then extend
`UserLocalServiceWrapper`. If you'd like to modify the `SanitizerUtil` class,
then extend `BaseSanitizer`. 

---

 ![tip](../../images/tip-pen-paper.png) **Tip:** Your `portal.properties` file
 also provides options to extend portal services. For example, you can extend
 `BaseSanitizer` to use a custom sanitizer by setting the `sanitizer.impl`
 property. By setting this property to your custom sanitizer class, you're
 extending the `BaseSanitizer` already included in Liferay Portal.

---

Liferay generates dummy wrapper classes for all its services. For example,
`UserLocalServiceWrapper` is created as a wrapper for `UserLocalService`, a
service for adding, removing, and retrieving user accounts. To modify the
functionality of `UserLocalService` from your hook, create a class that extends
`UserLocalServiceWrapper`, override the methods you want to modify, and instruct
Liferay to use your service class instead of the original. 

1.  In your hook project, create the directory
    `/docroot/WEB-INF/src/com/liferay/sample/hook`. Inside that directory, 
    create a file called `MyUserLocalServiceImpl.java` with this content:

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

    ---

    ![tip](../../images/tip-pen-paper.png) **Note:** The wrapper class
    (`MyUserLocalServiceImpl` in this example) will be loaded in the hook's
    class loader, which means it will have access to any other class included in
    the same WAR file, but *not* the *internal* classes of Liferay.

    ---

2.  Edit `liferay-hook.xml`, located in the `example-hook/docroot/WEB-INF`
    directory, by adding the following after the `<hook>` or `</custom-jsp-dir>` 
    tag, depending on which you have:

        <service>
            <service-type>
            com.liferay.portal.service.UserLocalService
            </service-type>
            <service-impl>
            com.liferay.sample.hook.MyUserLocalServiceImpl
            </service-impl>
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

For a complete list of available services and their methods, check the [Liferay Portal 6.2 Javadocs](http://docs.liferay.com/portal/6.2/javadocs/)
or access the Javadocs for your version of Liferay at
[http://docs.liferay.com/portal](http://docs.liferay.com/portal) and click on
the *Javadocs* link. 

## Next Steps

Now that you know how to override a portal service using a hook, you may want to 
explore other ways in which hooks can be useful. You can learn how to perform a 
custom action by extending a `Language.properties` file with a hook at the
following tutorial:

[Perform a Custom Action](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/performing-a-custom-action-using-a-hook)


