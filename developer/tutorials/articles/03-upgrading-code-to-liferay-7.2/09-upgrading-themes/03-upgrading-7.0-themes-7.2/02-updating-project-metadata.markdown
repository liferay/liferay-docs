---
header-id: updating-project-metadata
---

# Updating Project Metadata

[TOC levels=1-4]

If your theme uses the 
[Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes), 
the 
[gulp upgrade task](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-themes-created-with-the-theme-generator) 
automatically updates your theme's metadata as part of the upgrade process. If 
you're developing your theme in an environment other than the themes generator, 
follow the steps below to update your theme's metadata manually:

1.  Open your theme's `liferay-plugin-package.properties` file and change the 
    `liferay-versions` property value to `7.2.0+`: 

        liferay-versions=7.2.0+

2.  Open the `liferay-look-and-feel.xml` file and specify `7.2.0+` as the 
    compatibility version: 

        <look-and-feel>
                <compatibility>
                        <version>7.2.0+</version>
                </compatibility>

                ...

        </look-and-feel>

3.  If your theme uses the Liferay Theme Generator, open the `package.json` file 
    and update the file's Liferay version references to `7.2`. Update the 
    `liferayTheme`'s `templateLanguage` to `ftl` (since Velocity theme templates 
    are no longer supported), and update its `version` to `7.2`:

        "liferayTheme": {
        	...
        	"templateLanguage": "ftl",
        	"version": "7.2"
        },

4.  Update the `liferay-theme-deps-7-0` dependency to `liferay-theme-deps-7.2`
    with the version below, and add the `liferay-theme-tasks` dependency as
    shown in the example configuration below: 
<!--
This is automatically done when you run gulp upgrade, but if manually doing this, you should update it I suppose
-->
        "devDependencies": {
        	"gulp": "3.9.1",
        	"liferay-theme-tasks": "8.0.0-alpha.6",
        	"liferay-theme-deps-7.2": "8.0.0-alpha.6"
        },

Your theme's Liferay version references are updated for @product-ver@.

## Related Topics

[Updating CSS Code](/docs/7-1/tutorials/-/knowledge_base/t/updating-css-code)

[Developing Themes](/docs/7-1/tutorials/-/knowledge_base/t/developing-themes)
