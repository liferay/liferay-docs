# Configuring Modules for @product@'s Loaders

To load your modules in @product@, you need to know when they are needed, where 
they are located at build time, if you want to bundle them together or load them 
independently, and you must assemble them at runtime. Keeping track of all these 
tasks can be a hassle. @product@'s module Loaders (YUI Loader and AMD Loader) 
provide a streamlined process that handles loading for you, which saves you
time.

ES2015 `*.es.js` files are automatically transpiled to AMD modules and 
configured, so no additional work is needed for the Loader to recognize them. 
Other JavaScript modules, however, require more information to use @product@'s
Loaders. 

Manual configuration is required for the following use cases:

-  Custom AUI and YUI modules
-  External libraries with named AMD modules
-  External libraries with global exports that you want to load asynchronously
   or from other modules
-  Initialization code

This tutorial covers these concepts:

-  How to configure JavaScript modules to use @product@'s Loaders
-  How to use your loaded JavaScript modules in a portlet or a JavaScript

See the [Preparing your JavaScript Files for ES2015](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015)
tutorial to learn how to use ES2015 in your JavaScript modules. 

Get started by configuring your module next.

## Configuring your Module [](id=configuring-your-module)

To use the loaders you must first define your modules. This metadata, known as 
the *module definition*, provides details such as dependencies, name and 
location, when they should be loaded, and more.

The module is defined using a configuration file. @product@ uses `config.js` as a
naming convention, but you can use whatever name you prefer.

You must specify your configuration file's location in your bundle's `bnd.bnd` 
file, so @product@ knows where to access it. You'll learn how to do this next.

### Configuring your Bundle's BND File [](id=configuring-your-bundles-bnd-file)

Follow these steps to configure your BND file:

1.  Open your bundle's `bnd.bnd` file and add the `Liferay-JS-Config` 
    header to point to the configuration file that contains the module's 
    definition.
    
    For example, the header below points to a `config.js` file in the module's 
    bundle:

        Liferay-JS-Config: /META-INF/resources/config.js
        
    ![Figure 1: Custom JavaScript modules must use the `Liferay-JS-Config` BND header to point to a configuration file with the module definition.](../../images/loading-custom-modules-configuration.png)

2.  Next, add a web context path to retrieve resources for your module:

        Web-ContextPath: /my-bundle-name

Now that @product@ knows how to find the file, you can write it next.

### Writing the Configuration File [](id=writing-the-configuration-file)

Follow these steps to define your module:

1.  Create a configuration file, for example `config.js`, in the location you 
    specified above. 
    
    For example:
    
        src/main/resources/META-INF/resources

2.  Identify the loader your module requires.

    The type of module you are configuring determines the loader you must use in 
    your configuration file.
    
    **YUI and AlloyUI modules:**  Use the `YUI.applyConfig` mechanism to provide 
    the module information. Note that AUI modules use the AUI mechanism built on
    top of the existing YUI mechanism: `AUI().applyConfig`.

    **AMD or global libraries**: Use the `Liferay.Loader.addModule` mechanism to 
    provide the module information.

    **Initialization code:** requires no loader mechanism. Simply add your code 
    to the module's configuration file.

3.  Add the module's definition to the configuration file using the loader 
    mechanism you identified in step 2.

    Below are some example configurations for each use case.

    **Custom AUI module `config.js` example** [`com.liferay.map.common` module](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/map/map-common/src/main/resources/META-INF/resources/js/config.js):

        ;(function() {
                AUI().applyConfig(
                        {
                                groups: {
                                        mapbase: {
                                                base: MODULE_PATH + '/js/',
                                                combine: Liferay.AUI.getCombine(),
                                                modules: {
                                                        'liferay-map-common': {
                                                                path: 'map.js',
                                                                requires: [
                                                                        'aui-base'
                                                                ]
                                                        }
                                                },
                                                root: MODULE_PATH + '/js/'
                                        }
                                }
                        }
                );
        })();

+$$$

