# Simulation Panel App

The Simulation Panel app provides new functionality in Liferay Portal's
Simulation Menu. When deploying this sample with no customizations, the
*Simulation Sample* feature is provided in the Simulation Menu with four
options.

![Figure 1: A simulation panel app adds new functionality to the Simulation Menu.](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/simulation-panel-app.png)

This sample leverages the `PanelApp` extension point (OSGi service) in a Java
class. It also specifies two properties in the `@Component` annotation:

- `panel.app.order`: the order in which the panel app is displayed among other
   panel apps in the chosen category.
- `panel.category.key`: the host panel category for your panel app, which
   should be the Simulation Menu category.

These configurations are set using the following logic:

    @Component(
        immediate = true,
        property = {
            "panel.app.order:Integer=500",
            "panel.category.key=" + SimulationPanelCategory.SIMULATION
        },
        service = PanelApp.class
    )

The simulation panel app extends the
[BaseJSPPanelApp](https://docs.liferay.com/ce/apps/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html),
which provides a skeletal implementation of the
[PanelApp](https://docs.liferay.com/ce/apps/web-experience/latest/javadocs/com/liferay/application/list/PanelApp.html)
interface with JSP support. JSPs, however, are not the only way to provide
frontend functionality to your panel categories/apps. You can create your own
class implementing `PanelApp` to use other technologies, such as FreeMarker.

To learn more about Liferay Portal's product navigation using panel categories
and panel apps, see the
[Customizing the Product Menu](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)
tutorial. For more information on extending the Simulation Menu, see the
[Extending the Simulation Menu](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/extending-the-simulation-menu)
tutorial.