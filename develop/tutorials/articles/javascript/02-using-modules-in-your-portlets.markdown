# Using ES2015 Modules in your Portlet [](id=using-es2015-modules-in-your-portlet)

Once you've exposed your modules in your `bower.json` you can use them in your 
portlets. This is relatively easy to do, thanks to the new `require` attribute 
of the `<aui:script>` tag. You can learn how to expose your modules in 
the [Preparing your JavaScript files for ES2015](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015) 
tutorial. 

This tutorial covers how to access your exposed modules in your portlets. The
`printer.es` module referenced in this tutorial has been written inside of the 
Sequences Chart Portlet. You can view the Sequences Chart Portlet code in its 
entirety [here](../../code/liferay-plugins-sdk-7.0.0/apps/sequences-chart-web).

Follow the steps below to use your exposed modules in your portlets.

1. Add the aui taglib declaration to your `view.jsp` if you haven't already: 

        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

2. Add an `<aui:script>` tag to the `view.jsp` of your portlet and set the 
   `require` attribute to the relative path for your module. 

    The new `require` attribute will allow you to include your exposed modules 
    in your JSP. The AMD loader will fetch the specified modules and other 
    dependencies for you. For example the Sequences Chart Portlet has the 
    following code in its `view.jsp`:

        <aui:script require="sequences-chart/js/printer.es">
	    var SequencesPrinter = sequencesChartJsPrinterEs;

	    var chartContainer = $('#<portlet:namespace />Chart')[0];

	    new SequencesPrinter(chartContainer).print();
        </aui:script>

    Note that references to the module within the script tag are named after the 
    `require` value, camel-cased, and removed of all invalid characters. The 
    Sequences Chart Portlet uses the following to refer to the `printer.es` 
    module:
    
        var SequencesPrinter = sequencesChartJsPrinterEs;

3. Once your portlet is written, make sure to add a `bower.json` file to your 
   portlet's root folder, with the name and version of your bundle so that it 
   will be available. The Sequences Chart Portlet defines the following for the 
   `bower.json`:

        {
            "name": "sequences-chart",
            "version": "1.0.0"
        }
    
As you can see, thanks to the `<aui:script>` `require` attribute, including your 
modules in your Portlet is a piece of cake!

## Related Topics [](id=related-topics)

[Using Third Party Libraries in Your JavaScript](/develop/tutorials/-/knowledge_base/7-0/using-third-party-libraries-in-your-javascript)

[Using JSPs in a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/using-jsps-in-a-portlet-application)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
