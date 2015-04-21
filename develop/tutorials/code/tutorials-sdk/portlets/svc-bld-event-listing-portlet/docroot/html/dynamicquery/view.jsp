<%@ include file="/html/init.jsp" %>

<portlet:defineObjects />

This is the <b>Dynamic Query</b> portlet in View mode.

<%
	String eventName = "Launch of the ZXY-44B Prototype";
	String eventDescription = "Secret!";
	String locationName = "Launch Site Zeta";

	List<Event> dqEvents = null;
	dqEvents = EventLocalServiceUtil
			.findByEventNameEventDescriptionLocationNameDQ(eventName,
					eventDescription, locationName);
	
	if (dqEvents == null) {
		dqEvents = new ArrayList<Event>();
	}
%>

<liferay-ui:search-container emptyResultsMessage="event-empty-results-message">
	<liferay-ui:search-container-results
		results="<%= dqEvents %>"
		total="<%= dqEvents.size() %>"
	/>
	
	<liferay-ui:search-container-row
		className="com.nosester.portlet.eventlisting.model.Event"
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
			String eventLocationName = "";
	
			try {
				eventLocationName = LocationLocalServiceUtil.getLocation
						(event.getLocationId()).getName();
			} catch (Exception e) {
			}
		%>
	
		<liferay-ui:search-container-column-text
			name="location"
			value="<%= eventLocationName %>"
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