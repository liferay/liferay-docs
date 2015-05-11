<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ page import="com.tour.portlet.tasks.model.Task"%>
<%@ page import="com.tour.portlet.tasks.service.TaskLocalServiceUtil"%>

<%
    Task task = null;

    long taskId = ParamUtil.getLong(request, "taskId");

    if (taskId > 0) {
        task = TaskLocalServiceUtil.getTask(taskId);
    }

    String redirect = ParamUtil.getString(request, "redirect");
%>

<aui:model-context bean="<%= task %>" model="<%= Task.class %>" />
<portlet:renderURL var="viewTaskURL" />
<portlet:actionURL name='<%= task == null ? "addTask" : "updateTask" %>' var="editTaskURL" windowState="normal" />

<liferay-ui:header
    backURL="<%= viewTaskURL %>"
    title='<%= (task != null) ? task.getName() : "New Task" %>'
/>

<aui:form action="<%= editTaskURL %>" method="POST" name="fm">
    <aui:fieldset>
        <aui:input name="redirect" type="hidden" value="<%= redirect %>" />

        <aui:input name="taskId" type="hidden" value='<%= task == null ? "" : task.getTaskId() %>'/>

        <aui:input name="name" />

        <aui:input name="description" />

        <aui:input name="status" />

    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" />

        <aui:button onClick="<%= viewTaskURL %>"  type="cancel" />
    </aui:button-row>
</aui:form>