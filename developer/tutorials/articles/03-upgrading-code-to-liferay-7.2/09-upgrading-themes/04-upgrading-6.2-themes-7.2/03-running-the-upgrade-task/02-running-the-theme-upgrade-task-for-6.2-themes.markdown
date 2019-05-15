---
header-id: running-the-upgrade-task-for-6-2-themes
---

# Running the Upgrade Task for 6.2 Themes

<div class="learn-path-step">
    <p>Running the Upgrade Task for 6.2 Themes<br>Step 1 of 1</p>
</div>

You can upgrade a Liferay Portal 6.2 theme to @product-ver@, regardless of the
development environment you use. If you migrate your theme to use the Liferay JS 
Theme Toolkit, you can leverage the theme's Gulp `upgrade` task to automate much 
of the steps. 

Without the Liferay JS Theme Toolkit, you must follow the directions in the 
remaining tutorials in this section to upgrade your theme manually. 

Here's what the Upgrade Task does:

- Updates the theme's Liferay version
- Updates the theme's Bootstrap version
- Updates the theme's Lexicon version
- Updates CSS file names 
- Suggests specific code updates

Here are the steps for using the Gulp `upgrade` task:

1.  Navigate to your theme's root directory and run the command below to 
    initially upgrade your 6.2 theme to 7.0:

```bash
gulp upgrade
```

    Here's what the 6.2 to 7.0 upgrade task does:
 
    - Updates the theme's Liferay version
    - Updates the CSS (prefixing file names with underscores(`_`))
    - Suggests specific code updates

    The task continues upgrading CSS files, prompting you to update CSS file 
    names. For @product-ver@, Sass files should use the `.scss` extension and 
    file names for Sass partials should start with an underscore (e.g., 
    `_custom.scss`). The `upgrade` task prompts you for each CSS file to rename. 

    | **Note**: An upgraded theme can be restored to its original state by
    | executing `gulp upgrade:revert`.

    The upgrade task automatically upgrades CSS code that it can identify. For 
    everything else, it suggests upgrades. 

3.  Run the `gulp upgrade` command again to upgrade the 7.0 theme to 7.2.

    Here's what it does:

    - Creates core code for generating theme base files
    - Collects removed Bootstrap and Lexicon variables
    - Updates Bootstrap version references
    - Updates Lexicon version references
    - Updates Liferay version references

The Gulp `upgrade` task lists any deprecated or removed variables. For other 
areas of the code it suspects might need updates, it logs suggestions. The task 
also reports changes that may affect theme templates. 

The Gulp `upgrade` task jump-starts the upgrade process, but it doesn't complete 
it. Manual updates are required. The remaining portion of this tutorial covers 
these manual steps. 

## Related Topics

[Creating Themes](/docs/7-2/tutorials/-/knowledge_base/t/creating-themes)

[Updating 6.2 Project Metadata](/docs/7-2/tutorials/-/knowledge_base/t/updating-6-2-project-metadata)
