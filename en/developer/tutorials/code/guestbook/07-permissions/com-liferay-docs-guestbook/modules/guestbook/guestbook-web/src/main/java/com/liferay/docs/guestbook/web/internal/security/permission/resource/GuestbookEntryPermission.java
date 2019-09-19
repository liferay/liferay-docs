/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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