# Theme Builder Gradle Plugin [](id=theme-builder-gradle-plugin)

The Theme Builder Gradle plugin lets you run the [Liferay Theme Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-theme-builder)
tool to build the Liferay theme files in your project.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.theme.builder", version: "2.0.3"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.portal.tools.theme.builder"
```

The Theme Builder plugin automatically applies the [`war`](https://docs.gradle.org/current/userguide/war_plugin.html)
plugin. It also applies the [`com.liferay.css.builder`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-css-builder)
plugin to compile the [Sass](http://sass-lang.com/) files in the theme.

Since the plugin automatically resolves the Liferay Theme Builder library as a
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
`buildTheme` | \- | [`BuildThemeTask`](#buildthemetask) | Builds the theme files.

The plugin also adds the following dependencies to tasks defined by the
`com.liferay.css.builder` and `war` plugins:

Name | Depends On
---- | ----------
[`buildCSS`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-css-builder#tasks) | `buildTheme`
[`war`](https://docs.gradle.org/current/userguide/war_plugin.html#sec:war_default_settings) | `buildTheme`

The `buildCSS` dependency compiles the Sass files contained in the directory
specified by the [`buildTheme.outputDir`](#outputdir) property. Moreover, the
`war` task is configured as follows

- exclude the directory specified in the [`buildTheme.diffsDir`](#diffsdir)
property from the WAR file.
- include the files contained in the [`buildTheme.outputDir`](#outputdir)
directory into the WAR file.
- include only the compiled CSS files, not SCSS files, into the WAR file.

The `buildTheme` task is automatically configured with sensible defaults:

Property Name | Default Value
------------- | -------------
[`diffsDir`](#diffsdir) | `project.webAppDir`
[`outputDir`](#outputdir) | `"${project.buildDir}/buildTheme"`
[`parentFile`](#parentfile) | The first JAR file in the [`parentThemes`](#parent-theme-dependencies) configuration that contains a `META-INF/resources/${buildTheme.parentName}` directory, or the first WAR file in the `parentThemes` configuration whose name starts with `${parentName}-theme-`.
[`parentName`](#parentname) | `"_styled"`
[`templateExtension`](#templateextension) | `"ftl"`
[`themeName`](#themename) | `project.name`
[`unstyledFile`](#unstyledfile) | The first JAR file in the [`parentThemes`](#parent-theme-dependencies) configuration that contains a `META-INF/resources/_unstyled` directory.

### BuildThemeTask [](id=buildthemetask)

Tasks of type `BuildThemeTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, such as [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args\(java.css.Iterable\))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize),
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | Theme Builder command line arguments
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.themeBuilder`](#liferay-theme-builder-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.portal.tools.theme.builder.ThemeBuilder"`

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="diffsdir"></a>`diffsDir` | `File` | `null` | The directory that contains the files to copy over the parent theme. It sets the `--diffs-dir` argument.
<a name="outputdir"></a>`outputDir` | `File` | `null` | The directory where to build the theme. It sets the `--output-dir` argument.
<a name="parentdir"></a>`parentDir` | `File` | `null` | The directory of the parent theme. It sets the `--parent-path` argument.
<a name="parentfile"></a>`parentFile` | `File` | `null` | The JAR file of the parent theme. If `parentDir` is specified, this property has no effect. It sets the `--parent-path` argument.
<a name="parentname"></a>`parentName` | `String` | `null` | The name of the parent theme. It sets the `--parent-name` argument.
<a name="templateextension"></a>`templateExtension` | `String` | `null` | The extension of the template files, usually `"ftl"` or `"vm"`. It sets the `--template-extension` argument.
<a name="themename"></a>`themeName` | `String` | `null` | The name of the new theme. It sets the `--name` argument.
<a name="unstyleddir"></a>`unstyledDir` | `File` | `null` | The directory of [Liferay Frontend Theme Unstyled](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-theme/frontend-theme-unstyled). It sets the `--unstyled-dir` argument.
<a name="unstyledfile"></a>`unstyledFile` | `File` | `null` | The JAR file of [Liferay Frontend Theme Unstyled](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-theme/frontend-theme-unstyled). If `unstyledDir` is specified, this property has no effect. It sets the `--unstyled-dir` argument.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties to defer evaluation until task execution.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the CSS Builder.

### Liferay Theme Builder Dependency [](id=liferay-theme-builder-dependency)

By default, the plugin creates a configuration called `themeBuilder` and adds a
dependency to the latest released version of the Liferay Theme Builder. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `themeBuilder` configuration:

```gradle
dependencies {
    themeBuilder group: "com.liferay", name: "com.liferay.portal.tools.theme.builder", version: "1.1.4"
}
```

### Parent Theme Dependencies [](id=parent-theme-dependencies)

By default, the plugin creates a configuration called `parentThemes` and adds
dependencies to the latest released versions of the
[Liferay Frontend Theme Styled](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-theme/frontend-theme-styled),
[Liferay Frontend Theme Unstyled](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-theme/frontend-theme-unstyled),
and [Liferay Frontend Theme Classic](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-theme/frontend-theme-classic)
artifacts. It is possible to override this setting and use a specific version of
the artifacts by manually adding dependencies to the `parentThemes`
configuration:

```gradle
dependencies {
    parentThemes group: "com.liferay", name: "com.liferay.frontend.theme.styled", version: "2.0.13"
    parentThemes group: "com.liferay", name: "com.liferay.frontend.theme.unstyled", version: "2.0.13"
    parentThemes group: "com.liferay.plugins", name: "classic-theme", version: "1.0.29"
}
```
