---
header-id: running-the-upgrade-task-for-7-0-themes
---

# Running the Upgrade Task for 7.0 Themes

[TOC levels=1-4]

You can upgrade a @product@ 7.0 theme to @product-ver@, regardless of the 
development environment you use. This tutorial uses the Liferay JS Theme 
Toolkit's Gulp `upgrade` task to automate much of the steps. The Gulp `upgrade` 
task must be run twice to bring a @product@ 7.0 theme up to @product-ver@. 

The Liferay Theme Generator is available in a few different versions. To update 
the @product@ 7.0 theme to @product@ 7.1, you must install v8.x.x of the 
`liferay-theme-tasks` dependency. After the theme is updated to 7.1, you must 
then install v9.x.x of the `liferay-theme-tasks` dependency to complete the 
upgrade process. 

Here's what the Upgrade Task does:

- Updates the theme's Liferay version
- Updates the theme's Bootstrap version
- Updates the theme's Lexicon version
- Suggests specific code updates

Follow these steps to take the theme through the upgrade process:

1.  Navigate to the theme's root directory and run the command below to update 
    the theme's `liferay-theme-tasks` dependency to version `8.x.x`:
    
    ```bash
    npm install --save-dev liferay-theme-tasks@8.x.x
    ```

2.  Run the `gulp upgrade` command to upgrade the @product@ 7.0 theme to 7.1.

    | **Note**: The Upgrade task overwrites the theme's files. We recommend that 
    | you backup your files before proceeding with the upgrade process.

    Here's what it does:

    - Creates core code for generating theme base files
    - Collects removed Bootstrap and Lexicon variables
    - Updates Bootstrap version references
    - Updates Lexicon version references
    - Updates Liferay version references

3.  You must update the theme's `liferay-theme-tasks` dependency to version 
    `9.x.x` to complete the upgrade process. Install the latest version of the 
    Liferay Theme Generator as well while you're at it, so future uses of the 
    tool will be compatible with the @product-ver@ theme. Both commands are 
    shown below. Run them separately:
    
    ```bash
    npm install --save-dev liferay-theme-tasks@9.x.x

    npm install -g generator-liferay-theme@9.x.x
    ```

4.  With the `9.x.x` versions of the `liferay-theme-tasks` and Liferay Theme 
    Generator installed, run the `gulp upgrade` command for the final time to 
    upgrade the 7.1 theme to 7.2:

    Here's what it does:

    - Updates Liferay version references
    - Updates theme dependencies

5.  Run `gulp init` from your theme's root directory to update the path of your 
    @product@ server to point to your 7.2 @product@ server. 

The Gulp `upgrade` task lists any deprecated or removed variables. For other 
areas of the code it suspects might need updates, it logs suggestions. The task 
also reports changes that may affect theme templates. This jump-starts the 
upgrade process, but it doesn't complete it. Manual updates are required. The 
remaining portion of this tutorial covers these manual steps. 
