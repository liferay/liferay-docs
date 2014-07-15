# Designing a Layout Template

Initially, the layout template's generated TPL files are empty, a fresh canvas
on which you can design layout templates. If this seems overwhelming, don't
worry. By the end of this tutorial, you'll be able to build a new layout 
template and explain how it works. If you want to see more examples of layout 
templates, check out the Page Layouts section of [Liferay Marketplace](www.liferay.com/marketplace)
, download some CE layout templates provided by Liferay, and examine the source.
You also can examine Liferay's core layout templates. These can be found in
Liferay's source in the `liferay-portal/portal-web/docroot/layouttpl/custom/`
folder. 

Enough talking, time to get to what you came here for!

## Breaking Down the Design

Liferay provides a layout template called *1-2-1 Columns Layout CE* on Liferay 
Marketplace. Here's the source of the 1-2-1 Columns Layout template:

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

A CSS class named after the layout template project must be applied to the root
`<div>`: `class="columns-1-2-1"`. An ID of *main-content* and a role of *main*
must also be applied to the root `<div>`.

Inside of the root `<div>`, you need to create `<div>`s for each row of your
layout template. You must apply the *portlet-layout* and *row-fluid* CSS classes
to these `<div>`s: `<div class="portlet-layout row-fluid">`.

Inside each row `<div>`, you must specify one or more column `<div>`s. For each
column `<div>`, make sure to specify the *portlet-column* CSS class. If a column
is the first, last, or only column in a row, you must specify another CSS class:
*portlet-column-first*, *portlet-column-last*, or *portlet-column-only*.

Liferay 6.2 themes use a fork of Twitter Bootstrap called Alloy Bootstrap:
[https://github.com/liferay/alloy-bootstrap](https://github.com/liferay/alloy-bootstrap)
Alloy Bootstrap affects Liferay's layout templates as well as its themes.
Liferay 6.2 layout templates use Bootstrap's 12 column grid system:
[http://getbootstrap.com/css/#grid](http://getbootstrap.com/css/#grid). For each
column `<div>`, you must specify another CSS class called *span[width]* where
*width* is the numerator of a fraction over 12 representing the width of the
column. For example, if you apply a *span8* CSS class to a column `<div>`, the
column will take up `8/12 = 2/3` of the page width. Similarly, a *span3* CSS
class means that the column will take up `1/4` of the page width and a *span12*
CSS means that the column will take up the entire page width.

Next, for each column `<div>`, you need to specify a unique CSS ID. E.g.,
`id="column-1"`, `id="column-2"`, etc.

Finally, inside each column `<div>`, you need to include a Velocity template
directive. This directive is responsible for rendering the portlets that have
been added to each column:

    $processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")

The `processor.processColumn` function takes two arguments. The first is the CSS
column ID and the second is a list of CSS classes. You always need to pass
`"portlet-column-content"` in the second argument. If the column is the first,
last, or only column in a row, you also have to pass both
`"portlet-column-content"` and `portlet-column-content-[first|last|only]` in the 
second argument, separated by a space.

Now that you've explored how layout template TPL files are designed, you can
learn how to convert the 1 2 1 column template presented above into a 1 4 1 
column template next.

## Converting a Layout Template Design into a New One

The example below creates a layout template named *Columns 1 4 1* because the 
first row will have just one column, the second row will have 4 (equal width) 
columns, and the third row will have just one column. Note that you can use the 
same process to convert any layout template into a new one. Follow the steps 
below to learn how:

![Figure 1: You can edit your layout template with an HTML editor in Developer Studio.](../../images/layout-template-tpl-src-almost.png)

1. Change the first CSS class of the root `<div>` from *columns-1-2-1* to
   *columns-1-4-1*.

2. You don't need to change the first row `<div>` since its already set up
   with a single column. You do need to change the second row `<div>` since you
   need to set up four equal width columns. Replace the second row `<div>` with
   the following:

        <div class="portlet-layout row-fluid">
                <div class="portlet-column portlet-column-first span3" id="column-2">
                        $processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
                </div>

                <div class="portlet-column portlet-column span3" id="column-3">
                        $processor.processColumn("column-3", "portlet-column-content")
                </div>
                
                <div class="portlet-column portlet-column span3" id="column-4">
                        $processor.processColumn("column-4", "portlet-column-content")
                </div>
                
                <div class="portlet-column portlet-column-last span3" id="column-5">
                        $processor.processColumn("column-5", "portlet-column-content portlet-column-content-last")
                </div>
        </div>

3. The final row `<div>` is set up with a single column so the only thing you 
   need to change is its ID. Replace it with the following:

        <div class="portlet-layout row-fluid">
                <div class="portlet-column portlet-column-only span12" id="column-6">
                        $processor.processColumn("column-6", "portlet-column-content portlet-column-content-only")
                </div>
        </div>

Just like that, the rows and columns of the *Columns 1 4 1* layout template are
arranged and sized to fit your needs. 

Now you are the layout master! Go out there and generate some positive Feng Shui 
with your designs!

## Next Steps
<!-- URL will need changed to proper one when moved to LDN-->
 [Embedding Portlets in a Layout Template](/tutorials/-/knowledge_base/embedding-portlets-in-a-layout-template)
