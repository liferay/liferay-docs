# Configuring Dependencies [](id=configuring-dependencies)

Using external modules in your project requires configuring dependencies on
them. To do this, look up the modules' attributes and plug them into dependency
entries for your build system (either 
[Gradle](https://gradle.org/), 
[Maven](https://maven.apache.org/), or 
[Ant/Ivy](http://ant.apache.org/ivy/)). Your build system downloads the 
dependency artifacts your project needs to compile successfully. 

Before specifying a module as a dependency, you must first find its artifact 
attributes. Artifacts have these attributes: 

-   *Group ID*: Authoring organization 
-   *Artifact ID*: Name/identifier 
-   *Version*: Release number 

This tutorial shows you how to make sure your projects have the right
dependencies: 

-   [Find Core @product@ artifacts](#finding-core-liferay-portal-artifacts)
-   [Find @product@ app and independent artifacts](#finding-liferay-portal-app-and-independent-artifacts)
-   [Configure dependencies](#configuring-dependencies)

## Finding Core @product@ Artifacts [](id=finding-core-liferay-portal-artifacts)

Each module is composed of a `.jar` file that contains the module's OSGi
metadata in a `MANIFEST.MF` file. You can find the artifact attributes for
@product@'s core modules inside this file. The manifest also specifies the
module's artifact attributes. For example, these two OSGi headers specify the
module's artifact ID and version: 

    Bundle-SymbolicName:  artifact ID
    Bundle-Version: version

This table lists the group ID, artifact ID, version, and origin for each core
@product@ artifact: 

*Core @product@ Artifacts*:

 File          | Group ID | Artifact ID | Version | Origin | 
:------------ | :--------------- | :-------- | :--------- | :------ |
 `portal-kernel.jar` | `com.liferay.portal` | `com.liferay.portal.kernel` | (see JAR's `MANIFEST.MF`) | @product@ dependencies ZIP |
 `portal-impl.jar` | `com.liferay.portal` |  `com.liferay.portal.impl` | (see JAR's `MANIFEST.MF`) | @product@ `.war` |
 `portal-test.jar` | `com.liferay.portal` |  `com.liferay.portal.test` | (see JAR's `MANIFEST.MF`) | @product@ `.war` |
 `portal-test-integration.jar` | `com.liferay.portal` |  `com.liferay.portal.test.integration` | (see JAR's `MANIFEST.MF`) | @product@ `.war` |
 `util-bridges.jar` | `com.liferay.portal` | `com.liferay.util.bridges` | (see JAR's `MANIFEST.MF`) |  @product@ `.war` |
 `util-java.jar` | `com.liferay.portal` |  `com.liferay.util.java` | (see JAR's `MANIFEST.MF`) |  @product@ `.war` |
 `util-slf4j.jar` | `com.liferay.portal` |  `com.liferay.util.slf4j` | (see JAR's `MANIFEST.MF`) |  @product@ `.war` |
 `util-taglibs.jar` | `com.liferay.portal` | `com.liferay.util.taglib` | (see JAR's `MANIFEST.MF`) | @product@ `.war` |
 `com.liferay.*` JAR files | `com.liferay` | (see JAR's `MANIFEST.MF`) | (see JAR's `MANIFEST.MF`) | @product@ dependencies ZIP and the OSGi ZIP |

Next, you'll learn how to find artifacts for @product@ apps and independent 
modules. 

## Finding @product@ App and Independent Artifacts [](id=finding-liferay-portal-app-and-independent-artifacts)

Independent modules and modules that make up @product@'s apps aren't part of the
core. You must still, however, find their artifact attributes if you want to
declare dependencies on them. The resources below provide the artifact details
for @product@'s apps and independent modules: 

 Resource | Artifact Type |
:-------- | :-------------- |
[App Manager](#liferay-portals-app-manager) | Deployed modules |
[Reference Docs](#reference-docs) | @product@ modules (per release) |
[Maven Central](#maven-central) | All artifact types: @product@ and third party, module and non-module |

+$$$

**Important**: `com.liferay` is the group ID for all of Liferay's apps and 
independent modules. 

$$$

The App Manager is the best source for information on deployed modules. You'll 
learn about it next. 

### @product@'s App Manager [](id=liferay-portals-app-manager)

[The App Manager](/discover/portal/-/knowledge_base/7-1/managing-and-configuring-apps#using-the-app-manager) 
knows what's deployed on your Liferay instance. You can use it to find whatever
modules you're looking for. 

Follow these steps to get a deployed module's information: 

1.  In @product@, navigate to *Control Panel* &rarr; *Apps* &rarr; *App 
    Manager*. 

2.  Search for the module by its display name, symbolic name, or related
    keywords. You can also browse for the module in its app or app suite. 
    Whether browsing or searching, the App Manager shows the module's artifact 
    ID and version number. 

![Figure 1: You can inspect deployed module artifact IDs and version numbers.](../../images/intro-configuring-dependencies-search-app-manager-for-module.png)

![Figure 2: The App Manager aggregates Liferay and independent modules.](../../images/intro-configuring-dependencies-indep-modules-in-app-manager.png)

If you don't know a deployed module's group, use the 
[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell) 
to find it: 

1.  Open a Gogo Shell session by entering the following into a command prompt: 

        telnet localhost 11311

    This results in a `g!`: the Felix Gogo Shell command prompt. 

2.  Search for the module by its display name (e.g., `Liferay Bookmarks API`) or
    a keyword. In the results, note the module's number. You can use it in the 
    next step. For example, these results show the Liferay Bookmarks API
    module's number is `52`: 

        g! lb | grep "Liferay Bookmarks API"

           52|Active     |   10|Liferay Bookmarks API (2.0.1)

3.  To list the module's manifest headers, pass the module number to the 
    `headers` command. In the results, note the `Bundle-Vendor` value: you'll 
    match it with an artifact group in a later step: 

        g! headers 52
    
        Liferay Bookmarks API (52)
        --------------------------
        Manifest-Version = 1.0
        Bnd-LastModified = 1464725366614
        Bundle-ManifestVersion = 2
        Bundle-Name = Liferay Bookmarks API
        Bundle-SymbolicName = com.liferay.bookmarks.api
        Bundle-Vendor = Liferay, Inc.
        Bundle-Version = 2.0.1
        ... 

4.  Disconnect from the Gogo Shell session: 

        g! disconnect

5.  On [Maven Central](https://search.maven.org/), search for the module by its
    artifact ID. 

6.  Determine the group ID by matching the `Bundle-Vendor` value from step 3 
    with a group listed that provides the artifact. 

Now you know to find a module's symbolic name, version, and group ID using the
App Manager and Gogo Shell.  

Next, you'll learn how to use @product@'s reference documentation to find a 
Liferay app module's attributes. 

### Reference Docs [](id=reference-docs)

@product@'s app Javadoc lists each module's artifact ID, version number, and 
display name. This is the best place to look up Liferay modules that aren't 
yet deployed to your Liferay instance. 

Follow these steps to find a Liferay app module's attributes in the Javadoc: 

1.  Navigate to Javadoc for an app module class. If you don't have a link to the
    class's Javadoc, find it by browsing 
    [@app-ref@](@app-ref@). 

2.  Copy the class's package name. 

3.  Navigate to the *Overview* page. 

4.  On the *Overview* page, search for the package name you copied in step 2. 

The heading above the package name shows the module's artifact ID, version 
number, and display name. Remember, the group ID for all app modules is 
`com.liferay`. 

![Figure 3: @product@ app Javadoc overviews list each module's display name, followed by its group ID, artifact ID, and version number in a colon-separated string. It's using Gradle artifact syntax.](../../images/intro-configuring-dependencies-module-info-in-javadoc-overview.png)

+$$$

**Note**: Module version numbers aren't currently included in any tag library
reference docs. 

$$$

Next, you'll learn how to look up Liferay and non-Liferay artifacts on Maven 
Central. 

### Maven Central [](id=maven-central)

Most artifacts, regardless of type or origin, are on Maven Central. Use these 
steps to find an artifact on Maven Central: 

1.  Go to Maven Central at 
    [https://search.maven.org/](https://search.maven.org/). 

2.  Search for the artifact. The search results list each artifact's attributes.

+$$$

**Tip**: The best way to find an artifact on Maven Central is to search for one 
of its Java packages. For example, to find an artifact containing the class 
`org.osgi.service.component.annotations.Component`, search for the package 
`org.osgi.service.component.annotations`. 

$$$

![Figure 4: You can search for artifacts on Maven Central.](../../images/intro-configuring-dependencies-search-maven-central.png)

Now that you have your artifact's attribute values, you're ready to configure a 
dependency on it. 

## Configuring Dependencies [](id=configuring-dependencies)

Specifying dependencies to build systems is straightforward. In your project's
build file, specify a dependency entry that includes the group ID, artifact ID,
and version number. 

+$$$

**Note**: There's a recommended [workflow for resolving dependencies on third party library packages](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module). 

$$$

Note that different build systems use different artifact attribute names, as
shown below: 

*Artifact Terminology*

Framework | Group ID | Artifact ID | Version |
:------------ | :----------- | :----------- | :-------- |
Gradle | `group` | `name` | `version` 
Maven | `groupId` | `artifactId` | `version` |
Ivy | `org` | `name` | `rev` |

The following examples demonstrate configuring a dependency on Liferay's Journal
API module for Gradle, Maven, and Ivy. 

### Gradle [](id=gradle)

Here's the dependency configured in a `build.gradle` file: 

    dependencies {
        compileOnly group: "com.liferay", name: "com.liferay.journal.api", version: "1.0.1"
        ...
    }

### Maven [](id=maven)

Here's the dependency configured in a `pom.xml` file: 

    <dependency>
        <groupId>com.liferay</groupId>
        <artifactId>com.liferay.journal.api</artifactId>
        <version>1.0.1</version>
    </dependency>

### Ivy [](id=ivy)

Here's the dependency configured in an `ivy.xml` file: 

    <dependency name="com.liferay.journal.api" org="com.liferay" rev="1.0.1" />

Nice! Now you know how to find artifacts and configure them as dependencies. Now 
that's a skill you can depend on! 

## Related Topics [](id=related-topics)

[Reference](/develop/reference/-/knowledge_base/7-1/development-reference)

[Liferay API Modules](/develop/reference/-/knowledge_base/7-1/finding-liferay-api-modules)

[Resolving Third Party Library Package Dependencies](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module)

[Classes Moved from portal-service.jar](/develop/reference/-/knowledge_base/7-1/classes-moved-from-portal-service-jar)

[Tooling](/develop/tutorials/-/knowledge_base/7-1/tooling)

[Portlets](/develop/tutorials/-/knowledge_base/7-1/portlets)
