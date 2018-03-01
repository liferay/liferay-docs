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

This task gathers all the capabilities provided by
    
- the specified version of @product@
- the current workspace's modules

It also computes a list of run requirements for your project. Then it compares
the current project's requirements against the gathered capabilities. If your
project requires something not available in the gathered list of capabilities,
the task fails.

The task can only validate OSGi modules. It does not work with WAR-style
projects, themes, npm portlets, etc.

+$$$

**Note:** The `resolve` task can be executed from a specific project folder or
from the workspace's root folder. Running the task from the root folder
validates all the modules in your workspace.

$$$

The `resolve` task can automatically gather the available capabilities from your
workspace, but it cannot do this for your targeted @product@ version. You must
specify the version you intend to deploy to. You'll learn how to do this later.

Some capabilities/information gathered by the `resolve` task include

- declared required capabilities
- module versions
- package imports/use constraints
- service references
- etc.

The `resolve` task can also report issues with separate modules requiring
different versions of another module. For example, suppose you have *module A*
requiring *module Test version 1* and *module B* requiring *module Test version
4*. Without running the resolver, both modules A and B would compile
successfully, but when they were deployed, one would fail in @product@'s OSGi
runtime because both dependencies cannot be satisfied. These types of scenarios
are difficult to diagnose, but with the `resolve` task, can be found with ease.

Next, you'll learn how to set the @product@ version to gather capabilities from.

## Finding Capabilities for Your Targeted Platform

There are many different versions of @product@ that you can validate against, so
you'll need to let workspace know which version you want to gather capabilities
for. To do this, open your workspace's `gradle.properties` file and set the
`liferay.workspace.target.platform.version` property to the version you want to
target. For example,

    liferay.workspace.target.platform.version=7.1.0-m1

<!-- TODO: Update above version to accurate milestone version syntax. This was a
shot in the dark. -Cody -->

This provides a static *distro* JAR for the specified version of @product@,
which contains all the metadata (i.e., capabilities, packages, versions, etc.)
running inside @product@. The distro JAR is a complete snapshot of everything
provided in @product@'s OSGi runtime; this serves as the list of capabilities
described earlier.

You can now validate your module projects before deploying them to @product@!
Next, you'll learn about scenarios that require modifying the `resolve` task's
default behavior.

## Modifying the Target Platform's Capabilities

In a perfect world, everything the `resolve` task gathers and checks against
would work during your development process. Unfortunately, there are exceptions
that may force you to modify the default functionality of the `resolve` task.
There are two scenarios you may run into during development that would require
a modification for your project to pass the resolver check.

- You're depending on a third party library that is not available in the
  targeted @product@ instance or the current workspace.
- You're depending on a platform that must be installed in @product@.

You'll explore these use cases next.

### Depending on Third Party Libraries Not Included in @product@

The `resolve` task, by default, gathers all of @product@'s capabilities and the
capabilities of your workspace's modules. What if, however, your module depends
on a third party project that is not included in either space (e.g.,
[Google Guava](https://opensource.google.com/projects/guava))?. The `resolve`
task fails by default if your project depends on this project type. You
probably plan to have this project deployed and available in @product@ at
runtime, so it's not a concern, but the resolver doesn't know that; you'll need
to customize the resolver to bypass this.

There are three ways you can do this:

- [Embed the third party library in your module](#embed-the-third-party-project-in-your-module)
- [Add the third party library's capabilities to the current static set of resolver capabilities](#add-the-third-party-librarys-capabilities-to-the-current-static-set-of-resolver-capabilities)
- [Skip the resolving process for your module](#skip-the-resolving-process-for-your-module)

For help resolving third party dependency errors, see the
[Resolving Third Party Library Package Dependencies](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module)
tutorial.

#### Embed the Third Party Library in Your Module

If you only have one module that depends on the third party project, you can
bypass the resolver failure by embedding the JAR in your module. This is not
best practice if more than one project in your @product@ instance depends on
that module. See the
[Embedding Libraries in a Module](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module#embedding-libraries-in-a-module)
section for more details.

#### Add the Third Party Library's Capabilities to the Current Static Set of Resolver Capabilities

You can add your third party dependencies to the distro JAR's list of
capabilities by listing them as provided modules. Do this by adding the
following Gradle code into your workspace's root `build.gradle` file:

    dependencies {
        providedModules group: "GROUP_ID", name: "NAME", version: "VERSION"
    }

For example, if you wanted to provide
[Google Guava](https://opensource.google.com/projects/guava) as a provided
module, it would look like this:

    dependencies {
        providedModules group: "com.google.guava", name: "guava", version: "23.0"
    }

Not only does this provide the third party dependency to the resolver, it also
downloads and includes it in your @product@ bundle's `osgi/modules` folder when
you initialize it (e.g., `gradlew initBundle`).

#### Skip the Resolving Process for Your Module

It may be easiest to skip validating your module during the resolve process. To
do this, open your workspace's root `build.gradle` file and insert the following
Gradle code at the bottom of the file:

    targetPlatform {
        resolveOnlyIf { project ->
            project.name != 'PROJECT_NAME'
        }
    }

Be sure to replace the `PROJECT_NAME` filler with your module's name (e.g.,
`test-api`).

Now the `resolve` task skips your module project.

### Depending on a Platform That Must Be Installed in @product@

There are times when manually specifying your project's list of dependent JARs
does not suffice. For example, if you're creating a Liferay Audience Targeting
rule that depends on the Audience Targeting platform, you can't easily provide a
slew of JARs for your module. In this case, you should install the platform you
wish to depend on and regenerate a new distro JAR with an updated list of
capabilities that your @product@ instance will provide. Follow the steps below
to accomplish this:

<!-- The below process will likely be automated in Blade at some point. -Cody -->

1.  Start the @product@ instance stored in your workspace. Make sure the
    platform you want to depend on is installed.

2.  Download the
    [BND Remote Agent JAR file](https://search.maven.org/#search%7Cga%7C1%7Cbiz.aqute.remote.agent)
    and copy it into your @product@ instance's `osgi/modules` folder.

3.  From the root folder of you workspace, run the following command:

        bnd remote distro -o custom_distro.jar com.liferay.ce.portal.distro 7.1.0-m1

    <!-- TODO: Update milestone version with correct syntax, when available. -Cody -->

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

<!-- TODO: Remove this section once the reference doc for the Gradle plugin is
available. This is just gives a taste of targeting a platform for those who
don't want to use Workspace. More details will be provided in reference doc.
-Cody -->

If you prefer to not use Liferay Workspace, but still want to validate modules
against a target platform, you must apply the
[Target Platform](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-target-platform)
Gradle plugin to the root `build.gradle` file of your custom multi-module Gradle
build. Follow the
[Targeting a Platform Outside of Workspace](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace#targeting-a-platform-outside-of-workspace)
section to do this.

Once you have the Target Platform plugin and its BOM dependencies configured,
you must configure the `targetPlatformDistro` dependency. Open your project's
root `build.gradle` file and add it to the list of dependencies. It should look
like this:

    dependencies {
        targetPlatformBoms group: "com.liferay", name: "com.liferay.ce.portal.bom", version: "7.1.0-m1"
        targetPlatformBoms group: "com.liferay", name: "com.liferay.ce.portal.compile.only", version: "7.1.0-m1"
        targetPlatformDistro group: "com.liferay", name "com.liferay.ce.portal.distro", version: "7.1.0-m1"
    }

<!-- TODO: Update above versions to accurate milestone version syntax. This was
a shot in the dark. -Cody -->

Now you can validate your modules against a target platform!
