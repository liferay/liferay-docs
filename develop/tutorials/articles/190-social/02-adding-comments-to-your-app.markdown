# Adding Comments to your App [](id=adding-comments-to-your-app)

Users adding comments to your content makes your site come alive. Instead of you
statically giving users information, now the flow goes both ways. @product@'s
asset framework makes it easy to add a comment system to your app. 

![Figure 1: Your JSP lets users comment on content in your portlet.](../../images/social-comments-enabled.png)

This tutorial shows you how to add the comment feature for your application's
content.

Follow these steps:

1.  Make sure your entity is [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities).

2.  Choose a read-only view of the entity for the comments. You can display the
    comments component in your portlet's view, or if you've implemented 
    [asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset),
    you can display it in the full content view in the Asset Publisher portlet.

3.  Include the Liferay-UI taglib and Portlet taglib declarations in your JSP:

        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>      
        <%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>

4.  Use `ParamUtil` to get the entity's ID from the render request. Then create
    an entity object using the `-LocalServiceUtil` class. Below is an example
    configuration:

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Create a collapsible panel for the comments using the
    `liferay-ui:panel-container` and `liferay-ui:panel` tags. This makes the
    discussion area hide-able. Below is an example configuration:

        <liferay-ui:panel-container extended="<%=false%>"
          id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
          <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
            id="guestbookCollaborationPanel" persistState="<%=true%>"
            title="Collaboration">

6.  Next, create a URL for the discussion using the `portlet:actionURL` tag: 

        <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

7.  Finally, add the discussion with the `liferay-ui:discussion` tag. Pass the
    current URL using the `redirect` attribute, so the user can return to the
    JSP after making a comment. You can use
    `PortalUtil.getCurrentURL((renderRequest))` to get the current URL from the
    `request` object. Below is an example configuration:

            <liferay-ui:discussion className="<%=Entry.class.getName()%>"
              classPK="<%=entry.getEntryId()%>"
              formAction="<%=discussionURL%>" formName="fm2"
              ratingsEnabled="<%=true%>" redirect="<%=currentURL%>"
              userId="<%=entry.getUserId()%>" />

          </liferay-ui:panel>
        </liferay-ui:panel-container>

If you haven't already connected your portlet's view to the JSP for your entity,
you can refer [here](/develop/tutorials/-/knowledge_base/7-0/relating-assets#creating-a-url-to-your-new-jsp)
to see how to connect a portlet's main view JSP to an entity's view JSP.

Great! Now you know how to let users comment on content in your asset enabled
portlets.

## Related Topics [](id=related-topics)

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)

[Applying Social Bookmarks](/develop/tutorials/-/knowledge_base/7-0/applying-social-bookmarks)
