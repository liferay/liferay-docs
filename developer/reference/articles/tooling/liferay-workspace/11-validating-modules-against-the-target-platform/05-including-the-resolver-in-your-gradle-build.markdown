---
header-id: including-the-resolver-in-your-gradle-build
---

# Including the Resolver in Your Gradle Build

[TOC levels=1-4]

To call the `resolve` task during Gradle's `check` task automatically, follow
the instructions below:

1.  Open your workspace's root `build.gradle` file.

2.  Add the following directive:

    ```groovy
    check.dependsOn resolve
    ```

    The `resolve` task is now called during the `check` task.

    You can also configure this for specific projects in a workspace if you
    don't want all modules to be included in the global `check`.

3.  (Optional) If the `resolve` task runs during every Gradle build, you may
    want to prevent the build from failing if there are errors reported by the
    resolver. To do this, open your workspace's root `build.gradle` file and add
    the following code:

    ```groovy
    targetPlatform {
        ignoreResolveFailures = true
    }
    ```

    This reports the failures without failing the build. Note, this can only be
    configured in the workspace's root `build.gradle` file.

Awesome! You can now run the `resolve` task in your current Gradle lifecycle.
