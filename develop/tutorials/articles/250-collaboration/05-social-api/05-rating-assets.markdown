# Rating Assets [](id=rating-assets)

[The asset framework](/develop/tutorials/-/knowledge_base/7-1/asset-framework) 
supports a system that lets users rate content in apps. This feature appears in 
many of @product@'s built-in apps. For example, users can rate articles 
published in the Blogs app. Using taglibs, you can enable ratings for your app's 
content in only a few lines of code. This tutorial shows you how. 

![Figure 1: Ratings let users quickly provide feedback on content.](../../../images/social-ratings-thumbs.png)

Follow these steps to enable ratings in your app. Note that these steps use the 
Guestbook app as an example. As its name implies, this app lets users leave 
simple messages in a guestbook. 

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-1/asset-framework). 

2.  Choose a read-only view of the entity for which you want to enable ratings. 
    You can display ratings in one of your portlet's views, or if you've 
    [implemented asset rendering](/develop/tutorials/-/knowledge_base/7-1/rendering-an-asset) 
    you can display them in the full content view in the Asset Publisher app. 

3.  In the JSP, include the `liferay-ui` taglib declaration:

        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

4.  Use `ParamUtil` to get the entity's ID from the render request. Then create
    an entity object using the `-LocalServiceUtil` class. Here's an example that 
    does this for a guestbook entry in the example Guestbook app:

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Use the `liferay-ui:ratings` tag to add the ratings component for the 
    entity: 

        <liferay-ui:ratings className="<%=Entry.class.getName()%>"
            classPK="<%=entry.getEntryId()%>" type="stars" />

    The `type` attribute specifies the rating system to use: 

    - `like`: Likes
    - `stars`: Stars (five, by default)
    - `thumbs`: Thumbs up/down (as shown in the above screenshot)

    You can also make the rating type configurable by administrators. To do 
    this, see the tutorial 
    [Implementing Ratings Type Selection and Value Type Transformation](/develop/tutorials/-/knowledge_base/7-1/ratings). 

Great! Now you know how to let users rate content in your asset-enabled apps. 

## Related Topics [](id=related-topics)

[Asset Framework](/develop/tutorials/-/knowledge_base/7-1/asset-framework)

[Implementing Ratings Type Selection and Value Transformation](/develop/tutorials/-/knowledge_base/7-1/implementing-ratings-type-selection-and-value-transformation)
