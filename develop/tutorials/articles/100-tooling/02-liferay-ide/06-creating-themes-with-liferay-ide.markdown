---
header-id: creating-themes-with-liferay-ide
---

# Creating Themes with Liferay @ide@

Liferay @ide@ lets you create and configure Liferay theme projects. You can
create a standalone theme or in a
[Liferay Workspace](/docs/7-0/tutorials/-/knowledge_base/t/liferay-workspace).
You can even create a Gradle or Maven based theme! Read on to learn more about
creating themes in @ide@.

1.  In @ide@, navigate to *File* &rarr; *New* &rarr; *Liferay Module Project*.

2.  In the New Liferay Module Project wizard, give your project a name and
    select the *theme* project template. Also choose your theme's build type by
    selecting either *gradle-module* or *maven-module*.

    ![Figure 1: Use the theme project template to create a Liferay theme in @ide@.](../../../images/theme-in-ide.png)

3.  Select *Finish*.

That's it! You've created a theme project in @ide@!

If you've
[configured a Liferay Workspace in your @ide@ instance](/docs/7-0/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-liferay-ide),
your theme is available in the workspace's `wars` folder by default. If you
don't have a workspace configured in @ide@, it's available in the root of
@ide@'s Project Explorer.

Note that themes created in @ide@ follow a WAR-style layout. This is the default
layout of themes in @product-ver@. Although the wizard can be misleading by
calling the theme a new module project, it is a WAR.

To modify a theme created in @ide@, mirror the folder structure of the files 
you wish to change and copy them into your theme's `webapp` folder.

Under the hood, @ide@ is using the
[theme project template](/docs/7-0/reference/-/knowledge_base/r/theme-template).
If you're interested in creating Liferay themes using the
Liferay Theme Generator, see its dedicated
[tutorial](/docs/7-0/tutorials/-/knowledge_base/t/themes-generator). For more
general information on Liferay themes, visit their dedicated tutorial section
[Themes and Layout Templates](/docs/7-0/tutorials/-/knowledge_base/t/themes-and-layout-templates).
