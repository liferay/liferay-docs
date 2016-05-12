# Defining liferay-plugin-package.properties [](id=defining-liferay-plugin-package-properties)

In this section, you'll configure the `liferay-plugin-package.properties` file
for the Resources Importer.

1.  Open the `liferay-plugin-package.properties` file in the `WEB-INF`
    folder of the theme.
   
    By default, developer mode is enabled with the following line of code: 
    `resources-importer-developer-mode-enabled=true`. This ensures that 
    theme resources overwrite the existing portal resources on every
    deployment. You should leave this property enabled during development of the
    theme, but it should never be on in production. Since the resources for the
    theme will be imported directly into the site, and not a site template, you
    need to add two additional properties.
 
2.  Add the following code to the bottom of the file:

        resources-importer-target-class-name=com.liferay.portal.model.Group
        resources-importer-target-value=Guest
 
    The first property sets the type of portal entity to write the resources to,
    which is the site in this case. The second property sets the name of the 
    portal entity to write the resources to, which is set to the Guest group in 
    this case, so the resources will be visible on the public pages. It's 
    important to note that you should never set the 
    `resources-importer-target-class-name=com.liferay.portal.model.Group`
    property in a production environment. Instead you should remove the
    property, which will import the resources to the site template.
 
3.  Uncomment the following code and save the file:

        required-deployment-contexts=\
            resources-importer-web

    This requires the resources-importer-web plugin to be installed in order for 
    the theme to be deployed. If the *resources-importer-web* is not installed, 
    the theme will be placed on hold until the plugin is installed. There you 
    have it! All of the resources are ready to be deployed with the theme.

4.  Redeploy the Lunar Resort theme and refresh the browser to see the changes.

Your theme should look like the image below:

![Figure 1: The Resources Importer adds pages and content to your site.](../../images/resources-importer-fin-theme.png)

Now that your theme has these initial pages and content, you can disable the
Resources Importer. If you don't do this, the pages, web content, and documents 
will be reimported unnecessarily and asset reference issues can occur when you 
redeploy your theme. To disable the Resources Importer follow these steps: 

1.  Comment out the following properties from the
    `liferay-plugin-package.properties` file:

        required-deployment-contexts=\
            resources-importer-web

        resources-importer-target-class-name=com.liferay.portal.model.Group
        resources-importer-target-value=Guest

Your theme project is ready for more development. 

As you can see the Resources Importer is not too complicated once you break down
the individual pieces, and it serves as an effective way to stage your theme 
with content and pages. In the next portion of the Learning Path you'll learn
how to style your theme using CSS.
