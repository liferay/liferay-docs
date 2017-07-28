# Overriding Language Keys [](id=overriding-language-keys)

@product@ Core and portlet module `language*.properties` files implement site
internationalization. They're fully customizable, too. This tutorial
demonstrates this in the following topics:

-   [Modifying Liferay's Language Keys](#modifying-liferays-language-keys) 
-   [Overriding a Portlet Module's Language Keys](#overriding-a-modules-language-keys)

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
applications or another module whose source code you don't control? The process
for overriding a module's language keys is different from
[the process of overriding Liferay's core language properties](#modifying-liferays-language-keys),
which are located in Liferay's `portal-impl.jar`.

    add-blog-entry=Add Blog Entry

![Figure 3: You can override the language keys of a module deployed to Liferay's OSGi runtime.](../../images/standard-add-blogs-entry.png)

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

### Implementing a Resource Bundle Loader [](id=implementing-a-resource-bundle-loader)

Create an implementation of
[`com.liferay.portal.kernel.util.ResourceBundleLoader`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html) and override its
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

### Registering the Service [](id=registering-the-service)

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

### Providing Language Keys [](id=providing-language-keys)

Create properly named [language properties files](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application#what-are-language-keys) for each locale whose keys
you want to override. These should go in `src/content`, and are the resource
bundles that are loaded by the resource bundle loader you are implementing.

For example, create a `Language_en.properties` file and override English
language keys.

    add-blog-entry=Add a blog entry.

![Figure 4: The language keys in your resource bundle loader module will replace those of the target module.](../../images/localized-add-blog-entry.png)

Now you can modify the language keys of modules in Liferay's OSGi runtime.
Remember, language keys you want to override might actually be in Liferay's
core. You can find out how to override those language keys in
[Modifying Liferay's Language Keys](#modifying-liferays-language-keys).

## Related Topics [](id=related-topics)

[Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-core-language-key-hooks)

[Internationalization](internationalization)
