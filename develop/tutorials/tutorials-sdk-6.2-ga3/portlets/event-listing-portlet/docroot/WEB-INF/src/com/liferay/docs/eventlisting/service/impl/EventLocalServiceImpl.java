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

package com.liferay.docs.eventlisting.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.liferay.docs.eventlisting.model.Event;
import com.liferay.docs.eventlisting.service.EventLocalServiceUtil;
import com.liferay.docs.eventlisting.service.base.EventLocalServiceBaseImpl;
import com.liferay.docs.eventlisting.service.persistence.EventFinderUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the event local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.docs.eventlisting.service.EventLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Joe Bloggs
 * @see com.liferay.docs.eventlisting.service.base.EventLocalServiceBaseImpl
 * @see com.liferay.docs.eventlisting.service.EventLocalServiceUtil
 */
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.liferay.docs.eventlisting.service.EventLocalServiceUtil} to access
	 * the event local service.
	 */

	public Event addEvent(long userId, long groupId, String name,
			String description, int month, int day, int year, int hour,
			int minute, long locationId, ServiceContext serviceContext)
			throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		long eventId = counterLocalService.increment(Event.class.getName());

		Event event = eventPersistence.create(eventId);

		event.setName(name);
		event.setDescription(description);

		Calendar dateCal = CalendarFactoryUtil.getCalendar(user.getTimeZone());
		dateCal.set(year, month, day, hour, minute);
		Date date = dateCal.getTime();
		event.setDate(date);

		event.setLocationId(locationId);

		event.setGroupId(groupId);
		event.setCompanyId(user.getCompanyId());
		event.setUserId(user.getUserId());
		event.setCreateDate(serviceContext.getCreateDate(now));
		event.setModifiedDate(serviceContext.getModifiedDate(now));

		super.addEvent(event);

		// Resources

		if (serviceContext.isAddGroupPermissions()
				|| serviceContext.isAddGuestPermissions()) {

			addEventResources(event, serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());
		} else {
			addEventResources(event, serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		return event;
	}

	public void addEventResources(Event event, boolean addGroupPermissions,
			boolean addGuestPermissions) throws PortalException,
			SystemException {

		resourceLocalService.addResources(event.getCompanyId(),
				event.getGroupId(), event.getUserId(), Event.class.getName(),
				event.getEventId(), false, addGroupPermissions,
				addGuestPermissions);
	}

	public void addEventResources(Event event, String[] groupPermissions,
			String[] guestPermissions) throws PortalException, SystemException {

		resourceLocalService.addModelResources(event.getCompanyId(),
				event.getGroupId(), event.getUserId(), Event.class.getName(),
				event.getEventId(), groupPermissions, guestPermissions);
	}

	public void addEventResources(long eventId, boolean addGroupPermissions,
			boolean addGuestPermissions) throws PortalException,
			SystemException {

		Event event = eventPersistence.findByPrimaryKey(eventId);

		addEventResources(event, addGroupPermissions, addGuestPermissions);
	}

	public void addEventResources(long eventId, String[] groupPermissions,
			String[] guestPermissions) throws PortalException, SystemException {

		Event event = eventPersistence.findByPrimaryKey(eventId);

		addEventResources(event, groupPermissions, guestPermissions);
	}

	public Event deleteEvent(Event event) throws SystemException {

		return eventPersistence.remove(event);
	}

	public Event deleteEvent(long eventId) throws PortalException,
			SystemException {

		Event event = eventPersistence.findByPrimaryKey(eventId);

		return deleteEvent(event);
	}

	public List<Event> findByEventNameEventDescriptionLocationName(
			String eventName, String eventDescription, String locationName,
			int begin, int end) throws SystemException {

		return EventFinderUtil.findByEventNameEventDescriptionLocationName(
				eventName, eventDescription, locationName, begin, end);
	}

	public List<Event> findByUserId(long userId) throws SystemException {

		return eventPersistence.findByUserId(userId);
	}

	public Event getEvent(long eventId) throws PortalException, SystemException {

		return eventPersistence.findByPrimaryKey(eventId);
	}

	public List<Event> getEventsByGroupId(long groupId) throws SystemException {

		return eventPersistence.findByGroupId(groupId);
	}

	public List<Event> getEventsByGroupId(long groupId, int start, int end)
			throws SystemException {

		return eventPersistence.findByGroupId(groupId, start, end);
	}

	public int getEventsCountByGroupId(long groupId) throws SystemException {

		return eventPersistence.countByGroupId(groupId);
	}

	public Event updateEvent(long userId, long eventId, String name,
			String description, int month, int day, int year, int hour,
			int minute, long locationId, ServiceContext serviceContext)
			throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		Event event = EventLocalServiceUtil.fetchEvent(eventId);

		event.setModifiedDate(serviceContext.getModifiedDate(now));
		event.setName(name);
		event.setDescription(description);

		Calendar dateCal = CalendarFactoryUtil.getCalendar(user.getTimeZone());
		dateCal.set(year, month, day, hour, minute);
		Date date = dateCal.getTime();
		event.setDate(date);

		event.setLocationId(locationId);

		super.updateEvent(event);

		return event;
	}

}