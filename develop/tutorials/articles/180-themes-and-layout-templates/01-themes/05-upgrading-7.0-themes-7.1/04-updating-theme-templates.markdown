# Updating Theme Templates [](id=updating-theme-templates)

@product-ver@ theme templates are essentially the same as Liferay Portal 7.0 
theme templates. Here are the main changes:

-   Velocity templates were deprecated in Liferay Portal CE 7.0 and are now 
    removed in favor of FreeMarker templates in @product@. 

Key reasons for using FreeMarker templates and removing Velocity templates
are these: 

-   FreeMarker is developed and maintained regularly, while Velocity is no longer
    actively being developed.
 
-   FreeMarker is faster and supports more sophisticated macros.

-   FreeMarker supports using taglibs directly rather than requiring a method 
    to represent them. You can pass body content to them, parameters, etc.

You should start by addressing the Velocity theme templates. Since Velocity 
theme templates are no longer supported, **you must convert your Velocity theme 
templates to FreeMarker**.

If you're using the Liferay Theme Generator, the `gulp upgrade` command reports 
the required theme template changes in the log. 

For example, here is the `gulp upgrade` log for the Westeros Bank theme:

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

The log warns about removed and deprecated code and suggests replacements when
applicable. For reference, the main changes appear below:

- List items inside a container with the `list-inline` class 
  [now require](https://getbootstrap.com/docs/4.0/migration/#typography) 
  the `list-inline-item` class. 

- The `container-fluid-1280` class has been deprecated. Please use 
  `container-fluid container-fluid-max-xl` instead.

- Responsive navbar behaviors 
  [are now applied](https://getbootstrap.com/docs/4.0/migration/#navbar)
  to the `navbar` class via the required `navbar-expand-{breakpoint}` class. 

- The `navbar-toggle` class is now `navbar-toggler` and 
  [has different inner markup](https://getbootstrap.com/docs/4.0/migration/#navbar). 

- The `navbar-header` class has been removed. This container should be removed 
  in most cases. Please, use your own container if necessary.

Next, you'll learn how to update various theme templates to @product-ver@. If 
you didn't modify any theme templates, you can skip these sections.

## Updating Portal Normal FTL [](id=updating-portal-normal-ftl)

The first one to update is the `portal_normal.ftl` theme template. If you didn't 
customize `portal_normal.ftl`, you can skip this section. Follow the steps below 
to update `portal_normal.ftl`:

1.  Open your modified `portal_normal.ftl` file and remove the breadcrumbs:

        <nav id="breadcrumbs">		
            <@liferay.breadcrumbs />		
        </nav>

2.  Remove `id="main-surface"` from the `body` tag. This is not needed for SPA 
    to work properly:

        <body class="${css_class}" id="main-surface">

If you modified the portlet template for your theme, follow the steps in the 
next section. 

## Updating Portlet FTL [](id=updating-portlet-ftl)

Follow these steps to update your modified `portlet.ftl` file:

1.  Find the `<a class="icon-monospaced portlet-icon-back text-default" 
    href="${portlet_back_url}" title="<@liferay.language 
    key="return-to-full-page" />">` element and add the `list-unstyled` class 
    to it:

        <a 
          class="icon-monospaced list-unstyled portlet-icon-back text-default" 
          href="${portlet_back_url}" 
          title="<@liferay.language key="return-to-full-page" />"
        >

2.  Find the `<div class="autofit-float autofit-row">` element and add the 
    `portlet-header` class to it:
    
        <div class="autofit-float autofit-row portlet-header">

The portlet template is updated. That covers most, if not all, of the required 
theme template changes. If you modified any other FreeMarker theme templates, 
you can compare them with templates in the 
[`_unstyled` theme](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates). 
If your theme uses the Liferay Theme Generator, refer to the suggested changes 
that the `gulp upgrade` task reports. 

## Related Topics [](id=related-topics)

[Updating CSS Code](/develop/tutorials/-/knowledge_base/7-1/updating-css-code)

[Making Configurable Theme Settings](/develop/tutorials/-/knowledge_base/7-1/making-configurable-theme-settings)
