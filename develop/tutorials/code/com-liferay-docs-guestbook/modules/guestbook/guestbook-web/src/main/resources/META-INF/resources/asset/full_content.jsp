 <%@include file="../../init.jsp"%>

        <%
        Guestbook guestbook = (Guestbook)request.getAttribute("gb_guestbook");

        guestbook = guestbook.toEscapedModel();
        %>

        <dl>
                <dt>Name</dt>
                <dd><%= guestbook.getName() %></dd>
        </dl>
        
                request.setAttribute("gb_guestbook", _guestbook);
        
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