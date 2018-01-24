# XSD Builder Gradle Plugin [](id=xsd-builder-gradle-plugin)

The XSD Builder Gradle plugin lets you generate [Apache XMLBeans](https://xmlbeans.apache.org/)
bindings from XML Schema (XSD) files.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.xsd.builder", version: "1.0.5"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.xsd.builder"
```

The XSD Builder plugin automatically applies the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin.

Since the plugin automatically resolves the Liferay Service Builder library as a
dependency, you have to configure a repository that hosts the library and its
transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
    maven {
        url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
    }
}
```

## Tasks [](id=tasks)

The plugin adds three tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`buildXSD` | `buildXSDCompile` | [`BuildXSDTask`](#buildxsdtask) | Generates XMLBeans bindings and compiles them in a JAR file.
`buildXSDGenerate` | `cleanBuildXSDGenerate` | [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) | Invokes the [XMLBeans Schema Compiler](https://xmlbeans.apache.org/docs/2.6.0/guide/tools.html#scomp) to generate Java types from XML Schema.
`buildXSDCompile` | `buildXSDGenerate`, `cleanBuildXSDCompile` | [`JavaCompile`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.compile.JavaCompile.html) | Compiles the generated Java types.

By default, the `buildXSD` task looks for XSD files in the
`${project.projectDir}/xsd` directory, and saves the generated JAR file as
`${project.projectDir}/lib/${project.archivesBaseName}-xbean.jar`.

If the [`war`](https://docs.gradle.org/current/userguide/war_plugin.html)
plugin is applied, the task looks for XSD files in the
`${project.webAppDir}/WEB-INF/xsd` directory, and saves the generated JAR file
as `${project.webAppDir}/WEB-INF/lib/${project.archivesBaseName}-xbean.jar`.

### BuildXSDTask [](id=buildxsdtask)

Tasks of type `BuildXSDTask` extend [`Zip`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html).
They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`appendix`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html#org.gradle.api.tasks.bundling.Zip:appendix) | `"xbean"`
[`extension`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html#org.gradle.api.tasks.bundling.Zip:extension) | `"jar"`
[`version`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html#org.gradle.api.tasks.bundling.Zip:version) | `null`

For each task of type `BuildXSDTask`, the following helper tasks are created:

- `${buildXSDTask.name}Compile`
- `${buildXSDTask.name}Generate`

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`inputDir` | `File` | `null` | A directory containing XSD files from which to generate [Apache XMLBeans](https://xmlbeans.apache.org/) bindings.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.lang.Object\)).

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the XSD Builder.

### Apache XMLBeans Dependency [](id=apache-xmlbeans-dependency)

By default, the XSD Builder Gradle plugin creates a configuration called
`xsdBuilder` and adds a dependency to the 2.5.0 version of Apache XMLBeans. It
is possible to override this setting and use a specific version of the library
by manually adding a dependency to the `xsdBuilder` configuration:

```gradle
dependencies {
    xsdBuilder group: "org.apache.xmlbeans", name: "xmlbeans", version: "2.6.0"
}
```
