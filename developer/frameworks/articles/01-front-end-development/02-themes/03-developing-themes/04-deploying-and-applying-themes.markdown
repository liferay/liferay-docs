---
header-id: deploying-and-applying-themes
---

# Deploying and Applying Themes

[TOC levels=1-4]

Follow these steps to deploy your theme with the Deploy task. Note that this 
task only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme).


Follow these steps to deploy your theme:

1.  Navigate to your theme's root folder and run `gulp deploy`.

    | **Note:** If you're running the 
    | [Felix Gogo shell](/docs/7-2/reference/-/knowledge_base/r/using-the-felix-gogo-shell), 
    | you can also deploy your theme using the `gulp deploy:gogo` command.

2.  Your server's log displays that the OSGi bundle is started.

    ![Figure 1: Your server's log notifies you when the theme's bundle has started.](../../../../images/theme-dev-deploying-themes-server-log.png)

3.  Apply your theme through the *Build* &rarr; *Pages* menu in the Control 
    Menu. Select the *Configure* option for your site pages, and click the 
    *Change Current Theme* button to apply your theme. 

Wonderful! Your theme is deployed to your server and applied to your site. 

## Related Topics

- [Automatically Deploying Theme Changes](/docs/7-2/frameworks/-/knowledge_base/f/automatically-deploying-theme-changes)
- [Copying an Existing Theme's Files](/docs/7-2/frameworks/-/knowledge_base/f/copying-an-existing-themes-files)
- [Creating Reusable Pieces of Code for Your Themes](/docs/7-2/frameworks/-/knowledge_base/f/creating-reusable-pieces-of-code-for-your-themes)


