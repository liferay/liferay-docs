<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.samples.portlet.eventlisting.model.Location"%>
<%@ page import="com.liferay.samples.portlet.eventlisting.service.LocationLocalServiceUtil"%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

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

<liferay-ui:search-container emptyResultsMessage="There are no locations to display">
    <liferay-ui:search-container-results
        results="<%= LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
        total="<%= LocationLocalServiceUtil.getLocationsCountByGroupId(scopeGroupId) %>"
    />

    <liferay-ui:search-container-row
        className="com.liferay.samples.portlet.eventlisting.model.Location"
        keyProperty="locationId"
        modelVar="location" escapedModel="<%= true %>"
    >
        <liferay-ui:search-container-column-text
            name="name"
            value="<%= location.getName() %>"
        />

        <liferay-ui:search-container-column-text
            name="description"
            value="<%= location.getDescription() %>"
        />

        <liferay-ui:search-container-column-text
            name="streetAddress"
            value="<%= location.getStreetAddress() %>"
        />

        <liferay-ui:search-container-column-text
            name="city"
            value="<%= location.getCity() %>"
        />

        <liferay-ui:search-container-column-text
            name="stateOrProvince"
            value="<%= location.getStateOrProvince() %>"
        />

        <liferay-ui:search-container-column-text
            name="country"
            value="<%= location.getCountry() %>"
        />
        
        <liferay-ui:search-container-column-jsp
    		align="right"
    		path="/html/locationlisting/location_actions.jsp"
		/>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>