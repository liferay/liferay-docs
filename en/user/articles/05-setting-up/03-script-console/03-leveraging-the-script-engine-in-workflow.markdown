---
header-id: leveraging-the-script-engine-in-workflow
---

# Leveraging the Script Engine in Workflow

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/process-automation/workflow/developer-guide/using-the-script-engine-in-workflow.html?highlight=workflow">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Liferay's Kaleo workflow engine provides a robust system for reviewing and
approving content in an enterprise environment. Even if you don't leverage
scripts, it's a powerful and robust workflow solution. Adding scripts takes it
to the next level. These scripts aren't run from the script console, but are
embedded in
[XML workflow definitions](/docs/7-2/reference/-/knowledge_base/r/crafting-xml-workflow-definitions)
and run during the execution of the workflow.

## Injected Variables

Usually when you're scripting in Groovy, you must define your variables.

```groovy
KaleoInstanceToken kaleoInstanceToken=new KaleoInstanceToken();
```

In workflow scripts, there are several [pre-defined
variables](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-scripting-impl/src/main/java/com/liferay/portal/workflow/kaleo/runtime/scripting/internal/util/ScriptingContextBuilderImpl.java)
injected into your script context, to be called without defining them first.

### Variables that are Always Available

These variables are available from anywhere that you can run a workflow script:

`kaleoInstanceToken` ([`KaleoInstanceToken`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoInstanceToken.java))
: A workflow instance and corresponding instance token (the
`KaleoInstanceToken`) are created each time a User clicks _Submit for
Publication_. Use the injected token to retrieve its ID, by calling
`kaleoInstanceToken.getKaleoInstanceTokenId()`. This is often passed as a method
parameter in a script.

`userId`
: The `userId` returned is context dependent. Technically, the logic works like
this: if the `KaleoTaskInstanceToke.getcompletionUserId()` is null, check
`KaloeTaskInstanceToken.getUserId()`. If that's null too, call
`KaleoInstanceToken.getUserId()`. It's the ID of the last User to intervene in
the workflow at the time the script is run. In the `created` node, this would be
the User that clicked _Submit for Publication_, whereas it's the ID of the
reviewer upon exit of the `review` node of the Single Approver definition.

`workflowContext` (`Map<String, Serializable>`)
: The workflow context is full of useful information you can
use in your scripts. Usually you'll pass this as a parameter to a method that
requires a `WorkflowContext` object, but all of the `WorkflowContext`'s
attributes are available in the script as well. The workflow context in the
script is context dependent. If a call to
`ExecutionContext.getWorkflowContext()` comes back null, then the workflow
context is obtained by `KaleoInstanceModel.getWorkflowContext()`.

### Variables Injected into Task Nodes

If a `kaleoTaskInstanceToken` has been created:

`kaleoTaskInstanceToken` ([`KaleoTaskInstanceToken`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoTaskInstanceToken.java))
: The task's token itself is available in the workflow script. Use it to get its
ID, to use in other useful programmatic workflow activities, like programmatic
assignment.

`taskName` (`String`)
: The task's own name is accessible (returns the same as `KaleoTak.getName()`).

`workflowTaskAssignees` (`List<`[`WorkflowTaskAssignee`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/portal-kernel/src/com/liferay/portal/kernel/workflow/WorkflowTaskAssignee.java)`>`)
: If the script is inside a task node, get a `List` of its assignees.

