---
header-id: upgrading-6-2-themes-to-7-2
---

# Upgrading Your Theme from Liferay Portal 6.2 to 7.2

[TOC levels=1-4]

In this tutorial, you'll upgrade the Lunar Resort theme developed in the 
Liferay Portal 6.2 [Developing a Liferay Theme](/docs/6-2/tutorials/-/knowledge_base/t/developing-a-liferay-theme) 
Learning Path to @product-ver@ using the Liferay Theme Generator and Liferay 
Theme Tasks. The Lunar Resort theme is similar to many Liferay Portal 6.2 
themes, as it extends the [`_styled` theme](https://github.com/liferay/liferay-portal/tree/6.2.x/portal-web/docroot/html/themes/_styled), 
adds configurable settings, and incorporates a responsive design that leverages 
Font Awesome icons and Bootstrap. The theme [ZIP file](/documents/10184/656312/lunar-resort-theme-migration-6.2.zip) 
contains its original source code. 

![Figure 1: The Lunar Resort example theme upgraded in this tutorial uses a clean, minimal design.](../../../../images/finished-7-2-theme.png) 

As you upgrade this theme, you'll learn how to update metadata, theme templates, 
UI, and more using all the best practices and standards. Completing this 
tutorial prepares you for upgrading your own theme. 

To upgrade a theme from @product@ 6.2 to @product-ver@, start at the beginning: 
migrating the theme to use the [Liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages). 
The Liferay JS Theme Toolkit's Gulp `upgrade` task automates many upgrade steps. 
Though you can use anything from a text editor and the command line to your Java 
IDE of choice, Liferay Theme Generator and @ide@ optimizes development on 
Liferay's platform. See 
[Installing the Liferay Theme Generator and Creating a Theme](#installing-the-theme-generator-and-creating-a-theme) 
to set up the required tooling. 

Once you set up your development environment, you can upgrade the theme. 
From updating theme templates to updating CSS, you'll learn everything you need 
to know to update your theme. 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-2/development-setup-overview">Let's Go!<span class="icon-circle-arrow-right"></span></a>





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
[Developing a Liferay Theme](/docs/6-2/tutorials/-/knowledge_base/t/developing-a-liferay-theme)
Learning Path. It's similar to many Liferay Portal 6.2 themes, as it extends the
[`_styled`
theme](https://github.com/liferay/liferay-portal/tree/6.2.x/portal-web/docroot/html/themes/_styled),
adds configurable settings, and incorporates a responsive design that leverages 
Font Awesome icons and Bootstrap. The theme 
[ZIP file](/documents/10184/656312/lunar-resort-theme-migration-6.2.zip) contains its
original source code. 

![Figure 1: The Lunar Resort example theme upgraded in this tutorial uses a clean, minimal design.](../../../../images/finished-7-2-theme.png)

Before upgrading a theme, consider 
[migrating the theme](/docs/7-2/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) 
to use the 
[Liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages). 
@product-ver@ doesn't require this migration, but the Liferay JS Theme Toolkit's 
Gulp `upgrade` task automates many upgrade steps. Themes that use the Liferay JS 
Theme Toolkit can also leverage exclusive features, such as 
[Themelets](/docs/7-2/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes). 

If your theme uses Bootstrap 3 and Lexicon CSS, you can still use Bootstrap
3 and Lexicon CSS alongside Bootstrap 4 and Clay CSS markup, thanks to
@product@'s 
[compatibility layer](/docs/7-2/tutorials/-/knowledge_base/t/using-the-bootstrap-3-lexicon-css-compatibility-layer). 

Follow the steps in the 
[Running the 6.2 theme upgrade task](/docs/7-2/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) 
tutorial to learn how to migrate your theme to use the Liferay JS Theme Toolkit, 
including its Gulp `upgrade` task. Otherwise, you must follow the steps in the 
remaining tutorials in this section to upgrade your 6.2 theme to 7.2 manually. 
