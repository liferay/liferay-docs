# Liferay AMD Module Loader [](id=liferay-amd-module-loader)

## What is the Liferay AMD Module Loader?

The [Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader) is a JavaScript  module loader.

## What is a JavaScript module? [](id=what-is-a-javascript-module)

JavaScript modules are a way to encapsulate a piece of code into a useful unit that exports its' capability/value. This makes it easy for other modules to explicitly require this piece of code. Structuring an application this way makes it easier to see the broader scope, easier to find what you're looking for, and keeps things related close together.

## Purpose of Liferay AMD Module Loader [](id=purpose-of-liferay-amd-module-loader)

A normal web page usually loads JavaScript files via HTML `script` tags. That's fine for small websites, but when developing large scale web applications, a better way to organize and load files is needed. A module loader allows an application to load dependencies easily by just specifying a string that identifies the module name.

## Defining a Module [](id=defining-a-module)

The Liferay AMD Module loader works with JavaScript modules that are in the AMD format. Here is a basic example of the definition an AMD module:

```javascript
define('my-dialog', ['my-node', 'my-plugin-base'], function(myNode, myPluginBase) {
    return {
        log: function(text) {
            console.log('module my-dialog: ' + text);
        }
    };
});
```

You may specify that the module should be loaded on triggering some other module and only of some condition is being met:

```javascript
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
```

Here it is specified, that this module should be loaded automatically if developer requests 'aui-test' module, but only if some condition is being met.

## Loading a Module [](id=loading-a-module)

It's as easy as passing the module name to the `require` method.

```javascript
require('my-dialog', function(myDialog) {
    // your code here
}, function(error) {
    console.error(error);
});
```

## Mapping Module Names [](id=mapping-module-names)

You can map module names to specifc versions or other naming conventions.

```javascript
__CONFIG__.maps = {
    'liferay': 'liferay@1.0.0',
    'liferay2': 'liferay@1.0.0'
};
```

Mapping a module will change its name in order to match the value, specified in the map.

```javascript
require('liferay/html/js/autocomplete'...)
```

Under the hood, this will be the same as:

```javascript
require('liferay@1.0.0/html/js/autocomplete'...)
```

## Using Liferay AMD Module Loader in @product@ [](id=using-liferay-amd-module-loader-in-liferay)

Tools, like the [Liferay AMD Module Config Generator](https://github.com/liferay/liferay-module-config-generator), have been integrated into the portal to make it easy for developers to create and load modules. An outline of the process is as follows:

1. This tool scans your code and looks for amd modules `define(...)` statements.

2. It will then name the module, if it is not named already.

3. It takes note of that information, as well as the listed dependencies, and also any other configurations specified to creates a `config.json` file that may look something like this

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

This configuration object tells the loader which modules are available, where they are, and what dependencies they will require.