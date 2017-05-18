# Applying Social Bookmarks [](id=applying-social-bookmarks)

When social bookmarks are enabled, a row of icons appears below the content that 
lets users share portal content across social networks. You've most likely seen 
this feature while browsing an article or blog on Liferay or one of your other 
favorite sites. Simply click the Tweet, Facebook like, or Google Plus button to 
share the content with others. 

![Figure 1: Social bookmarks are enabled in the built-in Blogs portlet](../../../images/social-bookmarks-icons.png)

Since each piece of content in a portlet is an asset, your portlet needs to be 
[asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)
before you can add social bookmarks. 

To illustrate this feature, the Guestbook portlet, created in the 
[6.2 MVC Learning Path](/develop/tutorials/-/knowledge_base/6-2/writing-a-liferay-mvc-application) 
and since modularized, is used in this tutorial's examples. You can find the 
Guestbook portlet complete with social bookmarks enabled on [Github](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/guestbook-social-bookmarks/guestbook).

You can show social bookmarks in your portlet's view of your custom entity or, 
if you've implemented 
[asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset),
you can show social bookmarks in the full content view in an Asset Publisher
portlet. 

As an example, the `guestbook-web` portlet's view JSP file
[`view.jsp`](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/guestbook-social-bookmarks/guestbook/guestbook-web/src/main/resources/META-INF/resources/html/guestbookmvcportlet/view.jsp)
shows guestbook entry entities with the social bookmarks asset feature. 

The guestbook retrieves an entry entity's name from its 
[`com.liferay.docs.guestbook.model.entry` class](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/guestbook-social-bookmarks/guestbook/guestbook-api/src/main/java/com/liferay/docs/guestbook/model/entry.java), 
but you can also use `ParamUtil` to get an entity's ID from the `renderRequest`. 
If using the latter approach, you can create an entity object using your 
`-LocalServiceUtil` class, as shown below: 

    <%
    long [ID variable] = ParamUtil.getLong(renderRequest, "[ID variable]");
    [Entity object] [reference variable] = *LocalServiceUtil.[getEntityMethod]([ID variable]]);
    %>
 
Now you need to add the social bookmark component. This is done with the 
`liferay-ui:social-bookmarks` tag. Here's the `liferay-ui:social-bookmarks` tag 
for the Guestbook portlet:

    <liferay-ui:social-bookmarks
        displayStyle="menu"
        target="_blank"
        title="<%= String.valueOf(entry.getName()) %>"
        url="<%= PortalUtil.getCanonicalURL((viewPageURL.toString()), 
        themeDisplay, layout) %>" 
    />
 
When `displayStyle` is set to `menu`, the share buttons are hidden in a 
clutter-free menu. This is the case in the above screenshot. 

A `simple` `displayStyle` displays the share buttons with no share stats. 

![Figure 2: Here are the share buttons with `displayStyle` set to `"simple"`.](../../../images/social-bookmarks-icons-simple.png)

Setting `displayStyle` to `vertical` positions the flags above each share button. 

![Figure 3: Here are the share buttons with `displayStyle` set to `"vertical"`.](../../../images/social-bookmarks-icons-vertical.png)

When `displayStyle` is set to `horizontal`, the flags indicating the number of 
shares for each button are positioned to the right.

![Figure 4: Here are the share buttons with `displayStyle` set to `"horizontal"`.](../../../images/social-bookmarks-icons-horizontal.png)

The `target` attribute is the HTML target for the link. Setting it to `_blank`
opens a new browser tab or window. The `title` attribute is sent to the social
network and becomes part of the link you create there, as does the URL. 

Also, the specific URL of the shared content on your site is set with `url`. The 
URL for viewing the content is retrieved using `PortalUtil`.

Now you have a JSP that lets your users share content in your portlet. 

If you haven't already connected your portlet's view to the JSP for your entity,
you can refer [here](/develop/tutorials/-/knowledge_base/7-0/relating-assets#creating-a-url-to-your-new-jsp)
to see how to connect your portlet's main view JSP to your entity's view JSP. 

Now redeploy your module and refresh the page so that the your app's UI
reloads. The social bookmarks UI component now shows in your entity's view. 

![Figure 3: The new JSP lets users share content in your portlet.](../../../images/social-guestbook-social-bookmarks.png)

Great! Now you know how to let users share content in your asset enabled 
apps. 

Another thing you might want to do is perform permissions checks to control 
who can rate content. For example, the Guestbook Actions of the Guestbook 
portlet are wrapped in permissions checks in the 
[`guestbook_actions.jsp`](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/guestbook-social-bookmarks/guestbook/guestbook-web/src/main/resources/META-INF/resources/html/guestbookmvcportlet/guestbook_actions.jsp).
For more information on this, see the
[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources) 
tutorial.

**Related Topics**

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)