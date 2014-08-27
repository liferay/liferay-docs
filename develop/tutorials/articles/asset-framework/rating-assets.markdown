# Rating Assets

Liferay's asset framework supports a rating system that lets your users rate 
content in plugins. You've probably seen this in many of Liferay's built-in 
plugins. A great example is the Blogs portlet. When users click on a blog, a row 
of stars appears at the bottom that lets them rate the blog from one to five 
stars. Letting users rate content is a fantastic way not only to figure out what 
content is popular, but also to foster a sense of community and collaboration 
among content producers and consumers. Even better, once your plugin is asset 
enabled, implementing ratings is a snap. 

This tutorial shows you how to add ratings to an asset enabled portlet by using 
code from a custom Insults portlet as an example. After all, a truly 
distinguished writer of insults needs to know how good his or her insults really 
are. You can find both the beginning and ending Insults portlet here on Github.

Now go ahead and get started learning how to add ratings to your portlets!

## Creating a JSP for Rating Entities

The first step to enabling ratings in your portlet is to create a separate JSP 
specifically for letting your users rate content. You might be wondering why you 
need a separate JSP for this. The truth is, you don't. You could just put the 
ratings in the edit JSP. However, separating collaborative activities like 
ratings from editing or creating content makes sense--it reduces confusion 
between the two activities. Also, you probably don't want users to be able to 
edit content that someone else produced.

Create this JSP in the same directory as your portlet's `view.jsp`. For example, 
in the Insults portlet this JSP is `docroot/html/insults/view_insult.jsp`. Since 
this JSP is accessed each time an entity in the portlet is clicked, you need to 
add code to the JSP that gets the object for that entity. This object is what 
you use to get the fields that you want the JSP to display, in addition to 
letting your users rate that entity. To implement ratings, simply add the 
`liferay-ui:ratings` tag to the bottom of the JSP. For example, the 
`liferay-ui:ratings` tag for the Insults portlet looks like this:

    <liferay-ui:ratings className="<%=Insult.class.getName()%>"
        classPK="<%=ins.getInsultId()%>" type="stars" />
        
Note that `type` is given the value `"stars"`. You can implement a simple 
thumbs-up or thumbs-down rating system by changing this value to `"thumbs"`.

Great! Now you have the JSP that lets your users rate content. However, it's not 
hooked up to anything yet--your users can't access it from your portlet. The 
next step addresses this issue.

## Creating a URL to Your New JSP

Now that you have ratings implemented, you need to add a URL to your new JSP in 
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

Next, add the `href` attribute with the value `"<%=rowURL %>"` to the first 
search container column. For example, in the Insults portlet it looks like this:

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
ratings should appear at the bottom of the page.

<!-- insert screenshot -->

## Related Topics

[Liferay UI Taglibs](/tutorials/-/knowledge_base/liferay-ui-taglibs)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/customizing-liferay-portal)
