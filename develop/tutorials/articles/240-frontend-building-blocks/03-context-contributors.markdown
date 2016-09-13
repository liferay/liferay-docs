# Context Contributors

JSP templates are the predominant templating framework used throughout
@product@'s themes, application display templates (ADTs), DDM templates, etc.
JSPs, however, are not the only templating language supported by @product@.
Many developers prefer other templating frameworks (e.g., FreeMarker and
Velocity), and Liferay caters to those developers by offering the Context
Contributors framework.

Context Contributors change the context of templates that get rendered by
injecting variables related to your chosen template framework. This lets you use
non-JSP templating languages to create themes, ADTs, and any other templates
used in @product@. For example, suppose you want your theme to have access to
the user to display something about them. You could create a context contributor
to inject the User object to your theme's context. Anyone creating a theme could
get the User object and use it.

You can also provide additional functionality out-of-the-box for a context using
context contributors. For instance, you could pass in a variable to a menu's
theme that determines whether or not to display it.

@product@ uses context contributors in several areas by default to inject
context-specific variables into a theme. You'll learn how to create your own
context contributor and look at one way @product@ uses them next.

1.  Create a generic OSGi module using your favorite third party tool, or use
    the [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

2.  Create a unique package name in the module's `src` directory and create a
    new Java class in that package. To follow naming conventions, begin the
    class name with the entity you want to inject context-specific variables
    into, followed by *TemplateContextContributor* (e.g.,
    `ProductMenuTemplateContextContributor`).

3.  Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {"type=" + TemplateContextContributor.[Type of Contributor]},
            service = TemplateContextContributor.class
        )

    The `immediate` element instructs the module to immediately start once
    deployed to @product@. The `type` property should be set to one of the two
    fields defined in the
    [TemplateContextContributor](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html)
    interface: `TYPE_GLOBAL` or `TYPE_THEME`. The `TYPE_THEME` field should be
    set if you only wish to inject context-specific variables for your theme;
    otherwise, setting the `TYPE_GLOBAL` field affects every context execution
    in @product@, like themes, ADTs, DDM templates, etc. Finally, your `service`
    element should be set to `TemplateContextContributor.class`.

    The [ProductNavigationTemplateContextContributor](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/product-navigation/com.liferay.product.navigation.product.menu.theme.contributor/com/liferay/product/navigation/product/menu/theme/contributor/internal/ProductMenuTemplateContextContributor.html)
    class's `@Component` annotation follows a similar layout:

        @Component(
            immediate = true,
            property = {"type=" + TemplateContextContributor.TYPE_THEME},
            service = TemplateContextContributor.class
        )

4.  Implement the
    [TemplateContextContributor](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html)
    interface in your `-TemplateContextContributor` class.

For a quick example of how you can implement the `TemplateContextContributor`
interface to inject context-specific variables into a context, you'll examine
the [ProductNavigationTemplateContextContributor](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/product-navigation/com.liferay.product.navigation.product.menu.theme.contributor/com/liferay/product/navigation/product/menu/theme/contributor/internal/ProductMenuTemplateContextContributor.html)
class used by @product@ by default. This class injects variables into Liferay's
FreeMarker theme and determines whether the Product Menu is displayed in the
current theme.

The `ProductNavigationTemplateContextContributor` class implements the
`prepare(...)` method, which injects new variables into the theme context:

    @Override
    public void prepare(
        Map<String, Object> contextObjects, HttpServletRequest request) {

        if (!isShowProductMenu(request)) {
            return;
        }

        String cssClass = GetterUtil.getString(
            contextObjects.get("bodyCssClass"));
        String productMenuState = SessionClicks.get(
            request,
            ProductNavigationProductMenuWebKeys.
                PRODUCT_NAVIGATION_PRODUCT_MENU_STATE,
            "closed");

        contextObjects.put(
            "bodyCssClass", cssClass + StringPool.SPACE + productMenuState);

            contextObjects.put("liferay_product_menu_state", productMenuState);
    }

This method prepares the context contributor to inject variables into the theme
to be used by the Product Menu. For this example, the `cssClass` and
`productMenuState` variables are defined and then placed in the `contextObjects`
map. By doing this, these variables have been injected into the theme context,
making them accessible to the theme. Specifically, the `cssClass` variable
provides styling for the Product Menu and the `productMenuState` variable
determines whether the visible Product Menu should be open or closed.

+$$$

**Note:** In previous versions of Liferay, if you needed to inject variables
into themes, you were forced to create those variables in the `init.ftl` file of
every theme. This forced theme developers to keep that logic updated in every
theme version of every theme they developed. With context contributors, you can
inject variables into existing frameworks without forcing theme developers to
update their `init.ftl` files.

$$$

The `prepare` method above also determines whether to show the product menu or
not with the following if statement:

    if (!isShowProductMenu(request)) {
        return;
    }

The `isShowProductMenu(...)` method injects functionality into the theme's
context by providing an option to show/hide the Product Menu. This method is
also included in the `ProductNavigationTemplateContextContributor` class:

    protected boolean isShowProductMenu(HttpServletRequest request) {
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
            WebKeys.THEME_DISPLAY);

        if (themeDisplay.isImpersonated()) {
            return true;
        }

        if (!themeDisplay.isSignedIn()) {
            return false;
        }

        User user = themeDisplay.getUser();

        if (!user.isSetupComplete()) {
            return false;
        }

        return true;
    }

Terrific! You've learned how to create your own context contributor and studied
an example of how @product@ uses one to manage its Product Menu.

## Related Topics

[Customizing the Product Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)

[Frontend Customizations](/develop/tutorials/-/knowledge_base/7-0/frontend-customizations)

[Theme Contributors](/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
