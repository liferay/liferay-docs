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

<liferay-ui:social-bookmarks
    displayStyle="horizontal"
    target="_blank"
    title="<%= ins.getInsultString() %>"
    url="<%= PortalUtil.getCanonicalURL((PortalUtil.getCurrentURL(request)), themeDisplay, layout) %>" 
/>