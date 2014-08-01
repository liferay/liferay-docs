<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.tour.portlet.tasks.model.Setup"%>
<%@ page import="com.tour.portlet.tasks.service.SetupLocalServiceUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />


<liferay-ui:search-container emptyResultsMessage="There are no items to setup">
    <liferay-ui:search-container-results
        results="<%= SetupLocalServiceUtil.getSetups(searchContainer.getStart(), searchContainer.getEnd()) %>"
        total="<%= SetupLocalServiceUtil.getSetupsCount() %>"
    />

    <liferay-ui:search-container-row
        className="com.tour.portlet.tasks.model.Setup"
        keyProperty="setupId"
        modelVar="setup" escapedModel="<%= true %>"
    >
        <liferay-ui:search-container-column-text
            name="name"
            value="<%= setup.getName() %>"
        />

        <liferay-ui:search-container-column-text
            name="description"
            value="<%= setup.getDescription() %>"
        />

        <liferay-ui:search-container-column-text
            name="status"
            value="<%= setup.getStatus() %>"
        />
        
        <liferay-ui:search-container-column-jsp
    		align="right"
    		path="/html/setup/setup_actions.jsp"
		/>

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>