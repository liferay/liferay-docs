<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbookadmin/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header backURL="<%= viewURL %>" title="guestbook" />

<%
	long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
	Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
	guestbook = guestbook.toEscapedModel();
	
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			Guestbook.class.getName(), guestbook.getGuestbookId());

	String currentURL = PortalUtil.getCurrentURL(request);

	PortalUtil.addPortletBreadcrumbEntry(request, guestbook.getName(),
			currentURL);

	PortalUtil.setPageSubtitle(guestbook.getName(), request);
	PortalUtil.setPageDescription(guestbook.getName(), request);

	List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
			Guestbook.class.getName(), guestbook.getGuestbookId());
	PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"),
			request);
%>

<dl>
	<dt>Name</dt>
	<dd><%= guestbook.getName() %></dd>
</dl>

<c:if test="<%= themeDisplay.isSignedIn() %>">
	<liferay-ui:panel-container extended="<%= false %>"
		id="guestbookCollaborationPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"
	        id="guestbookCollaborationPanel" persistState="<%= true %>"
	        title='<%= LanguageUtil.get(pageContext, "collaboration") %>'>
			<liferay-ui:ratings className="<%= Guestbook.class.getName() %>"
				classPK="<%= guestbook.getGuestbookId() %>" type="stars" />
			
			<br />

			<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
	
			<liferay-ui:discussion className="<%= Guestbook.class.getName() %>"
	            classPK="<%= guestbook.getGuestbookId() %>"
	            formAction="<%= discussionURL %>" formName="fm2"
	            ratingsEnabled="<%= true %>" redirect="<%= currentURL %>"
	            subject="<%= guestbook.getName() %>"
	            userId="<%= guestbook.getUserId() %>" />

		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>

<liferay-ui:asset-links
	assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
	className="<%= Guestbook.class.getName() %>"
	classPK="<%= guestbook.getGuestbookId() %>" />