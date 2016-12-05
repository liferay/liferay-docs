# TLDDoc Builder Gradle Plugin [](id=tlddoc-builder-gradle-plugin)

The TLDDoc Builder Gradle plugin lets you run the
[Tag Library Documentation Generator](http://web.archive.org/web/20070624180825/https://taglibrarydoc.dev.java.net/)
tool in order to generate documentation for the JSP Tag Library Descriptor (TLD)
files in your project.

The plugin has been successfully tested with Gradle 2.5 up to 3.2.1.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.tlddoc.builder", version: "1.2.0"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}
```

There are two TLDDoc Builder Gradle plugins you can apply to your project:

- Apply the [*TLDDoc Builder Plugin*](#tlddoc-builder-plugin) to generate tag
library documentation for your project:

    ```gradle
    apply plugin: "com.liferay.tlddoc.builder"
    ```

- Apply the [*App TLDDoc Builder Plugin*](#app-tlddoc-builder-plugin) in a
parent project to generate the tag library documentation as a single, combined
HTML document for an application that spans different subprojects, each one
representing a different component of the same application:

    ```gradle
    apply plugin: "com.liferay.app.tlddoc.builder"
    ```

Since the plugin automatically resolves the Tag Library Documentation Generator
library as a dependency, you must configure a repository that hosts the
library and its transitive dependencies. The Liferay CDN repository hosts them
all:

```gradle
repositories {
    maven {
        url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
    }
}
```

## TLDDoc Builder Plugin [](id=tlddoc-builder-plugin)

The plugin adds three tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
<a name="copytlddocresources"></a>`copyTLDDocResources` | \- | [`Copy`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Copy.html) | Copies the tag library documentation resources from `src/main/tlddoc` to the [destination directory](#destinationdir) of the `tlddoc` task.
`tlddoc` | `copyTLDDocResources`, `validateTLD` | [`TLDDocTask`](#tlddoctask) | Generates the tag library documentation.
<a name="validatetld"></a>`validateTLD` | \- | [`ValidateSchemaTask`](#validateschematask) | Validates the TLD files in the project.

The `tlddoc` task is automatically configured with sensible defaults,
depending on whether the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied:

Property Name | Default Value with the `java` plugin
------------- | -------------
[`destinationDir`](#destinationdir) | `${project.docsDir}/tlddoc`
[`includes`](#includes) | `["**/*.tld"]`
[`source`](#source) | `project.sourceSets.main.resources.srcDirs`

If the `java` plugin is applied, the `validateTLD` task is similarly configured
with the following sensible defaults:

Property Name | Default Value with the `java` plugin
------------- | -------------
[`includes`](#includes) | `["**/*.tld"]`
[`source`](#source) | `project.sourceSets.main.resources.srcDirs`

By default, the `tlddoc` task generates the documentation for all the TLD files
that are found in the resources directories of the `main` source set. The
documentation files are saved in `build/docs/tlddoc` and include the files
copied from `src/main/tlddoc`.

The `copyTLDDocResources` task lets you add references to images and other
resources directly in the TLD files. For example, if the project includes an
image called `breadcrumb.png` in the `src/main/tlddoc/images` directory, you can
reference it in a TLD file contained in the `src/main/resources` directory:

```xml
<description>Hello World <![CDATA[<img src="../images/breadcrumb.png"]]></description>
```

## App TLDDoc Builder Plugin [](id=app-tlddoc-builder-plugin)

In order to use the App TLDDoc Builder plugin, it is required to apply the
`com.liferay.app.tlddoc.builder` plugin in a parent project (that is, a project
that is a common ancestor of all the subprojects representing the various
components of the app). It is also required to apply the
[`com.liferay.tlddoc.builder`](#tlddoc-builder-plugin) plugin to all the
subprojects that contain TLD files.

The App TLDDoc Builder plugin automatically applies the [`base`](https://docs.gradle.org/current/userguide/standard_plugins.html#N135C1)
plugin. It also adds three tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`appTLDDoc` | `copyAppTLDDocResources`, the [`validateTLD`](#validatetld) tasks of the subprojects | [`TLDDocTask`](#tlddoctask) | Generates tag library documentation for the app.
`copyAppTLDDocResources` | \- | [`Copy`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Copy.html) | Copies the tag library documentation resources defined as [inputs](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/TaskInputs.html#getFiles()) for the [`copyTDLDocResources`](#copytlddocresources) tasks of the subprojects, aggregating them into the [destination directory](#destinationdir) of the `appTLDDoc` task.
`jarAppTLDDoc` | `appTLDDoc` | [`Jar`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Jar.html) | Assembles a JAR archive containing the tag library documentation files for this app.

The `appTLDDoc` task is automatically configured with sensible defaults:

Property Name | Default Value
------------- | -------------
[`destinationDir`](#destinationdir) | `${project.buildDir}/docs/tlddoc`
[`source`](#source) | The sum of all the `tlddoc.source` values of the subprojects

## Project Extension [](id=project-extension)

The App TLDDoc Builder plugin exposes the following properties through the
extension named `appTLDDocBuilder`:

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`subprojects` | `Set<Project>` | `project.subprojects` | The subprojects to include in the tag library documentation of the app.

The same extension exposes the following methods:

Method | Description
------ | -----------
`AppTLDDocBuilderExtension subprojects(Iterable<Project> subprojects)` | Include additional projects in the tag library documentation of the app.
`AppTLDDocBuilderExtension subprojects(Project... subprojects)` | Include additional projects in the tag library documentation of the app.

## Tasks [](id=tasks)

### TLDDocTask [](id=tlddoctask)

Tasks of type `TLDDocTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, such as [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args(java.tlddoc.Iterable))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize),
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | Tag Library Documentation Generator command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.tlddoc`](#tag-library-documentation-generator-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.sun.tlddoc.TLDDoc"`
[`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize) | `"256m"`

The `TLDDocTask` class is also very similar to [`SourceTask`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html),
which means it provides a `source` property and lets you specify include and
exclude patterns.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="destinationdir"></a>`destinationDir` | `File` | `null` | The directory where the tag library documentation files are saved.
`excludes` | `Set<String>` | `[]` | The TLD file patterns to exclude.
<a name="includes"></a>`includes` | `Set<String>` | `[]` | The TLD file patterns to include.
<a name="source"></a>`source` | [`FileTree`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileTree.html) | `[]` | The TLD files to generate documentation for, after the include and exclude patterns have been applied.
`xsltDir` | `File` | `null` | The directory which contains the custom XSLT stylesheets used by the Tag Library Documentation Generator to produce the final documentation files. It sets the `-xslt` argument.

The properties of type `File` support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file(java.tlddoc.Object)).

#### Task Methods [](id=task-methods)

The methods available for `TLDDocTask` are exactly the same as the one defined
in the [`SourceTask`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html)
class.

### ValidateSchemaTask [](id=validateschematask)

Tasks of type `ValidateSchemaTask` extend [`SourceTask`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html),
so all its properties and methods, such as [`include`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:include(java.lang.Iterable))
and [`exclude`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:exclude(java.lang.Iterable)),
are available.

Tasks of this type invoke the [`schemavalidate`](http://ant.apache.org/manual/Tasks/schemavalidate.html)
Ant task in order to validate XML files described by an XML schema.

#### Task Properties [](id=task-properties-0)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`dtdDisabled` | `boolean` | `false` | Whether to disable DTD support.
`fullChecking` | `boolean` | `true` | Whether to enable full schema checking.
`lenient` | `boolean` | `false` | Whether to only check if the XML document is well-formed.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the TLDDoc Builder.

### Tag Library Documentation Generator Dependency [](id=tag-library-documentation-generator-dependency)

By default, the plugin creates a configuration called `tlddoc` and adds a
dependency to the 1.3 version of the Tag Library Documentation Generator. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `tlddoc` configuration:

```gradle
dependencies {
    tlddoc group: "taglibrarydoc", name: "tlddoc", version: "1.3"
}
```
