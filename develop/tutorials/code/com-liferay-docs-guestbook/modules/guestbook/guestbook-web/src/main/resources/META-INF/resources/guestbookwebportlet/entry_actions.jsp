<%@include file="../init.jsp"%>

        <%
        String mvcPath = ParamUtil.getString(request, "mvcPath");

        ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

        Entry entry = (Entry)row.getObject(); 
        %>

        <liferay-ui:icon-menu>
        
        <portlet:renderURL var="viewEntryURL">
      <portlet:param name="entryId"
        value="<%= String.valueOf(entry.getEntryId()) %>" />
      <portlet:param name="mvcPath"
        value="/guestbookwebportlet/view_entry.jsp" />
    </portlet:renderURL>

    <liferay-ui:icon message="View" url="<%= viewEntryURL.toString() %>" />
        

            <c:if
                test="<%= GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.UPDATE) %>">
                <portlet:renderURL var="editURL">
                    <portlet:param name="entryId"
                        value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="mvcPath" value="/guestbookwebportlet/edit_entry.jsp" />
                </portlet:renderURL>

                <liferay-ui:icon image="edit" message="Edit"
                    url="<%=editURL.toString() %>" />
            </c:if>

            <c:if
            test="<%=GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">

                <liferay-security:permissionsURL
                    modelResource="<%= Entry.class.getName() %>"
                    modelResourceDescription="<%= entry.getMessage() %>"
                    resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
                    var="permissionsURL" />
            
                <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

            </c:if>

            <c:if
                test="<%=GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>">

                <portlet:actionURL name="deleteEntry" var="deleteURL">
                    <portlet:param name="entryId"
                        value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="guestbookId"
                        value="<%= String.valueOf(entry.getGuestbookId()) %>" />
                </portlet:actionURL>

                <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
            </c:if>

        </liferay-ui:icon-menu>
