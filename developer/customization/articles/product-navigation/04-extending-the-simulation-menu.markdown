---
header-id: extending-the-simulation-menu
---

# Extending the Simulation Menu

[TOC levels=1-4]

To provide your own functionality in the Simulation Menu, you must create a
Panel App in `SimulationPanelCategory`. If you want to add extensive
functionality, you can even create additional Panel Categories in the menu to
divide up your Panel Apps. This article covers the simpler case of creating a
Panel App for the already present hidden category.

Before beginning, make sure you're accustomed to using Panel Categories and
Panel Apps. This is covered in detail in the
[Customizing the Product Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu)
articles. Once you know how to create Panel Categories and Panel Apps, continue
with this article.

1.  Follow the steps documented in 
    [Adding Custom Panel Apps](/docs/7-2/customization/-/knowledge_base/c/adding-custom-panel-apps)
    for creating custom Panel Apps. Once you've created the foundation 
    of your Panel App, move on to learn how to tweak it so it customizes the
    Simulation Menu.

    You can generate a Simulation Panel App by using Blade CLI's
    [Simulation Panel Entry template](/docs/7-2/reference/-/knowledge_base/r/simulation-panel-entry-template).
    You can also refer to the
    [Simulation Panel App sample](/docs/7-2/reference/-/knowledge_base/r/simulation-panel-app)
    for a working example.

2.  Since this article assumes you're providing more functionality to the
    existing simulation category, set the simulation category in the
    `panel.category.key` of the `@Component` annotation:

    ```java
    "panel.category.key=" + SimulationPanelCategory.SIMULATION
    ```

    To use this constant, you must add a dependency on 
    [`com.liferay.product.navigation.simulation`](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.product.navigation.simulation/).

    Be sure to also specify the order to display your new Panel App,
    which was explained in [Adding Custom Panel Apps](/docs/7-2/customization/-/knowledge_base/c/adding-custom-panel-apps).

3.  This article assumes you're using JSPs. 
    Therefore, you should extend the [`BaseJSPPanelApp`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html)
    abstract class, which implements the [`PanelApp`](@app-ref@/application-list/latest/javadocs/com/liferay/application/list/PanelApp.html)
    interface and also provides additional methods necessary for specifying JSPs
    to render your Panel App's UI. Remember that you can also implement your own
    `include()` method to use any front-end technology you want, if you want to
    use a technology other than JSP (e.g., FreeMarker).

4.  Define your simulation view. For instance, in `DevicePreviewPanelApp`, the
    `getJspPath` method points to the `simulation-device.jsp` file in the
    `resources/META-INF/resources` folder, where the device simulation interface
    is defined. Optionally, you can also add your own language keys, CSS, or
    JavaScript resources in your simulation module.

    The right servlet context is also provided by implementing this method:

    ```java
    @Override
    @Reference(
        target = "(osgi.web.symbolicname=com.liferay.product.navigation.simulation.device)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
    ```

    As explained in [Customizing The Product Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu),
    a Panel App should be associated with a portlet. This makes the Panel App 
    visible only when the user has permission to view the portlet.
    This Panel App is associated to the Simulation Device portlet using these
    methods:

    ```java
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
    ```

    Segments also provides a good example of how to extend the Simulation Menu.
    When segments are available, the Simulation Menu is extended to offer
    personalization options. You can simulate particular experiences directly
    from the Simulation Menu. Its Panel App class is similar to
    `DevicePreviewPanelApp`, except it points to a different portlet and JSP.
    For more information on Segments, see the
    [Segmentation and Personalization](/docs/7-2/user/-/knowledge_base/u/segmentation-and-personalization)
    section.

    ![Figure 1: The Simulation Menu also displays Segments to help simulate different user experiences.](../../images/segments-preview.png)

5.  You can combine your simulation options with the device simulation options 
    by interacting with the device preview iFrame. To retrieve the device 
    preview frame in an `aui:script` block of your custom simulation view's 
    JavaScript, you can use this code:

    ```js
    var iframe = A.one('#simulationDeviceIframe');
    ```

    Then you can modify the device preview frame URL like this:

    ```js
    iframe.setAttribute('src', newUrlWithCustomParameters);
    ```

Now that you know how to extend the necessary Panel Categories and Panel Apps to
modify the Simulation Menu,
[create a module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project) 
of your own and customize the Simulation Menu so it's most helpful for your 
needs.
