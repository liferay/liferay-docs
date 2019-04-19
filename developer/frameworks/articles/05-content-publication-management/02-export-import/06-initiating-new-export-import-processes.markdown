---
header-id: initiating-new-export-import-processes
---

# Initiating New Export/Import Processes

[TOC levels=1-4]

In this tutorial, you'll learn about the `ExportImportConfiguration` framework
and how you can take advantage of provided services and factories to create
these controller objects. Once they're created, you can easily implement
whatever import/export functionality you need.

Your first step is to create an `ExportImportConfiguration` object and use it to
initiate your custom export/import or staging process.

1.  Use the
    [`ExportImportConfigurationSettingsMapFactory`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationSettingsMapFactory.html)
    class to create a layout export settings map:

    ```java
    Map<String, Serializable> exportLayoutSettingsMap =
        ExportImportConfigurationSettingsMapFactory.
            buildExportLayoutSettingsMap(...);
    ```

2.  Create the `ExportImportConfiguration` object by using an *add* method in
    the entity's local service. The map created previously is used as a
    parameter to create the `ExportImportConfiguration` object.

    ```java
    ExportImportConfiguration exportImportConfiguration =
        exportImportConfigurationLocalService.
            addDraftExportImportConfiguration(
                user.getUserId(),
                ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT,
                exportLayoutSettingsMap);
    ```

    The
    [ExportImportConfigurationLocalService](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportConfigurationLocalService.html)
    provides several useful methods to create and modify your custom
    `ExportImportConfiguration`.

3.  Call the appropriate service using your newly created
    `ExportImportConfiguration` object to initiate an export/import or staging
    process. For example,

    ```java
    files[0] = exportImportLocalService.exportLayoutsAsFile(
        exportImportConfiguration);
    ```

    Notice that your `ExportImportConfiguration` object is the only needed
    parameter in the method. Your configuration object holds all the required
    parameters and settings necessary to export your layouts from @product@.

It's that easy! To start your own export/import or staging process, you must
create an `ExportImportConfiguration` object using a combination of the three
provided `ExportImportConfiguration` factories (outlined
[here](/docs/7-2/frameworks/-/knowledge_base/frameworks/export-import#export-import-processes)).
Once you have your configuration object, provide it as a parameter in one of the
many service methods available to you by the Export/Import or Staging interfaces
to begin your desired process.
