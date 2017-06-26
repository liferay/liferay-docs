# Providing Portlets to Manage Requests [](id=providing-portlets-to-manage-requests)

In this tutorial, you'll learn how to declare an entity type and action for a
desired function, and you'll create a module that finds the correct application
(portlet) to use based on those given parameters.

## Specifying a Desired Portlet Behavior [](id=specifying-a-desired-portlet-behavior)

To find the portlet you need for your particular request, you'll use the
*Portlet Providers* framework. The first thing you'll need to do is call the
[PortletProviderUtil](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html)
class and request the framework find a portlet suitable for the current task.
You can request the portlet ID or portlet URL, depending on what you prefer.
Here's an example declaration:

    String portletId = PortletProviderUtil.getPortletId(
        "com.liferay.portlet.trash.model.TrashEntry, PortletProvider.Action.VIEW);

This declaration expects two parameters: the class name of the entity type you
want the portlet to handle and the type of action. The above code requests a
portlet ID for a portlet that can view Recycle Bin entries.

There are five different kinds of actions supported by the Portlet Providers
framework: `ADD`, `BROWSE`, `EDIT`, `PREVIEW`, and `VIEW`. Find the portlet ID
or portlet URL (depending on your needs), and specify the entity type and action
you want the portlet to handle.

+$$$

**Note:** The `getPortletURL` methods require an additional `HttpServletRequest`
or `PortletRequest` parameter, depending on which you use. Make sure to account
for this additional parameter when using the `getPortletURL` method.

$$$

You've successfully requested the portlet ID/portlet URL of a portlet that
matches your entity and action type. The portal, however, is not yet configured
to handle this request. You'll need to create a module that can find the correct 
portlet to handle the request.

1. [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#creating-a-module).

2. Create a unique package name in the module's `src` directory and create a
   new Java class in that package. To follow naming conventions, name the class
   based on the element type and action type, followed by *PortletProvider*
   (e.g., `LanguageEntryViewPortletProvider`). The class should extend the
   [`BasePortletProvider`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)
   class and implement the appropriate portlet provider interface based on the
   action type you chose your portlet to handle (e.g.,
   [ViewPortletProvider](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ViewPortletProvider.html),
   [BrowsePortletProvider](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BrowsePortletProvider.html),
   etc.).

3. Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {"model.class.name=CLASS_NAME"},
            service = INTERFACE.class
        )

    The `property` element should match the element type you specified in your
    `getPortletID/getPortletURL` declaration (e.g.,
    `com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry`). Also, your
    `service` element should match the interface you're implementing (e.g.,
    `ViewPortletProvider.class`). You can view an example of a similar
    `@Component` annotation in the
    [RolesSelectorEditPortletProvider](@app-ref@/foundation/latest/javadocs/com/liferay/roles/selector/web/internal/portlet/RolesSelectorEditPortletProvider.html)
    class:

        @Component(
            immediate = true,
            property = {"model.class.name=com.liferay.portal.kernel.model.UserGroupRole"},
            service = EditPortletProvider.class
        )

4. In some cases, a default portlet is already in place to handle the entity
   and action type requested. To override the default portlet with a custom
   portlet, you can assign your portlet a higher service ranking. You can do
   this by setting the following property in your `@Component` declaration:

        property= {"service.ranking:Integer=10"}

    Make sure to replace the integer with a number that is ranked higher than
    the portlet being used by default. 

5. Specify the methods you'd like to implement. Make sure to retrieve the
   portlet ID/portlet URL that should be provided when this service is called.

Lastly, generate the module's JAR file and deploy it to your portal instance.
Now a portlet that can handle the entity and action type you specified is used
when requesting a portlet ID/URL. You can now specify portlet usage without
hardcoding a specific portlet!

## Related Topics [](id=related-topics)

[Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/embedding-portlets-in-themes-and-layout-templates)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services-service-wrappers)
