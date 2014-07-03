<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>

<liferay-util:buffer var="html">
    <liferay-util:include page="/html/portlet/blogs/search.portal.jsp" />
</liferay-util:buffer>

<%
html = StringUtil.add(
        "Stuff I'm adding BEFORE the original content",
        html,
        "\n");

html = StringUtil.add(
    html,
    "Stuff I'm adding AFTER the original content",
    "\n");
%>

<%= html %>