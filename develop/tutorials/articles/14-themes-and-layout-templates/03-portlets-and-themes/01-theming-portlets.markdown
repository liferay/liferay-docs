# Theming Portlets [](id=theming-portlets)

Themes can provide additional styles for your apps. You can change the markup 
for portlet containers by modifying the theme's `portlet.ftl` file.

This tutorial demonstrates how to style portlets with your themes.

## Portlet FTL [](id=portlet-ftl)

Although you can individually style a portlet via the theme's CSS or the 
portlet's *Look and Feel Configuration* menu, you may need or want to modify the 
default look and feel for all portlets in your site. A portlet's template--its 
container, CSS classes, and overall HTML Markup--is defined via the theme's 
`portlet.ftl` file. It is possible to add CSS classes, change markup, or add 
custom information to the `portlet.ftl`. To provide a default style for all 
portlets, use the CSS classes found in this file for the various container 
elements, in conjunction with the portlet decorators to achieve the desired look 
and feel. Be cautious when modifying this file; Any changes made affect all 
portlets in your site (when the theme is applied).  

To help you with your bearings as you modify your portlet's template, below is a 
quick look at the default 
[`portlet.ftl`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/foundation/frontend-theme/frontend-theme-classic/src/templates/portlet.ftl) 
that's included in the default theme of @product-ver@. The variables shown below 
are used throughout the template, so it's important that you understand them 
before modifying the file:

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
- `portlet_back_url`: URL to return to the previous page when the portlet 
  `WindowState` is maximized.
- `portlet_display_name`: The "friendly" name of the portlet as displayed in the
    GUI. 
- `portlet_display_root_portlet_id`: The root portlet ID of the portlet. 
- `portlet_id`: The ID of the portlet (not the same as the portlet namespace)
- `portlet_title`: The portlet name set in the portlet Java class 
  (usually from a `Keys.java` class).

Next, a condition checks if the portlet header should be displayed. If 
the portlet has a portlet toolbar (Configuration, Permissions, Look and Feel), 
the condition is true and the header is displayed:

    <#if portlet_display.isPortletDecorate() && !portlet_display.isStateMax() 
    && portlet_display.getPortletConfigurationIconMenu()?? 
    && portlet_display.getPortletToolbar()??>

You can use a similar pattern if you want to dynamically show portions of the 
portlet's UI. Next, the portlet title menus are defined. These are used in 
portlets that let you add resources (Web Content Display, Media Gallery, 
  Documents and Media):

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

Portlet Decorators are a powerful mechanism that let you customize the style of 
the application wrapper. Themes come bundled with three default portlet 
decorators in their `liferay-look-and-feel.xml`:

- `Barebone`: when this decorator is applied, neither the wrapping box nor the 
custom application title are shown. This option is recommended when you only 
want to display the bare application content.

- `Borderless`: when this decorator is applied, the application is no longer 
wrapped in a white box, but the application custom title is displayed at the top.

- `Decorate`: this is the default Portlet Decorator when using the Classic 
theme. When this decorator is applied, the application is wrapped in a white box 
with a border and the application custom title is displayed at the top.

You can learn how to create and apply your own portlet decorators in the section 
dedicated to 
[Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/portlet-decorators).

Now you know how you can theme your apps!

## Related Topics [](id=related-topics)

[Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/portlet-decorators)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-1/themes-and-layout-templates)