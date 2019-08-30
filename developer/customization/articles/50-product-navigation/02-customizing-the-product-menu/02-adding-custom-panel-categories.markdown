---
header-id: adding-custom-panel-categories
---

# Adding Custom Panel Categories

[TOC levels=1-4]

As you navigate the Product Menu, you can see that Panel Apps like *Web Content*
and *Settings* are organized into Panel Categories such as *Content & Data* and
*Configuration*. This article explains how to add new Panel Categories to the
menu. Adding new Panel Apps is covered in the next section.

There are three steps to creating a new category:

1.  Create the OSGi structure and metadata.

2.  Implement Liferay's Frameworks.

3.  Define the Panel Category.

## Creating the OSGi Module

First you must create the project.

1.  Create an OSGi module using your favorite third party tool, or use
    [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli). Blade CLI
    offers a 
    [Panel App](/docs/7-2/reference/-/knowledge_base/r/panel-app-template)
    template, which is for creating a Panel Category and Panel App.

2.  Create a unique package name in the module's `src` directory and create
    a new Java class in that package. To follow naming conventions, give your
    class a unique name followed by `PanelCategory` (e.g.,
    `ControlPanelCategory`).

## Implementing Liferay's Frameworks

Next, you must connect your OSGi module to Liferay's frameworks and use those to
define information about your entry. This takes only two steps: 

1.  Insert the `@Component` annotation declaring the panel category keys
    directly above the class's declaration:

    ```java
    @Component(
        immediate = true,
        property = {
            "panel.category.key=" + [Panel Category Key],
            "panel.category.order:Integer=[int]"
        },
        service = PanelCategory.class
    )
    ```

    You can view an example of a similar `@Component` annotation for the
    `UserPanelCategory` class below:

    ```java
    @Component(
        immediate = true,
        property = {
            "panel.category.key=" + PanelCategoryKeys.ROOT,
            "panel.category.order:Integer=200"
        },
        service = PanelCategory.class
    )
    ```

    The `property` element designates two properties that should be assigned for
    your category. The `panel.category.key` specifies the parent category for
    your custom category. You can find popular parent categories to assign in
    the [`PanelCategoryKeys`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/PanelCategoryKeys.html)
    class. For instance, if you wanted to create a child category in the Control
    Panel, you could assign `PanelCategoryKeys.CONTROL_PANEL`. Likewise, if you
    wanted to create a root category, like the Control Panel or Site
    Administration, you could assign `PanelCategoryKeys.ROOT`.
    
    The `panel.category.order:Integer` property specifies the order in which
    your category is displayed. The higher the number (integer), the lower your
    category is listed among other sibling categories assigned to a parent.

    | **Note:** To insert a Panel Category between existing categories in the
    | default menu, you must know the `panel.category.order:Integer` property
    | for the existing categories. For example, the Product Menu's two main
    | sections---Control Panel and Site Administration---have
    | `panel.category.order:Integer` properties of 100 and 200, respectively. A
    | new panel inserted between Control Panel and Site Administration would
    | need a `panel.category.key` of ROOT and a `panel.category.order:Integer`
    | of 150.

    Finally, your `service` element should specify the `PanelCategory.class`
    service.

2.  Implement the `PanelCategory` interface. See the
    [`PanelCategory` Interface](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu#panelcategory-interface)
    section for more details. Extending one of the provided base classes
    ([BasePanelCategory](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu#basepanelcategory)
    or
    [BaseJSPPanelCategory](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu#basejsppanelcategory))
    is a popular way to implement the `PanelCategory` interface.

3.  If you elect to leverage JSPs, you must also specify the servlet context
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

Excellent! You've successfully created a custom Panel Category to display in the
Product Menu. In many cases, a Panel Category holds Panel Apps for users to
access. You'll learn how to add a Panel App to a Panel Category next.
