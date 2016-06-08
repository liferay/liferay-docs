# Managing Module Projects

In Liferay Workspaces, Gradle is the build tool used to compile and publish
Liferay modules. Before you can begin managing your Liferay module projects from
a Liferay Workspace, you'll need to create a Liferay Workspace, which is covered
in the
[Creating a Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)
tutorial. Once you've created your Liferay Workspace, you can begin using it to
manage Gradle-based OSGi modules for @product@.

In this tutorial, you'll learn how to deploy modules to a Liferay server using
Liferay Workspace. Also, you'll explore several other ways to install, start,
stop, inspect, and modify a Liferay server. You'll examine two tools for working
with a workspace: Blade CLI and Liferay IDE. Time to get started!

## Using Blade CLI

Deploying your projects to @product@ is one of the more popular Blade CLI
commands that works with your Liferay server. Because Blade CLI is configured to
deploy modules to a running Liferay instance, the deployment mechanism works
similarly in a workspace compared to when it's run standalone. You can learn
more about deploying a module using Blade CLI in the
[Deploying to Liferay](/develop/tutorials/-/knowledge_base/7-0/deploying-to-liferay)
tutorial.

Besides the `blade deploy` command, there are several other miscellaneous
commands that can also be useful during development. Make sure you're in a
Liferay Workspace and have a bundle installed and configured in the workspace
before testing the Blade CLI commands on your own. To learn more about
installing a Liferay server in a Liferay Workspace, see the
[Running a Liferay Instance From Your Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace#running-a-liferay-instance-from-your-workspace)
section.

The first thing that comes to mind when interacting with a server is simply
turning it on/off. You can use the `server` sub-command to accomplish this. To
turn on a Liferay server, you can run

    blade server start -b

Likewise, to turn off a server, run

    blade server stop

Once you've started your bundle, you can examine your server's OSGi container by
using the `sh` command, which provides access to your server using the Felix
Gogo shell. For example, to check if you successfully deployed your application
from the previous section, you could run:

    blade sh lb

Your output lists a long list of modules that are active/installed in your
server's OSGi container.

![Figure 1: Blade CLI accesses the Gogo shell script to run the `lb` command.](../../../../images/blade-sh.png)

You can run any Gogo command using `blade sh`. See the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
section for more information on this tool.

You can access a workspace's Gradle wrapper automatically by using the `gw`
command. Since the workspace's Gradle wrapper resides in its root folder, it can
sometimes be a hassle running it for a deeply nested module (e.g.,
`../../../../gradlew compileJava`). Running Gradle wrapper from Blade CLI
automatically detects the Gradle wrapper and can run it anywhere. For instance,
if you're in a deeply nested directory trying to compile a module, you can
simply run:

    blade gw compileJava

This example runs the `compileJava` command using the workspace's include
wrapper. Any Gradle command can be used with the `gw` Blade CLI command.

Awesome! You successfully deployed a project from Blade CLI and learned how to
conveniently interact with @product@ using Blade CLI. Next, you'll learn more
about using a workspace from Liferay IDE.

## Using Liferay IDE

Liferay IDE offers the functionality provided by Blade CLI in a GUI. Before you
begin learning about managing your modules from Liferay IDE, you should make
sure a Liferay server is configured in your Eclipse workspace. Although a server
is not required to manage your module projects, it's helpful for testing once
you've finished developing them. You can learn how to create a Liferay bundle
and link it to your workspace in the
[Using Liferay IDE to Create Your Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace#using-liferay-ide-to-create-your-workspace)
section.





