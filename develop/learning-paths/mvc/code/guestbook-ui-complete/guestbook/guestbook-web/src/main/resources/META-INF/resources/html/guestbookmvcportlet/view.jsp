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
long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

boolean orderByAsc = false;

String orderByType = ParamUtil.getString(request, "orderByType", "asc");

if (orderByType.equals("asc")) {
	orderByAsc = true;
}

OrderByComparator orderByComparator = null;

if (orderByCol.equals("name")) {
	orderByComparator = new EntryNameComparator(orderByAsc);
}
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);

		for (Guestbook curGuestbook : guestbooks) {
			if (GuestbookPermission.contains(permissionChecker, curGuestbook.getGuestbookId(), "VIEW")) {
		%>

				<portlet:renderURL var="viewPageURL">
					<portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view.jsp" />
					<portlet:param name="guestbookId" value="<%= String.valueOf(curGuestbook.getGuestbookId()) %>" />
				</portlet:renderURL>

				<aui:nav-item href="<%= viewPageURL %>" label="<%= HtmlUtil.escape(curGuestbook.getName()) %>" selected="<%= curGuestbook.getGuestbookId() == guestbookId %>" />

		<%
			}
		}
		%>

	</aui:nav>
</aui:nav-bar>

<liferay-portlet:renderURL varImpl="viewPageURL">
	<portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view.jsp" />
	<portlet:param name="guestbookId" value="<%= String.valueOf(guestbookId) %>" />
	<portlet:param name="displayStyle" value="<%= displayStyle %>" />		
	<portlet:param name="orderByCol" value="<%= orderByCol %>" />		
	<portlet:param name="orderByType" value="<%= orderByType %>" />
</liferay-portlet:renderURL>

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
			portletURL="<%= viewPageURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>
	<liferay-frontend:management-bar-filters>
				<liferay-frontend:management-bar-navigation
					navigationKeys='<%= new String[] {"all"} %>'
					portletURL="<%= viewPageURL %>"
				/>
				
				<liferay-frontend:management-bar-sort
          orderByCol="<%= orderByCol %>"
          orderByType="<%= orderByType %>"
          orderColumns='<%= new String[] {"name"} %>'
          portletURL="<%= viewPageURL %>"
        />
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="no-results-were-found" total="<%= EntryLocalServiceUtil.getEntriesCount(scopeGroupId, guestbookId) %>"
	>
	<liferay-ui:search-container-results
		results="<%= EntryLocalServiceUtil.getEntries(scopeGroupId,
		guestbookId, searchContainer.getStart(), searchContainer.getEnd(),
		orderByComparator) %>"
	/>

		<liferay-ui:search-container-row
			className="com.liferay.docs.guestbook.model.Entry"
			modelVar="entry"
		>
		<c:choose>
		<c:when test='<%= Objects.equals(displayStyle, "icon") %>'>

			<%
			row.setCssClass("entry-card lfr-asset-item");
			%>

			<liferay-ui:search-container-column-text>
				<liferay-frontend:icon-vertical-card
					actionJsp="/html/guestbookmvcportlet/guestbook_actions.jsp"
					actionJspServletContext="<%= application %>"
					icon="quote-left"
					resultRow="<%= row %>"
					subtitle="<%= HtmlUtil.escape(entry.getMessage()) %>"
					title="<%= HtmlUtil.escape(entry.getName()) %>"
				/>
			</liferay-ui:search-container-column-text>
		</c:when>
				<c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
					<liferay-ui:search-container-column-icon
						icon="quote-left"
					/>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<h5>
							<%= HtmlUtil.escape(entry.getName()) %>
						</h5>

						<h6 class="text-default">
							<span><%= HtmlUtil.escape(entry.getMessage()) %></span>
						</h6>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-jsp
						path="/html/guestbookmvcportlet/guestbook_actions.jsp"
					/>
				</c:when>
				<c:when test='<%= Objects.equals(displayStyle, "list") %>'>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						property="message"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						property="name"
					/>

					<liferay-ui:search-container-column-jsp
						path="/html/guestbookmvcportlet/guestbook_actions.jsp"
					/>
				</c:when>
		</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
				displayStyle="<%= displayStyle %>"
				markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>