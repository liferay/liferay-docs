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

package com.liferay.docs.guestbook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GuestbookService}.
 *
 * @author Rich Sezov
 * @see GuestbookService
 * @generated
 */
public class GuestbookServiceWrapper implements GuestbookService,
	ServiceWrapper<GuestbookService> {
	public GuestbookServiceWrapper(GuestbookService guestbookService) {
		_guestbookService = guestbookService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _guestbookService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_guestbookService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _guestbookService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.docs.guestbook.model.Guestbook addGuestbook(
		long userId, java.lang.String name,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.addGuestbook(userId, name, serviceContext);
	}

	@Override
	public com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
		long guestbookId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.deleteGuestbook(guestbookId, serviceContext);
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
	public com.liferay.docs.guestbook.model.Guestbook getGuestbookByG_N(
		long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.getGuestbookByG_N(groupId, name,
			orderByComparator);
	}

	@Override
	public int getGuestbooksCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.getGuestbooksCount(groupId);
	}

	@Override
	public com.liferay.docs.guestbook.model.Guestbook updateGuestbook(
		long userId, long guestbookId, java.lang.String name,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookService.updateGuestbook(userId, guestbookId, name,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public GuestbookService getWrappedGuestbookService() {
		return _guestbookService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedGuestbookService(GuestbookService guestbookService) {
		_guestbookService = guestbookService;
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