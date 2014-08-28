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
You can find the beginning Insults portlet [here on Github](https://github.com/ngaskill/liferay-docs/tree/assetfw-tutorials/develop/tutorials/code/asset-framework/begin).
Likewise, the ending Insults portlet is on Github, [here](https://github.com/ngaskill/liferay-docs/tree/assetfw-tutorials/develop/tutorials/code/asset-framework/comments/end).

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
in the Insults portlet this JSP is `docroot/html/insults/view_insult.jsp`. Since 
this JSP is accessed each time an entity in the portlet is clicked, you need to 
add code to the JSP that gets the object for that entity. This object is what 
you use to get the fields that you want the JSP to display, in addition to 
letting your users comment on that entity. To implement commenting, add the 
`portlet:actionURL` and `liferay-ui:discussion` tags to the bottom of the JSP. 
For example, these tags for the Insults portlet look like this:

    ```
    <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
    ```
    <liferay-ui:discussion className="<%=Insult.class.getName()%>"
        classPK="<%=ins.getInsultId()%>"
        formAction="<%=discussionURL%>" formName="fm2"
        ratingsEnabled="<%=true%>" redirect="<%=currentUrl%>"
        subject="<%=ins.getInsultString()%>"
        userId="<%=ins.getUserId()%>" />
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
your plugin reloads. Each entity in the portlet should now be a link. Click on 
one to view the new JSP that you made in the first step of this tutorial. The 
comments section should appear at the bottom of the page.

![Figure 1: Entities in portlets appear as links after implementing comments.](../../images/asset-fw-comments-link.jpg)

![Figure 2: The new JSP lets users comment on content in your portlet.](../../images/asset-fw-comments.jpg)

Great! Now you know how to let users comment on content in your asset enabled 
portlets. Remember, you just follow two simple steps--make a new JSP for the 
comments, and then create a URL to that JSP so that your users can get to it.

## Related Topics

[Liferay UI Taglibs](/tutorials/-/knowledge_base/liferay-ui-taglibs)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/customizing-liferay-portal)
