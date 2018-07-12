# Applying Social Bookmarks [](id=applying-social-bookmarks)

When you enable social bookmarks, icons for sharing on Twitter, Facebook, and
Google Plus appear below your application's content. Taglibs provide the markup
you need to add this feature to your app. 

![Figure 1: Social bookmarks are enabled in the built-in Blogs portlet](../../../images/social-bookmarks-inline.png)

Follow these steps to add social bookmarks to your app:

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-1/asset-framework). 

2.  In your project's `build.gradle` file, add a dependency to the module 
    [`com.liferay.social.bookmarks.taglib`](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.bookmarks.taglib/): 

        compileOnly group: "com.liferay", name: "com.liferay.social.bookmarks.taglib", version: "1.0.0"

3.  Choose a view in which to show the social bookmarks. For example, you can 
    display them in one of your portlet's views. 

    +$$$

    **Note:** You don't need to implement social bookmarks in your portlet's 
    [asset renderers](/develop/tutorials/-/knowledge_base/7-1/rendering-an-asset). 
    The Asset Publisher displays social bookmarks in asset renderers by default. 

    $$$

4.  In your view's JSP, include the `liferay-social-bookmarks` taglib 
    declaration:

        <%@ taglib uri="http://liferay.com/tld/social-bookmarks" prefix="liferay-social-bookmarks" %>

5.  Get an instance of your entity. You can do this however you wish. This 
    example uses `ParamUtil` to get the entity's ID from the render request, 
    then uses the entity's `-LocalServiceUtil` class to create an entity object: 

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

6.  Use the `liferay-social-bookmarks:bookmarks` tag to add the social bookmarks 
    component. Its attributes are described below: 

`className`: The entity's class name.

`classPK`: The Java class primary key of the entity.

`displayStyle`: The display style of the social bookmarks. Possible 
values are `inline`, which displays them in a row, and `menu`, which 
hides them in a menu. 

`title`: A title for the content being shared.

`types`: A comma-delimited list of the social media services to use 
(e.g., `facebook,twitter`). To use every social media service available 
in the portal, omit this attribute or use `<%= null %>` for its value. 

`url`: A URL to the portal content being shared. 

Here's an example of using the `liferay-social-bookmarks:bookmarks` tag to 
add social bookmarks for a blog entry in the Blogs app: 

    <liferay-social-bookmarks:bookmarks
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            displayStyle="inline"
            title="<%= entry.getTitle() %>"
            types="facebook,twitter"
            url="<%= PortalUtil.getCanonicalURL(bookmarkURL.toString(), themeDisplay, layout) %>"
    />

The `displayStyle` in this example is set to `inline`. The screenshot at the
beginning of this tutorial shows what this looks like. The first three 
social bookmarks appear in a row, and the rest appear in the *Share* 
menu. If you use `menu` instead, all the social bookmarks appear in the 
menu. 

![Figure 2: With `displayStyle` set to `menu`, the social bookmarks all appear in the *Share* menu.](../../../images/social-bookmarks-menu.png)

The `title` is retrieved by the entry's `getTitle()` method. Keep in mind 
that you should retrieve your entity's title with the appropriate method for 
that entity. 

Note that the `PortalUtil` method `getCanonicalURL` is called for the `url`. 
This method constructs an SEO-friendly URL from the page's full URL. For 
more information, see the 
[method's Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/PortalUtil.html#getCanonicalURL-java.lang.String-com.liferay.portal.kernel.theme.ThemeDisplay-com.liferay.portal.kernel.model.Layout-). 

## Related Topics [](id=related-topics)

[Asset Framework](/develop/tutorials/-/knowledge_base/7-1/asset-framework)
