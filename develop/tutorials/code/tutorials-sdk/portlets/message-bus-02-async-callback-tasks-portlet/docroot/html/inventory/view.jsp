<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.tour.portlet.tasks.model.Inventory"%>
<%@ page import="com.tour.portlet.tasks.service.InventoryLocalServiceUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />


<liferay-ui:search-container emptyResultsMessage="No inventory items have been requested.">
    <liferay-ui:search-container-results
        results="<%= InventoryLocalServiceUtil.getInventories(searchContainer.getStart(), searchContainer.getEnd()) %>"
        total="<%= InventoryLocalServiceUtil.getInventoriesCount() %>"
    />

    <liferay-ui:search-container-row
        className="com.tour.portlet.tasks.model.Inventory"
        keyProperty="inventoryId"
        modelVar="inventory" escapedModel="<%= true %>"
    >
        <liferay-ui:search-container-column-text
            name="name"
            value="<%= inventory.getName() %>"
        />

        <liferay-ui:search-container-column-text
            name="description"
            value="<%= inventory.getDescription() %>"
        />
        
        <liferay-ui:search-container-column-jsp
    		align="right"
    		path="/html/inventory/inventory_actions.jsp"
		/>

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>