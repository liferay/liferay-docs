# Overriding Language Keys [](id=overriding-language-keys)

@product@ Core and portlet module `language*.properties` files implement site
internationalization. They're fully customizable, too. This tutorial
demonstrates this in the following topics:

-   [Modifying Liferay's Language Keys](#modifying-liferays-language-keys) 
-   [Overriding a Module's Language Keys](#overriding-a-modules-language-keys)

## Modifying Liferay's Language Keys [](id=modifying-liferays-language-keys)

Using language files with keys gives you the ultimate flexibility in
[translating your application's user interface messages](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application). But you can also modify
the language keys used by Liferay's core itself. This lets you change not only
your own application, but any messages in Liferay.

    publish=Publish

![Figure 1: Messages displayed in Liferay's User Interface can be customized.](../../images/standard-publish.png)

+$$$

**Note:** Much of the functionality and many of the applications in Liferay are now
separated out into their own modules. You might need to modify the keys included
in one of these modules. The process is different than the one described here,
and is described later in [Overriding a Module's Language Keys](#overriding-a-modules-language-keys).

$$$

So how do you find which keys are included in Liferay's core? You can access the
language properties from Liferay's source code or your Liferay bundle.

-  From Liferay's source:

    liferay-portal/portal-impl/src/content/Language.properties

-  From a Liferay bundle:

    [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

In any of the language properties files you'll see properties you can override,
like the `Language settings` properties:

    ##
    ## Language settings
    ##

    ...
    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr
    ...

You'll also see simple keys you can override to update messages in Liferay.

    ##
    ## Category titles
    ##

    category.admin=Admin
    category.alfresco=Alfresco
    category.christianity=Christianity
    category.cms=Content Management
    ...

As with most development tasks in Liferay, you'll deploy a module into Liferay's
OSGi runtime to override the keys you want.

### Creating a Resource Bundle [](id=creating-a-resource-bundle)

In your module, create a class that extends `java.util.ResourceBundle` and
register it as an OSGi service with the property `language.id` set to the
locale you'd like to override. Specify that you're providing an implementation
of `ResourceBundle`.

    @Component(
        property = { "language.id=en_US" }, 
        service = ResourceBundle.class
    )

In the class, override two methods:

-  **`handleGetObject`:** return the `Object` for a given key

-  **`getKeys`:** return an `Enumeration` of the keys in the resource bundle

        public class EnUSResourceBundle extends ResourceBundle {

            @Override
            protected Object handleGetObject(String key) {
                return _resourceBundle.getObject(key);
            }

            @Override
            public Enumeration<String> getKeys() {
                return _resourceBundle.getKeys();
            }

            private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
                "content.Language", UTF8Control.INSTANCE);

        }

The call to `ResourceBundle.getBundle` needs two parameters. The
`content.Language` parameter is meant to point to the language file (so it
answers the question, "what resource bundle?"), and the second parameter is a
`control` that sets the language syntax of the resource bundle. To use language
syntax identical to Liferay's syntax, import Liferay's
`com.liferay.portal.kernel.language.UTF8Control` and set the parameter to
`UTF8Control.INSTANCE`.

### Creating a Language Properties File [](id=creating-a-language-properties-file)

Next just provide a `Language.properties` file. Put it in your module's
`src/content` folder, and override any keys and values you'd like.

    publish=Publish Override

![Figure 2: To override core language keys, a simple module with a component and a properties file is created for each locale.](../../images/localized-publish.png)

That's all there is to overriding Liferay's core language keys.

## Overriding a Module's Language Keys [](id=overriding-a-modules-language-keys)

What do you do if the language keys you want to modify are in one of Liferay's
applications or another module whose source code you don't control? Since module
language keys are in the respective module, the process for overriding a
module's language keys is different from
[the process of overriding Liferay's core language properties](#modifying-liferays-language-keys).

The main difference is that the language keys are in a module outside of
@product@'s core. So you have to find the module. Of course, you must determine
the language keys to override. And you'll need the module's metadata to create a
component that applies your custom language key values. 

Here is the process for overriding module language keys:

1.  [Find the module and its metadata and language keys](#find-the-module-and-its-metadata-and-language-keys)
2.  [Write your custom language key values](#providing-language-keys) 
3.  [Implement a resource bundle loader](#implementing-a-resource-bundle-loader)

### Find the module and its metadata and language keys [](id=find-the-module-and-its-metadata-and-language-keys)

First find the module. In
[Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell),
list the bundles and grep for keyword(s) that match the portlet's display name.
Liferay portlet language keys are in the portlet's web module. When you find the
bundle, note its ID number.

For example, if you knew you were looking for the blogs portlet, your Gogo
commands and output might look like this:

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

List the bundle's headers by passing the bundle ID to the `headers` command. 

    g! headers 465

    Liferay Blogs Web (465)
    -----------------------
    Manifest-Version = 1.0
    Bnd-LastModified = 1459866186018
    Bundle-ManifestVersion = 2
    Bundle-Name = Liferay Blogs Web
    Bundle-SymbolicName = com.liferay.blogs.web
    ... 
    Web-ContextPath = /blogs-web
    g! 

Note the bundle symbolic name (`Bundle-SymbolicName`) and web context path. The
`Web-ContextPath` value, following the `/`, is the servlet context name.

For example, here are the Liferay Blogs Web module's bundle symbolic name and
servlet context name. 

- Bundle symbolic name: `com.liferay.blogs.web`
- Servlet context name: `blogs-web`

**Important**: Note the module's bundle symbolic name and servlet context name
for specifying in the resource bundle loader component you'll create later.

Next find the module's JAR file so you can examine its language keys. Liferay
follows this JAR file naming convention:

    [bundle symbolic name]-[version].jar

For example, version 1.0.0 of the Liferay Blogs Web module would be in
`com.liferay.blogs.web-1.0.0.jar`.

The module JAR is either standalone in `[Liferay Home]/osgi/modules` or embedded
in an application's or application suite's LPKG file in  `[Liferay
Home]/osgi/marketplace`. The language property files are typically in the
module's `src/main/resources/content` folder. In the module JAR, identify the
language keys you want to override.

Checkpoint: You have the required information for overriding the module's
language keys:

-   Bundle symbolic name
-   Servlet context name
-   Language keys

Next you'll write new values for the language keys. 

### Write custom language key values [](id=providing-language-keys)

Create a new module to hold your custom language keys and a resource bundle
loader. 

In your own module's `src/main/resources/content` folder, create a
[language properties files](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application#what-are-language-keys)
for each locale whose keys you want to override. In each language properties
file, assign the language keys your custom values. 

Next you'll create a resource bundle loader component to apply the language keys
to the target module. 

### Implement a resource bundle loader [](id=implementing-a-resource-bundle-loader)

In this step, you'll create a resource bundle loader component that aggregates
your new resource bundle (i.e., your new language key values) with the target
module's existing resource bundle. You'll aggregate them in such a way to give
preference to your new language key values. 

For example, this resource bundle loader component  aggregates the current
bundle's resource bundle with the `com.liferay.blogs.web` modules resource
bundle and applies it to the `com.liferay.blogs.web` module. 

    @Component(
    	immediate = true,
    	property = {
    		"bundle.symbolic.name=com.liferay.blogs.web",
    		"resource.bundle.base.name=content.Language",
    		"servlet.context.name=blogs-web"
    	}
    )
    public class ResourceBundleLoaderComponent implements ResourceBundleLoader {

    	@Override
    	public ResourceBundle loadResourceBundle(String languageId) {
    		return _resourceBundleLoader.loadResourceBundle(languageId);
    	}

    	@Reference(
    		target = "(&(bundle.symbolic.name=com.liferay.blogs.web)(!(component.name=com.liferay.blade.samples.hook.resourcebundle.ResourceBundleLoaderComponent)))"
    	)
    	public void setResourceBundleLoader(
    		ResourceBundleLoader resourceBundleLoader) {

    		_resourceBundleLoader = new AggregateResourceBundleLoader(
    			new CacheResourceBundleLoader(
    				new ClassResourceBundleLoader(
    					"content.Language",
    					ResourceBundleLoaderComponent.class.getClassLoader())),
    			resourceBundleLoader);
    	}

    	private AggregateResourceBundleLoader _resourceBundleLoader;

    }

This class implements
[`com.liferay.portal.kernel.util.ResourceBundleLoader`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html)
and overrides all its methods. 

The `@Component` annotation registers it as a resource bundle loader component
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

The class's resource bundle loader field `_resourceBundleLoader` field is an
`AggregateResourceBundleLoader` to comprise this resource bundle loader and the
target module's resource bundle loader. 

    private AggregateResourceBundleLoader _resourceBundleLoader;

The `loadResourceBundle` method returns a resource bundle based on the locale's
language ID. 

    @Override
    public ResourceBundle loadResourceBundle(String languageId) {
       return _resourceBundleLoader.loadResourceBundle(languageId);
    }

The setter method `setResourceBundleLoader` assigns an aggregate of the target
resource bundle loader and this class's resource bundle loader to the
`_resourceBundleLoader` field. 
        
    @Reference(target = "(bundle.symbolic.name=com.liferay.blogs.web)(!(component.name=com.liferay.blade.samples.hook.resourcebundle.ResourceBundleLoaderComponent)))"
    )
    public void setResourceBundleLoader(
        ResourceBundleLoader resourceBundleLoader) {

        _resourceBundleLoader = new AggregateResourceBundleLoader(
            new CacheResourceBundleLoader(
                new ClassResourceBundleLoader(
                    "content.Language",
                    ResourceBundleLoaderComponent.class.getClassLoader())),
            resourceBundleLoader);
    }

The `@Reference` annotation tells @product@'s OSGi framework to pass the target
module's resource bundler loader as the parameter. The target matches a resource
bundle loader component for module `com.liferay.blogs.web` and whose component
name isn't
`com.liferay.blade.samples.hook.resourcebundle.ResourceBundleLoaderComponent`.

The method creates a resource bundle loader that aggregates this module's
resource bundle loader and the target module's resource bundle loader. The
resource bundle of the first loader is prioritized ahead of resource bundles of
those that follow it. Therefore, this class's resource bundle loader, and its
resource bundle (i.e., its language keys), take precedence. This module's custom
language keys are applied to the target module.   

If you use the example `setResourceBundleLoader` method in your resource bundle
loader class, make sure to replace the following `[values]`:

In `@Reference`:
-   `bundle.symbolic.name=[target module's bundle symbolic name]`
-   `component.name=[your resource bundle loader class's fully qualified name]` 

In the method body:
-   Replace `ResourceBundleLoaderComponent` &rarr; `[your resource bundle loader class]` 

Resource bundle loader components have these class imports.

    import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
    import com.liferay.portal.kernel.util.CacheResourceBundleLoader;
    import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
    import com.liferay.portal.kernel.util.ResourceBundleLoader;

    import java.util.ResourceBundle;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

The following artifact's packages contain the classes imported above.

 Group | Artifact |
 :------ | :------ |
`com.liferay.portal` | `com.liferay.portal.kernel` |
 `org.osgi` | `org.osgi.service.component.annotations` |

Now you can modify the language keys of modules in Liferay's OSGi runtime.
Remember, language keys you want to override might actually be in Liferay's
core. You can find out how to override those language keys in
[Modifying Liferay's Language Keys](#modifying-liferays-language-keys).

## Related Topics [](id=related-topics)

[Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-core-language-key-hooks)

[Internationalization](internationalization)
