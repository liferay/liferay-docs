# Upgrading a Servlet-based Portlet [](id=upgrading-a-servlet-based-portlet)

This tutorial shows you how to upgrade servlet-based portlets. It refers to
code from before and after upgrading a sample servlet-based portlet called
*Sample JSON* (project `sample-json-portlet`). The portlet shows a *Click me*
link. When users click the link, the Liferay logo appears. 

![Figure 1: The Sample JSON portlet displays text stating *Click me* that you can click to initiate an action.](../../../../images/upgrading-portlets-sample-servlet-json-initial-view.png)

To get the most from this tutorial, download and refer to the 
[original sample portlet source code](https://dev.liferay.com/documents/10184/656312/sample-json-portlet-pre-7-0-upgrade.zip)
and the 
[upgraded source code](https://dev.liferay.com/documents/10184/1608802/sample-json-portlet-post-7-1-upgrade.zip).

Here are the sample portlet's characteristics:

-   Processes requests using a servlet that extends `javax.servlet.HttpServlet`
-   View layer implemented by JSPs
-   Processes data using JSON objects 
-   Relies on manual dependency management
-   Depends on third-party libraries that Liferay Portal 6.2 provides 
-   Embeds additional dependencies in its `WEB-INF/lib` folder
-   Developed in a Liferay Plugins SDK 6.2

Follow these steps to upgrade a servlet-based portlet: 

1.  [Adapt the code to @product-ver@'s API](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool).

2.  [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies).

The 
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
makes adapting a portlet's code straightforward, and it automates much of the
process. 

The sample portlet relied on Liferay Portal to provide several dependency JAR
files.  Here's the `portal-dependency-jars` property from the portlet's
`liferay-plugin-package.properties` file: 

    portal-dependency-jars=\
        dom4j.jar,\
        jabsorb.jar,\
        json-java.jar

Instructions for using packages that @product@ exports are found
[here](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies#using-packages-liferay-portal-exports).
@product-ver@'s core system exports the package this portlet needs from each of
the above dependency JARs. 

The upgraded sample portlet continues to specify these JARs in the
`portal-dependency-jars` property. They're made available to the portlet at
compile time. But to keep compile time packages from conflicting with the core
system's exported packages, the Liferay Plugins SDK 7.1 excludes the JARs from
the plugin WAR. 

Next, deploy your portlet as you always have.

The server prints messages that indicate the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

+$$$

Note: On deploying the sample upgraded portlet, the
[WAB processor](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
warns that the `portal-dependency-jars` property is deprecated.<br><br>

    21:40:25,347 WARN  [fileinstall-...][WabProcessor:564] The property "portal-dependency-jars" is deprecated. Specified JARs may not be included in the class path.

For running on @product-ver@, it's fine to specify the`portal-dependency-jars`
property per the
[instructions](resolving-a-plugins-dependencies#using-packages-liferay-portal-exports)
for using packages that @product@ exports. After upgrading, consider 
[using a dependency management tool](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)
in your project. This helps prepare it for future @product@ versions and
facilitates managing dependencies.

$$$

The portlet is installed to Liferay's OSGi runtime and is available to users.

![Figure 2: Clicking on the sample portlet's *Click me* link shows the Liferay logo.](../../../../images/upgrading-portlets-sample-servlet-json.png)

Congratulations! You've upgraded and deployed your servlet-based portlet to
@product-ver@. 

**Related Topics**

[Migrating Plugins SDK Projects to Workspace and Gradle](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)

[Migrating Data Upgrade Processes](/develop/tutorials/-/knowledge_base/7-1/optimizing-app-upgrade-processes)
