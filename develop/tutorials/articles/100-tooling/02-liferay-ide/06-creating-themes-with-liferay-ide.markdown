# Creating Themes with Liferay Dev Studio [](id=creating-themes-with-liferay-ide)

Liferay Dev Studio lets you create and configure Liferay theme projects. You can
create a theme standalone or in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).
You can even create a Gradle or Maven based theme! Read on to learn more about
creating themes in Dev Studio.

1.  In Dev Studio, navigate to *File* &rarr; *New* &rarr; *Liferay Module
    Project*.

2.  In the New Liferay Module Project wizard, give your project a name and
    select the *theme* project template. Also choose your theme's build type by
    selecting either *Gradle* or *Maven*.

    ![Figure 1: Use the theme project template to create a Liferay theme in Dev Studio.](../../../images/theme-in-ide.png)

3.  Select *Finish*.

That's it! You've created a theme project in Dev Studio!

If you've
[configured a Liferay Workspace in your Dev Studio instance](/develop/tutorials/-/knowledge_base/7-1/creating-a-liferay-workspace-with-liferay-ide),
your theme is available in the workspace's `wars` folder by default. If you
don't have a workspace configured in Dev Studio, it's available in the root of
Dev Studio's Project Explorer.

Note that themes created in Dev Studio follow a WAR-style layout. This is the default
layout of themes in @product-ver@. Although the wizard can be misleading by
calling the theme a new module project, it is a WAR.

To modify a theme created in Dev Studio, mirror the folder structure of the
files  you wish to change and copy them into your theme's `webapp` folder.

Under the hood, Dev Studio is using the
[theme project template](/develop/reference/-/knowledge_base/7-1/theme-template).
If you're interested in creating Liferay themes using the
Liferay Theme Generator, see its dedicated
[tutorial](/develop/tutorials/-/knowledge_base/7-1/themes-generator). For more
general information on Liferay themes, visit their dedicated tutorial section
[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-1/themes-and-layout-templates).
