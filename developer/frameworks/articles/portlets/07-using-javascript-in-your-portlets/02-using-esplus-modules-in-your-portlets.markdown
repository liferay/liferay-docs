---
header-id: using-esplus-modules-in-your-portlet
---

# Using ES2015 Modules in your Portlet

[TOC levels=1-4]

Once you've [exposed your modules](/docs/7-2/frameworks/-/knowledge_base/f/using-javascript-in-your-portlets) 
via your `package.json` file, you can use them in your portlets. The 
`aui:script` tag's `require` attribute makes it easy. 

Follow the steps below to use your exposed modules in your portlets:

1.  Declare the `aui` taglib in your view JSP:

    ```markup
    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    ```

    | **Note:** if you created the portlet using Blade, the `aui` taglib is 
    | already provided for you in the `init.jsp`. 

2.  Add an `aui:script` tag to the JSP and set the `require` attribute to the 
    relative path for your module. 

    The `require` attribute lets you include your exposed modules in your JSP. 
    The AMD Loader fetches the specified module and its dependencies. An example 
    faux Console Logger Portlet's `view.jsp` shown below includes the module 
    `logger.es`:

    ```javascript
    <aui:script require="js-logger/logger.es">
        var Logger = jsLoggerLoggerEs.default;

        var loggerOne = new Logger('*** -> ');
        loggerOne.log('Hello');

        var loggerDefault = new Logger();
        loggerDefault.log('World');
    </aui:script>
    ```

    References to the module within the script tag are named after the `require` 
    value, in camel-case and with all invalid characters removed. The 
    `logger.es` module's reference `jsLoggerLoggerEs` is derived from 
    the module's relative path value `js-logger/logger.es`. The value 
    is stripped of its dash and slash characters and converted to camel case. 

Thanks to the `aui:script` tag and its `require` attribute, using your modules 
in your portlet is a piece of cake! 

## Related Topics

- [Customizing JSPs](/docs/7-2/customization/-/knowledge_base/c/customizing-jsps)
- [Web Services](/docs/7-2/frameworks/-/knowledge_base/f/web-services)
