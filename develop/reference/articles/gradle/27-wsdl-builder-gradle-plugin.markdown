# WSDL Builder Gradle Plugin [](id=wsdl-builder-gradle-plugin)

The WSDL Builder Gradle plugin lets you generate [Apache Axis](http://axis.apache.org/axis/)
client stubs from Web Service Description (WSDL) files.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.wsdl.builder", version: "2.0.1"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.wsdl.builder"
```

The WSDL Builder plugin automatically applies the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin.

Since the plugin automatically resolves the Apache Axis library as a dependency,
you have to configure a repository that hosts the library and its
transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
    maven {
        url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
    }
}
```

## Tasks [](id=tasks)

The plugin adds one main task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`buildWSDL` | \- | [`BuildWSDLTask`](#buildwsdltask) | Generates WSDL client stubs.

By default, the `buildWSDL` task looks for WSDL files in the
`${project.projectDir}/wsdl` directory. If the [`war`](https://docs.gradle.org/current/userguide/war_plugin.html)
plugin is applied, it looks in the `${project.webAppDir}/WEB-INF/wsdl`
directory.

For each WSDL file that can be found, the task generates client stubs via direct
invocation of the [*WSDL2Java*](http://axis.apache.org/axis/java/user-guide.html#Client-side_bindings)
tool, saving them in the first [`java`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceSet.html#org.gradle.api.tasks.SourceSet:java)
directory of the `main` [source set](https://docs.gradle.org/current/userguide/java_plugin.html#N1503E)
(by default: `src/main/java`).

If configured to do so, `buildWSDL` can instead save the client stub Java files
in a temporary directory, compile them, and package them in JAR files. The JAR
files are named after the WSDL file and saved in `${project.projectDir}/lib`, by
default, or in `${project.webAppDir}/WEB-INF/lib`, if the `war` plugin is
applied.

### BuildWSDLTask [](id=buildwsdltask)

Tasks of type `FormatWSDLTask` extend [`SourceTask`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html),
so all its properties and methods, such as [`include`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:include\(java.lang.Iterable\))
and [`exclude`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:exclude\(java.lang.Iterable\)),
are available.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`buildLibs` | `boolean` | `true` | Whether to package the client stub classes of each WSDL file in JAR files, saved to the directory the `destinationDir` property references. If `false`, the task generates the client stub Java files to the `destinationDir` directory.
`destinationDir` | `File` | `null` | A directory where the client stub Java files (if `buildLibs` is `false`) or the client stub JAR files (if `buildLibs` is `true`) are saved.
`generateOptions.mapping` | `Map` | `[:]` | Namespace-to-package mappings (sets the `--NStoPkg` argument in the *WSDL2Java* invocation). It is possible to use a `Closure` or a `Callable`, to defer evaluation until task execution..
`generateOptions.noWrapped` | `boolean` | `false` | Whether to turn off support for "wrapped" document/literal (sets the `--noWrapped` argument in the *WSDL2Java* invocation).
`generateOptions.serverSide` | `boolean` | `false` | Whether to emit server-side bindings for the web service (sets the `--server-side` argument in the *WSDL2Java* invocation).
`generateOptions.verbose` | `boolean` | `false` | Whether to print informational messages (sets the `--verbose` argument in the *WSDL2Java* invocation).
`includeSource` | `boolean` | `true` | Whether to package the client stub Java files in the JAR file's  `OSGI-OPT/src` directory. If `buildLibs` is `false`, this property has no effect.
`includeWSDLs` | `boolean` | `true` | Whether to configure the [`processResources`](https://docs.gradle.org/current/userguide/java_plugin.html#sec:resources) task to include the WSDL files in the project JAR's `wsdl` directory.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.lang.Object\)).

#### Task Methods [](id=task-methods)

Method Signature | Description
---------------- | -----------
`generateOptions.mapping(Object namespace, Object packageName)` | Adds a namespace-to-package mapping.
`generateOptions.mappings(Map mappings)` | Adds multiple namespace-to-package mappings.

#### Helper Tasks [](id=helper-tasks)

At the end of the [project evaluation](https://docs.gradle.org/current/userguide/build_lifecycle.html#N11BAE),
a series of helper tasks are created for each WSDL file returned by the
[`source`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:source)
property of the `BuildWSDLTask` tasks. The names of the helper tasks start with
the WSDL file name, without any extension.

- `${WSDL file title}Generate` of type [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html): invokes [*WSDL2Java*](https://axis.apache.org/axis/java/reference.html#WSDL2Java_Reference) to generate the client stubs for the WSDL file.

If `buildWSDLTask.buildLibs` is `true`, the following helper tasks are also
created:

- `${WSDL file title}Compile` of type [`JavaCompile`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.compile.JavaCompile.html): compiles the client stub Java files for the WSDL file.
- `${WSDL file title}Jar` of type [`Jar`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Jar.html): packages in a JAR file called `${WSDL file title}-ws.jar`, the client stub for the WSDL file.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use WSDL Builder.

### Apache Axis Dependency [](id=apache-axis-dependency)

By default, the plugin creates a configuration called `wsdlBuilder` and adds the
following dependencies:

- `axis:axis-wsdl4j:1.5.1`
- `com.liferay:org.apache.axis:1.4.LIFERAY-PATCHED-1`
- `commons-discovery:commons-discovery:0.2`
- `commons-logging:commons-logging:1.0.4`
- `javax.activation:activation:1.1`
- `javax.mail:mail:1.4`
- `org.apache.axis:axis-jaxrpc:1.4`
- `org.apache.axis:axis-saaj:1.4`

It is possible to override this setting and use a specific version of Apache
Axis, by manually populating the `wsdlBuilder` configuration with the desired
dependencies.
