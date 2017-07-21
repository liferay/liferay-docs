# Upgrading a GenericPortlet [](id=upgrading-a-genericportlet)

It's common to create portlets that extend `javax.portlet.GenericPortlet`. After
all, `GenericPortlet` provides a default `javax.portlet.Portlet` interface
implementation. Upgrading a `GenericPortlet` is straightforward and takes only
two steps: 

1.  Adapt the portlet to @product-ver@'s API using the Code Upgrade Tool.

2.  Resolve its dependencies. 

This tutorial demonstrates upgrading a Liferay Plugins SDK 6.2 sample portlet
called *Sample DAO* (project `sample-dao-portlet`). 

![Figure 1: The `sample-dao-portlet` lets users manage food items.](../../../../images/upgrading-portlets-sample-dao-portlet.png)

The sample portlet lets users view, add, edit, and delete food items from a
listing. For reference, you can download the
[pre-upgraded portlet code](https://dev.liferay.com/documents/10184/656312/sample-dao-portlet-pre-7-0-upgrade.zip)
and the
[upgraded code](https://dev.liferay.com/documents/10184/656312/sample-dao-portlet-post-7-0-upgrade.zip). 

The sample portlet has the following characteristics:
 
-   Extends `GenericPortlet`
-   View layer implemented by JSPs 
-   Persists models using the Data Access Object (DAO) design pattern
-   Specifies database connection information in a properties file
-   Manages dependencies via Ant/Ivy
-   Developed in a Liferay Plugins SDK 6.2

The portlet uses a traditional Plugins SDK portlet project folder structure. 

![Figure 2: The `sample-dao-portlet` project uses a typical Plugins SDK portlet folder structure](../../../../images/upgrading-a-genericportlet-folder-structure.png)

Upgrading most `GenericPortlet` portlets involves these steps:

1.  [Adapt the code to @product-ver@'s API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
2.  [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)

Since the sample portlet's dependencies haven't changed, upgrading it involves
only adapting the code to @product-ver@'s API. The
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
facilitates updating the code and resolving compilation issues quickly. 

+$$$

**Note**: Refer to tutorial
[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)
if you need to adapt to dependency changes. 

$$$

You deploy a `GenericPortlet` to @product-ver@ in the same way you deploy to
Portal 6.x. When the plugin WAR file lands in the `[Liferay_Home]/deploy`
folder, @product@'s Plugin Compatibility Layer converts the WAR to a Web
Application Bundle (WAB) and installs the portlet as a WAB to @product@'s OSGi
runtime.

On deploying an upgraded portlet, the server prints messages that indicate the
following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

Deploying the sample portlet produces messages like these:

    20:57:02,571 INFO ... [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:252] Processing sample-dao-portlet-7.0.0.1.war
    ...
    20:57:12,639 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][BundleStartStopLogger:35] STARTED sample-dao-portlet_7.0.0.1 [996]
    ...
    20:57:13,480 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][PortletHotDeployListener:313] 1 portlet for sample-dao-portlet is available for use 

The portlet is now available on @product@.

You've learned how to upgrade and deploy a portlet that extends
`GenericPortlet`. You adapt the code, resolve dependencies, and deploy the
portlet as you always have. It's just that easy!

**Related Topics**

[Migrating Plugins SDK Projects to Workspace and Gradle](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)

[Migrating Data Upgrade Processes](/develop/tutorials/-/knowledge_base/7-0/optimizing-app-upgrade-processes)
