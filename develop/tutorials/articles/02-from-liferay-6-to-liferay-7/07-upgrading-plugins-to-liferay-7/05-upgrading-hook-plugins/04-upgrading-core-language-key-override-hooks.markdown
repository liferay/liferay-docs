# Upgrading Core Language Key Hooks [](id=upgrading-core-language-key-hooks)

Here are the steps for upgrading a  core language key hook to @product-ver@. 

1.  Create a new module based on the Blade sample `resource-bundle` in 
    [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/resource-bundle)
    or [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/resource-bundle). 

    Here are the main parts of the module folder structure:

    - `src/main/java/[resource bundle path]` &rarr; Custom resource bundle class goes here 
    -  `src/main/resources/content`
        - `Language.properties`
        - `Language_xx.properties`
        - ...

2.  Copy all your plugin's language properties files into the module
    folder `src/main/resources/content/`.

3.  [Create a `resource bundle`](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#creating-a-resource-bundle). 

4.  [Deploy your module](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module). 

Your core language key customizations are deployed to @product-ver@. 

## Related Topics [](id=related-topics)

[Overriding Core Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys)

[Upgrading Portlet Language Key Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-portlet-language-key-hooks)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-0/upgrading-the-liferay-maven-build)     
