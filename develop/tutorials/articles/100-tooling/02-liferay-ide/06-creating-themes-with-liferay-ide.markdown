---
header-id: creating-themes-with-liferay-ide
---

# Creating Themes with Liferay Dev Studio

[TOC levels=1-4]

Liferay Dev Studio lets you create and configure Liferay theme projects. You can
create a theme standalone or in a
[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace).
You can even create a Gradle or Maven based theme! Read on to learn more about
creating themes in Dev Studio.

1.  In Dev Studio, navigate to *File* &rarr; *New* &rarr; *Liferay Module
    Project*.

2.  In the New Liferay Module Project wizard, give your project a name and
    select the *theme* project template. Also choose your theme's build type by
    selecting either *Gradle* or *Maven*.

    ![Figure 1: Use the theme project template to create a Liferay theme in Dev Studio.](../../../images/theme-in-ide.png)

3.  Select *Finish*.

That's it! You've created a theme project in Dev Studio! Learn how to deploy it
in
[this tutorial](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-liferay-ide).

If you've
[configured a Liferay Workspace in your Dev Studio instance](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-liferay-ide),
your theme is available in the workspace's `wars` folder by default. If you
don't have a workspace configured in Dev Studio, it's available in the root of
Dev Studio's Project Explorer.

Note that themes created in Dev Studio follow a WAR-style layout. Although the
wizard can be misleading by calling the theme a new module project, it is a WAR.

To modify a theme created in Dev Studio, mirror the folder structure of the
files you wish to change and copy them into your theme's `webapp` folder.

**Important:** Under the hood, Dev Studio is using the
[theme project template](/docs/7-1/reference/-/knowledge_base/r/theme-template).
The WAR-style theme created by Dev Studio is Gradle/Maven based; this differs
from themes created with the 
[Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes) 
that use the 
[Liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages). 
Do not mix these two development strategies. See the
[Managing Themes in Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/managing-themes-in-liferay-workspace)
tutorial for more information on how these two strategies are used in Workspace
and Dev Studio.

If you're interested in creating Liferay themes using the
Liferay Theme Generator, see its dedicated
[tutorial](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes). For more
general information on Liferay themes, visit their dedicated tutorial section
[Themes and Layout Templates](/docs/7-1/tutorials/-/knowledge_base/t/themes-and-layout-templates).
