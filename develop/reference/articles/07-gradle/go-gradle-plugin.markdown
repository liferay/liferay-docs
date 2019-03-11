# Go Gradle Plugin [](id=go-gradle-plugin)

The Go Gradle plugin lets you run [Go](https://golang.org/) as part of your
build.

The plugin has been successfully tested with Gradle 3.5.1 up to 4.10.2.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.go", version: "1.0.0"
    }

    repositories {
        maven {
            url "https://repository-cdn.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.go"
```

## Project Extension [](id=project-extension)

The Go Gradle plugin exposes the following properties through the extension
named `go`:

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="godir"></a>`goDir` | `File` | `"${project.buildDir}/go"` | The directory where the Go distribution is unpacked.
`goUrl` | `String` | `"https://dl.google.com/go/go${go.goVersion}.${platform}-${bitMode}.${extension}` | The URL of the Go distribution to download.
`goVersion` | `String` | `"1.11.4"` | The Go distribution's version to use.
<a name="workingdir"></a>`workingDir` | `File` | `"${project.projectDir}"` | The directory that contains the project's Go source code.

## Tasks [](id=tasks)

The plugin adds a series of tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
<a name="downloadgo"></a>`downloadGo` | \- | [`DownloadGoTask`](#downloadgotask) | Downloads and unpacks the local Go distribution for the project.
[`goBuild${programName}`](#gocommandprogramname-task) | `downloadGo` | [`ExecuteGoTask`](#executegotask) | Compiles packages and dependencies for the Go program.
[`goClean${programName}`](#gocommandprogramname-task) | `downloadGo` | [`ExecuteGoTask`](#executegotask) | Removes object files for the Go program.
[`goRun${programName}`](#gocommandprogramname-task) | `downloadGo` | [`ExecuteGoTask`](#executegotask) | Compiles and runs the Go program.
[`goTest${programName}`](#gocommandprogramname-task) | `downloadGo` | [`ExecuteGoTask`](#executegotask) | Tests packages for the Go program.

### DownloadGoTask [](id=downloadgotask)

The purpose of this task is to download and unpack a Go distribution.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`goDir` | `File` | `null` | The directory where the Go distribution is unpacked.
`goUrl` | `String` | `null` | The URL of the Go distribution to download.

The `File` type support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties, to defer evaluation until task execution.

### ExecuteGoTask [](id=executegotask)

This is the base task to run Go in a Gradle build. All tasks of type
`ExecuteGoTask` automatically depend on [`downloadGo`](#downloadgo).

#### Task Properties [](id=task-properties-0)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`args` | `List<Object>` | `[]` | The arguments for the Go invocation.
`command` | `String` | `"go"` | The file name of the executable to invoke.
`environment` | `Map<Object, Object>` | `[]` | The environment variables for the Go invocation.
`inheritProxy` | `boolean` | `true` | Whether to set the `http_proxy`, `https_proxy`, and `no_proxy` environment variables in the Go invocation based on the values of the system properties `https.proxyHost`, `https.proxyPort`, `https.proxyUser`, `https.proxyPassword`, `https.nonProxyHosts`, `https.proxyHost`, `https.proxyPort`, `https.proxyUser`, `https.proxyPassword`, and `https.nonProxyHosts`. If these environment variables are already set, their values will not be overwritten.
`goDir` | `File` | `go.goDir`](#godir) | The directory that contains the executable to invoke.
`useGradleExec` | `boolean` | <p>**If running in a [Gradle Daemon](https://docs.gradle.org/current/userguide/gradle_daemon.html):** `true`</p><p>**Otherwise:** `false`</p> | Whether to invoke Go using [`project.exec`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:exec\(org.gradle.api.Action\)), which can solve hanging problems with the Gradle Daemon.
<a name="workingdirproperty"></a>`workingDir` | `File` | `go.workingDir`](#workingdir) | The working directory to use in the Go invocation.

The type `File` properties support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties to defer evaluation until task execution.

#### Task Methods [](id=task-methods)

Method | Description
------ | -----------
`ExecuteGoTask args(Iterable<?> args)` | Adds arguments for the Go invocation.
`ExecuteGoTask args(Object... args)` | Adds arguments for the Go invocation.
`ExecuteGoTask environment(Map<?, ?> environment)` | Adds environment variables for the Go invocation.
`ExecuteGoTask environment(Object key, Object value)` | Adds an environment variable for the Go invocation.

### go${command}${programName} Task [](id=gocommandprogramname-task)

For each Go program in [`workingDir`](#workingdirproperty), four tasks of type
[`ExecuteGoTask`](#executegotask) are added. Each of these tasks are
automatically configured with sensible defaults:

Property Name | Default Value
------------- | -------------
`args` | `["${command}", "${programFile.absolutePath}"]`
