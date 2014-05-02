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

package com.samples.portlet.eventlisting.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.samples.portlet.eventlisting.model.Event;
import com.samples.portlet.eventlisting.service.EventLocalServiceUtil;
import com.samples.portlet.eventlisting.service.base.EventServiceBaseImpl;
import com.samples.portlet.eventlisting.service.permission.EventListingPermission;
import com.samples.portlet.eventlisting.service.permission.EventPermission;
import com.samples.portlet.eventlisting.util.EventListingActionKeys;

/**
 * The implementation of the event remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the {@link
 * com.samples.portlet.eventlisting.service.EventService} interface. <p> This
 * is a remote service. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 * </p>
 *
 * @author Joe Bloggs
 * @see    com.samples.portlet.eventlisting.service.base.EventServiceBaseImpl
 * @see    com.samples.portlet.eventlisting.service.EventServiceUtil
 */
public class EventServiceImpl extends EventServiceBaseImpl {

	public Event addEvent(
			long groupId, String name, String description, int month, int day,
			int year, int hour, int minute, long locationId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		EventListingPermission.check(
			getPermissionChecker(), groupId, EventListingActionKeys.ADD_EVENT);

		return EventLocalServiceUtil.addEvent(
			getUserId(), groupId, name, description, month, day, year, hour,
			minute, locationId, serviceContext);
	}

	public Event deleteEvent(long eventId)
		throws PortalException, SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
			EventListingActionKeys.DELETE_EVENT);

		return eventLocalService.deleteEvent(eventId);
	}

	public Event getEvent(long eventId)
		throws PortalException, SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
			EventListingActionKeys.VIEW);

		return EventLocalServiceUtil.getEvent(eventId);
	}

	public Event updateEvent(
			long userId, long eventId, String name, String description,
			int month, int day, int year, int hour, int minute, long locationId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		EventPermission.check(getPermissionChecker(), eventId,
			EventListingActionKeys.UPDATE_EVENT);

		return EventLocalServiceUtil.updateEvent(
			userId, eventId, name, description, month, day, year, hour, minute,
			locationId, serviceContext);
	}

}