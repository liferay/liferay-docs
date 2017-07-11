# Resolving a Plugin's Dependencies [](id=resolving-a-plugins-dependencies)

Now that you've imported your plugin project to Liferay @ide@, you probably see
compile errors for some of the Liferay classes it uses. These classes are listed
as undefined classes or unresolved symbols because they've been moved, renamed,
or removed. As a part of modularization in @product@, many of these classes
reside in new modules. 

You must resolve all of these Liferay classes for your plugin. Some of the class 
changes are quick and easy to fix. Changes involving the new modules require 
more effort to resolve, but doing so is still straightforward. 

Liferay class changes and required adaptations are described here: 

1.  **Class moved to a package that's in the classpath**:  This change is 
    common and easy to fix. Since the module is already on your classpath, you
    need only update the class import. You can do this by using the Liferay Code 
    Upgrade Tool or by organizing imports in @ide@. The Code Upgrade Tool 
    reports each moved class for you to address one by one. Organizing imports 
    in @ide@ automatically resolves multiple classes at once. 

    It's typically faster to resolve moved classes using the mentioned Eclipse
    feature. Since Liferay @ide@ is based on Eclipse, you can generate imports
    of classes in your classpath with the *Organize Imports* keyboard sequence
    *Ctrl-Shift-o*. Comment out or remove any imports marked as errors, then
    press *Ctrl-Shift-o*. If there's only one match for the import, @ide@
    automatically generates its import statement. Otherwise, a wizard appears
    that lets you select the correct import. 

2.  **Class moved to a module that's *not* in the classpath**: You must resolve
    the new module as a dependency for your project. This requires identifying
    the module and specifying your project's dependency on it. 

3.  **Class replaced or removed**: The class has been replaced by another class
    or removed from the product. The Code Upgrade Tool (discussed later)
    explains what happened to the class, how to handle the change, and why the 
    change was made.

Resolving a class that's moved within your classpath is straightforward. 
Consider resolving such classes first. The remainder of this tutorial explains 
how to resolve the last two cases and starts with configuring your plugin 
project to declare the modules it needs. 

## Identifying Module Dependencies [](id=identifying-module-dependencies)

