# Theme Components and Workflow [](id=theme-components-and-workflow)

If you want to develop a website, you must have three key components: CSS, JS, 
and HTML. @product@ supports SASS as well as multiple JavaScript frameworks. The 
HTML, however, is rendered via 
[FreeMarker](https://freemarker.apache.org/) 
theme templates. This reference guide provides an overview of @product@'s theme 
development components and workflow, covering the following topics:

- Theme templates
- Theme customizations and extensions
- Portlet customizations and extensions
- Theme workflow
- CSS Frameworks and extensions

## Theme Templates [](id=theme-templates)

@product@ provides several default FreeMarker templates that each handle a key 
piece of functionality for the page:

- `portal_normal.ftl`: Similar to a static site’s `index.html`, this file acts 
  as a hub for all theme templates, as well as provides the basic markup for the 
  page.
- `init.ftl`: Contains common FreeMarker variables that are available to use in 
  your theme templates. Useful for reference if you need access to theme 
  objects. **We recommended that you DO NOT overwrite this file**.
- `init_custom.ftl`: Used to overwrite FreeMarker variables found in `init.ftl` 
  and to define any new ones such as 
  [theme setting](/develop/tutorials/-/knowledge_base/7-1/making-configurable-theme-settings) 
  variables.
- `portlet.ftl`: The theme template for the theme’s portlets. If your theme uses 
  Portlet Decorators, you can modify this file to create application 
  decorator-specific theme settings. See the 
  [Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/creating-configurable-styles-for-portlet-wrappers) 
  tutorial for more info.
- `navigation.ftl`: Contains the navigation markup. To customize pages in the 
  navigation, you must use the `liferay.navigation_menu` macro. This lets you 
  leverage 
  [ADTs](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/site-navigation/site-navigation-menu-web/src/main/resources/com/liferay/site/navigation/menu/web/portlet/template/dependencies) 
  for the navigation menu. Note that `navigation.ftl` also defines the hamburger 
  icon and `navbar-collapse` class that provides the simplified navigation 
  toggle for mobile viewports, as shown in the snippet below for the Classic 
  theme:

      <#if has_navigation && is_setup_complete>
        <button aria-controls="navigationCollapse" aria-expanded="false" 
        aria-label="Toggle navigation" class="navbar-toggler navbar-toggler-right" 
        data-target="#navigationCollapse" data-toggle="collapse" type="button">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
          <@liferay.navigation_menu default_preferences="${preferences}" />
        </div>
      </#if>

![Figure 1: The collapsed navbar provides simplified user-friendly navigation for mobile devices.](../../images/portal-layout-mobile-nav.png)

- `portal_pop_up.ftl`: The theme template for pop up dialogs for the theme's 
  portlets. Similar to `portal_normal.ftl`, `portal_pop_up.ftl` provides the 
  markup template for all pop-up dialogs, such as a portlet's Configuration 
  menu. It also has access to the FreeMarker variables defined in `init.ftl` and 
  `init_custom.ftl`.

![Figure 2: Each theme template provides a portion of the page's markup and functionality.](../../images/portal-layout-theme-templates.png)

### Theme Template Utilities [](id=theme-template-utilities)

@product@ provides several FreeMarker variables and macros that you can use in 
your theme templates to include portlets, use taglibs, access theme objects, and 
more. You can see examples of these used in `portal_normal`. These utilities are 
included in the files listed below:

- [`Init.ftl`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates/init.ftl): 
  Provides access to common theme variables
- [`FTL_Liferay.ftl`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl): 
  Provides macros for commonly used portlets and theme resources. See the 
  [Macros tutorial](/develop/tutorials/-/knowledge_base/7-1/using-liferays-macros-in-your-theme) 
  for more information.
- `taglib-mappings.properties`: 
  Maps the portal taglibs to FreeMarker macros. Taglibs let you quickly create 
  common UI components. This properties file is also provided 
  separately for each app taglib. For convenience, these FreeMarker macros are 
  listed in the 
  [FreeMarker Taglib Mappings reference guide](/develop/reference/-/knowledge_base/7-1/freemarker-taglib-macros).
  See the 
  [Taglib tutorials](/develop/tutorials/-/knowledge_base/7-1/front-end-taglibs) 
  for more information on using each taglib in your theme templates.

## CSS Frameworks and Extensions [](id=css-frameworks-and-extensions)

As noted above, @product@ supports the Sass CSS extension. This means that you 
can take full advantage of Sass mixins, nesting, partials, and variables in your 
CSS.

Also important to note is 
[Clay CSS](https://clayui.com/), 
the web implementation of Liferay's 
[Lexicon design language](https://lexicondesign.io/). 
An extension of Bootstrap, Clay CSS fills the gaps between Bootstrap and the 
needs of @product@, providing additional components and CSS patterns that you 
can use in your themes. Clay base, Liferay's Bootstrap API extension, along with 
Atlas, a custom Bootstrap theme, creates @product@'s Classic theme. See the 
[importing Clay CSS tutorial](/develop/tutorials/-/knowledge_base/7-1/importing-clay-css-into-a-theme) 
for more information.

## Theme Customizations and Extensions [](id=theme-customizations-and-extensions)

The theme templates, along with the CSS, provides much of the overall look and 
feel for the page, but additional extension points/customizations are available. 
The following extensions and mechanisms are available for themes:

- **Color Schemes:** specifies configurable color scheme settings for 
  Administrator's to configure via the Look and Feel menu. See the 
  [color scheme tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-color-schemes-for-your-theme) 
  for more information.
- **Configurable Theme Settings:** settings that let Administrators configure 
  aspects of a theme that may need changed frequently, such as controlling the 
  visibility of certain elements, changing a daily quote, etc. See the 
  [Configurable Theme Settings tutorial](/develop/tutorials/-/knowledge_base/7-1/making-configurable-theme-settings) 
  for more information. 
- **Context Contributor:** Exposes Java variables and functionality for  you to 
  use in your FreeMarker templates. This lets you use non-JSP templating languages 
  for themes, ADTs, and any other templates used in @product@. See the 
  [Context Contributors tutorial](/develop/tutorials/-/knowledge_base/7-1/injecting-additional-context-variables-into-your-templates) 
  or more information.
- **Theme Contributor:** a package containing UI resources, not attached to a 
  theme, that you want to include on every page. See the 
  [Theme Contributors tutorial](/develop/tutorials/-/knowledge_base/7-1/packaging-independent-ui-resources-for-your-site) 
  for more information. 
- **Themelet:** small, extendable, and reusable pieces of code that contain CSS 
  and JS. It can be shared with other developers to provide common components for 
  themes, and it only requires the files you want to extend. See the 
  [Themelets tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-reusable-pieces-of-code-for-your-themes) 
  for more information.

## Portlet Customizations and Extensions [](id=portlet-customizations-and-extensions)

You can customize portlets with these mechanisms and extensions:

- **Portlet FTL Customizations:** customize the base template markup for all 
  portlets. See the 
  [Theming Portlets tutorial](/develop/tutorials/-/knowledge_base/7-1/theming-portlets#portlet-ftl) 
  for more information.
- **Application Display Templates (ADTs):** provides an alternate display style 
  for a portlet. Note that not all portlets support ADTs. See the 
  [Application Display Templates (ADTs) User Guide](/discover/portal/-/knowledge_base/7-1/styling-widgets-with-application-display-templates) 
  for more information.
- **Portlet Decorator:** lets you customize the exterior decoration for a portlet. 
  See the 
  [Portlet Decorators tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-configurable-styles-for-portlet-wrappers) 
  for more information.
- **Web Content Template:** defines how structures are displayed for web content. 
  See the 
  [Web Content Templates User Guide articles](/discover/portal/-/knowledge_base/7-1/designing-web-content-with-templates) 
  for more information.

![Figure 3: There are several extension points for customizing portlets](../../images/portal-layout-portlet-customizations.png)

## Theme Workflow [](id=theme-workflow)

Themes are built on top of one of the following base themes: 

- **Unstyled:** provides basic markup, functions, and images for Portal
- **Styled:** inherits from the Unstyled base theme and adds some styling on top

@product@ lets you choose the development tools you're most comfortable 
with so you can focus on creating a well designed theme. The following tools let 
you build themes:

- [Theme Builder Gradle Plugin](/develop/reference/-/knowledge_base/7-1/theme-builder-gradle-plugin)
- [Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes)
- [Dev Studio](/develop/tutorials/-/knowledge_base/7-1/creating-themes-with-liferay-ide)
- [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)'s 
  [Theme Template](https://dev.liferay.com/develop/reference/-/knowledge_base/7-1/theme-template). 

Depending on the tool you choose 
(
  [Theme Generator](/develop/reference/-/knowledge_base/7-1/theme-reference-guide), 
  [Gradle](/develop/reference/-/knowledge_base/7-1/theme-builder-gradle-plugin), 
  [Blade CLI](/develop/reference/-/knowledge_base/7-1/theme-template), 
  [Maven](/develop/reference/-/knowledge_base/7-1/theme-template), 
  or 
  [Dev Studio](/develop/reference/-/knowledge_base/7-1/theme-template)
), 
the theme anatomy is a bit different. The overall development process is the 
same though: 

1.  Mirror the structure of the files you want to modify. The main modifications 
    are placed in the following files:

    - `portal_normal.ftl`: main theme markup
    - `_custom.scss`: custom CSS styling
    - `main.js`: the theme's JavaScript

2.  Build and deploy the theme to your @product@ server.

3.  Apply the theme 
    [through the Look and Feel menu](/discover/portal/-/knowledge_base/7-1/page-set-look-and-feel) 
    by selecting your 
    [theme's thumbnail](/develop/tutorials/-/knowledge_base/7-1/creating-a-thumbnail-preview-for-your-theme). 

The finished theme is bundled as a WAR (Web application ARchive) file. 

+$$$

**Note:** While developing your theme, we recommend that you enable 
[Developer Mode](/develop/tutorials/-/knowledge_base/7-1/using-developer-mode-with-themes). 
This will unminify JS files and disable caching for CSS and FreeMarker template 
files, making the debugging process much easier.

$$$

During theme development, if you’ve built your theme with the Liferay Theme 
Generator, there are some helpful Gulp tasks that you can use to make the 
process easier:

- **build:** builds your theme’s files based off of the specified base theme. 
  See the 
  [gulp build tutorial](/develop/tutorials/-/knowledge_base/7-1/building-your-themes-files) 
  for more information.
- **extend:** sets the base theme or themelet to extend. See the 
  [gulp extend tutorial](/develop/tutorials/-/knowledge_base/7-1/changing-your-base-theme) 
  for more information.
- **init:** specifies the app server to deploy your theme to (automatically run 
  during the initial. See the 
  [gulp init tutorial](/develop/tutorials/-/knowledge_base/7-1/configuring-your-themes-app-server) 
  for more information. 
- **kickstart:** copies files from an existing theme into your theme to help 
  kickstart it. See the 
  [gulp kickstart tutorial](/develop/tutorials/-/knowledge_base/7-1/copying-an-existing-themes-files) 
  for more information.
- **status:** lists the base theme/themelets that your theme extends. See the 
  [gulp status tutorial](/develop/tutorials/-/knowledge_base/7-1/listing-your-themes-extensions) 
  for more information.
- **watch:** watches for changes to your theme’s files and automatically deploys 
  them to the server when a change is made. See the 
  [gulp watch tutorial](/develop/tutorials/-/knowledge_base/7-1/automatically-deploying-theme-changes) 
  for more information.
