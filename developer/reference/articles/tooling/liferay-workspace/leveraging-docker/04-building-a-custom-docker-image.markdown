# Building a Custom Docker Image

You can preserve your container's configuration by building it as an image.

1.  Build your custom @product@ image by running

    ```bash
    ./gradlew buildDockerImage
    ```

    A `Dockerfile` is generated for your container when building your image. The
    `Dockerfile` is generated in your workspace's `build/docker` folder. For
    more information on how to configure the `Dockerfile`, see Docker's
    [Dockerfile reference documentation](https://docs.docker.com/engine/reference/builder/).

    You can generate a `Dockerfile` manually at any time by running

    ```bash
    ./gradlew createDockerfile
    ```

2.  Run `docker image ls` to verify the image's availability.

You can now build @product@ Docker images in Liferay Workspace!