---
header-id: theme-components
---

# Theme Components

[TOC levels=1-4]

This guide provides an overview of the theme development components and 
workflow, covering the following topics:

- [Theme Templates](#theme-templates)
- [CSS Frameworks and Extensions](#css-frameworks-and-extensions)
- [Theme Customizations and Extensions](#theme-customizations-and-extensions)
- [Portlet Customizations and Extensions](#portlet-customizations-and-extensions)

## Theme Templates

There are several default FreeMarker templates, and each handle a key piece of
page functionality: 

- `portal_normal.ftl`: Similar to a static site's `index.html`, this file is hub
  for all theme templates and provides the overall markup for the page.
- `init.ftl`: Contains common FreeMarker variables that can be used in your
  theme templates. Useful for reference if you need access to theme objects.
  **DO NOT override this file**.
- `init_custom.ftl`: Used to override FreeMarker variables in `init.ftl` and to
  define new variables, such as 
  [theme settings](/developer/frameworks/-/knowledge_base/7-2/making-configurable-theme-settings).
- `portlet.ftl`: Controls the theme's portlets. If your theme uses 
  Portlet Decorators, modify this file to create application decorator-specific
  theme settings. See the 
  [Portlet Decorators](/developer/frameworks/-/knowledge_base/7-2/creating-configurable-styles-for-portlet-wrappers) 
  tutorial for more info.
- `navigation.ftl`: Contains the navigation markup. To customize pages in the
  navigation, you must use the `liferay.navigation_menu` macro. Then you can
  leverage
  [ADTs](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/site-navigation/site-navigation-menu-web/src/main/resources/com/liferay/site/navigation/menu/web/portlet/template/dependencies)
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

![Figure 1: The collapsed navbar provides simplified user-friendly navigation for mobile devices.](../../../images/portal-layout-mobile-nav.png)

- `portal_pop_up.ftl`: Controls pop up dialogs for the
  theme's portlets. Similar to `portal_normal.ftl`, `portal_pop_up.ftl` provides
  the markup template for all pop-up dialogs, such as a portlet's Configuration 
  menu. It also has access to the FreeMarker variables defined in `init.ftl` and 
  `init_custom.ftl`.

![Figure 2: Each theme template provides a portion of the page's markup and functionality.](../../../images/portal-layout-theme-templates.png)

### Theme Template Utilities

There are several FreeMarker variables and macros you can use to include
portlets, use taglibs, access theme objects, and more. Examples of these appear
in `portal_normal.ftl`. These utilities are included in the files listed below:

- [`Init.ftl`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates/init.ftl):
  Provides access to common theme variables.
- [`FTL_Liferay.ftl`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl): 
  Provides 
  [macros](/developer/reference/-/knowledge_base/7-2/product-freemarker-macros) 
  for commonly used portlets and theme resources. 
- `taglib-mappings.properties`: Maps the portal taglibs to FreeMarker macros.
  Taglibs can quickly create common UI components. This properties file is 
  provided separately for each app taglib. For convenience, these FreeMarker
  macros appear in the 
  [FreeMarker Taglib Mappings reference guide](/develop/reference/-/knowledge_base/7-2/freemarker-taglib-macros). See
  the 
  [Taglib tutorials](/developer/frameworks/-/knowledge_base/7-2/front-end-taglibs) for
  more information on using each taglib in your theme templates.

## CSS Frameworks and Extensions

As noted above, you can take full advantage of Sass mixins, nesting, partials,
and variables in your CSS.

Also important to note is 
[Clay CSS](https://clayui.com/), 
the web implementation of Liferay's 
[Lexicon design language](https://lexicondesign.io/). 
An extension of Bootstrap, Clay CSS fills the gaps between Bootstrap and the 
needs of @product@, providing additional components and CSS patterns that you 
can use in your themes. Clay base, Liferay's Bootstrap API extension, along with 
Atlas, a custom Bootstrap theme, creates @product@'s Classic theme. See the 
[importing Clay CSS tutorial](/developer/frameworks/-/knowledge_base/7-2/importing-clay-css-into-a-theme) 
for more information.

## Theme Customizations and Extensions

The theme templates, along with the CSS, provide much of the overall look and 
feel for the page, but additional extension points/customizations are available. 
The following extensions and mechanisms are available for themes:

- **Color Schemes:** Specifies configurable color scheme settings Administrators
  can configure via the Look and Feel menu. See the 
  [color scheme tutorial](/developer/frameworks/-/knowledge_base/7-2/creating-color-schemes-for-your-theme)
  for more information.
- **Configurable Theme Settings:** Settings for Administrators to configure
  theme aspects that change frequently, such as the visibility of
  certain elements, changing a daily quote, etc. See the 
  [Configurable Theme Settings tutorial](/developer/frameworks/-/knowledge_base/7-2/making-configurable-theme-settings)
  for more information. 
- **Context Contributor:** Exposes Java variables and functionality for use in
  FreeMarker templates. This allows non-JSP templating languages in themes,
  ADTs, and any other templates. See the 
  [Context Contributors tutorial](/developer/frameworks/-/knowledge_base/7-2/injecting-additional-context-variables-into-your-templates)
  or more information.
- **Theme Contributor:** A package containing UI resources, not attached to a 
  theme, that you want to include on every page. See the 
  [Theme Contributors tutorial](/developer/frameworks/-/knowledge_base/7-2/packaging-independent-ui-resources-for-your-site) 
  for more information. 
- **Themelet:** Small, extendable, and reusable pieces of code containing CSS
  and JavaScript. They can be shared with other developers to provide common
  components for themes. See 
  [Generating Themelets](/docs/7-2/reference/-/knowledge_base/reference/creating-themelets-with-the-themes-generator)
  for more information.

## Portlet Customizations and Extensions

You can customize portlets with these mechanisms and extensions:

- **Portlet FTL Customizations:** Customize the base template markup for all 
  portlets. See the 
  [Theming Portlets tutorial](/developer/frameworks/-/knowledge_base/7-2/theming-portlets#portlet-ftl) 
  for more information.
- **Application Display Templates (ADTs):** Provides an alternate display style 
  for a portlet. Note that not all portlets support ADTs. See the 
  [Application Display Templates (ADTs) User Guide](/discover/portal/-/knowledge_base/7-2/styling-widgets-with-application-display-templates) 
  for more information.
- **Portlet Decorator:** Customizes the exterior decoration for a portlet. 
  See the 
  [Portlet Decorators tutorial](/developer/frameworks/-/knowledge_base/7-2/creating-configurable-styles-for-portlet-wrappers) 
  for more information.
- **Web Content Template:** Defines how structures are displayed for web content. 
  See the 
  [Web Content Templates User Guide articles](/discover/portal/-/knowledge_base/7-2/designing-web-content-with-templates) 
  for more information.

![Figure 3: There are several extension points for customizing portlets](../../../images/portal-layout-portlet-customizations.png)

## Related Topics

- [Understanding the Page Layout](/developer/frameworks/-/knowledge_base/7-2/understanding-the-page-layout)
- [Installing the Theme Generator and Creating a Theme](/docs/7-2/reference/-/knowledge_base/reference/installing-the-theme-generator-and-creating-a-theme)
- [Developing Themes](/developer/frameworks/-/knowledge_base/7-2/developing-themes)
