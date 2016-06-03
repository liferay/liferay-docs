<%@ include file="/init.jsp" %>

<p id="context"></p>

<aui:script require="js-logger/logger.es">
	var Logger = jsLoggerLoggerEs.default;

	var loggerOne = new Logger('*** -> ');
	loggerOne.log('Hello');

	var loggerDefault = new Logger();
	loggerDefault.log('World');
</aui:script>