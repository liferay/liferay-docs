# Using external libraries [](id=using-external-libraries)


It's possible and easy to use external JavaScript libraries in your portlets.

There are several ways of using this feature depending on the external libraries you plan to use and how you plan to use them (as modules or as browser globals).

If you are the owner of the library, you should make sure that it supports [UMD](https://github.com/umdjs/umd) 
(universal module definition), this is quite easy to add to existing code by using the following "template"

        // Assuming our "module" will be exported as "mylibrary"
        (function (root, factory) {
            if (typeof define === 'function' && define.amd) {
                // AMD. Register as an "named" module.
                define('mylibrary', [], factory);
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


If you want to use a library that does not export itself as a named module (as it is the case for many jQuery plugins) or load the library as a browser global, you can use the following workaround:

    - Add a script tag before loading your module with the following content

        <script>
            define._amd = define.amd;
            define.amd = false;
        </script>

    - Then add a script tag to load the module        

        <script type="text/javascript" src="${javascript_folder}/library.js"></script>

    - Finally, cancel the change made in the previous step

        <script>
            define.amd = define._amd;
        </script>

While this is not the "cleanest" solution, it will let you load your modules as browser globals.

If you're hosting the library (and not loading it from a CDN), you can also:

    - Name the library in the define function 
        
        define('mylibrary', [], factory);

    - Remove the UMD wrapper or do something like this

        if (typeof define === 'function' && define.amd) {

        }

    - Configure you bundle's build task so that the `configJSModules` task goes over the library. 
    This task will name it and generate the appropriate loader configuration for you.


As of version 7.x.x it's also possible to hide Liferay's AMD Loader from the configuration page or your Liferay Portal instance, by going to the JavaScript Loader's settings and unchecking the `expose global` option.

**Related Topics**

[Using ES2015 Modules in Your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet)

