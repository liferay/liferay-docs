---
header-id: using-the-export-import-lifecycle-listener-framework
---

# Using the Export/Import Lifecycle Listener Framework

[TOC levels=1-4]

In this tutorial, you'll learn how to use the `ExportImportLifecycleListener`
framework to listen for processes/events during the staging and export/import
lifecycles.

To begin creating your lifecycle listener, you must create a module. Follow the
steps below:

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project). 

2.  Create a unique package name in the module's `src` directory and create a
    new Java class in that package. To follow naming conventions, begin the
    class name with the entity or action name you're processing, followed by
    *ExportImportLifecycleListener* (e.g.,
    `LoggerExportImportLifecycleListener`).

3.  You must extend one of the two Base classes provided with the
    Export/Import Lifecycle Listener framework:
    [BaseExportImportLifecycleListener](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/BaseExportImportLifecycleListener.html)
    (event listener) or
    [BaseProcessExportImportLifecycleListener](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/BaseProcessExportImportLifecycleListener.html)
    (process listener). To choose, you'll need to consider what parts of a
    lifecycle you want to listen for (event or process).

4.  Directly above the class's declaration, insert the following annotation:

    ```java
    @Component(immediate = true, service = ExportImportLifecycleListener.class)
    ```

    This annotation declares the implementation class of the component and
    specifies that the portal should start the module immediately. 

5.  Specify the methods you want to implement in your class. As an example,
    you'll step through the
    [LoggerExportImportLifecycleListener](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/LoggerExportImportLifecycleListener.html).
    This listener extends the `BaseExportImportLifecycleListener`, so you 
    immediately know that it deals with lifecycle events.

6.  Add the `getStagedModelLogFragment(...)` method:

    ```java
    protected String getStagedModelLogFragment(StagedModel stagedModel) {
        StringBundler sb = new StringBundler(8);

        sb.append(StringPool.OPEN_CURLY_BRACE);
        sb.append("class: ");
        sb.append(ExportImportClassedModelUtil.getClassName(stagedModel));

        if (stagedModel instanceof StagedGroupedModel) {
            StagedGroupedModel stagedGroupedModel =
                (StagedGroupedModel)stagedModel;

            sb.append(", groupId: ");
            sb.append(stagedGroupedModel.getGroupId());
        }

        sb.append(", uuid: ");
        sb.append(stagedModel.getUuid());
        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
    ```

    This retrieves the staged model's log fragment, which is the lifecycle
    listener's logging information on events.

7.  Add the `isParallel()` method:

    ```java
    @Override
    public boolean isParallel() {
        return false;
    }
    ```

    This determines whether your listener should run in parallel with the
    import/export process, or if the calling method should stop, execute the
    listener, and return to where the event was fired after the listener has
    finished.

8.  Add the `onExportImportLifecycleEvent(...)` method:

    ```java
    @Override
    public void onExportImportLifecycleEvent(
            ExportImportLifecycleEvent exportImportLifecycleEvent)
        throws Exception {

        if (!_log.isDebugEnabled()) {
            return;
        }

        super.onExportImportLifecycleEvent(exportImportLifecycleEvent);
    }
    ```

    This consumes the lifecycle event and passes it through the base class's
    method (as long as Debug mode is not enabled).

9.  Each remaining method is called to print logging information for the user.
    For example, when a layout export fails, logging information directly
    related to that event is printed:

    ```java
    @Override
    protected void onLayoutExportFailed(
            PortletDataContext portletDataContext, Throwable throwable)
        throws Exception {

        if (!_log.isDebugEnabled()) {
            return;
        }

        _log.debug(
            "Layout export failed for group " + portletDataContext.getGroupId(),
            throwable);
    }
    ```

    In summary, the `LoggerExportImportLifecycleListener` uses the lifecycle
    listener framework to print messages to the log when an export/import event
    occurs. You can view the other logging methods implemented for this class
    [here](https://github.com/liferay/liferay-portal/blob/7.2.0-m2/modules/apps/export-import/export-import-service/src/main/java/com/liferay/exportimport/lifecycle/LoggerExportImportLifecycleListener.java).

10. Once you've successfully created your export/import lifecycle listener module,
    generate the module's JAR file and copy it to @product@'s `osgi/modules`
    folder.

    Once your module is installed and activated in your instance's service
    registry, your lifecycle listener is ready for use in your Portal instance.

Terrific! You learned about the Export/Import Lifecycle Listener framework, and
you've learned how to create your own listener for events/processes that occur
during export/import of your portal's content.
