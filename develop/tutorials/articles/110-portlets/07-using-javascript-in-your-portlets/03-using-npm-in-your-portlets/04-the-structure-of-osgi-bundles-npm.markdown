# The Structure of OSGi Bundles Containing npm Packages [](id=the-structure-of-osgi-bundles-containing-npm-packages)

To deploy JavaScript modules to @product@ you must create an OSGi bundle with 
all the npm dependencies extracted from the project's `node_modules` folder and 
modify them to work with the 
[Liferay AMD Loader](https://github.com/liferay/liferay-amd-loader). 
The liferay-npm-bundler automates this process for you, creating a bundle 
similar to the one below:

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

The packages inside `node_modules` are the same format as the npm tool and can 
be copied (after a little processing) from a standard `node_modules` folder. The 
`node_modules` folder can hold any number of npm packages (even different 
versions of the same package), or no npm packages at all.

Now that you know the structure for OSGi bundles containing npm packages, you 
can learn how the liferay-npm-bundler handles inline JavaScript packages. 

## Inline JavaScript packages [](id=inline-javascript-packages)

The resulting OSGi bundle that the liferay-npm-bundler creates lets you deploy 
one inline JavaScript package (named `my-bundle-package` in the example) with 
several npm packages. The npm packages are placed inside a `node_modules` 
folder, one package per folder.

The inline package is nested in the OSGi standard `META-INF/resources` folder 
and is defined by a standard npm `package.json` file.

The inline package is optional, but only one inline package is allowed per OSGi 
bundle. The inline package usually provides the JavaScript code for a portlet, 
when the OSGi bundle contains one. Note that the architecture does not 
differentiate between inline and npm packages once they are published. The 
inline package is only used for organizational purposes. 

Now you know the liferay-npm-bundler creates OSGi bundles for npm packages!

## Related Topics [](id=related-topics)

[Configuring liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-1/configuring-liferay-npm-bundler)

[liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-1/liferay-npm-bundler)

[Adding liferay-npm-bundler to Your Portlet](/develop/tutorials/-/knowledge_base/7-1/adding-liferay-npm-bundler-to-your-portlet)
