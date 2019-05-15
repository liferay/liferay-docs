# Updating liferay-plugin-package.properties

Follow the steps in this section to upgrade Plugins SDK themes. Skip to the next 
section for all other themes. 

1.  Open the `src\WEB-INF\liferay-plugin-package.properties` file and remove the 
    `required-deployment-contexts` property. This is no longer needed since the 
    Resources Importer is bundled with @product@.

2.  The group model class's fully-qualified class name has changed. Replace the 
    `resources-importer-target-class-name` property's value with the updated one 
    below:

        com.liferay.portal.kernel.model.Group

Now that your `liferay-plugin-package.properties` is updated, you can update 
your theme's web content.