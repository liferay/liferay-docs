# Upgrading Themes Created With the Liferay Theme Generator [](id=upgrading-themes-created-with-the-theme-generator)

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

1.  Open your theme's `package.json` file and update the `liferay-theme-deps` 
    and `liferay-theme-tasks` dependencies to `8.0.0-alpha.6`:
    
        "devDependencies": {
        	"gulp": "^3.8.10",
        	"liferay-theme-tasks": "8.0.0-alpha.6",
        	"liferay-theme-deps-7.0": "8.0.0-alpha.6"
        },

2.  In your theme's root directory, install the updated dependencies:

        npm install

3.  Run the command below to upgrade the theme:

        gulp upgrade

    Here's what it does:

    - Copies the existing theme to a folder called `_backup`
    - Creates core code for generating theme base files
    - Collects removed Bootstrap and Lexicon variables
    - Updates Bootstrap version references
    - Updates Lexicon version references
    - Updates Liferay version references

+$$$

**Note**: An upgraded theme can be restored to its original state by 
executing `gulp upgrade:revert`. 

$$$

The Gulp task list any deprecated or removed variables. For other areas of the 
code it suspects might need updates, it logs suggestions. The task also reports 
changes that may affect 
[theme templates](/develop/tutorials/-/knowledge_base/7-1/updating-theme-templates). 

The Gulp `upgrade` task jump-starts the upgrade process, but it doesn't complete 
it. Manual updates are required. 

The rest of the tutorials in this section explain all the theme upgrade steps, 
regardless of whether the `gulp upgrade` task performs them. Steps the upgrade 
task performs are noted in context. Even if you've already executed the upgrade 
task, it's best to learn all the steps and make sure they're applied to your 
theme. 

## Related Topics [](id=related-topics)

[Creating Themes](/develop/tutorials/-/knowledge_base/7-1/creating-themes)

[Updating Project Metadata](/develop/tutorials/-/knowledge_base/7-1/updating-project-metadata)
