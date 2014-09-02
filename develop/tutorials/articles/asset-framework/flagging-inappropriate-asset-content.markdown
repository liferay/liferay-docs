# Flagging Inappropriate Asset Content

As you're undoubtedly aware, users can sometimes cross the line with what they 
post online. When users are able to report inappropriate content, a greater 
sense of community and collaboration develops as users establish what kinds of 
things will and won't be tolerated by the community. This also takes the bulk of 
the monitoring work off the backs of administrators. Liferay's asset framework 
fortunately has you covered--you can leverage it to let users report 
inappropriate content posted by others. This takes the form of a simple flag 
that users can click to fill out a small form letting administrators know why 
they find the content objectionable. You've probably seen this flag in Liferay's 
built in portlets. For example, each blog in the Blogs portlet can be flagged as 
inappropriate by users.

![Figure 1: Flags for letting users mark objectionable content are enabled in the built in Blogs portlet](../../images/asset-fw-flags-blog.png)

This tutorial shows you how to enable flagging of content in a portlet. As a 
prerequisite, you must have assets enabled in your portlet before you can enable 
content flagging. Once asset enabled, there are just two steps to enable content 
flagging in your portlet--add a JSP for implementing the flags and then create a 
URL for the new JSP in your existing `view.jsp`. This tutorial shows you these 
steps using a custom Insults portlet. Coming up with great insults is a natural 
part of comedy, but sometimes things can go too far. It's for these situations 
that flagging inappropriate content is useful.
<!-- Add links to begin and end portlets -Nick -->

Now it's time to get on with the flagging!

## Creating a JSP for Adding Flags

The first step to enable content flagging in your asset enabled portlet is to 
create a separate JSP for letting your users flag content. You might be 
wondering why you need a separate JSP for this. The truth is, you don't. You 
could just enable flagging in the edit JSP. However, separating collaborative 
activities like content flagging from editing or creating content makes 
sense--it reduces confusion between the two activities. You also probably don't 
want users to be able to edit content that someone else produced.

Create this JSP in the same directory as your portlet's `view.jsp`. For example, 
in the Insults portlet this JSP is `docroot/html/insults/view_insult.jsp`. The 
rest of this section uses `view_insult.jsp` as an example. The first thing you 
want to do is make sure that your users have a way of getting back to `view.jsp` 
after clicking on an entity. This is handled by the `portlet:renderURL` and 
`liferay-ui:header` tags:

    ```
    <portlet:renderURL windowState="normal" var="backURL">
        <portlet:param name="mvcPath" value="/html/insults/view.jsp"></portlet:param>
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
    
Now you just need to implement the flagging itself. This is done with the 
`liferay-ui:flags` tag. Here's the `liferay-ui:flags` tag for the Insults 
portlet:

    ```
    <liferay-ui:flags
        className="<%= Insult.class.getName() %>"
        classPK="<%= ins.getInsultId() %>"
        contentTitle="<%= ins.getInsultString() %>"
        reportedUserId="<%= ins.getUserId() %>"
    />

Awesome! Now you have a JSP that lets your users flag inappropriate content in 
your portlet. However, it's not hooked up to anything yet--your users can't 
access it from your portlet. It's a bit like being all dressed up with nowhere 
to go. The next step addresses this issue.

## Creating a URL to Your New JSP

Now that you have flagging implemented, you need to add a URL to your new JSP in 
the `view.jsp`. This is done by inserting the `portlet:renderURL` tag just after 
the `liferay-ui:search-container-row` tag. For example, in the Insults portlet 
it looks like this:

    ```
    <liferay-ui:search-container-row
        className="com.sample.portlet.insults.model.Insult"
        keyProperty="insultId"
        modelVar="insult" escapedModel="<%= true %>"
    >
    
        <portlet:renderURL windowState="maximized" var="rowURL">
            <portlet:param name="mvcPath" value="/html/insults/view_insult.jsp" />
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
your portlet reloads. Each entity in the portlet should now be a link. Click on 
one to view the new JSP that you made in the first step of this tutorial. The 
flag icon should appear at the bottom of the page.

![Figure 2: Entities in portlets appear as links after implementing content flagging.](../../images/asset-fw-flags-link.png)

![Figure 3: The new JSP lets users flag content in your portlet as inappropriate.](../../images/asset-fw-flags.png)

Great! Now you know how to let users flag inappropriate content in your asset 
enabled portlets. Remember, you just follow two simple steps--make a new JSP for 
implementing flagging, and then create a URL to that JSP so that your users can 
get to it.

## Related Topics

[Liferay UI Taglibs](/tutorials/-/knowledge_base/liferay-ui-taglibs)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/customizing-liferay-portal)
