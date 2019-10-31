---
header-id: resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles
---

# Resolving ClassNotFoundException and NoClassDefFoundError in OSGi Bundles

[TOC levels=1-4]

`ClassNotFoundException` and `NoClassDefFoundError` are common, well known
exceptions:

-   `ClassNotFoundException` is thrown when looking up a class that isn't on the
    classpath or using an invalid name to look up a class that isn't on the
    runtime classpath. 
-   `NoClassDefFoundError` occurs when a compiled class references
    another class that isn't on the runtime classpath.

In OSGi environments, however, there are additional cases where a
`ClassNotFoundException` or `NoClassDefFoundError` can occur:

1.  The missing class belongs to a module dependency that's an OSGi module. 
2.  The missing class belongs to a module dependency that's *not* an OSGi 
    module. 
3.  The missing class belongs to a global library, either at the @product@ 
    web app scope or the application server scope. 
4.  The missing class belongs to a Java runtime package.

This tutorial explains how to handle each case.

## Case 1: The Missing Class Belongs to an OSGi Module

In this case, there are two possible causes: 

1.  **The module doesn't import the class's package:** For a module (or WAB) to 
    consume another module's exported class, the consuming module must import 
    the exported package that contains the class. To do this, you add an
    `Import-Package` header in the consuming module's `bnd.bnd` file. If the
    consuming module tries to access the class without importing the package, a 
    `ClassNotFoundException` or `NoClassDefFoundError` occurs. 

    Check the package name and make sure the consuming module imports the right
    package. If the import is correct but you still get the exception or
    error, the class might no longer exist in the package. 

2.  **The class no longer exists in the imported package:** Modules are changed
    frequently in OSGi runtime environments. If you reference another module's
    class that its developer removed, a `NoClassDefFoundError` or
    `ClassNotFoundException` occurs. [Semantic Versioning](http://semver.org)
    guards against this scenario: removing a class from an exported package
    constitutes a new major version for that package. Neglecting to increment
    the package's major version breaks dependent modules. 

    For example, say a module that consumes the class `com.foo.Bar` specifies
    the package import `com.foo;version=[1.0.0, 2.0.0)`. The module uses
    `com.foo` versions from `1.0.0` up to (but not including) `2.0.0`. The
    first part of the version number (the `1` in `1.0.0`) represents the
    *major* version. The consuming module doesn't expect any *major* breaking
    changes, like a class removal. Removing `com.foo.Bar` from `com.foo`
    without incrementing the  package to a new major version (e.g., `2.0.0`)
    causes a `ClassNotFoundException` or `NoClassDefFoundError` when other
    modules look  up or reference that class. 

    You have limited options when the class no longer exists in the package: 

    -   Adapt to the new API. To learn how to do this, read the 
        package's/module's Javadoc, release notes, and/or formal documentation. 
        You can also ask the author or search forums. 

    -   Revert to the module version you used previously. Deployed module 
        versions reside in `[Liferay_Home]/osgi/`. For details, see
        [Backing up Liferay Installations](/docs/7-2/deploy/-/knowledge_base/d/backing-up-a-liferay-installation). 

    Do what you think is best to get your module working properly. 

Now you know how to resolve common situations involving `ClassNotFoundException` 
or `NoClassDefFoundError`. For additional information on `NoClassDefFoundError`, 
see OSGi Enroute's article 
[What is NoClassDefFoundError?](http://enroute.osgi.org/faq/class-not-found-exception.html). 

## Case 2: The Missing Class Doesn't Belong to an OSGi Module

In this case, you have two options: 

1.  Convert the dependency into an OSGi module so it can export the missing 
    class. Converting a non-OSGi `JAR` file dependency into an OSGi module that 
    you can deploy alongside your application is the ideal solution, so it
    should be your first choice. 

2.  Embed the dependency in your module by embedding the dependency `JAR` file's
    packages as private packages in your module. If you want to embed a non-OSGi
    `JAR` file in your application, see [Resolving Third Party Library Package
    Dependencies](https://portal.liferay.dev/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module). 

## Case 3: The Missing Class Belongs to a Global Library

In this case, you can configure @product@ so the OSGi system module exports the
missing class's package. Then your module can import it. You should **NOT**,
however, undertake this lightly. If Liferay intended to make a global library
available for use by developers, the system module would already export this
library! Proceed only if you have no other solution, and watch out for
unintended consequences. There are two ways to export the package: 

1.  In your `portal-ext.properties` file, use the property
    [`module.framework.system.packages.extra`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework)
    to specify the packages to export. Preserve the property's current list. 

2.  If the package you need is from a @product@ JAR, you might be able to add 
    the module to the list of exported packages in
    `[LIFERAY_HOME]/osgi/core/com.liferay.portal.bootstrap.jar`'s
    `META-INF/system.packages.extra.bnd` file. Try this option 
    only if the first option doesn't work. 

If the package you need is from a @product@ module, (i.e., it's **NOT** 
from a global library), you can add the package to that module's `bnd.bnd` 
exports. You should **NOT**, however, undertake this lightly. The package would
already be exported if Liferay intended for it to be available. 

## Case 4: The Missing Class Belongs to a Java Runtime Package

`rt.jar` (the JRE library) has non-public packages. If your module imports one
of them, configure @product@'s system bundle to export the package to the module
framework. 

1.  Add the current
    [`module.framework.system.packages.extra` property setting](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework)
    to a
    [`portal-ext.properties` file](/docs/7-2/deploy/-/knowledge_base/d/portal-properties). 
    Your server's current setting is in the @product@ web application's
    `/WEB-INF/lib/portal-impl.jar/portal.properties` file. 

2.  In your `portal-ext.properties` file, append the required Java runtime 
    package to the end of the  `module.framework.system.packages.extra`
    property's package list. 

3.  Restart your server. 

Your module should resolve and install. 

## Related Topics

[Backing up Liferay Installations](/docs/7-2/deploy/-/knowledge_base/d/backing-up-a-liferay-installation)

[Resolving Third Party Library Package Dependencies](https://portal.liferay.dev/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module)
