# JS Transpiler Gradle Plugin [](id=js-transpiler-gradle-plugin)

The JS Transpiler Gradle plugin lets you run [`metal-cli`](https://github.com/metal/metal-cli)
to build [Metal.js](http://metaljs.com/) code, compile Soy files, and transpile
ES6 to ES5.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.js.transpiler", version: "2.4.10"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.js.transpiler"
```

There are two JS Transpiler Gradle plugins you can apply to your project:

- [*JS Transpiler Plugin*](#js-transpiler-plugin): builds Metal.js code,
compiles Soy files, and transpiles ES6 to ES5:

    ```gradle
    apply plugin: "com.liferay.js.transpiler"
    ```

- [*JS Transpiler Base Plugin*](#js-transpiler-base-plugin): provides a way to
use Gradle dependencies (such as an [external module](https://docs.gradle.org/current/userguide/dependency_management.html#sub:module_dependencies)
or [project dependencies](https://docs.gradle.org/current/userguide/dependency_management.html#sub:project_dependencies))
in Node.js scripts:

    ```gradle
    apply plugin: "com.liferay.js.transpiler.base"
    ```

## JS Transpiler Plugin [](id=js-transpiler-plugin)

The JS Transpiler plugin automatically applies the [*JS Transpiler Base Plugin*](#js-transpiler-base-plugin).

The plugin adds two tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`downloadMetalCli` | `downloadNode` | `DownloadNodeModuleTask` | Downloads `metal-cli` in the project's `node_modules` directory.
`transpileJS` | `downloadMetalCli`, `expandJSCompileDependencies`, `npmInstall`, `processResources` | [`TranspileJSTask`](#transpilejstask) | Builds Metal.js code.

By default, the `downloadMetalCli` task downloads the version 1.3.1 of
`metal-cli`. If the project's `package.json` file, however, already lists the
`metal-cli` package in its `dependencies` or `devDependencies`, the
`downloadMetalCli` task is disabled.

The `transpileJS` task is automatically configured with sensible defaults,
depending on whether the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied:

Property Name | Default Value
------------- | -------------
[`sourceDir`](#sourcedir) | The directory `META-INF/resources` in the first `resources` directory of the `main` source set (by default, `src/main/resources/META-INF/resources`).
`workingDir` | `"${sourceSets.main.output.resourcesDir}/META-INF/resources"`

The plugin also adds the following dependencies to tasks defined by the `java`
plugin:

Name | Depends On
---- | ----------
`classes` | `transpileJS`

The plugin adds a new configuration to the project called `soyCompile`. If one
or more dependencies are added to this configuration, they will be expanded into
temporary directories and passed to the `transpileJS` task as additional
[`soyDependencies`](#soydependencies) values.

## JS Transpiler Base Plugin [](id=js-transpiler-base-plugin)

The JS Transpiler Base plugin automatically applies the [`com.liferay.node`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-node)
plugin.

The plugin adds a new configuration to the project called `jsCompile`. If one or
more dependencies are added to this configuration, they will be expanded into
sub-directories of the `node_modules` directory, with names equal to the names
of the dependencies.

The plugin also adds one task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`expandJSCompileDependencies` | \- | [`DefaultTask`](https://docs.gradle.org/current/javadoc/org/gradle/api/DefaultTask.html) | Expands the additional configured JavaScript dependencies. The task itself does not do any work, but depends on a series of [Copy](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Copy.html) tasks called `expandJSCompileDependency${file}`, which expand each dependency declared in the `jsCompile` configuration into the `node_modules` directory.

All the tasks of type `ExecuteNpmTask` whose name starts with `"npmRun"` are
configured to depend on `expandJSCompileDependencies`. This means that, before
running any [script](https://docs.npmjs.com/misc/scripts) declared in the
`package.json` file of the project, all the `jsCompile` dependencies will be
expanded into the `node_modules` directory.

## Tasks [](id=tasks)

### TranspileJSTask [](id=transpilejstask)

Tasks of type `TranspileJSTask` extend `ExecuteNodeScriptTask`, so all its
properties and methods, such as `args`, `inheritProxy`, and `workingDir`, are
available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
`scriptFile` | `"${downloadMetalCli.moduleDir}/index.js"`

The purpose of this task is to run the `build` command of `metal-cli` to
build Metal.js code from [`sourceDir`](#sourcedir) into the `workingDir`
directory.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`bundleFileName` | `String` | `null` | The name of the final bundle file for formats (e.g., *globals*) that create one. It sets the `--bundleFileName` argument.
`globalName` | `String` | `null` | The name of the global variable that holds exported modules. It sets the `--globalName` argument. This is only used by the *globals* format build.
`moduleName` | `String` | `null` | The name of the project that is being compiled. All built modules are stored in a folder with this name. It sets the `--moduleName` argument. This is only used by the *amd* format build.
`modules` | `String` | `"amd"` | The format(s) that the source files are built to. It sets the `--format` argument.
`skipWhenEmpty` | `boolean` | `true` | Whether to disable the task and remove its dependencies if the [`sourceFiles`](#sourcefiles) property does not return any file at the end of the project evaluation.
<a name="sourcedir"></a>`sourceDir` | `File` | `null` | The directory that contains the files to build.
<a name="sourcefiles"></a>`sourceFiles` | `FileCollection` | `[]` | The Soy and JS files to compile. *(Read-only)*
`sourceMaps` | `SourceMaps` | `enabled` | Whether to generate source map files. Available values include `disabled`, `enabled`, and `enabled_inline`.
<a name="soydependencies"></a>`soyDependencies` | `Set<String>` | `["${npmInstall.workingDir}/node_modules/clay*/src/**/*.soy", "${npmInstall.workingDir}/node_modules/metal*/src/**/*.soy"]` | The path GLOBs of Soy files that the main source files depend on, but that should not be compiled. It sets the `--soyDeps` argument.
`soySkipMetalGeneration` | `boolean` | `false` | Whether to just compile Soy files, without adding Metal.js generated code, like the `component` class. It sets the `--soySkipMetalGeneration` argument.
`soySrcIncludes` | `Set<String>` | `[]` | The path GLOBs of the Soy files to compile. It sets the `--soySrc` argument.
`srcIncludes` | `Set<String>` | `[]` | The path GLOBs of the JS files to compile. It sets the `--src` argument.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the `int`
and `String` properties to defer evaluation until task execution.

#### Task Methods [](id=task-methods)

Method | Description
------ | -----------
`TranspileJSTask soyDependency(Iterable<?> soyDependencies)` | Adds path GLOBs of Soy files that the main source files depend on, but that should not be compiled.
`TranspileJSTask soyDependency(Object... soyDependencies)` | Adds path GLOBs of Soy files that the main source files depend on, but that should not be compiled.
`TranspileJSTask soySrcInclude(Iterable<?> soySrcIncludes)` | Adds path GLOBs of Soy files to compile.
`TranspileJSTask soySrcInclude(Object... soySrcIncludes)` | Adds path GLOBs of Soy files to compile.
`TranspileJSTask srcInclude(Iterable<?> srcIncludes)` | Adds path GLOBs of JS files to compile.
`TranspileJSTask srcInclude(Object... srcIncludes)` | Adds path GLOBs of JS files to compile.
