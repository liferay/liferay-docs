# Relating Assets [](id=relating-assets)

Relating assets connects individual pieces of content across your site or
portal. This helps users discover related content, particularly when there's an
abundance of other available content. For example, assets related to a web
content article appear alongside that entry in the Asset Publisher application.

![Figure 1: You and your users can find it helpful to relate assets to entities, such as this blogs entry.](../../images/asset-related-content-asset-publisher.png)

Now you'll learn how to provide a way for authors to relate content.
This tutorial assumes that you've
[asset enabled](/develop/tutorials/-/knowledge_base/7-1/adding-updating-and-deleting-assets-for-custom-entities)
your application. If you've already done this, go ahead and begin relating your
assets!

## Relating Assets in the Service Layer [](id=relating-assets-in-the-service-layer)

First, you must make some modifications to your portlet's service layer. You
must implement persisting your entity's asset relationships. 

1.  In your portlet's `service.xml`, put the following line of code below any
    finder method elements and then
    [run](/develop/tutorials/-/knowledge_base/7-1/running-service-builder)
    Service Builder:

        <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

2.  Modify the `add-`, `delete-`, and `update-` methods in your
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

    To call the `updateLinks` method, you must pass in the current user's ID, the
    asset entry's ID, the asset link entries' IDs, and the link type. Invoke
    this method after creating the asset entry. If you assign to an
    `AssetEntry` variable (e.g., one called `assetEntry`) the value returned
    from invoking `assetEntryLocalService.updateEntry`, you can get the asset
    entry's ID for updating its asset links. Lastly, in order to specify the
    link type parameter, make sure to import
    `com.liferay.portlet.asset.model.AssetLinkConstants`. 

3.  In your `-LocalServiceImpl` class' `delete-` method, you must delete the
    asset's relationships before deleting the asset. For example, you could
    delete your existing asset link relationships by using the following code:

        AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
            ENTITY.class.getName(), ENTITYId);

        assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

Make sure to replace the *ENTITY* place holders for your custom `-delete`
method.

Super! Now your portlet's service layer can handle related assets. Even so,
there's still nothing in your portlet's UI that lets your users relate assets.
You'll take care of that in the next step.

## Relating Assets in the UI [](id=relating-assets-in-the-ui)

The UI for linking assets should be in the JSP where users create and edit your
entity. This way only content creators can relate other assets to the entity.
Related assets are implemented in the JSP by using the Liferay UI tag
`liferay-ui:input-asset-links` inside a collapsible panel. This code is
placed inside the `aui:fieldset` tags of the JSP. 

1.  Add the `liferay-ui:input-asset-links` tag to your form. Here's how it's
    added in the Blogs application: 

        <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="related-assets">
            <liferay-ui:input-asset-links
                className="<%= BlogsEntry.class.getName() %>"
                classPK="<%= entryId %>"
            />

    The following screenshot shows the Related Assets menu for an application. Note
    that it is contained in a collapsible panel titled Related Assets.

    ![Figure 2: Your portlet's entity is now available in the Related Assets *Select* menu.](../../images/related-assets-select-menu.png)

2.  Unfortunately, the Related Assets menu shows your entity's fully qualified
    class name. To replace it with a simplified name for your entity, add
    a language key with the fully qualified class name for the key
    and the name you want for the value. Put the language key in file
    `docroot/WEB-INF/src/content/Language.properties` in your portlet. You can
    refer to the 
    [Overriding Language Keys](/develop/tutorials/-/knowledge_base/7-1/overriding-language-keys)
    tutorial for more documentation on using language properties.

    Upon redeploying your portlet, the value you assigned to the fully qualified
    class name in your `Language.properties` file shows in the Related Assets menu. 

Awesome! Now content creators and editors can relate the assets of your
application. The next thing you need to do is reveal any such related assets to
the rest of your application's users. After all, you don't want to give everyone
edit access just so they can view related assets!

## Showing Related Assets [](id=showing-related-assets)

You can show related assets in your application's view of that entity or, if
you've implemented asset rendering for your custom entity, you can show related
assets in the full content view of your entity for users to view in an Asset
Publisher portlet.

1.  You must get the `AssetEntry` object associated with your entity: 

        <%
        long insultId = ParamUtil.getLong(renderRequest, "insultId");
        Insult ins = InsultLocalServiceUtil.getInsult(insultId);
        AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(Insult.class.getName(), ins.getInsultId());
        %>

2.  Use the `liferay-ui:asset-links` tag to show the entity's related assets.
    For this tag, you should retrieve the entity's class name and the variable
    holding your instance object, so you can return its ID. The example code
    below uses the example entity class `Insult` and an instance object
    variable called `ins`:

        <liferay-ui:asset-links
            assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
            className="<%=Insult.class.getName()%>"
            classPK="<%=ins.getInsultId()%>" />

Great! Now you have the JSP that lets your users view related assets. Related
assets, if you've created any yet, should be visible near the bottom of the
page.

Excellent! Now you know how to implement related assets in your apps.

## Related Topics [](id=related-topics)

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-1/adding-updating-and-deleting-assets-for-custom-entities)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-1/what-is-service-builder)

[Defining Content Relationships](/discover/portal/-/knowledge_base/7-1/defining-content-relationships)
