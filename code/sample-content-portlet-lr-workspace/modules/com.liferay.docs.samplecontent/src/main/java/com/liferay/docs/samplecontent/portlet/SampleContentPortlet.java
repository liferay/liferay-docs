package com.liferay.docs.samplecontent.portlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
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
		"javax.portlet.display-name=Sample Content Portlet",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class SampleContentPortlet extends MVCPortlet {
	
	public void addUsers(ActionRequest request, ActionResponse response) throws PortalException, IOException {
		long companyId = PortalUtil.getDefaultCompanyId();
		Role adminRole = _roleLocalService.getRole(companyId, "Administrator");
		List<User> adminUsers = _userLocalService.getRoleUsers(adminRole.getRoleId());
		long adminUserId = adminUsers.get(0).getUserId();
		
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		URL jsonURL = bundle.getResource("users.json");
		StringBuilder out = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(jsonURL.openConnection().getInputStream()));
		while (br.ready()){
			out.append(br.readLine());
		}
		br.close();	
		String jsonString = out.toString();
		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonString);
		JSONArray users = jsonObject.getJSONArray("Users");
		for (int i = 0; i < users.length(); i++) {
			String screenName = users.getJSONObject(i).getString("Screen Name");
			String firstName = users.getJSONObject(i).getString("First Name");
			String lastName = users.getJSONObject(i).getString("Last Name");
			boolean male = users.getJSONObject(i).getBoolean("Male");
			String jobTitle = users.getJSONObject(i).getString("Job Title");
			String emailAddress = users.getJSONObject(i).getString("Email Address");

			JSONArray userGroups = users.getJSONObject(i).getJSONArray("User Groups");
			int userGroupsLength = userGroups.length();
			long[] userGroupIds = new long[userGroupsLength];
			for (int j = 0; j < userGroupsLength; j++) {
				String userGroupName = userGroups.getString(j);
				UserGroup userGroup = _userGroupLocalService.getUserGroup(companyId, userGroupName);
				long userGroupId = userGroup.getUserGroupId();
				userGroupIds[j] = userGroupId;
			}

			JSONArray organizations = users.getJSONObject(i).getJSONArray("Organizations");
			int organizationsLength = organizations.length();
			long[] organizationIds = new long[organizationsLength];
			for (int j = 0; j < organizationsLength; j++) {
				String organizationName = organizations.getString(j);
				Organization organization = _organizationLocalService.getOrganization(companyId, organizationName);
				long organizationId = organization.getOrganizationId();
				organizationIds[j] = organizationId;
			}
			
			try {
				_userLocalService.addUser(adminUserId, companyId, false, "liferay", "liferay", false, screenName, emailAddress, 0L, StringPool.BLANK, LocaleUtil.getDefault(), firstName, StringPool.BLANK, lastName, 0L, 0L, male, Calendar.JANUARY, 1, 1970, jobTitle, new long[0], organizationIds, new long[0], userGroupIds, false, null);
			}
			catch (PortalException pe) {
				_log.error(pe);
			}
		}
	}	

	public void addOrganizations(ActionRequest request, ActionResponse response) throws PortalException, IOException {
		long companyId = PortalUtil.getDefaultCompanyId();
		Role adminRole = _roleLocalService.getRole(companyId, "Administrator");
		List<User> adminUsers = _userLocalService.getRoleUsers(adminRole.getRoleId());
		long adminUserId = adminUsers.get(0).getUserId();
		
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		URL jsonURL = bundle.getResource("organizations.json");
		StringBuilder out = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(jsonURL.openConnection().getInputStream()));
		while (br.ready()){
			out.append(br.readLine());
		}
		br.close();	
		String jsonString = out.toString();
		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonString);
		JSONArray orgs = jsonObject.getJSONArray("Organizations");
		
		Queue<JSONOrgWrapper> queue = new LinkedList<JSONOrgWrapper>();
		
		for (int i = 0; i < orgs.length(); i++) {
			JSONOrgWrapper jsonOrgWrapper = new JSONOrgWrapper(orgs.getJSONObject(i), StringPool.BLANK);

			queue.add(jsonOrgWrapper);
		}
		
		while (!queue.isEmpty()) {
			JSONOrgWrapper jsonOrgWrapper = queue.remove();
			String orgName = jsonOrgWrapper.getJSONObject().getString("Name");
			JSONArray subOrgs = jsonOrgWrapper.getJSONObject().getJSONArray("Organizations");
			
			String parentOrgName = jsonOrgWrapper.getParentOrgName();
			if (StringPool.BLANK.equals(parentOrgName)) {
				try {
					_organizationLocalService.addOrganization(adminUserId, 0L, orgName, true);
				}
				catch (PortalException pe) {
					_log.error(pe);
				}
			}
			else {
				long parentOrgId = _organizationLocalService.getOrganizationId(companyId, parentOrgName);
				try {
					_organizationLocalService.addOrganization(adminUserId, parentOrgId, orgName, true);
				}
				catch (PortalException pe) {
					_log.error(pe);
				}
			}

			if (subOrgs == null) {
				continue;
			}
			
			for (int i = 0; i < subOrgs.length(); i++) {
				JSONOrgWrapper jsonSubOrgWrapper = new JSONOrgWrapper(subOrgs.getJSONObject(i), orgName);
				queue.add(jsonSubOrgWrapper);
			}
		}
	}	

	public void addUserGroups(ActionRequest request, ActionResponse response) throws PortalException, IOException {
		long companyId = PortalUtil.getDefaultCompanyId();
		Role adminRole = _roleLocalService.getRole(companyId, "Administrator");
		List<User> adminUsers = _userLocalService.getRoleUsers(adminRole.getRoleId());
		long adminUserId = adminUsers.get(0).getUserId();

		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		URL jsonURL = bundle.getResource("user-groups.json");
		StringBuilder out = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(jsonURL.openConnection().getInputStream()));
		while (br.ready()){
			out.append(br.readLine());
		}
		br.close();	
		String jsonString = out.toString();
		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonString);
		JSONArray userGroups = jsonObject.getJSONArray("User Groups");
		for (int i = 0; i < userGroups.length(); i++) {
			String name = userGroups.getJSONObject(i).getString("Name");
			String description = userGroups.getJSONObject(i).getString("Description");

			try {
				_userGroupLocalService.addUserGroup(adminUserId, companyId, name, description, null);
			}
			catch (PortalException pe) {
				_log.error(pe);
			}
		}
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
	public void setRoleLocalService(RoleLocalService roleLocalService) {
	    _roleLocalService = roleLocalService;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(SampleContentPortlet.class);
	
	private UserLocalService _userLocalService;
	private OrganizationLocalService _organizationLocalService;
	private UserGroupLocalService _userGroupLocalService;
	private RoleLocalService _roleLocalService;

	private class JSONOrgWrapper {
		
		public JSONOrgWrapper(JSONObject jsonObject, String parentOrgName) {
			_jsonObject = jsonObject;
			_parentOrgName = parentOrgName;
		}
		
		public JSONObject getJSONObject() {
			return _jsonObject;
		}
		
		public String getParentOrgName() {
			return _parentOrgName;
		}
		
		private JSONObject _jsonObject;
		private String _parentOrgName;
		
	}

}