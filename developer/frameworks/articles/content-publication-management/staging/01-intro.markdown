---
header-id: staging
---

# Staging

[TOC levels=1-4]

Staging lets you change your Site behind the scenes without affecting the live
Site, and then you can publish all the changes in one fell swoop. If you include
staging support in your application, your users can stage its content until it's
ready.

For example, if you have an application that provides information intended only
during a specific holiday, supporting the Staging environment lets users save
your application's assets specific for that holiday. They'll reside
in the Staging environment until they're ready for publishing. To learn more
about Staging, visit the
[Staging Content for Publication](/user/-/knowledge_base/7-2/staging-content-for-publication)
section.

Staging and Export/Import share the same base framework. When publishing your
staged content to the live Site, you're essentially importing content from the
staged Site and exporting it to the live Site. This means that implementing
Staging in your app is *almost* the same as implementing the Export/Import
framework. You can visit the
[Export/Import](/developer/frameworks/-/knowledge_base/7-2/export-import)
framework's articles for the base APIs that both it and the Staging frameworks
share.

If your app supports Export/Import, it's entities
([staged models](/developer/frameworks/-/knowledge_base/7-2/developing-staged-models))
are automatically tracked by Staging with the use of
[data handlers](/developer/frameworks/-/knowledge_base/7-2/developing-data-handlers).
There are some Staging-specific configurations you can add that are not shared
by Export/Import. Some Staging-specific actions you can complete include

- Control Staging UI settings
- Filter Staging-specific processes
- Check for Staging-specific states

You'll learn about these next.

## Controlling Staging's UI Settings

You can control most of Staging's UI from your portlet data handler. This can be
done several ways; first, you can configure predefined setter methods in the
portlet data handler's `activate()` method:

- `setStagingControls`: adds fine grained controls over staging behavior that is
  rendered in the Staging UI. For example, this enables your app's checkboxes in
  the Content section of the Publication screen. This is usually set like this:
  `setStagingControls(getExportControls());`. The staging UI typically provides
  the same content as the export UI (i.e., the Content section for selecting
  what to publish/export), so it leverages its UI. You can set the Staging UI
  differently by configuring the `setStagingControls` method differently. See
  the
  [AssetTagsPortletDataHandler](https://github.com/liferay/liferay-portal/blob/7.2.0-m2/modules/apps/asset/asset-tags-service/src/main/java/com/liferay/asset/tags/internal/exportimport/data/handler/AssetTagsPortletDataHandler.java#L82-L84)
  class for an example of not copying the Export UI for the Staging UI.
- `setDataAlwaysStaged`: defines whether you can enable/disable your app's
  content staging (i.e., selectable from the Publication screen). For example,
  setting this method to `true` automatically stages your app's content,
  removing the ability to choose whether its content should be staged.

Other setter methods are available that control both Export/Import and Staging
settings. You can reference them by visiting the
[Understanding the `PortletDataHandler` Inteface](/developer/frameworks/-/knowledge_base/7-2/developing-data-handlers#understanding-the-portlet-data-handler-interface)
section.

You can also control whether your app is selectable to enable for Staging by
adding this method to your portlet data handler:

```java
@Override
public boolean isConfigurationEnabled() {
    return false;
}
```

When this is set to `false`, your app is disabled on the Staged Content screen.
This is set to `true` by default.

![Figure 1: There are many apps available to select from the Staged Content screen.](../../../images/staged-content-screen.png)

The majority of Staging-specific configurations are completed in a portlet data
handler. The staged model data handler does come into play when you want to
filter for certain staging processes/states. You'll learn about this next.

## Filtering Staging-Specific Processes and States

You can filter for certain staging-specific processes/states and complete
actions based on the returned status. You can do this by leveraging the
following from a staged model data handler:

- [`ExportImportThreadLocal`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/ExportImportThreadLocal.html)
- [`StagingGroupHelper`](@app-ref@/staging/latest/javadocs/com/liferay/staging/StagingGroupHelper.html)

The `ExportImportThreadLocal` class provides boolean methods that return whether
a specific process is in progress. This is intended to check for events
affecting the entire site. For example, you can check if the following are in
progress:

- Local Staging
- Remote Staging
- Layout Validation
- Portlet Staging
- etc.

The `StagingGroupHelper` interface provides utility methods that return the
staging state in your app. This is intended to check for events only affecting
your app. For example, you can check if your app is in these states:

- Resides in Local Staging group
- Resides in Remote Live group
- Is a staged portlet
- etc.

A real example filtering for a staging process and state can be found in the
[`AssetListEntryStagedModelDataHandler`](https://github.com/liferay/liferay-portal/blob/7.2.0-m2/modules/apps/asset/asset-list-service/src/main/java/com/liferay/asset/list/internal/exportimport/data/handler/AssetListEntryStagedModelDataHandler.java#L215-L222):

```java
if ((assetRendererFactory != null) &&
    ExportImportThreadLocal.isStagingInProcess() &&
    !_stagingGroupHelper.isStagedPortlet(
        assetEntry.getGroupId(),
        assetRendererFactory.getPortletId())) {

    continue;
}
```

The staged model data handler uses the
`ExportImportThreadLocal.isStagingInProcess()` method to verify that a staging
process is running. It also checks whether the app is staged by executing
`!_stagingGroupHelper.isStagedPortlet(...)`.

Excellent! You can now filter for staging-specific processes and states.
