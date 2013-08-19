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

package com.nosester.portlet.eventlisting.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.nosester.portlet.eventlisting.service.LocationServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.nosester.portlet.eventlisting.service.LocationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.nosester.portlet.eventlisting.model.LocationSoap}.
 * If the method in the service utility returns a
 * {@link com.nosester.portlet.eventlisting.model.Location}, that is translated to a
 * {@link com.nosester.portlet.eventlisting.model.LocationSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Joe Bloggs
 * @see       LocationServiceHttp
 * @see       com.nosester.portlet.eventlisting.model.LocationSoap
 * @see       com.nosester.portlet.eventlisting.service.LocationServiceUtil
 * @generated
 */
public class LocationServiceSoap {
	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this interface directly. Always use {@link com.nosester.portlet.eventlisting.service.LocationServiceUtil} to access the location remote service.
	*/
	public static com.nosester.portlet.eventlisting.model.LocationSoap addLocation(
		com.nosester.portlet.eventlisting.model.LocationSoap location)
		throws RemoteException {
		try {
			com.nosester.portlet.eventlisting.model.Location returnValue = LocationServiceUtil.addLocation(com.nosester.portlet.eventlisting.model.impl.LocationModelImpl.toModel(
						location));

			return com.nosester.portlet.eventlisting.model.LocationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.nosester.portlet.eventlisting.model.LocationSoap addLocation(
		long locationId, java.lang.String name, java.lang.String description,
		java.lang.String streetAddress, java.lang.String city,
		java.lang.String stateOrProvince, java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.nosester.portlet.eventlisting.model.Location returnValue = LocationServiceUtil.addLocation(locationId,
					name, description, streetAddress, city, stateOrProvince,
					country, serviceContext);

			return com.nosester.portlet.eventlisting.model.LocationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.nosester.portlet.eventlisting.model.LocationSoap delete(
		com.nosester.portlet.eventlisting.model.LocationSoap location)
		throws RemoteException {
		try {
			com.nosester.portlet.eventlisting.model.Location returnValue = LocationServiceUtil.delete(com.nosester.portlet.eventlisting.model.impl.LocationModelImpl.toModel(
						location));

			return com.nosester.portlet.eventlisting.model.LocationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.nosester.portlet.eventlisting.model.LocationSoap deleteLocation(
		long locationId) throws RemoteException {
		try {
			com.nosester.portlet.eventlisting.model.Location returnValue = LocationServiceUtil.deleteLocation(locationId);

			return com.nosester.portlet.eventlisting.model.LocationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.nosester.portlet.eventlisting.model.LocationSoap update(
		com.nosester.portlet.eventlisting.model.LocationSoap location)
		throws RemoteException {
		try {
			com.nosester.portlet.eventlisting.model.Location returnValue = LocationServiceUtil.update(com.nosester.portlet.eventlisting.model.impl.LocationModelImpl.toModel(
						location));

			return com.nosester.portlet.eventlisting.model.LocationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.nosester.portlet.eventlisting.model.LocationSoap updateLocation(
		long locationId, java.lang.String name, java.lang.String description,
		java.lang.String streetAddress, java.lang.String city,
		java.lang.String stateOrProvince, java.lang.String country,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.nosester.portlet.eventlisting.model.Location returnValue = LocationServiceUtil.updateLocation(locationId,
					name, description, streetAddress, city, stateOrProvince,
					country, serviceContext);

			return com.nosester.portlet.eventlisting.model.LocationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LocationServiceSoap.class);
}