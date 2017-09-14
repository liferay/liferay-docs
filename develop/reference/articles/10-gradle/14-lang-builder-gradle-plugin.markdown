# Lang Builder Gradle Plugin [](id=lang-builder-gradle-plugin)

The Lang Builder Gradle plugin lets you run the [Liferay Lang Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/lang-builder)
tool in order to sort and translate the language keys in your project.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.lang.builder", version: "2.1.0"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.lang.builder"
```

Since the plugin automatically resolves the Liferay Lang Builder library as a
dependency, you have to configure a repository that hosts the library and its
transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
    maven {
        url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
	}
}
```

See [this page](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/automatically-generating-language-files)
on the *Liferay Developer Network* for more information about usage of the Lang
Builder Gradle plugin.

## Tasks [](id=tasks)

The plugin adds one task to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`buildLang` | \- | [`BuildLangTask`](#buildlangtask) | Runs Liferay Lang Builder to translate language property files.

The `buildLang` task is automatically configured with sensible defaults,
depending on whether the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin is applied:

Property Name | Default Value
------------- | -------------
[`langDir`](#langdir) | <p>**If the `java` plugin is applied:** The directory `content` in the first `resources` directory of the `main` source set (by default: `src/main/resources/content`).</p><p>**Otherwise:** `null`</p>

### BuildLangTask [](id=buildlangtask)

Tasks of type `BuildLangTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, such as [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args\(java.lang.Iterable\))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize),
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | Lang Builder command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.langBuilder`](#liferay-lang-builder-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.lang.builder.LangBuilder"`

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`excludedLanguageIds` | `Set<String>` | `["da", "de", "fi", "ja", "nl", "pt_PT", "sv"]` | The language IDs to exclude in the automatic translation. It sets the `lang.excluded.language.ids` argument.
<a name="langdir"></a>`langDir` | `File` | `null` | The directory where the language properties files are saved. It sets the `lang.dir` argument.
`langFileName` | `String` | `"Language"` | The file name prefix of the language properties files (e.g., `Language_it.properties`). It sets the `lang.file` argument.
`plugin` | `boolean` | `true` | Whether to check for duplicate language keys between the project and the portal. If `portalLanguagePropertiesFile` is not set, this property has no effect. It sets the `lang.plugin` argument.
`portalLanguagePropertiesFile` | `File` | `null` | The `Language.properties` file of the portal. It sets the `lang.portal.language.properties.file` argument.
`translate` | `boolean` | `true` | Whether to translate the language keys and generate a language properties file for each locale that's supported by Liferay. It sets the `lang.translate` argument.
`translateSubscriptionKey` | `String` | `null` | The subscription key for Microsoft Translation integration. Subscription to the Translator Text Translation API on Microsoft Cognitive Services is required. Basic subscriptions, up to 2 million characters a month, are free. See [here](http://docs.microsofttranslator.com/text-translate.html) for more information. It sets the `lang.translate.subscription.key` argument.

The properties of type `File` support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.lang.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties, to defer evaluation until task execution.

#### Task Methods [](id=task-methods)

Method | Description
------ | -----------
`BuildLangTask excludedLanguageIds(Iterable<Object> excludedLanguageIds)` | Adds language IDs to exclude in the automatic translation.
`BuildLangTask excludedLanguageIds(Object... excludedLanguageIds)` | Adds language IDs to exclude in the automatic translation.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the Lang Builder.

### Liferay Lang Builder Dependency [](id=liferay-lang-builder-dependency)

By default, the plugin creates a configuration called `langBuilder` and adds a
dependency to the latest released version of the Liferay Lang Builder. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `langBuilder` configuration:

```gradle
dependencies {
    langBuilder group: "com.liferay", name: "com.liferay.lang.builder", version: "1.0.14"
}
```
