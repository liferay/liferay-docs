# Upgrading Your Theme from Liferay Portal 6.2 to 7.1 [](id=upgrading-6-2-themes-to-7-1)

This section guides you through the process of upgrading your 6.2 theme to run
on @product-ver@. While you're at it, you should leverage theme improvements,
including support for Sass, Bootstrap 4 and Lexicon 2.0. 

Theme upgrades involve these steps:

-  Updating project metadata
-  Updating CSS
-  Updating theme templates
-  Updating resources importer configuration and content
-  Applying Clay design patterns

As an example, these tutorials apply the steps to a theme called the Lunar
Resort theme developed in the Liferay Portal 6.2 
[Developing a Liferay Theme](/develop/tutorials/-/knowledge_base/6-2/developing-a-liferay-theme)
Learning Path. It's similar to many Liferay Portal 6.2 themes, as it extends the
[`_styled`
theme](https://github.com/liferay/liferay-portal/tree/6.2.x/portal-web/docroot/html/themes/_styled),
adds configurable settings, and incorporats a responsive design that leverages
Font Awesome icons and Bootstrap. The theme 
[ZIP file](/documents/10184/656312/lunar-resort-theme-migration-6.2.zip) contains its
original source code. 

![Figure 1: The Lunar Resort example theme upgraded in this tutorial uses a clean, minimal design.](../../../../images/finished-7-1-theme.png)

Before upgrading a theme, consider 
[migrating the theme](/develop/tutorials/-/knowledge_base/7-1/running-the-upgrade-task-for-6-2-themes) 
to use the 
[Liferay JS Toolkit's theme development tools](https://github.com/liferay/liferay-themes-sdk/tree/master/packages). 
@product-ver@ doesn't require this migration, but the Liferay JS Toolkit's Gulp 
`upgrade` task automates many upgrade steps. Themes that use the Liferay JS 
Toolkit's theme development tools can also leverage exclusive features, such as 
[Themelets](/develop/tutorials/-/knowledge_base/7-1/creating-reusable-pieces-of-code-for-your-themes). 

If your theme uses Bootstrap 3 and Lexicon CSS, you can still use Bootstrap
3 and Lexicon CSS alongside Bootstrap 4 and Clay CSS markup, thanks to
@product@'s 
[compatibility layer](/develop/tutorials/-/knowledge_base/7-1/using-the-bootstrap-3-lexicon-css-compatibility-layer). 

Follow the steps in the 
[Running the 6.2 theme upgrade task](/develop/tutorials/-/knowledge_base/7-1/running-the-upgrade-task-for-6-2-themes) 
tutorial to learn how to migrate your theme to use the Liferay JS Toolkit's 
theme development tools and its Gulp `upgrade` task. Otherwise, you must follow 
the steps in the remaining tutorials in this section to upgrade your 6.2 theme 
to 7.1 manually. 
