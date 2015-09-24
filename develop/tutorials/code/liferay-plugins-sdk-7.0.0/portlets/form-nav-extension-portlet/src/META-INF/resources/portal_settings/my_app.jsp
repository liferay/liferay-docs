<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.docs.formnavextensionportlet.MyAppWebKeys" %>

<h3><%= LanguageUtil.get(request, "my-app-features") %><h3>

<%
boolean companyMyAppFeatureEnabled = GetterUtil.getBoolean(request.getAttribute(MyAppWebKeys.COMPANY_MY_APP_FEATURE_ENABLED));
%>

<aui:input checked="<%= companyMyAppFeatureEnabled %>" label="<%= LanguageUtil.get(request, "enable-my-app-feature") %>" name="settings--myAppFeatureEnabled--" type="checkbox" value="<%= companyMyAppFeatureEnabled %>" />