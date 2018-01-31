
<%@include file="../init.jsp"%>

<%
	String mvcPath = ParamUtil.getString(request, "mvcPath");

	ResultRow row = (ResultRow) request.getAttribute("SEARCH_CONTAINER_RESULT_ROW");

	Guestbook guestbook = (Guestbook) row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.UPDATE) %>'>
		<portlet:renderURL var="editURL">
			<portlet:param name="guestbookId"
				value="<%=String.valueOf(guestbook.getGuestbookId())%>" />
			<portlet:param name="mvcPath"
				value="/guestbookadminportlet/edit_guestbook.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="Edit"
			url="<%=editURL.toString()%>" />
	</c:if>
	<c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.DELETE) %>'>
		<portlet:actionURL name="deleteGuestbook" var="deleteURL">
			<portlet:param name="guestbookId"
				value="<%=String.valueOf(guestbook.getGuestbookId())%>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%=deleteURL.toString()%>" />
	</c:if>

</liferay-ui:icon-menu>
