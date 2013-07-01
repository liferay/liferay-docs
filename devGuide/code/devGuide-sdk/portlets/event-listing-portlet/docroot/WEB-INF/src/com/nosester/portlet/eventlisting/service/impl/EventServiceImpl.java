/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.nosester.portlet.eventlisting.service.impl;

import java.util.Date;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nosester.portlet.eventlisting.model.Event;
import com.nosester.portlet.eventlisting.service.base.EventServiceBaseImpl;

/**
 * The implementation of the event remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.nosester.portlet.eventlisting.service.EventService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author jbloggs
 * @see com.nosester.portlet.eventlisting.service.base.EventServiceBaseImpl
 * @see com.nosester.portlet.eventlisting.service.EventServiceUtil
 */
public class EventServiceImpl extends EventServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.nosester.portlet.eventlisting.service.EventServiceUtil} to access the event remote service.
	 */
	
	public Event addEvent(Event event) throws SystemException {
		
		long eventId = counterLocalService.increment(Event.class.getName());
		event.setEventId(eventId);
		
		Date now = new Date();
		event.setCreateDate(now.getTime());
		event.setModifiedDate(now.getTime());
		
		return eventLocalService.addEvent(event);
	}
	
	public Event update(Event event) throws SystemException {
		
		Date now = new Date();
		event.setModifiedDate(now.getTime());
		
		return eventLocalService.updateEvent(event);
	}	
	
	public Event delete(Event event) throws SystemException {
		
		return eventLocalService.deleteEvent(event);
	}
	
	public Event deleteEvent(long eventId) throws PortalException, SystemException {
		
		return eventLocalService.deleteEvent(eventId);
	}
	
}