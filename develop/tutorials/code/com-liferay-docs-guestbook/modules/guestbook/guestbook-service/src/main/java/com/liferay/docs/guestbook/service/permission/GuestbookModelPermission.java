
package com.liferay.docs.guestbook.service.permission;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.guestbook.portlet.constants.GuestbookPortletKeys;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

@Component(immediate = true, property = {
	"resource.name=" + GuestbookModelPermission.RESOURCE_NAME
}, service = ResourcePermissionChecker.class)

public class GuestbookModelPermission extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = "com.liferay.docs.guestbook";

	public static void check(
		PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME, groupId, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		return contains(
			permissionChecker, RESOURCE_NAME, GuestbookPortletKeys.GUESTBOOK,
			groupId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, String name, long classPK,
		String actionId) {

		Group group = GroupLocalServiceUtil.fetchGroup(classPK);

		if ((group != null) && group.isStagingGroup()) {
			classPK = group.getLiveGroupId();
		}

		return permissionChecker.hasPermission(
					classPK, name, classPK, actionId);
			}

	public static boolean contains(
		PermissionChecker permissionChecker, String name, String portletId,
		long classPK, String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, classPK, name, classPK, portletId, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		return contains(permissionChecker, name, classPK, actionId);
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		return contains(permissionChecker, classPK, actionId);
	}
}
