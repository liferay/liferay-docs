<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<portlet:defineObjects />

<%
int userCount = GetterUtil.getInteger(renderRequest.getAttribute("USER_COUNT"));

int bookmarksFolderCount = GetterUtil.getInteger(renderRequest.getAttribute("BOOKMARKS_FOLDER_COUNT"));
%>

<p>The portal has <%= userCount %> users.</p>

<p>The portal has <%= bookmarksFolderCount %> bookmarks folders.</p>