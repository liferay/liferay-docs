/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
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
package com.liferay.docs.guestbook.service.impl;

import java.util.List;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.base.EntryLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ResourceConstants;


/**
 * The implementation of the entry local service.
 *
 * <p>All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy
 * their definitions into the {@link com.liferay.docs.guestbook.service.EntryLocalService} interface.
 *
 * <p>This is a local service. Methods of this service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same VM.</p>
 *
 * @author  Vernon Singleton
 * @see     com.liferay.docs.guestbook.service.base.EntryLocalServiceBaseImpl
 * @see     com.liferay.docs.guestbook.service.EntryLocalServiceUtil
 */
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * com.liferay.docs.guestbook.service.EntryLocalServiceUtil} to access the entry local service.
	 */

	@Override
	public Entry addEntry(Entry entry, long userId) throws PortalException, SystemException {
		long entryId = counterLocalService.increment(Entry.class.getName());
		entry.setEntryId(entryId);

		entry = super.addEntry(entry);

		resourceLocalService.addResources(entry.getCompanyId(), entry.getGroupId(), userId, Entry.class.getName(),
			entryId, false, true, true);

		return entry;
	}

	@Override
	public Entry deleteEntry(Entry entry) throws PortalException, SystemException {

		resourceLocalService.deleteResource(entry.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, entry.getPrimaryKey());

		return super.deleteEntry(entry);
	}

	public List<Entry> getEntries(long groupId, long guestbookId) throws SystemException {
		return entryPersistence.findByG_G(groupId, guestbookId);
	}

	public List<Entry> getEntries(long groupId, long guestbookId, int start, int end) throws SystemException {
		return entryPersistence.findByG_G(groupId, guestbookId, start, end);
	}

	public int getEntriesCount(long groupId, long guestbookId) throws SystemException {
		return entryPersistence.countByG_G(groupId, guestbookId);
	}

}
