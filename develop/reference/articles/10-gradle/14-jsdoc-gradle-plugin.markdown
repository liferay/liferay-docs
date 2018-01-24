# JSDoc Gradle Plugin [](id=jsdoc-gradle-plugin)

The JSDoc Gradle plugin lets you run the [JSDoc](http://usejsdoc.org/) tool in
order to generate documentation for your project's JavaScript files.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.jsdoc", version: "1.0.2"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.jsdoc"
```

The JSDoc plugin automatically applies the [`com.liferay.node`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-node)
plugin.

## Tasks [](id=tasks)

The plugin adds two tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`downloadJSDoc` | `downloadNode` | `DownloadNodeModuleTask` | Downloads JSDoc in the project's `node_modules` directory.
`jsdoc` | `downloadJSDoc` | [`JSDocTask`](#jsdoctask) | Generates API documentation for the project's JavaScript code.

By default, the `downloadJSDoc` task downloads version `3.5.5` of the `jsdoc`
package. If the project's `package.json` file, however, already lists the
`jsdoc` package in its `dependencies` or `devDependencies`, the `downloadJSDoc`
task is disabled.

The `jsdoc` task is automatically configured with sensible defaults,
depending on whether the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied:

Property Name | Default Value
------------- | -------------
[`destinationDir`](#destinationdir) | <p>**If the `java` plugin is applied:** `"${project.docsDir}/jsdoc"`</p><p>**Otherwise:** `"${project.buildDir}/jsdoc"`</p>
[`sourcesDir`](#sourcesdir) | The directory `META-INF/resources` in the first `resources` directory of the `main` source set (by default, `src/main/resources/META-INF/resources`).

### JSDocTask [](id=jsdoctask)

Tasks of type `JSDocTask` extend `ExecuteNodeScriptTask`, so all its
properties and methods, such as `args`, `inheritProxy`, and `workingDir`, are
available.

They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
`scriptFile` | `"${downloadJSDoc.moduleDir}/jsdoc.js"`

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`configuration` | [`TextResource`](https://docs.gradle.org/current/dsl/org.gradle.api.resources.TextResource.html) | `null` | The JSDoc configuration file. It sets the `--configure` argument.
<a name="destinationdir"></a>`destinationDir` | `File` | `null` | The directory where the JavaScript API documentation files are saved. It sets the `--destination` argument.
`packageJsonFile` | `File` | `"${project.projectDir}/package.json"` | The path to the project's package file. It sets the `--package` argument.
<a name="sourcesdir"></a>`sourcesDir` | `File` | `null` | The directory that contains the files to process.
`readmeFile` | `File` | `null` | The path to the project's README file. It sets the `--readme` argument.
`tutorialsDir` | `File` | `null` | The directory in which JSDoc should search for tutorials. It sets the `--tutorials` argument.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file(java.css.Object)).
