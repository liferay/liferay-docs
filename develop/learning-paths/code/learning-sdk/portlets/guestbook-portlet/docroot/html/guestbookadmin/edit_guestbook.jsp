<%@include file = "/html/init.jsp" %>

<%
	Guestbook guestbook = null;

	long guestbookId = ParamUtil.getLong(request, "guestbookId");

	if (guestbookId > 0) {
		guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
	}
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbookadmin/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name='<%= guestbook == null ? "addGuestbook" : "updateGuestbook" %>' var="editGuestbookURL" />

<aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />

<aui:form action="<%= editGuestbookURL %>" name="<portlet:namespace />fm">
        <aui:fieldset>
			<aui:input type="hidden" name="guestbookId"
				value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' />
			<aui:input name="name" />
        </aui:fieldset>

        <aui:button-row>
			<aui:button type="submit"></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>
        </aui:button-row>
</aui:form>