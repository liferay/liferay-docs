---
header-id: layout-templates-intro
---

# Layout Templates

Layout templates dictate where content and apps can be placed on a page. 
@product@ includes several default layout templates out-of-the-box that you can 
use to organize content on your pages:

![Figure 1: There are many default layout templates to choose from.](../../../images/page-select-layout.png)

If you require a layout for your content that's not provided, you can create 
your own layout template. 

You'll learn the following:

- [How to create layout templates with the Layouts sub-generator](/develop/tutorials/-/knowledge_base/7-2/creating-layout-templates-with-the-themes-generator)
- [How to create layout template thumbnail previews](/develop/tutorials/-/knowledge_base/7-2/creating-custom-layout-template-thumbnail-previews)
- [How to bundle layout templates with a theme](/develop/tutorials/-/knowledge_base/7-2/including-layout-templates-with-a-theme)

Layout Templates specify the number of rows and columns the page has. The rows 
and columns dictate where apps (and fragments) can be placed on the page. layout 
templates are written in 
[FreeMarker](https://freemarker.apache.org/). 
An example row's HTML markup is shown below:

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

Columns use the 
[Bootstrap grid system](https://getbootstrap.com/docs/4.0/layout/grid/). 
Every row consists of twelve sections, so columns can range in size from `1` to 
`12`. Sizes are indicated with the number that follows the `col-[breakpoint]` 
class prefix (e.g. `col-md-6`). These specify two things: the percentage based 
width of the element and the media query breakpoint (`xs`, `sm`, `md`, or `lg`), 
which specifies when the element expands to 100% width. For example, `col-md-6` 
indicates `6/12` width, or `50%`. These classes can also be mixed to achieve 
more advanced layouts, as shown above. In the example, medium sized viewports 
display `column-1` at 33.33% width and `column-2` at 66.66% width, while both 
`column-1` and `column-2` are 50% width on small sized view ports. 

The processor (`${processor.processColumn()}`) processes each column's content, 
taking two arguments: the column's `id`, and the classes 
`portlet-column-content` and `portlet-column-content-[case]` (if applicable), 
where `[case]` refers to the `first`, `last`, or `only` column in the row. 
