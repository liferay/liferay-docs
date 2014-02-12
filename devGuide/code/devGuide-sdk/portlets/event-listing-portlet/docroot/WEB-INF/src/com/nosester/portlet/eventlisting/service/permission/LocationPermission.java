package com.nosester.portlet.eventlisting.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

import com.nosester.portlet.eventlisting.model.Location;
import com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class LocationPermission {

	public static void check(
			PermissionChecker permissionChecker, Location location,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, location, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long locationId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, locationId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Location location,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				location.getCompanyId(), Location.class.getName(),
				location.getLocationId(), location.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			location.getGroupId(), Location.class.getName(), location.getLocationId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long locationId, String actionId)
		throws PortalException, SystemException {

		Location location = LocationLocalServiceUtil.getLocation(locationId);

		return contains(permissionChecker, location, actionId);
	}

}