# Overriding a Module's Language Keys [](id=overriding-a-modules-language-keys)

What do you do if the language keys you want to modify are in one of Liferay's
applications or another module whose source code you don't control? Since module
language keys are in the respective module, the process for overriding a
module's language keys is different from
[the process of overriding Liferay's language keys](/develop/tutorials/-/knowledge_base/7-1/overriding-liferays-language-keys). 

Here is the process:

1.  [Find the module and its metadata and language keys](#find-the-module-and-its-metadata-and-language-keys)
2.  [Write your custom language key values](#providing-language-keys) 
3.  [Implement a Resource Bundle Loader](#implementing-a-resource-bundle-loader)

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

Next you'll create a resource bundle loader component to apply the language keys
to the target module. 

## Implement a Resource Bundle Loader [](id=implementing-a-resource-bundle-loader)

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
[`com.liferay.portal.kernel.util.ResourceBundleLoader`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html)
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
    
The `loadResourceBundle` method returns a resource bundle based on the locale. 

    @Override
    public ResourceBundle loadResourceBundle(Locale locale) {
        return _resourceBundleLoader.loadResourceBundle(locale);
    }

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
[depend on packages from the following artifacts](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)
to provide the classes imported above.

 Group | Artifact |
 :------ | :------ |
`com.liferay.portal` | `com.liferay.portal.kernel` |
 `org.osgi` | `org.osgi.service.component.annotations` | 

 **Important**: If your module
 [uses language keys from another module](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application#using-a-language-module)
 and
 [overrides any of that other module's keys](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application#using-other-resource-bundles-in-addition-to-your-own),
 make sure to use OSGi headers to specify the capabilities your module requires
 and provides. This lets you prioritize resource bundles from the modules. 

 To see your language key overrides in action,
 [deploy your module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module)
 and visit a portlet that uses the module's language keys. 

Now you can modify the language keys of modules in Liferay's OSGi runtime.
Remember, language keys you want to override might actually be in Liferay's
core. You can
[override Liferay's language keys](/develop/tutorials/-/knowledge_base/7-1/overriding-liferays-language-keys)
too.

## Related Topics [](id=related-topics)

[Resource Bundle Override Sample Project](/develop/reference/-/knowledge_base/7-1/resource-bundle-override)

[Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-1/upgrading-core-language-key-hooks)

[Internationalization](/develop/tutorials/-/knowledge_base/7-1/internationalization)
