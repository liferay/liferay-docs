# Extending the Simulation Menu [](id=extending-the-simulation-menu)

When testing how Liferay pages and apps will appear for users, it's critical to
simulate their views on as many useful ways as possible. By default, Liferay
provides the Simulation Menu on the right-side of the main page. What if,
however, you'd like to simulate something in Liferay that is not provided by the
Simulation Menu? You'll need to extend the Simulation Menu, of course! Luckily,
Liferay offers a simple way to extend and customize the Simulation Menu so you
can test what you need. In this tutorial, you'll learn how to add additional
functionality to the menu so you can do more simulating and less wondering.

The first thing you'll need to do is get accustomed to using panel
categories/apps. This is covered in detail in the
[Customizing The Product Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)
tutorial. Once you know the difference between panel categories and panel apps,
and know how to create them, continue on in this tutorial.

There are few differences between the Simulation Menu and Product Menu, mostly
because they extend the same base classes. The Simulation Menu, by default, is
made up of only one panel category and one panel app. Liferay provides the
[SimulationPanelCategory](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/simulation/application/list/SimulationPanelCategory.html)
class, which is a hidden category needed to hold the `DevicePreviewPanelApp`.
This is the app and functionality you see in the Simulation Menu by default.

![Figure 1: The Simulation Menu offers a device preview application.](../../images/simulation-menu-preview.png)

To provide your own functionality in the Simulation Menu, you'll need to create
a panel app in the `SimulationPanelCategory`. If you're looking to add extensive
functionality, you can even create additional panel categories in the menu to
divide up your panel apps. This tutorial will cover the simpler case of creating
a panel app for the already present hidden category.

1. Follow the steps documented in the
   [Adding Custom Panel Apps](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu#adding-custom-panel-apps)
   section for creating custom panel apps. Once you've created the foundation of
   your panel app, move on to learn how to tweak it so it customizes the
   Simulation Menu.

2. Since this tutorial assumes you're providing more functionality to the
   existing simulation category, set the simulation category in the
   `panel.category.key` of the `@Component` annotation:

        "panel.category.key=" + SimulationPanelCategory.SIMULATION

    In order to use this constant, you need to add a dependency on 
    [com.liferay.product.navigation.simulation](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.product.navigation.simulation/).
    Be sure to also specify the order you'd like to display your new panel app,
    which was explained in the 
    [Adding Custom Panel Apps](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu#adding-custom-panel-apps)
    section.

3. This tutorial assumes you're using JSPs for creating a complex UI. Therefore,
   you should extend the [BaseJSPPanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html)
   abstract class. This class implements the
   [PanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/PanelApp.html)
   interface and also provides additional methods necessary for specifying JSPs
   to render your panel app's UI. Remember that you can also implement your own
   `include()` method to use any frontend technology you want, if you'd like to
   use a technology other than JSP (e.g., FreeMarker).

4. Define your simulation view. For instance, in `DevicePreviewPanelApp`, the
   `getJspPath` method points to the `simulation-device.jsp` file in the
   `resources/META-INF/resources` folder, where the device simulation interface
   is defined. Optionally, you can also add your own language keys, CSS, or JS
   resources in your simulation module.

    <!-- Elaborate more on JSP! -Cody -->

    The right servlet context is also provided implementing this method:

        @Override
        @Reference(
            target = "(osgi.web.symbolicname=com.liferay.product.navigation.simulation.device)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            super.setServletContext(servletContext);
        }

    As explained in [Customizing The Product Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu),
    a panel app should be associated with a portlet. This makes the panel app 
    visible only when the user has permission to view the portlet.
    This panel app is associated to the Simulation Device portlet using these
    methods:

        @Override
        public String getPortletId() {
            return ProductNavigationSimulationPortletKeys.
                PRODUCT_NAVIGATION_SIMULATION;
        }

        @Override
        @Reference(
            target = "(javax.portlet.name=" + ProductNavigationSimulationPortletKeys.PRODUCT_NAVIGATION_SIMULATION + ")",
            unbind = "-"
        )
        public void setPortlet(Portlet portlet) {
            super.setPortlet(portlet);
        }

    Audience Targeting also provides a good example of how to extend the
    Simulation Menu. When the Audience Targeting app is deployed, the Simulation
    Menu is extended to offer more functionality, in particular, for Audience
    Targeting User Segments and Campaigns. You can simulate particular scenarios
    for campaigns and users directly from the Simulation Menu. Its panel app
    class is very similar to `DevicePreviewPanelApp`, except it points to a
    different portlet and JSP.

    ![Figure 2: The Audience Targeting app extends the Simulation Menu to help simulate different users and campaign views.](../../images/simulation-menu-at.png)

5. You can combine your simulation options with the device simulation options by
   interacting with the device preview iFrame. To retrieve the device preview
   frame in an `aui:script` block of your custom simulation view's JavaScript,
   you can use the following:

        var iframe = A.one('#simulationDeviceIframe');

    Then you can modify the device preview frame URL like this:

        iframe.setAttribute('src', newUrlWithCustomParameters);

Now that you know how to extend the necessary panel categories and panel apps to
modify the Simulation Menu, go ahead and create a module of your own and
customize the Simulation Menu so it's most helpful for your needs.
