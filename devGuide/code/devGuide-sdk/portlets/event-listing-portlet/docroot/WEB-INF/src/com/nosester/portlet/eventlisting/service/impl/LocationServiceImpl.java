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
import com.liferay.portal.service.ServiceContext;
import com.nosester.portlet.eventlisting.model.Location;
import com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil;
import com.nosester.portlet.eventlisting.service.base.LocationServiceBaseImpl;

/**
 * The implementation of the location remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.nosester.portlet.eventlisting.service.LocationService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author jbloggs
 * @see com.nosester.portlet.eventlisting.service.base.LocationServiceBaseImpl
 * @see com.nosester.portlet.eventlisting.service.LocationServiceUtil
 */
public class LocationServiceImpl extends LocationServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.nosester.portlet.eventlisting.service.LocationServiceUtil} to access the location remote service.
	 */
	
	public Location addLocation(Location location) throws SystemException {
		
		long locationId = counterLocalService.increment(Location.class.getName());
		location.setLocationId(locationId);
		
		Date now = new Date();
		location.setCreateDate(now);
		location.setModifiedDate(now);
		
		return locationLocalService.addLocation(location);
	}
	
	public Location addLocation(long locationId, String name, String description, String streetAddress, String city, String stateOrProvince, String country, ServiceContext serviceContext) {
		
		return LocationLocalServiceUtil.addLocation(name, description, streetAddress, city, stateOrProvince, country, serviceContext);
	}
	
	public Location update(Location location) throws SystemException {
		
		Date now = new Date();
		location.setModifiedDate(now);
		
		return locationLocalService.updateLocation(location);
	}	
	
	public Location updateLocation(long locationId, String name, String description, String streetAddress, String city, String stateOrProvince, String country, ServiceContext serviceContext) {
		
		return LocationLocalServiceUtil.updateLocation(locationId, name, description, streetAddress, city, stateOrProvince, country, serviceContext);
	}
	
	public Location delete(Location location) throws SystemException {
		
		return locationLocalService.deleteLocation(location);
	}
	
	public Location deleteLocation(long locationId) throws PortalException, SystemException {
		
		return locationLocalService.deleteLocation(locationId);
	}
	
}