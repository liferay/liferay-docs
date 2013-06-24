<%@include file="/html/init.jsp" %>

<%
	Location location = null;

	long locationId = ParamUtil.getLong(request, "locationId");

	if (locationId > 0) {
		location = LocationLocalServiceUtil.getLocation(locationId);
	}

	String redirect = ParamUtil.getString(request, "redirect");
%>

<aui:model-context bean="<%= location %>" model="<%= Location.class %>" />
<portlet:renderURL var="viewLocationURL" />
<portlet:actionURL name='<%= location == null ? "addLocation" : "updateLocation" %>' var="editLocationURL" windowState="normal" />

<liferay-ui:header
	backURL="<%= viewLocationURL %>"
	title='<%= (location != null) ? location.getName() : "new-location" %>'
/>

<aui:form action="<%= editLocationURL %>" method="POST" name="fm">
	<aui:fieldset>
		<aui:input type="hidden" name="redirect" value="<%= redirect %>" />

		<aui:input type="hidden" name="locationId" value='<%= location == null ? "" : location.getLocationId() %>'/>

		<aui:input name="name" />
		
		<aui:input name="description" />

		<aui:input name="streetAddress" />
		
		<aui:input name="city" />
		
		<aui:input name="stateOrProvince" />
		
		<aui:input name="country" />
		
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel"  onClick="<%= viewLocationURL %>" />
	</aui:button-row>
</aui:form>