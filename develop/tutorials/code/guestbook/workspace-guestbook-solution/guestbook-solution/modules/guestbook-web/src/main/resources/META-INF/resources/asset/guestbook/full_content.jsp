<%@include file="../../init.jsp"%>

<%
Guestbook guestbook = (Guestbook)request.getAttribute("gb_guestbook");

guestbook = guestbook.toEscapedModel();
%>

<dl>
        <dt>Name</dt>
        <dd><%= guestbook.getName() %></dd>
</dl>