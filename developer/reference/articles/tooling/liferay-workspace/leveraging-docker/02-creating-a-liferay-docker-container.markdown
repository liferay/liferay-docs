---
header-id: creating-a-product-docker-container
---

# Creating a @product@ Docker Container

[TOC levels=1-4]

To create a @product@ Docker container in Liferay Workspace, complete the steps
below.

1.  Choose the Docker image you need. This is configured in your workspace's
    `gradle.properties` file by customizing this property: 

    ```properties
    liferay.workspace.docker.image.liferay
    ```

    To find the possible property values you can set, see the official @product@
    Docker Hub's Tags section (e.g.,
    [Liferay Portal Docker Tags](https://hub.docker.com/r/liferay/portal/tags)).
    For example, if you want to base your container on the Liferay Portal 7.2
    GA1 image, you would set this property:

    ```properties
    liferay.workspace.docker.image.liferay=liferay/portal:7.2.0-ga1
    ```

2.  Run the following command from your workspace's root folder:

    ```bash
    ./gradlew createDockerContainer
    ```

This command creates a new container named `[projectName]-liferayapp`. A new
`build/docker` folder is generated in your workspace. This folder is mounted
into the container's file system. This means files in workspace's `build/docker`
folder are also available in the container's `/etc/liferay` folder.

Any projects in your workspace are automatically compiled and copied to the
`build/docker/deploy` folder when the container is created; this means that when
the container is started, all your projects are deployed to the container. All
configurations are also applied to the container.

| **Note:** During your container's startup, you may run into the following
| error:
| 
| ```bash
| /etc/liferay/entrypoint.sh: line 3:    11 Killed
| ${LIFERAY_HOME}/tomcat/bin/catalina.sh run
| ```
| 
| This usually means you have not allocated enough memory to your Docker engine
| to successfully run your container. See Docker's
| [documentation](https://docs.docker.com) to learn how to increase resources
| available to Docker.

Once your container is created, you can
[configure it](/docs/reference/7-2/-/knowledge_base/reference/configuring-a-docker-container).
