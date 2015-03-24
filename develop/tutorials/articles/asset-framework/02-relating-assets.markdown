# Relating Assets [](id=relating-assets)

<!--
Testing Notes:

The starting example portlet for this tutorial is at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-02-asset-enable-insults-portlet

On completing this tutorial, the example portlet looks like the portlet at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-03-end-insults-portlet

Make sure to read their README files. - Jim
-->

The ability to relate assets is one of the most powerful features of Liferay's 
asset framework. By relating assets, you can connect individual pieces of content 
across your site or portal. This helps your users discover related content, 
particularly when there's an abundance of other available content. For example, 
assets related to a Blog entry appear alongside that entry in the Blogs portlet.

![Figure 1: You and your users can find it helpful to relate assets to entities, such as this blogs entry.](../../images/asset-fw-related-blog-insults.png)

This tutorial shows you how to provide a way for authors to relate content.
This tutorial assumes that you've [asset enabled](/develop/tutorials/-/knowledge_base/6-2/adding-updating-and-deleting-assets-for-custom-entities)
your portlet. A custom Insults portlet is used as an example. After all, users
of such a portlet would want to relate their insults to all kinds of content!
The completed Insults portlet code that uses this feature is on [Github](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet). 
Now go ahead and get started relating your assets!

## Relating Assets in the Service Layer [](id=relating-assets-in-the-service-layer)

First, you must make some modifications to your portlet's service layer. You
must implement persisting your entity's asset relationships. In your portlet's
`service.xml`, put the following line of code below any finder method elements
and then [run](/develop/tutorials/-/knowledge_base/6-2/running-service-builder-and-understanding-the-generated-code)
Service Builder:

    <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

Next, you need to modify the `add-`, `delete-`, and `update-` methods in your
`-LocalServiceImpl` to persist the asset relationships. You'll use your
`-LocalServiceImpl`'s `assetLinkLocalService` instance variable to execute
persistence actions. When you add and update assets, you must invoke the `addInsult`
and `updateInsult` methods of `InsultLocalServiceImpl` both utilize the
`updateLinks` via your instance variable `assetLinkLocalService`. Here's the
`updateLinks` invocation in the example Insults portlet's
[`-LocalServiceImpl`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/WEB-INF/src/com/liferay/docs/insult/service/impl/InsultLocalServiceImpl.java):

    assetLinkLocalService.updateLinks(
        userId, assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
        AssetLinkConstants.TYPE_RELATED);

To call the `updateLinks` method, you need to pass in the current user's ID, the
asset entry's ID, the ID's of the asset link entries, and the link type. You
should invoke this method after creating the asset entry. You can assign to an
`AssetEntry` variable (e.g., one called `assetEntry`) the value returned from
invoking `assetEntryLocalService.updateEntry`. That way you can get the asset
entry's ID for updating its asset links. Lastly, in order to specify the link
type parameter, make sure to import
`com.liferay.portlet.asset.model.AssetLinkConstants`. 

In your `-LocalServiceImpl` class' `delete-` method, you must delete the asset's
relationships before deleting the asset. For example, the Insults portlet
deletes the asset link relationships using the following code:

    AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
        Insult.class.getName(), insultId);

    assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

Your `delete-` method can look similar, except it requires your entity's class
name and your entity's ID, instead of the `Insult` class and its ID variable
`insultId` that are used in the above code.

Super! Now your portlet's service layer can handle related assets. Even so,
there's still nothing in your portlet's UI that lets your users to relate
assets. You'll take care of that in the next step.

## Relating Assets in the UI [](id=relating-assets-in-the-ui)

