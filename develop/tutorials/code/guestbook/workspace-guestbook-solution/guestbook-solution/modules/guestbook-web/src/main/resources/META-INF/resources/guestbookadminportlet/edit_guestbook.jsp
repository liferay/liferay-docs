<%@ include file="../init.jsp"%>

<%

long guestbookId = ParamUtil.getLong(request, "guestbookId");

Guestbook guestbook = null;

if (guestbookId > 0) {
	guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
}

String headerTitle = (guestbook == null) ? "Add Guestbook" : guestbook.getName();
renderResponse.setTitle(headerTitle);
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath"
		value="/guestbookadminportlet/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name='<%= guestbook == null ? "addGuestbook" : "updateGuestbook" %>' var="addGuestbookURL" />

<aui:form action="<%= addGuestbookURL %>" name="fm">
	<aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />

	<aui:fieldset>
		<aui:input type="hidden" name="guestbookId" value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' />
		<aui:input name="name" />
	</aui:fieldset>

	<liferay-ui:asset-categories-error />
	<liferay-ui:asset-tags-error />
	<liferay-ui:panel defaultState="closed" extended="<%= false %>"
		id="guestbookCategorizationPanel" persistState="<%= true %>"
		title="categorization">
		<aui:fieldset>
			<aui:input name="categories" type="assetCategories" />

			<aui:input name="tags" type="assetTags" />
		</aui:fieldset>
	</liferay-ui:panel>

	<liferay-ui:panel defaultState="closed" extended="<%= false %>"
		id="guestbookAssetLinksPanel" persistState="<%= true %>"
		title="related-assets">
		<aui:fieldset>
			<liferay-ui:input-asset-links
				className="<%= Guestbook.class.getName() %>"
				classPK="<%= guestbookId %>" />
		</aui:fieldset>
	</liferay-ui:panel>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= viewURL %>" type="cancel" />
	</aui:button-row>
</aui:form>