# Updating 6.2 Navigation Theme Template

<div class="learn-path-step">
    <p>Updating 6.2 Theme Templates<br>Step 2 of 4</p>
</div>

Follow these steps to update your modified `navigation.ftl` file:

1.  Below the `<nav class="${nav_css_class}" id="navigation" role="navigation">` 
    element, add the following hidden heading for accessibility screen readers:

```html
<h1 class="hide-accessible">
    <@liferay.language key="navigation" />
</h1>
```

2.  To access the layout, add the following variable declaration below the 
    `<#assign nav_item_css_class = "" />` variable declaration:

```markup
<#assign nav_item_layout = nav_item.getLayout() />
```

3.  Replace the `${nav_item.icon()}`variable in the 
    `<a aria-labelledby="layout_${nav_item.getLayoutId()}"...</a>` anchor with 
    the following element: 

```markup
<@liferay_theme["layout-icon"] layout=nav_item_layout />
```

The navigation template is updated. You can update `portlet.ftl` next. 

