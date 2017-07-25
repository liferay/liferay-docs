# Handling Assets at the Guestbook Service Layer [](id=handling-assets-at-the-guestbook-service-layer)

In this section, you'll update the guestbook service layer to use assets. Follow 
these steps to make the updates:

1.  You must update the add, update, and delete methods of your project's
    `GuestbookLocalServiceImpl` and `EntryLocalServiceImpl` classes. Open your
    project's `GuestbookLocalServiceImpl` class and find the `addGuestbook` 
    method. Replace it with the following method:

        @Indexable(type = IndexableType.REINDEX)
        public Guestbook addGuestbook(
            long userId, String name, ServiceContext serviceContext)
          throws PortalException {

          long groupId = serviceContext.getScopeGroupId();

          User user = userLocalService.getUserById(userId);

          Date now = new Date();

          validate(name);

          long guestbookId = counterLocalService.increment();

          Guestbook guestbook = guestbookPersistence.create(guestbookId);

          guestbook.setUuid(serviceContext.getUuid());
          guestbook.setUserId(userId);
          guestbook.setGroupId(groupId);
          guestbook.setCompanyId(user.getCompanyId());
          guestbook.setUserName(user.getFullName());
          guestbook.setCreateDate(serviceContext.getCreateDate(now));
          guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
          guestbook.setName(name);
          guestbook.setExpandoBridgeAttributes(serviceContext);

          guestbookPersistence.update(guestbook);

          resourceLocalService.addResources(
            user.getCompanyId(), groupId, userId, Guestbook.class.getName(),
            guestbookId, false, true, true);

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


          return guestbook;
        }

    Your call to `assetEntryLocalService.updateEntry` is responsible for adding 
    a new row (corresponding to the guestbook that's being added) to the 
    `AssetEntry` table in Liferay's database. The `updateEntry` method of
    `AssetEntryLocalServiceImpl` can be used for both adding and updating asset
    entries. It checks whether or not the asset entry already exists in the
    database. If it does not, it adds it. If it does, it updates it. If you 
    check Liferay's [Javadocs](https://docs.liferay.com/portal/7.0-latest/javadocs/) 
    for `AssetEntryLocalServiceUtil.updateEntry`, you'll see that this method is
    overloaded. Why did you use a version of this method with such a long 
    message signature? There's only one version of the `updateEntry` method that 
    takes a `title` parameter (to set the asset entry's title). Since you want 
    to set the asset title, that's the version you use. In your call to
    `AssetEntryLocalServiceUtil.updateEntry`, you're setting the asset entry's 
    title to `guestbook.getName()`, i.e., to the name of the guestbook entity.

    Later in this Learning Path, you'll update the Guestbook Admin portlet's 
    form for adding guestbooks to allow related assets to be selected. Related 
    assets are stored in the `AssetLink` table of Liferay's database. Your call 
    to `assetLinkLocalService.updateLinks` is responsible for adding the 
    appropriate entries to Liferay's `AssetLink` table so that the related 
    assets feature works for your guestbook entities. Just as the `updateEntry` 
    method of `AssetEntryLocalServiceImpl` can be used for both adding and 
    updating asset entries, the `updateLink` method of 
    `AssetLinkLocalServiceImpl` can be used for both adding and updating asset 
    links.

2.  Next, replace the `updateGuestbook` method of `GuestbookLocalServiceImpl` 
    with the following one:

        @Indexable(type = IndexableType.REINDEX)
        public Guestbook updateGuestbook(
            long userId, long guestbookId, String name,
            ServiceContext serviceContext)
          throws PortalException {

          Date now = new Date();

          validate(name);

          Guestbook guestbook = getGuestbook(guestbookId);

          User user = UserLocalServiceUtil.getUser(userId);

          guestbook.setUserId(userId);
          guestbook.setUserName(user.getFullName());
          guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
          guestbook.setName(name);
          guestbook.setExpandoBridgeAttributes(serviceContext);

          guestbookPersistence.update(guestbook);

          resourceLocalService.updateResources(
            serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
            Guestbook.class.getName(), guestbookId,
            serviceContext.getGroupPermissions(),
            serviceContext.getGuestPermissions());


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

          return guestbook;
        }

    Here, you're using `assetEntryLocalService.updateEntry` to update an 
    existing asset entry and `assetLinkLocalService.updateLinks` to add or 
    update that entry's asset links (related assets). As mentioned above, you 
    can use the `updateEntry` method of `AssetEntryLocalServiceImpl` for both 
    adding and updating asset entries. `assetLinkLocalService.updateLinks` works 
    the same way.

3.  Next, replace the `deleteGuestbook` method of `GuestbookLocalServiceImpl` 
    with the following one:

        @Indexable(type = IndexableType.DELETE)
        public Guestbook deleteGuestbook(
            long guestbookId, ServiceContext serviceContext)
          throws PortalException {

          Guestbook guestbook = getGuestbook(guestbookId);

          List<Entry> entries = EntryLocalServiceUtil.getEntries(
            serviceContext.getScopeGroupId(), guestbookId);

          for (Entry entry : entries) {
            EntryLocalServiceUtil.deleteEntry(
              entry.getEntryId(), serviceContext);
          }

          resourceLocalService.deleteResource(
            serviceContext.getCompanyId(), Guestbook.class.getName(),
            ResourceConstants.SCOPE_INDIVIDUAL, guestbookId);

          AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                              Guestbook.class.getName(), guestbookId);

              assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

              assetEntryLocalService.deleteEntry(assetEntry);

          guestbook = deleteGuestbook(guestbook);

          return guestbook;
        }

    Here, you use the guestbook's classname and guestbook ID to retrieve the
    corresponding asset entry. Then you delete the asset links corresponding to 
    the asset entry and, finally, the asset entry itself. As you might expect, 
    the method signatures of the methods for deleting asset entries and asset 
    links are shorter than the ones for adding/updating!
    
4.  Finally, Organize your imports and run service builder to apply the changes.