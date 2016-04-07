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
[file](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)
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
`src/main/resources/content` folder in that module, and place all of your
`Language_xx.properties` there.

After that, make sure any portlet components (the `@Component` annotation
in your `-Portlet` classes) in the module include this property:

    "javax.portlet.resource-bundle=content.Language"

Providing translated language properties files and specifying the
`javax.portlet.resource-bundle` property in your portlet component is all you
need to do to have your language keys translated. Then, when the locale is
changed in Liferay, your application's language keys will be automatically
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
values for the same key, and you don't want to maintain language keys in multiple
places. In this case, you need to go even crazier with modularity and create a
new module, which we'll call a language module.

In the root project directory (the one that holds your service, API, and web
modules), create a new `my-application-lang` module.

Inside the module, you need a `bnd.bnd` file, a `build.gradle` file, and a
`src/main/resource/content` directory with language properties files.

    my-application-lang/
        bnd.bnd
        build.gradle
        src/
            main/
                resources/
                    content/
                        Language.properties
                        Language_ar.properties
                        Language_bg.properties
                        ...


In the manifest file (usually generated from a `bnd.bnd` file in Liferay
applications) you need a `Bundle-Name`, `Bundle-SymbolicName`, and a
`Bundle-Version`. A `bnd.bnd` file for a language module might look like this:

    Bundle-Name: My Application Lang
    Bundle-SymbolicName: com.liferay.docs.myapplication.lang
    Bundle-Version: 2.0.1

The `build.gradle` file can be left blank. There are no dependencies for this
module.

Next, create a `src/main/resources/content` directory in the language module.
Put your language properties files here.

    application=My Application
    add-entity=Add Entity

Create any translations you want, adding the locale for the translation into the
file name. A `Language_es.properties` file might look like this:

    my-app-title=Mi Aplicación
    add-entity=Añadir Entity

Once you've done that, you're done in the language module. Now when you use the
language key, it will automatically be translated depending on the user's locale.

## Put the Language Module in a JAR [](id=put-the-language-module-in-a-jar)

How do you get the language module's language properties files into the jar
containing your portlet components and views? 

![Figure 2: You need to include the language properties files in the JAR of the module that contains your portlet components and your JSPs.](../../images/web-jar-localized.png)
<!-- I worked with this open clip art:
https://openclipart.org/detail/177364/empty-glass-jar -->

To jar up your language files, in each portlet component, you need this
property:

    "javax.portlet.resource-bundle=content.Language"

Additionally, at compile time (and JAR building time) your Web module needs to
be able to find the language module. The specifics of this will vary depending
on how you're building your project. For a Liferay Workspace project, your web
module's should have this in their `build.gradle` files:

    compile project(':modules:myapplication:my-application-lang')

Now you can add language properties files to your Liferay development toolbox,
to provide translation of your application's user interface messages. But do you
need to translate every single key yourself? What about those keys that you
share with Liferay's core? 

## Using Liferay's Language Properties [](id=using-liferays-language-properties)

If you have Liferay's source code, you can check out
Liferay's core language properties by looking in the `portal-impl/src/main/content`
folder. Otherwise, you can look in the `portal-impl.jar` that's in your Liferay
bundle.

    liferay-portal/portal-impl/src/content/Language_xx.properties

    [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

These keys are available at runtime, so just use any of Liferay's default keys
in your user interface code, and they'll be automagically swapped out for the
appropriately translated value. Using Liferay's keys where possible saves you
time and ensures that your application follows Liferay's UI conventions.

If you want to automatically generate language files for each locale Liferay
supports, or even configure your application to generate translations
automatically using the Microsoft Translator API, check out the article on that
(in progress).
