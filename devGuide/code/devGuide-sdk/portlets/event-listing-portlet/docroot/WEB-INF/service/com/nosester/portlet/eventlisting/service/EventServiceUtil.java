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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the event remote service. This utility wraps {@link com.nosester.portlet.eventlisting.service.impl.EventServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Joe Bloggs
 * @see EventService
 * @see com.nosester.portlet.eventlisting.service.base.EventServiceBaseImpl
 * @see com.nosester.portlet.eventlisting.service.impl.EventServiceImpl
 * @generated
 */
public class EventServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.nosester.portlet.eventlisting.service.impl.EventServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.nosester.portlet.eventlisting.model.Event addEvent(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addEvent(event);
	}

	public static com.nosester.portlet.eventlisting.model.Event addEvent(
		java.lang.String name, java.lang.String description,
		java.util.Date date, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .addEvent(name, description, date, locationId, serviceContext);
	}

	public static com.nosester.portlet.eventlisting.model.Event update(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().update(event);
	}

	public static com.nosester.portlet.eventlisting.model.Event updateEvent(
		long eventId, java.lang.String name, java.lang.String description,
		java.util.Date date, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .updateEvent(eventId, name, description, date, locationId,
			serviceContext);
	}

	public static com.nosester.portlet.eventlisting.model.Event delete(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().delete(event);
	}

	public static com.nosester.portlet.eventlisting.model.Event deleteEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteEvent(eventId);
	}

	public static void clearService() {
		_service = null;
	}

	public static EventService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					EventService.class.getName());

			if (invokableService instanceof EventService) {
				_service = (EventService)invokableService;
			}
			else {
				_service = new EventServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(EventServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(EventService service) {
	}

	private static EventService _service;
}