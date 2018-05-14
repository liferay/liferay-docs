# Adding Custom Panel Categories [](id=adding-custom-panel-categories)

Liferay provides an easy way to extend the Product Menu and customize it to
display what is most helpful in your particular situation. First, you'll learn
how to add a panel category.

1. Create a generic OSGi module using your favorite third party tool, or use
   [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli). Blade CLI
   offers a
   [Panel App](/develop/reference/-/knowledge_base/7-1/panel-app-template)
   template, which you can use to generate a basic panel category and panel app.

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
    the [PanelCategoryKeys](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/constants/PanelCategoryKeys.html)
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
   [BasePanelCategory](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BasePanelCategory.html)
   or 
   [BaseJSPPanelCategory](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelCategory.html)
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
   [PanelCategory](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/PanelCategory.html)
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
    [ControlPanelCategory](https://github.com/liferay/liferay-portal/blob/7.0.3-ga4/modules/apps/web-experience/product-navigation/product-navigation-control-panel/src/main/java/com/liferay/product/navigation/control/panel/internal/application/list/ControlPanelCategory.java)
    extends `BasePanelCategory` and specifies a `getLabel` method to set and
    display the panel category name.

        @Override
        public String getLabel(Locale locale) {
            return LanguageUtil.get(locale, "control-panel");
        }

    If you'd like to provide functionality that is more complex, you can use
    JSPs or any other similar technology to render the panel category. You can
    easily do this by extending `BaseJSPPanelCategory`. For example, the
    [SiteAdministrationPanelCategory](https://github.com/liferay/liferay-portal/blob/7.0.3-ga4/modules/apps/web-experience/product-navigation/product-navigation-site-administration/src/main/java/com/liferay/product/navigation/site/administration/internal/application/list/SiteAdministrationPanelCategory.java)
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
