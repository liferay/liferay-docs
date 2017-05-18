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

<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Guestbook guestbook = (Guestbook)row.getObject();
%>

<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
		showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="guestbookId" value="<%= String.valueOf(guestbook.getGuestbookId()) %>" />
			<portlet:param name="mvcPath" value="/html/guestbookadminmvcportlet/edit_guestbook.jsp" />
			<portlet:param name="redirect" value="${currentURL}" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="Edit"
			url="<%= editURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Guestbook.class.getName() %>"
			modelResourceDescription="<%= guestbook.getName() %>"
			resourcePrimKey="<%= String.valueOf(guestbook.getGuestbookId()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon
			message="permissions"
			url="<%= permissionsURL %>"
		/>
	</c:if>

	<c:if test="<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteGuestbook" var="deleteURL">
			<portlet:param name="guestbookId" value="<%= String.valueOf(guestbook.getGuestbookId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL.toString() %>"
		/>
	</c:if>
</liferay-ui:icon-menu>