# Maven Workspace [](id=maven-workspace)

A Liferay Maven Workspace is a generated environment that is built to hold and
manage Liferay projects built with Maven. This workspace aids in Liferay project
management by applying various Maven plugins and configured properties. The
Liferay Maven Workspace offers a full development lifecycle for your Maven
projects to make developing them for @product@ easier than ever. In this
tutorial, you'll learn how to leverage the development lifecycle of a Liferay
Maven Workspace.

First, you'll learn how to install a Maven Workspace.

## Installation [](id=installation)

The Maven Workspace is installed by generating the workspace project from an
archetype. You can do this by executing the following command with your command
line tool:

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.workspace \
        -DgroupId=[GROUP_ID] \
        -DartifactId=[WORKSPACE_NAME] \
        -Dversion=[VERSION]

A Maven Workspace is generated in the current folder. No other tools or CLIs are
required for Maven Workspace.

## Anatomy [](id=anatomy)

The default Maven Workspace contains the following folders/files:

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
    - `pom.xml`

For more information on the `configs` folder, see the
[Testing Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#testing-modules)
section. The `modules`, `themes`, and `wars` folders hold projects of that type.
The parent `pom.xml` configures your workspace as a Maven project and applies
the
[Bundle Support plugin](https://repository.liferay.com/nexus/content/groups/public/com/liferay/com.liferay.portal.tools.bundle.support/),
which is required for your Maven Workspace to handle @product-ver@ projects. You
can also
[configure workspace properties](#configuring-maven-workspace-properties) in
your POM, which you'll learn about later.

Next, you'll learn how to initialize and package @product@ bundles using
workspace.

## Adding a Liferay Bundle to a Maven Workspace [](id=adding-a-liferay-bundle-to-a-maven-workspace)

Liferay Maven Workspaces can generate and hold a Liferay Server. This lets you
build/test your plugins against a running Liferay instance. Before generating a
Liferay instance, open the `pom.xml` file located in your workspace's root
folder and set the version of the Liferay bundle to generate and install by
setting the download URL for the `liferay.workspace.bundle.url` property. For
example,

    <properties>
        <liferay.workspace.bundle.url>
            http://downloads.sourceforge.net/project/lportal/Liferay%20Portal/7.0.3%20GA4/liferay-ce-portal-tomcat-7.0-ga4-20170613175008905.zip
        </liferay.workspace.bundle.url>
        ...
    </properties>

You can also set location of your Liferay bundle with the
`liferay.workspace.home.dir` property. It's set to `bundles` by default.

Once you've finalized your workspace properties, navigate to your workspace's
root folder and run

    mvn bundle-support:init

This command downloads the version of @product@ you specified in your POM file
and installs your @product@ instance in the `bundles` folder.

**Important:** Make sure the `com.liferay.portal.tools.bundle.support` plugin in
your POM is configured to use version `2.0.3+`. The `bundle-support` prefixed
commands don't work with previous versions of the Bundle Support plugin.

If you run into errors during the bundle downloading process, check to make sure
your workspace is accounting for the
[appropriate certificates](/develop/tutorials/-/knowledge_base/7-0/configuring-a-liferay-workspace#certification-issues-in-liferay-workspace).

If you want to skip the downloading process, you can create the `bundles` folder
manually in your workspace's ROOT folder and extract your Liferay Portal bundle to
that folder.

You can also produce a distributable @product@ bundle (Zip) from within a
workspace. To do this, navigate to your workspace's root folder and run the
following command:

    mvn bundle-support:dist

Your distribution file is available from the workspace's `/target` folder.

## Configuring Maven Workspace Properties [](id=configuring-maven-workspace-properties)

There are many configurable workspace properties you can set in the root
`pom.xml` file:

- `liferay.workspace.bundle.dest`: the destination folder for downloaded
   @product@ bundle ZIP files.
- `liferay.workspace.bundle.url`: the URL used to download the @product@ bundle.
   For more information, see
   [Adding a Liferay Bundle to a Maven Workspace](#adding-a-liferay-bundle-to-a-maven-workspace).
- `liferay.workspace.default.repository.enabled`: whether Liferay CDN is set as
   the default repository in the root project.
- `liferay.workspace.deploy.war.dir`: the deployment folder for WAR projects.
- `liferay.workspace.deploy.modules.dir`: the deployment folder for module
   projects.
- `liferay.workspace.environment`: the name of a `configs` subfolder holding the
   @product@ server configuration to use. See
   [Testing Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#testing-modules)
   for more information.
- `liferay.workspace.home.dir`: the @product@ bundle root folder.
- `liferay.workspace.modules.default.repository.enabled`: whether the Liferay
   CDN is set as the default repository for module projects.
- `liferay.workspace.modules.dir`: the module projects' root folder.
- `liferay.workspace.plugins.sdk.dir`: the converted Plugins SDK's root folder.
   For more information, see
   [Using a Plugins SDK from Your Workspace](/develop/tutorials/-/knowledge_base/7-0/configuring-a-liferay-workspace#using-a-plugins-sdk-from-your-workspace)
- `liferay.workspace.themes.dir`: the theme projects' root folder.
- `liferay.workspace.wars.dir`: the WAR projects' root folder.

Properties can be set by adding tags with the property name. See the property
configurations below for an example on how these can be set in your POM: 

    <properties>
        <liferay.workspace.home.dir>${liferay.workspace.basedir}/bundles</liferay.workspace.home.dir>
        <liferay.workspace.bundle.dest>${user.home}/.liferay/bundles/liferay-ce-portal-tomcat-7.0-ga3-20160804222206210.zip</liferay.workspace.bundle.dest>
        <liferay.workspace.bundle.url>http://downloads.sourceforge.net/project/lportal/Liferay%20Portal/7.0.2%20GA3/liferay-ce-portal-tomcat-7.0-ga3-20160804222206210.zip</liferay.workspace.bundle.url>
        <liferay.workspace.deploy.war.dir>${liferay.workspace.home.dir}/osgi/war</liferay.workspace.deploy.war.dir>
        <liferay.workspace.deploy.modules.dir>${liferay.workspace.home.dir}/osgi/modules</liferay.workspace.deploy.modules.dir>
        <liferay.workspace.environment>local</liferay.workspace.environment>
    </properties>

Next, you'll learn how to add and deploy modules/projects in your Maven
Workspace.

## Module Management [](id=module-management)

Maven Workspace makes managing your Maven project easier than ever. To create
a project, navigate to the appropriate workspace folder for that type of project
(e.g., `modules`, `wars`, etc.). Then generate the project archetype. You can
view a full listing of the available archetypes in the
[Project Templates](/develop/reference/-/knowledge_base/7-0/project-templates)
reference section. Once the project is generated, it can leverage all of Maven
Workspace's functionality.

Maven Workspace also lets you deploy your projects to @product@ using Maven. See
the
[Deploying a Module Built with Maven to @product@](/develop/tutorials/-/knowledge_base/7-0/deploying-a-module-built-with-maven-to-liferay-portal)
tutorial for more information.

Want to leverage Maven Workspace's testing infrastructure so you can simulate
your Maven projects in a specific environment? See the
[Testing Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#testing-modules)
section for more information.

Once you have your Maven projects solidified and ready for the limelight, it'd
be great to release your projects to the public. Maven Workspace doesn't provide
this functionality, but there are easy ways to use external release tools with
workspace. See the
[Releasing Modules](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#releasing-modules)
section for more information.

Next, you'll learn how to update a Maven Workspace.

## Updating a Maven Workspace [](id=updating-a-maven-workspace)

Liferay Workspace is updated periodically with new features, so you'll want to
update your workspace instance accordingly. To update your Maven Workspace, you
must update the Bundle Support plugin configured in your workspace's root
`pom.xml` file:

    <plugin>
        <groupId>com.liferay</groupId>
        <artifactId>com.liferay.portal.tools.bundle.support</artifactId>
        <version>3.0.1</version>
        ...
    </plugin>

Update the version to the latest available release. You can reference the
available releases for the Bundle Support plugin
[here](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.tools.bundle.support/).
