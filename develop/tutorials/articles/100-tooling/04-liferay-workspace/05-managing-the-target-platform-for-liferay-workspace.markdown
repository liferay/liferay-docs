# Managing the Target Platform for Liferay Workspace

Liferay Workspace helps you target a specific release of @product@, so
dependencies get resolved properly. This makes upgrades easy: specify your
target platform, and Workspace points to the new version. All your dependencies
are updated to the latest ones provided in the targeted release.

<!-- TODO: Add note below once Semantic Versioning tutorial is available. -Cody

+$$$

**Note:** There are times when configuring dependencies based on a version range
is better than tracking exact versions. See the
[Semantic Versioning](/develop/tutorials/-/knowledge_base/7-1/semantic-versioning)
tutorial for more details.

$$$

-->

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide) 
helps you streamline targeting a specific version eve more. @ide@ can index the
configured @product@ source code to

- provide advanced Java search (Open Type and Reference Searching)
  ([tutorial](/develop/tutorials/-/knowledge_base/7-1/searching-product-source-in-liferay-ide))
- debug @product@ sources
  ([tutorial](/develop/tutorials/-/knowledge_base/7-1/debugging-product-source-in-liferay-ide))

These options in @ide@ are only available when developing in a Liferay
Workspace, or if you have the
[Target Platform](/develop/reference/-/knowledge_base/7-1/target-platform-gradle-plugin)
Gradle plugin applied to your multi-module Gradle project with specific
configurations. See the
[Targeting a Platform Outside of Workspace](#targeting-a-platform-outside-of-workspace)
section for more info on applying the Target Platform Gradle plugin.

Next, you'll discover how all of this is possible.

## Dependency Management with BOMs

You can target a version by importing a predefined bill of materials (BOM). This
only requires that you specify a property in your workspace's
`gradle.properties` file. You'll see how to do this later.

Each @product@ version has a predefined BOM that you can specify for your
workspace to reference. Each BOM defines the artifacts and their versions used
in the specific release. BOMs list all dependencies in a management fashion, so
it doesn't **add** dependencies to your project; it only **provides** your build
tool (e.g., Gradle or Maven) the versions needed for the project's defined
artifacts. This means you don't need to specify your dependency versions; the
BOM automatically defines the appropriate artifact versions based on the BOM.
For more information on BOMs, see the
[Importing Dependencies](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism#Importing_Dependencies)
section in Maven's official documentation.

Pretty cool, right? Next, you'll step through an example configuration.

## Setting the Target Platform

Setting the version to develop for takes two steps: 

1.  Open the workspace's `gradle.properties` file and set the
    `liferay.workspace.target.platform.version` property to the version you want
    to target. For example,

        liferay.workspace.target.platform.version=7.1.0

2.  Once the target platform is configured, check to make sure no dependencies
    in your Gradle build files specify a version. The versions are now imported
    from the configured target platform's BOM. For example, a simple MVC
    portlet's `build.gradle` may look something like this:

        dependencies {
            compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
            compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
            compileOnly group: "javax.portlet", name: "portlet-api"
            compileOnly group: "javax.servlet", name: "javax.servlet-api"
            compileOnly group: "jstl", name: "jstl"
            compileOnly group: "org.osgi", name: "osgi.cmpn"
        }

+$$$

**Note**: The `liferay.workspace.target.platform.version` property also sets
the distro JAR, which can be used in to validate your projects during the build
process. See the
[Validating Modules Against the Target Platform](/develop/tutorials/-/knowledge_base/7-1/validating-projects-against-the-target-platform)
tutorials for more info.

$$$

The target platform functionality is available in Liferay Workspace version
1.9.0+. If you have an older version, you must update it to leverage platform
targeting. See the
[Updating Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/updating-liferay-workspace)
tutorial to do this.

You now know how to configure a target platform in workspace and how
dependencies without versions appear in your Gradle build files. You're all set!

## Targeting a Platform Outside of Workspace

If you prefer to not use Liferay Workspace, but still want to target a platform,
you must apply the 
[Target Platform](/develop/reference/-/knowledge_base/7-1/target-platform-gradle-plugin)
Gradle plugin to the root `build.gradle` file of your custom multi-module Gradle
build.

To do this, your `build.gradle` file should look similar to this:

    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.lifereay.gradle.plugins.target.platform", version "1.0.0"
        }
        repositories {
            maven {
                url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
            }
        }
    }

    apply plugin: "com.liferay.target.platform"

    dependencies {
        targetPlatformBoms group: "com.liferay", name: "com.liferay.ce.portal.bom", version: "7.1.0"
        targetPlatformBoms group: "com.liferay", name: "com.liferay.ce.portal.compile.only", version: "7.1.0"
    }

<!-- TODO: The temporary place to reference Liferay's portal artifacts for 7.1
is still TBD, according to Greg. Update this in the future, if necessary -Cody
-->

This Gradle code

- applies Liferay's Target Platform Gradle plugin
- configures the repository that provides the necessary artifacts for your
  project build
- sets the Target Platform plugin's dependencies:
    - `com.liferay.ce.portal.bom`: provides all the artifacts included in
      @product@.
    - `com.liferay.ce.portal.compile.only`: provides artifacts that are not
      included in @product@, but are necessary to reference during the build
      (e.g., `org.osgi.core`).

If you're interested in
[advanced search](/develop/tutorials/-/knowledge_base/7-1/searching-product-source-in-liferay-ide)
and/or
[debugging](/develop/tutorials/-/knowledge_base/7-1/debugging-product-source-in-liferay-ide)
@product@'s source using
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide), you must
also apply the following configuration:

    targetPlatformIDE {
        includeGroups "com.liferay", "com.liferay.portal"
    }

This indexes the target platform's source code and makes it available to @ide@.

Now you can define your target platform!
