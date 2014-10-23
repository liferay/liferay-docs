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

<liferay-ui:asset-links
	assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
	className="<%=Insult.class.getName()%>"
	classPK="<%=ins.getInsultId()%>" />