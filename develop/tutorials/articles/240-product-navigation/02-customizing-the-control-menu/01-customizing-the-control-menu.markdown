# Creating Control Menu Entries [](id=creating-control-menu-entries)

Now you'll create entries to customize the Control Menu. Make sure to read
[Adding Custom Panel Categories](/develop/tutorials/-/knowledge_base/7-1/adding-custom-panel-categories)
before beginning this tutorial. This tutorial assumes you know how to create a 
panel category. Creating a Control Menu Entry follows the same pattern as
creating a category:

1.  Create the OSGi structure and metadata.

2.  Implement Liferay's Frameworks.

3.  Define the Control Menu Entry. 

## Creating the OSGi Module [](id=creating-the-osgi-module)

First you must create the project.

1.  Create a generic OSGi module. Your module must contain a Java class, 
    `bnd.bnd` file, and build file (e.g., `build.gradle` or `pom.xml`). You'll 
    create your Java class next if your project does not already define one.

2.  Create a unique package name in the module's `src` directory and create a
    new Java class in that package. Give your class a unique name followed by 
    *ProductNavigationControlMenuEntry* 
    (e.g.,`StagingProductNavigationControlMenuEntry`).

## Implementing Liferay's Frameworks [](id=implementing-liferays-frameworks)

Next, you need to connect your OSGi module to Liferay's frameworks and use those
to define information about your entry.

1.  Directly above the class's declaration, insert this code:

        @Component(
            immediate = true,
            property = {
                "product.navigation.control.menu.category.key=" + [Control Menu Category],
                "product.navigation.control.menu.category.order:Integer=[int]"
            },
            service = ProductNavigationControlMenuEntry.class
        )

    The `product.navigation.control.menu.category.key` property specifies your
    entry's category. The default Control Menu provides three categories: Sites
    (left portion), Tools (middle portion), and User (right portion).

    ![Figure 1: This image shows where your entry will reside depending on the category you select.](../../../images/control-menu-areas.png)

    To specify the category, reference the appropriate key in the
    [ProductNavigationControlMenuCategoryKeys](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/constants/ProductNavigationControlMenuCategoryKeys.html)
    class. For example, this property places your entry in the middle portion of
    the Control Menu:

        "product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.TOOLS

    Like panel categories, you must specify an integer to place your entry in
    the category. Entries are ordered from left to right: an entry with order
    `1` appears to the left of an entry with order `2`. If the order is
    not specified, it's chosen at random based on which service was registered
    first in the OSGi container. Finally, your `service` element should specify
    the `ProductNavigationControlMenuEntry.class` service.

4.  Implement the [`ProductNavigationControlMenuEntry`](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/ProductNavigationControlMenuEntry.html)
    interface. You can also extend the [`BaseProductNavigationControlMenuEntry`](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/BaseProductNavigationControlMenuEntry.html)
    or [`BaseJSPProductNavigationControlMenuEntry`](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/BaseJSPProductNavigationControlMenuEntry.html)
    abstract classes. Typically, the `BaseProductNavigationControlMenuEntry` is
    extended for basic entries (e.g.,
    `IndexingProductNavigationControlMenuEntry`) that only display a link with
    text or a simple icon. If you want to provide a more complex UI with
    buttons or a sub-menu, you can override the `include()` and
    `includeBody()` methods. If you use JSPs for generating the UI, you can
    extend `BaseJSPProductNavigationControlMenuEntry` to save time. This
    is covered in more detail below.

## Defining the Control Menu Entry [](id=defining-the-control-menu-entry)

Now you must define your Control Menu Entry. Here are some examples for defining
your entry.

### Control Menu Examples [](id=control-menu-examples)

