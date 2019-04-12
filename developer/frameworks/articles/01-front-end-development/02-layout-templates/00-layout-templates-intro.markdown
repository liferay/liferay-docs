---
header-id: layout-templates-intro
---

# Layout Templates

[TOC levels=1-4]

Layout templates dictate where content and apps can be placed on a page. 
There are several default layout templates for organizing content on your
pages:

![Figure 1: There are many default layout templates to choose from.](../../../images/page-select-layout.png)

If the default layouts don't work for your site, you can create your own layout
template by following the articles listed below: 

- [Create the layout template](/docs/7-2/reference/-/knowledge_base/reference/creating-layout-templates-with-the-themes-generator)
- [Create the layout template thumbnail preview](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-custom-layout-template-thumbnail-previews)
- [Bundle the layout template with a theme](/docs/7-2/frameworks/-/knowledge_base/frameworks/including-layout-templates-with-a-theme)

Layout Templates specify a number of rows and columns for the page. The rows 
and columns dictate where apps (and fragments) can be placed on the page. Layout 
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
class prefix (e.g. `col-md-6`). These specify two things: the percentage-based 
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
