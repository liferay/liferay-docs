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

    blade server start

This starts the server in the background. You can tail the logs by adding the
`-t` flag. If you prefer starting the server in the foreground, run `blade
server run`. Additionally, if you prefer starting the server in debug mode, add
the `-d` flag.

Debug mode can be customized by adding the `-p` tag to set the custom remote
debugging port (defaults are `8080` for Tomcat and `8787` for Wildfly) and/or
the boolean `-s` tag to set whether you want to suspend the started server until
the debugger is connected.

Once you've started your server, you can examine its OSGi container by using the
`sh` command, which provides access to your server using the Felix Gogo shell.
For example, to check if you successfully deployed your application from the
previous section, you could run:

    blade sh lb

Your output lists a long list of modules that are active/installed in your
server's OSGi container.

![Figure 1: Blade CLI accesses the Gogo shell script to run the `lb` command.](../../../images/blade-sh.png)

You can run any Gogo command using `blade sh`. This command requires
[Developer Mode](/develop/tutorials/-/knowledge_base/7-1/using-developer-mode-with-themes#setting-developer-mode-for-your-server-using-portal-developer-properties)
to be enabled. Developer Mode is enabled in
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
by default. See the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
section for more information on this tool.

To turn off your server, run

    blade server stop

Awesome! You learned how to conveniently interact with @product@ using Blade
CLI.
