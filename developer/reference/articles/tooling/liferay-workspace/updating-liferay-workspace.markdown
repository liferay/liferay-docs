# Updating Liferay Workspace

Liferay Workspace is continuously being updated with new features. If you
created your workspace a while ago, you may be missing out on some of the latest
features that could improve your Liferay development experience. Updating your
Liferay Workspace is easy; you'll learn how to do it for Gradle and Maven based
workspaces next.

## Gradle

1.  Find the latest Liferay Workspace version. To do this, view the Workspace
    Gradle plugin's
    [released versions](https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.gradle.plugins.workspace/)
    on Liferay's repository. Copy the version to which you want to upgrade.

2.  Open your Liferay Workspace's `settings.gradle` file. This file resides in
    your Workspace's root folder.

3.  In the `dependencies` block, you'll find code similar to below:

    ```groovy
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "[WORKSPACE_VERSION]"
    }
    ```

    Update the `com.liferay.gradle.plugins.workspace` dependency's `version` to
    the version number you copied from the change log in step 1.

4.  Execute any Gradle command to initiate the update process for your Workspace
    (e.g., `blade gw tasks`).

| **Note:** The Gradle wrapper provided in a Gradle-based Liferay Workspace must
| be updated if you're migrating from a workspace before version `1.10.14` to
| the latest available version. To update your Gradle wrapper, run
| 
|     ./gradlew wrapper --gradle-version=4.10.2

Awesome! You've upgraded your Gradle based Liferay Workspace!

## Maven

1.  Find the latest Liferay Workspace version. To do this, view the Bundle
    Support plugin's
    [released versions](https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.tools.bundle.support/)
    on Liferay's repository. Copy the version to which you want to upgrade.

2.  Open your Liferay Workspace's root `pom.xml` file.

3.  Within the `plugin` tags, you'll find code similar to below:

    ```xml
    <plugin>
        <groupId>com.liferay</groupId>
        <artifactId>com.liferay.portal.tools.bundle.support</artifactId>
        <version>3.4.2</version>
        ...
    </plugin>
    ```

    Update the `com.liferay.portal.tools.bundle.support` artifact's `version` to
    the version number you copied from the change log in step 1.

4.  Execute any Maven command to initiate the update process for your Workspace
    (e.g., `mvn verify`).

Awesome! You've upgraded your Maven based Liferay Workspace!
