# Deploying a Spring MVC Portlet [](id=deploying-a-spring-mvc-portlet)

Developers creating portlets for @product@ can usually deploy their portlet as
Java EE-style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR)
OSGi bundle artifacts. Spring MVC portlet developers don't have that
flexibility. Spring MVC portlets must be packaged as WAR artifacts because the
Spring MVC framework is designed for Java EE. Therefore, it expects a WAR layout
and requires Java EE resources such as the `WEB-INF/web.xml` descriptor. 

Because Liferay supports the OSGi WAB (Web Application Bundler) standard for
deployment, you can deploy your WAR and it runs as expected in the OSGi
runtime. Here are the high points on why that works in Liferay:

-   The Liferay auto-deploy process runs, adding the `PortletServlet` and
    `PlugincontextListener` configurations to the `WEB-INF/web.xml` file.

-   The
    [Liferay WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
    automatically creates an OSGi-ready `META-INF/MANIFEST.MF` file. If you want
    to affect the content of the manifest file, you can place bnd directives and
    OSGi headers directly into a `WEB-INF/liferay-plugin-package.properties`
    file for the WAB.

The auto-deploy process and Liferay's WAB generator convert your project to a
Liferay-ready WAB. The WAB generator detects your class's `import` statements
and adds all external packages to the WAB's `Import-Package` header. The
generator also merges packages from your plugin's
`liferay-plugin-package.properties` into the header. You can 
[manually add
imports](/develop/tutorials/-/knowledge_base/7-1/importing-packages) for any
module packages that aren't picked up automatically. Follow the instructions for
[resolving any third party package dependencies from standard library artifacts](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module). 

If you depend on a package from Java's `rt.jar` other than a `java.*` package,
override
[portal property `org.osgi.framework.bootdelegation`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework)
and add it to the property's list. Go 
[here](/develop/tutorials/-/knowledge_base/7-1/resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles#case-4-the-missing-class-belongs-to-a-java-runtime-package)
for details. 

+$$$

**Note**: Spring MVC portlets whose embedded JARs contain properties files
(e.g., `spring.handlers`, `spring.schemas`, `spring.tooling`) might be affected
by issue
[LPS-75212](https://issues.liferay.com/browse/LPS-75212).
The last JAR that has properties files is the only JAR whose properties are
added to the resulting WAB's classpath. Properties in other JARs aren't added. 

For example, suppose that a portlet has several JARs containing these
properties files:

-   `WEB-INF/src/META-INF/spring.handlers`
-   `WEB-INF/src/META-INF/spring.schemas`
-   `WEB-INF/src/META-INF/spring.tooling`

The properties from the last JAR processed are the only ones added to the
classpath. The properties files must be on the classpath in order for the module
to use them.

To add all the properties files to the classpath, you can combine them into one
of each type (e.g., one `spring.handlers`, one `spring.schemas`, and one
`spring.tooling`) and add them to `WEB-INF/src`. 

Here's a shell script that combines these files:

    cat /dev/null > docroot/WEB-INF/src/META-INF/spring.handlers
    cat /dev/null > docroot/WEB-INF/src/META-INF/spring.schemas
    cat /dev/null > docroot/WEB-INF/src/META-INF/spring.tooling
    for jar in $(find docroot/WEB-INF/lib/ -name '*.jar'); do
    for file in $(unzip -l $jar | grep -F META-INF/spring. | awk '
    { print $4 } 
    '); do
    if [ "META-INF/spring.tld" != "$file" ]; then
    unzip -p $jar $file >> docroot/WEB-INF/src/$file
    echo >> docroot/WEB-INF/src/$file
    fi
    done
    done

You can modify and use the shell script to add your JAR's properties files to
the classpath. 

$$$

+$$$

**Note**: If you want to use a Spring Framework version different from the
version Liferay provides, you must name your Spring Framework JARs
differently from the ones
[portal property `module.framework.web.generator.excluded.paths`](https://docs.liferay.com/ce/portal/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework)
excludes. If you don't rename your Spring Framework JARs, the WAB generator
assumes you're using Liferay's Spring Framework JARs and excludes yours from
the generated WAB.
[Understanding Excluded JARs](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies#understanding-excluded-jars)
explains how to detect @product@'s Spring Framework version. 

$$$

Once you've packaged your Spring MVC portlet as a WAR, you can deploy it by
copying it to `[LIFERAY_HOME]/deploy`. 

Congratulations on deploying your Spring MVC portlet!
