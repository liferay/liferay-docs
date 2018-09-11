# Portlet Providers [](id=portlet-providers)

Some apps perform the same operations on different entity types. For example,
the Asset Publisher lets users browse, add, preview, and view various entities
as assets including: documents, web content, blogs, and more. The entities vary,
while the operations and surrounding business logic stay the same. Apps such as
the Asset Publisher rely on the Portlet Providers framework to fetch portlets to
operate on the entities---the apps don't need to know any of these portlets
ahead of time. In this way, the framework lets you focus on entity operations
and frees you from concern about portlets that carry out those operations. This
tutorial shows you how to,

-   [Create and register Portlet Providers](#creating-portletproviders) 
-   [Retrieve portlets from the Portlet Providers](#retrieving-portlets-for-desired-behaviors)

## Creating PortletProviders [](id=creating-portletproviders)

[`PortletProvider`s](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html)
are Component classes associated with an entity type. They
have methods that return portlet IDs and portlet URLs. Once you've registered a
`PortletProvider`s, you can invoke the
[`PortletProviderUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html)
class to retrieve the portlet ID or portlet URL from the corresponding
`PortletProvider`. 

Examine the 
[`WikiPortletProvider`](@platform-ref@/7.1.0-ga1/modules/apps/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/portlet/WikiPortletProvider.java#L27-L44)
class:

    @Component(
    	immediate = true,
    	property = {
    		"model.class.name=com.liferay.wiki.model.WikiPage",
    		"service.ranking:Integer=100"
    	},
    	service = {EditPortletProvider.class, ViewPortletProvider.class}
    )
    public class WikiPortletProvider
    	extends BasePortletProvider
    	implements EditPortletProvider, ViewPortletProvider {

    	@Override
    	public String getPortletName() {
    		return WikiPortletKeys.WIKI;
    	}

    }

`WikiPortletProvider` extends `BasePortletProvider`, inheriting its
`getPortletURL` methods. It must however, implement `PortletProvider`'s
`getPortletName` method, which returns the portlet's name
`WikiPortletKeys.WIKI`.

+$$$

**Note:** If you're creating a `PortletProvider` for one of Liferay's portlets, 
make your `getPortletName` method return the portlet name from that portlet's
`*PortletKeys` class if it has such a class. 

$$$

`WikiPortletProvider`'s `@Component` annotation specifies these elements and
properties:

-   `immediate = true` activates the component immediately upon installation.
-   `"model.class.name=com.liferay.wiki.model.WikiPage"` specifies the entity 
    type the portlet operates on. 
-   `"service.ranking:Integer=100"` sets the component's rank to `100`, 
    prioritizing it above all `PortletProvider`s that specify the same
    `model.class.name` value but have a lower rank. 
-   `service = {EditPortletProvider.class, ViewPortletProvider.class}` reflects
    the subinterface `PortletProvider` classes this class implements.  

Here's how to create your own `PortletProvider`:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module).

2.  Create a `PortletProvider` class in your module. Use the recommended class 
    naming convention:
    
    `[Entity] + [Action] + PortletProvider`
    
    Example: 
    
    `LanguageEntryViewPortletProvider`

3.  Extend 
    [`BasePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)
    if you want to use its `getPortletURL` method implementations. 
    
4.  Implement one or more
    [`PortletProvider`](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html) 
    subinterfaces that match your action(s):

    - [`BrowsePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BrowsePortletProvider.html)
    - [`EditPortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/EditPortletProvider.html)
    - [`ManagePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ManagePortletProvider.html)
    - [`PreviewPortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PreviewPortletProvider.html)
    - [`ViewPortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ViewPortletProvider.html)

3.  Make the class an OSGi Component by adding an annotation like this one:

        @Component(
            immediate = true,
            property = {"model.class.name=CLASS_NAME"},
            service = {INTERFACE_1.class, ...}
        )

    The `immediate = true` element specifies that the component should be
    activated immediately upon installation.

    Assign the property `model.class.name` class name of the entity the portlet
    operates on by replacing `CLASS_NAME` with your entity's fully qualified
    class name. Here's an example `model.class.name` property: 

        "model.class.name=com.liferay.wiki.model.WikiPage"

    Assign the `service` element to the `PortletProvider` subinterface(s) you're
    implementing (e.g., `ViewPortletProvider.class`, `BrowsePortletProvider`).
    Replace `INTERFACE_1.class, ...` with a list of the subinterface(s) you're
    implementing. 

4.  In case you want to override an existing `PortletProvider`, outrank it with 
    your own custom `PortletProvider` by specifying a `service.ranking:Integer`
    property with a higher integer ranking. 

        property= {"service.ranking:Integer=10"}

5.  Implement the provider methods you want making sure to implement 
    [`PortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html)'s 
    `getPortletName` method. If you didn't extend
    [`BasePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html),
    implement `PortletProvider`'s `getPortletURL` methods too. 

6.  [Deploy your module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module). 

Now your `PortletProvider` is available to return the ID and URL of the portlet
that provides the desired behaviors. Using `PortletProviderUtil` to fetch the
portlet IDs and URLs is next.  

## Retrieving Portlets for Desired Behaviors [](id=retrieving-portlets-for-desired-behaviors)

The
[`PortletProviderUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html)
class facilitates fetching portlets to execute actions on entities. You can
request the ID or URL of a portlet that performs the entity action you want.  

The Portlet Provider framework's
[`PortletProvider.Action`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.Action.html)
Enums define these action types:

- `ADD`
- `BROWSE`
- `EDIT`
- `MANAGE`
- `PREVIEW`
- `VIEW`

The action type and entity type are key parameters in fetching a portlet's ID or URL. 

### Fetching a Portlet ID [](id=fetching-a-portlet-id)

The Portlet Provider framework's
[`PortletProviderUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html)
class facilitates fetching an ID of a portlet for handling an entity operation.
For example, this call gets the ID of a portlet for viewing Recycle Bin entries:

    String portletId = PortletProviderUtil.getPortletId(
        "com.liferay.portlet.trash.model.TrashEntry", 
        PortletProvider.Action.VIEW);

`PortletProvider.Action.VIEW` is the operation and
`com.liferay.portlet.trash.model.TrashEntry` is the entity type. 

Another example is how the Asset Publisher uses the Portlet Provider framework
to add a previewed asset to a page---it adds the asset to a portlet and adds
that portlet to the page. The Asset Publisher uses the
`liferay-asset:asset_display` tag library tag whose `asset_display/preview.jsp`
shows an *Add* button for adding the portlet. If the previewed asset is a blogs
entry, for example, the framework returns a blogs portlet ID or URL for adding
the portlet to the current page. Here's the relevant code from the 
[`asset_display/preview.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/asset/asset-taglib/src/main/resources/META-INF/resources/asset_display/preview.jsp#L75-L91):

    Map<String, Object> data = new HashMap<String, Object>();

    <!-- populate the data map -->

    String portletId = PortletProviderUtil.getPortletId(assetEntry.getClassName(), PortletProvider.Action.ADD);

    data.put("portlet-id", portletId);

    <!-- add more to the data map -->
    %>

    <c:if test="<%= PortletPermissionUtil.contains(permissionChecker, layout, portletId, ActionKeys.ADD_TO_PAGE) %>">
        <aui:button cssClass="add-button-preview" data="<%= data %>" value="add" />
    </c:if>

The code above invokes
`PortletProviderUtil.getPortletId(assetEntry.getClassName(),
PortletProvider.Action.ADD)` to get the ID of a portlet that adds and displays
the asset of the underlying entity class. 

The JSP stuffs the portlet ID into the `data` map.

    data.put("portlet-id", portletId);

Then it passes the `data` map to a new *Add* button, that adds the portlet to
the page. 

    <aui:button cssClass="add-button-preview" data="<%= data %>" value="add" />

Fetching a portlet URL is just as easy. 

### Fetching a Portlet URL [](id=fetching-a-portlet-url)

`PortletProviderUtil`'s
[`getPortletURL` methods](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html)
return a `javax.portlet.PortletURL` based on an `HttpServletRequest` or
`PortletRequest`. They also let you specify a `Group`. 

For example, when the Asset Publisher is configured in Manual mode, the user can
use an Asset Browser to select asset entries. The `asset-publisher-web` module's
[`configuration/asset_entries.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/asset/asset-publisher-web/src/main/resources/META-INF/resources/configuration/asset_entries.jsp#L125)
file uses `PortletProviderUtil`'s `getPortletURL` method (at the end of the code
below) to generate a corresponding Asset Browser URL.

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

Now you can unleash an arsenal of `PortletProvider`s to use in your apps!

## Related Topics [](id=related-topics)

[Portlets](/develop/tutorials/-/knowledge_base/7-1/portlets)

[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-1/embedding-portlets-in-themes-and-layout-templates)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-1/customizing-liferay-services-service-wrappers)
