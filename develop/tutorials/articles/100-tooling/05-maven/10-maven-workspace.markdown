# Maven Workspace

A Liferay Maven Workspace is a generated environment that is built to hold and
manage your Liferay projects built with Maven. This workspace is intended to aid
in the management of Liferay projects by providing various Maven build scripts
and configured properties.

<!-- Continue with intro. -Cody -->

## Installation

The Maven Workspace is installed by generating the workspace project from an
archetype. You can do this by executing the following command in your command
line tool:

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.workspace \
        -DgroupId=[GROUP_ID] \
        -DartifactId=[WORKSPACE_NAME] \
        -Dversion=[VERSION] \
        -Dpackage=[PACKAGE]
        
Your Maven Workspace is generated to the folder you ran the command from.

## Anatomy

The default Maven Workspace is broken down into the following folders/files:

- `[MAVEN_WORKSPACE]`
    - `configs`
        - `common`
        - `dev`
        - `local`
        - `prod`
        - `uat`
    - `modules`
        - `pom.xml`
    - `themes`
        - `pom.xml`
    - `wars`
        - `pom.xml`
    - `gradle.properties`
    - `pom.xml`
    - `settings.gradle`

For more information on the `configs` folder, see the
[Testing Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#testing-modules)
section. The proceeding folders should hold projects of that type. The
`gradle.properties` file specifies the workspace's project locations and Liferay
Portal server configuration globally. The `settings.gradle` file applies plugins
to the workspace and configures its dependencies. Lastly, the parent `pom.xml`
configures your workspace as a Maven project and applies the
[Bundle Support plugin](https://repository.liferay.com/nexus/content/groups/public/com/liferay/com.liferay.portal.tools.bundle.support/),
which is required for your Maven workspace to properly handle @product-ver@
projects.

## Adding a Liferay Bundle to a Maven Workspace

Liferay Workspaces can generate and hold a Liferay Server. This lets you
build/test your plugins against a running Liferay instance. Before generating a
Liferay instance, open the gradle.properties file located in your workspace's
root folder. There are several configurable properties for your workspace's
Liferay instance. You can set the version of the Liferay bundle you'd like to
generate and install by setting the download URL for the
liferay.workspace.bundle.url property (e.g.,
https://sourceforge.net/projects/lportal/files/Liferay Portal/7.0.1 GA2/liferay-ce-portal-tomcat-7.0-ga2-20160610113014153.zip).
You can also set the folder where your Liferay bundle is generated with the
liferay.workspace.home.dir property. It's set to bundles by default.

Once you've finalized your Gradle properties, navigate to your workspace's root
folder and run

    ./gradlew initBundle
    
As you read earlier, the workspace uses a Gradle wrapper to download and use
Gradle build tools. This command downloads the version of Liferay you specified
in your Gradle properties and installs your Liferay instance in the bundles
folder.

If you run into errors during the bundle downloading process, check to make sure
your workspace is accounting for the
[appropriate certificates](/develop/tutorials/-/knowledge_base/7-0/configuring-a-liferay-workspace#certification-issues-in-liferay-workspace).

If you want to skip the downloading process, you can create the bundles folder
manually in your workspace's ROOT folder and unzip your Liferay Portal bundle to
that folder.

You can also produce a distributable Liferay bundle (Zip or Tar) from within a
workspace. To do this, navigate to your workspace's root folder and run the
following command:

    ./gradlew distBundle[Zip|Tar]

Your distribution file is available from the workspace's /build directory.













