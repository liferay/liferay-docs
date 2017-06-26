# Developing Data Handlers [](id=data-handlers)

To leverage the Export/Import framework's ability to export/import a LAR file,
you can implement Data Handlers in your application. There are two types of data
handlers you can implement: *Portlet Data Handlers* and *Staged Model Data
Handlers*. For more information on the fundamentals behind Liferay's data
handlers and how a LAR file is constructed, see the
[Understanding Data Handlers](/develop/tutorials/-/knowledge_base/7-0/understanding-data-handlers)
tutorial. You also must ensure your application is properly configured to use
data handlers; this is also covered in the linked tutorial.

To learn how to implement data handlers for your custom application, you'll
examine how the Bookmarks application does it. First you'll start with its
portlet data handler implementation.

## Portlet Data Handlers [](id=portlet-data-handlers)

The following steps create the `BookmarksPortletDataHandler` class used for the
Bookmarks application.

1.  Create a new package in your existing Service Builder project for your data
    handler classes. For instance, the Bookmarks application's data handler
    classes reside in the `com.liferay.bookmarks.service` module's
    `com.liferay.bookmarks.internal.exportimport.data.handler` package.

2.  Create your `-PortletDataHandler` class for your application in the new
    `-exportimport.data.handler` package and have it implement the
    [PortletDataHandler](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)
    interface by extending the
    [BasePortletDataHandler](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BasePortletDataHandler.html)
    class. See the `BookmarksPortletDataHandler` class as an example:

        public class BookmarksPortletDataHandler extends BasePortletDataHandler {

3.  Create an `@Component` annotation section above the class declaration. This
    annotation registers this class as a portlet data handler in the OSGi
    service registry.

        @Component(
            immediate = true,
            property = {
                "javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS,
                "javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS_ADMIN
            },
            service = PortletDataHandler.class
        )

    There are a few annotation attributes you should set:

    - The `immediate` element directs the container to activate the
      component immediately once its provided module has started.
    - The `property` element sets various properties for the component service.
      You must associate the portlets you wish to handle with this service so
      they function properly in the export/import environment. For example,
      since the Bookmarks data handler is used for two portlets, they're both
      configured using the `javax.portlet.name` property.
    - The `service` element should point to the `PortletDataHandler.class`
      interface.

    +$$$

    **Note:** In previous versions of @product@, you had to register the portlet
    data handler in a portlet's `liferay-portlet.xml` file. The registration
    process is now completed automatically by OSGi using the `@Component`
    annotation.

    $$$

4.  Set what the portlet data handler controls and the portlet's Export/Import
    UI by adding an `activate` method:

        @Activate
        protected void activate() {
            setDataPortletPreferences("rootFolderId");
            setDeletionSystemEventStagedModelTypes(
                new StagedModelType(BookmarksEntry.class),
                new StagedModelType(BookmarksFolder.class));
            setExportControls(
                new PortletDataHandlerBoolean(
                    NAMESPACE, "entries", true, false, null,
                    BookmarksEntry.class.getName()));
            setImportControls(getExportControls());
        }

    This method is called during initialization of the component by using the
    [@Activate](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Activate.html)
    annotation. This method is invoked after dependencies are set and before
    services are registered.

    The four `set` methods called in the `BookmarksPortletDataHandler`'s
    `activate` method are described below:

    - `setDataPortletPreferences`: sets portlet preferences the Bookmarks
      application should handle.
    - `setDeletionSystemEventStagedModelTypes`: sets the staged model deletions
      that the portlet data handler should track. For the Bookmarks application,
      Bookmark entries and folders are tracked.
    - `setExportControls`: adds fine grained controls over export behavior that
      are rendered in the Export UI. For the Bookmarks application, a checkbox
      is added to select Bookmarks content (entries) to export.
    - `setImportControls`: adds fine grained controls over import behavior that
      are rendered in the Import UI. For the Bookmarks application, a checkbox
      is added to select Bookmarks content (entries) to import.

    ![Figure 1: You can select the content types you'd like to export/import in the UI.](../../images/export-import-controls.png)

    For more information on these methods, visit the
    [PortletDataHandler](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)
    API.

5.  For the Bookmarks portlet data handler to reference its entry and folder
    staged models successfully, you must set them in your class:

        @Reference(unbind = "-")
        protected void setBookmarksEntryLocalService(
            BookmarksEntryLocalService bookmarksEntryLocalService) {

            _bookmarksEntryLocalService = bookmarksEntryLocalService;
        }

        @Reference(unbind = "-")
        protected void setBookmarksFolderLocalService(
            BookmarksFolderLocalService bookmarksFolderLocalService) {

            _bookmarksFolderLocalService = bookmarksFolderLocalService;
        }

        private BookmarksEntryLocalService _bookmarksEntryLocalService;
        private BookmarksFolderLocalService _bookmarksFolderLocalService;

    The `set` methods must be annotated with the
    [@Reference](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Reference.html)
    annotation. Visit the
    [Invoking Liferay Services Locally](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services#invoking-liferay-services-locally)
    section of the *Finding and Invoking Liferay Services* tutorial for more
    information on using the `@Reference` annotation in @product@.

6.  You must create a namespace for your entities so the Export/Import framework
    can identify your application's entities from other entities in @product@.
    The Bookmarks application's namespace declaration looks like this:

        public static final String NAMESPACE = "bookmarks";

    You'll see how this namespace is used later.

7.  Your portlet data handler should retrieve the data related to its staged
    model entities so it can properly export/import it. Add this functionality
    by inserting the following methods:

        @Override
        protected String doExportData(
                final PortletDataContext portletDataContext, String portletId,
                PortletPreferences portletPreferences)
            throws Exception {

            Element rootElement = addExportDataRootElement(portletDataContext);

            if (!portletDataContext.getBooleanParameter(NAMESPACE, "entries")) {
                return getExportDataRootElementString(rootElement);
            }

            portletDataContext.addPortletPermissions(
                BookmarksResourcePermissionChecker.RESOURCE_NAME);

            rootElement.addAttribute(
                "group-id", String.valueOf(portletDataContext.getScopeGroupId()));

            ExportActionableDynamicQuery folderActionableDynamicQuery =
                _bookmarksFolderLocalService.
                    getExportActionableDynamicQuery(portletDataContext);

            folderActionableDynamicQuery.performActions();

            ActionableDynamicQuery entryActionableDynamicQuery =
                _bookmarksEntryLocalService.
                    getExportActionableDynamicQuery(portletDataContext);

            entryActionableDynamicQuery.performActions();

            return getExportDataRootElementString(rootElement);
        }

        @Override
        protected PortletPreferences doImportData(
                PortletDataContext portletDataContext, String portletId,
                PortletPreferences portletPreferences, String data)
            throws Exception {

            if (!portletDataContext.getBooleanParameter(NAMESPACE, "entries")) {
                return null;
            }

            portletDataContext.importPortletPermissions(
                BookmarksResourcePermissionChecker.RESOURCE_NAME);

            Element foldersElement = portletDataContext.getImportDataGroupElement(
                BookmarksFolder.class);

            List<Element> folderElements = foldersElement.elements();

            for (Element folderElement : folderElements) {
                StagedModelDataHandlerUtil.importStagedModel(
                    portletDataContext, folderElement);
            }

            Element entriesElement = portletDataContext.getImportDataGroupElement(
                BookmarksEntry.class);

            List<Element> entryElements = entriesElement.elements();

            for (Element entryElement : entryElements) {
                StagedModelDataHandlerUtil.importStagedModel(
                    portletDataContext, entryElement);
            }

            return null;
        }

    The `doExportData` method first checks if anything should be exported. The
    `portletDataContext.getBooleanParameter(...)` method checks if the user
    selected Bookmarks entries for export. Later, the
    `ExportImportActionableDynamicQuery` framework runs a query against bookmarks
    folders and entries to find ones which should be exported to the LAR file.

    The `-ActionableDynamicQuery` classes are automatically generated by Service
    Builder and are available in your application's local service. It queries
    the database searching for certain Staging-specific parameters (e.g.,
    `createDate` and `modifiedDate`), and based on those parameters, finds a
    list of exportable records from the staged model data handler.

    <!-- Dynamic Queries should be documented elsewhere for LRDOCS-328 and 
    linked here. -Cody -->

    The `doImportData` queries for Bookmark entry and folder data in the
    imported LAR file that should be added to the database. This is done by
    extracting XML elements from the LAR file by using utility methods from
    the
    [StagedModelDataHandlerUtil](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandlerUtil.html)
    class. The extracted elements tell @product@ what data to import from the
    LAR file.

8.  Add a method that deletes the portlet's data. The Staging framework has an
    option called *Delete Portlet Data Before Importing* that lets the user
    delete portlet data before importing any new data. The `doDeleteData(...)`
    method is called to execute this deletion operation.

        @Override
        protected PortletPreferences doDeleteData(
                PortletDataContext portletDataContext, String portletId,
                PortletPreferences portletPreferences)
            throws Exception {

            if (portletDataContext.addPrimaryKey(
                    BookmarksPortletDataHandler.class, "deleteData")) {

                return portletPreferences;
            }

            _bookmarksEntryLocalService.deleteEntries(
                portletDataContext.getScopeGroupId(),
                BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);

            _bookmarksFolderLocalService.deleteFolders(
                portletDataContext.getScopeGroupId());

            return portletPreferences;
        }

    This method can also return a modified version of the portlet preferences if
    it contains references to data that no longer exists.

9.  Add a method that counts the number of affected entities based on the
    current export or staging process:

        @Override
        protected void doPrepareManifestSummary(
                PortletDataContext portletDataContext,
                PortletPreferences portletPreferences)
            throws Exception {

            ActionableDynamicQuery entryExportActionableDynamicQuery =
                _bookmarksEntryLocalService.
                    getExportActionableDynamicQuery(portletDataContext);

            entryExportActionableDynamicQuery.performCount();

            ActionableDynamicQuery folderExportActionableDynamicQuery =
                _bookmarksFolderLocalService.
                    getExportActionableDynamicQuery(portletDataContext);

            folderExportActionableDynamicQuery.performCount();
        }

    This number is displayed in the Export and Staging UI. Note that since the
    Staging framework traverses the entity graph during export, the built-in
    components provide an approximate value in some cases.

    ![Figure 2: The number of modified Bookmarks entities are displayed in the Export UI.](../../images/manifest-summary-count.png)

10. Set the XML schema version for the XML files included in your exported LAR
    file:

        public static final String SCHEMA_VERSION = "1.0.0";

        @Override
        public String getSchemaVersion() {
            return SCHEMA_VERSION;
        }

Awesome! You've set up your portlet data handler and your application can now
support the Export/Import framework and display a UI for it. The next step for
supporting data handlers in your app is to implement staged model data handlers
for your staged models. You'll do this next.

## Staged Model Data Handlers [](id=staged-model-data-handlers)

Now that your application has a portlet data handler and staged models, you'll
create the staged model data handlers. The Bookmarks application has two staged
models: entries and folders. Creating data handlers for these two entities is
similar, so you'll examine how this is done for Bookmark entries.

1.  Create a `-StagedModelDataHandler` class in the same folder as its portlet
    data handler class. For Bookmarks, the `BookmarksEntryStagedDataHandler`
    class resides in the `com.liferay.bookmarks.service` module's
    `com.liferay.bookmarks.internal.exportimport.data.handler` package. The
    staged model data handler class should extend the
    [BaseStagedModelDataHandler](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BaseStagedModelDataHandler.html)
    class and the entity type should be specified as its parameter. You can see
    how this was done for the `BookmarksEntryStagedModelDataHandler` class
    below:

        public class BookmarksEntryStagedModelDataHandler
            extends BaseStagedModelDataHandler<BookmarksEntry> {

2.  Create an `@Component` annotation section above the class declaration. This
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

3.  Create a getter and setter method for the local service of the staged model
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

4.  You must provide the class names of the models the data handler tracks. You
    can do this by overriding the
    [StagedModelDataHandler](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandler.html)'s
    `getClassnames()` method:

        public static final String[] CLASS_NAMES = {BookmarksEntry.class.getName()};

        @Override
        public String[] getClassNames() {
            return CLASS_NAMES;
        }

    As a best practice, you should have one staged model data handler per staged
    model. It's possible to use multiple class types, but this is not
    recommended.

5.  Add a method that retrieves the staged model's display name:

        @Override
        public String getDisplayName(BookmarksEntry entry) {
            return entry.getName();
        }

    The display name is presented with the progress bar during the export/import
    process.

    ![Figure 3: Your staged model data handler provides the display name in the Export/Import UI.](../../images/staged-model-display-name.png)

6.  A staged model data handler should ensure everything required for its
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
    [PortletDataContext](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html)
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
    (e.g., *Copy As New* or *Mirror*) and depending on the process configuration
    and existing environment, the entry is either added or updated.

7.  When importing a LAR that specifies a missing reference, the import process
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
Export/Import framework can now track your entity's behavior and data.

With the ability to track entity data, your application is available during the 
Export/Import and Staging processes.
