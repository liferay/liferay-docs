# Relating Assets [](id=relating-assets)

The ability to relate assets is one of the most powerful features of Liferay's
asset framework. By relating assets, you can connect individual pieces of
content across your site or portal. This helps your users discover related
content, particularly when there's an abundance of other available content. For
example, assets related to a web content article appear alongside that entry in
the Asset Publisher application.

![Figure 1: You and your users can find it helpful to relate assets to entities, such as this blogs entry.](../../images/asset-related-content-asset-publisher.png)

This tutorial shows you how to provide a way for authors to relate content.
This tutorial assumes that you've
[asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)
your appliation. If you've already done this, go ahead and begin relating your
assets!

## Relating Assets in the Service Layer [](id=relating-assets-in-the-service-layer)

First, you must make some modifications to your portlet's service layer. You
must implement persisting your entity's asset relationships. In your portlet's
`service.xml`, put the following line of code below any finder method elements
and then [run](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
Service Builder:

    <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

Next, you need to modify the `add-`, `delete-`, and `update-` methods in your
`-LocalServiceImpl` to persist the asset relationships. You'll use your
`-LocalServiceImpl`'s `assetLinkLocalService` instance variable to execute
persistence actions. 

For example, consider the Wiki application. When you update wiki assets and
statuses, both methods utilize the `updateLinks` via your instance variable
`assetLinkLocalService`. Here's the `updateLinks` invocation in the Wiki
application's `WikiPageLocalServiceImpl.updateStatus(...)` method:

    assetLinkLocalService.updateLinks(
        userId, assetEntry.getEntryId(), assetLinkEntryIds,
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
relationships before deleting the asset. For example, you could delete your
existing asset link relationships by using the following code:

    AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
        ENTITY.class.getName(), ENTITYId);

    assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

Make sure to replace the *ENTITY* place holders for your custom `-delete`
method.

Super! Now your portlet's service layer can handle related assets. Even so,
there's still nothing in your portlet's UI that lets your users relate assets.
You'll take care of that in the next step.

## Relating Assets in the UI [](id=relating-assets-in-the-ui)

You typically implement the UI for linking assets in the JSP that you provide
users the ability to create and edit your entity, This way only content creators
can relate other assets to the entity. Related assets are implemented in the JSP
by using the Liferay UI tag `liferay-ui:input-asset-links` inside of a
collapsible panel. This code is placed inside the `aui:fieldset` tags of the
JSP. The panel and `liferay-ui:input-asset-links` tag are shown below for the
Blogs application:

    <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="related-assets">
        <liferay-ui:input-asset-links
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entryId %>"
        />

Your content authors are able to relate assets once you add this code and
redeploy your portlet.

The following screenshot shows the Related Assets menu for an appliation. Note
that it is contained in a collapsible panel titled Related Assets.

![Figure 2: Your portlet's entity is now available in the Related Assets *Select* menu.](../../images/related-assets-select-menu.png)

Even though you've provided a way for authors to assign related assets, the
Related Assets menu shows your entity's fully qualified class name, instead of a
more concise name. To replace the long fully qualified class name shown in the
menu with a simplified name for your entity, add a language key that uses the
fully qualified class name as the key's name and the new simplified name as the
key's value. Put the language key in file
`docroot/WEB-INF/src/content/Language.properties` in your portlet. You can refer
to the
[Modifying Liferay's Language Keys](/develop/tutorials/-/knowledge_base/7-0/modifying-liferays-language-keys)
tutorial for more documentation on using language properties.

Upon redeploying your portlet, the value you assigned to the fully qualified
class name in your `Language.properties` file shows in the Related Assets menu:

Awesome! Now content creators and editors can relate the assets of your
application. The next thing you need to do is reveal any such related assets to
the rest of your application's users. After all, you don't want to give everyone
edit access just so they can view related assets!

## Showing Related Assets [](id=showing-related-assets)

You can show related assets in your application's view of that entity or, if
you've implemented asset rendering for your custom entity, you can show related
assets in the full content view of your entity for users to view in an Asset
Publisher portlet.

<!-- Link to asset rendering above, when the tutorial is available. -Cody -->

This section shows you how to access an entity's asset entry in your entity's
view JSP and how to display links to its related assets. When you finish, users
can click on the entity instances in your portlet to view any related assets.

In your entity's view JSP you can use `ParamUtil` to get the ID of the entity
from the render request. Then you can create an entity object using your
`-LocalServiceUtil` class. You can use an entity instance object to get the
`AssetEntry` object associated with it. 

    <%
    long insultId = ParamUtil.getLong(renderRequest, "insultId");
    Insult ins = InsultLocalServiceUtil.getInsult(insultId);
    AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(Insult.class.getName(), ins.getInsultId());
    %>

To show the entity's related assets, you can use the `liferay-ui:asset-links`
tag. For this tag, you should retrieve the entity's class name and the variable
holding your instance object, so you can return its ID. The example code below
uses the example entity class `Insult` and an instance object variable called
`ins`:

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
your application's main view JSP to your entity's view JSP. If your main view JSP
uses a search container to list your entity instances, you can insert a
`portlet:renderURL` tag just after the `liferay-ui:search-container-row` tag.
For example, your `view.jsp` could look like this:

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
reloads. Each entity listed is a link. Click on one to view your entity's JSP
that you made in the previous step of this tutorial.

Related assets, if you've created any yet, should be visible near the bottom of
the page.

Excellent! Now you know how to implement related assets in your apps.

<!-- Another thing you might want to do is investigate permissioning in the UI. For
moreinformation on this, see the learning path [Checking Permissions in the UI](/develop/learning-paths/mvc/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).
-->

<!-- Add above text back when "Checking Perimssions in the UI" article is
available. -Cody -->

## Related Topics [](id=related-topics)

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)

[Defining Content Relationships](/discover/portal/-/knowledge_base/7-0/defining-content-relationships)
