# Permissions

Why we need permissions

## Permissions in Liferay Outline
Define Resources
Register Resources
Associate Permissions and Resources
Check permissions


"Resource" can be used to describe virtually anything that you interact with in
Liferay. In order to be useful, resources need to be defined.

Portlet Resources

Model Resources

Resource Actions

### Implementing Resources

portlet.properties

    resource.actions.configs=resource-actions/default.xml
	
resource-actions/default.xml

	<?xml version="1.0"?>
	<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

	<resource-action-mapping>
		<portlet-resource>
			<portlet-name>com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet</portlet-name>
			<permissions>
				<supports>
					<action-key>ACCESS_IN_CONTROL_PANEL</action-key>
					<action-key>CONFIGURATION</action-key>
					<action-key>VIEW</action-key>
				</supports>
				<site-member-defaults>
					<action-key>VIEW</action-key>
				</site-member-defaults>
				<guest-defaults>
					<action-key>VIEW</action-key>
				</guest-defaults>
				<guest-unsupported>
					<action-key>ACCESS_IN_CONTROL_PANEL</action-key>
					<action-key>CONFIGURATION</action-key>
				</guest-unsupported>
			</permissions>
		</portlet-resource>
		<portlet-resource>
			<portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
			<permissions>
				<supports>
					<action-key>ADD_TO_PAGE</action-key>
					<action-key>CONFIGURATION</action-key>
					<action-key>VIEW</action-key>
				</supports>
				<site-member-defaults>
					<action-key>VIEW</action-key>
				</site-member-defaults>
				<guest-defaults>
					<action-key>VIEW</action-key>
				</guest-defaults>
				<guest-unsupported />
			</permissions>
		</portlet-resource>
	</resource-action-mapping>
	



### Permissions in the Service Layer

LocalServiceImpl

    resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
	    	       Entry.class.getName(), entryId, false, true, true);

    resourceLocalService.deleteResource(
			serviceContext.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, entryId);

    resourceLocalService.updateResources(
			user.getCompanyId(), groupId, Entry.class.getName(), entryId,
			serviceContext.getGroupPermissions(),
			serviceContext.getGuestPermissions());
			
			

    resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
			       Guestbook.class.getName(), guestbookId, false, true, true);
				   
	resourceLocalService.updateResources(serviceContext.getCompanyId(),
		                                serviceContext.getScopeGroupId(), name, guestbookId,
		                                serviceContext.getGroupPermissions(),
		                                serviceContext.getGuestPermissions());

	resourceLocalService.deleteResource(serviceContext.getCompanyId(),
	                     Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
	                     guestbookId);
						 


ServiceImpl

	GuestbookModelPermission.check(getPermissionChecker(),
	                    serviceContext.getScopeGroupId(), ActionKeys.ADD_GUESTBOOK);
    
	GuestbookPermission.check(getPermissionChecker(), guestbookId,
	                    ActionKeys.UPDATE);
	
	GuestbookPermission.check(getPermissionChecker(), guestbookId,
                    ActionKeys.DELETE);

	GuestbookModelPermission.check(getPermissionChecker(),
	                    serviceContext.getScopeGroupId(), ActionKeys.ADD_ENTRY);
						
	EntryPermission.check(getPermissionChecker(),
	                    entryId, ActionKeys.DELETE);
	
    EntryPermission.check(getPermissionChecker(),
                       entryId, ActionKeys.UPDATE);
					   

Create ActionKeys

### Permissions classes

GuestbookModelPermissions

	package com.liferay.docs.guestbook.service.permission;

	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.security.auth.PrincipalException;
	import com.liferay.portal.kernel.security.permission.PermissionChecker;

	/**
	 * @author liferay
	 */
	public class GuestbookModelPermission {

		public static final String RESOURCE_NAME =
			"com.liferay.docs.guestbook.model";

		public static void check(
				PermissionChecker permissionChecker, long groupId, String actionId)
			throws PortalException {

			if (!contains(permissionChecker, groupId, actionId)) {
				throw new PrincipalException();
			}
		}

		public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String actionId) {

			return permissionChecker.hasPermission(
				groupId, RESOURCE_NAME, groupId, actionId);
		}

	}

EntryPermission

	package com.liferay.docs.guestbook.service.permission;

	import com.liferay.docs.guestbook.model.Entry;
	import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.security.auth.PrincipalException;
	import com.liferay.portal.kernel.security.permission.PermissionChecker;

	/**
	 * @author liferay
	 */
	public class EntryPermission {

		public static void check(
				PermissionChecker permissionChecker, long entryId, String actionId)
			throws PortalException {

			if (!contains(permissionChecker, entryId, actionId)) {
				throw new PrincipalException();
			}
		}

		public static boolean contains(
				PermissionChecker permissionChecker, long entryId, String actionId)
			throws PortalException {

			Entry entry = EntryLocalServiceUtil .getEntry(entryId);

			return permissionChecker.hasPermission(
				entry.getGroupId(), Entry.class.getName(), entry.getEntryId(),
				actionId);
		}

	}

GuestbookPermission

	package com.liferay.docs.guestbook.service.permission;

	import com.liferay.docs.guestbook.model.Guestbook;
	import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.security.auth.PrincipalException;
	import com.liferay.portal.kernel.security.permission.PermissionChecker;

	/**
	 * @author liferay
	 */
	public class GuestbookPermission {

		public static void check(
				PermissionChecker permissionChecker, long guestbookId,
				String actionId)
			throws PortalException {

			if (!contains(permissionChecker, guestbookId, actionId)) {
				throw new PrincipalException();
			}
		}

		public static boolean contains(
				PermissionChecker permissionChecker, long guestbookId,
				String actionId)
			throws PortalException {

			Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbook(
				guestbookId);

			return permissionChecker.hasPermission(
				guestbook.getGroupId(), Guestbook.class.getName(),
				guestbook.getGuestbookId(), actionId);
		}

	}

### Permissions in JSPs