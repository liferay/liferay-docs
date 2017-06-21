# Upgrading Core Language Key Hooks [](id=upgrading-core-language-key-hooks)

If you developed any core language key hooks in previous versions of @product@, you'll want to upgrade them to Liferay 7. The following steps show you how. 

1.  Create a new module based on the Blade sample `blade.resourcebundle` in     
    [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/blade.resourcebundle)
    or [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/blade.resourcebundle). 

    Here are the module folder structure's main files:

    - `src/main/java/[resource bundle path]/` &rarr; Custom resource bundle class here 
    -  `src/main/resources/content/` &rarr; Language properties files here
        - `Language.properties`
        - `Language_xx.properties`
        - ...

2.  Copy all of your plugin's language properties files into the module
    folder `src/main/resources/content/`.

3.  [Create a `resource bundle`](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#creating-a-resource-bundle). 

4.  [Deploy your module](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module). 

Your Language key customizations are deployed to @product-ver@. 



         
