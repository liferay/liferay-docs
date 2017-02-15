# Creating Themes with Liferay @ide@ [](id=creating-themes-with-liferay-ide)

Liferay @ide@ provides a wizard letting you create and configure a Liferay theme
project. You can create a theme standalone or in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace).
You even have the option to create a Gradle or Maven based theme! Read
on to learn more about creating themes in @ide@.

1.  In @ide@, navigate to *File* &rarr; *New* &rarr; *Liferay Module Project*.

2.  In the New Liferay Module Project wizard, give your project a name and
    select the *theme* project template. Also select your theme's build type by
    selecting either *gradle-module* or *maven-module*.

    ![Figure 1: Use the theme project template to create a Liferay theme in @ide@.](../../../images/theme-in-ide.png)

3.  Select *Finish*.

That's it! You've created a theme project in @ide@!

If you've
[configured a Liferay Workspace in your @ide@ instance](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-liferay-ide),
your theme is available in the workspace's `wars` folder, by default. If you
don't have a workspace configured in @ide@, it's available in the root of
@ide@'s Project Explorer.

Note that themes created in @ide@ follow a WAR-style layout. This is the default
layout of themes in @product-ver@. Although the wizard can be misleading by
calling the theme a new module project, it is a WAR.

Under the hood, @ide@ is using
[Blade CLI's theme project template](/develop/tutorials/-/knowledge_base/7-0/theme-template).
If you're interested in creating Liferay themes using the
Themes Generator, see its dedicated
[tutorial](/develop/tutorials/-/knowledge_base/7-0/themes-generator).
