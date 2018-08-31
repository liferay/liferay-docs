# Summary [](id=summary)

Congratulations! You've learned how to build a theme from start to finish. So
what's next? Now that you've created a theme, you can publish it to Liferay's
[Marketplace](https://www.liferay.com/marketplace) for others to download. You
can read about Marketplace and the requirements for publishing in our 
[Publishing Your App](/distribute/how-to-publish)
guide.

Before you publish your theme to Marketplace make sure you update your 
`liferay-plugin-package.properties` file for the Resources Importer so it can 
run properly in a production environment. Follow these steps to configure your 
`liferay-plugin-package.properties` file:

1.  Open the `liferay-plugin-package.properties` file in the `WEB-INF`
    folder of the theme and set the developer mode property to `false`.
    
        resources-importer-developer-mode-enabled=false
        
    The theme resources will overwrite the existing portal resources only on 
    initial deployment now. Now that developer mode is disabled, you'll need to
    remove one additional property.   

2. Uncomment the `required-deployment-contexts`:

        required-deployment-contexts=\
            resources-importer-web

3.  Remove the following code from the file:

        resources-importer-target-class-name=com.liferay.portal.model.Group
 
    This ensures that the resources will be imported into the site template by 
    default.
 
There you have it! As you can see, theme development is a multi-headed beast,
but with careful planning and a thought out process you can tame it and develop 
a well designed theme. 

