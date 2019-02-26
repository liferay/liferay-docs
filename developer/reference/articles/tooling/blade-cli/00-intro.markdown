---
header-id: blade-cli
---

# Blade CLI

[Blade CLI](https://github.com/liferay/liferay-blade-cli/) is a command line
tool that provides the easiest way for Liferay developers to create, manage, and
deploy Liferay projects (Gradle or Maven). Blade CLI lets you

- create Liferay projects that can be used with any IDE or development
  environment
- create/manage @product@ instances
- deploy Liferay projects
- etc.

The table below lists all Blade CLI commands for the latest Blade CLI release.
Helpful descriptions for the commands are also provided.

Command | Description
------- | -------------
`convert` | Converts a Plugins SDK plugin project to a Gradle Workspace project. See the [Running the Migration Command](/developer/reference/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications#running-the-migration-command) command for details.
`create` | Creates a new Liferay project from available templates. See the [Creating a Project section for Blade CLI](/developer/reference/-/knowledge_base/7-2/creating-a-project#blade-cli) for more information.
`deploy` | Builds and deploys projects to @product@. See the [Deploying a Project section for Blade CLI](/developer/reference/-/knowledge_base/7-2/deploying-a-project#blade-cli) for more information.
`extension install` | Installs an extension into Blade CLI.
`extension uninstall` | Uninstalls an extension from Blade CLI.
`gw` | Executes a Gradle command using the Gradle Wrapper, if detected (e.g., `blade gw tasks`).
`help` | Provides information for Blade CLI's commands.
`init` | Initializes a new Liferay Workspace. See the [Creating a Liferay Workspace](/developer/reference/-/knowledge_base/7-2/creating-a-liferay-workspace#blade-cli) article for more information.
`samples` | Generates a sample project. See the [Generating Project Samples with Blade CLI](/developer/reference/-/knowledge_base/7-2/generating-project-samples-with-blade-cli) article for more information.
`server init` | Initializes the Liferay server configured in Liferay Workspace's `gradle.properties` file. Set the `liferay.workspace.bundle.url` property to configure the server to initialize.
`server start` | Starts the Liferay server in the background. You can add the `-d` flag to start the server in debug mode. Debug mode can be customized by adding the `-p` tag to set the custom remote debugging port (defaults are `8080` for Tomcat and `8787` for Wildfly) and/or the boolean `-s` tag to set whether you want to suspend the started server until the debugger is connected. See the [Managing Your Liferay Server with Blade CLI](/developer/reference/-/knowledge_base/7-2/managing-your-liferay-server-with-blade-cli) article for more information.
`server stop` | Stops the Liferay server.
`server run` | Starts the Liferay server in the foreground. See the `server start` property for more information.
`sh` | Connects to @product@, executes succeeding Gogo command, and returns output. For example, `blade sh lb` lists @product@'s bundles using the Gogo shell. See the [Managing Your Liferay Server with Blade CLI](/developer/reference/-/knowledge_base/7-2/managing-your-liferay-server-with-blade-cli) article for more information.
`update` | Updates Blade CLI to the latest version. See the [Updating Blade CLI](/developer/reference/-/knowledge_base/7-2/updating-blade-cli) article for details.
`watch` | Watches for changes to a deployed project and automatically redeploys it when changes are detected. This command does not rebuild your project and copy it to Portal every time a change is detected, but rather, installs it into the runtime as a reference. This means that the Portal does not make a cached copy of the project. This allows the Portal to see changes that are made to your project's files immediately. When you cancel the `watch` task, your module is uninstalled automatically. The `blade deploy -w` command works similarly to `blade watch`, except it manually recompiles and deploys your project every time a change is detected. This causes slower update times, but does preserve your deployed project in Portal when it's shut down.
`version` | Displays version information about Blade CLI.

<!-- TODO: Find more about new `upgradeProps` property. --Cody-->

For information on command options, run the command with the `--help` flag
(e.g., `blade samples --help`).

Continue on to learn about leveraging Blade CLI to create and test @product@
instances and projects.
