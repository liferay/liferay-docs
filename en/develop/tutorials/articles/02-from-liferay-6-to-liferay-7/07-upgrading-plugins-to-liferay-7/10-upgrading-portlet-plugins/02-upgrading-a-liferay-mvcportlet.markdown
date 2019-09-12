---
header-id: upgrading-a-liferay-mvc-portlet
---

# Upgrading a Liferay MVC Portlet

[TOC levels=1-4]

Liferay's MVC Portlet framework is used extensively in Liferay's portlets and is
a popular choice for Liferay Portal 6.2 portlet developers. The
[`MVCPortlet` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
is a lightweight extension of `javax.portlet.GenericPortlet`. Its `init` method
saves you from writing a lot of boilerplate code. MVC portlets can upgraded
to @product-ver@ without a hitch. 

To upgrade a Liferay MVC Portlet, adapt your code to @product-ver@'s API using
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

After you upgrade your portlet, deploy it the same way you always do. 

The server prints messages that indicate the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

You've upgraded and deployed your Liferay MVC Portlet on your @product-ver@
instance. Have fun showing off your upgraded portlet!

**Related Topics**

[Migrating Plugins SDK Projects to Workspace and Gradle](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool)

[Using the WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)

[Migrating Data Upgrade Processes](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-app-upgrade-processes)
