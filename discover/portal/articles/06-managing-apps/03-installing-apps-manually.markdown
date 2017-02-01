# Installing Apps Manually [](id=installing-apps-manually)

By default, apps you download in a running Liferay instance via Liferay 
Marketplace install automatically. But what if the app you want to install isn't 
on Marketplace? What if all you have is the app's file? In this case, you must 
install the app manually. This article shows you how to do this. You can use the 
procedures here to install any app. 

## Using the Control Panel to Install Apps [](id=using-the-control-panel-to-install-apps)

To install an app manually via your running Liferay instance, navigate to 
*Control Panel* &rarr; *Apps* &rarr; *App Manager*, and select *Upload* from the 
options button (![Options](../../images/icon-options.png)). In the Upload 
dialog, choose the app on your machine, and then click *Install*. When the 
install completes, close the dialog and you're ready to roll! 

![Figure 1: Installing an app via the Upload dialog in your Liferay instance's App Manager is very simple.](../../images/app-manager-plugin-upload.png)

Next, you'll learn how to install an app manually via your file system. 

## Using Your File System to Install Apps [](id=using-your-file-system-to-install-apps)

To install an app manually via the file system your Liferay instance is running 
on, put the app in the `[Liferay_Home]/deploy` folder. That's it. Liferay's hot 
deploy mechanism takes care of the rest. 

You might now be thinking, "Whoa there! What do you mean by 'the rest'? What 
exactly does Liferay do here? And what if my app server doesn't support hot 
deploy?" These are fantastic questions! When you put an app in the 
`[Liferay_Home]/deploy` folder, Liferay's OSGi container deploys the app to the 
appropriate subfolder in `[Liferay_Home]/osgi`. By default, the following 
subfolders are used for apps matching the indicated file type: 

- `marketplace`: Marketplace LPKG packages
- `modules`: OSGi modules
- `war`: WAR files

You can, however, change these subfolders by setting the properties 
`module.framework.base.dir` and `module.framework.auto.deploy.dirs` in a 
`portal-ext.properties` file. These properties define the `[Liferay_Home]/osgi` 
folder and its hot deploy subfolders, respectively. The default settings for 
these properties in 
[Liferay's `portal.properties` file](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html) 
are as follows: 

    module.framework.base.dir=${liferay.home}/osgi

    module.framework.auto.deploy.dirs=\
        ${module.framework.base.dir}/configs,\
        ${module.framework.base.dir}/marketplace,\
        ${module.framework.base.dir}/modules,\
        ${module.framework.base.dir}/war

Note that the `configs` subfolder isn't for apps: it's for configuration files 
[imported from other Liferay instances](/discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations). 

But what happens if your app server doesn't support hot deploy? No problem! 
Liferay's module framework (OSGi) enables hot deploy. Any app server running 
Liferay therefore also supports Liferay's hot deploy mechanism. 

## Manually Deploying an LPKG App [](id=manually-deploying-an-lpkg-app)

When manually installing an LPKG app, the installation may hang with a server 
log message like this: 

    14:00:15,789 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:252] Processing Liferay Push 2.1.0.lpkg

This happens when LPKG apps have the `restart-required=true` property in their 
`liferay-marketplace.properties` file (inside the LPKG file). This property 
setting specifies that a server restart is required to complete the 
installation. 
