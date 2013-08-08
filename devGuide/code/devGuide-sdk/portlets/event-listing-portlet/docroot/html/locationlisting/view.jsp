<%@include file="/html/init.jsp" %>

This is the <b>Location Listing Portlet</b> portlet in View mode.

<%
	String redirect = PortalUtil.getCurrentURL(renderRequest);
	ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	long scopeGroupId = themeDisplay.getScopeGroupId();
%>

<aui:button-row>
	<portlet:renderURL var="addLocationURL">
		<portlet:param name="mvcPath" value="/html/locationlisting/edit_location.jsp" />
		<portlet:param name="redirect" value="<%= redirect %>" />
	</portlet:renderURL>

	<aui:button onClick="<%= addLocationURL.toString() %>" value="add-location" />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="location-empty-results-message">
	<liferay-ui:search-container-results
		results="<%= LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= LocationLocalServiceUtil.getLocationsCountByGroupId(scopeGroupId) %>"
	/>

	<liferay-ui:search-container-row
		className="com.nosester.portlet.eventlisting.model.Location"
		keyProperty="locationId"
		modelVar="location" escapedModel="<%= true %>"
	>
		<liferay-ui:search-container-column-text
			name="name"
			value="<%= location.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			property="description"
		/>

		<liferay-ui:search-container-column-text
			name="street-address"
			property="streetAddress"
		/>

		<liferay-ui:search-container-column-text
			name="city"
			property="city"
		/>

		<liferay-ui:search-container-column-text
			name="state-province"
			property="stateOrProvince"
		/>

		<liferay-ui:search-container-column-text
			name="country"
			property="country"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/locationlisting/location_actions.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>