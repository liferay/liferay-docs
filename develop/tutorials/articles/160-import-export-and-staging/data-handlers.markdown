# Data Handlers

A common requirement for many data driven applications is the ability to import
and export data. This *could* be accomplished by accessing your database
directly and running SQL queries to export/import data; however, this has
several drawbacks:

- Working with different database vendors might require customized SQL scripts.
- Access to the database may be tightly controlled, restricting the ability to
  export/import on demand.
- Database administrators may not give you access to the Liferay database.

An easier way to export/import your application's data is to use a Liferay
ARchive (LAR) file. Liferay provides the LAR feature to address the need to
export/import data in a database agnostic manner. So what exactly is a LAR file?

A LAR file is a compressed file (ZIP archive) that can be used to export/import
data from @product@. They can be created for single portlets, pages, or sets of
pages. Portlets that are LAR capable provide an interface to let you control how
its data is imported/exported. There are several @product@ use cases that
require the use of LAR files:

- Backing up and restoring portlet specific data without requiring a full
  database backup.
- Cloning sites.
- Specifying a template to be used for users' public or private pages.
- Using Local Live or Remote Live staging.

To work around database limitations and give your application the ability to
export/import a LAR file, you can implement Data Handlers in your application.
There are two types of data handlers you'll learn about: *Portlet Data Handlers*
and *Staged Model Data Handlers*.

A Portlet Data Handler imports/exports portlet specific data to a LAR file.
These classes only have the role of querying and coordinating between staged
model data handlers. For example, the Bookmarks application's portlet data
handler tracks portlet preferences and system events dealing with Bookmarks
entities. It also configures the Export/Import UI options for the Bookmarks
application.

<!-- Creating Staged Models will be its own tutorial. For now, I'm going to give
a brief intro to them here so readers have a general understanding of them,
which is required to understand Staged Model Data Handlers. -Cody -->

