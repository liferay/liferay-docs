# Setting the Target Platform

Setting the version to develop for takes two steps: 

1.  Open the workspace's `gradle.properties` file and set the
    `liferay.workspace.target.platform.version` property to the version you want
    to target. For example,

    ```properties
    liferay.workspace.target.platform.version=7.1.1
    ```

    If you're using Liferay DXP, you can set the property like this:

    ```properties
    liferay.workspace.target.platform.version=7.1.10
    ```

    The versions following a GA1 release of DXP follow fix pack versions (e.g.,
    `7.1.10.fp1`, `7.1.10.fp2`, etc.).

2.  Once the target platform is configured, check to make sure no dependencies
    in your Gradle build files specify a version. The versions are now imported
    from the configured target platform's BOM. For example, a simple MVC
    portlet's `build.gradle` may look something like this:

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
        compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
        compileOnly group: "javax.portlet", name: "portlet-api"
        compileOnly group: "javax.servlet", name: "javax.servlet-api"
        compileOnly group: "jstl", name: "jstl"
        compileOnly group: "org.osgi", name: "osgi.cmpn"
    }
    ```

| **Note**: The `liferay.workspace.target.platform.version` property also sets
| the distro JAR, which can be used to validate your projects during the build
| process. See the
| [Validating Modules Against the Target Platform](/developer/reference/-/knowledge_base/7-2/validating-modules-against-the-target-platform)
| tutorials for more info.

| **Note:** The target platform functionality is available in Liferay Workspace
| version 1.9.0+. If you have an older version, you must update it to leverage
| platform targeting. See the
| [Updating Liferay Workspace](/developer/reference/-/knowledge_base/7-2/updating-liferay-workspace)
| article to do this.

You've configured your target platform in workspace. You're all set!
