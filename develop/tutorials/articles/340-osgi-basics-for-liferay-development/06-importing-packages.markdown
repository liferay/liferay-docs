# Importing Packages [](id=importing-packages)

Your modules will often need to use Java classes from packages
[exported](/develop/reference/-/knowledge_base/7-1/exporting-packages) by other
modules. When a module is set up to import, the OSGi framework finds other
registered modules that export the needed packages and wires them to the
importing module. At run time, the importing module gets the class from the
wired module that exports the class's package. 

For this to happen, a module must specify the `Import-Package` OSGi manifest
header with a comma-separated list of the Java packages it needs. For example,
if a module needs classes from the `javax.portlet` and
`com.liferay.portal.kernel.util` packages, it must specify them like so:

    Import-Package: javax.portlet,com.liferay.portal.kernel.util

Import packages must sometimes be specified manually, but not always.
Conveniently, @product@ project templates and tools automatically detect the
packages a module uses and add them to the package imports in the module JAR's
manifest. Let's explore how package imports are specified in different
scenarios.

[Gradle and Maven module projects](/develop/reference/-/knowledge_base/7-1/project-templates)
created using
[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli),
[Liferay's Maven archetypes](/develop/tutorials/-/knowledge_base/7-1/maven),
or
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)
use
[bnd](http://bnd.bndtools.org/).
On building such a project's module JAR, bnd detects the packages the module
uses and generates a `META-INF/MANIFEST.MF` file whose `Import-Package` header
specifies the packages. 

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
headers to your module JAR's `META-INF/MANIFEST.MF`. 

Here's an example dependencies section from a module's `build.gradle` file:

    dependencies {
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }

And here's the `Import-Package` header that's generated in the module JAR's
`META-INF/MANIFEST.MF` file:

    Import-Package: com.liferay.portal.kernel.portlet.bridges.mvc;version=
    "[1.0,2)",com.liferay.portal.kernel.util;version="[7.0,8)",javax.nami
    ng,javax.portlet;version="[2.0,3)",javax.servlet,javax.servlet.http,j
    avax.sql

Note that your build file need only specify JAR file dependencies. bnd examines
your module's class path to determine which packages from those JAR files
contain classes your application uses and imports the packages. The examination
includes all classes found in the class path--even those from embedded
[third party library JARs](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module). 

Regarding classes used by a traditional Liferay plugin WAR,
[Liferay's WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
detects their use in the WAR's JSPs, descriptor files, and classes (in
`WEB-INF/classes` and embedded JARs). The WAB Generator searches the `web.xml`,
`liferay-web.xml`, `portlet.xml`, `liferay-portlet.xml`, and `liferay-hook.xml`
descriptor files. It adds package imports for classes that are neither found in
the plugin's `WEB-INF/classes` folder nor in embedded JARs. 

The WAB Generator and bnd don't add package imports for classes referenced in
these places:

-   Unrecognized descriptor file
-   Custom or unrecognized descriptor element or attribute
-   Reflection code
-   Class loader code

In such cases, you must manually determine the packages required and add them to
an `Import-Package` OSGi header in the location appropriate to your project
type:

 Project type | `Import-Package` header location |
:----------- | :------------------------------- |
 Module (uses bnd)     | `[project]/bnd.bnd` |
 Module (doesn't use bnd) | `[module JAR]/META-INF/MANIFEST.MF` |
 Traditional Liferay plugin WAR | `WEB-INF/liferay-plugin-package.properties` |
 
Congratulations! Now you can import all kinds of packages for your modules and
plugins to use.

## Related Topics [](id=related-topics)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)

[Tooling](/develop/tutorials/-/knowledge_base/7-1/tooling)
