# Updating 6.2 Portlet Theme Template

<div class="learn-path-step">
    <p>Updating 6.2 Theme Templates<br>Step 3 of 4</p>
</div>

Follow these steps to update your modified `portlet.ftl` file:

1.  Find the `<a class="icon-monospaced portlet-icon-back text-default" 
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

2.  Find the `<div class="autofit-float autofit-row">` element and add the 
    `portlet-header` class to it:

```html    
<div class="autofit-float autofit-row portlet-header">
```

The portlet template is updated. You can update `init_custom.ftl` next.

