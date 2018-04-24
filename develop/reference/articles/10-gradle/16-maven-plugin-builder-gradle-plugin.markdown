# Maven Plugin Builder Gradle Plugin [](id=maven-plugin-builder-gradle-plugin)

The Maven Plugin Builder Gradle Plugin lets you generate the
[Maven plugin descriptor](https://maven.apache.org/ref/current/maven-plugin-api/plugin.html)
for any [Mojos](https://maven.apache.org/general.html#What_is_a_Mojo) found in
your project.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage [](id=usage)

To use the plugin, include it in your build script:

```gradle
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.maven.plugin.builder", version: "1.2.0"
    }

    repositories {
        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.maven.plugin.builder"
```

## Tasks [](id=tasks)

The plugin adds two tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
`buildPluginDescriptor` |[`compileJava`](https://docs.gradle.org/current/userguide/java_plugin.html#sec:compile), [`WriteMavenSettings`](#writemavensettings) | [`BuildPluginDescriptorTask`](#buildplugindescriptortask) | Generates the Maven plugin descriptor for the project.
<a name="writemavensettings"></a>`WriteMavenSettings` | \- | [`WriteMavenSettingsTask`](#writemavensettingstask) | Writes a temporary Maven settings file to be used during subsequent Maven invocations.

The Maven Plugin Builder Plugin automatically applies the [`java`](https://docs.gradle.org/current/userguide/java_plugin.html)
plugin.

The plugin also adds the following dependencies to tasks defined by the [`maven`](https://docs.gradle.org/current/userguide/maven_plugin.html)
plugin:

Name | Depends On
---- | ----------
`install`, `uploadArchives`, and all the other tasks of type [`Upload`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Upload.html) | `buildPluginDescriptor`

The `buildPluginDescriptor` task is automatically configured with sensible
defaults:

Property Name | Default Value
------------- | -------------
[`classesDir`](#classesdir) | `sourceSets.main.output.classesDir`
[`mavenEmbedderClasspath`](#mavenembedderclasspath) | [`configurations.mavenEmbedder`](#maven-embedder-dependency)
[`mavenSettingsFile`](#mavensettingsfile) | [`writeMavenSettings.outputFile`](#outputfile)
[`outputDir`](#outputdir) | The directory `META-INF/maven` in the first `resources` directory of the `main` source set (by default: `src/main/resources/META-INF/maven`).
[`pomArtifactId`](#pomartifactid) | The bundle symbolic name of the project inferred via the [`OsgiHelper`](https://github.com/gradle/gradle/blob/master/subprojects/osgi/src/main/java/org/gradle/api/internal/plugins/osgi/OsgiHelper.java) class.
[`pomGroupId`](#pomgroupid) | [`project.group`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:group)
[`pomVersion`](#pomversion) | [`project.version`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:version) (if it ends with `"-SNAPSHOT"`, the suffix will be removed)
[`sourceDir`](#sourcedir) | The first `java` directory of the `main` source set (by default: `src/main/java`).

The plugin ensures that the `processResources` task always runs before
`buildPluginDescriptor` to let `processResources` copy the newly generated files
in the `buildPluginDescriptor.outputDir` directory.

The `writeMavenSettings` task is also automatically configured with sensible
defaults:

Property Name | Default Value
------------- | -------------
[`localRepositoryDir`](#localrepositorydir) | `maven.repo.local` system property
[`nonProxyHosts`](#nonproxyhosts) | `http.nonProxyHosts` system property
[`outputFile`](#outputfile) | `"${project.buildDir}/settings.xml"`
[`proxyHost`](#proxyhost) | `http.ProxyHost` or `https.proxyHost` system property (depending on the protocol of [`repositoryUrl`](#repositoryurl))
[`proxyPassword`](#proxypassword) | `http.ProxyPassword` or `https.proxyPassword` system property (depending on the protocol of [`repositoryUrl`](#repositoryurl))
[`proxyPort`](#proxyport) | `http.ProxyPort` or `https.proxyPort` system property (depending on the protocol of [`repositoryUrl`](#repositoryurl))
[`proxyUser`](#proxyuser) | `http.ProxyUser` or `https.proxyUser` system property (depending on the protocol of [`repositoryUrl`](#repositoryurl))
[`repositoryUrl`](#repositoryurl) | `repository.url` system property

If running on JDK8+, the plugin also disables the [*doclint*](http://docs.oracle.com/javase/8/docs/technotes/tools/unix/javadoc.html#BEJEFABE)
feature in all tasks of type [`Javadoc`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.javadoc.Javadoc.html).

### BuildPluginDescriptorTask [](id=buildplugindescriptortask)

Tasks of type `BuildPluginDescriptorTask` work by generating a temporary
`pom.xml` file based on the project, and then invoking the [Maven Embedder](http://maven.apache.org/ref/3.3.9/maven-embedder/)
to build the Maven plugin descriptor.

It is possible to declare information for the plugin descriptor generation using
either [Java 5 Annotations](https://maven.apache.org/plugin-tools/maven-plugin-tools-annotations/)
or [Javadoc Tags](https://maven.apache.org/plugin-tools/maven-plugin-tools-java/).

#### Task Properties [](id=task-properties)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="classesdir"></a>`classesDir` | `File` | `null` | The directory that contains the compiled classes. It sets the value of the [`build.outputDirectory`](http://maven.apache.org/ref/3.3.9/maven-model/maven.html#class_build) element in the generated `pom.xml` file.
`configurationScopeMappings` | `Map<String, String>` | `["compile": "compile", "provided", "provided"]` | The mapping between the configuration names in the Gradle project and the [dependency scopes](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Dependency_Scope) in the `pom.xml` file. It is used to add [`dependencies.dependency`](http://maven.apache.org/ref/3.3.3/maven-model/maven.html#class_dependency) elements in the generated `pom.xml` file.
`forcedExclusions` | `Set<String>` | `[]` | The *group:name:version* notation of the dependencies to always exclude from the ones added in the `pom.xml` file. It adds [`dependencies.dependency.exclusions.exclusion`](http://maven.apache.org/ref/3.3.3/maven-model/maven.html#class_exclusion) elements to the generated `pom.xml` file.
`goalPrefix` | `String` | `null` | The goal prefix for the Maven plugin specified in the descriptor. It sets the value of the [`build.plugins.plugin.configuration.goalPrefix`](https://maven.apache.org/plugin-tools/maven-plugin-plugin/examples/generate-descriptor.html) element in the generated `pom.xml` file.
`mavenDebug` | `boolean` | `false` | Whether to invoke the Maven Embedder in debug mode.
<a name="mavenembedderclasspath"></a>`mavenEmbedderClasspath` | `FileCollection` | `null` | The classpath used to invoke the Maven Embedder.
`mavenEmbedderMainClassName` | `String` | `"org.apache.maven.cli.MavenCli"` | The Maven Embedder's main class name.
`mavenPluginPluginVersion` | `String` | `"3.4"` | The version of the [Maven Plugin Plugin](https://maven.apache.org/plugin-tools/maven-plugin-plugin/) to use to generate the plugin descriptor for the project.
<a name="mavensettingsfile"></a>`mavenSettingsFile` | `File` | `null` | The custom `settings.xml` file to use. It sets the `--settings` argument on the Maven Embedder invocation.
<a name="outputdir"></a>`outputDir` | `File` | `null` | The directory where the Maven plugin descriptor files are saved.
<a name="pomartifactid"></a>`pomArtifactId` | `String` | `null` | The identifier for the artifact that is unique within the group. It sets the value of the [`project.artifactId`](http://maven.apache.org/ref/3.3.3/maven-model/maven.html#class_project) element in the generated `pom.xml` file.
<a name="pomgroupid"></a>`pomGroupId` | `String` | `null` | The universally unique identifier for the project. It sets the value of the [`project.groupId`](http://maven.apache.org/ref/3.3.3/maven-model/maven.html#class_project) element in the generated `pom.xml` file.
`pomRepositories` | `Map<String, Object>` | `["liferay-public": "http://repository.liferay.com/nexus/content/groups/public"]` | The name and URL of the remote repositories. It adds [`repositories.repository`](http://maven.apache.org/ref/3.3.3/maven-model/maven.html#class_repository) elements in the generated `pom.xml` file.
<a name="pomversion"></a>`pomVersion` | `String` | `null` | The version of the artifact produced by this project. It sets the value of the [`project.version`](http://maven.apache.org/ref/3.3.3/maven-model/maven.html#class_project) element in the generated `pom.xml` file.
<a name="sourcedir"></a>`sourceDir` | `String` | `null` | The directory that contains the source files.  It sets the value of the [`build.sourceDirectory`](http://maven.apache.org/ref/3.3.9/maven-model/maven.html#class_build) element in the generated `pom.xml` file.
`useSetterComments` | `boolean` | `true` | Whether to allow [Mojo Javadoc Tags](https://maven.apache.org/plugin-tools/maven-plugin-tools-java/) in the setter methods of the Mojo.

The properties of type `File` support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.lang.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties, to defer evaluation until task execution.

### Task Methods [](id=task-methods)

Method | Description
------ | -----------
`void configurationScopeMapping(String configurationName, String scope)` | Adds a mapping between a configuration name in the Gradle project and the dependency scope in the `pom.xml` file.
`BuildPluginDescriptorTask forcedExclusions(Iterable<String> forcedExclusions)` | Adds *group:name:version* notations of dependencies to always exclude from the ones added in the `pom.xml` file.
`BuildPluginDescriptorTask forcedExclusions(String... forcedExclusions)` | Adds *group:name:version* notations of dependencies to always exclude from the ones added in the `pom.xml` file.
`BuildPluginDescriptorTask pomRepositories(Map<String, ?> pomRepositories` | Adds names and URLs of remote repositories in the `pom.xml` file.
`BuildPluginDescriptorTask pomRepository(String id, Object url)` | Adds the name and URL of a remote repository in the `pom.xml` file.

### WriteMavenSettingsTask [](id=writemavensettingstask)

#### Task Properties [](id=task-properties-0)

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
<a name="localrepositorydir"></a>`localRepositoryDir` | `String` | `null` | The directory of the system's local repository. It sets the value of the [`localRepository`](https://maven.apache.org/settings.html#Simple_Values) element in the generated `settings.xml` file.
<a name="nonproxyhosts"></a>`nonProxyHosts` | `String` | `null` | The patterns of the host that should be accessed without going through the proxy. It sets the value of the [`proxies.proxy.nonProxyHosts`](https://maven.apache.org/settings.html#Proxies) element in the generated `settings.xml` file.
<a name="outputfile"></a>`outputFile` | `File` | `null` | The generated `settings.xml` file.
<a name="proxyhost"></a>`proxyHost` | `String` | `null` | The host name or address of the proxy server. It sets the value of the [`proxies.proxy.host`](https://maven.apache.org/settings.html#Proxies) element in the generated `settings.xml` file.
<a name="proxypassword"></a>`proxyPassword` | `String` | `null` | The password to use to access a protected proxy server. It sets the value of the [`proxies.proxy.password`](https://maven.apache.org/settings.html#Proxies) element in the generated `settings.xml` file.
<a name="proxyport"></a>`proxyPort` | `String` | `null` | The port number of the proxy server. It sets the value of the [`proxies.proxy.port`](https://maven.apache.org/settings.html#Proxies) element in the generated `settings.xml` file.
<a name="proxyuser"></a>`proxyUser` | `String` | `null` | The user name to use to access a protected proxy server. It sets the value of the [`proxies.proxy.username`](https://maven.apache.org/settings.html#Proxies) element in the generated `settings.xml` file.
<a name="repositoryurl"></a>`repositoryUrl` | `String` | `null` | The URL of the repository [mirror](https://maven.apache.org/guides/mini/guide-mirror-settings.html#Using_A_Single_Repository). It sets the value of the [`mirrors.mirror.url`](https://maven.apache.org/settings.html#Mirrors) element in the generated `settings.xml` file.

The properties of type `File` support any type that can be resolved by
[`project.file`](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file\(java.lang.Object\)).
Moreover, it is possible to use Closures and Callables as values for the
`String` properties, to defer evaluation until task execution.

## Additional Configuration [](id=additional-configuration)

There are additional configurations that can help you use the Maven Plugin
Builder.

### Maven Embedder Dependency [](id=maven-embedder-dependency)

By default, the plugin creates a configuration called `mavenEmbedder` and adds a
dependency to the 3.3.9 version of the Maven Embedder. It is possible to
override this setting and use a specific version of the tool by manually adding
a dependency to the `mavenEmbedder` configuration:

```gradle
dependencies {
    mavenEmbedder group: "org.apache.maven", name: "maven-embedder", version: "3.3.9"
    mavenEmbedder group: "org.apache.maven.wagon", name: "wagon-http", version: "2.10"
    mavenEmbedder group: "org.eclipse.aether", name: "aether-connector-basic", version: "1.0.2.v20150114"
    mavenEmbedder group: "org.eclipse.aether", name: "aether-transport-wagon", version: "1.0.2.v20150114"
    mavenEmbedder group: "org.slf4j", name: "slf4j-simple", version: "1.7.5"
}
```

### System Properties [](id=system-properties)

It is possible to set the default value of the `mavenDebug` property for a
`BuildPluginDescriptorTask` task via system property:

- `-D${task.name}.maven.debug=true`

For example, run the following Bash command to invoke the Maven Embedder in
debug mode to attach a remote debugger:

```bash
./gradlew buildPluginDescriptor -DbuildPluginDescriptor.maven.debug=true
```
