# Rating Assets [](id=rating-assets)

<!--
Testing Notes:

The starting example portlet for this tutorial is at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-02-asset-enable-insults-portlet

On completing this tutorial, the example portlet looks like the portlet at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-03-end-insults-portlet

Make sure to read their README files.
-->

Liferay's asset framework supports a rating system that lets your users rate
content in plugins. You've probably seen this in many of Liferay's built-in
plugins. A great example is the Blogs portlet. On viewing a blogs entry,
a row of stars appears, letting the user rate the entry on a scale from one to
five stars. Ratings help users figure out what content is popular. The feature
also fosters a sense of community and collaboration among content producers and
consumers. Even better, once your plugin is asset enabled, implementing ratings
is a snap. 

![Figure 1: Users can now rate instances of your custom entities.](../../images/asset-fw-ratings.png)

This tutorial shows you how to add ratings to an asset enabled portlet. The
tutorial uses code from a custom Insults portlet as an example. The Insults
portlet seemed appropriate, since a truly distinguished writer of insults needs
to know how good his or her insults really are. 

In order to implement ratings on your custom entity, it must be [asset enabled](/develop/tutorials/-/knowledge_base/6-2/adding-updating-and-deleting-assets-for-custom-entities). 
The completed Insults portlet code that uses this feature is on GitHub, [here](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet).

Now go ahead and get started learning how to add ratings to your portlets!

If you've implemented [asset rendering](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)
for your custom entity, you can display ratings in the full content view
of your entity. You can, of course, also display this asset feature in any view
JSP you create for viewing the entity. 

As an example, the Insult portlet's view JSP file [`view_insult.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view_insult.jsp)
shows an insult entity and its ratings. This tutorial shows you how to associate
the ratings component with your custom entity.

In your view JSP you can use `ParamUtil` to get the ID of the entity from the
render request. Then you can create an entity object using your
`-LocalServiceUtil` class. 

    <%
    long insultId = ParamUtil.getLong(renderRequest, "insultId");
    Insult ins = InsultLocalServiceUtil.getInsult(insultId);
    %>

After the code that displays your entity, you can add the ratings component
using the `liferay-ui:ratings` tag. Note that the object's ID is used to tie the
ratings to the entity:

    <liferay-ui:ratings className="<%=Insult.class.getName()%>"
        classPK="<%=ins.getInsultId()%>" type="stars" />

In the code above, the `type` attribute is given the value `"stars"` to use the
five star rating system. You can optionally replace the star ratings with a
thumbs-up or thumbs-down rating system by changing this value to
`"thumbs"`.

If you haven't already connected your portlet's view to the JSP for your entity,
you can refer [here](/develop/tutorials/-/knowledge_base/6-2/relating-assets#creating-a-url-to-your-new-jsp)
to see how to connect your portlet's main view JSP to your entity's view JSP.

Great! Now you have the JSP that lets your users rate content.

Now redeploy your portlet and refresh the page so that the your plugin's UI
reloads. The ratings UI component now appears in your entity's view. 

Great! Now you know how to add ratings for content in your asset enabled 
portlets. 

Another thing you might want to do is perform permissions checks to make sure
only the proper users can rate content. For example, the Add Insult and 
Permissions buttons of the Insults portlet are wrapped in a permissions check in 
its [`view.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view.jsp).
For more information on this, see the learning path [Checking Permissions in the UI](/develop/learning-paths/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).

**Related Topics**

[Asset Enabling Custom Entities](/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)

[Implementing Asset Renderers](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)
