<%@include file="/html/init.jsp" %>

<%@ page import="com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateUtil" %>

This is the <b>Location Listing Portlet</b> portlet in View mode.

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
String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);

long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);
%>

<c:choose>
    <c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
    	<% List<Location> locations = LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId); %>

        <%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, locations) %>
    </c:when>
    <c:otherwise>
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
	</c:otherwise>
</c:choose>