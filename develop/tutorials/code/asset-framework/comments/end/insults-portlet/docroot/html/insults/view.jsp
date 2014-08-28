<%@include file="/html/insults/init.jsp"%>

<%
	boolean hasAddPermission = permissionChecker.hasPermission(
		scopeGroupId, "com.sample.portlet.insults.model",
		scopeGroupId, "ADD_INSULT");
	boolean hasConfigurePermission = permissionChecker.hasPermission(
		scopeGroupId, "com.sample.portlet.insults.model", scopeGroupId,
		ActionKeys.PERMISSIONS);
%>

<%
    String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<aui:button-row>
	<c:if test='<%= hasAddPermission %>'>
	    <portlet:renderURL var="addInsultURL">
	        <portlet:param name="mvcPath" value="/html/insults/edit_insults.jsp" />
	        <portlet:param name="redirect" value="<%= redirect %>" />
	    </portlet:renderURL>

    	<aui:button onClick="<%= addInsultURL.toString() %>" value="Add Insult" />
    </c:if>
    
    <c:if test='<%= hasConfigurePermission %>'>
		<liferay-security:permissionsURL
			modelResource="com.sample.portlet.insults.model"
			modelResourceDescription="Insults Top Level Actions"
			resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
			var="permissionsURL" />

		<aui:button value="permissions" onClick="<%= permissionsURL %>" />
	</c:if>
	    
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="There are no insults to display">
    <liferay-ui:search-container-results
        results="<%= InsultLocalServiceUtil.getInsults(searchContainer.getStart(), searchContainer.getEnd()) %>"
        total="<%= InsultLocalServiceUtil.getInsultsCount() %>"
    />

    <liferay-ui:search-container-row
        className="com.sample.portlet.insults.model.Insult"
        keyProperty="insultId"
        modelVar="insult" escapedModel="<%= true %>"
    >
    	
        <portlet:renderURL windowState="maximized" var="rowURL">
      		<portlet:param name="mvcPath" value="/html/insults/view_insult.jsp" />
      		<portlet:param name="insultId" value="<%= String.valueOf(insult.getInsultId()) %>" />
    	</portlet:renderURL>
        
        <liferay-ui:search-container-column-text
            name="Insult"
            value="<%= insult.getInsultString() %>"
            href="<%=rowURL %>"
        />
        
        <liferay-ui:search-container-column-jsp
    		align="right"
    		path="/html/insults/insult_actions.jsp"
		/>

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>