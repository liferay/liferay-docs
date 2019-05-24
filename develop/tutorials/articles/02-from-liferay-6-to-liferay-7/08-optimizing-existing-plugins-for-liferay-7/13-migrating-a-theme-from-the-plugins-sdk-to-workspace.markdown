---
header-id: migrating-a-theme-from-the-plugins-sdk-to-workspace
---

# Migrating a Theme from the Plugins SDK to Workspace

After you've adapted your Plugins SDK theme to @product@'s API, you can continue
maintaining it in the Plugins SDK. The Plugins SDK, however, is deprecated as of
@product-ver@. Maintaining plugins in the SDK will become increasingly
difficult.
[Liferay Workspace replaces the Plugins SDK](/docs/7-0/tutorials/-/knowledge_base/t/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#from-the-plugins-sdk-to-liferay-workspace), 
providing a comprehensive Gradle development environment and more. A simple
command migrates Plugins SDK (Ant-based) themes to Workspace (Gradle-based)
themes. From there you can build and deploy them to @product-ver@ as Web
ARchives (WARs). 

## Running the Migration Command

[Blade CLI's](/docs/7-0/tutorials/-/knowledge_base/t/blade-cli) `convert`
command migrates Plugins SDK themes to Workspace themes in Workspace's
`wars` folder. Theme files are re-organized to follow the standard @product-ver@
[theme folder structure](/docs/7-0/reference/-/knowledge_base/r/theme-reference-guide#theme-anatomy).

In a terminal, navigate to the Liferay Workspace root folder. Then pass your
Plugins SDK theme's name to Blade CLI's `convert` command:

    blade convert --themebuilder [THEME_PROJECT_NAME]

Blade CLI extracts the plugin from the Plugins SDK and reorganizes it in a
standard web application project in Workspace's `wars` folder. Blade CLI uses
the [Theme Builder](/docs/7-0/tutorials/-/knowledge_base/t/theme-builder)
plugin to migrate your theme to a workspace. You can also migrate your Plugins
SDK theme to a Liferay Theme Generator theme using Blade CLI. Follow the
[Migrating Themes to the Theme Generator Using Workspace](/docs/7-0/tutorials/-/knowledge_base/t/migrating-a-6-2-theme-to-liferay-7#migrating-themes-to-the-theme-generator-using-workspace)
tutorial for more information.

The image below shows the theme files before and after they're migrated to
Workspace. 

![Figure 1: The `convert` command migrates a Plugins SDK theme project to a Workspace theme project.](../../../images/convert-theme-compare-folder-structure.png)

From your theme's new location, you can
[deploy](/docs/7-0/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#building-modules)
it to @product-ver@ and maintain it using
[Workspace Gradle tasks](/docs/7-0/tutorials/-/knowledge_base/t/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#plugins-sdk-to-workspace-task-map).
Welcome to your theme's new home in Workspace! 

## Related Topics

[Migrating a Theme from the Plugins SDK to the Theme Generator](/docs/7-0/tutorials/-/knowledge_base/t/migrating-a-6-2-theme-to-liferay-7)

[Workspace Development lifecycle](/docs/7-0/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#building-modules)

[Workspace Gradle Tasks](/docs/7-0/tutorials/-/knowledge_base/t/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#plugins-sdk-to-workspace-task-map)
