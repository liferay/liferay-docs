# Creating Layout Templates [](id=creating-layout-templates-with-the-themes-generator)

Layout Templates specify how content is arranged on your site pages, as shown in 
the *1-2-1 Columns Layout CE* layout below:

![Figure 1: The *1-2-1 Columns Layout CE* page layout creates a nice flow for your content.](../../../images/layout-template-1-2-1-columns.png)

The 
[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes) 
provides a Layouts sub-generator that helps automate layout template creation. 
This tutorial covers how to use this tool to create layout templates. Install 
the Liferay Theme Generator if it's not already installed, then follow these 
steps to create a layout template:

1.  Open the Command Line and navigate to the folder you want to create your
    layout template in.

2.  Run The Layouts sub-generator and follow the prompts to create your layout: 
    
        `yo liferay-theme:layout`

    ![Figure 2: The Layouts sub-generator automates the layout creation process.](../../../images/layout-prompt.png)
    
    +$$$
    
    **Note:** Run the Layouts sub-generator from the theme's root folder to 
    bundle it with the theme. This adds the layout template to the theme's 
    `src/layouttpl/custom` folder. This **only works** for generated themes.
    
    $$$
    
    Every row consists of 12 sections, so columns can range in size from 1 to 12. 
    Once you've entered a value, the generator asks the size you want your row 
    and column to be and presents you with the available width(s).
    
    ![Figure 3: You must specify the width for each column in the row.](../../../images/layout-column-widths.png)
    
    Choose from the available option(s) with your arrow keys and press Enter to 
    make your selection. Repeat this process for the remaining columns.
    
    The Layouts sub-generator provides the following options for layout 
    templates:
    
    - *Add a row:* Adds a row below the last row.
    
    - *Insert row:* Displays a vi to insert your row. Use your arrow keys to 
    choose where to insert your row, highlighted in blue, then press Enter to 
    insert the row.

    ![Figure 4: Rows can be inserted using the layout vi.](../../../images/insert-row.png)

    - *Remove row:* Displays a vi to remove your row. Use your arrow keys to 
    select the row you want to remove, highlighted in red, then press Enter to 
    remove the row.

    ![Figure 5: Rows are removed using the layout vi.](../../../images/remove-row.png)

    ![Figure 6: Select the *Finish layout* option to complete your design.](../../../images/finish-layout.png)

3.  Run `gulp deploy` to deploy your layout template to the server you 
    specified. If the layout is bundled with your theme, deploy the theme to 
    deploy the layout template.

## Related Topics [](id=related-topics)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-1/importing-resources-with-a-theme)

[Creating Themes](/develop/tutorials/-/knowledge_base/7-1/creating-themes)
