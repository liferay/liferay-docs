# Theming Portlets [](id=theming-portlets)

Liferay themes have the ability to provide additional styles to a portlet. Through the `portlet.ftl` the developer can change markup for the containers that hold portlets.

## Portlet.ftl [](id=portlet-ftl)

Here is a quick look at the default [`portlet.ftl`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-theme/frontend-theme-classic/src/templates/portlet.ftl) that included in the default theme in @product_ver@.

```html
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

- `portletDisplay`: is fetched from the `themeDisplay` object and contains infromation about the portlet.
- `portlet_back_url`: URL to return to previous page with portlet `WindowState` is maximized
- `portlet_display_name`: Custom portlet name if set
- `portlet_display_root_portlet_id`:
- `portlet_id`: The id of portlet (not the same as the portlet namespace)
- `portlet_title`: Portlet name set in Portlet java class (usually from a `Keys.java` class).

The following condition checks to see the portlet header should be displayed. It is checking to see if there portlet toolbar for the portlet (Configuartion, Permissions, Look and Feel)

```
<#if portlet_display.isPortletDecorate() && !portlet_display.isStateMax() && portlet_display.getPortletConfigurationIconMenu()?? && portlet_display.getPortletToolbar()??>
```

The portlet title menus is used in portlets that allow for adding resources (Web Content Display, Media Gallery, Documents and Media). It used to build a menu of items for adding resources.

```
portlet_title_menus = portlet_toolbar.getPortletTitleMenus(portlet_display_root_portlet_id, renderRequest, renderResponse)
```

The line below contains the information for the configuration menu (Configuration, Permissions, Look and Feel)

```
portlet_configuration_icons = portlet_configuration_icon_menu.getPortletConfigurationIcons(portlet_display_root_portlet_id, renderRequest, renderResponse)
```

The rest of the file contains the HTML markup for the portlet topper and the portlet content. It is possible to add CSS classes, change markup, or add custom information. For styling the look and feel of all portlets, use the CSS classes found in this file in conjunction with the portlet decorators to achieve the desired look and feel.

## Portlet Decorators [](id=portlet-decorators)

In previous versions of @product@, administrators could display or hide the application borders through the Show Borders option of the look and feel configuration menu. In @product_ver@ this option has been replaced with Application Decorators, a more powerful mechanism to customize the style of the application wrapper.

### Out of the box portlet decorators

There are three out of the box portlet decorators that are added to your themes `liferay-look-and-feel.xml`:

- `Barebone`: when this decorator is applied, neither the wrapping box nor the custom application title are shown. This option is recommended when you only want to display the bare application content.
- `Borderless`: when this decorator is applied, the application is no longer wrapped in a white box, but the application custom title is displayed at the top.
- `Decorate`: this is the default Application Decorator when using the Classic theme. When this decorator is applied, the application is wrapped in a white box with a border and the application custom title is displayed at the top.

You can learn how to create and apply your own portlet decorators in the section dedicated to [Portlet Decorators](/develop/tutorials/-/knowledge_base/7-0/portlet-decorators).