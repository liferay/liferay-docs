<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<portlet:defineObjects />

<aui:button-row cssClass="guestbook-buttons">

	<portlet:renderURL var="addEntryURL">
		<portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp"></portlet:param>
	</portlet:renderURL>
	
	<aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
	
</aui:button-row>