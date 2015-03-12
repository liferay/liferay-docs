<%@include file="/html/insult/init.jsp"%>

<portlet:renderURL windowState="normal" var="backURL">
    <portlet:param name="mvcPath" value="/html/insult/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header backURL="<%=backURL%>" title="insult" />

<%
long insultId = ParamUtil.getLong(renderRequest, "insultId");
Insult ins = InsultLocalServiceUtil.getInsult(insultId);
AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(Insult.class.getName(), ins.getInsultId());
%>

<h1><%=ins.getInsultString()%></h1>

<dl class="insult">
    <dt>
       <liferay-ui:message key="Insult" />
    </dt>
    <dd><%=HtmlUtil.escape(ins.getInsultString())%></dd>
</dl>

<liferay-ui:ratings className="<%=Insult.class.getName()%>"
    classPK="<%=ins.getInsultId()%>" type="stars" />

<liferay-ui:social-bookmarks
    displayStyle="horizontal"
    target="_blank"
    title="<%= ins.getInsultString() %>"
    url="<%= PortalUtil.getCanonicalURL((PortalUtil.getCurrentURL(request)), themeDisplay, layout) %>" 
/>

<liferay-ui:flags
    className="<%= Insult.class.getName() %>"
    classPK="<%= ins.getInsultId() %>"
    contentTitle="<%= ins.getInsultString() %>"
    reportedUserId="<%= ins.getUserId() %>"
/>

<liferay-ui:asset-links
    assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
    className="<%=Insult.class.getName()%>"
    classPK="<%=ins.getInsultId()%>" />

<c:if test="<%=themeDisplay.isSignedIn()%>">
	<liferay-ui:panel-container extended="<%=false%>"
		id="insultCommentsPanelContainer" persistState="<%=true%>">

		<liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
			id="insultCommentsPanel" persistState="<%=true%>"
			title='<%=LanguageUtil.get(pageContext, "comments")%>'>

			<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

			<%
			String currentUrl = PortalUtil.getCurrentURL(request);
			%>

			<liferay-ui:discussion className="<%=Insult.class.getName()%>"
				classPK="<%=ins.getInsultId()%>"
				formAction="<%=discussionURL%>" formName="fm2"
				ratingsEnabled="<%=true%>" redirect="<%=currentUrl%>"
				subject="<%=ins.getInsultString()%>"
				userId="<%=ins.getUserId()%>" />

		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>