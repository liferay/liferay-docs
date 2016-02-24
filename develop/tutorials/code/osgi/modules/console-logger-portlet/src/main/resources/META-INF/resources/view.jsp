<%@ include file="/init.jsp" %>

<p id="context"></p>

<aui:script require="js-logger/logger.es">
    var Logger = jsLoggerLoggerEs;

    var loggerOne = new Logger('*** -> ');
    loggerOne.log('Hello from the other side');
 
    var loggerDefault = new Logger(); 
    loggerDefault.log('World');
</aui:script>