---
header-id: configuring-your-themes-app-server
---

# Configuring Your Theme's App Server

[TOC levels=1-4]

When your theme was first created with the 
[Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes), 
you had to specify the app server's location. This was done with the `gulp init` 
task. Your theme uses this information to deploy to the proper server. If your 
app server or site changes during development, you can update their 
configuration information by manually running the `gulp init` task.

| **Note:** Gulp is included as a local dependency in generated themes, so you
| are not required to install it. It can be accessed by running
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's
| root folder.

Follow these steps:

1.  Navigate to your theme's root folder and run `gulp init`.

    ![Figure 1: Run the `gulp init` task to update your app server configuration.](../../../../images/theme-dev-server-configuration-gulp-init.png)

2.  Enter the path to your app server and site.

    ![Figure 2: You can also run the `gulp init` task to update your site's URL.](../../../../images/theme-dev-server-configuration-gulp-init-config.png)

3.  Your theme's `liferay-theme.json` file contains the updated server 
    configuration information:
    
        {
          "LiferayTheme": {
            "appServerPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\tomcat-8.0.32",
            "deployPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\deploy",
            "url": "http://localhost:8080",
            "appServerPathPlugin": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\tomcat-8.0.32\\webapps\\my-liferay-theme",
            "deployed": false,
            "pluginName": "my-liferay-theme"
          }
        }

## Related Topics

[Automatically Deploying Theme Changes](/docs/7-1/tutorials/-/knowledge_base/t/automatically-deploying-theme-changes)

[Changing Your Base Theme](/docs/7-1/tutorials/-/knowledge_base/t/changing-your-base-theme)

[Listing Your Theme's Extensions](/docs/7-1/tutorials/-/knowledge_base/t/listing-your-themes-extensions)
