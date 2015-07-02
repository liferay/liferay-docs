# Embedding Portlets in a Theme

A frequently used capability that Liferay provides when building a site is the
ability to embed a portlet in a theme. This allows the portlet to be visible on
all pages where the theme is used. In the past, this was only possible by
hardcoding a specific portlet into the theme, which has many drawbacks. Liferay
now provides the *Portlet Providers* framework that only requires you specify
the element type and action to be displayed. Based on the given element type and
action, Liferay can distinguish which deployed portlet to use. This increases
the flexibility and modularity of theming in Liferay Portal.

In this tutorial, you'll learn how to declare an element type and action in a
custom theme, and create a module that finds the correct portlet to use based on
those given parameters.

## Adding a Portlet to a Custom Theme

The first thing you'll need to do is open your theme's template file that you'd
like to declare the embedded portlet for. For example, the `portal_normal.ftl`
template file is a popular place to declare embedded portlets. In the template
file, find the location where you plan on embedding a portlet and insert the
following declaration:

    ${theme.runtime("CLASS_NAME", ACTION)}

This declaration expects two parameters: the class name of the element type
you'd like the portlet to handle, and the type of action. An example of an
embedded portlet declaration can be viewed below:

    $theme.runtime("com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry", $portletProviderAction.VIEW)

This declares that the theme is requesting to view language entries. There are
four different kinds of actions supported by the Portlet Providers framework:
`ADD`, `BROWSE`, `EDIT`, and `VIEW`. Specify these two parameters in your
theme's empty runtime declaration.

Great, your theme declaration is complete! However, the Portal is not yet
configured to handle this request. You'll need to create a module that can
find the portlet that fits the theme's request.

1. Create a generic OSGi module using your favorite third party tool.

    <!-- If we decide to document how to create an OSGi module from scratch, we
    should point to that documentation here. At the current time, there is no
    Liferay "recommended" way of doing this. Therefore, I'm assuming that the
    reader has experience with OSGi development. Pointing to introductory OSGi
    tutorials (once available) would be very helpful here. -Cody -->

2. Create a unique package name in the module's `src` directory, and create a
   new Java class in that package. To follow naming conventions, name the class
   based on the element type and action type, followed by *PortletProvider*
   (e.g., `LanguageEntryViewPortletProvider`). The class should extend the
   [`BasePortletProvider`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/portlet/BasePortletProvider.java)
   class and implement the appropriate portlet provider interface based on the
   action you chose in your theme (e.g.,
   [ViewPortletProvider](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/portlet/ViewPortletProvider.java),
   [BrowsePortletProvider](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/portlet/BrowsePortletProvider.java),
   etc.).

3. Directly above the class's declaration, insert the following:

        @Component(
            immediate = true,
            property = {"model.class.name=CLASS_NAME"},
            service = INTERFACE.class
        )

    The `property` element should match the element type you specified in your
    theme declaration (e.g.,
    `com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry`). Also, your
    `service` element should match the interface you're implementing (e.g.,
    `ViewPortletProvider.class`). You can view an example of a similar
    `@Component` annotation in the
    [RolesSelectorEditPortletProvider](https://github.com/liferay/liferay-portal/blob/master/modules/apps/roles/roles-selector-web/src/com/liferay/roles/selector/web/portlet/RolesSelectorEditPortletProvider.java)
    class.

4. Specify the methods you'd like to implement. Make sure to retrieve the
   portlet ID and page ID that should be provided when this service is called by
   your theme.

    A common use case is to implement the `getPortletId()` and
    `getPlid(ThemeDisplay)` methods. You can view the
    [LanguageEntryViewPortletProvider](https://github.com/liferay/liferay-portal/blob/master/modules/apps/site-navigation/site-navigation-language-web/src/com/liferay/site/navigation/language/web/portlet/LanguageEntryViewPortletProvider.java)
    for an example of how these methods can be implemented to provide a portlet
    for embedding in a theme. This example module returns the portlet ID of the
    Language portlet specified in
    [LanguagePortletKeys](https://github.com/liferay/liferay-portal/blob/master/modules/apps/site-navigation/site-navigation-language-web/src/com/liferay/site/navigation/language/web/constants/LanguagePortletKeys.java).
    Furthermore, it returns the PLID, which is the ID that uniquely identifies a
    page used by your theme. By retrieving these, your theme will know which
    portlet to use, and which page to use it on.

The only thing left to do is generate the module's JAR file and copy it to your
Portal's `osgi/modules` directory. Once the module is installed and activated in
your Portal's service registry, your new editor configuration is available for
use.

You succesfully requested a portlet based on the element and action types
required, and created and deployed a module that retrieves the portlet and
embeds it in your theme.
