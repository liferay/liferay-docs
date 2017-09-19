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

package com.liferay.docs.guestbook.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GuestbookService}.
 *
 * @author liferay
 * @see GuestbookService
 * @generated
 */
@ProviderType
public class GuestbookServiceWrapper implements GuestbookService,
	ServiceWrapper<GuestbookService> {
	public GuestbookServiceWrapper(GuestbookService guestbookService) {
		_guestbookService = guestbookService;
	}

	@Override
	public com.liferay.docs.guestbook.model.Guestbook addGuestbook(
		long userId, java.lang.String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.addGuestbook(userId, name, serviceContext);
	}

	@Override
	public com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
		long guestbookId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.deleteGuestbook(guestbookId, serviceContext);
	}

	@Override
	public com.liferay.docs.guestbook.model.Guestbook updateGuestbook(
		long userId, long guestbookId, java.lang.String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.updateGuestbook(userId, guestbookId, name,
			serviceContext);
	}

	@Override
	public int getGuestbooksCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.getGuestbooksCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _guestbookService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.getGuestbooks(groupId);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.getGuestbooks(groupId, start, end);
	}

	@Override
	public GuestbookService getWrappedService() {
		return _guestbookService;
	}

	@Override
	public void setWrappedService(GuestbookService guestbookService) {
		_guestbookService = guestbookService;
	}

	private GuestbookService _guestbookService;
}