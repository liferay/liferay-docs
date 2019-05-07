# Migrating Plugins SDK Projects to Liferay Workspace

The Plugins SDK was deprecated for @product@ 7.0 and removed for @product@ 7.1.
Therefore, to upgrade your custom apps to @product-ver@, you must migrate them
to a new environment. 
[Liferay Workspace](/docs/reference/7-2/-/knowledge_base/r/liferay-workspace) is
the recommended environment for your code migration and will be the assumed
choice in this section.

There are two steps you must follow to migrate your custom code to workspace:

1. Copy the Plugins SDK project to the applicable workspace folder.

2. Convert the Plugins SDK project to a supported workspace build type.

## Copy the Plugins SDK Project

The majority of Plugins SDK projects belong in the workspace's `wars` folder.
You can consult the
[Workspace Anatomy](/docs/reference/7-2/-/knowledge_base/r/liferay-workspace#workspace-anatomy)
section for a full overview of a workspace's folder structure and choose where
your custom app should reside. Once you've made the decision, copy your custom
apps to the applicable workspace folder. If you plan to use a Gradle-based
Liferay Workspace, you can execute a Blade CLI command to expedite the migration
process. See the next section for more information. There is not a Maven command
for the conversion yet, so you must do the copy manually for Maven-based
workspaces.

## Convert the Plugins SDK Project

Liferay Workspace can be generated as a Gradle or Maven environment, but it does
not support the Plugins SDK's Ant build. Because of this, you must convert your
projects to one of the supported build tools (i.e., Gradle or Maven).

There is a [Blade CLI](/docs/reference/7-2/-/knowledge_base/r/blade-cli) command
that automatically converts your Ant projects












