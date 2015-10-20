# Using ES2015 Modules in your Portlet [](id=using-es2015-modules-in-your-portlet)

Once you've [exposed your modules](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015)
via your `bower.json` file, you can use them in your portlets. The `aui:script`
tag's `require` attribute makes it easy. 

This tutorial covers how to access in your portlets the modules you've exposed.
The example module `printer.es` was written inside the
[Sequences Chart Portlet]( https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferay-plugins-sdk-7.0.0/apps/sequences-chart-web).

Follow the steps below to use your exposed modules in your portlets.

1. Declare the `aui` taglib in a JSP in your view: 

        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

2.  Add an `aui:script` tag to the JSP and set the `require` attribute to the
    relative path for your module.

    Since Liferay 7, the `require` attribute allows you to include your exposed
    modules in your JSP. The AMD Loader fetches the specified module and its
    dependencies. For example, the Sequences Chart Portlet's `view.jsp` includes
    the module `printer.es`:

        <aui:script require="sequences-chart/js/printer.es">
            var SequencesPrinter = sequencesChartJsPrinterEs;

            var chartContainer = $('#<portlet:namespace />Chart')[0];

            new SequencesPrinter(chartContainer).print();
        </aui:script>

    References to the module within the script tag are named after the `require`
    value, in camel-case and with all invalid characters removed. The
    `printer.es` module's reference `sequencesChartJsPrinterEs` is derived from
    the module's relative path value `sequences-chart/js/printer.es`. The value
    is stripped of its dash and slash characters and converted to camel case. 

    The `printer.es` module is made available by the following bundle definition
    in the Sequences Chart Portlet's `bower.json` file:

        {
            "name": "sequences-chart",
            "version": "1.0.0"
        }
    
Thanks to the `aui:script` tag and its `require` attribute, using your modules
in your portlet is a piece of cake! 

**Related Topics**

[Using Third Party Libraries in Your JavaScript](/develop/tutorials/-/knowledge_base/7-0/using-third-party-libraries-in-your-javascript)

[Using JSPs in a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/using-jsps-in-a-portlet-application)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