`kaleoTimerInstanceToken` ([`KaleoTimerInstanceToken`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoTimerInstanceToken.java))
: If a [task timer](/docs/7-2/reference/-/knowledge_base/r/workflow-task-nodes#task-timers)
exists, use the `kaleoTimerInstanceToken` to get its ID, by calling
`kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId()`.

## Scripting Examples

The final step in a workflow runs a script that makes content available for use.
The snippet below accesses the Java class associated with the workflow to set
content's status to *approved*.

```xml
<script>
    <![CDATA[
        import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
        import com.liferay.portal.kernel.workflow.WorkflowConstants;

        WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
    ]]>
</script>
<script-language>groovy</script-language>
```

At virtually any point in a workflow, you can use Liferay's script engine to
access workflow APIs or other Liferay APIs. There are a lot of different ways
you could use this. Here are a few practical examples:

-  Getting a list of users with a specific workflow-related role
-  Sending an email to the designated content approver with a list of people to
   contact if he is unable to review the content
-  Creating an alert to be displayed in the Alerts portlet for any user assigned
   to approve content

Of course, before you try any of this, you need to know the appropriate syntax
for inserting a script into a workflow. In an XML workflow definition, a script
can be used in any XML type that can contain an `actions` tag: those types are
`<state>`, `<task>`, `<fork>` and `<join>`. Inside of one of those types, format
your script like this:

```xml
<actions>
    <action>
        <script>
            <![CDATA[*the contents of your script*]]>
        </script>
        <script-language>*your scripting language of choice*</script-language>
    </action>
    ...
</actions>
```

Here's an example of a workflow script created in Groovy. This one is used with
a `Condition` statement in Kaleo. It accesses Liferay's asset framework to
determine the category of an asset. The script uses the category to determine
the correct approval process automatically.  If the category `legal` has been
applied to the asset, the asset is sent to the `Legal Review` task upon
submission. Otherwise, the asset is sent to the `Default Review` task.

```xml
<script>
    <![CDATA[
        import com.liferay.portal.kernel.util.GetterUtil;
        import com.liferay.portal.kernel.workflow.WorkflowConstants;
        import com.liferay.portal.kernel.workflow.WorkflowHandler;
        import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
        import com.liferay.asset.kernel.model.AssetCategory;
        import com.liferay.asset.kernel.model.AssetEntry;
        import com.liferay.asset.kernel.model.AssetRenderer;
        import com.liferay.asset.kernel.model.AssetRendererFactory;
        import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;

        import java.util.List;

        String className = (String)workflowContext.get(
            WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

        WorkflowHandler workflowHandler =
            WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

        AssetRendererFactory assetRendererFactory =
            workflowHandler.getAssetRendererFactory();

        long classPK =
            GetterUtil.getLong((String)workflowContext.get
            (WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

        AssetRenderer assetRenderer =
            workflowHandler.getAssetRenderer(classPK);

        AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
            assetRendererFactory.getClassName(), assetRenderer.getClassPK());

        List<AssetCategory> assetCategories = assetEntry.getCategories();

        returnValue = "Default Review";

        for (AssetCategory assetCategory : assetCategories) {
            String categoryName = assetCategory.getName();

            if (categoryName.equals("legal")) {
                returnValue = "Legal Review";

                return;
            }
        }
       ]]>
</script>
<script-language>groovy</script-language>
```

Within a workflow, the next task or state is chosen based on the return value.
See some examples of workflow scripts by accessing the
[embedded workflows](/docs/7-2/user/-/knowledge_base/u/workflow#embedded-workflows)
and inspecting the XML.

## Calling OSGi Services

How do you call OSGi services from a workflow script, accounting for the dynamic
environment of the OSGi runtime, where services your script depends on can
disappear without notice?
[Use a service tracker](/docs/7-2/frameworks/-/knowledge_base/f/service-trackers-for-osgi-services).
That way you can make sure your code has access to the service it
needs, and if not, do something appropriate in response. Here's a little example
code to show you how this might look in Groovy:

```groovy
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.scripting.groovy.internal.GroovyExecutor;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

ServiceTracker<JournalArticleLocalService, JournalArticleLocalService> st;

try {
    Bundle bundle = FrameworkUtil.getBundle(GroovyExecutor.class);

    st = new ServiceTracker(bundle.getBundleContext(), JournalArticleLocalService.class, null);
    st.open();

    JournalArticleLocalService jaService = st.waitForService(500);

    if (jaService == null) {
        _log.warn("The required service 'JournalArticleLocalService' is not available.");
    }
    else {
        java.util.List<JournalArticle>articles = jaService.getArticles();
        if (articles != null) {
            _log.info("Article count: " + articles.size());
        } else {
            _log.info("no articles");
        }
    }
}
catch(Exception e) {
    //Handle error appropriately
}
finally {
    if (st != null) {
        st.close();
    }
}
```

If you read the article on [service
trackers](/docs/7-2/frameworks/-/knowledge_base/f/service-trackers-for-osgi-services), the only odd
looking piece of the above code is the `getBundle` call: why is
`GroovyExecutor.class` passed as a parameter? The parameter passed to the
`FrameworkUtil.getBundle` call must be a class from the OSGi bundle executing
the workflow script. This is different from the context of a plugin project,
where you'd want to get the bundle hosting the class where you're making the
call (using `this.getClass()`, for example). Note that for another scripting
engine, you must pass in a concrete class from the particular bundle executing
your script.

The combination of Liferay's script and workflow engines is incredibly powerful.
Since, however, it enables users to execute code, it can be dangerous. When
configuring your permissions, be aware of the potential consequences of poorly
or maliciously written scripts inside a workflow definition. For more
information on creating workflow definitions, see the [workflow
documentation](/docs/7-2/user/-/knowledge_base/u/workflow).

## Related Topics

[Running Scripts From the Script Console](/docs/7-2/user/-/knowledge_base/u/running-scripts-from-the-script-console)

[Script Examples](/docs/7-2/user/-/knowledge_base/u/script-examples)
