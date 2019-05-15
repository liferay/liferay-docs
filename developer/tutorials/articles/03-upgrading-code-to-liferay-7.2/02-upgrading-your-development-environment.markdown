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

Continue on to set up a workspace.

## Setting Up Liferay Workspace

You must set up your workspace development environment before you begin
upgrading your custom apps. If you don't have an existing workspace, follow the
step for creating one. If you have an existing workspace, follow the step on
importing it into the Code Upgrade Planner.

### Creating New Liferay Workspace

Initiating this step in the Code Upgrade Planner loads the Liferay Workspace
Project wizard.

1.  Give your new workspace a name.

2.  Choose the build type (Gradle or Maven) you prefer for your workspace
    environment and future Liferay projects.

3.  Click Finish.

You now have a new Liferay Workspace available in the Code Upgrade Planner!

For more information on creating a Liferay Workspace outside the planner, see
the
[Creating a Liferay Workspace](/docs/reference/7-2/-/knowledge_base/r/creating-a-liferay-workspace)
section.

### Importing Existing Liferay Workspace

If you already have an existing 7.x Liferay Workspace, you should import it
into the planner. Once you initiate this step, you're given a File
Explorer/Manager to select your existing workspace. After selecting it, the
workspace is imported into the Project Explorer.

For more information importing a workspace into your IDE, see
[this article](/docs/reference/7-2/-/knowledge_base/r/importing-projects-in-dev-studio).



## Configuring Liferay Workspace Settings

You must configure your workspace with the @product@ version you intend to
upgrade to. You should verify the workspace's

- Bundle URL
- Target Platform Version

The bundle URL version and target platform version must match.

Visit these steps to begin.

### Configuring Bundle URL

The bundle URL points to the @product@ version you want workspace to download.
When initiating this step, your workspace's Bundle URL property is updated to
the latest release of @product-ver@.

For more information on configuring a workspace's bundle URL, see the
[Adding a Liferay Bundle to Liferay Workspace](/docs/reference/7-2/-/knowledge_base/r/adding-a-liferay-bundle-to-liferay-workspace)
article.

### Configuring Target Platform Version

The target platform is the @product@ version you intend to develop for in your
workspace. This is used to specify dependencies associated with a specific
release. You set the target platform, define your dependencies, and workspace
automatically assigns the dependency versions based on the set @product@
version. When initiating this step, your workspace's Target Platform property is
updated to the latest release of @product-ver@.

For more information on this, see the
[Managing the Target Platform](/docs/reference/7-2/-/knowledge_base/r/managing-the-target-platform)
article.

## Initializing Server Bundle

Once your workspace is configured for the @product@ version you're upgrading to,
you can initialize the server bundle. This involves downloading the bundle and
extracting it into its folder (e.g., `bundles`). If you have an existing
workspace already equipped with an older Liferay bundle, this deletes the old
bundle and initializes the new one.

If you're upgrading your code manually and working in Dev Studio, you can do
this by right-clicking the workspace project and selecting *Liferay* &rarr;
*Initialize Server Bundle*. See the
[Installing a Server in IntelliJ](/docs/reference/7-2/-/knowledge_base/r/installing-a-server-in-intellij)
article if you use IntelliJ instead. Visit the
[Managing Your Liferay Server with Blade CLI](/docs/reference/7-2/-/knowledge_base/r/managing-your-liferay-server-with-blade-cli)
article for information on how to do this via the command line.
