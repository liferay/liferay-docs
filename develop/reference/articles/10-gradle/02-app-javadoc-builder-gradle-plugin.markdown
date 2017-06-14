# App Javadoc Builder Gradle Plugin [](id=app-javadoc-builder-gradle-plugin)

The App Javadoc Builder Gradle plugin lets you generate API documentation as a
single, combined HTML document for an application that spans different
subprojects, each one representing a different component of the same
application.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in the build script of the root project:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.app.javadoc.builder", version: "1.2.0"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.app.javadoc.builder"
```

The App Javadoc Builder plugin automatically applies the [`base`](https://docs.gradle.org/current/userguide/standard_plugins.html#N135C1)
and `reporting-base` plugins.

## Project Extension [](id=project-extension)

The App Javadoc Builder plugin exposes the following properties through the
extension named `appJavadocBuilder`:

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`copyTags` | `boolean` | `true` | Whether to copy the custom block tags configuration from the subprojects. It sets the Javadoc [`-tag`](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html#tag) argument for the [`appJavadoc`](#appjavadoc) task.
`doclintDisabled` | `boolean` | `true` on JDK8+, `false` otherwise. | Whether to ignore Javadoc errors. It sets the Javadoc [`-Xdoclint`](docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html#BEJEFABE) and [`-quiet`](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html#CHDGFHAA) arguments for the [`appJavadoc`](#appjavadoc) task.
`groupNameClosure` | `Closure<String>` | The subproject's description, or the subproject's name if the description is empty. | The closure invoked in order to get the group heading for a subproject. The given closure is passed a [`Project`](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html) as its parameter. If `groupPackages` is `false`, this property is not used.
`groupPackages` | `boolean` | `true` | Whether to separate packages on the overview page based on the subprojects they belong to. It sets the [`-group`](docs.oracle.com/javase/8/docs/technotes/tools/unix/javadoc.html#CHDIGGII) argument for the [`appJavadoc`](#appjavadoc) task.
`subprojects` | `Set<Project>` | `project.subprojects` | The subprojects to include in the API documentation of the app.

The same extension exposes the following methods:

Method | Description
------ | -----------
`AppJavadocBuilderExtension onlyIf(Closure<Boolean> onlyIfClosure)` | Includes a subproject in the API documentation if the given closure returns `true`. The closure is evaluated at the end of the subproject configuration phase and is passed a single parameter: the subproject. If the closure returns `false`, the subproject is not included in the API documentation.
`AppJavadocBuilderExtension onlyIf(Spec<Project> onlyIfSpec)` | Includes a subproject in the API documentation if the given spec is satisfied. The spec is evaluated at the end of the subproject configuration phase. If the spec is not satisfied, the subproject is not included in the API documentation.
`AppJavadocBuilderExtension subprojects(Iterable<Project> subprojects)` | Include additional projects in the API documentation of the app.
`AppJavadocBuilderExtension subprojects(Project... subprojects)` | Include additional projects in the API documentation of the app.

## Tasks [](id=tasks)

The plugin adds two tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
<a name="appjavadoc"></a>`appJavadoc` | The `javadoc` tasks of the subprojects. | [`Javadoc`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.javadoc.Javadoc.html) | Generates Javadoc API documentation for the app.
`jarAppJavadoc` | `appJavadoc` | [`Jar`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Jar.html) | Assembles a JAR archive containing the Javadoc files for this app.

The `appJavadoc` task is automatically configured with sensible defaults:

Property Name | Default Value
------------- | -------------
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.javadoc.Javadoc.html#org.gradle.api.tasks.javadoc.Javadoc:classpath) | The `javadoc.classpath` of all the subprojects.
[`destinationDir`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.javadoc.Javadoc.html#org.gradle.api.tasks.javadoc.Javadoc:destinationDir) | `${project.buildDir}/docs/javadoc`
[`options.encoding`](https://docs.gradle.org/current/javadoc/org/gradle/external/javadoc/MinimalJavadocOptions.html#getEncoding\(\)) | `"UTF-8"`
[`source`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.javadoc.Javadoc.html#org.gradle.api.tasks.javadoc.Javadoc:source) | The `javadoc.source` of all the subprojects.
[`title`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.javadoc.Javadoc.html#org.gradle.api.tasks.javadoc.Javadoc:title) | `project.reporting.apiDocTitle`
