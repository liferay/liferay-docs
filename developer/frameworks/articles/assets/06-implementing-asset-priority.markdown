---
header-id: implementing-asset-priority
---

# Implementing Asset Priority

[TOC levels=1-4]

This asset priority field isn't enabled when you create an asset. You must 
manually add support for it. You'll learn how below.

## Add the Priority Field to Your JSP

In the JSP for adding and editing your asset, add the following input field 
that lets users set the asset's priority. This example also validates the input 
to make sure the value the user sets is a number higher than zero: 

    <aui:input label="priority" name="assetPriority" type="text" value="<%= priority %>">
        <aui:validator name="number" />

        <aui:validator name="min">[0]</aui:validator>
    </aui:input>

That's it for the view layer! Now when users create or edit your asset, they can
enter its priority. Next, you'll learn how to use that value in your service
layer. 

## Using the Priority Value in Your Service Layer

To make the priority value functional, you must retrieve it from the view and
add it to the asset in your database. The priority value is automatically
available in your service layer via the `ServiceContext` variable
`serviceContext`. Retrieve it with `serviceContext.getAssetPriority()`, and then
pass it as the last argument to the `assetEntryLocalService.updateEntry` call in
your `-LocalServiceImpl`. You can see an example of this in 
[the `BlogsEntryLocalServiceImpl` class](https://github.com/liferay/liferay-portal/blob/master/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/service/impl/BlogsEntryLocalServiceImpl.java)
of @product@'s Blogs app. The `updateAsset` method takes a `priority` argument,
which it passes as the last argument to its 
`assetEntryLocalService.updateEntry` 
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
