---
header-id: updating-6-2-navigation-theme-template
---

# Updating 6.2 Navigation Theme Template

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Updating 6.2 Theme Templates</p><p>Step 2 of 3</p>
</div>

Follow these steps to update `navigation.ftl`:

1.  Below the `<nav class="${nav_css_class}" id="navigation" role="navigation">` 
    element, add the heading below to improve accessibility for screen readers:

    ```html
    <h1 class="hide-accessible">
        <@liferay.language key="navigation" />
    </h1>
    ```

2.  Remove the `nav_item_attr_selected` variable declaration at the top, and add 
    the layout declaration shown below instead, to access the layout. Don't 
    forget to remove all uses of `nav_item_attr_selected` throughout the rest of 
    the template:

    ```markup
    <#assign nav_item_layout = nav_item.getLayout() />
    ```

3.  Replace the `${nav_item.icon()}`variable in the 
    `<a aria-labelledby="layout_${nav_item.getLayoutId()}"...</a>` anchor with 
    the element below: 

    ```markup
    <@liferay_theme["layout-icon"] layout=nav_item_layout />
    ```

4.  Remove the `nav_child_attr_selected` variable from the bottom of the 
    template, including all uses throughout the rest of the template.

The navigation template is updated. You can update `portlet.ftl` next. 
