# Simulation Panel App [](id=simulation-panel-app)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Simulation Panel App provides new functionality in @product's Simulation
Menu. When deploying this sample with no customizations, the *Simulation Sample*
feature is provided in the Simulation Menu with four options.

![Figure 1: A simulation panel app adds new functionality to the Simulation Menu.](../../../images/simulation-panel-app.png)

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[PanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/PanelApp.html)
API.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample leverages the `PanelApp` interface as an OSGi service via the
`@Component` annotation:

    @Component(
        immediate = true,
        property = {
            "panel.app.order:Integer=500",
            "panel.category.key=" + SimulationPanelCategory.SIMULATION
        },
        service = PanelApp.class
    )

There are also two properties provided via the `@Component` annotation:

- `panel.app.order`: the order in which the panel app is displayed among other
   panel apps in the chosen category. Entries are ordered from top to bottom.
   For example, an entry with order `1` will be listed above an entry with order
   `2`. If the order is not specified, it's chosen at random based on which
   service was registered first in the OSGi container.
- `panel.category.key`: the host panel category for your panel app, which
   should be the Simulation Menu category.

The simulation panel app extends the
[BaseJSPPanelApp](https://docs.liferay.com/ce/apps/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html),
which provides a skeletal implementation of the
[PanelApp](https://docs.liferay.com/ce/apps/web-experience/latest/javadocs/com/liferay/application/list/PanelApp.html)
interface with JSP support. JSPs, however, are not the only way to provide
frontend functionality to your panel categories/apps. You can create your own
class implementing `PanelApp` to use other technologies, such as FreeMarker.

To learn more about Liferay Portal's product navigation using panel categories
and panel apps, see the
[Customizing the Product Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)
tutorial. For more information on extending the Simulation Menu, see the
[Extending the Simulation Menu](/develop/tutorials/-/knowledge_base/7-0/extending-the-simulation-menu)
tutorial.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/simulation-panel-app)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/apps/simulation-panel-app)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/apps/simulation-panel-app)
