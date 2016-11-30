# Loading Modules with AUI Script in @product@ [](id=loading-modules-with-aui-script-in-liferay)

## What is the aui:script Tag?

The `aui:script` tag is a tag for JSPs that allows you to load JavaScript in script tags on the page and ensure certain resources are loaded before executing.

## Using aui:script [](id=using-aui-script)

The `aui:script` tag supports the following options:

- **require**: Requires an AMD module that will be loaded with the [Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader).
- **use**: Uses an AlloyUI/YUI module that will be loaded via the YUI loader
- **position**: The position the script tag is put in the page.  Can be `inline` or `auto`.
- **sandbox**: If set to true, it will wrap the script tag in an anonymous function.  Also, `$` and `_` will be defined for jQuery and underscore.

## Loading ES2015 and Metal.js Modules [](id=loading-es2015-and-metaljs-modules)

You can use `aui:script` to load your ES2015 and Metal.js modules like this:

You can also load AMD modules by using the `require` attribute, like this:

```jsp
    <aui:script require="metal-clipboard/src/Clipboard">
        new metalClipboardSrcClipboard.default();
    </aui:script>
```

This will resolve the dependencies of the registered `Clipboard.js` and load them in order until all of them are satisfied and the requested module can be safely executed.

In the browser, the real code that will be executed will look like this:

```html
<script type="text/javascript">
    require("metal-clipboard/src/Clipboard", function(metalClipboardSrcClipboard) {
        (function() {
            new metalClipboardSrcClipboard.default();
        })()
    }, function(error) {
        console.error(error)
    });
</script>
```

## Loading AlloyUI Modules [](id=loading-alloyui-modules)

You can also load AlloyUI/YUI modules by using the `use` attribute, like this:

```jsp
    <aui:script use="aui-base">
        A.one('#someNodeId').on(
            'click',
            function(event) {
                alert('Thank you for clicking.')
            }
        );
    </aui:script>
```

This will load the `aui-base` AlloyUI component and make it available to the code inside the `aui:script`.

In the browser, the real code that will be executed shall look like this:

```html
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