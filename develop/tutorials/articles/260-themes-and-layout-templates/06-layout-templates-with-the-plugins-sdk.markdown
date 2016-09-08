# Layout Templates with the Plugins SDK [](id=layout-templates)

In this tutorial, you'll learn how a layout template is constructed so you can
create your own custom templates.

This tutorial assumes you've already created your layout template project. For 
example, execute `./create.sh hello-world "Hello World"` from the `layouttpl` 
folder of the Plugins SDK. For detailed steps on how to create your layout
template, checkout the [Creating a Layout Template in the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/creating-a-layout-template-project-in-the-plugins-sdk) 
tutorial. If you would like to know more about the Plugins SDK itself, checkout 
the [Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk) tutorial.

This tutorial demonstrates how to:

- Create a Layout Template

- Create a Thumbnail for a Layout Template

Before you get started, take a look at what Liferay's *1-2-1 Columns Layout CE* 
page layout looks like, populated with some of Liferay's portlets. 

![Figure 1: The *1-2-1 Columns Layout CE* page layout works nicely for spanning portlets accross a page's header and footer and for grouping portlets in the middle of the page.](../../images/layout-template-1-2-1-columns.png)

As you can see the *1-2-1* refers to the number of columns in each row that can
hold a portlet.

the figure below shows the .tpl file for Liferay's *1-2-1 Columns Layout CE* 
[1_2_1_columns.tpl](https://github.com/liferay/liferay-portal/blob/7.0.x/portal-web/docroot/layouttpl/custom/1_2_1_columns.tpl) 
that you just saw:

    <div class="columns-1-2-1" id="main-content" role="main">
	    <div class="portlet-layout row">
		    <div class="col-md-12 portlet-column portlet-column-only" id="column-1">
			    $processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")
		    </div>
	    </div>

	    <div class="portlet-layout row">
		    <div class="col-md-6 portlet-column portlet-column-first" id="column-2">
		            $processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
		    </div>

		    <div class="col-md-6 portlet-column portlet-column-last" id="column-3">
			    $processor.processColumn("column-3", "portlet-column-content portlet-column-content-last")
		    </div>
	    </div>

	    <div class="portlet-layout row">
		    <div class="col-md-12 portlet-column portlet-column-only" id="column-4">
			    $processor.processColumn("column-4", "portlet-column-content portlet-column-content-only")
		    </div>
	    </div>
    </div>

In the steps below, you'll dive deeper into the different parts of a layout
template and learn how to create your own custom layout.

## Create the Columns and Rows for Your Layout [](id=create-the-columns-and-rows-for-your-layout)

Layout templates are constructed using HTML. To create your rows and columns 
follow these steps:

1.  Open your layout template project's `.tpl` file, found in your project's
`docroot` folder. If you're adding a layout template to the project, create a
new `.tpl` file in the `docroot` folder. 

2.  Create a layout template `<div>` in which to specify your entire layout
template. You must specify `class`, `id`, and `role` attributes for the `<div>`.
You can assign an arbitrary name to the `class`, but you must specify
`id=main-content` and `role=main` attribute settings. A layout template with a
class name *my-layout*, for example, could look like this: 

        <div class="my-layout" id="main-content" role="main">
            ...
        </div>

3.  Within the layout template `<div>`, specify a `<div>` for each row of
portlets your layout template supports. For example, if it supports only one row
of portlets, add one `<div>` within your layout template `<div>`. If it supports
two rows of portlets, add two `<div>`s, and so on.

    You must apply *portlet-layout* and *row* CSS classes to each of these row 
    `<div>`s:

        <div class="portlet-layout row">
            ...
        </div>

4.  Within each row `<div>`, specify one or more column `<div>`s. If a row
supports only one column, add one column `<div>`. If it supports two columns,
add two column `<div>`s, and so on.

    Each column `<div>` column must have a unique CSS ID attribute. A common
    convention to follow is to start each ID with *column-*, followed by an
    integer representing where that column is sequentially located in the
    template. For example, you could idenity the first column  as
    `id="column-1"`, you could identify the second column  as `id="column-2"`,
    and so on. 

    Each column `<div>` must specify the *portlet-column* CSS class as a class
    attribute value. In addition to that class, if a column is the first, the
    last, or the only column in a row, you must specify either the
    *portlet-column-first*, *portlet-column-last*, or *portlet-column-only* CSS
    class respectively. Note, that the CSS class values for a column `<div>`
    must be space separated. The column `<div>` elements in the *1-2-1 Columns
    Layout CE* source above, provides a good example of specifying the various
    kinds of column `<div>`s. 

    Each column `<div>`'s class attribute must also specify a CSS class called
    *col-[device-size]-[width]*. The *device-size* value must be one of the 
    following: `xs|sm|md|lg`. The size values match [Bootstrap's grid options](http://getbootstrap.com/css/#grid-options).
    The device sizes refer to Phones, Tablets, Desktops, and large Desktops 
    respectively. In most cases `col-md-[width]`, a setting for standard Desktop, 
    will be fine. The grid system stacks and scales your columns automatically 
    on smaller devices. You really only need to specify a device size other than 
    `md` when you wish to have one layout for Device A, and a different layout 
    for Device B. 
    
    Layout template columns follow a 12 column grid system.`The *width* value of 
    each *col-md-[width]* must be an integer value between 1 and 12. The sum of 
    all of the width values of a row's columns must equal 12.

    If a row only has one column, for example, that one column's col width class 
    must be *col-md-12*. The *col-md-12* CSS class name means that the column 
    will take up the entire page width.
    
    If a row has 12 columns, for example, each column must have a width class of 
    *col-md-1*. The point is that the width values of a row's columns must 
    compliment each other to equal 12. 

    If you wanted a row with four columns of equal page width, for example,
    you'd divide the twelve grid columns available between them (12 / 4 = 3).
    So, you'd specify a col width value *col-md-3* for each of the 
    four columns.

    Here's an example of a column `<div>`, with class name *column-4*, for 
    medium devices, that is for the last column of four equal width columns in a 
    row: 

        <div class="col-md-3 portlet-column portlet-column-last" id="column-4">
            ...
        </div>

    You're getting the hang of specifying columns, right?

    +$$$
    
    **Note:** Liferay 7.0 themes use a extension of Twitter Bootstrap v3.3.5 
    called [Lexicon](https://github.com/liferay/lexicon/tree/master/src/scss/bootstrap). 
    Lexicon affects Liferay's layout templates as well as its themes. Liferay
    7.0 layout templates use Bootstrap's 12 column grid system: 
    <http://http://getbootstrap.com/css/#grid>.

    $$$

That's all you have to do to specify the rows and columns for your layout
template. Jump into the last step next.

## Add the Velocity Template Directive to Each Column [](id=add-the-velocity-template-directive-to-each-column)

Now that your columns and rows are good to go, you'll need to add the Velocity
directive next.

1.  With each column `<div>...</div>`, you must include a Velocity template 
directive to render each column's portlets. As a user adds a portlet to the 
column of a page, the directives of the layout template's columns renders the 
portlet.

    For example, the directive for a layout's first column (with ID *column-1*) 
    in a row that only has one column could use a directive like this:

        $processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")

    The `processor.processColumn` function takes two arguments. The first is the 
    CSS column ID and the second is a list of CSS classes. You always need to 
    pass `"portlet-column-content"` in the second argument. If the column is the 
    first, last, or only column in a row, you also have to pass both
    `"portlet-column-content"` and `portlet-column-content-[first|last|only]` in 
    the second argument, separated by a space.

2.  Save the `.tpl` file.

    Your layout template is complete! At the moment it just has the default
    thumbnail, which isn't very helpful for users.
    
    ![Figure 2: The The default layout thumbnail is a placeholder and isn't very descriptive.](../../images/blank_columns.png)
    
    To create your own thumbnail follow the additional step below.
    
3.  Replace the `[layout-name.png]` file in your layout templates `docroot`
folder with your own custom thumbnail PNG.

Believe it or not, you've just created a layout template! You can deploy it to 
your server and apply it to your pages.

If you want to see more examples of layout templates, look at the
source of Liferay's layout templates available in the
[`layouttpl`](https://github.com/liferay/liferay-plugins/tree/master/layouttpl)
folder of the
[Liferay CE Plugins SDK repository](http://github.com/liferay/liferay-plugins).
Liferay Portal CE's core layout template files are available in the
installation's
[`portal-web/docroot/layouttpl/custom/`](https://github.com/liferay/liferay-portal/tree/7.0.x/portal-web/docroot/layouttpl/custom)
folder. 

Note, to save time, you can copy an existing layout template and modify it to
fit your needs. 

Since you're now a master at designing layout templates, go out there and
generate some positive Feng Shui with your eye-pleasing layout templates!

## Related Topics

[Embedding Portlets and Themes in Layout Templates](/develop/tutorials/-/knowledge_base/7-0/embedding-portlets-in-themes-and-layout-templates)

[Layout Templates with the Themes Generator](/develop/tutorials/-/knowledge_base/7-0/creating-layout-templates-with-the-themes-generator-0)
