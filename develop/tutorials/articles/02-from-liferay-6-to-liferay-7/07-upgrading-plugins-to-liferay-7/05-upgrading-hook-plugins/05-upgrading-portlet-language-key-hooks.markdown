# Upgrading Portlet Language Key Hooks [](id=upgrading-portlet-language-key-hooks)

You can upgrade your portlet language key hooks to @product@ 7.0, following
these steps. 

1.  Create a new module based on the Blade sample `blade.resourcebundle` in     
    [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/blade.hook.resourcebundle)
    or [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/blade.hook.resourcebundle). 

    Here are the module folder structure's main files:

    -   `src/main/java/[resource bundle path]/` &rarr; [`ResourceBundleLoader`
        extension](@platform-ref@/7.0-latest/javadocs/portal-kernel/) here
    -   `src/main/resources/content/` &rarr; Language properties files here
        - `Language.properties`
        - `Language_xx.properties`
        - ...

2.  Copy all of your plugin's language properties files into module
    folder `src/main/resources/content/`.

3.  [Create a `ResourceBundleLoader` ](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#creating-a-resource-bundle). 

4.  [Deploy your module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module). 

Your Language key customizations are deployed in your new module on @product-ver@. 

**Related Topics**

[Overriding a Module's Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#creating-a-resource-bundle)
