# Configuring a Liferay Workspace

A Liferay Workspace offers a development environment that can be configured to
fit your development needs. You'll learn about the files/folders a workspace
provides by default, and then you'll dive into configuring your workspace.

The top-level files/folder of a Liferay workspace are outlined below:

- `bundles` (generated): the default folder for @product@ bundles.
- `configs`: holds the configuration files for different environments. These
  files serve as your global configuration files for all Liferay servers and
  projects residing in your workspace. To learn more about using the `configs`
  folder, see the
  [Testing Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-liferay-workspaces#testing-modules)
  section.
- `gradle`: holds the Gradle Wrapper used by your workspace.
- `modules`: holds your custom modules.
- `plugins-sdk` (generated): holds plugins to migrate from previous releases.
- `themes`: holds your custom themes which are built using the Theme Generator.
- `wars` (generated): holds traditional WAR-style web application projects.
- `build.gradle`: the common Gradle build file.
- `gradle.properties`: specifies the workspace's project locations and @product@
  server configuration.
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

+$$$

**Note:** Liferay Workspace provides many subprojects for you behind the scenes,
which hides some complexities of Gradle. You can learn more about this in the
[Building Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-liferay-workspaces#building-modules)
section.

$$$

Once you've developed and managed projects in your workspace, it could also be
useful to bundle your workspace into a ZIP or TAR file. This provides the
flexibility to distribute your workspace to potential consumers. To do this,
navigate to your workspace's root folder and run the following command:

    ./gradlew distBundle[Zip|Tar]

Your distribution file is available from the workspace's `/build` directory.

Now that you know about a workspace's default folder structure and how to modify
its Gradle properties, you'll learn how to add a Liferay bundle to your
workspace.

## Adding a Liferay Bundle to a Workspace

Liferay Workspaces can generate and hold a Liferay Server. This lets you
build/test your plugins against a running Liferay instance. Before generating a
Liferay instance, open the `gradle.properties` file located in your workspace's
root folder. There are several configurable properties for your workspace's
Liferay instance. You can set the version of the Liferay bundle you'd like to
generate and install by setting the download URL for the
`liferay.workspace.bundle.url` property
(e.g., `https://sourceforge.net/projects/lportal/files/Liferay Portal/7.0.1 GA2/liferay-ce-portal-tomcat-7.0-ga2-20160610113014153.zip`).
You can also set the folder where your Liferay bundle is generated with the
`liferay.workspace.home.dir` property. It's set the `bundles` by default.

Once you've finalized your Gradle properties, navigate to your workspace's root
folder and run

    ./gradlew initBundle

As you read earlier, the workspace uses a Gradle wrapper to download and use
Gradle build tools. This command downloads the version of Liferay you specified
in your Gradle properties and installs your Liferay instance in the `bundles`
folder.

If you want to skip the downloading process, you can create the `bundles` folder
manually in your workspace's ROOT folder and unzip your @product@ bundle to that
folder.

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
[Planning a Plugin Upgrade to Liferay @product-ver@](/develop/tutorials/-/knowledge_base/7-0/migrating-existing-code-to-liferay-7).

The Blade CLI offers a command that allows a Plugins SDK environment be
automatically generated with Liferay workspace. If you created your workspace
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
