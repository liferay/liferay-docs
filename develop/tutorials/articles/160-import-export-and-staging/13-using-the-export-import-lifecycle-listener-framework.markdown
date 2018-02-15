# Using the Export/Import Lifecycle Listener Framework [](id=using-the-export-import-lifecycle-listener-framework)

The `ExportImportLifecycleListener` framework lets developers write code that
listens for certain staging or export/import events during the publication
process. The staging and export/import processes have many behind-the-scenes
events that you cannot listen to by default. Some of these, like export
successes and import failures, may be events on which you'd want to take some
action. You also have the ability to listen for processes comprised of many
events and implement custom code when these processes are initiated. Here is a
short list of events you could listen for: 

- Staging has started
- A portlet export has failed
- An entity export has succeeded

The concept of listening for export/import and staging events sounds cool, but
you may be curious as to why listening for certain events is useful. Listening
for events can help you know more about your application's state. Suppose you'd
like a detailed log of when certain events occur during an import process. You
could configure a listener to listen for certain import events you're interested
in and print information about those events to your console when they occur.

@product@ uses this framework by default in several cases. For instance, the
cache is cleared when a web content import process finishes. To accomplish this,
the lifecycle listener framework listens for an event that specifies that a web
content import process has completed. Once that event occurs, there is an event
listener that automatically clears the cache. You could implement this sort of
functionality yourself for any event. You can listen for a specific event and
then complete an action based on when that event occurs. For a list of events
you can listen for during Export/Import and Staging processes, see
[ExportImportLifecycleConstants](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/ExportImportLifecycleConstants.html).

Some definitions are in order: 

**Events** are particular actions that occur during processing. 

**Processes** are longer running groups of events. 

In this tutorial, you'll learn how to use the `ExportImportLifecycleListener`
framework to listen for processes/events during the staging and export/import
lifecycles.

## Listening to Lifecycle Events [](id=listening-to-lifecycle-events)

To begin creating your lifecycle listener, you must create a module. Follow the
steps below:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module). 

2.  Create a unique package name in the module's `src` directory and create a
    new Java class in that package. To follow naming conventions, begin the
    class name with the entity or action name you're processing, followed by
    *ExportImportLifecycleListener* (e.g.,
    `LoggerExportImportLifecycleListener`).

3.  You must extend one of the two Base classes provided with the
    Export/Import Lifecycle Listener framework:
    [BaseExportImportLifecycleListener](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/BaseExportImportLifecycleListener.html)
    or
    [BaseProcessExportImportLifecycleListener](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/BaseProcessExportImportLifecycleListener.html).
    To choose, you'll need to consider what parts of a lifecycle you want to
    listen for.

    Extend the `BaseExportImportLifecycleListener` class if you want to listen
    for specific *events* during a lifecycle. For example, you may want to write
    custom code if a layout export fails.

    Extend the `BaseProcessExportImportLifecycleListener` class if you want to
    listen for *processes* during a lifecycle. For example, you may want to
    write custom code if a site publication fails. Keep in mind that a process
    usually consists of many individual events. Methods provided by this base
    class are only run once when the desired process action occurs.

4.  Directly above the class's declaration, insert the following annotation:

        @Component(immediate = true, 
                    service = ExportImportLifecycleListener.class)

    This annotation declares the implementation class of the component and
    specifies that the portal should start the module immediately. 

5.  Specify the methods you want to implement in your class.

Once you've successfully created your export/import lifecycle listener module,
generate the module's JAR file and copy it to @product@'s `osgi/modules` folder.
Once your module is installed and activated in your instance's service registry,
your lifecycle listener is ready for use in your Portal instance.

If you're still thirsting for more information on this framework, you're in
luck! Here's an example, using the
[LoggerExportImportLifecycleListener](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/LoggerExportImportLifecycleListener.html).
This listener extends the `BaseExportImportLifecycleListener`, so you 
immediately know that it deals with lifecycle events.

The first method `getStagedModelLogFragment` retrieves the staged model's log
fragment, which is the lifecycle listener's logging information on events. The
next method `isParallel()` determines whether your listener should run in
parallel with the import/export process, or if the calling method should stop,
execute the listener, and return to where the event was fired after the listener
has finished. The following method is the `onExportImportLifecycleEvent(...)`
method, which consumes the lifecycle event and passes it through the base
class's method (as long as Debug mode is not enabled).

Each remaining method is called to print logging information for the user. For
example, when a layout export starts, succeeds, or fails, logging information
directly related to that event is printed. In summary, the
`LoggerExportImportLifecycleListener` uses the lifecycle listener framework to
print messages to the log when an export/import event occurs. Anther good
example of an event lifecycle listener is the
[CacheExportImportLifecycleListener](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/CacheExportImportLifecycleListener.html).

For an example of a lifecycle listener extending the
`BaseProcessExportImportLifecycleListener` class, inspect the
[ExportImportProcessCallbackLifecycleListener](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/ExportImportProcessCallbackLifecycleListener.html)
class. Instead of listening for lifecycle events, this class only listens for
process actions.

Terrific! You learned about the Export/Import Lifecycle Listener framework, and
you've learned how to create your own listener for events/processes that occur
during export/import of your portal's content.
