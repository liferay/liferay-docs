# Upgrading a Struts 1 Portlet

Struts is a stable, widely adopted framework that implements the Model View
Controller (MVC) design pattern. If you have a Struts portlet for previous
versions of Liferay Portal, you can upgrade it to @product-ver@.

Upgrading Struts portlets to @product-ver@ is easier than you might think.
@product@ lets you continue working with Struts portlets as Java EE web
applications.

This tutorial demonstrates how to upgrade a portlet that uses the Struts 1
Framework.

Here's a sample Struts portlet's folder structure with file/folder descriptions:

-   `sample-struts-portlet`
    -   `docroot/`
        -   `html/portlet/sample_struts_portlet/` &rarr; JSPs
        -   `WEB-INF/`
            -   `lib/` &rarr; Required third-party libraries unavailable in the @product@ system
            -   `src/`
                -   `com/liferay/samplestruts/model/` &rarr; Model classes
                -   `com/liferay/samplestruts/servlet/` &rarr; Test servlet and servlet context listener
                -   `com/liferay/samplestruts/struts/`
                    -   `action/` &rarr; `Action` classes that return View pages to the client
                    -   `form/` &rarr; `ActionForm` classes for model interaction
                    -   `render/` &rarr; `Action` classes that present additional pages and handle input
                    -   `SampleException.java` &rarr; Exception class
                -   `content/test/` &rarr; Resource bundles
                -   `META-INF/` &rarr; Javadoc
            -   `tld/` &rarr; Tag library definitions
            -   `liferay-display.xml` &rarr; Sets the application category
            -   `liferay-plugin-package.properties` &rarr; Sets metadata and portal dependencies
            -   `liferay-portlet.xml` &rarr; Maps descriptive role names to roles 
            -   `liferay-releng.properties` &rarr; (internal) Release properties
            -   `portlet.xml` &rarr; Defines the portlet and its initialization parameters and security roles
            -   `struts-config.xml` &rarr; Struts configuration
            -   `tiles-defs.xml` &rarr; Struts Tile definitions
            -   `validation.xml` &rarr; Defines form inputs for validation
            -   `validation-rules.xml` &rarr; Struts validation rules
            -   `web.xml` &rarr; Web application descriptor
    -   `build.xml` &rarr; Apache Ant build file

Upgrading a Struts 1 portlet involves these steps:

1.  Adapt the portlet to @product-ver@'s API using the Code Upgrade Planner.
    When running the planner's *Fix Upgrade Problems* step, many of the existing
    issues are autocorrected. For remaining issues, the planner identifies code
    affected by the new API and ways to adapt it.

2.  [Resolve its dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)

You've resolved the Sample Struts portlet's dependencies. It's ready to deploy. 

| **Important**: Setting Portal property `jsp.page.context.force.get.attribute` 
| (described in the
| [JSP section](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#JSP)) 
| to `true` (default) forces calls to
| `com.liferay.taglib.servlet.PageContextWrapper#findAttribute(String)` to use
| `getAttribute(String)`. Although this improves performance by avoiding
| unnecessary fall-backs, it can cause attribute lookup problems in Struts
| portlets. To use Struts portlets in your sites, makes sure to set the Portal
| property `jsp.page.context.force.get.attribute` to `false` in a file
| `[Liferay-Home]/portal-ext.properties`. 
| 
| ```properties
| jsp.page.context.force.get.attribute=false
| ```

On [deploying](/docs/reference/7-2/-/knowledge_base/r/deploying-a-project) a
Struts portlet Web Application aRchive (WAR), @product@'s Web Application Bundle
(WAB) Generator creates an OSGi module (bundle) for the portlet and installs it
to Liferay's OSGi framework. The server prints messages indicating the following
portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

The Struts portlet is now available on your @product@ instance. The Struts
portlet behaves just as it did on previous versions on your @product-ver@ site.

Congratulations on upgrading your Struts portlet to @product-ver@!
