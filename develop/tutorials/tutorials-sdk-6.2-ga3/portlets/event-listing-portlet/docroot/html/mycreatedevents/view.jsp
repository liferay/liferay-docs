<%@ include file="/html/init.jsp" %>

<%@ page import="com.liferay.portal.model.User" %>

This is the <b>My Created Events</b> portlet in View mode.

<%
User currentUser = PortalUtil.getUser(request);
long currentUserId = user.getUserId();

List<Event> myCreatedEvents = EventLocalServiceUtil.findByUserId(currentUserId);

if (myCreatedEvents == null) {
	myCreatedEvents = new ArrayList<Event>();
}
%>

<liferay-ui:search-container emptyResultsMessage="event-empty-results-message">
	<liferay-ui:search-container-results
		results="<%= myCreatedEvents %>"
		total="<%= myCreatedEvents.size() %>"
	/>
	
	<liferay-ui:search-container-row
		className="com.liferay.docs.eventlisting.model.Event"
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
