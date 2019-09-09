---
header-id: changing-your-base-theme
---

# Changing Your Base Theme

[TOC levels=1-4]

Once your theme is 
[built](/docs/7-1/tutorials/-/knowledge_base/t/building-your-themes-files), 
you can use the `gulp extend` task to change your theme's base theme. 

| **Note:** Gulp is included as a local dependency in generated themes, so you
| are not required to install it. It can be accessed by running
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's
| root folder.

Follow these steps to change your base theme:

1.  Navigate to your theme's root folder and run `gulp extend` and choose option 
    1 to change the base theme your theme extends. 

    ![Figure 1: Run the `gulp extend` task to change your base theme or install a themelet.](../../../../images/theme-dev-changing-base-themes-gulp-extend-base-theme.png)
    
2.  Choose which base theme you want to extend. By default, themes created with 
    the 
    [Liferay Theme Generator](https://github.com/liferay/generator-liferay-theme) 
    are based off of the 
    [styled theme](https://www.npmjs.com/package/liferay-theme-styled). You can 
    extend the styled or unstyled base theme, a globally installed theme, a 
    theme published on the npm registry, or you can specify a package URL. 
    Enter the number for the option you wish to select. 

    | **Note:** You can retrieve the URL for a package by running 
    | `npm show package-name dist.tarball`. 

    ![Figure 2: You can extend the styled or unstyled base theme, a globally installed theme, or a theme published to the npm registry.](../../../../images/theme-dev-changing-base-themes-gulp-extend-base-theme-choice.png)

    | **Note:** The Classic theme is an implementation of an existing base theme
    | and is therefore not meant to be extended. Extending Liferay's Classic theme
    | is strongly discouraged.

3.  Your theme's `package.json` contains the updated base theme configuration :

        {
          ...
        	"liferayTheme": {
        		"baseTheme": "styled",
        		"screenshot": "",
        		"rubySass": false,
        		"templateLanguage": "ftl",
        		"version": "7.1"
        	},
          ...
        }
        
When you 
[build your theme's files](/docs/7-1/tutorials/-/knowledge_base/t/building-your-themes-files) 
or 
[deploy it](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme), 
your theme will inherit the updated base theme's files.

## Related Topics

[Configuring Your Theme's App Server](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server)

[Deploying Themes](/docs/7-1/tutorials/-/knowledge_base/t/deploying-your-theme)

[Listing Your Theme's Extensions](/docs/7-1/tutorials/-/knowledge_base/t/listing-your-themes-extensions)
