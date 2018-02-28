# Validating Modules Against the Target Platform

Once you've finished developing your custom module in Liferay Workspace, you're
ready to deploy it and see how it looks. Right before deploying your app for the
first time, you may feel a sense of nervousness. Will my app deploy
successfully? Will it appear on the screen? Will there be some sort of missing
requirement? If so, how will I diagnose the issue? These are all common worries
that can be frustrating.

Instead of deploying your app and waiting for @product@ to spit errors back at
you, you can validate your app before deployment. This can be done by calling
the `resolve` Gradle task provided by Liferay Workspace.

    gradlew resolve

This task gathers all the @product@ capabilities and the capabilities of your
workspace's modules and computes a list of run requirements that need to execute
for your project to work. Then it validates the current project against those
gathered capabilities. If your project requires something not gathered by the
task, the task fails.

+$$$

**Note:** The `resolve` task can be executed from a specific project folder or
from the workspace's root folder. Running the task from the root folder
validates all the modules in your workspace.

$$$

Some capabilities/information gathered by the `resolve` task include

- package imports
- service references
- module versions
- constraints
- etc.

<!-- TODO: Ask Greg for more capabilities that are gathered. -Cody -->

The `resolve` task can also report issues with separate modules requiring
different versions of another module. For example, you may have *module A*
requiring *module Test version 1* and *module B* requiring *module Test version
4*. Without running the resolver, both modules A and B would compile
successfully, but when they were deployed, one would fail in @product@'s OSGi
runtime because both dependencies cannot be satisfied. These types of scenarios
are difficult to diagnose, but with the `resolve` task, can be found with ease.

There are many different versions of @product@ that you can validate against, so
you'll need to let workspace know which version you want to gather capabilities
from. To do this, open your workspace's `gradle.properties` file and set the
`liferay.workspace.target.platform.version` property to the version you want to
target. For example,

    liferay.workspace.target.platform.version=7.1.0-m1

<!-- TODO: Update above version to accurate milestone version syntax. This was a
shot in the dark. -Cody -->

This provides a static *distro* JAR for the specified version of @product@,
which contains all the metadata (i.e., packages, capabilities, constraints,
etc.) running inside @product@. The distro JAR is a complete snapshot of
everything provided in @product@'s OSGi runtime.



## Modifying the Target Platform's Capabilities

In a perfect world, everything the `resolve` task gathers and checks against
would work during your development process. Unfortunately, there are exceptions
that may force you to modify the default functionality of the `resolve` task.
There are two scenarios you may run into during development that would require
a modification for your project to pass the resolver check.

### Depending on a Project Not Included in @product@

The `resolve` task, by default, gathers all the capabilities provided by the
specified version of @product@ and included in your current workspace
environment. What if, however, your module depends on a third party project that
is not included in either space (e.g.,
[Google Guava](https://opensource.google.com/projects/guava))?. The `resolve`
task would fail by default if your project depended on this project type. You
probably plan to have this project deployed and available in @product@ at
runtime, so it's not a concern, but the resolver doesn't know that; you'll need
to customize the resolver to bypass this.

There are three ways you can do this:

- [Embed the third party project in your module](#embed-the-third-party-project-in-your-module)
- [Skip the resolving process for your module](#skip-the-resolving-process-for-your-module)
- [Add the third party project to the current static set of resolver capabilities](#add-the-third-party-project-to-the-current-static-set-of-resolver-capabilities)

#### Embed the Third Party Project in Your Module

If you only have one module that depends on the third party project, you can
bypass the resolver failure by embedding the JAR in your module. This is not
best practice if more than one project in your @product@ instance depends on
that module. See the
[Embedding Libraries in a Module](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module#embedding-libraries-in-a-module)
section for more details.

#### Skip the Resolving Process for Your Module

It may be easiest to skip your module during the resolving process. To do this,
open your workspace's root `build.gradle` file and insert the following Gradle
code at the bottom of the file:

    targetPlatform {
        resolveOnlyIf { project ->
            project.name != 'MODULE_NAME'
        }
    }

Be sure to replace the `PROJECT_NAME` filler with your actual module name.

Now the `resolve` task skips the configured project.

#### Add the Third Party Project to the Current Static Set of Resolver Capabilities

providedModules




### Depending on a Platform That Must Be Installed in @product@

There are times when manually skipping over a project does not suffice. For
example, if you're creating a Liferay Audience Targeting rule that depends on
the Audience Targeting platform, you can't easily provide a slew of JARs for
your module. In this case, you should regenerate a new distro JAR with an
updated list of capabilities that your @product@ instance will provide. Follow
the steps below to accomplish this:

<!-- The below process will likely be automated in Blade at some point. -Cody -->

1.  Start the @product@ instance stored in your workspace.

2.  Download the
    [BND Remote Agent JAR file](https://search.maven.org/#search%7Cga%7C1%7Cbiz.aqute.remote.agent)
    and copy it into your @product@ instance's `osgi/modules` folder.

3.  From the root folder of you workspace, run the following command:

        bnd remote distro -o custom_distro.jar com.liferay.ce.portal.distro 7.1.0

    This connects to the newly deployed BND agent running in @product@ and
    generates a new distro JAR named `custom_distro.jar`. All other capabilities
    will be overridden based on the @product@ instance you're generating the
    custom distro against too, so verify the workspace bundle is the version you
    plan to release for in production.

4.  Navigate to your workspace's root `build.gradle` file and add the following
    dependency:

        dependencies {
            targetPlatformDistro files('custom_distro.jar')
        }

Now your workspace is pointing to a custom distro JAR file instead of the
default one provided. Run the `resolve` task to validate your custom modules
against the custom set of capabilities.

## Validating Modules Outside of Workspace

44:15 -"build/resolve.bndrun" is resolver input file (check what resolver is doing behind the scenes)







targetPlatformDistro group: "com.liferay", name "com.liferay.ce.portal.distro", version: "7.0.5-SNAPSHOT"








