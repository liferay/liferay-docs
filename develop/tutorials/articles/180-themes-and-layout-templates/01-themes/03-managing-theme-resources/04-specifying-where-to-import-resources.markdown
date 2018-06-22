# Specifying Where to Import Your Theme's Resources [](id=specifying-where-to-import-your-themes-resources)

By default, resources are imported into a new site template named after the 
theme, but you can also import resources into a new site or existing sites or 
site templates. This tutorial covers all these options. 

First you must enable Developer Mode for the Resources Importer.

## Enabling Developer Mode [](id=enabling-developer-mode)

Before specifying where to import your resources, you must enable Developer Mode 
in your theme. To do this, add the following property to your theme's 
`liferay-plugin-package.properties` file:

    `resources-importer-developer-mode-enabled=true`

This is enabled by default for themes generated with the 
[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator). 
This is a convenience feature for theme developers. With this setting enabled,
importing resources into a site or site template that already exists recreates
the site or site template. Importing resources into a site template reapplies
the site template and its resources to the sites that are based on the site
template. Without `resources-importer-developer-mode-enabled=true`, you must
manually delete the sites or site templates built by the Resources Importer each
time you want to apply changes from your theme's
`src/WEB-INF/src/resources-importer` folder. 

+$$$

**Warning:** the `resources-importer-developer-mode-enabled=true` setting can be 
dangerous since it involves *deleting* (and re-creating) the affected site or 
site template. It's only intended to be used during development. **Never use it 
in production.**

$$$

With Developer Mode enabled in the Resource Importer, you can choose where you 
want to import your theme's resources. 

## Importing Resources into Existing Site Templates and Sites [](id=importing-resources-into-existing-site-templates-and-sites)

By default, resources are imported into a new site template named after the
theme. If you want your resources to be imported into an existing site template,
you must specify a value for the `resources-importer-target-value` property in
your theme's `liferay-plugin-package.properties` file:

    #resources-importer-target-class-name

    resources-importer-target-value=[site-template-name]

**You must** import your resources into a site if you define both public and 
private page sets in your `sitemap.json`. To import resources into an existing 
site, uncomment the `resources-importer-target-class-name` property and set it 
to `com.liferay.portal.kernel.model.Group`:

    resources-importer-target-class-name=com.liferay.portal.kernel.model.Group

    resources-importer-target-value=[site-name] 

Double check the name that you're specifying. If you specify the wrong value, 
you could end up deleting (and re-creating) the wrong site or site template! 

+$$$

**Warning:** It's safer to import theme resources into a site template than into
an actual site. The 
`resources-importer-target-class-name=com.liferay.portal.kernel.model.Group` 
setting can be handy for development and testing but should be used cautiously. 
Don't use this setting in a theme deployed to a production Liferay instance or
a theme submitted to Liferay Marketplace. To prepare a theme for deployment to
a production Liferay instance, use the default setting so that the resources are
imported into a site template. You can do this explicitly by setting 
`resources-importer-target-class-name=com.liferay.portal.kernel.model.LayoutSetPrototype` 
or implicitly by commenting out or removing the 
`resources-importer-target-class-name` property.

$$$

To view your theme and its resources, deploy the theme, log in as an
administrator, and check the Sites or Site Templates section of the Control
Panel to make sure your resources were deployed correctly. From the Control
Panel you can easily view your theme and its resources:

- If you imported into a site template, open its actions menu and select 
  *View Pages* to see it.
- If you imported directly into a site, open its actions menu and select 
  *Go to Public Pages* to see it.

It's just that easy to import resources with your theme!

## Related Topics [](id=related-topics)

[Preparing and organizing resources](/develop/tutorials/-/knowledge_base/7-1/preparing-resources-for-the-importer)

[Defining the Sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-the-sitemap)

[Defining the Assets](/develop/tutorials/-/knowledge_base/7-1/defining-the-assets)
