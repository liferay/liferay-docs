# Node Gradle Plugin [](id=node-gradle-plugin)

The Node Gradle plugin lets you run [Node.js](https://nodejs.org/) and
[NPM](https://www.npmjs.com/) as part of your build.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.node", version: "3.0.0"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.node"
```

## Project Extension [](id=project-extension)

The Node Gradle plugin exposes the following properties through the extension
named `node`:

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="download"></a>`download` | `boolean` | `true` | Whether to download and use a local and isolated Node.js distribution instead of the one installed in the system.
`global` | `boolean` | `false` | Whether to use a single Node.js installation for the whole multi-project build. This reduces the time required to unpack the Node.js distribution and the time required to download NPM packages thanks to a shared packages cache. If `download` is `false`, this property has no effect.
<a name="nodedir"></a>`nodeDir` | `File` | <p>**If `global` is `true`:** `"${rootProject.buildDir}/node"`</p><p>**Otherwise:** `"${project.buildDir}/node"`</p> | The directory where the Node.js distribution is unpacked. If `download` is `false`, this property has no effect.
`nodeExeUrl` | `String` | `"http://nodejs.org/dist/v${node.nodeVersion}/${arch}/node.exe"` | The URL of `node.exe` to download when on Windows. If `download` is `false`, this property has no effect.
`nodeUrl` | `String` | `"http://nodejs.org/dist/v${node.nodeVersion}/node-v${node.nodeVersion}-${platform}-x${bitMode}.tar.gz"` | The URL of the Node.js distribution to download. If `download` is `false`, this property has no effect.
`nodeVersion` | `String` | `"5.5.0"` | The version of the Node.js distribution to use. If `download` is `false`, this property has no effect.
`npmArgs` | `List<String>` | `[]` | The arguments added automatically to every task of type [`ExecuteNpmTask`](#executenpmtask).
`npmUrl` | `String` | `"https://registry.npmjs.org/npm/-/npm-${node.npmVersion}.tgz"` | The URL of the NPM version to download. If `download` is `false`, this property has no effect.
`npmVersion` | `String` | `null` | The version of NPM to use. If `null`, the version of NPM embedded inside the Node.js distribution is used. If `download` is `false`, this property has no effect.

It is possible to override the default value of the `download` property by
setting the `nodeDownload` project property. For example, this can be done via
command line argument:

```bash
./gradlew -PnodeDownload=false npmInstall
```

The same extension exposes the following methods:

Method | Description
------ | -----------
`NodeExtension npmArgs(Iterable<?> npmArgs)` | Adds arguments to automatically add to every task of type [`ExecuteNpmTask`](#executenpmtask).
`NodeExtension npmArgs(Object... npmArgs)` | Adds arguments to automatically add to every task of type [`ExecuteNpmTask`](#executenpmtask).

The properties of type `File` support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for `String`,
to defer evaluation until execution.

Please note that setting the `global` property of the `node` extension via the
command line is not supported. It can only be set via Gradle script, which can
be done by adding the following code to the `build.gradle` file in the root of
a project (e.g., Liferay Workspace):

```gradle
allprojects {
    plugins.withId("com.liferay.node") {
        node.global = true
    }
}
```

## Tasks [](id=tasks)

The plugin adds a series of tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`cleanNPM` | \- | [`Delete`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Delete.html) | Deletes the `node_modules` directory and the `npm-shrinkwrap.json` file from the project, if present.
<a name="downloadnode"></a>`downloadNode` | \- | [`DownloadNodeTask`](#downloadnodetask) | Downloads and unpacks the local Node.js distribution for the project. If `node.download` is `false`, this task is disabled.
`npmInstall` | `downloadNode` | [`NpmInstallTask`](#npminstalltask) | Runs `npm install` to install the dependencies declared in the project's `package.json` file, if present. By default, the task is [configured](#npminstallretries) to run `npm install` two more times if it fails.
[`npmRun${script}`](#tasks-npmrunscript) | `npmInstall` | [`ExecuteNpmTask`](#executenpmtask) | Runs the `${script}` NPM script.
`npmShrinkwrap` | `cleanNPM`, `npmInstall` | [`NpmShrinkwrapTask`](#npmshrinkwraptask) | Locks down the versions of a package's dependencies in order to control which dependency versions are used.

### DownloadNodeTask [](id=downloadnodetask)

The purpose of this task is to download and unpack a Node.js distribution.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`nodeDir` | `File` | `null` | The directory where the Node.js distribution is unpacked.
`nodeExeUrl` | `String` | `null` | The URL of `node.exe` to download when on Windows.
`nodeUrl` | `String` | `null` | The URL of the Node.js distribution to download.
`npmUrl` | `String` | `null` | The URL of the NPM version to download.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties, to defer evaluation until task execution.

### ExecuteNodeTask [](id=executenodetask)

This is the base task to run Node.js in a Gradle build. All tasks of type
`ExecuteNodeTask` automatically depend on [`downloadNode`](#downloadnode).

#### Task Properties [](id=task-properties-0)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`args` | `List<Object>` | `[]` | The arguments for the Node.js invocation.
`command` | `String` | `"node"` | The file name of the executable to invoke.
`inheritProxy` | `boolean` | `true` | Whether to set the `http_proxy`, `https_proxy`, and `no_proxy` environment variables in the Node.js invocation based on the values of the system properties `https.proxyHost`, `https.proxyPort`, `https.proxyUser`, `https.proxyPassword`, `https.nonProxyHosts`, `https.proxyHost`, `https.proxyPort`, `https.proxyUser`, `https.proxyPassword`, and `https.nonProxyHosts`. If these environment variables are already set, their values will not be overwritten.
`nodeDir` | `File` | <p>**If [`node.download`](#download) is `true`:** [`node.nodeDir`](#nodedir)</p><p>**Otherwise:** `null`</p> | The directory that contains the executable to invoke. If `null`, the executable must be available in the system `PATH`.
`npmInstallRetries` | `int` | `0` | The number of times the `node_modules` is deleted and `npm install` is retried in case the Node.js invocation defined by this task fails. This can help solving corrupted `node_modules` directories by re-downloading the project's dependencies.
`workingDir` | `File` | `project.projectDir` | The working directory to use in the Node.js invocation.

The properties of type `File` support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties to defer evaluation until task execution.

#### Task Methods [](id=task-methods)

Method | Description
------ | -----------
`ExecuteNodeTask args(Iterable<?> args)` | Adds arguments for the Node.js invocation.
`ExecuteNodeTask args(Object... args)` | Adds arguments for the Node.js invocation.

### ExecuteNodeScriptTask [](id=executenodescripttask)

The purpose of this task is to execute a Node.js script. Tasks of type
`ExecuteNodeScriptTask` extend [`ExecuteNodeTask`](#executenodetask).

#### Task Properties [](id=task-properties-1)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`scriptFile` | `File` | `null` | The Node.js script to execute.

The properties of type `File` support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).

### ExecuteNpmTask [](id=executenpmtask)

The purpose of this task is to execute an NPM command. Tasks of type
`ExecuteNpmTask` extend [`ExecuteNodeScriptTask`](#executenodescripttask) with
the following properties set by default:

Property Name | Default Value
------------- | -------------
`command` | <p>**If `nodeDir` is `null`:** `"npm"`</p><p>**Otherwise:** `"node"`
`scriptFile` | <p>**If `nodeDir` is `null`:** `null`</p><p>**Otherwise:** `"${nodeDir}/lib/node_modules/npm/bin/npm-cli.js"`

#### Task Properties [](id=task-properties-2)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`cacheDir` | `File` | <p>**If `nodeDir` is `null`:** `null`</p><p>**Otherwise:** `"${nodeDir}/.cache"` | The location of NPM's cache directory. It sets the [`--cache`](https://docs.npmjs.com/misc/config#cache) argument. Leave the property `null` to keep the default value.
`logLevel` | `String` | Value to mirror the log level set in the task's [`logger`](https://docs.gradle.org/current/dsl/org.gradle.api.Task.html#org.gradle.api.Task:logger) object. | The NPM log level. It sets the [--loglevel](https://docs.npmjs.com/misc/config#loglevel) argument.
`progress` | `boolean` | `true` | Whether to show a progress bar during the NPM invocation. It sets the [`--progress`](https://docs.npmjs.com/misc/config#progress) argument.
<a name="registry"></a>`registry` | `String` | `null` | The base URL of the NPM package registry. It sets the [`--registry`](https://docs.npmjs.com/misc/config#registry) argument. Leave the property `null` or empty to keep the default value.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties, to defer evaluation until task execution.

### DownloadNodeModuleTask [](id=downloadnodemoduletask)

The purpose of this task is to download a Node.js package. The packages are
downloaded in the `${workingDir}/node_modules` directory, which is equal, by
default, to the `node_modules` directory of the project. Tasks of type
`DownloadNodeModuleTask` extend [`ExecuteNpmTask`](#executenpmtask) in order to
execute the command [`npm install ${moduleName}@${moduleVersion}`](https://docs.npmjs.com/cli/install).

`DownloadNodeModuleTask` instances are automatically disabled if the project's
`package.json` file already lists a module with the same name in its
`dependencies` or `devDependencies` object.

#### Task Properties [](id=task-properties-3)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`moduleName` | `String` | `null` | The name of the Node.js package to download.
`moduleVersion` | `String` | `null` | The version of the Node.js package to download.

It is possible to use Closures and Callables as values for the `String`
properties, to defer evaluation until task execution.

### NpmInstallTask [](id=npminstalltask)

Purpose of these tasks is to install the dependencies declared in a
`package.json` file. Tasks of type `NpmInstallTask` extend
[`ExecuteNpmTask`](#executenpmtask) in order to run the command [`npm install`](https://docs.npmjs.com/cli/install).

`NpmInstallTask` instances are automatically disabled if the `package.json` file
does not declare any dependency in the `dependency` or `devDependencies` object.

#### Task Properties [](id=task-properties-4)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`nodeModulesCacheDir` | `File` | `null` | <p>The directory where `node_modules` directories are cached. By setting this property, it is possible to cache the `node_modules` directory of a project and avoid unnecessary invocations of `npm install`, useful especially in Continuous Integration environments.</p><p>The `node_modules` directory is cached based on the content of the project's `npm-shrinkwrap.json` (or `package.json` if absent). Therefore, if `NpmInstallTask` tasks in multiple projects are configured with the same `nodeModulesCacheDir`, and their `npm-shrinkwrap.json` or `package.json` declare the same dependencies, their `node_modules` caches will be shared.</p><p>This feature is not available if the [`com.liferay.cache`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-cache) plugin is applied.</p>
`nodeModulesCacheNativeSync` | `boolean` | `true` | Whether to use `rsync` (on Linux/macOS) or `robocopy` (on Windows) to cache and restore the `node_modules` directory. If `nodeModulesCacheDir` is not set, this property has no effect.
`removeShrinkwrappedUrls` | `boolean` | `true` if the [registry](#registry) property has a value, `false` otherwise. | Whether to temporarily remove all the hard-coded URLs in the `from` and `resolved` fields of the `npm-shinkwrap.json` file before invoking `npm install`. This way, it is possible to force NPM to download all dependencies from a custom registry declared in the [`registry`](#registry) property.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).

### NpmShrinkwrapTask [](id=npmshrinkwraptask)

The purpose of this task is to lock down the versions of a package's
dependencies so that you can control exactly which dependency versions are used
when your package is installed. Tasks of type `NpmShrinkwrapTask` extend
[`ExecuteNpmTask`](#executenpmtask) to execute the command
[`npm shrinkwrap`](https://docs.npmjs.com/cli/shrinkwrap).

The generated `npm-shrinkwrap.json` file is automatically sorted and formatted,
so it's easier to see the changes with the previous version.

`NpmShrinkwrapTask` instances are automatically disabled if the `package.json`
file does not exist.

#### Task Properties [](id=task-properties-5a)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`excludedDependencies` | `List<String>` | `[]` | The package names to exclude from the generated `npm-shrinkwrap.json` file.
`includeDevDependencies` | `boolean` | `true` | Whether to include the package's `devDependencies`. It sets the [`--dev`](https://docs.npmjs.com/cli/shrinkwrap#other-notes) argument.

It is possible to use Closures and Callables as values for the `String`
properties to defer evaluation until task execution.

#### Task Methods [](id=task-methods-0)

Method | Description
------ | -----------
`NpmShrinkwrapTask excludeDependencies(Iterable<?> excludedDependencies)` | Adds package names to exclude from the generated `npm-shrinkwrap.json` file.
`NpmShrinkwrapTask excludeDependencies(Object... excludedDependencies)` | Adds package names to exclude from the generated `npm-shrinkwrap.json` file.

### PublishNodeModuleTask [](id=publishnodemoduletask)

The purpose of this task is to publish a package to the
[NPM registry](#https://www.npmjs.com/). Tasks of type `PublishNodeModuleTask`
extend [`ExecuteNpmTask`](#executenpmtask) in order to execute the command
[`npm publish`](https://docs.npmjs.com/cli/publish).

These tasks generate a new temporary `package.json` file in the root of the
project directory, based on the values provided for the task properties. If the
project already includes a custom `package.json` file, it is easier to use
`npm publish` directly.

#### Task Properties [](id=task-properties-5)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`moduleAuthor` | `String` | `null` | The value of the [`author`](https://docs.npmjs.com/files/package.json#people-fields-author-contributors) field in the generated `package.json` file.
`moduleBugsUrl` | `String` | `null` | The value of the [`bugs.url`](https://docs.npmjs.com/files/package.json#bugs) field in the generated `package.json` file.
`moduleDescription` | `String` | `project.description` | The value of the [`description`](https://docs.npmjs.com/files/package.json#description-1) field in the generated `package.json` file.
`moduleKeywords` | `List<String>` | `[]` | The value of the [`keywords`](https://docs.npmjs.com/files/package.json#keywords) field in the generated `package.json` file.
`moduleLicense` | `String` | `null` | The value of the [`license`](https://docs.npmjs.com/files/package.json#license) field in the generated `package.json` file.
`moduleMain` | `String` | `null` | The value of the [`main`](https://docs.npmjs.com/files/package.json#main) field in the generated `package.json` file.
`moduleName` | `String` | Name based on [`osgiHelper.bundleSymbolicName`](https://github.com/gradle/gradle/blob/master/subprojects/osgi/src/main/java/org/gradle/api/internal/plugins/osgi/OsgiHelper.java): for example, if `osgiHelper.bundleSymbolicName` is `"com.liferay.gradle.plugins.node"`, the default value for the `moduleName` property is `"liferay-gradle-plugins-node"`. | The value of the [`name`](https://docs.npmjs.com/files/package.json#name) field in the generated `package.json` file.
`moduleRepository` | `String` | `null` | The value of the [`repository`](https://docs.npmjs.com/files/package.json#repository) field in the generated `package.json` file.
`moduleVersion` | `String` | `project.version` | The value of the [`version`](https://docs.npmjs.com/files/package.json#version) field in the generated `package.json` file.
`npmEmailAddress` | `String` | `null` | The email address of the npmjs.com user that publishes the package.
`npmPassword` | `String` | `null` | The password of the npmjs.com user that publishes the package.
`npmUserName` | `String` | `null` | The name of the npmjs.com user that publishes the package.

### npmRun${script} Task [](id=npmrunscript-task)

For each [script](https://docs.npmjs.com/misc/scripts) declared in the
`package.json` file of the project, one task `npmRun${script}` of type
[`ExecuteNpmTask`](#executenpmtask) is added. Each of these tasks is
automatically configured with sensible defaults:

Property Name | Default Value
------------- | -------------
`args` | `["run-script", "${script}"]`

If the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied and the `package.json` file declares a script named `"build"`,
then the script is executed before the `classes` task but after the
[`processResources`](https://docs.gradle.org/4.0/userguide/java_plugin.html#sec:java_resources)
task.
