---
header-id: specifying-where-to-import-your-themes-resources
---

# Specifying Where to Import Your Theme's Resources

[TOC levels=1-4]

By default, resources are imported into a new site template named after the 
theme, but you can also import resources into a new site or existing sites or 
site templates. These options are covered below. Follow these steps:

1.  Before specifying where to import your resources, you must enable Developer 
    Mode in your theme. To do this, add the following property to your theme's 
    `liferay-plugin-package.properties` file. This is enabled by default for 
    themes generated with the [Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/theme-generator). 
    Without this property enabled, you must manually delete the sites or site 
    templates built by the Resources Importer each time you want to apply 
    changes from your theme's `src/WEB-INF/src/resources-importer` folder:

    ```properties
    resources-importer-developer-mode-enabled=true
    ```

    | **Warning:** the `resources-importer-developer-mode-enabled=true` setting can be
    | dangerous since it involves *deleting* (and re-creating) the affected site or
    | site template. It's only intended to be used during development. **Never use it
    | in production.**

2.  Specify where to import your resources. By default, resources are imported 
    into a new site template named after the theme. If that's what you want, you 
    can skip this step. If instead you want your resources to be imported into 
    an existing site template, you must specify a value for the 
    `resources-importer-target-value` property in your theme's 
    `liferay-plugin-package.properties` file:

    ```properties
    #resources-importer-target-class-name

    resources-importer-target-value=[site-template-name]
    ```

    Alternatively, you can import resources into an existing site. **You must** 
    import your resources into a site if you define both public and private page 
    sets in your `sitemap.json`. To import resources into an existing site, 
    uncomment the `resources-importer-target-class-name` property and set it to 
    `com.liferay.portal.kernel.model.Group`:

    ```properties
    resources-importer-target-class-name=com.liferay.portal.kernel.model.Group

    resources-importer-target-value=[site-name] 
    ```

    Double check the name that you're specifying. If you specify the wrong value, 
    you could end up deleting (and re-creating) the wrong site or site template! 

    | **Warning:** It's safer to import theme resources into a site template than into
    | an actual site. The
    | `resources-importer-target-class-name=com.liferay.portal.kernel.model.Group`
    | setting can be handy for development and testing but should be used cautiously.
    | Don't use this setting in a theme deployed to a production Liferay instance or
    | a theme submitted to Liferay Marketplace. To prepare a theme for deployment to
    | a production Liferay instance, use the default setting so that the resources are
    | imported into a site template. You can do this explicitly by setting
    | `resources-importer-target-class-name=com.liferay.portal.kernel.model.LayoutSetPrototype`
    | or implicitly by commenting out or removing the
    | `resources-importer-target-class-name` property.

3.  Deploy the theme. To view your theme and its resources, log in as an 
    administrator, and check the Sites or Site Templates section of the Control 
    Panel to make sure your resources were deployed correctly. From the Control 
    Panel you can easily view your theme and its resources:

    - If you imported into a site template, open its actions menu and select 
      *View Pages* to see it.
    - If you imported directly into a site, open its actions menu and select 
      *Go to Public Pages* to see it.

Great! Now you know how to specify where to import your theme's resources. 

## Related Topics

- [Preparing and Organizing Web Content for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/preparing-and-organizing-web-content-for-the-resources-importer)
- [Creating a Sitemap for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-sitemap-for-the-resources-importer)
- [Defining Assets for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/defining-assets-for-the-resources-importer)
