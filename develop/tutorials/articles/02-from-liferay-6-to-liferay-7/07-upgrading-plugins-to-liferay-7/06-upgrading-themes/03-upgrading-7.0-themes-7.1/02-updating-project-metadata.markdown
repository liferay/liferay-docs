# Updating Project Metadata [](id=updating-project-metadata)

If your theme uses the 
[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes), 
the 
[gulp upgrade task](/develop/tutorials/-/knowledge_base/7-1/upgrading-themes-created-with-the-theme-generator) 
automatically updates your theme's metadata as part of the upgrade process. If 
you're developing your theme in an environment other than the themes generator, 
follow the steps below to update your theme's metadata manually:

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

3.  If your theme uses the Liferay Theme Generator, open the `package.json` file 
    and update the file's Liferay version references to `7.1`. Update the 
    `liferayTheme`'s `templateLanguage` to `ftl` (since Velocity theme templates 
    are no longer supported), and update its `version` to `7.1`:

        "liferayTheme": {
        	...
        	"templateLanguage": "ftl",
        	"version": "7.1"
        },

4.  Update the `liferay-theme-deps-7-0` dependency to `liferay-theme-deps-7.1`
    with the version below, and add the `liferay-theme-tasks` dependency as
    shown in the example configuration below: 

        "devDependencies": {
        	"gulp": "3.9.1",
        	"liferay-theme-tasks": "8.0.0-alpha.6",
        	"liferay-theme-deps-7.1": "8.0.0-alpha.6"
        },

Your theme's Liferay version references are updated for @product-ver@.

## Related Topics [](id=related-topics)

[Updating CSS Code](/develop/tutorials/-/knowledge_base/7-1/updating-css-code)

[Developing Themes](/develop/tutorials/-/knowledge_base/7-1/developing-themes)
