# How the Liferay npm Bundler Publishes npm Packages [](id=how-liferay-portal-publishes-npm-packages)

When you deploy an OSGi bundle with the specified structure, as explained in 
[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-1/the-structure-of-osgi-bundles-containing-npm-packages) 
tutorial, its modules are made available for consumption through canonical URLs.
The example OSGi bundle used in the previously mentioned tutorial is shown 
below:

+$$$

**NOTE:** The structure of the OSGi bundle shown below does not have namespaced
packages. This is the standard structure that liferay-npm-bundler 1.x generates,
but it changes since version 2.0. 

The reason why this structure is used is because it is better suited to explain
resolved modules. Please refer to the last sections of this article to know how
liferay-npm-bundler 2.0 overrides this de-duplication mechanism to implement 
isolated dependencies and imports.

$$$

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

## Package de-duplication [](id=package-deduplication)

Since two or more OSGi modules may export multiple copies of the same package 
and version, Liferay Portal must de-duplicate such collisions. To accomplish 
de-duplication, a new concept called resolved module was created.

A resolved module is the reference package exported to Liferay Portal’s 
front-end, when multiple copies of the same package and version exist. It’s 
randomly referenced from one of the several bundles exporting the same copies of 
the package.

Using the example from the previous section, for each group of canonical URLs 
referring to the same module inside different OSGi bundles, there’s another 
canonical URL for the resolved module. In this example, you have:

