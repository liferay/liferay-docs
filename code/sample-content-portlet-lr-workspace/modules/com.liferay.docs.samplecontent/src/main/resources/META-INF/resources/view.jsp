<%@ include file="init.jsp" %>

<portlet:actionURL name="addUsers" var="addUsersURL"></portlet:actionURL>
<portlet:actionURL name="addOrganizations" var="addOrganizationsURL"></portlet:actionURL>
<portlet:actionURL name="addUserGroups" var="addUserGroupsURL"></portlet:actionURL>

<liferay-ui:tabs names="users,organizations,user-groups" refresh="false" tabsValues="users,organizations,user-groups">
    <liferay-ui:section>
		<p>If your users JSON file specifies organization and user group
		associations, make sure to add the organizations and user groups before
		adding the users.</p>
		<aui:form action="<%= addUsersURL %>" name="<portlet:namespace />addUsersForm" enctype="multipart/form-data" method="post">
			<aui:input name="usersFile" label="Select Users JSON File" type="file" />
			
			<aui:button name="addUsers" value="Add Users" type="submit" />
		</aui:form>
    </liferay-ui:section>
    <liferay-ui:section>
		<aui:form action="<%= addOrganizationsURL %>" name="<portlet:namespace />addOrganizationsForm" enctype="multipart/form-data" method="post">
			<aui:input name="organizationsFile" label="Select Organizations JSON File" type="file" />
			
			<aui:button name="addOrganizations" value="Add Organizations" type="submit" />
		</aui:form>
    </liferay-ui:section>
    <liferay-ui:section>
		<aui:form action="<%= addUserGroupsURL %>" name="<portlet:namespace />addUserGroupsForm" enctype="multipart/form-data" method="post">
			<aui:input name="userGroupsFile" label="Select User Groups JSON File" type="file" />
			
			<aui:button name="addUserGroups" value="Add User Groups" type="submit" />
		</aui:form>
    </liferay-ui:section>
</liferay-ui:tabs>