---
header-id: using-a-custom-portlet-in-place-of-the-user-personal-bar
---

# Using a Custom Portlet in Place of the User Personal Bar

[TOC levels=1-4]

In this article, you'll learn how to write the single Java class required to
replace the default User Personal Bar with a custom portlet. Writing the
portlet itself is up to each developer's needs. See the documentation on
[portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)
if you need guidance.

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
     be provided based on whether it can be displayed in the User Personal Bar.

     You may recall from the 
     [Portlet Providers](/docs/7-2/frameworks/-/knowledge_base/f/embedding-portlets-in-themes)
     articles that you can request portlets in several different ways (e.g.,
     *Edit*, *Browse*, etc.).

     You should also specify the service rank for your new portlet so it
     overrides the default. Make sure to set the `service.ranking:Integer`
     property to a number that is ranked higher than the portlet being used by
     default.

     Since you want to display your portlet instead of the User Personal Bar,
     the `service` element should be `ViewPortletProvider.class`.

4.  Update the class's declaration to extend the 
    [`BasePortletProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)
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

    Replace the `PORTLET_NAME` text with the portlet to provide when one is
    requested by the theme template. For example, the default portlet uses
    `com_liferay_product_navigation_user_personal_bar_web_portlet_ProductNavigationPersonalBarPortlet`

If you want to inspect the entire module used for Liferay's User Personal Bar,
see the
[product-navigation-user-personal-bar-web](https://github.com/liferay/liferay-portal/tree/7.2.0-ga1/modules/apps/product-navigation/product-navigation-user-personal-bar-web) module.

## Related Topics

- [Customizing the Product Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu)
- [Customizing the Control Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-control-menu)
