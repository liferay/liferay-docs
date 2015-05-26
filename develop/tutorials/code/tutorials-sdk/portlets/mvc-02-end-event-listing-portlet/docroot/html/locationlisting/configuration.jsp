<%@include file="/html/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%  
boolean showLocationAddress_cfg = GetterUtil.getBoolean(portletPreferences.getValue("showLocationAddress", StringPool.TRUE));
%>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

    <aui:input name="preferences--showLocationAddress--" type="checkbox" value="<%= showLocationAddress_cfg %>" />

    <aui:button-row>
        <aui:button type="submit" />
    </aui:button-row>
</aui:form>