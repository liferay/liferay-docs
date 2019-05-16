---
header-id: converting-plugins-sdk-projects-with-blade-cli
---

# Converting Plugins SDK Projects with Blade CLI

[TOC levels=1-4]

Blade CLI offers a command that can automatically migrate a Plugins SDK project
to a Liferay Workspace. During the process, the Ant-based Plugins SDK project is
copied to the applicable workspace folder based on its project type (e.g.,
`wars`) and is converted to a Gradle-based Liferay Workspace project. This
drastically speeds up the migration process when upgrading to a Liferay
Workspace from a legacy Plugins SDK.

| **Note:** There is not a Maven command for the migration process yet, so you
| must complete it manually for Maven-based workspaces.

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

    | **Note:** If the `convert` task doesn't work as described above, you may
    | need to update your Blade CLI version. See the
    | [Updating Blade CLI](/docs/7-2/reference/-/knowledge_base/r/updating-blade-cli)
    | article for more information.

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

Your project is successfully converted to a Gradle-based workspace project!
Great job!
