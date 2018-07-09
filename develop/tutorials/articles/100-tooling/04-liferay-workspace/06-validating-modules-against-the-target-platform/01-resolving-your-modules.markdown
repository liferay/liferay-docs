# Resolving Your Modules [](id=resolving-your-modules)

Deploying your modules only to be met with console errors or mysterious
problems can be frustrating. You can avoid this painful process by resolving
your modules before deployment. This can be done by calling the
`resolve` Gradle task provided by Liferay Workspace.

    ../gradlew resolve

This task gathers all the capabilities provided by
 
- the specified version of @product@ (i.e., [targeted platform](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace))
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

+$$$

**Note:** The `resolve` task can be executed from a specific project folder or
from the workspace's root folder. Running the task from the root folder
validates all the modules in your workspace.

$$$

The `resolve` task can automatically gather the available capabilities from your
workspace, but you must specify this for your targeted @product@ version. To do
this, open your workspace's `gradle.properties` file and set the
`liferay.workspace.target.platform.version` property to the version you want to
target. For example,

    liferay.workspace.target.platform.version=7.1-GA1

If you're using Liferay DXP, you can set the property like this:

    liferay.workspace.target.platform.version=7.1.10.0

The versions following a GA1 release of DXP follow service pack versions
(e.g., `7.1-sp2`, `7.1-sp3`, etc.).

This provides a static *distro* JAR for the specified version of @product@,
which contains all the metadata (i.e., capabilities, packages, versions, etc.)
running in that version. The distro JAR is a complete snapshot of everything
provided in the OSGi runtime; this serves as the target platform's list
of capabilities that your modules are validated against.

You can now validate your module projects before deploying them! Sometimes, you
must modify the `resolve` task's default behavior to successfully validate your
app. See the 
[Modifying the Target Platform's Capabilities](/develop/tutorials/-/knowledge_base/7-1/modifying-the-target-platforms-capabilities)
tutorial for more information. For help resolving common output errors printed
by the `resolve` task, see the
[Resolving Common Output Errors Reported by the resolve Task](/develop/reference/-/knowledge_base/7-1/resolving-common-output-errors-reported-by-the-resolve-task)
article.
