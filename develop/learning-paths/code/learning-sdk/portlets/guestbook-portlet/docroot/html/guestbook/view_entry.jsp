<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header backURL="<%= viewURL %>" title="entry" />

<%
	long entryId = ParamUtil.getLong(renderRequest, "entryId");
	Entry entry = EntryLocalServiceUtil.getEntry(entryId);
	entry = entry.toEscapedModel();
	
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
	Entry.class.getName(), entry.getEntryId());
	
	String currentURL = PortalUtil.getCurrentURL(request);

	PortalUtil.addPortletBreadcrumbEntry(request, entry.getMessage(),
			currentURL);

	PortalUtil.setPageSubtitle(entry.getMessage(), request);
	PortalUtil.setPageDescription(entry.getMessage(), request);

	List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
			Entry.class.getName(), entry.getEntryId());
	PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"),
			request);
%>

<dl>
	<dt>Guestbook</dt>
	<dd><%= GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName() %></dd>
	<dt>Name</dt>
	<dd><%= entry.getName() %></dd>
	<dt>Message</dt>
	<dd><%= entry.getMessage() %></dd>
</dl>

<c:if test="<%= themeDisplay.isSignedIn() %>">
	<liferay-ui:panel-container extended="<%= false %>"
		id="entryCollaborationPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"
	        id="entryCollaborationPanel" persistState="<%= true %>"
	        title='<%= LanguageUtil.get(pageContext, "collaboration") %>'>
			<liferay-ui:ratings className="<%= Entry.class.getName() %>"
				classPK="<%= entry.getEntryId() %>" type="stars" />
			
			<br />

			<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
	
			<liferay-ui:discussion className="<%= Entry.class.getName() %>"
	            classPK="<%= entry.getEntryId() %>"
	            formAction="<%= discussionURL %>" formName="fm2"
	            ratingsEnabled="<%= true %>" redirect="<%= currentURL %>"
	            subject="<%= entry.getMessage() %>"
	            userId="<%= entry.getUserId() %>" />

		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>

<liferay-ui:asset-links
	assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
	className="<%= Entry.class.getName() %>"
	classPK="<%= entry.getEntryId() %>" />