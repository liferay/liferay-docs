# Modules as an Improvement over Traditional Plugins [](id=modules-as-an-improvement-over-traditional-plugins)

In @product-ver@, you can develop applications using OSGi modules or using
traditional Liferay plugins (WAR-style portlets, hooks, EXT, and web
applications). Liferay's Plugin Compatibility Layer (explained later) makes it
possible to deploy traditional plugins to the OSGi runtime framework. To benefit
from all @product@ and OSGi offer, however, you should use OSGi modules.

Modules offer these benefits:

- **Better Encapsulation** - The only classes a module exposes publicly are
those in [packages it exports](/develop/reference/-/knowledge_base/7-1/exporting-packages)
explicitly. This lets you define internal public classes transparent to
external clients.

- **Dependencies by Package** - Dependencies are specified by Java package, not
by JAR file. In traditional plugins, you had to add *all* of a JAR file's
classes to the classpath to use *any* of its classes. With OSGi, you need only
[import packages](/develop/reference/-/knowledge_base/7-1/importing-packages)
containing the classes you need. Only the classes in those packages are added to
the module's classpath.

- **Lightweight** - A module can be as small as you want it to be. In contrast
to a traditional plugin, which may require several descriptor files, a module
requires only a single descriptor file--a standard JAR manifest. Also,
traditional plugins are typically larger than modules and deployed on app server
startup, which can slow down that process considerably. Modules deploy more
quickly and require minimal overhead cost.

- **Easy Reuse** - Modules lend themselves well to developing small, highly
cohesive chunks of code. They can be combined to create applications that are
easier to test and maintain. Modules can be distributed publicly (e.g., on
[Maven Central](https://search.maven.org/)) or privately. And since modules are
versioned, you can specify precisely the modules you want to use.

- **In-Context Descriptors** - Where plugins use descriptor files (e.g.,
`web.xml`, `portlet.xml`, etc.) to describe classes, module classes use OSGi
annotations to describe themselves. For example, a module portlet class can use
[OSGi Service annotation properties](/develop/reference/-/knowledge_base/7-1/portlet-descriptor-to-osgi-service-property-map)
to specify its name, display name, resource bundle, public render parameters,
and much more. Instead of specifying that information in descriptor files
separate from the code, you specify them in context in the code.

These are just a few ways modules outshine traditional plugins. Note, however,
that developers experienced with Liferay plugins have the best of both worlds.
@product-ver@ supports traditional plugins *and* modules. Existing Liferay
developers can find comfort in the simplicity of modules and their similarities
with plugins.

Here are some fundamental characteristics modules share with plugins:

- Developers use them to create applications (portlets for Liferay)

- They're zipped up packages of classes and resources

- They're packaged as a standard Java JARs

Now that you've compared and contrasted modules with plugins, it's time to tour
the module anatomy. 

## Module Structure: A JAR File with a Manifest [](id=module-structure-a-jar-file-with-a-manifest)

A module's structure is extremely simple. It has one mandatory file:
`META-INF/MANIFEST.MF`. You add code and resources to the module and organize
them as desired. 

Here's the essential structure of a module JAR file:

- `[Project root]`
    - `[Module's files]`
    - `META-INF`
        - `MANIFEST.MF`

The `MANIFEST.MF` file describes the module to the system. The manifest's
[OSGi headers](https://www.osgi.org/bundle-headers-reference/)
identify the module and its relationship to other modules. 

Here are some of the most commonly used headers:

- `Bundle-Name`: User friendly name of the module.

- `Bundle-SymbolicName`: Globally unique identifier for the module. Java package
conventions (e.g., `com.liferay.journal.api`) are commonly used.

- `Bundle-Version`: Version of the module.

- `Export-Package`: Packages from this module to make accessible to other
modules.

- `Import-Package`: Packages this module requires that other modules provide.

Other headers can be used to specify more characteristics, such as how the
module was built, development tools used, etc. 

For example, here are some headers from the Liferay Journal Web module manifest:

    Manifest-Version: 1.0
    Bundle-ManifestVersion: 2
    Bundle-Name: Liferay Journal Web
    Bundle-SymbolicName: com.liferay.journal.web
    Bundle-Vendor: Liferay, Inc.
    Bundle-Version: 1.1.2
    Export-Package:\
        com.liferay.journal.web.asset,\
        com.liferay.dynamic.data.mapping.util,\
        com.liferay.journal.model,
        com.liferay.journal.service,com.liferay.journal.util, [..]
    Import-Package:\
        aQute.bnd.annotation.metatype,\
        com.liferay.announcements.kernel.model,
        com.liferay.application.list,\
        com.liferay.asset.kernel,\
        com.liferay.asset.kernel.exception, [..]

Note: to remove unnecessary "noise" from this example, some headers have been
abbreviated (`[..]`) and some have been removed.

You can organize and build a module's Java code and resources however you like.
You're free to use any folder structure conventions, such as those used in Maven
or by your development team. And you can use any build tool, such as Gradle or
Maven, to  [manage dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies). 

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
is an environment for managing module projects (and theme projects). A default
Workspace provides Gradle build scripts and a Workspace created from the Liferay
Project Templates Workspace archetype provides Maven build scripts for
developing on Liferay. Workspace can be used [from the command line](/develop/tutorials/-/knowledge_base/7-1/blade-cli)
or from within [Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide).
Note also that Liferay @ide@ provides plugins for Gradle, Maven, and BndTools.
Tooling details are covered later in this series.

Now that you're familiar with the module structure and manifest, it's time to
explore how to build modules.

## Building Modules with bnd [](id=building-modules-with-bnd)

The most common way to build modules is with a little tool called [bnd](http://bnd.bndtools.org/).
It's an engine that, among other things, simplifies generating manifest
metadata. Instead of manually creating a `MANIFEST.MF` file, developers use bnd
to generate it. bnd can be used on its own or along with other build tools, such
as Gradle or Maven. Liferay Workspace uses bnd together with Gradle or Maven.

One of bnd's best features is that it automatically transverses a module's code
to identify external classes the module uses and adds them to the manifest's
list of packages to import. bnd also provides several OSGi-specific operations
that simplify module development.

bnd generates the manifest based on a file called `bnd.bnd` in the project root.
This file's header list is similar to (but shorter than) that of the
`MANIFEST.MF`. Compare the Liferay Journal Web module's `bnd.bnd` file content
(simplified a bit) below to its `MANIFEST.MF` file content that was listed
earlier:

    Bundle-Name: Liferay Journal Web
    Bundle-SymbolicName: com.liferay.journal.web
    Bundle-Version: 1.1.2
    Export-Package:\
        com.liferay.journal.web.asset,\
        com.liferay.journal.web.dynamic.data.mapping.util,\
        com.liferay.journal.web.social,\
        com.liferay.journal.web.util

The main difference is that the `bnd.bnd` file doesn't specify an
`Import-Package` header. It's unnecessary because bnd generates it in the
`MANIFEST.MF` file automatically. It's metadata made easy!

bnd plugins are available to use with Gradle and Maven. And since Liferay
Workspace includes bnd, you can use bnd from the command line and from Liferay
@ide@.

It's time to get hands-on experience creating and deploying an OSGi module.
That's next. 
