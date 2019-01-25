# Leveraging Docker

Docker has become increasingly popular in today's development lifecycle, by
providing an automated way to package software and its dependencies into
a standardized unit that can be shared cross-platform. Read Docker's extensive
[documentation](https://docs.docker.com/) to learn more.

Liferay provides Docker images for

- [Liferay Portal](https://hub.docker.com/r/liferay/portal)
- [Liferay DXP](https://hub.docker.com/r/liferay/dxp)
- [Liferay Commerce](https://hub.docker.com/r/liferay/commerce)
- [Liferay Portal Snapshots](https://hub.docker.com/r/liferay/portal-snapshot)

You can pull Liferay's Docker images from those resources and manage them
yourself. Liferay Workspace, however, provides an easy way to integrate Docker
development into your existing development workflow with preconfigured Gradle
tasks.

+$$$

**Note:** Leveraging Docker in Liferay Workspace is only available for Gradle
projects at this time.

$$$

In this tutorial, you'll learn how to do the following tasks within a workspace:

- [Creating a Docker container based on a provided @product@ image](#creating-a-docker-container-based-on-a-provided-product-image)
- [Configuring the container](#configuring-the-container)
- [Interacting with the container](#interacting-with-the-container)
- [Building a custom @product@ image](#building-a-custom-product-image)

## Creating a @product@ Docker Container

1.  Choose the Docker image you need. This is configured in your workspace's
    `gradle.properties` file by customizing this property: 

        liferay.workspace.docker.image.liferay

    To find the possible property values you can set, see the official @product@
    Docker Hub's Tags section (e.g.,
    [Liferay Portal Docker Tags](https://hub.docker.com/r/liferay/portal/tags).
    For example, if you want to base your container on the Liferay Portal 7.1
    GA2 image, you would set this property:

        liferay.workspace.docker.image.liferay=liferay/portal:7.1.1-ga2

2.  Run the following command from your workspace's root folder:

        ./gradlew createDockerContainer

This command creates a new container named `[projectName]-liferayapp`. A new
`build/docker` folder is generated in your workspace. This folder is mounted
into the container's file system. This means files in workspace's `build/docker`
folder are also available in the container's `/etc/liferay` folder.

Any projects in your workspace are automatically compiled and copied to the
`build/docker/deploy` folder when the container is created; this means that when
the container is started, all your projects are deployed to the container. All
configurations are also applied to the container. You'll learn more about
configuring your container next.

## Configuring the Container

Before starting your container, you may want to add additional portal
configurations. This could include things like

- Property overrides (e.g., `portal-ext.properties`)
- Marketplace app overrides
- App server configurations
- License files

You can do this by applying files (and their accompanying folder structures, if
necessary) to your workspace's `configs/docker` folder. This folder is treated
as your Liferay Home for Docker development; you add additional files that
overlay your @product@ container's default configuration.

For example, to enable the Gogo shell for your container, add
a `configs/docker/portal-ext.properties` file to your workspace with the
following configuration:

    module.framework.properties.osgi.console=0.0.0.0:11311

This lets you access your container using
[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
via telnet session.

Once the container is started, the configurations stored in `configs/docker` are
transferred to the `build/docker/files` folder, which applies all configurations
to the container's file system.

+$$$

**Note:** You can call the `deployDocker` Gradle task from your workspace's
root folder to initiate the Docker configuration transfer to the
`build/docker/files` folder manually. It's executed automatically when creating
or starting the container.

$$$

Next, you'll explore the commands for interacting with the container.

##  Interacting with the Container

`startDockerContainer`: starts the container.

`logsDockerContainer`: prints the portal runtime's logs. You can exit log
tracking mode while maintaining a running container (e.g., [Ctrl|Command] +
C).

`dockerDeploy`: deploys the project to the container's `deploy` folder by
copying the project archive file to workspace's `build/docker/deploy` folder.
This command can also be executed from workspace's root folder to deploy all
projects and copy all Docker configurations (i.e., from the `configs/docker`
folder) to the container.

`stopDockerContainer`: stops the container.

`removeDockerContainer`: removes the container from Docker's system.

+$$$

**Note:** During your container's startup, you may run into the following error:

    /etc/liferay/entrypoint.sh: line 3:    11 Killed
    ${LIFERAY_HOME}/tomcat/bin/catalina.sh run

This usually means you have not allocated enough memory to your Docker engine to
successfully run your container. See Docker's
[documentation](https://docs.docker.com) to learn how to increase resources
available to Docker.

$$$

Next, you'll learn how to build a custom image.

## Building a Custom @product@ Image

You can preserve your container's configuration by building it as an image. To
build your custom @product@ image, run

    ./gradlew buildDockerImage

A `Dockerfile` is generated for your container when building your image. To do
this manually, run

    ./gradlew createDockerfile

The `Dockerfile` is generated in your workspace's `build/docker` folder. For
more information on how to configure the `Dockerfile`, see Docker's
[Dockerfile reference documentation](https://docs.docker.com/engine/reference/builder/).

Your custom image is now available! Run `docker image ls` to verify its
availability.

You can now manage Liferay's Docker images in Liferay Workspace!
