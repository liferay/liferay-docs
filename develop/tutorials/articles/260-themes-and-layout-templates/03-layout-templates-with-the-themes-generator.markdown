# Layout Templates with the Liferay Theme Generator [](id=creating-layout-templates-with-the-themes-generator-0)

Layout Templates specify how content is arranged on your site pages in @product@.
For example, take a look at the *1-2-1 Columns Layout CE* layout shown below:

![Figure 1: The *1-2-1 Columns Layout CE* page layout creates a nice flow for your content.](../../images/layout-template-1-2-1-columns.png)

@product-ver@ and DXP provide several layout templates out-of-the-box for you to 
choose from. You can change the layout for your page, and view the installed 
layout templates, by opening the `Edit` menu for your page, under the `Navigation`
heading of the `Product Menu` and scrolling down to the *Layouts* heading.

![Figure 2: Liferay provides several layout templates out-of-the-box for you to use.](../../images/layout-templates.png)

If you'd like to create your own custom layout templates, you've come to the
right place.

This tutorial demonstrates how to:

- Create a Layout Template with the Layouts Sub-generator

- Create a Thumbnail for a Layout Template

In order to create a layout template with the Layouts Sub-generator, you will 
need the Node.js build tools installed. The [Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
tutorial explains how to install these tools and how to create a theme.

Once you have the Liferay Theme Generator installed you can go ahead and get
started.

## Creating a Layout Template with the Layouts Sub-generator [](id=creating-a-layout-template-with-the-layouts-sub-generator)

Follow these steps to create a layout template:

1.  Open the Command Line and navigate to the directory you want to create your
    layout template in.

2.  Run `yo liferay-theme:layout` to start the layouts sub-generator.

    ![Figure 3: The Layout Template sub-generator automates the layout creation process.](../../images/layout-prompt.png)
    
    **Note:** If you run the layout sub-generator from the root directory of a 
    theme created with the themes generator, it will add the layout template as 
    a part of the theme in the `src/layouttpl` directory.
    
3.  Enter a name and ID for your layout template, or press Enter to accept the
    default values.

4.  Choose your Liferay version and press Enter to continue.

    At this point the layout template design process begins. As the generator
    states, Layout templates implement Bootstrap's grid system. Every row 
    consists of 12 sections, so columns range in size from 1 to 12. The 
    sub-generator is user-friendly, allowing you to add and remove rows and
    columns as you design.
    
5.  Enter the number of columns you would like for `row 1`.

    Once you've entered a value, the generator asks how wide you want your row
    and column to be, and presents you with the available width(s).
    
6.  Choose from the available option(s) with your arrow keys and press Enter to
    make your selection.
    
    If you have remaining space, the generator will repeat this step for the
    remaining columns.
    
    Once you're done configuring your row, you are presented with a few options:
    
    - Add a row: Adds a row below the last row.
    
    - Insert row: Displays a vi to insert your row. Use your arrow keys to 
    choose where to insert your row, highlighted in blue, then press Enter to 
    insert the row.
    
    ![Figure 4: Rows can be inserted using the layout vi.](../../images/insert-row.png)

    - Remove row: Displays a vi to remove your row. Use your arrow keys to
    select the row you want to remove, highlighted in red, then press Enter to
    remove the row.
    
    ![Figure 5: Rows are removed using the layout vi.](../../images/remove-row.png)

    Once you are done designing your layout you can move onto the next step.
    
7.  Select *Finish layout* to complete your layout's design.

    ![Figure 6: Select the *Finish layout* option to complete your design.](../../images/finish-layout.png)

    Your layout template files are generated for you in the current directory.
    
8.  Enter the path to your app server directory, or press Enter to accept the
    default.
    
9.  Finally, enter the URL to your server or press Enter to accept the default
    `http://localhost:8080` development site.
    
When your layout template was generated, a default thumbnail was created. You
can learn how to create a custom thumbnail in the next section.

## Creating a Custom Thumbnail for Your Layout Template [](id=creating-a-custom-thumbnail-for-your-layout-template)

To create your own thumbnail follow the steps below:

1.  Navigate to the `docroot` directory of the layout template you just created.

    **Note:** if you created the layout template in your existing themes
    generator theme, the thumbnail is located in your theme's 
    `src/layouttpl/custom` directory.

2.  Replace the `layout-name.png` file with your own custom thumbnail PNG.
    
3.  navigate back to the layout's root directory and run `gulp deploy` to
    re-build and deploy the template to your app server.
    
    **Note:** If your layout template was added as part of your themes generator
    theme, the layout template will deploy when the theme is deployed.
    
Your layout template is complete! As you can see, the layouts sub-generator
makes creating a layout template a piece of cake.

Edit a page on your site and select your new layout template to use it.

## Related Topics [](id=related-topics)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme)

[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
