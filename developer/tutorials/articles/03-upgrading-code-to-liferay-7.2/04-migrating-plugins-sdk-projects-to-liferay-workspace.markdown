# Migrating Plugins SDK Projects to Liferay Workspace

<div class="learn-path-step">
    <p>Migrating Plugins SDK Projects to Liferay Workspace<br>Step 1 of 1</p>
</div>

The Plugins SDK was deprecated for @product@ 7.0 and removed for @product@ 7.1.
Therefore, to upgrade your custom apps to @product-ver@, you must migrate them
to a new environment. 
[Liferay Workspace](/docs/reference/7-2/-/knowledge_base/r/liferay-workspace) is
the recommended environment for your code migration and will be the assumed
choice in this section.

There are two steps you must follow to migrate your custom code to workspace:

1. Import the Plugins SDK project into the Code Upgrade Planner.

2. Convert the Plugins SDK project to a supported workspace build type.

You'll step through importing a Plugins SDK project first.

## Importing Existing Plugins SDK Projects

Initiating this step in the Code Upgrade Planner imports your Plugins SDK
projects into the Code Upgrade Planner. These projects originate from the
Plugins SDK you set when the Upgrade Planner process was started.

If you're manually upgrading your code, you can skip this step.

You're now ready to migrate your Plugins SDK projects to your new workspace!

## Migrating Existing Plugins to Workspace

Liferay Workspace can be generated as a Gradle or Maven environment, but it does
not support the Plugins SDK's Ant build. Because of this, you must convert your
projects to one of the supported build tools:

- Gradle
- Maven

When initiating this step for a Gradle-based workspace, your Ant-based
Plugins SDK project is copied to the applicable workspace folder based on its
project type (e.g., wars) and is converted to a Gradle project. There is also a
Blade CLI command that completes this via the command line. Visit the
[Converting Plugins SDK Projects with Blade CLI](/docs/reference/7-2/-/knowledge_base/r/converting-plugins-sdk-projects-with-blade-cli)
article for more information.

If you're migrating your Ant project to a Maven workspace, you must manually
copy the project to the applicable folder based on the project type (e.g.,
`wars`). The majority of Plugins SDK projects belong in the workspace's `wars`
folder. You can consult the
[Workspace Anatomy](/docs/reference/7-2/-/knowledge_base/r/liferay-workspace#workspace-anatomy)
section for a full overview of a workspace's folder structure and choose where
your custom app should reside. Once you've made the decision, copy your custom
app to the applicable workspace folder.

Then you must convert your project from Ant to Maven. You'll have to
complete this conversion manually.

Once you're finished, you should have your project(s) residing in the applicable
workspace folders as Gradle/Maven projects.
