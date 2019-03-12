---
header-id: creating-layout-templates-with-the-themes-generator
---

# Creating Layout Templates

This tutorial shows how to use the Liferay Theme Generator's Layouts 
sub-generator to create a layout template. 

![Figure 1: The *1-2-1 Columns* page layout creates a nice flow for your content.](../../../images/layout-template-1-2-1-columns.png)

Your first step in creating a layout template with the Liferay Theme Generator's 
Layouts sub-generator is installing the 
[Liferay Theme Generator](/developer/frameworks/-/knowledge_base/7-2/creating-themes) 
if it's not already installed. Once the generator is installed, you can follow 
these steps to create a layout template:

1.  Open the Command Line and navigate to the folder where you want to create
    your layout template.

2.  Run The Layouts sub-generator with the command below, and use the 
    [available options](/develop/reference/-/knowledge_base/7-2/layouts-subgenerator-reference) 
    to create your layout: 
    
        `yo liferay-theme:layout`

    ![Figure 2: The Layouts sub-generator automates the layout creation process.](../../../images/layout-prompt.png)

3.  Run `gulp deploy` to deploy your layout template to the server you 
    specified, or deploy your theme if the layout is 
    [bundled with it](/developer/frameworks/-/knowledge_base/7-2/including-layout-templates-with-a-theme).

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

- [Creating Layout Template Thumbnail Previews](/developer/frameworks/-/knowledge_base/7-2/creating-custom-layout-template-thumbnail-previews)
- [Bundling Layout Templates with a Theme](/developer/frameworks/-/knowledge_base/7-2/including-layout-templates-with-a-theme)
- [Creating Themes](/developer/frameworks/-/knowledge_base/7-2/creating-themes)
