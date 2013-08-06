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
import com.liferay.portal.service.ServiceContext;

import com.nosester.portlet.eventlisting.model.Location;
import com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil;
import com.nosester.portlet.eventlisting.service.base.LocationLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the location local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.nosester.portlet.eventlisting.service.LocationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author jbloggs
 * @see com.nosester.portlet.eventlisting.service.base.LocationLocalServiceBaseImpl
 * @see com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil
 */
public class LocationLocalServiceImpl extends LocationLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil} to access the location local service.
	 */

	public Location addLocation(Location location) throws SystemException {

		long locationId = counterLocalService.increment(Location.class.getName());
		location.setLocationId(locationId);

		Date now = new Date();
		location.setCreateDate(now);
		location.setModifiedDate(now);

		return super.addLocation(location);
	}

	public Location addLocation(String name, String description, String streetAddress, String city, String stateOrProvince, String country, ServiceContext serviceContext) {

		long locationId = 0;
		try {
			locationId = counterLocalService.increment(Location.class.getName());
		} catch (SystemException e) {
			e.printStackTrace();
		}

		Location location = locationPersistence.create(locationId);

		location.setName(name);
		location.setDescription(description);
		location.setStreetAddress(streetAddress);
		location.setCity(city);
		location.setStateOrProvince(stateOrProvince);
		location.setCountry(country);

		long companyId = serviceContext.getCompanyId();
		location.setCompanyId(companyId);

		long groupId = serviceContext.getScopeGroupId();
		location.setGroupId(groupId);

		long userId = 0;
		try {
			userId = serviceContext.getGuestOrUserId();
		} catch (PortalException pe) {
			pe.printStackTrace();
		} catch (SystemException se) {
			se.printStackTrace();
		}

		location.setUserId(userId);

		Date now = new Date();
		location.setCreateDate(now);
		location.setModifiedDate(now);

		try {
			return super.addLocation(location);
		} catch (SystemException e) {
			e.printStackTrace();
		}

		return location;
	}

	public List<Location> getLocationsByGroupId(long groupId) throws SystemException {

		return locationPersistence.findByGroupId(groupId);
	}

	public List<Location> getLocationsByGroupId(long groupId, int start, int end) throws SystemException {

		return locationPersistence.findByGroupId(groupId, start, end);
	}

	public int getLocationsCountByGroupId(long groupId) throws SystemException {

		return locationPersistence.countByGroupId(groupId);
	}

	public Location updateLocation(Location location) throws SystemException {

		Date now = new Date();
		location.setModifiedDate(now);

		return super.updateLocation(location);
	}

	public Location updateLocation(long locationId, String name, String description, String streetAddress, String city, String stateOrProvince, String country, ServiceContext serviceContext) {

		Location location = null;
		try {
			location = LocationLocalServiceUtil.fetchLocation(locationId);
		} catch (SystemException e) {
			e.printStackTrace();
		}

		location.setName(name);
		location.setDescription(description);
		location.setStreetAddress(streetAddress);
		location.setCity(city);
		location.setStateOrProvince(stateOrProvince);
		location.setCountry(country);

		long companyId = serviceContext.getCompanyId();
		location.setCompanyId(companyId);

		long groupId = serviceContext.getScopeGroupId();
		location.setGroupId(groupId);

		long userId = 0;
		try {
			userId = serviceContext.getGuestOrUserId();
		} catch (PortalException pe) {
			pe.printStackTrace();
		} catch (SystemException se) {
			se.printStackTrace();
		}

		location.setUserId(userId);

		Date now = new Date();
		location.setModifiedDate(now);

		try {
			return super.addLocation(location);
		} catch (SystemException e) {
			e.printStackTrace();
		}

		return location;
	}

}