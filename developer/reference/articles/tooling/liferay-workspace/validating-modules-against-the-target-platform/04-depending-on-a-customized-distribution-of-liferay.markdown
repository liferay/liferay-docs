---
header-id: depending-on-a-customized-distribution-of-product
---

# Depending on a Customized Distribution of @product@

[TOC levels=1-4]

To regenerate the target platform's capabilities (distro JAR) based on the
current workspace's @product@ instance, follow the steps below:

1.  Start the @product@ instance stored in your workspace. Make sure the
    platform you want to depend on is installed.

2.  Download the
    [BND Remote Agent JAR file](https://search.maven.org/#search%7Cga%7C1%7Cbiz.aqute.remote.agent)
    and copy it into the `osgi/modules` folder.

3.  From the root folder of your workspace, run the following command:

    ```bash
    bnd remote distro -o custom_distro.jar release.portal.distro 7.2.0
    ```

    Liferay DXP users must replace the `release.portal.distro` artifact name
    with `release.dxp.distro` and use the `7.1.10` version syntax.

    This connects to the newly deployed BND agent running in @product@ and
    generates a new distro JAR named `custom_distro.jar`. All other capabilities
    inherit their functionality based on your @product@ instance, so verify the
    workspace bundle is the version you plan to release in production.

4.  Navigate to your workspace's root `build.gradle` file and add the following
    dependency:

    ```groovy
    dependencies {
        targetPlatformDistro files('custom_distro.jar')
    }
    ```

Now your workspace is pointing to a custom distro JAR file instead of the
default one provided. Run the `resolve` task to validate your modules against
the new set of capabilities.
