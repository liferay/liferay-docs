# JS Module Config Generator Gradle Plugin [](id=js-module-config-generator-gradle-plugin)

The JS Module Config Generator Gradle plugin lets you run the
[Liferay AMD Module Config Generator](https://github.com/liferay/liferay-module-config-generator)
to generate the configuration file needed to load AMD files via combo loader in
Liferay.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.js.module.config.generator", version: "2.1.31"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.js.module.config.generator"
```

The JS Module Config Generator plugin automatically applies the
[`com.liferay.node`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-node)
plugin.

## Project Extension [](id=project-extension)

The JS Module Config Generator plugin exposes the following properties through
the extension named `jsModuleConfigGenerator`:

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="version"></a>`version` | `String` | `"1.2.1"` | The version of the Liferay AMD Module Config Generator to use.

## Tasks [](id=tasks)

The plugin adds two tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`configJSModules` | `downloadLiferayModuleConfigGenerator`, `processResources` | [`ConfigJSModulesTask`](#configjsmodulestask) | Generates the configuration file needed to load AMD files via combo loader in Liferay.
`downloadLiferayModuleConfigGenerator` | `downloadNode` | `DownloadNodeModuleTask` | Downloads the Liferay AMD Module Config Generator in the project's `node_modules` directory.

By default, the `downloadLiferayModuleConfigGenerator` task downloads the
version of `liferay-module-config-generator` declared in the
[`jsModuleConfigGenerator.version`](#version) property. If the project's
`package.json` file, however, already lists the
`liferay-module-config-generator` package in its `dependencies` or
`devDependencies`, the
`downloadLiferayModuleConfigGenerator` task is disabled.

The `configJSModules` task is automatically configured with sensible defaults,
depending on whether the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied:

Property Name | Default Value
------------- | -------------
[`moduleConfigFile`](#moduleconfigfile) | `"${project.projectDir}/package.json"`
[`outputFile`](#outputfile) | `"${sourceSets.main.output.resourcesDir}/META-INF/config.json"`
[`sourceDir`](#sourcedir) | `"${sourceSets.main.output.resourcesDir}/META-INF/resources"`

The plugin also adds the following dependencies to tasks defined by the `java`
plugin:

Name | Depends On
---- | ----------
`classes` | `configJSModules`

If the [`com.liferay.js.transpiler`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-js-transpiler)
plugin is applied, the `configJSModules` task is configured to always run after
the `transpileJS` task.

### ConfigJSModulesTask [](id=configjsmodulestask)

Tasks of type `ConfigJSModulesTask` extend `ExecuteNodeScriptTask`, so all its
properties and methods, such as `args`, `inheritProxy`, and `workingDir`, are
available. The `ConfigJSModulesTask` instances also implement the
[`PatternFilterable`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/util/PatternFilterable.html)
interface, which lets you specify include and exclude patterns for the files in
[`sourceDir`](#sourcedir) to process.

They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`includes`](https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/util/PatternFilterable.html#getIncludes\(\)) | `["**/*.es.js*", "**/*.soy.js*"]`
`scriptFile` | `"${downloadLiferayModuleConfigGenerator.moduleDir}/bin/index.js"`

The purpose of this task is to run the Liferay AMD Module Config Generator from
the included files in [`sourceDir`](#sourcedir). The generator processes these
files and creates a configuration file in the location specified by the
[`outputFile`](#outputfile) property.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`configVariable` | `String` | `null` | The configuration variable to which the modules should be added. It sets the `--config` argument.
`customDefine` | `String` | `"Liferay.Loader"` | The namespace of the `define(...)` call to use in the JS files. It sets the `--namespace` argument.
`ignorePath` | `boolean` | `false` | Whether not to create module `path` and `fullPath` properties. It sets the `--ignorePath` argument.
`keepFileExtension` | `boolean` | `false` | Whether to keep the file extension when generating the module name. It sets the `--keepExtension` argument.
`lowerCase` | `boolean` | `false` | Whether to convert file name to lower case before using it as the module name. It sets the `--lowerCase` argument.
<a name="moduleconfigfile"></a>`moduleConfigFile` | `File` | `null` | The JSON file which contains configuration data for the modules. It sets the `--moduleConfig` argument.
`moduleExtension` | `String` | `null` | The extension for the module file (e.g., `.js`). If specified, use the provided string as an extension instead to get it automatically from the file name. It sets the `--extension` argument.
`moduleFormat` | `String` | `null` | The regular expression and value to apply to the file name when generating the module name. It sets the `--format` argument.
<a name="outputfile"></a>`outputFile` | `File` | `null` | The file where the generated configuration is stored. It sets the `--output` argument.
<a name="sourcedir"></a>`sourceDir` | `File` | `null` | The directory that contains the files to process.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the `int`
and `String` properties to defer evaluation until task execution.
