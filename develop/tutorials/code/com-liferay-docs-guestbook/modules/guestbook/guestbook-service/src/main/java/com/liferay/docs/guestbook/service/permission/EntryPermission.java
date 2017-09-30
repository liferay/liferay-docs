
package com.liferay.docs.guestbook.service.permission;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {"model.class.name=com.liferay.docs.guestbook.model.Entry"}
)
public class EntryPermission implements BaseModelPermissionChecker {

	public static void check(
		PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, entryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException, SystemException {

		Entry entry = _entryLocalService.getEntry(entryId);

		return contains (permissionChecker, entry, actionId);

	}

	public static boolean contains(
		PermissionChecker permissionChecker, Entry entry, String actionId) throws
		PortalException, SystemException {

		return permissionChecker.hasPermission(entry.getGroupId(), Entry.class.getName(), entry.getEntryId(), actionId);
	}

	@Reference(unbind = "-")
	protected void setEntryLocalService (EntryLocalService entryLocalService) {

		_entryLocalService = entryLocalService;
	}

	private static EntryLocalService _entryLocalService; 

	@Override
	public void checkBaseModel(PermissionChecker permissionChecker, long groupId, long primaryKey, String actionId) throws PortalException {
		check(permissionChecker, primaryKey, actionId);
	}
}
