# Understanding How liferay-npm-bundler Formats JavaScript Modules for AMD [](id=understanding-how-liferay-npm-bundler-formats-javascript-modules-for-amd)

Liferay AMD Loader is based on the 
[AMD specification](https://github.com/amdjs/amdjs-api/wiki/AMD). 
All modules inside an npm OSGi bundle must be in AMD format. This is done for 
[CommonJS](http://www.commonjs.org/) modules by wrapping the module code inside 
a `define` call. The liferay-npm-bundler helps automate this process by wrapping 
the module for you. This tutorial references the OSGi structure below as an 
example. You can learn more about this structure in 
[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-1/the-structure-of-osgi-bundles-containing-npm-packages) 
tutorial.

- `my-bundle/`
    - `META-INF/`
        - `resources/`
            - `package.json`
                - name: my-bundle-package
                - version: 1.0.0
                - main: lib/index
                - dependencies:
                    - isarray: 2.0.0
                    - isobject: 2.1.0
                - ...
            - `lib/`
                - `index.js`
                - ...
            - ...
            - `node_modules/`
                - `isobject@2.1.0/`
                    - `package.json`
                        - name: isobject
                        - version: 2.1.0
                        - main: lib/index
                        - dependencies:
                            - isarray: 1.0.0
                        - ...
                    - ...
                - `isarray@1.0.0/`
                    - `package.json`
                        - name: isarray
                        - version: 1.0.0
                        - ...
                    - ...
                - `isarray@2.0.0/`
                    - `package.json`
                        - name: isarray
                        - version: 2.0.0
                        - ...
                    - ...

For example, the `isobject@2.1.0` package's `index.js` file contains the 
following code:

    'use strict';

    var isArray = require('isarray');

    module.exports = function isObject(val) {
        return val != null && typeof val === 'object' && isArray(val) === false;
    };

The updated module code configured for AMD format is shown below:

    define(
        'isobject@2.1.0/index', 
        ['module', 'require', 'isarray'], 
        function (module, require) {
            'use strict';

            var isArray = require('isarray');

            module.exports = function isObject(val) {
                return val != null && typeof val === 'object' 
                && isArray(val) === false;
            };
        }
    );

+$$$

**Note:** The module's name must be based on its package, version, and file path 
(for example `isobject@2.1.0/index`), otherwise Liferay AMD Loader can't find 
it.

$$$

Note the module's dependencies: `['module', 'require', 'isarray']`.

`module` and `require` must be used to get a reference to the `module.exports`
object and the local `require` function, as defined in the AMD specification.

The subsequent dependencies state the modules on which this module depends. Note 
that `isarray` in the example is not a package, but rather an alias of the 
`isarray` package's main module (thus, it is equivalent to `isarray/index`).

Also note that @product@ has enough information in the `package.json` files 
to know that `isarray` refers to `isarray/index`, but also that it must be 
resolved to version `1.0.0` of such package, i.e., that `isarray/index` in this 
case refers to `isarray@1.0.0/index`.

You can leverage liferay-npm-bundler with the correct presets to process your 
npm modules for AMD. All liferay-npm-bundler presets 
(*liferay-npm-bundler-preset-*) found in the [liferay-npm-build-tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages) 
repository include some or all of the following Babel plugins to accomplish the 
AMD conversion:

-  [babel-plugin-wrap-modules-amd](https://github.com/liferay/liferay-npm-build-tools/blob/master/packages/babel-plugin-wrap-modules-amd)

-  [babel-plugin-name-amd-modules](https://github.com/liferay/liferay-npm-build-tools/blob/master/packages/babel-plugin-name-amd-modules)

-  [Babel-plugin-namespace-amd-define](https://github.com/liferay/liferay-npm-build-tools/blob/master/packages/babel-plugin-namespace-amd-define)

Now you have a better understanding of how liferay-npm-bundler formats 
JavaScript modules for AMD!

## Related Topics [](id=related-topics)

[How @product@ Publishes NPM Packages](/develop/tutorials/-/knowledge_base/7-1/how-liferay-portal-publishes-npm-packages)

[Configuring liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-1/configuring-liferay-npm-bundler)
