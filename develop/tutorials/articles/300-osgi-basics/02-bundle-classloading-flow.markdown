# Bundle Classloading Flow

The classloading algorithm in bundles is powerful, but not trivial. There are
several sources that are searched until a class is found and the search order
makes good sense. The classloading algorithm @product@ uses for bundles follows
the OSGi Core specification.

This tutorial includes a diagram of the classloading flow and walks you through
it. 

![Figure 1.0: This flow chart illustrates classloading in a bundle.](../../images/bundle-classloading-flow-chart.png)

Here is the algorithm for classloading in a bundle: 

1.  If the class is in a `java.*` package, delegate loading to the parent
    classloader. Otherwise, continue.

2.  If the class is in the OSGi Framework's boot delegation list, delegate
    loading to the parent classloader. Otherwise, continue.

3.  If the class is in one of the packages the bundle imports from a wired
    exporter. A wired exporter is another bundle's classloader that's previously
    loaded the package. If the class's package matches a wired package export,
    the exporting bundle's classloader loads it. If the class isn't found,
    continue.

4.  If the class is imported by one of the bundle's required bundles, that
    bundle's classloader loads it.

5.  If the class is in the bundle's classpath (manifest header
    `Bundle-ClassPath`), the bundle's classloader loads it. Otherwise, continue.

6.  If the class is in the bundle's fragments classpath, the bundle's
    classloader loads it.

7.  If the class is in a package that's dynamically imported using
    `DynamicImport-Package` and a wire is established with the exporting bundle,
    that bundle's classloader loads it. Otherwise, the class isn't found.

There you have it. You've learned all the steps for finding and loading a class
an OSGi bundle uses. 
