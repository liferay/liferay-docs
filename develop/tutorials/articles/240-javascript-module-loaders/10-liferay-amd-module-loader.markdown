# Liferay AMD Module Loader [](id=liferay-amd-module-loader)

The [Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader) 
is a JavaScript  module loader.

## What is a JavaScript module? [](id=what-is-a-javascript-module)

A JavaScript module encapsulates a piece of code into a useful unit that exports
its capability/value. This makes it easy for other modules to explicitly require
this piece of code. Structuring an application this way makes it easier to see
the broader scope, easier to find what you're looking for, and keeps related
pieces close together. This way of coding is a specification for the JavaScript
language called Asynchronous Module Definition, or AMD. 

## Purpose of Liferay AMD Module Loader [](id=purpose-of-liferay-amd-module-loader)

A normal web page usually loads JavaScript files via HTML `script` tags. That's 
fine for small websites, but when developing large scale web applications, a 
more robust organization and loader is needed. A module loader allows an 
application to load dependencies easily by specifying a string that identifies
the module name.

Now that you know the purpose of the Liferay AMD Module Loader, you can learn
how to define modules next.

## Defining a Module [](id=defining-a-module)

The Liferay AMD Module loader works with JavaScript modules that are in the AMD 
format. Here is a basic example of the definition of an AMD module:


    define('my-dialog', ['my-node', 'my-plugin-base'], function(myNode, myPluginBase) {
        return {
            log: function(text) {
                console.log('module my-dialog: ' + text);
            }
        };
    });

You can specify to load the module when another module is triggered or when a
given condition is met:

    define('my-dialog', ['my-node', 'my-plugin-base'], function(myNode, myPluginBase) {
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

The configuration above specifies that this module should be loaded
automatically, if the developer requests the `aui-test` module under the given
condition.

Next you can learn how to load a module.

## Loading a Module [](id=loading-a-module)

Loading a module is as easy as passing the module name to the `require` method.
The example below loads a module called `my-dialog`:


    require('my-dialog', function(myDialog) {
        // your code here
    }, function(error) {
        console.error(error);
    });

Next you can learn how to map module names.
 
## Mapping Module Names [](id=mapping-module-names)

You can map module names to specific versions or other naming conventions. The
example below maps the `liferay` and `liferay2` modules to `liferay@1.0.0`:

    __CONFIG__.maps = {
        'liferay': 'liferay@1.0.0',
        'liferay2': 'liferay@1.0.0'
    };

Mapping a module changes its name to the value specified in the map. Take this
require value for example:

    require('liferay/html/js/autocomplete'...)

Under the hood, this is the same as the value shown below:

    require('liferay@1.0.0/html/js/autocomplete'...)

## Using Liferay AMD Module Loader in @product@ [](id=using-liferay-amd-module-loader-in-liferay)

Tools, like the [Liferay AMD Module Config Generator](https://github.com/liferay/liferay-module-config-generator), 
have been integrated into @product@ to make it easy for developers to create 
and load modules. Here's how it works:

1. The Module Config Generator scans your code and looks for AMD module 
   `define(...)` statements.

2. It then names the module if it is not named already.

3. It uses that information, along with the listed dependencies, as well as any
   other configurations specified, to create a `config.json` file. Below is an
   example of a generated `config.json` file:

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

This configuration object tells the loader which modules are available, where 
they are, and what dependencies they require.

Now you know all about the Liferay AMD Module Loader!

## Related Topics [](id=related-topics)

[Configuring Modules for Liferay Portal's Loaders](/develop/tutorials/-/knowledge_base/7-0/configuring-modules-for-liferay-portals-loaders)
