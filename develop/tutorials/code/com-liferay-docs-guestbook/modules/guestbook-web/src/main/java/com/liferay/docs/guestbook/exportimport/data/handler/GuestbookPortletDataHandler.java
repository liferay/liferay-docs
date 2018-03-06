
package com.liferay.docs.guestbook.exportimport.data.handler;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalService;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.docs.guestbook.service.permission.GuestbookModelPermission;
import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.xml.Element;

@Component(immediate = true, property = {
	"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
	"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN
	}, 
	service = PortletDataHandler.class
)
public class GuestbookPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "guestbook";

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

	@Override
	protected PortletPreferences doDeleteData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
			GuestbookPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		List<Guestbook> guestbooks = _guestbookLocalService.getGuestbooks(
			portletDataContext.getScopeGroupId());

		for (Guestbook guestbook : guestbooks) {

			List<Entry> entries = _entryLocalService.getEntries(
				portletDataContext.getScopeGroupId(),
				guestbook.getGuestbookId());

			for (Entry entry : entries) {

				_entryLocalService.deleteEntry(entry);
			}
			_guestbookLocalService.deleteGuestbook(guestbook.getGuestbookId());
		}

		return portletPreferences;
	}

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
}