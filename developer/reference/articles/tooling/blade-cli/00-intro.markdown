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
<td>Converts a Plugins SDK plugin project to a Gradle Workspace project. See the [Running the Migration Command](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications#running-the-migration-command) command for details.</td>
</tr>
<tr>
<td><code>create</code></td>
<td>Creates a new Liferay project from available templates. See the [Creating a Project section for Blade CLI](/developer/reference/-/knowledge_base/7-2/creating-a-project#blade-cli) section for more information.</td>
</tr>
<tr>
<td><code>deploy</code></td>
<td>Builds and deploys projects to @product@. See the [Deploying a Project section for Blade CLI](/developer/reference/-/knowledge_base/7-2/deploying-a-project#blade-cli) section for more information.</td>
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
<td>Initializes a new Liferay Workspace. See the [Creating a Liferay Workspace](/developer/reference/-/knowledge_base/7-2/creating-a-liferay-workspace#blade-cli) article for more information.</td>
</tr>
<tr>
<td><code>samples</code></td>
<td>Generates a sample project. See the [Generating Project Samples with Blade CLI](/developer/reference/-/knowledge_base/7-2/generating-project-samples-with-blade-cli) article for more information.</td>
</tr>
<tr>
<td><code>server init</code></td>
<td></td>
</tr>
<tr>
<td><code>server start</code></td>
<td></td>
</tr>
<tr>
<td><code>server stop</code></td>
<td></td>
</tr>
<tr>
<td><code>server run</code></td>
<td></td>
</tr>
<tr>
<td><code>sh</code></td>
<td>Connects to @product@, executes succeeding Gogo command, and returns output. For example, `blade sh lb` lists @product@'s bundles using the Gogo shell. See the [Managing Your Liferay Server with Blade CLI](/developer/reference/-/knowledge_base/7-2/managing-your-liferay-server-with-blade-cli) article for more information.</td>
</tr>
<tr>
<td><code>update</code></td>
<td>Updates Blade CLI to the latest version. See the [Updating Blade CLI](/developer/reference/-/knowledge_base/7-2/updating-blade-cli) article for details.</td>
</tr>
<tr>
<td><code>watch</code></td>
<td>Watches for changes to a deployed project and automatically redeploys it when changes are detected. This command does not rebuild your project and copy it to Portal every time a change is detected, but rather, installs it into the runtime as a reference. This means that the Portal does not make a cached copy of the project. This allows the Portal to see changes that are made to your project's files immediately. When you cancel the `watch` task, your module is uninstalled automatically. The `blade deploy -w` command works similarly to `blade watch`, except it manually recompiles and deploys your project every time a change is detected. This causes slower update times, but does preserve your deployed project in Portal when it's shut down.</td>
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
