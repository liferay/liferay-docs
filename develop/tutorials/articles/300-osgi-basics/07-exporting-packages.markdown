# Exporting Packages [](id=exporting-packages)

OSGi module classes are inaccessible to other modules by default. To expose a
class, you must explicitly export its package. Exporting a package in your OSGi
bundle JAR's manifest makes all the package's classes available for other
bundles to
[import](/develop/tutorials/-/knowledge_base/7-0/importing-packages).

To export a package, you must add it to an `Export-Package` OSGi header. Here's
where to specify the `Export-Package` header in your project:

 Project Type | `Export-Package` header location |
 :----------- | :------------------------------- |
 Module (uses bnd)     | `[project]/bnd.bnd` |
 Module (doesn't use bnd) | `[module JAR]/META-INF/MANIFEST.MF` |
 Traditional Liferay plugin WAR | `WEB-INF/liferay-plugin-package.properties` |

Module projects created using
[Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli),
[Liferay's Maven archetypes](/develop/tutorials/-/knowledge_base/7-0/maven),
or
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
come with
[bnd](http://bnd.bndtools.org/).
When you build a module JAR, bnd propagates the OSGi headers from the project's
`bnd.bnd` file to the JAR's `META-INF/MANIFEST.MF`.  

Here's a simple example of how to export packages from a `bnd.bnd` file:

    Export-Package:\
    com.liferay.petra.io,\
    com.liferay.petra.io.unsync

In module projects that don't use bnd, you must manually add package exports to
an `Export-Package` header in the module JAR's `META-INF/MANIFEST.MF`. 

When you copy a traditional Liferay plugin WAR into the `[Liferay Home]/deploy`
folder, the
[WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)
propagates the OSGi headers from the WAR's `liferay-plugin-package.properties`
file to the `META-INF/MANIFEST.MF` file in the generated Web Application Bundle (WAB).

+$$$

**Note**: bnd makes your module's exported packages *substitutable*. That is,
the OSGi framework can substitute your module's exported package with a
compatible package of the same name from a different bundle. In this way, your
module can work on it's own, but can also work in conjunction with bundles that
provide a compatible, or even same version of the package. A package from
another bundle might provide better "wiring" opportunities with other bundles.
For more details on substitutable exports, see
[this blog post from Peter Krien's](http://blog.osgi.org/2007/04/importance-of-exporting-nd-importing.html). 

$$$

Now you can share your module's terrific packages with other modules! 

## Related Topics [](id=related-topics)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)
