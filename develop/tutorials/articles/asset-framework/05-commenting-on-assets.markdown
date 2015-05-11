# Commenting on Assets [](id=commenting-on-assets)

<!--
Testing Notes:

The starting example portlet for this tutorial is at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-02-asset-enable-insults-portlet

On completing this tutorial, the example portlet looks like the portlet at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-03-end-insults-portlet

Make sure to read their README files.
-->

Liferay's asset framework facilitates discussions on individual pieces of 
content posted in a plugin. This is great because it lets discuss any posted
content. Fortunately, most of the work is already done for you so you don't have
to spend time developing a commenting system from scratch. 

![Figure 1: Your JSP lets users comment on content in your portlet.](../../images/asset-fw-comments.png)

In order to implement the comments feature on your custom entity, it must be [asset enabled](/develop/tutorials/-/knowledge_base/6-2/adding-updating-and-deleting-assets-for-custom-entities). 
This tutorial shows you how to add the comment feature for your application's
content. A custom Insults portlet is used as an example: a community discussion
definitely helps to bring about insults of the highest quality! The
completed Insults portlet code that uses this feature is on 
[Github](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet).

Without any further ado, go ahead and get started enabling comments in your 
portlet!

You can display the comments component in your portlet's view 
or, if you've implemented [asset rendering](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers),
you can display it in the full content view in the Asset Publisher portlet. 

As an example, the Insult portlet's view JSP file
[`view_insult.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view_insult.jsp)
shows an insult entity and this asset feature. 

In your entity's view JSP, you can use `ParamUtil` to get the ID of the entity
from the render request. Then you can create an entity object using your
`-LocalServiceUtil` class. 

    <%
    long insultId = ParamUtil.getLong(renderRequest, "insultId");
    Insult ins = InsultLocalServiceUtil.getInsult(insultId);
    %>

Next, you need to add the code that handles the discussion. First you'll create 
a collapsible panel for the comments, so the discussion area doesn't visually
take precedence over the content. This is done with the
`liferay-ui:panel-container` and `liferay-ui:panel` tags. Collapsible panels let
your users obscure any potentially long content on a page. Now that this panel
is in place, you need to create a URL for the discussion. This is done with the
`portlet:actionURL` tag. You also need to get the current URL from the `request`
object, so that your user can return to the JSP after adding a comment. This is
done using `PortalUtil`. Last, but certainly not least, is the implementation of
the discussion itself with the `liferay-ui:discussion` tag. The following block
of code shows the collapsible panel, URLs, and the discussion:

    <liferay-ui:panel-container extended="<%=false%>"
        id="insultCommentsPanelContainer" persistState="<%=true%>">
	
        <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
            id="insultCommentsPanel" persistState="<%=true%>"
            title='<%=LanguageUtil.get(pageContext, "comments")%>'>
	
            <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
			
            <%
            String currentUrl = PortalUtil.getCurrentURL(request);
            %>
	
            <liferay-ui:discussion className="<%=Insult.class.getName()%>"
                classPK="<%=ins.getInsultId()%>"
                formAction="<%=discussionURL%>" formName="fm2"
                ratingsEnabled="<%=true%>" redirect="<%=currentUrl%>"
                subject="<%=ins.getInsultString()%>"
                userId="<%=ins.getUserId()%>" />

        </liferay-ui:panel>
    </liferay-ui:panel-container>

Awesome! Now you have a JSP that lets your users comment on content in your 
portlet.

If you haven't already connected your portlet's view to the JSP for your entity,
you can refer [here](/develop/tutorials/-/knowledge_base/6-2/relating-assets#creating-a-url-to-your-new-jsp)
to see how to connect your portlet's main view JSP to your entity's view JSP. 

Now redeploy your portlet and refresh the page so that the your plugin's UI
reloads. The comments section appear at the bottom of the page.

Great! Now you know how to let users comment on content in your asset enabled 
portlets. 

Before moving on, another thing you might want to do is perform permissions 
checks to control who can access the discussion. For example, you can surround
the collapsible panel 
[`view_insult.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view_insult.jsp)
with `c:if` tags that only reveal their contents to users that are signed in to
the portal:

    <c:if test="<%=themeDisplay.isSignedIn()%>">

        [Collapsible Panel Here]

    </c:if>

This is just one way of controlling access to the discussion. You 
could also perform more specific permissions checks as the Insults 
portlet does for the Add Insults and Permissions buttons in its [`view.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view.jsp).
For more information, see the Learning Path [Checking Permissions in the UI](/develop/learning-paths/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).

**Related Topics**

[Asset Enabling Custom Entities](/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)

[Implementing Asset Renderers](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)
