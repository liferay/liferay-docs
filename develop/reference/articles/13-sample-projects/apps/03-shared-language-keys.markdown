# Shared Language Keys [](id=shared-language-keys)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Shared Language Keys sample provides a JSP portlet that displays language
keys.

![Figure 1: The sample JSP portlet displays three language keys.](../../../images/language-web-portlet.png)

The language keys displayed in the portlet come from two different modules.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample is broken into two modules:

- `language`
- `language-web`

The `language-web` module provides a JSP portlet with unique language keys that
it displays. The `language` module provides a resource module which only holds
language keys. Its sole purpose is to share language keys with the JSP portlet
provided in `language-web`. This sample conveys Liferay's recommended approach
to sharing language keys through OSGi services.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

You must deploy both `language-web` and `language` modules to simulate this
sample's targeted demonstration.

First, note the language keys provided by each module:

`language-web`
- `blade_language_web_LanguageWebPortlet.caption=Hello from BLADE Language Web!`
- `blade_language_web_override_LanguageWebPortlet.caption=I have overridden the key from BLADE Language Module!`

`language`
- `blade_language_LanguageWebPortlet.caption=Hello from the BLADE Language Module!`
- `blade_language_web_override_LanguageWebPortlet.caption=Hello from the BLADE Language Module but you won't see me!`

When you place the sample BLADE Language Web portlet on a @product@ page, you're
presented with three language keys.

![Figure 2: The Language Web portlet displays three phrases, two of which are shared from a different module.](../../../images/shared-language-keys.png)

The first message is provided by the `language-web` module. The second message
is from the `language` module. The third message is provided by both modules; as
you can see, the `language-web`'s message is used, overriding the `language`
module's identically named language key.

This sample shows what takes precedence when displaying language keys. The order
for this example goes

1.  `language-web` module language keys
2.  `language` module language keys
3.  @product@ language keys

So how does sharing language keys work?

By default, the `ResourceBundleLoaderAnalyzerPlugin` expands modules with
`/content/Language.properties` files to add provided capabilities:

- `bundle.symbolic.name`
- `resource.bundle.base.name`

Then the deployed `LanguageExtender` scans modules with those capabilities to
automatically register an associated `ResourceBundleLoader`.

You can leverage this functionality to use keys from common language modules by
republishing an aggregate `ResourceBundleLoader`. This can be done two ways:

1. Via Components

    You can get a reference to the registered service in your components as
    detailed in the
		[Overriding a Module's Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#overriding-a-modules-language-keys)
		tutorial. The main disadvantage of this approach is that it forces you to
		provide a specific implementation of the `ResourceBundleLoader`, making it
		harder to modularize in the future.

2. Via Provide Capability

    The same `LanguageExtender` that registers the services supports an extended
    syntax that lets you register an aggregate of a collection of bundles:

        -liferay-aggregate-resource-bundles: \
            blade.language

    This approach has the advantage of easier extensibility. When language keys
    change, only the common language modules must be built and redeployed for
    the modules referencing them to recognize their updates.

For more information on sharing language keys, visit the
[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
tutorials.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/shared-language-keys)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/apps/shared-language-keys)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/apps/shared-language-keys)
