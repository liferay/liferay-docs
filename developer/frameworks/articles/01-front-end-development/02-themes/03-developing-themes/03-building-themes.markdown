---
header-id: building-your-themes-files
---

# Building Your Theme's Files

[TOC levels=1-4]

Follow these steps to build your theme's files with the Build task. Note that 
this task only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/developer/reference/-/knowledge_base/7-2/installing-the-theme-generator-and-creating-a-theme).

1.  Navigate to your theme's root folder and run `gulp build`.

2.  The `gulp build` task generates the base theme files (in the `build` folder), 
    compiles Sass into CSS, and compresses all theme files into a `.war` file 
    (in the `dist` folder), that you can deploy to your server. Copy any of 
    these files and folders to your theme's `src` folder to modify them. 

3.  [Deploy](/developer/frameworks/-/knowledge_base/7-2/deploying-and-applying-your-theme) 
    the `war` file to your app server to make it available.
    
![Figure 1: Run the `gulp build` task to build your theme's files.](../../../../images/theme-dev-building-themes-gulp-build.png)

## Related Topics

- [Automatically Deploying Theme Changes](/developer/frameworks/-/knowledge_base/7-2/automatically-deploying-theme-changes)
- [Copying an Existing Theme's Files](/developer/frameworks/-/knowledge_base/7-2/copying-an-existing-themes-files)
- [Deploying and Applying Themes](/developer/frameworks/-/knowledge_base/7-2/deploying-and-applying-your-theme)
