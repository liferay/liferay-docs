---
header-id: customizing-the-control-menu
---

# Customizing the Control Menu

[TOC levels=1-4]

Liferay's Control Menu consists of three main sections: Sites (left portion),
Tools (middle portion), and User (right portion).

![Figure 1: This image shows where your entry will reside depending on the category you select.](../../../images/control-menu-areas.png)

| **Note:** You can add the Control Menu to a theme by adding the following
| snippet into your `portal_normal.ftl`:
| 
| ```
| <@liferay.control_menu />
| ```
| 
| The other product navigation menus (e.g., Product Menu, Simulation Menu) are
| included in this tag, so specifying the above snippet embeds all three menus
| into your theme. Embedding the User Personal Menu is slightly different. Visit
| the
| [Providing the User Personal Menu](/docs/7-2/customization/-/knowledge_base/c/providing-the-user-personal-menu)
| article for more information.

You can reference a sample Control Menu Entry by visiting the
[Control Menu Entry](/docs/7-2/reference/-/knowledge_base/r/control-menu-entry-template)
article.

## ProductNavigationControlMenuEntry Interface

To create a control menu entry, you must implement the
[`ProductNavigationControlMenuEntry`](@app-ref@/product-navigation/latest/javadocs/com/liferay/product/navigation/control/menu/ProductNavigationControlMenuEntry.html)
interface. It's recommended to implement this interface by extending the
[`BaseProductNavigationControlMenuEntry`](@app-ref@/product-navigation/latest/javadocs/com/liferay/product/navigation/control/menu/BaseProductNavigationControlMenuEntry.html)
or
[`BaseJSPProductNavigationControlMenuEntry`](@app-ref@/product-navigation/latest/javadocs/com/liferay/product/navigation/control/menu/BaseJSPProductNavigationControlMenuEntry.html)
abstract classes. 


 These base classes are
covered in more
detail next.

### BaseProductNavigationControlMenuEntry

Typically, the `BaseProductNavigationControlMenuEntry` is extended for basic
entries that only display a link with text or a simple icon. If you want to
provide a more complex UI with buttons or a sub-menu, you can override the
`include()` and `includeBody()` methods.

The
[`IndexingProductNavigationControlMenuEntry`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-web/src/main/java/com/liferay/portal/search/web/internal/product/navigation/control/menu/IndexingProductNavigationControlMenuEntry.java)
is a simple example for providing text and an icon. It extends the
`BaseProductNavigationControlMenuEntry` class and is used when Liferay is
indexing. The indexing entry is displayed in the *Tools* (middle) area of the
Control Menu with a *Refresh* icon and text stating *The Portal is currently
indexing*.

### BaseJSPProductNavigationControlMenuEntry

If you use JSPs for generating the UI, you can extend
`BaseJSPProductNavigationControlMenuEntry` to save time when creating/modifying
a control menu entry.

The
[`ProductMenuProductNavigationControlMenuEntry`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/product-navigation/product-navigation-product-menu-web/src/main/java/com/liferay/product/navigation/product/menu/web/internal/product/navigation/control/menu/ProductMenuProductNavigationControlMenuEntry.java)
creates an entry that appears in the *Sites* (left) area of the Control Menu.
This class extends the `BaseJSPProductNavigationControlMenuEntry` class. This
provides several more methods that use JSPs to define your entry's UI. There are
two methods to notice:

```java
@Override
public String getBodyJspPath() {
return "/portlet/control_menu/product_menu_control_menu_entry_body.jsp";
}

@Override
public String getIconJspPath() {
return "/portlet/control_menu/product_menu_control_menu_entry_icon.jsp";
}
```

The `getIconJspPath()` method provides the Product Menu icon
(![Menu Closed](../../../images/icon-menu.png) &rarr; ![Menu Open](../../../images/icon-menu-open.png)),
and the `getBodyJspPath()` method adds the UI body for the entry outside of the
Control Menu. The latter method must be used when providing a UI outside the
Control Menu. You can test this by opening and closing the Product Menu on the
home page.

Finally, if you provide functionality that is exclusively inside the Control
Menu, the `StagingProductNavigationControlMenuEntry` class calls its JSP like
this:

```java
@Override
public String getIconJspPath() {
return "/control_menu/entry.jsp";
}
```

The `entry.jsp` is returned, which embeds the Staging Bar portlet into the
Control Menu.

Next, you'll step through the process of customizing the Control Menu.
