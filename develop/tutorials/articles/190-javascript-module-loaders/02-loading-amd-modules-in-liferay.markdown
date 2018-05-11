# Loading AMD Modules in Liferay [](id=loading-amd-modules-in-liferay)

<!-- 
What is the difference between the `Loader.addModule()` and the 
`Loader.define()` method?
Why and when would you use one over the other?
 -->
Modularizing pieces of JavaScript code is a specification for the JavaScript 
language called Asynchronous Module Definition, or AMD. The 
[Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader) 
is Portal's native loader that you can use to load your AMD modules. This 
tutorial covers how to use the Liferay AMD Module Loader to load your AMD 
modules in Portal. 

## Configuring Your AMD Module for the Loader [](id=using-liferay-amd-module-loader-in-liferay)

The 
[Liferay AMD Module Config Generator](https://github.com/liferay/liferay-module-config-generator) 
makes it easy for developers to create and load AMD modules. Follow these steps 
to prepare your module:

1.  Wrap your AMD module code with the `Liferay.Loader.define()` method. The 
    Module Config Generator scans your code and looks for these statements, 
    such as the one shown below:
   
        Liferay.Loader.define('my-dialog', ['my-node', 'my-plugin-base'], 
        function(myNode, myPluginBase) {
           return {
               log: function(text) {
                   console.log('module my-dialog: ' + text);
               }
           };
        });

    You can specify to load the module when another module is triggered or when 
    a condition is met. The configuration below specifies that this module 
    should be loaded if the developer requests the `my-test` module:

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

    The Liferay AMD Loader uses the definition, along with the listed 
    dependencies, as well as any other configurations specified, to create a 
    `config.json` file. This configuration object tells the loader which modules 
    are available, where they are located, and what dependencies they require. 
    Below is an example of a generated `config.json` file:

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

2.  By default, if your module is not named, the Module Config Generator names 
    the module for you. The module's name is used to load your module in other 
    projects. You can optionally map module names to specific versions or other 
    naming conventions if you prefer instead. The example below maps `liferay` 
    to the module `liferay@1.0.0` and `liferay2` to the module 
    `liferay@2.0.0`:

        __CONFIG__.maps = {
            'liferay': 'liferay@1.0.0',
            'liferay2': 'liferay@2.0.0'
        };

3.  Load your module in your scripts. Pass the module name or the mapped name to 
    the `Liferay.Loader.require` method. The example below loads a module called 
    `my-dialog`:

        Liferay.Loader.require('my-dialog', function(myDialog) {
            // your code here
        }, function(error) {
            console.error(error);
        });

    Mapping a module changes its name to the value specified in the map. Take 
    this require value based on the example from step 2:
        
        Liferay.Loader.require('liferay/html/js/autocomplete'...)
        
    Under the hood, this is the same as the value shown below:
        
        Liferay.Loader.require('liferay@1.0.0/html/js/autocomplete'...)

Now you know how to load your AMD modules!

## Related Topics [](id=related-topics)

[Configuring Modules for Liferay Portal's Loaders](/develop/tutorials/-/knowledge_base/7-0/configuring-modules-for-products-loaders)
