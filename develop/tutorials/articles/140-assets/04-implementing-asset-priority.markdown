# Implementing Asset Priority

The Asset Publisher lets you order assets by priority. For this to work, 
however, you must enable your assets to accept priority values. Doing this is 
very straightforward. 

![Figure 1: The red box in this image highlights the priority selection for assets in the Asset Publisher.](../../images/asset-priority.png)

In the JSP for editing your asset, add the following input field that lets users 
set the asset's priority. This example also validates the input to make sure the 
priority the user chooses is a number higher than zero: 

    <aui:input label="priority" name="assetPriority" type="text" value="<%= priority %>">
        <aui:validator name="number" />

        <aui:validator name="min">[0]</aui:validator>
    </aui:input>

This parameter is then available in your service layer via the `ServiceContext` 
variable `serviceContext`. You can retrieve it with 
`serviceContext.getAssetPriority()`, and then pass it as the last argument to 
the `assetEntryLocalService.updateEntry` call in your `-LocalServiceImpl`. You 
can see an example of this in 
[the `BlogsEntryLocalServiceImpl` class](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/blogs/blogs-service/src/main/java/com/liferay/blogs/service/impl/BlogsEntryLocalServiceImpl.java) 
of @product@'s Blogs app. The `updateAsset` method takes a `priority` argument, 
which it passes to its `assetEntryLocalService.updateEntry` call: 

    @Override
    public void updateAsset(
            long userId, BlogsEntry entry, long[] assetCategoryIds,
            String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
        throws PortalException {

        ...

        AssetEntry assetEntry = assetEntryLocalService.updateEntry(
            userId, entry.getGroupId(), entry.getCreateDate(),
            entry.getModifiedDate(), BlogsEntry.class.getName(),
            entry.getEntryId(), entry.getUuid(), 0, assetCategoryIds,
            assetTagNames, true, visible, null, null, null, null,
            ContentTypes.TEXT_HTML, entry.getTitle(), entry.getDescription(),
            summary, null, null, 0, 0, priority);

        ...
	}

This `updateAsset` method is called in `BlogsEntryLocalServiceImpl` when adding 
or updating a blog entry. Note that `serviceContext.getAssetPriority()` 
retrieves the priority: 

    updateAsset(
            userId, entry, serviceContext.getAssetCategoryIds(),
            serviceContext.getAssetTagNames(),
            serviceContext.getAssetLinkEntryIds(),
            serviceContext.getAssetPriority());

That's it! Now you know how to enable priorities for your assets. 

**Related Topics**

[Adding, Updating, and Deleting Assets For Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Implementing Asset Categorization and Tagging](/develop/tutorials/-/knowledge_base/7-0/implementing-asset-categorization-and-tagging)

[Relating Assets](/develop/tutorials/-/knowledge_base/7-0/relating-assets)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)

[Publishing Assets](/discover/portal/-/knowledge_base/7-0/publishing-assets)
