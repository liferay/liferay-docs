# Designing a Layout Template [](id=designing-a-layout-template)

Initially, the layout template's generated TPL files are empty, a fresh canvas
on which you can design a page layout template. If this seems overwhelming,
don't worry. By the end of this tutorial, you'll be able to build a new layout
template and explain how it works. In this tutorial, you'll learn the structure
of a layout template and design your own custom layout template. 

Before diving into designing your own layout template, you may want to look at
an existing one, like Liferay's *1-2-1 Columns Layout CE* page layout, to learn
how a layout template works from a user perspective. The figure below shows what
it looks like, populated with some of Liferay's portlets. 

![Figure 1: The *1-2-1 Columns Layout CE* page layout works nicely for spanning portlets accross a page's header and footer and for grouping portlets in the middle of the page.](../../images/layout-template-1-2-1-columns.png)

The figure above shows a page that uses the *1-2-1 Columns Layout CE* page
layout. The first row has one column that spans the width of the page. A column
can hold as many portlets as you like. A Breadcrumb portlet was added to this
column. The second row has two columns: this row's first column is designed so
that it takes up twice as much width as the second column. In this example page,
the first column has a Dictionary and Quick Note portlet stacked in it and the
second column has a Language and Unit Converter portlet stacked in it. At the
bottom of the page, the layout's last row is similar to the first row in that it
has only one column. Two portlets were added to this column: a User Statistics
and Network Utilities portlet. As you can see, layout templates give structure
to a page by organizing portlets as users drag the portlets onto the different
parts of the page's layout layout template. 

You're probably wondering what the source code looks like for this layout
template. Like all Liferay layout templates, it's specified in a single file
called a TPL file. Here's the content of the *1-2-1 Columns Layout CE*'s TPL
file,
[1_2_1_columns.tpl](https://github.com/liferay/liferay-plugins/blob/6.2.x/layouttpl/1-2-1-columns-layouttpl/docroot/1_2_1_columns.tpl):

    <div class="columns-1-2-1" id="main-content" role="main">
        <div class="portlet-layout row-fluid">
            <div class="portlet-column portlet-column-only span12" id="column-1">
                $processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")
            </div>
        </div>

        <div class="portlet-layout row-fluid">
            <div class="portlet-column portlet-column-first span8" id="column-2">
                $processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
            </div>

            <div class="portlet-column portlet-column-last span4" id="column-3">
                $processor.processColumn("column-3", "portlet-column-content portlet-column-content-last")
            </div>
        </div>

        <div class="portlet-layout row-fluid">
            <div class="portlet-column portlet-column-only span12" id="column-4">
                $processor.processColumn("column-4", "portlet-column-content portlet-column-content-only")
            </div>
        </div>
    </div>

The TPL uses HTML `<div>` tags to specify its rows and columns. The *1-2-1
Columns Layout CE* page layout has an outter `<div>` that contains everything.
This template's three row `<div>`s are child elements of the layout template
`<div>`. Each row contains one or more column `<div>`s. There are plenty of
attributes in these `<div>` elements that you'll learn to use as you construct
your own page layout templates, next. Remember that you can refer to existing
page layout TPL code, like the TPL of *1-2-1 Columns Layout CE* page layout, as
you design your own layout templates. 

You can follow these steps to design your own templates: 

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

    You must apply *portlet-layout* and *row-fluid* CSS classes to each of these
    row `<div>`s:

        <div class="portlet-layout row-fluid">
            ...
        </div>

4.  Within each row `<div>`, specify one or more column `<div>`s. If a row
supports only one column, add one column `<div>`. If it supports two columns,
add two column `<div>`s, and so on.

    Each column `<div>` column must have a unique CSS ID attribute. A common
    convention to follow is to start each ID with *column-*, followed by an
    integer representing where that column is sequentially located in the
    template. For example, you could idenity the first column  as
    `id="column-1"`, you could identiry the second column  as `id="column-2"`,
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
    *span[width]*. Layout template columns follow a 12 column grid system. The
    *width* value of each *span[width]* must be an integer value between 1 and
    12. The sum of all of the span width values of a row's columns must equal
    12. 

    If a row only has one column, for example, that one column's span width
    class must be *span12*. The *span12* CSS class name means that the column
    will take up the entire page width.
    
    If a row has 12 columns, for example, each column must have a span width
    class of *span1*. The point is that the span width values of a row's columns
    must compliment each other to equal 12. 

    If you wanted a row with four columns of equal page width, for example,
    you'd divide the twelve grid columns available between them (12 / 4 = 3).
    So, you'd specify a span width value *span3* for each of the four columns.

    Here's an example of a column `<div>`, with class name *column-4*, that is
    for the last column of four equal width columns in a row: 

        <div class="portlet-column portlet-column-last span4" id="column-4">
            ...
        </div>

    You're getting the hang of specifying columns, right?

$$$
    
    **Note:** Liferay 6.2 themes use a fork of
    Twitter Bootstrap v2.3.2 called 
    [Alloy Bootstrap](https://github.com/liferay/alloy-bootstrap). Alloy
    Bootstrap affects Liferay's layout templates as well as its themes. Liferay
    6.2 layout templates use Bootstrap's 12 column grid system: 
    <http://getbootstrap.com/2.3.2/scaffolding.html#gridSystem>.

$$$

6.  Finally, with each column `<div>...</div>`, you must include a Velocity
template directive to render each column's portlets. As a user adds a portlet to
the column of a page, the directives of the layout template's columns renders
the portlet. 

    For example, the directive for a layout's first column (with ID
    *column-1*) in a row that only has one column could use a directive like
    this: 

        $processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")

    The `processor.processColumn` function takes two arguments. The first is the 
    CSS column ID and the second is a list of CSS classes. You always need to 
    pass `"portlet-column-content"` in the second argument. If the column is the 
    first, last, or only column in a row, you also have to pass both
    `"portlet-column-content"` and `portlet-column-content-[first|last|only]` in 
    the second argument, separated by a space.

7.  Save the `.tpl` file. 

Believe it or not, you've just created a layout template! You can
[deploy](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/deploying-plugins)
it to your portal and apply it to your portal's pages. 

If you want to see more examples of layout templates, check out the Page Layouts
section of [Liferay Marketplace](www.liferay.com/marketplace) or look at the
source of Liferay's layout templates available in the
[`layouttpl`](https://github.com/liferay/liferay-plugins/tree/6.2.x/layouttpl)
folder of the Liferay CE Plugins SDK repository
<http://github.com/liferay/liferay-plugins>. Liferay Portal CE's core layout
template files are available in the installation's
[`portal-web/docroot/layouttpl/custom/`](https://github.com/liferay/liferay-portal/tree/6.2.x/portal-web/docroot/layouttpl/custom)
folder. 

Note, to save time, you can copy an existing layout template and modify it to
fit your needs. 

Since you're now a master at designing layout templates, go out there and
generate some positive Feng Shui with your eye-pleasing layout templates! 

