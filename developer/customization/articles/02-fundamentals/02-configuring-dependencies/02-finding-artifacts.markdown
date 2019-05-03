---
header-id: finding-artifacts
---

# Finding Artifacts

[TOC levels=1-4]

Using external artifacts in your project requires configuring their
dependencies. To do this, look up the artifact's attributes and plug them into
dependency entries for your build system (either 
[Gradle](https://gradle.org/)
or
[Maven](https://maven.apache.org/)).
Your build system downloads the dependency artifacts your project needs to
compile successfully. 

Before specifying an artifact as a dependency, you must first find its
attributes. Artifacts have these attributes: 

-   *Group ID*: Authoring organization 
-   *Artifact ID*: Name/identifier 
-   *Version*: Release number 

Here you'll learn how to find artifact attributes to specify artifact dependencies. 

## Finding Core @product@ Artifact Attributes

Each Liferay artifact is a JAR file whose `META-INF/MANIFEST.MF` file specifies
OSGi bundle metadata the artifact's attributes. For example, these two OSGi
headers specify the artifact ID and version: 

    Bundle-SymbolicName:  [artifact ID]
    Bundle-Version: [version]

| **Important:** Artifacts in @product@ fix packs override @product@
| installation artifacts. Subfolders of a fix pack ZIP file's `binaries` folder
| hold the artifacts. If an installed fix pack provides an artifact you depend | on, specify the version of that fix pack artifact in your dependency.

This table lists each core @product@ artifact's group ID and artifact ID and
where to find the artifact's manifest, which lists the artifact version: 

*Core @product@ Artifacts*:

| File          | Group ID | Artifact ID | Version | Origin | 
| :------------ | :--------------- | :-------- | :--------- | :------ |
| `portal-kernel.jar` | `com.liferay.portal` | `com.liferay.portal.kernel` | (see JAR's `MANIFEST.MF`) | fix pack ZIP, @product@ installation, or @product@ dependencies ZIP |
| `portal-impl.jar` | `com.liferay.portal` |  `com.liferay.portal.impl` | (see JAR's `MANIFEST.MF`) | fix pack ZIP or @product@ `.war` |
| `util-bridges.jar` | `com.liferay.portal` | `com.liferay.util.bridges` | (see JAR's `MANIFEST.MF`) |  fix pack ZIP or @product@ `.war` |
| `util-java.jar` | `com.liferay.portal` |  `com.liferay.util.java` | (see JAR's `MANIFEST.MF`) |  fix pack ZIP or @product@ `.war` |
| `util-slf4j.jar` | `com.liferay.portal` |  `com.liferay.util.slf4j` | (see JAR's `MANIFEST.MF`) | fix pack ZIP or  @product@ `.war` |
| `util-taglibs.jar` | `com.liferay.portal` | `com.liferay.util.taglib` | (see JAR's `MANIFEST.MF`) | fix pack ZIP or @product@ `.war` |
| `com.liferay.*` JAR files | `com.liferay` | (see JAR's `MANIFEST.MF`) | (see JAR's `MANIFEST.MF`) | fix pack ZIP, @product@ installation, @product@ dependencies ZIP, or the OSGi ZIP |

Next, you'll learn how to find @product@ app and independent module artifact
attributes. 

## Finding @product@ App and Independent Artifacts

Independent modules and @product@ app modules aren't part of the  @product@
core. You must still, however, find their artifact attributes if you  depend on
them. The resources below provide the  artifact details for @product@'s apps and
independent modules: 

| Resource | Artifact Type |
| :-------- | :-------------- |
| [App Manager](#products-app-manager) | Deployed modules |
| [Reference Docs](#reference-docs) | @product@ modules (per release) |
| [Maven Central](#maven-central) | All artifact types: @product@ and third party, module and non-module |

| **Important**: `com.liferay` is the group ID for all of Liferay's apps and
| independent modules.

The App Manager is the best source for information on deployed modules. You'll 
learn about it next. 

### @product@'s App Manager

[The App Manager](/docs/7-2/user/-/knowledge_base/u/managing-and-configuring-apps#using-the-app-manager) 
knows what's deployed on your Liferay instance. Use it to find deployed module
attributes. 

1.  In @product@, navigate to *Control Panel* &rarr; *Apps* &rarr; *App 
    Manager*. 

2.  Search for the module by its display name, symbolic name, or related
    keywords. You can also browse for the module in its app. Whether browsing
    or searching, the App Manager shows the module's artifact ID and version
    number. 

![Figure 1: You can inspect deployed module artifact IDs and version numbers.](../../../images/configuring-dependencies-search-app-manager-for-module.png)

![Figure 2: The App Manager aggregates Liferay and independent modules.](../../../images/configuring-dependencies-indep-modules-in-app-manager.png)

If you don't know a deployed module's group ID, use the 
[Felix Gogo Shell](/docs/7-2/reference/-/knowledge_base/r/using-the-felix-gogo-shell) 
to find it: 

1.  Navigate to the Gogo Shell portlet in the Control Panel &rarr;
    *Configuration* &rarr; *Gogo Shell*. Enter commands in the Felix Gogo Shell
    command prompt. 

2.  Search for the module by its display name (e.g., `Liferay Blogs API`) or
    a keyword. In the results, note the module's number. You can use it in the
    next step. For example, Gogo command results in the figure below show the
    Liferay Blogs API module number. 

    ![Figure 3: Results from this Gogo command show that the module's number is `1173`.](../../../images/configuring-deps-gogo-grep-for-module.png)

3.  List the module's manifest headers by passing the module number to the 
    `headers` command. In the results, note the `Bundle-Vendor` value: you'll 
    match it with an artifact group in a later step: 

    ![Figure 4: Results from running the `headers` command show the module's bundle vendor and bundle version.](../../../images/configuring-deps-gogo-module-info.png)

5.  On
    [Maven Central](https://search.maven.org/)
    or
    [MVNRepository](https://mvnrepository.com),
    search for the module by its artifact ID. 

6.  Determine the group ID by matching the `Bundle-Vendor` value from step 3 
    with a group listed that provides the artifact. 

Next, @product@'s reference documentation provides @product@ app artifact
attributes. 

### Reference Docs

@product@'s app Javadoc lists each app module's artifact ID, version number, and
display name. This is the best place to look up @product@ app modules that
aren't yet deployed to your @product@ instance. 

| **Note:** To find artifact information on a Core @product@ artifact, refer to
| the previous section *Finding Core @product@ Artifact Attributes*.

Follow these steps to find a @product@ app module's attributes in the Javadoc: 

1.  Navigate to Javadoc for an app module class. If you don't have a link to the
    class's Javadoc, find it by browsing 
    [@app-ref@](@app-ref@). 

2.  Copy the class's package name. 

3.  Navigate to the *Overview* page. 

4.  On the *Overview* page, search for the package name you copied in step 2. 

The heading above the package name shows the module's artifact ID, version 
number, and display name. Remember, the group ID for all app modules is 
`com.liferay`. 

![Figure 5: @product@ app Javadoc overviews list each app module's display name, followed by its group ID, artifact ID, and version number in a colon-separated string. It's a Gradle artifact syntax.](../../../images/intro-configuring-dependencies-module-info-in-javadoc-overview.png)

| **Note**: Module version numbers aren't currently included in any tag library
| reference docs.

Next, you'll learn how to look up artifacts on MVNRepository and Maven  Central. 

### Maven Central

Most artifacts, regardless of type or origin, are on 
[MVNRepository](https://mvnrepository.com/)
and
[Maven Central](https://search.maven.org/).
These sites can help you find artifacts based on class packages. It's common to
include an artifact's ID in the start of an artifact's package names. For
example, if you depend on the class
`org.osgi.service.component.annotations.Component`, search for the package name
`org.osgi.service.component.annotations` on one of the Maven sites. 

| **Note:** Make sure to follow the instructions listed earlier to determine the
| version of Liferay artifacts you need.

Now that you know the artifact's attributes, you can configure a  dependency on
it. 
