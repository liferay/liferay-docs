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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import com.nosester.portlet.eventlisting.model.Location;
import com.nosester.portlet.eventlisting.service.base.LocationLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the location local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the {@link
 * com.nosester.portlet.eventlisting.service.LocationLocalService} interface.
 * <p> This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Joe Bloggs
 * @see    com.nosester.portlet.eventlisting.service.base.LocationLocalServiceBaseImpl
 * @see    com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil
 */
public class LocationLocalServiceImpl extends LocationLocalServiceBaseImpl {

	public Location addLocation(
			long userId, long groupId, String name, String description,
			String streetAddress, String city, String stateOrProvince,
			String country, ServiceContext serviceContext)
	throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		long locationId = counterLocalService.increment(
			Location.class.getName());

		Location location = locationPersistence.create(locationId);

		location.setName(name);
		location.setDescription(description);
		location.setStreetAddress(streetAddress);
		location.setCity(city);
		location.setStateOrProvince(stateOrProvince);
		location.setCountry(country);

		location.setGroupId(groupId);
		location.setCompanyId(user.getCompanyId());
		location.setUserId(user.getUserId());
		location.setCreateDate(serviceContext.getCreateDate(now));
		location.setModifiedDate(serviceContext.getModifiedDate(now));

		super.addLocation(location);

		return location;
	}

	public void addLocationResources(
			Location location, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			location.getCompanyId(), location.getGroupId(),
			location.getUserId(), Location.class.getName(),
			location.getLocationId(), false, addGroupPermissions,
			addGuestPermissions);
	}

	public void addLocationResources(
			Location location, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			location.getCompanyId(), location.getGroupId(),
			location.getUserId(), Location.class.getName(),
			location.getLocationId(), groupPermissions, guestPermissions);
	}

	public void addLocationResources(
			long locationId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		Location location = locationPersistence.fetchByPrimaryKey(locationId);

		addLocationResources(
			location, addGroupPermissions, addGuestPermissions);
	}

	public void addLocationResources(
			long locationId, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		Location location = locationPersistence.fetchByPrimaryKey(locationId);

		addLocationResources(location, groupPermissions, guestPermissions);
	}

	public Location deleteLocation(Location location) throws SystemException {
		return locationPersistence.remove(location);
	}

	public Location deleteLocation(long locationId)
		throws PortalException, SystemException {

		Location location = locationPersistence.fetchByPrimaryKey(locationId);

		return deleteLocation(location);
	}

	public List<Location> getLocationsByGroupId(long groupId)
		throws SystemException {

		return locationPersistence.findByGroupId(groupId);
	}

	public List<Location> getLocationsByGroupId(
			long groupId, int start, int end)
		throws SystemException {

		return locationPersistence.findByGroupId(groupId, start, end);
	}

	public int getLocationsCountByGroupId(long groupId) throws SystemException {

		return locationPersistence.countByGroupId(groupId);
	}

	public Location updateLocation(
			long userId, long locationId, String name, String description,
			String streetAddress, String city, String stateOrProvince,
			String country, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		Location location = locationPersistence.findByPrimaryKey(locationId);

		location.setName(name);
		location.setDescription(description);
		location.setStreetAddress(streetAddress);
		location.setCity(city);
		location.setStateOrProvince(stateOrProvince);
		location.setCountry(country);
		location.setModifiedDate(serviceContext.getModifiedDate(now));

		super.updateLocation(location);

		return location;
	}

}