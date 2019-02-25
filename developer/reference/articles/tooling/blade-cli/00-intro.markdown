# Blade CLI [](id=blade-cli)

The [Blade CLI](https://github.com/liferay/liferay-blade-cli/) tool is the
easiest way for Liferay developers to create and manage Liferay projects (Gradle
or Maven). Blade CLI lets you

- create Liferay projects that can be used with any IDE or development
  environment
- create/manage @product@ instances
- deploy Liferay projects
- etc.

The table below lists all Blade CLI commands for the latest Blade CLI release.
Helpful descriptions for the commands are also provided.

<table class="table table-striped table-bordered">
<thead>
<tr>
<th>Command</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>convert</code></td>
<td>Converts a Plugins SDK plugin project to a Gradle Workspace project. See the <a href="/developer/reference/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications#running-the-migration-command">Running the Migration Command</a> command for details.</td>
</tr>
<tr>
<td><code>create</code></td>
<td>Creates a new Liferay project from available templates. See the <a href="/developer/reference/-/knowledge_base/7-2/creating-a-project#blade-cli">Creating a Project section for Blade CLI</a> section for more information.</td>
</tr>
<tr>
<td><code>deploy</code></td>
<td>Builds and deploys projects to @product@. See the <a href="/developer/reference/-/knowledge_base/7-2/deploying-a-project#blade-cli">Deploying a Project section for Blade CLI</a> section for more information.</td>
</tr>
<tr>
<td><code>extension install</code></td>
<td>Installs an extension into Blade CLI.</td>
</tr>
<tr>
<td><code>extension uninstall</code></td>
<td>Uninstalls an extension from Blade CLI.</td>
</tr>
<tr>
<td><code>gw</code></td>
<td>Executes a Gradle command using the Gradle Wrapper, if detected.</td>
</tr>
<tr>
<td><code>help</code></td>
<td>Provides information for Blade CLI's commands.</td>
</tr>
<tr>
<td><code>init</code></td>
<td>Initializes a new Liferay Workspace. See the <a href="/developer/reference/-/knowledge_base/7-2/creating-a-liferay-workspace#blade-cli">Creating a Liferay Workspace</a> article for more information.</td>
</tr>
<tr>
<td><code>samples</code></td>
<td>Generates a sample project. See the <a href="/developer/reference/-/knowledge_base/7-2/generating-project-samples-with-blade-cli">Generating Project Samples with Blade CLI</a> article for more information.</td>
</tr>
<tr>
<td><code>server init</code></td>
<td>Initializes the Liferay server configured in Liferay Workspace's <code>gradle.properties</code> file. Set the <code>liferay.workspace.bundle.url</code> property to configure the server to initialize.</td>
</tr>
<tr>
<td><code>server start</code></td>
<td>Starts the Liferay server in the background. You can add the <code>-d</code> flag to start the server in debug mode. Debug mode can be customized by adding the <code>-p</code> tag to set the port number (defaults are <code>8080</code> for Tomcat and <code>8787</code> for Wildfly) and/or the boolean <code>-s</code> tag to set whether you want to suspend the started server until the debugger is connected. See the <a href="/developer/reference/-/knowledge_base/7-2/managing-your-liferay-server-with-blade-cli">Managing Your Liferay Server with Blade CLI</a> article for more information.</td>
</tr>
<tr>
<td><code>server stop</code></td>
<td>Stops the Liferay server.</td>
</tr>
<tr>
<td><code>server run</code></td>
<td>Starts the Liferay server in the foreground. See the <code>server start</code> property for more information.</td>
</tr>
<tr>
<td><code>sh</code></td>
<td>Connects to @product@, executes succeeding Gogo command, and returns output. For example, <code>blade sh lb</code> lists @product@'s bundles using the Gogo shell. See the <a href="/developer/reference/-/knowledge_base/7-2/managing-your-liferay-server-with-blade-cli">Managing Your Liferay Server with Blade CLI</a> article for more information.</td>
</tr>
<tr>
<td><code>update</code></td>
<td>Updates Blade CLI to the latest version. See the <a href="/developer/reference/-/knowledge_base/7-2/updating-blade-cli">Updating Blade CLI</a> article for details.</td>
</tr>
<tr>
<td><code>watch</code></td>
<td>Watches for changes to a deployed project and automatically redeploys it when changes are detected. This command does not rebuild your project and copy it to Portal every time a change is detected, but rather, installs it into the runtime as a reference. This means that the Portal does not make a cached copy of the project. This allows the Portal to see changes that are made to your project's files immediately. When you cancel the <code>watch</code> task, your module is uninstalled automatically. The <code>blade deploy -w</code> command works similarly to <code>blade watch</code>, except it manually recompiles and deploys your project every time a change is detected. This causes slower update times, but does preserve your deployed project in Portal when it's shut down.</td>
</tr>
<tr>
<td><code>version</code></td>
<td>Displays version information about Blade CLI.</td>
</tr>
</tbody>
</table>

<!-- TODO: Find more about new `upgradeProps` property. --Cody-->

For information on command options, run the command with the `--help` flag
(e.g., `blade samples --help`).

Continue on to learn about leveraging Blade CLI to create and test @product@
instances and projects.
