# Theming Portlets [](id=theming-portlets)

@product@ themes can provide additional styles to a portlet. You can change the
markup for the portlet containers by modifying the `portlet.ftl` file.

This tutorial demonstrates how to style portlets with your themes.

## Portlet FTL [](id=portlet-ftl)

Here is a quick look at the default [`portlet.ftl`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-theme/frontend-theme-classic/src/templates/portlet.ftl) 
that's included in the default theme of @product-ver@:

    <#assign
            portlet_display = portletDisplay
    
            portlet_back_url = htmlUtil.escapeHREF(portlet_display.getURLBack())
            portlet_content_css_class = "portlet-content"
            portlet_display_name = htmlUtil.escape(portlet_display.getPortletDisplayName())
            portlet_display_root_portlet_id = htmlUtil.escapeAttribute(portlet_display.getRootPortletId())
            portlet_id = htmlUtil.escapeAttribute(portlet_display.getId())
            portlet_title = htmlUtil.escape(portlet_display.getTitle())
    />
 
An explanation of each variable used in `portlet.ftl` is shown below:

- `portletDisplay`: is fetched from the `themeDisplay` object and contains 
  information about the portlet.
- `portlet_back_url`: URL to return to the previous page with portlet 
  `WindowState` is maximized.
- `portlet_display_name`: The "friendly" name of the portlet as displayed in the
    GUI. 
- `portlet_display_root_portlet_id`: Sets the 
- `portlet_id`: The ID of the portlet (not the same as the portlet namespace)
- `portlet_title`: The portlet name set in the portlet Java class 
  (usually from a `Keys.java` class).

The following condition checks if the portlet header should be displayed. If 
the portlet has a portlet toolbar (Configuration, Permissions, Look and Feel), 
the condition is true:

    <#if portlet_display.isPortletDecorate() && !portlet_display.isStateMax() 
    && portlet_display.getPortletConfigurationIconMenu()?? 
    && portlet_display.getPortletToolbar()??>

Portlet title menus are used in portlets that allow you to add resources 
(Web Content Display, Media Gallery, Documents and Media). This is used to build 
a menu of items for adding resources:

    portlet_title_menus = portlet_toolbar.getPortletTitleMenus(portlet_display_root_portlet_id, renderRequest, renderResponse)

The configuration below contains the information for the configuration menu 
(Configuration, Permissions, Look and Feel):

    portlet_configuration_icons = portlet_configuration_icon_menu.getPortletConfigurationIcons(portlet_display_root_portlet_id, renderRequest, renderResponse)

The rest of the file contains the HTML markup for the portlet topper and the 
portlet content. It is possible to add CSS classes, change markup, or add custom 
information to the `portlet.ftl`. To provide a default style for all portlets, 
use the CSS classes found in this file, in conjunction with the portlet 
decorators to achieve the desired look and feel.

Portlet Decorators are explained in more detail next.

## Portlet Decorators [](id=portlet-decorators)

In previous versions of @product@, administrators could display or hide the 
application borders through the Show Borders option of the look and feel 
configuration menu. In @product-ver@ this option has been replaced with 
Portlet Decorators, a more powerful mechanism to customize the style of the 
application wrapper.

The default portlet decorators are covered next.

### Default Portlet Decorators [](id=default-portlet-decorators)

Themes come bundled with three default portlet decorators in their 
`liferay-look-and-feel.xml`. These are listed below:

- `Barebone`: when this decorator is applied, neither the wrapping box nor the 
custom application title are shown. This option is recommended when you only 
want to display the bare application content.

- `Borderless`: when this decorator is applied, the application is no longer 
wrapped in a white box, but the application custom title is displayed at the top.

- `Decorate`: this is the default Portlet Decorator when using the Classic 
theme. When this decorator is applied, the application is wrapped in a white box 
with a border and the application custom title is displayed at the top.

You can learn how to create and apply your own portlet decorators in the section 
dedicated to [Portlet Decorators](/develop/tutorials/-/knowledge_base/7-0/portlet-decorators).

Now you know how to make your portlets stylish!

## Related Topics [](id=related-topics)

[Portlet Decorators](/develop/tutorials/-/knowledge_base/7-0/portlet-decorators)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/themes-and-layout-templates)
