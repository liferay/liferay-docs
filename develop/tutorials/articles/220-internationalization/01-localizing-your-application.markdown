# Localizing Your Application [](id=localizing-your-application)

If you're writing a Liferay Application, you're probably a genius who is also
really cool. Which means your application is going to be used throughout the
entire world. At least, it will if the messages that appear to its users can be
translated into their language. Thankfully, Liferay makes it easy to support
translation of your application's language keys.

+$$$

**Note:** Even if you don't think your application needs to be translated into
multiple languages, use the localization pattern presented here for any messages
displayed in your user interface. It's much easier to change the messages by
updating a language properties file than by finding every instance of a message
and replacing it in your JSPs and Java classes.

$$$

You just need to create a default language properties file
(`Language.properties`) and one for each translation you'd like to support (for
example, `Language_fr.properties` for your French translation), and put them in
the correct location in your application. Use the two letter locale that
corresponds to the language you want to translate in your file names (for example,
`Language_es.properties` provides a Spanish translation for each key).

<!-- Discuss adding a new locale? -->

## What are Language Keys? [](id=what-are-language-keys)

Each language property file holds key/value pairs. The key is the same in all
the language property files, while the value is translated in each file. You
specify the key in your user interface code, and the appropriately translated
message is returned automatically for your users, depending on the locale being
used in Liferay. If you have Liferay running locally, append the URL with a
supported locale to see how Liferay's language keys are translated (for example,
enter `localhost:8080/es`).

![Figure 1: Append the locale to your running Liferay's URL and see Liferay's translation power in action.](../../images/locale-message-spain.png)

Language keys are just keys you'll use in place of a hard coded, fully
translated String value in your user interface code. For example, you can use a
language key in your JSP via a `<liferay-ui:message />` tag. 

The tag might be set up like this if you're not considering the need to
translate your application's messages:

    <liferay-ui:message key="Howdy, Partner!" />

In that case you'll get a properly capitalized and punctuated message in your
application.

Alternatively, you can specify a simple key instead of the final value:

    <liferay-ui:message key="howdy-partner" />

That way you can provide a translation of the key in a default language
properties file (`Language.properties`):

    howdy-partner=Howdy, Partner!

You'll get the same output in your application with either method above, but you
have the flexibility to add additional language properties files that provide
translations for your application's keys if you use the language properties
approach. Use a key in your UI code, then provide the value (or translation) in
your language properties file. You just need to make sure there's a locale that
corresponds to your translation.

The values from your default `Language.properties` file will appear if no locale
is specified. If a locale is specified, Liferay will try to find a file that
corresponds to the locale. For example, if a Spanish translation is sought, a
`Language_es.properties` file must be present to provide the proper values. If
it isn't, the default language properties (from the `Language.properties` file)
will be used.

## What Locales are Available By Default? [](id=what-locales-are-available-by-default)

There are a bunch of locales available by default in Liferay. Look in the
`portal.properties` file
[file](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)
to find them.

    locales=ar_SA,eu_ES,bg_BG,ca_AD,ca_ES,zh_CN,zh_TW,hr_HR,cs_CZ,da_DK,nl_NL,
        nl_BE,en_US,en_GB,en_AU,et_EE,fi_FI,fr_FR,fr_CA,gl_ES,de_DE,el_GR,
        iw_IL,hi_IN,hu_HU,in_ID,it_IT,ja_JP,ko_KR,lo_LA,lt_LT,nb_NO,fa_IR,
        pl_PL,pt_BR,pt_PT,ro_RO,ru_RU,sr_RS,sr_RS_latin,sl_SI,sk_SK,es_ES,
        sv_SE,tr_TR,uk_UA,vi_VN

To provide a translation for one of these locales, specify the locale in the
file name where the translated keys will be (for example,
`Langauge_es.properties` holds the Spanish translation).

## Where do I Put Language Files? [](id=where-do-i-put-language-files)

In an application with only one module that holds all your application's views
(for example, all its JSPs) and portlet components, just create a
`src/main/resources/content` folder in that module, and place your
`Language.properties` and `Language_xx.properties` files there.

After that, make sure any portlet components (the `@Component` annotation
in your `-Portlet` classes) in the module include this property:

    "javax.portlet.resource-bundle=content.Language"

Providing translated language properties files and specifying the
`javax.portlet.resource-bundle` property in your portlet component is all you
need to do to have your language keys translated. Then, when the locale is
changed in @product@, your application's language keys will be automatically
translated.

In a more complicated, well-modularized application, you might have language
keys spread over multiple modules providing portlet components and JSP files.
Moreover, there might be a fair number of duplicated language keys between the
modules. Thankfully you don't need to maintain language properties files in each
module.

## Creating a Language Module [](id=creating-a-language-module)

If you're crazy about modularity (and you should be), you might have an
application with multiple modules that provide the view layer. These modules are
often called Web modules.

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
module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#creating-a-module)
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

Create any translations you want, adding the translation locale ID to the language file name. File `Language_es.properties` might look like this: 

    my-app-title=Mi Aplicación
    add-entity=Añadir Entity

