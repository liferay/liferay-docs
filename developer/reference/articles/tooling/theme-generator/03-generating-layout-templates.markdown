---
header-id: creating-layout-templates-with-the-themes-generator
---

# Generating Layout Templates with the Theme Generator

[TOC levels=1-4]

This article shows how to use the Liferay Theme Generator's Layouts 
sub-generator to create a layout template. 

![Figure 1: The *1-2-1 Columns* page layout creates a nice flow for your content.](../../../images/layout-template-1-2-1-columns.png)

Your first step in creating a layout template with the Liferay Theme Generator's 
Layouts sub-generator is installing the 
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/reference/installing-the-theme-generator-and-creating-a-theme) 
if it's not already installed. Once the generator is installed, you can follow 
these steps to create a layout template:

1.  Open the Command Line and navigate to the folder where you want to create
    your layout template.

    | **Note:** Run the Layouts sub-generator from the theme's root folder to
    | bundle it with the theme. This adds the layout template to the theme's
    | `src/layouttpl/custom` folder. This **only works** for generated themes.

2.  Run The Layouts sub-generator with the command below, and use the 
    [available options](/docs/7-2/reference/-/knowledge_base/reference/layouts-sub-generator-options) 
    to create your layout: 
    
        `yo liferay-theme:layout`

    ![Figure 2: The Layouts sub-generator automates the layout creation process.](../../../images/layout-prompt.png)

3.  Run `gulp deploy` to deploy your layout template to the server you 
    specified, or deploy your theme if the layout is 
    [bundled with it](/docs/7-2/frameworks/-/knowledge_base/frameworks/including-layout-templates-with-a-theme).

Awesome! You just created a layout template with the Theme Generator's Layouts 
sub-generator. Your layout template project should have a file structure similar 
to the one below:

- `my-liferay-layout-layouttpl/`
    - `docroot/`
        - `WEB-INF/`
            - `liferay-layout-templates.xml`
            - `liferay-plugin-package.properties`
        - `my_liferay_layout.png`
        - `my_liferay_layout.tpl`
    - `node_modules/`
        - (lots of packages)
    - `gulpfile.js`
    - `liferay-plugin.json`
    - `package-lock.json`
    - `package.json`

## Related Topics

- [Creating Layout Template Thumbnail Previews](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-custom-layout-template-thumbnail-previews)
- [Bundling Layout Templates with a Theme](/docs/7-2/frameworks/-/knowledge_base/frameworks/including-layout-templates-with-a-theme)
- [Installing the Liferay Theme Generator and Creating a Theme](/docs/7-2/reference/-/knowledge_base/reference/installing-the-theme-generator-and-creating-a-theme)
