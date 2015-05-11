# Flagging Inappropriate Asset Content [](id=flagging-inappropriate-asset-content)

<!--
Testing Notes:

The starting example portlet for this tutorial is at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-02-asset-enable-insults-portlet

On completing this tutorial, the example portlet looks like the portlet at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-03-end-insults-portlet

Make sure to read their README files.
-->

As you're undoubtedly aware, users can sometimes cross the line with what they 
post. When users are able to report inappropriate content, a greater sense of
community and collaboration develops as users establish what kinds of things
will and won't be tolerated by the community. This also takes much of the
monitoring work off the backs of administrators. Liferay's asset framework
fortunately has you covered--you can leverage it to let users report
inappropriate content posted by others. Users can click a flag which opens 
a small form they can fill out, letting administrators know why they find the
content objectionable. You've probably seen this flag in Liferay's built in
portlets. For example, each blog in the Blogs portlet can be flagged as
inappropriate by users.

![Figure 1: Flags for letting users mark objectionable content are enabled in the built in Blogs portlet](../../images/asset-fw-flags-blog.png)

This tutorial shows you how to enable flagging of content in a portlet. As a 
prerequisite, you must have [assets enabled](/develop/tutorials/-/knowledge_base/6-2/adding-updating-and-deleting-assets-for-custom-entities)
for your portlet's custom entity before you can enable content flagging. This
tutorial demonstrates implementing this feature in a custom Insults portlet.
Coming up with great insults is a natural part of comedy, but sometimes things
can go too far. It's for these situations that flagging inappropriate content is
useful. You can find the Insults portlet complete with the flagging feature
enabled on [Github](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-begin-insults-portlet).

Now it's time to get on with the flagging!

If you've implemented [asset rendering](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)
for your entity, you can use flags in the full content view of an Asset
Publisher portlet. You can also use flags in any view JSP you create for viewing
the entity. 

As an example, the Insult portlet's view JSP file [`view_insult.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view_insult.jsp)
shows an insult entity and the flagging component. This tutorial shows you how
to associate the flagging component with your custom entity.

In your view JSP, you can use `ParamUtil` to get the ID of the entity from the
render request. Then you can create an entity object using your
`-LocalServiceUtil` class. 

    <%
    long insultId = ParamUtil.getLong(renderRequest, "insultId");
    Insult ins = InsultLocalServiceUtil.getInsult(insultId);
    %>
 
Now you just need to implement the flagging itself. This is done with the
`liferay-ui:flags` tag. Here's the `liferay-ui:flags` tag for the Insults
portlet:

    <liferay-ui:flags
        className="<%= Insult.class.getName() %>"
        classPK="<%= ins.getInsultId() %>"
        contentTitle="<%= ins.getInsultString() %>"
        reportedUserId="<%= ins.getUserId() %>"
    />

Awesome! Now you have a JSP that lets your users flag inappropriate content in 
your portlet. 

If you haven't already connected your portlet's view to the JSP for your entity,
you can refer [here](/develop/tutorials/-/knowledge_base/6-2/relating-assets#creating-a-url-to-your-new-jsp)
to see how to connect your portlet's main view JSP to your entity's view JSP.

Now redeploy your portlet and refresh the page so that the your plugin's UI
reloads. The flag icon appears at the bottom of the page.

![Figure 2: Users can now flag content in your portlet as inappropriate.](../../images/asset-fw-flags.png)

Great! Now you know how to let users flag inappropriate content in your asset 
enabled portlets. 

Another thing you might want to do is perform permissions checks to control 
who can flag content. For example, the Add Insult and Permissions buttons of the
Insults portlet are wrapped in a permissions check in the 
[`view.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view.jsp).
For more information on this, see the Learning Path [Checking Permissions in the UI](/develop/learning-paths/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).

**Related Topics**

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)
