# Creating the Entry Staged Model Data Handler

A Staged Model Data Handler supplies information about a staged model (entity)
to the Staging and Export/Import framework. Data handlers replace the need to
manually access the database directly and run queries to export/import data.

You're required to create a staged model data handler for every entity you want
Staging to track. This means you must create a data handler for both your
guestbook and entry entities.

First, you'll create a staged model data handler for guestbook entries.

1.  In your `guestbook-service` module, create a package named
    `com.liferay.docs.guestbook.exportimport.data.handler`.

2.  In that package, create the `EntryStagedModelDataHandler` class and have
    it extend the
    [`BaseStagedModelDataHandler<STAGED_MODEL>`](@platform-ref@/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BaseStagedModelDataHandler.html)
    class:

        public class EntryStagedModelDataHandler
            extends BaseStagedModelDataHandler<Entry> {

3.  Add an `@Component` annotation above the class declaration to declare that
    the `EntryStagedModelDataHandler` class provides an implementation of
    the [`StagedModelDataHandler`](@platform-ref@/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandler.html)
    service:

        @Component(
            immediate = true, 
            service = StagedModelDataHandler.class
        )

4.  Set the staged model's local services you want to leverage in your data
    handler:

        @Reference(unbind = "-")
        protected void setEntryLocalService(EntryLocalService entryLocalService) {

            _entryLocalService = entryLocalService;
        }

        @Reference(unbind = "-")
        protected void setGuestbookLocalService(
            GuestbookLocalService guestbookLocalService) {

            _guestbookLocalService = guestbookLocalService;
        }

        private EntryLocalService _entryLocalService;
        private GuestbookLocalService _guestbookLocalService;

    This logic provides access to the entry and guestbook's local services.

5.  You must provide the class names of the models the data handler tracks. You
    can do this by overriding the `StagedModelDataHandler`'s `getClassnames()`
    method:

        public static final String[] CLASS_NAMES = {Entry.class.getName()};

        @Override
        public String[] getClassNames() {
        		return CLASS_NAMES;
        }

    As a best practice, you should have one staged model data handler per staged
    model. It's possible to use multiple class types, but this is not
    recommended.

6.  Add a method that retrieves the staged model's display name:

        @Override
        public String getDisplayName(Entry entry) {
            return entry.getName();
        }

    The display name is presented with the progress bar during the export/import
    process.

    ![Figure 1: Your staged model data handler provides the display name in the Publish to Live UI.](../../../images/guestbook-staging-display-name.png)

7.  A staged model data handler should ensure everything required for its
    operation is also imported/exported. For example, an entry requires a
    guestbook. Therefore, the guestbook should be exported first followed by the
    entry.

    Add methods that import and export your staged model and its references.

        @Override
        protected void doExportStagedModel(
                PortletDataContext portletDataContext, Entry entry)
            throws Exception {
    
            Guestbook guestbook =
                _guestbookLocalService.getGuestbook(entry.getGuestbookId());
    
            StagedModelDataHandlerUtil.exportReferenceStagedModel(
                portletDataContext, entry, guestbook,
                PortletDataContext.REFERENCE_TYPE_PARENT);
    
            Element entryElement = portletDataContext.getExportDataElement(entry);
    
            portletDataContext.addClassedModel(
                entryElement, ExportImportPathUtil.getModelPath(entry), entry);
    	}

        @Override
        protected void doImportStagedModel(
                PortletDataContext portletDataContext, Entry entry)
            throws Exception {
      
            long userId = portletDataContext.getUserId(entry.getUserUuid());
      
            Map<Long, Long> guestbookIds =
                (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                    Guestbook.class);
      
            long guestbookId = MapUtil.getLong(
                guestbookIds, entry.getGuestbookId(), entry.getGuestbookId());
      
            Entry importedEntry = null;
      
            ServiceContext serviceContext =
                portletDataContext.createServiceContext(entry);
      
            if (portletDataContext.isDataStrategyMirror()) {
                Entry existingEntry = fetchStagedModelByUuidAndGroupId(
                    entry.getUuid(), portletDataContext.getScopeGroupId());
      
                if (existingEntry == null) {
                    serviceContext.setUuid(entry.getUuid());
      
                    importedEntry = _entryLocalService.addEntry(
                        userId, guestbookId, entry.getName(), entry.getEmail(),
                        entry.getMessage(), serviceContext);
                }
                else {
                    importedEntry = _entryLocalService.updateEntry(
                        userId, guestbookId, existingEntry.getEntryId(),
                        entry.getName(), entry.getEmail(), entry.getMessage(),
                        serviceContext);
                }
            }
            else {
                importedEntry = _entryLocalService.addEntry(
                    userId, guestbookId, entry.getName(), entry.getEmail(),
                    entry.getMessage(), serviceContext);
            }
      
            portletDataContext.importClassedModel(entry, importedEntry);
        }

    The `doExportStagedModel` method retrieves the entry's data element from the
    `PortletDataContext` and then adds the class model characterized by that
    data element to the `PortletDataContext`. The `PortletDataContext` is used
    to populate the
    [LAR file](/develop/tutorials/-/knowledge_base/7-0/understanding-data-handlers#liferay-archive-lar-file)
    with your application's data during the export process. Note that once an
    entity has been exported, subsequent calls to the export method won't
    actually repeat the export process multiple times, ensuring optimal
    performance.

    An important feature of the import process is that all exported reference
    elements are automatically imported when needed. The `doImportStagedModel`
    method does not need to import the reference elements manually; it must only
    find the new assigned ID for the guestbook before importing the entry.

    The `PortletDataContext` keeps this information and a slew of other data
    up-to-date during the import process. The old ID and new ID mapping can be
    reached by using the `portletDataContext.getNewPrimaryKeysMap()` method as
    shown in the code snippet. The method proceeds with checking the import mode
    (e.g., Copy As New or Mirror) and depending on the process configuration and
    existing environment, the entry is either added or updated.

8.  When importing a LAR that specifies a missing reference, the import process
    expects the reference to be available and must validate that it's there. You
    must add a method that maps the missing reference ID from the export to the
    existing ID during import.

    For example, suppose you export a guestbook entry as a missing reference
    with an ID of `1`. When importing that information, the LAR only provides
    the ID but not the entry itself. Therefore, during the import process, the
    Data Handler framework searches for the entry to replace, but the entry to
    replace has a different ID of `2`. You must provide a method that maps these
    two IDs so the import process can recognize the missing reference.

        @Override
        protected void doImportMissingReference(
            PortletDataContext portletDataContext, String uuid, long groupId,
            long entryId)
        throws Exception {

            Entry existingEntry = fetchMissingReference(uuid, groupId);

            if (existingEntry == null) {
                return;
            }

            Map<Long, Long> entryIds =
                (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                Entry.class);

            entryIds.put(entryId, existingEntry.getEntryId());
        }

    This method maps the existing staged model to the old ID in the reference
    element. When a reference is exported as missing, the Data Handler framework
    calls this method during the import process and updates the new primary key
    map in the portlet data context.

9.  Provide a way for the staged model data handler to fetch your staged models:

        @Override
        public Entry fetchStagedModelByUuidAndGroupId(String uuid, long groupId) {

            return _entryLocalService.fetchEntryByUuidAndGroupId(uuid, groupId);
        }

        @Override
        public List<Entry> fetchStagedModelsByUuidAndCompanyId(
            String uuid, long companyId) {

            return _entryLocalService.getEntriesByUuidAndCompanyId(
                uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
                new StagedModelModifiedDateComparator<Entry>());
        }

    These methods use the entry's local service to get the entries by UUID and
    company ID (i.e., portal instance's primary key) or group ID (i.e., site,
    organization, or user group's primary key).

10. Override the `BaseStagedModelDataHandler`'s delete methods to leverage your
    newly created fetch method and custom local service:

        @Override
        public void deleteStagedModel(
                String uuid, long groupId, String className, String extraData)
            throws PortalException {

            Entry entry = fetchStagedModelByUuidAndGroupId(uuid, groupId);

            if (entry != null) {
                deleteStagedModel(entry);
            }

        }

        @Override
        public void deleteStagedModel(Entry entry)
            throws PortalException {

            _entryLocalService.deleteEntry(entry);
        }

  	These methods are necessary for the Staging framework to properly delete
    your entry staged models.

11. Organize your imports (*[CTRL]+[SHIFT]+O*), and save the file.

The entry's staged model data handler is complete! Next you can create the
guestbook's staged model data handler.
