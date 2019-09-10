---
header-id: loading-aui-modules-with-aui-script
---

# Loading AlloyUI Modules with AUI Script

[TOC levels=1-4]

Follow these steps to load modules with `<aui:script>`:

1.  Add the following declaration to your portlet's JSP:

    ```markup
    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
    ```

2.  Add the `<aui:script>` tag and use the `use` attribute to load AlloyUI/YUI 
    modules:

    ```javascript
    <aui:script use="aui-base">
        A.one('#someNodeId').on(
            'click',
            function(event) {
                alert('Thank you for clicking.')
            }
        );
    </aui:script>
    ```

This loads the `aui-base` AlloyUI component and makes it available to the code 
inside the `aui:script`.

In the browser, the `aui:script` translates to the full JavaScript shown below:

```javascript
<script type="text/javascript">
    AUI().use("aui-base",
        function(A){
            A.one('#someNodeId').on(
                'click',
                function(event) {
                    alert('Thank you for clicking.')
                }
            );
        }
    );
</script>
```

Wonderful! Now you know how to load AUI/YUI modules in AUI scripts. 

## Related Topics

- [Using External JavaScript Libraries](/docs/7-2/frameworks/-/knowledge_base/f/using-external-javascript-libraries)
- [Loading AMD Modules](/docs/7-2/frameworks/-/knowledge_base/f/loading-amd-modules-in-liferay)
- [Loading ES2015 and Metal.js Modules with AUI Script](/docs/7-2/frameworks/-/knowledge_base/f/loading-es2015-and-metal-modules-with-aui-script)
