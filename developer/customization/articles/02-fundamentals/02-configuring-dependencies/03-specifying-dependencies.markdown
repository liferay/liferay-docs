---
header-id: specifying-dependencies
---

# Specifying Dependencies

[TOC levels=1-4]

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
    | Framework | Group ID | Artifact ID | Version |
    | :------------ | :----------- | :----------- | :-------- |
    | Gradle | `group` | `name` | `version` 
    | Maven | `groupId` | `artifactId` | `version` |

    Here is an example dependency on Liferay's Journal API module for Gradle,
    and Maven: 

    *Gradle (`build.gradle` entry):*
    
        dependencies {
            compileOnly group: "com.liferay", name: "com.liferay.journal.api", version: "1.0.1"
            ...
        }
    
    *Maven (`pom.xml` entry):*
    
    ```xml
    <dependency>
        <groupId>com.liferay</groupId>
        <artifactId>com.liferay.journal.api</artifactId>
        <version>1.0.1</version>
        <scope>provided</scope>
    </dependency>
    ```

| **Note**: To configure third-party libraries in a module, see
| [Adding Third Party Libraries to a Module](/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module). 

Nice! You know how to specify artifact dependencies. Now that's a skill you can
depend on! 

## Related Topics 

[Finding Artifacts](/docs/7-2/customization/-/knowledge_base/c/finding-artifacts)

[Importing Packages](/docs/7-2/customization/-/knowledge_base/c/importing-packages)

[Exporting Packages](/docs/7-2/customization/-/knowledge_base/c/exporting-packages)

[Resolving Third Party Library Package Dependencies](/docs/7-2/customization/-/knowledge_base/c/resolving-third-party-library-package-dependencies)

[Deploying WARs \(WAB Generator\)](/docs/7-2/customization/-/knowledge_base/c/deploying-wars-wab-generator)
