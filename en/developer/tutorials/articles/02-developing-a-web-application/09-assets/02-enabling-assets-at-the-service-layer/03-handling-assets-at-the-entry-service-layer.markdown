---
header-id: handling-assets-for-the-guestbookentry-service
---

# Handling Assets for the GuestbookEntry Service

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Enabling Assets at the Service Layer</p><p>Step 3 of 3</p>
</div>

Now you must update the guestbook entry entity's service methods. In these 
methods, the calls you'll make to `assetEntryLocalService` and 
`assetLinkLocalService` are identical to the ones you made in the guestbook
entity's service methods, except you're specifying assets for `GuestbookEntry`
entities. 

1.  Open `GuestbookEntryLocalServiceImpl` and add the asset calls to the
    `addGuestbookEntry` method after the resource calls: 

    ```java
    AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                      groupId, entry.getCreateDate(), entry.getModifiedDate(),
                      GuestbookEntry.class.getName(), entryId, entry.getUuid(), 0,
                      serviceContext.getAssetCategoryIds(),
                      serviceContext.getAssetTagNames(), true, true, null, null, null, null,
                      ContentTypes.TEXT_HTML, entry.getMessage(), null, null, null,
                      null, 0, 0, null);

    assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                      serviceContext.getAssetLinkEntryIds(),
                      AssetLinkConstants.TYPE_RELATED);
    ```

2.  Next, add the asset calls to the `updateGuestbookEntry` method after the resource 
    calls: 

    ```java
    AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                  serviceContext.getScopeGroupId(),
                  entry.getCreateDate(), entry.getModifiedDate(),
                  GuestbookEntry.class.getName(), entryId, entry.getUuid(),
                  0, serviceContext.getAssetCategoryIds(),
                  serviceContext.getAssetTagNames(), true, true,
                  entry.getCreateDate(), null, null, null,
                  ContentTypes.TEXT_HTML, entry.getMessage(), null,
                  null, null, null, 0, 0,
                  serviceContext.getAssetPriority());

    assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                  serviceContext.getAssetLinkEntryIds(),
                  AssetLinkConstants.TYPE_RELATED);
    ```

3.  Add the asset calls to the `deleteGuestbookEntry` method after the resource calls: 

    ```java
    AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                          GuestbookEntry.class.getName(), entry.getEntryId());

    assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

    assetEntryLocalService.deleteEntry(assetEntry);
    ```

4.  Organize your imports, save the file, and run Service Builder. 

5.  Finally, add these language keys to the
    `guestbook-web/src/main/resources/content/Language.properties` file:

    ```properties
    model.resource.com.liferay.docs.guestbook.model.Guestbook=Guestbook
    model.resource.com.liferay.docs.guestbook.model.GuestbookEntry=Guestbook Entry
    ```

Excellent! You've asset-enabled your guestbook and guestbook entry entities at
the service layer. Your next step is to implement asset renderers for these
entities so they can be fully integrated into the asset framework. Every asset
needs an asset renderer class so the Asset Publisher portlet can display it.
