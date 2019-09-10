---
header-id: updating-7-0-theme-templates-to-7-2
---

# Updating 7.0 Theme Templates to 7.2

[TOC levels=1-4]

@product@ 7.0 theme templates and @product-ver@ theme templates are essentially 
the same. Here are the main changes:

-   Velocity templates were deprecated in Liferay Portal CE 7.0 and are now 
    removed in favor of FreeMarker templates in @product-ver@. 

Key reasons for using FreeMarker templates and removing Velocity templates
are these: 

-   FreeMarker is developed and maintained regularly, while Velocity is no longer
    actively being developed.
 
-   FreeMarker is faster and supports more sophisticated macros.

-   FreeMarker supports using taglibs directly rather than requiring a method 
    to represent them. You can pass body content to them, parameters, etc.

If you haven't converted your Velocity theme templates to FreeMarker, **you must 
convert your Velocity theme templates to FreeMarker now**. 

The `gulp upgrade` command reports the required theme template changes in the 
log. For example, here is the `gulp upgrade` log for the Westeros Bank theme:

```bash
----------------------------------------------------------------
 Liferay Upgrade (7.0 to 7.1)
----------------------------------------------------------------

Renamed aui.scss to clay.scss
File: footer.ftl
    Warning: .container-fluid-1280 has been deprecated. Please use 
    .container-fluid.container-fluid-max-xl instead.
File: portal_normal.ftl
    Warning: .navbar-header has been removed. This container should be 
    removed in most cases. Please, use your own container if necessary.
```

The log warns about removed and deprecated code and suggests replacements when
applicable. For reference, the main changes between @product@ 7.0 themes and 
@product-ver@ themes appear below:

- List items inside a container with the `list-inline` class 
  [now require](https://getbootstrap.com/docs/4.3/migration/#typography) 
  the `list-inline-item` class. 

- The `container-fluid-1280` class has been deprecated. Please use 
  `container-fluid container-fluid-max-xl` instead.

- Responsive navbar behaviors 
  [are now applied](https://getbootstrap.com/docs/4.3/migration/#navbar)
  to the `navbar` class via the required `navbar-expand-{breakpoint}` class. 

- The `navbar-toggle` class is now `navbar-toggler` and 
  [has different inner markup](https://getbootstrap.com/docs/4.3/migration/#navbar). 

- The `navbar-header` class has been removed. This container should be removed 
  in most cases. Please, use your own container if necessary.

In this section you'll learn how to update various theme templates to 
@product-ver@. 

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/updating-7-0-theme-templates">Let's Go<span class="icon-circle-arrow-right"></span></a>
