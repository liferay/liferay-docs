---
header-id: upgrading-core-language-key-hooks
---

# Upgrading Core Language Key Hooks

[TOC levels=1-4]

Here are the steps for upgrading a core language key hook to @product-ver@. 

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

3.  [Create a resource bundle loader](/docs/7-1/tutorials/-/knowledge_base/t/overriding-global-language-keys#create-a-resource-bundle-service-component). 

4.  [Deploy your module](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module). 

Your core language key customizations are deployed to @product-ver@. 

## Related Topics

[Overriding Global Language Keys](/docs/7-1/tutorials/-/knowledge_base/t/overriding-global-language-keys)

[Upgrading Portlet Language Key Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portlet-language-key-hooks)

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)     
