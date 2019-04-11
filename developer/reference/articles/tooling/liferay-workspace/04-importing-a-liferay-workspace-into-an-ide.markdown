---
header-id: importing-a-liferay-workspace-into-an-ide
---

# Importing a Liferay Workspace into an IDE

[TOC levels=1-4]

Liferay supports two IDEs with preconfigured Liferay Workspace wizards and
functionalities

- [Dev Studio](#dev-studio)
- [IntelliJ](#intellij)

These aren't the only IDEs you can leverage to use Liferay Workspace, but they
are the ones with out-of-the-box support for it.

Visit the appropriate section to learn how to import a workspace with the
highlighted tool.

## Dev Studio

1.  Navigate to *File* &rarr; *Import* &rarr; *Liferay* &rarr; *Liferay
    Workspace Project*.

2.  Click *Next* and browse for your workspace project.

    ![Figure 1: You can import an existing Liferay Workspace into your current Dev Studio session.](../../../images/liferay-workspace-import.png)

3.  Once you've selected you workspace, click *Finish*.

## IntelliJ

1.  Select *File* &rarr; *New* &rarr; *Project from Existing Sources...*.

2.  Select the workspace you want to import. Then click *OK*.

    ![Figure 2: Specify your workspace's configurations.](../../../images/intellij-import-workspace.png)

3.  Click the *Import project from external model* radio button and select the
    build tool your workspace uses (e.g., Gradle or Maven).

4.  Configure the project import (if necessary) and then click *Finish*. See the
    [Import a Project](https://www.jetbrains.com/help/idea/creating-and-managing-projects.html#importing-project)
    section of IntelliJ's official documentation for more information.

5.  Step through the remaining import prompts and then open your imported
    workspace as you desire (i.e., in the current window or a new window).
