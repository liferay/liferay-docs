# Initiating New Processes with ExportImportConfiguration Objects

The Staging and Export/Import features of Liferay are the building blocks for
creating, managing, and publishing a site. These features can be accessed in
your Portal's *Publishing tools* menu. However, is there a way to start these
processes programatically? Or maybe you want to mimic the functionality of these
features in your own application?

In this tutorial, you'll learn about the `ExportImportConfiguration` framework,
and how you can take advantage of provided services and factories to fulfill
your export/import and staging needs.

## Using the ExportImportConfiguration Framework

The `ExportImportConfiguration` framework is used to initiate a new
export/import or staging process by creating an `ExportImportConfiguration`
object. This object serves as the controller object, which sets up the
export/import or staging process. The `ExportImportConfiguration` object
contains a large set of parameters, settings, etc. that are processed during
export/import and staging. Liferay provides an extensive amount of services that
can be called to initiate various export/import or staging processes, and many
of these services utilize the `ExportImportConfiguration` object.

It's also important to know that a `ExportImportConfiguration` is a Portal
entity, similar to `Journal`, `User`, or `Group`. This means that the
`ExportImportConfiguration` framework offers local and remote services, models,
persistence classes, etc.

Next, you'll create an `ExportImportConfiguration` object, and utilize it to
initiate your custom export/import or staging process.

1. Utilize the Export Import Configuration factory classes to build your
   `ExportImportConfiguration` object. A common way to build your configuration
   object is the following:

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
    [ExportImportConfigurationSettingsMapFactory](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/configuration/ExportImportConfigurationSettingsMapFactory.java)
    to create a layout export settings map. Then this map is used as a parameter
    to create an `ExportImportConfiguration` by calling an *add* method in the
    entity's local service interface. The
    [ExportImportConfigurationLocalService](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/service/ExportImportConfigurationLocalService.java)
    provides several useful methods to create and modify your custom
    `ExportImportConfiguration`.
    
    The `ExportImportConfigurationSettingsMapFactory` provides many `build`
    methods to create settings maps for various scenarios, like importing,
    exporting, and publishing layouts and portlets. For examples of this
    particular scenario, you can reference
    [UserGroupLocalServiceImpl.exportLayouts(...)](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portal/service/impl/UserGroupLocalServiceImpl.java)
    and [GroupLocalServiceImpl.addDefaultGuestPublicLayoutsByLAR(...)](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portal/service/impl/GroupLocalServiceImpl.java). 

    There are two other important factories provided by this framework that are
    useful during the creation of `ExportImportConfiguration` objects:

    - [ExportImportConfigurationFactory](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/configuration/ExportImportConfigurationFactory.java):
      This factory is used to build `ExportImportConfiguration` objects used
      for default local/remote publishing.
    - [ExportImportConfigurationParameterMapFactory](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/configuration/ExportImportConfigurationParameterMapFactory.java):
      This factory is used to build parameter maps, which are frequently
      required during export/import and publishing.

2. Call the appropriate service to initiate the export/import or staging
   process. There are two important service interfaces that you can use in the
   cases of exporting, importing, and staging:
   [ExportImportLocalService](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/service/ExportImportLocalService.java)
   and
   [StagingLocalService](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/service/StagingLocalService.java).
   In the previous step's example code snippet, you created an
   `ExportImportConfiguration` object intended for exporting layouts. To
   initiate that process, you can declare the following:

        files[0] = exportImportLocalService.exportLayoutsAsFile(
            exportImportConfiguration);

    By calling this interface's method, you're exporting layouts from Portal
    into a `java.io.File` array. Notice that your `ExportImportConfiguration`
    object is the only needed parameter in the method. Your configuration object
    holds all the required parameters and settings necessary to initiate the
    exportation of your layouts from Portal. Although this example code resides
    in Liferay Portal, you could easily use this framework from your own plugin
    or module.

It's that easy! To start your own export/import or staging process, you must
create an `ExportImportConfiguration` object using a combination of the three
provided `ExportImportConfiguration` factories. Once you have your configuration
object, provide it as a parameter in one of the many service methods provided to
you by the ExportImport or Staging interfaces to begin your desired process.
