---
header-id: loading-modules-with-aui-script-in-liferay
---

# Loading Modules with AUI Script in @product@

[TOC levels=1-4]

The `aui:script` tag is a JSP tag that loads JavaScript in script tags on the
page, while ensuring that certain resources are loaded before executing.

## Using aui:script

The `aui:script` tag supports the following options:

- `require`: Requires an AMD module that will be loaded with the 
  [Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader).
- `use`: Uses an AlloyUI/YUI module that is loaded via the YUI loader.
- `position`: The position the script tag is put on the page.  Possible 
  options are `inline` or `auto`.
- `sandbox`: Whether to wrap the script tag in an anonymous function. If set 
  to `true`, in addition to the wrapping, `$` and `_` are defined for jQuery 
  and underscore.

Next you can learn how to load ES2015 and Metal.js modules.

## Loading ES2015 and Metal.js Modules

You can use `aui:script` to load your ES2015 and Metal.js modules like this:

    <aui:script require="metal-clipboard/src/Clipboard">
        new metalClipboardSrcClipboard.default();
    </aui:script>

This resolves the dependencies of the registered `Clipboard.js` and loads them in
order until all of them are satisfied and the requested module can be safely
executed.

In the browser, the `aui:script` translates to the full HTML shown below:

    <script type="text/javascript">
        Liferay.Loader.require("metal-clipboard/src/Clipboard", function(metalClipboardSrcClipboard) {
            (function() {
                new metalClipboardSrcClipboard.default();
            })()
        }, function(error) {
            console.error(error)
        });
    </script>
 
Next you can learn how to load AlloyUI modules.

## Loading AlloyUI Modules

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

Next you can learn how to load AlloyUI modules together with ES2015 and Metal.js 
modules. 

## Loading AlloyUI Modules and ES2015 and Metal.js Modules Together

You may want to load an AUI module along with an ES2015 module or Metal.js 
module in an `aui:script`. The `aui:script` tag doesn't support both the 
`require` and `use` attributes in the same configuration. Not to worry though. 
You can use the `aui:script`'s `require` attribute to load the ES2015 and 
Metal.js modules, while loading the AUI module(s) with the `AUI().use()` 
function within the script. Below is an example configuration:

    <aui:script require="path-to/metal/module">
     AUI().use(
        'liferay-aui-module', 
        function(A) {
            let var = pathToMetalModule.default;
        }
    );
    </aui:script>

Now you know how to load modules with the `aui:script` tag! 

## Related Topics

[Configuring Modules for Liferay Portal Loaders](/docs/7-0/tutorials/-/knowledge_base/t/configuring-modules-for-products-loaders)
