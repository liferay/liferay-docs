
<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

<%
long entryId = ParamUtil.getLong(renderRequest, "entryId");

Entry entry = null;

if (entryId > 0) {
	
	entry = EntryLocalServiceUtil.getEntry(entryId);
	
}

%>

<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">

        <aui:fieldset>

		    <aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />

            <aui:input name="name" />
            <aui:input name="email" />
            <aui:input name="message" />
            <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>
            <aui:input name="entryId" type="hidden" />

        </aui:fieldset>

        <aui:button-row>

			<aui:button type="submit"></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

        </aui:button-row>
</aui:form>