**Note:** You can use the `MODULE_PATH` variable to reference your 
module's location in relative paths. This mechanism is more robust and 
reliable than hard coded paths in the event of modified system settings.
 
$$$
 
The parameters used in the AUI module example are defined below:
 
**groups:** A list of group definitions. Each group can contain definitions 
for `base`, `comboBase`, `Combine`, and a list of `modules`.
 
**base:** The base directory to fetch the module from.

**combine:** Whether to use a combo service to reduce the number of 
HTTP connections required to load your dependencies. Best practice
is to use `Liferay.AUI.getCombine()` as the value, as @product@'s
own modules do. If `js_fast_load` in enabled in your theme, 
`Liferay.AUI.getCombine()` returns `true`, otherwise it returns 
`false`. Hard coding a value can result in odd or unexpected behavior, 
and is not recommended.

**modules:** A list of module definitions.

**path:** The path to the script from `base`. This parameter is
required.
 
**requires:** An array of modules required by this component.
 
See the [`Loader.addModule` method](http://alloyui.com/api/classes/Loader.html#method_addModule)
for a full list of the supported module metadata.
 
**root:** The root path to prepend to module names for the combo
service. Ex: `2.5.2/build`.
 
See the [`Loader` class](http://alloyui.com/api/classes/Loader.html) 
for a full list of available methods and properties.

**Custom AMD module `config.js` example** [`com.liferay.frontend.js.polyfill.babel.web` module](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-js/frontend-js-polyfill-babel-web/bnd.bnd):

        Liferay.Loader.addModule(
                {
                        dependencies: [],
                        exports: '_babelPolyfill',
                        name: 'polyfill-babel',
                        path: MODULE_PATH + 'browser-polyfill.min.js'
                }
        );

The parameters used in the custom AMD module example are defined below:
 
**dependencies:** An array of module dependencies.
 
**exports:** The value, as a `string`, that the module exports to the global
namespace. This is used for non-AMD modules. For example if your module
exposes the global attribute `window.MyLibrary`, then you can set 
`exports = 'MyLibrary'` to let the loader know when this module is done
loading.

**name:** The name of the module.

**path:** Sets the path of the module. If omitted, the module `name` value 
will be used as the path.
 
**fullpath:** Sets the full path to the module. This property should be used
instead of the `path` property when the module isn't located in Portal. For 
example, you can use the `fullpath` property to load a library from an 
external CDN: `fullPath: 'https://web/address/external-library.js'`.

**Library initialization code module `config.js` example** 
[`com.liferay.frontend.js.metal.web` module](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-js/frontend-js-metal-web/src/main/resources/META-INF/resources/config.js):
    
        window.__METAL_COMPATIBILITY__ = {
                renderers: ['soy']
        };
 
Although the example above contains library configuration code, you could 
add any initialization code that you require.

@product@ automatically collects all the module definitions in a single 
request at startup, so you don't need to be concerned about the timing and 
placement of their configuration.

Now that your module is configured, you can learn how to use it in @product@ 
next.

## Using your Module [](id=using-your-module)

Once your module is configured, you have a few ways in which you can use it in 
@product@.

This example is configured to use a module in the JSP of a portlet, via the 
`aui:script`'s `require` attribute:

    <aui:script require="relative/path/to/module/module-name">
      // variable `relativePathToModuleModuleName` is available here
    </aui:script>

To adhere to JavaScript standards, references to the module within the script 
tag are named after the require value, in camel-case and with all invalid 
characters removed. For more information on using your module in a portlet, see
the [Using ES2015 Modules in your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet)
tutorial.

You can also use the module in a generic JavaScript:

    <script>
    require('module-name', function (moduleName) {
      // variable `moduleName` is available here
    });
    </script>

Now you know how to load your custom JavaScript modules and global libraries in 
@product@!

## Related Topics [](id=related-topics)

[Preparing your JavaScript Files for ES2015](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015)

[Using ES2015 Modules in your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet)
