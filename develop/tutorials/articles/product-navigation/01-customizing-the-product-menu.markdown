# Customizing the Product Menu

Liferay's Product Menu comes with three major panel categories, by default: the
Control Panel, User Settings, and Site Administration. Panel categories are the
term used to differentiate between sections of the menu. For instance, the
Control Panel is a single panel category, and when clicking on it, you're
presented with four other child panel categories: *Users*, *Sites*, *Apps*, and
*Configuration*. It you click on one of these child panel categories, you're
presented with panel apps.

+$$$

**Note:** The Product Menu cannot be changed by applying a new theme. To change
the layout/style of the Product Menu, you must create and deploy a theme
contributor. See the
[Theme Contributors](/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
tutorial for more details.

$$$

<!-- Check link for above tutorial. Was not published yet when this was written.
-Cody -->

This construction of the Product Menu was designed to
be intuitive and easy to use. In many scenarios, however, developers want to add
another panel category with custom panel apps, for example. Also, maybe you'd
like to change the order of your panel categories and/or apps. In this tutorial,
you'll learn how to provide your own custom panel categories and panel apps for
the Product Menu.

## Adding Custom Panel Categories

Liferay provides an easy way to extend the Product Menu and customize it to
display what is most helpful in your particular situation. First, you'll learn
how to add a panel category.

1. Create a generic OSGi module using your favorite third party tool, or use
   [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli). 

2. Create a unique package name in the module's `src` directory and create a
   new Java class in that package. To follow naming conventions, give your
   category a unique name followed by *Category* (e.g., `ControlPanelCategory`).
   The class should extend

3. Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {
                "panel.category.key=" + PANEL_CATEGORY_KEY,
                "panel.category.order:Integer=INTEGER"
            },
            service = PanelCategory.class
        )

    The `property` element designates two properties that should be assigned for
    your category. The `panel.category.key` specifies the parent category for
    your custom category. You can find popular parent categories to assign in
    the [PanelCategoryKeys](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/application-list/application-list-api/src/main/java/com/liferay/application/list/constants/PanelCategoryKeys.java)
    class. For instance, if you wanted to create a category in the Control
    Panel, you could assign `PanelCategoryKeys.CONTROL_PANEL`. Likewise, if you
    wanted to create a root category, like the Control Panel or Site
    Administration, you could assign `PanelCategoryKeys.ROOT`.

    The `panel.category.order:Integer` property specifies the order in which
    your category is displayed. The higher the number (integer), the lower your
    category is listed among other sibling categories assigned to a parent.
    
    Lastly, your `service` element should specify the `PanelCategory.class`
    service. You can view an example of a similar `@Component` annotation in the
    [UserPanelCategory](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/product-navigation/product-navigation-control-panel/src/main/java/com/liferay/product/navigation/control/panel/application/list/UsersPanelCategory.java)
    class.

4. Implement the `PanelCategory` interface. A popular way to do this is by
   extending the
   [BasePanelCategory](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/application-list/application-list-api/src/main/java/com/liferay/application/list/BasePanelCategory.java)
   or 
   [BaseJSPPanelCategory](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/application-list/application-list-api/src/main/java/com/liferay/application/list/BaseJSPPanelCategory.java)
   abstract classes. Typically, the `BasePanelCategory` is extended for basic
   categories (e.g., the Control Panel category) that only display the category
   name or other simple functionality. If you'd like to provide more complex
   functionality, like a logo or buttons, the `BaseJSPPanelCategory` should be
   extended. This will be elaborated on my extensively later.

    +$$$

    **Note:** In this tutorial, JSPs are used to describe how to provide
    functionality to panel categories and apps. JSPs, however, are not the only
    way to provide frontend functionality to your categories/apps. You can
    create your own abstract class implementing `PanelCategory` and provide ways
    to use other technologies, such as FreeMarker.

    $$$

5. Since you're implementing the
   [PanelCategory](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/application-list/application-list-api/src/main/java/com/liferay/application/list/PanelCategory.java)
   interface, you'll need to implement its methods:

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
    [ControlPanelCategory](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/product-navigation/product-navigation-control-panel/src/main/java/com/liferay/product/navigation/control/panel/application/list/ControlPanelCategory.java)
    extends `BasePanelCategory` and specifies a `getLabel` method to set and
    display the panel category name.

    If you'd like to provide functionality that is more involved, you can use
    other technologies like JSPs to render the panel category. You can easily do
    this by extending `BaseJSPPanelCategory`. For example, the
    [SiteAdministrationPanelCategory](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/product-navigation/product-navigation-site-administration/src/main/java/com/liferay/product/navigation/site/administration/application/list/SiteAdministrationPanelCategory.java)
    specifies the `getHeaderJspPath` and `getJspPath` methods. You could create
    a JSP with the UI you'd like to render and specify its path in methods like
    these.

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
    expanded). You can inspect the
    [site_administration_header.jsp](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/product-navigation/product-navigation-site-administration/src/main/resources/META-INF/resources/sites/site_administration_header.jsp)
    and
    [site_administration_body.jsp](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/product-navigation/product-navigation-site-administration/src/main/resources/META-INF/resources/sites/site_administration_body.jsp)
    for examples.

Excellent! You've successfully created a custom panel category to display in the
Product Menu. In many cases, a panel category holds panel apps for users to
access. You'll learn about how to add a panel app to a panel category next.

## Adding Custom Panel Apps

Just as adding panel categories was straight-forward and dynamic, so too is the
process for adding panel apps.

1. 










