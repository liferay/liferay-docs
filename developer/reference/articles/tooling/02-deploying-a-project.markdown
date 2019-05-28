---
header-id: deploying-a-project
---

# Deploying a Project

[TOC levels=1-4]

Deploying a project to @product@ can be completed using your tool of choice. The
following tools are preconfigured (or can be easily configured) for Liferay
project generation:

- [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli)
- [Gradle](https://gradle.org/)
- [Liferay Dev Studio](/docs/7-2/reference/-/knowledge_base/r/liferay-dev-studio)
- [Liferay IntelliJ Plugin](/docs/7-2/reference/-/knowledge_base/r/intellij)
- [Maven](/docs/7-2/reference/-/knowledge_base/r/maven)

The deployment process is the same across all tools; the deployment
command/action builds and copies your project to the @product@ `/deploy` folder,
which is found by reading the Liferay Home folder. The Liferay Home folder is
preconfigured in most cases; if it's not, ways to configure it are included
below. All tools support JAR and WAR-style project deployment.

It's recommended to deploy Liferay projects within a
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace).
Most tools, however, support deploying projects in a standalone environment
(except for IntelliJ). Visit the appropriate section to learn how to deploy a
project with the highlighted tool.

## Blade CLI

This is the recommended way to deploy Gradle and Maven projects in a Liferay
Workspace via command line. Blade CLI is leveraged by Dev Studio and IntelliJ
too.

1.  Run this command to deploy your project:

        blade deploy

## Gradle

Deploying with pure Gradle is not recommended unless you prefer to develop
outside of a Liferay Workspace. [Blade CLI](#blade-cli) is a better tool for
deploying Liferay Gradle projects in most cases.

1.  Apply the Liferay Gradle plugin in your project's `build.gradle` file:

        apply plugin: "com.liferay.plugin"

2.  Extend the Liferay extension object to set your Liferay Home and `deploy`
    folder:

        liferay {
            liferayHome = "../../../../liferay-ce-portal-7.1.1-ga2"
            deployDir = file("${liferayHome}/deploy")
        }

3.  Run this command to deploy your project:

        ./gradlew deploy

## Liferay Dev Studio

These steps assume you've
[installed a Liferay server in Dev Studio](/docs/7-2/reference/-/knowledge_base/r/installing-a-liferay-server-in-dev-studio). 

1.  Right-click the server from the Servers window and select *Add and
    Remove...*.

2.  Add the project(s) you'd like to deploy from the Available window to the
    Configured window. Then click *Finish*.

    ![Figure 1: Using the this deployment method is convenient when deploying multiple projects.](../../images/add-and-remove-ide.png)

3.  Verify your project builds, deploys, and starts successfully by viewing the
    results in the Console window.

## Liferay IntelliJ Plugin

These steps assume you've
[installed a Liferay server in IntelliJ](/docs/7-2/reference/-/knowledge_base/r/installing-a-server-in-intellij).
A configured Liferay Workspace is required to create and deploy Liferay projects
in IntelliJ.

1.  Right-click your project from within the Liferay Workspace folder structure
    and select *Liferay* &rarr; *Deploy*.

    This automatically loads a build progress window viewable at the bottom of
    your IntelliJ instance.

    ![Figure 2: Verify that your project built successfully.](../../images/intellij-project-build.png)

2.  Verify that your project builds successfully from the build progress window.
    Then navigate back to your server's window and confirm it starts in your
    configured @product@ instance.

## Maven

If you're developing your project in a Liferay Workspace, skip to step 3.

1.  Add the following plugin configuration to your Liferay Maven project's
    parent `pom.xml` file.

        <build>
            <plugins>
                <plugin>
                    <groupId>com.liferay</groupId>
                    <artifactId>com.liferay.portal.tools.bundle.support</artifactId>
                    <version>3.4.1</version>
                    <executions>
                        <execution>
                            <id>deploy</id>
                            <goals>
                                <goal>deploy</goal>
                            </goals>
                            <phase>pre-integration-test</phase>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>

    This POM configuration applies Liferay's
    [Bundle Support plugin](/docs/7-2/reference/-/knowledge_base/r/bundle-support-plugin).
    This plugin is applied in Liferay Workspace by default. The Bundle Support
    configuration defines the
    [executions](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag)
    tag, which configures the Bundle Support plugin to run during the
    `pre-integration-test` phase of your Maven project's build lifecycle. The
    [`deploy`](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)
    goal is defined for that lifecycle phase. 

2.  Define your Liferay home folder in your POM. You can do this by adding the
    following logic within the `plugin` tags, but outside of the `execution`
    tags:

        <configuration>
            <liferayHome>C:/liferay/liferay-ce-portal-7.1-ga1</liferayHome>
        </configuration>

3.  Run this command to deploy your project:

        mvn verify
