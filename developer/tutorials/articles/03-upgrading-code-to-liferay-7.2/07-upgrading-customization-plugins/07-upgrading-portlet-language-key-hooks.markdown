# Upgrading Portlet Language Key Hooks

<div class="learn-path-step">
    <p>Upgrading Customization Plugins<br>Step 6 of 11</p>
</div>

You can upgrade your portlet language key hooks to @product-ver@ by following
these steps:

1.  Create a new module based on the Blade sample `resource-bundle` project
    ([Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/resource-bundle)
    or [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/resource-bundle)). 

    Here are the module folder structure's main files:

    - `src/main/java/[resource bundle path]` &rarr;
      [`ResourceBundleLoader` extension](@platform-ref@/7.2-latest/javadocs/portal-kernel/)
      goes here
    - `src/main/resources/content`
        - `Language.properties`
        - `Language_xx.properties`
        - ...

2.  Copy your language properties files into module folder
    `src/main/resources/content/`.

3.  In your `bnd.bnd` file,
    [specify OSGi manifest headers](/docs/customization/7-2/-/knowledge_base/c/overriding-a-modules-language-keys)
    that target the portlet module's resource bundle, but prioritize yours. 

4.  [Deploy your module](/docs/reference/7-2/-/knowledge_base/r/deploying-a-project). 

Your portlet language key customizations are deployed in your new module on
@product-ver@. 
