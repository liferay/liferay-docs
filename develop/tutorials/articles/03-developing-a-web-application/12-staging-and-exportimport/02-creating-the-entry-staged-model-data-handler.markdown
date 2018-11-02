# Creating the Entry Staged Model Data Handler [](id=creating-the-entry-staged-model-data-handler)

<div class="learn-path-step">
    <p>Enabling Staging and Export/Import<br>Step 2 of 7</p>
</div>

A Staged Model Data Handler supplies information about a staged model (entity)
to the Staging and Export/Import framework. Data handlers replace the need to
access the database directly and run queries to export/import data.

You must create a staged model data handler for every entity you want Staging to
track. This means you must create a data handler for both your guestbook and
entry entities.

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

4.  Set the staged model's local services you need in your data handler:

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

    This injects the entry and guestbook's local services.

5.  You must provide the class names of the models the data handler tracks. You
    can do this by overriding the `StagedModelDataHandler`'s `getClassNames()`
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
    and publication processes.

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
    data element to the `PortletDataContext`. The `PortletDataContext` populates
    the [LAR file](/develop/tutorials/-/knowledge_base/7-0/understanding-data-handlers#liferay-archive-lar-file)
    with your application's data during the export process. Note that once an
    entity has been exported, subsequent calls to the export method don't
    repeat the export process multiple times, ensuring optimal performance.

    An important feature of the import process is that all exported reference
    elements are automatically imported when needed. The `doImportStagedModel`
    method does not need to import the reference elements manually; it must only
    find the new assigned ID for the guestbook before importing the entry.

    The `PortletDataContext` keeps this information and a slew of other data
    up-to-date during the import process. The code snippet shows how to access
    the old ID and new ID mapping, by using the
    `portletDataContext.getNewPrimaryKeysMap()` method. The method proceeds with
    checking the import mode (e.g., Copy As New or Mirror) and depending on the
    process configuration and existing environment, the entry is either added or
    updated.

8.  When importing a LAR (i.e., publishing to the live Site), the import process
    expects all of an entity's references to be available and validates their
    existence.

    For example, if you republish an updated guestbook to the live Site and did
    not include some of its existing entries in the publication, these entries
    are considered missing references. A more practical example of this would be
    an image included in a web content article. If the image included in the web
    content lives on a different Site (i.e., the image is contained in a
    different group) or was not included in the publication process, it's
    considered a missing reference of the web content article.

    Since you're dealing with references on two separate Sites that have
    differing IDs, the system can't easily match them during publication.
    Consider this scenario for the Guestbook app; suppose you export a guestbook
    entry as a missing reference with a primary key (ID) of `1`. When importing
    that information, the LAR only provides the ID but not the entry itself.
    Therefore, during the import process, the Data Handler framework searches
    for the entry to replace by its UUID, but the entry to replace has
    a different ID (primary key) of `2`. You must provide a way to handle these
    missing references.

    To do this, you must add a method that maps the missing reference's primary
    key from the export to the existing primary key during import. Since the
    reference's UUID is constant across systems, it's used to complete the
    mapping of differing primary keys. Note that a reference can only be missing
    on the live Site if it has already been published previously. Therefore,
    when publishing a guestbook for the first time, the system doesn't check for
    missing references.

    Add this method to your class: 

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
    company ID (i.e., portal instance's primary key) or group ID (i.e., Site,
    Organization, or User Group's primary key).

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
