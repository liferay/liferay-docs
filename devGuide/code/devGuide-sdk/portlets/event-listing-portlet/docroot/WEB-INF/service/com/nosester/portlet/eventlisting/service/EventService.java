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

package com.nosester.portlet.eventlisting.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * The interface for the event remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Joe Bloggs
 * @see EventServiceUtil
 * @see com.nosester.portlet.eventlisting.service.base.EventServiceBaseImpl
 * @see com.nosester.portlet.eventlisting.service.impl.EventServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface EventService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventServiceUtil} to access the event remote service. Add custom service methods to {@link com.nosester.portlet.eventlisting.service.impl.EventServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.nosester.portlet.eventlisting.model.Event addEvent(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.nosester.portlet.eventlisting.model.Event addEvent(
		java.lang.String name, java.lang.String description,
		java.util.Date date, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext);

	public com.nosester.portlet.eventlisting.model.Event update(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.nosester.portlet.eventlisting.model.Event updateEvent(
		long eventId, java.lang.String name, java.lang.String description,
		java.util.Date date, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext);

	public com.nosester.portlet.eventlisting.model.Event delete(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.nosester.portlet.eventlisting.model.Event deleteEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}