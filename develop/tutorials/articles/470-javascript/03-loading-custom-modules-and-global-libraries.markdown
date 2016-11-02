# Loading Custom Modules and Global Libraries [](id=loading-custom-modules-and-global-libraries)
 
JavaScript modules each have their own configuration code that provides the 
metadata necessary for the module loaders (YUI Loader and AMD Loader) in the 
system to locate and use the bundle. This metadata, known as the 
*module definition*, provides details such as dependencies, the module's name 
and location, when the module should be loaded, and more. Without the module 
definition, the module is invisible to Portal and **will not be loaded**.
<!-- Is that last sentence above correct Chema? -->

ES2015 `*.es.js` files are automatically transpiled to AMD modules and 
configured via the `configJSModules` task, so no additional work is needed on 
your part in order for the loader to recognize them. See the 
[Preparing your JavaScript Files for ES2015](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015)
tutorial to learn how to use ES2015 in your JavaScript modules.

Manual configuration is required for the following use cases:

-  Bundles containing legacy YUI and AlloyUI modules
-  Third party global libraries (such as those installed by NPM or Bower)
-  Custom AUI modules for the YUI loader
-  Initialization code

This tutorial covers how to load your custom and third party JavaScript modules 
in @product@.

## Configuring your Module [](id=configuring-your-module)

Module definitions are written within a configuration file called `config.js`. 
This file is loaded on every page along with the module.

![Figure 1: Custom JavaScript module configuration consist of the `Liferay-JS-Config` BND header and a `config.js` file.](../../images/loading-custom-modules-configuration.png)

You must specify the `config.js` file's location in your bundle's BND file, so 
Portal knows where to access it.

### Configuring your Bundle's BND File [](id=configuring-your-bundles-bnd-file)

Follow these steps to configure your BND file:

1.  Open your bundle's `bnd.bnd` file and add the `Liferay-JS-Config` 
    header pointing to the `config.js` file that will contain the module's 
    definitions:

        Liferay-JS-Config: /META-INF/resources/config.js

2.  Next, you can add a web context path to retrieve resources for your module:

        Web-ContextPath: /my-bundle-name

Now that Portal knows where the module's `config.js` file is located, you can
write it next.

### Writing the Config JS File [](id=writing-the-config-js-file)

Follow these steps to write your module's `config.js` file:

1.  Create a `config.js` file in your bundle's 
    `src/main/resources/META-INF/resources` directory.

2.  Identify the loader your module requires.

    The type of module you are configuring determines the loader you must use in 
    your `config.js` file.
    
    **YUI and AlloyUI modules:**  Use the `YUI.applyConfig` mechanism to provide 
    the module information. Note that AUI modules use the AUI mechanism built on
    top of the existing YUI mechanism: `AUI().applyConfig`.

    **AMD or global libraries**: Use the `Liferay.Loader.addModule` mechanism to 
    provide the module information.

    **Initialization code:** requires no loader mechanism. Simply add your code 
    to the module's configuration file (`config.js`).

3.  Define your module in the `config.js` file, using the corresponding loader 
    mechanism.

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

    **Note:** You can use the `MODULE_PATH` variable to quickly reference your 
    module's location in relative paths. This mechanism is more robust and 
    reliable than hardcoded paths in the event of modified system settings.
    
    $$$
    
    The parameters used in the custom AUI module example are defined below:
    
    **groups:** A list of group definitions. Each group can contain specific
    definitions for `base`, `comboBase`, `Combine`, and a list of `modules`.
    
            **base:** The base directory to fetch the module from.
    
            **combine:** Whether to use a combo service to reduce the number of 
            HTTP connections required to load your dependencies.
    
<!-- Can you explain what `Liferay.AUI.getCombine()` does?

From what I can tell, it returns `true` if JS fastload is enabled in your theme,
otherwise it returns `false`. So, if JS fastload is enabled then combo service 
is enabled, if not then it isn't.

I imagine, you can also simply put a value of `true` or `false` as well. Is this
considered best practice though?

thx!

