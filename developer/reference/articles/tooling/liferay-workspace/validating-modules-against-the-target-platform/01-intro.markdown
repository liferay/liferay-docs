# Validating Modules Against the Target Platform

After you write a module in Liferay Workspace, you can validate it before
deployment to make sure of several things: 

- Will my app deploy successfully? 
- Will there be some sort of missing requirement? 
- If there's an issue, how do I diagnose it? 

These are all common worries that can be frustrating.

Instead of deploying your app and checking for errors in the log, you can
validate your app before deployment. This is done by calling Liferay Workspace's
`resolve` task, which validates your modules against a targeted platform.

You'll cover the following topics in this section:

- [Resolving your modules](#resolving-your-modules)
- [Modifying the target platform's capabilities](#modifying-the-target-platforms-capabilities)
- [Including the resolver in your Gradle build](#including-the-resolver-in-your-gradle-build)

Continue on to learn how this works.

## Resolving Your Modules

You can resolve your modules before deployment. This can be done by calling the
`resolve` Gradle task provided by Liferay Workspace.

    ```bash
    ./gradlew resolve
    ```

This task gathers all the capabilities provided by
 
- the specified version of @product@ (i.e.,
  [targeted platform](/docs/7-2/reference/-/knowledge_base/reference/managing-the-target-platform-for-liferay-workspace))
- the current workspace's modules

Some capabilities/information gathered by the `resolve` task that are validated
include

- declared required capabilities
- module versions
- package imports/use constraints
- service references

It also computes a list of run requirements for your project. Then it compares
the current project's requirements against the gathered capabilities. If your
project requires something not available in the gathered list of capabilities,
the task fails.

The task can only validate OSGi modules. It does not work with WAR-style
projects, themes, or npm portlets.

| **Note:** The `resolve` task can be executed from a specific project folder or
| from the workspace's root folder. Running the task from the root folder
| validates all the modules in your workspace.

The `resolve` task can automatically gather the available capabilities from your
workspace, but you must specify this for your targeted @product@ version. To do
this, open your workspace's `gradle.properties` file and set the
`liferay.workspace.target.platform.version` property to the version you want to
target. For example,

    ```properties
    liferay.workspace.target.platform.version=7.2.0
    ```

If you're using Liferay DXP, you can set the property like this:

    ```properties
    liferay.workspace.target.platform.version=7.2.10
    ```

The versions following a GA1 release of DXP follow fix pack versions (e.g.,
`7.1.10.fp1`, `7.1.10.fp2`, etc.).

Setting the target platform property provides a static *distro* JAR for the
specified version of @product@, which contains all the metadata (i.e.,
capabilities, packages, versions, etc.) running in that version. The distro JAR
is a complete snapshot of everything provided in the OSGi runtime; this serves
as the target platform's list of capabilities that your modules are validated
against.

You can now validate your module projects before deploying them! If the resolver
throws errors, see the article on
[how to resolve common output errors reported by the `resolve` task](/docs/reference/7-2/-/knowledge_base/reference/how-to-resolve-common-output-errors-reported-by-the-resolve-task).
Sometimes, you must modify the `resolve` task's default behavior to successfully
validate your app. See the next section for more information.

## Modifying the Target Platform's Capabilities

In a perfect world, everything the `resolve` task gathers and checks against
would work during your development process. Unfortunately, there are exceptions
that may force you to modify the default functionality of the `resolve` task.

There are two scenarios you may run into during development that require a
modification for your project to pass the resolver check.

- You're depending on a third party library that is not available in the
  targeted @product@ instance or the current workspace.
- You're depending on a customized distribution of @product@.

You'll explore these use cases next.

### Depending on Third Party Libraries Not Included in @product@

The `resolve` task, by default, gathers all of @product@'s capabilities and the
capabilities of your workspace's modules. What if, however, your module depends
on a third party project that is not included in either space (e.g.,
[Google Guava](https://opensource.google.com/projects/guava))?. The `resolve`
task fails by default if your project depends on this project type. You
probably plan to have this project deployed and available at runtime, so it's
not a concern, but the resolver doesn't know that; you must customize the
resolver to bypass this.

There are three ways you can do this:

- [Embed the third party library in your module](/docs/reference/7-2/-/knowledge_base/reference/adding-third-party-libraries-to-a-module#embedding-libraries-in-a-module)
- [Add the third party library's capabilities to the current static set of resolver capabilities](/docs/reference/7-2/-/knowledge_base/reference/adding-a-third-party-librarys-capabilities-to-the-resolvers-capabilities)
- [Skip the resolving process for your module](/docs/reference/7-2/-/knowledge_base/reference/skipping-the-resolving-process-for-your-module)

| **Note:** You should only embed a third party library in your module if it's
| the only module that depends on it. You should not bypass the resolver failure
| this way if more than one project in the OSGi container depends on that
| library.

For help resolving third party dependency errors, see the
[Resolving Third Party Library Package Dependencies](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module)
tutorial.

### Depending on a Customized Distribution of @product@

There are times when manually specifying your project's list of dependent JARs
does not suffice. If your app requires a customized @product@ instance to
run, you must regenerate the target platform's default list of capabilities with
an updated list. Two examples of a customized @product@ instance are described
below:

**Example 1: Leveraging an External Feature**

There are many external features/frameworks available that are not included in
the downloadable bundle by default. After deploying a feature/framework, it's
available for your module projects to leverage. When validating your app,
however, the `resolve` task does not have access to external capabilities not
included by default. For example, Audience Targeting is an example of this type
of external framework. If you're creating a Liferay Audience Targeting rule that
depends on the Audience Targeting framework, you can't easily provide a slew of
JARs for your module. In this case, you should install the platform your code
depends on and regenerate an updated list of capabilities that your @product@
instance provides.

**Example 2: Leveraging a Customized Core Feature**

You can extend @product@'s core features to provide a customized experience for
your intended audience. Once deployed, you can assume these customizations are
present and build other things on top of them. The new capabilities resulting
from your customizations are not available, however, in the target platform's
default list of capabilities. Therefore, when your application relies on
non-default capabilities, it fails during the `resolve` task. To get around
this, you must regenerate a new list of capabilities that your customized
@product@ instance provides.

To regenerate the target platform's capabilities (distro JAR) based on the
current workspace's @product@ instance, follow the
[Depending on a Customized Distribution of @product@](/docs/7-2/reference/-/knowledge_base/reference/depending-on-a-customized-distribution-of-product)
article.

## Including the Resolver in Your Gradle Build

By default, Liferay Workspace provides the `resolve` task as an independent
executable. It's provided by the
[Target Platform](/docs/7-2/reference/-/knowledge_base/reference/target-platform-gradle-plugin)
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

You can learn how to include the resolver in your Gradle build by visiting
[this article](/docs/7-2/reference/-/knowledge_base/reference/including-the-resolver-in-your-gradle-build).

Continue on for various step-by-step instructions for configuring/manipulating
the resolver task.