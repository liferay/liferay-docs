package com.liferay.docs.guestbook.exportimport.data.handler;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;

@Component(
	immediate = true, 
	service = StagedModelDataHandler.class
)
public class GuestbookStagedModelDataHandler
	extends BaseStagedModelDataHandler<Guestbook> {

	public static final String[] CLASS_NAMES = {
		Guestbook.class.getName()
	};

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

	@Override
	public String[] getClassNames() {

		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Guestbook guestbook) {

		return guestbook.getName();
	}

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

	@Reference(unbind = "-")
	protected void setGuestbookLocalService(
		GuestbookLocalService guestbookLocalService) {

		_guestbookLocalService = guestbookLocalService;
	}

	private GuestbookLocalService _guestbookLocalService;
}