-->

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

        Loader.addModule(
                {
                        dependencies: [],
                        exports: '_babelPolyfill',
                        name: 'polyfill-babel',
                        path: MODULE_PATH + 'browser-polyfill.min.js'
                }
        );

    **AMD module configuration example for React**:

        Loader.addModule(
          {
            dependencies: [],
            name: 'react',
            anonymous: true,
            // Either this:
            path: MODULE_PATH + 'path/to/something.js'
            // or
            fullPath: 'https://unpkg.com/react@15.3.2/dist/react.js'
          }
        );
    
    AMD Loader module options are defined in 
    https://github.com/liferay/liferay-amd-loader/blob/master/src/js/script-loader.js#L35-L59

    The parameters used in the custom AMD module examples are defined below:
    
    **dependencies:** An array of module dependencies.
    
    **exports:** The value, as a `string`, that the module exports to the global
    namespace. This parameter is only used if the module does not expose a 
    `define` function. The `define` function can be found here [define](https://github.com/liferay/liferay-amd-loader/blob/v1.5.6/src/js/script-loader.js#L60-L118)

<!-- Would you ever expose a `define` function if you are configuring your 
module in a `config.js`? 

If not, I think we can remove the `define` function information from the
definition.

-->

    **name:** The name of the module.
    
    **anonymous:** Whether the module should be anonymous.

<!-- Can you clarify what is meant by anonymous? 

Does it just mean that it is not located in Portal, and therefore does not have
a fullpath?

-->

    **path:** Sets the path of the module. If omitted, the module `name` value 
    will be used as the path.
    
    **fullpath:** Sets the full path to the module. This property should be used
    instead of the `path` property when the module isn't located in Portal. For
    example, the `fullpath` property is used to specify the location of 
    `react.js` in the example above.
    
<!-- Are there any properties that I missed? I would like to define them all 
here, since we don't have an API docs for these. -->

    **Library initialization code module `config.js` example** 
    [`com.liferay.frontend.js.metal.web` module](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-js/frontend-js-metal-web/src/main/resources/META-INF/resources/config.js):
    
        window.__METAL_COMPATIBILITY__ = {
                renderers: ['soy']
        };
        
    Although the example above contains library configuration code, you could 
    add any initialization code that you require.

The platform automatically collects all the module definitions in a single 
request at startup, so you don’t need to be concerned about the timing and 
placement of their configuration.

Now that your module is configured, you can learn how to use it next.

### Using your Module [](id=using-your-module)

Once you module is loaded, you have a few methods in which you can use it in 
@product@.

For example, this `aui:script` is configured to use React in a portlet, via the 
`aui:script`'s `require` attribute.

    <aui:script require="react">
      // variable `react` is available here (lower case "r")
    </aui:script>

This is a generic JavaScript that can be used anywhere:

    <script>
    require('react', function (React) {
      // variable `React` is available here (upper case "R")
    });
    </script>

Finally, this method imports the module for babel compilation, which is 
configured by default with the typical Gradle tasks.

<!-- Can you specify what typical Gradle tasks?

By babel compilation you mean that this module is intended to be translated by
babel so ES2015 can be used?

-->

    import React from 'react';

<!-- What type of file would this import go into? `build.gradle`? -->

Now that you know how to use your loaded modules in @product@, you can learn the
best practices to follow next.

### Module Best Practices [](id=module-best-practices)

Follow these module configuration guidelines for the best results:

-  Work with dependency-free dependencies (i.e. your dependencies don’t have 
   dependencies themselves).

-  Only have bundles installed that use your defined dependencies or define the 
   same version again.<!-- bundle version? -->

-  You can't combine anonymous modules through Combo Loading or request them in 
   batch. There is no way to identify individual modules when they are combined.

-  Give your module a unique name. Modules go into a global registry managed by 
   the loader. If two modules share the same name in the registry, it could 
   result in odd behavior because there is no telling which module will arrive 
   first. **If both define the same version, everything should work**
<!-- By same version, I'm assuming same Bundle version? -->

Now you know how to load your custom JavaScript modules and global libraries in 
@product@!

<!-- What is the next step to export my module? Can I just build the module and 
deploy it, or do I need to export it some how first? -->

## Related Topics [](id=related-topics)

[Preparing your JavaScript Files for ES2015](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015)

[Using ES2015 Modules in your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet)
