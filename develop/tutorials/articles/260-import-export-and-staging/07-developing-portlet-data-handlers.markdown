# Developing Portlet Data Handlers [](id=developing-portlet-data-handlers)

There are two types of data handlers you can implement: *Portlet Data Handlers*
and *Staged Model Data Handlers*. For more information on the fundamentals
behind Liferay's data handlers and how a LAR file is constructed, see the
[Understanding Data Handlers](/develop/tutorials/-/knowledge_base/7-1/understanding-data-handlers)
tutorial. In this tutorial, you'll create a Portlet Data Handler for a Bookmarks
application.

+$$$

**Note:** You must ensure your application is properly configured to use data
handlers. For more information on how to do this, see the
[Data Handler Fundamentals](/develop/tutorials/-/knowledge_base/7-1/understanding-data-handlers#data-handler-fundamentals)
section.

$$$

A Portlet Data Handler imports/exports portlet specific data to a LAR file.
These classes only have the role of querying and coordinating between
[staged model data handlers](/develop/tutorials/-/knowledge_base/developing-staged-model-data-handlers).
For example, the Bookmarks application's portlet data handler tracks system
events dealing with Bookmarks entities. It also configures the Export/Import UI
options for the Bookmarks application.

The following steps create the `BookmarksPortletDataHandler` class used for the
Bookmarks application.

1.  Create a new package in your existing Service Builder project for your data
    handler classes. For instance, the Bookmarks application's data handler
    classes reside in the `bookmarks-service` module's
    `com.liferay.bookmarks.internal.exportimport.data.handler` package.

2.  Create your `-PortletDataHandler` class for your application in the new
    `-exportimport.data.handler` package and have it implement the
    [PortletDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)
    interface by extending the
    [BasePortletDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BasePortletDataHandler.html)
    class. For example,

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
    [PortletDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)
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
    [Invoking Liferay Services Locally](/develop/tutorials/-/knowledge_base/7-1/finding-and-invoking-liferay-services#invoking-liferay-services-locally)
    section for more information on using the `@Reference` annotation in
    @product@.

    **Important:** @product@'s official Bookmarks app does not use local
    services in its portlet data handler; instead, it uses the
    [StagedModelRepository](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html)
    framework. This is a new framework, but is a viable option when setting up
    your portlet data handlers. For more information on this, see the
    [Using the StagedModelRepository Framework (Coming Soon)](/develop/tutorials/-/knowledge_base/7-1/using-the-stagedmodelrepository-framework)
    section. Since local services are more widely used in custom apps, this
    tutorial covers those instead.

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
                BookmarksConstants.RESOURCE_NAME);

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
                BookmarksConstants.RESOURCE_NAME);

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
    `ExportImportActionableDynamicQuery` framework runs a query against
    bookmarks folders and entries to find ones which should be exported to the
    LAR file.

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
    [StagedModelDataHandlerUtil](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandlerUtil.html)
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

        @Override
        public boolean validateSchemaVersion(String schemaVersion) {
            return _portletDataHandlerHelper.validateSchemaVersion(
                schemaVersion, getSchemaVersion());
        }

Awesome! You've set up your portlet data handler and your application can now
support the Export/Import framework and display a UI for it. Be sure to also
implement staged model data handlers for your staged models. See the
[Developing Staged Model Data Handlers](/develop/tutorials/-/knowledge_base/7-1/developing-staged-model-data-handlers)
to do this for the Bookmarks app.
