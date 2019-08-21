---
header-id: upgrading-a-servlet-based-portlet
---

# Upgrading a Servlet-based Portlet

[TOC levels=1-4]

This tutorial shows you how to upgrade servlet-based portlets. It refers to
code from before and after upgrading a sample servlet-based portlet called
*Sample JSON* (project `sample-json-portlet`). The portlet shows a *Click me*
link. When users click the link, the Liferay logo appears. 

![Figure 1: The Sample JSON portlet displays text stating *Click me* that you can click to initiate an action.](../../../../images/upgrading-portlets-sample-servlet-json-initial-view.png)

To get the most from this tutorial, download and refer to the 
[original sample portlet source code](https://portal.liferay.dev/documents/113763090/114000186/sample-json-portlet-pre-7-0-upgrade.zip)
and the 
[upgraded source code](https://portal.liferay.dev/documents/113763090/114000653/sample-json-portlet-post-7-1-upgrade.zip).

Here are the sample portlet's characteristics:

-   Processes requests using a servlet that extends `javax.servlet.HttpServlet`
-   View layer implemented by JSPs
-   Processes data using JSON objects 
-   Relies on manual dependency management
-   Depends on third-party libraries that Liferay Portal 6.2 provides 
-   Embeds additional dependencies in its `WEB-INF/lib` folder
-   Developed in a Liferay Plugins SDK 6.2

To upgrade a servlet-base Portlet, adapt your code to @product-ver@'s API using
the Liferay Upgrade Planner. When you ran the planner's *Fix Upgrade Problems*
step, many of the existing issues were autocorrected or flagged. For any
remaining errors, consult the
[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)
article.

Liferay's 
[Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)
identifies code affected by the new API, explains the API changes and how to
adapt to them, and in many cases, provides options for adapting the code
automatically. 

The sample portlet relied on Liferay Portal to provide several dependency JAR
files.  Here's the `portal-dependency-jars` property from the portlet's
`liferay-plugin-package.properties` file: 

    portal-dependency-jars=\
        dom4j.jar,\
        jabsorb.jar,\
        json-java.jar

Instructions for using packages that @product@ exports are found
[here](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-packages-portal-exports).
@product-ver@'s core system exports the package this portlet needs from each of
the above dependency JARs. 

The upgraded sample portlet continues to specify these JARs in the
`portal-dependency-jars` property. They're made available to the portlet at
compile time. But to keep compile time packages from conflicting with the core
system's exported packages, the Liferay Plugins SDK 7.0 excludes the JARs from
the plugin WAR. 

Next, deploy your portlet as you always have.

The server prints messages that indicate the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

| Note: On deploying the sample upgraded portlet, the
| [WAB processor](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)
| warns that the `portal-dependency-jars` property is deprecated.<br><br>
| 
|     21:40:25,347 WARN  [fileinstall-...][WabProcessor:564] The property "portal-dependency-jars" is deprecated. Specified JARs may not be included in the class path.
| 
| For running on @product-ver@, it's fine to specify the`portal-dependency-jars`
| property per the
| [instructions](resolving-a-plugins-dependencies#using-packages-liferay-portal-exports)
| for using packages that @product@ exports. After upgrading, consider
| [using a dependency management tool](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool)
| in your project. This helps prepare it for future @product@ versions and
| facilitates managing dependencies.

The portlet is installed to Liferay's OSGi runtime and is available to users.

![Figure 2: Clicking on the sample portlet's *Click me* link shows the Liferay logo.](../../../../images/upgrading-portlets-sample-servlet-json.png)

Congratulations! You've upgraded and deployed your servlet-based portlet to
@product-ver@. 

**Related Topics**

[Migrating Plugins SDK Projects to Workspace and Gradle](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool)

[Using the WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)

[Migrating Data Upgrade Processes](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-app-upgrade-processes)
