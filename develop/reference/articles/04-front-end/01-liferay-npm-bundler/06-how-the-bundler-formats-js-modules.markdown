# Understanding How liferay-npm-bundler Formats JavaScript Modules for AMD [](id=understanding-how-liferay-npm-bundler-formats-javascript-modules-for-amd)

Liferay AMD Loader is based on the 
[AMD specification](https://github.com/amdjs/amdjs-api/wiki/AMD). 
All modules inside an npm OSGi bundle must be in AMD format. This is done for 
[CommonJS](http://www.commonjs.org/) modules by wrapping the module code inside 
a `define` call. The liferay-npm-bundler helps automate this process by wrapping 
the module for you. This article references the OSGi structure below as an 
example. You can learn more about this structure in 
[The Structure of OSGi Bundles Containing NPM Packages](/develop/reference/-/knowledge_base/7-1/the-structure-of-osgi-bundles-containing-npm-packages) 
reference.

- `my-bundle/`
    - `META-INF/`
        - `resources/`
            - `package.json`
                - name: my-bundle-package
                - version: 1.0.0
                - main: lib/index
                - dependencies:
                    - my-bundle-package$isarray: 2.0.0
                    - my-bundle-package$isobject: 2.1.0
                - ...
            - `lib/`
                - `index.js`
                - ...
            - ...
            - `node_modules/`
                - `my-bundle-package$isobject@2.1.0/`
                    - `package.json`
                        - name: my-bundle-package$isobject
                        - version: 2.1.0
                        - main: lib/index
                        - dependencies:
                            - my-bundle-package$isarray: 1.0.0
                        - ...
                    - ...
                - `my-bundle-package$isarray@1.0.0/`
                    - `package.json`
                        - name: my-bundle-package$isarray
                        - version: 1.0.0
                        - ...
                    - ...
                - `my-bundle-package$isarray@2.0.0/`
                    - `package.json`
                        - name: my-bundle-package$isarray
                        - version: 2.0.0
                        - ...
                    - ...

For example, the `my-bundle-package$isobject@2.1.0` package's `index.js` file 
contains the following code:

    'use strict';

    var isArray = require('my-bundle-package$isarray');

    module.exports = function isObject(val) {
        return val != null && typeof val === 'object' && isArray(val) === false;
    };

The updated module code configured for AMD format is shown below:

    define(
        'my-bundle-package$isobject@2.1.0/index', 
        ['module', 'require', 'my-bundle-package$isarray'], 
        function (module, require) {
            'use strict';

            var define = undefined;

            var isArray = require('my-bundle-package$isarray');

            module.exports = function isObject(val) {
                return val != null && typeof val === 'object' 
                && isArray(val) === false;
            };
        }
    );

+$$$

**Note:** The module's name must be based on its package, version, and file path 
(for example `my-bundle-package$isobject@2.1.0/index`), otherwise Liferay AMD 
Loader can't find it. 

$$$

Note the module's dependencies: 
`['module', 'require', 'my-bundle-package$isarray']`.

`module` and `require` must be used to get a reference to the `module.exports` 
object and the local `require` function, as defined in the AMD specification. 

The subsequent dependencies state the modules on which this module depends. Note 
that `my-bundle-package$isarray` in the example is not a package but rather an 
alias of the `my-bundle-package$isarray` package's main module (thus, it is 
equivalent to `my-bundle-package$isarray/index`). 

Also note that there is enough information in the `package.json` files to know 
that `my-bundle-package$isarray` refers to `my-bundle-package$isarray/index`, 
but also that it must be resolved to version `1.0.0` of such package, i.e., that 
`my-bundle-package$isarray/index` in this case refers to 
`my-bundle-package$isarray@1.0.0/index`. 

You may also have noted the `var define = undefined;` addition to the top of the
file. This is introduced by `liferay-npm-bundler` to make the module think that
it is inside a CommonJS environment (instead of an AMD one). This is because
some npm packages are written in UMD format and, because we are wrapping it
inside our AMD `define()` call, we don't want them to execute their own
`define()` but prefer them to take the CommonJS path, where the exports are done
through the `module.exports` global.

Now you have a better understanding of how liferay-npm-bundler formats 
JavaScript modules for AMD! 