Before @product-ver@, all the platform APIs were in `portal-service.jar`. Many 
of these APIs are now in independent modules. Modularization has resulted in 
many benefits, as described in the article 
[Benefits of @product-ver@ for Liferay Portal 6 Developers](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers#modular-development-paradigm). 
One such advantage is that these API modules can evolve separately from the
platform kernel. They also simplify future upgrades. For example, instead of
having to check all of Liferay's APIs, each module's 
[Semantic Versioning](http://semver.org) 
indicates whether the module contains any backwards-incompatible changes. You
need only adapt your code to such modules (if any). 

As part of the modularization, `portal-service.jar` has been renamed 
appropriately to `portal-kernel.jar`, as it continues to hold the portal 
kernel's APIs. 

![Figure 1: Liferay refactored the portal-service JAR for @product-ver@. Application APIs now exist in their own modules, and the portal-service JAR is now *portal-kernel*.](../../../images/from-liferay-6-portal-apis-before-after.png)

Each app module consists of a set of classes that are highly cohesive and have
a specific purpose, such as providing the app's API, implementation, or UI. The 
app modules are therefore much easier to understand. Next, you'll track down the 
modules that now hold the classes referenced by your plugin. 

The reference article 
[Classes Moved from `portal-service.jar`](/develop/reference/-/knowledge_base/7-0/classes-moved-from-portal-service-jar) 
contains a table that maps each class moved from `portal-service.jar` to its new 
module. The table includes each class's new package and symbolic name 
(artifact ID). You'll use this information to configure your plugin's 
dependencies on these modules. 

Your plugin might reference classes that are in Liferay utility modules such as 
`util-java`, `util-bridges`, `util-taglib`, or `util-slf4j`. 

The following table shows each Liferay utility module's symbolic name.

  **Liferay Utility** |  &nbsp;**Symbolic Name (Artifact ID)** |
:---------------------- | :----------------------------------------- |
 util-bridges           |  `com.liferay.util.bridges` |
 util-java               | `com.liferay.util.java` |
 util-slf4j               | `com.liferay.util.slf4j` |
 util-taglib             | `com.liferay.util.taglib` |

You can use @product@'s
[App Manager](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies#finding-liferay-portal-app-and-independent-artifacts),
[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell),
or
[module JAR file manifests](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies#finding-core-liferay-portal-artifacts)
to find versions of modules deployed on your @product@ instance. 

+$$$

**Note:** Previous versions of the Plugins SDK made `portal-service.jar` 
available to projects. The @product-ver@ Plugins SDK similarly makes 
`portal-kernel.jar` available. If you're using a @product@ bundle (@product@ 
pre-installed on an app server), the Liferay utility modules are already on your 
classpath. If you manually installed @product@ on your app server, the Liferay 
utility modules might not be on your classpath. If a utility module you need is 
not on your classpath, note its symbolic name (artifact ID) and version. 

$$$

## Resolving Dependencies [](id=resolving-dependencies)

Now that you have the module artifact IDs and versions, you can make the modules
available to your plugin project. The modules your plugin uses must be available
to it at compile time and run time. Here are two options for resolving module
dependencies in your traditional plugin project: 

[**Option 1: Use a dependency management tool**](#managing-dependencies-with-ivy)

[**Option 2: Manage dependencies manually**](#managing-dependencies-manually)

The next sections explain and demonstrate these options. 

### Using a Dependency Management Tool [](id=managing-dependencies-with-ivy)

Dependency management tools such as [Ant/Ivy](http://ant.apache.org/ivy/), [Maven](/develop/tutorials/-/knowledge_base/7-0/maven), and
[Gradle](https://gradle.org/)
facilitate acquiring Java artifacts that provide packages your plugins need.
They can download artifacts from public repositories or from internal
repositories you configure as a proxies. From internal repositories you can
audit dependencies. 

+$$$

The following links provide proxy details:

- [Ant/Ivy](http://ant.apache.org/ivy/) - See documentation on proxy configuration, the `Setproxy` task, and [resolvers](http://ant.apache.org/ivy/history/latest-milestone/settings/resolvers.html)
- [Maven](/develop/tutorials/-/knowledge_base/7-0/creating-a-maven-repository)
- [Liferay Workspace \(Gradle\)](/develop/tutorials/-/knowledge_base/7-0/setting-proxy-requirements-for-liferay-workspace)
- [Setting proxies in Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/setting-proxy-requirements-for-liferay-ide)

$$$

The Liferay Plugins SDK provides an Ant/Ivy infrastructure. You declare
your dependencies in an `ivy.xml` file in your plugin project's root folder. The
Plugins SDK's Ant tasks leverage the `ivy.xml` file and the Plugins SDK's Ivy 
scripts to download the specified modules and their dependencies and make them 
available to your plugin.

+$$$

**Note**: You can use Gradle or Maven in place of Ivy for dependency management, 
but this isn't in this tutorial's scope. Liferay's
[Maven](/develop/tutorials/-/knowledge_base/7-0/maven) and
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
tutorials demonstrate using these tools. 

Additionally, Liferay Workspace provides a command for migrating Ant/Ivy
projects to Gradle-based Liferay Workspace projects. See the tutorial
[Migrating Traditional Plugins to Workspace Web Applications](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications).

$$$

Here's an example dependency element for the Liferay Journal API module, version 
2.0.1: 

    <dependency name="com.liferay.journal.api" org="com.liferay" rev="2.0.1" />

Each dependency includes the module's name (`name`), organization (`org`), and
revision number (`rev`). The 
[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) 
tutorial explains how to determine the module's organization (`org`). 

At compile time, Ivy downloads the dependency JAR files to a cache folder so you
can compile against them.

At deployment, @product@'s WAB Generator creates an OSGi Web Application Bundle
(WAB) for the plugin. The WAB generator detects the Java packages your plugin
uses and declares dependencies on them. Your plugin can use the packages once a
registered OSGi service provides them. 

If your project doesn't already have an `ivy.xml` file, you can get one by 
creating a new plugin project in Liferay @ide@ and copying the `ivy.xml` file it 
generates. 

Here's an example of an `ivy.xml` file from the Liferay Portal 6.2 Knowledge
Base portlet:

    <?xml version="1.0"?>

    <ivy-module
        version="2.0"
        xmlns:m2="http://ant.apache.org/ivy/maven"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://ant.apache.org/ivy/schemas/ivy.xsd"
    >
        <info module="knowledge-base-portlet" organisation="com.liferay">
            <extends extendType="configurations,description,info" location="${sdk.dir}/ivy.xml" module="com.liferay.sdk" organisation="com.liferay" revision="latest.integration" />
        </info>

        <dependencies defaultconf="default">
            <dependency org="com.liferay" name="com.liferay.markdown.converter" rev="1.0.2" />
        </dependencies>
    </ivy-module>

The Plugins SDK works with project Ivy files to store artifacts and make them
accessible to your plugin projects. 

If you don't want to use Ivy or some other dependency management framework, you
can store dependency JARs within your plugin project manually. You'll learn
about this next. 

### Managing Dependencies Manually [](id=managing-dependencies-manually)

Plugins rely on their dependencies' availability at compile time and run time.
To compile your plugin, you must make sure the dependencies are available in the
plugin's `WEB-INF/lib` folder. To run your plugin, the container must be able to
find them: either 1) the dependency Java packages must already be active in
@product@'s OSGi framework or 2) the dependency JARs must be included in the WAB
generated for the plugin. Your plugin can use both the JARs it currently has and
the packages @product@ exports. 

#### Using Packages @product@ Exports [](id=using-packages-liferay-portal-exports)

The Plugins SDK for Liferay Portal 6 provided a way to compile against JARs it
had. You'd specify these JARs in  the `portal-dependency-jars` property in your
[`liferay-plugin-package.properties`](@platform-ref@/7.0-latest/propertiesdoc/liferay-plugin-package_7_0_0.properties.html)
file. On seeing a plugin's `portal-dependency-jars` list, the Liferay Plugins
SDK copied the JARs into the plugin's `WEB-INF/lib`. The Plugins SDK refrained
from adding the JARs to the plugin WAR. This kept the WARs small for deploying
faster. It was especially useful for deploying WARs remotely or to cluster
nodes. 

In @product-ver@, the `portal-dependency-jars` property is deprecated and
behaves differently from previous versions. Because importing and exporting Java
packages has replaced wholesale use of JARs, modules and WABs can import
packages without concerning themselves with JARs. This means that @product@
can't make available to plugins the same Java classes it did in the past. For a
list of packages @product-ver@ exports to modules, look at the
`META-INF/system.packages.extra.bnd` file in
`[LIFERAY_HOME]/osgi/core/com.liferay.portal.bootstrap.jar`. 

If you're still using the `portal-dependency-jars` property, you may run into
one of the scenarios below. Follow the instructions below the scenario to fix
the issue. 

1.  **I've specified a JAR, but in @product-ver@ none of the classes are
    available to my plugin.**
 
    Some JARs that Liferay Portal 6.2 used were removed in @product-ver@. If you
    specify them in your `portal-dependency-jars`, @product@ can't provide them.
    If you still need them, remove them from the `portal-dependency-jars`
    property and add the JARs you need to your plugin's `WEB-INF/lib` folder. 

2.  **I've specified JARs, and @product-ver@ also exports all the JAR's packages
    my plugin imports**

    Keep the JAR in your `portal-dependency-jars` list. The Plugins SDK copies the
    JAR to your plugin's `WEB-INF/lib` folder at compile time but refrains from
    adding the JAR to the plugin WAB. The WAB generated for the plugin imports the
    packages from a registered provider at run time. 

3.  **@product-ver@ provides the JAR but doesn't export a package my plugin imports**

    Keep the JAR in your `portal-dependency-jars` property. The Plugins SDK copies
    the JAR to your plugin's `WEB-INF/lib` folder at compile time and adds the JAR
    to the plugin WAB at deployment. 

+$$$

**Note**: The portal property `module.framework.web.generator.excluded.paths`
declares JAR file paths that are excluded from all @product@ generated WABs.
All JARs listed for this property are excluded from the WABs, even if the
plugins listed the JAR in their `portal-dependency-jars` property. Exercise
great care if you modify the `module.framework.web.generator.excluded.paths`
property. Altering the property can result in undefined behavior and might
adversely affect your @product@ run time environment. 

$$$

#### Using Packages @product@ Doesn't Export [](id=using-packages-liferay-portal-doesnt-export)

You must download and install to your plugin's `WEB-INF/lib` folder JARs that
provide packages @product@ doesn't export that your plugin requires. 

Follow these steps to do this: 

1.  Go to Maven Central at 
    [https://search.maven.org/](https://search.maven.org/). 

2.  Search for the module by its artifact ID and group ID.

3.  Navigate the search results to find the version of the module you want. 

4.  Click the *jar* link to download the module's JAR file. 

5.  Add the JAR to your project's `WEB-INF/lib` folder.

![Figure 2: After searching Maven Central, download an artifact's JAR file by clicking the *jar* link.](../../../images/resolving-plugin-dependencies-using-maven-central.png)

As you manage module JARs, make sure **not** to deploy any OSGi framework JARs 
or Liferay module JARs (e.g., `com.liferay.journal.api.jar`). If you deploy 
these, they'll conflict with the JARs already installed in the OSGi framework. 
Identical JARs in two different classloaders can cause class cast exceptions. 
The easiest way to exclude such JARs from your plugin's deployment is to list
them in a `deploy-excludes` property in your plugin's 
`liferay-plugin-package.properties`. You must otherwise remove the JARs manually
from the plugin WAR file. To exclude JARs in your plugin's 
`liferay-plugin-package.properties` file, add an entry like the one below, 
replacing the square-bracketed items with the names of JAR files to exclude: 

    deploy-excludes=\
        **/WEB-INF/lib/[module-artifact.jar],\
        **/WEB-INF/lib/[another-module-artifact.jar]

For example, here's an example property that excludes the OSGi framework JAR 
`osgi.core.jar` and the Liferay app module JAR `com.liferay.journal.api.jar`: 

    deploy-excludes=\
        **/WEB-INF/lib/com.liferay.portal.journal.api.jar,\
        **/WEB-INF/lib/org.osgi.core.jar

How do you know what modules are already installed in @product@? If your 
@product@ instance has a particular Liferay app suite installed, then don't 
deploy module JARs you know are in that app suite. For example, if the Web 
Experience Management App Suite is already installed (which is the case for a 
@product@ bundle), then don't deploy Web Content module JARs such as 
`com.liferay.journal.api.jar`. Searching for a module in @product@'s 
[App Manager](/discover/portal/-/knowledge_base/7-0/managing-and-configuring-apps) 
is a sure-fire way to verify existing module installations. 

Way to go! You've fixed class imports and resolved dependencies on all the 
modules your plugin uses. 

## Related Topics [](id=related-topics)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)

[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)

[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)
