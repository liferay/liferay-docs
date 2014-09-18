<%@include file="/html/init.jsp"%>

<%
Entry entry = (Entry)request.getAttribute("gb_entry");

entry = entry.toEscapedModel();
%>

<h1><%= entry.getName() %></h1>

<dl>
	<dt>Guestbook</dt>
	<dd><%= GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()) %></dd>
	<dt>Message</dt>
	<dd><%= entry.getMessage() %></dd>
</dl>