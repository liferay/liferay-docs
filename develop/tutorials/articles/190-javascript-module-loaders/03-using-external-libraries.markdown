# Using External JavaScript Libraries [](id=using-external-javascript-libraries)

You can use external (i.e., anything but Metal.js, jQuery, or Lodash, which are
included in @product@) JavaScript libraries in your portlets. There are a few
methods you can use to make external libraries available. The method you should 
choose depends on the external libraries you plan to use and how you plan to use 
them (as modules or as browser globals).

This tutorial covers how to adapt external libraries for @product@'s JavaScript 
Loaders.

Go ahead and get started.

## Configuring Libraries to Support UMD [](id=configuring-libraries-to-support-umd)

If you're the owner of the library, you should make sure that it supports 
[UMD](https://github.com/umdjs/umd)
(Universal Module Definition). You can configure your code to support UMD with 
the template shown below:

    // Assuming your "module" will be exported as "mylibrary"
    (function (root, factory) {
        if (typeof Liferay.Loader.define === 'function' && Liferay.Loader.define.amd) {
            // AMD. Register as a "named" module.
            Liferay.Loader.define('mylibrary', [], factory);
        } else if (typeof module === 'object' && module.exports) {
            // Node. Does not work with strict CommonJS, but
            // only CommonJS-like environments that support module.exports,
            // like Node.
            module.exports = factory();
        } else {
            // Browser globals (root is window)
            root.mylibrary = factory();
      }
    }(this, function () {

        // Your library code goes here
        return {};
    }));

Next you can learn how to load external libraries as browser globals.

## Loading Libraries as Browser Globals [](id=loading-libraries-as-browser-globals)

If you want to use a library that doesn't export itself as a named module 
(as is the case for many plugins) or load the library as a browser global, you 
can use the following steps:

1.  Add a `<script>` tag with the following content before loading your module:

        <script>
            Liferay.Loader.define._amd = Liferay.Loader.define.amd;
            Liferay.Loader.define.amd = false;
        </script>

2.  Next, add a `<script>` tag to load the module itself. Below is an example 
    configuration:

        <script type="text/javascript" src="${javascript_folder}/library.js">
        </script>

3.  Finally, cancel the change made in the previous step, by adding the 
    following `<script>` tag:

        <script>
            Liferay.Loader.define.amd = Liferay.Loader.define._amd;
        </script>

This approach lets you load your modules as browser globals. Next, you can learn 
how to load libraries that you host.

## Using Libraries That You Host [](id=using-libraries-that-you-host)

If you're hosting the library (and not loading it from a CDN), you can follow 
these steps to use your library:

1.  Name the library in the define function, as covered in the 
    [Configuring Libraries to Support UMD](/develop/tutorials/-/knowledge_base/7-1/using-external-libraries#configuring-libraries-to-support-umd)
    section. Below is an example configuration:

        Liferay.Loader.define('mylibrary', [], factory);

2.  Remove the UMD wrapper 
    `if (typeof Liferay.Loader.define === 'function' && Liferay.Loader.define.amd)` 
    or update the UMD wrapper to match the one below:

        if (false && typeof Liferay.Loader.define === 'function' && Liferay.Loader.define.amd)

3.  Configure your bundle's build task to run the `configJSModules` task over 
    the library.
    
    This task names the library and generates the appropriate loader 
    configuration for you.

+$$$

**Note:** You can hide the Liferay AMD Loader through @product@'s System 
Settings. Open the Control Panel, navigate to *Configuration* &rarr; 
*System Settings*, and click *JavaScript Loader* under the *Foundation* tab. 
Finally, uncheck the `expose global` option.

$$$

Now you know how to adapt external libraries for Liferay's JavaScript Loaders.

## Related Topics [](id=related-topics)

[Configuring Modules for Liferay Portal's Loaders](/develop/tutorials/-/knowledge_base/7-1/configuring-modules-for-products-loaders)

[Liferay AMD Module Loader](/develop/tutorials/-/knowledge_base/7-1/liferay-amd-module-loader)

[Using ES2015 Modules in Your Portlet](/develop/tutorials/-/knowledge_base/7-1/using-es2015-modules-in-your-portlet)
