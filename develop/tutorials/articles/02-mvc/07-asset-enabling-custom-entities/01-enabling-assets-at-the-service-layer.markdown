# Enabling Assets at the Service Layer [](id=enabling-assets-at-the-service-layer)

Every Liferay asset is represented by a row in the `AssetEntry` table of
Liferay's database. Each row in this table has an `entryId` primary key and
`classNameId` and `classPK` foreign keys (among many others). An asset entry
row's `classNameId` specifies the type of asset represented by the asset entry.
E.g., if a row has a `classNameId` corresponding to `JournalArticle`, this means
that the asset entry row represents a web content article. (In Liferay,
`JournalArticle` is the back-end name for web content article.) An asset entry
row's `classPK` represents the primary key of the entity represented by the
asset. E.g., the `classPK` field of an asset entry row corresponding to a web
content article contains the primary key of the web content article in the
`JournalArticle` table of Liferay's database.

Your first step in asset-enabling your guestbook and guestbook entry entities is
to make Liferay's asset services available to your entities' service layers.
Open your guestbook-portlet project's `docroot/WEB-INF/service.xml` file and add
the following references just above the closing `</entity>` tag corresponding to
the guestbook entity:

    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
    <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

Add the same two references just above the closing `</entity>` tag of the
guestbook entry entity. As discussed above, you need to use Liferay's
`AssetEntry` service so that your application can add asset entries
corresponding to guestbooks and guestbook entries. You'll also use Liferay's
`AssetLink` service so that your application will support related assets.
"Asset links" are Liferay's back-end term for related assets.

## Handling Assets at the Guestbook Service Layer [](id=handling-assets-at-the-guestbook-service-layer)

Next, you need to update the add, update, and delete methods of your project's
`GuestbookLocalServiceImpl` and `EntryLocalServiceImpl` classes. Open your
project's `GuestbookLocalServiceImpl` class and find the `addGuestbook` method.
Replace it with the following method:

    public Guestbook addGuestbook(long userId, String name,
                    ServiceContext serviceContext) throws SystemException,
                    PortalException {

            long groupId = serviceContext.getScopeGroupId();

            User user = userPersistence.findByPrimaryKey(userId);

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

            resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
                            Guestbook.class.getName(), guestbookId, false, true, true);
            
            AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                            groupId, guestbook.getCreateDate(),
                            guestbook.getModifiedDate(), Guestbook.class.getName(),
                            guestbookId, guestbook.getUuid(), 0,
                            serviceContext.getAssetCategoryIds(),
                            serviceContext.getAssetTagNames(), true, null, null, null,
                            ContentTypes.TEXT_HTML, guestbook.getName(), null, null, null,
                            null, 0, 0, null, false);
            
            assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                            serviceContext.getAssetLinkEntryIds(),
                            AssetLinkConstants.TYPE_RELATED);
            
            Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                            Guestbook.class);

            indexer.reindex(guestbook);

            return guestbook;

    }

Your call to `assetEntryLocalService.updateEntry` is responsible for adding a
new row (corresponding to the guestbook that's being added) to the `AssetEntry`
table in Liferay's database. The `updateEntry` method of
`AssetEntryLocalServiceImpl` can be used for both adding and updating asset
entries. It checks whether or not the asset entry already exists in the
database. If it does not, it adds it. If it does, it updates it. If you check
Liferay's [Javadocs](http://docs.liferay.com/portal/6.2/javadocs) for
`AssetEntryLocalServiceUtil.updateEntry`, you'll see that this method is
overloaded. Why did you use a version of this method with such a long message
signature? There's only one version of the `updateEntry` method that takes a
`title` parameter (to set the asset entry's title). Since you want to set the
asset title, that's the version you use. In your call to
`AssetEntryLocalServiceUtil.updateEntry`, you're setting the asset entry's title
to `guestbook.getName()`, i.e., to the name of the guestbook entity.

Later in this learning path, you'll update the Guestbook Admin portlet's form
for adding guestbooks to allow related assets to be selected. Related assets are
stored in the `AssetLink` table of Liferay's database. Your call to
`assetLinkLocalService.updateLinks` is responsible for adding the appropriate
entries to Liferay's `AssetLink` table so that the related assets feature works
for your guestbook entities. Just as the `updateEntry` method of
`AssetEntryLocalServiceImpl` can be used for both adding and updating asset
entries, the `updateLink` method of `AssetLinkLocalServiceImpl` can be used for
both adding and updating asset links.

Next, replace the `updateGuestbook` method of `GuestbookLocalServiceImpl` with
the following one:

    public Guestbook updateGuestbook(long userId, long guestbookId,
                    String name, ServiceContext serviceContext) throws PortalException,
                    SystemException {

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

            resourceLocalService.updateResources(serviceContext.getCompanyId(),
                            serviceContext.getScopeGroupId(), name, guestbookId,
                            serviceContext.getGroupPermissions(),
                            serviceContext.getGuestPermissions());
            
            AssetEntry assetEntry = assetEntryLocalService.updateEntry(guestbook.getUserId(),
                            guestbook.getGroupId(), guestbook.getCreateDate(),
                            guestbook.getModifiedDate(), Guestbook.class.getName(),
                            guestbookId, guestbook.getUuid(), 0,
                            serviceContext.getAssetCategoryIds(),
                            serviceContext.getAssetTagNames(), true, null, null, null,
                            ContentTypes.TEXT_HTML, guestbook.getName(), null, null, null,
                            null, 0, 0, null, false);
            
            assetLinkLocalService.updateLinks(serviceContext.getUserId(),
                            assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
                            AssetLinkConstants.TYPE_RELATED);
            
            Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                            Guestbook.class);

            indexer.reindex(guestbook);

            return guestbook;
    }

Here, you're using `assetEntryLocalService.updateEntry` to update an existing
asset entry and `assetLinkLocalService.updateLinks` to add or update that
entry's asset links (related assets). As mentioned above, you can use the
`updateEntry` method of `AssetEntryLocalServiceImpl` for both adding and
updating asset entries. `assetLinkLocalService.updateLinks` works the same way.

Next, replace the `deleteGuestbook` method of `GuestbookLocalServiceImpl` with
the following one:

    public Guestbook deleteGuestbook(long guestbookId,
                    ServiceContext serviceContext) throws PortalException,
                    SystemException {

            Guestbook guestbook = getGuestbook(guestbookId);

            List<Entry> entries = EntryLocalServiceUtil.getEntries(
                            serviceContext.getScopeGroupId(), guestbookId);

            for (Entry entry : entries) {
                    EntryLocalServiceUtil.deleteEntry(entry.getEntryId(),
                                    serviceContext);
            }

            resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                            Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                            guestbookId);
            
            AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                            Guestbook.class.getName(), guestbookId);

            assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
            
            assetEntryLocalService.deleteEntry(assetEntry);

            Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                            Guestbook.class);

            indexer.delete(guestbook);
            
            guestbook = deleteGuestbook(guestbookId);

            return guestbook;
    }

