# Initiating New Processes with ExportImportConfiguration Objects

The Staging and Export/Import features of Liferay are the building blocks for
creating, managing, and publishing a site. These features can be accessed in
your Portal's *Publishing Tools* menu. You can also, however, start these
processes programatically. This lets you provide new interfaces or mimic the
functionality of these features in your own application?

For example, suppose you want your custom OSGi application to have an export
feature, which users could use to export content from their application as a LAR
file. To offer this feature, you'd need to call services offered by the
`ExportImportService` interface. All of the methods in this interface require an
`ExportImportConfiguration` object, which is a controller object that contains
many parameters and settings that are required during the exportation process of
content from Liferay. The `ExportImportConfiguration` is essential to the export
functionality of your custom application. Liferay provides a way to generate
these configuration objects, so you can easily pass them in your service
methods.

In this tutorial, you'll learn about the `ExportImportConfiguration` framework
and how you can take advantage of provided services and factories to create
these controller obects. Once they're created, you can easily fulfill your
export/import and staging needs.

You can initiate a new export/import or staging process by creating an
`ExportImportConfiguration` object. This object serves as the controller object,
which sets up the export/import or staging process. The
`ExportImportConfiguration` object contains a large set of parameters and
settings that are processed during export/import and staging. Liferay provides
an extensive amount of services that can be called to initiate various
export/import or staging processes, and many of these services use the
`ExportImportConfiguration` object.

It's also important to know that a `ExportImportConfiguration` is a Portal
entity, similar to `User` or `Group`. This means that the
`ExportImportConfiguration` framework offers local and remote services, models,
persistence classes, etc.

Next, you'll create an `ExportImportConfiguration` object and use it to initiate
your custom export/import or staging process.

1.  Use the Export Import Configuration factory classes to build your
    `ExportImportConfiguration` object. Below is a common way to build your
    configuration object:

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
      This factory is used to build parameter maps, which are required during
      export/import and publishing.

2.  Call the appropriate service to initiate the export/import or staging
    process. There are two important service interfaces that you can use in the
    cases of exporting, importing, and staging:
    [ExportImportLocalService](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/service/ExportImportLocalService.java)
    and
    [StagingLocalService](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/service/StagingLocalService.java).
    In the previous step's example code snippet, you created an
    `ExportImportConfiguration` object intended for exporting layouts. Here's
    how to initiate that process: 

        files[0] = exportImportLocalService.exportLayoutsAsFile(
            exportImportConfiguration);

    By calling this interface's method, you're exporting layouts from Portal
    into a `java.io.File` array. Notice that your `ExportImportConfiguration`
    object is the only needed parameter in the method. Your configuration object
    holds all the required parameters and settings necessary to export your
    layouts from Portal. Although this example code resides in Liferay Portal,
    you could easily use this framework from your own plugin or module.

    +$$$

    **Note:** If you're not calling the export/import or staging service methods
    from an OSGi module, you should not use the interface. The Liferay
    OSGi container automatically handles interface referencing, which is why
    using the interface is permitted for modules. If you're calling
    export/import or staging service methods outside of a module, you should use
    their service Util classes (e.g., `ExportImportLocalServiceUtil`).

    $$$

It's that easy! To start your own export/import or staging process, you must
create an `ExportImportConfiguration` object using a combination of the three
provided `ExportImportConfiguration` factories. Once you have your configuration
object, provide it as a parameter in one of the many service methods provided to
you by the Export/Import or Staging interfaces to begin your desired process.
