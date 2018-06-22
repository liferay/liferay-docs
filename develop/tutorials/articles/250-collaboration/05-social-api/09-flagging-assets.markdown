# Flagging Inappropriate Asset Content [](id=flagging-inappropriate-asset-content)

In a perfect world, people would post nice, kind, and decent content. They would 
reply to comments with constructive feedback and never lash out at each other. 
Unfortunately, sometimes people have a bad day and decide to take their 
frustrations out in inappropriate posts. No worries though, the asset framework 
supports a system for flagging content in apps. Letting users flag inappropriate 
content takes much of the work off site administrators. 

This tutorial shows you how to enable content flagging in a portlet.

![Figure 1: Flags for letting users mark objectionable content are enabled in the Message Boards portlet.](../../../images/social-flags.png)

Follow these steps to enable content flagging in your app: 

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-0/asset-framework). 

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
    [tag `liferay-flags:flags`](@app-ref@/collaboration/latest/taglibdocs/liferay-flags/flags.html) 
    to add the flags component: 

        <liferay-flags:flags
        	className="<%= Entry.class.getName() %>"
        	classPK="<%= entry.getEntryId() %>"
        	contentTitle="<%= title %>"
        	message="flag-this-content"
        	reportedUserId="<%= reportedUserId %>"
        />

    The `reportedUserId` attribute specifies the user who flagged the asset. 

Great! Now you know how to let users flag content in your asset-enabled apps. 

## Related Topics [](id=related-topics)

[Asset Framework](/develop/tutorials/-/knowledge_base/7-1/asset-framework)

[Rating Assets](/develop/tutorials/-/knowledge_base/7-1/rating-assets)
