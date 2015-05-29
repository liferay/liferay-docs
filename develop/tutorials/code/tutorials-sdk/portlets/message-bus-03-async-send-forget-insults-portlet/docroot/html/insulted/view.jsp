<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.sample.model.Insulted"%>
<%@ page import="com.liferay.sample.service.InsultedLocalServiceUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:search-container emptyResultsMessage="There are no insults to display">
    <liferay-ui:search-container-results
        results="<%= InsultedLocalServiceUtil.getInsulteds(searchContainer.getStart(), searchContainer.getEnd()) %>"
        total="<%= InsultedLocalServiceUtil.getInsultedsCount() %>"
    />

    <liferay-ui:search-container-row
        className="com.liferay.sample.model.Insulted"
        keyProperty="insultedId"
        modelVar="insulted" escapedModel="<%= true %>"
    >
        <liferay-ui:search-container-column-text
            name="Insult"
            value="<%= insulted.getInsult() %>"
        />

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>