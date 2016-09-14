# Managing Module Projects with Blade CLI [](id=managing-module-projects-with-blade-cli)

In this tutorial, you'll learn how to deploy modules to a Liferay server using
Blade CLI. Also, you'll explore several other Blade CLI commands that let
install, start, stop, inspect, and modify a Liferay server.

## Deploying to Liferay [](id=deploying-to-liferay)

To use the Blade `deploy` command, you must first have built a module to deploy.
See the [Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)
tutorials for more information about creating module projects. Once you've built
a module, use the following command to deploy it to @product@:

    blade deploy

Blade CLI can detect a locally running Liferay instance and automatically
deploys your module to that Liferay instance. You can also specify a custom port
to deploy your module to using the `-p` parameter followed by the port number.
For instance, you could run `blade deploy -p 8090` to deploy to port 8090. You
can also watch the deployed module for changes by specifying the `-w` parameter.
This parameter automatically redeploys the module when changes are detected.

+$$$

**Note:** The `blade deploy` command requires a gradle wrapper to successfully
execute. To ensure the availability of the Gradle wrapper, be sure to work in a
Liferay Workspace. For more information on Liferay Workspaces, see the
[Creating a Liferay Workspace with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-blade-cli)
tutorial.

$$$

Now that you've successfully deployed your project, next you'll learn how to
use Blade CLI to further interact with your Liferay server.

## Interacting with the Liferay Server [](id=interacting-with-the-liferay-server)

Deploying your projects to @product@ is one of the more popular Blade CLI commands
that works with your server, but there are other miscellaneous commands that can
also be useful during development. Make sure you're in a Liferay Workspace and
have a bundle installed and configured in the workspace before testing the Blade
CLI commands on your own. To learn more about installing a Liferay server in a
Liferay Workspace, see the
[Creating a Liferay Workspace with Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-liferay-ide)
section. The following Blade CLI commands are covered in this sub-section:

- `server`
- `sh`
- `gw`

The first thing that comes to mind when interacting with a server is simply
turning it on/off. You can use the `server` sub-command to accomplish this. To
turn on a Liferay server, you can run

    blade server start -b

Likewise, to turn off a server, run

    blade server stop

Once you've started your bundle, you can examine your server's OSGi container by
using the `sh` command, which provides access to your server using Felix Gogo
shell. For example, to check if you successfully deployed your application from
the previous section, you could run:

    blade sh lb

Your output lists a long list of modules that are active/installed in your
server's OSGi container.

![Figure 1: Blade CLI accesses the Gogo shell script to run the `lb` command.](../../../images/blade-sh.png)

You can run any Gogo command using `blade sh`. See the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
section for more information on this tool.

Lastly, you can access a workspace's Gradle wrapper automatically by using the
`gw` command. Since the workspace's Gradle wrapper resides in its root folder,
it can sometimes be a hassle running it for a deeply nested module (e.g.,
`../../../../gradlew compileJava`). Running Gradle wrapper from Blade CLI
automatically detects the Gradle wrapper and can run it anywhere. For instance,
if you're in a deeply nested directory trying to compile a module, you can
simply run:

    blade gw compileJava

This example runs the `compileJava` command using the workspace's include
wrapper. Any Gradle command can be used with the `gw` Blade CLI command.

Awesome! You successfully deployed a project from Blade CLI and learned how to
conveniently interact with @product@ using Blade CLI.
