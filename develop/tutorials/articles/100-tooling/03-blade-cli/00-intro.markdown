# Blade CLI [](id=blade-cli)

The [Blade CLI](https://github.com/liferay/liferay-blade-cli/) tool is the
easiest way for Liferay developers to create new Liferay modules. Although the
Plugins SDK is also supported, Blade CLI lets you create projects that can be
used with any IDE or development environment. Blade CLI is a command line tool
bootstrapped on to a Gradle based environment that is used to build Liferay 7.0
modules. This tool set provides a host of sub-commands that help Liferay
developers create and deploy modules to a Liferay instance. The following
sub-commands are callable in the Blade CLI environment:

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

In this set of tutorials, you'll learn how to use these commands to create and
test Liferay modules.
