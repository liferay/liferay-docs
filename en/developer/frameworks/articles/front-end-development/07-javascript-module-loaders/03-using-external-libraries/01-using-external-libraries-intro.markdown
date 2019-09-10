---
header-id: using-external-javascript-libraries
---

# Using External JavaScript Libraries

[TOC levels=1-4]

You can use external JavaScript libraries in your portlets (i.e., anything but 
Metal.js or jQuery, which are included by default). If you're the owner or 
hosting the external library, there are a few more requirements to load them 
with the JavaScript Loaders. 

Follow these steps:

1.  If you're the owner of the library, you should make sure that it supports 
    [UMD](https://github.com/umdjs/umd) (Universal Module Definition). You can 
    configure your code to support UMD with the template shown below:

    ```javascript
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
    ```

2.  If you're hosting the library (and not loading it from a CDN), you must hide 
    the Liferay AMD Loader to use your Library. Open the Control Panel, navigate 
    to *Configuration* &rarr; *System Settings*. 

3.  Click *JavaScript Loader* under *Platform* &rarr; *Infrastructure*. 

4.  Uncheck the `expose global` option. 

| **Note:**  Once this option is unchecked, you can no longer use the 
| `Liferay.Loader.define` or `Liferay.Loader.require` functions in your app. Also, 
| if you're using third party libraries that are AMD compatible, they could stop 
| working after unchecking this option because they usually use global functions 
| like `require()` or `define()`. 

Great! Now you know how to adapt external libraries for Liferay's JavaScript 
Loaders. 

## Related Topics

- [Liferay AMD Module Loader](/docs/7-2/frameworks/-/knowledge_base/f/loading-amd-modules-in-liferay)
- [Using ES2015+ Modules in Your Portlet](/docs/7-2/frameworks/-/knowledge_base/f/using-esplus-modules-in-your-portlet)
- [Loading Modules with AUI Script](/docs/7-2/frameworks/-/knowledge_base/f/loading-modules-with-aui-script)
