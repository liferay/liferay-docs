<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ page import="com.liferay.sample.model.InsultWriter"%>
<%@ page import="com.liferay.sample.service.InsultWriterLocalServiceUtil"%>

<%
    InsultWriter insultwriter = null;

    long insultWriterId = ParamUtil.getLong(request, "insultWriterId");

    if (insultWriterId > 0) {
    	insultwriter = InsultWriterLocalServiceUtil.getInsultWriter(insultWriterId);
    }

    String redirect = ParamUtil.getString(request, "redirect");
%>

<aui:model-context bean="<%= insultwriter %>" model="<%= InsultWriter.class %>" />
<portlet:renderURL var="viewInsultWriterURL" />
<portlet:actionURL name='<%= insultwriter == null ? "addInsultWriter" : "updateInsultWriter" %>' var="editInsultWriterURL" windowState="normal" />

<liferay-ui:header
    backURL="<%= viewInsultWriterURL %>"
    title='<%= (insultwriter != null) ? insultwriter.getInsult() : "New Insult" %>'
/>

<aui:form action="<%= editInsultWriterURL %>" method="POST" name="fm">
    <aui:fieldset>
        <aui:input name="redirect" type="hidden" value="<%= redirect %>" />

        <aui:input name="insultWriterId" type="hidden" value='<%= insultwriter == null ? "" : insultwriter.getInsultWriterId() %>'/>

        <aui:input name="insult" label="Insult" />

        <aui:input name="rating" label="Rating" />

    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" />

        <aui:button onClick="<%= viewInsultWriterURL %>"  type="cancel" />
    </aui:button-row>
</aui:form>