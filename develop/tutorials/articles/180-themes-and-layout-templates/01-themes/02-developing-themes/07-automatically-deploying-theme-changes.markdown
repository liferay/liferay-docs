# Automatically Deploying Theme Changes [](id=automatically-deploying-theme-changes)

You may have noticed that you have to deploy your theme manually each time you 
make a change. This can become tedious during the development process. The 
`gulp watch` task lets you see the changes you make to your theme without 
requiring a full redeploy.

Follow these steps to deploy changes to your theme automatically: 

1.  Enable
    [Developer Mode](/develop/tutorials/-/knowledge_base/7-1/using-developer-mode-with-themes)
    in your server. Without this enabled, the gulp watch task **will not work**.

2.  Navigate to your theme's root folder and run `gulp watch`. This sets up a 
    proxy for your app server and opens it in a new window in the browser. It 
    also provides an IP address for you to view your app server across all 
    devices connected to the local network. The browser is synced across all 
    devices that use the given IP address. 

    ![Figure 1: Run the `gulp watch` task to automatically deploy any changes to your theme.](../../../../images/theme-dev-watching-themes-gulp-watch-startup.png)

    You can verify that the watch task is running by checking that the 
    `webBundleDir` property is present in your theme's `liferay-theme.json` 
    file. It should have the value `watching`:

        {
          "LiferayTheme": {
            "appServerPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\tomcat-8.0.32",
            "deployPath": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1-master-bundle\\bundles\\deploy",
            "url": "http://localhost:8080",
            "appServerPathPlugin": "C:\\Users\\liferay\\Desktop\\projects\\themes\\7-1-themes\\my-liferay-theme\\.web_bundle_build",
            "deployed": true,
            "pluginName": "my-liferay-theme",
            "webBundleDir": "watching"
          }
        }

3.  Make a change to your theme and save the file. The updated files are built, 
    compiled, and copied directly to your app server. CSS changes are deployed 
    live, so no page reload is needed. For JS and template changes, **you must** 
    reload the browser to see the changes.

    ![Figure 2: The watch task notifies you that the changes are deployed.](../../../../images/theme-dev-watching-themes-gulp-watch-auto-deploy.png)

## Related Topics [](id=related-topics)

[Configuring Your Theme's App Server](/develop/tutorials/-/knowledge_base/7-1/configuring-your-themes-app-server)

[Copying an Existing Theme's Files](/develop/tutorials/-/knowledge_base/7-1/copying-an-existing-themes-files)

[Deploying Themes](/develop/tutorials/-/knowledge_base/7-1/deploying-themes)
