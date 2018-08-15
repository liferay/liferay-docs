# Customizing the Control Menu [](id=customizing-the-control-menu)

The Control Menu is the most visible and accessible menu in Liferay. For
example, on your home page, the Control Menu offers default options for
accessing the Product Menu, Simulation Menu, and Add Menu. You can think of this
menu as the gateway to configuring options in Liferay.

![Figure 1: The Control Menu has three configurable areas: left, right, and middle. It also displays the title and type of page that you are currently viewing.](../../../images/control-menu-home.png)

If you navigate away from the home page, the Control Menu adapts and provides
helpful functionality for whatever option you're using. For example, if you
navigate to Site Administration &rarr; *Content* &rarr; *Web Content*, you see
a Control Menu with different functionality tailored for that option.

![Figure 2: When switching your context to web content, the Control Menu adapts to provide helpful options for that area.](../../../images/control-menu-web-content.png)

The default Control Menu contains three categories representing the left,
middle, and right portions of the menu. You can create navigation entries for
each category.

+$$$

**Note:** You can add the Control Menu to a theme by adding the following
snippet into your `portal_normal.ftl`:

    <@liferay.control_menu />

The other product navigation menus (e.g., Product Menu, Simulation Menu) are
included in this tag, so specifying the above snippet embeds all three menus
into your theme. Embedding the User Personal Bar is slightly different. Visit
the [Providing the User Personal Bar](/develop/tutorials/-/knowledge_base/7-1/providing-the-user-personal-bar)
tutorial for more information.

$$$

You can reference a sample Control Menu Entry by visiting the
[Control Menu Entry](/develop/reference/-/knowledge_base/7-1/control-menu-entry-template)
article.

Next you'll learn how to customize the Control Menu.
