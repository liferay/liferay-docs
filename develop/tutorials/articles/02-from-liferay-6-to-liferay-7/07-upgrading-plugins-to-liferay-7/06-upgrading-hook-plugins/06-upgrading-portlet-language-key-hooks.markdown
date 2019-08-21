---
header-id: upgrading-portlet-language-key-hooks
---

# Upgrading Portlet Language Key Hooks

[TOC levels=1-4]

You can upgrade your portlet language key hooks to @product-ver@ by following
these steps: 

1.  Create a new module based on the Blade sample `resource-bundle` 
    ([Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/resource-bundle)
    or [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/resource-bundle) project). 

    Here are the module folder structure's main files:

    -   `src/main/java/[resource bundle path]` &rarr; [`ResourceBundleLoader`
        extension](@platform-ref@/7.1-latest/javadocs/portal-kernel/) goes here
    -   `src/main/resources/content`
        - `Language.properties`
        - `Language_xx.properties`
        - ...

2.  Copy your language properties files into module
    folder `src/main/resources/content/`.

3.  In your `bnd.bnd` file,
    [specify OSGi manifest headers](/docs/7-1/tutorials/-/knowledge_base/t/overriding-a-modules-language-keys)
    that target the portlet module's resource bundle, but prioritize yours. 

4.  [Deploy your module](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module). 

Your portlet language key customizations are deployed in your new module on
@product-ver@. 

## Related Topics

[Overriding a Module's Language Keys](/docs/7-1/tutorials/-/knowledge_base/t/overriding-a-modules-language-keys)

[Upgrading Core Language Key Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-language-key-hooks)

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
