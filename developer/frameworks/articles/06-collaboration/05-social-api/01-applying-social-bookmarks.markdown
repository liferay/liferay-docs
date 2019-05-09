---
header-id: applying-social-bookmarks
---

# Applying Social Bookmarks

[TOC levels=1-4]

When you enable social bookmarks, icons for sharing on Twitter, Facebook, and 
LinkedIn appear below your app's content. Taglibs provide the markup you need 
to add this feature to your app. 

![Figure 1: These social bookmarks are in the inline display style.](../../../images/social-bookmarks-inline.png)

Follow these steps to add social bookmarks to your app: 

1.  Make sure your entity is 
    [asset enabled](/docs/7-2/frameworks/-/knowledge_base/f/asset-framework). 

2.  In your project's `build.gradle` file, add a dependency to the module 
    [`com.liferay.social.bookmarks.taglib`](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.bookmarks.taglib/): 

        compileOnly group: "com.liferay", name: "com.liferay.social.bookmarks.taglib", version: "1.0.0"

3.  Choose a view in which to show the social bookmarks. For example, you can 
    display them in one of your app's views. However, note that you don't need 
    to implement social bookmarks in your app's 
    [asset renderers](/docs/7-2/frameworks/-/knowledge_base/f/rendering-an-asset). 
    The Asset Publisher displays social bookmarks in asset renderers by default. 

4.  In your view's JSP, include the `liferay-social-bookmarks` taglib 
    declaration: 

        <%@ taglib uri="http://liferay.com/tld/social-bookmarks" prefix="liferay-social-bookmarks" %>

5.  Get an instance of your entity. You can do this however you wish. This 
    example uses 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) 
    to get the entity's ID from the render request, then uses the entity's 
    `-LocalServiceUtil` class to create an entity object: 

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

6.  Use the `liferay-social-bookmarks:bookmarks` tag to add the social bookmarks 
    component. See 
    [Social Bookmarks](/docs/7-2/frameworks/-/knowledge_base/f/social-api#social-bookmarks) 
    for information on this tag's attributes. Here's an example of using this 
    tag to add social bookmarks for a blog entry in the Blogs app: 

        <liferay-social-bookmarks:bookmarks
                className="<%= BlogsEntry.class.getName() %>"
                classPK="<%= entry.getEntryId() %>"
                displayStyle="inline"
                title="<%= entry.getTitle() %>"
                types="facebook,twitter"
                url="<%= PortalUtil.getCanonicalURL(bookmarkURL.toString(), themeDisplay, layout) %>"
        />

## Related Topics

[Social Bookmarks](/docs/7-2/frameworks/-/knowledge_base/f/social-api#social-bookmarks)

[Creating Social Bookmarks](/docs/7-2/frameworks/-/knowledge_base/f/creating-social-bookmarks)

[Asset Framework](/docs/7-2/frameworks/-/knowledge_base/f/asset-framework)
