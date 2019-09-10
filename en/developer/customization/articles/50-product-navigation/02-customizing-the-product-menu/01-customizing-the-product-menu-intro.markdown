---
header-id: customizing-the-product-menu
---

# Customizing the Product Menu

[TOC levels=1-4]

Customizing the Product Menu can be completed by adding Panel Categories and
Panel Apps.

| **Note:** The Product Menu cannot be changed by applying a new theme. To
| change the layout/style of the Product Menu, you must create and deploy a
| theme contributor. See the
| [Theme Contributors](/docs/7-2/frameworks/-/knowledge_base/f/packaging-independent-ui-resources-for-your-site)
| article for more details.

To create these entities, you must implement the
[`PanelCategory`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/PanelCategory.html)
and
[`PanelApp`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/PanelApp.html)
interfaces.

## PanelCategory Interface

The `PanelCategory` interface requires you to implement the following methods:

- `getNotificationCount`: returns the number of notifications to be shown in
  the Panel Category.
- `include`: renders the body of the Panel Category.
- `includeHeader`: renders the Panel Category header.
- `isActive`: whether the panel is selected.
- `isPersistState`: whether to persist the Panel Category's state to the
  database. This saves the state of the Panel Category when navigating away from
  the menu.

You can reduce the number of methods you must implement if you extend a base
class that already implements the `PanelCategory` interface. The recommended way
to do this is by extending the
[`BasePanelCategory`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/BasePanelCategory.html)
or
[`BaseJSPPanelCategory`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/BaseJSPPanelCategory.html)
abstract classes. Typically, the `BasePanelCategory` is extended for basic
categories (e.g., the Control Panel category) that only display the category
name. To add more complex functionality, you can then provide a custom UI for
your panel using any front-end technology by implementing the `include()` or
`includeHeader()` from the `PanelCategory` interface.

If you plan to use JSPs as the front-end technology, extend a base class called
`BaseJSPPanelCategory` that already implements the methods `include()` and
`includeHeader()` for you.
 
| **Note:** In this article, example JSPs describe how to provide functionality
| to Panel Categories and Panel Apps. JSPs, however, are not the only way to provide
| front-end functionality to your categories/apps. You can create your own class
| implementing `PanelCategory` to use other technologies such as FreeMarker.

More information on provided base classes for your `PanelCategory`
implementation are described next.

### BasePanelCategory

If you need something simple for your Panel Category like a name, extending
`BasePanelCategory` is probably sufficient. For example, the
[`ControlPanelCategory`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/product-navigation/product-navigation-control-panel/src/main/java/com/liferay/product/navigation/control/panel/internal/application/list/ControlPanelCategory.java)
extends `BasePanelCategory` and specifies a `getLabel` method to set and display
the Panel Category name.

```java
@Override
public String getLabel(Locale locale) {
    return LanguageUtil.get(locale, "control-panel");
}
```

### BaseJSPPanelCategory

If you need more complex functionality, extend `BaseJSPPanelCategory` and use
JSPs to render the Panel Category. For example, the
[`SiteAdministrationPanelCategory`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/product-navigation/product-navigation-site-administration/src/main/java/com/liferay/product/navigation/site/administration/internal/application/list/SiteAdministrationPanelCategory.java)
specifies the `getHeaderJspPath` and `getJspPath` methods. You could create
a JSP with the UI you want to render and specify its path in methods like these:

```java
@Override
public String getHeaderJspPath() {
    return "/sites/site_administration_header.jsp";
}

@Override
public String getJspPath() {
    return "/sites/site_administration_body.jsp";
}
```

One JSP renders the Panel Category's header (displayed when panel is collapsed)
and the other its body (displayed when panel is expanded).

Next, you'll learn about the `PanelApp` interface.

## PanelApp Interface

The `PanelApp` interface requires you to implement the following methods:

- `getNotificationCount`: returns the number of notifications for the user.
- `getPortlet`: returns the portlet associated with the application.
- `getPortletId`: returns the portlet's ID associated with the application.
- `getPortletURL`: returns the URL used to render a portlet based on the servlet
  request attributes.
- `include`: Returns `true` if the application successfully renders.
- `setGroupProvider`: sets the group provider associated with the application.
- `setPortlet`: sets the portlet associated with the application.

You can reduce the number of methods you must implement if you extend a base
class that already implements the `PanelCategory` interface. The recommended way
to do this is by extending the
[`BasePanelApp`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/BasePanelApp.html)
or
[`BaseJSPPanelApp`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html)
abstract classes. If you want to use JSPs to render that UI, extend
`BaseJSPPanelApp`. This provides additional methods you can use to incorporate
JSP functionality into your app's listing in the Product Menu.

| **Note:** JSPs are not the only way to provide front-end functionality to your
| Panel Apps. You can create your own class implementing `PanelApp` to use other
| technologies such as FreeMarker.

The `BlogsPanelApp` is a simple example of how to specify your portlet as a
Panel App. This class extends `BasePanelApp`, overriding the `getPortletId` and
`setPortlet` methods. These methods specify and set the Blogs portlet as a Panel
App.

This is how those methods look for the Blogs portlet:

```java
@Override
public String getPortletId() {
    return BlogsPortletKeys.BLOGS_ADMIN;
}

@Override
@Reference(
    target = "(javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN + ")",
    unbind = "-"
)
public void setPortlet(Portlet portlet) {
    super.setPortlet(portlet);
}
```

Each Panel App must belong to a portlet and each portlet can have at most one
Panel App. If more than one Panel App is needed, another portlet must be
created. By default, the Panel App only appears if the user has permission to
view the associated portlet.

Continue on the learn about creating custom Panel Categories and Panel Apps.
