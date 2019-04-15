---
header-id: export-import
---

# Export/Import

[TOC levels=1-4]

The Export/Import feature exports content from the portal and imports external
content into the portal. Your application is much more site
administrator-friendly if users can export/import your application's assets. For
example, if you want to export your application's assets to use on another
installation or you must clear its data but save a copy, you can implement
the export feature. Implementing the import feature lets you bring your
assets/data back into your application.

Here's what you'll learn to do with the Export/Import framework: 

- Create Staged Models
- Develop Portlet Data Handlers
- Develop Staged Model Data Handlers
- Provide entity-specific local services for Export/Import framework
- Listen to export/import events
- Initiate new export/import processes programmatically

## Staged Models

To track an entity of an application with the Export/Import framework, you must
implement the
[`StagedModel`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface in the app's model classes. It provides the behavior contract for
entities during the Export/Import and Staging processes. There are two ways to
create staged models for your application's entities:

- [Generate them using Service Builder](/docs/7-2/frameworks/-/knowledge_base/frameworks/generating-staged-models-using-service-builder)
- [Implement the appropriate interfaces manually](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-staged-models-manually)

Using Service Builder to generate your staged models is the easiest way to
create staged models for your app. You define the necessary columns in your
`service.xml` file and set the `uuid` attribute to `true`. Then you run
Service Builder, which generates the required code for your new staged models.

Implementing the necessary staged model logic *manually* should be done if you
**don't** want to extend your model with special attributes only required to
generate Staging logic (i.e., not needed by your business logic). In this case,
you should adapt your business logic to meet the Staging framework's needs.

See the
[Developing Staged Models](/docs/7-2/frameworks/-/knowledge_base/frameworks/developing-staged-models)
section for more information on the Staged Model architecture.

## Data Handlers

You must implement Data Handlers to use the Export/Import framework to process
[LAR files](/docs/7-2/reference/-/knowledge_base/reference/liferay-archive-lar-file)
in your application. There are two types of data handlers:

- Portlet Data Handlers
- Staged Model Data Handlers

A Portlet Data Handler imports/exports portlet specific data to a LAR file.
These classes query and coordinate between staged model data handlers. They also
configure the Export/Import and Staging UI options.

A Staged Model Data Handler supplies information about a staged model (entity)
to the Export/Import framework, defining a display name for the UI, deleting an
entity, etc. It also exports referenced content.

Visit the
[Developing Data Handlers](/docs/7-2/frameworks/-/knowledge_base/frameworks/developing-data-handlers)
section for more information.

## Provide Entity Specific Local Services

When creating data handlers, you must leverage your app's local services to
perform Export/Import and Staging related tasks for its entities. When these
frameworks operate on entities (i.e., staged models), it often cannot manage
important information from the entity's local services alone; instead, you're
forced to reinvent basic functionality so the framework can access it. This is
caused by services not sharing a common ancestor (i.e., interface or base
class).

The *Staged Model Repository* framework removes this barrier by linking an app's
staged model to a local service.

![Figure 1: Staged Model Repositories provide a Staging-specific layer of functionality for your local services.](../../../images/staged-model-repository.png)

This lets the Staging framework call a staged model repository independently
based on the entity being processed. This gives you access to entity-specific
methods tailored specifically for the staged model data you're handling.

## Export/Import Event Listeners

The `ExportImportLifecycleListener` framework is for 
listening for certain staging or export/import events (like export successes and
import failures) during the publication process so you can take some action. You
can also listen for processes comprised of many events and take action when
these processes are initiated. For example, you can listen for when

- Staging has started
- A portlet export has failed
- An entity export has succeeded

After an event is triggered, you can take an action like these: 

- Print information about the event to your console 
- When an import process has completed, clear the cache.

For a complete list of events you can listen for, see
[`ExportImportLifecycleConstants`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/ExportImportLifecycleConstants.html).

You must extend one of the two Base listener classes:

- [`BaseExportImportLifecycleListener`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/BaseExportImportLifecycleListener.html):
  listens for specific *events* during a lifecycle. For example, if a layout
  export fails, you might take some action. 

- [`BaseProcessExportImportLifecycleListener`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/BaseProcessExportImportLifecycleListener.html):
  listens for *processes* during a lifecycle. A process usually consists of many
  individual events. For example, if a site publication fails, you might take
  some action. Methods provided by this base class are only run once when the
  desired process action occurs.

What's the difference between events and processes? 

**Events:** particular actions that occur during processing (example event
listener:
[`CacheExportImportLifecycleListener`](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/CacheExportImportLifecycleListener.html)).

**Processes:** longer running groups of events (example process listener:
[`ExportImportProcessCallbackLifecycleListener`](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/ExportImportProcessCallbackLifecycleListener.html)).

Use the listener type that is most appropriate for your use case.

## Export/Import Processes

You can start the process programmatically instead of through the UI. This lets
you provide new interfaces or mimic the functionality of these features in your
own application.

To initiate an export/import or staging process, you must pass in an
[`ExportImportConfiguration`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/model/ExportImportConfiguration.html)
object. This object encapsulates many parameters and settings that are required
while the export/import is running. Having one single object with all your
necessary data makes executing these frameworks quick and easy.

For example, when implementing export, you must call services offered by the
[`ExportImportService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportService.html)
interface. All the methods in this interface require an
`ExportImportConfiguration` object. You can generate these
configuration objects, so you can easily pass them in your service methods.

There are three factory classes that are useful to create an
`ExportImportConfiguration` object:

- [`ExportImportConfigurationSettingsMapFactory`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationSettingsMapFactory.html):
  provides many `build` methods to create settings maps for various scenarios
  like importing, exporting, and publishing layouts and portlets. For example,
  you can reference
  [`UserGroupLocalServiceImpl.exportLayouts(...)`](@platform-ref@/7.2-latest/javadocs/portal-impl/com/liferay/portal/service/impl/UserGroupLocalServiceImpl.html#exportLayouts-long-java.util.Map-)
  and
  [`GroupLocalServiceImpl.addDefaultGuestPublicLayoutsByLAR(...)`](@platform-ref@/7.2-latest/javadocs/portal-impl/com/liferay/portal/service/impl/GroupLocalServiceImpl.html#addDefaultGuestPublicLayoutsByLAR-com.liferay.portal.kernel.model.Group-java.io.File-).
- [`ExportImportConfigurationFactory`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationFactory.html):
  This factory builds `ExportImportConfiguration` objects used for default
  local/remote publishing.
- [`ExportImportConfigurationParameterMapFactory`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationParameterMapFactory.html):
  This factory builds parameter maps, which are required during export/import
  and publishing.

There are two important service interfaces that primarily use
`ExportImportConfiguration` objects for exporting, importing, and staging:
[`ExportImportLocalService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportLocalService.html)
and
[`StagingLocalService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/StagingLocalService.html).

| **Note:** If you're not calling the export/import or staging service methods
| from an OSGi module, you should not use the interface. The Liferay
| OSGi container automatically handles interface referencing, which is why
| using the interface is permitted for modules. If you're calling
| export/import or staging service methods outside of a module, you should use
| their service Util classes (e.g., `ExportImportLocalServiceUtil`).

It's also important to know that `ExportImportConfiguration` is a @product@
entity, similar to `User` or `Group`. This means that the
`ExportImportConfiguration` framework offers local and remote services, models,
persistence classes, and more.
