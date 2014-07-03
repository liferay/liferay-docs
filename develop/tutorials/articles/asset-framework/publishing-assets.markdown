# Publishing Assets 

A huge benefit of using the asset framework is that you can leverage the Asset
Publisher portlet to publish lists of your custom asset types. You can choose
to have users specify lists dynamically (e.g., based on the asset tags or
categories) or have administrators do it statically. 

To display your assets, the Asset Publisher needs to know how to access their
metadata. You also need to provide the Asset Publisher templates for the types
of views (e.g. *full* view and *abstract* view) available to display your
assets. This tutorial describes how to provide all this to the Asset Publisher 
by extending Liferay's base implementations of `AssetRendererFactory` and 
`AssetRenderer`. Let the extensions begin! 

## `AssetRendererFactory` 

The `AssetRendererFactory` class knows how to retrieve specific assets from 
persistent storage using the `classPK`. The `classPK` is typically the asset's 
primary key, but can be anything you specified to the `updateAsset` method used 
to add or update the asset. Your factory implementation can grab the asset from 
a `groupId` (identifies a scope of data) and a `urlTitle` (a title that can be 
used in friendly URLs to refer uniquely to the asset within a given scope). 
Finally, the asset renderer factory can provide a URL for the Asset Publisher to 
use when a user wants to add a new asset of your custom type. This URL should 
point to your own portlet. There are other less important methods of the 
interface, but you can avoid implementing them by extending 
`BaseAssetRendererFactory`. `BaseAssetRendererFactory` is Liferay's 
implementation of `AssetRendererFactory`. Since this base implementation will be 
updated to accomodate future changes to the interface, extending it instead of 
implementing `AssetRendererFactory` yourself makes your code more robust. An 
example of this can be seen in the [code of Liferay's Blogs portlet on Github](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portlet/blogs/asset/BlogsEntryAssetRendererFactory.java).

## `AssetRenderer` 

The `AssetRenderer` is an interface that provides metadata information
about one specific asset. It checks whether the current user has permission
to edit or view the asset and then renders the asset for the different templates
(e.g. abstract and full content view) by forwarding to a specific JSP. As with 
`AssetRendererFactory`, it's recommended that you extend the `BaseAssetRenderer` 
class rather than directly implementing the interface. The base class provides 
helpful defaults and contains methods that get added to the interface in the 
future. An example of extending the `BaseAssetRenderer` can be seen in the 
[code of Liferay's Blogs portlet on Github](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portlet/blogs/asset/BlogsEntryAssetRenderer.java).

In the `render` method, there's a forward to a JSP in the case of the abstract
and the full content templates. The abstract isn't mandatory and if it isn't
provided, the Asset Publisher shows the title and summary from the appropriate 
methods of the renderer. The full content template should always be provided. 
Here's how it looks for blogs entries: 

    <%@ include file="/html/portlet/blogs/init.jsp" %>

    <%
    BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);
    %>

    <%= entry.getContent() %>

    <liferay-ui:custom-attributes-available
        className="<%= BlogsEntry.class.getName() %>"
    >

        <liferay-ui:custom-attribute-list
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= (entry != null) ? entry.getEntryId() : 0 %>"
            editable="<%= false %>"
            label="<%= true %>"
        />

    </liferay-ui:custom-attributes-available>

<!--
That's about it. It wasn't that hard, right? Now it's time to get really fancy.
Put on your dancing shoes! If you need to extend the capabilities of the
`AssetRendererFactory` for one of Liferay's core portlets, check out the
article [Extending an
AssetRendererFactory](http://www.liferay.com/web/juan.fernandez/blog/-/blogs/extending-an-assetrendererfactory)
by Juan Fern&agrave;ndez; he talks about doing just that. 
-->

<!-- Let's not do this. Blog entries get out of date quickly. Instead, we should
take his blog entry and massage the text to include it right here in the
documentation. -Rich -->

That's about it! It wasn't that hard, right? Now get out there and start 
enjoying the benefits of the asset framework in your custom portlets! 

## Next Steps 

[Localization](/tutorials/-/knowledge_base/localization-lp-6-2-develop-tutorial)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/customizing-liferay-portal-lp-6-2-develop-tutorial)

[Platform Frameworks](/tutorials/-/knowledge_base/platform-frameworks-lp-6-2-develop-tutorial)
