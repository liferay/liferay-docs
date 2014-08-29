<%@include file="/html/insults/init.jsp"%>

<portlet:renderURL windowState="normal" var="backURL">
	<portlet:param name="mvcPath" value="/html/insults/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header backURL="<%=backURL%>" title="insult" />

<%
	long insultId = ParamUtil.getLong(renderRequest, "insultId");
	Insult ins = InsultLocalServiceUtil.getInsult(insultId);
%>

<h1><%=ins.getInsultString()%></h1>

<dl class="insult">
	<dt>
		<liferay-ui:message key="Insult" />
	</dt>
	<dd><%=HtmlUtil.escape(ins.getInsultString())%></dd>
</dl>

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