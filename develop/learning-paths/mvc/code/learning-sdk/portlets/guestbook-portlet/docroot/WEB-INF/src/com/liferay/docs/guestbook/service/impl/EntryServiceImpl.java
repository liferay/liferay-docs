/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.base.EntryServiceBaseImpl;
import com.liferay.docs.guestbook.service.permission.EntryPermission;
import com.liferay.docs.guestbook.service.permission.GuestbookModelPermission;
import com.liferay.docs.guestbook.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.docs.guestbook.service.EntryService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Rich Sezov
 * @see com.liferay.docs.guestbook.service.base.EntryServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.EntryServiceUtil
 */
public class EntryServiceImpl extends EntryServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.docs.guestbook.service.EntryServiceUtil} to access the entry remote service.
	 */

	public Entry addEntry(long userId, long guestbookId, String name,
			String email, String message, ServiceContext serviceContext)
			throws PortalException, SystemException {

		GuestbookModelPermission.check(getPermissionChecker(),
				serviceContext.getScopeGroupId(), ActionKeys.ADD_ENTRY);

		return EntryLocalServiceUtil.addEntry(userId, guestbookId, name, email,
				message, serviceContext);
	}

	public Entry deleteEntry(long entryId, ServiceContext serviceContext)
			throws PortalException, SystemException {

		EntryPermission.check(getPermissionChecker(), entryId,
				ActionKeys.DELETE);

		return EntryLocalServiceUtil.deleteEntry(entryId, serviceContext);
	}

	public List<Entry> getEntries(long groupId, long guestbookId)
			throws SystemException {

		return entryPersistence.filterFindByG_G(groupId, guestbookId);
	}

	public List<Entry> getEntries(long groupId, long guestbookId, int start,
			int end) throws SystemException {

		return entryPersistence.filterFindByG_G(groupId, guestbookId, start,
				end);
	}

	public int getEntriesCount(long groupId, long guestbookId)
			throws SystemException {

		return entryPersistence.filterCountByG_G(groupId, guestbookId);
	}

	public Entry updateEntry(long userId, long guestbookId, long entryId,
			String name, String email, String message,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		EntryPermission.check(getPermissionChecker(), entryId,
				ActionKeys.UPDATE);

		return EntryLocalServiceUtil.updateEntry(userId, guestbookId, entryId,
				name, email, message, serviceContext);
	}

}