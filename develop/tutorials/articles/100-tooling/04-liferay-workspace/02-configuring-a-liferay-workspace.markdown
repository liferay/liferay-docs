# Configuring a Liferay Workspace [](id=configuring-a-liferay-workspace)

A Liferay Workspace offers a development environment that can be configured to
fit your development needs. You'll learn about the files/folders a workspace
provides by default, and then you'll dive into configuring your workspace.

The top-level files/folder of a Liferay workspace are outlined below:

- `bundles` (generated): the default folder for @product@ bundles.
- `configs`: holds the configuration files for different environments. These
  files serve as your global configuration files for all Liferay servers and
  projects residing in your workspace. To learn more about using the `configs`
  folder, see the
  [Testing Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#testing-modules)
  section.
- `gradle`: holds the Gradle Wrapper used by your workspace.
- `modules`: holds your custom modules.
- `plugins-sdk` (generated): holds plugins to migrate from previous releases.
- `themes`: holds your custom themes which are built using the Theme Generator.
- `wars` (generated): holds traditional WAR-style web application projects.
- `build.gradle`: the common Gradle build file.
- `gradle-local.properties`: sets user-specific properties for your workspace.
  This lets multiple users use a single workspace, letting them configure
  specific properties for the workspace on their own machine.
- `gradle.properties`: specifies the workspace's project locations and @product@
  server configuration globally. 
- `gradlew`: executes the Gradle command wrapper
- `settings.gradle`: applies plugins to the workspace and configures its
  dependencies.

The build/properties files included in your workspace's root directory sets your
workspace's Gradle properties and facilitates the build processes of your
modules.

Before you begin using your workspace, you should set your workspace Gradle
properties in the `gradle.properties` file. There are several commented out
properties in this file. These are the default properties set in your workspace.
If you'd like to change a variable, uncomment the variable and set it to a
custom value. For instance, if you want to store your modules in a folder other
than `[ROOT]/modules`, uncomment the `liferay.workspace.modules.dir` variable
and set it to a different value.

If you'd like to keep the global Gradle properties the same, but want to change
them for yourself only (perhaps for local testing), you can override the
`gradle.properties` file with your own `gradle-local.properties` file.

+$$$

**Note:** Liferay Workspace provides many subprojects for you behind the scenes,
which hides some complexities of Gradle. You can learn more about this in the
[Building Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#building-modules)
section.

$$$

Now that you know about a workspace's default folder structure and how to modify
its Gradle properties, you'll learn how to add a Liferay bundle to your
workspace.

## Adding a Liferay Bundle to a Workspace [](id=adding-a-liferay-bundle-to-a-workspace)

Liferay Workspaces can generate and hold a Liferay Server. This lets you
build/test your plugins against a running Liferay instance. Before generating a
Liferay instance, open the `gradle.properties` file located in your workspace's
root folder. There are several configurable properties for your workspace's
Liferay instance. You can set the version of the Liferay bundle you'd like to
generate and install by setting the download URL for the
`liferay.workspace.bundle.url` property
(e.g., `http://downloads.sourceforge.net/project/lportal/Liferay%20Portal/7.0.2%20GA3/liferay-ce-portal-tomcat-7.0-ga3-20160804222206210.zip`).
You can also set the folder where your Liferay bundle is generated with the
`liferay.workspace.home.dir` property. It's set to `bundles` by default.

Once you've finalized your Gradle properties, navigate to your workspace's root
folder and run

    ./gradlew initBundle

As you read earlier, the workspace uses a Gradle wrapper to download and use
Gradle build tools. This command downloads the version of Liferay you specified
in your Gradle properties and installs your Liferay instance in the `bundles`
folder.

If you run into errors during the bundle downloading process, check to make sure
your workspace is accounting for the
[appropriate certificates](/develop/tutorials/-/knowledge_base/7-0/configuring-a-liferay-workspace#certification-issues-in-liferay-workspace).

If you want to skip the downloading process, you can create the `bundles` folder
manually in your workspace's ROOT folder and unzip your @product@ bundle to that
folder.

You can also produce a distributable Liferay bundle (Zip or Tar) from within a
workspace. To do this, navigate to your workspace's root folder and run the
following command:

    ./gradlew distBundle[Zip|Tar]

Your distribution file is available from the workspace's `/build` directory.

+$$$

**Note:** You can define different environments for your Liferay bundle for
easy testing. You can learn more about this in the
[Testing Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#testing-modules)
section.

$$$

The Liferay Workspace is a great development environment for Liferay module
development; however, what if you'd like to also stick with developing WAR-style
applications? Liferay Workspace can handle that request too!

## Using a Plugins SDK from Your Workspace [](id=using-a-plugins-sdk-from-your-workspace)

<!-- This section should be moved to Migration tooling section once it's
available. -Cody -->

Because @product-ver@ uses a module-based framework, the current structure of a
Liferay Workspace is centered around module development. There are still,
however, many situations where you must create WAR-style plugins using the
Plugins SDK. Because of this, your workspace can also work with the Plugins SDK.
When configuring your SDK in a workspace, you can take advantage of all the new
functionality workspaces provide and also use the SDK environment that you're
used to. To learn more about upgrading legacy applications to @product-ver@ and
what you should consider before converting them to modules, visit the tutorial
[Planning Plugin Upgrades and Optimizations](/develop/tutorials/-/knowledge_base/7-0/migrating-existing-code-to-liferay-7).

The Blade CLI offers a command that allows a Plugins SDK environment be
automatically generated with Liferay Workspace. If you created your workspace
from scratch, however, and want to use a Plugins SDK, follow the instructions
below.

If you revisit your workspace's `gradle.properties` file, you'll notice the
Plugins SDK folder is set to `plugins-sdk`. This folder was not generated by
default, so you must create it yourself. In your workspace's root
folder, create the `plugins-sdk` folder. Then copy your legacy Plugins SDK
files into the `plugins-sdk` folder.

Liferay Workspace and the Plugins SDK require many build related artifacts. To
start the artifact download process, execute the following command in your
workspace's root folder:

    ./gradlew tasks

The Workspace and Plugins SDK's artifacts are downloaded, and the available
tasks are listed in the terminal. Since the download process takes time,
invoking it at a convenient time such as before a work break can be
worthwhile. Once the downloading is complete, your Plugins SDK is ready to use
in your workspace. It's as simple as that!

## Certification Issues in Liferay Workspace [](id=certification-issues-in-liferay-workspace)

Some of Liferay Workspace's Gradle functionality downloads files from hosting
sites that depend on certificates that are not included in older versions of the
JDK. This can cause issues when executing commands that use Gradle (e.g., Blade
CLI).

To avoid issues with certification issues, you should update your JDK to
*Java 8u101* or later. If you prefer to stay on the same JDK version, you must
add the *Let's Encrypt* certificate chain to your JDK. To learn more about the
certificates and their compatibility, see
[Let's Encrypt Compatibility](https://letsencrypt.org/docs/certificate-compatibility/).

Not accounting for these certificates could lead to broken executables in
workspace that rely on Gradle services. For example, downloading a @product@
bundle via `./gradlew initBundle` without an updated JDK causes the following
error:

    Execution failed for task ':downloadBundle'.
    > javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed:
    sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target

Errors can also be thrown during the Gradle build process of your modules. Make
sure to update your JDK for optimal performance when using Liferay Workspace.
