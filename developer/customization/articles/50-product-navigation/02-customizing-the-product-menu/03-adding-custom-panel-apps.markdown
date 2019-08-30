---
header-id: adding-custom-panel-apps
---

# Adding Custom Panel Apps

[TOC levels=1-4]

After you have created a Panel Category, create a Panel App to go in it:

1.  Create an OSGi module using your favorite third party tool, or use
    [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli). Blade CLI
    offers a
    [Panel App](/docs/7-2/reference/-/knowledge_base/r/panel-app-template)
    template to help generate a basic Panel Category and Panel App.

2.  Create a unique package name in the module's `src` directory, and create a
    new Java class in that package. To follow naming conventions, give your class
    a unique name followed by *PanelApp* (e.g., `JournalPanelApp`).

3.  Directly above the class's declaration, insert the following annotation:

    ```java
    @Component(
        immediate = true,
        property = {
            "panel.app.order:Integer=INTEGER"
            "panel.category.key=" + PANEL_CATEGORY_KEY,
        },
        service = PanelApp.class
    )
    ```

    You can view an example of a similar `@Component` annotation for the
    `JournalPanelApp` class below.

    ```java
    @Component(
        immediate = true,
        property = {
            "panel.app.order:Integer=100",
            "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
        },
        service = PanelApp.class
    )
    ```

    These properties and attributes are similar to those discussed in
    the previous
    [article](/docs/7-2/customization/-/knowledge_base/c/adding-custom-panel-categories).
    The `panel.category.key` assigns your Panel App to a Panel Category. The
    `panel.app.order:Integer` property specifies the order your Panel App
    appears among other Panel Apps in the same category. For example, if you
    want to add a Panel App to Site Administration &rarr; *Content & Data*, add
    the following property:

    ```java
    "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
    ```

    Visit the
    [PanelCategoryKeys](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/constants/PanelCategoryKeys.html)
    class for keys you can use to specify default Panel Categories in Liferay.

    Set the `service` attribute to `PanelApp.class`. 

4.  Implement the `PanelApp` interface. See the
    [`PanelApp` Interface](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu#panelapp-interface)
    section for more details. Extending one of the provided base classes
    ([BasePanelApp](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/BasePanelApp.html)
    or
    [BaseJSPPanelApp](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html))
    is a popular way to implement the `PanelApp` interface. See the
    [PanelApp Interface](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu#panelapp-interface)
    section for more information.

5.  If you elect to leverage JSPs, you must also specify the servlet context
    from where you are loading the JSP files. If this is inside an OSGi module,
    make sure your `bnd.bnd` file has defined a web context path:

    ```
    Bundle-SymbolicName: com.sample.my.module.web
    Web-ContextPath: /my-module-web
    ```

    Then reference the Servlet context using the symbolic name of your module
    like this:

    ```java
    @Override
    @Reference(
        target = "(osgi.web.symbolicname=com.sample.my.module.web)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
    ```

Now you know how to add or modify a Panel App in the Product Menu. Not only does
Liferay provide a simple solution to add new Panel Categories and Panel Apps, it
also gives you the flexibility to add a more complex UI to the Product Menu
using any technology.
