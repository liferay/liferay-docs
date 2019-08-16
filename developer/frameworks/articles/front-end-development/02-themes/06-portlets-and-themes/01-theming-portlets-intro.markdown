---
header-id: theming-portlets
---

# Theming Portlets

[TOC levels=1-4]

Although you can individually style a portlet via the theme's CSS or the 
portlet's [Look and Feel Configuration](/docs/7-2/user/-/knowledge_base/u/look-and-feel-configuration) 
menu, you may want to modify the default look and feel for all portlets in your 
site. A portlet's template--its container, CSS classes, and overall HTML 
Markup--is defined via the theme's `portlet.ftl` file. To provide a custom style 
for all portlets, use the CSS classes in this file for the various container 
elements along with the portlet decorators to achieve the desired look and feel. 
Be cautious: changes to `portlet.ftl` affect all the portlets in your site when 
the theme is applied. 

To help you with your bearings as you modify your portlet's template, below is 
a quick look at the 
[`portlet.ftl`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/frontend-theme/frontend-theme-classic/src/templates/portlet.ftl) 
file that's included in @product-ver@'s Classic theme. 

```markup
<#assign
    portlet_display = portletDisplay
    portlet_back_url = htmlUtil.escapeHREF(portlet_display.getURLBack())
    portlet_content_css_class = "portlet-content"
    portlet_display_name = htmlUtil.escape(portlet_display.getPortletDisplayName())
    portlet_display_root_portlet_id = htmlUtil.escapeAttribute(portlet_display.getRootPortletId())
    portlet_id = htmlUtil.escapeAttribute(portlet_display.getId())
    portlet_title = htmlUtil.escape(portlet_display.getTitle())
/>
```

These variables are described in the table below:

**Portlet FTL Variables**

| Variable | Description |
| --- | --- |
| `portletDisplay` | Fetched from the `themeDisplay` object, contains information about the portlet |
| `portlet_back_url` | URL to return to the previous page when the portlet `WindowState` is maximized |
| `portlet_display_name` | The "friendly" name of the portlet as displayed in the GUI |
| `portlet_display_root_portlet_id` | The root portlet ID of the portlet |
| `portlet_id` | The ID of the portlet (not the same as the portlet namespace) |
| `portlet_title` | The portlet name set in the portlet Java class (usually from a `Keys.java` class) |

Next, a condition checks if the portlet header should be displayed. If the 
portlet has a portlet toolbar (Configuration, Permissions, Look and Feel), the 
condition is true and the header is displayed:

```markup
<#if portlet_display.isPortletDecorate() && !portlet_display.isStateMax() 
&& portlet_display.getPortletConfigurationIconMenu()?? 
&& portlet_display.getPortletToolbar()??>
```

You can use a similar pattern if you want to dynamically show portions of the 
portlet's UI. 

Next, the portlet title menus are defined. These are used in portlets that let 
you add resources (Web Content Display, Media Gallery, Documents and Media):

```markup
portlet_title_menus = portlet_toolbar.getPortletTitleMenus(portlet_display_root_portlet_id, renderRequest, renderResponse)
```

The configuration below contains the information for the configuration menu 
(Configuration, Permissions, Look and Feel):

```markup
portlet_configuration_icons = portlet_configuration_icon_menu.getPortletConfigurationIcons(portlet_display_root_portlet_id, renderRequest, renderResponse)
```

The rest of the file contains the HTML markup for the portlet topper and the 
portlet content. This section barely scratches the surface of the `portlet.ftl` 
file. You must examine the `portlet.ftl` file yourself and determine what 
elements and classes need updated for your theme and site. 

Now that you are more familiar with your theme's `portlet.ftl` file, you can 
learn the role Portlet Decorators play in the overall look and feel of your 
portlets. 

## Portlet Decorators

Portlet Decorators modify the style of the application wrapper. Themes come 
bundled with three default portlet decorators, defined in 
`liferay-look-and-feel.xml`:

-   Decorate: this is the default Application Decorator when using the Classic 
    theme. It wraps the application in a white box with a border, and displays
    the title at the top. 

    ![Figure 1: The Classic theme's Decorate Application Decorator wraps the portlet in a white box.](../../../../images/application-decorator-decorate.png)

-   Borderless: this decorator shows the title at the top, but does not display
    a wrapping box. 

    ![Figure 2: The Classic theme's Borderless Application Decorator displays the application's custom title.](../../../../images/application-decorator-borderless.png)

-   Barebone: this decorator displays the bare application content, showing 
    neither the wrapping box nor the custom application title. 

    ![Figure 3: The Classic theme's Barebone Application Decorator displays only the application's content.](../../../../images/application-decorator-barebone.png)

| **Note:** Upgrading to @product@ assigns the *borderless* decorator
| automatically to those portlets that had the *Show Borders* option set to false
| in previous versions of Liferay.

This section covers these topics:

- Embedding Portlets in Themes
