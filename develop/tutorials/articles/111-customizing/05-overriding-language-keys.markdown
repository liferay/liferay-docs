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
    
    `publish=Publish`
 
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
3.  [Implement a Resource Bundle Loader](#implementing-a-resource-bundle-loader)

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

Next you'll create a resource bundle loader component to apply the language keys
to the target module. 

### Implement a resource bundle loader [](id=implementing-a-resource-bundle-loader)

In this step, you'll create a resource bundle loader component that combines
your new resource bundle containing the new language key values with the target
module's existing resource bundle. The new language key values are combined so
they override the old ones. 

For example, the following resource bundle loader component combines the current
bundle's language keys with the `com.liferay.blogs.web` module's language keys
and applies them to the `com.liferay.blogs.web` module. 

    @Component(
    	immediate = true,
    	property = {
    		"bundle.symbolic.name=com.liferay.blogs.web",
    		"resource.bundle.base.name=content.Language",
    		"servlet.context.name=blogs-web"
    	}
    )
    public class MyBlogsResourceBundleLoader implements ResourceBundleLoader {

    	@Override
    	public ResourceBundle loadResourceBundle(Locale locale) {
    		return _resourceBundleLoader.loadResourceBundle(locale);
    	}

    	@Override
    	public ResourceBundle loadResourceBundle(String languageId) {
    		return _resourceBundleLoader.loadResourceBundle(languageId);
    	}

    	@Reference(
    		target = "(&(bundle.symbolic.name=com.liferay.blogs.web)(!(component.name=com.liferay.docs.override.moduleresourcebundle.MyBlogsResourceBundleLoader)))"
    	)
    	public void setResourceBundleLoader(
    		ResourceBundleLoader resourceBundleLoader) {

    		_resourceBundleLoader = new AggregateResourceBundleLoader(
    			new CacheResourceBundleLoader(
    				new ClassResourceBundleLoader(
    					"content.Language",
    					MyBlogsResourceBundleLoader.class.getClassLoader())),
    			resourceBundleLoader);
    	}

    	private AggregateResourceBundleLoader _resourceBundleLoader;

    }

This class implements
[`com.liferay.portal.kernel.util.ResourceBundleLoader`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html)
and overrides all its methods. 

The `@Component` annotation registers the class as a resource bundle loader component
for the target module. The example's target module has the bundle symbolic name
`com.liferay.blogs.web` and servlet context `blogs-web`. Liferay's modules use a
base resource bundle named `content.Language`. Attribute setting `immediate =
true` tells the OSGi framework to activate the component immediately after its
dependencies resolve.

    @Component(
    	immediate = true,
    	property = {
    		"bundle.symbolic.name=com.liferay.blogs.web",
    		"resource.bundle.base.name=content.Language",
    		"servlet.context.name=blogs-web"
    	}
    )

Write your `@Component` annotation similarly, making sure to apply the bundle
symbolic name and servlet context name you recorded earlier to the following
properties:

-   `"bundle.symbolic.name=[target module bundle symbolic name]"`
-   `"servlet.context.name=[target module servlet context name]"`

The class's resource bundle loader field `_resourceBundleLoader` field is an
`AggregateResourceBundleLoader` for grouping this resource bundle loader and the
target module's resource bundle loader together. 

    private AggregateResourceBundleLoader _resourceBundleLoader;

The `loadResourceBundle` methods return a resource bundle based on the locale and language. 

    @Override
    public ResourceBundle loadResourceBundle(Locale locale) {
        return _resourceBundleLoader.loadResourceBundle(locale);
    }

    @Override
    public ResourceBundle loadResourceBundle(String languageId) {
       return _resourceBundleLoader.loadResourceBundle(languageId);
    }

+$$$

**Note**: As of Liferay DXP Digital Experience Fix Pack de-33 and Liferay Portal
CE GA6, `ResourceBundleLoader` method `loadResourceBundle(String)` is deprecated
and replaced by new method `loadResourceBundle(Locale)`.

$$$

The setter method `setResourceBundleLoader` assigns an aggregate of this class's
resource bundle loader and the target resource bundle loader to the
`_resourceBundleLoader` field. 
        
    @Reference(target = "(bundle.symbolic.name=com.liferay.blogs.web)(!(component.name=com.liferay.docs.override.moduleresourcebundle.MyBlogsResourceBundleLoader)))"
    )
    public void setResourceBundleLoader(
        ResourceBundleLoader resourceBundleLoader) {

        _resourceBundleLoader = new AggregateResourceBundleLoader(
            new CacheResourceBundleLoader(
                new ClassResourceBundleLoader(
                    "content.Language",
                    MyBlogsResourceBundleLoader.class.getClassLoader())),
            resourceBundleLoader);
    }

The `@Reference` annotation tells @product@'s OSGi framework to pass the target
module's resource bundle loader as the parameter. Specifying the target module
is tricky because the target module and the resource bundle loader component
have the same bundle symbolic name property
(`bundle.symbolic.name=com.liferay.blogs.web`). To prevent this resource bundle
loader component from being targeted, specify an `!` (not) character followed by
this resource bundle loader's `component.name` property. 

The method creates a resource bundle loader that aggregates this module's
resource bundle loader and the target module's resource bundle loader. The first
loader's resource bundle is prioritized ahead of the resource bundles that
follow it. Therefore, this class's resource bundle loader and its resource
bundle (i.e., language keys), take precedence.

If you use the example `setResourceBundleLoader` method, make sure to replace
`MyBlogsResourceBundleLoader` with your resource bundle loader's class name. 

Resource bundle loader components have these class imports.

    import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
    import com.liferay.portal.kernel.util.CacheResourceBundleLoader;
    import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
    import com.liferay.portal.kernel.util.ResourceBundleLoader;

    import java.util.ResourceBundle;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

You can
[depend on packages from the following artifacts](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)
to provide the classes imported above.

 Group | Artifact |
 :------ | :------ |
`com.liferay.portal` | `com.liferay.portal.kernel` |
 `org.osgi` | `org.osgi.service.component.annotations` | 

 **Important**: If your module
 [uses language keys from another module](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application#using-a-language-module)
 and
 [overrides any of that other module's keys](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application#using-a-language-module-from-a-module),
 make sure to use OSGi headers to specify the capabilities your module requires
 and provides. This lets you prioritize resource bundles from the modules. 

 To see your language key overrides in action,
 [deploy your module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module)
 and visit a portlet that uses the module's language keys. 

Now you can modify the language keys of modules in Liferay's OSGi runtime.
Remember, language keys you want to override might actually be in Liferay's
core. You can
[override global language keys](#modifying-liferays-language-keys) too.

## Related Topics [](id=related-topics)

[Resource Bundle Override Sample Project](/develop/reference/-/knowledge_base/7-0/resource-bundle-override)

[Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-core-language-key-hooks)

[Internationalization](internationalization)
