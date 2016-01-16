<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="com_liferay_mike_jspportlet_Com_liferay_mike_jspportletPortlet.caption"/></b>
</p>

<aui:script require="js-logger/logger.es">
    var Logger = jsLoggerLoggerEs;

    var loggerOne = new Logger('*** -> '); 
    loggerOne.log('Hello from the other side'); 

    var loggerDefault = new Logger(); 
    loggerDefault.log('World');
</aui:script>