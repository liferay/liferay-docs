# Adding, Updating, and Deleting Assets for Custom Entities [](id=adding-updating-and-deleting-assets-for-custom-entities)

To use Liferay's asset framework with an entity, you must inform the 
asset framework about each entity instance you create, modify, and delete. In
this sense, it's similar to informing Liferay's permissions framework about a
new resource. All you have to do is invoke a method of the asset framework that
associates an `AssetEntry` with the entity so Liferay can keep track of
the entity as an asset. When it's time to update the entity, you update the
asset at the same time. 
<!--To see how to asset-enable entities in a working example
portlet, visit the Learning
Path [Asset Enabling Custom Entities](/develop/learning-paths/mvc/-/knowledge_base/6-2/asset-enabling-custom-entities).
-->

To leverage assets, you must also implement indexers for your portlet's
entities. Liferay's asset framework uses indexers to manage assets. 
<!--  For
instructions on
creating an indexer in a working example portlet, see the learning path [Enabling Search and Indexing](/develop/learning-paths/mvc/-/knowledge_base/6-2/enabling-search-and-indexing).
-->

This tutorial shows you how to enable assets for your custom entities and
implement indexes for them. It's time to get started! 

## Preparing Your Project for the Asset Framework [](id=preparing-your-project-for-the-asset-framework)

In your project's `service.xml` file, add an asset entry entity reference for
your custom entity. Add the following `reference` tag before your custom
entity's closing `</entity>` tag.

    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />

