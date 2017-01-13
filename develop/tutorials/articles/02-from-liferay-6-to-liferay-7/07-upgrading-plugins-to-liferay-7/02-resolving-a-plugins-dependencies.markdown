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
[Benefits of @product-ver@ for Liferay Portal 6 Developers](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers#modular-development-paradigm) 
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
contains a table that maps each class moved from `portal-service.jar` to its
new module. The table includes each class's new package and the module's version
at the time of @product@'s latest release. For each class, the table contains 
the following items: 

* *Package*: The class' Java package
* *Module Symbolic Name*: The module's unique identifier
* *Module Version*: The module's version identifier

You'll use this information to manage your plugin's dependencies on these 
modules. 

+$$$

**IMPORTANT**: The module versions in the @product@ source code are currently 
one micro-version higher than what's actually in that @product@ release. For 
example, even though the source code for the Liferay Journal API module 
specifies version 2.0.2, version 2.0.1 is bundled with the @product@ release and 
is therefore the version you should specify. The module versions are incremented 
for the module's next release to make sure a new version is associated with all 
module changes. **Make sure to specify one module micro-version lower** than 
what's listed in the @product@ source code and in the reference table. 

$$$

Your plugin might reference classes that are in Liferay utility modules such as 
`util-java`, `util-bridges`, `util-taglib`, or `util-slf4j`. The module 
artifacts are listed on the 
[MVNRepository site](http://mvnrepository.com/artifact/com.liferay.portal). 
All versions of each module are available. When you click a module version, the 
site shows options for the Ivy, Maven, or Gradle dependency elements to specify 
in your project. Note the Ivy dependency information, as the next section 
demonstrates using Ivy. 

![Figure 2: The MVNRepository site conveniently presents module dependency elements for Ivy, Maven, Gradle, and more.](../../../images/from-liferay-6-mvnrepository-entry.png)

The following table shows each Liferay utility module's symbolic name, which
links to its artifacts. 

  **Liferay Utility** |  &nbsp;**Module Symbolic Name** |
:---------------------- | :----------------------------------------- |
 util-bridges           |  [`com.liferay.util.bridges`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.util.bridges) |
 util-java               | [`com.liferay.util.java`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.util.java) |
 util-slf4j               | [`com.liferay.util.slf4j`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.util.slf4j) |
 util-taglib             | [`com.liferay.util.taglib`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.util.taglib) |
 
As you inspect the module artifacts, note the *module symbolic name* and 
*version* of the modules you use. Module dependency configuration is explained 
shortly. 

+$$$

**Note:** Previous versions of the Plugins SDK made `portal-service.jar` 
available to projects. The @product-ver@ Plugins SDK similarly makes 
`portal-kernel.jar` available. If you're using a @product@ bundle (@product@ 
pre-installed on an app server), the Liferay utility modules are already on your 
classpath. If you manually installed @product@ on your app server, the Liferay 
utility modules might not be on your classpath. If a utility module you need is 
not on your classpath, note its dependency elements. 

$$$

Now that you have the module symbolic names and versions, you can make the 
modules available to your plugin project. The modules your plugin uses must be 
available to it at compile time and run time. Here are two options for resolving 
module dependencies in your traditional plugin project: 

**Option 1: Use Ivy**

**Option 2: Work with dependency JAR files manually**

The next sections explain and demonstrate these options. 

## Managing Dependencies with Ivy [](id=managing-dependencies-with-ivy)

Apache Ivy provides an elegant approach to managing dependencies. You declare
your dependencies in an `ivy.xml` file in your plugin project's root folder. The
Plugins SDK's Ant tasks leverage the `ivy.xml` file and the Plugins SDK's Ivy 
scripts to download the specified modules and their dependencies and make them 
available to your plugin.

+$$$

**Note**: You can use Gradle or Maven in place of Ivy for dependency management, 
but this isn't in this tutorial's scope. 

$$$

Here's an example dependency element for the Liferay Journal API module, version 
2.0.1: 

    <dependency name="com.liferay.journal.api" org="com.liferay" rev="2.0.1" />

Each dependency includes the module's name (`name`), organization (`org`), and
revision number (`rev`). All Liferay modules use the organization (`org`) value 
`"com.liferay"`. 

At compile time, Ivy downloads the dependency JAR files to a cache folder so you
can compile against them. At deployment, @product@'s OSGi framework installs and 
registers the dependency modules for runtime access. 

+$$$

**IMPORTANT**: Make sure to specify module version numbers *one micro-version
lower* than what's specified in the @product@ source code. The source code lists
one micro-version higher than what's associated with the @product@ release. 

$$$

If your project doesn't already have an `ivy.xml` file, you can get one by 
creating a new plugin project in @ide@ and copying the `ivy.xml` file it 
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

If you don't want to use Ivy, you can store dependency JARs within your plugin
project manually. You'll learn about this next. 

## Managing Dependencies Manually [](id=managing-dependencies-manually)

Managing dependencies manually is also possible. If you're already managing 
dependencies using Ivy or some other dependency management framework, skip this
section and continue at the section *Adapting to the API with the Code Upgrade
Tool*. 

Manual dependency management involves downloading dependency JAR files and 
adding them to your project's `WEB-INF/lib` folder. Liferay JARs are available 
on the MVNRepository site at 
[http://mvnrepository.com/artifact/com.liferay](http://mvnrepository.com/artifact/com.liferay.portal). 
The repository organizes modules and their artifacts in folders named after
module symbolic names and versions. For example, the Journal API module's 
artifacts are accessible at 
[http://mvnrepository.com/artifact/com.liferay/com.liferay.journal.api/2.0.1](http://mvnrepository.com/artifact/com.liferay/com.liferay.journal.api/2.0.1). 
To download a module's JAR file, click the *Download (JAR)* link. For example, 
clicking this link for the Journal API module downloads its JAR file 
`com.liferay.journal.api-2.0.1.jar`. Clicking a module's *Repositories* link 
(e.g., link named *Central*) takes you to the module's Maven Central repository 
page. 

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
