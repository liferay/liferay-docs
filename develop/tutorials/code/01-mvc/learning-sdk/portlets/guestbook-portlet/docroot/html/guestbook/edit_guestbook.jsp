
<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addGuestbook" var="addGuestbookURL"></portlet:actionURL>

<liferay-ui:error
        key="existing-guestbook"
        message="existing-guestbook" />

<aui:form action="<%= addGuestbookURL %>" name="<portlet:namespace />fm">

        <aui:fieldset>

            <aui:input name="guestbookName" />

        </aui:fieldset>

        <aui:button-row>

			<aui:button type="submit"></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

        </aui:button-row>
</aui:form>