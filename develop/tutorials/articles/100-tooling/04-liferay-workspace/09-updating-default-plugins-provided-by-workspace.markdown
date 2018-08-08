# Updating Default Plugins Provided by Liferay Workspace

Liferay Workspace is bundled with a slew of plugins that provide many features.
For example,

- [CSS Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/css-builder)
- [Javadoc Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/javadoc-formatter)
- [Lang Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/lang-builder)
- [Service Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-service-builder)
- [Source Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/source-formatter)
- [Theme Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-theme-builder)
- etc.

The bundled plugins are updated with each release of workspace, but what if you
want workspace to use a different version of a bundled plugin? For example,
suppose a new feature is developed for the
[Source Formatter plugin](https://github.com/liferay/liferay-portal/tree/master/modules/util/source-formatter),
but the latest workspace version has not yet been updated to include it.

To upgrade one of workspace's bundled plugins, follow these steps:

1.  Find the bundle symbolic name (BSN) for the bundled plugin you want to
    update. You can find this value in the plugin's `bnd.bnd` set by the
    `Bundle-SymbolicName` header. For example,

        Bundle-SymbolicName: com.liferay.source.formatter

    Most of Liferay tooling plugins (and corresponding `bnd.bnd` files) can be
    found in the `liferay-portal` repository's
    [`modules/util`](https://github.com/liferay/liferay-portal/tree/master/modules/util)
    folder.

2.  Open your workspace's `build.gradle` file and copy the plugin's BSN followed
    by `.version` and set the desired plugin version you want to use. For
    example,

        com.liferay.source.formatter.version=1.0.654

    If you're most interested in the latest and greatest plugins, you can set
    the above property to `latest.release` to always use the latest available
    version. This could, however, cause your workspace to become unstable.

That's it! You're no longer tied to particular plugin versions provided by your
workspace.

## Related Topics

[Updating Default Plugins for a Project Created Outside of Workspace](/develop/reference/-/knowledge_base/7-1/#updating-default-plugins-for-a-project-created-outside-of-workspace)

