/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.guestbook.exportimport.data.handler;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalService;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
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
public class EntryStagedModelDataHandler
	extends BaseStagedModelDataHandler<Entry> {

	public static final String[] CLASS_NAMES = {
		Entry.class.getName()
	};

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

	@Override
	public String[] getClassNames() {

		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Entry entry) {

		return entry.getName();
	}

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
	protected void doImportMissingReference(
		PortletDataContext portletDataContext, String uuid, long groupId,
		long entryId)
		throws Exception {

		Entry existingEntry = fetchMissingReference(uuid, groupId);

		Map<Long, Long> entryIds =
			(Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
				Entry.class);

		entryIds.put(entryId, existingEntry.getEntryId());
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
}