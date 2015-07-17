<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.tour.portlet.tasks.model.Setup"%>

<portlet:defineObjects />

<%
    ResultRow row = (ResultRow) request
            .getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    Setup setup = (Setup) row.getObject();

    String name = Setup.class.getName();
    long setupId = setup.getSetupId();

    String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:icon-menu>
    <portlet:renderURL var="editURL">
        <portlet:param name="mvcPath" value="/html/setup/edit_setup.jsp" />
        <portlet:param name="setupId" value="<%= String.valueOf(setupId) %>" />
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:renderURL>

    <liferay-ui:icon image="edit" url="<%= editURL.toString() %>" />
    
    <portlet:actionURL name="deleteSetup" var="deleteURL">
        <portlet:param name="setupId" value="<%= String.valueOf(setupId) %>" />
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:actionURL>

    <liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
</liferay-ui:icon-menu>