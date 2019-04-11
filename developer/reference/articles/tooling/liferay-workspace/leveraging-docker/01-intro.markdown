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

The following Docker commands (Gradle-based) are available in Liferay Workspace:

Command | Description
------- | -------------
`buildDockerImage` | Builds the Docker image with all modules/configs deployed.
`createDockerContainer` | Creates a Docker container from the @product@ image and mounts the workspace's `/build/docker` folder to the container's `/etc/liferay` folder.
`createDockerfile` | Creates a `Dockerfile` to build the Docker image.
`dockerDeploy` | Deploys the project to the container's `deploy` folder by copying the project archive file to workspace's `build/docker/deploy` folder. This command can also be executed from workspace's root folder to deploy all projects and copy all Docker configurations (i.e., from the `configs/docker` folder) to the container.
`logsDockerContainer` | Prints the portal runtime's logs. You can exit log tracking mode while maintaining a running container (e.g., [Ctrl|Command] + C).
`pullDockerImage` | Pulls the Docker image.
`removeDockerContainer` | Removes the container from Docker's system.
`startDockerContainer` | Starts the Docker container.
`stopDockerContainer` | Stops the Docker container.

| **Note:** Leveraging Docker in Liferay Workspace is only available for Gradle
| projects at this time.

In this section, you'll learn how to

- [Create a Docker container based on a provided @product@ image](/docs/reference/7-2/-/knowledge_base/reference/creating-a-product-docker-container)
- [Configure the container](/docs/reference/7-2/-/knowledge_base/reference/configuring-a-docker-container)
- [Build a custom image](/docs/reference/7-2/-/knowledge_base/reference/building-a-custom-product-image)

Continue on to learn more.
