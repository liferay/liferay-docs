# Handling Assets at the Entry Service Layer [](id=handling-assets-at-the-entry-service-layer)

<div class="learn-path-step">
    <p>Enabling Assets at the Service Layer<br>Step 3 of 3</p>
</div>

Now you must update the guestbook entry entity's service methods. In these 
methods, the calls you'll make to `assetEntryLocalService` and 
`assetLinkLocalService` are identical to the ones you made in the guestbook 
entity's service methods. Follow these steps: 

1.  Open `EntryLocalServiceImpl` and add the asset calls to the `addEntry` 
    method after the resource calls: 

          AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                              groupId, entry.getCreateDate(), entry.getModifiedDate(),
                              Entry.class.getName(), entryId, entry.getUuid(), 0,
                              serviceContext.getAssetCategoryIds(),
                              serviceContext.getAssetTagNames(), true, true, null, null, null, null,
                              ContentTypes.TEXT_HTML, entry.getMessage(), null, null, null,
                              null, 0, 0, null);

          assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                              serviceContext.getAssetLinkEntryIds(),
                              AssetLinkConstants.TYPE_RELATED);

2.  Next, add the asset calls to the `updateEntry` method after the resource 
    calls: 

              AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                              serviceContext.getScopeGroupId(),
                              entry.getCreateDate(), entry.getModifiedDate(),
                              Entry.class.getName(), entryId, entry.getUuid(),
                              0, serviceContext.getAssetCategoryIds(),
                              serviceContext.getAssetTagNames(), true, true,
                              entry.getCreateDate(), null, null, null,
                              ContentTypes.TEXT_HTML, entry.getMessage(), null,
                              null, null, null, 0, 0,
                              serviceContext.getAssetPriority());

              assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                              serviceContext.getAssetLinkEntryIds(),
                              AssetLinkConstants.TYPE_RELATED);

3.  Add the asset calls to the `deleteEntry` method after the resource calls: 

        AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                              Entry.class.getName(), entryId);

        assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

        assetEntryLocalService.deleteEntry(assetEntry);

4.  Organize your imports, save the file, and run Service Builder. 

5.  Finally, add these language keys to the
    `guestbook-web/src/main/resource/content/Language.properties` file:

        model.resource.com.liferay.docs.guestbook.model.Guestbook=Guestbook
        model.resource.com.liferay.docs.guestbook.model.Entry=Guestbook Entry

Excellent! You've asset-enabled your guestbook and guestbook entry entities at
the service layer. Your next step is to implement asset renderers for these
entities so they can be fully integrated into @product@'s asset framework.
Every asset needs an asset renderer class so the Asset Publisher portlet can
display it.
