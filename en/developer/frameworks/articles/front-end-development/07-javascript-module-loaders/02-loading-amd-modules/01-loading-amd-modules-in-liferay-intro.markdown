---
header-id: loading-amd-modules-in-liferay
---

# Loading AMD Modules in Liferay

[TOC levels=1-4]

Modularized JavaScript code is a specification for the JavaScript language 
called Asynchronous Module Definition, or AMD. The 
[Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader) 
is the native loader that you can use to load your AMD modules. The steps below 
cover how to use the Liferay AMD Module Loader. 

| **Note:** While you can manually configure the AMD Loader, we recommend that you
| use the
| [liferay-npm-bundler](/docs/7-2/frameworks/-/knowledge_base/f/using-npm-in-your-portlets)
| instead.

Follow these steps to prepare your module for the Liferay AMD Module Loader:

1.  Wrap your AMD module code with the `Liferay.Loader.define()` method, such as 
    the one shown below:

    ```javascript
    Liferay.Loader.define('my-dialog', ['my-node', 'my-plugin-base'], 
    function(myNode, myPluginBase) {
       return {
           log: function(text) {
               console.log('module my-dialog: ' + text);
           }
       };
    });
    ```

2.  You can modify the configuration to load the module when another module is 
    triggered or when a condition is met. The configuration below specifies that 
    this module should be loaded if another module requests the `my-test` module:

    ```javascript
    Liferay.Loader.define('my-dialog', ['my-node', 'my-plugin-base'], 
    function(myNode, myPluginBase) {
       return {
           log: function(text) {
               console.log('module my-dialog: ' + text);
           }
       };
    }, {
       condition: {
           trigger: 'my-test',
           test: function() {
               var el = document.createElement('input');

               return ('placeholder' in el);
           }
       },
       path: 'my-dialog.js'
    });
    ```

    The Liferay AMD Loader uses the definition, along with the listed 
    dependencies, as well as any other configurations specified, to create a 
    `config.json` file. This configuration object tells the loader which modules 
    are available, where they are located, and what dependencies they require. 
    Below is an example of a generated `config.json` file:

    ```json
    {
        "frontend-js-web@1.0.0/html/js/parser": {
            "dependencies": []
        },
        "frontend-js-web@1.0.0/html/js/list-display": {
            "dependencies": ["exports"]
        },
        "frontend-js-web@1.0.0/html/js/autocomplete": {
            "dependencies": ["exports", "./parser", "./list-display"]
        }
    }
    ```

3.  Load your module in your scripts. Pass the module name to the 
    `Liferay.Loader.require` method. The example below loads a module called 
    `my-dialog`:

    ```javascript
    Liferay.Loader.require('my-dialog', function(myDialog) {
        // your code here
    }, function(error) {
        console.error(error);
    });
    ```

    | **Note:** By default, the AMD Loader times out in seven seconds. You can 
    | configure this value through System Settings. Open the Control Panel and 
    | navigate to *Configuration* &rarr; *System Settings* &rarr; *PLATFORM* &rarr; 
    | *Infrastructure*, and select *JavaScript Loader*. Set the *Module Definition 
    | Timeout* configuration to the time you want and click *Save*.

## Related Topics

- [Loading Modules with AUI Script](/docs/7-2/frameworks/-/knowledge_base/f/loading-modules-with-aui-script)
- [Using npm in Your Portlets](/docs/7-2/frameworks/-/knowledge_base/f/using-npm-in-your-portlets)
- [Loading Modules with AUI Script](/docs/7-2/frameworks/-/knowledge_base/f/loading-modules-with-aui-script)
