<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.docs.formnavextensionportlet.MyAppWebKeys" %>

<h3>My App<h3>

<%
boolean companyMyAppFeatureEnabled = GetterUtil.getBoolean(request.getAttribute(MyAppWebKeys.COMPANY_MY_APP_FEATURE_ENABLED));
%>

<aui:input checked="<%= companyMyAppFeatureEnabled %>" label="Enable Feature1" name="myAppFeatureEnabled" type="checkbox" value="<%= companyMyAppFeatureEnabled %>" />