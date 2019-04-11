# Setting Environment Configurations for Liferay Workspace

Liferay Workspace offers the `configs` folder, which provides a way to
organize multiple environment settings and generate a Liferay bundle for each
environment configuration.

To simulate using the `configs` folder, you'll explore a typical scenario.
Suppose you want a local @product@ installation for testing and a UAT
installation for simulating a production site. Assume you want the following
configuration for the two environments:

**Local Environment**

- Use MySQL database pointing to localhost
- Skip setup wizard

**UAT Environment**

- Use MySQL database pointing to a live server
- Skip setup wizard

To configure these two environments in your workspace, follow the steps below:

1.  Open the `configs/common` folder and add the
    `portal-setup-wizard.properties` file with the `setup.wizard.enabled=false`
    property.

2.  Open the `configs/local` folder and configure the MySQL database settings
    for localhost in a `portal-ext.properties` file.

3.  Open the `configs/uat` folder and configure the MySQL database settings for
    the live server in a `portal-ext.properties` file.

4.  Now that your two environments are configured, generate one of them:

    ```bash
    blade server init --environment uat
    ```

5.  To generate a distributable @product@ installation of the environment to the
    workspace's `/build` folder, run

    ```bash
    ./gradlew distBundle[Zip|Tar] -Pliferay.workspace.environment=uat
    ```

    | **Note:** You may prefer to set your workspace environment in the
    | `gradle.properties` file instead of passing it via Gradle command. If so,
    | it's recommended to set the workspace environment variable inside the
    | `[USER_HOME]/.gradle/gradle.properties` file.
    | 
    | ```properties
    | liferay.workspace.environment=local
    | ```
    | 
    | The variable is set to `local` by default.

    You've successfully configured two environments and generated one of them.

Awesome! You can now test various @product@ bundle environments using Liferay
Workspace.