- [http://localhost/o/js/resolved-module/my-bundle-package@1.0.0/lib/index.js](http://localhost/o/js/resolved-module/my-bundle-package@1.0.0/lib/index.js)

- [http://localhost/o/js/resolved-module/my-bundle-package$isobject@2.1.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isobject@2.1.0/index.js)

- [http://localhost/o/js/resolved-module/my-bundle-package$isarray@1.0.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isarray@1.0.0/index.js)

- [http://localhost/o/js/resolved-module/my-bundle-package$isarray@2.0.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isarray@2.0.0/index.js)

+$$$

**NOTE:** The OSGi bundle ID (598 in the example) is removed and module is 
replaced by `resolved-module`.

$$$

Next you can learn how the bundler (since version 2.0.0) isolates package 
dependencies.

## Isolated Package Dependencies [](id=isolated-package-dependencies)

A typical OSGi bundle structure generated with liferay-npm-bundler 2.x is shown
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

+$$$

**NOTE:** In this case, packages are namespaced with the bundle name, as opposed
to the case when liferay-npm-bundler 1.x is used.

$$$

The fact that each package dependency is namespaced with the bundle's name 
(`my-bundle-package$` in the example structure) means that each project 
loads its own dependencies, avoiding potential collisions with projects that 
export the same package. For example, consider the two portlet projects below:

    - `my-portlet`
        - package.json
            - dependencies:
                - a-library 1.0.0
                - a-helper 1.0.0
        - node_modules
            - a-library
                - version: 1.0.0
                - dependencies:
                    - a-helper ^1.0.0
            - a-helper
                - version: 1.0.0

    - `another-portlet`
        - package.json
            - dependencies:
                - a-library 1.0.0
                - a-helper 1.2.0
        - node_modules
            - a-library
                - version: 1.0.0
                - dependencies:
                    - a-helper ^1.0.0
            - a-helper
                - version: 1.2.0

In this example, `a-library` depends on `a-helper` at version 1.0.0
or higher (note the caret ^ expression in the dependecies) but `my-portlet` 
forces `a-helper` to version 1.0.0 while `another-portlet` forces it to 1.2.0.

Because both versions satisfy the ^1.0.0 constraint of `a-library`, version 1.x
of the bundler would have caused version 1.2.0 to be shared among the two 
portlets.

However, version 2.x implements isolated dependencies by prefixing the name of 
the bundle to the modules so that `a-helper` 1.2.0 is no longer shared and 
`my-portlet` gets its `a-helper` at 1.0.0 while `another-portlet` gets its 
`a-helper` at 1.2.0.
 
The dependencies isolation not only avoids collisions between bundles, but also
makes peer dependencies behave deterministically as each portlet gets what it 
had in its node_modules folder when it was developed and nothing is switched
during runtime as it was the case for liferay-npm-bundler 1.x projects.

However, with dependency isolation we no longer enjoy de-duplication. Head on to
the next section to learn how you can de-duplicate in projects when using 
liferay-npm-bundler 2.x.

## De-duplication through Importing

Isolated dependencies are very useful, but there are times when sharing the same 
package between modules would be more beneficial. To do this, the 
liferay-npm-bundler lets you import packages from an external OSGi bundle, 
instead of using your own. This lets you put shared dependencies in one project 
and reference them from the rest. 

Imagine that you have three portlets that compose the homepage of your site: 
`my-toolbar`, `my-menu`, and `my-content`. These portlets depend on the fake, 
but awesome, Wonderful UI Components (WUI) framework. This quite limited 
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
            - component-core: 1.0.0
            - button: 1.0.0
            - textfield: 1.0.0

The bundler switches the namespace of certain packages, thus pointing them to an 
external bundle. Say that you have the following code in `my-toolbar` portlet:

    var Button = require('button');

By default, the bundler 2.x transforms this into the following when not imported 
from another bundle:

    var Button = require('my-toolbar$button');

But, because `button` is imported from `wui-provider`, it is instead changed to 
the value below:

    var Button = require('wui-provider$button');

Also, a dependency on `wui-provider$button` at version `^1.0.0` is included in 
`my-toolbar`'s `package.json` file so that the loader finds the correct version. 
That's all you need. Once `wui-provider$button` is required at runtime, it 
"jumps" to `wui-provider`'s context and loads the subdependencies from there on, 
even if code is executed from `my-toolbar`. This works because, as you can 
imagine, `wui-provider`'s modules are namespaced too, and once you load a module 
from it, it keeps requiring `wui-provider$` prefixed modules all the way down.

Next, you will learn possible strategies for importing.

## Strategies when importing Packages

De-duplication by importing is a powerful tool, but you must design a versioning
strategy suitable for you so that you don't get caught into future errors.

First of all, you must decide if you will declare imported dependencies only in
the `.npmbundlerrc` file or in the `package.json` too. Listing an imported 
dependency in `.npmbundlerrc` is enough, even if it is not present in your 
`node_modules` folder, because during runtime the loader will find it. Think of
it as a DLL or shared object, if you have previous experience with dynamic 
linking support in standard operating systems.

However, you may need to install your dependencies in `node_modules` too if you
use them for tests, or if they contain types needed to compile (like in 
Typescript), etc. If that is the case, then you can place them in the 
`dependencies` or `devDependencies` section of your `package.json`. If you list 
them under the latter, they will be automatically excluded from the output 
bundle by the liferay-npm-bundler. Otherwise, you will need to exclude them in 
the `.npmbundlerrc` file if you don't want them to appear in the output. Note 
that if they appear nothing will fail, it's simply that they won't be used and 
thus it's redundant and goes against performance.

The next step, if you list dependencies both in `package.json` and 
`.npmbundlerrc`, is deciding how you will maintain versions in sync. The best
advice is to use the same version constraints in both files, but you may decide
not to do so if it is necessary. 

For example: imagine that you use one of your dependencies to run the tests and 
during runtime you import it from another bundle. Say you are using version 
constraint ^1.5.1. It would be desirable that, if you have tested your code with
a version >=1.5.1 and <2.0.0 (that's what ^1.5.1 means in the end), you get a 
compatible version during runtime. Thus, you would declare the dependency with
^1.5.1 in `.npmbundlerrc` too.

However, there are times when you may want to be more lenient and, perhaps, it
is valid for you to get a lower version (like say 1.4.0) during runtime even if
you are developing against ^1.5.1. If that is the case, then you can declare
^1.5.1 in your `package.json` but ^1.0.0 in `.npmbundlerrc`.

In the end, it's up to you to decide how you want to handle your dependencies
when:

1. Developing (package.json)

2. During runtime (.npmbundlerrc)

But we encourage you to pay attention to your versioning strategy and stick to 
the decided one. Otherwise you will probably get into trouble in the near 
future because dependencies are not satisfied at runtime.

Now you know how the liferay-npm-bundler publishes npm packages!

## Related Topics [](id=related-topics)

[Understanding How liferay-npm-bundler Formats JavaScript Modules for AMD](/develop/tutorials/-/knowledge_base/7-1/understanding-how-liferay-npm-bundler-formats-javascript-modules-for-amd)

[Understanding How @product@ Exposes Configuration for Liferay AMD Loader](/develop/tutorials/-/knowledge_base/7-1/how-liferay-portal-exposes-configuration-for-amd)
