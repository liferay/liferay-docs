---
header-id: updating-your-themes-app-server
---

# Updating Your Theme's App Server

[TOC levels=1-4]

Follow these steps to update the configuration for your theme's app server with 
the Init task. Note that this task only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/developer/reference/-/knowledge_base/7-2/installing-the-theme-generator-and-creating-a-theme).

1.  Navigate to your theme's root folder and run `gulp init`.

    ![Figure 1: Run the `gulp init` task to update your app server configuration.](../../../../images/theme-dev-server-configuration-gulp-init.png)

2.  Enter the updated path to your app server and site.

3.  Your theme's `liferay-theme.json` file contains the updated server 
    configuration information:

    ```json
    {
      "LiferayTheme": {
        "deploymentStrategy": "LocalAppServer",
        "appServerPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\liferay-ce-portal-tomcat-7.2.0\\liferay-ce-portal-7.2.0\\tomcat-9.0.10",
        "deployPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\liferay-ce-portal-tomcat-7.2.0\\liferay-ce-portal-7.2.0\\deploy",
        "url": "http://localhost:8080",
        "appServerPathPlugin": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\liferay-ce-portal-tomcat-7.2.0\\liferay-ce-portal-7.2.0\\tomcat-9.0.10\\webapps\\my-new72theme-theme",
        "deployed": true,
        "pluginName": "my-new72theme-theme"
      }
    }
    ```

Awesome! Now you can 
[deploy your theme](/developer/frameworks/-/knowledge_base/7-2/deploying-and-applying-your-theme) 
to the proper server. 

## Related Topics

- [Automatically Deploying Theme Changes](/developer/frameworks/-/knowledge_base/7-2/automatically-deploying-theme-changes)
- [Changing Your Base Theme](/developer/frameworks/-/knowledge_base/7-2/changing-your-base-theme)
- [Listing Your Theme's Extensions](/developer/frameworks/-/knowledge_base/7-2/listing-your-themes-extensions)
