# Upgrading Your Development Environment

<div class="learn-path-step">
    <p>Upgrading Your Development Environment<br>Step 1 of 1</p>
</div>

A [Liferay Workspace](/docs/reference/7-2/-/knowledge_base/r/liferay-workspace)
is a generated environment that is built to hold and manage your Liferay
projects. It is intended to aid in the management of Liferay projects by
providing various build scripts and configured properties.

Liferay Workspace is the recommended environment for your code migration;
therefore, it will be the assumed development environment in this section.

You must set up your workspace development environment before you begin
upgrading your custom apps.

## Installing a Liferay Workspace

First, you must install a Liferay Workspace instance before proceeding with the
code upgrade process. You can do this by
[creating a new workspace](/docs/reference/7-2/-/knowledge_base/r/creating-a-liferay-workspace)
or
[importing an existing one](/docs/reference/7-2/-/knowledge_base/r/importing-projects-in-dev-studio)
into your current IDE, if necessary.

## Configuring Liferay Workspace

You must configure your workspace with the @product@ version you intend to
upgrade to. You should verify the workspace's

- Bundle URL
- Target Platform Version

| **Note:** The bundle URL version and target platform version must match.

The bundle URL points to the @product@ version you want workspace to download.
To configure the workspace's bundle URL, see the
[Adding a Liferay Bundle to Liferay Workspace](/docs/reference/7-2/-/knowledge_base/r/adding-a-liferay-bundle-to-liferay-workspace)
article.

The target platform is the @product@ version you intend to develop for in your
workspace. This is used to specify dependencies associated with a specific
release. You set the target platform, define your dependencies, and workspace
automatically assigns the dependency versions based on the set @product@
version. See the
[Managing the Target Platform](/docs/reference/7-2/-/knowledge_base/r/managing-the-target-platform)
article for more information.

## Initializing a Server Bundle

Once your workspace is configured for the @product@ version you're upgrading to,
initialize the server bundle. You can do this in Dev Studio by right-clicking
the workspace project and selecting *Liferay* &rarr; *Initialize Server Bundle*.
If you have an existing workspace already equipped with an older Liferay bundle,
delete its folder (e.g., `bundles`) and initialize a new bundle. See the
[Installing a Server in IntelliJ](/docs/reference/7-2/-/knowledge_base/r/installing-a-server-in-intellij)
article if you use IntelliJ instead. Visit the
[Managing Your Liferay Server with Blade CLI](/docs/reference/7-2/-/knowledge_base/r/managing-your-liferay-server-with-blade-cli)
article for information on how to do this via the command line.
