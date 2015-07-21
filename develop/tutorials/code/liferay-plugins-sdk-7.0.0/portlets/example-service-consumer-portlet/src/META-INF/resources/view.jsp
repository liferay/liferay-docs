<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
int userCount = GetterUtil.getInteger(renderRequest.getAttribute("USER_COUNT"));

int bookmarksFolderCount = GetterUtil.getInteger(renderRequest.getAttribute("BOOKMARKS_FOLDER_COUNT"));
%>

<p>The portal has <%= userCount %> users.</p>

<p>The portal has <%= bookmarksFolderCount %> bookmarks folders.</p>