You typically implement the UI for linking assets in the JSP that you provide
users the ability to create and edit your entity, This way only content creators
can relate other assets to the entity. In the Insults portlet, for example,
assets can only be related from its [`edit_insult.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/edit_insult.jsp).
Related assets are implemented in the JSP by using the Liferay UI tag
`liferay-ui:input-asset-links` inside of a collapsible panel. This code is
placed inside the `aui:fieldset` tags of the JSP. The panel and
`liferay-ui:input-asset-links` tag from the example Insults portlet is shown
here:

    <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="insultAssetLinksPanel" persistState="<%= true %>" title="related-assets">
        <aui:fieldset>
            <liferay-ui:input-asset-links
                className="<%= Insult.class.getName() %>"
                classPK="<%= insultId %>"
            />
        </aui:fieldset>
    </liferay-ui:panel>

You can use similar code, replacing the `Insult` and `insultId` references with
your portlet's entity class and entity ID variable. Your content authors are
able to relate assets once you add this code and redeploy your portlet.

The following screenshot shows the Related Assets menu of the Insults 
portlet. Note that it is contained in a collapsible panel titled Related Assets. 

![Figure 2: Your portlet's entity is now shown in the Related Assets menu.](../../images/asset-fw-related-path-01.png)

Even though you've provided a way for authors to assign related assets, the
Related Assets menu shows your entity's fully qualified class name, instead of a
more concise name. To replace the long fully qualified class name shown in the
menu with a simplified name for your entity, add a language key that uses the
fully qualified class name as the key's name and the new simplified name as the
key's value. Put the language key in file
`docroot/WEB-INF/src/content/Language.properties` in your portlet. You can refer
to a tutorial [here](/develop/tutorials/-/knowledge_base/6-2/generating-language-properties-file-and-automated-t#implementing-translations-in-your-portlet)
for more documentation on using language properties.

Here's a language key to simplify the label for the `Insult` entity class used
in the example:

    model.resource.com.liferay.docs.insult.model.Insult=Insult

Upon redeploying your portlet, the value you assigned to the fully qualified
class name in your `Language.properties` file shows in the Related Assets menu:

![Figure 3: After deploying with your language key, your entity appears as desired in the Related Assets menu.](../../images/asset-fw-related-select.png)

Awesome! Now content creators and editors can relate the assets of your portlet. 
The next thing you need to do is reveal any such related assets to the rest of 
your portlet's users. After all, you don't want to give everyone edit access 
just so they can view related assets!

## Showing Related Assets [](id=showing-related-assets)

You can show related assets in your portlet's view of that entity or, if you've
implemented [asset rendering](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)
for your custom entity, you can show related assets in the full content view of
your entity for users to view in an Asset Publisher portlet. 

As an example, the Insult portlet's view JSP file
[`view_insult.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view_insult.jsp)
shows an insult entity and links to all of its related assets. This section
shows you how to access an entity's asset entry in your entity's view JSP and
how to display links to its related assets. When you finish, users can click on
the entity instances in your portlet to view any related assets.

In your entity's view JSP you can use `ParamUtil` to get the ID of the entity
from the render request. Then you can create an entity object using your
`-LocalServiceUtil` class. You can use an entity instance object to get the
`AssetEntry` object associated with it. 

    <%
    long insultId = ParamUtil.getLong(renderRequest, "insultId");
    Insult ins = InsultLocalServiceUtil.getInsult(insultId);
    AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(Insult.class.getName(), ins.getInsultId());
    %>

To show the entity's related assets you use the `liferay-ui:asset-links` tag.
You use in your entity's class to get it's class name and the variable holding
your instance object to return the its ID. The example code below uses the
example entity class `Insult` and an instance object variable called `ins`:

    <liferay-ui:asset-links
        assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
        className="<%=Insult.class.getName()%>"
        classPK="<%=ins.getInsultId()%>" />

Go ahead and use a the `liferay-ui:asset-links` tag in your JSP. Great! Now you
have the JSP that lets your users view related assets. 

If you've already connected your portlet's view to the view JSP for your entity,
you've completed the tutorial. You can otherwise follow the remainder of this
tutorial to learn how to implement that connection. 

## Creating a URL to Your New JSP [](id=creating-a-url-to-your-new-jsp)

Now that you've implemented showing off this asset feature, you must connect
your portlet's main view JSP to your entity's view JSP. If your main view JSP
uses a search container to list your entity instances, you can insert a
`portlet:renderURL` tag just after the `liferay-ui:search-container-row` tag.
For example, in the Insults portlet's
[`view.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/view.jsp)
it looks like this:

    <liferay-ui:search-container-row
        className="com.sample.portlet.insults.model.Insult"
        keyProperty="insultId"
        modelVar="insult" escapedModel="<%= true %>"
    >
    
    <portlet:renderURL windowState="maximized" var="rowURL">
        <portlet:param name="mvcPath" value="/html/insult/view_insult.jsp" />
        <portlet:param name="insultId" value="<%= String.valueOf(insult.getInsultId()) %>" />
    </portlet:renderURL>

Next, add to the first search container column  an `href` attribute with the 
value of the URL you just created in the `portlet:renderURL` tag. For example, 
the value of `href` that corresponds with the render URL created above is
`"<%=rowURL %>"`. Your `search-container-column-text` tag can look similar to
this tag:

    <liferay-ui:search-container-column-text
        name="Insult"
        value="<%= insult.getInsultString() %>"
        href="<%=rowURL %>"
    />

Now, redeploy your portlet and refresh the page so that your portlet's view JSP
reloads. Each entity listed is a link. Click on one to view the your entity's
JSP that you made in the previous step of this tutorial.

Related assets, if you've created any yet, should be visible near the bottom of
the page.

![Figure 4: Any related assets now show in the new JSP you created.](../../images/asset-fw-related-insult.png)

Most excellent! Now you know how to implement related assets in your portlets.
Another thing you might want to do is investigate permissioning in the UI. For
example, the Insults portlet only allows assets to be related by those with
`ADD_INSULT` or `UPDATE` permissions. These permissions are checked in the
Insults portlet's `view.jsp` and `insult_actions.jsp`, respectively. For more
information on this, see the learning path [Checking Permissions in the UI](/develop/learning-paths/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).

## Related Topics [](id=related-topics)

[Asset Enabling Custom Entities](/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)

[Implementing Asset Renderers](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)
