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

package com.nosester.portlet.eventlisting.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the location remote service. This utility wraps {@link com.nosester.portlet.eventlisting.service.impl.LocationServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Joe Bloggs
 * @see LocationService
 * @see com.nosester.portlet.eventlisting.service.base.LocationServiceBaseImpl
 * @see com.nosester.portlet.eventlisting.service.impl.LocationServiceImpl
 * @generated
 */
public class LocationServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.nosester.portlet.eventlisting.service.impl.LocationServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.nosester.portlet.eventlisting.model.Location addLocation(
		com.nosester.portlet.eventlisting.model.Location location)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addLocation(location);
	}

	public static com.nosester.portlet.eventlisting.model.Location addLocation(
		long locationId, java.lang.String name, java.lang.String description,
		java.lang.String streetAddress, java.lang.String city,
		java.lang.String stateOrProvince, java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .addLocation(locationId, name, description, streetAddress,
			city, stateOrProvince, country, serviceContext);
	}

	public static com.nosester.portlet.eventlisting.model.Location update(
		com.nosester.portlet.eventlisting.model.Location location)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().update(location);
	}

	public static com.nosester.portlet.eventlisting.model.Location updateLocation(
		long locationId, java.lang.String name, java.lang.String description,
		java.lang.String streetAddress, java.lang.String city,
		java.lang.String stateOrProvince, java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .updateLocation(locationId, name, description,
			streetAddress, city, stateOrProvince, country, serviceContext);
	}

	public static com.nosester.portlet.eventlisting.model.Location delete(
		com.nosester.portlet.eventlisting.model.Location location)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().delete(location);
	}

	public static com.nosester.portlet.eventlisting.model.Location deleteLocation(
		long locationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteLocation(locationId);
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
	 * @deprecated
	 */
	public void setService(LocationService service) {
	}

	private static LocationService _service;
}