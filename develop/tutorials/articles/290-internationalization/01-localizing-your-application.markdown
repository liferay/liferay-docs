# Localizing Your Application [](id=localizing-your-application)

If you're writing a Liferay Application, you're probably a genius who is also
really cool. Which means your application will be used throughout the entire
world. At least, it will if the messages that appear to its users can be
translated into their language. Thankfully, Liferay makes it easy to support
translation of your application's language keys.

+$$$

**Note:** Even if you don't think your application needs to be translated into
multiple languages, use the localization pattern presented here for any messages
your user interface displays. It's much easier to change the messages by
updating a language properties file than by finding every instance of a message
and replacing it in your JSPs and Java classes.

$$$

You just need to create a default language properties file
(`Language.properties`) and one for each translation you'd like to support (for
example, `Language_fr.properties` for your French translation), and put them in
the correct location in your application. In your file names, use the two character
locale code that corresponds to the language translation you're providing (for
example, use the `es` in your Spanish translation file name
`Language_es.properties`). 

<!-- Discuss adding a new locale? -->

Application localization topics:

-   [What are Language Keys?](#what-are-language-keys)
-   [What Locales are Available By Default?](#what-locales-are-available-by-default)
-   [Where do I Put Language Files?](#where-do-i-put-language-files)
-   [Creating a Language Module](#creating-a-language-module)
-   [Using a Language Module](#using-a-language-module)
-   [Using Other Resource Bundles in Addition to Your Own](#using-other-resource-bundles-in-addition-to-your-own)
-   [Using Liferay's Language Properties](#using-liferays-language-properties)

## What are Language Keys? [](id=what-are-language-keys)

Each language property file holds key/value pairs. The key is the same in all
the language property files, while the value is translated in each file. You
specify the key in your user interface code, and the appropriately translated
message is returned automatically for your users, depending on the locale being
used in Liferay. If you have Liferay running locally, append the URL with a
supported locale to see the message translations for that locale (for example,
enter `localhost:8080/es`).

![Figure 1: Append the locale to your running Liferay's URL and see Liferay's translation power in action.](../../images/locale-message-spain.png)

Language keys are just keys to use in place of a hard coded, fully
translated String value in your user interface code. For example, you can use a
language key in your JSP via a
[`liferay-ui:message` tag](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/message.html). 

You can specify a language key as value of the tag's `key` attribute": 

    <liferay-ui:message key="howdy-partner" />

That way you can provide a translation of the key in a default language
properties file (`Language.properties`):

    howdy-partner=Howdy, Partner!


Language keys give you flexibility to localize content. You can add language
properties files that provide translations for your application's keys. Use a
key in your UI code, then set the translated value in your language file.
Just make sure to specify the locale code in Liferay's portal property
`locales`, if it doesn't specify it already. The next section explains the
`locales` property. 

If no locale is specified, your `Language.properties` file's values appear. If a
locale is specified, Liferay searches for a file that corresponds to the locale.
For example,  if the Spanish locale is specified, Liferay looks for a
`Language_es.properties` file to use its properties. If there is no
`Language_es.properties` file, the default language properties (from the
`Language.properties` file) are used.

@product@ uses language properties files along with standard Java resource
bundles as a convenience for supporting internationalization. A Java resource
bundle comprises a `ResourceBundle` object that's associated with key/value
pairs of localized content. The keys are non-localized tokens associated to
localized values. Liferay's `liferay-ui:message` tag is one of the easiest ways
to show localized text in your user interface. The `liferay-ui:message` tag and
other Liferay tags and classes use Java resource bundles behind the scenes. 

## What Locales are Available By Default? [](id=what-locales-are-available-by-default)

There are a bunch of locales available by default in Liferay. The
[`portal.properties` file](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)
lists them.

    locales=ar_SA,eu_ES,bg_BG,ca_AD,ca_ES,zh_CN,zh_TW,hr_HR,cs_CZ,da_DK,nl_NL,
        nl_BE,en_US,en_GB,en_AU,et_EE,fi_FI,fr_FR,fr_CA,gl_ES,de_DE,el_GR,
        iw_IL,hi_IN,hu_HU,in_ID,it_IT,ja_JP,ko_KR,lo_LA,lt_LT,nb_NO,fa_IR,
        pl_PL,pt_BR,pt_PT,ro_RO,ru_RU,sr_RS,sr_RS_latin,sl_SI,sk_SK,es_ES,
        sv_SE,tr_TR,uk_UA,vi_VN

Translations for these locales belong in a language file whose name includes the
locale code. For example, a Spanish translation belongs in a file called
`Langauge_es.properties`. 

## Where do I Put Language Files? [](id=where-do-i-put-language-files)

In an application with only one module that holds all your application's views
(for example, all its JSPs) and portlet components, just create a
`src/main/resources/content` folder in that module, and place your
`Language.properties` and `Language_xx.properties` files there.

After that, make sure all of the module's portlet components (the `@Component`
annotation in your `-Portlet` classes) include this property:

    "javax.portlet.resource-bundle=content.Language"

Providing translated language properties files and specifying the
`javax.portlet.resource-bundle` property in your portlet component is all
Liferay requires for serving your translated content. @product@ automatically
shows the translated language keys to users based on their locale. 

In a more complicated, well-modularized application, you might have language
keys spread over multiple modules. Moreover, you might use the same language
keys in them. Thankfully you don't need to maintain language properties files in each
module.

## Creating a Language Module [](id=creating-a-language-module)

If you're crazy about modularity (and you should be), you might have an
application with multiple modules that provide the view layer. These modules are
often called web modules.

    my-application/
        my-application-web/
        my-admin-application-web/
        my-application-content-web/
        my-application-api/
        my-application-service/

Each of these modules can have language keys and translations to maintain, and
there will probably be duplicate keys. You don't want to end up with different
values for the same key, and you don't want to maintain language keys in
multiple places. In this case, you need to go even crazier with modularity and
create a new module, which we'll call a language module.

In the root project directory (the one that holds your service, API, and web
modules), [create a new
module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module)
to hold your app's language keys. For example, here's the folder structure of a
language module called `my-application-lang`.

    my-application-lang/
        bnd.bnd
        src/
            main/
                resources/
                    content/
                        Language.properties
                        Language_ar.properties
                        Language_bg.properties
                        ...

In the language module, create a `src/main/resources/content` folder. Put your
language properties files here. A `Language.properties` file might look like
this: 

    application=My Application
    add-entity=Add Entity

Create any translations you want, adding the translation locale ID to the
language file name. File `Language_es.properties` might look like this: 

    my-app-title=Mi Aplicación
    add-entity=Añadir Entity

On building the language module, Liferay's
`ResourceBundleLoaderAnalyzerPlugin` detects the `content/Language.properties`
file and adds a resource bundle
[*capability*](http://blog.osgi.org/2015/12/using-requirements-and-capabilities.html)
to the module. Remember, resource bundles are objects that contain localized
data. A capability is a contract a module declares to Liferay's OSGi
framework. Capabilities let you associate services with modules that provide
them. In this case, Liferay registers a
[ResourceBundleLoader](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html)
service for the resource bundle capability. 

Next, you'll configure a web module to use the language module's resource
bundle. 

## Using a Language Module [](id=using-a-language-module)

A module can use a language module's resource bundle loader by declaring it as a
required capability and declaring the language module's resource bundle as its
own. 

Follow these steps to configure a web module to use a language module resource
bundle.

1.  Open the web module's `bnd.bnd` file and add a `Require-Capability` header
    that filters on the language module's resource bundle capability. For
    example, if the language module's symbolic name is
    `com.liferay.docs.l10n.myapp.lang`, you'd specify the requirement like this: 

        Require-Capability: liferay.resource.bundle;filter:="(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang)"

2.  In the `bnd.bnd`, add a `Provide-Capability` header that adds the language
    module's resource bundle as this module's own resource bundle:

        Provide-Capability: liferay.resource.bundle;resource.bundle.aggregate:String="(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang)";resource.bundle.base.name="content.Language"

In this case, the web module solely uses the language module's resource bundle. 

## Using Other Resource Bundles in Addition to Your Own [](id=using-other-resource-bundles-in-addition-to-your-own)

Aggregating resource bundles comes into play when you want to use your module's
resource bundle *in addition to* other modules' resource bundles. 

For example, you might want to compliment a language module's resource bundles
with new module-specific keys or override a language module's key values with
your own. This requires aggregating both modules' resource bundles and
prioritizing your module's resource bundle higher than the language module's.
The following example demonstrates this. 

For example, a web module called `com.liferay.docs.l10n.myapp.admin.web` uses
keys from language module `com.liferay.docs.l10n.myapp.lang`, but overrides some
of the key values. The web module's `Provide-Capability` and `Web-ContextPath`
OSGi headers accomplish this.

    Provide-Capability:\
    liferay.resource.bundle;resource.bundle.base.name:String="(bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web)";resource.bundle.base.name="content.Language",\
    liferay.resource.bundle;resource.bundle.aggregate:String="(&(bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web)(!(aggregate=true))),(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang)";bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web;resource.bundle.base.name="content.Language";service.ranking:Long="1";aggregate=true;\
    servlet.context.name=my-admin-application-web

    Web-ContextPath:/my-admin-application-web

Each line is explained:

1.  The first `Provide-Capability` line declares the web module's resource
    bundle. This module's bundle symbolic name is
    `com.liferay.docs.l10n.myapp.admin.web`. 

        liferay.resource.bundle;resource.bundle.base.name:String="(bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web)";resource.bundle.base.name="content.Language",\

2.  The second `Provide-Capability` line aggregates the web module resource
    bundle and the language module resource bundle, prioritizing the web module
    resource bundle over the language module resource bundle. The last part of this capability declares the module's servlet context name. 

        liferay.resource.bundle;resource.bundle.aggregate:String="(&(bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web)(!(aggregate=true))),(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang)";bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web;resource.bundle.base.name="content.Language";service.ranking:Long="1";aggregate=true;\
        servlet.context.name=my-admin-application-web

3.  The `Web-ContextPath` header declares the web module's web context path.
    Liferay uses the web context path and the servlet context declared in
    the `Provide-Capability` header to make the aggregated resource bundle
    available to the web module's JSPs automatically.

        Web-ContextPath:/my-admin-application-web

To aggregate web module keys and language module keys, follow the pattern
demonstrated by the example above. The example language module and web modules
can be downloaded
[here](https://dev.liferay.com/documents/10184/656312/l10n-my-application.zip). 

Now you can add language properties files to your Liferay development toolbox,
to provide translation of your application's user interface messages. But you
might not need to translate all of your keys yourself: @product@'s core might
already provide translations for them. 

## Using Liferay's Language Properties [](id=using-liferays-language-properties)

If you have @product-ver@'s source code, you can check out Liferay's core
language properties by looking in the `portal-impl/src/main/content` folder.
Otherwise, you can look in the `portal-impl.jar` that's in your @product@
bundle.

    liferay-portal/portal-impl/src/content/Language_xx.properties

    [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

These properties are available at runtime, so when you use any of Liferay's
default keys in your user interface code, they're automagically swapped out for
the appropriately translated value. Using Liferay's keys where possible saves
you time and ensures that your application follows Liferay's UI conventions.

If you want to generate language files for each supported locale automatically,
or to configure your application to generate translations automatically using
the Microsoft Translator API, check out the tutorial
[Automatically Generating Language Files](/develop/tutorials/-/knowledge_base/7-1/automatically-generating-language-files).
