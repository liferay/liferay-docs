---
header-id: loading-aui-es2015-and-metal-modules-with-aui-script
---

# Loading AUI, ES2015, and Metal.js Modules Together with AUI Script

[TOC levels=1-4]

You may want to load an AUI module along with an ES2015 module or Metal.js 
module in an `aui:script`. The `aui:script` tag doesn't support both the 
`require` and `use` attributes in the same configuration. Not to worry though. 
You can use the `aui:script`'s `require` attribute to load the ES2015 and 
Metal.js modules, while loading the AUI module(s) with the `AUI().use()` 
function within the script. 

Follow these steps to load your ES2015, Metal.js, and AUI modules together with 
`<aui:script>`:

1.  Add the following declaration to your portlet's JSP:

    ```markup
    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
    ```

2.  Add the `<aui:script>` tag and use the `require` attribute to load ES2015 
    and Metal.js modules, while using the `AUI().use()` function to load AUI 
    modules, as shown in the example below:

    ```javascript
    <aui:script require="path-to/metal/module">
     AUI().use(
        'liferay-aui-module', 
        function(A) {
            let var = pathToMetalModule.default;
        }
    );
    </aui:script>
    ```

Great! Now you know how to load all your modules with `aui:script`. 

## Related Topics

- [Using External JavaScript Libraries](/docs/7-2/frameworks/-/knowledge_base/f/using-external-javascript-libraries)
- [Loading AMD Modules](/docs/7-2/frameworks/-/knowledge_base/f/loading-amd-modules-in-liferay)
- [Loading ES2015 and Metal.js Modules with AUI Script](/docs/7-2/frameworks/-/knowledge_base/f/loading-es2015-and-metal-modules-with-aui-script)
