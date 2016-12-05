# Soy Gradle Plugin [](id=soy-gradle-plugin)

The Soy Gradle plugin lets you compile [Closure Templates](https://developers.google.com/closure/templates/)
into JavaScript functions. It also lets you use a custom localization mechanism
in the generated `.soy.js` files by replacing [`goog.getMsg`](https://developers.google.com/closure/templates/docs/translation#closurecompiler)
definitions with a different function call (e.g., `Liferay.Language.get`).

The plugin has been successfully tested with Gradle 2.5 up to 3.2.1.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.soy", version: "2.0.0"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}
```

There are two Soy Gradle plugins you can apply to your project:

- Apply the [*Soy Plugin*](#soy-plugin) to compile Closure Templates into
JavaScript functions:

    ```gradle
    apply plugin: "com.liferay.soy"
    ```

- Apply the [*Soy Translation Plugin*](#soy-translation-plugin) to use a custom
localization mechanism in the generated `.soy.js` files:

    ```gradle
    apply plugin: "com.liferay.soy.translation"
    ```

Since the Soy Gradle plugin automatically resolves the Soy library as a
dependency, you have to configure a repository that hosts the library and its
transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
    maven {
        url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
    }
}
```

## Soy Plugin [](id=soy-plugin)

The Soy plugin adds one task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`buildSoy` | \- | [`BuildSoyTask`](#buildsoytask) | Compiles Closure Templates into JavaScript functions.

The `buildSoy` task is automatically configured with sensible defaults,
depending on whether the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied:

Property Name | Default Value
------------- | -------------
[`includes`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:includes) | `["**/*.soy"]`
[`source`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:source) | <p>**If the `java` plugin is applied:** The first `resources` directory of the `main` source set (by default, `src/main/resources`).</p><p>**Otherwise:** `[]`</p>

### Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the Soy library.

#### Soy Dependency [](id=soy-dependency)

By default, the plugin creates a configuration called `soy` and adds a
dependency to the `2015-04-10` version of the Soy library. It is possible to
override this setting and use a specific version of the tool by manually adding
a dependency to the `soy` configuration:

```gradle
dependencies {
    soy group: "com.google.template", name: "soy", version: "2015-04-10"
}
```

## Soy Translation Plugin [](id=soy-translation-plugin)

The Soy Translation plugin adds one task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`replaceSoyTranslation` | - `configJSModules` if [`com.liferay.js.module.config.generator`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-js-module-config-generator) is applied <br> - `processResources` if `java` is applied <br> - `transpileJS` if [`com.liferay.js.transpiler`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-js-transpiler is applied) | [`ReplaceSoyTranslationTask`](#replacesoytranslationtask) | Replaces `goog.getMsg` definitions with `Liferay.Language.get` calls.

The plugin also adds the following dependencies to tasks defined by the `java`
plugin:

Name | Depends On
---- | ----------
`classes` | `replaceSoyTranslation`

The `replaceSoyTranslation` task is automatically configured with sensible
defaults, depending on whether the `java` plugin is applied:

Property Name | Default Value
------------- | -------------
[`includes`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:includes) | `["**/*.soy.js"]`
[`replacementClosure`](#replacementclosure) | Replaces `goog.getMsg` definitions with `Liferay.Language.get` calls.
[`source`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:source) | <p>**If the `java` plugin is applied:** `project.sourceSets.main.output.resourcesDir`</p><p>**Otherwise:** `[]`</p>

## Tasks [](id=tasks)

### BuildSoyTask [](id=buildsoytask)

Tasks of type `BuildSoyTask` extend [`SourceTask`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html),
so all its properties and methods, such as [`include`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:include(java.lang.Iterable))
and [`exclude`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:exclude(java.lang.Iterable)),
are available.

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`classpath` | [`FileCollection`](https://docs.gradle.org/current/javadoc/org/gradle/api/file/FileCollection.html) | [`project.configurations.soy`](#soy-dependency) | The classpath for executing the main class `com.google.template.soy.SoyToJsSrcCompiler`.

### ReplaceSoyTranslationTask [](id=replacesoytranslationtask)

The `ReplaceSoyTranslationTask` task type finds all the `goog.getMsg`
definitions in the project's files and replaces them with a custom function
call.

```javascript
var MSG_EXTERNAL_123 = goog.getMsg('welcome-to-{$releaseInfo}', { 'releaseInfo': opt_data.releaseInfo });
```

A `goog.getMsg` definition looks like the example above, and it has the
following components:

- *variable name*: `MSG_EXTERNAL_123`
- *language key*: `welcome-to-{$releaseInfo}`
- *arguments object*: `{ 'releaseInfo': opt_data.releaseInfo }`

Tasks of type `ReplaceSoyTranslationTask` extend [`SourceTask`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html),
so all its properties and methods, such as [`include`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:include(java.lang.Iterable))
and [`exclude`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceTask.html#org.gradle.api.tasks.SourceTask:exclude(java.lang.Iterable)),
are available.

#### Task Properties [](id=task-properties-0)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="replacementclosure"></a>`replacementClosure` | `Closure<String>` | `null` | The closure invoked in order to get the replacement for `goog.getMsg` definitions. The given closure is passed the *variable name*, *language key*, and *arguments object* as its parameters.
