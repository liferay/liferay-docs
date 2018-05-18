# Context Contributors [](id=context-contributors)

JSP templates are the predominant templating framework in @product@. Themes,
application display templates (ADTs), DDM templates, and more make use of JSPs
as a templating engine. JSPs, however, are not the only templating language
supported by @product@. Since many developers prefer other templating frameworks
(e.g., FreeMarker), @product@ enables you to use other template formats by 
offering the Context Contributors framework.

Because JSPs are "native" to Java EE, they have access to all the contextual
objects inherent to the platform, like the request and the session. Through
these objects, developers can normally obtain @product@-specific context
information by accessing container objects like `themeDisplay` or
`serviceContext`. 

Template formats like FreeMarker aren't native to Java EE, so they don't have
access to these objects. If your template needs contextual information such as
the current user, the page, or anything else, Java EE won't make it available to
the template like it does for JSPs: you must inject it yourself into the
template. @product@, however, gives you a head start by injecting a
`contextObjects` map of common variables (e.g., `themeDisplay`, `locale`,
`user`, etc.) by default into FreeMarker templates (e.g., themes). This map is
usually referred to as the *context* of a template. If you need to access some
other context object that @product@ doesn't provide by default, you must modify 
or add to a template's context. To do that, you create a context contributor.

Context contributors modify a template's context by injecting variables and
functionality usable by the template framework. This lets you use non-JSP
templating languages for themes, ADTs, and any other templates used in
@product@. For example, suppose you want your theme to change color based on the
user's organization. You could create a context contributor to inject the user's
organization to your theme's context, and then determine the theme's color based
on that information.

Context contributors are already used in @product@ by default. @product@'s 
Product Menu display is determined by a variable injected by a context 
contributor. You'll learn more about this later.

First, you'll learn how to create your own context contributor, and then you'll
examine one example of how @product@ uses context contributors.

1.  Create a generic OSGi module using your favorite third party tool, or use
    [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli).

2.  Create a unique package name in the module's `src` directory and create a
    new Java class in that package. To follow naming conventions, begin the
    class name with the entity you want to inject context-specific variables
    for, followed by *TemplateContextContributor* (e.g.,
    `ProductMenuTemplateContextContributor`).

3.  Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {"type=" + TemplateContextContributor.[Type of Contributor]},
            service = TemplateContextContributor.class
        )

    The `immediate` element instructs the module to start immediately once
    deployed to @product@. The `type` property should be set to one of the two
    fields defined in the
    [TemplateContextContributor](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html)
    interface: `TYPE_GLOBAL` or `TYPE_THEME`. The `TYPE_THEME` field should be
    set if you only wish to inject context-specific variables for your theme;
    otherwise, setting the `TYPE_GLOBAL` field affects every context execution
    in @product@, like themes, ADTs, DDM templates, etc. Finally, your `service`
    element should be set to `TemplateContextContributor.class`.

    The [ProductMenuTemplateContextContributor](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/product/menu/theme/contributor/internal/ProductMenuTemplateContextContributor.html)
    class's `@Component` annotation follows a similar layout:

        @Component(
            immediate = true,
            property = {"type=" + TemplateContextContributor.TYPE_THEME},
            service = TemplateContextContributor.class
        )

4.  Implement the
    [TemplateContextContributor](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html)
    interface in your `-TemplateContextContributor` class. This only requires
    implementing the `prepare(Map<String,Object>, HttpServletRequest)` method.

    Notice that the `prepare` method receives the `contextObjects` map as a
    parameter. This is your template's context that was described earlier. This
    method should be used to edit the context by injecting new or modified
    variables into the `contextObjects` map.

For a quick example of how you can implement the `TemplateContextContributor`
interface to inject variables into a template's context, examine the 
[`ProductMenuTemplateContextContributor` class](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/product/menu/theme/contributor/internal/ProductMenuTemplateContextContributor.html)
used by @product@ by default. This class injects variables into @product@'s
FreeMarker theme and determines whether the Product Menu is displayed in the
current theme.

The `ProductMenuTemplateContextContributor` class implements the `prepare(...)`
method, which injects a modified variable (`bodyCssClass`) and a new variable
(`liferay_product_menu_state`) into the theme context:

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

The `prepare` method above also determines whether to show the Product Menu or
not with the following if statement:

    if (!isShowProductMenu(request)) {
        return;
    }

The `isShowProductMenu(...)` method injects functionality into the theme's
context by providing an option to show/hide the Product Menu. This method is
also included in the `ProductMenuTemplateContextContributor` class:

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

The `ProductMenuTemplateContextContributor` provides an easy way to inject
variables into @product@'s theme directly related to the Product Menu. You can
do the same with your custom context contributor. With the power to inject
additional variables to any context in @product@, you're free to fully harness the
power of your chosen templating language.

## Related Topics [](id=related-topics)

[Customizing the Product Menu](/develop/tutorials/-/knowledge_base/7-1/customizing-the-product-menu)

[Creating Themes](/develop/tutorials/-/knowledge_base/7-1/creating-themes)

[Theme Contributors](/develop/tutorials/-/knowledge_base/7-1/theme-contributors)
