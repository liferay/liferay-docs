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

import com.liferay.docs.guestbook.constants.GuestbookConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

@Component(immediate=true)
public class GuestbookPermission {

	public static boolean contains(PermissionChecker permissionChecker, long groupId, String actionId) {
		
		return _portletResourcePermission.contains(permissionChecker, groupId, actionId);
		
	}
	
	@Reference(
			target="(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")", 
			unbind="-"
			)
	protected void setPortletResourcePermission(PortletResourcePermission portletResourcePermission) {
		
		_portletResourcePermission = portletResourcePermission;
	}
	
	private static PortletResourcePermission _portletResourcePermission;

}