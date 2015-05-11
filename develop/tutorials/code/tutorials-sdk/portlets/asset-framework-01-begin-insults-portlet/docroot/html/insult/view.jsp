<%@include file="/html/insult/init.jsp"%>

<%
	boolean hasAddPermission = permissionChecker.hasPermission(
		scopeGroupId, "com.liferay.docs.insult.model",
		scopeGroupId, "ADD_INSULT");
	boolean hasConfigurePermission = permissionChecker.hasPermission(
		scopeGroupId, "com.liferay.docs.insult.model", scopeGroupId,
		ActionKeys.PERMISSIONS);
%>

<%
    String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<aui:button-row>
	<c:if test='<%= hasAddPermission %>'>
	    <portlet:renderURL var="addInsultURL">
	        <portlet:param name="mvcPath" value="/html/insult/edit_insult.jsp" />
	        <portlet:param name="redirect" value="<%= redirect %>" />
	    </portlet:renderURL>

    	<aui:button onClick="<%= addInsultURL.toString() %>" value="Add Insult" />
    </c:if>
    
    <c:if test='<%= hasConfigurePermission %>'>
		<liferay-security:permissionsURL
			modelResource="com.liferay.docs.insult.model"
			modelResourceDescription="Insults Top Level Actions"
			resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
			var="permissionsURL" />

		<aui:button value="permissions" onClick="<%= permissionsURL %>" />
	</c:if>
	    
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="There are no insults to display">
    <liferay-ui:search-container-results
        results="<%= InsultLocalServiceUtil.getInsultsByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
        total="<%= InsultLocalServiceUtil.getInsultsCountByGroupId(scopeGroupId) %>"
    />

    <liferay-ui:search-container-row
        className="com.liferay.docs.insult.model.Insult"
        keyProperty="insultId"
        modelVar="insult" escapedModel="<%= true %>"
    >
        
        <liferay-ui:search-container-column-text
            name="Insult"
            value="<%= insult.getInsultString() %>"
        />
        
        <liferay-ui:search-container-column-jsp
    		align="right"
    		path="/html/insult/insult_actions.jsp"
		/>

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>