On
[deploying](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module)
the language module, @product@ detects the `content/Language.properties` file,
adds a resource bundle
[*capability*](http://blog.osgi.org/2015/12/using-requirements-and-capabilities.html)
to the module, and registers a
[ResourceBundleLoader](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html)
service for that capability. A capability is a contract a module declares to
@product@'s OSGi framework. It lets you associate services with modules that
provide them. `ResourceBundleLoaderAnalyzerPlugin` is the plugin that performs
the detection. 

Next, you'll configure a web module to use the language module's resource
bundle. 

## Using a Language Module

A module can use a language module's resource bundle loader by declaring it as a
required capability and declaring the language module's resource bundle as its
own. 

Here's how you configure a web module to use a language module's resource
bundle.

1.  Open the web module's `bnd.bnd` file and add a `Require-Capability` header
that filters on the language module's resource bundle capability. For example,
if the language module's symbolic name is `com.liferay.docs.l10n.myapp.lang`,
you'd specify the requirement like this: 

        Require-Capability: liferay.resource.bundle;filter:="(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang)"

2.  In the `bnd.bnd`, add a `Provide-Capability` header that adds the language
module's resource bundle as this module's:

        Provide-Capability: liferay.resource.bundle;resource.bundle.aggregate:String="(bundle.symbolic.name=com.liferay.my.editor.lang)";resource.bundle.base.name="content.Language"

It might seem a bit strange that the resource bundle aggregate has only one
bundle. Grouping together multiple resource bundles comes into play when you
want to include your module's own resource bundle in addition to a language
module's. 

For example, you might want to use language keys specific to your module as a
compliment the language module's keys and override some of the language module's
keys with your own. This requires aggregating the resource bundles and
prioritizing your module's resource bundle higher than the language module's.
You'll learn this best by way of example.

A web module called `com.liferay.docs.l10n.myapp.admin.web` uses the language
module `com.liferay.docs.l10n.myapp.lang`'s language keys, but overrides some of
them. The web module's `Provide-Capability` and `Web-ContextPath` OSGi headers
accomplish this.

    Provide-Capability:\
    liferay.resource.bundle;resource.bundle.base.name:String="(bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web)";resource.bundle.base.name="content.Language",\
    liferay.resource.bundle;resource.bundle.aggregate:String="(&(bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web)(!(aggregate=true))),(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang)";bundle.symbolic.name=com.liferay.docs.l10n.myapp.admin.web;resource.bundle.base.name="content.Language";service.ranking:Long="1";aggregate=true;\
    servlet.context.name=my-admin-application-web
    
    Web-ContextPath:/my-admin-application-web

Each line is explained:

1.  `Provide-Capability Line 1`: declares the web module's resource bundle. This
module's bundle symbolic name is `com.liferay.docs.l10n.myapp.admin.web`.
2.  `Provide-Capability Line 2`: specifies an aggregate resource bundle to
include one from the web module and another from language module. The web
module's ranking `service.ranking:Long="1"` prioritizes its resource bundle over
the language module's.
3.  `Provide-Capability Line 3`: provides the web module's servlet context name
(see the next line description).
4.  `Web-ContextPath Line`: @product@ uses the servlet context name and web
context path to make the aggregated resource bundle available to the web
module's JSPs automatically. 

To aggregate a language module's keys with your own, follow this pattern. The
example language module and web modules can be downloaded in [this ZIP
file](https://dev.liferay.com/documents/10184/656312/l10n-my-application.zip/3bf58646-95ba-4031-bd1a-ce52cc6152f3). 

Now you can add language properties files to your Liferay development toolbox,
to provide translation of your application's user interface messages. But do you
need to translate every single key yourself? What about those keys that you
share with Liferay's core? 

Just as a side note: this is one of the ways you can use the infrastructure, but
it is not the only one. All this is needed for the tooling to be able to find
the appropiate `ResourceBundleLoader` without giving them any context (back in
the days of the monolith everything was stored in the same place). While all (or
most) of our modules are released using this I guess we can assume this is the
default but any developer can also specify the `ResourceBundle` or
`ResourceBundleLoader` that a JSP will have to use. In order to accomplish this
the user only needs to get a reference to the desired
`ResourceBundle/ResourceBundleLoader` and use standard JSP tooling for handling
language keys. 

Other option is to set a reference to a `ResourceBundleLoader` in the request
with the attribute name `WebKeys.RESOURCE_BUNDLE_LOADER`. From that point on
that would be the chosen resource for the JSP tooling. Also, the user can also 
set a specific `ResourceBundle` in the JSP `PageContext` with the key
`resourceBundle` and it will be used. 

Note that the most unambiguous way for specifying the messages is by using the
JSP standard tooling that allows to specify the `ResourceBundle` wanted for a
translation. 

Since most of the modules publish their resources in OSGi with the languge
extender capabilities the users can get references to them just by asking for
them as `@Reference` or by any other means. 

## Using Liferay's Language Properties [](id=using-liferays-language-properties)

If you have Liferay's source code, you can check out Liferay's core language
properties by looking in the `portal-impl/src/main/content` folder. Otherwise,
you can look in the `portal-impl.jar` that's in your Liferay bundle.

    liferay-portal/portal-impl/src/content/Language_xx.properties

    [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

These keys are available at runtime, so just use any of Liferay's default keys
in your user interface code, and they'll be automagically swapped out for the
appropriately translated value. Using Liferay's keys where possible saves you
time and ensures that your application follows Liferay's UI conventions.

If you want to automatically generate language files for each locale Liferay
supports, or even configure your application to generate translations
automatically using the Microsoft Translator API, check out the tutorial
[Automatically Generating Language Files](/develop/tutorials/-/knowledge_base/7-0/automatically-generating-language-files).
