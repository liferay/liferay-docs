<%@ include file="/html/init.jsp" %>

<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	Event event = (Event) row.getObject();

	long groupId = event.getGroupId();
	String name = Event.class.getName();
	long eventId = event.getEventId();

	String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="/html/eventlisting/edit_event.jsp" />
		<portlet:param name="eventId" value="<%= String.valueOf(eventId) %>" />
		<portlet:param name="redirect" value="<%= redirect %>" />
	</portlet:renderURL>

	<liferay-ui:icon image="edit" url="<%= editURL.toString() %>" />

	<portlet:actionURL name="deleteEvent" var="deleteURL">
		<portlet:param name="eventId" value="<%= String.valueOf(eventId) %>" />
		<portlet:param name="redirect" value="<%= redirect %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
</liferay-ui:icon-menu>