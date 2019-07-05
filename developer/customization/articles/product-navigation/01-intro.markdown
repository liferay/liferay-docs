---
header-id: product-navigation
---

# Product Navigation

[TOC levels=1-4]

@product@'s product navigation consists of the main menus you use to customize,
configure, and navigate the system. When you edit a page, switch to a different
Site scope, access a User's credentials, etc., you're using the default
navigation menus. Customizing a default menu can help give your Liferay instance
a unique touch. You can extend and customize the default product navigation to
fit your need.

There are four product navigation sections that you can extend:

- Product Menu
- Control Menu
- Simulation Menu
- User Personal Menu

![Figure 1: The main product navigation menus include the Product Menu, Control Menu, Simulation Menu and User Personal Menu.](../../images/product-navigation-summary.png)

The Product Menu is on the left, and displays the Control Panel and Site
Administration functionality. The Control Menu is on top, offering navigation to
the Product Menu, Simulation Menu (the right menu), and the *Add* button. When
certain settings are enabled (e.g., Staging, Page Customization, etc.), more
tools are offered. The Simulation Menu offers options to simulate your Site's
look for different scenarios (devices, user segments, etc.). Finally, the User
Personal Menu holds selectable items containing a user's own account settings.

You'll learn more about each of these product navigation sections next.

## Product Menu

By default, Liferay's Product Menu consists of two main sections: Control Panel
and Site Administration. These sections are called *Panel Categories*. For
instance, the Control Panel is a single Panel Category, and when clicking on it,
you see six child Panel Categories: *Users*, *Sites*, *Apps*, *Configuration*,
*Workflow*, and *Change Lists*. Clicking a child Panel Category shows *panel
apps*.

The Product Menu is intuitive and easy to use---but you can still change it any
way you want. You can reorganize the Panel Categories and apps, or add
completely new categories and populate them with custom Panel Apps. You'll
learn how to provide new or modified Panel Categories and Panel Apps for the
Product Menu. For more information, read the
[Customizing the Product Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu)
articles.

## Control Menu

The Control Menu is the most visible and accessible menu. For example, on your 
home page, the Control Menu offers default options for accessing the Product
Menu, Simulation Menu, and Add Menu. You can think of this menu as the gateway
to configuring options in @product@.

![Figure 2: The Control Menu has three configurable areas: left, right, and middle. It also displays the title and type of page that you are currently viewing.](../../images/control-menu-home.png)

If you navigate away from the home page, the Control Menu adapts and provides
helpful functionality for whatever option you're using. For example, if you
navigate to Site Administration &rarr; *Content & Data* &rarr; *Web Content*,
you see a Control Menu with different functionality tailored for that option.

![Figure 3: When switching your context to web content, the Control Menu adapts to provide helpful options for that area.](../../images/control-menu-web-content.png)

The default Control Menu contains three categories representing the left,
middle, and right portions of the menu. You can create navigation entries for
each category. For more information, read the
[Customizing the Control Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-control-menu)
articles.

## Simulation Menu

When testing how pages and apps appear for users, it's important to simulate
their views in as many ways as possible. The Simulation Menu on the right-side
of the main page allows this, and you can extend the menu if you need to
simulate something that it does not provide.

![Figure 4: The Simulation Menu offers a device preview application.](../../images/simulation-menu-preview.png)

There are few differences between the Simulation Menu and Product Menu, mostly
because they extend the same base classes. The Simulation Menu, by default, is
made up of only one Panel Category and one Panel App. Liferay provides the
[`SimulationPanelCategory`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/product-navigation/product-navigation-simulation-web/src/main/java/com/liferay/product/navigation/simulation/web/internal/application/list/SimulationPanelCategory.java)
class, a hidden category needed to hold the `DevicePreviewPanelApp`. This is the
app and functionality you see in the Simulation Menu by default.

For more information, read the
[Extending the Simulation Menu](/docs/7-2/customization/-/knowledge_base/c/extending-the-simulation-menu)
article.

## User Personal Menu

The User Personal Menu displays options unique to the current user. By default,
this menu appears as an avatar button that expands the User Settings sub-menu
just below the Control Menu. In a custom theme, the User Personal Menu could
appear anywhere in the interface.

![Figure 5: By default, the User Personal Menu contains the signed-in user's avatar, which opens the user's settings when selected.](../../images/user-personal-menu.png)

Although Liferay's default User Personal Menu is bare-bones, you can
add more functionality to fit your needs. Unlike other product navigation menus
(e.g., Product Menu), the User Personal Bar does not require the
extension/creation of Panel Categories and Panel Apps. It uses another common
Liferay framework for providing functionality:
[Portlet Providers](/docs/7-2/frameworks/-/knowledge_base/f/embedding-portlets-in-themes).

The User Personal Menu can be seen as a placeholder in every Liferay theme. By
default, Liferay provides one sample *User Personal Bar* portlet that fills that
placeholder, but the portlet Liferay provides can be replaced by other portlets.

| **Note:** You can add the User Personal Bar to your theme by adding the
| following snippet into your `portal_normal.ftl`:
| 
| ```
| <@liferay.user_personal_bar />
| ```

For more information, read the
[Providing the User Personal Menu](/docs/7-2/customization/-/knowledge_base/c/providing-the-user-personal-menu)
article.
