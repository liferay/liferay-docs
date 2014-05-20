
<%@ include file="/html/init.jsp" %>

This is the <b>Event Listing Portlet</b> in View mode.

<%
	String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<aui:button-row>
	<portlet:renderURL var="addEventURL">
		<portlet:param name="mvcPath" value="/html/eventlisting/edit_event.jsp" />
		<portlet:param name="redirect" value="<%= redirect %>" />
	</portlet:renderURL>

	<aui:button onClick="<%= addEventURL.toString() %>" value="add-event" />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="event-empty-results-message">
	<liferay-ui:search-container-results
		results="<%= EventLocalServiceUtil.getEventsByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= EventLocalServiceUtil.getEventsCountByGroupId(scopeGroupId) %>"
	/>

	<liferay-ui:search-container-row
		className="com.samples.portlet.eventlisting.model.Event"
		keyProperty="eventId"
		modelVar="event" escapedModel="<%= true %>"
	>
		<liferay-ui:search-container-column-text
			name="name"
			value="<%= event.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			property="description"
		/>

		<%
			String locationName = "";

			try {
				locationName = LocationLocalServiceUtil.getLocation
						(event.getLocationId()).getName();
			} catch (Exception e) {
			}
		%>

		<liferay-ui:search-container-column-text
			name="location"
			value="<%= locationName %>"
		/>

		<%
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		%>

		<liferay-ui:search-container-column-text
			name="date"
			value="<%= sdf.format(event.getDate()) %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/eventlisting/event_actions.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>