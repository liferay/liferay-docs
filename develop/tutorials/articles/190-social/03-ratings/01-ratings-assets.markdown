# Rating Assets [](id=rating-assets)

@Product@'s asset framework supports a ratings system that lets users rate 
content in apps. This feature is standard across several of @product@ core apps, 
such as the Blogs portlet. Ratings are a powerful way to give your users a voice, 
and help foster a sense of community. Thanks to @product@'s taglibs, you can 
enable ratings for your app in just a few lines of code.

![Figure 1: Ratings let users quickly provide feedback on content.](../../../images/social-ratings-thumbs.png)

Follow these steps to provide ratings in your app:

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities).

2.  Choose a view in which to show the ratings. For example, you can display 
    them in one of your portlet's views, or if you've implemented 
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

5.  Use the `liferay-ui:ratings` tag to add the ratings component:

        <liferay-ui:ratings className="<%=Entry.class.getName()%>"
            classPK="<%=entry.getEntryId()%>" type="stars" />

    The `type` attribute specifies the type of rating system to display: 
    *like* (a likes rating system), *stars* (a five star rating system), or 
    *thumbs* 
    (a thumbs-up or thumbs-down rating system, as shown in the figure above). 
    Although the ratings type is specified here, you can make the type 
    configurable for administrators by 
    [Implementing Ratings Type Selection and Value Type Transformation](/develop/tutorials/-/knowledge_base/7-0/ratings) 
    in your app. 

Great! Now you know how to let users rate content in your asset-enabled portlets. 

## Related Topics [](id=related-topics)

[Enabling Comments on Your App's Contents](/develop/tutorials/-/knowledge_base/7-0/enabling-comments-on-your-apps-contents)

[Applying Social Bookmarks](/develop/tutorials/-/knowledge_base/7-0/applying-social-bookmarks)

