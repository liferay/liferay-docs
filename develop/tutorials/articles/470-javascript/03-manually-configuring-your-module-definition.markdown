**Notes:** This template is designed for a single tutorial, and by definition a 
tutorial describes *one* topic. If you want to document a larger feature, you 
may want to break it up into several tutorials. 

The Javascript tutorials [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015) 
and [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet) explain how to automatically use ES2015 for Liferay DXP development.

Sometimes, however, a more fine-grained/manual process is required in order to 
properly setup all the pieces of your application.

This tutorial describes the **`Liferay-JS-Config` bnd header**, what it does and 
how it can be used to provide all the information the platform needs to locate 
and successfully load your JavaScript modules. 

# Background

Why does this feature exist? Who needed it? What problem does it solve? How can 
it be used by the user or developer? How does it make life easier? 

This feature exists so different modules can contribute the configuration for 
their own modules in a controlled and efficient way. 

This is mostly needed by bundles still containing legacy YUI and AlloyUI modules, 
but is also helpful for developers trying to add specific modules not 
automatically handled by build process previously mentioned.

To use it, the developer needs to add the Liferay-JS-Config header to its 
`*bnd.bnd*` file pointing to the file that will contain the bundle’s modules 
definitions:

Liferay-JS-Config: /META-INF/resources/path/to/your/config.js

The platform automatically collects all the module definitions in a single 
request at startup, so bundle developers don’t need to be concerned about the 
timing and placement of their configuration.

# Features

What does this feature do? For example, does it let you add/change/delete 
anything? How else does it operate on data? Can a developer modify or customize 
any of these features? Why would anybody want to use or develop on this? 

By using the `Liferay-JS-Config` header, the developer is effectively 
contributing a piece of configuration code that is meant to be used to provide 
all the additional information the different module loaders in the system 
(YUILoader and Liferay AMD Loader) may need to locate and use the different 
pieces located in the bundle.   

# Steps

List the steps a developer needs to take to implement this feature, or a user 
needs to perform in order to use this feature. 

1.  Add the `Liferay-JS-Config` header to your bundle `*bnd.bnd*` file pointing 
    to the file that will contain the bundle’s modules definitions:

        Liferay-JS-Config: /META-INF/resources/path/to/your/config.js

2.  Write your modules configuration in the `config.js` file.

    1.  YUI modules Use the [YUI.applyConfig](http://yuilibrary.com/yui/docs/api/classes/YUI.html#method_applyConfig) 
        mechanism to provide the information about your YUI and AlloyUI modules.

    2.  Use the [Liferay.Loader.addModule](https://github.com/liferay/liferay-amd-loader/blob/master/src/js/script-loader.js#L31) 
    mechanism to provide the information about your amd or global modules

In the probable event that you need to provide your module’s path to configure 
where the different loaders need to look for the module files, you can use the 
available `MODULE_PATH` variable that is injected in the enclosing call to your 
configuration file, so you don’t need to hardcode any system-specific parts of 
the urls, making this mechanism more robust and reliable in the event of 
modified system settings. 

# Code

Provide example code illustrating an example of this feature. Explain what the 
code does and how it works.

This is a config.js file exemplifying how to configure a custom *YUI module*:

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

This is a config.js file exemplifying how to configure a custom *amd module*:

    Loader.addModule(
    
            {
    
                    dependencies: [],
    
                    exports: '_babelPolyfill',
    
                    name: 'polyfill-babel',
    
                    path: MODULE_PATH + 'browser-polyfill.min.js'
    
            }
    
    );

Finally, this is a config.js file that simply executes some needed library 
startup configuration code:

    window.__METAL_COMPATIBILITY__ = {
    
            renderers: ['soy']
    
    };

You can define as many modules as you using the available APIs to make sure the 
system is ready for runtime!
