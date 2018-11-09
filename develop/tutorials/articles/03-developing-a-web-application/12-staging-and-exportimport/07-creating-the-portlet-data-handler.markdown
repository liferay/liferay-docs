# Creating the Portlet Data Handler [](id=creating-the-portlet-data-handler)

<div class="learn-path-step">
    <p>Enabling Staging and Export/Import<br>Step 7 of 7</p>
</div>

A Portlet Data Handler imports/exports portlet-specific data to a LAR file. Its
primary role is to query and coordinate between staged model data handlers. It
also configures the Export/Import UI options for the application. For example,
the Guestbook application's portlet data handler should

- import/export portlet-specific data pertaining to the Guestbook app
- track actions dealing with guestbook and entry entities (staged models)
- configure export/import UI options for the Guestbook app

![Figure 1: The Guestbook's portlet data handler must manage the portlet data, staged model data handlers, and UI configuration.](../../../images/guestbook-pdh-diagram.png)

Follow the instructions below to create the Guestbook's portlet data handler.

1.  In your `guestbook-service` module's
    `com.liferay.docs.guestbook.exportimport.data.handler` package, create the
    `GuestbookPortletDataHandler` class.

2.  Extend the
    [`BasePortletDataHandler`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BasePortletDataHandler.html)
    class and add the `@Component` annotation to the class declaration with
    several configured properties like this:

        @Component(
            immediate = true,
            property = {
                "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
                "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN
            }, 
            service = PortletDataHandler.class
        )
        public class GuestbookPortletDataHandler extends BasePortletDataHandler {

    The three set annotation attributes are described below:

    - The `immediate` element tells the container to activate the component
      immediately once its provided module has started.
    - The `property` element sets various properties for the component service.
      You must associate the portlets you wish to handle with this service so
      they function properly in the export/import environment. For example,
      since the Guestbook data handler is used for two portlets, they're both
      configured using the `javax.portlet.name` property.
    - The `service` element should point to the
      [`PortletDataHandler.class`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)
      interface.

3.  Set what the portlet data handler controls and the portlet's Export/Import
    UI by adding an activate method:

        @Activate
        protected void activate() {

            setDeletionSystemEventStagedModelTypes(
                new StagedModelType(Entry.class),
                new StagedModelType(Guestbook.class));

            setExportControls(
                new PortletDataHandlerBoolean(
                    NAMESPACE, "entries", true, false, null,
                    Entry.class.getName()));

            setImportControls(getExportControls());
        }

    This method is called during initialization of the component by using the
    [`@Activate`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Activate.html)
    annotation. It's invoked after dependencies are set and before services are
    registered.

    The three `set` methods called in the `GuestbookPortletDataHandler`'s
    `activate` method are described below:

    - `setDeletionSystemEventStagedModelTypes`: sets the staged model deletions
      that the portlet data handler should track. For the Guestbook application,
      guestbooks and entries are tracked.
    - `setExportControls`: adds fine grained controls over export behavior that
      are rendered in the Export UI. For the Guestbook application, a checkbox
      is added to select Guestbook content (entries) to export.
    - `setImportControls`: adds fine grained controls over import behavior that
      are rendered in the Import UI. For the Guestbook application, a checkbox
      is added to select Guestbook content (entries) to import.

    ![Figure 2: You can select the content types you'd like to export/import in the UI.](../../../images/pdh-entries-ui.png)

4.  Set the entity local services you want to leverage in your portlet data
    handler:

        @Reference(unbind = "-")
        protected void setGuestbookLocalService(
            GuestbookLocalService guestbookLocalService) {

            _guestbookLocalService = guestbookLocalService;
        }

        @Reference(unbind = "-")
        protected void setEntryLocalService(EntryLocalService entryLocalService) {

            _entryLocalService = entryLocalService;
        }

        private GuestbookLocalService _guestbookLocalService;
        private EntryLocalService _entryLocalService;

    This provides access to the entry and guestbook's local services.

5.  Create a namespace for your entities so the Export/Import framework can
    tell your application's entities from other entities in @product@.

        public static final String NAMESPACE = "guestbook";

6.  Your portlet data handler should retrieve the data related to its staged
    model entities so it can properly export/import them. Add this functionality
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
                GuestbookModelPermission.RESOURCE_NAME);

            rootElement.addAttribute(
                "group-id", String.valueOf(portletDataContext.getScopeGroupId()));

            ActionableDynamicQuery guestbookActionableDynamicQuery =
                _guestbookLocalService.getExportActionableDynamicQuery(
                    portletDataContext);
            guestbookActionableDynamicQuery.performActions();

            ActionableDynamicQuery entryActionableDynamicQuery =
                _entryLocalService.getExportActionableDynamicQuery(
                    portletDataContext);
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
                GuestbookModelPermission.RESOURCE_NAME);

            Element guestbooksElement =
                portletDataContext.getImportDataGroupElement(Guestbook.class);

            List<Element> guestbookElements = guestbooksElement.elements();

            for (Element guestbookElement : guestbookElements) {
                StagedModelDataHandlerUtil.importStagedModel(
                    portletDataContext, guestbookElement);
            }

            Element entriesElement =
                portletDataContext.getImportDataGroupElement(Entry.class);

            List<Element> entryElements = entriesElement.elements();

            for (Element entryElement : entryElements) {
                StagedModelDataHandlerUtil.importStagedModel(
                    portletDataContext, entryElement);
            }

            return null;
        }

    The `doExportData` method first checks if anything should be exported. The
    `portletDataContext.getBooleanParameter(...)` method checks if the user
    selected Guestbook entries for export. Later, the `ActionableDynamicQuery`
    framework runs a query against guestbooks and entries to find ones which
    should be exported to the LAR file.

    The `-ActionableDynamicQuery` classes are automatically generated by Service
    Builder and are available in your application's local service. It queries
    the database searching for certain Staging-specific parameters (e.g.,
    `createDate` and `modifiedDate`), and based on those parameters, finds a
    list of exportable records from the staged model data handler.

    The `doImportData` method queries for guestbook and entry data in the
    imported LAR file that should be added to the database. This is done by
    extracting XML elements from the LAR file by using utility methods from the
    [`StagedModelDataHandlerUtil`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandlerUtil.html)
    class. The extracted elements tell @product@ what data to import from the
    LAR file.

