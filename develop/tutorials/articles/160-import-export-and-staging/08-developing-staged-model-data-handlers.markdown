# Developing Staged Model Data Handlers [](id=developing-staged-model-data-handlers)

There are two types of data handlers you can implement: *Portlet Data Handlers*
and *Staged Model Data Handlers*. For more information on the fundamentals
behind Liferay's data handlers and how a LAR file is constructed, see the
[Understanding Data Handlers](/develop/tutorials/-/knowledge_base/7-1/understanding-data-handlers)
tutorial. In this tutorial, you'll learn how to create a Staged Model Data
Handler for a Bookmarks application.

+$$$

**Note:** You must ensure your application is properly configured to use data
handlers. For more information on how to do this, see the
[Data Handler Fundamentals](/develop/tutorials/-/knowledge_base/7-1/understanding-data-handlers#data-handler-fundamentals)
section.

$$$

A Staged Model Data Handler supplies information about a staged model (entity)
to the Export/Import framework, defining a display name for the UI, deleting an
entity, etc. It's also responsible for exporting referenced content. For
example, if a Bookmarks entry resides in a Bookmarks folder, the
`BookmarksEntry` staged model data handler invokes the export of the
`BookmarksFolder`.

This tutorial assumes you've already created
[staged models](/develop/tutorials/-/knowledge_base/7-1/understanding-staged-models).
The Bookmarks application has two staged models: entries and folders. Creating
data handlers for these two entities is similar, so you'll examine how this is
done for Bookmark entries.

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

3.  Create an `@Component` annotation section above the class declaration. This
    annotation is responsible for registering the class as a staged model data
    handler similar to the portlet data handler.

        @Component(immediate = true, service = StagedModelDataHandler.class)

    The `immediate` element directs the container to activate the component
    immediately once its provided module has started. The `service` element
    should point to the `StagedModelDataHandler.class` interface.

    +$$$

    **Note:** In previous versions of @product@, you had to register the staged
    model data handler in a portlet's `liferay-portlet.xml` file. The
    registration process is now completed automatically by OSGi using the
    `@Component` annotation.

    $$$

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
    [Using the StagedModelRepository Framework (Coming Soon)](/develop/tutorials/-/knowledge_base/7-1/using-the-stagedmodelrepository-framework)
    section. Since local services are more widely used in custom apps, this
    tutorial covers those instead.

5.  You must provide the class names of the models the data handler tracks. You
    can do this by overriding the
    [StagedModelDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandler.html)'s
    `getClassnames()` method:

        public static final String[] CLASS_NAMES = {BookmarksEntry.class.getName()};

        @Override
        public String[] getClassNames() {
            return CLASS_NAMES;
        }

    As a best practice, you should have one staged model data handler per staged
    model. It's possible to use multiple class types, but this is not
    recommended.

6.  Add a method that retrieves the staged model's display name:

        @Override
        public String getDisplayName(BookmarksEntry entry) {
            return entry.getName();
        }

    The display name is presented with the progress bar during the export/import
    process.

    ![Figure 3: Your staged model data handler provides the display name in the Export/Import UI.](../../images/staged-model-display-name.png)

7.  A staged model data handler should ensure everything required for its
    operation is also exported. For example, in the Bookmarks application, an
    entry requires its folder to keep the folder structure intact. Therefore,
    the folder should be exported first followed by the entry.

    Add methods that import and export your staged model and its references.

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

    The `doExportStagedModel` method retrieves the Bookmark entry's data element
    from the
    [PortletDataContext](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html)
    and then adds the class model characterized by that data element to the
    `PortletDataContext`. The `PortletDataContext` is used to populate the LAR
    file with your application's data during the export process. Note that once
    an entity has been exported, subsequent calls to the export method won't
    actually repeat the export process multiple times, ensuring optimal
    performance.

    An important feature of the import process is that all exported reference
    elements in the Bookmarks example are automatically imported when needed.
    The `doImportStagedModel` method does not need to import the reference
    elements manually; it must only find the new assigned ID for the folder
    before importing the entry.

    The `PortletDataContext` keeps this information and a slew of other data
    up-to-date during the import process. The old ID and new ID mapping can be
    reached by using the `portletDataContext.getNewPrimaryKeysMap()` method as
    shown in the example. The method proceeds with checking the import mode
    (e.g., *Copy As New* or *Mirror*) and, depending on the process
    configuration and existing environment, the entry is either added or
    updated.

8.  When importing a LAR that specifies a missing reference, the import process
    expects the reference to be available and must validate that it's there. You
    must add a method that maps the missing reference ID from the export to the
    existing ID during import.

    For example, suppose you export a `FileEntry` as a missing reference with an
    ID of `1`. When importing that information, the LAR only provides the ID but
    not the entry itself. Therefore, during the import process, the Data Handler
    framework searches for the entry to replace, but the entry to replace has a
    different ID of `2`. You must provide a method that maps these two IDs so
    the import process can recognize the missing reference.

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

    This method maps the existing staged model to the old ID in the reference
    element. When a reference is exported as missing, the Data Handler framework
    calls this method during the import process and updates the new primary key
    map in the portlet data context.

Fantastic! You've created a data handler for your staged model. The
Export/Import framework can now track your entity's behavior and data. Be sure
to also implement a portlet data handler to manage portlet specific data. See
the
[Developing Portlet Data Handlers](/develop/tutorials/-/knowledge_base/7-1/developing-portlet-data-handlers).
to do this for the Bookmarks app.
