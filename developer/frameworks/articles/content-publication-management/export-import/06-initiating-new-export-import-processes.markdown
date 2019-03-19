---
header-id: initiating-new-export-import-processes
---

# Initiating New Export/Import Processes

In this tutorial, you'll learn about the `ExportImportConfiguration` framework
and how you can take advantage of provided services and factories to create
these controller objects. Once they're created, you can easily implement
whatever import/export functionality you need.

Your first step is to create an `ExportImportConfiguration` object and use it to
initiate your custom export/import or staging process.

1.  Use the Export/Import Configuration factory classes to build your
    `ExportImportConfiguration` object. Below is a common way to do it:

        Map<String, Serializable> exportLayoutSettingsMap =
            ExportImportConfigurationSettingsMapFactory.
                buildExportLayoutSettingsMap(...);

        ExportImportConfiguration exportImportConfiguration =
            exportImportConfigurationLocalService.
                addDraftExportImportConfiguration(
                    user.getUserId(),
                    ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT,
                    exportLayoutSettingsMap);

    This example uses the
    [ExportImportConfigurationSettingsMapFactory](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationSettingsMapFactory.html)
    to create a layout export settings map. Then this map is used as a parameter
    to create an `ExportImportConfiguration` by calling an *add* method in the
    entity's local service interface. The
    [ExportImportConfigurationLocalService](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportConfigurationLocalService.html)
    provides several useful methods to create and modify your custom
    `ExportImportConfiguration`.

2.  Call the appropriate service to initiate the export/import or staging
    process. There are two important service interfaces that you can use in the
    cases of exporting, importing, and staging:
    [ExportImportLocalService](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportLocalService.html)
    and
    [StagingLocalService](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/StagingLocalService.html).
    In the previous step's example code snippet, you created an
    `ExportImportConfiguration` object intended for exporting layouts. Here's
    how to initiate that process: 

        files[0] = exportImportLocalService.exportLayoutsAsFile(
            exportImportConfiguration);

    By calling this interface's method, you're exporting layouts from @product@
    into a `java.io.File` array. Notice that your `ExportImportConfiguration`
    object is the only needed parameter in the method. Your configuration object
    holds all the required parameters and settings necessary to export your
    layouts from @product@. Although this example code resides in @product@, you
    could easily use this framework from your own project.

    | **Note:** If you're not calling the export/import or staging service methods
    | from an OSGi module, you should not use the interface. The Liferay
    | OSGi container automatically handles interface referencing, which is why
    | using the interface is permitted for modules. If you're calling
    | export/import or staging service methods outside of a module, you should use
    | their service Util classes (e.g., `ExportImportLocalServiceUtil`).

It's that easy! To start your own export/import or staging process, you must
create an `ExportImportConfiguration` object using a combination of the three
provided `ExportImportConfiguration` factories (outlined
[here](/developer/frameworks/-/knowledge_base/7-2/export-import#export-import-processes)).
Once you have your configuration object, provide it as a parameter in one of the
many service methods available to you by the Export/Import or Staging interfaces
to begin your desired process.
