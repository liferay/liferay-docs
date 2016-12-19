# Providing the User Personal Bar [](id=providing-the-user-personal-bar)

Liferay offers a touch of personability with the User Personal Bar. This
navigation menu is used to display options that are unique to the current logged
in user. By default, Liferay displays this menu as a simple avatar button that
expands the User Settings sub-menu in the Product Menu.

![Figure 1: By default, the User Personal Bar contains the signed-in user's avatar, which navigates to the Product Menu when selected.](../../images/user-personal-bar.png)

Although Liferay's default usage of the User Personal Bar is bare-bones, you can
add more functionality to the user bar to fit your needs. Unlike other product
navigation menus (e.g., Product Menu), the User Personal Bar does not require
the extension/creation of panel categories and panel apps. It uses another
common Liferay framework for providing functionality:
[Portlet Providers](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests).
Be sure to visit the linked tutorial to learn about how the Portlet Providers
framework works in Liferay.

The User Personal Bar can be seen as a placeholder in every Liferay theme. By
default, Liferay provides one sample *User Personal Bar* portlet that fills that
placeholder, but the portlet Liferay provides can be easily replaced by other
portlets.

+$$$

**Note:** You can add the User Personal Bar to a custom theme by adding the
following snippet into your `portal_normal.ftl`:

    <@liferay.user_personal_bar />

$$$

In this tutorial, you'll learn how to customize the User Personal Bar.

1. Create a generic OSGi module using your favorite third party tool, or use
   [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli). 

2. Create a unique package name in the module's `src` directory and create a
   new Java class in that package.

3. Directly above the class's declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {
                "model.class.name=" + PortalUserPersonalBarApplicationType.UserPersonalBar.CLASS_NAME
            },
            service = ViewPortletProvider.class
        )

    The `model.class.name` property must be set to the class name of the entity
    type you want the portlet to handle. In this case, you want your portlet to
    be provided based on whether or not it can be displayed in the User Personal
    Bar. You may recall from the
    [Portlet Providers](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests)
    tutorial that you can request portlets in several different ways (e.g.,
    *Edit*, *Browse*, etc.). Since you're only wanting the User Personal Bar to
    display your portlet, you'll always have the `service` element be
    `ViewPortletProvider.class`.

    The example annotation provided above is the one provided for Liferay's
    current User Personal Bar in the
    `ProductNavigationUserPersonalBarViewPortletProvider` class.

4. Extend the
   [BasePortletProvider](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)
   abstract class and implement `ViewPortletProvider`.

5. Specify the portlet you'd like to provide in the User Personal Bar by using
   the following method in your class:

        @Override
        public String getPortletName() {
            return ProductNavigationUserPersonalBarPortletKeys.
                PRODUCT_NAVIGATION_USER_PERSONAL_BAR;
        }

    This example snippet provides the
    `com_liferay_product_navigation_user_personal_bar_web_portlet_ProductNavigationPersonalBarPortlet`.
    In summary, you're providing a portlet based on Liferay's request for one
    that can be viewed in the User Personal Bar.

6. Specify the service rank for your portlet so it overrides the default
   portlet provided in Liferay. To do this, set the following property in your
   `@Component` declaration:

        property= {"service.ranking:Integer=10"}

    Make sure to replace the integer with a number that is ranked higher than
    the portlet being used by default. 

You've successfully provided a portlet to be displayed in the User Personal Bar.
If you'd like to inspect the entire module used for Liferay's default User
Personal Bar, see
[product-navigation-user-personal-bar-web](https://github.com/liferay/liferay-portal/tree/7.0.2-ga3/modules/apps/web-experience/product-navigation/product-navigation-user-personal-bar-web).
