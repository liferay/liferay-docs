# Handling Assets at the Guestbook Service Layer [](id=handling-assets-at-the-guestbook-service-layer)

<div class="learn-path-step">
    <p>Enabling Assets at the Service Layer<br>Step 2 of 3</p>
</div>

Now you'll update the guestbook service layer to use assets. You must update the
`add`, `update`, and `delete` methods of your project's `GuestbookLocalServiceImpl`:

1.  Open your project's `GuestbookLocalServiceImpl` class and find the 
    `addGuestbook` method. Add the call to add the asset entries below the call 
    that adds resources: 

          AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                              groupId, guestbook.getCreateDate(),
                              guestbook.getModifiedDate(), Guestbook.class.getName(),
                              guestbookId, guestbook.getUuid(), 0,
                              serviceContext.getAssetCategoryIds(),
                              serviceContext.getAssetTagNames(), true, true, null, null, null, null,
                              ContentTypes.TEXT_HTML, guestbook.getName(), null, null, null,
                              null, 0, 0, null);

          assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                              serviceContext.getAssetLinkEntryIds(),
                              AssetLinkConstants.TYPE_RELATED);

    Calling `assetEntryLocalService.updateEntry` adds a new row (corresponding
    to the guestbook that's being added) to the `AssetEntry` table in
    @product@'s database. `AssetEntryLocalServiceImpl`'s `updateEntry` method
    both adds and updates asset entries because it checks to see whether the
    asset entry already exists in the database and then takes the appropriate 
    action. If you check the 
    [Javadoc](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portlet/asset/service/impl/AssetEntryLocalServiceImpl.html) 
    for `AssetEntryLocalServiceUtil.updateEntry`, you'll see that this method is
    overloaded. Now, why did you use a version of this method with such a long
    method signature? Because there's only one version of `updateEntry` that
    takes a `title` parameter (to set the asset entry's title). Since you want
    to set the asset title to `guestbook.getName()`, that's the version you use. 

    Later, you'll update the Guestbook Admin portlet's form for adding 
    guestbooks to allow the selection of related assets, which are stored in the 
    database's `AssetLink` table. The `assetLinkLocalService.updateLinks` call 
    adds the appropriate entries to the table so related assets work for your 
    guestbook entities. The `updateEntry` method adds and updates asset entries 
    the same way `updateLink` adds and updates asset links. 

2.  Next, add the asset calls to `GuestbookLocalServiceImpl`'s `updateGuestbook`
    method, directly after the resource call:

          AssetEntry assetEntry = assetEntryLocalService.updateEntry(guestbook.getUserId(),
                              guestbook.getGroupId(), guestbook.getCreateDate(),
                              guestbook.getModifiedDate(), Guestbook.class.getName(),
                              guestbookId, guestbook.getUuid(), 0,
                              serviceContext.getAssetCategoryIds(),
                              serviceContext.getAssetTagNames(), true, true, guestbook.getCreateDate(), 
                              null, null, null, ContentTypes.TEXT_HTML, guestbook.getName(), null, null, 
                              null, null, 0, 0, serviceContext.getAssetPriority());

          assetLinkLocalService.updateLinks(serviceContext.getUserId(),
                              assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
                              AssetLinkConstants.TYPE_RELATED);

    Here, `assetEntryLocalService.updateEntry` updates an existing asset entry 
    and `assetLinkLocalService.updateLinks` adds or updates that entry's asset 
    links (related assets). 

3.  Next, add the asset calls to the `deleteGuestbook` method, directly after 
    the resource calls:

          AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                              Guestbook.class.getName(), guestbookId);

          assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

          assetEntryLocalService.deleteEntry(assetEntry);

    Here, you use the guestbook's class name and ID to retrieve the 
    corresponding asset entry. Then you delete that asset entry's asset links 
    and the asset entry itself. 
 
4.  Finally, organize your imports, save the file, and run Service Builder to
    apply the changes. 

Next, you'll do the same thing for guestbook entries. 
