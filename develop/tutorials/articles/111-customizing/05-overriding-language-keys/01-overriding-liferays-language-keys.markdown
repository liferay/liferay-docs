# Overriding Liferay's Language Keys [](id=overriding-liferays-language-keys)

Using language files gives you the ultimate flexibility in
[translating your application's user interface messages](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application). 
But you can also override Liferay's language keys to affect @product@ and other
applications (including your own) that use those keys. Here are the steps for
overriding Liferay's language keys:

1.  [Determine the language keys to override](#determine-the-language-keys-to-override)
2.  [Override the keys in a new language properties file](#override-the-keys-in-a-new-language-properties-file)
3.  [Create a Resource Bundle service component](#create-a-resource-bundle-service-component)

+$$$

**Note:** Many applications that were once part of Liferay Portal 6.2 are now
modularized. Such an application's language keys might have been moved out of
Liferay's language properties files and into one of the application's modules.
The process for
[overriding a module's language keys](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-language-keys)
is different from the process for overriding Liferay's language keys. 

$$$

## Determine the language keys to override [](id=determine-the-language-keys-to-override)

So how do you find Liferay's language keys? They're in Liferay's `Language.properties` file found in Liferay's source code or your Liferay bundle.

-   From Liferay's source:

    `/portal-impl/src/content/Language.properties`

-   From a Liferay bundle:

    `portal-impl.jar`

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

There are also many simple keys you can override to update Liferay's messages
and labels.

    ##
    ## Category titles
    ##

    category.admin=Admin
    category.alfresco=Alfresco
    category.christianity=Christianity
    category.cms=Content Management
    ...

For example, Figure 1 shows a button that uses Liferay's `publish` language key.
    
    `publish=Publish`
    
![Figure 1: Messages displayed in Liferay's user interface can be customized.](../../../images/standard-publish.png)

## Override the keys in a new language properties file [](id=override-the-keys-in-a-new-language-properties-file)

Now that you know the keys to override, create a language properties file for
the locale you want (or the default `Language.properties` file) in your module's
`src/main/resources/content` folder. In your file, override the keys as you
like. For example, you could override the `publish` key.

    publish=Publish Override

Now that you've customized the language keys in your language file, you must
create a resource bundle service component to reference your language file. 

### Create a Resource Bundle service component [](id=create-a-resource-bundle-service-component)

In your module, create a class that extends `java.util.ResourceBundle` for the
locale you're overriding. Here's an example resource bundle class for the
`en_US` locale:

    @Component(
        property = { "language.id=en_US" }, 
        service = ResourceBundle.class
    )
    public class EnUsResourceBundle extends ResourceBundle {

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

The class's `_resourceBundle` field is assigned a `ResourceBundle`. The call to
`ResourceBundle.getBundle` needs two parameters. The `content.Language`
parameter is meant to point to the language file (so it answers the question,
"what resource bundle?"), and the second parameter is a `control` that sets the
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

Your resource bundle service component directs @product@ and modules that use
Liferay's language keys to use your module's language key overrides. 

**Important**: If your module
[uses language keys from another module](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application#using-a-language-module)
and
[overrides any of that other module's keys](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application#using-other-resource-bundles-in-addition-to-your-own),
make sure to use OSGi headers to specify the capabilities your module requires
and provides. This lets you prioritize resource bundles from the modules. 

To see your Liferay language key overrides in action,
[deploy your module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module)
and visit the portlets and pages that use the keys. 

![Figure 2: This button uses the overridden `publish` key.](../../../images/localized-publish.png)

That's all there is to overriding Liferay's language keys.

## Related Topics [](id=related-topics)

[Resource Bundle Override Sample Project](/develop/reference/-/knowledge_base/7-1/resource-bundle-override)

[Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-1/upgrading-core-language-key-hooks)

[Internationalization](/develop/tutorials/-/knowledge_base/7-1/internationalization)
