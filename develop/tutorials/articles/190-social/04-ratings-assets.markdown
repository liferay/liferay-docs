# Rating Assets [](id=rating-assets)

@product@'s asset framework supports a system for rating content in apps. This
feature appears in many core apps, such as the Blogs portlet. Ratings give your
users a voice, and you can benefit from their feedback.  Thanks to @product@'s
taglibs, you can enable ratings for your app in only a few lines of code.

![Figure 1: Ratings let users quickly provide feedback on content.](../../images/social-ratings-thumbs.png)

Follow these steps: 

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities).

2.  Choose a read-only view of the entity for the ratings. You can display 
    them in one of your portlet's views, or if you've implemented 
    [asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset) 
    you can display them in the full content view in the Asset Publisher 
    portlet. 

3.  In the JSP, include the `liferay-ui `taglib declaration:

        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

4.  Use `ParamUtil` to get the entity's ID from the render request. Then use 
    the `-LocalServiceUtil` class to create an entity object:

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Use the `liferay-ui:ratings` tag to add the ratings component:

        <liferay-ui:ratings className="<%=Entry.class.getName()%>"
            classPK="<%=entry.getEntryId()%>" type="stars" />

    The `type` attribute specifies the type of rating system to display: 

    - *like*: a likes rating system 
    - *stars*: a five star rating system
    - *thumbs*: a thumbs-up or thumbs-down rating system, as shown in the figure above 

    Although the ratings type is specified here, you can make the type 
    configurable for administrators by 
    [Implementing Ratings Type Selection and Value Type Transformation](/develop/tutorials/-/knowledge_base/7-0/ratings) 
    in your app. 

Great! Now you know how to let users rate content in your asset-enabled portlets. 

## Related Topics [](id=related-topics)

[Adding Comments to Your App](/develop/tutorials/-/knowledge_base/7-0/adding-comments-to-your-app)

[Applying Social Bookmarks](/develop/tutorials/-/knowledge_base/7-0/applying-social-bookmarks)

