---
header-id: developing-staged-model-data-handlers
---

# Developing Staged Model Data Handlers

In this tutorial, you'll create the `BookmarksStagedModelDataHandler` class used
for the Bookmarks application. The Bookmarks application has two staged models:
entries and folders. Creating data handlers for these two entities is similar,
so you'll examine how this is done for Bookmark entries. This tutorial assumes
you've already created
[staged models](/develop/tutorials/-/knowledge_base/7-1/understanding-staged-models).

1.  Create a new package in your existing Service Builder project for your data
    handler classes. For instance, the Bookmarks application's data handler
    classes reside in the `bookmarks-service` module's
    `com.liferay.bookmarks.internal.exportimport.data.handler` package.

2.  Create a `-StagedModelDataHandler` class in the `-exportimport.data.handler`
    package. The staged model data handler class should extend the
    [BaseStagedModelDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BaseStagedModelDataHandler.html)
    class and the entity type should be specified as its parameter. You can see
    how this was done for the `BookmarksEntryStagedModelDataHandler` class
    below:

        public class BookmarksEntryStagedModelDataHandler
            extends BaseStagedModelDataHandler<BookmarksEntry> {

3.  Create an `@Component` annotation section above the class declaration.

        @Component(immediate = true, service = StagedModelDataHandler.class)

4.  Create a getter and setter method for the local service of the staged model
    for which you want to provide a data handler:

        @Override
        protected BookmarksEntryLocalService getBookmarksEntryLocalService() {
            return _bookmarksEntryLocalService;
        }

        @Reference(unbind = "-")
        protected void setBookmarksEntryLocalService(
            BookmarksEntryLocalService bookmarksEntryLocalService) {

            _bookmarksEntryLocalService = bookmarksEntryLocalService;
        }

        private BookmarksEntryLocalService _bookmarksEntryLocalService;

    These methods are used to link this data handler with the staged model for
    bookmark entries.

    **Important:** @product@'s official Bookmarks app does not use local
    services in its staged model data handlers; instead, it uses the
    [StagedModelRepository](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html)
    framework. This is a new framework, but is a viable option when setting up
    your staged model data handlers. For more information on this, see the
    [Providing Entity-Specific Local Services for Staging](/develop/tutorials/-/knowledge_base/7-1/providing-entity-specific-local-services-for-staging)
    tutorial section. Since local services are more widely used in custom apps,
    this tutorial covers those instead.

5.  Provide the class names of the models the data handler tracks. You can do
    this by overriding the
    [StagedModelDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandler.html)'s
    `getClassnames()` method:

        public static final String[] CLASS_NAMES = {BookmarksEntry.class.getName()};

        @Override
        public String[] getClassNames() {
            return CLASS_NAMES;
        }

6.  Add a method that retrieves the staged model's display name:

        @Override
        public String getDisplayName(BookmarksEntry entry) {
            return entry.getName();
        }

    The display name is presented with the progress bar during the export/import
    process.

    ![Figure 1: Your staged model data handler provides the display name in the Export/Import UI.](../../images/staged-model-display-name.png)

7.  Add methods that import and export your staged model and its references.

        @Override
        protected void doExportStagedModel(
                PortletDataContext portletDataContext, BookmarksEntry entry)
            throws Exception {

            if (entry.getFolderId() !=
                    BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

                StagedModelDataHandlerUtil.exportReferenceStagedModel(
                    portletDataContext, entry, entry.getFolder(),
                    PortletDataContext.REFERENCE_TYPE_PARENT);
            }

            Element entryElement = portletDataContext.getExportDataElement(entry);

            portletDataContext.addClassedModel(
                entryElement, ExportImportPathUtil.getModelPath(entry), entry);
        }

        @Override
        protected void doImportStagedModel(
                PortletDataContext portletDataContext, BookmarksEntry entry)
            throws Exception {

            Map<Long, Long> folderIds =
                (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
                    BookmarksFolder.class);

            long folderId = MapUtil.getLong(
                folderIds, entry.getFolderId(), entry.getFolderId());

            ServiceContext serviceContext =
                portletDataContext.createServiceContext(entry);

            BookmarksEntry importedEntry = null;

            if (portletDataContext.isDataStrategyMirror()) {

                BookmarksEntry existingEntry =
                    _bookmarksEntryLocalService. fetchBookmarksEntryByUuidAndGroupId(
                        entry.getUuid(), portletDataContext.getScopeGroupId());

                if (existingEntry == null) {

                    serviceContext.setUuid(entry.getUuid());
                    importedEntry = _bookmarksEntryLocalService.addEntry(					
                      userId, portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(), serviceContext);
                }
                else {
                    importedEntry = _bookmarksEntryLocalService.updateEntry(
                        userId, existingEntry.getEntryId(), portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(),	serviceContext);
                }
            }
            else {
                importedEntry = _bookmarksEntryLocalService.addEntry(userId, portletDataContext.getScopeGroupId(), folderId,entry.getName(), entry.getUrl(), entry.getDescription(),	serviceContext);
            }

            portletDataContext.importClassedModel(entry, importedEntry);
        }

8.  Add the `doImportMissingReference` method to your class:

        @Override
        protected void doImportMissingReference(
                PortletDataContext portletDataContext, String uuid, long groupId,
                long entryId)
            throws Exception {

            BookmarksEntry existingEntry = fetchMissingReference(uuid, groupId);

            if (existingEntry == null) {
                return;
            }

            Map<Long, Long> entryIds =
                (Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
                    BookmarksEntry.class);

            entryIds.put(entryId, existingEntry.getEntryId());
        }

Fantastic! You've created a data handler for your staged model. The
Export/Import framework can now track your entity's behavior and data. Be sure
to also implement a portlet data handler to manage portlet specific data. See
the
[Developing Portlet Data Handlers](/develop/tutorials/-/knowledge_base/7-1/developing-portlet-data-handlers).
to do this for the Bookmarks app.
