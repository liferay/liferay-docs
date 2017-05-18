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
long entryId = ParamUtil.getLong(renderRequest, "entryId");
long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

Entry entry = null;

if (entryId > 0) {
	entry = EntryLocalServiceUtil.getEntry(entryId);

	guestbookId = entry.getGuestbookId();
}

PortletURL backURL = renderResponse.createRenderURL();

backURL.setParameter("mvcPath", "/html/guestbookmvcportlet/view.jsp");
backURL.setParameter("guestbookId", String.valueOf(guestbookId));

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL.toString());

renderResponse.setTitle((entry == null) ? "Add Entry" : entry.getName());
%>

<portlet:actionURL name="addEntry" var="addEntryURL" />

<aui:form action="<%= addEntryURL %>" cssClass="container-fluid-1280" name="fm">
	<aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="name" />

			<aui:input name="email" />

			<aui:input name="message" />

			<aui:input name="entryId" type="hidden" />

			<aui:input name="guestbookId" type="hidden" value="<%= guestbookId %>" />
		</aui:fieldset>
	</aui:fieldset-group>
	
	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" onClick="<%= backURL.toString() %>" type="cancel" />
	</aui:button-row>
</aui:form>