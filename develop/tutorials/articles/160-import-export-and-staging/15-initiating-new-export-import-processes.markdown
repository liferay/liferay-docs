# Initiating New Export/Import Processes [](id=initiating-new-export-import-processes)

The Staging and Export/Import features are the building blocks for creating,
managing, and publishing a site. These features can be accessed in the
*Publishing Tools* menu. You can also, however, start these processes
programmatically. This lets you provide new interfaces or mimic the
functionality of these features in your own application.

Providing the ability to stage your application's assets makes using your
application much more site administrator-friendly. Your new assets no longer
have to be saved somewhere off-site until they're ready to be published. You can
publish them to a staging environment, test their usability, and save
them to a page. Once the time is right for publishing, you can publish your
application's assets to the live site with one mouse click. The export/import
feature offers similar conveniences: if you want to export your application's
assets to use in another place or you need to clear its data but save a copy you
can implement the export feature. Implementing the import feature lets you bring
your assets/data back into your application.

To initiate a export/import or staging process, you must pass in an
[ExportImportConfiguration](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/model/ExportImportConfiguration.html)
object. This object encapsulates many parameters and settings that are required
while the export/import is running. Having one single object with all your
necessary data makes executing these frameworks quick and easy.

For example, when you want to implement the export feature, you must call
services offered by the
[ExportImportService](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportService.html)
interface. All the methods in this interface require an
`ExportImportConfiguration` object. @product@ provides a way to generate these
configuration objects, so you can easily pass them in your service methods.

It's also important to know that `ExportImportConfiguration` is an @product@
entity, similar to `User` or `Group`. This means that the
`ExportImportConfiguration` framework offers local and remote services, models,
persistence classes, and more.

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

    The `ExportImportConfigurationSettingsMapFactory` provides many `build`
    methods to create settings maps for various scenarios, like importing,
    exporting, and publishing layouts and portlets. For examples of this
    particular scenario, you can reference
    [UserGroupLocalServiceImpl.exportLayouts(...)](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/service/impl/UserGroupLocalServiceImpl.html#exportLayouts-long-java.util.Map-)
    and
    [GroupLocalServiceImpl.addDefaultGuestPublicLayoutsByLAR(...)](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/service/impl/GroupLocalServiceImpl.html#addDefaultGuestPublicLayoutsByLAR-com.liferay.portal.kernel.model.Group-java.io.File-).

    There are two other important factories provided by this framework that are
    useful during the creation of `ExportImportConfiguration` objects:

    - [ExportImportConfigurationFactory](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationFactory.html):
      This factory builds `ExportImportConfiguration` objects used for default
      local/remote publishing.
    - [ExportImportConfigurationParameterMapFactory](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationParameterMapFactory.html):
      This factory builds parameter maps, which are required during
      export/import and publishing.

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
object, provide it as a parameter in one of the many service methods available
to you by the Export/Import or Staging interfaces to begin your desired process.
