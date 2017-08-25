# Resolving ClassNotFoundException and NoClassDefFoundError in OSGi Bundles [](id=resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles)

Understanding a `ClassNotFoundException` or `NoClassDefFoundError` in non-OSGi
environments is straightforward. 

-   `ClassNotFoundException`: thrown when looking up a class that isn't on the
    classpath or using an invalid name to look up a class.
-   `NoClassDefFoundError`: occurs when a compiled class references
    another class that isn't on the run time classpath.

In OSGi environments, however, there are additional cases where a
`ClassNotFoundException` or `NoClassDefFoundError` can occur. Here are two:

1.  The bundle doesn't import the class's package.

2.  The class no longer exists in the imported package.

This tutorial explains how to handle each case.

## Case 1: The Bundle doesn't import the class's package [](id=case-1-bundle-doesnt-import-the-class-package)

For a bundle (module or WAB) to consume a another bundle's exported class, the
consuming bundle must import the exported package containing the class (for
example, in an `Import-Package` header in the bundle's `bnd.bnd` file). If the
consumer accesses the class without importing it, a `ClassNotFoundException` or
`NoClassDefFoundError` occurs.

In the consuming bundle, make sure to import the correct package. Check the
package name. If the package import is correct but you still get the exception
or error, the class might no longer exist in the package.

## Case 2: Class no longer exists in the package [](id=case-2-class-no-longer-exists-in-the-package)

In OSGi runtime environments, bundles can change and come and go. If you
reference or look up another bundle's class and that class has been removed, a
`NoClassDefFoundError` or `ClassNotFoundException` occurs.
[Semantic Versioning](http://semver.org) guards against this scenario. It
dictates that removing a class from an exported package constitutes a new major
version for that package. Neglecting to increment the package's major version
breaks dependent bundles. 

For example, a bundle that consumes class `com.foo.Bar` specifies package import
`com.foo;version=[1.0.0, 2.0.0)`--the bundle uses `com.foo` package versions
from `1.0.0` up to but not including `2.0.0`. The first part of the version
number (the `1` in `1.0.0`) represents the *major* version. The consuming bundle
doesn't expect any *major* breaking changes, such as a class being removed.
Removing class `com.foo.Bar` from package `com.foo` constitutes a major breaking
change. Removing the class without incrementing the major version number breaks
consuming bundles. 

The example consuming bundle doesn't expect any major breaking changes, such as
a class being removed. If the authoring developer removes class `Bar` from
`com.foo` without incrementing the package to a new major version (e.g.,
`2.0.0`), a `ClassNotFoundException` or `NoClassDefFoundError` occurs when other
bundles look up or reference that class.

Here are a couple options to consider since the class no longer exists in the package:

-   Adapt to the new API. To learn how to adapt, read the package's/module's
    Javadoc, release notes, and or formal documentation. You can ask the author
    or investigate forums. 

-   Revert to the module version you used previously. Deployed module versions
    reside in `[Liferay_Home]/osgi/`. See
    [Backing up Liferay Installations](https://dev.liferay.com/discover/deployment/-/knowledge_base/7-0/backing-up-a-liferay-installation#backing-up-liferays-file-system)
    for details. 

Do what you think is best to get your module working properly. 

Now you know how to resolve common situations involving `ClassNotFoundException`
or `NoClassDefFoundError`. For additional information on `NoClassDefFoundError`
see OSGi Enroute's article
[What is NoClassDefFoundError?](http://enroute.osgi.org/faq/class-not-found-exception.html).

## Related Topics [](id=related-topics)

[Backing up Liferay Installations](https://dev.liferay.com/discover/deployment/-/knowledge_base/7-0/backing-up-a-liferay-installation)
