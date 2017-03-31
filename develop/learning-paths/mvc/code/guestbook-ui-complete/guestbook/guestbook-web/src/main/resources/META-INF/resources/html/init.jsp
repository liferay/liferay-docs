<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@ 
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@ 
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>

<%@ page import="com.liferay.docs.guestbook.model.Entry" %><%@
page import="com.liferay.docs.guestbook.model.Guestbook" %><%@
page import="com.liferay.docs.guestbook.service.EntryLocalServiceUtil" %><%@
page import="com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil" %><%@
page import="com.liferay.docs.guestbook.service.permission.EntryPermission" %><%@
page import="com.liferay.docs.guestbook.service.permission.GuestbookModelPermission" %><%@
page import="com.liferay.docs.guestbook.service.permission.GuestbookPermission" %><%@
page import="com.liferay.docs.guestbook.util.WebKeys" %><%@
page import="com.liferay.docs.guestbook.util.comparator.EntryNameComparator" %><%@
page import="com.liferay.docs.guestbook.util.comparator.GuestbookNameComparator" %><%@
page import="com.liferay.portal.kernel.model.PersistedModel" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchEntry" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.OrderByComparator" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="java.util.List" %><%@
page import="java.util.Objects" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-theme:defineObjects />
<liferay-frontend:defineObjects />

<portlet:defineObjects />