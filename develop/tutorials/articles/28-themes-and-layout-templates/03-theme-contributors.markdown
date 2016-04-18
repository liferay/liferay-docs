# Theme Contributors [](id=theme-contributors)

In Liferay versions prior to 7.0, the standard UI for User menus and navigation,
such as the Dockbar, was included in the theme template. Starting in Liferay 7.0, 
these standard UI components are packaged as Theme Contributors.

A Theme Contributor is a [module](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Module)
that contains UI resources to use in the Liferay platform. The module is scanned
for all valid CSS and JS files, and then includes them on the page.

Specifically, the Control Menu, Product Menu, and Simulation Panel, are packaged 
in theme contributor modules. Each of these theme contributor modules live 
within Liferay, by default. This means that new themes don't have to copy all of 
the styles of these standard UI. Instead, styles for these specific UI
components can be handled outside of the theme. 

You can therefore style these UI components as you like, and the styles will be
applied, regardless of the current theme. 

This tutorial demonstrates the following:

**How to Identify a Theme Contributor Module**

**How to Style a Theme Contributor UI Component**

Now that you know what theme contributors are, you can learn how to identify
them next.

## Theme Contributor Properties [](id=theme-contributor-properties)

There are two ways to identify an OSGi module as a Theme Contributor. 

In the Manifest:

    Bundle-Name: Liferay Product Navigation Control Menu Theme Contributor
    Bundle-SymbolicName: com.liferay.product.navigation.control.menu.theme.contributor
    Bundle-Version: 1.0.0
    Liferay-Releng-Module-Group-Description:
    Liferay-Releng-Module-Group-Title: Product Navigation
    Liferay-Theme-Contributor-Type: product-navigation-control-menu
    Web-ContextPath: /product-navigation-control-menu-theme-contributor
    -include: ../../../../../marketplace/web-content-management/bnd.bnd

**Note:**  All theme contributor modules will also require a Web-ContextPath to 
be set in the manifest.

In a `package.json` file:

    {
        "name": "lfr-product-menu-animation-themelet",
        "liferayTheme": {
            themeContributorType?: product-navigation-control-menu
            "themelet": true,
            "version": "7.0"
        }
    }

If this property is set in either one of these ways, the module will then be 
treated as a Theme Contributor.

Now that you can identify theme contributors, you can learn how to style them 
next.

## Styling Theme Contributors [](id=styling-theme-contributors)

Theme contributors are, simply, packaged UI components, as mentioned earlier. To
style a theme contributor UI component follow these steps:

1. Open the Command Line and navigate to the theme contributor module. For
    instance, the Product Menu Theme Contributor module resides in the following
    directory: 
    
        liferay-portal/modules/apps/web-experience-management/product-navigation/product-navigation-control-menu-theme-contributor

2. Navigate to the `src/main/resources/META-INF/resources` directory for the
   module and edit the SCSS file you want to update the styles for.
   
3. Save the file and navigate back to the module's root directory.
        
4. Run `../../../../../gradlew build` to build the updated jar file.

5. Copy the updated jar file from the module's `build` directory into your
   Liferay bundle `osgi/modules` directory to deploy the UI changes to the
   server.
   
Your standard UI menus are now updated!

## Related Topics [](id=related-topics)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme)
