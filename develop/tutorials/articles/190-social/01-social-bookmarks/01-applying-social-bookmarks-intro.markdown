# Applying Social Bookmarks [](id=applying-social-bookmarks)

When social bookmarks are enabled, a row of icons for sharing on Twitter,
Facebook, and Google Plus appears below the content. @product@'s taglibs provide
you with the markup you need to add this feature to your app.

![Figure 1: Social bookmarks are enabled in the built-in Blogs portlet](../../../images/social-bookmarks-icons.png)

Follow these steps to add social bookmarks to your app:

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities).

2.  Choose a view to show the social bookmarks. You can display them in your 
    portlet's view, or if you've implemented [asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset), you 
    can display it in the full content view in the Asset Publisher portlet.

3.  In your JSP, include the Liferay-UI taglib declaration:

        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

4.  Use `ParamUtil` to get the entity's ID from the render request. Then create
    an entity object using your `-LocalServiceUtil` class. Below is an example
    configuration:

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Add the social bookmarks component using the
    [`liferay-ui:social-bookmarks` tag](@platform-ref@/7.0-latest/taglibs/util-taglib/liferay-ui/social-bookmarks.html). 
    Pass the content's URL in the `url` attribute. You can use `PortalUtil` to
    retrieve the URL. The `target` attribute refers to the HTML target. The URL and
    `title` you provide is sent to the social network and becomes part of the
    link you create there. Below is an example configuration:
    
        <liferay-ui:social-bookmarks
        	contentId="<%= String.valueOf(assetEntry.getEntryId()) %>"
        	displayStyle="menu"
        	target="_blank"
        	title="<%= String.valueOf(entry.getName()) %>"
        	url="<%= PortalUtil.getCanonicalURL((PortalUtil.getCurrentURL(request)), 
          themeDisplay, layout) %>" 
        />

    Setting `displayStyle` to `menu` hides the share buttons in a 
    clutter-free menu. This is the case in the above screenshot. 

    The `simple` `displayStyle` displays the share buttons without share stats. 

    ![Figure 2: Here are the share buttons with `displayStyle` set to `"simple"`.](../../../images/social-bookmarks-icons-simple.png)

    Setting `displayStyle` to `vertical` positions the stat flags above each
    share button. The stat flags show the number of times the asset has been
    shared on the corresponding social network. 

    ![Figure 3: Here are the share buttons with `displayStyle` set to `"vertical"`.](../../../images/social-bookmarks-icons-vertical.png)

    Setting `displayStyle` to `horizontal` positions the stat flags to the right
    side of each button.

    ![Figure 4: Here are the share buttons with `displayStyle` set to `"horizontal"`.](../../../images/social-bookmarks-icons-horizontal.png)

The social bookmarks UI component now shows in your entity's view. 

![Figure 5: The new JSP lets users share app content to social networks.](../../../images/social-guestbook-social-bookmarks.png) 

+$$$

**Note:** You can install the Social Bookmarks app from the Marketplace 
(available for [CE](https://web.liferay.com/marketplace/-/mp/application/15194315) 
and [DXP](https://web.liferay.com/marketplace/-/mp/application/15188453)) to let 
your users share your app's content across more social networks. For more 
information, see the [Integrating with Facebook, Twitter, and More](/discover/portal/-/knowledge_base/7-0/integrating-with-facebook-twitter-and-more#using-social-bookmarks)
article.  

$$$

Great! Now you know how to let users share content in your asset enabled apps. 

## Related Topics [](id=related-topics)

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)
