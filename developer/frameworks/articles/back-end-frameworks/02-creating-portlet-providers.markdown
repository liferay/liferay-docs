---
header-id: creating-portlet-providers
---

# Creating Portlet Providers

[TOC levels=1-4]

Follow these steps to create your own 
[Portlet Provider](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#portlet-providers): 

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  Create a `PortletProvider` class in your module. Use the recommended class 
    naming convention:

    `[Entity] + [Action] + PortletProvider`

    For example, here's a Portlet Provider class for viewing a `LanguageEntry`: 

    `LanguageEntryViewPortletProvider`

3.  Extend 
    [`BasePortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html) 
    if you want to use its `getPortletURL` method implementations. 
 
4.  Implement one or more 
    [`PortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html) 
    subinterfaces that match your action(s): 

    -   [`AddPortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/AddPortletProvider.html)
    -   [`BrowsePortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BrowsePortletProvider.html)
    -   [`EditPortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/EditPortletProvider.html)
    -   [`ManagePortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ManagePortletProvider.html)
    -   [`PreviewPortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PreviewPortletProvider.html)
    -   [`ViewPortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ViewPortletProvider.html)

3.  Make the class an OSGi Component by adding an annotation like this one: 

    ```java
    @Component(
        immediate = true,
        property = {
            "model.class.name=CLASS_NAME",
            "service.ranking:Integer=10"
        },
        service = {INTERFACE_1.class, ...}
    )
    ```

    The `immediate = true` element specifies that the component should be
    activated immediately upon installation.

    Assign to the `model.class.name` property the fully qualified class name of 
    the entity the portlet operates on. Here's an example `model.class.name` 
    property for the `WikiPage` entity: 

        "model.class.name=com.liferay.wiki.model.WikiPage"

    Assign the `service` element to the `PortletProvider` subinterface(s) you're
    implementing (e.g., `ViewPortletProvider.class`, 
    `BrowsePortletProvider.class`, etc.). This example sets `service` to 
    `EditPortletProvider.class`: 

        service = EditPortletProvider.class

4.  If you're overriding an existing Portlet Provider, rank your Portlet 
    Provider higher by specifying a `service.ranking:Integer` property with a 
    higher integer value: 

        property = {
            ...,
            "service.ranking:Integer=10"
        }

5.  Implement the provider methods you want. Be sure to implement the 
    `PortletProvider` method `getPortletName`. If you didn't extend 
    `BasePortletProvider`, implement `PortletProvider`'s `getPortletURL` methods 
    too. 

6.  [Deploy your module](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project). 

Now your Portlet Provider is available to return the ID and URL of the portlet
that provides the desired behaviors. For more information on this, see 
[Retrieving Portlets](/docs/7-2/frameworks/-/knowledge_base/f/retrieving-portlets). 

## Related Topics

[Portlet Providers](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#portlet-providers)

[Retrieving Portlets](/docs/7-2/frameworks/-/knowledge_base/f/retrieving-portlets)

