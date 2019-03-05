# Flagging Inappropriate Asset Content [](id=flagging-inappropriate-asset-content)

The asset framework supports a system for flagging inappropriate content in 
apps. The steps here show you how to enable it in your app. 

![Figure 1: Users can flag objectionable content.](../../../images/social-flags.png)

Follow these steps to enable content flagging in your app: 

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-2/asset-framework). 

2.  Choose a read-only view of the entity you want to enable flags on. You can 
    display flags in one of your app's views, or if you've 
    [implemented asset rendering](/develop/tutorials/-/knowledge_base/7-1/rendering-an-asset) 
    you can display it in the full content view in the Asset Publisher app. 

3.  In your JSP, include the `liferay-flags` taglib declaration:

        <%@ taglib prefix="liferay-flags" uri="http://liferay.com/tld/flags" %>

4.  Use `ParamUtil` to get the entity's ID from the render request. Then use 
    your `-LocalServiceUtil` class to create an entity object: 

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Use the 
    [`liferay-flags:flags`](@app-ref@/collaboration/latest/taglibdocs/liferay-flags/flags.html) 
    tag to add the flags component: 

        <liferay-flags:flags
        	className="<%= Entry.class.getName() %>"
        	classPK="<%= entry.getEntryId() %>"
        	contentTitle="<%= title %>"
        	message="flag-this-content"
        	reportedUserId="<%= reportedUserId %>"
        />

    The `reportedUserId` attribute specifies the ID of the user who flagged the 
    asset. 

## Related Topics [](id=related-topics)

[Asset Framework](/develop/tutorials/-/knowledge_base/7-1/asset-framework)

[Rating Assets](/develop/tutorials/-/knowledge_base/7-1/rating-assets)
