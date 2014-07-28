<%@ include file="/html/init.jsp" %>

This is the <b>Location Listing Portlet</b> in View mode.

<%
    String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<aui:button-row>
    <portlet:renderURL var="addLocationURL">
        <portlet:param name="mvcPath" value="/html/locationlisting/edit_location.jsp" />
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:renderURL>

    <aui:button onClick="<%= addLocationURL.toString() %>" value="add-location" />
</aui:button-row>


	<%
    boolean showLocationAddress_view = GetterUtil.getBoolean(portletPreferences.getValue("showLocationAddress", StringPool.TRUE));
    %>

<liferay-ui:search-container
	emptyResultsMessage="There are no locations to display">
	<liferay-ui:search-container-results
		results="<%= LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= LocationLocalServiceUtil.getLocationsCountByGroupId(scopeGroupId) %>" />

	<liferay-ui:search-container-row
		className="com.liferay.samples.portlet.eventlisting.model.Location"
		keyProperty="locationId" modelVar="location"
		escapedModel="<%= true %>">
		<liferay-ui:search-container-column-text name="name"
			value="<%= location.getName() %>" />

		<liferay-ui:search-container-column-text name="description"
			value="<%= location.getDescription() %>" />

		<c:choose>
			<c:when test="<%= showLocationAddress_view == true %>">

				<liferay-ui:search-container-column-text name="streetAddress"
					value="<%= location.getStreetAddress() %>" />

				<liferay-ui:search-container-column-text name="city"
					value="<%= location.getCity() %>" />

				<liferay-ui:search-container-column-text name="stateOrProvince"
					value="<%= location.getStateOrProvince() %>" />

				<liferay-ui:search-container-column-text name="country"
					value="<%= location.getCountry() %>" />
			</c:when>
		</c:choose>
		<liferay-ui:search-container-column-jsp align="right"
			path="/html/locationlisting/location_actions.jsp" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>