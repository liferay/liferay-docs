<%@ include file="/init.jsp" %>

<%
int userCount = GetterUtil.getInteger(renderRequest.getAttribute("USER_COUNT"));

int bookmarksFolderCount = GetterUtil.getInteger(renderRequest.getAttribute("BOOKMARKS_FOLDER_COUNT"));
%>

<p>The portal has <%= userCount %> users.</p>

<p>The portal has <%= bookmarksFolderCount %> bookmarks folders.</p>