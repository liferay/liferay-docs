
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.template.TemplateHandler" %><%@
page import="com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.model.Group" %><%@
page import="com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateUtil" %><%@
page import="com.liferay.portal.theme.ThemeDisplay" %><%@
page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.docs.eventlisting.model.Event" %><%@
page import="com.liferay.docs.eventlisting.model.Location" %><%@
page import="com.liferay.docs.eventlisting.service.EventLocalServiceUtil" %><%@
page import="com.liferay.docs.eventlisting.service.LocationLocalServiceUtil" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ page import="com.liferay.docs.eventlisting.model.Event"%><%@
page import="com.liferay.docs.eventlisting.model.Location"%><%@
page import="com.liferay.docs.eventlisting.service.EventLocalServiceUtil"%><%@
page import="com.liferay.docs.eventlisting.service.LocationLocalServiceUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />