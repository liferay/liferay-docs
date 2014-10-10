# Sharing Language Keys Between Your Portlets [](id=sharing-language-keys-between-your-portlets)

<!-- The beginning and ending test portlets can be found at the following:
Begin: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferay-mvc/prefs/begin/event-listing-portlet
End: https://github.com/codyhoag/liferay-docs/tree/master/develop/tutorials/code/loc/share-lang-keys/end/event-listing-portlet
-->

It's likely that you'll have messages that you want to localize that aren't
already implemented in one of Liferay's core language keys. You'll need to
specify these language keys in one or more resource bundles in your plugin. If
one of your portlets will be used in the Control Panel and you want to localize
its title and description, it's best to use a separate resource bundle for that
portlet. If none of your portlets will be used in the Control Panel, then the
portlets can share the same resource bundle. 

You can share a resource bundle between portlets by adding a resource bundle to
the portlets' project. By the end of this tutorial, you'll know how to share a
resource bundle between multiple portlets in your project. Figure 1 below
shows two portlets displaying the same language key from a shared resource
bundle: 

![Figure 1: When portlets share the same resource bundle they can display the same textual elements, based on language keys from that resource bundle.](../../images/portlet-localization-shared-bundle.png)

Let's begin sharing a single resource bundle with multiple portlets! 

For this tutorial, assume that both portlets you're working with are contained
in the same project. 

1. In your project's `docroot/WEB-INF/src/content/Language.properties` file, add
   your desired language key.

    Here's an example language key: 

        you-know-it-is-the-best=You know it is the best

2. In your project's `docroot/WEB-INF/src/content` folder, create another
   translation of the language key file for a language of your choice, and add
   the same language keys translated to that language.

    For example, if you were translating to Spanish, you would create a file
    `Language_es.properties` in the `docroot/WEB-INF/src/content` folder. Here, 
    you'd enter the same language keys as you did in your `Language.properties` file,
    but with Spanish values. Here's the example language key from step one 
    translated to Spansish:

        you-know-it-is-the-best=Usted sabe que es la mejor

    You can view Liferay's available locales in the [Language and Time
    Zones](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)
    section of the Portal Properties. 

3.  For all the portlets that are to share the language keys, edit their view
    JSP files to use the language keys. 

    For example, to use a language key named `you-know-it-is-the-best` in a
    `<liferay-ui:message>` tag, you could specify this: 

        Liferay - <liferay-ui:message key="you-know-it-is-the-best" />!

    This line brings your translated language key value into your JSP. You can
    reference a sample portlet's
    [`view.jsp`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/loc/share-lang-keys/end/event-listing-portlet/docroot/html/locationlisting/view.jsp)
    for an example of how to use a Liferay UI message in your JSP.

    Make sure to specify any required taglibs you're using to display your
    language keys. For example, to use the `liferay-ui` taglib, you could
    declare the following line in your JSP: 

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %> 

4. Navigate to the `portlet.xml` file for your project. Make sure that all the
   portlets have the same `resource-bundle` specified. If the portlets specify
   the same resource bundle, they're sharing it. The `portlet.xml` file code
   snippet below exemplifies what this can look like for two portlets: 

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

    It's important to check that the `resource-bundle` element is in the proper
    place in the `portlet` element when developing your own app. See the
    `portlet.xml` file's schema
    [http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)
    for details. 

5. Redeploy the plugin project and place the portlets on a page, if
   you haven't already done so. Verify that they display the same intended
   messages based on your resource bundle. 

6. Switch your portal's locale to the alternate language by adding the
   language's two letter abbreviation in the context of the URL and refreshing
   the page.

    For example, to display the portlets in a Spanish context on
    `localhost:8080`, your URL would look like this:

        localhost:8080/es

    Notice how the portlets display your translated language keys. 

![Figure 2: This figure displays two portlets sharing a Spanish translation of a language key from the same resource bundle. Sharing resource bundles between multiple portlets helps you leverage common translated text.](../../images/portlet-localization-shared-bundle-spanish.png)

At this point, any language keys you specify in the language properties files
are accessible from all of your plugin project's portlets. 

+$$$

**Note:** It's best to use the Liferay
naming convention for the language resource bundle file and folder so that your
portlets can access the bundle and so that you can use the automatic language
building capabilities of Liferay IDE and the Plugins SDK with the bundle. 

$$$

In this tutorial, you created language keys, specified the language key values
in different languages translations, and shared the new language keys among
your plugin project's portlets. 

Related topics: 

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
