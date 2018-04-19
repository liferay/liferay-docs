# Upgrading a Spring MVC portlet [](id=upgrading-a-spring-mvc-portlet)

The [Spring Portlet MVC framework](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/portlet.html)
facilitates injecting dependencies and implementing the Model View Controller
pattern in portlets. If you use this framework in a portlet for Liferay Portal
6.x, you can upgrade it to @product-ver@. 

This tutorial demonstrates upgrading a Spring MVC portlet called My Spring MVC
(project `my-spring-mvc-portlet`). It's a bare-bones portlet created from the
Plugins SDK's `spring_mvc` template. 

![Figure 1: My Spring MVC portlet shows its name and @product@'s information.](../../../../images/upgraded-spring-mvc-portlet.png)

To follow along, download and refer to the
[original source code](https://dev.liferay.com/documents/10184/1608802/my-spring-mvc-portlet-pre-7-0-upgrade.zip)
and the 
[upgraded source code](https://dev.liferay.com/documents/10184/1608802/my-spring-mvc-portlet-post-7-1-upgrade.zip).

The figure below shows the `my-spring-mvc-portlet` project.

![Figure 2: The `my-spring-mvc-portlet` project has traditional Liferay plugin files, Spring Portlet MVC application contexts (in `spring-context/`), and a controller class `MySpringMVCPortletviewController`. ](../../../../images/upgrading-spring-mvc-portlets-folder-structure.png)

These files have Spring-related content:

-   `view.jsp` &rarr; Shows the portlet's name and @product@'s release
    information.
-   `my-spring-mvc-portlet.xml` &rarr; @product@ uses this context file for the
    portlet.
-   `portlet-applications-context.xml` &rarr; Spring's
    `SpringContextLoaderListener` class uses this context file. 
-   `MySpringMVCPortletviewController` &rarr; Maps `VIEW` requests to the
    `view.jsp` and assigns @product@ release information to a model attribute.
-   `portlet.xml` &rarr; References context configuration file
    `my-spring-mvc-portlet.xml` and specifies a dispatcher for registered
    portlet request handlers.
-   `web.xml` &rarr; References context configuration file
    `portlet-application-context.xml` and specifies a `ViewRendererServlet` to
    convert portlet requests and responses to HTTP servlet requests and
    responses.

Here are the Spring MVC portlet upgrade steps:

1.  [Adapt the code to @product-ver@'s API](#adapt-the-code-to-liferay-api-changes)

2.  [Resolve dependencies](#resolve-dependencies)

## Adapt the code to @product-ver@'s API [](id=adapt-the-code-to-liferay-api-changes)

The
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
facilitates updating the code and resolving compilation issues quickly.

The Code Upgrade Tool detects if the value of the `liferay-versions` property in
your plugin's `liferay-plugin-package.properties` file needs updating, and it
provides an option to fix it automatically. This is the only code adaptation
required by `my-spring-mvc-portlet`. 

## Resolve Dependencies [](id=resolve-dependencies)

In Liferay Portal 6.2, `my-spring-mvc-portlet` leveraged Portal's JARs by
specifying them in the `liferay-plugin-package.properties` file's
`portal-dependency-jars` property. Since the property is deprecated in
@product-ver@, you should acquire dependencies using a dependency management
framework, such as Gradle, Maven, or Apache Ant/Ivy.

[Converting the sample portlet plugin from a traditional plugin to a Liferay Workspace web application](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications)
facilitated resolving its dependencies. 

Here's the updated `my-spring-mvc-portlet`'s `build.gradle` file:

    dependencies {
    	compileOnly group: 'aopalliance', name: 'aopalliance', version: '1.0'
    	compileOnly group: 'commons-logging', name: 'commons-logging', version: '1.2'
    	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
    	compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
    	compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    	compile group: 'org.jboss.arquillian.junit', name: 'arquillian-junit-container', version: '1.1.3.Final'
    	compile group: 'org.jboss.arquillian.container', name: 'arquillian-tomcat-remote-7', version: '1.0.0.CR6'
    	compile group: 'com.liferay', name: 'com.liferay.ant.arquillian', version: '1.0.0-SNAPSHOT'
    	compile group: 'org.springframework', name: 'spring-aop', version: '4.1.9.RELEASE'
    	compile group: 'org.springframework', name: 'spring-beans', version: '4.1.9.RELEASE'
    	compile group: 'org.springframework', name: 'spring-context', version: '4.1.9.RELEASE'
    	compile group: 'org.springframework', name: 'spring-core', version: '4.1.9.RELEASE'
    	compile group: 'org.springframework', name: 'spring-expression', version: '4.1.9.RELEASE'
    	compile group: 'org.springframework', name: 'spring-web', version: '4.1.9.RELEASE'
    	compile group: 'org.springframework', name: 'spring-webmvc', version: '4.1.9.RELEASE'
    	compile group: 'org.springframework', name: 'spring-webmvc-portlet', version: '4.1.9.RELEASE'
    }

Some of `my-spring-mvc-portlet`'s dependency artifacts have new names. 

 Old name | New name |
 -------- | -------- |
 `spring-web-portlet` | `spring-webmvc-portlet` |
 `spring-web-servlet` | `spring-webmvc` |

[Maven Central](https://search.maven.org/) provides artifact dependency
information. 

+$$$

**Note**: If the Spring Framework version you're using differs from the version
@product@ uses, you must name your Spring Framework JARs differently from
@product@'s Spring Framework JARs. If you don't rename your JARs, @product@
assumes you're using its Spring Framework JARs and excludes yours from the
generated WAB (Web Application Bundle).
[Portal property `module.framework.web.generator.excluded.paths`](https://docs.liferay.com/ce/portal/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework)
lists @product@'s Spring Framework JARs. 
[Understanding Excluded JARs](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies#understanding-excluded-jars)
explains how to detect the Spring Framework version @product@ uses. 

$$$

+$$$

**Note**: If a dependency is an OSGi module JAR and @product@ already exports
your plugin's required packages, *exclude* the JAR from your plugin's WAR file.
This prevents your plugin from exporting the same package(s) that Liferay is
already exporting. This prevents class loader collisions. To exclude a JAR from
deployment, add its name to the your project's
`liferay-plugin-package.properties` file's `deploy-excludes` property.

    deploy-excludes=\
        **/WEB-INF/lib/module-a.jar,\ 
        **/WEB-INF/lib/module-b.jar

Since `my-spring-mvc-portlet`'s dependencies aren't OSGi modules, no JARs
must be excluded.

$$$

To import class packages referenced by your portlet's descriptor files, add the
packages to an `Import-Package` header in the
`liferay-plugin-package.properties` file. See 
[Packaging a Spring MVC Portlet](/develop/tutorials/-/knowledge_base/7-1/spring-mvc#packaging-a-spring-mvc-portlet)
for details.

If you depend on a package from Java's `rt.jar` other than its `java.*`
packages, override
[portal property `org.osgi.framework.bootdelegation`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework)
and add it to the property's list. Go [here](/develop/tutorials/-/knowledge_base/7-1/resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles#case-4-the-missing-class-belongs-to-a-java-runtime-package)
for details. 

+$$$

**Note**: Spring MVC portlets whose embedded JARs contain properties files
(e.g., `spring.handlers`, `spring.schemas`, `spring.tooling`) might be affected
by issue
[LPS-75212](https://issues.liferay.com/browse/LPS-75212).
The last JAR that has properties files is the only JAR whose properties are
added to the resulting WAB's classpath. Properties in other JARs aren't added.

[Packaging a Spring MVC Portlet](/develop/tutorials/-/knowledge_base/7-1/spring-mvc#packaging-a-spring-mvc-portlet)
explains how to add all the embedded JAR properties.

$$$

The portlet is ready to deploy. Deploy it as you always have.

@product@'s
[WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
converts the portlet WAR to a Web Application Bundle (WAB) and installs the WAB
to Liferay's OSGi Runtime Framework. 

    2018-04-12 19:28:36.810 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:262] Processing my-spring-mvc-portlet.war
    2018-04-12 19:28:42.182 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseAutoDeployListener:43] Copying portlets for C:\portals\liferay-ce-portal-7.1-m1\tomcat-8.0.32\temp\20180412192842100ZSINUETA\my-spring-mvc-portlet.war
    2018-04-12 19:28:42.706 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseDeployer:876] Deploying my-spring-mvc-portlet.war
    2018-04-12 19:28:47.708 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseAutoDeployListener:50] Portlets for C:\portals\liferay-ce-portal-7.1-m1\tomcat-8.0.32\temp\20180412192842100ZSINUETA\my-spring-mvc-portlet.war copied successfully
    2018-04-12 19:28:56.600 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][HotDeployImpl:226] Deploying my-spring-mvc-portlet from queue
    2018-04-12 19:28:56.601 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PluginPackageUtil:1003] Reading plugin package for my-spring-mvc-portlet
    2018-04-12 19:28:56.700 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PortletHotDeployListener:186] Registering portlets for my-spring-mvc-portlet
    2018-04-12 19:28:56.955 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PortletHotDeployListener:298] 1 portlet for my-spring-mvc-portlet is available for use
    2018-04-12 19:28:57.114 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BundleStartStopLogger:35] STARTED my-spring-mvc-portlet_7.1.0.1 [658]

You've upgraded a Spring MVC portlet to @product-ver@. Way to go!

## Related Topics [](id=related-topics)

[Spring MVC](/develop/tutorials/-/knowledge_base/7-1/spring-mvc)

[Migrating Plugins SDK Projects to Workspace and Gradle](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
