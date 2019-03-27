---
header-id: developing-data-handlers
---

# Developing Data Handlers

[TOC levels=1-4]

A common requirement for many data driven applications is to import and export
data. This *could* be accomplished by accessing your database directly and
running SQL queries to export/import data; however, this has several drawbacks:

- Working with different database vendors might require customized SQL scripts.
- Access to the database may be tightly controlled, restricting the ability to
  export/import on demand.
- You'd have to come up with your own means of storing and parsing the data. 

Liferay provides data handlers as a more convenient and reliable way to
export/import your data (as a LAR file) without accessing the database.

There are two types of data handlers:

- Portlet Data Handlers
- Staged Model Data Handlers

A Portlet Data Handler imports/exports portlet specific data to a LAR file.
These classes only have the role of querying and coordinating between staged
model data handlers. For example, the Bookmarks application's portlet data
handler tracks system events dealing with Bookmarks entities. It also configures
the Export/Import UI options for the Bookmarks application.

A Staged Model Data Handler supplies information about a staged model (entity)
to the Export/Import framework, defining a display name for the UI, deleting an
entity, etc. It's also responsible for exporting referenced content. For
example, if a Bookmarks entry resides in a Bookmarks folder, the
`BookmarksEntry` staged model data handler invokes the export of the
`BookmarksFolder`.

![Figure 1: The Data Handler framework uses portlet data handlers and staged model data handlers to track and export/import portlet and staged model information, respectively.](../../../../images/data-handler-diagram.png)

You're not required to implement a staged model data handler for every entity in
your application, but they're necessary for any entity you want to export/import
or have the staging framework track.

| **Note:** Creating data handlers for your app means it's automatically tracked
| by the Staging framework. You can further customize how Staging handles your
| app, but creating staged models and data handlers is what registers your app
| for Staging.

Before implementing data handlers, make sure your application is ready for the
Export/Import and Staging frameworks by creating
[staged models](/developer/frameworks/-/knowledge_base/7-2/staged-models).

## Understanding the `PortletDataHandler` Interface

A Portlet Data Handler imports/exports portlet specific data to a LAR file.
These classes only have the role of querying and coordinating between
[staged model data handlers](/developer/frameworks/-/knowledge_base/7-2/developing-staged-model-data-handlers).

To create a portlet data handler for your staged model, you must implement the
[`PortletDataHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)
interface. This is typically done by extending the
[`BasePortletDataHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BasePortletDataHandler.html)
class. Visit the API reference documentation for this interface/class for useful
information on the methods provided.

Some additional information for implementing the `PortletDataHandler` 
interface is provided below:

- The `@Component` annotation section above the implementation class's
  declaration registers the class as a portlet data handler in the OSGi service
  registry. There are a few annotation attributes you should set:

    - `immediate`: directs the container to activate the component immediately
      once its provided module has started.
    - `property`: sets various properties for the component service. You must
      associate the portlet you wish to handle with this service so they
      function properly in the export/import environment. You should have one
      portlet data handler for each portlet (e.g., Bookmarks and Bookmarks
      Admin).
    - `service`: points to the `PortletDataHandler.class` interface.

- The `activate` method sets what the portlet data handler controls. It also
  configures the portlet's Export/Import and Staging UI. This method is called
  during initialization of the component by using the
  [`@Activate`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Activate.html)
  annotation; it's invoked after dependencies are set and before services are
  registered. Five callable `set` methods are described below:

    - `setDataPortletPreferences`: sets portlet preferences your app should
      handle.
    - `setDeletionSystemEventStagedModelTypes`: sets the staged model deletions
      that the portlet data handler should track. For example, the Bookmarks app
      tracks Bookmark entries and folders.
    - `setPublishToLiveByDefault`: controls whether your app is selected to
      publish on the Publication screen by default.
    - `setExportControls`: adds fine grained controls over export/import
      behavior that is rendered in the Export/Import UI. This also sets the
      `setImportControls` method. For example, the Bookmarks app adds a
      checkbox to select Bookmarks content (entries) to export.
    - `setStagingControls`: adds fine grained controls over staging behavior
      that is rendered in the Staging UI. For example, this enables your
      app's checkboxes in the Content section displayed during publication.

- The `doExportData` method checks if anything should be exported. For example,
  the Bookmarks app uses this method to check if the user selected Bookmarks
  entries for export by leveraging the `portletDataContext`. Later, the
  `ExportImportActionableDynamicQuery` framework runs a query against
  bookmarks folders and entries to find ones which should be exported to the
  LAR file.

  The `-ActionableDynamicQuery` classes are automatically generated by Service
  Builder and are available in an app's local services. It queries the database
  searching for certain Export/Import-specific parameters (e.g., `createDate`
  and `modifiedDate`), and based on those parameters, finds a list of exportable
  records from the staged model data handler.

