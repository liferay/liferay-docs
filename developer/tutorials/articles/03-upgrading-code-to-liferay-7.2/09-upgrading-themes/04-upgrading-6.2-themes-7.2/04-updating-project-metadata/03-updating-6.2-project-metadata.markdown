---
header-id: updating-6-2-project-metadata
---

# Updating 6.2 Project Metadata

[TOC levels=1-4]

If your theme uses the 
[Liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
the 
[Gulp `upgrade` task](/docs/7-2/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) 
automatically updates some of your theme's metadata as part of the upgrade 
process. Follow the steps below to update your theme's metadata manually:

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
 
3.  While you're updating `liferay-look-and-feel.xml`, enable your theme to use 
    [Portlet Decorators](/docs/7-2/tutorials/-/knowledge_base/t/adding-portlet-decorators-to-a-theme).
    <!--
    This is optional really. Should we require it?
    -->

4.  If your theme uses the Liferay JS Theme Toolkit, the 
    [Gulp `upgrade` task](/docs/7-2/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) 
    updates the `package.json` file's Liferay version references to `7.2`, and 
    it updates the `liferayTheme`'s `templateLanguage` to `ftl` 
    (since Velocity theme templates are no longer supported):

        "liferayTheme": {
        	...
        	"templateLanguage": "ftl",
        	"version": "7.2"
        },

Your theme's Liferay version references are updated for @product-ver@. 

## Related Topics

[Updating 6.2 CSS Code](/docs/7-2/tutorials/-/knowledge_base/t/updating-6-2-css-code)

[Developing Themes](/docs/7-2/tutorials/-/knowledge_base/t/developing-themes)
