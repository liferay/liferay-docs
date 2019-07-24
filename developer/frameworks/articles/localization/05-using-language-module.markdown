---
header-id: using-a-language-module
---

# Using a Language Module

[TOC levels=1-4]

A module or traditional Liferay plugin can use a resource bundle from another
module and optionally include its own resource bundle. OSGi manifest headers
`Require-Capability` and `Provide-Capability` make this possible, and it's
especially easy in modules generated from Liferay project templates. 
Instructions for using a language module are divided into these environments: 

-  [Using a Language Module from a Module](#using-a-language-module-from-a-module)
-  [Using a Language Module from a Traditional Plugin](#using-a-language-module-from-a-traditional-plugin)

If you're using bnd with Maven or Gradle, you need only specify Liferay's
`-liferay-aggregate-resource-bundle:` bnd instruction---at build time, Liferay's
bnd plugin converts the instruction to `Require-Capability` and
`Provide-Capability` parameters automatically. Both approaches are demonstrated 
here. 

## Using a Language Module from a Module

Modules generated from Liferay project templates have a Liferay bnd build time
instruction called `-liferay-aggregate-resource-bundles`. It lets you use other
resource bundles (including their language keys) along with your own. 

Here's how to use this bnd instruction: 

1.  Open your module's `bnd.bnd` file.

2.  Add the `-liferay-aggregate-resource-bundles:` bnd instruction and assign it
    the bundle symbolic names of modules whose resource bundles you wish to 
    aggregate with the current module's resource bundle: 

    ```properties
    -liferay-aggregate-resource-bundles: \
        [bundle.symbolic.name1],\
        [bundle.symbolic.name2]
    ```

For example, a module that uses resource bundles from modules
`com.liferay.docs.l10n.myapp1.lang` and `com.liferay.docs.l10n.myapp2.lang`
would set this in its `bnd.bnd` file:

```properties
-liferay-aggregate-resource-bundles: \
    com.liferay.docs.l10n.myapp1.lang,\
    com.liferay.docs.l10n.myapp2.lang
```

The current module's resource bundle is prioritized over those of the listed 
modules. 

| **Note:** The Shared Language Key 
| [sample project](/docs/7-2/reference/-/knowledge_base/r/shared-language-keys)
| is a working example that demonstrates aggregating resource bundles. You can
| deploy it in Gradle, Maven, and Liferay Workspace build environments. 

At build time, Liferay's bnd plugin converts the bnd instruction to 
`Require-Capability` and `Provide-Capability` parameters automatically. In 
traditional Liferay plugins, you must specify the parameters manually. 

| **Note:** You can always specify the `Require-Capability` and `Provide-
| Capability` OSGi manifest headers manually, as the next section demonstrates. 

## Using a Language Module from a Traditional Plugin

To use a language module from a traditional Liferay plugin you must specify the
language module via the `Require-Capability` and `Provide-Capability` OSGi 
manifest headers in the plugin's `liferay-plugin-package.properties` file. 

Follow these steps to configure your traditional plugin to use a language 
module: 

1.  Open the plugin's `liferay-plugin-package.properties` file and add a 
    `Require-Capability` header that filters on the language module's resource
    bundle capability. For example, if the language module's symbolic name is
    `myapp.lang`, specify the requirement like this: 

    ```properties
    Require-Capability: liferay.resource.bundle;filter:="(bundle.symbolic.name=myapp.lang)"
    ```

2.  In the same `liferay-plugin-package.properties` file, add a 
    `Provide-Capability` header that adds the language module's resource bundle
    as this plugin's (the `myapp.web` plugin) own resource bundle:

    ```properties
    Provide-Capability:\
    liferay.resource.bundle;resource.bundle.base.name="content.Language",\
    liferay.resource.bundle;resource.bundle.aggregate:String="(bundle.symbolic.name=myapp.lang)";bundle.symbolic.name=myapp.web;resource.bundle.base.name="content.Language";service.ranking:Long="4";\
    servlet.context.name=myapp-web
    ```

In this case, the `myapp.web` plugin solely uses the language module's resource
bundle---the resource bundle aggregate only includes language module 
`myapp.lang`. 

Aggregating resource bundles comes into play when you want to use a language
module's resource bundle in addition to your plugin's resource bundle. These
instructions show you how to do this, while prioritizing your current plugin's
resource bundle over the language module resource bundle. In this way, the
language module's language keys compliment your plugin's language keys. 

For example, a portlet whose bundle symbolic name is `myapp.web` uses keys from
the language module `myapp.lang` in addition to its own. The portlet's
`Provide-Capability` and `Web-ContextPath` OSGi headers accomplish this.

```properties
Provide-Capability:\
liferay.resource.bundle;resource.bundle.base.name="content.Language",\
liferay.resource.bundle;resource.bundle.aggregate:String="(bundle.symbolic.name=myapp.web),(bundle.symbolic.name=myapp.lang)";bundle.symbolic.name=myapp.web;resource.bundle.base.name="content.Language";service.ranking:Long="4";\
servlet.context.name=myapp-web
```

The example `Provide-Capability` header has two parts: 

1.  `liferay.resource.bundle;resource.bundle.base.name="content.Language"` 
    declares that the module provides a resource bundle whose base name is
    `content.language`. 

2.  The `liferay.resource.bundle;resource.bundle.aggregate:String=...` directive
    specifies the list of bundles whose resource bundles are aggregated, the
    target bundle, the target bundle's resource bundle name, and this service's
    ranking:

    -   `"(bundle.symbolic.name=myapp.web),(bundle.symbolic.name=myapp.lang)"`:
        The service aggregates resource bundles from bundles
        `bundle.symbolic.name=myapp.web` (the current
        module) and `bundle.symbolic.name=myapp.lang`.
        Aggregate as many bundles as desired. Listed bundles are prioritized in
        descending order. 
    -   `bundle.symbolic.name=myapp.web;resource.bundle.base.name="content.Language"`:
        Override the `myapp.web` bundle's resource bundle named
        `content.Language`.
    -   `service.ranking:Long="4"`: The resource bundle's service ranking is 
        `4`. The OSGi framework applies this service if it outranks all other
        resource bundle services that target `myapp.web`'s
        `content.Language` resource bundle. 
    -   `servlet.context.name=myapp-web`: The target resource bundle is in 
        servlet context `myapp-web`. 

Now the language keys from the aggregated resource bundles compliment your 
plugin's language keys. 

## Related Topics

[Localizing Your Application](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application)

[Overriding Language Keys](/docs/7-2/customization/-/knowledge_base/c/overriding-language-keys)
