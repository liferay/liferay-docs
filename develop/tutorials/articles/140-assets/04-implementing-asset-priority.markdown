# Implementing Asset Priority [](id=implementing-asset-priority)

The 
[Asset Publisher](/discover/portal/-/knowledge_base/7-0/publishing-assets) 
lets you order assets by priority. For this to work, 
however, users must be able to set the asset's priority when creating or editing 
the asset. For example, when creating or editing web content, users can assign 
a priority in the Metadata section's Priority field. 

![Figure 1: The Priority field lets users set an asset's priority.](../../images/web-content-categorization.png)

This field isn't enabled by default for your custom assets. You must manually 
add support for it. Fortunately, this is very straightforward. This tutorial 
shows you how. Onwards! 

## Add the Priority Field to Your JSP [](id=add-the-priority-field-to-your-jsp)

In the JSP for adding and editing your asset, add the following input field that 
lets users set the asset's priority. This example also validates the input to 
make sure the value the user sets is a number higher than zero: 

    <aui:input label="priority" name="assetPriority" type="text" value="<%= priority %>">
        <aui:validator name="number" />

        <aui:validator name="min">[0]</aui:validator>
    </aui:input>

That's it for the view layer! Now when users create or edit your custom asset,
they can enter its priority. Next, you'll learn how to use that value in your
service layer. 

## Using the Priority Value in Your Service Layer [](id=using-the-priority-value-in-your-service-layer)

To make the priority value functional, you must retrieve it from the view and
add it to the asset in your database. The priority value is automatically
available in your service layer via the `ServiceContext` variable
`serviceContext`. Retrieve it with `serviceContext.getAssetPriority()`, and then
pass it as the last argument to the `assetEntryLocalService.updateEntry` call in
your `-LocalServiceImpl`. You can see an example of this in 
[the `BlogsEntryLocalServiceImpl` class](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/blogs/blogs-service/src/main/java/com/liferay/blogs/service/impl/BlogsEntryLocalServiceImpl.java)
of @product@'s Blogs app. The `updateAsset` method takes a `priority` argument,
which it passes as the last argument to its `assetEntryLocalService.updateEntry` 
call: 

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

The `BlogsEntryLocalServiceImpl` class calls this `updateAsset` method when 
adding or updating a blog entry. Note that `serviceContext.getAssetPriority()` 
retrieves the priority: 

    updateAsset(
            userId, entry, serviceContext.getAssetCategoryIds(),
            serviceContext.getAssetTagNames(),
            serviceContext.getAssetLinkEntryIds(),
            serviceContext.getAssetPriority());

Sweet! Now you know how to enable priorities for your app's assets. 

## Related Topics [](id=related-topics)

[Adding, Updating, and Deleting Assets For Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Implementing Asset Categorization and Tagging](/develop/tutorials/-/knowledge_base/7-0/implementing-asset-categorization-and-tagging)

[Relating Assets](/develop/tutorials/-/knowledge_base/7-0/relating-assets)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)

[Publishing Assets](/discover/portal/-/knowledge_base/7-0/publishing-assets)
