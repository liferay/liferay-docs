---
header-id: upgrading-7-1-themes-to-7-2
---

# Upgrading 7.1 Themes to 7.2

[TOC levels=1-4]

You can upgrade a Liferay Portal 7.1 theme to @product-ver@, regardless of the
development environment you use. This tutorial uses the Liferay JS Theme 
Toolkit's Gulp `upgrade` task to automate much of the steps. This requires 
v9.x.x of the Liferay Theme Generator and liferay theme tasks. 

Here's what the Upgrade Task does:

- Updates Liferay version references
- Updates theme dependencies

Follow these steps to upgrade the theme:

1.  Install the Liferay Theme Generator v9.x.x with the command below:

    ```bash
    npm install -g generator-liferay-theme@9.x.x
    ```

2.  You must update the theme's `liferay-theme-tasks` dependency to version 
    `9.x.x` as well to run the upgrade process:
    
    ```bash
    npm install --save-dev liferay-theme-tasks@9.x.x
    ```

3.  With the `9.x.x` versions of the `liferay-theme-tasks` and Liferay Theme 
    Generator installed, run the `gulp upgrade` command to upgrade the 7.1 theme 
    to 7.2.

    | **Note**: The Upgrade task overwrites the theme's files. We recommend that 
    | you backup your files before proceeding with the upgrade process.

4.  Run `gulp init` from the theme's root directory to update the path of the 
    app server to point to the new 7.2 app server. 

There you have it! The theme is ready to run on @product-ver@. 
