# Loading Modules with AUI Script [](id=loading-modules-with-aui-script)

The `aui:script` tag is a JSP tag that loads JavaScript in script tags on the
page, while ensuring that certain resources are loaded before executing.

+$$$

**Note:** AUI is deprecated and no longer in active development in 
@product-ver@, but all the tags will remain fully functional in @product-ver@. 
Eventually, these tags will be replaced with 
[Clay](https://claycss.com/) 
tag counterparts.

$$$

## Using aui:script [](id=using-aui-script)

The `aui:script` tag supports the following options:

- `require`: Requires an AMD module to load with the 
  [Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader).
- `use`: Uses an AlloyUI/YUI module that is loaded via the YUI loader.
- `position`: The position the script tag is put on the page.  Possible 
  options are `inline` or `auto`.
- `sandbox`: Whether to wrap the script tag in an anonymous function. If set 
  to `true`, in addition to the wrapping, `$` and `_` are defined for jQuery 
  and underscore.

Next you can learn how to load ES2015 and 
[Metal.js](https://metaljs.com/) 
modules.

## Loading ES2015 and Metal.js Modules [](id=loading-es2015-and-metaljs-modules)

You can use `aui:script` to load your ES2015 and Metal.js modules like this:

    <aui:script require="metal-clipboard/src/Clipboard">
        new metalClipboardSrcClipboard.default();
    </aui:script>

alternatively, you can specify a variable for your module by adding 
`as variableName` after the module name, as shown in the example below:

    <aui:script require="metal-clipboard/src/Clipboard as myModule">
        new myModule.default();
    </aui:script>

This resolves the dependencies of the registered `Clipboard.js` and loads them 
in order until all of them are satisfied and the requested module can be safely
executed.

In the browser, the `aui:script` translates to the full HTML shown below:

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
 
Next you can learn how to load AlloyUI modules.

## Loading AlloyUI Modules [](id=loading-alloyui-modules)

You can use the `use` attribute to load AlloyUI/YUI modules:

    <aui:script use="aui-base">
        A.one('#someNodeId').on(
            'click',
            function(event) {
                alert('Thank you for clicking.')
            }
        );
    </aui:script>

This loads the `aui-base` AlloyUI component and makes it available to the 
code inside the `aui:script`.

In the browser, the `aui:script` translates to the full HTML shown below:

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
 
Now you know how to load modules with the `aui:script` tag!

## Related Topics [](id=related-topics)

[Configuring Modules for Liferay Portal Loaders](/develop/tutorials/-/knowledge_base/7-1/configuring-modules-for-products-loaders)
