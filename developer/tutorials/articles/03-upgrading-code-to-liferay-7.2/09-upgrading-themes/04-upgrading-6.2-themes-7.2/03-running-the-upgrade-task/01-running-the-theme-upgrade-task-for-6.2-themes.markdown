---
header-id: running-the-upgrade-task-for-6-2-themes
---

# Running the Upgrade Task for 6.2 Themes

<div class="learn-path-step">
    <p>Running the Upgrade Task for 6.2 Themes<br>Step 1 of 1</p>
</div>

You can upgrade a Liferay Portal 6.2 theme to @product-ver@, regardless of the
development environment you use. This tutorial uses the the Liferay JS Theme 
Toolkit's Gulp `upgrade` task to automate much of the steps. Because the theme 
was built on @product@ 6.2, the Gulp `upgrade` task must be run three times to 
bring it up to @product-ver@. 

The Liferay Theme Generator is available in a few different versions. To update 
the @product@ 6.2 theme to @product@ 7.0, you must have v8.x.x of the Liferay 
Theme Generator installed---this was installed when you 
[set up your development environment](/docs/7-2/tutorials/-/knowledge_base/t/setting-up-the-development-environment-for-6-2-themes). 
After the theme is updated to 7.1, you must then install v9.x.x of the Liferay 
Theme Generator and liferay theme tasks to complete the upgrade tasks. 

Here's what the Upgrade Task does:

- Updates the theme's Liferay version
- Updates the theme's Bootstrap version
- Updates the theme's Lexicon version
- Updates CSS file names
- Updates theme dependencies
- Suggests specific code updates

Follow these steps to take the theme through the upgrade process:

1.  Navigate to the theme's root directory and run the command below to 
    initially upgrade it from 6.2 to 7.0.
    
    | **Note**: The Upgrade task overwrites the theme's files. We recommend that 
    | you backup your files before proceeding with the upgrade process.

```bash
gulp upgrade
```

    Here's what the 6.2 to 7.0 upgrade task does:
 
    - Updates the theme's Liferay version
    - Renames CSS files
    - Suggests specific code updates

    The task continues upgrading CSS files, prompting you to update CSS file 
    names. For @product-ver@, Sass files should use the `.scss` extension, and 
    file names for Sass partials should start with an underscore (e.g., 
    `_custom.scss`). The `upgrade` task prompts you for each CSS file to rename. 

    The upgrade task automatically upgrades CSS code that it can identify. For 
    everything else, it suggests upgrades. 

2.  Run the `gulp upgrade` command again to upgrade the 7.0 theme to 7.1.

    Here's what it does:

    - Creates core code for generating theme base files
    - Collects removed Bootstrap and Lexicon variables
    - Updates Bootstrap version references
    - Updates Lexicon version references
    - Updates Liferay version references

3.  You must update the theme's `liferay-theme-tasks` dependency to version 
    `9.x.x` to complete the upgrade process. To ensure full compatibility, 
    install the latest version of the Liferay Theme Generator as well. Both 
    commands are shown below. Run them separately:
    
```bash
npm install -g generator-liferay-theme@9.x.x

npm install --save-dev liferay-theme-tasks@9.x.x
```

4.  With the `9.x.x` versions of the `liferay-theme-tasks` and Liferay Theme 
    Generator installed, run the `gulp upgrade` command for the final time to 
    upgrade the 7.1 theme to 7.2:

    Here's what it does:

    - Updates Liferay version references
    - Updates theme dependencies

The Gulp `upgrade` task lists any deprecated or removed variables. For other 
areas of the code it suspects might need updates, it logs suggestions. The task 
also reports changes that may affect theme templates. 

The Gulp `upgrade` task jump-starts the upgrade process, but it doesn't complete 
it. Manual updates are required. The remaining portion of this tutorial covers 
these manual steps. 
