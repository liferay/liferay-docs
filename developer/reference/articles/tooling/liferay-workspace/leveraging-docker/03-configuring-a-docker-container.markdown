# Configuring a Docker Container

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

As an example, you'll enable the
[Gogo shell](/docs/reference/7-2/-/knowledge_base/reference/using-the-felix-gogo-shell)
for your container.

1.  Add a `portal-ext.properties` file to your workspace's `configs/docker`
    folder.

2.  Add the following property to the `portal-ext.properties` file:

    ```properties
    module.framework.properties.osgi.console=0.0.0.0:11311
    ```

    This lets you access your container using Gogo shell via telnet session.

3.  Start the container.

    Once the container is started, the configurations stored in `configs/docker`
    are transferred to the `build/docker/files` folder, which applies all
    configurations to the container's file system.

    | **Note:** You can call the `deployDocker` Gradle task from your
    | workspace's root folder to initiate the Docker configuration transfer to
    | the `build/docker/files` folder manually. It's executed automatically when
    | creating or starting the container.

You can now apply configurations to your @product@ Docker container.
