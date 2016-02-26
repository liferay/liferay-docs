package com.liferay.docs.samplecontent.portlet;

import java.util.Calendar;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.OrganizationLocalService;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.UserGroupLocalService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.util.PortalUtil;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=com.liferay.docs.samplecontent Portlet",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class SampleContentPortlet extends MVCPortlet {
	
	public void addUsers(ActionRequest request, ActionResponse response) throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		Role adminRole = _roleLocalService.getRole(companyId, "Administrator");
		List<User> adminUsers = _userLocalService.getRoleUsers(adminRole.getRoleId());
		long adminUserId = adminUsers.get(0).getUserId();
		
		_userLocalService.addUser(adminUserId, companyId, false, "liferay", "liferay", false, "jjeffries", "jjeffries@lunarresort.com", 0L, StringPool.BLANK, LocaleUtil.getDefault(), "James", StringPool.BLANK, "Jeffries", 0L, 0L, true, Calendar.JANUARY, 1, 1970, "Lunar Associate", new long[0], new long[0], new long[0], new long[0], false, null);
		_userLocalService.addUser(adminUserId, companyId, false, "liferay", "liferay", false, "msmart", "msmart@lunarresort.com", 0L, StringPool.BLANK, LocaleUtil.getDefault(), "Marvin", StringPool.BLANK, "Smart", 0L, 0L, true, Calendar.JANUARY, 1, 1970, "Lunar Associate", new long[0], new long[0], new long[0], new long[0], false, null);
	}	

	public void addOrganizations(ActionRequest request, ActionResponse response) throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		Role adminRole = _roleLocalService.getRole(companyId, "Administrator");
		List<User> adminUsers = _userLocalService.getRoleUsers(adminRole.getRoleId());
		long adminUserId = adminUsers.get(0).getUserId();

		_organizationLocalService.addOrganization(adminUserId, 0L, "Lunar Resort", true);
		
		long lunarResortOrgId = _organizationLocalService.getOrganizationId(companyId, "Lunar Resort");
		_organizationLocalService.addOrganization(adminUserId, lunarResortOrgId, "Physical Plant Department", true);
		_organizationLocalService.addOrganization(adminUserId, lunarResortOrgId, "Recreation Department", true);
		_organizationLocalService.addOrganization(adminUserId, lunarResortOrgId, "Sales Department", true);

		long physicalPlantOrgId = _organizationLocalService.getOrganizationId(companyId, "Physical Plant Department");
		_organizationLocalService.addOrganization(adminUserId, physicalPlantOrgId, "Grounds Crew", true);
		_organizationLocalService.addOrganization(adminUserId, physicalPlantOrgId, "Janitorial Crew", true);
		_organizationLocalService.addOrganization(adminUserId, physicalPlantOrgId, "Mechanical Crew", true);

		long recreationOrgId = _organizationLocalService.getOrganizationId(companyId, "Recreation Department");
		_organizationLocalService.addOrganization(adminUserId, recreationOrgId, "Lunar Golf Instructors", true);
		_organizationLocalService.addOrganization(adminUserId, recreationOrgId, "Lunar Rover Instructors", true);
		_organizationLocalService.addOrganization(adminUserId, recreationOrgId, "Lunar Sherpas", true);

		long salesOrgId = _organizationLocalService.getOrganizationId(companyId, "Sales Department");
		_organizationLocalService.addOrganization(adminUserId, salesOrgId, "Up-sale Group", true);
		_organizationLocalService.addOrganization(adminUserId, salesOrgId, "Souvenir and Memorabilia Group", true);
		_organizationLocalService.addOrganization(adminUserId, salesOrgId, "Retail Group", true);
	}	

	public void addUserGroups(ActionRequest request, ActionResponse response) throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		Role adminRole = _roleLocalService.getRole(companyId, "Administrator");
		List<User> adminUsers = _userLocalService.getRoleUsers(adminRole.getRoleId());
		long adminUserId = adminUsers.get(0).getUserId();

		_userGroupLocalService.addUserGroup(
				adminUserId, companyId, "List Creators",
				"The List Creators user group can manage dynamic data lists in the Lunar Resort.", null);

		_userGroupLocalService.addUserGroup(
				adminUserId, companyId, "Message Board Administrators",
				"The Message Board Administrators user group can manage message boards in the Lunar Resort.", null);
	}	

	public void addSites(ActionRequest request, ActionResponse response) {
		System.out.println("addSites method not yet implemented.");
	}	

	public UserLocalService getUserLocalService() {
		return _userLocalService;
	}

	public OrganizationLocalService getOrganizationLocalService() {
		return _organizationLocalService;
	}

	public UserGroupLocalService getUserGroupLocalService() {
		return _userGroupLocalService;
	}

	public GroupLocalService getGroupLocalService() {
		return _groupLocalService;
	}

	public RoleLocalService getRoleLocalService() {
		return _roleLocalService;
	}

	@Reference
	public void setUserLocalService(UserLocalService userLocalService) {
	    _userLocalService = userLocalService;
	}

	@Reference
	public void setOrganizationLocalService(OrganizationLocalService organizationLocalService) {
	    _organizationLocalService = organizationLocalService;
	}

	@Reference
	public void setUserGroupLocalService(UserGroupLocalService userGroupLocalService) {
	    _userGroupLocalService = userGroupLocalService;
	}

	@Reference
	public void setGroupLocalService(GroupLocalService groupLocalService) {
	    _groupLocalService = groupLocalService;
	}
	
	@Reference
	public void setRoleLocalService(RoleLocalService roleLocalService) {
	    _roleLocalService = roleLocalService;
	}
	
	private UserLocalService _userLocalService;
	private OrganizationLocalService _organizationLocalService;
	private UserGroupLocalService _userGroupLocalService;
	private GroupLocalService _groupLocalService;
	private RoleLocalService _roleLocalService;

}