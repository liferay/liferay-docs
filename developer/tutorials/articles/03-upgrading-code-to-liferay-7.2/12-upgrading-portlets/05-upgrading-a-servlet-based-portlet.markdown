# Upgrading a Servlet-based Portlet

This tutorial shows you how to upgrade servlet-based portlets. It refers to
code from before and after upgrading a sample servlet-based portlet called
*Sample JSON* (project `sample-json-portlet`). The portlet shows a *Click me*
link. When users click the link, the Liferay logo appears. 

Follow these steps to upgrade a servlet-based portlet: 

1.  Adapt the portlet to @product-ver@'s API using the Code Upgrade Planner.
    When running the planner's *Fix Upgrade Problems* step, many of the existing
    issues are autocorrected. For remaining issues, the planner identifies code
    affected by the new API and ways to adapt it.

2.  [Resolve its dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)

3.  [Deploy it](/docs/reference/7-2/-/knowledge_base/r/deploying-a-project)

Some servlet-based portlets relied on Liferay Portal to provide several
dependency JAR files. Here's the `portal-dependency-jars` property from a sample
portlet's `liferay-plugin-package.properties` file: 

```properties
portal-dependency-jars=\
    dom4j.jar,\
    jabsorb.jar,\
    json-java.jar
```

Your upgraded sample portlet can continue to specify these JARs in the
`portal-dependency-jars` property. @product-ver@'s core system exports the
package this portlet needs from each of the above dependency JARs. They're made
available to the portlet at compile time.

| **Note:** Best practices for using packages that @product@ exports are found
| [here](/docs/customization/7-2/-/knowledge_base/c/configuring-dependencies).

Once you've deployed your portlet, the server prints messages that indicate the
following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

The portlet is installed to Liferay's OSGi runtime and is available to users.

Congratulations! You've upgraded and deployed your servlet-based portlet to
@product-ver@. 
