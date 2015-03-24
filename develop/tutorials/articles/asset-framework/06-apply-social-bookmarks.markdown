# Apply Social Bookmarks [](id=apply-social-bookmarks)

<!--
Testing Notes:

The starting example portlet for this tutorial is at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-02-asset-enable-insults-portlet

On completing this tutorial, the example portlet looks like the portlet at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-03-end-insults-portlet

Make sure to read their README files.
-->

Social bookmarks in Liferay let users share portal content on social networks. 
When social bookmarks are enabled, a row of icons appears below the content that 
lets users share it with Twitter, Facebook, and Google Plus. You've probably 
seen this before in some of Liferay's built-in portlets. The Blogs portlet is a
good example. The row of share icons appears below each blog entry in the
portlet. 

![Figure 1: Social bookmarks are enabled in the built-in Blogs portlet](../../images/asset-fw-social-bookmarks-icons.png)

Since each piece of content in a portlet is an asset, your portlet needs to be 
[asset enabled](/develop/tutorials/-/knowledge_base/6-2/adding-updating-and-deleting-assets-for-custom-entities)
before you can add social bookmarks. 

Users naturally want to share the greatest of insults with the world! To help
demonstrate this feature, a custom Insults portlet is used in this tutorial's
examples. You can find the Insults portlet complete with social bookmarks
enabled on [Github](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet).

You can show social bookmarks in your portlet's view of your custom entity or, if
you've implemented [asset rendering](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers),
you can show social bookmarks in the full content view in an Asset Publisher
portlet. 

As an example, the Insult portlet's view JSP file
[`view_insult.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view_insult.jsp)
shows an insult entity with the social bookmarks asset feature. 

In your entity's view JSP, you can use `ParamUtil` to get the entity's ID 
from the `renderRequest`. Then you can create an entity object using your
`-LocalServiceUtil` class. 

    <%
    long insultId = ParamUtil.getLong(renderRequest, "insultId");
    Insult ins = InsultLocalServiceUtil.getInsult(insultId);
    %>
 
Now you need to add the social bookmark component. This is done with the 
`liferay-ui:social-bookmarks` tag. Here's the `liferay-ui:social-bookmarks` tag 
for the Insults portlet:

    <liferay-ui:social-bookmarks
        displayStyle="horizontal"
        target="_blank"
        title="<%= ins.getInsultString() %>"
        url="<%= PortalUtil.getCanonicalURL((PortalUtil.getCurrentURL(request)), themeDisplay, layout) %>" 
    />
 
There are a some things to take note of in this tag. When `displayStyle` is set 
to `horizontal`, the flags indicating the number of shares for each button are 
positioned to the right. This is the case in the above screenshot. Setting 
`displayStyle` to `vertical` positions the flags above each share button.

![Figure 2: Here are the share buttons with `displayStyle` set to `"vertical"`.](../../images/asset-fw-social-bookmarks-icons-vertical.png)

The `target` attribute is the HTML target for the link. Setting it to `_blank`
opens a new browser tab or window. The `title` attribute is sent to the social
service and becomes part of the link you create there, as does the URL. 

Also, the specific URL of the shared content on your site is set with `url`. The 
URL for viewing the content is retrieved using `PortalUtil`.

Now you have a JSP that lets your users share content in your portlet. 

If you haven't already connected your portlet's view to the JSP for your entity,
you can refer [here](/develop/tutorials/-/knowledge_base/6-2/relating-assets#creating-a-url-to-your-new-jsp)
to see how to connect your portlet's main view JSP to your entity's view JSP. 

Now redeploy your portlet and refresh the page so that the your plugin's UI
reloads. The social bookmarks UI component now shows in your entity's view. 

![Figure 3: The new JSP lets users share content in your portlet.](../../images/asset-fw-social-bookmarks.png)

Great! Now you know how to let users share content in your asset enabled 
portlets. 

Another thing you might want to do is perform permissions checks to control 
who can rate content. For example, the Add Insult and 
Permissions buttons of the Insults portlet are wrapped in a permissions check in 
the [`view.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view.jsp).
For more information on this, see the Learning Path [Checking Permissions in the UI](/develop/learning-paths/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).

**Related Topics**

[Asset Enabling Custom Entities](/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)

[Implementing Asset Renderers](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)
