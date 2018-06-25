# Creating a Liferay Workspace with IntelliJ IDEA [](id=creating-a-liferay-workspace-with-intellij-idea)

In this tutorial, you'll learn how to generate a Liferay Workspace using
IntelliJ IDEA, which runs on
[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli) behind the
scenes. IntelliJ gives you a graphical interface instead of the command prompt,
which can streamline your workflow. You'll also learn how to import an existing
Liferay Workspace into IntelliJ. To learn more about Liferay Workspaces, visit
its dedicated
[tutorial section](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).

## Creating a Liferay Workspace [](id=creating-a-liferay-workspace)

Follow the steps below to create a Liferay Workspace:

1.  Open the New Project wizard by selecting *File* &rarr; *New* &rarr;
    *Project*. If you're starting IntelliJ for the first time, you can do this
    by selecting *Create New Project* in the opening window.

2.  Select *Liferay* from the left menu.

3.  Choose the build type for your workspace (i.e., Gradle or Maven). Then click
    *Next*.

    ![Figure 1: Choose *Liferay Gradle Workspace* or *Liferay Maven Workspace*, depending on the build you prefer.](../../../images/intellij-workspace-build.png)

4.  Specify your workspace's name, location, intended @product@ version, and
    SDK (i.e., Java JDK). Then click *Finish*.

    ![Figure 2: Specify your workspace's configurations.](../../../images/intellij-workspace-project.png)

5.  A window opens for additional build configurations for the build type you
    selected (i.e., Gradle or Maven). Verify the settings and click *OK*.

Awesome! You've successfully created a Liferay Workspace in IntelliJ IDEA!

## Importing a Liferay Workspace [](id=importing-a-liferay-workspace)

To import an existing workspace into IntelliJ, follow the steps below:

1.  Select *File* &rarr; *New* &rarr; *Project from Existing Sources...*.

2.  Select the workspace you want to import. Then click *OK*.

    ![Figure 3: Specify your workspace's configurations.](../../../images/intellij-import-workspace.png)

3.  Click the *Import project from external model* radio button and select the
    build tool your workspace uses (e.g., Gradle or Maven).

4.  Configure the project import (if necessary) and then click *Finish*. See the
    [Import a Project](https://www.jetbrains.com/help/idea/creating-and-managing-projects.html#importing-project)
    section of IntelliJ's official documentation for more information.

5.  Step through the remaining import prompts and then open your imported
    workspace as you desire (i.e., in the current window or a new window).

Excellent! Your existing Liferay Workspace is now imported in IntelliJ IDEA!
