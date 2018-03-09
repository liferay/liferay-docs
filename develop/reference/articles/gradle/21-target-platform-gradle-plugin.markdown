# Target Platform Gradle Plugin

The Target Platform Gradle plugin helps with building multiple projects
against a declared API target platform. Java dependencies can be managed with
Maven BOMs and OSGi modules can be resolved against an OSGi distribution.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage

To use the plugin, include it in your build script:

```gradle
buildscript {
	dependencies {
		classpath group: "com.liferay", name: "com.liferay.gradle.plugins.target.platform", version: "1.0.0"
	}

	repositories {
		maven {
			url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
		}
	}
}
```

There are two Target Platform Gradle plugins you can apply to your project:

- The [*Target Platform Plugin*](#target-platform-plugin) helps to configure
your projects to build against an established set of platform artifacts,
including Java and OSGi dependencies.

	```gradle
	apply plugin: "com.liferay.target.platform"
	```

- The [*Target Platform IDE Plugin*](#target-platform-ide-plugin) is a superset
of the Target Platform Plugin (it applies the above plugin) and also adds IDE
integration for searching and debugging source code in the target platform
artifacts.

	```gradle
	apply plugin: "com.liferay.target.platform.ide"
	```

Since the plugin automatically resolves target platform configurations as
dependencies, you must configure a repository that hosts these artifacts. The
Liferay CDN repository hosts them all:

```gradle
repositories {
	maven {
		url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
	}
}
```

## Target Platform Plugin

The plugin applies the [Spring Dependency Management Plugin](https://github.com/spring-gradle-plugins/dependency-management-plugin)
and then adds several specific configurations to configure the BOMs that are
imported to manage Java dependencies and the various artifacts used in resolving
OSGi dependencies. Also, a new `resolve` task is added to resolve all OSGi
requirements against a declared distribution artifact.

The plugin adds a series of configurations to your project:

Name | Description
---- | -----------
`targetPlatformBOMs` | Configures all the BOMs to import as managed dependencies.
`targetPlatformBundles` | Configures all the bundles in addition to the distro to resolve against.
`targetPlatformDistro` | Configures the distro JAR file to use as base for resolving against.
`targetPlatformRequirements` | Configures the list of JAR files to use as run requirements for resolving.

The plugin adds a task `resolve` of type [`ResolveTask`](#resolvetask) to your
project that performs an OSGi resolve operation using the
`targetPlatformRequirements` configuration as the basis of the requirements. The
`targetPlatformBundles` configuration is used as a repository for the resolver
to resolve requirements. Lastly, the `targetPlatformDistro` configuration is
used to provide the *distro* artifact for the resolve process. The *distro* is
the artifact that provides all the OSGi capabilities of the target platform. All
of these parameters are used to create a `bndrun` file that can be used as input
into the Bndrun resolve operation.

## Target Platform IDE Plugin

The plugin applies the [Target Platform](#target-platform-plugin) and the
[`eclipse`](https://docs.gradle.org/current/userguide/eclipse_plugin.html)
plugins to your project, and also adds a special `targetPlatformIDE`
configuration, which is used to configure the Eclipse model in Gradle to add all
target platform artifacts to the classpath so they are visible to Eclipse's Java
Model Search (for looking up sources to classes).

## Project Extension

The Target Platform plugin exposes the following properties through the
extension named `targetPlatform`:

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`ignoreResolveFailures` | `boolean` | `true` | Whether to ignore resolve failures found when executing tasks of type [`ResolveTask`](#resolvetask).
`subprojects` | `Set<Project>` | `project.subprojects` | The subprojects to configure with target platform support, including dependency management and the `resolve` task.

The same extension exposes the following methods:

Method | Description
------ | -----------
`TargetPlatformExtension onlyIf(Closure<Boolean> onlyIfClosure)` | Includes a subproject in the target platform configuration if the given closure returns `true`. The closure is evaluated at the end of the subproject configuration phase and is passed a single parameter: the subproject. If the closure returns `false`, the subproject is not included in the target platform configuration
`TargetPlatformExtension onlyIf(Spec<Project> onlyIfSpec)` | Includes a subproject in the target platform configuration if the given spec is satisfied. The spec is evaluated at the end of the subproject configuration phase. If the spec is not satisfied, the subproject is not included in the target platform configuration.
`TargetPlatformExtension resolveOnlyIf(Closure<Boolean> resolveOnlyIfClosure)` | Includes a subproject in the resolving process (including both the requirements and bundles configuration) if the given closure returns `true`. The closure is evaluated at the end of the subproject configuration phase and is passed a single parameter: the subproject. If the closure returns `false`, the subproject is the resolution process.
`TargetPlatformExtension resolveOnlyIf(Spec<Project> resolveOnlyIfSpec)` | Includes a subproject in the resolving platform configuration if the given spec is satisfied. The spec is evaluated at the end of the subproject configuration phase. If the spec is not satisfied, the subproject is not included in the target platform configuration.
`TargetPlatformExtension subprojects(Iterable<Project> subprojects)` | Includes additional projects to be configured with Target Platform support.
`TargetPlatformExtension subprojects(Project... subprojects)` | Includes additional projects to be configured with Target Platform support.

## Tasks

### ResolveTask

The purpose of this task is to resolve an OSGi module (or all OSGi modules of
subprojects) against the available `targetPlatformBundles` and
`targetPlatformDistro` configurations. By default, the `targetPlatformBundles`
are all the artifacts created by all the subprojects. The `targetPlatformDistro`
must be set explicitly to a valid distribution artifact. When the task is
performed, a `bndrun` file is generated using the specified
`targetPlatformDistro` as the `-distro` instruction; the `-runrequirements` are
a set of `osgi.identity` requirements for the `targetPlatformRequirements`
configuration. If the resolve operation is able to find a valid set of
`-runbundles` that match the `-runrequirements`, then the task passes
successfully (the resolution is valid). If a set of run bundles can't be found,
the resolution has failed and the failed requirements are listed as output of
the task.

#### Task Properties

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`bndrunFile` | `File` | `null` | If this property is specified, it is used as the `bndrun` file to input into the resolver.
`bundlesFileCollection` | `FileCollection` | All JAR files of subprojects with `jar` task | The input to `bndrun` resolve operation.
`distroFileCollection` | `FileCollection` | `null` | The *distro* parameter for the generated `bndrun` file.
`ignoreFailures` | `boolean` | `false` | Whether the `resolve` task should ignore failing the build for resolution errors.
`offline` | `boolean` | `null` | Whether to run the bndrun resolve operation in offline mode.
`requirementsFileCollection` | `FileCollection` | <p>**For the root project:** All the output JAR files of the subprojects.</p><p>**For subprojects:** The output JAR file of the subproject.</p> | For each resolve operation, the requirements must be specified in the `bndrun` file; each of the JARs in this collection generate an `osgi.identify` requirement in the `bndrun` file.

## Additional Configuration

There are additional configurations that you can use to configure the target
platform.

### Target Platform BOMs Dependency

The plugin creates a configuration called `targetPlatformBOMs` with no defaults.
You can use this dependency to set which BOMs to import to configure your target
platform.

```gradle
dependencies {
	targetPlatformBOMs group: "com.liferay", name: "com.liferay.ce.portal.bom", version: "7.1.0"
	targetPlatformBOMs group: "com.liferay", name: "com.liferay.ce.portal.compile.only", version: "7.1.0"
}
```

### Target Platform Bundles Dependency

The plugin creates a configuration called `targetPlatformBundles`. It is
configured with default dependencies to all resolvable bundles in a
multi-project build (e.g., all projects in multi-project build that have a `jar`
task). This can be used to specify additional bundles that should be added to
the set of bundles given to `resolve` task to resolve against when checking for
OSGi requirements.

```gradle
dependencies {
	targetPlatformBundles group: "com.google.guava", name: "guava", version: "23.0"
}
```

### Target Platform Distro Dependency

The plugin creates a configuration called `targetPlatformDistro`. It is has no
default so you must specify which artifact you want to use as the distribution
to resolve against.

```gradle
dependencies {
	targetPlatformDistro group: "com.liferay", name: "com.liferay.ce.portal.distro", version: "7.1.0"
}
```

If you have created your own custom distro JAR that is available locally, you
can use the `files` method to add it to the configuration.

```gradle
dependencies {
	targetPlatformDistro files("custom-distro.jar")
}
```

### Target Platform Requirements Dependency

The plugin creates a configuration called `targetPlatformRequirements`. It is
configured with default dependencies to all resolvable bundles in a
multi-project build (e.g., all projects in multi-project build that have a `jar`
task). This is can be used to specify additional bundles that should be added to
the set of bundles given to the `resolve` task to set as `osgi.identity`
requirements.

```gradle
dependencies {
	targetPlatformRequirements group: "com.liferay", name: "com.liferay.other.bundle", version: "1.0"
}
```