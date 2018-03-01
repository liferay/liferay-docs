# Overriding @product@'s Default YUI and AUI Modules [](id=overriding-liferays-default-yui-and-aui-modules)

@product@ contains several default YUI/AUI modules. You may need to override
functionality provided by these module's scripts. It's possible to 
[override JSPs using fragments](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-jsps),
but you can't override JavaScript files with fragments. Instead, you must create 
a custom AUI module containing three things:

- A copy of the original module's JavaScript file containing your modifications
- A `config.js` file that specifies the modified JavaScript file's path and the
module it overrides
- A `bnd.bnd` file that tells the OSGi container to override the original

Follow these steps:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module) 
    to override the original one. For example, you can create a module named 
    `session-js-override-web` to override @product@'s `session.js` file.
 
2.  Create a `src/main/resources/META-INF/resources/js` folder in your module, 
    copy the original JavaScript file into it, and rename it. For example, 
    create a copy of the `session.js` module and rename it 
    `session-override.js`.

3.  Apply your modifications and save the file.

4.  Next, 
    [write your module's configuration file](/develop/tutorials/-/knowledge_base/7-1/configuring-modules-for-products-loaders#writing-the-configuration-file)
    (`config.js`) to apply your override. Add the `config.js` file to the
    module's `src/main/resources/META-INF/resources/js` folder. The example
    `config.js` file below specifies the `condition` that the 
    [YUI/AUI Loader](/develop/tutorials/-/knowledge_base/7-1/configuring-modules-for-products-loaders#writing-the-configuration-file)
    should load the custom AUI module (`liferay-session-override`) `instead`
    (indicated with the `when` property) of the `trigger` module
    (`liferay-session`). You can follow this same pattern to create your
    module's `config.js` file:

        ;(function() {

            var base = MODULE_PATH + '/js/';

            AUI().applyConfig(
                {
                    groups: {
                        mymodulesoverride: { //mymodulesoverride
                            base: base,
                            combine: Liferay.AUI.getCombine(),
                            filter: Liferay.AUI.getFilterConfig(),
                            modules: {
                                'liferay-session-override': { //my-module-override
                                    path: 'session-override.js', //my-module.js
                                    condition: {
                                        name: 'liferay-session-override', //my-module-override
                                        trigger: 'liferay-session', //original module
                                        when: 'instead'
                                    }
                                }
                            },
                            root: base
                        }
                    }
                }
            );
        })();

5.  Finally, you must configure your `bnd.bnd` file. For the system to apply the 
    changes, you must specify the `config.js`'s location with the 
    `Liferay-JS-Config` BND header. The `liferay-session-override` module from 
    the previous example has the configuration below in its `bnd.bnd` file:

        Bundle-Name: session-js-override
        Bundle-SymbolicName: session.js.override.web
        Bundle-Version: 1.0.0
        Liferay-JS-Config:/META-INF/resources/js/config.js
        Web-ContextPath: /liferay-session-override-web

Now you know how to override @product@'s default YUI/AUI modules!

## Related Topics [](id=related-topics)

[Overriding Core JSPs](/develop/tutorials/-/knowledge_base/7-1/overriding-core-jsps)

[Overriding App JSPs](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-jsps)

[Configuring Modules for @product@'s Module Loaders](/develop/tutorials/-/knowledge_base/7-1/configuring-modules-for-products-loaders)
