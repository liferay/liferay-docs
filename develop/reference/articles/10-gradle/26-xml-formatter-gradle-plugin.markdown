# XML Formatter Gradle Plugin [](id=xml-formatter-gradle-plugin)

The XML Formatter Gradle plugin lets you format a project's XML files using the
[Liferay XML Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/xml-formatter)
tool.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.xml.formatter", version: "1.0.8"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.xml.formatter"
```

Since the plugin automatically resolves the Liferay XML Formatter library as a
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

The plugin adds one task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`formatXML` | \- | [`FormatXMLTask`](#formatxmltask) | Runs the Liferay XML Formatter to format the project files.

If the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied, the task formats XML files contained in the [`resources`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceSet.html#org.gradle.api.tasks.SourceSet:resources)
directories of the `main` [source set](https://docs.gradle.org/current/userguide/java_plugin.html#N1503E)
(by default: `src/main/resources/**/*.xml`).

### FormatXMLTask [](id=formatxmltask)

Tasks of type `FormatXMLTask` extend [`SourceTask`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html),
so all its properties and methods, such as [`include`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:include\(java.lang.Iterable\))
and [`exclude`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:exclude\(java.lang.Iterable\)),
are available.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`classpath` | [`FileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileCollection.html) | [`project.configurations.xmlFormatter`](#liferay-xml-formatter-dependency) | The classpath for executing the main class.
`mainClassName` | `String` | `"com.liferay.xml.formatter.XMLFormatter"` | The fully qualified name of the XML Formatter Main class.
`stripComments` | `boolean` | `false` | Whether to remove all the comments from the XML files. It sets the `xml.formatter.strip.comments` argument.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the XML Formatter.

### Liferay XML Formatter Dependency [](id=liferay-xml-formatter-dependency)

By default, the plugin creates a configuration called `xmlFormatter` and adds
a dependency to the latest released version of the Liferay XML Formatter. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `xmlFormatter` configuration:

```gradle
dependencies {
    xmlFormatter group: "com.liferay", name: "com.liferay.xml.formatter", version: "1.0.4"
}
```
