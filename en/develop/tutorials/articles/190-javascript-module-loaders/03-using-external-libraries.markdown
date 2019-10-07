---
header-id: using-external-javascript-libraries
---

# Using External JavaScript Libraries

[TOC levels=1-4]

You can use external JavaScript libraries in your portlets (i.e., anything but 
Metal.js, jQuery, or Lodash, which are included by default). There are a few 
methods you can use to make external libraries available. The method you should 
choose depends on the external libraries you plan to use and how you plan to use 
them. 

This tutorial covers how to adapt external libraries for the JavaScript Loaders.

## Configuring Libraries to Support UMD

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

Next you can learn how to use libraries that you host. 

## Using Libraries That You Host

If you're hosting the library (and not loading it from a CDN), you must hide the 
Liferay AMD Loader to use your Library. Follow these steps:

1.  Open the Control Panel, navigate to *Configuration* &rarr; 
    *System Settings*. 

2.  Click *JavaScript Loader* under *Platform* &rarr; *Infrastructure*. 

3.  Uncheck the `expose global` option. 

| **Note:**  Once this option is unchecked, you can no longer use the
| `Liferay.Loader.define` or `Liferay.Loader.require` functions in your app. Also,
| if you're using third party libraries that are AMD compatible, they could stop
| working after unchecking this option because they usually use global functions
| like `require()` or `define()`.

Now you know how to adapt external libraries for Liferay's JavaScript Loaders.

## Related Topics

[Liferay AMD Module Loader](/docs/7-1/tutorials/-/knowledge_base/t/loading-amd-modules-in-liferay)

[Using ES2015+ Modules in Your Portlet](/docs/7-1/tutorials/-/knowledge_base/t/preparing-your-javascript-files-for-esplus)
