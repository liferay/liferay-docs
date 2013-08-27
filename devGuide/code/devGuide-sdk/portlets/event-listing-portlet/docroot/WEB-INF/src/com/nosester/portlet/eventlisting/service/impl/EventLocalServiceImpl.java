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

package com.nosester.portlet.eventlisting.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.nosester.portlet.eventlisting.NoSuchEventException;
import com.nosester.portlet.eventlisting.model.Event;
import com.nosester.portlet.eventlisting.service.EventLocalServiceUtil;
import com.nosester.portlet.eventlisting.service.base.EventLocalServiceBaseImpl;
import com.nosester.portlet.eventlisting.service.persistence.EventFinderUtil;

/**
 * The implementation of the event local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the {@link
 * com.nosester.portlet.eventlisting.service.EventLocalService} interface.  <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Joe Bloggs
 * @see    com.nosester.portlet.eventlisting.service.base.EventLocalServiceBaseImpl
 * @see    com.nosester.portlet.eventlisting.service.EventLocalServiceUtil
 */
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {

	public Event addEvent(
			long groupId, String name, String description, int month, int day,
			int year, int hour, int minute, long locationId,
			ServiceContext serviceContext)
		throws SystemException {

		Date now = new Date();

		long eventId = counterLocalService.increment(Event.class.getName());

		Event event = eventPersistence.create(eventId);

		event.setName(name);
		event.setDescription(description);

		Calendar dateCal = CalendarFactoryUtil.getCalendar();
		dateCal.set(year, month, day, hour, minute);
		Date date = dateCal.getTime();
		event.setDate(date);

		event.setLocationId(locationId);

		long userId = 0;
		try {
			userId = serviceContext.getGuestOrUserId();
		} catch (PortalException pe) {
			pe.printStackTrace();
		} catch (SystemException se) {
			se.printStackTrace();
		}

		event.setUserId(userId);
		event.setCompanyId(serviceContext.getCompanyId());
		event.setGroupId(groupId);
		event.setCreateDate(now);
		event.setModifiedDate(now);

		super.addEvent(event);

		return event;
	}

	public Event deleteEvent(Event event) throws SystemException {

		return eventPersistence.remove(event);
	}

	public Event deleteEvent(long eventId)
		throws NoSuchEventException, SystemException {

		Event event = eventPersistence.findByPrimaryKey(eventId);

		return deleteEvent(event);
	}

	public List<Event> findByEventNameEventDescriptionLocationName(String eventName, String eventDescription, String locationName,
	int begin, int end) throws SystemException {

	return EventFinderUtil.findByEventNameEventDescriptionLocationName(eventName, eventDescription, locationName, begin, end);
	}

	public Event getEvent(long eventId) throws SystemException, PortalException {

		return eventPersistence.findByPrimaryKey(eventId);
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

	public Event updateEvent(
			long eventId, String name, String description, int month, int day,
			int year, int hour, int minute, long locationId,
			ServiceContext serviceContext)
		throws SystemException {

		Event event = null;
		try {
			event = EventLocalServiceUtil.fetchEvent(eventId);
		} catch (SystemException e) {
			e.printStackTrace();
		}

		event.setModifiedDate(serviceContext.getModifiedDate(null));
		event.setName(name);
		event.setDescription(description);

		Calendar dateCal = CalendarFactoryUtil.getCalendar();
		dateCal.set(year, month, day, hour, minute);
		Date date = dateCal.getTime();
		event.setDate(date);

		event.setLocationId(locationId);

		super.updateEvent(event);

		return event;
	}

}