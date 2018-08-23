# Upgrading a Liferay MVC Portlet [](id=upgrading-a-liferay-mvc-portlet)

Liferay's MVC Portlet framework is used extensively in Liferay's portlets and is
a popular choice for Liferay Portal 6.2 portlet developers. The
[`MVCPortlet` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
is a lightweight extension of `javax.portlet.GenericPortlet`. Its `init` method
saves you from writing a lot of boilerplate code. MVC portlets can upgraded
to @product-ver@ without a hitch. 

Upgrading a Liferay MVC Portlet involves these steps:

1.  [Adapt the code to @product-ver@'s API](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

2.  [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)

Liferay's 
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
identifies code affected by the new API, explains the API changes and how to
adapt to them, and in many cases, provides options for adapting the code
automatically. 

After you upgrade your portlet, deploy it the same way you always do. 

The server prints messages that indicate the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

You've upgraded and deployed your Liferay MVC Portlet on your @product-ver@
instance. Have fun showing off your upgraded portlet!

**Related Topics**

[Migrating Plugins SDK Projects to Workspace and Gradle](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)

[Migrating Data Upgrade Processes](/develop/tutorials/-/knowledge_base/7-1/optimizing-app-upgrade-processes)
