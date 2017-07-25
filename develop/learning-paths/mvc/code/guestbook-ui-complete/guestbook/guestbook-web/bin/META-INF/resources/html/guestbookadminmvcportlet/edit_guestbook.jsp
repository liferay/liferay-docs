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

long guestbookId = ParamUtil.getLong(request, "guestbookId");

Guestbook guestbook = null;

if (guestbookId > 0) {
	guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
}

String redirect = ParamUtil.getString(request, "redirect");
String headerTitle = (guestbook == null) ? "Add Guestbook" : guestbook.getName();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(headerTitle);
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbookadminmvcportlet/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name='<%= (guestbook == null) ? "addGuestbook" : "updateGuestbook" %>' var="editGuestbookURL" />

        <aui:form 
            action="<%= editGuestbookURL %>" 
            cssClass="container-fluid-1280" 
            name="fm"
        >
	<aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />

	<aui:input name="guestbookId" type="hidden" value="<%= guestbook == null ? StringPool.BLANK : guestbook.getGuestbookId() %>" />

        <aui:fieldset-group markupView="lexicon">
        	<aui:fieldset>
        		<aui:input name="name" />
        	</aui:fieldset>
        </aui:fieldset-group>

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
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" onClick="<%= viewURL %>" type="cancel" />
	</aui:button-row>
</aui:form>