To track each entity of an application for staging, you should create staged
models by implementing the
[StagedModel](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface. Staged models are the parent interface of an entity being handled in
the Staging framework. For example, since the Bookmarks application manages two
entities (folders and entries), it has a staged model for both (e.g.,
`BookmarksEntryStagedModelRepository` and
`BookmarksFolderStagedModelRepository`).

A Staged Model Data Handler is responsible for importing/exporting data related
to the staged model entity.

![Figure 1: The Data Handler framework uses portlet data handlers and staged model data handlers to track portlet and entity behavior, respectively.](../../images/data-handler-diagram.png)

In this tutorial, you'll learn how to create a portlet data handler for your
custom application. Then you'll create a staged model data handler for each
entity your custom application manages. You're not required to implement a
staged model data handler for every entity in your application, but they're
necessary for any entity you want to export/import or have the staging framework
track.

<!-- Bare bones instructions for enabling a project for Staging using Service
Builder is outlined below. This info will go into a separate tutorial at a later
date. -Cody -->

Before beginning, make sure your application is ready for the Export/Import and
Staging frameworks by running Service Builder in your application. To ensure
Service Builder recognizes your entity as a staged model, you must set the
`uuid` attribute to `true` in your `service.xml` file and have the following
columns declared:

- `companyId`
- `groupId`
- `userId`
- `userName`
- `createDate`
- `modifiedDate`

You can learn how to create a `service.xml` file for your application by
visiting the
[Defining an Object-Relational Map with Service Builder](/develop/tutorials/-/knowledge_base/7-0/defining-an-object-relational-map-with-service-builder)
tutorial.

You'll examine how the Bookmarks application implements data handlers. First
you'll start with its portlet data handler implementation.

## Portlet Data Handlers

The following steps create the `BookmarksPortletDataHandler` class used for the
[Bookmarks](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/bookmarks/)
application.

1.  Create a new package in your existing Service Builder project for your data
    handler classes. For instance, the Bookmarks application's data handler
    classes reside in the `com.liferay.bookmarks.service` module's
    `com.liferay.bookmarks.internal.exportimport.data.handler` package.

2.  Create your `-PortletDataHandler` class for your application in the new
    `-exportimport.data.handler` package and have it implement the
    [PortletDataHandler](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)
    interface by extending the
    [BasePortletDataHandler](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BasePortletDataHandler.html)
    class. See the `BookmarksPortletDataHandler` class as an example:

        public class BookmarksPortletDataHandler extends BasePortletDataHandler {

3.  Create an `@Component` annotation section above the class declaration. This
    annotation is responsible for registering the data handler for the portlet.

        @Component(
            immediate = true,
            property = {
                "javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS,
                "javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS_ADMIN
            },
            service = PortletDataHandler.class
        )

    There are a few annotation elements you should set:

    - The `immediate` element directs the factory to start in @product@ when
      it's first created.
    - The `property` element sets the portlets associated with the portlet data
      handler. For example, since the Bookmarks data handler is used for two
      portlets, they're both configured using the `javax.portlet.name` property.
    - The service element should point to the `PortletDataHandler.class`
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
      is added to select Bookmarks content (entry or folder) to export.
    - `setImportControls`: adds fine grained controls over import behavior that
      are rendered in the Import UI. For the Bookmarks application, a checkbox
      is added to select Bookmarks content (entry or folder) to import.

    For more information on these methods, visit the
    [BasePortletDataHandler](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BasePortletDataHandler.html)
    API.

5.  For the Bookmarks portlet data handler to successfully reference its entry
    and folder staged models, you'll need to set them in your class:

        @Reference(
            target = "(model.class.name=com.liferay.bookmarks.model.BookmarksEntry)",
            unbind = "-"
        )
        protected void setBookmarksEntryStagedModelRepository(
            StagedModelRepository<BookmarksEntry>
                bookmarksEntryStagedModelRepository) {

            _bookmarksEntryStagedModelRepository =
                bookmarksEntryStagedModelRepository;
        }

        @Reference(
            target = "(model.class.name=com.liferay.bookmarks.model.BookmarksFolder)",
            unbind = "-"
        )
        protected void setBookmarksFolderStagedModelRepository(
            StagedModelRepository<BookmarksFolder>
                bookmarksFolderStagedModelRepository) {

            _bookmarksFolderStagedModelRepository =
                bookmarksFolderStagedModelRepository;
        }

        private StagedModelRepository<BookmarksEntry>
            _bookmarksEntryStagedModelRepository;
        private StagedModelRepository<BookmarksFolder>
            _bookmarksFolderStagedModelRepository;

    The `set` methods must be annotated with the
    [@Reference](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Reference.html)
    annotation and the `model.class.name` property should specify the staged
    model entity's full package path. Visit the
    [Invoking Liferay Services Locally](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services#invoking-liferay-services-locally)
    section of the *Finding and Invoking Liferay Services* tutorial for more
    information on using the `@Reference` annotation in @product@.

6.  It's a best practice to create a namespace for your entities so the
    Export/Import framework can identify your application's entities from other
    entities in @product@. The Bookmarks application's namespace declaration
    looks like this:

        public static final String NAMESPACE = "bookmarks";

    You'll see how this namespace is used later.

7.  Your application should retrieve the data related to its staged model
    entities so it can properly export/import it. Add this functionality by
    inserting the following methods:

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
                _bookmarksFolderStagedModelRepository.
                    getExportActionableDynamicQuery(portletDataContext);

            folderActionableDynamicQuery.performActions();

            ActionableDynamicQuery entryActionableDynamicQuery =
                _bookmarksEntryStagedModelRepository.
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

    The `doExportData` method runy a query against the database and finds what
    Bookmark entities should be exported to the LAR file. The method first
    retrieves the Bookmarks application's data context, and then it uses the
    [ExportActionableDynamicQuery](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ExportActionableDynamicQuery.html)
    to get Bookmark folder data and the
    [ActionableDynamicQuery](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ActionableDynamicQuery.html)
    to get Bookmark entry data.

    These `-ActionableDynamicQuery` classes are automatically generated by
    Service Builder and are available in your application's local service. It
    queries the database searching for certain Staging-specific parameters
    (e.g., `createDate` and `modifiedDate`), and based on those parameters,
    finds a list of exportable records from the staged model data handler.

    <!-- Dynamic Queries should be documented elsewhere for LRDOCS-328 and 
    linked here. -Cody -->

    The `doImportData` queries for Bookmark entry and folder data in the
    imported LAR file that should be added to the database. This is done by
    extracting XML elements from the LAR file by using utility methods from
    the
    [StagedModelDataHandlerUtil](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandlerUtil.html)
    class. The extracted elements tell @product@ what data to import from the
    LAR file.

8.  Add a method that deletes the portlet's data, which is necessary when
    importing new portlet data.

        @Override
        protected PortletPreferences doDeleteData(
                PortletDataContext portletDataContext, String portletId,
                PortletPreferences portletPreferences)
            throws Exception {

            if (portletDataContext.addPrimaryKey(
                    BookmarksPortletDataHandler.class, "deleteData")) {

                return portletPreferences;
            }

            _bookmarksEntryStagedModelRepository.deleteStagedModels(
                portletDataContext);
            _bookmarksFolderStagedModelRepository.deleteStagedModels(
                portletDataContext);

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
                _bookmarksEntryStagedModelRepository.
                    getExportActionableDynamicQuery(portletDataContext);

            entryExportActionableDynamicQuery.performCount();

            ActionableDynamicQuery folderExportActionableDynamicQuery =
                _bookmarksFolderStagedModelRepository.
                    getExportActionableDynamicQuery(portletDataContext);

            folderExportActionableDynamicQuery.performCount();
        }

    This number is displayed in the Export and Staging UI.

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

## Staged Model Data Handlers

Now that your application has a portlet data handler and has registered staged
models, you'll create the staged model data handlers. The Bookmarks application
has two staged models: entries and folders. Creating data handlers for these two
entities is similar, so you'll examine how this is done for Bookmark entries.

1.  Create a `-StagedModelDataHandler` class in the same folder as its portlet
    data handler class. For Bookmarks, the `BookmarksEntryStagedDataHandler`
    class resides in the `com.liferay.bookmarks.service` module's
    `com.liferay.bookmarks.internal.exportimport.data.handler` package. The
    staged model data handler class should extend the
    [BaseStagedModelDataHandler](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BaseStagedModelDataHandler.html)
    class and the entity type should be specified as its parameter. You can see
    how this was done for the `BookmarksEntryStagedModelDataHandler` class
    below:

        public class BookmarksEntryStagedModelDataHandler
            extends BaseStagedModelDataHandler<BookmarksEntry> {

2.  Create an `@Component` annotation section above the class declaration. This
    annotation is responsible for registering the class as a staged model data
    handler.

        @Component(immediate = true, service = StagedModelDataHandler.class)

    The `immediate` element directs the data handler to activate in @product@
    immediately after deployment. The `service` element should point to the
    `StagedModelDataHandler.class` interface.

    +$$$

    **Note:** In previous versions of @product@, you had to register the staged
    model data handler in a portlet's `liferay-portlet.xml` file. The
    registration process is now completed automatically by OSGi using the
    `@Component` annotation.

    $$$

3.  Create a getter and setter method for the staged model for which you want to
    provide a data handler:

        @Override
        protected StagedModelRepository<BookmarksEntry> getStagedModelRepository() {
            return _stagedModelRepository;
        }

        @Reference(
            target = "(model.class.name=com.liferay.bookmarks.model.BookmarksEntry)",
            unbind = "-"
        )
        protected void setStagedModelRepository(
            StagedModelRepository<BookmarksEntry> stagedModelRepository) {

            _stagedModelRepository = stagedModelRepository;
        }

        private StagedModelRepository<BookmarksEntry> _stagedModelRepository;

    These methods link this data handler with the staged model for bookmark
    entries by using the
    [StagedModelRepository](https://docs.liferay.com/ce/portal/7.0/javadocs/modules/apps/web-experience/export-import/com.liferay.exportimport.api/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html)
    interface. This interface is implemented by the bookmarks entry staged
    model.

4.  You must provide the class names of the models the data handler handles. You
    can do this by overriding the
    [StagedModelDataHandler](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandler.html)'s
    `getClassnames()` method:

        public static final String[] CLASS_NAMES = {BookmarksEntry.class.getName()};

        @Override
        public String[] getClassNames() {
            return CLASS_NAMES;
        }

5.  Add a method that retrieves the staged model's display name:

        @Override
        public String getDisplayName(BookmarksEntry entry) {
            return entry.getName();
        }

    The display name is presented in the UI so users can follow the
    export/import process.

    ![Figure 3: Your staged model data handler provides the display name in the Export/Import UI.](../../images/staged-model-display-name.png)

6.  Add methods that import and export your staged model and its references:

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

            BookmarksEntry importedEntry = (BookmarksEntry)entry.clone();

            importedEntry.setGroupId(portletDataContext.getScopeGroupId());
            importedEntry.setFolderId(folderId);

            BookmarksEntry existingEntry =
                _stagedModelRepository.fetchStagedModelByUuidAndGroupId(
                    entry.getUuid(), portletDataContext.getScopeGroupId());

            if ((existingEntry == null) ||
                !portletDataContext.isDataStrategyMirror()) {

                importedEntry = _stagedModelRepository.addStagedModel(
                    portletDataContext, importedEntry);
            }
            else {
                importedEntry.setEntryId(existingEntry.getEntryId());

                importedEntry = _stagedModelRepository.updateStagedModel(
                    portletDataContext, importedEntry);
            }

            portletDataContext.importClassedModel(entry, importedEntry);
        }

    The `doExportStagedModel` method retrieves the Bookmark entry's data element
    from the
    [PortletDataContext](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html)
    and then adds the classed model characterized by that data element to the
    `PortletDataContext`. The `PortletDataContext` is used to populate the LAR
    file with your application's data during the export process.

    The `doImportStagedModel` method clones the Bookmark entries from the
    incoming LAR file and updates existing entries in the
    [PortletDataContext](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html);
    if there are no entries to update, the imported entries are added to the
    `PortletDataContext`, which is eventually added to the database.

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
