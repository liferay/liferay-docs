# Providing the User Personal Bar [](id=providing-the-user-personal-bar)

The User Personal Bar displays options unique to the current user. By default,
this portlet appears as an avatar button that expands the Personal Menu when
selected. In a custom theme, the User Personal Bar could appear anywhere in the
interface.

![Figure 1: By default, the User Personal Bar contains the signed-in user's avatar, which opens the Personal Menu when selected.](../../../images/user-personal-bar.png)

The User Personal Bar can be seen as a placeholder in every Liferay theme. By
default, Liferay provides one sample *User Personal Bar* portlet that fills that
placeholder, but the portlet Liferay provides can be replaced by a custom portlet.

## Displaying the Personal Menu

Starting with @product-ver@, the Personal Menu is attached to the User Personal
Bar rather than the Product Menu. To display the Personal Menu, embed the User
Personal Bar into your theme by adding the following snippet into your
`portal_normal.ftl`:

```ftl
<@liferay.user_personal_bar />
```

If you use a custom portlet as you user personal bar, you must also render the
Personal Menu. Include the following taglib in your portlet's jsp:

```jsp
<liferay-product-navigation:personal-menu
    expanded="<%= true %>"
    label="<%= userAvatar %>"
/>
```

| **Note:** The recommended way to display the Personal Menu is by embedding the
| User Personal Bar in a theme. If this is not practical, a workaround exists: go
| to *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings* &rarr;
| *Users* and select *Personal Menu*. Enable the *Show in Control Menu* toggle
| and click *Update*.
| 
| This places a button to expand the Personal Menu in the Control Menu. It
| appears on every site and page in your virtual instance, including sites that
| have the User Personal Bar embedded in the theme.

Unlike the Product Menu, the Personal Menu can be customized without
creating panel categories and panel apps. See 
[Customizing the Personal Menu](/docs/7-2/frameworks/-/knowledge_base/f/customizing-the-personal-menu)
for details.

## Using a Custom Portlet as the User Personal Bar

Replacing the default User Personal Bar with a Custom Portlet requires a single
Java class. Follow these steps:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development).

2.  Create a unique package name in the module's `src` directory and create a
    new Java class in that package.

3.  Above the class declaration, insert the following annotation:

        @Component(
            immediate = true,
            property = {
                "model.class.name=" + PortalUserPersonalBarApplicationType.UserPersonalBar.CLASS_NAME,
                "service.ranking:Integer=10"
            },
            service = ViewPortletProvider.class
        )

     The `model.class.name` property must be set to the class name of the entity
     type you want the portlet to handle. In this case, you want your portlet to
     be provided based on whether it can be displayed in the User Personal Bar.

     You should also specify the service rank for your new portlet so it
     overrides the default. Make sure to set the `service.ranking:Integer`
     property to a number that is ranked higher than the portlet being used by
     default.

     Since you only want the User Personal Bar to display your portlet, the
     `service` element should be `ViewPortletProvider.class`.

4.  Update the class's declaration to extend the 
    [BasePortletProvider](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)
    abstract class and implement `ViewPortletProvider`:

        public class ExampleViewPortletProvider extends BasePortletProvider implements ViewPortletProvider {

5.  Specify the portlet you want in the User Personal Bar by declaring the
    following method in your class:

        @Override
        public String getPortletName() {
            return PORTLET_NAME;
        }

    Replace the `PORTLET_NAME` text with the portlet you want to provide Liferay
    when it requests one to be viewed in the User Personal Bar. For example,
    Liferay declares
    `com_liferay_product_navigation_user_personal_bar_web_portlet_ProductNavigationPersonalBarPortlet`
    for its default User Personal Bar portlet.

You've successfully provided a portlet to be displayed in the User Personal Bar.
If you want to inspect the entire module used for Liferay's default User
Personal Bar, see
[product-navigation-user-personal-bar-web](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/product-navigation/product-navigation-user-personal-bar-web).
Besides the `*ViewPortletProvider` class, this module contains two classes
defining constants and a portlet class defining the default portlet to provide.
Although these additional classes are not required, your module should have
access to the portlet you want to provide.
