# Modifying Liferay's Language Keys

Using language files with keys allows you the ultimate flexibility in
translating your application's user interface messages. But you can also modify
the language keys used by Liferay's core itself. This lets you change not only
your own application, but any messages in Liferay.

    publish=Publish

So how do you modify Liferay's keys?

## Modifying Liferay's Core Language Properties

You can access the core default language properties from Liferay's source code
or your Liferay bundle.

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

### Creating a Resource Bundle

In your module, create a class that extends `java.util.ResourceBundle` and
register it as an OSGi service with the property `language.id` set to whatever
locale you'd like to override. Specify that you're providing an implementation
of `ResourceBundle`.

    @Component(
        property = { "language.id=en_US" }, 
        service = ResourceBundle.class
    )

+$$$

**Note:** As you might have guessed, to override the keys for more than one
locale, create multiple resource bundle implementations in your module, with
each one specifying a different `language.id` property. Make sure you include a
`Language_xx.properties` file for each locale.

$$$

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

### Creating a Language Properties File

Next just provide the language properties file that corresponds with the locale
whose language properties you are overriding (for example,
`Language_en.properties`). Put it in your module's
`src/content` folder, and override any keys and values you'd like.

    publish=Publish Override

![Figure 1: To override core language keys, a simple module with a component and
a properties file is created for each locale.](../images/localized-publish.png)

That's all there is to overriding a core language key.

Much of the functionality and many of the applications in Liferay are now
separated out into their own modules. You might need to modify the keys included
in one of these modules. The process is different than the one described above,
and will be covered in a [separate tutorial](/develop/tutorials/-/knowledge_base/7-0/modifying-module-language-keys).
