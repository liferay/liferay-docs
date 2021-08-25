---
header-id: specifying-dependencies
---

# Specifying Dependencies

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120"> This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/liferay-internals/fundamentals/configuring-dependencies/specifying-dependencies.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Compiling your project and deploying it to @product@ requires satisfying its
dependencies on external artifacts. After
[finding the attributes of an artifact](/docs/7-2/customization/-/knowledge_base/c/finding-artifacts), 
set a dependency for it in your build file. Here's how: 

1.  Determine whether @product@ provides the Java packages you use from the 
    artifact. These files list the packages @product@ exports:

    -   `modules/core/portal-bootstrap/system.packages.extra.bnd` file in the
        [GitHub repository](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/core/portal-bootstrap/system.packages.extra.bnd).
        It lists exported packages on separate lines, making them easy to read. 

    -   `META-INF/system.packages.extra.mf` file in
        `[LIFERAY_HOME]/osgi/core/com.liferay.portal.bootstrap.jar`. The file is
        available in @product@ bundles. It lists exported packages in a
        paragraph wrapped at 70 columns--they're harder to read here than in the
        `system.packages.extra.bnd` file. 

2.  If @product@ exports all the packages you use from the artifact, specify the
    artifact as a compile-only dependency. This prevents your build framework
    from bundling the artifact with your project. Here's how to make the
    dependency compile-only:

    **Gradle:** Add the `compileOnly` directive to the dependency
    
    **Maven:** Add the `<scope>provided</scope>` element to the dependency. 

3.  Add a dependency entry for the artifact. Here's the artifact terminology for
    the Gradle and Maven build frameworks:

*Artifact Terminology*

| Framework | Group ID  | Artifact ID  | Version   |
| :-------- | :-------- | :----------- | :-------- |
| Gradle    | `group`   | `name`       | `version` |
| Maven     | `groupId` | `artifactId` | `version` |

Here is an example dependency on Liferay's Journal API module for Gradle,
and Maven: 

*Gradle (`build.gradle` entry):*

```groovy
dependencies {
    compileOnly group: "com.liferay", name: "com.liferay.journal.api", version: "1.0.1"
    ...
}
```

*Maven (`pom.xml` entry):*

```xml
<dependency>
    <groupId>com.liferay</groupId>
    <artifactId>com.liferay.journal.api</artifactId>
    <version>1.0.1</version>
    <scope>provided</scope>
</dependency>
```

| **Important:**
| [@product@ exports many third-party packages](/docs/7-2/reference/-/knowledge_base/r/third-party-packages-portal-exports).
| Deploy your module to check if @product@ or another module in your Liferay
| instance's OSGi runtime framework provides the package you need. If it's
| provided already, specify the corresponding dependency as being "provided".
| Here's how to specify a provided dependency:
| 
| Maven: `<scope>provided</scope>`
|  
| Gradle: `providedCompile`
|
| Don't deploy a provided package's JAR again or embed the JAR in  your project.
| Exporting the same package from different JARs leads to "split package" 
| issues, whose side affects differ from case to case. If the package is in a
| third-party library (not an OSGi module), refer to
| [Resolving Third
Party Library Dependencies](/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module). 
|
| If you're developing a WAR that requires a different version of a third-party 
| package that
| [@product@ or another module exports](/docs/7-2/reference/-/knowledge_base/r/third-party-packages-portal-exports),
| specify that package in your
| [`Import-Package:` list](/docs/7-2/customization/-/knowledge_base/c/importing-packages).
| If the package provider is an OSGi module, publish its exported packages by 
| deploying that module. Otherwise, follow the instructions for
| [adding a third-party library (not an OSGi module)](/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module). 

Nice! You know how to specify artifact dependencies. Now that's a skill you can
depend on! 

## Related Topics 

[Finding Artifacts](/docs/7-2/customization/-/knowledge_base/c/finding-artifacts)

[Importing Packages](/docs/7-2/customization/-/knowledge_base/c/importing-packages)

[Exporting Packages](/docs/7-2/customization/-/knowledge_base/c/exporting-packages)

[Resolving Third Party Library Package Dependencies](/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module)

[Deploying WARs \(WAB Generator\)](/docs/7-2/customization/-/knowledge_base/c/deploying-wars-wab-generator)
