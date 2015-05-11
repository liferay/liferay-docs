<%@include file="/html/insult/init.jsp"%>

<%
Insult ins = (Insult)request.getAttribute("INSULT ENTRY");
%>

<h1><%=ins.getInsultString() %></h1>

<dl class="insult">
	<dt>
		<liferay-ui:message key="Hey! Yeah, you there!" />
	</dt>
	<dd><%=HtmlUtil.escape(ins.getInsultString())%></dd>

</dl>
	    