<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.sample.model.InsultWriter"%>
<%@ page import="com.liferay.sample.service.InsultWriterLocalServiceUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<aui:button-row>
    <portlet:renderURL var="addInsultWriterURL">
        <portlet:param name="mvcPath" value="/html/insultwriter/edit_insultwriter.jsp" />
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:renderURL>

    <aui:button onClick="<%= addInsultWriterURL.toString() %>" value="Add Insult" />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="There are no insults to display">
    <liferay-ui:search-container-results
        results="<%= InsultWriterLocalServiceUtil.getInsultWriters(searchContainer.getStart(), searchContainer.getEnd()) %>"
        total="<%= InsultWriterLocalServiceUtil.getInsultWritersCount() %>"
    />

    <liferay-ui:search-container-row
        className="com.liferay.sample.model.InsultWriter"
        keyProperty="insultWriterId"
        modelVar="insultwriter" escapedModel="<%= true %>"
    >
        <liferay-ui:search-container-column-text
            name="Insult"
            value="<%= insultwriter.getInsult() %>"
        />

        <liferay-ui:search-container-column-text
            name="Rating"
            value="<%= insultwriter.getRating() %>"
        />
        
        <liferay-ui:search-container-column-jsp
    		align="right"
    		path="/html/insultwriter/insultwriter_actions.jsp"
		/>

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>