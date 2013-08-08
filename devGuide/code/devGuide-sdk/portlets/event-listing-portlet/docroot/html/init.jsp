<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.model.Group"%>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>

<%@ page import="com.nosester.portlet.eventlisting.model.Event"%>
<%@ page import="com.nosester.portlet.eventlisting.model.Location"%>
<%@ page import="com.nosester.portlet.eventlisting.service.EventLocalServiceUtil"%>
<%@ page import="com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />