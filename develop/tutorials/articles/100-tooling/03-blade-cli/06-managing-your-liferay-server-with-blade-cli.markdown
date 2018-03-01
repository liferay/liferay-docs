# Managing Your Liferay Server with Blade CLI [](id=managing-module-projects-with-blade-cli)

In this tutorial, you'll learn how to manage a Liferay server using Blade CLI.
For example, Blade CLI lets you install, start, stop, inspect, and modify a
Liferay server.

Make sure you're in a Liferay Workspace and have a bundle installed and
configured in the workspace before testing the Blade CLI commands on your own.
To learn more about installing a Liferay server in a Liferay Workspace, see the
[Creating a Liferay Workspace with Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/creating-a-liferay-workspace-with-liferay-ide)
section. The following Blade CLI commands are covered in this sub-section:

- `server`
- `sh`

The first thing that comes to mind when interacting with a server is simply
turning it on/off. You can use the `server` sub-command to accomplish this. To
turn on a Liferay server (Tomcat or Wildfly/JBoss), you can run

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

![Figure 1: Blade CLI accesses the Gogo shell script to run the `lb` command.](../../../images/blade-sh.png)

You can run any Gogo command using `blade sh`. See the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
section for more information on this tool.

Awesome! You learned how to conveniently interact with @product@ using Blade
CLI.
