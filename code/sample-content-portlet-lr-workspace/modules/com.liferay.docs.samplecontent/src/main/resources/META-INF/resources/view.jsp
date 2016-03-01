<%@ include file="init.jsp" %>

<portlet:actionURL name="addUsers" var="addUsersURL"></portlet:actionURL>
<portlet:actionURL name="addUsersFromJSON" var="addUsersFromJSONURL"></portlet:actionURL>
<portlet:actionURL name="addOrganizations" var="addOrganizationsURL"></portlet:actionURL>
<portlet:actionURL name="addOrganizationsFromJSON" var="addOrganizationsFromJSONURL"></portlet:actionURL>
<portlet:actionURL name="addUserGroups" var="addUserGroupsURL"></portlet:actionURL>
<portlet:actionURL name="addUserGroupsFromJSON" var="addUserGroupsFromJSONURL"></portlet:actionURL>
<portlet:actionURL name="addSites" var="addSitesURL"></portlet:actionURL>

<aui:form action="<%= addUsersURL %>" name="<portlet:namespace />addUsersForm">
	<aui:button-row>
		<aui:button type="submit" value="Add Users"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addUsersFromJSONURL %>" name="<portlet:namespace />addUsersFromJSONForm">
	<aui:button-row>
		<aui:button type="submit" value="Add Users from JSON"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addOrganizationsURL %>" name="<portlet:namespace />addOrganizationsForm">
	<aui:button-row>
		<aui:button type="submit" value="Add Organizations"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addOrganizationsFromJSONURL %>" name="<portlet:namespace />addOrganizationsFromJSONForm">
	<aui:button-row>
		<aui:button type="submit" value="Add Organizations from JSON"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addUserGroupsURL %>" name="<portlet:namespace />addUserGroupsForm">
	<aui:button-row>
		<aui:button type="submit" value="Add User Groups"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addUserGroupsFromJSONURL %>" name="<portlet:namespace />addUserGroupsFromJSONForm">
	<aui:button-row>
		<aui:button type="submit" value="Add User Groups from JSON"></aui:button>
	</aui:button-row>
</aui:form>

<aui:form action="<%= addSitesURL %>" name="<portlet:namespace />addSitesForm">
	<aui:button-row>
		<aui:button type="submit" value="Add Sites"></aui:button>
	</aui:button-row>
</aui:form>