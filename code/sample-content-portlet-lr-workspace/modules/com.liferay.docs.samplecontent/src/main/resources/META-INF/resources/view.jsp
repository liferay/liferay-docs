<%@ include file="init.jsp" %>

<portlet:actionURL name="addUsers" var="addUsersURL"></portlet:actionURL>
<portlet:actionURL name="addOrganizations" var="addOrganizationsURL"></portlet:actionURL>
<portlet:actionURL name="addUserGroups" var="addUserGroupsURL"></portlet:actionURL>
<portlet:actionURL name="addOrganizationsFromFile" var="addOrganizationsFromFileURL"></portlet:actionURL>
<portlet:actionURL name="addUserGroupsFromFile" var="addUserGroupsFromFileURL"></portlet:actionURL>

<aui:form action="<%= addUsersURL %>" name="<portlet:namespace />addUsersForm">
	<aui:button-row>
		<aui:button type="submit" value="Add Users"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addOrganizationsURL %>" name="<portlet:namespace />addOrganizationsForm">
	<aui:button-row>
		<aui:button type="submit" value="Add Organizations"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addUserGroupsURL %>" name="<portlet:namespace />addUserGroupsForm">
	<aui:button-row>
		<aui:button type="submit" value="Add User Groups"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addOrganizationsFromFileURL %>" name="<portlet:namespace />addOrganizationsFromFileForm" enctype="multipart/form-data" method="post">
	<aui:input name="organizationsFile" label="Select Organizations JSON File" type="file" />
	
	<aui:button name="addOrganizations" value="Add Organizations" type="submit" />
</aui:form>

<aui:form action="<%= addUserGroupsFromFileURL %>" name="<portlet:namespace />addUserGroupsFromFileForm" enctype="multipart/form-data" method="post">
	<aui:input name="userGroupsFile" label="Select User Groups JSON File" type="file" />
	
	<aui:button name="addUserGroups" value="Add User Groups" type="submit" />
</aui:form>