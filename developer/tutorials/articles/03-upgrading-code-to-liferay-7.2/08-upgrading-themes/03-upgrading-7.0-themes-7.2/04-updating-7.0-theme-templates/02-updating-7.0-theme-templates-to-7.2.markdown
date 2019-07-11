---
header-id: updating-7-0-theme-templates
---

# Updating 7.0 Theme Templates

[TOC levels=1-4]

Follow these steps to update the theme's templates. Note these changes are only 
required if the templates are modified in the theme:

1.  Open `portal_normal.ftl` and remove the breadcrumbs:

    ```html
    <nav id="breadcrumbs">		
        <@liferay.breadcrumbs />		
    </nav>
    ```

2.  Still inside `portal_normal.ftl`, remove `id="main-surface"` from the `body` 
    tag so it looks like the one below. This is not needed for SPA to work 
    properly:

    ```html
    <body class="${css_class}">
    ```

3.  Open `navigation.ftl` and remove the `nav_item_attr_selected` variable 
    declaration at the top. Don't forget to remove all uses of the 
    `nav_item_attr_selected` throughout the rest of the template.

4.  Also inside `navigation.ftl`, remove the `nav_child_attr_selected` variable 
    from the bottom of the template, including all uses throughout the rest of 
    the template.
    
5.  Open `portlet.ftl`, find the 
    `<a class="icon-monospaced portlet-icon-back text-default" 
    href="${portlet_back_url}" title="<@liferay.language 
    key="return-to-full-page" />">` element and add the `list-unstyled` class 
    to it:

    ```html
    <a 
      class="icon-monospaced list-unstyled portlet-icon-back text-default" 
      href="${portlet_back_url}" 
      title="<@liferay.language key="return-to-full-page" />"
    >
    ```

6.  Still inside `portlet.ftl`, find the 
    `<div class="autofit-float autofit-row">` element and add the 
    `portlet-header` class to it:

    ```html    
    <div class="autofit-float autofit-row portlet-header">
    ```

The theme templates are updated! If you modified any other FreeMarker theme 
templates, you can compare them with templates in the 
[`_unstyled` theme](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates). Next you can learn how 
to use @product@'s compatibility layer to help ease the transition to Bootstrap 
4 and Clay CSS. 
