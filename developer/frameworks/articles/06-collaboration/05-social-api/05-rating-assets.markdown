---
header-id: rating-assets
---

# Rating Assets

In only a few lines of code, you can use a taglib to enable ratings for your 
app's content. The steps here show you how. For more information on this taglib 
and ratings in general, see 
[Ratings](/developer/frameworks/-/knowledge_base/7-2/social-api#ratings). 

![Figure 1: Users can rate content to let others know how they really feel about it.](../../../images/social-ratings-thumbs.png)

Follow these steps to enable ratings in your app. Note that these steps use a 
sample Guestbook app as an example. This app lets users leave simple messages in 
a guestbook. 

1.  Make sure your entity is 
    [asset enabled](/developer/frameworks/-/knowledge_base/7-2/asset-framework). 

2.  Choose a read-only view of the entity for which you want to enable ratings. 
    You can display ratings in one of your portlet's views, or if you've 
    [implemented asset rendering](/developer/frameworks/-/knowledge_base/7-2/rendering-an-asset) 
    you can display them in the full content view in the Asset Publisher app. 

3.  In the JSP, include the `liferay-ui` taglib declaration:

        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

4.  Use 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) 
    to get the entity's ID from the render request. Then create an entity object 
    using the `-LocalServiceUtil` class. Here's an example that does this for a 
    guestbook entry in the example Guestbook app: 

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Use the `liferay-ui:ratings` tag to add the ratings component for the 
    entity. This example uses the stars rating type: 

        <liferay-ui:ratings className="<%=Entry.class.getName()%>"
            classPK="<%=entry.getEntryId()%>" type="stars" />

## Related Topics

[Ratings](/developer/frameworks/-/knowledge_base/7-2/social-api#ratings)

[Implementing Rating Type Selection](/developer/frameworks/-/knowledge_base/7-2/implementing-rating-type-selection)

[Customizing Rating Value Transformation](/developer/frameworks/-/knowledge_base/7-2/customizing-rating-value-transformation)
