---
header-id: loading-es2015-and-metal-modules-with-aui-script
---

# Loading ES2015 and Metal.js Modules with AUI Script

[TOC levels=1-4]

Follow these steps to load your ES2015 and Metal.js modules with `<aui:script>`:

1.  Add the following declaration to your portlet's JSP:

    ```markup
    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
    ```

2.  Add the `<aui:script>` tag and use the `require` attribute to load ES2015 
    and Metal.js modules:

    ```javascript
    <aui:script require="metal-clipboard/src/Clipboard">
        new metalClipboardSrcClipboard.default();
    </aui:script>
    ```

alternatively, you can specify a variable for your module by adding 
`as variableName` after the module name, as shown in the example below:

```javascript
<aui:script require="metal-clipboard/src/Clipboard as myModule">
    new myModule.default();
</aui:script>
```

This resolves the dependencies of the registered module and loads them in order 
until all of them are satisfied and the requested module can be safely executed. 

In the browser, the `aui:script` translates to the full JavaScript shown below:

```javascript
<script type="text/javascript">
    Liferay.Loader.require("metal-clipboard/src/Clipboard", 
    function(metalClipboardSrcClipboard) {
        (function() {
            new metalClipboardSrcClipboard.default();
        })()
    }, function(error) {
        console.error(error)
    });
</script>
```

Great! Now you know how to load ES2015 and Metal.js modules in AUI scripts. 

## Related Topics

- [Using External JavaScript Libraries](/docs/7-2/frameworks/-/knowledge_base/f/using-external-javascript-libraries)
- [Loading AMD Modules](/docs/7-2/frameworks/-/knowledge_base/f/loading-amd-modules-in-liferay)
- [Loading AUI, ES2015, and Metal.js Modules Together with AUI Script](/docs/7-2/frameworks/-/knowledge_base/f/loading-aui-es2015-and-metal-modules-with-aui-script)
