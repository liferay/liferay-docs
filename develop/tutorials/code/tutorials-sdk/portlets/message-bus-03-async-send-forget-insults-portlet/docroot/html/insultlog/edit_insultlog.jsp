<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ page import="com.liferay.sample.model.InsultLog"%>
<%@ page import="com.liferay.sample.service.InsultLogLocalServiceUtil"%>

<%
    InsultLog insultlog = null;

    long insultLogId = ParamUtil.getLong(request, "insultLogId");

    if (insultLogId > 0) {
    	insultlog = InsultLogLocalServiceUtil.getInsultLog(insultLogId);
    }

    String redirect = ParamUtil.getString(request, "redirect");
%>

<aui:model-context bean="<%= insultlog %>" model="<%= InsultLog.class %>" />
<portlet:renderURL var="viewInsultLogURL" />
<portlet:actionURL name='<%= insultlog == null ? "addInsultLog" : "updateInsultLog" %>' var="editInsultLogURL" windowState="normal" />

<liferay-ui:header
    backURL="<%= viewInsultLogURL %>"
    title='<%= (insultlog != null) ? insultlog.getInsult() : "New Insult" %>'
/>

<aui:form action="<%= editInsultLogURL %>" method="POST" name="fm">
    <aui:fieldset>
        <aui:input name="redirect" type="hidden" value="<%= redirect %>" />

        <aui:input name="insultLogId" type="hidden" value='<%= insultlog == null ? "" : insultlog.getInsultLogId() %>'/>

        <aui:input name="insult" label="Insult" />

        <aui:input name="rating" label="Rating" />

    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" />

        <aui:button onClick="<%= viewInsultLogURL %>"  type="cancel" />
    </aui:button-row>
</aui:form>