---
header-id: running-the-upgrade-task-for-6-2-themes
---

# Running the Gulp Upgrade Task for 6.2 Themes

[TOC levels=1-4]

You can upgrade Liferay Portal 6.2 theme @product-ver@, regardless of the
development environment (Plugins SDK, Maven, etc.) you used. If you migrate your
theme to use the Liferay JS Theme Toolkit, you can leverage the theme's Gulp 
`upgrade` task. 

Without the Liferay JS Theme Toolkit, you must follow the directions in the 
remaining tutorials in this section to upgrade your theme manually. 

Here's what the Upgrade Task does:

- Updates the theme's Liferay version
- Updates the theme's Bootstrap version
- Updates the theme's Lexicon version
- Suggests specific code updates

Here are the steps for using the Gulp `upgrade` task:

1.  Migrate your 6.2 theme to use the Liferay JS Theme Toolkit. Note that 
    **you must** have the 
    [Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes) 
    installed to migrate your theme to use the Liferay JS Theme Toolkit:

        yo liferay-theme:import

    **You must** provide the absolute path to your 6.2 theme's root folder. The 
    import task does not work for relative paths.

2.  Navigate to your theme's root directory and run the command below to 
    initially upgrade your 6.2 theme to 7.0:

        gulp upgrade -v 6.2

    Here's what the 6.2 to 7.0 upgrade task does:
 
    - Updates the theme's Liferay version
    - Updates the CSS
    - Suggests specific code updates

    The task continues upgrading CSS files, prompting you to update CSS file 
    names. For @product-ver@, Sass files should use the `.scss` extension and 
    file names for Sass partials should start with an underscore (e.g., 
    `_custom.scss`). The `upgrade` task prompts you for each CSS file to rename. 

    | **Note**: The `gulp upgrade` task overwrites the theme's files. We recommend
    | that you backup your theme's files before running it.

    The upgrade task automatically upgrades CSS code that it can identify. For 
    everything else, it suggests upgrades. 

3.  Run the `gulp upgrade` command again to upgrade the 7.0 theme to 7.1.

    Here's what it does:

    - Creates core code for generating theme base files
    - Collects removed Bootstrap and Lexicon variables
    - Updates Bootstrap version references
    - Updates Lexicon version references
    - Updates Liferay version references

The Gulp `upgrade` task lists any deprecated or removed variables. For other 
areas of the code it suspects might need updates, it logs suggestions. The task 
also reports changes that may affect 
[theme templates](/docs/7-1/tutorials/-/knowledge_base/t/updating-6-2-theme-templates). 

The Gulp `upgrade` task jump-starts the upgrade process, but it doesn't complete 
it. Manual updates are required. 

The rest of the tutorials in this section explain all the 6.2 theme upgrade 
steps, regardless of whether the Gulp `upgrade` task performs them. Steps the 
upgrade task performs are noted in context. Even if you've already executed the 
upgrade task, it's best to learn all the steps and make sure they're applied to 
your theme. 

## Related Topics

[Creating Themes](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[Updating 6.2 Project Metadata](/docs/7-1/tutorials/-/knowledge_base/t/updating-6-2-project-metadata)
