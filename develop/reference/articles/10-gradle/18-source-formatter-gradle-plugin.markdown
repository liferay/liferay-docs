# Source Formatter Gradle Plugin [](id=source-formatter-gradle-plugin)

The Source Formatter Gradle plugin lets you format project files using the
[Liferay Source Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/source-formatter)
tool.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.source.formatter", version: "2.1.3"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.source.formatter"
```

Since the plugin automatically resolves the Liferay Source Formatter library as
a dependency, you have to configure a repository that hosts the library and its
transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
    maven {
        url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
    }
}
```

## Tasks [](id=tasks)

The plugin adds two tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`checkSourceFormatting` | \- | [`FormatSourceTask`](#formatsourcetask) | Runs the Liferay Source Formatter to check for source formatting errors.
`formatSource` | \- | [`FormatSourceTask`](#formatsourcetask) | Runs the Liferay Source Formatter to format the project files.

If desired, it is possible to check for source formatting errors while executing
the [`check`](https://docs.gradle.org/current/userguide/java_plugin.html#N15056)
task by adding the following dependency:

```gradle
check {
    dependsOn checkSourceFormatting
}
```

The same can be achieved by adding the following snippet to the `build.gradle`
file in the root directory of a [*Liferay Workspace*](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace):

```gradle
subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("base") && plugins.hasPlugin("com.liferay.source.formatter")) {
            check.dependsOn checkSourceFormatting
        }
    }
}
```

The tasks `checkSourceFormatting` and `formatSource` are automatically skipped
if another task with the same name is being executed in a parent project.

### FormatSourceTask [](id=formatsourcetask)

Tasks of type `FormatSourceTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, like [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args\(java.lang.Iterable\))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize)
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | Source Formatter command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.sourceFormatter`](#liferay-source-formatter-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.source.formatter.SourceFormatter"`

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`autoFix` | `boolean` | `false` | Whether to automatically fix source formatting errors. It sets the `source.auto.fix` argument.
`baseDir` | `File` |  | The Source Formatter base directory. It sets the `source.base.dir` argument. *(Read-only)*
`baseDirName` | `String` | `"./"` | The name of the Source Formatter base directory, relative to the project directory.
`files` | `List<File>` | | The list of files to format. It sets the `source.files` argument. *(Read-only)*
`fileNames` | `List<String>` | `null` | The file names to format, relative to the project directory. If `null`, all files contained in `baseDir` will be formatted.
`formatCurrentBranch` | `boolean` | `false` | Whether to format only the files contained in `baseDir` that are added or modified in the current Git branch. It sets the `format.current.branch` argument.
`formatLatestAuthor` | `boolean` | `false` | Whether to format only the files contained in `baseDir` that are added or modified in the latest Git commits of the same author. It sets the `format.latest.author` argument.
`formatLocalChanges` | `boolean` | `false` | Whether to format only the unstaged files contained in `baseDir`. It sets the `format.local.changes` argument.
`gitWorkingBranchName` | `String` | `"master"` | The Git working branch name. It sets the `git.working.branch.name` argument.
`includeSubrepositories` | `boolean` | `false` | Whether to format files that are in read-only subrepositories. It sets the `include.subrepositories` argument.
`maxLineLength` | `int` | `80` | The maximum number of characters allowed in Java files. It sets the `max.line.length` argument.
`printErrors` | `boolean` | `true` | Whether to print formatting errors on the Standard Output stream. It sets the `source.print.errors` argument.
`processorThreadCount` | `int` | `5` | The number of threads used by Source Formatter. It sets the `processor.thread.count` argument.
`showDocumentation` | `boolean` | `false` | Whether to show the documentation for the source formatting issues, if present. It sets the `show.documentation` argument.
`showStatusUpdates` | `boolean` | `false` | Whether to show status updates during source formatting, if present. It sets the `show.status.updates` argument.
`throwException` | `boolean` | `false` | Whether to fail the build if formatting errors are found. It sets the `source.throw.exception` argument.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the Source Formatter.

### Liferay Source Formatter Dependency [](id=liferay-source-formatter-dependency)

By default, the plugin creates a configuration called `sourceFormatter` and adds
a dependency to the latest released version of Liferay Source Formatter. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `sourceFormatter` configuration:

```gradle
dependencies {
    sourceFormatter group: "com.liferay", name: "com.liferay.source.formatter", version: "1.0.449"
}
```

### System Properties [](id=system-properties)

It is possible to set the default values of the `formatCurrentBranch`,
`formatLatestAuthor`, and `formatLocalChanges` properties for a
`FormatSourceTask` task via system properties:

- `-D${task.name}.format.current.branch=true`
- `-D${task.name}.format.latest.author=true`
- `-D${task.name}.format.local.changes=true`

For example, run the following Bash command to format only the unstaged files in
the project:

```bash
./gradlew formatSource -DformatSource.format.local.changes=true
```
