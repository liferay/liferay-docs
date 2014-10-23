# Adding, Updating, and Deleting Assets 

Whenever you create a new entity, you need to let the asset framework know. In
this sense, it's similar to permission resources. It's a simple procedure: you
invoke a method of the asset framework that adds an `AssetEntry` so that Liferay
can keep track of the asset. 

Specifically, you should access these methods using either the static methods
of `AssetLocalServiceUtil` or an instance of the `AssetEntryLocalService`
injected by Spring. This tutorial uses the latter approach. Code from Liferay's 
Blogs portlet is used as an example. You can find it in Liferay's source code 
on [Github](https://github.com/liferay/liferay-portal/tree/6.2.x/portal-impl/src/com/liferay/portlet/blogs).

<!-- To simplify this section, you'll use the static methods of 
`AssetLocalServiceUtil`, since it doesn't require any special setup in your 
application.
- Commenting out this original sentence that Rich's comment applies to -Nick -->

<!-- This is wrong. We should show them the right way to do it, rather than the
way that's easier to document. Remember that developers will be taking direction
from our text and writing actual code based on it. The right way is to add a
<reference> tag to the service.xml and inject the service with Spring. In fact,
the example below does just that (since it's Liferay's best practice), so the
example code here doesn't even match what we just stated. We need to fix this.
-Rich--> 

## Preparing Your Project for the Asset Framework

Before proceeding, make sure that you've implemented an indexer for your plugin. 
Liferay's asset framework uses the indexer to manage assets. For instructions on 
creating an indexer, see the learning path [Enabling Search and Indexing](/learning-paths/-/knowledge_base/6-2/enabling-search-and-indexing). 

You also need to make a small addition to your project's `service.xml` file. Put 
the following line of code below any finder methods and then run Service 
Builder:

    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />

Now you're ready to learn about adding and updating assets!

## Adding and Updating Assets 

The method to invoke when one of your custom content entries is added or
updated is the same. This method is called `updateEntry`. Here's the full 
signature: 

    AssetEntry updateEntry(
		long userId, long groupId, Date createDate, Date modifiedDate,
		String className, long classPK, String classUuid, long classTypeId,
		long[] categoryIds, String[] tagNames, boolean visible,
		Date startDate, Date endDate, Date expirationDate, String mimeType,
		String title, String description, String summary, String url,
		String layoutUuid, int height, int width, Integer priority,
		boolean sync)
	throws PortalException, SystemException

Here's an example of this method's invocation extracted from the built in
Blogs portlet: 

    assetEntryLocalService.updateEntry(
		userId, entry.getGroupId(), entry.getCreateDate(),
		entry.getModifiedDate(), BlogsEntry.class.getName(),
		entry.getEntryId(), entry.getUuid(), 0, assetCategoryIds,
		assetTagNames, visible, null, null, null, ContentTypes.TEXT_HTML,
		entry.getTitle(), entry.getDescription(), summary, null, null, 0, 0,
		null, false);

Here's a quick summary of the most important parameters of this method: 

-   `userId` is the identifier of the user who created the content. 
-   `groupId` identifies the scope of the created content. If your content
    doesn't support scopes (extremely rare), just pass `0` as the value. 
-   `className` identifies the type of asset. The recommended convention is to
    use the name of the Java class that represents your content type, but you
    can actually use any String you want as long as you are sure that it is
    unique.  
-   `classPK` identifies the specific content being created among others of the
    same type. It's usually the primary key of the table where the custom
    content is stored. If you want, you can use the *classUuid* parameter to
    specify a secondary identifier; it's guaranteed to be universally unique.
    It's especially useful if your content will be exported and imported across
    separate portals.  
-   `assetCategoryIds` and `assetTagNames` represent the categories and tags
    selected by the author of the content. The asset framework stores them for
    you.
-   `visible` specifies whether the content should be shown at all by Asset
    Publisher. 
-   `title,` `description` and `summary` are descriptive fields used by the
    Asset Publisher when displaying entries of your content type. 
-   `publishDate` and `expirationDate`, when specified, tell Asset Publisher it
    shouldn't show the content before a given publication date or after a given
    expiration date, respectively. 
-   All other fields are optional. It won't always make sense to include them.
    The `sync` parameter should always be *false* unless you're doing something
    very advanced (feel free to look at the code if you're really curious). 

<!-- We should fully describe the sync parameter, as well as any others.
Otherwise, it looks like we're holding back information for no apparent reason.
-Rich -->

Another thing you need to do when adding or updating assets is call the indexer. 
For example, the calls to the indexer that the Blogs portlet makes when adding 
or updating assets are shown here:

    ```
    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(BlogsEntry.class);
    ```
    indexer.reindex(entry);
    ```

## Deleting Assets 

When one of your custom content entries is deleted, you should once again let
the asset framework know. This way, it can clean up stored information and make
sure that the Asset Publisher doesn't show any information for the content that
has been deleted. The signature of the method to delete an asset entry is: 

    void deleteEntry(String className, long classPK)

Here's an example invocation extracted from the blogs portlet: 

    assetEntryLocalService.deleteEntry(
        BlogsEntry.class.getName(), entry.getEntryId());
        
As with adding and updating assets, you also need to call the indexer when 
deleting assets. The calls to the indexer that the Blogs portlet makes when 
deleting assets are shown here:

    ```
    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(BlogsEntry.class);
    
    indexer.delete(entry);
    ```

Great! Now you know how to add, update, and delete assets in your portlets!

## Related Topics

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)
