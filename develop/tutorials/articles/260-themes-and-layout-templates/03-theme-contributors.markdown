# Theme Contributors [](id=theme-contributors)

If you'd like to package UI resources, independent of a specific theme, and
include them on a @product@ page, Theme Contributors are your best option. If,
instead, you'd like to include separate UI resources on a @product@ page that
are attached to a theme, you should look into
[themelets](/develop/tutorials/-/knowledge_base/7-0/themelets).

A Theme Contributor is a
[module](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Module)
that contains UI resources to use in @product@. Once a Theme Contributor is
deployed to @product@, it's scanned for all valid CSS and JS files, and then its
resources are included on the page. You can, therefore, style these UI
components as you like, and the styles are applied, regardless of the current
theme.

This tutorial demonstrates how to

- identify a Theme Contributor module.
- create a Theme Contributor module.

Now that you know what Theme Contributors are, you'll learn how to create one
next.

## Creating Theme Contributors [](id=creating-theme-contributors)

In Liferay versions prior to 7.0, the standard UI for User menus and navigation,
such as the Dockbar, was included in the theme template. Starting in Liferay
7.0, these standard UI components are packaged as Theme Contributors.

For example, the Control Menu, Product Menu, and Simulation Panel are packaged
as Theme Contributor modules in Liferay, separating them from the theme. This
means that these UI components must be handled outside the theme.

![Figure 1: The Control Menu, Product Menu, and Simulation Panel are packaged as Theme Contributor modules.](../../images/theme-contributor-menus-diagram.png)

If you want to edit or style these standard UI components, you'll need to create
your own Theme Contributor and add your modifications on top. You can also add
new UI components to @product@ by creating a Theme Contributor.

To create a Theme Contributor module, follow these steps:

1.  Create a generic OSGi module using your favorite third party tool, or use
    [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

2.  To identify your module as a Theme Contributor, you must add the
    `Liferay-Theme-Contributor-Type` and `Web-ContextPath` properties to your
    module's `bnd.bnd` file. For example, see the
    [Control Menu module's](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/product-navigation/com.liferay.product.navigation.control.menu.theme.contributor/)
    `bnd.bnd`:

        Bundle-Name: Liferay Product Navigation Control Menu Theme Contributor
        Bundle-SymbolicName: com.liferay.product.navigation.control.menu.theme.contributor
        Bundle-Version: 1.0.0
        Liferay-Releng-Module-Group-Description:
        Liferay-Releng-Module-Group-Title: Product Navigation
        Liferay-Theme-Contributor-Type: product-navigation-control-menu
        Web-ContextPath: /product-navigation-control-menu-theme-contributor
        -include: ../../../../../marketplace/web-content-management/bnd.bnd

    The Theme Contributor type helps @product@ better identify your module. For
    example, if you're creating a Theme Contributor to override an existing
    Theme Contributor, you should try to use the same type to maximize
    compatibility with future developments.

    <!-- leaving this section out for now, until it is developed further--

    Alternatively, Theme Contributors can be identifed in the `package.json`
    file:

        {
            "name": "lfr-product-menu-animation-themelet",
            "liferayTheme": {
                themeContributorType?: product-navigation-control-menu
                "themelet": true,
                "version": "7.0"
            }
        }

    If this property is set in either one of these ways, the module will then be
    treated as a Theme Contributor.-->

3.  Create a `src/main/resources/META-INF/resources` folder in your module
    and place your resources (CSS and JS) in that folder.

4.  Build and deploy your module to see your modifications applied to @product@
    pages and themes.

Theme Contributors do not guarantee the resource's insertion order. For
example, if you're creating a Theme Contributor to override a specific CSS
styling in a different module, you should make sure your CSS selectors are more
specific than the ones being overridden.

That's all you need to do to create a Theme Contributor for your site. Remember,
with great power comes great responsibility, so use Theme Contributors wisely.
The UI contributions affect every page, and will remain, regardless of the
theme.

## Related Topics [](id=related-topics)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme)
