# Adding, Updating, and Deleting Assets 

To leverage Liferay asset features with an entity, you must inform Liferay's
asset framework about each entity instance you create, modify, and delete. In
this sense, it's similar to informing Liferay's permissions framework about a
new resource. It's a simple procedure: you invoke a method of the asset
framework that adds an `AssetEntry` associated with the entity instance, so that
Liferay can keep track of the instance as an asset. You similarly, update the
asset as you update the entity. To see how to asset-enable entities in a working example portlet, visit learning
path [Asset Enabling Custom Entities](/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities).

To leverage assets, you must implement indexers for your portlet's entities.
Liferay's asset framework uses indexers to manage assets. For instructions on
creating an indexer in a working example portlet, see the learning path [Enabling Search and Indexing](/learning-paths/-/knowledge_base/6-2/enabling-search-and-indexing).
This tutorial also shows you how to implement indexes that are required for
asset-enabling your entities. 

It's time to associate assets with your portlet's entities. 

## Preparing Your Project for the Asset Framework

In your project's `service.xml` file, you must add an asset entry entity for
your custom entity. Add the following `reference` tag before the closing
`</entity>` tag for your custom entity.

    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />

Then run Service Builder.

Now you're ready to learn about adding and updating assets!

## Adding and Updating Assets 

Your `-LocalServiceImpl` Java class inherits from its parent base class an
`AssetEntryLocalService` instance; it's assigned to field `assetEntryLocalService`.
Service Builder creates this field in the `-LocalServiceBaseImpl` base class that
it generates. To add your custom entity as a Liferay asset, you must invoke the
`assetEntryLocalService` field's `updateEntry` method.

Here's what the [`updateEntry`](http://docs.liferay.com/portal/6.2/javadocs-all/com/liferay/portlet/asset/service/impl/AssetEntryLocalServiceImpl.html)
method's signature looks like:

    AssetEntry updateEntry(
		long userId, long groupId, Date createDate, Date modifiedDate,
		String className, long classPK, String classUuid, long classTypeId,
		long[] categoryIds, String[] tagNames, boolean visible,
		Date startDate, Date endDate, Date expirationDate, String mimeType,
		String title, String description, String summary, String url,
		String layoutUuid, int height, int width, Integer priority,
		boolean sync)
	throws PortalException, SystemException

Here are descriptions of each of the `updateEntry` method's parameters: 

-   `userId` - identifies the user updating the content. 
-   `groupId` - identifies the scope of the created content. If your content
    doesn't support scopes (extremely rare), pass `0` as the value. 
-   `createDate` - the date the entity was created.
-   `modifiedDate` - the date of this change to the entity.
-   `className` - identifies the entity's class. The recommended convention
    is to use the name of the Java class that represents your content type. For
    example, you can pass in the value returned from
    `[YourClassName].class.getName()`. 
-   `classPK` - identifies the specific entity instance, distinguishing it
    from other instances of the same type. It's usually the primary key of the
    table where the entity is stored.
-   `classUuid` - serves as a secondary identifier that's guaranteed  to
    be universally unique. It correlates entity instances accross scopes. It's
    especially useful if your content is exported and imported across separate
    portals. 
-   `classTypeId` - identifies the particular variation of this class, if it has
    any variations. Otherwise, use `0`. 
-   `categoryIds` - represent the categories selected for the entity.
    The asset framework stores them for you. 
-   `assetTagNames` - represent the tags selected for the entity.
    The asset framework stores them for you.
-   `visible` - specifies whether the entity is approved. 
-   `startDate` - the entity's publish date. You can use it to specify when an
     Asset Publisher should show the entity's content.
-   `endDate` - the date the entity is taken down. You can use it to specify
     when an Asset Publisher should stop showing the entity's content.
-   `expirationDate` - the date the entity will no longer be shown. 
-   `mimetype` - the Multi-Purpose Internet Mail Extensions type, such as [ContentTypes.TEXT_HTML](http://docs.liferay.com/portal/6.2/javadocs-all/com/liferay/portal/kernel/util/ContentTypes.html#TEXT_HTML),
    used for the content.
-   `title` - the entity's name.
-   `description` - provides a complete description of the entity.
-   `summary` - provides an overview of the entity's description. 
-   `url` - a URL to optionally associate with the entity. 
-   `layoutUuid` - the universally unique ID of the layout of the entry's
    default display page.
-   `priority` - specifies how the entity is ranked among peer entity instances.
    Low integers take priority over higher integers.

<!-- We need get definitions for these params. Jim
-   `height` - 
-   `width` - 
-   `sync` - 
-->

The following example code demonstrates invoking this method on an example
entity called `Insult`. To help show the values assigned to some of the
parameters, they're declared in variables before the invocation.

    long classTypeId = 0;
    boolean visible = true;
    Date startDate = null;
    Date endDate = null;
    Date expirationDate = null;
    String mimeType = ContentTypes.TEXT_HTML;
    String title = insult.getInsultString();
    String description = insult.getInsultString();
    String summary = insult.getInsultString();
    String url = null;
    String layoutUuid = null;
    int height = 0;
    int width = 0;
    Integer priority = null;
    boolean sync = false;

    assetEntryLocalService.updateEntry(
        userId, groupId, insult.getCreateDate(),
        insult.getModifiedDate(), Insult.class.getName(),
        insult.getInsultId(), insult.getUuid(), classTypeId,
        serviceContext.getAssetCategoryIds(),
        serviceContext.getAssetTagNames(), visible, startDate, endDate,
        expirationDate, mimeType, title, description, summary, url,
        layoutUuid, height, width, priority, sync);

Immediately after invoking the `updateEntry` method, you must update the
respective asset and index the entity instance. For example, here are calls to
the indexer that an example insults portlet makes when adding and updating its
insult entities:

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Insult.class);
    indexer.reindex(insult);

As you can see, you get an indexer for your entity's class and re-index the
entity instance. It's that easy to to update assets and indexes when adding and
updating entities.

Next, you'll learn what's needed to properly delete an entity that's associated
with an asset. 

## Deleting Assets 

On deleting your custom entities, you should similarly delete the assets and
indexes associated with the entities. This cleans up stored information and
makes sure that the Asset Publisher doesn't show any information for the
entities you've deleted.

After deleting an entity's resource in your `delete-` method, delete the entity
instance's asset entry and delete the entity instance's index. 

Here's example code for deleting an asset entry and deleting an index associated
with an example insult portlet's `Insult` entity. 

    assetEntryLocalService.deleteEntry(
        Insult.class.getName(), insult.getInsultId());

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Insult.class);
    indexer.delete(insult);

In your `-LocalServiceImpl` class, you can write similar code. Instead of using
the class name `Insult` however, you use your entity's class name, and instead
of using a variable named `insult` use a variable that holds your entity's
instance. 

On deploying your portlet with all of these asset and indexer implementations in
place, an Asset Publisher can show your custom entities! 

Great! Now you know how to add, update, and delete assets in your portlets!

## Related Topics

[Relating Assets](/tutorials/-/knowledge_base/6-2/relating-assets)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)
