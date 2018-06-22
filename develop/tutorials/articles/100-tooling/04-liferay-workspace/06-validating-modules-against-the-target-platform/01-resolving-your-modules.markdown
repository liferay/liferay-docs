# Resolving Your Modules [](id=resolving-your-modules)

Deploying your modules to @product@ only to be met with console errors or
mysterious problems can be frustrating. You can avoid this painful process by
resolving your modules before deployment. This can be done by calling the
`resolve` Gradle task provided by Liferay Workspace.

    ../gradlew resolve

This task gathers all the capabilities provided by
    
- the specified version of @product@ (i.e., [targeted platform](/develop/tutorials/-/knowledge_base/7-0/managing-the-target-platform-for-liferay-workspace))
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
projects, themes, or npm portlets.

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

    liferay.workspace.target.platform.version=7.0-GA7

**Important:** You can leverage the target platform features in Liferay Portal
GA6+. Previous versions do not provide these features.
    
If you're using Liferay DXP, the versions are specified based on service packs.
For example, you could set your target platform workspace Gradle property to
    
    liferay.workspace.target.platform.version=7.0-sp7
    
<!-- TODO: Still need to find the earliest service pack version available for
Liferay 7.0 DXP and document it here. -Cody -->

Setting this property provides a static *distro* JAR for the specified version
of @product@, which contains all the metadata (i.e., capabilities, packages,
versions, etc.) running inside @product@. The distro JAR is a complete snapshot
of everything provided in @product@'s OSGi runtime; this serves as the target
platform's list of capabilities that your modules are validated against.

You can now validate your module projects before deploying them to @product@!
Sometimes, you must modify the `resolve` task's default behavior to successfully
validate your app. See the
[Modifying the Target Platform's Capabilities](/develop/tutorials/-/knowledge_base/7-0/modifying-the-target-platforms-capabilities)
tutorial for more information. For help resolving common output errors printed
by the `resolve` task, see the
[Resolving Common Output Errors Reported by the resolve Task](/develop/reference/-/knowledge_base/7-0/resolving-common-output-errors-reported-by-the-resolve-task)
article.
