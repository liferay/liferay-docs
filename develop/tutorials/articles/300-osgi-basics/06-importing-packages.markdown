# Importing Packages [](id=importing-packages)

Your modules and plugins are sure to use Java packages from other sources.
@product@ project templates and tools detect these packages and add them to the
package imports in your OSGi bundle JAR's manifest. Your bundle can import and
use packages from bundles that
[export](/develop/reference/-/knowledge_base/7-0/exporting-packages)
them. Let's explore how to specify package imports in different scenarios.

[Gradle and Maven module projects](/develop/reference/-/knowledge_base/7-0/project-templates)
created using
[Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli),
[Liferay's Maven archetypes](/develop/tutorials/-/knowledge_base/7-0/maven),
or
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
come with
[bnd](http://bnd.bndtools.org/).
When you build a module JAR, bnd detects packages the module's bytecode uses and
generates a `META-INF/MANIFEST.MF` that imports them. 

+$$$

**Note**: Liferay's Maven module archetypes use the `bnd-maven-plugin`.
Liferay's Gradle module project templates use
[a third-party Gradle plugin](https://github.com/TomDmitriev/gradle-bundle-plugin)
to invoke bnd. 

$$$

For example, suppose you're developing a Liferay module using Maven or Gradle.
In most cases, you specify your module's dependencies in your `pom.xml` or
`build.gradle` file. At build time, the Maven or Gradle bundle plugin reads your
`pom.xml` or `build.gradle` file and bnd adds the required `Import-Package`
headers to the `META-INF/MANIFEST.MF` file in your module's JAR file.

Here's an example dependencies section from a module's `build.gradle` file:

    dependencies {
        provided group: "com.liferay", name: "com.liferay.petra.string", version: "1.0.0"
        provided group: "org.osgi", name: "org.osgi.core", version: "5.0.0"
        provided group: "org.slf4j", name: "slf4j-api", version: "1.7.2"
    }

And here's the generated `Import-Package` header that's generated in
the `META-INF/MANIFEST.MF` file:

    Import-Package: com.liferay.petra.string;version="[1.0,2)",org.osgi.fra
    mework;version="[1.7,2)",org.slf4j;version="[1.7,2)"

Note that you only need to specify JAR file dependencies. bnd examines your
application to determine which packages from those JAR files contain classes
your application uses and thus must be imported.

For traditional Liferay plugin WARs,
[Liferay's WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)
detects packages referenced in their JSPs, descriptor files, and classes (in
`WEB-INF/classes` and embedded JARs). The descriptor files include `web.xml`,
`liferay-web.xml`, `portlet.xml`, `liferay-portlet.xml`, and `liferay-hook.xml`.
Referenced packages that are neither found in the plugin's `WEB-INF/classes`
folder nor in embedded JARs are added to the WAB's manifest's package imports. 

Here are cases in which packages aren't detected:

-   Unrecognized descriptor file
-   Custom or unrecognized descriptor element or attribute
-   Reflection code
-   Class loader code

In such cases, you must manually detect the required packages and add them to an
`Import-Package` OSGi header. Here's where to specify the `Import-Package`
header for different project types:

 Project type | `Import-Package` header location |
:----------- | :------------------------------- |
 Module (uses bnd)     | `[project]/bnd.bnd` |
 Module (doesn't use bnd) | `[module JAR]/META-INF/MANIFEST.MF` |
 Traditional Liferay plugin WAR | `WEB-INF/liferay-plugin-package.properties` |
 
Congratulations! Now you can import all kinds of packages for your modules and
plugins to use.

## Related Topics [](id=related-topics)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)
