# Creating a Liferay Workspace

You can create a Liferay Workspace using the following tools:

- [Blade CLI](#blade-cli)
- [Dev Studio](#dev-studio)
- [IntelliJ](#intellij)
- [Maven](#maven)

Visit the appropriate section to learn how to create a workspace with the
highlighted tool.

## Blade CLI

1.  Navigate to the folder where you want your workspace generated.

2.  Run the following command to build a Gradle-based workspace:

    ```bash
    blade init -v 7.2 [WORKSPACE_NAME]
    ```

    | **Note**: Workspace automatically sets the default @product@ version to
    | develop against when it's first initialized. The default version is set to
    | 7.1. This is applied to create projects using appropriately versioned
    | project templates. If you wish to develop projects for a different
    | @product@ version, you can pass a different version in the Blade init
    | command. For example,
    | 
    | ```bash
    | blade init -v 7.0 [WORKSPACE_NAME]
    | ```
    | 
    | You can update the default version after it has been set by opening your
    | workspace's `.blade/settings.properties` file and setting the
    | `liferay.version.default` property.

You can also create a Maven-based workspace with Blade CLI. See the
[Maven](#maven) section for more information.

## Dev Studio

1.  Select *File* &rarr; *New* &rarr; *Liferay Workspace Project*.

    ![Figure 1: By selecting *Liferay Workspace Project*, you begin the process of creating a new workspace for your Liferay projects.](../../../images/selecting-liferay-workspace.png)

    A New Liferay Workspace dialog appears, presenting several configuration
    options.

2.  Give your workspace project a name.

3.  Choose the location where you'd like your workspace to reside. Checking the
    *Use default location* checkbox places your Liferay Workspace in the Eclipse
    workspace you're working in.

4.  Select the build tool you want your workspace to be build with (i.e., Gradle
    or Maven).

5.  Choose the Liferay Portal version you plan to develop for (i.e., 7.2, 7.1 or
    7.0).

6.  Select the specific target platform version corresponding to the GA release
    you're developing for (e.g., 7.1.0 &rarr; 7.1 GA1). For more information on
    target platform benefits, see the
    [Managing the Target Platform for Liferay Workspace]()
    articles.

7.  Check the *Download Liferay bundle* checkbox if you'd like to auto-generate
    a Liferay instance in your workspace. You'll be prompted to name the server
    and provide the server's download URL, if selected.

    | **Note:** You can configure a pre-existing Liferay bundle in your
    | workspace by creating a folder for the bundle in your workspace and
    | configuring it in the workspace's `gradle.properties` file by setting the
    | `liferay.workspace.home.dir` property.

8.  Check the Add project to working set checkbox if you want the workspace to
    be a part of a larger working set you've already created in Dev Studio. For
    more information on working sets, visit
    [Eclipse Help](https://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.platform.doc.user%2Fconcepts%2Fcworkset.htm).

9  Click *Finish* to create your Liferay Workspace.

    ![Figure 2: Dev Studio provides an easy-to-follow menu to create your Liferay Workspace.](../../../images/new-workspace-menu.png)

A dialog appears prompting you to open the Liferay Workspace perspective. Click
*Yes*, and your perspective will switch to Liferay Workspace.

## IntelliJ

1.  Open the New Project wizard by selecting *File* &rarr; *New* &rarr;
    *Project*. If you're starting IntelliJ for the first time, you can do this
    by selecting *Create New Project* in the opening window.

2.  Select *Liferay* from the left menu.

3.  Choose the build type for your workspace (i.e., Gradle or Maven). Then click
    *Next*.

    ![Figure 1: Choose *Liferay Gradle Workspace* or *Liferay Maven Workspace*, depending on the build you prefer.](../../../images/intellij-workspace-build.png)

4.  Specify your workspace's name, location, intended @product@ version,
    [target platform](/docs/reference/7-2/-/knowledge_base/reference/managing-the-target-platform-for-liferay-workspace),
    and SDK (i.e., Java JDK). Then click *Finish*.

    ![Figure 2: Specify your workspace's configurations.](../../../images/intellij-workspace-project.png)

5.  A window opens for additional build configurations for the build type you
    selected (i.e., Gradle or Maven). Verify the settings and click *OK*.

## Maven

1.  Execute the following Maven command:

        mvn archetype:generate -Dfilter=liferay

2.  Select the `com.liferay.project.templates.workspace` archetype to generate.

3.  Step through the remaining prompts to generate the workspace project.

 A Maven-based Liferay Workspace can also be generated using Blade CLI. Follow
 [Blade CLI's](#blade-cli) workspace creation instructions and insert the `-b
 maven` parameter in the Blade command.