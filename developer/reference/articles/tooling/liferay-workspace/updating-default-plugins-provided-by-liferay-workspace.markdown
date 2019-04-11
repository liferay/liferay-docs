# Updating Default Plugins Provided by Liferay Workspace

Liferay Workspace comes with a slew of plugins like these: 

- [CSS Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/css-builder)
- [Javadoc Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/javadoc-formatter)
- [Lang Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/lang-builder)
- [Service Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-service-builder)
- [Source Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/source-formatter)
- [Theme Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-theme-builder)
- etc.

Bundled plugins are updated with each release of workspace. Suppose you need
a new feature in the 
[Source Formatter plugin](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.source.formatter/),
but the latest workspace version has not yet been updated to include it. You can
upgrade it yourself!

To upgrade one of workspace's bundled plugins, follow these steps:

1.  Find the bundle symbolic name (BSN) for the plugin you want to update. You
    can find this value in the
    [`portal-tools.properties`](https://github.com/liferay/liferay-portal/blob/master/modules/sdk/gradle-plugins/src/main/resources/com/liferay/gradle/plugins/dependencies/portal-tools.properties)
    file. For example, the Source Formatter's BSN is
    `com.liferay.source.formatter`.

2.  Open your workspace's `build.gradle` file and copy the plugin's BSN followed
    by `.version` and set the desired plugin version you want to use. For
    example,

    ```groovy
    com.liferay.source.formatter.version=1.0.819
    ```

    If you're most interested in the latest and greatest plugins, you can set
    the above property to `latest.release` to always use the latest available
    version. This could, however, cause your workspace to become unstable.

That's it! You're no longer tied to particular plugin versions provided by your
workspace.
