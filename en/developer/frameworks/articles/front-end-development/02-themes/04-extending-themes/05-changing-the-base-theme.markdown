---
header-id: changing-your-base-theme
---

# Changing Your Base Theme

[TOC levels=1-4]

Follow these steps to change your theme's base theme with the Extend task. Note 
that this task only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme). 

| **Note:** Gulp is included as a local dependency in generated themes, so you 
| are not required to install it. It can be accessed by running 
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's 
| root folder.

1.  Navigate to your theme's root folder and run `gulp extend`. 

    ![Figure 1: Run the `gulp extend` task to change your base theme.](../../../../images/theme-ext-changing-base-themes-gulp-extend-base-theme.png)
    
2.  Enter 1 to select a new base theme to extend. 

3.  By default, themes created with the 
    [Liferay Theme Generator](https://github.com/liferay/generator-liferay-theme) 
    are based off of the 
    [styled theme](https://www.npmjs.com/package/liferay-theme-styled). You can 
    extend the styled or unstyled base theme, a globally installed theme, a 
    theme published on the npm registry, or you can specify a package URL. 
    Enter the number for the option you wish to select. 

    | **Note:** You can retrieve the URL for a package by running 
    | `npm show package-name dist.tarball`. 

    ![Figure 2: You can extend the styled or unstyled base theme, a globally installed theme, a theme published to the npm registry, or you can specify a package URL.](../../../../images/theme-ext-changing-base-themes-gulp-extend-base-theme-choice.png)

    | **Note:** The Classic theme is an implementation of an existing base theme 
    | and is therefore not meant to be extended. Extending Liferay's Classic 
    | theme is strongly discouraged.

Your theme's `package.json` contains the updated base theme configuration:

```json
"liferayTheme": {
  "baseTheme": "styled",
  "screenshot": "",
  "templateLanguage": "ftl",
  "version": "7.2"
},
```

Great! You've updated your base theme. When you 
[build your theme's files](/docs/7-2/frameworks/-/knowledge_base/f/building-your-themes-files) 
or 
[deploy it](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes), 
your theme will inherit the updated base theme's files. 

## Related Topics

- [Configuring Your Theme's App Server](/docs/7-2/frameworks/-/knowledge_base/f/updating-your-themes-app-server)
- [Deploying and Applying Themes](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes)
- [Listing Your Theme's Extensions](/docs/7-2/frameworks/-/knowledge_base/f/listing-your-themes-extensions)
