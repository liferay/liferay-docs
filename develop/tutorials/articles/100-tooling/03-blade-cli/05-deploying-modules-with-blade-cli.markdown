# Deploying Modules with Blade CLI [](id=deploying-modules-with-blade-cli)

Deploying modules to a Liferay server using Blade CLI is easy. To use the Blade
`deploy` command, you must first have built a module to deploy. See the
[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)
tutorials for more information about creating module projects. Once you've built
a module, navigate to it in your terminal window and execute the following
command to deploy it to @product@:

    blade deploy

You can also deploy all modules in a folder by running the `deploy` command from
the parent folder (e.g., `[WORKSPACE_ROOT]/modules`).

If you run into errors during the build/deploy process of your module, check to
make sure your workspace is accounting for the
[appropriate certificates](/develop/tutorials/-/knowledge_base/7-0/configuring-a-liferay-workspace#certification-issues-in-liferay-workspace).

Blade CLI can detect a locally running Liferay instance and automatically
deploys your module to that Liferay instance. Blade communicates with
@product@'s OSGi framework using Felix Gogo shell and deploys the module
directly to the OSGi container using Felix File Install commands. The command
uses the default `11311` port by default.

<!--
You can also specify a custom port to deploy your module to using the `-p`
parameter followed by the port number. For instance, you could run `blade deploy
-p 8090` to deploy to port 8090.
-->

You can also watch the deployed module for changes by specifying the `-w`
parameter.

    blade deploy -w

This parameter automatically redeploys the module when changes are detected.

+$$$

**Note:** The `blade deploy` command requires a Gradle wrapper to successfully
execute. To ensure the availability of the Gradle wrapper, be sure to work in a
Liferay Workspace. For more information on Liferay Workspaces, see the
[Creating a Liferay Workspace with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-blade-cli)
tutorial.

$$$

Cool! You've successfully deployed your module project using Blade CLI.
