# Enabling Comments on Your App's Contents [](id=enabling-comments-on-your-apps-contents)

Letting users comment on assets is a key social feature you can add to your app.
Such comments bring new life to your app's content, letting it grow organically 
with user interaction. @product@'s asset framework enables this functionality, 
which you can implement in a few steps.

![Figure 1: Your JSP lets users comment on content in your portlet.](../../images/social-comments-enabled.png)

Follow these steps to enable comments on your app's contents: 

1.  Make sure your app's custom entities are 
    [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities). 

2.  Choose a view to display the comments in. You can display the comments
    component in your portlet's view, or if you've implemented
    [asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset),
    you can display it in the full content view in the Asset Publisher portlet. 
    If you haven't already connected your portlet's view to your entity's JSP, 
    [see this tutorial](/develop/tutorials/-/knowledge_base/7-0/relating-assets#creating-a-url-to-your-new-jsp) 
    to learn how to do so. 

3.  Include the `liferay-ui` and `portlet` taglib declarations in your JSP: 

        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>      
        <%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>

4.  In your entity's view, use `ParamUtil` to get the entity ID from the render 
    request. Then use your `-LocalServiceUtil` class to create an entity object: 

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  Use the `liferay-ui:panel-container` and `liferay-ui:panel` tags to create a 
    collapsible panel for the comments. This lets your users hide any long 
    comment threads:

        <liferay-ui:panel-container extended="<%=false%>"
          id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
          <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
            id="guestbookCollaborationPanel" persistState="<%=true%>"
            title="Collaboration">

6.  Use the `portlet:actionURL` tag to create a discussion URL: 

        <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

7.  Finally, use the `liferay-ui:discussion` tag to implement the discussion. 
    Use the `redirect` attribute to pass the current URL, so the user can return 
    to the JSP after making a comment: 

            <liferay-ui:discussion className="<%=Entry.class.getName()%>"
              classPK="<%=entry.getEntryId()%>"
              formAction="<%=discussionURL%>" formName="fm2"
              ratingsEnabled="<%=true%>" redirect="<%=currentURL%>"
              userId="<%=entry.getUserId()%>" />

          </liferay-ui:panel>
        </liferay-ui:panel-container>

    To get the current URL from the `request` object, you can use 
    `PortalUtil.getCurrentURL((renderRequest))`. 

Great! Now you know how to let users comment on content in your asset-enabled
portlets. 

## Related Topics [](id=related-topics)

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)
