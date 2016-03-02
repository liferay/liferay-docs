<%@ include file="init.jsp" %>

<portlet:actionURL name="addUsers" var="addUsersURL"></portlet:actionURL>
<portlet:actionURL name="addOrganizations" var="addOrganizationsURL"></portlet:actionURL>
<portlet:actionURL name="addUserGroups" var="addUserGroupsURL"></portlet:actionURL>

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