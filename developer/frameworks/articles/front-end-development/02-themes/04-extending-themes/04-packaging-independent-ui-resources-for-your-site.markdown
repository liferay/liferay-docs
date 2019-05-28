---
header-id: packaging-independent-ui-resources-for-your-site
---

# Packaging Independent UI Resources for Your Site

[TOC levels=1-4]

If you want to package UI resources independent of a specific theme and include 
them on every page, a *Theme Contributor* is your best option. If, instead, you 
want to include separate UI resources on a page that are attached to a theme, 
use 
[themelets](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator).

A Theme Contributor is a module that contains CSS and JS resources to apply to 
the page. The Control Menu, Product Menu, and Simulation Panel are packaged as 
Theme Contributors. 

![Figure 1: The Control Menu, Product Menu, and Simulation Panel are packaged as Theme Contributor modules.](../../../../images/theme-contributor-menus-diagram.png)

If you want to edit or style these standard UI components, you must create a 
Theme Contributor and add your modifications on top. You can also add new UI 
components to @product@ by creating a Theme Contributor. This article shows how 
to create a Theme Contributor module. 

Follow these steps to create a Theme Contributor:

1.  Create a generic OSGi module using your favorite third party tool, or use 
    [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli). You can 
    also use the 
    [Blade Template](/docs/7-2/reference/-/knowledge_base/r/theme-contributor-template) 
    to create your module, in which case you can skip step 2. 

2.  Add the `Liferay-Theme-Contributor-Type` header to your module's `bnd.bnd` 
    file to identify your module as a Theme Contributor, and add the 
    `Web-ContextPath` header to set the context from which the Theme 
    Contributor's resources are hosted. See the 
    [Control Menu module's](https://search.maven.org/search?q=a:com.liferay.product.navigation.control.menu.theme.contributor) 
    `bnd.bnd` below as an example:

    ```properties
    Bundle-Name: Liferay Product Navigation Product Menu Theme Contributor
    Bundle-SymbolicName: com.liferay.product.navigation.product.menu.theme.contributor
    Bundle-Version: 3.0.4
    Liferay-Theme-Contributor-Type: product-navigation-product-menu
    Web-ContextPath: /product-navigation-product-menu-theme-contributor
    ```

    The Theme Contributor type helps @product@ better identify your module. If 
    you're creating a Theme Contributor to override an existing Theme 
    Contributor, you should try to use the same type to maximize compatibility 
    with future developments. 

3.  Add the `Liferay-Theme-Contributor-Weight` to your `bnd.bnd` file to set a 
    priority for your Theme Contributor. To override another Theme Contributor's 
    styles, such as those for the Control Menu, set a higher weight. The higher 
    the value, the higher the priority. If your Theme Contributor has a weight 
    of 100, it will be loaded after one with a weight of 99, allowing your CSS 
    to override theirs:

    ```properties
    Liferay-Theme-Contributor-Weight: [value]
    ```

4.  Create a `src/main/resources/META-INF/resources` folder in your module and 
    place your resources (CSS and JS) in that folder.

5.  Build and deploy your module to see your modifications applied to @product@ 
    pages and themes.

That's all you need to do to create a Theme Contributor for your site. Remember, 
with great power comes great responsibility, so use Theme Contributors wisely. 
The UI contributions affect every page and aren't affected by theme deployments. 

## Related Topics

- [Developing Themes](/docs/7-2/frameworks/-/knowledge_base/f/developing-themes)
- [Generating Themelets](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator)
- [Installing a Themelet](/docs/7-2/frameworks/-/knowledge_base/f/installing-a-themelet-in-your-theme)
