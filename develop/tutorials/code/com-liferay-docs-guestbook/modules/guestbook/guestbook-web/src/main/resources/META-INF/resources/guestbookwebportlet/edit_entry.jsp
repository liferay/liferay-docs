<%@ include file="../init.jsp" %>

<%
long entryId = ParamUtil.getLong(renderRequest, "entryId");

Entry entry = null;

if (entryId > 0) {
    entry = EntryLocalServiceUtil.getEntry(entryId);
}

long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
%>

<portlet:renderURL var="viewURL">
	<portlet:param 
	    name="mvcPath" 
	    value="/guestbookwebportlet/view.jsp" 
            />
</portlet:renderURL>

<liferay-ui:header
backURL="<%= viewURL.toString() %>"
title="<%= entry == null ? "Add Entry" : entry.getName() %>"
    />

<portlet:actionURL name="addEntry" var="addEntryURL" />

<aui:form action="<%= addEntryURL %>" name="fm">
	<aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:input name="email" />

		<aui:input name="message" />

		<aui:input name="entryId" type="hidden" />

		<aui:input name="guestbookId" type="hidden" 
			   value=
		"<%= entry == null ? guestbookId : entry.getGuestbookId() %>" />
	</aui:fieldset>

        <liferay-ui:asset-categories-error />
	<liferay-ui:asset-tags-error />
	<liferay-ui:panel defaultState="closed" 
	extended="<%= false %>" id="entryCategorizationPanel" 
	persistState="<%= true %>" title="categorization">
		<aui:fieldset>
			<liferay-asset:asset-categories-selector className="<%= Entry.class.getName() %>" classPK="<%= entryId %>" />
			<liferay-asset:asset-tags-selector className="<%= Entry.class.getName() %>" classPK="<%= entryId %>" />
		</aui:fieldset>
	</liferay-ui:panel>

	<liferay-ui:panel defaultState="closed" 
	extended="<%= false %>" id="entryAssetLinksPanel" 
	persistState="<%= true %>" title="related-assets">
		<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="related-assets">

			<liferay-asset:input-asset-links
			className="<%= Entry.class.getName() %>"
			classPK="<%= entryId %>"
			    />

		</aui:fieldset>
	</liferay-ui:panel>

	<aui:button-row>
                <aui:button type="submit" />

                <aui:button onClick="<%= viewURL.toString() %>" type="cancel" />
	</aui:button-row>
</aui:form>
