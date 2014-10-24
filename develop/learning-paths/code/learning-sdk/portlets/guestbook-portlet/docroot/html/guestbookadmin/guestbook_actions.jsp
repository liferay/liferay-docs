<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@include file="/html/init.jsp"%>

<%
	String mvcPath = ParamUtil.getString(request, "mvcPath");

	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

	Guestbook guestbook = (Guestbook) row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if
		test="<%=GuestbookPermission.contains(permissionChecker,
							guestbook.getGuestbookId(), ActionKeys.UPDATE)%>">
		<portlet:renderURL var="editURL">
			<portlet:param name="guestbookId"
				value="<%=String.valueOf(guestbook.getGuestbookId()) %>" />
			<portlet:param name="mvcPath"
				value="/html/guestbookadmin/edit_guestbook.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="Edit"
			url="<%=editURL.toString() %>" />
	</c:if>

	<c:if
		test="<%=GuestbookPermission.contains(permissionChecker,
							guestbook.getGuestbookId(), ActionKeys.PERMISSIONS)%>">
		<liferay-security:permissionsURL
			modelResource="<%= Guestbook.class.getName() %>"
			modelResourceDescription="<%= guestbook.getName() %>"
			resourcePrimKey="<%= String.valueOf(guestbook.getGuestbookId()) %>"
			var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

	<c:if
		test="<%=GuestbookPermission.contains(permissionChecker,
							guestbook.getGuestbookId(), ActionKeys.DELETE)%>">
		<portlet:actionURL name="deleteGuestbook" var="deleteURL">
			<portlet:param name="guestbookId"
				value="<%= String.valueOf(guestbook.getGuestbookId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
	</c:if>
</liferay-ui:icon-menu>