# Sharing Language Keys Between Your Portlets [](id=sharing-language-keys-between-your-portlets)

<!-- The beginning and ending test portlets can be found at the following:
Begin: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/prefs/begin/event-listing-portlet
End: https://github.com/codyhoag/liferay-docs/tree/master/develop/tutorials/code/plat-fws/share-lang-keys/end/event-listing-portlet
-->

It's likely that you'll have messages that you want to localize that aren't
already implemented in one of Liferay's core language keys. You'll need to
specify these language keys in one or more resource bundles in your plugin. If
one of your portlets will be used in the Control Panel and you want to localize
its title and description, it's best to use a separate resource bundle for that
portlet. If none of your portlets will be used in the Control Panel, then the
portlets can share the same resource bundle. 

You can share a resource bundle between portlets by adding a resource bundle to
the portlets' project. At the end of this tutorial, two of your project's
portlets will share the same resource bundle. Figure 1 below displays what two
portlets sharing the same resource bundle looks like: 

![Figure 1: When portlets share the same resource bundle with the same language key, they display the same message.](../../images/portlet-localization-shared-bundle.png)

Let's begin sharing a single resource bundle with multiple portlets! 

For this tutorial, assume that both portlets you're working with are contained
in the same project. 

1. In your project's `docroot/WEB-INF/src/content/Language.properties` file, add
   your desired language key. For example: 

        you-know-it-is-the-best=You know it is the best

2. Create another language key file in the `docroot/WEB-INF/src/content` folder
   and add the equivalent language key you specified previously, translated to
   the language of your choice. For example, if you were translating in Spanish,
   you would create a `Language_es.properties` file and add the following
   language key: 

        you-know-it-is-the-best=Usted sabe que es la mejor

    You can view Liferay's available locales in the [Language and Time
    Zones](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)
    section of Portal Properties. 

3. Open the `view.jsp` file for both portlets and insert the
   `<liferay-ui:message>` tag specifying your language key: 

        Liferay - <liferay-ui:message key="you-know-it-is-the-best" />!

    This line brings your translated language key value into your JSP. You can
    reference a sample portlet's
    [`view.jsp`](https://github.com/codyhoag/liferay-docs/blob/master/develop/tutorials/code/plat-fws/share-lang-keys/end/event-listing-portlet/docroot/html/locationlisting/view.jsp)
    for an example of where this UI message can be placed within your JSP. Make
    sure you've specified the required taglib as well: 

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %> 

4. Navigate to the `portlet.xml` file for your project. Make sure that both
   portlets have the same `resource-bundle` specified. If both portlets specify
   the same resource bundle, this means that they are sharing it. The example
   code snippet below exemplifies what this may look like: 

        <portlet>
            <portlet-name>yourportlet1</portlet-name>
            ...
            <resource-bundle>content.Language</resource-bundle>
            <portlet-info>
            ...
        </portlet>
        <portlet>
            <portlet-name>yourportlet2</portlet-name>
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

5. Redeploy the project and place both portlets on a Liferay site page, if you
   haven't already done so. Verify that they display the same message. 

6. Switch your portal's locale to the alternate language you chose by adding its
   two letter abbreviation after `localhost:8080` and refreshing the page. For
   example, if you were translating to Spanish, your URL would look like the
   following:

        localhost:8080/es

    Notice how both portlets display your translated language key. 

![Figure 2: This figure displays two portlets sharing a Spanish language key from the same resource bundle. Sharing resource bundles between multiple portlets helps you leverage common translated text.](../../images/portlet-localization-shared-bundle-spanish.png)

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

