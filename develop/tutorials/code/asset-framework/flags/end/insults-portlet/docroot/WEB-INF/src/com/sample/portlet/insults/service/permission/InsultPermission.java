package com.sample.portlet.insults.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.sample.portlet.insults.model.Insult;
import com.sample.portlet.insults.service.InsultLocalServiceUtil;

public class InsultPermission {
	
	public static void check(PermissionChecker permissionChecker,
			Insult insult, String actionId) throws PortalException {

		if (!contains(permissionChecker, insult, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(PermissionChecker permissionChecker,
			long insultId, String actionId) throws PortalException,
			SystemException {

		if (!contains(permissionChecker, insultId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker,
			Insult insult, String actionId) {

		if (permissionChecker.hasOwnerPermission(insult.getCompanyId(),
				Insult.class.getName(), insult.getInsultId(),
				insult.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(insult.getGroupId(),
				Insult.class.getName(), insult.getInsultId(),
				actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker,
			long insultId, String actionId) throws PortalException,
			SystemException {

		Insult insult = InsultLocalServiceUtil
				.getInsult(insultId);

		return contains(permissionChecker, insult, actionId);
	}

}
