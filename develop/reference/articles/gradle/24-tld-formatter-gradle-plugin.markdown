# TLD Formatter Gradle Plugin [](id=tld-formatter-gradle-plugin)

The TLD Formatter Gradle plugin lets you format a project's TLD files using the
[Liferay TLD Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/tld-formatter)
tool.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.tld.formatter", version: "1.0.6"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.tld.formatter"
```

Since the plugin automatically resolves the Liferay TLD Formatter library as a
dependency, you have to configure a repository that hosts the library
and its transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
    maven {
        url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
    }
}
```

## Tasks [](id=tasks)

The plugin adds one task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`formatTLD` | \- | [`FormatTLDTask`](#formattldtask) | Runs the Liferay TLD Formatter to format files.

### FormatTLDTask [](id=formattldtask)

Tasks of type `FormatTLDTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, such as [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args\(java.lang.Iterable\))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize),
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | TLD Formatter command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.tldFormatter`](#liferay-tld-formatter-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.tld.formatter.TLDFormatter"`

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`plugin` | `boolean` | `true` | Whether to format all the TLD files contained in the [`workingDir`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:workingDir) directory. If `false`, all `liferay-portlet-ext.tld` files are ignored. It sets the `tld.plugin` argument.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the TLD Formatter.

### Liferay TLD Formatter Dependency [](id=liferay-tld-formatter-dependency)

By default, the plugin creates a configuration called `tldFormatter` and adds
a dependency to the latest released version of Liferay TLD Formatter. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `tldFormatter` configuration:

```gradle
dependencies {
    tldFormatter group: "com.liferay", name: "com.liferay.tld.formatter", version: "1.0.4"
}
```
