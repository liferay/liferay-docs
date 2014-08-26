# Overriding a Portal Service Using a Hook

All the functionality provided by Liferay is enclosed in a layer of services
that are accessed by the controller layer in its portlets; this architecture lets 
you change how a Liferay core portlet behaves without changing the portlet 
itself. Hook plugins are the best tool for leveraging this architecture to 
customize portal service behavior. 

This tutorial shows you how to modify a portal service using a hook. By the end
of this tutorial, you'll have a hook plugin that overrides a Liferay service. 

## Implementing the Portal Service Override

When extending Liferay Portal with hooks, you should try to avoid implementing
the portal's interfaces directly. In some cases, patches are added to the
interfaces in fix packs to fix an issue (e.g., adding a new method to a
service). If you implement the API directly, a patch can break your
customization. If you, instead, extend the base implementation of a portal interface, a patch to the interface won't
break your customization. The best practice to override a portal service, therefore, is to extend the Liferay
Portal base implementation of that server. For example, if you'd like to modify the
implementation of the `UserLocalService` interface, then extend
`UserLocalServiceWrapper`. If you'd like to modify the `SanitizerUtil` class,
then extend `BaseSanitizer`. 

---

 ![tip](../../images/tip-pen-paper.png) **Tip:** Your `portal.properties` file
 also provides options to extend portal services. For example, you can extend
 `BaseSanitizer` to use a custom sanitizer, by setting the `sanitizer.impl`
 property. By setting this property to your custom sanitizer class, you're
 extending Liferay Portal's `BaseSanitizer`.

---

Liferay generates dummy wrapper classes for all its service interfaces. For example,
`UserLocalServiceWrapper` is created as a wrapper for `UserLocalService`, a
service interface for adding, removing, and retrieving user accounts. To modify the
functionality of `UserLocalService` from a hook, you'd create a class that extends
`UserLocalServiceWrapper`, override the methods you want to modify, and instruct
Liferay to use your service class instead of the original. You can follow the
steps described below to override any Liferay service from your own hook plugin.

1.  Create a Liferay Hook plugin project in a
    [Liferay Plugins SDK project](/develop/tutorials/-/knowledge_base/creating-a-hook-project-in-the-plugins-sdk)
    or
    [Maven project](/develop/tutorials/-/knowledge_base/developing-liferay-hook-plugins-with-maven). 

2.  Create a class that extends the wrapper class of the service interface you
    want to override. 

    To create the extension class from Liferay IDE/Developer Studio, open your
    project's `liferay-hook.xml` file, which is found in the `docroot/WEB-INF/`
    folder. Select the *Overview* mode tab for the `liferay-hook.xml` file
    editor and select  *Service Wrappers* from the editor's outline. Select *Add
    a Service Wrapper* in the editor's main area to bring up the Service Wrapper
    Detail options. 

    ![Figure 1: Liferay IDE's Hook Configuration editor comes with custom service wrapper creation and editing capabilities.](../../images/hook-service-wrappers.png)

    In the Service Wrapper Detail screen, click the icon at the right of the
    Service Type text field and browse and select the service class you want to
    override. Then, in the Service Impl text field you can enter the fully
    qualified class name of your service wrapper extension class and click the
    Create icon to the right of the text field. This creates the extension class
    you entered and brings it up for editing in Liferay IDE. 

    ![Figure 2: Creating wrapper extensions is easy. You enter the name of your service implentation class and click the *Create* icon to create it to override the service type.](../../images/hook-create-service-wrapper-impl.png)

    You can alternatively create your wrapper extension class manually in your
    favorite editor. 

    The initial wrapper extension class that Liferay IDE creates, is virtually a
    blank canvas on which you can add your custom override methods. A class
    named `MyUserLocalServiceImpl.java`, for example, created in this  manner in
    IDE to override the `UserLocalService` interface, would initially look like
    this: 

        package com.liferay.sample.hook;

        import com.liferay.portal.service.UserLocalService;
        import com.liferay.portal.service.UserLocalServiceWrapper;

        public class MyUserLocalServiceImpl extends UserLocalServiceWrapper {

            public MyUserLocalServiceImpl(UserLocalService userLocalService) {
                super(userLocalService);
                // TODO Auto-generated constructor stub
            }
        }

    Now that you've created your wrapper extension class, you can add methods to
    it to override Liferay's implementation of the service interface.  

    ---

    ![tip](../../images/tip-pen-paper.png) **Note:** On deployment, the wrapper
    class extension is loaded in the hook's 
    class loader, which means the extension can access any other class included in
    the same WAR file, but it *cannot* access Liferay's *internal* classes.

    ---

2.  You must specify your custom service implementation in your
    `liferay-hook.xml` file. On creating wrapper extension classes using
    Liferay IDE's Hook Configuration editor, Liferay IDE automatically specifies
    the service implementation in the `liferay-hook.xml` file. If you create
    your wrapper extension class manually, you must manually specify the service
    implementation in a `<service></service>` element within the `<hook></hook>`
    element. See the file's
    [DTD](http://www.liferay.com/dtd/liferay-hook_6_2_0.dtd) for details.  

    For example, here's what a wrapper extension to `UserLocalService` can look
    like: 

		<hook>
			<service>
				<service-type>com.liferay.portal.service.UserLocalService</service-type>
				<service-impl>com.liferay.sample.hook.MyUserLocalServiceImpl</service-impl>
			</service>
		</hook>

3.  [Deploy](/develop/tutorials/-/knowledge_base/deploying-plugins) your hook to
    your portal. 

Your hook substitutes the service's default behavior with the behavior of your
custom implementation. 

There are other Liferay services that you may need to extend to meet advanced
requirements. Here are just a few services that you may want to customize:

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

## Related Topics

<!-- Uncomment when the tutorial is available. Jim
[Override language.properties using a Hook](/develop/tutorials/-/knowledge_base/overriding-language-props-hook)
-->

[Developing Plugins with the Plugins SDK](/develop/tutorials/-/knowledge_base/plugins-sdk)

[Developing Liferay Hook Plugins with Maven](/develop/tutorials/-/knowledge_base/developing-liferay-hook-plugins-with-maven)

[Application Display Templates](/develop/tutorials/-/knowledge_base/application-display-templates)

