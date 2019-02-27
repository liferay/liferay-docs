# Overriding Language Keys [](id=overriding-language-keys)

@product@ Core and portlet module `language*.properties` files implement site
internationalization. They're fully customizable, too. This tutorial
demonstrates this in the following topics:

-   [Overriding Global Language Keys](#modifying-liferays-language-keys) 
-   [Overriding a Module's Language Keys](#overriding-a-modules-language-keys)

## Modifying Global Language Keys [](id=modifying-liferays-language-keys)

Language files contain 
[translations of your application's user interface
messages](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application).
But you can also override the default language keys globally and in other
applications (including your own). Here are the steps for overriding 
language keys:

1.  [Determine the language keys to override](#determine-the-language-keys-to-override)
2.  [Override the keys in a new language properties file](#override-the-keys-in-a-new-language-properties-file)
3.  [Create a Resource Bundle service component](#create-a-resource-bundle-service-component)

+$$$

**Note:** Many applications that were once part of Liferay Portal 6.2 are now
modularized. Their language keys might have been moved out of Liferay's language
properties files and into one of the application modules. The process for
[overriding a module's language keys](#overriding-a-modules-language-keys) is
different from the process for overriding global language keys. 

$$$

## Determine the language keys to override [](id=determine-the-language-keys-to-override)

So how do you find global language keys? They're in the `Language[xx_XX].properties`
files in the source code or your @product@ bundle.

-   From the source:

    `/portal-impl/src/content/Language[_xx_XX].properties`

-   From a bundle:

    `portal-impl.jar`

All language properties files contain properties you can override, like the
language settings properties:

    ##
    ## Language settings
    ##

    ...
    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr
    ...

There are also many simple keys you can override to update default messages and
labels.

    ##
    ## Category titles
    ##

    category.admin=Admin
    category.alfresco=Alfresco
    category.christianity=Christianity
    category.cms=Content Management
    ...

For example, Figure 1 shows a button that uses Liferay's `publish` default
language key.
    
    publish=Publish
 
![Figure 1: Messages displayed in Liferay's user interface can be customized.](../../images/standard-publish.png)

Next, you'll learn how to override this key. 

## Override the keys in a new language properties file [](id=override-the-keys-in-a-new-language-properties-file)

Once you know the keys to override, create a language properties file for the
locale you want (or the default `Language.properties` file) in your module's
`src/main/resources/content` folder. In your file, define the keys your way. For
example, you could override the `publish` key.

    publish=Publish Override

To enable your change, you must create a resource bundle service component to
reference your language file. 

### Create a Resource Bundle service component [](id=create-a-resource-bundle-service-component)

In your module, create a class that extends `java.util.ResourceBundle` for the
locale you're overriding. Here's an example resource bundle class for the
`en_US` locale:

    @Component(
        property = { "language.id=en_US" }, 
        service = ResourceBundle.class
    )
    public class MyEnUsResourceBundle extends ResourceBundle {

        @Override
        protected Object handleGetObject(String key) {
            return _resourceBundle.getObject(key);
        }

        @Override
        public Enumeration<String> getKeys() {
            return _resourceBundle.getKeys();
        }

        private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
            "content.Language_en_US", UTF8Control.INSTANCE);

    }

The class's `_resourceBundle` field is assigned a `ResourceBundle`. The call to
`ResourceBundle.getBundle` needs two parameters. The `content.Language_en_US`
parameter is the language file's qualified name with respect to the module's
`src/main/resources` folder. The second parameter is a `control` that sets the
language syntax of the resource bundle. To use language syntax identical to
Liferay's syntax, import Liferay's
`com.liferay.portal.kernel.language.UTF8Control` class and set the second
parameter to `UTF8Control.INSTANCE`.

The class's `@Component` annotation declares it an OSGi `ResourceBundle` service
component. It's `language.id` property designates it for the `en_US` locale. 

    @Component(
        property = { "language.id=en_US" }, 
        service = ResourceBundle.class
    )

The class overrides these methods:

-   **`handleGetObject`:** Looks up the key in the module's resource bundle 
    (which is based on the module's language properties file) and returns the
    key's value as an `Object`. 

-   **`getKeys`:** Returns an `Enumeration` of the resource bundle's keys. 

Your resource bundle service component redirects the default language keys to
your module's language key overrides. 

+$$$

**Note**: Global language key overrides for multiple locales require a separate
module for each locale. Each module's `ResourceBundle` extension class (like the
`MyEnUsResourceBundle` class above) must specify its locale in the `language.id`
component property definition and in the language file qualified name parameter.
For example, here is what they look like for the Spanish locale.

Component definition:

    @Component(
        property = { "language.id=es_ES" }, 
        service = ResourceBundle.class
    )

Resource bundle assignment:

    private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
        "content.Language_es_ES", UTF8Control.INSTANCE);

$$$

**Important**: If your module
[uses language keys from another module](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application#using-a-language-module)
and
[overrides any of that other module's keys](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application#using-a-language-module-from-a-module),
make sure to use OSGi headers to specify the capabilities your module requires
and provides. This lets you prioritize resource bundles from the modules. 

To see your Liferay language key overrides in action,
[deploy your module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module)
and visit the portlets and pages that use the keys. 

![Figure 2: This button uses the overridden `publish` key.](../../images/localized-publish.png)

That's all there is to overriding global language keys.

## Overriding a Module's Language Keys [](id=overriding-a-modules-language-keys)

What do you do if the language keys you want to modify are in one of Liferay's
applications or another module whose source code you don't control? Since module
language keys are in the respective module, the process for overriding a
module's language keys is different from
[the process of overriding global language keys](#modifying-liferays-language-keys). 

Here is the process:

1.  [Find the module and its metadata and language keys](#find-the-module-and-its-metadata-and-language-keys)
2.  [Write your custom language key values](#providing-language-keys) 
3.  [Prioritize your module's resource bundle](#prioritize-your-modules-resource-bundle)

### Find the module and its metadata and language keys [](id=find-the-module-and-its-metadata-and-language-keys)

In
[Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell),
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

### Write custom language key values [](id=providing-language-keys)

Create a new module to hold a resource bundle loader and your custom language
keys. 

In your module's `src/main/resources/content` folder, create
[language properties files](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application#what-are-language-keys)
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

Let's examine the example `Provide-Capability` header. 

1.  `liferay.resource.bundle;resource.bundle.base.name="content.Language"` 
    declares that the module provides a resource bundle whose base name is
    `content.language`. 

2.  The `liferay.resource.bundle;resource.bundle.aggregate:String=...` directive
    specifies the list of bundles whose resource bundles are aggregated, the
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

[Deploy your module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module)
to see the language keys you've overridden. 

+$$$

**Tip:** If your override isn't showing, use
[Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
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
[override global language keys](#modifying-liferays-language-keys) too.

## Related Topics [](id=related-topics)

[Resource Bundle Override Sample Project](/develop/reference/-/knowledge_base/7-0/resource-bundle-override)

[Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-core-language-key-hooks)

[Internationalization](internationalization)
