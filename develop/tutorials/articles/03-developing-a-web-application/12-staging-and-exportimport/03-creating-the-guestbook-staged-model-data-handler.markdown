# Creating the Guestbook Staged Model Data Handler [](id=creating-the-guestbook-staged-model-data-handler)

The guestbook's staged model data handler is similar to the entry's data
handler. Refer to the previous article for any in-depth code analysis.

1.  In the `guestbook-service` module's
    `com.liferay.docs.guestbook.exportimport.data.handler` package, create the
    `GuestbookStagedModelDataHandler` class.

2.  Declare `BaseStagedModelDataHandler<STAGED_MODEL>` as your extension class
    and add the `@Component` declaration to declare `StagedModelDataHandler` as
    your implemented service:

        @Component(
            immediate = true, 
            service = StagedModelDataHandler.class
        )
        public class GuestbookStagedModelDataHandler
            extends BaseStagedModelDataHandler<Guestbook> {

3.  Set the staged model's local service you want to leverage in your data
    handler:

        @Reference(unbind = "-")
        protected void setGuestbookLocalService(
            GuestbookLocalService guestbookLocalService) {

            _guestbookLocalService = guestbookLocalService;
        }

        private GuestbookLocalService _guestbookLocalService;

4.  Add the methods to retrieve the guestbook staged model's classes to track
    and display names:

        public static final String[] CLASS_NAMES = {
            Guestbook.class.getName()
        };

        @Override
        public String[] getClassNames() {

            return CLASS_NAMES;
        }

        @Override
        public String getDisplayName(Guestbook guestbook) {

            return guestbook.getName();
        }

5.  Add methods to ensure all import/export information is provided to the
    Staging framework for your guestbook entity:

        @Override
        protected void doExportStagedModel(
                PortletDataContext portletDataContext, Guestbook guestbook)
            throws Exception {

            Element guestbookElement =
                portletDataContext.getExportDataElement(guestbook);

            portletDataContext.addClassedModel(
                guestbookElement, ExportImportPathUtil.getModelPath(guestbook),
                guestbook);
        }

        @Override
        protected void doImportStagedModel(
                PortletDataContext portletDataContext, Guestbook guestbook)
            throws Exception {

            long userId = portletDataContext.getUserId(guestbook.getUserUuid());

            Map<Long, Long> guestbookIds =
                (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                Guestbook.class);

            long guestbookId = MapUtil.getLong(
                guestbookIds, guestbook.getGuestbookId(),
                guestbook.getGuestbookId());

            Guestbook importedGuestbook = null;

            ServiceContext serviceContext =
                portletDataContext.createServiceContext(guestbook);

            if (portletDataContext.isDataStrategyMirror()) {

                Guestbook existingGuestbook = fetchStagedModelByUuidAndGroupId(
                    guestbook.getUuid(), portletDataContext.getScopeGroupId());

                if (existingGuestbook == null) {
                    serviceContext.setUuid(guestbook.getUuid());

                    importedGuestbook = _guestbookLocalService.addGuestbook(
                        userId, guestbook.getName(), serviceContext);
                }
                else {
                    importedGuestbook = _guestbookLocalService.updateGuestbook(
                        userId, existingGuestbook.getGuestbookId(), guestbook.getName(), serviceContext);

                }
            }
            else {
                importedGuestbook = _guestbookLocalService.addGuestbook(
                    userId, guestbook.getName(), serviceContext);
            }

            portletDataContext.importClassedModel(guestbook, importedGuestbook);
        }

    Similar to the guestbook entry, these methods add export/import information
    to the `PortletDataContext`.

6.  Add a method that maps the missing reference ID from the export to the
    existing ID during import:

        @Override
        protected void doImportMissingReference(
                PortletDataContext portletDataContext, String uuid, long groupId,
                long guestbookId)
            throws Exception {

            Guestbook existingGuestbook = fetchMissingReference(uuid, groupId);

            if (existingGuestbook == null) {
                return;
            }

            Map<Long, Long> guestbookIds =
                (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                Guestbook.class);

            guestbookIds.put(guestbookId, existingGuestbook.getGuestbookId());
        }

7.  Provide a way for the staged model data handler to fetch your staged models:

        @Override
        public Guestbook fetchStagedModelByUuidAndGroupId(
            String uuid, long groupId) {

            return _guestbookLocalService.fetchGuestbookByUuidAndGroupId(
                uuid, groupId);
        }

        @Override
        public List<Guestbook> fetchStagedModelsByUuidAndCompanyId(
            String uuid, long companyId) {

            return _guestbookLocalService.getGuestbooksByUuidAndCompanyId(
                uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
                new StagedModelModifiedDateComparator<Guestbook>());
        }

8.  Override the `BaseStagedModelDataHandler`'s delete methods to leverage your
    newly created fetch method and custom local service:

        @Override
        public void deleteStagedModel(
                String uuid, long groupId, String className, String extraData)
            throws PortalException {

            Guestbook guestbook = fetchStagedModelByUuidAndGroupId(uuid, groupId);

            if (guestbook != null) {
                deleteStagedModel(guestbook);
            }
        }

        @Override
        public void deleteStagedModel(Guestbook guestbook)
            throws PortalException {

            _guestbookLocalService.deleteGuestbook(guestbook);
        }

9.  Organize your imports (*[CTRL]+[SHIFT]+O*), and save the file.

Your guestbook staged model data handler is ready to go! Next, you'll begin
updating your guestbook's permissions to account for staging.
