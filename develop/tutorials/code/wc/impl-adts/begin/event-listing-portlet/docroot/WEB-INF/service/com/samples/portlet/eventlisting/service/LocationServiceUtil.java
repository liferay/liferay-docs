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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for Location. This utility wraps
 * {@link com.samples.portlet.eventlisting.service.impl.LocationServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Joe Bloggs
 * @see LocationService
 * @see com.samples.portlet.eventlisting.service.base.LocationServiceBaseImpl
 * @see com.samples.portlet.eventlisting.service.impl.LocationServiceImpl
 * @generated
 */
public class LocationServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.samples.portlet.eventlisting.service.impl.LocationServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.samples.portlet.eventlisting.model.Location addLocation(
		long groupId, java.lang.String name, java.lang.String description,
		java.lang.String streetAddress, java.lang.String city,
		java.lang.String stateOrProvince, java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addLocation(groupId, name, description, streetAddress,
			city, stateOrProvince, country, serviceContext);
	}

	public static com.samples.portlet.eventlisting.model.Location deleteLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteLocation(locationId);
	}

	public static com.samples.portlet.eventlisting.model.Location updateLocation(
		long locationId, java.lang.String name, java.lang.String description,
		java.lang.String streetAddress, java.lang.String city,
		java.lang.String stateOrProvince, java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateLocation(locationId, name, description,
			streetAddress, city, stateOrProvince, country, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static LocationService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					LocationService.class.getName());

			if (invokableService instanceof LocationService) {
				_service = (LocationService)invokableService;
			}
			else {
				_service = new LocationServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(LocationServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(LocationService service) {
	}

	private static LocationService _service;
}