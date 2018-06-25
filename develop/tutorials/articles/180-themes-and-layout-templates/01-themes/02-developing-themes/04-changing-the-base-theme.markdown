# Changing Your Base Theme [](id=changing-your-base-theme)

Once your theme is 
[built](/develop/tutorials/-/knowledge_base/7-1/building-your-themes-files), 
you can use the `gulp extend` task to change your theme's base theme. 

Follow these steps to change your base theme:

1.  Navigate to your theme's root folder and run `gulp extend` and choose option 
    1 to change the base theme your theme extends. 

    ![Figure 1: Run the `gulp extend` task to change your base theme or install a themelet.](../../../../images/theme-dev-changing-base-themes-gulp-extend-base-theme.png)
    
2.  Choose which base theme you want to extend. By default, themes created with 
    the 
    [Liferay Theme Generator](https://github.com/liferay/generator-liferay-theme) 
    are based off of the 
    [styled theme](https://www.npmjs.com/package/liferay-theme-styled). You can 
    extend the styled or unstyled base theme, a globally installed theme, or a 
    theme published on the npm registry. 

    ![Figure 2: You can extend the styled or unstyled base theme, a globally installed theme, or a theme published to the npm registry.](../../../../images/theme-dev-changing-base-themes-gulp-extend-base-theme-choice.png)

3.  Your theme's `package.json` contains the updated base theme configuration :

        {
          ...
        	"liferayTheme": {
        		"baseTheme": "styled",
        		"screenshot": "",
        		"rubySass": false,
        		"templateLanguage": "ftl",
        		"version": "7.1"
        	},
          ...
        }
        
When you 
[build your theme's files](/develop/tutorials/-/knowledge_base/7-1/building-your-themes-files) 
or 
[deploy it](/develop/tutorials/-/knowledge_base/7-1/deploying-your-theme), 
your theme will inherit the updated base theme's files.

## Related Topics [](id=related-topics)

[Configuring Your Theme's App Server](/develop/tutorials/-/knowledge_base/7-1/configuring-your-themes-app-server)

[Deploying Themes](/develop/tutorials/-/knowledge_base/7-1/deploying-themes)

[Listing Your Theme's Extensions](/develop/tutorials/-/knowledge_base/7-1/listing-your-themes-extensions)
