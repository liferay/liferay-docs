# Updating 6.2 Project Metadata [](id=updating-6-2-project-metadata)

If your theme uses the 
[Liferay JS Toolkit's theme development tools](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
the 
[Gulp `upgrade` task](/develop/tutorials/-/knowledge_base/7-1/running-the-upgrade-task-for-6-2-themes) 
automatically updates some of your theme's metadata as part of the upgrade 
process. Follow the steps below to update your theme's metadata manually:

1.  Open your theme's `liferay-plugin-package.properties` file and change the 
    `liferay-versions` property value to `7.1.0+`: 

        liferay-versions=7.1.0+

2.  Open the `liferay-look-and-feel.xml` file and specify `7.1.0+` as the 
    compatibility version: 

        <look-and-feel>
                <compatibility>
                        <version>7.1.0+</version>
                </compatibility>

                ...

        </look-and-feel>
 
3.  While you're updating `liferay-look-and-feel.xml`, enable your theme to use 
    [Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/adding-portlet-decorators-to-a-theme).

4.  If your theme uses the Liferay JS Toolkit's theme development tools, the 
    [Gulp `upgrade` task](/develop/tutorials/-/knowledge_base/7-1/running-the-upgrade-task-for-6-2-themes) 
    updates the `package.json` file's Liferay version references to `7.1`, and 
    it updates the `liferayTheme`'s `templateLanguage` to `ftl` 
    (since Velocity theme templates are no longer supported):

        "liferayTheme": {
        	...
        	"templateLanguage": "ftl",
        	"version": "7.1"
        },

Your theme's Liferay version references are updated for @product-ver@. 

## Related Topics [](id=related-topics)

[Updating 6.2 CSS Code](/develop/tutorials/-/knowledge_base/7-1/updating-6-2-css-code)

[Developing Themes](/develop/tutorials/-/knowledge_base/7-1/developing-themes)
