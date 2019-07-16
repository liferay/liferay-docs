---
header-id: providing-the-user-personal-menu
---

# Providing the User Personal Menu

[TOC levels=1-4]

In this article, you'll learn how to customize the User Personal Menu. You'll
create a single Java class where you'll specify a portlet to replace the
existing default portlet.

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  Create a unique package name in the module's `src` directory and create a
    new Java class in that package.

3.  Above the class declaration, insert the following annotation:

    ```java
    @Component(
        immediate = true,
        property = {
            "model.class.name=" + PortalUserPersonalBarApplicationType.UserPersonalBar.CLASS_NAME,
            "service.ranking:Integer=10"
        },
        service = ViewPortletProvider.class
    )
    ```

     The `model.class.name` property must be set to the class name of the entity
     type you want the portlet to handle. In this case, you want your portlet to
     be provided based on whether it can be displayed in the User Personal Menu.

     You may recall from the 
     [Portlet Providers](/docs/7-2/frameworks/-/knowledge_base/f/embedding-portlets-in-themes)
     articles that you can request portlets in several different ways (e.g.,
     *Edit*, *Browse*, etc.).

     You should also specify the service rank for your new portlet so it
     overrides the default. Make sure to set the `service.ranking:Integer`
     property to a number that is ranked higher than the portlet being used by
     default.

     Since you only want the User Personal Menu to display your portlet, the
     `service` element should be `ViewPortletProvider.class`.

4.  Update the class's declaration to extend the 
    [BasePortletProvider](/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)
    abstract class and implement `ViewPortletProvider`:

    ```java
    public class ExampleViewPortletProvider extends BasePortletProvider implements ViewPortletProvider {

    }
    ```

5.  Specify the portlet you want in the User Personal Bar by declaring the
    following method in your class:

    ```java
    @Override
    public String getPortletName() {
        return PORTLET_NAME;
    }
    ```

    Replace the `PORTLET_NAME` text with the portlet you want to provide Liferay
    when it requests one to be viewed in the User Personal Menu. For example,
    Liferay declares
    `com_liferay_product_navigation_user_personal_bar_web_portlet_ProductNavigationPersonalBarPortlet`
    for its default User Personal Bar portlet.

You've successfully provided a portlet to be displayed in the User Personal
Menu. If you want to inspect the entire module used for Liferay's default User
Personal Bar, see
[product-navigation-user-personal-bar-web](https://github.com/liferay/liferay-portal/tree/7.2.0-ga1/modules/apps/product-navigation/product-navigation-user-personal-bar-web).
Besides the `*ViewPortletProvider` class, this module contains two classes
defining constants and a portlet class defining the default portlet to provide.
Although these additional classes are not required, your module should have
access to the portlet you want to provide.
