# Shared Language Keys [](id=shared-language-keys)

The Shared Language Keys sample is broken into two modules:

- `language`
- `language-web`

The Language Web module provides a JSP portlet that displays language keys. The
Language module shares language keys with the portlet. You'll explore the web
module first.

## Language Web [](id=language-web)

The Language Web module is a simple JSP portlet that conveys Liferay's
recommended approach to sharing language keys through OSGI services.

You must deploy this module with the `language` sample module. The Language Web
portlet uses language keys shared by the Language module. When you place
this sample portlet on a @product@ page, you're presented with the portlet's
name followed by three language keys.

![Figure 1: The Language Web portlet displays three phrases, two of which are shared from a different module.](../../../images/language-web-portlet.png)

The first message is provided by the Language Web module. The second message is
from the Language module. The third message is provided by both modules; as you
can see, the Language Web's message is used, overriding the Language module's
identically named language key.

This sample shows what takes precedence when displaying language keys. The order
for this example goes

1.  Language Web module language keys
2.  Language module language keys
3.  Liferay Portal language keys

Next, you'll examine the `language` module and learn how language keys are used
from modules and shared between modules.

## Language [](id=language)

The Language module conveys Liferay's recommended approach to sharing language
keys through OSGI services. This particular module provides a resource module
which only holds language keys.

You should deploy this module with the `language-web` sample module. The
Language module shares language keys with the Language Web module. How does this
work?

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
