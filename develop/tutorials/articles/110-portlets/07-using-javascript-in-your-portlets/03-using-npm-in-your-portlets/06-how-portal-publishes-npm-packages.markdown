# How the Liferay npm Bundler Publishes npm Packages [](id=how-liferay-portal-publishes-npm-packages)

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

<!--
These URLs change now with 2.x. What should they be?
-->
 
If you deploy the example OSGi bundle shown above, the following URLs are made 
available (one for each module):

- [http://localhost/o/js/module/598/my-bundle-package@1.0.0/lib/index.js](http://localhost/o/js/module/598/my-bundle-package@1.0.0/lib/index.js)

- [http://localhost/o/js/module/598/my-bundle-package$isobject@2.1.0/index.js](http://localhost/o/js/module/598/my-bundle-package$isobject@2.1.0/index.js)

- [http://localhost/o/js/module/598/my-bundle-package$isarray@1.0.0/index.js](http://localhost/o/js/module/598/my-bundle-package$isarray@1.0.0/index.js)

- [http://localhost/o/js/module/598/my-bundle-package$isarray@2.0.0/index.js](http://localhost/o/js/module/598/my-bundle-package$isarray@2.0.0/index.js)

+$$$

**NOTE:** The OSGi bundle ID (598) may vary.

$$$

Next you can learn how the bundler isolates package dependencies.

## Isolated Package Dependencies [](id=isolated-package-dependencies)

Using the example from the previous section, you can see that each package 
dependency is namespaced with the bundle's name 
(`my-bundle-package$` in the example structure). This means that each project 
loads its own dependencies, avoiding potential collisions with projects that 
export the same package. This also helps meet peer dependencies. For example, 
consider the two portlets below:

    - `my-portlet@1.0.0`
        - dependencies: 
            - my-portlet$a-library 1.0.0
            - my-portlet$a-helper 1.0.0

    - `another-portlet@1.0.0`
        - dependencies:
            - another-portlet$a-library 1.0.0
            - another-portlet$a-helper 1.2.0

In this example, `my-portlet$a-library` depends on `a-helper` at version 1.0.0 
(which is namespaced as `my-portlet$a-helper`) and `another-portlet$a-library` 
depends on `a-helper` at version 1.2.0 (which is namespaced as 
`another-portlet$a-helper`). Because they are namespaced, peer dependencies are 
easily met.

## De-duplication through Importing

Isolated dependencies are very useful, but there are times when sharing the same 
package between modules would be more beneficial. To do this, the 
liferay-npm-bundler lets you import packages from an external OSGi bundle, 
instead of using your own. This lets you put shared dependencies in one project 
and reference them from the rest. 

Imagine that you have three portlets that compose the homepage of your site: 
`my-toolbar`, `my-menu`, and `my-content`. These portlets depend on the fake, 
but awesome, Wonderful UI Components(WUI) framework. This quite limited 
framework is composed of only three packages:

1.  `component-core`
2.  `button`
3.  `textfield`

Since the bundler namespaces each dependency package with the portlet's name by 
default, you would end up with three namespaced copies of the WUI package on the 
page. This is not what you want. Since they share the same package, instead you 
can create a fourth bundle that contains the WUI package, and import the WUI 
package in the three portlets. This results in the structure below:

- `my-toolbar/`
    - `.npmbundlerrc`
        - config:
            - imports:
                - wui-provider:
                    - component-core: ^1.0.0
                    - button: ^1.0.0
                    - textfield: ^1.0.0
- `my-menu/`
    - `.npmbundlerrc`
        - config:
            - imports:
                - wui-provider:
                    - component-core: ^1.0.0
                    - button: ^1.0.0
                    - textfield: ^1.0.0
- `my-content/`
    - `.npmbundlerrc`
        - config:
            - imports:
                - wui-provider:
                    - component-core: ^1.0.0
                    - button: ^1.0.0
                    - textfield: ^1.0.0
- `wui-provider/`
    - `.package.json`
        - name: wui-provider
        - dependencies: 
            - component-core: ^1.0.0
            - button: ^1.0.0
            - textfield: ^1.0.0



The bundler switches the namespace of certain packages, thus pointing them to an 
external bundle. Say that you have the following code in `my-toolbar` portlet:

    var Button = require('button');

By default, the bundler transforms this into the following when not imported 
from another bundle:

    var Button = require('my-toolbar$button');

But, because `button` is imported from `wui-provider`, it is instead changed to 
the value below:

    var Button = require('wui-provider$button');

Also, a dependency on `wui-provider$button` at version `^1.0.0` is included in 
`my-toolbar`'s `package.json` file so that the loader finds the correct version. 
That's all you need. Once `wui-provider$button` is required at runtime, it jumps 
to `wui-provider`'s context and loads the subdependencies from there on, even if 
code is executed from `my-toolbar`. This works because, as you know, 
`wui-provider`'s modules are namespaced too, and once you load a module from it, 
it keeps requiring `wui-provider$` prefixed modules all the way down.

<!--
Since de-duplication is no longer needed, I'm guessing there are no longer 
"resolved modules" or URLs for them?

- [http://localhost/o/js/resolved-module/my-bundle-package@1.0.0/lib/index.js](http://localhost/o/js/resolved-module/my-bundle-package@1.0.0/lib/index.js)

- [http://localhost/o/js/resolved-module/my-bundle-package$isobject@2.1.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isobject@2.1.0/index.js)

- [http://localhost/o/js/resolved-module/my-bundle-package$isarray@1.0.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isarray@1.0.0/index.js)

- [http://localhost/o/js/resolved-module/my-bundle-package$isarray@2.0.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isarray@2.0.0/index.js)

+$$$

**NOTE:**  the OSGi bundle ID (598 in the example) is removed and module is 
replaced by `resolved-module`.

$$$
-->

Now you know how the liferay-npm-bundler publishes npm packages!

## Related Topics [](id=related-topics)

[Understanding How liferay-npm-bundler Formats JavaScript Modules for AMD](/develop/tutorials/-/knowledge_base/7-1/understanding-how-liferay-npm-bundler-formats-javascript-modules-for-amd)

[Understanding How @product@ Exposes Configuration for Liferay AMD Loader](/develop/tutorials/-/knowledge_base/7-1/how-liferay-portal-exposes-configuration-for-amd)
