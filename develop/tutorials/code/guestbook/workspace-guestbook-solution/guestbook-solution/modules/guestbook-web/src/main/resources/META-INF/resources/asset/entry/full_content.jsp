<%@include file="../../init.jsp"%>

<%
Entry entry = (Entry)request.getAttribute("gb_entry");

entry = entry.toEscapedModel();
%>

<dl>
        <dt>Guestbook</dt>
        <dd><%= GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName() %></dd>
        <dt>Name</dt>
        <dd><%= entry.getName() %></dd>
        <dt>Message</dt>
        <dd><%= entry.getMessage() %></dd>
</dl>