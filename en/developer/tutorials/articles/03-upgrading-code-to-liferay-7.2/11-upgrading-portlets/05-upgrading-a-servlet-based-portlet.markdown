---
header-id: upgrading-a-servlet-based-portlet
---

# Upgrading a Servlet-based Portlet

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Portlets</p><p>Step 4 of 6</p>
</div>

This tutorial shows you how to upgrade servlet-based portlets. It refers to
code from before and after upgrading a sample servlet-based portlet called
*Sample JSON* (project `sample-json-portlet`). The portlet shows a *Click me*
link. When users click the link, the Liferay logo appears. 

Follow these steps to upgrade a servlet-based portlet: 

1.  Adapt the portlet to @product-ver@'s API using the Liferay Upgrade Planner.
    When running the planner's *Fix Upgrade Problems* step, many of the existing
    issues are autocorrected. For remaining issues, the planner identifies code
    affected by the new API and ways to adapt it.

2.  [Resolve its dependencies](/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies)

3.  [Deploy it](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project)

For an example upgrade scenario, consider this:

Some servlet-based portlets relied on Liferay Portal to provide several
dependency JAR files. Here's the `portal-dependency-jars` property from a sample
portlet's `liferay-plugin-package.properties` file: 

```properties
portal-dependency-jars=\
    dom4j.jar,\
    jabsorb.jar,\
    json-java.jar
```

This property is deprecated in @product-ver@ because importing and exporting
Java packages has replaced wholesale use of JARs. This means modules and WABs
can import packages without concerning themselves with JARs. @product@ exports
many third party packages for plugins to use. Best practices for using packages
that @product@ exports are found
[here](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies). For
more information on this specific scenario, see the
[Using Packages Liferay Portal Exports](/docs/tutorials/7-1/-/knowledge_base/t/resolving-a-plugins-dependencies#using-packages-liferay-portal-exports)
section.

Once you've deployed your portlet, the server prints messages that indicate the
following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

The portlet is installed to Liferay's OSGi runtime and is available to users.

Congratulations! You've upgraded and deployed your servlet-based portlet to
@product-ver@. 
