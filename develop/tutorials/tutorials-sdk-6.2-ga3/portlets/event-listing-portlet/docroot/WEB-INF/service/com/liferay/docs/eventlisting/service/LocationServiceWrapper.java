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

package com.liferay.docs.eventlisting.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LocationService}.
 *
 * @author Joe Bloggs
 * @see LocationService
 * @generated
 */
public class LocationServiceWrapper implements LocationService,
	ServiceWrapper<LocationService> {
	public LocationServiceWrapper(LocationService locationService) {
		_locationService = locationService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _locationService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_locationService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _locationService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.docs.eventlisting.model.Location addLocation(
		long groupId, java.lang.String name, java.lang.String description,
		java.lang.String streetAddress, java.lang.String city,
		java.lang.String stateOrProvince, java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationService.addLocation(groupId, name, description,
			streetAddress, city, stateOrProvince, country, serviceContext);
	}

	@Override
	public com.liferay.docs.eventlisting.model.Location deleteLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationService.deleteLocation(locationId);
	}

	@Override
	public com.liferay.docs.eventlisting.model.Location getLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationService.getLocation(locationId);
	}

	@Override
	public com.liferay.docs.eventlisting.model.Location updateLocation(
		long locationId, java.lang.String name, java.lang.String description,
		java.lang.String streetAddress, java.lang.String city,
		java.lang.String stateOrProvince, java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationService.updateLocation(locationId, name, description,
			streetAddress, city, stateOrProvince, country, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LocationService getWrappedLocationService() {
		return _locationService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLocationService(LocationService locationService) {
		_locationService = locationService;
	}

	@Override
	public LocationService getWrappedService() {
		return _locationService;
	}

	@Override
	public void setWrappedService(LocationService locationService) {
		_locationService = locationService;
	}

	private LocationService _locationService;
}