---
header-id: deploying-your-theme
---

# Deploying Your Theme

[TOC levels=1-4]

To deploy your theme to your app server, run the `gulp deploy` task. The `gulp 
deploy` task 
[builds your theme's files](/docs/7-1/tutorials/-/knowledge_base/t/building-your-themes-files), 
and deploys the generated WAR file to the 
[app server you configured](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server) 
when you 
[created the theme](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes).

| **Note:** If you're running the
| [Felix Gogo shell](/docs/7-0/reference/-/knowledge_base/r/using-the-felix-gogo-shell),
| you can also deploy your theme using the `gulp deploy:gogo` command.

| **Note:** Gulp is included as a local dependency in generated themes, so you
| are not required to install it. It can be accessed by running
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's
| root folder.

Follow these steps to deploy your theme:

1.  Navigate to your theme's root folder and run `gulp deploy`.

    ![Figure 1: Run the `gulp deploy` task to build your theme's files and deploy it to your app server.](../../../../images/theme-dev-deploying-themes-gulp-deploy.png)

2.  Your server's log displays that the OSGi bundle is started.

    ![Figure 2: Your server's log notifies you when the theme's bundle has started.](../../../../images/theme-dev-deploying-themes-server-log.png)

3.  You can apply your theme through the *Navigation* &rarr; *Site Pages* 
    menu in the Control Menu. Select the *Configure* option for your site 
    pages, and click the *Change Current Theme* button to apply your theme. 

    ![Figure 3: Run the `gulp deploy` task to build your theme's files and deploy it to your app server.](../../../../images/theme-dev-deploying-themes-install-theme.png)

## Related Topics

[Automatically Deploying Theme Changes](/docs/7-1/tutorials/-/knowledge_base/t/automatically-deploying-theme-changes)

[Copying an Existing Theme's Files](/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files)

[Creating Reusable Pieces of Code for Your Themes](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)
