# Embedding Portlets in Themes and Layout Templates [](id=embedding-portlets-in-themes-and-layout-templates)

One thing developers often want to do is embed a portlet in a theme or layout
template. This makes the portlet visible on all pages where the theme/layout is
used. In the past, this was only possible by hard-coding a specific portlet into
place, which has many drawbacks. Liferay now provides the *Portlet Providers*
framework that requires you only specify the entity type and action to be
displayed. Based on the given entity type and action, Liferay determines which
deployed portlet to use. This increases the flexibility and modularity of
embedding portlets in Liferay Portal.

In this tutorial, you'll learn how to declare an entity type and action in a
custom theme/layout, and you'll create a module that finds the correct portlet
to use based on those given parameters. You'll first learn how to embed portlets
into a theme.

## Adding a Portlet to a Custom Theme [](id=adding-a-portlet-to-a-custom-theme)

The first thing you should do is open the template file for which you want to
declare an embedded portlet. For example, the `portal_normal.ftl` template file
is a popular place to declare embedded portlets. Insert the following
declaration wherever you want to embed the portlet:

    <@liferay_portlet["runtime"]
        portletProviderAction=ACTION
        portletProviderClassName="CLASS_NAME"
    />

This declaration expects two parameters: the type of action and the class name
of the entity type the portlet should handle. Here's an example of an embedded
portlet declaration: 

    <@liferay_portlet["runtime"]
        portletProviderAction=portletProviderAction.VIEW
        portletProviderClassName="com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry"
    />

This declares that the theme is requesting to view language entries. There are
five different kinds of actions supported by the Portlet Providers framework:
`ADD`, `BROWSE`, `EDIT`, `PREVIEW`, and `VIEW`. Specify the entity type and
action in your theme's runtime declaration.

Great! Your theme declaration is complete. However, the Portal is not yet
configured to handle this request. You must create a module that can
find the portlet that fits the theme's request.

1. Create a generic OSGi module using your favorite third party tool, or use
   [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli). 

2. Create a unique package name in the module's `src` directory, and create a
   new Java class in that package. To follow naming conventions, name the class
   based on the entity type and action type, followed by *PortletProvider*
   (e.g., `SiteNavigationLanguageEntryViewPortletProvider`). The class should
   extend the
   [`BasePortletProvider`](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/portlet/BasePortletProvider.java)
   class and implement the appropriate portlet provider interface based on the
   action you chose in your theme (e.g.,
   [ViewPortletProvider](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/portlet/ViewPortletProvider.java),
   [BrowsePortletProvider](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/portlet/BrowsePortletProvider.java),
   etc.).

3. Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {"model.class.name=CLASS_NAME"},
            service = INTERFACE.class
        )

    The `property` element should match the entity type you specified in your
    theme declaration (e.g.,
    `com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry`). Also, your
    `service` element should match the interface you're implementing (e.g.,
    `ViewPortletProvider.class`). You can view an example of a similar
    `@Component` annotation in the
    [RolesSelectorEditPortletProvider](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/roles/roles-selector-web/src/main/java/com/liferay/roles/selector/web/internal/portlet/RolesSelectorEditPortletProvider.java)
    class.

4. Specify the methods you want to implement. Make sure to retrieve the
   portlet ID and page ID that should be provided when this service is called by
   your theme.

    A common use case is to implement the `getPortletId()` and
    `getPlid(ThemeDisplay)` methods. You can view the
    [SiteNavigationLanguageViewPortletProvider](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/site-navigation/site-navigation-language-web/src/main/java/com/liferay/site/navigation/language/web/internal/portlet/SiteNavigationLanguageViewPortletProvider.java)
    for an example of how these methods can be implemented to provide a portlet
    for embedding in a theme. This example module returns the portlet ID of the
    Language portlet specified in
    [SiteNavigationLanguagePortletKeys](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/site-navigation/site-navigation-language-web/src/main/java/com/liferay/site/navigation/language/web/constants/SiteNavigationLanguagePortletKeys.java).
    Furthermore, it returns the PLID, which is the ID that uniquely identifies a
    page used by your theme. By retrieving these, your theme will know which
    portlet to use, and which page to use it on.

The only thing left to do is generate the module's JAR file and copy it to your
Portal's `osgi/modules` directory. Once the module is installed and activated in
your Portal's service registry, your embedded portlet is available for
use wherever your theme is used.

You successfully requested a portlet based on the entity and action types
required, and created and deployed a module that retrieves the portlet and
embeds it in your theme. Next, you'll learn a similar process to embed a portlet
in your layout template.

## Adding a Portlet to a Custom Layout Template [](id=adding-a-portlet-to-a-custom-layout-template)

The process for embedding portlets in layout templates is similar to that for the
theme. The only change is the declaration you insert in your template's TPL
file.

1. Open your layout template's TPL file (e.g., `docroot/1_2_1-columns.tpl`).

2. Add a `$processor.processPortlet("CLASS_NAME", ACTION)` directive within the
   column in which to embed the portlet.

    This declaration, just as the theme declaration, expects two parameters: the
    class name of the entity type you want the portlet to handle and the type
    of action. An example of an embedded portlet declaration can be viewed
    below:

        $processor.processPortlet("com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry", $portletProviderAction.VIEW)

    This declares that the layout is requesting to view breadcrumb entries. The
    Portlet Providers framework offers four different actions for layout
    templates: `ADD`, `BROWSE`, `EDIT`, and `VIEW`. Specify the entity type and
    action in your layout's declaration.

Now that your layout declaration is complete, you'll need to create a module
that can handle this request. This process is exactly the same for themes and
layout templates. Follow steps 1-4 in the previous section to set up your
module.

+$$$

**Note:** In some cases, a default portlet is already provided by Liferay to
fulfill certain requests. You can override the default portlet with your custom
portlet by specifying a higher service rank. To do this, set the following
property in your class' `@Component` declaration:

    property= {"service.ranking:Integer=20"}

Make sure you set the service ranking higher than the default portlet being used.

$$$

Lastly, generate the module's JAR file and deploy it to Liferay Portal. Now when
you assign your layout to a page, the portlet that fulfills the layout's request
is embedded on the page.

## Related Topics [](id=related-topics)

[Providing Portlets to Manage Requests](/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)
