---
header-id: specifying-how-resources-are-loaded-during-navigation
---

# Specifying How Resources Are Loaded During Navigation

[TOC levels=1-4]

By default, @product@ unloads CSS resources from the `<head>` element on 
navigation. JavaScript resources in the `<head>`, however, are not removed on 
navigation. This functionality can be customized by setting the resource's 
`data-senna-track` attribute. Follow these steps to customize your resources:

1.  Select the resource you want to modify the default behavior for.

2.  Add the `data-senna-track` attribute to the resource.

3.  Set the `data-senna-track` attribute to `permanent` to prevent a resource 
    from unloading on navigation.
    
    Alternatively, set the `data-senna-track` attribute to `temporary` to unload 
    the resource on navigation. 
    
    | **Note:** the `data-senna-track` attribute can be added to resources loaded
    | outside of the `<head>` element as well to specify navigation behavior.
    
The example below ensures that the JS resource isn't unloaded during navigation:

```html
<script src="myscript.js" data-senna-track="permanent" />
```

Great! Now you know how to specify how resources are loaded during SPA 
navigation. 

## Related Topics

- [Configuring SPA System Settings](/docs/7-2/frameworks/-/knowledge_base/f/configuring-spa-system-settings)
- [Disabling SPA](/docs/7-2/frameworks/-/knowledge_base/f/disabling-spa)
- [Detaching Global Listeners](/docs/7-2/frameworks/-/knowledge_base/f/detaching-global-listeners)
