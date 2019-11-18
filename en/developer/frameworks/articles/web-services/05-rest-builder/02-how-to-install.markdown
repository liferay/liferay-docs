---
header-id: how-to-install
---

# How to install REST Builder

[TOC levels=1-4]

To use REST builder the recommended way is using the [Gradle plugin](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/rest-builder-gradle-plugin).

To do that we just have to add this gradle configuration to our project:

```groovy
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.rest.builder", version: "1.0.21"
    }

    repositories {
        maven {
            url "https://repository-cdn.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.portal.tools.rest.builder"
```

To use it we just have to run `gradlew buildREST`. Note that your Gradle wrapper may not be in your app’s project directory, so you may need to use .. to locate it (e.g., ../../../gradlew buildREST`).

If we want to use a specific version of REST builder we can specify it explicitly like this:

```groovy
dependencies {
    restBuilder group: "com.liferay", name: "com.liferay.portal.tools.rest.builder", version: "1.0.30"
}
```