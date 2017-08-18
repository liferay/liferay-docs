# Resolving ClassNotFoundException and NoClassDefFoundError in OSGi Bundles [](id=resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles)

Understanding a `ClassNotFoundException` or `NoClassDefFoundError` in non-OSGi
environments is straightforward. 

-   `ClassNotFoundException`: thrown when looking up a class that isn't on the
    classpath or using an invalid name to look up a class.
-   `NoClassDefFoundError`: occurs when a compiled class references
    another class that isn't in the run time classpath.

In OSGi environments, however, there are additional cases in which a
`ClassNotFoundException` or `NoClassDefFoundError` can occur. Here are two
common cases:

1.  Bundle doesn't import the class's package.

2.  Class no longer exists in the imported package.

This tutorial explains how to handle each case.

## Case 1: Bundle doesn't import the class's package [](id=case-1-bundle-doesnt-import-the-class-package)

For a bundle (module or WAB) to consume a another bundle's exported class, the
consuming bundle must import (e.g., in an `Import-Package` header in your
bundle's `bnd.bnd` file) the exported package containing the class. If the
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

Here are two ways to resolve the issue:

1.  Request that the providing bundle owner increment the bundle's major
    version, and as a result honor Semantic Versioning. This solution is ideal
    because it can resolve the issue for all consumers. 

2.  Refine the consuming bundle's package import to a range up to but not 
    including the broken version. For example, if you're consuming a package
    starting at version `1.0.0` but version `1.3.0` breaks you, specify the
    range `[1.0.0, 1.3.0)`.

Now you know how to resolve common situations involving `ClassNotFoundException`
or `NoClassDefFoundError`. For additional information on `NoClassDefFoundError`
see OSGi Enroute's article
[What is NoClassDefFoundError?](http://enroute.osgi.org/faq/class-not-found-exception.html).
