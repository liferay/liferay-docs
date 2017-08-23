# Applying Social Bookmarks [](id=applying-social-bookmarks)

When social bookmarks are enabled, a row of icons appears below the content that 
lets users share it on Twitter, Facebook, and Google Plus. This is a standard 
feature across the web today. You've probably seen it in some of @product@'s 
built-in apps, such as Blogs. @product@'s taglibs provide you with the markup 
you need to add this feature to your app.

![Figure 1: Social bookmarks are enabled in the built-in Blogs portlet](../../images/social-bookmarks-icons.png)

This tutorial shows you how to add social bookmarks for your application's
content. The Guestbook app from the MVC Learning Path is used as an example:
The completed Guestbook app code that uses this feature is on
[Github](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/guestbook-social-features/social-bookmarks/guestbook).

Follow these steps to add social bookmarks to your app:

1. Make sure your custom entity is [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities).

2. Choose a view to display the social bookmarks in. You can display them in 
   your portlet's view, or if you've implemented [asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset),
   you can display it in the full content view in the Asset Publisher portlet.

3.  Include the Liferay-UI taglib declaration in your JSP:

        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

4. In your entity's view, use `ParamUtil` to get the ID of the entity
   from the render request. Then you can create an entity object using your
   `-LocalServiceUtil` class. Below is an example configuration for the
   Guestbook app's `guestbook-web` module's view entry JSP file
   [`view_entry.jsp`](https://github.com/liferay/liferay-docs/develop/tutorials/code/osgi/modules/guestbook-social-features/social-bookmarks/guestbook/guestbook-web/src/main/resources/META-INF/resources/html/guestbookmvcportlet/view_entry.jsp):

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5. Add the implementation of the social bookmark component itself with the
   [`liferay-ui:social-bookmarks` tag](@platform-ref@/7.0-latest/taglibs/util-taglib/liferay-ui/social-bookmarks.html). 
   Pass the content's URL in the `url` attribute. You can use `PortalUtil` to 
   retrieve this. The `target` attribute refers to the HTML target. The `title` 
   you provide is sent to the social network and becomes part of the link you 
   create there, as does the URL. Below is an example configuration:
    
        <liferay-ui:social-bookmarks
        	contentId="<%= String.valueOf(assetEntry.getEntryId()) %>"
        	displayStyle="menu"
        	target="_blank"
        	title="<%= String.valueOf(entry.getName()) %>"
        	url="<%= PortalUtil.getCanonicalURL((PortalUtil.getCurrentURL(request)), 
          themeDisplay, layout) %>" 
        />

    When `displayStyle` is set to `menu`, the share buttons are hidden in a 
    clutter-free menu. This is the case in the above screenshot. 

    A `simple` `displayStyle` displays the share buttons with no share stats. 

    ![Figure 2: Here are the share buttons with `displayStyle` set to `"simple"`.](../../images/social-bookmarks-icons-simple.png)

    Setting `displayStyle` to `vertical` positions the flags above each share button. 

    ![Figure 3: Here are the share buttons with `displayStyle` set to `"vertical"`.](../../images/social-bookmarks-icons-vertical.png)

    When `displayStyle` is set to `horizontal`, the flags indicating the number of 
    shares for each button are positioned to the right.

    ![Figure 4: Here are the share buttons with `displayStyle` set to `"horizontal"`.](../../images/social-bookmarks-icons-horizontal.png)

If you haven't already connected your portlet's view to the JSP for your entity,
you can refer [here](/develop/tutorials/-/knowledge_base/7-0/relating-assets#creating-a-url-to-your-new-jsp)
to see how to connect your portlet's main view JSP to your entity's view JSP.

The social bookmarks UI component now shows in your entity's view. 

![Figure 5: The new JSP lets users share content in your portlet.](../../images/social-guestbook-social-bookmarks.png)

Great! Now you know how to let users share content in your asset enabled apps. 

You can install the Social Bookmarks app from the Marketplace (available for 
[CE](https://web.liferay.com/marketplace/-/mp/application/15194315) and 
[EE](https://web.liferay.com/marketplace/-/mp/application/15188453)) to let your 
users share your app's content across more social networks. For more information 
see the [Integrating with Facebook, Twitter, and More](/discover/portal/-/knowledge_base/7-0/integrating-with-facebook-twitter-and-more#using-social-bookmarks)
User Guide. 

You can also perform permissions checks to control who can share content. For 
example, you can surround the social bookmarks with `c:if` tags that only reveal 
their contents to users that are signed in to the portal: 

    <c:if test="<%=themeDisplay.isSignedIn()%>">

        [Social Bookmarks Here]

    </c:if>

## Related Topics [](id=related-topics)

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)
