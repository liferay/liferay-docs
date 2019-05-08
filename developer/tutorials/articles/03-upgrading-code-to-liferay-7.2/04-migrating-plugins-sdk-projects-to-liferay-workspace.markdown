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

## Copying the Plugins SDK Project

The majority of Plugins SDK projects belong in the workspace's `wars` folder.
You can consult the
[Workspace Anatomy](/docs/reference/7-2/-/knowledge_base/r/liferay-workspace#workspace-anatomy)
section for a full overview of a workspace's folder structure and choose where
your custom app should reside. Once you've made the decision, copy your custom
apps to the applicable workspace folder. If you plan to use a Gradle-based
Liferay Workspace, you can execute a Blade CLI command to automate the migration
process. See the next section for more information. There is not a Maven command
for the conversion yet, so you must do the copy manually for Maven-based
workspaces.

## Converting the Plugins SDK Project

Liferay Workspace can be generated as a Gradle or Maven environment, but it does
not support the Plugins SDK's Ant build. Because of this, you must convert your
projects to one of the supported build tools:

- [Gradle](#converting-to-gradle)
- [Maven](#converting-to-maven)

There is a [Blade CLI](/docs/reference/7-2/-/knowledge_base/r/blade-cli) command
that automatically converts your Ant projects to Gradle projects. You'll learn
how to leverage this next.

### Converting to Gradle

To copy your Plugins SDK project and convert it to Gradle, you can execute the
Blade `convert` command:

1.  Navigate to the root folder of your workspace in a command line tool.

2.  Execute the following command:

    ```bash
    blade convert -s [PLUGINS_SDK_PATH] [PLUGINS_SDK_PROJECT_NAME]
    ```

    You must provide the path of the Plugins SDK your project resides in and the
    project name you want to convert. If you prefer converting all the Plugins
    SDK projects at once, replace the project name variable with `-a` (i.e.,
    specifying all plugins).

    This Gradle conversion process also works for themes; they're converted to
    automatically leverage NodeJS. If you're converting a Java-based theme, add
    the `-t` option to your command too. This will incorporate the
    [Theme Builder](/docs/reference/7-2/-/knowledge_base/r/theme-builder-gradle-plugin)
    Gradle plugin for the theme instead. For more information on upgrading
    6.2 themes, see the
    [Upgrade a 6.2 Theme to 7.2](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-6-2-themes-to-7-2).

| **Note:** When converting a Service Builder project, the `convert` task
| automatically extracts the project's service interfaces and implementations
| into OSGi modules (i.e., *-impl and *-api) and places them in the workspace's
| `modules` folder. Your portlet and controller logic will remain a WAR and
| reside in the `wars` folder.

Once you run the `convert` command, your project is copied from the Plugins SDK
into the applicable workspace folder based on the project type. It is also
converted from Ant to Gradle.

### Converting to Maven

If you're migrating your Ant project to a Maven workspace, you should have
copied the project to the applicable folder based on the project type (e.g.,
`wars`). Now you must convert your project from Ant to Maven. You'll have to
complete this conversion manually.

Once you're finished, you should have your project(s) residing in the applicable
workspace folders as Maven projects.
