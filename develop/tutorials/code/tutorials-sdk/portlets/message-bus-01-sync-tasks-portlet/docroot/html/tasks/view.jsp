<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.tour.portlet.tasks.model.Task"%>
<%@ page import="com.tour.portlet.tasks.service.TaskLocalServiceUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:success key="success" message="Roadies received message!" />

<aui:button-row>
    <portlet:renderURL var="addTaskURL">
        <portlet:param name="mvcPath" value="/html/tasks/edit_tasks.jsp" />
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:renderURL>

    <aui:button onClick="<%= addTaskURL.toString() %>" value="add-task" />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="There are no tasks to display">
    <liferay-ui:search-container-results
        results="<%= TaskLocalServiceUtil.getTasks(searchContainer.getStart(), searchContainer.getEnd()) %>"
        total="<%= TaskLocalServiceUtil.getTasksCount() %>"
    />

    <liferay-ui:search-container-row
        className="com.tour.portlet.tasks.model.Task"
        keyProperty="taskId"
        modelVar="task" escapedModel="<%= true %>"
    >
        <liferay-ui:search-container-column-text
            name="name"
            value="<%= task.getName() %>"
        />

        <liferay-ui:search-container-column-text
            name="description"
            value="<%= task.getDescription() %>"
        />

        <liferay-ui:search-container-column-text
            name="status"
            value="<%= task.getStatus() %>"
        />
        
        <liferay-ui:search-container-column-jsp
    		align="right"
    		path="/html/tasks/task_actions.jsp"
		/>

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>