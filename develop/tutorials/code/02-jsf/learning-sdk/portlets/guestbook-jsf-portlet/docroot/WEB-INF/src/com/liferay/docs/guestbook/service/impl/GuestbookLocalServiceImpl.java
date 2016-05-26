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

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.base.GuestbookLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ResourceConstants;


/**
 * The implementation of the guestbook local service.
 *
 * <p>All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy
 * their definitions into the {@link com.liferay.docs.guestbook.service.GuestbookLocalService} interface.
 *
 * <p>This is a local service. Methods of this service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same VM.</p>
 *
 * @author  Vernon Singleton
 * @see     com.liferay.docs.guestbook.service.base.GuestbookLocalServiceBaseImpl
 * @see     com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil
 */
public class GuestbookLocalServiceImpl extends GuestbookLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil} to access the guestbook local service.
	 */

	@Override
	public Guestbook addGuestbook(Guestbook guestbook, long userId) throws PortalException, SystemException {
		long guestbookId = counterLocalService.increment(Guestbook.class.getName());
		guestbook.setGuestbookId(guestbookId);

		guestbook = super.addGuestbook(guestbook);

		resourceLocalService.addResources(guestbook.getCompanyId(), guestbook.getGroupId(), userId,
			Guestbook.class.getName(), guestbookId, false, true, true);

		return guestbook;
	}

	@Override
	public Guestbook deleteGuestbook(Guestbook guestbook) throws PortalException, SystemException {

		resourceLocalService.deleteResource(guestbook.getCompanyId(), Guestbook.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, guestbook.getPrimaryKey());

		return super.deleteGuestbook(guestbook);
	}

	public Guestbook getFirstGuestbookByName(long groupId, String name) throws SystemException {
		Guestbook guestbook = null;

		List<Guestbook> guestbooks = guestbookPersistence.findByName(groupId, name);

		if ((guestbooks != null) && (guestbooks.size() > 0)) {
			guestbook = guestbooks.get(0);
		}

		return guestbook;
	}

	public List<Guestbook> getGuestbooks(long groupId) throws SystemException {
		return guestbookPersistence.findByGroupId(groupId);
	}

	public List<Guestbook> getGuestbooks(long groupId, int start, int end) throws SystemException {
		return guestbookPersistence.findByGroupId(groupId, start, end);
	}

}
