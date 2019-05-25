---
header-id: copying-an-existing-themes-files
---

# Copying an Existing Theme's Files

[TOC levels=1-4]

If you want to jump start developing your theme, you can copy an 
existing theme's files and build on top of them. The `gulp kickstart` task 
automates this process for you. It copies another theme's css, images, js, and 
templates into the `src` directory of your own. While this is similar to 
extending your theme with a base theme or a themelet, kickstarting from another 
theme is a one time inheritance, whereas extending from another theme is a 
dynamic inheritance that applies your `src` files on top of the base theme on 
every build. 

| **Note:** The gulp kickstart task copies an existing theme's files into your own,
| which can potentially overwrite files with the same name. Proceed with caution.

| **Note:** Gulp is included as a local dependency in generated themes, so you
| are not required to install it. It can be accessed by running
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's
| root folder.

To kickstart your theme, follow these steps:

1.  Navigate to your theme's root folder and run `gulp kickstart`.

    ![Figure 1: Run the `gulp kickstart` task to copy a theme's files into your own theme.](../../../../images/theme-dev-kickstarting-themes-gulp-kickstart.png)

2.  Select where the theme is located. You can copy files from globally 
    installed themes or themes published on the npm registry.

    | **Note:** To globally install a theme, run the `npm link` command from the
    | theme's root folder.

    ![Figure 2: You can copy files from  globally installed themes.](../../../../images/theme-dev-kickstarting-themes-global-theme.png)

3.  The theme's files are copied into your own theme, jump starting 
    development.
    
    ![Figure 3: The kickstart tasks copies another theme's files into your own, potentially  overwriting files.](../../../../images/theme-dev-kickstarting-themes-copied-files.png)
 
## Related Topics

[Building Your Theme's files](/docs/7-1/tutorials/-/knowledge_base/t/building-your-themes-files)

[Creating Reusable Pieces of Code for Your Themes](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)

[Deploying Themes](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme)
