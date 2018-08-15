# Adding Custom Panel Apps [](id=adding-custom-panel-apps)

After you have created a Category, create a Panel app to go in it:

1.  Create an OSGi module using your favorite third party tool, or use
    [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli). Blade CLI
    offers a
    [Panel App](/develop/reference/-/knowledge_base/7-0/panel-app-template)
    template to help generate a basic panel category and panel app.

2.  Create a unique package name in the module's `src` directory, and create a
    new Java class in that package. To follow naming conventions, give your class
    a unique name followed by *PanelApp* (e.g., `JournalPanelApp`).

3.  Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {
                "panel.app.order:Integer=INTEGER"
                "panel.category.key=" + PANEL_CATEGORY_KEY,
            },
            service = PanelApp.class
        )

    These properties and attributes are similar to those discussed  in
    the previous
    [tutorial](/develop/tutorials/-/knowledge_base/7-1/adding-custom-panel-categories).
    The `panel.category.key` assigns your panel app to a panel category. The
    `panel.app.order:Integer` property specifies the order your panel app
    appears among other panel apps in the same category. For example, if you
    want to add a panel app to Site Administration &rarr; *Content*, add the
    following property:

        "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT

    Visit the
    [PanelCategoryKeys](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/constants/PanelCategoryKeys.html)
    class for keys you can use to specify default panel categories in Liferay.

    Set the `service` attribute to `PanelApp.class`. You can view an example of
    a similar `@Component` annotation for the `JournalPanelApp` class below.

        @Component(
            immediate = true,
            property = {
                "panel.app.order:Integer=100",
                "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
            },
            service = PanelApp.class
        )

4.  Implement the `PanelApp` interface by extending the
    [BasePanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BasePanelApp.html)
    abstract class. As you learned in the previous
    [tutorial](/develop/tutorials/-/knowledge_base/7-1/adding-custom-panel-categories)
    on panel categories, if you must create a more complex UI to render in
    the panel, you can.

    If you want to use JSPs to render that UI, extend 
    [BaseJSPPanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html).
    This provides additional methods you can use to incorporate JSP 
    functionality into your app's listing in the Product Menu. 

    JSPs are not the only way to provide front-end functionality to your panel
    apps. You can create your own class implementing `PanelCategory` to use 
    other technologies such as FreeMarker.

5.  If you are implementing the
    [PanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/PanelApp.html)
    interface without extending a base class, you must implement its methods.
    The `BlogsPanelApp` is a simple example of how to specify your portlet as
    a panel app. This class extends `BasePanelApp`, overriding the
    `getPortletId` and `setPortlet` methods. These methods specify and set the
    Blogs portlet as a panel app. 

    Each panel app must belong to a portlet and each portlet can have at most 
    one panel app. If more than one panel app is needed, another portlet 
    must be created. By default, the panel app only appears if the user 
    has permission to view the associated portlet.

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

    You can also customize your panel app's appearance in the Product Menu. As
    you learned before, the `BaseJSPPanelApp` abstract class can be extended to
    provide further functionality with JSPs.

Now you know how to add or modify a panel app in the Product Menu. Not only does
Liferay provide a simple solution to add new panel categories and apps, it also
gives you the flexibility to add a more complex UI to the Product Menu using
any technology.
