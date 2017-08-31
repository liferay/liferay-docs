# Applying Social Bookmarks [](id=applying-social-bookmarks)

When you enable social bookmarks, icons for sharing on Twitter, Facebook, and 
Google Plus appear below your app's content. @product@'s taglibs provide the 
markup you need to add this feature to your app. 

![Figure 1: Social bookmarks are enabled in the built-in Blogs portlet](../../../images/social-bookmarks-icons.png)

Follow these steps to add social bookmarks to your app:

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities).

2.  Choose a view in which to show the social bookmarks. For example, you can 
    display them in one of your portlet's views, or if you've implemented 
    [asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset) 
    you can display them in the full content view in the Asset Publisher 
    portlet. 

3.  In your JSP, include the `liferay-ui `taglib declaration:

        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

4.  Use `ParamUtil` to get the entity's ID from the render request. Then use 
    your `-LocalServiceUtil` class to create an entity object:

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Use the 
    [`liferay-ui:social-bookmarks` tag](@platform-ref@/7.0-latest/taglibs/util-taglib/liferay-ui/social-bookmarks.html) 
    to add the social bookmarks component. The `target` attribute refers to the 
    HTML target. Pass the content's URL in the `url` attribute, using 
    `PortalUtil` to retrieve the URL. The URL and `title` you provide is sent to 
    the social network and becomes part of the link you create there: 

        <liferay-ui:social-bookmarks
            contentId="<%= String.valueOf(assetEntry.getEntryId()) %>"
            displayStyle="menu"
            target="_blank"
            title="<%= String.valueOf(entry.getName()) %>"
            url="<%= PortalUtil.getCanonicalURL((PortalUtil.getCurrentURL(request)), 
                themeDisplay, layout) %>" 
        />

    Pay special attention to the `displayStyle` attribute. This attribute sets 
    the social bookmarks' appearance. Setting `displayStyle` to `menu`, as in 
    this example, hides the share buttons in a clutter-free menu (see above 
    screenshot). 

    Setting `displayStyle` to `simple` displays the share buttons in a simple 
    row without share stats. 

    ![Figure 2: Here are the share buttons with `displayStyle` set to `simple`.](../../../images/social-bookmarks-icons-simple.png)

    Setting `displayStyle` to `vertical` displays the share buttons in a column, 
    with share stats above each (share stats show the number of times the asset 
    has been shared on the corresponding social network). 

    ![Figure 3: Here are the share buttons with `displayStyle` set to `vertical`.](../../../images/social-bookmarks-icons-vertical.png)

    Setting `displayStyle` to `horizontal` displays the share buttons in a row 
    with share stats to the right of each. 

    ![Figure 4: Here are the share buttons with `displayStyle` set to `horizontal`.](../../../images/social-bookmarks-icons-horizontal.png)

The social bookmarks UI component now shows in your entity's view. 

![Figure 5: The new JSP lets users share app content to social networks.](../../../images/social-guestbook-social-bookmarks.png) 

+$$$

**Note:** You can install the Social Bookmarks app from the Marketplace 
(available for [CE](https://web.liferay.com/marketplace/-/mp/application/15194315) 
and [DXP](https://web.liferay.com/marketplace/-/mp/application/15188453)) to let 
your users share your app's content across more social networks. For more 
information, see the article 
[Integrating with Facebook, Twitter, and More](/discover/portal/-/knowledge_base/7-0/integrating-with-facebook-twitter-and-more#using-social-bookmarks).  

$$$

Great! Now you know how to let users share content in your asset enabled apps. 

## Related Topics [](id=related-topics)

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)
