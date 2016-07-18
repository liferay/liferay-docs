# Theme Contributors [](id=theme-contributors)

If you would like to package UI resources, independent of a specific theme, and 
include them on the page, Theme Contributors are the tool for you.

If instead you would like to include separate UI resources on the page that are 
attached to a theme, you should look into [themelets](/develop/tutorials/-/knowledge_base/7-0/themelets).

A theme contributor is a [module](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Module)
that contains UI resources to use in Liferay 7 and DXP. The module is scanned 
for all valid CSS and JS files, and then includes the resources on the page.

You can therefore style these UI components as you like, and the styles will be
applied, regardless of the current theme.

This tutorial assumes that you have already created your module project. You can
learn how to create a module with [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli).

This tutorial demonstrates how to:

- Identify a Theme Contributor Module
- Edit a Theme Contributor UI Component

Now that you know what theme contributors are, you can learn how to configure 
your module to be identified as one.

## Identifying Theme Contributor Modules [](id=identifying-theme-contributor-modules)

In Liferay versions prior to 7.0, the standard UI for User menus and navigation,
such as the Dockbar, was included in the theme template. Starting in Liferay 7.0, 
these standard UI components are packaged as theme contributors.

Specifically, the Control Menu, Product Menu, and Simulation Panel, are packaged 
in theme contributor modules in Liferay, removing them from a theme. Instead, 
styles for these specific UI components can be handled outside of the theme.

![Figure 1: The Control Menu, Product Menu, and Simulation Panel are packaged as theme contributor modules.](../../images/theme-contributor-menus-diagram.png)

If you want to edit or style these standard UI components, you'll need to modify
the module directly and rebuild it. More on this in a bit.

To identify your module as a theme contributor add the 
`Liferay-Theme-Contributor-Type` property to the `bnd.bnd` file: 

For example the [Control Menu](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/product-navigation/product-navigation-control-menu-theme-contributor/bnd.bnd) 
has the configuration below:

    Bundle-Name: Liferay Product Navigation Control Menu Theme Contributor
    Bundle-SymbolicName: com.liferay.product.navigation.control.menu.theme.contributor
    Bundle-Version: 1.0.0
    Liferay-Releng-Module-Group-Description:
    Liferay-Releng-Module-Group-Title: Product Navigation
    Liferay-Theme-Contributor-Type: product-navigation-control-menu
    Web-ContextPath: /product-navigation-control-menu-theme-contributor
    -include: ../../../../../marketplace/web-content-management/bnd.bnd

+$$$

**Note:**  All theme contributor modules will also require a Web-ContextPath to 
be set in the manifest.

$$$

<!-- leaving this section out for now, until it is developed further--

Alternatively, theme contributors can be identifed in the `package.json` file:

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

Now that you can identify theme contributors, you can learn how to style them 
next.

## Editing Theme Contributors [](id=editing-theme-contributors)

Theme contributors are, simply, packaged UI components, as mentioned earlier. To
edit a theme contributor UI component, for instance to style one, follow these 
steps:

1.  Open the Command Line and navigate to the theme contributor module. For
    instance, the Product Menu Theme Contributor module resides in the following
    directory:

        liferay-portal/modules/apps/web-experience/product-navigation/
        product-navigation-product-menu-theme-contributor

2.  Navigate to the `src/main/resources/META-INF/resources` directory for the
    module and edit the file you want to update.

3.  Save the file and navigate back to the module's root directory.
        
4.  Run `../../../../../gradlew build` to build the updated jar file.

5.  Copy the updated jar file from the module's `build` directory into your
    Liferay bundle `osgi/modules` directory to deploy the UI changes to the
    server.
   
That's all you need to do to create a theme contributor for your site. Remember,
with great power there must also come–great responsibility, so use theme 
contributors wisely. The UI contributions will affect every page, and will 
remain, regardless of the theme.

## Related Topics [](id=related-topics)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme)
