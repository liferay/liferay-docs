# App Docker Gradle Plugin

The App Docker Gradle plugin lets you build a single [Docker](https://www.docker.com/)
image for an application that spans different subprojects, each one representing
a different component of the same application. The Docker image can then be
pushed to a registry.

The plugin has been successfully tested with Gradle 2.5 up to 3.3.

## Usage

To use the plugin, include it in the build script of the root project:

```gradle
buildscript {
	dependencies {
		classpath group: "com.liferay", name: "com.liferay.gradle.plugins.app.docker", version: "1.0.0"
	}

	repositories {
		maven {
			url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
		}
	}
}

apply plugin: "com.liferay.app.docker"
```

The App Docker plugin automatically applies the
[`com.bmuschko.docker-remote-api`](https://github.com/bmuschko/gradle-docker-plugin)
plugin.

Since the plugin automatically resolves the [Java Docker API Client](https://github.com/docker-java/docker-java)
library as a dependency, you must configure a repository that hosts the library
and its transitive dependencies. The Liferay CDN repository hosts them all:

```gradle
repositories {
	maven {
		url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
	}
}
```

## Project Extension

The App Docker plugin exposes the following properties through the
extension named `appDocker`:

Property Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`imageName` | `String` | `project.name` | The name of the app's Docker image (e.g., `foo` in the image named `liferay/foo`).
`imageTags` | `List<String>` | `[`The latest Git commit's committer date`, ` The latest Git commit's hash`]` | The list of tags for the app's Docker image to push to the registry.
`imageUser` | `String` | `project.group` | The user repository of the app's Docker image (e.g., `liferay` in the image named `liferay/foo`).
`inputDir` | `File` | `"${project.projectDir}/${project.name}-docker"` | The directory that contains the `Dockerfile` and other resources to copy into the context path used to build the app's Docker image.
`subprojects` | `Set<Project>` | `project.subprojects` | The subprojects to include in the app's Docker image.

The same extension exposes the following methods:

Method | Description
------ | -----------
`AppDockerExtension imageTags(Iterable<?> imageTags)` | Adds tags for the app's Docker image to push to the registry.
`AppDockerExtension imageTags(Object... imageTags)` | Adds tags for the app's Docker image to push to the registry.
`AppDockerExtension onlyIf(Closure<Boolean> onlyIfClosure)` | Includes a subproject in the Docker image if the given closure returns `true`. The closure is evaluated at the end of the subproject configuration phase and is passed the subproject as a single parameter. If the closure returns `false`, the subproject is not included in the Docker image.
`AppDockerExtension onlyIf(Spec<Project> onlyIfSpec)` | Includes a subproject in the Docker image if the given spec is satisfied. The spec is evaluated at the end of the subproject configuration phase. If the spec is not satisfied, the subproject is not included in the Docker image.
`AppDockerExtension subprojects(Iterable<Project> subprojects)` | Includes additional projects in the app's Docker image.
`AppDockerExtension subprojects(Project... subprojects)` | Includes additional projects in the app's Docker image.

## Tasks

The plugin adds a series of tasks to your project:

Name | Depends On | Type | Description
---- | ---------- | ---- | -----------
[`buildAppDockerImage`](#task-buildappdockerimage) | `prepareAppDockerImageInputDir` | [`DockerBuildImage`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerBuildImage.html) | Builds the app's Docker image.
[`prepareAppDockerImageInputDir`](#task-prepareappdockerimageinputdir) | \- | [`Sync`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Sync.html) | Copies the subproject artifacts and other resources to a temporary directory that will be used to build the app's Docker image.
[`pushAppDockerImage`](#task-pushappdockerimage) | `[buildAppDockerImage, pushAppDockerImage_tag1, pushAppDockerImage_tag2, `...`]` | [`DockerPushImage`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerPushImage.html) | Pushes the app's Docker image to the registry.
[`pushAppDockerImage_${tag}`](#tasks-pushappdockerimage_tag) | `tagAppDockerImage_${tag}` | [`DockerPushImage`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerPushImage.html) | Pushes the Docker image `${tag}` to the registry.
[`tagAppDockerImage_${tag}`](#tasks-tagappdockerimage_tag) | `buildAppDockerImage` | [`DockerTagImage`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerTagImage.html) | Creates the tag `${tag}`, which refers to the app's Docker image.

### Task `buildAppDockerImage`

The `buildAppDockerImage` task is automatically configured with sensible
defaults:

Property Name | Default Value
------------- | -------------
[`inputDir`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerBuildImage.html#inputDir) | `project.tasks.prepareAppDockerImageInputDir.destinationDir`
[`tag`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerBuildImage.html#tag) | `"${appDocker.imageUser}/${appDocker.imageName}"`, or `"${appDocker.imageName}"` if `appDocker.imageUser` is not set.

### Task `prepareAppDockerImageInputDir`

The `prepareAppDockerImageInputDir` task is automatically configured with
sensible defaults:

Property Name | Default Value
------------- | -------------
[`destinationDir`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Sync.html#org.gradle.api.tasks.Sync:destinationDir) | `"${project.buildDir}/docker"`
[`from`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Sync.html#org.gradle.api.tasks.Sync:from%28java.lang.Object,%20groovy.lang.Closure%29) | `appDocker.inputDir`, `appDocker.subprojects*.allArtifacts.files` (only the subprojects that respect the `appDocker.onlyIf` conditions are included in the `appDocker.subprojects*.allArtifacts.files` parameter).

### Task `pushAppDockerImage`

The `pushAppDockerImage` task is automatically configured with sensible
defaults:

Property Name | Default Value
------------- | -------------
[`imageName`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerPushImage.html#imageName) | `"${appDocker.imageUser}/${appDocker.imageName}"`, or `"${appDocker.imageName}"` if `appDocker.imageUser` is not set.

### Tasks `pushAppDockerImage_${tag}`

For each `imageTag` entry in the `appDocker.imageTags` collection, one task
`pushAppDockerImage_${appDocker.imageUser}/${appDocker.imageName}:${imageTag}`
of type [DockerPushImage](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerPushImage.html)
is created at the end of the project evaluation. Each one of these tasks are
automatically configured with sensible defaults:

Property Name | Default Value
------------- | -------------
[`imageName`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerPushImage.html#imageName) | `project.tasks.tagAppDockerImage_${tag}.repository`
[`tag`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerPushImage.html#tag) | `project.tasks.tagAppDockerImage_${tag}.tag`

### Tasks `tagAppDockerImage_${tag}`

For each `imageTag` entry in the `appDocker.imageTags` collection, one task
`tagAppDockerImage_${appDocker.imageUser}/${appDocker.imageName}:${imageTag}` of
type [DockerTagImage](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerTagImage.html)
is created at the end of the project evaluation. Each one of these tasks are
automatically configured with sensible defaults:

Property Name | Default Value
------------- | -------------
[`imageId`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerExistingImage.html#imageId) | `project.tasks.buildAppDockerImage.imageId`
[`repository`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerTagImage.html#repository) | `"${appDocker.imageUser}/${appDocker.imageName}"`, or `"${appDocker.imageName}"` if `appDocker.imageUser` is not set.
[`tag`](http://bmuschko.github.io/gradle-docker-plugin/docs/groovydoc/com/bmuschko/gradle/docker/tasks/image/DockerTagImage.html#tag) | `imageTag`