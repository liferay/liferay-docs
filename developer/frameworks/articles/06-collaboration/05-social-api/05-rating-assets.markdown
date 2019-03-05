# Rating Assets [](id=rating-assets)
 
In only a few lines of code, you can use a taglib to enable ratings for your 
app's content. The steps here show you how. For more information on this taglib 
and ratings in general, see 
[Ratings](liferay.com). 

![Figure 1: Ratings let users quickly provide feedback on content.](../../../images/social-ratings-thumbs.png)

Follow these steps to enable ratings in your app. Note that these steps use the 
Guestbook app as an example. As its name implies, this app lets users leave 
simple messages in a guestbook. 

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-2/asset-framework). 

2.  Choose a read-only view of the entity for which you want to enable ratings. 
    You can display ratings in one of your portlet's views, or if you've 
    [implemented asset rendering](/develop/tutorials/-/knowledge_base/7-2/rendering-an-asset) 
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
    entity. This example uses the stars rating type: 

        <liferay-ui:ratings className="<%=Entry.class.getName()%>"
            classPK="<%=entry.getEntryId()%>" type="stars" />

## Related Topics [](id=related-topics)

[Ratings](liferay.com)

[Implementing Rating Type Selection](liferay.com)

[Customizing Rating Value Transformation](liferay.com)
