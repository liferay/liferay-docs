---
header-id: creating-control-menu-entries
---

# Creating Control Menu Entries

[TOC levels=1-4]

Now you'll create entries to customize the Control Menu. Make sure to read
[Adding Custom Panel Categories](/docs/7-2/customization/-/knowledge_base/c/adding-custom-panel-categories)
before beginning this article. This article assumes you know how to create a 
Panel Category. Creating a Control Menu Entry follows the same pattern as
creating a Panel Category:

1.  Create the OSGi structure and metadata.

2.  Implement Liferay's Frameworks.

3.  Define the Control Menu Entry. 

## Creating the OSGi Module

First you must create the project. 

1.  Create a generic OSGi module. Your module must contain a Java class, 
    `bnd.bnd` file, and build file (e.g., `build.gradle` or `pom.xml`). You'll 
    create your Java class next if your project does not already define one.

2.  Create a unique package name in the module's `src` directory and create a
    new Java class in that package. Give your class a unique name followed by 
    *ProductNavigationControlMenuEntry* 
    (e.g.,`StagingProductNavigationControlMenuEntry`).

## Implementing Liferay's Frameworks

Next, you need to connect your OSGi module to Liferay's frameworks and use those
to define information about your entry.

1.  Directly above the class's declaration, insert this code:

    ```java
    @Component(
        immediate = true,
        property = {
            "product.navigation.control.menu.category.key=" + [Control Menu Category],
            "product.navigation.control.menu.category.order:Integer=[int]"
        },
        service = ProductNavigationControlMenuEntry.class
    )
    ```

    The `product.navigation.control.menu.category.key` property specifies your
    entry's category. The default Control Menu provides three categories: Sites
    (left portion), Tools (middle portion), and User (right portion).

    To specify the category, reference the appropriate key in the
    [ProductNavigationControlMenuCategoryKeys](@app-ref@/product-navigation/latest/javadocs/com/liferay/product/navigation/control/menu/constants/ProductNavigationControlMenuCategoryKeys.html)
    class. For example, this property places your entry in the middle portion of
    the Control Menu:

    ```java
    "product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.TOOLS
    ```

    Like Panel Categories, you must specify an integer to place your entry in
    the category. Entries are ordered from left to right: an entry with order
    `1` appears to the left of an entry with order `2`. If the order is
    not specified, it's chosen at random based on which service was registered
    first in the OSGi container.

    Finally, your `service` element should specify the
    `ProductNavigationControlMenuEntry.class` service.

2.  Implement the [`ProductNavigationControlMenuEntry`](@app-ref@/product-navigation/latest/javadocs/com/liferay/product/navigation/control/menu/ProductNavigationControlMenuEntry.html)
    interface. You can also extend the
    [`BaseProductNavigationControlMenuEntry`](@app-ref@/product-navigation/latest/javadocs/com/liferay/product/navigation/control/menu/BaseProductNavigationControlMenuEntry.html)
    or
    [`BaseJSPProductNavigationControlMenuEntry`](@app-ref@/product-navigation/latest/javadocs/com/liferay/product/navigation/control/menu/BaseJSPProductNavigationControlMenuEntry.html)
    abstract classes. See the
    [Customizing the Control Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-control-menu)
    article for more information on these classes.

3.  If you elect to leverage JSPs, you must specify the servlet context for the
    JSP files. If this is inside an OSGi module, make sure your `bnd.bnd` file
    defines a web context path:

    ```
    Bundle-SymbolicName: com.sample.my.module.web
    Web-ContextPath: /my-module-web
    ```

    And then reference the Servlet context using the symbolic name of your
    module:

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

4.  Part of creating the entry is defining when it appears. The Control Menu
    shows different entries depending on the displayed page. You can specify
    when your entry appears with the `isShow(HttpServletRequest)` method.

    For example, the `IndexingProductNavigationControlMenuEntry` class queries
    the number of indexing jobs when calling `isShow`. If the query count is
    `0`, the indexing entry doesn't appear in the Control Menu:

    ```java
    @Override
    public boolean isShow(HttpServletRequest request) throws PortalException {
        int count = _indexWriterHelper.getReindexTaskCount(
            CompanyConstants.SYSTEM, false);

        if (count == 0) {
            return false;
        }

        return super.isShow(request);
    }
    ```

    The `StagingProductNavigationControlMenuEntry` class selects the pages to
    appear. The staging entry never appears if the page is an administration
    page (e.g., *Site Administration*, *Control Panel*, etc.):

    ```java
    @Override
    public boolean isShow(HttpServletRequest request) throws PortalException {
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
            WebKeys.THEME_DISPLAY);

        Layout layout = themeDisplay.getLayout();

        // This controls if the page is an Administration Page

        if (layout.isTypeControlPanel()) {
            return false;
        }

        // This controls if Staging is enabled

      if (!themeDisplay.isShowStagingIcon()) {
            return false;
        }

        return true;
    }
    ```

Excellent! You've created your entry in one of the three default sections in the
Control Menu.
