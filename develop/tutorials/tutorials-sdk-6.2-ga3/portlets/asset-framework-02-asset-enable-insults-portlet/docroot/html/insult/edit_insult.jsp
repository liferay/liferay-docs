<%@include file="/html/insult/init.jsp"%>

<%
    Insult insult = null;

    long insultId = ParamUtil.getLong(request, "insultId");

    if (insultId > 0) {
        insult = InsultLocalServiceUtil.getInsult(insultId);
    }

    String redirect = ParamUtil.getString(request, "redirect");
%>

<aui:model-context bean="<%= insult %>" model="<%= Insult.class %>" />
<portlet:renderURL var="viewInsultURL" />
<portlet:actionURL name='<%= insult == null ? "addInsult" : "updateInsult" %>' var="editInsultURL" windowState="normal" />

<liferay-ui:header
    backURL="<%= viewInsultURL %>"
    title='<%= (insult != null) ? insult.getInsultString() : "New Insult" %>'
/>

<aui:form action="<%= editInsultURL %>" method="POST" name="fm">
    <aui:fieldset>
        <aui:input name="redirect" type="hidden" value="<%= redirect %>" />

        <aui:input name="insultId" type="hidden" value='<%= insult == null ? "" : insult.getInsultId() %>'/>

        <aui:input name="insultString" />

    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" />

        <aui:button onClick="<%= viewInsultURL %>"  type="cancel" />
    </aui:button-row>
</aui:form>