# Deploying Your Theme [](id=deploying-your-theme)

To deploy your theme to your app server, run the `gulp deploy` task. The `gulp 
deploy` task 
[builds your theme's files](/develop/tutorials/-/knowledge_base/7-1/building-your-themes-files), 
and deploys the generated WAR file to the 
[app server you configured](/develop/tutorials/-/knowledge_base/7-1/configuring-your-themes-appserver) 
when you 
[created the theme](/develop/tutorials/-/knowledge_base/7-1/creating-themes).

+$$$

**Note:** If you're running the 
[Felix Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell), 
you can also deploy your theme using the `gulp deploy:gogo` command.

$$$

Follow these steps to deploy your theme:

1.  Navigate to your theme's root folder and run `gulp deploy`.

    ![Figure 1: Run the `gulp deploy` task to build your theme's files and deploy it to your app server.](../../../../images/theme-dev-deploying-themes-gulp-deploy.png)

2.  Your server's log displays that the OSGi bundle is started.

    ![Figure 2: Your server's log notifies you when the theme's bundle has started.](../../../../images/theme-dev-deploying-themes-server-log.png)

3.  You can apply your theme through the *Navigation* &rarr; *Site Pages* 
    menu in the Control Menu. Select the *Configure* option for your site 
    pages, and click the *Change Current Theme* button to apply your theme. 

    ![Figure 3: Run the `gulp deploy` task to build your theme's files and deploy it to your app server.](../../../../images/theme-dev-deploying-themes-install-theme.png)

## Related Topics [](id=related-topics)

[Automatically Deploying Theme Changes](/develop/tutorials/-/knowledge_base/7-1/automatically-deploying-theme-changes)

[Copying an Existing Theme's Files](/develop/tutorials/-/knowledge_base/7-1/copying-an-existing-themes-files)

[Creating Reusable Pieces of Code for Your Themes](/develop/tutorials/-/knowledge_base/7-1/creating-reusable-pieces-of-code-for-your-themes)
