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

package com.samples.portlet.eventlisting.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LocationLocalService}.
 *
 * @author Joe Bloggs
 * @see LocationLocalService
 * @generated
 */
public class LocationLocalServiceWrapper implements LocationLocalService,
	ServiceWrapper<LocationLocalService> {
	public LocationLocalServiceWrapper(
		LocationLocalService locationLocalService) {
		_locationLocalService = locationLocalService;
	}

	/**
	* Adds the location to the database. Also notifies the appropriate model listeners.
	*
	* @param location the location
	* @return the location that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.samples.portlet.eventlisting.model.Location addLocation(
		com.samples.portlet.eventlisting.model.Location location)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.addLocation(location);
	}

	/**
	* Creates a new location with the primary key. Does not add the location to the database.
	*
	* @param locationId the primary key for the new location
	* @return the new location
	*/
	@Override
	public com.samples.portlet.eventlisting.model.Location createLocation(
		long locationId) {
		return _locationLocalService.createLocation(locationId);
	}

	/**
	* Deletes the location with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param locationId the primary key of the location
	* @return the location that was removed
	* @throws PortalException if a location with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.samples.portlet.eventlisting.model.Location deleteLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.deleteLocation(locationId);
	}

	/**
	* Deletes the location from the database. Also notifies the appropriate model listeners.
	*
	* @param location the location
	* @return the location that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.samples.portlet.eventlisting.model.Location deleteLocation(
		com.samples.portlet.eventlisting.model.Location location)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.deleteLocation(location);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _locationLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.samples.portlet.eventlisting.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.samples.portlet.eventlisting.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.samples.portlet.eventlisting.model.Location fetchLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.fetchLocation(locationId);
	}

	/**
	* Returns the location with the primary key.
	*
	* @param locationId the primary key of the location
	* @return the location
	* @throws PortalException if a location with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.samples.portlet.eventlisting.model.Location getLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.getLocation(locationId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the locations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.samples.portlet.eventlisting.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of locations
	* @param end the upper bound of the range of locations (not inclusive)
	* @return the range of locations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.samples.portlet.eventlisting.model.Location> getLocations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.getLocations(start, end);
	}

	/**
	* Returns the number of locations.
	*
	* @return the number of locations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getLocationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.getLocationsCount();
	}

	/**
	* Updates the location in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param location the location
	* @return the location that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.samples.portlet.eventlisting.model.Location updateLocation(
		com.samples.portlet.eventlisting.model.Location location)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.updateLocation(location);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _locationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_locationLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _locationLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.samples.portlet.eventlisting.model.Location addLocation(
		long userId, long groupId, java.lang.String name,
		java.lang.String description, java.lang.String streetAddress,
		java.lang.String city, java.lang.String stateOrProvince,
		java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.addLocation(userId, groupId, name,
			description, streetAddress, city, stateOrProvince, country,
			serviceContext);
	}

	@Override
	public void addLocationResources(
		com.samples.portlet.eventlisting.model.Location location,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_locationLocalService.addLocationResources(location,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addLocationResources(
		com.samples.portlet.eventlisting.model.Location location,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_locationLocalService.addLocationResources(location, groupPermissions,
			guestPermissions);
	}

	@Override
	public void addLocationResources(long locationId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_locationLocalService.addLocationResources(locationId,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addLocationResources(long locationId,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_locationLocalService.addLocationResources(locationId,
			groupPermissions, guestPermissions);
	}

	@Override
	public java.util.List<com.samples.portlet.eventlisting.model.Location> getLocationsByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.getLocationsByGroupId(groupId);
	}

	@Override
	public java.util.List<com.samples.portlet.eventlisting.model.Location> getLocationsByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.getLocationsByGroupId(groupId, start, end);
	}

	@Override
	public int getLocationsCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.getLocationsCountByGroupId(groupId);
	}

	@Override
	public com.samples.portlet.eventlisting.model.Location updateLocation(
		long userId, long locationId, java.lang.String name,
		java.lang.String description, java.lang.String streetAddress,
		java.lang.String city, java.lang.String stateOrProvince,
		java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _locationLocalService.updateLocation(userId, locationId, name,
			description, streetAddress, city, stateOrProvince, country,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LocationLocalService getWrappedLocationLocalService() {
		return _locationLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLocationLocalService(
		LocationLocalService locationLocalService) {
		_locationLocalService = locationLocalService;
	}

	@Override
	public LocationLocalService getWrappedService() {
		return _locationLocalService;
	}

	@Override
	public void setWrappedService(LocationLocalService locationLocalService) {
		_locationLocalService = locationLocalService;
	}

	private LocationLocalService _locationLocalService;
}