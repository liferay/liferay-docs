<%@include file="/html/insult/init.jsp"%>

<%
    ResultRow row = (ResultRow) request
            .getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    Insult insult = (Insult) row.getObject();

    String name = Insult.class.getName();
    long insultId = insult.getInsultId();
    long groupId = insult.getGroupId();

    String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:icon-menu>
	<c:if test="<%= permissionChecker.hasPermission(groupId, name, insultId, ActionKeys.UPDATE) %>">
	    <portlet:renderURL var="editURL">
	        <portlet:param name="mvcPath" value="/html/insult/edit_insult.jsp" />
	        <portlet:param name="insultId" value="<%= String.valueOf(insultId) %>" />
	        <portlet:param name="redirect" value="<%= redirect %>" />
	    </portlet:renderURL>
	
	    <liferay-ui:icon image="edit" url="<%= editURL.toString() %>" />
    </c:if>

	<c:if test="<%= permissionChecker.hasPermission(groupId, name, insultId, ActionKeys.DELETE) %>">
	    <portlet:actionURL name="deleteInsult" var="deleteURL">
	        <portlet:param name="insultId" value="<%= String.valueOf(insultId) %>" />
	        <portlet:param name="redirect" value="<%= redirect %>" />
	    </portlet:actionURL>
	
	    <liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
	</c:if>
	
	<c:if test="<%= permissionChecker.hasPermission(groupId, name, insultId, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Insult.class.getName() %>"
			modelResourceDescription="<%= insult.getInsultString() %>"
			resourcePrimKey="<%= String.valueOf(insultId) %>"
			var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>
	    
</liferay-ui:icon-menu>