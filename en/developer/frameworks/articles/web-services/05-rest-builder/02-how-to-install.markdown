---
header-id: how-to-install
---

# How to install REST Builder

[TOC levels=1-4]

Use the [Gradle plugin](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/rest-builder-gradle-plugin) 
to install REST builder by adding this gradle configuration to your project: 

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

To use it, run `gradlew buildREST`. Note that your Gradle wrapper may not be in
your app’s project directory, so you may need to use [Blade](/docs/7-2/reference/-/knowledge_base/r/blade-cli) 
to locate it: 

```bash
blade gw buildREST
```

If you want to use a specific version of REST builder, you can specify it explicitly:

```groovy
dependencies {
    restBuilder group: "com.liferay", name: "com.liferay.portal.tools.rest.builder", version: "1.0.30"
}
```
