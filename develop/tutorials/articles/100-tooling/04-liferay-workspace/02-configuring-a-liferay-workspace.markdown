# Configuring a Liferay Workspace [](id=configuring-a-liferay-workspace)

A Liferay Workspace offers a development environment that can be configured to
fit your development needs. You'll learn about the files/folders a workspace
provides by default, and then you'll dive into configuring your workspace.

The top-level files/folder of a Liferay (Gradle) Workspace are outlined below:

- `bundles` (generated): the default folder for @product@ bundles.
- `configs`: holds the configuration files for different environments. These
  files serve as your global configuration files for all @product@ servers and
  projects residing in your workspace. To learn more about using the `configs`
  folder, see the
  [Testing Modules](/develop/tutorials/-/knowledge_base/7-1/development-lifecycle-for-a-liferay-workspace#testing-modules)
  section.
- `gradle`: holds the Gradle Wrapper used by your workspace.
- `modules`: holds your custom modules.
- `plugins-sdk` (generated): holds plugins to migrate from previous releases.
- `themes`: holds your custom themes which are built using the Theme Generator.
- `wars` (generated): holds traditional WAR-style web application projects.
- `build.gradle`: the common Gradle build file.
- `gradle.properties`: specifies the workspace's project locations and @product@
  server configuration globally. 
- `gradle-local.properties`: sets user-specific properties for your workspace.
  This lets multiple users use a single workspace, letting them configure
  specific properties for the workspace on their own machine.
- `gradlew`: executes the Gradle command wrapper
- `settings.gradle`: applies plugins to the workspace and configures its
  dependencies.

If you're using a Liferay
[Maven Workspace](/develop/tutorials/-/knowledge_base/7-1/maven-workspace)
instead, your folder hierarchy is the same, except the Gradle build files are
swapped out for a `pom.xml` file. See the Maven Workspace tutorial for more info
on configuring that kind of workspace project.

The build/properties files included in your workspace's root folder sets your
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

All properties in the `gradle.properties` file are documented within the file.
To learn more about how each property works and what's available, visit your
workspace's generated `gradle.properties` file. You can also visit the
[Gradle Workspace Properties](#gradle-workspace-properties) section for a list
of these properties.


+$$$

**Note:** Liferay Workspace provides many subprojects for you behind the scenes,
which hides some complexities of Gradle. You can learn more about this in the
[Building Modules](/develop/tutorials/-/knowledge_base/7-1/development-lifecycle-for-a-liferay-workspace#building-modules)
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
(e.g., `http://downloads.sourceforge.net/project/lportal/Liferay%20Portal/7.0.4%20GA5/liferay-ce-portal-tomcat-7.0-ga5-20171018150113838.zip`).
You can also set the folder where your Liferay bundle is generated with the
`liferay.workspace.home.dir` property. It's set to `bundles` by default.

You can download a Liferay DXP bundle for your workspace if you're a DXP
subscriber. Do this by setting the `liferay.workspace.bundle.url` property to a
ZIP hosted on *api.liferay.com*. For example,

`liferay.workspace.bundle.url=https://api.liferay.com/downloads/portal/7.0.10.6/liferay-dxp-digital-enterprise-tomcat-7.0-sp6-20171010144253003.zip`

<!-- I formatted the above snippet differently to avoid providing a link.
-Cody-->

It can be tricky to find the fully qualified ZIP name/number for the DXP bundle
you want. You cannot access Liferay's API site directly to find it, so you must
start to download DXP manually, take note of the file name, and append it to
`https://api.liferay.com/downloads/portal/`.

You must also set the `liferay.workspace.bundle.token.download` property to
`true` to allow your workspace to access Liferay's API site.

Once you've finalized your Gradle properties, navigate to your workspace's root
folder and run

    ./gradlew initBundle

As you read earlier, the workspace uses a Gradle wrapper to download and use
Gradle build tools. This command downloads the version of Liferay you specified
in your Gradle properties and installs your Liferay instance in the `bundles`
folder.

If you run into errors during the bundle downloading process, check to make sure
your workspace is accounting for the
[appropriate certificates](#certification-issues-in-liferay-workspace).

If you want to skip the downloading process, you can create the `bundles` folder
manually in your workspace's ROOT folder and unzip your @product@ bundle to that
folder.

You can also produce a distributable Liferay bundle (Zip or Tar) from within a
workspace. To do this, navigate to your workspace's root folder and run the
following command:

    ./gradlew distBundle[Zip|Tar]

Your distribution file is available from the workspace's `/build` folder.

+$$$

**Note:** You can define different environments for your Liferay bundle for
easy testing. You can learn more about this in the
[Testing Modules](/develop/tutorials/-/knowledge_base/7-1/development-lifecycle-for-a-liferay-workspace#testing-modules)
section.

$$$

The Liferay Workspace is a great development environment for Liferay module
development; however, what if you'd like to also stick with developing WAR-style
applications? Liferay Workspace can handle that request too!

## Using a Plugins SDK from Your Workspace [](id=using-a-plugins-sdk-from-your-workspace)

Because @product-ver@ uses a module-based framework, the current structure of a
Liferay Workspace is centered around module development. There are still,
however, situations where you must create WAR-style plugins using the Plugins
SDK. Because of this, your workspace can also work with the Plugins SDK. When
configuring your SDK in a workspace, you can take advantage of all the new
functionality workspaces provide and also use the SDK environment that you're
used to. To learn more about upgrading legacy applications to @product-ver@ and
what you should consider before converting them to modules, visit the tutorial
[Planning Plugin Upgrades and Optimizations](/develop/tutorials/-/knowledge_base/7-1/migrating-existing-code-to-liferay-7).

Blade CLI offers a command that adds and configures your current Plugins SDK
environment automatically for use inside a newly generated workspace (e.g.,
`blade init -u`). You can learn more about this in the
[Creating a Liferay Workspace with Blade CLI](/develop/tutorials/-/knowledge_base/7-1/creating-a-liferay-workspace-with-blade-cli)
tutorial. If you created your workspace from scratch and want to use a Plugins
SDK, however, you can add one to your workspace by completing one of the two
options:

1.  Copy your existing Plugins SDK's files into the workspace.

2.  Generate a new Plugins SDK to use in the workspace.

Follow the appropriate section based on the option you want to follow.

### Copying an Existing Plugins SDK into Workspace [](id=copying-an-existing-plugins-sdk-into-workspace)

If you open your workspace's `gradle.properties` file, you'll notice the
`liferay.workspace.plugins.sdk.dir` property sets the Plugins SDK folder to
`plugins-sdk`. This is where the workspace expects any Plugins SDK files to
reside. This folder is not generated by default, so you must create it
yourself. In your workspace's root folder, create the `plugins-sdk` folder. Then
copy your legacy Plugins SDK files into the `plugins-sdk` folder.

The copied Plugins SDK requires many build-related artifacts. To start the
artifact download process, execute the following command in your workspace's
root folder:

    ./gradlew upgradePluginsSDK

The Plugins SDK's artifacts are downloaded. The Plugins SDK is now ready for
use!

### Generating a New Plugins SDK in Workspace [](id=generating-a-new-plugins-sdk-in-workspace)

You can easily generate a new Plugins SDK for your workspace by executing a
single Gradle command in your workspace's root folder:

    ./gradlew upgradePluginsSDK

This generates a new @product-ver@ Plugins SDK into the folder set by the
`liferay.workspace.plugins.sdk.dir` property, which is configured to
`plugins-sdk` by default in the workspace's `gradle.properties` file. You can
change the folder name by updating the property. The downloaded Plugins SDK
version is the latest release at the time of execution. You can reference the
latest Plugins SDK releases
[here](https://repository.liferay.com/nexus/content/groups/public/com/liferay/portal/com.liferay.portal.plugins.sdk/).

Once the downloading is complete, your Plugins SDK is ready to use
in your workspace!

## Gradle Workspace Properties [](id=gradle-workspace-properties)

The following configurable properties are available in your workspace's
`gradle.properties` file:

- `liferay.workspace.bundle.cache.dir`: Set the directory where the downloaded
  bundle Zip files are stored. The default value is the `.liferay/bundles`
  folder inside the user home directory.
- `liferay.workspace.bundle.token.download`: Set this to `true` if the
  `liferay.workspace.bundle.url` property is set to a DXP bundle Zip. This
  property allows the token residing in the `~/.liferay` folder to be used to
  validate your user credentials when downloading the bundle. The default value
  is `false`.
- `liferay.workspace.bundle.token.email.address`: Set the email address to use
  when downloading a DXP bundle. This is used to create the authentication
  token. The email address must match the one registered for your DXP
  subscription. If you wish to create a new token without providing your email
  address and password in this file, you can create a token manually by
  navigating to your Liferay profile's Account Setting page and generating a
  token in the Authentication Tokens menu. Your token must reside in the
  `~/.liferay` folder.
- `liferay.workspace.bundle.token.force`: Set this to `true` to override the
  existing token with a newly generated  token created by the `createToken`
  task. The default value is `false`.
- `liferay.workspace.bundle.token.password`: Set the password to use when
  downloading a DXP bundle. This is used to create the authentication token. The
  password must match the one registered for your DXP subscription. See the
  `liferay.workspace.bundle.token.email.address` property for more details.
- `liferay.workspace.bundle.token.password.file`: Set the file to hold the
  Liferay bundle authentication token password. The default file value is
  `~/.liferay/token`.
- `liferay.workspace.bundle.url`: Set the URL pointing to the bundle Zip to
  download. If the URL points to a DXP bundle (e.g.,
  https://api.liferay.com/...), set the `liferay.workspace.bundle.token.download` 
  property to `true`. The default value is the URL for Liferay Portal CE 7.0.4
  GA5.
- `liferay.workspace.default.repository.enabled`: Set this to `true` to
  configure Liferay CDN as the default repository in the root project. The
  default value is `true`.
- `liferay.workspace.environment`: Set the environment with the settings
  appropriate for current development. The `configs` folder is used to hold
  different environments in the same workspace. You can organize environment
  settings and generate an environment installation with those settings. There
  are five environments: `common`, `dev`, `local`, `prod`, and `uat`. The
  default value is `local`.
- `liferay.workspace.home.dir`: Set the folder that contains the Liferay bundle
  downloaded from the `liferay.workspace.bundle.url` property. The default value
  is `bundles`.
- `liferay.workspace.modules.default.repository.enabled`: Set this to `true` to
  configure Liferay CDN as the default repository for module/OSGi projects. The
  default value is `true`.
- `liferay.workspace.modules.dir`: Set the folder that contains all module/OSGi
  projects. The default value is `modules`.
- `liferay.workspace.modules.jsp.precompile.enabled`: Set this to `true` to
  compile the JSP files in OSGi modules and have them added to the distributable
  Zip/Tar. The default value is `false`.
- `liferay.workspace.plugins.sdk.dir`: Set the folder that contains the Plugins
  SDK environment. The default value is `plugins-sdk`.
- `liferay.workspace.themes.dir`: Set the folder that contains Node.js-style
  theme projects. The default value is `themes`.
- `liferay.workspace.themes.java.build`: Set this to `true` to build the theme
  projects using the Liferay Portal Tools Theme Builder. The default value is
  `false`.
- `liferay.workspace.wars.dir`: Set the folder that contains classic WAR-style
  projects. The default value is `wars`.

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
