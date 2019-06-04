---
header-id: redeploying-an-ext-plugin
---

# Redeploying an Ext Plugin

[TOC levels=1-4]

After editing an Ext plugin, you must follow a slightly different process to
redeploy your Ext plugin. This section assumes you're redeploying an Ext plugin
in Liferay Workspace.

1.  Stop the @product@ server. 

2.  Delete your Ext plugin bundle, which resides in your app server's `webapps`
    folder.

3.  (Optional) If you removed part(s) of your plugin, if there are changes to
    your plugin that can affect plugin deployment, or if you want to start with
    a clean @product@ environment, you must **also** clean your application
    server. You can clean the application server by deleting your Liferay Home
    and regenerating the bundle. This is done in Liferay Workspace by calling
    `blade server init`.

4.  From your Ext plugin root folder, run `blade deploy` (Gradle or Maven) or
    `mvn verify` (Maven only).

    The `deploy` target creates a `.war` file with your changes and then deploys
    it to your server. A `BUILD SUCCESSFUL` message indicates your plugin is now
    being deployed. Your console window running @product@ shows a message
    like this:

    ```bash
    Extension environment for sample-ext has been applied. You must
    reboot the server and redeploy all other plugins.
    ```

5.  After your plugin is published to @product@, verify that your customizations
    are available.

You're all set to redeploy Ext plugins!
