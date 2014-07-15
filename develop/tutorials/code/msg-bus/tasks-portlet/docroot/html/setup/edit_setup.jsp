<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ page import="com.tour.portlet.tasks.model.Setup"%>
<%@ page import="com.tour.portlet.tasks.service.SetupLocalServiceUtil"%>

<%
    Setup setup = null;

    long setupId = ParamUtil.getLong(request, "setupId");

    if (setupId > 0) {
    	setup = SetupLocalServiceUtil.getSetup(setupId);
    }

    String redirect = ParamUtil.getString(request, "redirect");
%>

<aui:model-context bean="<%= setup %>" model="<%= Setup.class %>" />
<portlet:renderURL var="viewSetupURL" />
<portlet:actionURL name="updateSetup" var="editSetupURL" windowState="normal" />
<%-- <portlet:actionURL name='<%= setup == null ? "addSetup" : "updateSetup" %>' var="editSetupURL" windowState="normal" /> --%>

<liferay-ui:header
    backURL="<%= viewSetupURL %>"
    title="<%= setup.getName() %>"
/>

<aui:form action="<%= editSetupURL %>" method="POST" name="fm">
    <aui:fieldset>
        <aui:input name="redirect" type="hidden" value="<%= redirect %>" />

        <aui:input name="setupId" type="hidden" value='<%= setup == null ? "" : setup.getSetupId() %>'/>

        <aui:input name="name" type="hidden" />
        
        <aui:input name="description" type="hidden" />
        
        <aui:input name="status" />

    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" />

        <aui:button onClick="<%= viewSetupURL %>"  type="cancel" />
    </aui:button-row>
</aui:form>