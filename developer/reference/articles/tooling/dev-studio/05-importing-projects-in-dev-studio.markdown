---
header-id: importing-projects-in-dev-studio
---

# Importing Projects in Dev Studio

[TOC levels=1-4]

There are two types of Liferay projects you can import into Dev Studio:

- Liferay Module Project (this also includes WAR-style projects)
- Liferay Workspace Project

You cannot import Liferay projects individually that reside in a
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/reference/liferay-workspace).
You can either import a non-workspace Liferay project (or group of projects if
the parent folder is specified) or an entire workspace project with all its
Liferay projects.

To import a pre-existing Liferay project into Dev Studio, follow the steps
outlined below:

1.  Right-click in the Project Explorer and select *Import* &rarr; *Liferay
    Module Project*. If you're interested in importing an entire Liferay
    Workspace, select the *Liferay Workspace Project* instead.

    ![Figure 1: You can import a single project or folder of projects.](../../../images/import-liferay-project.png)

    Once you've selected your project(s), the project build type is displayed.

2.  Click *Finish*.

Now your Liferay project is available from the Package Explorer.
