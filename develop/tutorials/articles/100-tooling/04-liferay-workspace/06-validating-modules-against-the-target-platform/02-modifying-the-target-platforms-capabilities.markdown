# Modifying the Target Platform's Capabilities

In a perfect world, everything the `resolve` task gathers and checks against
would work during your development process. Unfortunately, there are exceptions
that may force you to modify the default functionality of the `resolve` task. If
you're unfamiliar with workspace's `resolve` task, see the
[Resolving Your Modules](/develop/tutorials/-/knowledge_base/7-1/resolving-your-modules)
tutorial for more information.

There are two scenarios you may run into during development that require a
modification for your project to pass the resolver check.

- You're depending on a third party library that is not available in the
  targeted @product@ instance or the current workspace.
- You're depending on a customized distribution of @product@.

You'll explore these use cases next.

## Depending on Third Party Libraries Not Included in @product@

The `resolve` task, by default, gathers all of @product@'s capabilities and the
capabilities of your workspace's modules. What if, however, your module depends
on a third party project that is not included in either space (e.g.,
[Google Guava](https://opensource.google.com/projects/guava))?. The `resolve`
task fails by default if your project depends on this project type. You
probably plan to have this project deployed and available at runtime, so it's
not a concern, but the resolver doesn't know that; you must customize the
resolver to bypass this.

There are three ways you can do this:

- [Embed the third party library in your module](#embed-the-third-party-project-in-your-module)
- [Add the third party library's capabilities to the current static set of resolver capabilities](#add-the-third-party-librarys-capabilities-to-the-current-static-set-of-resolver-capabilities)
- [Skip the resolving process for your module](#skip-the-resolving-process-for-your-module)

For help resolving third party dependency errors, see the
[Resolving Third Party Library Package Dependencies](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module)
tutorial.

### Embed the Third Party Library in Your Module

If you only have one module that depends on the third party project, you can
bypass the resolver failure by embedding the JAR in your module. This is not
a best practice if more than one project in the OSGi container depends on that
module. See the 
[Embedding Libraries in a Module](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module#embedding-libraries-in-a-module)
section for more details.

### Add the Third Party Library's Capabilities to the Current Static Set of Resolver Capabilities

You can add your third party dependencies to the target platform's default list
of capabilities by listing them as provided modules. Do this by adding the
following Gradle code into your workspace's root `build.gradle` file:

    dependencies {
        providedModules group: "GROUP_ID", name: "NAME", version: "VERSION"
    }

For example, if you wanted to add 
[Google Guava](https://opensource.google.com/projects/guava) 
as a provided module, it would look like this:

    dependencies {
        providedModules group: "com.google.guava", name: "guava", version: "23.0"
    }

This both provides the third party dependency to the resolver, and it downloads
and includes it in your @product@ bundle's `osgi/modules` folder when you
initialize it (e.g., `gradlew initBundle`).

### Skip the Resolving Process for Your Module

It may be easiest to skip validating a particular module during the resolve
process. To do this, open your workspace's root `build.gradle` file and insert
the following Gradle code at the bottom of the file:

    targetPlatform {
        resolveOnlyIf { project ->
            project.name != 'PROJECT_NAME'
        }
    }

Be sure to replace the `PROJECT_NAME` filler with your module's name (e.g.,
`test-api`).

If you prefer to disable the Target Platform plugin altogether, you can add a
slightly different directive to your `build.gradle` file:

    targetPlatform {
        onlyIf { project ->
            project.name != 'PROJECT_NAME'
        }
    }

This both skips the `resolve` task execution and disables BOM dependency
management. 

Now the `resolve` task skips your module project.

## Depending on a Customized Distribution of @product@

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
current workspace's @product@ instance, follow the steps below:

<!-- The below process will likely be automated in Blade at some point. -Cody -->

1.  Start the @product@ instance stored in your workspace. Make sure the
    platform you want to depend on is installed.

2.  Download the
    [BND Remote Agent JAR file](https://search.maven.org/#search%7Cga%7C1%7Cbiz.aqute.remote.agent)
    and copy it into the `osgi/modules` folder.

3.  From the root folder of your workspace, run the following command:

        bnd remote distro -o custom_distro.jar com.liferay.ce.portal.distro 7.1.0

    This connects to the newly deployed BND agent running in @product@ and
    generates a new distro JAR named `custom_distro.jar`. All other capabilities
    inherit their functionality based on your @product@ instance, so verify the
    workspace bundle is the version you plan to release in production.

4.  Navigate to your workspace's root `build.gradle` file and add the following
    dependency:

        dependencies {
            targetPlatformDistro files('custom_distro.jar')
        }

Now your workspace is pointing to a custom distro JAR file instead of the
default one provided. Run the `resolve` task to validate your modules against
the new set of capabilities.
