# Using the Export/Import Lifecycle Listener Framework

The `ExportImportLifecycleListener` framework allows developers to write code
that listens to certain staging events during the publication process. The
staging and export/import processes have many behind-the-scenes events that you
cannot inspect, by default. Some of these events, like logging and caching, can
hold important information that you'd like to know about.

As the publication (or export/import) is executed, different events are being
sent as a broadcast, and developers can write code to react or process these
events. Some examples of these events include:

- Staging has started
- A portlet export has succeeded or failed
- An entity export succeeded

In this tutorial, you'll learn how to use the `ExportImportLifecycleListener`
framework to react or process events during the staging and export/import
lifecycles.

## Listening to Lifecycle Events
