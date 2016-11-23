# Overriding a Module's Language Keys [](id=overriding-a-modules-language-keys)

What do you do if the language keys you want to modify are in one of Liferay's
applications or another module whose source code you don't control? The process
for overriding a module's language keys is different from [the process of overriding Liferay's core language properties](/develop/tutorials/-/knowledge_base/7-0/modifying-liferays-language-keys), which are located in Liferay's `portal-impl.jar`.

    add-blog-entry=Add Blog Entry

![Figure 1: You can override the language keys of a module deployed to Liferay's OSGi runtime.](../../images/standard-add-blogs-entry.png)

You can find the language keys for one of Liferay's applications in the source
code or in the JAR file that's part of your Liferay bundle installation.

In the source code, the `Language_xx.properties` files will usually be located
in the `src/main/resources/content` folder of the `.web` module. 

    liferay-portal/modules/apps/collaboration/blogs/blogs-web/src/main/resources/content

In your bundle, the `Language_xx.properties` files will be located in the
`content` folder of the module's JAR.

    [Liferay Home]/osgi/modules/com.liferay.blogs.web.jar

You can override the language properties of any module by creating a module with
a language file and one Java class.

## Implementing a Resource Bundle Loader [](id=implementing-a-resource-bundle-loader)

Create an implementation of
[`com.liferay.portal.kernel.util.ResourceBundleLoader`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html) and override its
`loadResourceBundle` method.

        public class ResourceBundleLoaderComponent implements ResourceBundleLoader {

            @Override
            public ResourceBundle loadResourceBundle(String languageId) {
                return _resourceBundleLoader.loadResourceBundle(languageId);
            }


The rest of the code you need should set the resource bundle loader into
which the resource bundles are loaded as an `AggregateResourceBundleLoader`.
This implementation of `ResourceBundleLoader` allows multiple resource bundles
to be loaded for the target module. 

            @Reference(target = "(bundle.symbolic.name=com.liferay.blogs.web)")
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

Take note of the `new ClassResourceBundleLoader` parameter in the instantiation
of the aggregate resource bundle loader. You need to pass in `content.Language`,
the classloader for your resource bundle loader, and the resource bundle loader
from the method's parameter to instantiate the
`ClassResourceBundleLoader` class.

## Registering the Service [](id=registering-the-service)

Register the resource bundle loader in the OSGi runtime with three properties:

    -  The symbolic name of the target module (the module whose keys you're
       overriding).
        
            bundle.symbolic.name=com.liferay.blogs.web

    -  The resource bundle base name that points to your language files.

            resource.bundle.base.name=content.Language

    -  The servlet context name of the target module.

            servlet.context.name=blogs-web

        @Component(
            immediate = true,
            property = {
                "bundle.symbolic.name=com.liferay.blogs.web",
                "resource.bundle.base.name=content.Language",
                "servlet.context.name=blogs-web"
            }
        )

How do you find the bundle symbolic name and the servlet context name for the
module you want to override? You can find both by accessing the [Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). If
you knew you were looking for the blogs `.web` module, your Gogo commands and
output might look like this:

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

The `Web-ContextPath` value, following the `/`, is the value to enter for the
servlet context name property (`blogs-web`).

The only thing you're missing now is the language keys you want to override.

## Providing Language Keys [](id=providing-language-keys)

Create properly named [language properties files](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application#what-are-language-keys) for each locale whose keys
you want to override. These should go in `src/content`, and are the resource
bundles that are loaded by the resource bundle loader you are implementing.

For example, create a `Language_en.properties` file and override English
language keys.

    add-blog-entry=Add a blog entry.

![Figure 2: The language keys in your resource bundle loader module will replace those of the target module.](../../images/localized-add-blog-entry.png)

Now you can modify the language keys of modules in Liferay's OSGi runtime.
Remember, language keys you want to override might actually be in Liferay's
core. Find out how to override those language keys in a [separate tutorial](/develop/tutorials/-/knowledge_base/7-0/modifying-core-language-keys).
