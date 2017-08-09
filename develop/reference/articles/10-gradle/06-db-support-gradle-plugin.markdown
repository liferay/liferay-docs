# DB Support Gradle Plugin [](id=db-support-gradle-plugin)

The DB Support Gradle plugin lets you run the [Liferay DB Support](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-db-support)
tool to execute certain actions on a local Liferay database. So far, the
following actions are available:

- Cleans the Liferay database from the Service Builder tables and rows of a
module.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
	dependencies {
		classpath group: "com.liferay", name: "com.liferay.gradle.plugins.db.support", version: "1.0.2"
	}

	repositories {
		maven {
			url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
		}
	}
}

apply plugin: "com.liferay.portal.tools.db.support"
```

Since the plugin automatically resolves the Liferay DB Support library as
a dependency, you have to configure a repository that hosts the library and its
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
`cleanServiceBuilder` | \- | [`CleanServiceBuilderTask`](#cleanservicebuildertask) | Cleans the Liferay database from the Service Builder tables and rows of a module.

The `cleanServiceBuilder` task is automatically configured with sensible
defaults, depending on whether the [`base`](https://docs.gradle.org/current/userguide/standard_plugins.html#N135C1)
plugin is applied:

Property Name | Default Value
------------- | -------------
[`servletContextName`](#servletcontextname) | <p>**If the `base` plugin is applied:**  The bundle symbolic name of the project inferred via the [`OsgiHelper`](https://github.com/gradle/gradle/blob/master/subprojects/osgi/src/main/java/org/gradle/api/internal/plugins/osgi/OsgiHelper.java) class.</p><p>**Otherwise:** `null`</p>
[`serviceXmlFile`](#servicexmlfile) | `"${project.projectDir}/service.xml"`

### CleanServiceBuilderTask [](id=cleanservicebuildertask)

Tasks of type `BuildDeploymentHelperTask` extend [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html),
so all its properties and methods, such as [`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args\(java.lang.Iterable\))
and [`maxHeapSize`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:maxHeapSize),
are available. They also have the following properties set by default:

Property Name | Default Value
------------- | -------------
[`args`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:args) | The DB Support command line arguments.
[`classpath`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:classpath) | [`project.configurations.dbSupport`](#jdbc-drivers-dependency) + [`project.configurations.dbSupportTool`](#liferay-db-support-dependency)
[`main`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html#org.gradle.api.tasks.JavaExec:main) | `"com.liferay.portal.tools.db.support.DBSupport"`

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`password` | `String` | `null` | The user password for connecting to the Liferay database. It sets the `--password` argument. If [`propertiesFile`](#propertiesfile) is set, this property has no effect.
<a name="propertiesfile"></a>`propertiesFile` | `File` | `null` | The `portal-ext.properties` file that contains the JDBC settings for connecting to the Liferay database. It sets the `--properties-file` argument.
<a name="servletcontextname"></a>`servletContextName` | `String` | `null` | The servlet context name (usually the value of the `Bundle-Symbolic-Name` manifest header) of the module. It sets the `--servlet-context-name` argument.
<a name="servicexmlfile"></a>`serviceXmlFile` | `File` | `null` | The `service.xml` file of the module. It sets the `--service-xml-file` argument.
`url` | `String` | `null` | The JDBC URL for connecting to the Liferay database. It sets the `--url` argument. If [`propertiesFile`](#propertiesfile) is set, this property has no effect.
`userName` | `String` | `null` | The user name for connecting to the Liferay database. It sets the `--user-name` argument. If [`propertiesFile`](#propertiesfile) is set, this property has no effect.

The properties of type `File` support any type that can be resolved by [`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.css.Object\)).
Moreover, it is possible to use Closures and Callables as values for the `int`
and `String` properties to defer evaluation until task execution.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the Deployment Helper.

### JDBC Drivers Dependency [](id=jdbc-drivers-dependency)

The plugin creates a configuration called `dbSupport`, which can be used to
provide the suitable JDBC driver for your Liferay database:

```gradle
dependencies {
	dbSupport group: "mysql", name: "mysql-connector-java", version: "5.1.23"
	dbSupport group: "org.mariadb.jdbc", name: "mariadb-java-client", version: "1.1.9"
	dbSupport group: "org.postgresql", name: "postgresql", version: "9.4-1201-jdbc41"
}
```

### Liferay DB Support Dependency [](id=liferay-db-support-dependency)

By default, the plugin creates a configuration called `dbSupportTool` and adds a
dependency to the latest released version of the Liferay DB Support. It is
possible to override this setting and use a specific version of the tool by
manually adding a dependency to the `dbSupportTool` configuration:

```gradle
dependencies {
	dbSupportTool group: "com.liferay", name: "com.liferay.portal.tools.db.support", version: "1.0.5"
}
```
