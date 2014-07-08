# Sharing Language Keys Between Your Portlets [](id=sharing-language-keys-between-your-portlets-lp-6-2-develop-tutorial)

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
a sample project. This sample project has two separate portlets. At the end of
this tutorial, both portlets will share the same resource bundle. Figure 1 below
displays what two portlets sharing the same resource bundle looks like: 

![Figure 1: Both portlets share the same resource bundle with the same language key. Thus, both portlets display the same message.](../../images/portlet-localization-shared-bundle.png)

Let's begin sharing a single resource bundle with multiple portlets! 

## Sharing a Resource Bundle

For this tutorial, assume that both portlets you're working with are contained
in the same project. 

1. In your project's `docroot/WEB-INF/src/content/Language.properties` file, add
   the following language key: 

        you-know-it-is-the-best=You know it is the best

2. Create another language key file `Language_es.properties` in the
   `docroot/WEB-INF/src/content` folder and add the equivalent
   `you-know-it-is-the-best` key translated to Spanish:

        you-know-it-is-the-best=Usted sabe que es la mejor

3. Open the `view.jsp` file for both portlets and insert the following code: 

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
   haven't already done so. Verify that they display the same message: *Liferay
   \- You know it is the best!*. 

6. Switch your portal's locale to Spanish by adding `/es` after
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

## Next Steps

[Generating Language Properties File and Automated Translations](/tutorials/-/knowledge_base/generating-language-properties-file-and-automated-t-lp-6-2-develop-tutorial)

[Localizing Site Administration Portlets](/tutorials/-/knowledge_base/localizing-site-administration-portlets-lp-6-2-develop-tutorial)

