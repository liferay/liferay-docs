# Resolving Your Modules

Deploying your modules to @product@ only to be met with console errors or
mysterious problems can be frustrating. You can avoid this painful process by
resolving your modules before deployment. This can be done by calling the
`resolve` Gradle task provided by Liferay Workspace.

    gradlew resolve

This task gathers all the capabilities provided by
    
- the specified version of @product@
- the current workspace's modules

Some capabilities/information gathered by the `resolve` task that are validated
include

- declared required capabilities
- module versions
- package imports/use constraints
- service references
- etc.

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
specify the version you intend to deploy to. To do this, open your workspace's
`gradle.properties` file and set the `liferay.workspace.target.platform.version`
property to the version you want to target. For example,

    liferay.workspace.target.platform.version=7.1.0

This provides a static *distro* JAR for the specified version of @product@,
which contains all the metadata (i.e., capabilities, packages, versions, etc.)
running inside @product@. The distro JAR is a complete snapshot of everything
provided in @product@'s OSGi runtime; this serves as the target platform's list
of capabilities that your modules are validated against.

<!-- TODO: Below info is out of place after reorg. This will probably fit better
in the error resolving tutorial. -Cody -->

<!--
The `resolve` task can also report issues with separate modules requiring
different versions of another module. For example, suppose you have *module A*
requiring *module Test version 1* and *module B* requiring *module Test version
4*. Without running the resolver, both modules A and B would compile
successfully, but when they were deployed, one would fail in @product@'s OSGi
runtime because both dependencies cannot be satisfied. These types of scenarios
are difficult to diagnose, but with the `resolve` task, can be found with ease.
-->

You can now validate your module projects before deploying them to @product@!
Sometimes, you must modify the `resolve` task's default behavior to successfully
validate your app. See the
[Modifying the Target Platform's Capabilities](/develop/tutorials/-/knowledge_base/7-1/modifying-the-target-platforms-capabilities)
tutorial for more information.