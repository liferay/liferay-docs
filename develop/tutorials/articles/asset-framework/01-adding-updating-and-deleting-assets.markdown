# Adding, Updating, and Deleting Assets for Custom Entities [](id=adding-updating-and-deleting-assets-for-custom-entities)

<!--
Testing Notes:

The starting example portlet for this tutorial is at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-01-begin-insults-portlet

On completing this tutorial, the example portlet looks like the portlet at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-02-asset-enable-insults-portlet

Make sure to read their README files. - Jim
-->

To use Liferay's asset framework with an entity, you must inform the 
asset framework about each entity instance you create, modify, and delete. In
this sense, it's similar to informing Liferay's permissions framework about a
new resource. All you have to do is invoke a method of the asset framework that
associates an `AssetEntry` with the entity so Liferay can keep track of
the entity as an asset. When it's time to update the entity, you update the
asset at the same time. To see how to asset-enable entities in a working example
portlet, visit the Learning
Path [Asset Enabling Custom Entities](/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities).

To leverage assets, you must also implement indexers for your portlet's
entities. Liferay's asset framework uses indexers to manage assets. For
instructions on
creating an indexer in a working example portlet, see the learning path [Enabling Search and Indexing](/develop/learning-paths/-/knowledge_base/6-2/enabling-search-and-indexing).

This tutorial shows you how to enable assets for your custom entities and
implement indexes for them. It's time to get started! 

## Preparing Your Project for the Asset Framework [](id=preparing-your-project-for-the-asset-framework)

In your project's `service.xml` file, add an asset entry entity reference for
your custom entity. Add the following `reference` tag before your custom
entity's closing `</entity>` tag.

    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />

Then [run](/develop/tutorials/-/knowledge_base/6-2/running-service-builder-and-understanding-the-generated-code)
Service Builder.

Now you're ready to implement adding and updating assets!

## Adding and Updating Assets [](id=adding-and-updating-assets)

Your `-LocalServiceImpl` Java class inherits from its parent base class an
`AssetEntryLocalService` instance; it's assigned to the variable 
`assetEntryLocalService`. To add your custom entity
as a Liferay asset, you must invoke the `assetEntryLocalService`'s
`updateEntry` method. 

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
-   `assetTagNames`: represent the tags selected for the entity.
    The asset framework stores them for you.
-   `visible`: specifies whether the entity is approved. 
-   `startDate`: the entity's publish date. You can use it to specify when an
     Asset Publisher should show the entity's content.
-   `endDate`: the date the entity is taken down. You can use it to specify
     when an Asset Publisher should stop showing the entity's content.
-   `expirationDate`: the date the entity will no longer be shown. 
-   `mimetype`: the Multi-Purpose Internet Mail Extensions type, such as [ContentTypes.TEXT_HTML](http://docs.liferay.com/portal/6.2/javadocs-all/com/liferay/portal/kernel/util/ContentTypes.html#TEXT_HTML),
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
    Low integers take priority over higher integers.
-   `sync`: this can be set to `false`.

The following code from an example portlet's [`-LocalServiceImpl`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-02-asset-enable-insults-portlet/docroot/WEB-INF/src/com/liferay/docs/insult/service/impl/InsultLocalServiceImpl.java)
Java class demonstrates invoking the `updateEntry` method on an example entity
called `Insult`. In your `add-` method, you invoke `updateEntry` after adding
your entity's resources. In your `update-` method, you invoke `updateEntry` after
calling the `super.update-` method. To help show the values assigned to some of
the parameters, they're declared in local variables before the invocation.

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

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Insult.class);
    indexer.reindex(insult);

Immediately after invoking the `updateEntry` method, you must update the
respective asset and index the entity instance. The above example code calls 
the indexer to index (or re-index, if updating) the entity. It's that easy to
update assets and indexes.

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

Here's some code which deletes an asset entry and an index associated with the
example insult portlet's `Insult` entity. 

    assetEntryLocalService.deleteEntry(
        Insult.class.getName(), insult.getInsultId());

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Insult.class);
    indexer.delete(insult);

In your `-LocalServiceImpl` class, you can write similar code. Instead of using
the class name `Insult` however, specify your entity's class name, and instead
of using a variable named `insult`, use a variable that holds your entity's
instance. 

**Important:** In order for Liferay's Asset Publisher portlet to show your
entity, the entity must have an Asset Renderer. To learn how to implement
an Asset Renderer for your custom entity, refer to Learning Path [Implementing Asset Renderers](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers).
Note also that an Asset Renderer is how you show a user the components of your
entity in the Asset Publisher. On deploying your portlet with asset, indexer,
and asset rendering implementations in place, an Asset Publisher can show your
custom entities! 

![Figure 1: It can be useful to show custom entities, like this example insult entity, in a JSP or in an Asset Publisher.](../../images/basic-asset-in-asset-publisher.png)

Great! Now you know how to add, update, and delete assets in your portlets!

## Related Topics [](id=related-topics)

[Asset Enabling Custom Entities](/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)

[Implementing Asset Renderers](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)

[Relating Assets](/develop/tutorials/-/knowledge_base/6-2/relating-assets)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)
