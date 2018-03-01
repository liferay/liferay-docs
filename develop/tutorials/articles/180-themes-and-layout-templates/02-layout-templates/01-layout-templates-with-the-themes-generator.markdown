# Layout Templates with the Liferay Theme Generator [](id=creating-layout-templates-with-the-themes-generator)

Layout Templates specify how content is arranged on your site pages in @product@, 
as shown in the *1-2-1 Columns Layout CE* layout below:

![Figure 1: The *1-2-1 Columns Layout CE* page layout creates a nice flow for your content.](../../../images/layout-template-1-2-1-columns.png)

@product@'s 
[Theme Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator) 
provides a Layouts sub-generator that helps automate layout template creation. 
This tutorial covers how to use this tool to create layout templates for 
@product@.

+$$$

**Note:** To create a layout template with the Layouts sub-generator, you must 
have the the 
[Liferay Theme Generator build tools installed](/develop/tutorials/-/knowledge_base/7-1/themes-generator).

$$$

## Creating a Layout Template with the Layouts Sub-generator [](id=creating-a-layout-template-with-the-layouts-sub-generator)

Follow these steps to create a layout template with the Theme Generator:

1.  Open the Command Line and navigate to the folder you want to create your
    layout template in.

2.  Run `yo liferay-theme:layout` to start the Layouts sub-generator.

    ![Figure 2: The Layout Template sub-generator automates the layout creation process.](../../../images/layout-prompt.png)
    
    +$$$
    
    **Note:** Run the Layouts sub-generator from the root folder of a theme 
    created with the Themes Generator to bundle it with the theme. This adds it 
    to the theme's `src/layouttpl` folder.
    
    $$$
    
3.  Enter a name and ID for your layout template, or press Enter to accept the
    default values.

4.  Choose your @product@ version and press Enter to continue.
    
5.  Enter the number of columns you would like for `row 1`. Every row consists 
    of 12 sections, so columns can range in size from 1 to 12. Once you've 
    entered a value, the generator asks the size you want your row and column to 
    be and presents you with the available width(s).
    
6.  Choose from the available option(s) with your arrow keys and press Enter to
    make your selection. If you have remaining space, the generator repeats this 
    step for the remaining columns.
    
    Once you're done configuring your row, you are presented with a few options:
    
    - *Add a row:* Adds a row below the last row.
    
    - *Insert row:* Displays a vi to insert your row. Use your arrow keys to 
    choose where to insert your row, highlighted in blue, then press Enter to 
    insert the row.
    
    ![Figure 3: Rows can be inserted using the layout vi.](../../../images/insert-row.png)

    - *Remove row:* Displays a vi to remove your row. Use your arrow keys to
    select the row you want to remove, highlighted in red, then press Enter to
    remove the row.
    
    ![Figure 4: Rows are removed using the layout vi.](../../../images/remove-row.png)
    
7.  Select *Finish layout* to complete your layout's design.

    ![Figure 5: Select the *Finish layout* option to complete your design.](../../../images/finish-layout.png)

    The layout template files are generated in the current folder.
    
8.  Enter the path to your app server, or press Enter to accept the default.
    
9.  Enter the URL to your server, or press Enter to accept the default
    `http://localhost:8080` development site.

    +$$$
    
    **Note:** Currently the Liferay Theme Generator does not add the layout 
    template configuration to your `liferay-look-and-feel.xml`. This feature 
    will be added in a future release. For now you must 
    [add this manually](/develop/tutorials/-/knowledge_base/7-1/including-layout-templates-with-a-theme).
    
    $$$

10.  Run `gulp deploy` to deploy your layout template with your theme to the 
     server you specified.

You can use your new layout template on your site!

## Related Topics [](id=related-topics)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-1/importing-resources-with-a-theme)

[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator)
