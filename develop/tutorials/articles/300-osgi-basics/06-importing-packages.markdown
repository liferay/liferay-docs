# Importing Packages [](id=importing-packages)

Your modules and plugins are sure to use Java packages outside themselves.
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

For traditional Liferay plugin WARs, Liferay's WAB Generator detects packages
referenced in their JSPs, descriptor files, and classes (in `WEB-INF/classes`
and embedded JARs). The descriptor files include `web.xml`, `liferay-web.xml`,
`portlet.xml`, `liferay-portlet.xml`, and `liferay-hook.xml`. Referenced
packages that aren't found in either the plugin's `WEB-INF/classes` folder or in
embedded JARs are added to the WAB's manifest's package imports. 

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
