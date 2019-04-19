---
header-id: automatically-deploying-theme-changes
---

# Automatically Deploying Theme Changes

[TOC levels=1-4]

Follow these steps to automatically deploy your theme's changes with the Watch 
task. Note that this task only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/reference/installing-the-theme-generator-and-creating-a-theme). 

1.  Navigate to your theme's root folder and run `gulp watch`. This sets up a 
    proxy for your app server and opens it in a new window in the browser. 

    | **Note:** Live changes are only viewable on port `9080` 
    | (`http://localhost:9080`). Live changes **are not viewable** on your app 
    | server (e.g. `http://localhost:8080`).

    ![Figure 1: Run the `gulp watch` task to automatically deploy any changes to your theme.](../../../../images/theme-dev-watching-themes-gulp-watch-startup.png)

    You can verify that the watch task is running by checking that the 
    `webBundleDir` property is present in your theme's `liferay-theme.json` 
    file. It should have the value `watching`:


    ```json
    {
      "LiferayTheme": {
        ...
        "pluginName": "my-liferay-theme",
        "webBundleDir": "watching"
      }
    }
    ```

2.  Make a change to your theme and save the file. The updated files are built, 
    compiled, and copied directly to the proxy port. CSS changes are deployed 
    live, so no page reload is needed. 
    
3.  Once you're happy with the changes, 
    [re-deploy](/docs/7-2/frameworks/-/knowledge_base/frameworks/deploying-and-applying-themes) 
    your theme to apply the changes to your site on your app server.

    ![Figure 2: The watch task notifies you that the changes are deployed.](../../../../images/theme-dev-watching-themes-gulp-watch-auto-deploy.png)

## Related Topics

- [Configuring Your Theme's App Server](/docs/7-2/frameworks/-/knowledge_base/frameworks/configuring-your-themes-app-server)
- [Copying an Existing Theme's Files](/docs/7-2/frameworks/-/knowledge_base/frameworks/copying-an-existing-themes-files)
- [Deploying and Applying Themes](/docs/7-2/frameworks/-/knowledge_base/frameworks/deploying-and-applying-themes)
