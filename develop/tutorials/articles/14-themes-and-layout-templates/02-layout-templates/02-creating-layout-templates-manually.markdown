# Creating Layout Templates Manually [](id=creating-layout-templates-manually)

Although you can 
[generate layout templates with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator),  
you may prefer to create or modify them manually using your own tools. In this 
tutorial you'll learn the anatomy of @product@'s layout templates so you can 
create layout templates manually or modify existing ones.

## Understanding the Anatomy [](id=anatomy)

Layout templates are made of rows and columns. The design you create specifies 
where users can place portlets on the page. An example row's HTML markup is 
shown below:

    <div class="portlet-layout row">
            <div class="col-md-4 col-sm-6 portlet-column portlet-column-first" 
            id="column-1">
                    ${processor.processColumn("column-1", 
                    "portlet-column-content portlet-column-content-first")}
            </div>
            <div class="col-md-8 col-sm-6 portlet-column portlet-column-last" 
            id="column-2">
                    ${processor.processColumn("column-2", 
                    "portlet-column-content portlet-column-content-last")}
            </div>
    </div>

Each row has a `div`, with the classes `portlet-layout` and `row`, that contains 
child `div`s for each column. Each column is indicated with the class 
`portlet-column`, as well as a class that specifies whether it is the first 
(`portlet-column-first`), last (`portlet-column-last`), or only column in the 
row (`portlet-column-only`). 

Columns use the 
[Bootstrap grid system](https://getbootstrap.com/docs/4.0/layout/grid/) 
and can therefore range in width from 1 to 12. Sizes are indicated with the 
number that follows the `col-[breakpoint]` class prefix (e.g. `col-md-6`). These 
specify two things: the percentage based width of the element and the media 
query breakpoint (`xs`, `sm`, `md`, or `lg`) for when this element expands to 
100% width. 12 is the maximum amount, so `col-md-6` indicates `6/12` width, or 
`50%`. These classes can also be mixed to achieve more advanced layouts, as 
shown above. In the example, medium sized viewports display `column-1` at 33.33% 
width and `column-2` at 66.66% width, but on small sized view ports both 
`column-1` and `column-2` are 50% width. 

The processor (`${processor.processColumn()}`) processes each column's content, 
taking two arguments: the column's `id`, and the classes 
`portlet-column-content` and `portlet-column-content-[case]` (if applicable), 
where `[case]` refers to the `first`, `last`, or `only` column in the row. 

+$$$

**Note:** Velocity layout templates are supported, but deprecated as of 
@product-ver@. We recommend that you convert your Velocity layout templates to 
FreeMarker at your earliest convenience.

$$$

Now that you understand a layout template's anatomy, you can write your own 
@product@ layout templates!

## Related Topics [](id=related-topics)

[Layout Templates with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator)

[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator)

[Creating Custom Layout Template Thumbnail Previews](/develop/tutorials/-/knowledge_base/7-1/creating-custom-layout-template-thumbnail-previews)
