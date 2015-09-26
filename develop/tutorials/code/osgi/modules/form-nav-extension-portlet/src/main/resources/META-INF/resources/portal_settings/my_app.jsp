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

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.docs.formnavextensionportlet.MyAppWebKeys" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.ResourceBundleUtil" %>

<%@ page import="java.util.ResourceBundle" %>

<%
boolean companyMyAppFeatureEnabled = GetterUtil.getBoolean(request.getAttribute(MyAppWebKeys.COMPANY_MY_APP_FEATURE_ENABLED));

ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", request.getLocale(), getClass());
%>

<h3><liferay-ui:message key='<%= resourceBundle.getString("my-app-features") %>' /><h3>

<aui:input checked="<%= companyMyAppFeatureEnabled %>" label='<%= resourceBundle.getString("enable-my-app-feature") %>' name="settings--myAppFeatureEnabled--" type="checkbox" value="<%= companyMyAppFeatureEnabled %>" />