7.  Add a method that counts the number of affected entities based on the
    current export or staging process:

        @Override
        protected void doPrepareManifestSummary(
                PortletDataContext portletDataContext,
                PortletPreferences portletPreferences)
            throws Exception {

            ActionableDynamicQuery entryExportActionableDynamicQuery =
                _entryLocalService.getExportActionableDynamicQuery(
                    portletDataContext);
            entryExportActionableDynamicQuery.performCount();

            ActionableDynamicQuery guestbookExportActionableDynamicQuery =
                _guestbookLocalService.getExportActionableDynamicQuery(
                    portletDataContext);
            guestbookExportActionableDynamicQuery.performCount();
        }

    This number is displayed in the Export and Staging UI. Note that since the
    Staging framework traverses the entity graph during export, the built-in
    components provide an approximate value in some cases.

    ![Figure 4: The number of modified Guestbook entities are displayed in the Export UI.](../../../images/guestbook-staging-entity-counter.png)

8.  Organize your imports (*[CTRL]+[SHIFT]+O*), and save the file. **Hint:** Be
    sure to choose the `javax.portlet.PortletPreferences` import package.

Excellent! You've set up your Guestbook's portlet data handler and can now
handle your portlet's data and control its staged model data handlers.

Your Guestbook app is now leveraging the Staging and Export/Import frameworks!
To verify this, when you go to
[enable Staging](/discover/portal/-/knowledge_base/7-0/enabling-staging), you
can now enable it for your Guestbook app.

![Figure 5: Enable the Guestbook Staging functionality.](../../../images/guestbook-staging-enable.png)

You can also navigate to the Guestbook Admin portlet and manage Staging from the
Options menu. This menu also offers a way to export and import
Guestbook LAR files manually. 

![Figure 6: You can manually export and import Guestbook LAR files from the Guestbook Admin portlet.](../../../images/guestbook-export-import.png)

The Guestbook is ready for the staging process!
