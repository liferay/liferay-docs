---
header-id: back-end-frameworks
---

# Back-end Frameworks

[TOC levels=1-4]

Back-end frameworks are analogous to supporting actors and actresses in show
business. They fill out the stories in films we know and love. As actors bring
richness and life to their films, Liferay's powerful back-end frameworks bring
essential services and deliver terrific performances of their own. Here are some
of the frameworks: 

-   Device Recognition
-   [Portlet Providers](#portlet-providers)
-   [Data Scopes](#data-scopes)
-   [Message Bus](/docs/7-1/frameworks/-/knowledge_base/f/message-bus)

These frameworks and more deliver smashing performances and are stars in their
own right. 

## Portlet Providers

Some apps perform the same operations on different entity types. For example, 
the Asset Publisher lets users browse, add, preview, and view various entities 
as assets including documents, web content, blogs, and more. The entities vary, 
but the operations and surrounding business logic stay the same. Apps like the 
Asset Publisher rely on the Portlet Providers framework to fetch portlets to 
operate on the entities. In this way, the framework lets you focus on entity 
operations and frees you from concern about portlets that carry out those 
operations. 

### Portlet Provider Classes

Portlet Provider classes are components that implement the 
[`PortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html) 
interface, and are associated with an entity type. Once you've registered a 
Portlet Provider, you can invoke the
[`PortletProviderUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html) 
class to retrieve the portlet ID or portlet URL from that Portlet Provider. 

As an example, examine the 
[`WikiEditPortletProvider`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/portlet/WikiEditPortletProvider.java)
class: 

```java
@Component(
    immediate = true,
    property = {
        "model.class.name=com.liferay.wiki.model.WikiPage",
        "service.ranking:Integer=100"
    },
    service = EditPortletProvider.class
)
public class WikiEditPortletProvider
    extends BasePortletProvider implements EditPortletProvider {

    @Override
    public String getPortletName() {
        return WikiPortletKeys.WIKI;
    }

}
```

`WikiEditPortletProvider` extends 
[`BasePortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html), 
inheriting its `getPortletURL` methods. `WikiEditPortletProvider` must, however, 
implement `PortletProvider`'s `getPortletName` method, which returns the 
portlet's name `WikiPortletKeys.WIKI`. 

| **Note:** If you're creating a Portlet Provider for one of Liferay's portlets, 
| your `getPortletName` method should return the portlet name from that 
| portlet's `*PortletKeys` class, if such a class exists. 

The `@Component` annotation for `WikiEditPortletProvider` specifies these 
elements and properties: 

-   `immediate = true` activates the component immediately upon installation.
-   `"model.class.name=com.liferay.wiki.model.WikiPage"` specifies the entity 
    type the portlet operates on. 
-   `"service.ranking:Integer=100"` sets the component's rank to `100`, 
    prioritizing it above all Portlet Providers that specify the same
    `model.class.name` value but have a lower rank. 
-   `service = EditPortletProvider.class` reflects the subinterface 
    `PortletProvider` class this class implements 
    ([`EditPortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/EditPortletProvider.html)). 

For step-by-step instructions on creating a Portlet Provider class, see 
[Creating Portlet Providers](/docs/7-2/frameworks/-/knowledge_base/f/creating-portlet-providers). 
For instructions on using Portlet Providers to retrieve a portlet, see 
[Retrieving Portlets](/docs/7-2/frameworks/-/knowledge_base/f/retrieving-portlets). 

## Data Scopes

Apps can restrict their data to specific *scopes*. Scopes provide a context for 
the application's data. 

**Global:** One data set throughout a portal instance. 

**Site:** Separate data sets for each Site it's added to. 

**Page:** Separate data sets for each page it's added to. 

For example, a Site-scoped app can display its data across a single Site. For a 
detailed explanation of scopes, see the user guide article 
[Widget Scope](/docs/7-2/user/-/knowledge_base/u/widget-scope). 
To give your applications scope, you must manually add support for it. For 
instructions on this, see 
[Enabling and Accessing Data Scopes](/docs/7-2/frameworks/-/knowledge_base/f/enabling-and-accessing-data-scopes). 

### Accessing the Site Scope Across Apps

There may be times when you must access a different app's Site-scoped data from 
your app that is scoped to a page or the portal. For example, web content 
articles can be created in the page, Site, or portal scope. 
[Structures and Templates](/docs/7-2/user/-/knowledge_base/u/designing-uniform-content) 
for such articles, however, exist only in the Site scope. Other techniques 
return your app's scope, which might not be the Site scope. What a pickle! Never
fear, the 
[`ThemeDisplay`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html) 
method `getSiteGroupId()` is here! This method always gets the Site scope, no 
matter your app's current scope. For an example of using this method, see 
[Enabling and Accessing Data Scopes](/docs/7-2/frameworks/-/knowledge_base/f/enabling-and-accessing-data-scopes). 
