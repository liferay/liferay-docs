# Generating Language Properties File and Automated Translations

In this tutorial, we'll learn how Liferay facilitates generating language key
files and translating the keys to languages you want to support. In order for a
user to see a message in his own locale, the message value must be specified in
a resource bundle file with a name ending in his locale's two character code.
For example, a resource bundle file named `Language_es.properties` containing a
message property with key `welcome` must be present with a Spanish translation
of the word "Welcome". Don't worry, the Plugins SDK provides a means for you to
get translations for your default resource bundle.

<!-- Insert image and transition -->

We'll get started by doing the initial setup for the Bing Translator service.

## Translating Languages Using the Bing Translator

The Plugins SDK uses the Bing Translator service
[http://www.microsofttranslator.com/](http://www.microsofttranslator.com/) to
translate all of the resources in your Language.properties file to multiple
languages. It provides a base translation for you to start with. To create base
translations using the Bing Translator service, you'll need to do the following:

1. Sign up for an Azure Marketplace account and subscribe to the Microsoft 
   Translator. It's listed in the *Data* section of the Azure Marketplace.

2. Register your portlet in the Azure Marketplace. You can do this from the 
   *Developers* tab in *My Account*. The client ID needs to match the project 
   name of the portlet. For example, this tutorial uses the 
   `event-listing-portlet` portlet project, so enter that as the client ID. 
   Accept the default client secret. Be sure to save the client ID and client 
   secret for later use.

3. Make sure that you have a `build.[username].properties` file in your Plugins
   SDK root directory. Create it if you do not. This 
   `build.[username].properties` file should contain a reference to a Liferay 
   bundle. If you have a Liferay Tomcat bundle, for example, your reference 
   should look like this:

       app.server.dir=[Liferay Home]/tomcat-7.0.42
       auto.deploy.dir=[Liferay Home]/deploy

   `[Liferay Home]` refers to your bundle's root directory.

4. Make sure that you have a `portal-ext.properties` file in your Liferay Home 
   directory. Create it if you do not. Edit the `portal-ext.properties` file by 
   adding the following two lines replaced with your values:

       microsoft.translator.client.id=[your-client-id]
       microsoft.translator.client.secret=[your-client-secret]

   Liferay copies the `portal-ext.properties` file from your Liferay Home
   directory to the `tomcat-[version]/webapps/ROOT/WEB-INF/classes` directory
   upon startup. So either start Liferay or manually copy your
   `portal-ext.properties` file from Liferay Home to this location. If you opt 
   for the automatic copy on startup, be sure to check the 
   `tomcat-[version]/webapps/ROOT/WEB-INF/classes` directory for the 
   `portal-ext.properties` file afterwards.

Next, you'll make the necessary changes to your portlet so that it can make use 
of the Bing Translator.
   
## Implementing Translations in Your Portlet

Now that you've done the initial setup for the Bing Translator, you're ready to 
add support for translations to your portlet.

1. As a starting point, use the `event-listing-portlet`, which is available in
   the <https://github.com/liferay/liferay-docs> GitHub repository
   [here](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/prefs/begin/event-listing-portlet).
   You'll need to clone the repository if you haven't already done so. Then copy
   the `event-listing-portlet` folder into the `portlets` folder of your Liferay
   Plugins SDK. Note that this portlet is also the beginning portlet for the
   [Using Configurable Portlet
   Preferences](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/articles/platform-frameworks/configurable-portlet-preferences.markdown)
   tutorial. Deploy the portlet and add the Location Listing portlet to a page 
   of your portal.

2. In the `docroot/WEB-INF/src/content/Language.properties` file, add the
   following language key: 

        you-know-it-is-the-best=You know it is the best
   
   Translations are generated for properties that you add or edit in this file. 
   However, translations will *not* be generated for properties that already 
   exist in your portlet.
        
3. Replace the view mode statement (e.g., *This is the \<b\>Location Listing
   Portlet\</b\> in View mode.*) in the Location Listing portlet's `view.jsp` 
   with the following: 

        Liferay - <liferay-ui:message key="you-know-it-is-the-best" />!

   This line brings your translated language key value into your JSP. 

4. Run `ant build-lang` from the 
   `[Liferay Plugins SDK]/portlets/event-listing-portlet` directory.

   When the build completes, you'll find the generated files with all of the
   translations in the same folder as your `Language.properties` file. Note that 
   many of the language properties files don't contain translations. This is 
   because they are intentionally disabled. We decided to do this because many 
   of the translations are incorrect or offensive. The issue is described 
   [here](https://issues.liferay.com/browse/LPS-29246).

5. Switch your portal's locale to Spanish by adding `/es` after `localhost:8080` 
   and refresh the page. Notice how the Location Listing portlet now shows your 
   translated language key.
   
![Figure 1: Autogenerated translation for the Location Listing portlet.](../../images/portlet-localization-generated-translation.PNG)

---

 ![Note](../../images/tip-pen-paper.png) **Note:** Since translations aren't
 generated for existing properties, use two steps if you need to edit existing
 properties. First, remove the properties from `Language.properties` and run
 `ant build-lang` to remove the properties from all the other resource bundles.
 Then re-add the properties with new values and run `ant build-lang` again. Now
 the Microsoft Translator should generate new translations for your properties.

---

---

 ![Note](../../images/tip-pen-paper.png) **Note:** If you're Mavenizing your
 portlet, make sure to copy your `content` folder into your portlet's
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
