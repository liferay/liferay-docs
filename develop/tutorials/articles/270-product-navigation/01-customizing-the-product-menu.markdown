# Customizing the Product Menu [](id=customizing-the-product-menu)

Liferay's Product Menu comes with three major sections to choose from, by
default: the Control Panel, User Settings, and Site Administration. These
options are called panel categories, which is the term used to differentiate
between sections of the menu. For instance, the Control Panel is a single panel
category, and when clicking on it, you're presented with four other child panel
categories: *Users*, *Sites*, *Apps*, and *Configuration*. It you click on one
of these child panel categories, you're presented with panel apps.

+$$$

**Note:** The Product Menu cannot be changed by applying a new theme. To change
the layout/style of the Product Menu, you must create and deploy a theme
contributor. <!--See the
[Theme Contributors](/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
tutorial for more details.-->

$$$

This construction of the Product Menu was designed to be intuitive and easy to
use. For your instance of Liferay, however, you may want to add other panel
categories with custom panel apps. Also, you may desire to change the order of
your panel categories and/or apps. In this tutorial, you'll learn how to provide
your own custom or modify existing panel categories and panel apps for the
Product Menu.

## Adding Custom Panel Categories [](id=adding-custom-panel-categories)

Liferay provides an easy way to extend the Product Menu and customize it to
display what is most helpful in your particular situation. First, you'll learn
how to add a panel category.

1. Create a generic OSGi module using your favorite third party tool, or use
   [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli). 

2. Create a unique package name in the module's `src` directory and create a
   new Java class in that package. To follow naming conventions, give your
   class a unique name followed by *PanelCategory* (e.g.,
   `ControlPanelCategory`).

3. Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {
                "panel.category.key=" + [Panel Category Key],
                "panel.category.order:Integer=[int]"
            },
            service = PanelCategory.class
        )

    The `property` element designates two properties that should be assigned for
    your category. The `panel.category.key` specifies the parent category for
    your custom category. You can find popular parent categories to assign in
    the [PanelCategoryKeys](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/application-list/com.liferay.application.list.api/com/liferay/application/list/constants/PanelCategoryKeys.html)
    class. For instance, if you wanted to create a child category in the Control
    Panel, you could assign `PanelCategoryKeys.CONTROL_PANEL`. Likewise, if you
    wanted to create a root category, like the Control Panel or Site
    Administration, you could assign `PanelCategoryKeys.ROOT`.

    The `panel.category.order:Integer` property specifies the order in which
    your category is displayed. The higher the number (integer), the lower your
    category is listed among other sibling categories assigned to a parent.
    
    Lastly, your `service` element should specify the `PanelCategory.class`
    service. You can view an example of a similar `@Component` annotation for
    the `UserPanelCategory` class below.

        @Component(
            immediate = true,
            property = {
                "panel.category.key=" + PanelCategoryKeys.ROOT,
                "panel.category.order:Integer=200"
            },
            service = PanelCategory.class
        )

4. Implement the `PanelCategory` interface. A popular way to do this is by
   extending the
   [BasePanelCategory](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/application-list/com.liferay.application.list.api/com/liferay/application/list/BasePanelCategory.html)
   or 
   [BaseJSPPanelCategory](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/application-list/com.liferay.application.list.api/com/liferay/application/list/BaseJSPPanelCategory.html)
   abstract classes. Typically, the `BasePanelCategory` is extended for basic
   categories (e.g., the Control Panel category) that only display the category
   name or other simple functionality. If you'd like to provide a custom UI for
   your panel, you can do so using any frontend technology, you only need to
   implement the methods `include()` or `includeHeader()` from the
   `PanelCategory` interface. The `includeHeader` method is used to render the
   header of the section and the `include` method is used to render the body.
   Implementing a custom UI gives you the flexibility to add more complex
   functionality. If you are going to use JSPs as the frontend technology, a
   base class called `BaseJSPPanelCategory` can be extended that already
   implements the methods `include()` and `includeHeader()` for you. This will
   be elaborated on more extensively later.

    +$$$

    **Note:** In this tutorial, JSPs are used to describe how to provide
    functionality to panel categories and apps. JSPs, however, are not the only
    way to provide frontend functionality to your categories/apps. You can
    create your own class implementing `PanelCategory` to use other 
    technologies, such as FreeMarker.

    $$$

