---
header-id: copying-an-existing-themes-files
---

# Copying an Existing Theme's Files

[TOC levels=1-4]

Follow these steps to copy an existing theme's files with the Kickstart task. 
Unlike extending a base theme, which is a dynamic inheritance that applies your 
`src` files on top of the base theme on every build, the Kickstart task is a one 
time inheritance. Note that this task only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/developer/reference/-/knowledge_base/7-2/installing-the-theme-generator-and-creating-a-theme).

| **Note:** The gulp kickstart task copies an existing theme's files into your 
| own. This can potentially overwrite files with the same name. Proceed with 
| caution. 

1.  Navigate to your theme's root folder and run `gulp kickstart`.

    ![Figure 1: Run the `gulp kickstart` task to copy a theme's files into your own theme.](../../../../images/theme-ext-kickstarting-themes-gulp-kickstart.png)

2.  Select where to search for the theme to copy. You can copy files from 
    globally installed themes or themes published on the npm registry.
    
    | **Note:** To globally install a theme, run the `npm link` command from the 
    | theme's root folder. 

    ![Figure 2: You can copy files from  globally installed themes.](../../../../images/theme-ext-kickstarting-themes-global-theme.png)

3.  The theme's files are copied into your own theme, jump starting development. 
    Add your changes on top of these files.
    
Congrats! Now you have a head start to developing your theme. 
 
## Related Topics

- [Building Your Theme's files](/developer/frameworks/-/knowledge_base/7-2/building-your-themes-files)
- [Generating Themelets](/developer/reference/-/knowledge_base/7-2/creating-themelets-with-the-themes-generator)
- [Deploying and Applying Themes](/developer/frameworks/-/knowledge_base/7-2/deploying-and-applying-themes)
