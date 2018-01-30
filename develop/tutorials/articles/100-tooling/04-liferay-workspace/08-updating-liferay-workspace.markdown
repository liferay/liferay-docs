# Updating Liferay Workspace [](id=updating-liferay-workspace)

Liferay Workspace is continuously being updated with new features. If you
created your workspace a while ago, you may be missing out on some of the latest
features that could improve your Liferay development experience. Updating your
Liferay Workspace is easy; you'll learn how to do it next.

1.  Find the latest Liferay Workspace version. To do this, open the
    [Liferay Gradle Plugins Workspace Change Log](https://github.com/liferay/liferay-portal/blob/master/modules/sdk/gradle-plugins-workspace/CHANGELOG.markdown)
    and copy the version to which you want to upgrade. You can find the updates
    and new features associated with each version by browsing the change log
    too.

2.  Open your Liferay Workspace's `settings.gradle` file. This file resides in
    your Workspace's root folder.

3.  In the `dependencies` block, you'll find code similar to below:

        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "[WORKSPACE_VERSION]"
        }

    Update the `com.liferay.gradle.plugins.workspace` dependency's `version` to
    the version number you copied from the change log in step 1.

4.  Execute any Gradle command to initiate the update process for your Workspace
    (e.g., `blade gw tasks`).

Awesome! You learned where to check for Liferay Workspace's latest version, how
to update your Workspace to that version, and how to initiate the update
process.
