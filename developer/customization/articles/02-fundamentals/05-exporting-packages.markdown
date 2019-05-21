---
header-id: exporting-packages
---

# Exporting Packages

[TOC levels=1-4]

An OSGi bundle's Java packages are private by default. To expose a package, you
must explicitly export it. This way you share only the classes you want to
share. Exporting a package in your OSGi bundle (bundle) manifest makes all the
package's classes available for other bundles to
[import](/docs/7-2/customization/-/knowledge_base/c/importing-packages).

To export a package, add it to your module's or plugin's `Export-Package` OSGi
header. A header exporting `com.liferay.petra.io` and
`com.liferay.petra.io.unsync` would look like this:

    Export-Package:\
    com.liferay.petra.io,\
    com.liferay.petra.io.unsync

The correct location for the header depends on your project's type:

| Project Type | `Export-Package` header location |
| :----------- | :------------------------------- |
| Module JAR (uses bnd)     | `[project]/bnd.bnd` |
| Module JAR (doesn't use bnd) | `[module JAR]/META-INF/MANIFEST.MF` |
| Plugin WAR | `WEB-INF/liferay-plugin-package.properties` |

Module projects created using
[Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli),
[Liferay's Maven archetypes](/docs/7-2/reference/-/knowledge_base/r/maven),
or
[Liferay @ide@](/docs/7-2/reference/-/knowledge_base/r/liferay-ide)
use
[bnd](http://bnd.bndtools.org/).
On building such a project's module JAR, bnd propagates the OSGi headers from
the project's `bnd.bnd` file to the JAR's `META-INF/MANIFEST.MF`.  

In module projects that don't use bnd, you must manually add package exports to
an `Export-Package` header in the module JAR's `META-INF/MANIFEST.MF`. 

In plugin WAR projects, you must add package exports to an
`Export-Package` header in the project's `WEB-INF/liferay-plugin-package.properties`. On
copying the WAR into the `[Liferay Home]/deploy` folder, the
[WAB Generator](/docs/7-2/customization/-/knowledge_base/c/deploying-wars-wab-generator)
propagates the OSGi headers from the WAR's `liferay-plugin-package.properties`
file to the `META-INF/MANIFEST.MF` file in the generated Web Application Bundle (WAB).

| **Note:** bnd makes a module's exported packages *substitutable*. That is, the
| OSGi framework can substitute your module's exported package with a compatible
| package of the same name, but potentially different version, that's exported
| from a different OSGi bundle. bnd enables this for your module by 
| automatically making your module import every package it exports. In this way,
| your module can work on its own, but can also work in conjunction with bundles
| that provide a different (compatible) version, or even the same version, of 
| the package. A package from another bundle might provide better "wiring" 
| opportunities with other bundles.
| [Peter Kriens' blog post](http://blog.osgi.org/2007/04/importance-of-exporting-nd-importing.html)
| provides more details on how substitutable exports works.

Now you can share your module's or plugin's terrific [EDITOR: or terrible!]
packages with other OSGi bundles! 

## Related Topics

[Configuring Dependencies](/docs/7-2/tutorials/-/knowledge_base/t/configuring-dependencies)

[Deploying WARs \(WAB Generator\)](/docs/7-2/tutorials/-/knowledge_base/t/using-the-wab-generator)

[Project Templates](/docs/7-2/reference/-/knowledge_base/r/project-templates)

[Liferay's Maven Archetypes](/docs/7-2/reference/-/knowledge_base/r/maven)

[Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli)

[Liferay @ide@](/docs/7-2/reference/-/knowledge_base/t/liferay-ide)

[Semantic Versioning](/docs/7-2/customization/-/knowledge_base/c/semantic-versioning)
