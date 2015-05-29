package com.nosester.portlet.eventlisting.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

import com.nosester.portlet.eventlisting.model.Event;
import com.nosester.portlet.eventlisting.service.EventLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class EventPermission {

	public static void check(
			PermissionChecker permissionChecker, Event event, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, event, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long eventId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, eventId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Event event, String actionId) {
		if (permissionChecker.hasOwnerPermission(
				event.getCompanyId(), Event.class.getName(), event.getEventId(),
				event.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			event.getGroupId(), Event.class.getName(), event.getEventId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long eventId, String actionId)
		throws PortalException, SystemException {

		Event event = EventLocalServiceUtil.getEvent(eventId);

		return contains(permissionChecker, event, actionId);
	}

}