Then [run](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
Service Builder.

Now you're ready to implement adding and updating assets!

## Adding and Updating Assets [](id=adding-and-updating-assets)

Your `-LocalServiceImpl` Java class inherits from its parent base class an
`AssetEntryLocalService` instance; it's assigned to the variable 
`assetEntryLocalService`. To add your custom entity
as a Liferay asset, you must invoke the `assetEntryLocalService`'s
`updateEntry` method. 

Here's what the [`updateEntry`](@platform-ref@/7.0-latest/javadocs/portal-impl/com/liferay/portlet/asset/service/impl/AssetEntryLocalServiceImpl.html#updateEntry-long-long-java.util.Date-java.util.Date-java.lang.String-long-java.lang.String-long-long:A-java.lang.String:A-boolean-boolean-java.util.Date-java.util.Date-java.util.Date-java.util.Date-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-int-int-java.lang.Double-)
method's signature looks like:

    AssetEntry updateEntry(
        long userId, long groupId, Date createDate, Date modifiedDate,
        String className, long classPK, String classUuid, long classTypeId,
        long[] categoryIds, String[] tagNames, boolean listable,
        boolean visible, Date startDate, Date endDate, Date publishDate,
        Date expirationDate, String mimeType, String title,
        String description, String summary, String url, String layoutUuid,
        int height, int width, Double priority)
	throws PortalException

Here are descriptions of each of the `updateEntry` method's parameters: 

-   `userId`: identifies the user updating the content. 
-   `groupId`: identifies the scope of the created content. If your content
    doesn't support scopes (extremely rare), pass `0` as the value. 
-   `createDate`: the date the entity was created.
-   `modifiedDate`: the date of this change to the entity.
-   `className`: identifies the entity's class. The recommended convention
    is to use the name of the Java class that represents your content type. For
    example, you can pass in the value returned from
    `[YourClassName].class.getName()`. 
-   `classPK`: identifies the specific entity instance, distinguishing it
    from other instances of the same type. It's usually the primary key of the
    table where the entity is stored.
-   `classUuid`: serves as a secondary identifier that's guaranteed  to
    be universally unique. It correlates entity instances across scopes. It's
    especially useful if your content is exported and imported across separate
    portals. 
-   `classTypeId`: identifies the particular variation of this class, if it has
    any variations. Otherwise, use `0`. 
-   `categoryIds`: represent the categories selected for the entity.
    The asset framework stores them for you. 
-   `tagNames`: represent the tags selected for the entity.
    The asset framework stores them for you.
-   `listable`: specifies whether the entity can be shown in dynamic lists of 
     content (such as asset publisher configured dynamically). 
-   `visible`: specifies whether the entity is approved. 
-   `startDate`: the entity's publish date. You can use it to specify when an
     Asset Publisher should show the entity's content.
-   `endDate`: the date the entity is taken down. You can use it to specify
     when an Asset Publisher should stop showing the entity's content.
-   `publishDate`: the date the entity will start to be shown. 
-   `expirationDate`: the date the entity will no longer be shown. 
-   `mimetype`: the Multi-Purpose Internet Mail Extensions type, such as
    [ContentTypes.TEXT_HTML](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html#TEXT_HTML),
    used for the content.
-   `title`: the entity's name.
-   `description`: a `String`-based textual description of the entity.
-   `summary`: a shortened or truncated sample of the entity's content. 
-   `url`: a URL to optionally associate with the entity. 
-   `layoutUuid`: the universally unique ID of the layout of the entry's
    default display page.
-   `height`: this can be set to `0`.
-   `width`: this can be set to `0`.
-   `priority`: specifies how the entity is ranked among peer entity instances.
    Low numbers take priority over higher numbers.

The following code from Liferay's Wiki application's
[WikiPageLocalServiceImpl](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/wiki/wiki-service/src/main/java/com/liferay/wiki/service/impl/WikiPageLocalServiceImpl.java)
Java class demonstrates invoking the `updateEntry` method on the wiki page
entity called `WikiPage`. In your `add-` method, you could invoke `updateEntry`
after adding your entity's resources. Likewise, in your `update-` method, you
could invoke `updateEntry` after calling the `super.update-` method. The code
below is called in the `WikiPageLocalServiceImpl` class's `updateStatus(...)`
method.

    AssetEntry assetEntry = assetEntryLocalService.updateEntry(
        userId, page.getGroupId(), page.getCreateDate(),
        page.getModifiedDate(), WikiPage.class.getName(),
        page.getResourcePrimKey(), page.getUuid(), 0,
        assetCategoryIds, assetTagNames, true, true, null, null,
        page.getCreateDate(), null, ContentTypes.TEXT_HTML,
        page.getTitle(), null, null, null, null, 0, 0, null);

    Indexer<JournalArticle> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
        WikiPage.class);

    indexer.reindex(page);

Immediately after invoking the `updateEntry` method, you must update the
respective asset and index the entity instance. The above code calls the indexer
to index (or re-index, if updating) the entity. It's that easy to update assets
and indexes.

+$$$

**Tip:** The current user's ID and the scope group ID are commonly made
available in service context parameters. If the service context you use contains
them, then you can access them in calls like these:

	long userId = serviceContext.getUserId();
	long groupId = serviceContext.getScopeGroupId();

$$$

Next, you'll learn what's needed to properly delete an entity that's associated
with an asset. 

## Deleting Assets [](id=deleting-assets)

When deleting your entities, you should delete the associated assets and indexes
at the same time. This cleans up stored asset and index information, which keeps
the Asset Publisher from showing information for the entities you've deleted.

In your `-LocalServiceImpl` Java class, open your `delete-` method. After the
code that deletes the entity's resource, you must delete the entity instance's
asset entry and index. 

Here's some code which deletes an asset entry and an index associated with a
portlet's entity. 

    assetEntryLocalService.deleteEntry(
        ENTITY.class.getName(), ENTITY.getInsultId());

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ENTITY.class);
    indexer.delete(ENTITY);

In your `-LocalServiceImpl` class, you can write similar code. Replace the
*ENTITY* class name and variable with your entity's name.

+$$$

**Important:** In order for Liferay's Asset Publisher application to show your
entity, the entity must have an Asset Renderer. 
<!-- To learn how to implement
an Asset Renderer for your custom entity, refer to Learning Path
[Implementing Asset Renderers](/develop/learning-paths/mvc/-/knowledge_base/6-2/implementing-asset-renderers).-->
Note also that an Asset Renderer is how you show a user the components of your
entity in the Asset Publisher. On deploying your portlet with asset, indexer,
and asset rendering implementations in place, an Asset Publisher can show your
custom entities!

$$$

![Figure 1: It can be useful to show custom entities, like this wiki page entity, in a JSP or in an Asset Publisher.](../../images/basic-asset-in-asset-publisher.png)

Great! Now you know how to add, update, and delete assets in your apps!

## Related Topics [](id=related-topics)

[Relating Assets](/develop/tutorials/-/knowledge_base/7-0/relating-assets)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
