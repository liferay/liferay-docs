# Generating Language Properties File and Automated Translations [](id=generating-language-properties-file-and-automated-t-lp-6-2-develop-tutorial)

<!-- The beginning and ending test portlets can be found at the following:
Begin: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/prefs/begin/event-listing-portlet
End: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/lang-trans/end/event-listing-portlet
-->

In this tutorial, you'll learn how Liferay facilitates generating language key
files and translating the keys to languages you want to support. In order for a
user to see a message in his own locale, the message value must be specified in
a resource bundle file with a name ending in his locale's two character code.
For example, a resource bundle file named `Language_es.properties` containing a
message property with key `welcome` must be present with a Spanish translation
of the word "Welcome". Don't worry, the Plugins SDK provides a means for you to
get translations for your default resource bundle.

It's time to get started by doing the initial setup for the Bing Translator
service for translating the language properties. 

## Translating Languages Using the Bing Translator [](id=translating-languages-using-the-bing-translator-lp-6-2-develop-tutorial)

The Plugins SDK uses the Bing Translator service
[http://www.microsofttranslator.com/](http://www.microsofttranslator.com/) to
translate all of the resources in your `Language.properties` file to multiple
languages. It provides a base translation for you to start with. To create base
translations using the Bing Translator service, you'll need to do the following:

1. Sign up for an Azure Marketplace account and subscribe to the Microsoft 
   Translator. It's listed in the *Data* section of the Azure Marketplace.

2. Register your portlet in the Azure Marketplace. You can do this from the 
   *Developers* tab in *My Account*. The client ID needs to match the project
   name of the portlet. Accept the default client secret. Make sure to save the
   client ID and client secret for later use.

3. Create a `build.[username].properties` file in your Plugins
   SDK root directory, if you don't already have one. This 
   `build.[username].properties` file must reference a Liferay 
   bundle. If you have a Liferay Tomcat bundle, for example, your reference 
   should look like this:

        app.server.dir=[Liferay Home]/tomcat-7.0.42
        auto.deploy.dir=[Liferay Home]/deploy

    `[Liferay Home]` refers to your bundle's root directory.

4. Make sure that you have a `portal-ext.properties` file in your Liferay Home 
   directory. Create it if you do not. Edit the `portal-ext.properties` file, 
   adding the following two lines replaced with your values:

        microsoft.translator.client.id=[your-client-id]
        microsoft.translator.client.secret=[your-client-secret]

   Liferay copies the `portal-ext.properties` file from your Liferay Home
   directory to the `tomcat-[version]/webapps/ROOT/WEB-INF/classes` directory
   upon startup. So, you can either start Liferay to have it copy the file over
   or you can manually copy it over yourself. If you opt  
   for having Liferay automatically copy it on startup, make sure to check the 
   `tomcat-[version]/webapps/ROOT/WEB-INF/classes` directory for the 
   `portal-ext.properties` file afterward.

Next, you'll make the necessary changes to your portlet so that it can leverage 
the Bing Translator service.
   
## Implementing Translations in Your Portlet [](id=implementing-translations-in-your-portlet-lp-6-2-develop-tutorial)

Now that you've done the initial setup for the Bing Translator, you're ready to 
add support for translations to your portlet.

1. In your portlet's `docroot/WEB-INF/src/content/Language.properties` file, add
   your language keys. For example, you may enter a key like this one: 

        you-know-it-is-the-best=You know it is the best

    Translations are only generated for properties that have not been translated
    previously. 

2. Make use of your language keys in your JSPs. For example, you can specify
   language keys in `<liferay-ui:message>` tags, as demonstrated in the
   following JSP code:  

        Liferay - <liferay-ui:message key="you-know-it-is-the-best" />!

    Tags like the above `<liferay-ui:message>` tag will look up the value of the
    language key for the current locale and render it for the user.

    For example JSP code that references language keys, see
    [`view.jsp`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/plat-fws/lang-trans/end/event-listing-portlet/docroot/html/locationlisting/view.jsp).

3. Build the language keys, by either right-clicking on the
   `Language.properties` file &rarr; *Liferay* &rarr; *Build Languages* in
   Liferay IDE/Developer Studio or by running `ant build-lang` from your plugin
   project's root directory in your terminal. 

    When the build completes, you'll find the generated files with all of the
    translations in `Language_*.properties` files in the same folder as your
    `Language.properties` file. Note that many of the language properties files
    don't contain translations. This is because they are intentionally disabled.
    We decided to do this because many of the translations are incorrect or
    offensive. The issue is described
    [here](https://issues.liferay.com/browse/LPS-29246). 

4. Switch your portal's locale to the language for which you want to see the
   translated language properties. For example, you can switch to 
   Spanish by adding `/es` after `localhost:8080` 
   and refreshing the page. Notice how your portlet shows your translated
   language keys.

![Figure 1: Liferay automatically translates your portlet's language properties to different languages.](../../images/portlet-localization-generated-translation.PNG)

After you've generated an initial translation for a language property (i.e.,
key/value pair), the assumption is that you'll manually make any updates that
those translated values require. On subsequent builds of the language
properties, the language builder intentionally leaves existing language property
translation values alone, so as to not overwrite any of your manual translation
work. That said, if you want to generate new translations of an existing
property you must first remove that property from `Language.properties`, build
the languages (to remove the property from the `Language_*.properties` files),
re-add that property and its new value to `Language.properties`, and build the
languages again. At this point, Bing Translator generates translations based on
your property's new value. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** If you're Mavenizing your
 portlet, you must manually copy the translations to your deployed application.
 You copy your `content` folder into your portlet's
 `src/main/webapp/WEB-INF/classes` folder. 

---

By using the Plugins SDK's language building capability, you can keep all
created translations synchronized with your default `Language.properties`. You
can run it any time during development. It significantly reduces the time spent
on the maintenance of translations. However, remember that a *machine*
translation is generated by the Microsoft Translator. Machine translations can
often come across as rude or (unintentionally) humorous. Sometimes they are
simply inaccurate. Someone fluent in each language should review the
translations before the translations are deployed to a production environment. 

In this tutorial you learned to leverage the Plugins SDK to generate 
translations for your portlets using the Bing Translator.