Here, you use the guestbook's classname and guestbook ID to retrieve the
corresponding asset entry. Then you delete the asset links corresponding to the
asset entry and, finally, the asset entry itself. As you might expect, the
method signatures of the methods for deleting asset entries and asset links are
shorter than the ones for adding/updating!

## Handling Assets at the Entry Service Layer [](id=handling-assets-at-the-entry-service-layer)

You've updated the guestbook entity's service methods. Now it's time to update
the guestbook entry entity's service methods. The calls you need to make to
`assetEntryLocalService` and `assetLinkLocalService` in the guestbook entry
entity's service methods are identical to the ones you made in the guestbook
entity's service methods. Open `EntryLocalServiceImpl` and replace the
`addEntry` method with the following method:

    public Entry addEntry(long userId, long guestbookId, String name,
                    String email, String message, ServiceContext serviceContext) throws PortalException, SystemException {
            long groupId = serviceContext.getScopeGroupId();
            
            User user = userPersistence.findByPrimaryKey(userId);
            
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
            
            resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
                            Entry.class.getName(), entryId, false, true, true);
            
            AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                            groupId, entry.getCreateDate(), entry.getModifiedDate(),
                            Entry.class.getName(), entryId, entry.getUuid(), 0,
                            serviceContext.getAssetCategoryIds(),
                            serviceContext.getAssetTagNames(), true, null, null, null,
                            ContentTypes.TEXT_HTML, entry.getMessage(), null, null, null,
                            null, 0, 0, null, false);
            
            assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                            serviceContext.getAssetLinkEntryIds(),
                            AssetLinkConstants.TYPE_RELATED);
            
            Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                            Entry.class);

            indexer.reindex(entry);
            
            return entry;
    }

Next, replace the `updateEntry` method with the following one:

    public Entry updateEntry(long userId, long guestbookId, long entryId,
                    String name, String email, String message,
                    ServiceContext serviceContext) throws PortalException,
                    SystemException {
            long groupId = serviceContext.getScopeGroupId();

            User user = userPersistence.findByPrimaryKey(userId);

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

            resourceLocalService.updateResources(user.getCompanyId(), groupId,
                            Entry.class.getName(), entryId,
                            serviceContext.getGroupPermissions(),
                            serviceContext.getGuestPermissions());
            
            AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                            groupId, entry.getCreateDate(), entry.getModifiedDate(),
                            Entry.class.getName(), entryId, entry.getUuid(), 0,
                            serviceContext.getAssetCategoryIds(),
                            serviceContext.getAssetTagNames(), true, null, null, null,
                            ContentTypes.TEXT_HTML, entry.getMessage(), null, null, null,
                            null, 0, 0, null, false);
            
            assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                            serviceContext.getAssetLinkEntryIds(),
                            AssetLinkConstants.TYPE_RELATED);
            
            Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                            Entry.class);

            indexer.reindex(entry);

            return entry;
    }

Finally, replace the `deleteEntry` method with the following one:

    public Entry deleteEntry(long entryId, ServiceContext serviceContext)
                    throws PortalException, SystemException {

            Entry entry = getEntry(entryId);

            resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                            Entry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                            entryId);
            
            AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                            Entry.class.getName(), entryId);
            
            assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

            assetEntryLocalService.deleteEntry(assetEntry);
            
            Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                            Entry.class);

            indexer.delete(entry);
            
            entry = deleteEntry(entryId);

            return entry;
    }

Excellent! You've asset-enabled your guestbook and guestbook entry entities at
the service layer. Your next step is to implement asset renderers for these
entities so that they can be fully integrated into Liferay's asset framework.
Every Liferay asset needs an asset renderer class so that it can be displayed by
the Asset Publisher portlet.
