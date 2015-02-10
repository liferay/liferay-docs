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

package com.liferay.docs.eventlisting.service.impl;

import com.liferay.docs.eventlisting.service.base.LocationServiceBaseImpl;
import com.liferay.docs.eventlisting.service.permission.EventListingPermission;
import com.liferay.docs.eventlisting.service.permission.LocationPermission;
import com.liferay.docs.eventlisting.util.EventListingActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.nosester.portlet.eventlisting.model.Location;
import com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil;

/**
 * The implementation of the location remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the {@link
 * com.nosester.portlet.eventlisting.service.LocationService} interface. <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Joe Bloggs
 * @see    com.liferay.docs.eventlisting.service.base.LocationServiceBaseImpl
 * @see    com.nosester.portlet.eventlisting.service.LocationServiceUtil
 */
public class LocationServiceImpl extends LocationServiceBaseImpl {

	public Location addLocation(long groupId, String name, String description,
			String streetAddress, String city, String stateOrProvince,
			String country, ServiceContext serviceContext)
		throws PortalException, SystemException {

		EventListingPermission.check(getPermissionChecker(), groupId,
			EventListingActionKeys.ADD_LOCATION);

		return LocationLocalServiceUtil.addLocation(
			getUserId(), groupId, name, description, streetAddress, city,
			stateOrProvince, country, serviceContext);
	}

	public Location deleteLocation(long locationId)
		throws PortalException, SystemException {

		LocationPermission.check(getPermissionChecker(), locationId,
			EventListingActionKeys.DELETE_LOCATION);

		return locationLocalService.deleteLocation(locationId);
	}

	public Location updateLocation(
			long locationId, String name, String description,
			String streetAddress, String city, String stateOrProvince,
			String country, ServiceContext serviceContext)
		throws PortalException, SystemException {

		LocationPermission.check(getPermissionChecker(), locationId,
			EventListingActionKeys.VIEW);

		return LocationLocalServiceUtil.updateLocation(
			getUserId(), locationId, name, description, streetAddress, city,
			stateOrProvince, country, serviceContext);
	}

}