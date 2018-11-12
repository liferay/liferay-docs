package com.liferay.docs.guestbook.web.internal.security.permission.resource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

@Component(immediate = true)
public class GuestbookModelPermission {
	
	public static boolean contains(
			PermissionChecker permissionChecker, Guestbook guestbook, String actionId) throws PortalException {
		
		return _guestbookModelResourcePermission.contains(permissionChecker, guestbook, actionId);
	}
	
	public static boolean contains(
			PermissionChecker permissionChecker, long guestbookId, String actionId) throws PortalException {
		
		return _guestbookModelResourcePermission.contains(permissionChecker, guestbookId, actionId);
	}
	
	@Reference(
			target = "(model.class.name=com.liferay.docs.guestbook.model.Guestbook)", 
			unbind = "-")
	protected void setEntryModelPermission(ModelResourcePermission<Guestbook> modelResourcePermission) {
		
		_guestbookModelResourcePermission = modelResourcePermission;
	}
	
	private static ModelResourcePermission<Guestbook>_guestbookModelResourcePermission;

}
