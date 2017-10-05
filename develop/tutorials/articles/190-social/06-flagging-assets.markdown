# Flagging Inappropriate Asset Content [](id=flagging-inappropriate-asset-content)

In a perfect world, people would post nice, kind, and decent content. They would 
reply to comments with constructive feedback and never lash out at one another. 
Unfortunately, sometimes people have a bad day and decide to take their 
frustration out on Joe Bloggs in the form of an inappropriate post. No worries 
though, @product@'s asset framework supports a system for flagging content in 
apps. Letting users flag inappropriate content takes much of the work off site
administrators. 

![Figure 1: Flags for letting users mark objectionable content are enabled in the Message Boards portlet.](../../images/social-flags.png)

This tutorial shows you how to enable flagging of content in a portlet.

Follow these steps: 

1.  Make sure your entity is 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities).

2.  Choose a read-only view of the entity for the flags. You can display 
    them in one of your portlet's views, or if you've implemented 
    [asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset), 
    you can display them in the full content view in the Asset Publisher 
    portlet. 

3.  In the JSP, include the `liferay-flags` taglib declaration:

        <%@ taglib prefix="liferay-flags" uri="http://liferay.com/tld/flags" %>

4.  Use the `-LocalServiceUtil` class to get the entity:

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Use the [`liferay-flags:flags` tag](@app-ref@/collaboration/latest/taglibdocs/liferay-flags/flags.html) 
    to add the flags component:

        <liferay-flags:flags
        	className="<%= Entry.class.getName() %>"
        	classPK="<%= entry.getEntryId() %>"
        	contentTitle="<%= title %>"
        	message="flag-this-content"
        	reportedUserId="<%= reportedUserId %>"
        />

    The `reportedUserId` attribute specifies the user who flagged the asset.

Great! Now you know how to let users flag content in your asset-enabled portlets. 

## Related Topics [](id=related-topics)

[Adding Comments to Your App](/develop/tutorials/-/knowledge_base/7-0/adding-comments-to-your-app)

[Applying Social Bookmarks](/develop/tutorials/-/knowledge_base/7-0/applying-social-bookmarks)
