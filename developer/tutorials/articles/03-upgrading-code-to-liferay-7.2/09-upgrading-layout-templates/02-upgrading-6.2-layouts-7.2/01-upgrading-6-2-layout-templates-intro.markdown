---
header-id: upgrading-6-2-layout-templates-to-7-2
---

# Upgrading 6.2 Layout Templates to 7.2

[TOC levels=1-4]

Upgrading your @product@ 6.2 layout template to @product-ver@ requires a few 
updates:

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

3.  Update the Bootstrap `span[number]` classes to use the newer 
    `col-[size]-[number]` classes. See [Layout Templates](/docs/7-2/frameworks/-/knowledge_base/f/layout-templates-intro) 
    for more information on the updated syntax.

4.  Save the changes.

    Below is an example configuration:

    Original:

    ```html
    <div class="span4 span6 portlet-column portlet-column-first" 
    id="column-1">
            $processor.processColumn("column-1", 
            "portlet-column-content portlet-column-content-first")
    </div>
    <div class="span8 span6 portlet-column portlet-column-last" 
    id="column-2">
            $processor.processColumn("column-2", 
            "portlet-column-content portlet-column-content-last")
    </div>
    </div>
    ```

    Updated:

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
