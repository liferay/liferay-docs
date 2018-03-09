# Validating Modules Outside of Workspace

If you prefer to not use Liferay Workspace, but still want to validate modules
against a target platform, you must apply the
[Target Platform](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-target-platform)
Gradle plugin to the root `build.gradle` file of your custom multi-module Gradle
build. Follow the
[Targeting a Platform Outside of Workspace](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace#targeting-a-platform-outside-of-workspace)
section to do this.

Once you have the Target Platform plugin and its BOM dependencies configured,
you must configure the `targetPlatformDistro` dependency. Open your project's
root `build.gradle` file and add it to the list of dependencies. It should look
like this:

    dependencies {
        targetPlatformBoms group: "com.liferay", name: "com.liferay.ce.portal.bom", version: "7.1.0"
        targetPlatformBoms group: "com.liferay", name: "com.liferay.ce.portal.compile.only", version: "7.1.0"
        targetPlatformDistro group: "com.liferay", name "com.liferay.ce.portal.distro", version: "7.1.0"
    }

<!-- TODO: Update above versions to accurate milestone version syntax. This was
a shot in the dark. -Cody -->

Now you can validate your modules against a target platform!