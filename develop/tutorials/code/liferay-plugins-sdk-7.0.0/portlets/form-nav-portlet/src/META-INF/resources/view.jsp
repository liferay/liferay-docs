<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

Person person = new Person();
%>

<aui:form>
	<liferay-ui:form-navigator
		backURL="<%= backURL %>"
		formModelBean="<%= person %>"
		htmlTop="<h2>Person Settings</h2>"
		id="<%= FormNavConstants.FORM_NAVIGATOR_ID_PERSON_SETTINGS %>"
	/>
</aui:form>