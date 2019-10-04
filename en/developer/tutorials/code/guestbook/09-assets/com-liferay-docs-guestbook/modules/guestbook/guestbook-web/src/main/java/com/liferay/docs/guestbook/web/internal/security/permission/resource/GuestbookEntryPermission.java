package com.liferay.docs.guestbook.web.internal.security.permission.resource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.GuestbookEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

@Component(immediate = true)
public class GuestbookEntryPermission {
	
	public static boolean contains(
			PermissionChecker permissionChecker, GuestbookEntry entry, String actionId) throws PortalException {
		
		return _guestbookEntryModelResourcePermission.contains(permissionChecker, entry, actionId);
	}
	
	public static boolean contains(
			PermissionChecker permissionChecker, long entryId, String actionId) throws PortalException {
		
		return _guestbookEntryModelResourcePermission.contains(permissionChecker, entryId, actionId);
	}
	
	@Reference(
			target = "(model.class.name=com.liferay.docs.guestbook.model.GuestbookEntry)", 
			unbind = "-")
	protected void setEntryModelPermission(ModelResourcePermission<GuestbookEntry> modelResourcePermission) {
		
		_guestbookEntryModelResourcePermission = modelResourcePermission;
	}
	
	private static ModelResourcePermission<GuestbookEntry>_guestbookEntryModelResourcePermission;

}
