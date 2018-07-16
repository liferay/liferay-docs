# Importing Packages [](id=importing-packages)

Your modules will often need to use Java classes from packages
[exported](/develop/tutorials/-/knowledge_base/7-1/exporting-packages) by other
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
manifest. Here are the different package import scenarios:

- [Automatic Package Import Generation](#automatic-package-import-generation)

- [Importing Java Platform Packages](#importing-java-platform-packages)

- [Manually Adding Package Imports](#manually-adding-package-imports)

Let's explore how package imports are specified in these scenarios. 

## Automatic Package Import Generation [](id=automatic-package-import-generation)

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

Java platform packages (e.g., `javax.portlet.*` ) are handled differently.  

## Importing Java Platform Packages [](id=importing-java-platform-packages)

You'll want to make sure the Java platform APIs you compile against are
available at runtime. Since the API packages aren't versioned, you must use OSGi
Portable Java Contracts to assure Java packages for the JSR you require are
provided at runtime. 

Here's how to set up Java platform API contracts in different module projects:

- **Blade CLI and Liferay @ide@ projects** handle it automatically! They set up
Java contracts based on the versions of `JavaPortlet` and `JavaServlet` you
compiled against. 

    - **Gradle projects** add the `JavaPortlet` and `JavaPortlet` contracts to
    the module JAR manifest on assembly.
    - **Maven projects** have `-contract` instructions for `JavaPortlet` and
    `JavaPortlet` in their `bnd.bnd` file. 

- **Other projects that use bnd** that are not created using Blade CLI or
Liferay @ide@ must specify contracts in their `bnd.bnd` file. The Portable Java
Contracts framework adds `Require-Capability` entries that contract the Java
APIs and *removes* version information from the corresponding Java API
`Import-Package` entries.

        -contract: JavaPortlet
        -contract: JavaServlet 

- **Projects using other tool chains** must explicitly specify contracts in
their OSGi manifest. For example, here's how to contract for `JavaPortlet` 3.0
in your `META-INF/MANIFEST.MF` file:

        Import-Package: javax.portlet
        Require-Capability: osgi.contract;filter:=(&(osgi.contract=JavaPortlet)(version=3.0))

In all these project scenarios, the contract requirement and capability
specifies the module's relationship with the imported package. If the system
you're running does *not* provide the exact contract, your module does not
resolve. Resolving the missing package is better than getting an incompatibility
failure during execution. 

For Portable Java Contract details, see 
[Portable Java Contract Definitions](https://www.osgi.org/portable-java-contract-definitions/). 

## Manually Adding Package Imports [](id=manually-adding-package-imports)

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
