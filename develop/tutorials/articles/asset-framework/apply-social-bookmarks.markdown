# Apply Social Bookmarks

Social bookmarks in Liferay let users share portal content on social networks. 
When social bookmarks are enabled, a row of icons appears below the content that 
lets users share it with Twitter, Facebook, and Google Plus. You've probably 
seen this before in some of Liferay's built in portlets. The built in Blogs 
portlet is a good example. The row of share icons appears below each blog in the 
portlet. 

![Figure 1: Social bookmarks are enabled in the built in Blogs portlet](../../images/asset-fw-social-bookmarks-icons.png)

Since each piece of content in a portlet is an asset, your portlet needs to be 
asset enabled before you can add social bookmarks. Once asset enabled, there are 
just two steps to enable social bookmarks in your portlet--add a JSP for the 
social bookmarks and then create a URL for the new JSP in your existing 
`view.jsp`. This tutorial shows you these steps using a custom Insults portlet. 
Users naturally want to share the greatest of insults with the world! You can 
find the beginning Insults portlet [here on Github](https://github.com/liferay/liferay-docs/tree/assetfw-tutorials/develop/tutorials/code/asset-framework/begin).
Likewise, the Insults portlet with social bookmarks enabled is on Github, [here](https://github.com/liferay/liferay-docs/tree/assetfw-tutorials/develop/tutorials/code/asset-framework/social-bookmarks/end).

Now go ahead and get started with social bookmarks!

## Creating a JSP for Applying Social Bookmarks

The first step to apply social bookmarks in your asset enabled portlet is to 
create a separate JSP for letting your users share content. You might be 
wondering why you need a separate JSP for this. The truth is, you don't. You 
could just add social bookmarks in the edit JSP. However, separating 
collaborative activities like sharing from editing or creating content makes 
sense--it reduces confusion between the two activities. You also probably don't 
want users to be able to edit content, that someone else produced, when sharing. 
The internet is wild enough without bestowing that kind of power on users.

Create this JSP in the same directory as your portlet's `view.jsp`. For example, 
in the Insults portlet this JSP is `docroot/html/insult/view_insult.jsp`. The 
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
    
Now you just need to add the social bookmark itself. This is done with the 
`liferay-ui:social-bookmarks` tag. Here's the `liferay-ui:social-bookmarks` tag 
for the Insults portlet:

    ```
    <liferay-ui:social-bookmarks
        displayStyle="horizontal"
        target="_blank"
        title="<%= ins.getInsultString() %>"
        url="<%= PortalUtil.getCanonicalURL((PortalUtil.getCurrentURL(request)), themeDisplay, layout) %>" 
    />
    
There are a some things to take note of in this tag. When `displayStyle` is set 
to `"horizontal"`, the flags indicating the number of shares for each button are 
positioned to the right. This is the case in the above screenshot. Setting 
`displayStyle` to `"vertical"` positions these flags above each share button.

![Figure 2: The share buttons with `displayStyle` set to `"vertical"`.](../../images/asset-fw-social-bookmarks-icons-vertical.png)

<!-- What do target and title do? I don't see a title anywhere in the row of share icons -Nick -->
Also, the specific URL of the shared content on your site is set with `url`. The 
URL for viewing the content, which is also the URL of the JSP, is retrieved by 
`PortalUtil`.

Far out! Now you have a JSP that lets your users share content in your portlet. 
However, it's not hooked up to anything yet--your users can't access it from 
your portlet. It's a bit like being all dressed up with nowhere to go. The next 
step addresses this issue.

## Creating a URL to Your New JSP

Now that you have social bookmarks implemented, you need to add a URL to your 
new JSP in the `view.jsp`. This is done by inserting the `portlet:renderURL` tag 
just after the `liferay-ui:search-container-row` tag. For example, in the 
Insults portlet it looks like this:

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
share buttons should appear at the bottom of the page.

![Figure 2: Entities in portlets appear as links after implementing social bookmarks.](../../images/asset-fw-social-bookmarks-link.png)

![Figure 3: The new JSP lets users share content in your portlet.](../../images/asset-fw-social-bookmarks.png)

Great! Now you know how to let users share content in your asset enabled 
portlets. Remember, you just follow two simple steps--make a new JSP for the 
social bookmarks, and then create a URL to that JSP so that your users can get 
to it.

Another thing you might want to do is perform permissions checks to control 
access to the JSP your ratings are in. For example, the Add Insult and 
Permissions buttons of the Insults portlet are wrapped in a permissions check in 
the `view.jsp`. For more information on this, see the learning path 
[Checking Permissions in the UI](/learning-paths/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).

## Related Topics

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)
