# Sharing Language Keys Between Your Portlets

It's likely that you'll have messages that you want to localize that aren't one
of Liferay's core language keys. So you'll need to specify these language keys
in one or more resource bundles in your plugin. If one of your portlets is going
to be used in the Control Panel and you want to localize its title and
description used in the Control Panel, then it's best to use a separate resource
bundle for that portlet. If none of your portlets are going to be used in the
Control Panel, then the portlets can share the same resource bundle. 

We'll show you how to share a resource bundle between portlets by adding a
resource bundle to a sample project named `event-listing-portlet`. This sample
project has two separate portlets. At the end of this tutorial, both portlets
will share the same resource bundle. Figure 1 below displays what both portlets
will look like once sharing a resource bundle: 

![Figure 1: When neither portlet is used in the Control Panel, sharing language keys is a smart and viable option.](../../images/portlet-localization-shared-bundle.png)

Let's begin sharing a single resource bundle with multiple portlets! 

## Sharing a Resource Bundle

Let's add a resource bundle to a sample portlet called the Event Listing
portlet. 

1. As a starting point, use the `event-listing-portlet`, which is available in
   the <https://github.com/liferay/liferay-docs> GitHub repository
   [here](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/prefs/begin/event-listing-portlet).
   You'll need to clone the repository if you haven't already done so. Then copy
   the `event-listing-portlet` folder into the `portlets` folder of your Liferay
   Plugins SDK. Note that this portlet is also the beginning portlet for the
   [Using Configurable Portlet
   Preferences](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/articles/platform-frameworks/configurable-portlet-preferences.markdown)
   tutorial. 

2. In the `docroot/WEB-INF/src/content/Language.properties` file, add the
   following language key: 

        you-know-it-is-the-best=You know it is the best

3. Create another language key file `Language_es.properties` in the content
   folder and add the equivalent `you-know-it-is-the-best` key translated to
   Spanish: 

        you-know-it-is-the-best=Usted sabe que es la mejor

4. Replace the view mode statements (e.g., *This is the \<b\>Location Listing
   Portlet\</b\> in View mode.*) in both portlets' `view.jsp` with the
   following: 

        Liferay - <liferay-ui:message key="you-know-it-is-the-best" />!

    This line brings your translated language key value into your JSP: 

5. In the Event Listing portlet's `view.jsp`, you'll need to add the following
   taglib to the top of the file: 

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    This identifies the location of the library we're using for our Liferay UI
    message. Note that this taglib directive is already specified in the
    Location Listing portlet's `view.jsp`. 

6. Navigate to the `portlet.xml` file for the `event-listing-portlet` project.
   Make sure that both portlets have the same `resource-bundle` specified. If
   both portlet's specify the same resource bundle, this means that they are
   sharing it. This is already done for our Event Listing and Location Listing
   portlets: 

        <portlet>
            <portlet-name>locationlisting</portlet-name>
            ...
            <resource-bundle>content.Language</resource-bundle>
            <portlet-info>
            ...
        </portlet>
        <portlet>
            <portlet-name>eventlisting</portlet-name>
            ...
            <resource-bundle>content.Language</resource-bundle>
            <portlet-info>
            ...
        </portlet>

    It's important to check that the `resource-bundle` element is in its proper
    place in the `portlet` element when developing your own app. See the
    `portlet.xml` file's schema
    [http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)
    for details. 

7. Redeploy the plugin and go to the page where you added the Event Listing and
   Location Listing portlets to verify that they display the same message
   "Liferay - You know it is the best!". 

8. Switch your portal's locale to Spanish by adding `/es` after
   `localhost:8080` and refresh the page. Notice how both portlets display your
   translated language key. 

![Figure 2: Sharing resource bundles between multiple portlets helps you leverage common translated text.](../../images/portlet-localization-shared-bundle-spanish.png)

At this point, any language keys you specify in the `Language.properties` files
are accessible from either of the portlets. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** It's best to use the Liferay
 naming convention for the language bundle file and folder so your portlets can
 access the bundle and you can use the automatic language building capabilities
 of Liferay IDE and the Plugins SDK with the bundle. 

---

In this tutorial, you created a new language key, learned how to specify that
language key in different languages, and shared the new language key with two
different portlets. 
