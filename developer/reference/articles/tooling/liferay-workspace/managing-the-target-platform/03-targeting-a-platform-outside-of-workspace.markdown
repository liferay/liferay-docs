# Targeting a Platform Outside of Workspace

If you prefer to not use Liferay Workspace, but still want to target a platform,
you must apply the 
[Target Platform Gradle plugin](/developer/reference/-/knowledge_base/7-2/target-platform-gradle-plugin)
to the root `build.gradle` file of your custom multi-module Gradle build.

To do this, follow the steps below.

1.  Open your project's `build.gradle` file and add this:

    ```groovy
    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.target.platform", version: "1.1.6"
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
        targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom", version: "7.1.0"
        targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom.compile.only", version: "7.1.0"
    }
    ```

    These dependencies are described below:

    - `com.liferay.ce.portal.bom`: provides all the artifacts included in
      @product@.
    - `com.liferay.ce.portal.compile.only`: provides artifacts that are not
      included in @product@, but are necessary to reference during the build
      (e.g., `org.osgi.core`).

    Liferay DXP users must replace the artifact names and versions:

    - `release.portal.bom` &rarr; `release.dxp.bom`
    - `release.portal.bom.compile.only` &rarr; `release.dxp.bom.compile.only`
    - `7.1.0` &rarr; `7.1.10`

4.  If you're interested in
[advanced search](/developer/reference/-/knowledge_base/7-2/searching-product-source-in-dev-studio)
and/or
[debugging](/developer/reference/-/knowledge_base/7-2/debugging-product-source-in-dev-studio)
@product@'s source using
[Liferay Dev Studio](/developer/reference/-/knowledge_base/7-2/liferay-dev-studio),
you must also apply the following configuration:

    ```groovy
    targetPlatformIDE {
        includeGroups "com.liferay", "com.liferay.portal"
    }
    ```

    This indexes the target platform's source code and makes it available to
    Dev Studio.

Now you can define your target platform!