The
[`IndexingProductNavigationControlMenuEntry`](https://github.com/liferay/liferay-portal/blob/7.0.3-ga4/modules/apps/foundation/portal-search/portal-search-web/src/main/java/com/liferay/portal/search/web/internal/product/navigation/control/menu/IndexingProductNavigationControlMenuEntry.java)
is a simple example for providing text and an icon. It extends the
`BaseProductNavigationControlMenuEntry` class and is used when Liferay is
indexing. The indexing entry is displayed in the *Tools* (middle) area of the
Control Menu with a *Refresh* icon and text stating *The Portal is currently
indexing*. 

The
[`ProductMenuProductNavigationControlMenuEntry`](https://github.com/liferay/liferay-portal/blob/7.0.3-ga4/modules/apps/web-experience/product-navigation/product-navigation-product-menu-web/src/main/java/com/liferay/product/navigation/product/menu/web/internal/product/navigation/control/menu/ProductMenuProductNavigationControlMenuEntry.java)
is more sophisticated. This entry appears in the *Sites* (left) area of the
Control Menu, but unlike the previous example, it extends the
`BaseJSPProductNavigationControlMenuEntry` class. This provides several more
methods that use JSPs to define your entry's UI. There are two methods to notice:
 
        @Override
        public String getBodyJspPath() {
            return "/portlet/control_menu/product_menu_control_menu_entry_body.jsp";
        }

        @Override
        public String getIconJspPath() {
            return "/portlet/control_menu/product_menu_control_menu_entry_icon.jsp";
        }

The `getIconJspPath()` method provides the Product Menu icon
(![Menu Closed](../../../images/icon-menu.png)
&rarr; ![Menu Open](../../../images/icon-menu-open.png)), and the 
`getBodyJspPath()` method adds the UI body for the entry outside of the Control 
Menu. The latter method must be used when providing a UI outside the Control 
Menu. You can test this by opening and closing the Product Menu on the home
page.

Finally, if you provide functionality that is exclusively inside the Control
Menu, the `StagingProductNavigationControlMenuEntry` class calls its JSP like
this:

        @Override
        public String getIconJspPath() {
            return "/control_menu/entry.jsp";
        }

The `entry.jsp` is returned, which embeds the Staging Bar
portlet into the Control Menu.

You must also specify the servlet context for the JSP files. If this is inside
an OSGi module, make sure your `bnd.bnd` file defines a web context path:

        Bundle-SymbolicName: com.sample.my.module.web
        Web-ContextPath: /my-module-web

And then reference the Servlet context using the symbolic name of your
module:

        @Override
        @Reference(
            target = "(osgi.web.symbolicname=com.sample.my.module.web)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            super.setServletContext(servletContext);
        }

### Displaying Your Control Menu Entry [](id=displaying-your-control-menu-entry)

Part of creating the entry is defining when it appears. The Control Menu
shows different entries depending on the displayed page. You can specify when
your entry appears with the `isShow(HttpServletRequest)` method.

For example, the `IndexingProductNavigationControlMenuEntry` class queries
the number of indexing jobs when calling `isShow`. If the query count is
`0`, the indexing entry doesn't appear in the Control Menu:

        @Override
        public boolean isShow(HttpServletRequest request) throws PortalException {
            int count = _indexWriterHelper.getReindexTaskCount(
                CompanyConstants.SYSTEM, false);

            if (count == 0) {
                return false;
            }

            return super.isShow(request);
        }

The `StagingProductNavigationControlMenuEntry` class selects the pages to
appear. The staging entry never appears if the page is an administration page
(e.g., *Site Administration*, *My Account*, etc.):

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

### Defining Dependencies [](id=defining-dependencies)

Define dependencies for your Control Menu Entry in your build file (e.g., 
`build.grade` or `pom.xml`). For example, some popular dependencies (in Gradle
format) are defined below:

        dependencies {
            compileOnly group: "com.liferay", name: "com.liferay.product.navigation.control.menu.api", version: "[VERSION]"
            compile group: "com.liferay", name: "com.liferay.product.navigation.taglib", version: "[VERSION]"
            compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "[VERSION]"
            compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "[VERSION]"
            compile group: "javax.servlet.jsp", name: "javax.servlet.jsp-api", version: "[VERSION]"
            compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "[VERSION]"
        }

Your project may require more dependencies, depending on your module's
functionality.

Excellent! You've created your entry in one of the three default panel
categories in the Control Menu. You learned a basic way and an advanced way of
providing that entry, and learned how to apply both.
