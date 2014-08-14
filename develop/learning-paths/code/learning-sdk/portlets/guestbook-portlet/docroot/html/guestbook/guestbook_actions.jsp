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

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Entry entry = (Entry)row.getObject(); 
%>

<liferay-ui:icon-menu>

	<c:if
		test="<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="entryId"
				value="<%=String.valueOf(entry.getEntryId()) %>" />
			<portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="Edit"
			url="<%=editURL.toString() %>" />
	</c:if>

	<c:if
		test="<%=EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">


		<liferay-security:permissionsURL
			modelResource="<%= Entry.class.getName() %>"
			modelResourceDescription="<%= entry.getMessage() %>"
			resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
			var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

	</c:if>

	<c:if
		test="<%=EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>">

		<portlet:actionURL name="deleteEntry" var="deleteURL">
			<portlet:param name="entryId"
				value="<%= String.valueOf(entry.getEntryId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

	</c:if>

</liferay-ui:icon-menu>