---
header-id: retrieving-portlets
---

# Retrieving Portlets

[TOC levels=1-4]

When a 
[Portlet Provider](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#portlet-providers) 
exists for an entity, you can use the 
[`PortletProviderUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html) 
class to retrieve the ID or URL of the portlet that performs the entity action 
you want. 

The Portlet Provider framework's
[`PortletProvider.Action`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.Action.html) 
Enum defines these action types: 

-   `ADD`
-   `BROWSE`
-   `EDIT`
-   `MANAGE`
-   `PREVIEW`
-   `VIEW`

The action type and entity type are key parameters in fetching a portlet's ID or 
URL. 

## Fetching a Portlet ID

To get the ID of the portlet that performs an action on an entity, pass that 
entity and action as arguments to the 
[`PortletProviderUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html) 
method `getPortletId`. For example, this call gets the ID of a portlet for 
viewing Recycle Bin entries: 

```java
String portletId = PortletProviderUtil.getPortletId(
    "com.liferay.portlet.trash.model.TrashEntry", 
    PortletProvider.Action.VIEW);
```

The `com.liferay.portlet.trash.model.TrashEntry` entity specifies Recycle Bin 
entries, and `PortletProvider.Action.VIEW` specifies the view action. 

How and where you use the portlet ID depends on your needs---there's no typical 
use case or set of steps to follow. One example is how the Asset Publisher uses 
the Portlet Provider framework to add a previewed asset to a page; it adds the 
asset to a portlet and adds that portlet to the page. The Asset Publisher uses 
the `liferay-asset:asset_display` tag library tag whose 
`asset_display/preview.jsp` shows an *Add* button for adding the portlet. If the 
previewed asset is a Blogs entry, for example, the framework returns a blogs 
portlet ID or URL for adding the portlet to the current page. Here's the 
relevant code from the 
[`asset_display/preview.jsp`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/asset/asset-taglib/src/main/resources/META-INF/resources/asset_display/preview.jsp#L75-L91): 

```markup
<%
Map<String, Object> data = new HashMap<String, Object>();

<!-- populate the data map -->

String portletId = PortletProviderUtil.getPortletId(assetEntry.getClassName(), PortletProvider.Action.ADD);

data.put("portlet-id", portletId);

<!-- add more to the data map -->
%>

<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, layout, portletId, ActionKeys.ADD_TO_PAGE) %>">
    <aui:button cssClass="add-button-preview" data="<%= data %>" value="add" />
</c:if>
```

This code invokes 
`PortletProviderUtil.getPortletId(assetEntry.getClassName(), PortletProvider.Action.ADD)` 
to get the ID of a portlet that adds and displays the asset of the underlying 
entity class. 

The JSP puts the portlet ID into the `data` map:

```java
data.put("portlet-id", portletId);
```

Then it passes the `data` map to a new *Add* button that adds the portlet to
the page:

```markup
<aui:button cssClass="add-button-preview" data="<%= data %>" value="add" />
```

## Fetching a Portlet URL

To get the URL of the portlet that performs an action on an entity, call one of 
[`PortletProviderUtil`'s](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html) 
`getPortletURL` methods. These methods return a `javax.portlet.PortletURL` based 
on an `HttpServletRequest` or `PortletRequest`. You can also specify a `Group`, 
the entity's class name, and the action. 

How you call these methods depends on your use case---there's no typical set of 
steps to follow. As an example, when the Asset Publisher is configured in Manual 
mode, the user can use an Asset Browser to select asset entries. The 
`asset-publisher-web` module's 
[`configuration/asset_entries.jsp`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/asset/asset-publisher-web/src/main/resources/META-INF/resources/configuration/asset_entries.jsp#L123)
file uses `PortletProviderUtil`'s `getPortletURL` method (at the end of the code 
below) to generate a corresponding Asset Browser URL: 

```java
List<AssetRendererFactory<?>> assetRendererFactories = 
    ListUtil.sort(
        AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
            company.getCompanyId()),
            new AssetRendererFactoryTypeNameComparator(locale));

for (AssetRendererFactory<?> curRendererFactory : assetRendererFactories) {
        long curGroupId = groupId;

        if (!curRendererFactory.isSelectable()) {
            continue;
        }

        PortletURL assetBrowserURL = PortletProviderUtil.getPortletURL(
            request, curRendererFactory.getClassName(),
            PortletProvider.Action.BROWSE);
```

## Related Topics

[Portlet Providers](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#portlet-providers)

[Creating Portlet Providers](/docs/7-2/frameworks/-/knowledge_base/f/creating-portlet-providers)
