---
header-id: validating-modules-outside-of-workspace
---

# Validating Modules Outside of Workspace

[TOC levels=1-4]

If you prefer to not use Liferay Workspace, but still want to validate modules
against a target platform, you must apply the
[Target Platform Gradle plugin](/docs/7-2/reference/-/knowledge_base/r/target-platform-gradle-plugin)
to the root `build.gradle` file of your multi-module Gradle build. Follow the
[Targeting a Platform Outside of Workspace](/docs/7-2/reference/-/knowledge_base/r/targeting-a-platform-outside-of-workspace)
section to do this.

Once you have the Target Platform plugin and its BOM dependencies configured,
you must configure the `targetPlatformDistro` dependency. Follow the
instructions below to do this.

1.  Open your project's root `build.gradle` file.

2.  Add the `targetPlatformDistro` dependency to the list of dependencies. It
    should look like this:

    ```groovy
    dependencies {
        targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom", version: "7.2.0"
        targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom.compile.only", version: "7.2.0"
        targetPlatformDistro group: "com.liferay.portal", name "release.portal.distro", version: "7.2.0"
    }
    ```

    Liferay DXP users must replace the artifact names and versions:
    
    - `release.portal.bom` &rarr; `release.dxp.bom`
    - `release.portal.bom.compile.only` &rarr; `release.dxp.bom.compile.only`
    - `release.portal.distro` &rarr; `release.dxp.distro`
    - `7.2.0` &rarr; `7.2.10`

Now you can validate your non-workspace modules against a target platform!
