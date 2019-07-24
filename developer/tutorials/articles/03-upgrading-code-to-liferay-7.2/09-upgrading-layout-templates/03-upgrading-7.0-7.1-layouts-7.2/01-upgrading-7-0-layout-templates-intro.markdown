---
header-id: upgrading-7-0-and-7-1-layout-templates-to-7-2
---

# Upgrading 7.0 and 7.1 Layout Templates to 7.2

[TOC levels=1-4]

If you're upgrading your @product@ 7.0 or @product@ 7.1 layout template to 
@product-ver@, follow these steps:

1.  Open your layout template's `liferay-plugin-package.properties` file and 
    update the `liferay-versions` property to `7.2.0+`:

    ```properties
    liferay-versions=7.2.0+
    ```

2.  Velocity layout templates are supported, but deprecated as of @product@ 7.1. 
    We recommend that you convert your Velocity layout templates to FreeMarker 
    now. Wrap the 
    `processor.processColumn("column-1", "portlet-column-content portlet-column-content-first")` 
    methods with braces (`{...}`) and change the template's file extension to 
    `.ftl`.

3.  Save the changes.

    Below is an example configuration:

    Original (`my_layout_template.tpl`):

    ```html
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
    ```

    Updated (`my_layout_template.ftl`):

    ```html
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
    ```

Awesome! Your layout template is upgraded. 
