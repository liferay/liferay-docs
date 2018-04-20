# Blade CLI [](id=blade-cli)

The [Blade CLI](https://github.com/liferay/liferay-blade-cli/) tool is the
easiest way for Liferay developers to create new Liferay modules. Although the
Plugins SDK is also supported, Blade CLI lets you create projects that can be
used with any IDE or development environment. Blade CLI is a command line tool
used to create/manage @product@ instances. This tool set also provides a host of
sub-commands that help Liferay developers create and deploy modules (Gradle or
Maven). The following sub-commands are callable in the Blade CLI environment:

<!-- TODO: Will Plugins SDK be supported in Liferay 7.1? -Cody -->

- *convert*: Converts a Plugins SDK plugin project to a Gradle Workspace
  project.
- *create*: Creates a new Liferay module project from available templates.
- *deploy*: Builds and deploys bundles to the Liferay module framework.
- *gw*: Executes Gradle command using the Gradle Wrapper, if detected.
- *help*: Gives help on a specific command.
- *init*: Initializes a new Liferay Workspace.
- *install*: Installs a bundle into Liferay's module framework.
- *open*: Opens or imports a file or project in Liferay @ide@.
- *samples*: Generates a sample project.
- *server*: Starts or stops server defined by your Liferay project.
- *sh*: Connects to @product@, executes succeeding Gogo command, and returns
  output.
- *update*: Updates Blade CLI to latest version.
- *version*: Displays version information about Blade CLI.

<!-- TODO: New options `outputs` and `upgradeProps`. Should these be added to
list? - Cody -->

For additional information on these sub-commands, run the sub-command with the
`--help` flag (e.g., `blade samples --help`).

In this set of tutorials, you'll learn how to use these commands to create and
test @product@ instances and modules.
