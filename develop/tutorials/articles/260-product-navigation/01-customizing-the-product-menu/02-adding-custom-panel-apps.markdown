# Adding Custom Panel Apps [](id=adding-custom-panel-apps)

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
   [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli). Blade CLI
   offers a
   [Panel App](/develop/reference/-/knowledge_base/7-0/panel-app-template)
   template, which you can use to generate a basic panel category and panel app.

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
    [PanelCategoryKeys](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/constants/PanelCategoryKeys.html)
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
   [BasePanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BasePanelApp.html)
   abstract class. Just as you learned in the previous sub-section on panel
   categories, if you need to create a more complex UI to render in the panel, 
   you can do so. If you want to use JSPs to render that UI, you can extend an 
   additional abstract class which extends `BasePanelApp` called 
   [BaseJSPPanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html).
   This provides additional methods you can use to incorporate JSP functionality
   into your app's listing in the Product Menu. 

    JSPs are not the only way to provide frontend functionality to your panel
    apps. You can create your own class implementing `PanelCategory` to use 
    other technologies, such as FreeMarker.

5. Since you're implementing the
   [PanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/PanelApp.html)
   interface, you must implement its methods if you're not extending a base 
   class. The `BlogsPanelApp` is a simple example of how to specify your portlet 
   as a panel app. This class extends the `BasePanelApp`, overriding the 
   `getPortletId` and `setPortlet` methods. These methods specify and set the 
   Blogs portlet as a panel app. 

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

    @product@ also lets you customize your panel app's appearance in the Product 
    Menu. As you learned before, the `BaseJSPPanelApp` abstract class can be 
    extended to provide further functionality with JSPs. For instance, the
    Navigation category in Site Administration offers a dynamic Pages panel app
    that provides much more than a simple link to access a portlet. This is
    accomplished by extending `BaseJSPPanelApp` in the
    [GroupPagesPanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/layout/admin/web/internal/application/list/GroupPagesPanelApp.html)
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
