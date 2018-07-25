# Leveraging the Script Engine in Workflow [](id=leveraging-the-script-engine-in-workflow)

Liferay's Kaleo workflow engine provides a robust system for reviewing and
approving content in an enterprise environment. Even if you don't leverage
custom scripts, it's a powerful and robust workflow solution. Adding custom
scripts takes it to the next level. These scripts aren't run from the script
console, but are embedded in 
[XML workflow definitions](/discover/portal/-/knowledge_base/7-1/crafting-xml-workflow-definitions) 
and run during the execution of the workflow.

The final step in a workflow runs a script that makes content available for use.
The snippet below accesses the Java class associated with the workflow to set
content's status to *approved*.

    <script>
        <![CDATA[
            import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
            import com.liferay.portal.kernel.workflow.WorkflowConstants;

            WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
        ]]>
    </script>
    <script-language>groovy</script-language>

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

    <actions>
        <action>
            <script>
                <![CDATA[*the contents of your script*]]>
            </script>
            <script-language>*your scripting language of choice*</script-language>
        </action>
        ...
    </actions>

Here's an example of a workflow script created in Groovy. This one is designed
to be used with a `Condition` statement in Kaleo. It accesses Liferay's asset
framework to determine the category of an asset in the workflow. The script uses
the category to automatically determine the correct approval process. If the
category `legal` has been applied to the asset, the asset is sent to the `Legal
Review` task upon submission. Otherwise, the asset is sent to the `Default
Review` task.

    <script>
        <![CDATA[
            import com.liferay.portal.kernel.util.GetterUtil;
            import com.liferay.portal.kernel.workflow.WorkflowConstants;
            import com.liferay.portal.kernel.workflow.WorkflowHandler;
            import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
            import com.liferay.portlet.asset.model.AssetCategory;
            import com.liferay.portlet.asset.model.AssetEntry;
            import com.liferay.portlet.asset.model.AssetRenderer;
            import com.liferay.portlet.asset.model.AssetRendererFactory;
            import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

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

Within a workflow, the next task or state is chosen based on the return value.
See some examples of workflow scripts by accessing the
[embedded workflows](/discover/portal/-/knowledge_base/7-1/workflow#embedded-workflows) 
and inspecting the XML.

## Calling OSGi Services [](id=calling-osgi-services)

How do you call OSGi services from a workflow script, accounting for the dynamic
environment of the OSGi runtime, where services your script depends on can
disappear without notice? 
[Use a service tracker](/develop/tutorials/-/knowledge_base/7-1/service-trackers). 
That way you can make sure your code has access to the service it
needs, and if not, do something appropriate in response. Here's a little example
code to show you how this might look in Groovy:

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

If you read the article on 
[service trackers](/develop/tutorials/-/knowledge_base/7-1/service-trackers), 
the only odd looking piece of the above code is the `getBundle` call: why is
`GroovyExecutor.class` passed as a parameter? The parameter passed to the
`FrameworkUtil.getBundle` call must be a class from the OSGi bundle executing
the workflow script. This is different from the context of a plugin project,
where you'd want to get the bundle hosting the class where you're making the
call (using `this.getClass()`, for example). Note that for another scripting
engine, you must pass in a concrete class from the particular bundle executing
your script.

The combination of Liferay's script and workflow engines is incredibly powerful.
However, since it provides users with the ability to execute code, it can be
dangerous. When configuring your permissions, be aware of the potential
consequences of poorly or maliciously written scripts inside of a workflow
definition. For more information on creating workflow definitions with Kaleo
workflow, see the 
[workflow documentation](/discover/portal/-/knowledge_base/7-1/workflow).

## Related Topics [](id=related-topics)

[Running Scripts From the Script Console](/discover/portal/-/knowledge_base/7-1/running-scripts-from-the-script-console)

[Script Examples](/discover/portal/-/knowledge_base/7-1/script-examples)