- The `doImportData` method queries for entity data in the imported LAR file
  that should be added to the database. This is done by extracting XML elements
  from the LAR file by using utility methods in the
  [`StagedModelDataHandlerUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandlerUtil.html)
  class. The extracted elements tell @product@ what data to import from the LAR
  file.

- The `doPrepareManifestSummary` method calculates the number of affected
  entities based on the current export or staging process.

- You must retrieve and manage the schema version. This is done with the
  `getSchemaVersion` and `validateSchemaVersion` methods. The schema version is
  used to perform component related validation before importing data. It's added
  to the
  [LAR file](/developer/reference/-/knowledge_base/7-2/liferay-archive-file) for
  each application being processed. During import, the environment's schema
  version is compared to the LAR file's schema version. Validating the schema
  version avoids broken data when importing. See the
  [`PortletDataHandler.getSchemaVersion()`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html#getSchemaVersion--)
  method's Javadoc for more information.

Next you'll learn about the `StagedModelDataHandler` interface.

## Understanding the `StagedModelDataHandler` Interface

A Staged Model Data Handler supplies information about a staged model (entity)
to the Export/Import framework, defines a display name for the UI, deletes
entities, etc. It's also responsible for exporting referenced content. For
example, if a Bookmarks entry resides in a Bookmarks folder, the
`BookmarksEntry` staged model data handler invokes the export of the
`BookmarksFolder`.

To create a staged model data handler for your staged model, you must implement the
[`StagedModelDataHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandler.html)
interface. This is typically done by extending the
[`BaseStagedModelDataHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/BaseStagedModelDataHandler.html)
class. Visit the API reference documentation for this interface/class for useful
information on the methods provided.

Additional implementation details for the `StagedModelDataHandler`  interface is
provided below:

- The `@Component` annotation section above the implementation class's
  declaration registers the class as a staged model data handler in the OSGi
  service registry. There are two annotation attributes you should set:

    - `immediate`: directs the container to activate the component immediately
      once its provided module has started.
    - `service`: points to the `StagedModelDataHandler.class` interface.

- The `getClassNames` method provides the class names of the models the data
  handler tracks. As a best practice, you should have one staged model data
  handler per staged model. It's possible to use multiple class types, but this
  is not recommended.

- The `getDisplayName` method retrieves the staged model's display name. This is
  used in the Export/Import UI.

- The `doExportStagedModel` method retrieves your app entity's data element
  from the
  [`PortletDataContext`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html)
  and then adds the class model characterized by that data element to the
  `PortletDataContext`. The `PortletDataContext` is used to populate the LAR
  file with your application's data during the export process.

    | **Note:** A staged model data handler should ensure everything required
    | for its operation is also exported. For example, in the Bookmarks
    | application, an entry requires its folder to keep the folder structure
    | intact. Therefore, the folder should be exported first followed by the
    | entry. Note that once an entity has been exported, subsequent calls to the
    | export method won't actually repeat the export process multiple times,
    | ensuring optimal performance.

- The `doImportStagedModel` method imports the staged model data. An important
  feature of the import process is that all exported reference elements are
  automatically imported when needed. The `doImportStagedModel` method does not
  need to import the reference elements manually; it must only find the new
  assigned ID for the folder before importing the entry.

    The `PortletDataContext` keeps this information and a slew of other data
    up-to-date during the import process. The old ID and new ID mapping can be
    reached by using the `portletDataContext.getNewPrimaryKeysMap()` method.
    This method also checks the import mode (e.g., *Copy As New* or *Mirror*)
    and, depending on the process configuration and existing environment, adds
    or updates the entry.

- The `doImportMissingReference` method maps the existing staged model to the
  old ID in the reference element. When a reference is exported as missing, the
  Data Handler framework calls this method during the import process and updates
  the new primary key map in the portlet data context.

    When importing a LAR (i.e., publishing to the live Site), the import process
    expects all of an entity's references to be available and validates their
    existence.

    For example, if you republish an updated bookmarks folder to the live Site
    and did not include some of its existing entries in the publication, these
    entries are considered missing references.

    Since you have references from two separate Sites with differing IDs, the
    system can't match them during publication. Suppose you export a bookmark
    entry as a missing reference with a primary key (ID) of `1`. When importing
    that information, the LAR only provides the ID but not the entry itself.
    Therefore, during the import process, the Data Handler framework searches
    for the entry to replace by its UUID, but the entry to replace has a
    different ID (primary key) of `2`. You must provide a way to handle these
    missing references.

    To do this, you must add a method that maps the missing reference's primary
    key from the export to the existing primary key during import. Since the
    reference's UUID is constant across systems, it's used to complete the
    mapping of differing primary keys. Note that a reference can only be missing
    on the live Site if it has already been published previously. Therefore,
    when publishing a bookmarks folder for the first time, the system doesn't
    check for missing references.

Continue in the section to learn how to develop data handlers for your app.
