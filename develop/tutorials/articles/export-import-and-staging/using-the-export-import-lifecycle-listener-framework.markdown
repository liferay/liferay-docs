# Using the Export/Import Lifecycle Listener Framework

The `ExportImportLifecycleListener` framework allows developers to write code
that listens to certain staging events during the publication process. The
staging and export/import processes have many behind-the-scenes events that you
cannot react upon, by default. Some of these events, like export successes and
import failures, can hold important information that you'd like to know about
and react upon. You also have the ability to listen for processes comprised of
many events, and implement custom code when these processes are initiated.

As the publication is executed, different events are being sent as a broadcast,
and developers can write code to react to these events, or their processes. Some
examples of these events include:

- Staging has started
- A portlet export has failed
- An entity export has succeeded

In this tutorial, you'll learn how to use the `ExportImportLifecycleListener`
framework to listen for processes/events during the staging and export/import
lifecycles.

## Listening to Lifecycle Events

To begin creating your lifecycle listener, you'll need to create a module.
Follow the steps below:

1. Create a generic OSGi module using your favorite third party tool, or use the
[Plugins SDK](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle). 

2. Create a unique package name in the module's `src` directory, and create a
   new Java class in that package. To follow naming conventions, begin the class
   name with the entity or action name you're processing, followed by
   *ExportImportLifecycleListener* (e.g.,
   `LoggerExportImportLifecycleListener`).

3. You'll need to extend one of the two Base classes provided for using the
   Export/Import Lifecycle Listener framework:
   [`BaseExportImportLifecycleListener`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/lifecycle/BaseExportImportLifecycleListener.java)
   or
   [`BaseProcessExportImportLifecycleListener`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portlet/exportimport/lifecycle/BaseProcessExportImportLifecycleListener.java).
   To choose, you'll need to consider what parts of a lifecycle you want to
   listen for.

    The `BaseExportImportLifecycleListener` class should be extended if you'd
    like to listen for specific *events* during a lifecycle. For example, if you
    want to write custom code for when a layout export fails.

    The `BaseProcessExportImportLifecycleListener` class should be extended if
    you'd like to listen for processes during a lifecycle. For example, if you
    want to write custom code for when a site publication fails. Keep in mind
    that a process usually consists of many individual events. Methods provided
    by this base class are only run once when the desired process action occurs.

4. Directly above the class's declaration, insert the following annotation:

        @Component(immediate = true, service = ExportImportLifecycleListener.class)

    This annotation declares the implementation class of the component, and
    specifies to immediately start the module once deployed to Portal.

5. Specify the methods you want to implement in your class.

Once you've successfully created your export/import lifecycle listener module,
generate the module's JAR file and copy it to your Portal's `osgi/modules`
directory. Once your module is installed and activated in your Portal's service
registry, your lifecycle listener is ready for use in your Portal instance.

If you're still thirsting for more information on this framework, and want to
look at some examples for guidance, you're in luck! The first example this
tutorial will walk through is the
[`LoggerExportImportLifecycleListener`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/export-import/export-import-service/src/com/liferay/exportimport/lifecycle/LoggerExportImportLifecycleListener.java).
This listener extends the `BaseExportImportLifecycleListener`, so you should
immediately know that it deals with lifecycle events specifically.

The first method `isParallel()` determines whether your listener should run
parallel with the execution, or if the listener should stop and return the
execution to the coding method. The next method is the
`onExportImportLifecycleEvent(...)` method, which consumes the lifecycle event
and passes it through the base class's method (as long as Debug mode is not
enabled).

Each remaining method is called to print logging information to the user. For
example, when a layout export starts, succeeds, and fails, logging information
directly related to that event is printed. In summary, the
`LoggerExportImportLifecycleListener` uses the lifecycle listener framework to
print messages to the user when an export/import event occurs. Other good
examples for event lifecycle listeners are
[`CacheExportImportLifecycleListener`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/export-import/export-import-service/src/com/liferay/exportimport/lifecycle/CacheExportImportLifecycleListener.java)
and [`JournalCacheExportImportLifecycleListener`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-web/src/com/liferay/journal/web/lar/lifecycle/JournalCacheExportImportLifecycleListener.java).

For an example of a lifecycle listener extending the
`BaseProcessExportImportLifecycleListener` class, inspect the
[`ExportImportProcessCallbackLifecycleListener`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/export-import/export-import-service/src/com/liferay/exportimport/lifecycle/ExportImportProcessCallbackLifecycleListener.java)
class. Instead of listening for lifecycle events, this class only listens for
process actions.

Terrific! You learned about the Export/Import Lifecycle Listener framework, and
created your own listener to react to events/processes that occur during
export/import of your portal's content.
