# Commenting on Assets

Liferay's asset framework facilitates discussions on individual pieces of 
content posted in a plugin. This is great because when users see content posted, 
they expect to be able to discuss it. Fortunately, most of the work is already 
done for you so you don't have to spend time developing a commenting system from 
scratch. As long as your plugin is asset enabled, there are just a couple of 
steps to enable commenting on content--add a JSP for viewing entities and then 
create a URL for the new JSP in your existing `view.jsp`. That's it. 

This tutorial shows you how to enable commenting on content posted in an asset 
enabled portlet. A custom Insults portlet is used as an example--a community 
discussion will definitely help to bring about insults of the highest quality! 
You can find the beginning Insults portlet [here on Github](https://github.com/liferay/liferay-docs/tree/assetfw-tutorials/develop/tutorials/code/asset-framework/begin).
Likewise, the ending Insults portlet is on Github, [here](https://github.com/liferay/liferay-docs/tree/assetfw-tutorials/develop/tutorials/code/asset-framework/comments/end).

Without any further ado, go ahead and get started enabling comments in your 
portlet!

## Creating a JSP for Adding Comments to Content

The first step to enabling comments in your portlet is to create a separate JSP 
for letting your users comment on content. You might be wondering why you need a 
separate JSP for this. The truth is, you don't. You could just enable comments 
in the edit JSP. However, separating collaborative activities like commenting 
from editing or creating content makes sense--it reduces confusion between the 
two activities. Also, you probably don't want users to be able to edit content 
that someone else produced.

Create this JSP in the same directory as your portlet's `view.jsp`. For example, 
in the Insults portlet this JSP is `docroot/html/insults/view_insult.jsp`. The 
rest of this section uses `view_insult.jsp` as an example. The first thing you 
want to do is make sure that your users have a way of getting back to `view.jsp` 
after clicking on an entity. This is handled by the `portlet:renderURL` and 
`liferay-ui:header` tags:

    ```
    <portlet:renderURL windowState="normal" var="backURL">
        <portlet:param name="mvcPath" value="/html/insult/view.jsp"></portlet:param>
    </portlet:renderURL>

    <liferay-ui:header backURL="<%=backURL%>" title="insult" />
    ```
    
You also want to show the entity in the JSP when a user clicks on it. To do 
this, use `ParamUtil` to get the id of the entity from the `renderRequest`. Then 
create an object using your `*LocalServiceUtil`. Here, this is done to create an 
`Insult` object:

    ```
    <%
    long insultId = ParamUtil.getLong(renderRequest, "insultId");
    Insult ins = InsultLocalServiceUtil.getInsult(insultId);
    ```
    %>
    ```

Next, you need to add the code that handles the discussion. First you'll create 
a collapsible panel to put the discusison in. This is done with the 
`liferay-ui:panel-container` and `liferay-ui:panel` tags. Collapsible panels are 
a nice feature of Liferay UI that lets your users obscure any potentially long 
content on a page. Now that this panel is in place, you need to create a URL for 
the discussion. This is done with the `portlet:actionURL` tag. You also need to 
get the current URL from the `request` object, so that your user can return to 
the JSP after adding a comment. This is done with `PortalUtil`. Last, but 
certainly not least, is the implementation of the discussion itself with the 
`liferay-ui:discussion` tag. Note that the URLs you created are used here, as 
well as the entity object. The following block of code shows the collapsible 
panel, URLs, and the discussion:

    ```
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
    ```

Awesome! Now you have a JSP that lets your users comment on content in your 
portlet. However, it's not hooked up to anything yet--your users can't access it 
from your portlet. The next step addresses this issue.

## Creating a URL to Your New JSP

Now that you have commenting implemented, you need to add a URL to your new JSP 
in the `view.jsp`. This is done by inserting the `portlet:renderURL` tag just 
after the `liferay-ui:search-container-row` tag. For example, in the Insults 
portlet it looks like this:

    ```
    <liferay-ui:search-container-row
        className="com.liferay.docs.insult.model.Insult"
        keyProperty="insultId"
        modelVar="insult" escapedModel="<%= true %>"
    >
    
        <portlet:renderURL windowState="maximized" var="rowURL">
            <portlet:param name="mvcPath" value="/html/insult/view_insult.jsp" />
            <portlet:param name="insultId" value="<%= String.valueOf(insult.getInsultId()) %>" />
        </portlet:renderURL>
    ```

Next, add the `href` attribute to the first search container column with the 
value of the URL you just created in the `portlet:renderURL` tag. For example, 
the value of `href` in the Insults portlet is `"<%=rowURL %>"`:

    ```
    <liferay-ui:search-container-column-text
        name="Insult"
        value="<%= insult.getInsultString() %>"
        href="<%=rowURL %>"
    />
    ```

Now just redeploy your portlet and refresh the page so that the `view.jsp` of 
your plugin reloads. Each entity in the portlet should now be a link. Click on 
one to view the new JSP that you made in the first step of this tutorial. The 
comments section should appear at the bottom of the page.

![Figure 1: Entities in portlets appear as links after implementing comments.](../../images/asset-fw-comments-link.png)

![Figure 2: The new JSP lets users comment on content in your portlet.](../../images/asset-fw-comments.png)

Great! Now you know how to let users comment on content in your asset enabled 
portlets. Remember, you just follow two simple steps--make a new JSP for the 
comments, and then create a URL to that JSP so that your users can get to it.

Before moving on, another thing you might want to do is perform permissions 
checks to control who can access the discussion. For example, the collapsible 
panel in the `view_insult.jsp` of the Insults portlet is surrounded by `c:if` 
tags that only reveal their contents to users that are signed in to the portal:

    <c:if test="<%=themeDisplay.isSignedIn()%>">
    ```
    </c:if>

This is just one way of controlling access to the discussion. For example, you 
can also do so by performing more specific permissions checks, as the Insults 
portlet does for the Add Insults and Permissions buttons in its `view.jsp`. For 
more information, see the learning path [Checking Permissions in the UI](/learning-paths/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).

## Related Topics

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)
