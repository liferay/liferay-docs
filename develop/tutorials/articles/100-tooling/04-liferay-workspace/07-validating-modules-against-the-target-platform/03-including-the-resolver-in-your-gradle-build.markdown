# Including the Resolver in Your Gradle Build [](id=including-the-resolver-in-your-gradle-build)

By default, Liferay Workspace provides the `resolve` task as an independent
executable. It's provided by the
[Target Platform](/develop/reference/-/knowledge_base/7-1/target-platform-gradle-plugin)
Gradle plugin and is not integrated in any other Gradle processes. This gives
you control over your Gradle build without imposing strategies you may not want
included in your default build process.

With that said, the `resolve` task can be useful to include in your build
process if you want to check for errors in your module projects before
deployment. Instead of resolving your projects separately from your standard
build, you can build and resolve them all in one shot.

In Liferay Workspace, the recommended path for doing this is adding it to the
default `check` Gradle task. The `check` task is provided by default in a
workspace by the
[Java](https://docs.gradle.org/current/userguide/java_plugin.html#_lifecycle_tasks)
plugin. Adding the `resolve` task to the `check` lifecycle task also promotes
the `resolve` task to run for CI and other test tools that typically run the
`check` task for verification. Of course, Gradle's `build` task also depends on
the `check` task, so you can run `gradlew build` and run the resolver too.

To call the `resolve` task during the `check` task automatically, open your
workspace's root `build.gradle` file and add the following directive:

    check.dependsOn resolve

You can also configure this for specific projects in a workspace if you don't
want all modules to be included in the global `check`.

If the `resolve` task runs during every Gradle build, you may want to prevent
the build from failing if there are errors reported by the resolver. To do this,
open your workspace's root `build.gradle` file and add the following code:

    targetPlatform {
        ignoreResolveFailures = true
    }

This reports the failures without failing the build. Note, this can only be
configured in the workspace's root `build.gradle` file.

Awesome! You can now run the `resolve` task in your current Gradle lifecycle.
