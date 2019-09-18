<%@include file="../init.jsp"%>

        <%
        String mvcPath = ParamUtil.getString(request, "mvcPath");

        ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

        GuestbookEntry entry = (GuestbookEntry)row.getObject(); 
        %>

        <liferay-ui:icon-menu>

                <portlet:renderURL var="editURL">
                    <portlet:param name="entryId"
                        value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp" />
                </portlet:renderURL>

                <liferay-ui:icon image="edit" message="Edit"
                    url="<%=editURL.toString() %>" />

                <portlet:actionURL name="deleteEntry" var="deleteURL">
                    <portlet:param name="entryId"
                        value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="guestbookId"
                        value="<%= String.valueOf(entry.getGuestbookId()) %>" />
                </portlet:actionURL>

                <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

        </liferay-ui:icon-menu>