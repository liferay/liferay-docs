---
header-id: managing-your-liferay-server-with-blade-cli
---

# Managing Your Liferay Server with Blade CLI

[TOC levels=1-4]

You can  manage a Liferay server using Blade CLI. Managing a server with Blade
CLI should be done in a 
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace).

Blade CLI has commands for installing, starting, stopping, inspecting, and
modifying a Liferay server:

1.  Make sure you've created a Liferay Workspace. See the
    [Creating a Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/creating-a-liferay-workspace#blade-cli)
    article for more information.

2.  Initialize a Liferay server by running

        blade server init

    This downloads the @product@ bundle set in your workspace's
    `gradle.propeties` file. See the
    [Adding a Liferay Bundle to Workspace](/docs/7-2/reference/-/knowledge_base/r/adding-a-liferay-bundle-to-workspace)
    article for more information.

3.  Start your Liferay server (Tomcat or Wildfly/JBoss) by running

        blade server start

    This starts the server in the background. You can tail the logs by adding
    the `-t` flag. If you prefer starting the server in the foreground, run
    `blade server run`. Additionally, if you prefer starting the server in debug
    mode, add the `-d` flag. See the
    [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli) article for
    additional flags you can set when starting your Liferay server.

4.  Examine your server's OSGi container by using Blade CLI's `sh` command,
    which provides access to your server using the Felix Gogo shell. For
    example, to check if you successfully deployed your application from the
    previous section, you could run:

        blade sh lb

    Your output lists a long list of modules that are active/installed in your
    server's OSGi container.

    ![Figure 1: Blade CLI accesses the Gogo shell script to run the `lb` command.](../../../images/blade-sh.png)

    You can run any Gogo command using `blade sh`. This command requires
    [Developer Mode](/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes#setting-developer-mode-for-your-server-using-portal-developer-properties)
    to be enabled. Developer Mode is enabled in workspace by default. See the
    [Using the Felix Gogo Shell](/docs/7-2/reference/-/knowledge_base/r/using-the-felix-gogo-shell)
    section for more information on this tool.

5.  Once you're finished modifying your Liferay bundle, you can package it as a
    sharable file by running this command:

        blade gw distBundle[Zip|Tar]

    This lets you create a ZIP or TAR file to share with others. This option is
    only available with Gradle at this time. The above command leverages Blade
    CLI's `gw` option, which executes the project's Gradle wrapper.

    <!-- TODO: Add way for producing a distributable workspace using Blade, when
    available. It can only be done currently with ./gradlew distBundle[Zip|Tar].
    -->

6.  Turn off your Liferay server:

        blade server stop

To reference all of Blade CLI's available options, see the
[Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli) article.

Awesome! You learned how to interact with @product@ using Blade CLI.
