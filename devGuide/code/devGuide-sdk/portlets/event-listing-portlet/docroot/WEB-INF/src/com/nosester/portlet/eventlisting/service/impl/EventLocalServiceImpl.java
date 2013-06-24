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
import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;
import com.nosester.portlet.eventlisting.model.Event;
import com.nosester.portlet.eventlisting.service.base.EventLocalServiceBaseImpl;

/**
 * The implementation of the event local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.nosester.portlet.eventlisting.service.EventLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author jbloggs
 * @see com.nosester.portlet.eventlisting.service.base.EventLocalServiceBaseImpl
 * @see com.nosester.portlet.eventlisting.service.EventLocalServiceUtil
 */
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.nosester.portlet.eventlisting.service.EventLocalServiceUtil} to access the event local service.
	 */

	public Event addEvent(Event event) throws SystemException {
		
		long eventId = counterLocalService.increment(Event.class.getName());
		event.setEventId(eventId);
		
		Date now = new Date();
		event.setCreateDate(now.getTime());
		event.setModifiedDate(now.getTime());
		
		return super.addEvent(event);
	}
	
	public Event updateEvent(Event event) throws SystemException {
		
		Date now = new Date();
		event.setModifiedDate(now.getTime());
		
		return super.updateEvent(event);
	}

	public List<Event> getEventsByGroupId(long groupId) throws SystemException {
		
		return eventPersistence.findByGroupId(groupId);
	}

	public List<Event> getEventsByGroupId(long groupId, int start, int end) throws SystemException {
		
		return eventPersistence.findByGroupId(groupId, start, end);
	}

	public int getEventsCountByGroupId(long groupId) throws SystemException {
		
		return eventPersistence.countByGroupId(groupId);
	}	
	
}