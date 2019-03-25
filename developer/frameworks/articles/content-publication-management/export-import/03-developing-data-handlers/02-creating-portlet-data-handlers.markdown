---
header-id: creating-portlet-data-handlers
---

# Creating Portlet Data Handlers

[TOC levels=1-4]

In this tutorial, you'll create the `BookmarksPortletModelDataHandler` class
used for the Bookmarks application. The Bookmarks application's portlet data
handler tracks system events dealing with Bookmarks entities. It also configures
the Export/Import and Staging UI options for the Bookmarks application.

1.  Create a new package in your existing Service Builder project for your data
    handler classes. For instance, the Bookmarks application's data handler
    classes reside in the `bookmarks-service` module's
    `com.liferay.bookmarks.internal.exportimport.data.handler` package.

2.  Create your `-PortletDataHandler` class for your application in the new
    `-exportimport.data.handler` package and have it implement the
    [PortletDataHandler](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)
    interface by extending the
    [BasePortletDataHandler](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BasePortletDataHandler.html)
    class. For example,

        public class BookmarksPortletDataHandler extends BasePortletDataHandler {

3.  Create an `@Component` annotation section above the class declaration:

        @Component(
            immediate = true,
            property = {
                "javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS
            },
            service = PortletDataHandler.class
        )

4.  Set what the portlet data handler controls and the portlet's Export/Import
    and Staging UIs by adding an `activate` method:

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
            setStagingControls(getExportControls());
        }

    ![Figure 1: You can select the content types you'd like to export/import in the UI.](../../../../images/export-import-controls.png)

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
    [Invoking Local Services](/developer/frameworks/-/knowledge_base/7-2/invoking-local-services)
    article for more information on using the `@Reference` annotation in
    @product@.

    **Important:** @product@'s official Bookmarks app does not use local
    services in its portlet data handler; instead, it uses the
    [`StagedModelRepository`](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html)
    framework. This is a new framework, but is a viable option when setting up
    your portlet data handlers. For more information on this, see the
    [Providing Entity-Specific Local Services for Staging](/developer/frameworks/-/knowledge_base/7-2/providing-entity-specific-local-services-for-staging)
    tutorial section. Since local services are more widely used in custom apps,
    this tutorial covers those instead.

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

8.  Add a method that counts the number of affected entities based on the
    current export or staging process:

        @Override
        protected void doPrepareManifestSummary(
                PortletDataContext portletDataContext,
                PortletPreferences portletPreferences)
            throws Exception {

            if (ExportImportDateUtil.isRangeFromLastPublishDate(
                    portletDataContext)) {

                _staging.populateLastPublishDateCounts(
                    portletDataContext,
                    new StagedModelType[] {
                        new StagedModelType(BookmarksEntry.class.getName()),
                        new StagedModelType(BookmarksFolder.class.getName())
                    });

            			return;
            		}

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

    ![Figure 2: The number of modified Bookmarks entities are displayed in the Export UI.](../../../../images/manifest-summary-count.png)

9.  Set the XML schema version for the XML files included in your exported LAR
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
[Creating Staged Model Data Handlers](/developer/frameworks/-/knowledge_base/7-2/creating-staged-model-data-handlers)
for more information.
