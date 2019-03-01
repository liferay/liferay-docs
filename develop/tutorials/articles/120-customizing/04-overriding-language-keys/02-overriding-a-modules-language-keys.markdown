# Overriding a Module's Language Keys [](id=overriding-a-modules-language-keys)

What do you do if the language keys you want to modify are in one of Liferay's
applications or another module whose source code you don't control? Since module
language keys are in the respective module, the process for overriding a
module's language keys is different from
[the process of overriding Liferay's language keys](/develop/tutorials/-/knowledge_base/7-1/overriding-language-keys). 

Here is the process:

1.  [Find the module and its metadata and language keys](#find-the-module-and-its-metadata-and-language-keys)
2.  [Write your custom language key values](#providing-language-keys) 
3.  [Prioritize your module's resource bundle](#prioritize-your-modules-resource-bundle)

## Find the module and its metadata and language keys [](id=find-the-module-and-its-metadata-and-language-keys)

In
[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell),
list the bundles and grep for keyword(s) that match the portlet's display name.
Language keys are in the portlet's web module (bundle). When you
find the bundle, note its ID number.

To find the Blogs portlet, for example, your Gogo commands and output might look
like this:

    g! lb | grep Blogs
      152|Active     |    1|Liferay Blogs Service (1.0.2)
      184|Active     |    1|Liferay Blogs Editor Config (2.0.1)
      202|Active     |    1|Liferay Blogs Layout Prototype (2.0.2)
      288|Active     |    1|Liferay Blogs Recent Bloggers Web (1.0.2)
      297|Active     |    1|Liferay Blogs Item Selector Web (1.0.2)
      374|Active     |    1|Liferay Blogs Item Selector API (2.0.1)
      448|Active     |    1|Liferay Blogs API (3.0.1)
      465|Active     |    1|Liferay Blogs Web (1.0.6)
    true

List the bundle's headers by passing its ID to the `headers` command. 

    g! headers 465

    Liferay Blogs Web (465)
    -----------------------
    Manifest-Version = 1.0
    Bnd-LastModified = 1459866186018
    Bundle-ManifestVersion = 2
    Bundle-Name = Liferay Blogs Web
    Bundle-SymbolicName = com.liferay.blogs.web
    Bundle-Version: 1.0.6
    ... 
    Web-ContextPath = /blogs-web
    g! 

Note the `Bundle-SymbolicName`, `Bundle-Version`, and `Web-ContextPath`. The
`Web-ContextPath` value, following the `/`, is the servlet context name.

**Important**: Record the servlet context name, bundle symbolic name and
version, as you'll use them to create the resource bundle loader later in the
process.

For example, here are those values for Liferay Blogs Web module:

- Bundle symbolic name: `com.liferay.blogs.web`
- Bundle version: `1.0.6`
- Servlet context name: `blogs-web`

Next find the module's JAR file so you can examine its language keys. Liferay
follows this module JAR file naming convention:

    [bundle symbolic name]-[version].jar

For example, the Blogs Web version 1.0.6 module is in
`com.liferay.blogs.web-1.0.6.jar`.

Here's where to find the module JAR:

-   Liferay's [Nexus repository](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/)
-  `[Liferay Home]/osgi/modules`
-   Embedded in an application's or application suite's LPKG file in `[Liferay
    Home]/osgi/marketplace`.

The language property files are in the module's
`src/main/resources/content` folder. Identify the language keys you want to
override in the `Language[_xx].properties` files.

Checkpoint: Make sure you have the required information for overriding the
module's language keys:

-   Language keys
-   Bundle symbolic name
-   Servlet context name

Next you'll write new values for the language keys. 

## Write custom language key values [](id=providing-language-keys)

Create a new module to hold a resource bundle loader and your custom language
keys. 

In your module's `src/main/resources/content` folder, create
[language properties files](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application#what-are-language-keys)
for each locale whose keys you want to override. In each language properties
file, specify your language key overrides. 

Next you'll prioritize your module's language keys as a resource bundle for the
target module. 

## Prioritize Your Module's Resource Bundle [](id=prioritize-your-modules-resource-bundle)

Now that your language keys are in place, use OSGi manifest headers to specify
the language keys are for the target module. To compliment the target module's
resource bundle, you'll aggregate your resource bundle with the target module's
resource bundle. You'll list your module first to prioritize its resource bundle
over the target module resource bundle.  Here's an example of module
`com.liferay.docs.l10n.myapp.lang` prioritizing its resource bundle over target
module `com.liferay.blogs.web`'s resource bundle:

    Provide-Capability:\
    liferay.resource.bundle;resource.bundle.base.name="content.Language",\
    liferay.resource.bundle;resource.bundle.aggregate:String="(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang),(bundle.symbolic.name=com.liferay.blogs.web)";bundle.symbolic.name=com.liferay.blogs.web;resource.bundle.base.name="content.Language";service.ranking:Long="2";\
    servlet.context.name=blogs-web

The example `Provide-Capability` header has two parts: 

1.  `liferay.resource.bundle;resource.bundle.base.name="content.Language"`
    declares that the module provides a resource bundle with the base name
    `content.language`. 

2.  The `liferay.resource.bundle;resource.bundle.aggregate:String=...` directive
    specifies the list of bundles with resource bundles to aggregate, the
    target bundle, the target bundle's resource bundle name, and this service's
    ranking:

    -   `"(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang),(bundle.symbolic.name=com.liferay.blogs.web)"`:
        The service aggregates resource bundles from bundles
        `com.liferay.docs.l10n.myapp.lang` and `com.liferay.blogs.web`.
        Aggregate as many bundles as desired. Listed bundles are prioritized in
        descending order. 
    -   `bundle.symbolic.name=com.liferay.blogs.web;resource.bundle.base.name="content.Language"`:
        Override the `com.liferay.blogs.web` bundle's resource bundle named
        `content.Language`.
    -   `service.ranking:Long="2"`: The resource bundle's service ranking is 
        `2`. The OSGi framework applies this service if it outranks all other
        resource bundle services that target `com.liferay.blogs.web`'s
        `content.Language` resource bundle. 
    -   `servlet.context.name=blogs-web`: The target resource bundle is in 
        servlet context `blogs-web`. 

[Deploy your module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module)
to see the language keys you've overridden. 

+$$$

**Tip:** If your override isn't showing, use
[Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
to check for competing resource bundle services. It may be that another service
outranks yours. To check for competing resource bundle services whose aggregates
include `com.liferay.blogs.web`'s resource bundle, for example, execute this
Gogo Shell command:

    services "(bundle.symbolic.name=com.liferay.login.web)"

Search the results for resource bundle aggregate services whose ranking is
higher. 

$$$

Now you can modify the language keys of modules in Liferay's OSGi runtime.
Remember, language keys you want to override might actually be in Liferay's
core. You can
[override Liferay's language keys](/develop/tutorials/-/knowledge_base/7-1/overriding-language-keys)
too.

## Related Topics [](id=related-topics)

[Resource Bundle Override Sample Project](/develop/reference/-/knowledge_base/7-1/resource-bundle-override)

[Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-1/upgrading-core-language-key-hooks)

[Internationalization](/develop/tutorials/-/knowledge_base/7-1/internationalization)
