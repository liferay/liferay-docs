# Understanding How @product@ Exposes Configuration For Liferay AMD Loader [](id=how-liferay-portal-exposes-configuration-for-amd)

**NOTE:** This tutorial is for users who know how Liferay AMD Loader works under 
the hood. You can learn more about Liferay AMD Loader in the 
[Liferay AMD Module Loader](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/liferay-amd-module-loader) 
tutorial.

With [de-duplication](/develop/tutorials/-/knowledge_base/7-1/how-liferay-portal-publishes-npm-packages#package-deduplication) 
in place, JavaScript modules are made available to Liferay AMD Loader through
the configuration returned by the `/o/js_loaded_modules` URL.

The OSGi bundle shown below is used for reference in this tutorial:

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

For example, for the specified structure (shown above), as explained in 
[The Structure of OSGi Bundles Containing npm Packages](/develop/tutorials/-/knowledge_base/7-1/the-structure-of-osgi-bundles-containing-npm-packages) 
tutorial, the following configuration is published for Liferay AMD loader to 
consume:

    Liferay.PATHS = {
      ...
      'my-bundle-package@1.0.0/lib/index': '/o/js/resolved-module/my-bundle-package@1.0.0/lib/index',
      'isobject@2.1.0/index': '/o/js/resolved-module/isobject@2.1.0/index',
      'isarray@1.0.0/index': '/o/js/resolved-module/isarray@1.0.0/index',
      'isarray@2.0.0/index': '/o/js/resolved-module/isarray@2.0.0/index',
      ...
    }
    Liferay.MODULES = {
      ...
      "my-bundle-package@1.0.0/lib/index.es": {
        "dependencies": ["exports", "isarray", "isobject"],
        "map": {
          "isarray": "isarray@2.0.0", 
          "isobject": "isobject@2.1.0"
        }
      },
      "isobject@2.1.0/index": {
        "dependencies": ["module", "require", "isarray"],
        "map": {
          "isarray": "isarray@1.0.0"
        }
      },
      "isarray@1.0.0/index": {
        "dependencies": ["module", "require"],
        "map": {}
      },
      "isarray@2.0.0/index": {
        "dependencies": ["module", "require"],
        "map": {}
      },
      ...
    }
    Liferay.MAPS = {
      ...
      'my-bundle-package@1.0.0': { value: 'my-bundle-package@1.0.0/lib/index', exactMatch: true}
      'isobject@2.1.0': { value: 'isobject@2.1.0/index', exactMatch: true},
      'isarray@2.0.0': { value: 'isarray@2.0.0/index', exactMatch: true},
      'isarray@1.0.0': { value: 'isarray@1.0.0/index', exactMatch: true},
      ...
    }

Note:

- The `Liferay.PATHS` property describes paths to the JavaScript module files.

- The `Liferay.MODULES` property describes the dependency names and versions of each module.

- The `Liferay.MAPS` property describes the aliases of the package's main modules.

Now you know how @product@ exposes configuration for Liferay AMD Loader!

# Related Topics [](id=related-topics)

[How @product@ Publishes npm Packages](/develop/tutorials/-/knowledge_base/7-1/how-liferay-portal-publishes-npm-packages)

[Understanding How liferay-npm-bundler Formats JavaScript Modules for AMD](/develop/tutorials/-/knowledge_base/7-1/understanding-how-liferay-npm-bundler-formats-javascript-modules-for-amd)
