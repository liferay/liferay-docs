<%@ include file="init.jsp" %>

<portlet:actionURL name="addUsers" var="addUsersURL"></portlet:actionURL>

<aui:form action="<%= addUsersURL %>" name="<portlet:namespace />addUsersForm">
	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>
</aui:form>