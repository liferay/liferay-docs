# Creating Layout Templates Manually [](id=creating-layout-templates-manually)

You can use the [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
to generate Layout Templates automatically. This is covered in the 
[Layout Templates with the Themes Generator](develop/tutorials/-/knowledge_base/7-0/creating-layout-templates-with-the-themes-generator-0) 
tutorial. You may, however, want to create or modify your layout templates manually.

In this tutorial you'll learn how to create or modify a Layout Template manually.

You can see the HTML markup for a basic layout template next.

## Basic Layout Template [](id=basic-layout-template)

Below is an example of a basic Layout Template `.tpl` file:

    <div class="my-liferay-layout" id="main-content" role="main">
            <div class="portlet-layout row">
                    <div class="col-md-4 portlet-column portlet-column-first" 
                    id="column-1">
                            $processor.processColumn("column-1", 
                            "portlet-column-content portlet-column-content-first")
                    </div>
                    <div class="col-md-8 portlet-column portlet-column-last" id="column-2">
                            $processor.processColumn("column-2", 
                            "portlet-column-content portlet-column-content-last")
                    </div>
            </div>
            <div class="portlet-layout row">
                    <div class="col-md-12 portlet-column portlet-column-only" id="column-3">
                            $processor.processColumn("column-3", 
                            "portlet-column-content portlet-column-content-only")
                    </div>
            </div>
            <div class="portlet-layout row">
                    <div class="col-md-4 portlet-column portlet-column-first" id="column-4">
                            $processor.processColumn("column-4", 
                            "portlet-column-content portlet-column-content-first")
                    </div>
                    <div class="col-md-4 portlet-column" id="column-5">
                            $processor.processColumn("column-5", "portlet-column-content")
                    </div>
                    <div class="col-md-4 portlet-column portlet-column-last" id="column-6">
                            $processor.processColumn("column-6", 
                            "portlet-column-content portlet-column-content-last")
                    </div>
            </div>
    </div>

The column elements and classes are described in more detail next.

## Column Elements and Classes [](id=column-elements-and-classes)

To understand how the layout template works, you must look closely at how the
HTML is structured. This section uses the first column of the example above 
to demonstrate the key elements and classes of a layout template:

    <div class="col-md-4 portlet-column portlet-column-first" id="column-1">
            $processor.processColumn("column-1", "portlet-column-content 
            portlet-column-content-first")
    </div>

You can learn more about the column container next.

### Column Container [](id=column-container)

Below is a description of each of the column container classes:

`column-1`: A unique identifier for the column that matches the ID passed to 
`$processor.processColumn`.

`col-md-4`: This class comes from Bootstrap's grid system and determines two 
things: the percentage based width of the element, and the media query 
breakpoint for when this element expands to 100% width. 12 is the maximum 
amount, so `col-md-4` indicates `4/12` width, or `33.33%`.

`portlet-column portlet-column-first`: All column containers must use the 
`portlet-column` class. For rows with more than one column, the first column 
must have `portlet-column-first` and the last must have `portlet-column-last`. 
For rows with only one column, use the `portlet-column-only` class.
 
Next you can learn more about the `$processor.processColumn`.

### Processor ProcessColumn [](id=processor-processcolumn)

`$processor.processColumn` takes these arguments: 

`column-1`: A unique identifier. This should match the ID of the parent `div`.

`portlet-column-content portlet-column-content-first`: Additional classes 
added to the content element. These classes must match the parent `div`'s 
classes with `-content` appended.

Next you can learn how to modify template breakpoints.

## Modifying Template Breakpoints [](id=modifying-template-breakpoints)

When looking at the example template, you'll notice this Bootstrap grid class 
is used on every column:

    col-md-{size}

The different sizes available are `xs`, `sm`, `md`, and `lg`. The medium size is 
used by default, but the others can be used in layout templates as well.

For example, setting the column classes to `col-lg-{size}` means the columns 
would expand to 100% width at a larger screen width than `col-md-{size}`.

These classes can also be mixed to achieve more advanced layouts, as shown
below:

    <div class="portlet-layout row">
            <div class="col-md-4 col-sm-6 portlet-column portlet-column-first" 
            id="column-1">
                    $processor.processColumn("column-1", 
                    "portlet-column-content portlet-column-content-first")
            </div>
            <div class="col-md-8 col-sm-6 portlet-column portlet-column-last" 
            id="column-2">
                    $processor.processColumn("column-2", 
                    "portlet-column-content portlet-column-content-last")
            </div>
    </div>

In the example row above, on medium sized view, ports `column-1` are 33.33% 
width and `column-2` are 66.66% width, but on small sized view ports both 
`column-1` and `column-2` are 50% width.

Place the completed layout in your theme's `src/layouttpl` folder if you created
your theme with Theme's Generator, or place it in your theme's 
`docroot/layouttpl/custom` folder if using the Plugins SDK.

There you have it. Now you know how to create and modify layout
templates manually!

## Related Topics [](id=related-topics)

[Layout Templates with the Themes Generator](develop/tutorials/-/knowledge_base/7-0/creating-layout-templates-with-the-themes-generator-0)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
