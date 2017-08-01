# Handling Assets at the Entry Service Layer [](id=handling-assets-at-the-entry-service-layer)

You've updated the guestbook entity's service methods. Now it's time to update
the guestbook entry entity's service methods. The calls you need to make to
`assetEntryLocalService` and `assetLinkLocalService` in the guestbook entry
entity's service methods are identical to the ones you made in the guestbook
entity's service methods. Follow these steps:

1.  Open `EntryLocalServiceImpl` and replace the `addEntry` method with the 
    following method:

        @Indexable(type = IndexableType.REINDEX)
        public Entry addEntry(
            long userId, long guestbookId, String name, String email,
            String message, ServiceContext serviceContext)
          throws PortalException {

          long groupId = serviceContext.getScopeGroupId();

          User user = userLocalService.getUserById(userId);

          Date now = new Date();

          validate(name, email, message);

          long entryId = counterLocalService.increment();

          Entry entry = entryPersistence.create(entryId);

          entry.setUuid(serviceContext.getUuid());
          entry.setUserId(userId);
          entry.setGroupId(groupId);
          entry.setCompanyId(user.getCompanyId());
          entry.setUserName(user.getFullName());
          entry.setCreateDate(serviceContext.getCreateDate(now));
          entry.setModifiedDate(serviceContext.getModifiedDate(now));
          entry.setExpandoBridgeAttributes(serviceContext);
          entry.setGuestbookId(guestbookId);
          entry.setName(name);
          entry.setEmail(email);
          entry.setMessage(message);

          entryPersistence.update(entry);

          resourceLocalService.addResources(
            user.getCompanyId(), groupId, userId, Entry.class.getName(),
            entryId, false, true, true);


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

          return entry;
        }

2.  Next, replace the `updateEntry` method with the following one:

        @Indexable(type = IndexableType.REINDEX)
        public Entry updateEntry(
            long userId, long guestbookId, long entryId, String name,
            String email, String message, ServiceContext serviceContext)
          throws PortalException {

          long groupId = serviceContext.getScopeGroupId();

          User user = userLocalService.getUserById(userId);

          Date now = new Date();

          validate(name, email, message);

          Entry entry = getEntry(entryId);

          entry.setUserId(userId);
          entry.setUserName(user.getFullName());
          entry.setName(name);
          entry.setEmail(email);
          entry.setMessage(message);
          entry.setModifiedDate(serviceContext.getModifiedDate(now));
          entry.setExpandoBridgeAttributes(serviceContext);

          entryPersistence.update(entry);

          resourceLocalService.updateResources(
            user.getCompanyId(), groupId, Entry.class.getName(), entryId,
            serviceContext.getGroupPermissions(),
            serviceContext.getGuestPermissions());

              AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                              groupId, entry.getCreateDate(), entry.getModifiedDate(),
                              Entry.class.getName(), entryId, entry.getUuid(), 0,
                              serviceContext.getAssetCategoryIds(),
                              serviceContext.getAssetTagNames(), true, true, entry.getCreateDate(), 
                              null, null, null, ContentTypes.TEXT_HTML, entry.getMessage(), null, null, 
                              null, null, 0, 0, serviceContext.getAssetPriority());

              assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                              serviceContext.getAssetLinkEntryIds(),
                              AssetLinkConstants.TYPE_RELATED);

          return entry;
        }

3.  Replace the `deleteEntry` method with the following one:

        @Indexable(type = IndexableType.DELETE)
        public Entry deleteEntry(long entryId, ServiceContext serviceContext)
          throws PortalException {

          Entry entry = getEntry(entryId);

          resourceLocalService.deleteResource(
            serviceContext.getCompanyId(), Entry.class.getName(),
            ResourceConstants.SCOPE_INDIVIDUAL, entryId);

        AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                              Entry.class.getName(), entryId);

              assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

              assetEntryLocalService.deleteEntry(assetEntry);

          entry = deleteEntry(entryId);

          return entry;
        }

4.  Finally, organize your imports and run service builder again.

Excellent! You've asset-enabled your guestbook and guestbook entry entities at
the service layer. Your next step is to implement asset renderers for these
entities so that they can be fully integrated into Liferay's asset framework.
Every Liferay asset needs an asset renderer class so that it can be displayed by
the Asset Publisher portlet.