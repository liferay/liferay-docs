---
header-id: upgrading-core-language-key-hooks
---

# Upgrading Core Language Key Hooks

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Customization Plugins</p><p>Step 5 of 11</p>
</div>

Here are the steps for upgrading a core language key hook to @product-ver@. 

1.  Create a new module based on the Blade sample `resource-bundle` project
    ([Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/resource-bundle)
    or [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/resource-bundle)). 

    Here are the main parts of the module folder structure:

    - `src/main/java/[resource bundle path]` &rarr; Custom resource bundle class
      goes here 
    -  `src/main/resources/content`
        - `Language.properties`
        - `Language_xx.properties`
        - ...

2.  Copy all your plugin's language properties files into the module
    folder `src/main/resources/content/`.

3.  [Create a resource bundle loader](/docs/7-2/customization/-/knowledge_base/c/overriding-global-language-keys#create-a-resource-bundle-service-component). 

4.  [Deploy your module](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project). 

Your core language key customizations are deployed to @product-ver@.
