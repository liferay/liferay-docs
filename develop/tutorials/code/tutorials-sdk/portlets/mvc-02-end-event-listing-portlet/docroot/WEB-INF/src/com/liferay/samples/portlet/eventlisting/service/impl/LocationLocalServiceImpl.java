/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.samples.portlet.eventlisting.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.samples.portlet.eventlisting.service.base.LocationLocalServiceBaseImpl;
import com.liferay.samples.portlet.eventlisting.model.Location;

/**
 * The implementation of the location local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.samples.portlet.eventlisting.service.LocationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Joe Bloggs
 * @see com.liferay.samples.portlet.eventlisting.service.base.LocationLocalServiceBaseImpl
 * @see com.liferay.samples.portlet.eventlisting.service.LocationLocalServiceUtil
 */
public class LocationLocalServiceImpl extends LocationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.samples.portlet.eventlisting.service.LocationLocalServiceUtil} to access the location local service.
	 */
	
	public Location addLocation(
	        long userId, long groupId, String name, String description,
	        String streetAddress, String city, String stateOrProvince,
	        String country, ServiceContext serviceContext)
	throws PortalException, SystemException {

	    User user = userPersistence.findByPrimaryKey(userId);

	    Date now = new Date();

	    long locationId =
	        counterLocalService.increment(Location.class.getName());

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

	public Location deleteLocation(Location location)
	    throws SystemException {

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