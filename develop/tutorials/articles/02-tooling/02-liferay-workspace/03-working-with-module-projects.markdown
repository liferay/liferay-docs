# Working with Module Projects

In Liferay Workspaces, Gradle is the build tool used to compile and publish
Liferay modules. Before you can begin managing your Liferay module projects from
a Liferay Workspace, you'll need to create a Liferay Workspace, which is covered
in the
[Creating a Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)
tutorial. Once you've created your Liferay Workspace, you can begin using it to
create Gradle-based OSGi modules for @product@.

## Interacting with the Liferay Server [](id=interacting-with-the-liferay-server)

Deploying your projects to @product@ is one of the more popular Blade CLI commands
that works with your server, but there are other miscellaneous commands that can
also be useful during development. Make sure you're in a Liferay Workspace and
have a bundle installed and configured in the workspace before testing the Blade
CLI commands on your own. To learn more about installing a Liferay server in a
Liferay Workspace, see the
[Running a Liferay Instance From Your Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace#running-a-liferay-instance-from-your-workspace)
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

![Figure 1: Blade CLI accesses the Gogo shell script to run the `lb` command.](../../../../images/blade-sh.png)

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
