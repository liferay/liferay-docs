# Portlet Providers [](id=portlet-providers)

If you're tired of specifying portlets to interact with the *Portlet Providers*
framework is for you. It finds portlets that let you operate on the entity types
you want. You specify the entity type and an operation genre (e.g., VIEW
entities or MANAGE entities), and the Portlet Providers framework gives you the
ID or URL of an active portlet to service your requests. What's more, you can
add preferable portlets to the mix. 

Here you'll learn how to

-   [Use `PortletProviderUtil` to request a portlet](#requesting-a-portlet-for-a-desired-behavior)
    that can perform a specific genre of operations on an entity type.  

-   [Create a `PortletProvider`](#creating-a-portlet-provider)
    to prioritize preferable portlet ahead of a default portlet. 

Start with requesting a portlet from `PortletProviderUtil`. 

## Requesting a Portlet for a Desired Behavior [](id=requesting-a-portlet-for-a-desired-behavior)

The
[`PortletProviderUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html)
class finds portlets to execute a particular actions on particular entities. You
can request the portlet ID or portlet URL, depending on what you prefer. To get the ID of a portlet that can view Recycle Bin
entries, for example, declare this:

    String portletId = PortletProviderUtil.getPortletId(
        "com.liferay.portlet.trash.model.TrashEntry", 
        PortletProvider.Action.VIEW);

The call passes in the name of the entity
`com.liferay.portlet.trash.model.TrashEntry` and the action type
`PortletProvider.Action.VIEW` to perform on it. 

+$$$

**Note:** The `getPortletURL` methods require an additional `HttpServletRequest`
or `PortletRequest` parameter, depending on which you use. Make sure to include this additional parameter.

$$$

When you call `PortletProviderUtil` methods, make sure to use the entity's fully qualified class name. For the action, specify one of the 
[`PortletProvider.Action`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.Action.html)
Enums:

- `ADD`
- `BROWSE`
- `EDIT`
- `MANAGE`
- `PREVIEW`
- `VIEW`

You've successfully requested the portlet ID/portlet URL of a portlet that
matches your entity and action type. If @product@'s runtime has a portlet
provider to handle the request, a non empty portlet ID/portlet URL is returned.
If, however, there's no portlet provider to handle your request or you want to
prioritize a different portlet over the default, you must create a portlet
provider. 

## Creating a Portlet Provider [](id=creating-a-portlet-provider)

Here's how to create a portlet provider:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module).

2.  In your module, follow the recommended naming convention as you create your 
    portlet provider class, starting its name with the element type, followed by
    the action type, followed by *PortletProvider* (e.g.,
    `LanguageEntryViewPortletProvider`). Extend the
    [`BasePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)
    class and implement a 
    [`PortletProvider`](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html) 
    subinterface (e.g.,
    [ViewPortletProvider](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ViewPortletProvider.html),
    [BrowsePortletProvider](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BrowsePortletProvider.html),
    etc.) that matches your action type.

3.  Make the class an OSGi Component by adding an annotation like this one 
    above the class declaration: 

        @Component(
            immediate = true,
            property = {"model.class.name=CLASS_NAME"},
            service = INTERFACE.class
        )

    The `immediate = true` element specifies that the component should be
    activated immediately upon installation. The property `model.class.name` is
    assigned the entity class name. Replace `CLASS_NAME` with your entity's
    fully qualified class name. Here's an example entity class name: 

        com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry

    Assign the `service` element the portlet provider subinterface you're
    implementing (e.g., `ViewPortletProvider.class`).

    Here's an example `@Component` annotation from the
    [RolesSelectorEditPortletProvider](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/roles/roles-selector-web/src/main/java/com/liferay/roles/selector/web/internal/portlet/RolesSelectorEditPortletProvider.java#L26-L30)
    class:

        @Component(
            immediate = true,
            property = {"model.class.name=com.liferay.portal.kernel.model.UserGroupRole"},
            service = EditPortletProvider.class
        )

4.  In some cases, a default portlet is already in place to handle the entity
    and action type requested. To override the default portlet with your own
    custom portlet, specify a `service.ranking:Integer` `@Component`
    property with a higher integer randing than the default portlet. 

        property= {"service.ranking:Integer=10"}

5.  Implement the provider methods you want making sure to retrieve the
    ID/URL of the portlet you're providing. 

6.  [Deploy your module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module). 

Now your portlet provider is available to return the ID/URL of the portlet you
want to handle specific entity operations. 

Now you can use portlet providers to loosely couple with portlets to operate on
entities. 

## Related Topics [](id=related-topics)

[Portlets](/develop/tutorials/-/knowledge_base/7-1/portlets)

[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-1/embedding-portlets-in-themes-and-layout-templates)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-1/customizing-liferay-services-service-wrappers)
