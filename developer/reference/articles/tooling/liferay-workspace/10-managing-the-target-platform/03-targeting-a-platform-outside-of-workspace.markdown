---
header-id: targeting-a-platform-outside-of-workspace
---

# Targeting a Platform Outside of Workspace

[TOC levels=1-4]

If you prefer to not use Liferay Workspace, but still want to target a platform,
you must apply the 
[Target Platform Gradle plugin](/docs/7-2/reference/-/knowledge_base/r/target-platform-gradle-plugin)
to the root `build.gradle` file of your custom multi-module Gradle build.

To do this, follow the steps below.

1.  Open your project's `build.gradle` file and add this:

    ```groovy
    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.target.platform", version: "2.0.0"
        }
        repositories {
            maven {
                url "https://repository-cdn.liferay.com/nexus/content/groups/public"
            }
        }
    }
    ```

    This sets the dependency on the Target Platform Gradle plugin and configures
    the repository that provides the necessary artifacts for your project build.

2.  Apply Liferay's Target Platform Gradle plugin to the `build.xml` file:

    ```groovy
    apply plugin: "com.liferay.target.platform"
    ```

3.  Set the Target Platform plugin's dependencies:

    ```groovy
    dependencies {
        targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom", version: "7.2.0"
        targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom.compile.only", version: "7.2.0"
        targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom.third.party", version: "7.2.0"
    }
    ```

    These dependencies are described below:

    `com.liferay.ce.portal.bom`: provides all the artifacts included in
    @product@.

    `com.liferay.ce.portal.compile.only`: provides artifacts that are not
    included in @product@, but are necessary to reference during the build
    (e.g., `org.osgi.core`).

    `release.portal.bom.third.party`: provides all third party artifacts that
    make up the Liferay Portal bundle.

    Liferay DXP users must replace the artifact names and versions:

    - `release.portal.bom` &rarr; `release.dxp.bom`
    - `release.portal.bom.compile.only` &rarr; `release.dxp.bom.compile.only`
    - `release.portal.bom.third.party` &rarr; `release.dxp.bom.third.party`
    - `7.2.0` &rarr; `7.2.10`

4.  If you're interested in
[advanced search](/docs/7-2/reference/-/knowledge_base/r/searching-product-source-in-dev-studio)
and/or
[debugging](/docs/7-2/reference/-/knowledge_base/r/debugging-product-source-in-dev-studio)
@product@'s source using
[Liferay Dev Studio](/docs/7-2/reference/-/knowledge_base/r/liferay-dev-studio),
you must also apply the following configuration:

    ```groovy
    targetPlatformIDE {
        includeGroups "com.liferay", "com.liferay.portal"
    }
    ```

    This indexes the target platform's source code and makes it available to
    Dev Studio.

Now you can define your target platform!
