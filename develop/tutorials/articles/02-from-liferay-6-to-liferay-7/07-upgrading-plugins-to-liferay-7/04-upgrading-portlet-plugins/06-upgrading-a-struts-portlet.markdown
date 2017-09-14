# Upgrading a Struts Portlet [](id=upgrading-a-struts-portlet)

Struts is a stable, widely adopted framework that implements the Model View
Controller (MVC) design pattern. If you have a Struts portlet for Liferay Portal
6.2,  you can upgrade it to @product-ver@.

Upgrading Struts portlets to @product-ver@ is easier than you might think.
@product@ lets you continue working with Struts portlets as Java EE web
applications. On deploying a Struts portlet Web Application aRchive (WAR),
@product@'s Web Application Bundle (WAB) Generator creates an OSGi module
(bundle) for the portlet and installs it to Liferay's OSGi framework. The Struts
portlet behaves just as it did in 6.2 on your @product-ver@ site.

This tutorial demonstrates how to upgrade a portlet that uses the Struts 1
Framework and refers to Liferay's Sample Struts portlet (Sample Struts) as
an example. Sample Struts uses several Struts features to show page
navigation, `Action` and `ActionForm` Controller classes, exceptions, and
more.

![Figure 1: The Sample Struts portlet's charts compare fictitious soft drink survey results.](../../../../images/upgrading-struts-portlets.png)

Here are the sample portlet's characteristics:

-   Model class (`Book`)
-   View comprised of JSPs that leverage Struts tag libraries
-   `Action` classes (Controllers) for handling requests and responses
-   `ActionForm` classes for interacting with models and forwarding requests to
    `Action`s
-   Visual component reuse with Tiles 
-   Internationalization using Action Messages and resource bundles
-   Form validation using the Validator Framework
-   Error management through Action Errors

You can follow this tutorial to upgrade your Struts portlet. Along the way, you
can examine the Sample Struts portlet source code from before and after its
upgrade:

-   [Liferay Portal 6.2 Sample Struts portlet code](https://dev.liferay.com/documents/10184/656312/sample-struts-portlet-pre-7-0-upgrade.zip)

-   [@product-ver@ Sample Struts portlet code](https://dev.liferay.com/documents/10184/656312/sample-struts-portlet-post-7-0-upgrade.zip)

Here's the Sample Struts portlet's folder structure:

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

Upgrading a Struts portlet involves these steps:

1.  [Adapt the code to @product-ver@'s API](#adapting-the-code-to-liferay-portal-7-0s-api)

2.  [Resolve dependencies](#resolving-dependencies)

## Adapting the code to Liferay Portal 7.0's API [](id=adapting-the-code-to-liferay-portal-7-0s-api)

Liferay's 
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
identifies code affected by the new API, explains the API changes and how to
adapt to them, and in many cases, provides options for adapting the code
automatically.

Adapting the Sample Struts portlet's code is straightforward. Resolving its
dependencies is more involved. 

## Resolving dependencies [](id=resolving-dependencies)

The Liferay Portal 6.2 Sample Struts portlet depends on Liferay Portal to
provide required third-party libraries and tag library definitions (TLDs). The
`portal-dependency-jars` and `portal-dependency-tlds` properties in the
portlet's `liferay-plugin-package.properties` specifies them:

    portal-dependency-jars=\
        antlr2.jar,\
        commons-beanutils.jar,\
        commons-collections.jar,\
        commons-digester.jar,\
        commons-fileupload.jar,\
        commons-io.jar,\
        commons-lang.jar,\
        commons-validator.jar,\
        jcommon.jar,\
        jfreechart.jar,\
        oro.jar,\
        portals-bridges.jar,\
        struts.jar

    portal-dependency-tlds=\
        struts-bean.tld,\
        struts-bean-el.tld,\
        struts-html.tld,\
        struts-html-el.tld,\
        struts-logic.tld,\
        struts-logic-el.tld,\
        struts-nested.tld,\
        struts-tiles.tld,\
        struts-tiles-el.tld

Resolving the tag libraries is easy.

### Resolving Tag Library Definitions [](id=resolving-tag-library-definitions)

@product-ver@ continues to provide many of the same TLDs Liferay Portal 6.2 provided. 

If the @product-ver@ application's `WEB-INF/tld` folder contains a TLD you need,
add it to your portlet's `portal-dependency-tlds` property in the
`liferay-plugin-package.properties` file. If the folder doesn't contain the TLD,
find the TLD on the web, download it, and add it to your portlet's `WEB-INF/tld`
folder.

### Resolving Third-Party Libraries [](id=resolving-third-party-libraries)

Third-party libraries listed as `portal-dependency-jars` in a 6.x portlet's
`liferay-plugin-package.properties` file might not be provided by @product-ver@.
Providing fewer libraries streamlines @product@. @product@ has replaced some of
its libraries with newer ones.

@product-ver@ exposes (exports) Java packages instead of sharing JAR content
wholesale. If you need packages @product@ doesn't export, you can find and
download the artifact (JAR) that provides them and add it to your portlet's
`docroot/WEB-INF/lib` folder. 

Here are steps for resolving the Sample Struts portlet's Java package
dependencies:

1.  @product@ doesn't export Antlr packages. Replace the portlet's JAR file
    `antlr2.jar` with newer JAR `antlr.jar` from
    [Maven Central](https://search.maven.org/).

2.  @product@ doesn't export packages from `portals-bridges.jar`. Replace the
    portlet's JAR file `portals-bridges.jar` with these JARs from
    [Maven Central](https://search.maven.org/):

    - `portals-bridges-common.jar`
    - `portals-bridges-struts.jar`

3.  @product@ doesn't export packages from `struts.jar`. Replace the
    portlet's JAR file `struts.jar` with the following ones from
    [Maven Central](https://search.maven.org/):

    - `struts-taglib.jar`
    - `struts-tiles.jar`

4.  The `Import-Packages` heading in the `com.liferay.portal.bootstrap`
    module's
    [`system.packages.extra.bnd` file](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/core/portal-bootstrap/system.packages.extra.bnd)
    lists the following JAR files Sample Struts requires. Since the bootstrap
    module exports packages from these JARs, add their names to the
    `portal-dependency-jars` property in the portlet's
    `liferay-plugin-package.properties` file. :

    - `commons-beanutils.jar`
    - `commons-collections.jar`
    - `commons-lang.jar`

5.  Add all the rest of the JARs the Sample Struts portlet depends on to the
    portlet's `WEB-INF/lib` folder.

The following table summarizes the sample portlet's Java dependency resolution.

**Sample Struts Portlet's Dependency Resolution:**

JAR | [System exports the packages](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/core/portal-bootstrap/system.packages.extra.bnd) the<br>portletneeds from this JAR? | Resolution |
--- | ------------------------------------- | ---------- |
`antlr.jar` | No | add to `WEB-INF/lib`
`commons-beanutils.jar` | Yes | List in `portal-dependency-jars` |
`commons-collections.jar` | Yes | List in `portal-dependency-jars` |
`commons-digester.jar` | No | add to `WEB-INF/lib` |
`commons-fileupload.jar` | No | add to `WEB-INF/lib` |
`commons-io.jar` | No | add to `WEB-INF/lib` |
`commons-lang.jar` | Yes |  List in `portal-dependency-jars` |
`commons-validator.jar` | No |  Add to `WEB-INF/lib` |
`jcommon.jar` | No | Add to `WEB-INF/lib` |
`jfreechart.jar` | No | Add to `WEB-INF/lib` |
`oro.jar` | No | Add to `WEB-INF/lib` |
`portals-bridges-common.jar` | No | Add to `WEB-INF/lib` |
`portals-bridges-struts.jar` | No | Add to `WEB-INF/lib` |
`struts-core.jar` | No | Add to `WEB-INF/lib` |
`struts-extras.jar` | No | Add to `WEB-INF/lib` |
`struts-taglib.jar` | No | Add to `WEB-INF/lib` |
`struts-tiles.jar` | No | Add to `WEB-INF/lib` |

+$$$

Note: The official
[Sample Struts portlet](https://github.com/liferay/liferay-plugins/tree/master/portlets/sample-struts-portlet)
for @product-ver@ uses
[Apache Ant/Ivy](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)
to manage dependencies.

$$$

For more details on resolving dependencies, see the tutorial
[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies).

You've resolved the Sample Struts portlet's dependencies. It's ready to deploy. 

+$$$

**Important**: Setting Portal property `jsp.page.context.force.get.attribute` 
(described in the
[JSP section](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html#JSP)) 
to `true` (default) forces calls to `com.liferay.taglib.servlet.
PageContextWrapper#findAttribute(String)` to use `getAttribute(String)`.
Although this improves performance by avoiding unnecessary fall-backs, it can
cause attribute lookup problems in Struts portlets. To use Struts portlets in
your sites, makes sure to set the Portal property
`jsp.page.context.force.get.attribute` to `false` in a file
`[Liferay-Home]/portal-ext.properties`. 

    jsp.page.context.force.get.attribute=false

$$$

Deploy the Struts portlet as you normally would. The server prints messages
indicating the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

Deploying the Sample Struts portlet produces these messages:


    00:15:20,344 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:252] Processing sample-struts-portlet-7.0.0.1.war
    00:15:26,871 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][BaseAutoDeployListener:42] Copying portlets for C:\portals\liferay-dxp-digital-enterprise-7.0-sp1\tomcat-8.0.32\temp\20170727241526847GURDCOLU\sample-struts-portlet-7.0.0.1.war
    00:15:27,282 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][BaseDeployer:863] Deploying sample-struts-portlet-7.0.0.1.war
    00:15:29,627 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][BaseAutoDeployListener:50] Portlets for C:\portals\liferay-dxp-digital-enterprise-7.0-sp1\tomcat-8.0.32\temp\20170727241526847GURDCOLU\sample-struts-portlet-7.0.0.1.war copied successfully
    00:15:29,644 WARN  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][WabProcessor:564] The property "portal-dependency-jars" is deprecated. Specified JARs may not be included in the class path.
    00:15:33,123 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][BundleStartStopLogger:35] STARTED sample-struts-portlet_7.0.0.1 [1230]
    00:15:34,063 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][HotDeployImpl:226] Deploying sample-struts-portlet from queue
    00:15:34,065 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][PluginPackageUtil:1007] Reading plugin package for sample-struts-portlet
    00:15:34,106 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][PortletHotDeployListener:201] Registering portlets for sample-struts-portlet
    00:15:34,424 INFO  [fileinstall-C:/portals/liferay-dxp-digital-enterprise-7.0-sp1/osgi/war][PortletHotDeployListener:313] 1 portlet for sample-struts-portlet is available for use

The Struts portlet is available on your @product@ instance. 

Congratulations on upgrading your Struts portlet to @product-ver@!

## Related Topics [](id=related-topics)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)

[Using Dependency Management Tools](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)
