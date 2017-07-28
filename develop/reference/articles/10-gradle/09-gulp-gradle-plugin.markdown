# Gulp Gradle Plugin [](id=gulp-gradle-plugin)

The Gulp Gradle plugin lets you run [Gulp](http://gulpjs.com/) tasks as part of
your build.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.gulp", version: "2.0.17"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.gulp"
```

The Gulp plugin automatically applies the [`com.liferay.node`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-node)
plugin.

## Tasks [](id=tasks)

The plugin adds one [task rule](https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:task_rules)
to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`gulp<Task>` | `downloadNode`, `npmInstall` | [`ExecuteGulpTask`](#executegulptask) | Executes a named Gulp task.

### ExecuteGulpTask [](id=executegulptask)

Tasks of type `ExecuteGulpTask` extend [`ExecuteNodeScriptTask`](#executenodetask),
so all its properties and methods, such as `args` and `inheritProxy`, are
available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
`scriptFile` | `"node_modules/gulp/bin/gulp.js"`

Gulp must be already installed in the `node_modules` directory of the project;
otherwise, it will not be downloaded by the task. In order to ensure Gulp is
installed, you can add the Gulp dependency to the project's `package.json` file.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`gulpCommand` | `String` | `null` | The Gulp task to execute.

It is possible to use Closures and Callables as values for the `String`
properties to defer evaluation until task execution.
