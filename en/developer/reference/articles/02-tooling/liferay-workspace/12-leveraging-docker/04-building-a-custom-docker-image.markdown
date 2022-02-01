---
header-id: building-a-custom-docker-image
---

# Building a Custom Docker Image

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="
https://learn.liferay.com/dxp/latest/en/building-applications/tooling/liferay-workspace/configuring-a-liferay-docker-container.html
">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

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
