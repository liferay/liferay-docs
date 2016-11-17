# Javadoc Formatter Gradle Plugin

The Javadoc Formatter Gradle plugin lets you format project Javadoc comments
using the [Liferay Javadoc Formatter tool](https://github.com/liferay/liferay-portal/tree/master/modules/util/javadoc-formatter).
The tool lets you generate:

- Default [`@author`](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#@author)
  tags to all classes.
- Comment stubs to classes, fields, and methods.
- Missing [`@Override`](https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html)
  annotations.
- An XML representation of the Javadoc comments, which can be used by tools in
  order to index the Javadocs of the project.

## Usage

To use the plugin, include it in your build script:

```gradle
buildscript {
	dependencies {
		classpath group: "com.liferay", name: "com.liferay.gradle.plugins.javadoc.formatter", version: "1.0.10"
	}

	repositories {
		maven {
			url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
		}
	}
}

apply plugin: "com.liferay.javadoc.formatter"
```

Since the plugin automatically resolves the Liferay Javadoc Formatter library as
a dependency, you have to configure a repository that hosts the library and its
transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
	maven {
		url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
	}
}
```

## Tasks

The plugin adds one task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`formatJavadoc` | \- | [`FormatJavadocTask`](#formatjavadoctask) | Runs the Liferay Javadoc Formatter to format files.

### FormatJavadocTask

Tasks of type `FormatJavadocTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, like [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args(java.lang.Iterable))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize),
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | Javadoc Formatter command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.javadocFormatter`](#liferay-javadoc-formatter-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.javadoc.formatter.JavadocFormatter"`

#### Task Properties

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`author` | `String` | `"Brian Wing Shun Chan"` | The value of the `@author` tag to add at class level if missing. It sets the `javadoc.author` argument.
`generateXML` | `boolean` | `false` | Whether to generate a XML representation of the Javadoc comments. The XML files are generated in the `src/main/resources` directory only if the Java files are contained in `src/main/java`. It sets the `javadoc.generate.xml` argument.
`initializeMissingJavadocs` | `boolean` | `false` | Whether to add comment stubs at the class, field, and method levels. If `false`, only the class-level `@author` is added. It sets the `javadoc.init` argument.
`limits` | `List<String>` | `[]` | The Java file name patterns, relative to [`workingDir`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:workingDir), to include when formatting Javadoc comments. The patterns must be specified without the `.java` file type suffix. If empty, all Java files are formatted. It sets the `javadoc.limit` argument.
`lowestSupportedJavaVersion` | `double` | `1.7` | If a method is annotated with the [`@SinceJava`](https://github.com/liferay/liferay-portal/blob/master/modules/util/javadoc-formatter/src/main/java/com/liferay/javadoc/formatter/SinceJava.java) annotation and its `value` argument is greater than the value specified for the `lowestSupportedJavaVersion` property, then the `@Override` annotation is not automatically added, even if it is missing. It sets the `javadoc.lowest.supported.java.version` argument. See [LPS-37353](https://issues.liferay.com/browse/LPS-37353).
`outputFilePrefix` | `String` | `"javadocs"` | The file name prefix of the XML representation of the Javadoc comments. If `generateXML` is `false`, this property is not used. It sets the `javadoc.output.file.prefix` argument.
`updateJavadocs` | `boolean` | `false` | Whether to fix existing comment blocks by adding missing tags. It sets the `javadoc.update` argument.

It is possible to use Closures and Callables as values for the `String`
properties, to defer evaluation until task execution.

#### Task Methods

Method | Description
------ | -----------
`FormatJavadocTask dirNames(Iterable<Object> limits)` | Adds Java file name patterns, relative to `workingDir`, to include when formatting Javadoc comments.
`FormatJavadocTask dirNames(Object... limits)` | Adds Java file name patterns, relative to `workingDir`, to include when formatting Javadoc comments.

## Additional Configuration

There are additional configurations that can help you use the Javadoc Formatter.

### Liferay Javadoc Formatter Dependency

By default, the plugin creates a configuration called `javadocFormatter` and adds
a dependency to the latest released version of the Liferay Javadoc Formatter. It
is possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `javadocFormatter` configuration:

```gradle
dependencies {
	javadocFormatter group: "com.liferay", name: "com.liferay.javadoc.formatter", version: "1.0.16"
}
```

If the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied, the `javadocFormatter` configuration automatically extends
from the [`compile`](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_plugin_and_dependency_management)
configuration.

### System Properties

It is possible to set the default values of the `generateXML`,
`initializeMissingJavadocs`, `limits`, and `updateJavadocs` properties for a
`FormatJavadocTask` task via system properties:

- `-D${task.name}.generate.xml=true`
- `-D${task.name}.init=SomeClassName1,SomeClassName2,com.liferay.portal.**`
- `-D${task.name}.limit=**/com/example/`
- `-D${task.name}.update=true`