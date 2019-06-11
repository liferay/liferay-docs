---
header-id: creating-a-language-module
---

# Creating a Language Module

[TOC levels=1-4]

You might have an application with multiple modules that provide the view layer. 
These modules are often called web modules. For example, this application 
contains three such modules: 

    my-application/
    my-application-web/
    my-admin-application-web/
    my-application-content-web/
    my-application-api/
    my-application-service/

Each of these modules can have language keys and translations to maintain, 
likely resulting in duplicate keys. You don't want to end up with different
values for the same key, and you don't want to maintain language keys in 
multiple places. In this case, you should create a single module---a language 
module---for housing all your app's language keys. 

Follow these steps to create a language module: 

1.  In the root project folder (the one that holds your service, API, and web 
    modules), 
    [create a new module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project) 
    to hold your app's language keys. 

2.  In the language module, create a `src/main/resources/content` folder. In 
    this folder, create a `Language.properties` file for your app's default 
    language keys. For example, such a file might look like this: 

    ```properties
    my-app-title=My Application
    add-entity=Add Entity
    ```

3.  Create any translations you want in additional language properties files, 
    appending the locale's ID to the file name. For example, a file 
    `Language_es.properties` holds Spanish (`es`) translations and could contain 
    something like this: 

    ```properties
    my-app-title=Mi Aplicación
    add-entity=Añadir Entity
    ```

    Here's the folder structure of an example language module called 
    `my-application-lang`. This module contains the app's default language keys 
    (`Language.properties`) and a Spanish translation 
    (`Language_es.properties`): 

        my-application-lang/
            bnd.bnd
            src/
                main/
                    resources/
                        content/
                            Language.properties
                            Language_es.properties
                            ...

On building the language module, @product@'s 
`ResourceBundleLoaderAnalyzerPlugin` detects the module's `Language.properties` 
file and adds a resource bundle 
[capability](http://blog.osgi.org/2015/12/using-requirements-and-capabilities.html) 
to the module. A capability is a contract a module declares to the OSGi 
framework. Capabilities let you associate services with modules that provide 
them. In this case, @product@ registers a 
[ResourceBundleLoader](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html) 
service for the resource bundle capability. 

## Related Topics

[Overriding Language Keys](/docs/7-2/customization/-/knowledge_base/c/overriding-language-keys)

[Localizing Your Application](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application)

[Using Liferay's Localization Settings](/docs/7-2/frameworks/-/knowledge_base/f/using-liferays-localization-settings)

[Using a Language Module](/docs/7-2/frameworks/-/knowledge_base/f/using-a-language-module)
