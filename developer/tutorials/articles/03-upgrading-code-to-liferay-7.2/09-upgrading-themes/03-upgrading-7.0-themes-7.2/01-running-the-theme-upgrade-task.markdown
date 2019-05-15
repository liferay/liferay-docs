---
header-id: upgrading-themes-created-with-the-theme-generator
---

# Upgrading Themes Created With the Liferay Theme Generator

[TOC levels=1-4]

A Liferay Portal 7.0 theme can be upgraded to @product-ver@, regardless of its 
project environment (Liferay Theme Generator, Plugins SDK, Maven, etc.). But a 
theme created with the Liferay Theme Generator can leverage the theme's 
`gulp upgrade` task. If you're developing your theme in an environment other
than the Themes Generator, the `gulp upgrade` task doesn't work for your theme.
Please follow the manual directions in the remaining tutorials in this section
to upgrade your theme manually. 

Here's what the Upgrade Task does:

- Updates the theme's Liferay version
- Updates the theme's Bootstrap version
- Updates the theme's Lexicon version
- Suggests specific code updates

Here are the steps for using the theme Gulp `upgrade` task:

1.  Navigate to your theme's root directory.

2.  Update the path of your @product@ server in your theme's 
    `liferay-theme.json` file to point to your 7.2 @product@ server. You can use 
    the `gulp init` task to update the path to your server. 

3.  Run the command below to update liferay-theme-tasks to the latest version:

        npm update liferay-theme-tasks

4.  Run the command below to upgrade the theme:

        gulp upgrade

    Here's what it does:

    - Copies the existing theme to a folder called `_backup`
    - Creates core code for generating theme base files
    - Collects removed Bootstrap and Lexicon variables
    - Updates Bootstrap version references
    - Updates Lexicon version references
    - Updates Liferay version references

| **Note**: An upgraded theme can be restored to its original state by
| executing `gulp upgrade:revert`.

The Gulp task list any deprecated or removed variables. For other areas of the 
code it suspects might need updates, it logs suggestions. The task also reports 
changes that may affect 
[theme templates](/docs/7-1/tutorials/-/knowledge_base/t/updating-theme-templates). 

The Gulp `upgrade` task jump-starts the upgrade process, but it doesn't complete 
it. Manual updates are required. 

The rest of the tutorials in this section explain all the theme upgrade steps, 
regardless of whether the `gulp upgrade` task performs them. Steps the upgrade 
task performs are noted in context. Even if you've already executed the upgrade 
task, it's best to learn all the steps and make sure they're applied to your 
theme. 

## Related Topics

[Creating Themes](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[Updating Project Metadata](/docs/7-1/tutorials/-/knowledge_base/t/updating-project-metadata)
