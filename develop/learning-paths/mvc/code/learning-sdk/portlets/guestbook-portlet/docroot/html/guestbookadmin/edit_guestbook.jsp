<%@include file = "/html/init.jsp" %>

<%
	Guestbook guestbook = null;

	long guestbookId = ParamUtil.getLong(request, "guestbookId");

	if (guestbookId > 0) {
		guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
	}
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbookadmin/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name='<%= guestbook == null ? "addGuestbook" : "updateGuestbook" %>' var="editGuestbookURL" />

<aui:form action="<%= editGuestbookURL %>" name="<portlet:namespace />fm">
		<aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />
		
        <aui:fieldset>
			<aui:input type="hidden" name="guestbookId"
				value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' />
			<aui:input name="name" />
        </aui:fieldset>
        
		<liferay-ui:asset-categories-error />
		<liferay-ui:asset-tags-error />
	    <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="guestbookCategorizationPanel" persistState="<%= true %>" title="categorization">
			<aui:fieldset>
				<aui:input name="categories" type="assetCategories" />

				<aui:input name="tags" type="assetTags" />
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="guestbookAssetLinksPanel" persistState="<%= true %>" title="related-assets">
			<aui:fieldset>
				<liferay-ui:input-asset-links
					className="<%= Guestbook.class.getName() %>"
					classPK="<%= guestbookId %>"
				/>
			</aui:fieldset>
		</liferay-ui:panel>

        <aui:button-row>
			<aui:button type="submit"></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>
        </aui:button-row>
</aui:form>
