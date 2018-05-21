# Customizing the Control Menu [](id=customizing-the-control-menu)

The Control Menu is the most visible and accessible menu in Liferay. It is
visible to the user in most places, always displaying helpful text or options
at the top of the page. For example, on your home page, the Control Menu offers
default options for accessing the Product Menu, Simulation Menu, and Add Menu.
You can think of this menu as the gateway to configuring options in Liferay.

![Figure 1: The Control Menu has three configurable areas: left, right, and middle.](../../../images/control-menu-home.png)

If you navigate away from the home page, the Control Menu adapts and provides
helpful functionality for what ever option you're using. For example, if you
navigated to Site Administration &rarr; *Content* &rarr; *Web Content*, you'd be
displayed a Control Menu with different functionality tailored for that option.

![Figure 2: When switching your context to web content, the Control Menu adapts to provide helpful options for that area.](../../../images/control-menu-web-content.png)

The default Control Menu is made up of three categories that represent the left,
middle, and right portions of the menu. You can create navigation entries for
each category, which can provide options or further navigation for the
particular screen you're on.

+$$$

**Note:** You can add the Control Menu to a custom theme by adding the following
snippet into your `portal_normal.ftl`:

    <@liferay.control_menu />

The other product navigation menus (e.g., Product Menu, Simulation Menu) are
included in this tag, so specifying the above snippet will embed all three menus
into your theme. Embedding the User Personal Bar is slightly different. Visit
the [Providing the User Peronsal Bar](/develop/tutorials/-/knowledge_base/7-0/providing-the-user-personal-bar)
tutorial for more information.

$$$

You can reference a sample Control Menu Entry by visiting the
[Control Menu Entry](/develop/reference/-/knowledge_base/7-0/control-menu-entry)
article.
