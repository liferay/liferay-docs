---
header-id: back-end-frameworks
---

# Back-end Frameworks

[TOC levels=1-4]

Liferay's powerful back-end frameworks provide essential services behind the
scenes. Here are some of the frameworks: 

-   [Portlet Providers](#portlet-providers)
-   [Data Scopes](#data-scopes)
-   [Message Bus](#message-bus)

You can use these frameworks to provide important functionality to your
applications. 

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

**Site:** One data set for each Site. 

**Page:** One data set for each Page on a Site. 

For example, a Site-scoped app has one set of data on one Site and a completely
different set of data for another Site. For a detailed explanation of scopes,
see the user guide article 
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

## Message Bus

If you must ever do data processing outside the scope of the web's
request/response, look no further than the Message Bus. It's conceptually
similar to Java Messaging Service (JMS) Topics, but sacrifices transactional,
reliable delivery capabilities, making it much lighter-weight. @product@ uses
Message Bus in many places: 

- Auditing
- Search engine integration
- Email subscriptions
- Monitoring
- Document Library processing
- Background tasks
- Cluster-wide request execution
- Clustered cache replication

You can use it too! Here are some of Message Bus's most important features:

-   publish/subscribe messaging 
-   request queuing and throttling
-   flow control
-   multi-thread message processing

There are also tools, such as the Java SE's JConsole, that can monitor Message 
Bus activities. 

![Figure 1: JConsole shows statistics on Message Bus messages sent, messages pending, and more.](../../images/message-bus-jconsole.png)
