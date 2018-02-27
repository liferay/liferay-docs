# Managing the Target Platform for Liferay Workspace

Liferay Workspace provides a way to target a specific release of @product@ to
develop against. This greatly decreases the need to manage dependencies and
their versions when developing your app. For example, suppose you've developed
your app for the current release of @product@, but then a new version is
released. Of course, many of the artifact versions have incremented, so you must
update your app to use the latest artifacts available. It can be a hassle
tracking down artifact versions and updating your app accordingly. With the
option to specify your target platform, you update your workspace to point to a
different @product@ version and all your dependencies are updated to the latest
ones provided in the targeted release.

The option to target a specific @product@ version to develop against also offers
additional features for those using
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide) for
development. @ide@ can index the configured @product@ source code to

- provide advanced search
  ([tutorial](/develop/tutorials/-/knowledge_base/7-1/searching-product-source-in-liferay-ide))
- debug @product@
  ([tutorial](/develop/tutorials/-/knowledge_base/7-1/debugging-product-source-in-liferay-ide))

These options in @ide@ are only available when developing in a Liferay
Workspace, or if you have the
[Target Platform](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-target-platform)
Gradle plugin applied to your multi-module Gradle project. See the
[Targeting a Platform Outside of Workspace](#targeting-a-platform-outside-of-workspace)
section for more info on applying the Target Platform Gradle plugin.

<!-- TODO: Update link for Gradle plugin with reference doc/README when it's
available. -Cody -->

Next, you'll discover how all of this is possible.

## Dependency Management with BOMs

Targeting a @product@ version to develop against is possible by importing a
predefined bill of materials (BOM). Don't worry; this is handled behind the
scenes and only requires that you specify a property in your workspace's
`gradle.properties` file. You'll see how to do this later.

Each @product@ version has a defined BOM that you can specify for your workspace
to reference. Each BOM defines the artifacts and their versions used in the
specific release. BOMs list all dependencies in a management fashion. So it
doesn't **add** dependencies to your project; it only **provides** your build
tool (e.g., Gradle or Maven) the versions needed for the project's defined
artifacts. This means you don't need to specify your dependency versions; the
BOM automatically defines the appropriate artifact versions based on the BOM.
For more information on BOMs, see the
[Importing Dependencies](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism#Importing_Dependencies)
section in Maven's official documentation.

Pretty cool, right? Next, you'll step through an example configuration.

## Setting the Target Platform

Setting a @product@ version to develop against is easy in Liferay Workspace.
Open the workspace's `gradle.properties` file and set the
`liferay.workspace.target.platform.version` property to the version you want to
target. For example,

    liferay.workspace.target.platform.version=7.1.0-m1

<!-- TODO: Update above version to accurate milestone version syntax. This was a
shot in the dark. -Cody -->

Once the target platform is configured, check to make sure no dependencies in
your Gradle build files specify a version. The versions are now imported from
the configured target platform's BOM. For example, a simple MVC portlet's
`build.gradle` may look something like this:

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
the distro JAR used in workspace to validate your projects during the build
process. See the
[Validating Projects Against the Target Platform](/develop/tutorials/-/knowledge_base/7-1/validating-projects-against-the-target-platform)
tutorial for more info.

$$$

<!-- Question: What if dependency I want to use is not in BOM? Do I specify
version? -Cody -->

The target platform functionality is available in Liferay Workspace version
1.8.1+. If you have an older version, you must update it to leverage platform
targeting. See the
[Updating Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/updating-liferay-workspace)
tutorial to do this.

<!-- TODO: Verify that the 1.8.1 version is accurate once the features are
officially released. -Cody -->

You now know how to configure a target platform in workspace and how
dependencies without versions appear in your Gradle build files. You're all set!

## Targeting a Platform Outside of Workspace

<!-- TODO: Remove this section once the reference doc for the Gradle plugin is
available. This is just gives a taste of targeting a platform for those who
don't want to use Workspace. More details will be provided in reference doc.
-Cody -->

If you prefer to not use Liferay Workspace, but still want to target a platform
to develop against, you must apply the
[Target Platform](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-target-platform)
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
            maven {
                url "https://repository.lifray.com/nexus/service/local/repositories/liferay-public-snapshots/content"
            }
            mavenLocal()
        }
    }

    apply plugin: "com.liferay.target.platform"

    dependencies {
        targetPlatformBoms group: "com.liferay", name: "com.liferay.ce.portal.bom", version: "7.0.5-SNAPSHOT"
        targetPlatformBoms group: "com.liferay", name: "com.liferay.ce.portal.compile.only", version: "7.0.5-SNAPSHOT"
    }

This Gradle code

- applies Liferay's Target Platform Gradle plugin
- configures repositories that will provide the necessary artifacts for your
  project build
- sets Target Platform dependencies:
    - `com.liferay.ce.portal.bom`: provides all the artifacts included in
      @product@.
    - `com.liferay.ce.portal.compile.only`: provides artifacts that are not
      included in @product@, but are necessary to reference during the build
      (e.g., `org.osgi.core`).

Now you can develop against a target platform!