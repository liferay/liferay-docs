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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link EventService}.
 * </p>
 *
 * @author    Joe Bloggs
 * @see       EventService
 * @generated
 */
public class EventServiceWrapper implements EventService,
	ServiceWrapper<EventService> {
	public EventServiceWrapper(EventService eventService) {
		_eventService = eventService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _eventService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_eventService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _eventService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.nosester.portlet.eventlisting.model.Event addEvent(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventService.addEvent(event);
	}

	public com.nosester.portlet.eventlisting.model.Event addEvent(
		java.lang.String name, java.lang.String description,
		java.util.Date date, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _eventService.addEvent(name, description, date, locationId,
			serviceContext);
	}

	public com.nosester.portlet.eventlisting.model.Event update(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventService.update(event);
	}

	public com.nosester.portlet.eventlisting.model.Event updateEvent(
		long eventId, java.lang.String name, java.lang.String description,
		java.util.Date date, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _eventService.updateEvent(eventId, name, description, date,
			locationId, serviceContext);
	}

	public com.nosester.portlet.eventlisting.model.Event delete(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventService.delete(event);
	}

	public com.nosester.portlet.eventlisting.model.Event deleteEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventService.deleteEvent(eventId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public EventService getWrappedEventService() {
		return _eventService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedEventService(EventService eventService) {
		_eventService = eventService;
	}

	public EventService getWrappedService() {
		return _eventService;
	}

	public void setWrappedService(EventService eventService) {
		_eventService = eventService;
	}

	private EventService _eventService;
}