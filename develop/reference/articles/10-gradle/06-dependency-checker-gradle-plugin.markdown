# Dependency Checker Gradle Plugin [](id=dependency-checker-gradle-plugin)

The Dependency Checker Gradle plugin lets you warn users if a specific
configuration dependency is not the latest one available from the Maven central
repository. The plugin eventually fails the build if the dependency age (the
difference between the timestamp of the current version and the latest version)
is above a predetermined threshold.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.dependency.checker", version: "1.0.0"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.dependency.checker"
```

## Project Extension [](id=project-extension)

The Dependency Checker Gradle plugin exposes the following properties through
the extension named `dependencyChecker`:

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="ignorefailures"></a>`ignoreFailures` | `boolean` | `true` | Whether to print an error message instead of failing the build when the dependency check fails, either for a network error or because the dependency is out-of-date.

The same extension exposes the following methods:

Method | Description
------ | -----------
`void maxAge(Map<?, ?> args)` | Declares the max age allowed for a dependency. The `args` map must contain the following entries: <ul><li>`configuration`: the configuration name</li><li>`group`: the dependency group</li><li>`name`: the dependency name</li><li>`maxAge`: an instance of [`groovy.time.Duration`](http://docs.groovy-lang.org/latest/html/api/groovy/time/Duration.html) that represents the maximum age allowed for the dependency</li><li>`throwError`: a `boolean` value representing whether to throw an error if the dependency is out-of-date</li></ul>

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the Deployment Helper.

### Project Properties [](id=project-properties)

It is possible to set the default values of the [`ignoreFailures`](#ignorefailures)
property via the project property `dependencyCheckerIgnoreFailures`:

    -PdependencyCheckerIgnoreFailures=false
