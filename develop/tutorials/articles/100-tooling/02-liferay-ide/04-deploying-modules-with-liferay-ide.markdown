# Deploying Modules with Liferay @ide@ [](id=deploying-modules-with-liferay-ide)

Deploying modules in Liferay @ide@ is a cinch. Before deploying your module,
make sure you have a Liferay server configured in @ide@. To see how to do this
when creating a Liferay Workspace, see the
[Creating a Liferay Workspace with Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-liferay-ide)

<!-- Create independent tutorial for configuring Liferay server in IDE. Once
that tutorial is available, replace the link above. -Cody
-->

There are two ways to deploy a module to your Liferay server. You should start
your Liferay server before attempting to deploy to it.

1.  Select the module from the Package Explorer window and drag it to your
    Liferay server in the Servers window.

    ![Figure 1: You can use the drag-and-drop method to deploy your module to @product@.](../../../images/starting-module-dev-drag-module.png)

2.  Right-click the server from the Servers window and select *Add and
    Remove...*. Add the module(s) you'd like to deploy from the Available window
    to the Configured window. Then click *Finish*.

    ![Figure 2: Using the this deployment method is convenient when deploying multiple module projects.](../../../images/add-and-remove-ide.png)

That's it! Once your module is deployed to the Liferay server, you can verify
its installation in @ide@'s Console window.
