# Upgrading Portlet Language Key Hooks [](id=upgrading-portlet-language-key-hooks)

You can upgrade your portlet language key hooks to @product-ver@ by following
these steps: 

1.  Create a new module based on the Blade sample `resource-bundle` 
    ([Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/resource-bundle)
    or [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/resource-bundle) project). 

    Here are the module folder structure's main files:

    -   `src/main/java/[resource bundle path]` &rarr; [`ResourceBundleLoader`
        extension](@platform-ref@/7.0-latest/javadocs/portal-kernel/) goes here
    -   `src/main/resources/content`
        - `Language.properties`
        - `Language_xx.properties`
        - ...

2.  Copy your language properties files into module
    folder `src/main/resources/content/`.

3.  [Create a `ResourceBundleLoader` ](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#creating-a-resource-bundle). 

4.  [Deploy your module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module). 

Your portlet language key customizations are deployed in your new module on
@product-ver@. 

## Related Topics [](id=related-topics)

[Overriding a Module's Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#creating-a-resource-bundle)

[Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-core-language-key-hooks)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-0/upgrading-the-liferay-maven-build)