5. Since you're implementing the
   [PanelCategory](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/application-list/com.liferay.application.list.api/com/liferay/application/list/PanelCategory.html)
   interface, you'll need to implement its methods if you're not extending a
   base class:

    - `getNotificationCount`: returns the number of notifications to be shown in
    the panel category.
    - `include`: renders the body of the panel category.
    - `includeHeader`: renders the panel category header.
    - `isActive`: whether the panel is selected.
    - `isPersistState`: whether to persist the panel category's state to the
      database. This is used to save the state of the panel category when
      navigating away from the menu.

6. Add any other methods that are necessary to create your custom panel
   category. As you learned earlier, you can extend the `BasePanelCategory` and
   `BaseJSPPanelCategory` abstract classes to implement `PanelCategory`.

    If you'd like to provide something simple for your panel category like a
    name, extending `BasePanelCategory` is probably sufficient. For example, the
    [ControlPanelCategory](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/product-navigation/com.liferay.product.navigation.control.panel/com/liferay/product/navigation/control/panel/application/list/ControlPanelCategory.html)
    extends `BasePanelCategory` and specifies a `getLabel` method to set and
    display the panel category name.

        @Override
        public String getLabel(Locale locale) {
            return LanguageUtil.get(locale, "control-panel");
        }

    If you'd like to provide functionality that is more complex, you can use
    JSPs or any other similar technology to render the panel category. You can
    easily do this by extending `BaseJSPPanelCategory`. For example, the
    [SiteAdministrationPanelCategory](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/product-navigation/com.liferay.product.navigation.site.administration/com/liferay/product/navigation/site/administration/application/list/SiteAdministrationPanelCategory.html)
    specifies the `getHeaderJspPath` and `getJspPath` methods. You could create
    a JSP with the UI you'd like to render and specify its path in methods like
    these:

        @Override
        public String getHeaderJspPath() {
            return "/sites/site_administration_header.jsp";
        }

        @Override
        public String getJspPath() {
            return "/sites/site_administration_body.jsp";
        }

    One JSP is responsible for rendering the panel category's header (displayed
    when panel is collapsed) and the other for its body (displayed when panel is
    expanded).

    <!-- Explain two JSPs above further! -Cody -->
    
    You will also need to specify the servlet context from where you are loading
    the JSP files. If this is inside an OSGi module, make sure your `bnd.bnd`
    file has defined a web context path:

        Bundle-SymbolicName: com.sample.my.module.web
        Web-ContextPath: /my-module-web

    And then reference the Servlet context using the symbolic name of your
    module like this:

        @Override
        @Reference(
            target = "(osgi.web.symbolicname=com.sample.my.module.web)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            super.setServletContext(servletContext);
        }

Excellent! You've successfully created a custom panel category to display in the
Product Menu. In many cases, a panel category holds panel apps for users to
access. You'll learn about how to add a panel app to a panel category next.

## Adding Custom Panel Apps [](id=adding-custom-panel-apps)

Just as adding panel categories is straight-forward and dynamic, so too is the
process for adding panel apps. Panel apps are, by default, links provided in a
panel category that allow you to access an application. For instance, if you 
navigate to the Site Administration &rarr; *Content* panel category, you can 
select the *Web Content* option, which is a panel app that allows you to access
web content. Panel apps can also have a custom UI in the same way Panel
categories could have a more complex UI.

Follow the steps below to add a panel app to your Liferay instance's Product
Menu.

1. Create a generic OSGi module using your favorite third party tool, or use
   [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli). 

2. Create a unique package name in the module's `src` directory and create a
   new Java class in that package. To follow naming conventions, give your class
   a unique name followed by *PanelApp* (e.g., `JournalPanelApp`).

3. Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {
                "panel.app.order:Integer=INTEGER"
                "panel.category.key=" + PANEL_CATEGORY_KEY,
            },
            service = PanelApp.class
        )

    These properties and attributes are very similar to the ones discussed for
    panel categories. The `panel.app.order:Integer` property specifies the order
    your panel app is listed among other panel apps in the same category. The
    `panel.category.key` specifies the panel category your panel app will reside
    in. For example, if you want to add a panel app to Site Administration
    &rarr; *Content*, you would add the following property:

        "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT

    Visit the
    [PanelCategoryKeys](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/application-list/com.liferay.application.list.api/com/liferay/application/list/constants/PanelCategoryKeys.html)
    class for keys you can use to specify default panel categories in Liferay.

    Lastly, be sure to set the `service` attribute to `PanelApp.class`. You can
    view an example of a similar `@Component` annotation for the
    `JournalPanelApp` class below.

        @Component(
            immediate = true,
            property = {
                "panel.app.order:Integer=100",
                "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
            },
            service = PanelApp.class
        )

4. Implement the `PanelApp` interface. A popular way to do this is by
   extending the
   [BasePanelApp](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/application-list/com.liferay.application.list.api/com/liferay/application/list/BasePanelApp.html)
   abstract class. Just as you learned in the previous sub-section on panel
   categories, if you need to create a more complex UI, you can do so. If you
   want to use JSPs to render that UI, you can extend an additional abstract 
   class which extends `BasePanelApp` called
   [BaseJSPPanelApp](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/application-list/com.liferay.application.list.api/com/liferay/application/list/BaseJSPPanelApp.html).
   This provides additional methods you can use to incorporate JSP functionality
   into your panel apps listed in the Product Menu.

    JSPs are not the only way to provide frontend functionality to your panel
    apps. You can create your own class implementing `PanelCategory` to use 
    other technologies, such as FreeMarker.

5. Since you're implementing the
   [PanelApp](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/application-list/com.liferay.application.list.api/com/liferay/application/list/PanelApp.html)
   interface, you'll need to implement its methods if you're not extending a
   base class. The `BlogsPanelApp` is a simple example of how to specify your
   portlet as a panel app. In this class, the `BasePanelApp` is extended, and
   the `getPortletId` and `setPortlet` methods are overridden. These methods are
   used to specify and set the Blogs portlet as a panel app.

    Each panel app must belong to a portlet and each portlet can have at most one
    panel app. If more than one panel app is needed, another custom portlet must 
    be created. By default, the panel app will only be shown if the user has 
    permission to view the associated portlet.

    This is how those methods look for the Blogs portlet:

        @Override
        public String getPortletId() {
            return BlogsPortletKeys.BLOGS_ADMIN;
        }

        @Override
        @Reference(
            target = "(javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN + ")",
            unbind = "-"
        )
        public void setPortlet(Portlet portlet) {
            super.setPortlet(portlet);
        }

    Liferay provides full flexibility to make the UI of panel apps much more
    complex. As you learned before, the `BaseJSPPanelApp` abstract class can be
    extended to provide further functionality with JSPs. For instance, the
    Navigation category in Site Administration offers a dynamic Pages panel app
    that provides much more than a simple link to access a portlet. This is
    accomplished by extending `BaseJSPPanelApp` in the
    [GroupPagesPanelApp](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/layout/com.liferay.layout.admin.web/com/liferay/layout/admin/web/internal/application/list/GroupPagesPanelApp.html)
    class, which provides this functionality in the Product Menu.

    In `GroupPagesPanelApp`, notice that the portlet ID is still returned
    similarly to the previous `BlogsPanelApp` example, but a `getJspPath` method
    is also called, which gives the panel app much more functionality provided
    by the `layouts_tree` JSP file:

        @Override
        public String getJspPath() {
            return "/panel/app/layouts_tree.jsp";
        }

    <!-- Elaborate more on JSP! -Cody -->

    Since you're including custom JSPs in your module, you'll also need to set
    the right `ServletContext`.

        @Override
        @Reference(
            target = "(osgi.web.symbolicname=com.liferay.layout.admin.web)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            super.setServletContext(servletContext);
        }

Now you know how to add or modify a panel app in the Product Menu. Not only does
Liferay provide a simple solution to add new panel categories and apps, it also
gives you the flexibility to add a more complex UI to the Product Menu using
any technology.
