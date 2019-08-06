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
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme) 
if it's not already installed. Once the generator is installed, you can follow 
these steps to create a layout template:

1.  Open the Command Line and navigate to the folder where you want to create
    your layout template.

    | **Note:** Run the Layouts sub-generator from the theme's root folder to
    | bundle it with the theme. This adds the layout template to the theme's
    | `src/layouttpl/custom` folder. This **only works** for generated themes.

2.  Run The Layouts sub-generator with the command below, and use the options 
    listed below to create your layout: 

    ```bash
    yo liferay-theme:layout
    ```

    ![Figure 2: You must specify the width for each column in the row.](../../../images/layout-column-widths.png)

    ![Figure 3: The Layouts sub-generator automates the layout creation process.](../../../images/layout-prompt.png)

    - **Add a row:** Adds a row below the last row.
    
    - **Insert row:** Displays a vi to insert your row. Use your arrow keys to 
    choose where to insert your row, highlighted in blue, then press Enter to 
    insert the row.
    
    ![Figure 4: Rows can be inserted using the layout vi.](../../../images/insert-row.png)
    
    - **Remove row:** Displays a vi to remove your row. Use your arrow keys to 
    select the row you want to remove, highlighted in red, then press Enter to 
    remove the row.
    
    ![Figure 5: Rows are removed using the layout vi.](../../../images/remove-row.png)
    
    - **Finish Layout:** Complete the layout template.
    
    ![Figure 6: Select the *Finish layout* option to complete your design.](../../../images/finish-layout.png)

3.  Run `gulp deploy` to deploy your layout template to the server you 
    specified, or deploy your theme if the layout is [bundled with it](/docs/7-2/frameworks/-/knowledge_base/f/including-layout-templates-with-a-theme).

    | **Note:** Gulp is included as a local dependency of the generator, so you 
    | are not required to install it. It can be accessed by running 
    | `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's 
    | root folder.

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
