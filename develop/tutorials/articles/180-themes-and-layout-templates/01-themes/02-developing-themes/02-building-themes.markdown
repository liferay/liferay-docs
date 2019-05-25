---
header-id: building-your-themes-files
---

# Building Your Theme's Files

[TOC levels=1-4]

The `gulp build` task generates the base theme files, compiles Sass into CSS, 
and zips all theme files into a WAR file that you can deploy to your server. 

| **Note:** Gulp is included as a local dependency in generated themes, so you
| are not required to install it. It can be accessed by running
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's
| root folder.

Follow these steps to build your theme's files:

1.  Navigate to your theme's root folder and run `gulp build`.

    ![Figure 1: Run the `gulp build` task to build your theme's files.](../../../../images/theme-dev-building-themes-gulp-build.png)

2.  A new `build` folder is created with all your theme's files. You can copy 
    these files and folders to your theme's `src` folder to modify the theme. 

    ![Figure 2: The build folder contains all your theme's files.](../../../../images/theme-dev-building-themes-build-folder.png)

3.  Your theme's files are zipped into a `war` file in a new `dist` folder. 
    Deploy the `war` file to your app server to make it available.
    
![Figure 3: The dist folder contains your theme's WAR file.](../../../../images/theme-dev-building-themes-dist-folder.png)

## Related Topics

[Automatically Deploying Theme Changes](/docs/7-1/tutorials/-/knowledge_base/t/automatically-deploying-theme-changes)

[Copying an Existing Theme's Files](/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files)

[Deploying Themes](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme)
