# How @product@ Publishes npm Packages [](id=how-liferay-portal-publishes-npm-packages)

When you deploy an OSGi bundle with the specified structure, as explained in 
[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-1/the-structure-of-osgi-bundles-containing-npm-packages) 
tutorial, its modules are made available for consumption through canonical URLs.
The example OSGi bundle used in the previously mentioned tutorial is shown 
below:

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
 
If you deploy the example OSGi bundle shown above, the following URLs are made 
available (one for each module):

- [http://localhost/o/js/module/598/my-bundle-package@1.0.0/lib/index.js](http://localhost/o/js/module/598/my-bundle-package@1.0.0/lib/index.js)

- [http://localhost/o/js/module/598/isobject@2.1.0/index.js](http://localhost/o/js/module/598/isobject@2.1.0/index.js)

- [http://localhost/o/js/module/598/isarray@1.0.0/index.js](http://localhost/o/js/module/598/isarray@1.0.0/index.js)

- [http://localhost/o/js/module/598/isarray@2.0.0/index.js](http://localhost/o/js/module/598/isarray@2.0.0/index.js)

+$$$

**NOTE:** The OSGi bundle ID (598) may vary.

$$$

You can learn about package de-duplication next.

## Package De-duplication [](id=package-deduplication)

Since two or more OSGi modules may export multiple copies of the same package 
and version, @product@ must de-duplicate such collisions. To accomplish 
de-duplication, a new concept called *resolved module* was created.

A resolved module is the reference package exported to @product@'s front-end, 
when multiple copies of the same package and version exist. It's randomly 
referenced from one of the several bundles exporting the same copies of the 
package.

Using the example from the previous section, for each group of canonical URLs 
referring to the same module inside different OSGi bundles, there's another 
canonical URL for the resolved module. In this example, you have:

- [http://localhost/o/js/resolved-module/my-bundle-package@1.0.0/lib/index.js](http://localhost/o/js/resolved-module/my-bundle-package@1.0.0/lib/index.js)

- [http://localhost/o/js/resolved-module/isobject@2.1.0/index.js](http://localhost/o/js/resolved-module/isobject@2.1.0/index.js)

- [http://localhost/o/js/resolved-module/isarray@1.0.0/index.js](http://localhost/o/js/resolved-module/isarray@1.0.0/index.js)

- [http://localhost/o/js/resolved-module/isarray@2.0.0/index.js](http://localhost/o/js/resolved-module/isarray@2.0.0/index.js)

+$$$

**NOTE:**  the OSGi bundle ID (598 in the example) is removed and module is 
replaced by `resolved-module`.

$$$

Now you know how @product@ publishes npm packages!

## Related Topics [](id=related-topics)

[Understanding How liferay-npm-bundler Formats JavaScript Modules for AMD](/develop/tutorials/-/knowledge_base/7-1/understanding-how-liferay-npm-bundler-formats-javascript-modules-for-amd)

[Understanding How @product@ Exposes Configuration for Liferay AMD Loader](/develop/tutorials/-/knowledge_base/7-1/how-liferay-portal-exposes-configuration-for-amd)
