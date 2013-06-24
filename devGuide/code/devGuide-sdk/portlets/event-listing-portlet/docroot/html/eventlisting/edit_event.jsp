<%@include file="/html/init.jsp" %>

<%
	Event event = null;

	long eventId = ParamUtil.getLong(request, "eventId");

	if (eventId > 0) {
		event = EventLocalServiceUtil.getEvent(eventId);
	}
	
	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY); 
	
	long scopeGroupId = themeDisplay.getScopeGroupId();
	
	List<Location> locations = LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId);

	String redirect = ParamUtil.getString(request, "redirect");
%>

<aui:model-context bean="<%= event %>" model="<%= Event.class %>" />
<portlet:renderURL var="viewEventURL" />
<portlet:actionURL name='<%= event == null ? "addEvent" : "updateEvent" %>' var="editEventURL" windowState="normal" />

<liferay-ui:header
	backURL="<%= viewEventURL %>"
	title='<%= (event != null) ? event.getName() : "new-event" %>'
/>

<aui:form action="<%= editEventURL %>" method="POST" name="fm">
	<aui:fieldset>
		<aui:input type="hidden" name="redirect" value="<%= redirect %>" />

		<aui:input type="hidden" name="eventId" value='<%= event == null ? "" : event.getEventId() %>'/>

		<aui:input name="name" />

		<aui:input name="description" />
		
		<aui:select name="locationId" showEmptyOption="<%= true %>" label="Location">

	 		<%
				for (Location location : locations){
			%>
			<aui:option value="<%= location.getLocationId() %>" selected="<%= event != null && location.getLocationId() == event.getLocationId() %>"><%=location.getName()%></aui:option>
			<%
				}
			%>

		</aui:select>
		
		<aui:input name="date" />

	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel"  onClick="<%= viewEventURL %>" />
	</aui:button-row>
</aui:form>