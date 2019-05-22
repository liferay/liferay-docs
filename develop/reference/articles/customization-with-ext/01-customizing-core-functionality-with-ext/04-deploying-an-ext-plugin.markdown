# Deploying an Ext Plugin [](id=deploying-an-ext-plugin)

Deploying an Ext plugin is similar to deploying any other @product@ project. For
example, you'll step through deploying an Ext plugin in Liferay Workspace.

1.  From your Ext plugin root folder, run `blade deploy` (Gradle or Maven) or
    `mvn verify` (Maven only).

    The `deploy` target creates a `.war` file with your changes and then deploys
    it to your server. A `BUILD SUCCESSFUL` message indicates your plugin is now
    being deployed. Your console window running @product@ shows a message like
    this:

        Extension environment for sample-ext has been applied. You must
        reboot the server and redeploy all other plugins.

2.  Restart the server so that it detects and publishes your Ext plugin. Your
    server must be restarted after the initial deployment and every
    redeployment.

    If any changes applied through the Ext plugin affect the deployment process
    itself, you must redeploy all other plugins. Even if the Ext plugin doesn't
    affect the deployment process, it's a best practice to redeploy all your
    other plugins following initial deployment of the Ext plugin. 

    When the server starts, it detects the `.war` file, inspects it, and copies
    its contents to the appropriate destinations inside @product@.

3.  Once your server restarts, open @product@ to see the customizations you
    made with your Ext plugin.

Frequently, you'll want to add further customizations to your original Ext
plugin. You can make unlimited customizations to an Ext plugin that has already
been deployed, but the redeployment process is different from other project
types. You'll learn more on redeploying your Ext plugin next.
