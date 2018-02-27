# Using ES2015 Modules in your Portlet [](id=using-esplus-modules-in-your-portlet)

Once you've 
[exposed your modules](/develop/tutorials/-/knowledge_base/7-1/preparing-your-javascript-files-for-esplus) 
via your `package.json` file, you can use them in your portlets. The 
`aui:script` tag's `require` attribute makes it easy.

This tutorial covers how to access your exposed modules in your portlets. Follow 
the steps below to use your exposed modules in your portlets.

1.  Declare the `aui` taglib in your view JSP:

        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

    **Note:** if you created the portlet using Blade, the `aui` taglib is
    already provided for you in the `init.jsp`.

2.  Add an `aui:script` tag to the JSP and set the `require` attribute to the
    relative path for your module.

    The `require` attribute lets you include your exposed modules in your JSP. 
    The AMD Loader fetches the specified module and its dependencies. An example 
    faux Console Logger Portlet's `view.jsp` shown below includes the module 
    `logger.es`:

        <aui:script require="js-logger/logger.es">
            var Logger = jsLoggerLoggerEs.default;

            var loggerOne = new Logger('*** -> ');
            loggerOne.log('Hello');

            var loggerDefault = new Logger();
            loggerDefault.log('World');
        </aui:script>

    References to the module within the script tag are named after the `require`
    value, in camel-case and with all invalid characters removed. The
    `logger.es` module's reference `jsLoggerLoggerEs` is derived from
    the module's relative path value `js-logger/logger.es`. The value
    is stripped of its dash and slash characters and converted to camel case.

Thanks to the `aui:script` tag and its `require` attribute, using your modules
in your portlet is a piece of cake!

## Related Topics [](id=related-topics)

[Overriding a Module's JSPs](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-jsps)

[Web Services](/develop/tutorials/-/knowledge_base/7-1/web-services)
