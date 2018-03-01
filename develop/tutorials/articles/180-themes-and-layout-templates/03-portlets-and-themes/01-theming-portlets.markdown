# Theming Portlets [](id=theming-portlets)

Themes can provide additional styles for your apps. You can change the markup 
for portlet containers by modifying the theme's `portlet.ftl` file.

This tutorial demonstrates how to style portlets with your themes.

## Portlet FTL [](id=portlet-ftl)

Although you can individually style a portlet via the theme's CSS or the
portlet's [Look and Feel Configuration](/discover/portal/-/knowledge_base/7-1/look-and-feel-configuration)
menu, you may want to modify the default look and feel for all portlets in your
site. A portlet's template--its container, CSS classes, and overall HTML
Markup--is defined via the theme's `portlet.ftl` file. To provide a custom style
for all portlets, use the CSS classes in this file for the various container
elements, in conjunction with the portlet decorators to achieve the desired look
and feel. Be cautious: changes to `portlet.ftl` affect all the portlets in your
site when the theme is applied.

To help you with your bearings as you modify your portlet's template, below is
a quick look at the
[`portlet.ftl`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/foundation/frontend-theme/frontend-theme-classic/src/templates/portlet.ftl)
file that's included in the default theme of @product-ver@.

    <#assign
        portlet_display = portletDisplay
        portlet_back_url = htmlUtil.escapeHREF(portlet_display.getURLBack())
        portlet_content_css_class = "portlet-content"
        portlet_display_name = htmlUtil.escape(portlet_display.getPortletDisplayName())
        portlet_display_root_portlet_id = htmlUtil.escapeAttribute(portlet_display.getRootPortletId())
        portlet_id = htmlUtil.escapeAttribute(portlet_display.getId())
        portlet_title = htmlUtil.escape(portlet_display.getTitle())
    />
 
The variables shown above are used throughout the template, so its important
that you understand them before modifying the file:

- `portletDisplay`: is fetched from the `themeDisplay` object and contains 
  information about the portlet.
- `portlet_back_url`: URL to return to the previous page when the portlet 
  `WindowState` is maximized.
- `portlet_display_name`: The "friendly" name of the portlet as displayed in the
    GUI. 
- `portlet_display_root_portlet_id`: The root portlet ID of the portlet. 
- `portlet_id`: The ID of the portlet (not the same as the portlet namespace)
- `portlet_title`: The portlet name set in the portlet Java class 
  (usually from a `Keys.java` class).

Next, a condition checks if the portlet header should be displayed. If the
portlet has a portlet toolbar (Configuration, Permissions, Look and Feel), the
condition is true and the header is displayed:

    <#if portlet_display.isPortletDecorate() && !portlet_display.isStateMax() 
    && portlet_display.getPortletConfigurationIconMenu()?? 
    && portlet_display.getPortletToolbar()??>

You can use a similar pattern if you want to dynamically show portions of the
portlet's UI. 

Next, the portlet title menus are defined. These are used in portlets that let
you add resources (Web Content Display, Media Gallery, Documents and Media):

    portlet_title_menus = portlet_toolbar.getPortletTitleMenus(portlet_display_root_portlet_id, renderRequest, renderResponse)

The configuration below contains the information for the configuration menu 
(Configuration, Permissions, Look and Feel):

    portlet_configuration_icons = portlet_configuration_icon_menu.getPortletConfigurationIcons(portlet_display_root_portlet_id, renderRequest, renderResponse)

The rest of the file contains the HTML markup for the portlet topper and the 
portlet content. This section barely scratches the surface of the `portlet.ftl` 
file. You must examine the `portlet.ftl` file yourself and determine what 
elements and classes need updated for your theme and site. 

Now that you are more familiar with your theme's `portlet.ftl` file, you can 
learn the role Portlet Decorators play in the overall look and feel of your 
portlets.

## Portlet Decorators [](id=portlet-decorators)

With Portlet Decorators, you can customize the style of 
the application wrapper. Themes come bundled with three default portlet 
decorators in their `liferay-look-and-feel.xml`:

-  `Barebone`: this decorator displays the bare application content, showing 
    neither the wrapping box nor the custom application title. 

-  `Borderless`: this decorator shows the title at the top, but does not display
    a wrapping box.

-  `decorate`: this is the default Portlet Decorator when using the Classic 
    theme. It wraps the application in a white box with a border, and displays the title at the top.

Now you know how to make your portlets stylish! But if default decorators are
not stylish enough for you, [EDITOR: Our defaults are always stylish!] go to
[Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/portlet-decorators) to
learn how make and apply your own.

## Related Topics [](id=related-topics)

[Look and Feel Configuration]( https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/look-and-feel-configuration)

[Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/portlet-decorators)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-1/themes-and-layout-templates)
