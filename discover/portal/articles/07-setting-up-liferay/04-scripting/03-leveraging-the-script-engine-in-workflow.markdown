# Leveraging the Script Engine in Workflow [](id=leveraging-the-script-engine-in-workflow)

Liferay's Kaleo workflow engine provides a robust system for reviewing and
approving content in an enterprise environment. Even if you don't leverage
custom scripts, it's a powerful and robust workflow solution. Adding custom
scripts takes it to the next level.

The final step in a workflow should run a script that makes content available
for use. As you can see in the snippet below, JavaScript can be used to access
the Java class associated with the workflow to set the status of the content to
*approved*.

    <script>
        <![CDATA[
            Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("denied"), workflowContext);
            Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("pending"), workflowContext);
        ]]>
    </script>
    <script-language>javascript</script-language>

At virtually any point in a workflow, you can use Liferay's script engine to
access workflow APIs or other Liferay APIs. There are a lot of different ways
you could use this. Here are a few practical examples:

- Getting a list of users with a specific workflow-related role
- Sending an email to the designated content approver with a list of people to
  contact if he is unable to review the content
- Creating an alert to be displayed in the Alerts portlet for any user assigned
  to approve content

+$$$

**Note:** In earlier versions of @product@, a
[bug](https://issues.liferay.com/browse/LPS-70234) made it difficult (without a
messy workaround) to resolve classes outside of @product@'s core (for example,
the `portal-kernel` module). That bug is fixed as of the following versions:

- Liferay Digital Enterprise 7.0 Fix Pack 11
- Liferay Portal 7.0 GA 4

If your workflow script depends on services in a module outside of @product@'s
core (for example, `JournalArticleLocalService`), make sure you are running an
up-to-date version of @product@.

$$$

Of course, before you try any of this, you need to know the appropriate syntax
for inserting a script into a workflow. In an XML workflow definition, a script
can be used in any XML type that can contain an *actions* tag: those types are
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
For a complete example of a workflow script that uses the above Groovy script,
please see this `legal-workflow-script.xml` file:
[https://github.com/liferay/liferay-docs/blob/6.2.x/userGuide/code/legal-workflow-script.xml](https://github.com/liferay/liferay-docs/blob/6.2.x/userGuide/code/legal-workflow-script.xml).

## Calling OSGi Services [](id=calling-osgi-services)

How do you call OSGi services from a workflow script, accounting for the dynamic
environment of the OSGi runtime, where services your script depends on can
disappear without notice? 
[Use a service tracker](/develop/tutorials/-/knowledge_base/7-0/service-trackers). 
That way you can check to make sure your code has access to the service it
needs, and if not, do something appropriate in response. Here's a little example
code to show you how this might look in Groovy (import statements excluded):

    ServiceTracker<SomeLocalService,SomeLocalService> st;

    try {
        Bundle bundle = FrameworkUtil.getBundle(GroovyExecutor.class);

        st = new ServiceTracker(bundle.getBundleContext(), JournalArticleLocalService.class, null);
        st.open();

        if (!st.isEmpty()) {
            SomeLocalService _SomeLocalService = st.getService();

            //Do cool stuff with the service you retrieved
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
[service trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers), 
the only odd looking piece of the above code is the `getBundle` call: why is
`GroovyExecutor.class` passed as a parameter? The parameter passed to the
`FrameworkUtil.getBundle` call must be a class from the bundle executing the
workflow script. This is different from the context of a plugin project, where
you'd want to get the bundle hosting the class where you're making the call
(using `this.getClass()`, for example). Note that for another scripting engine,
you must pass in a concrete class from the particular bundle executing your
script.

The combination of Liferay's script and workflow engines is incredibly powerful.
However, since it provides users with the ability to execute code, it can be
dangerous. When configuring your permissions, be aware of the potential
consequences of poorly or maliciously written scripts inside of a workflow
definition. For more information on creating workflow definitions with Kaleo
workflow, see @product@'s 
[workflow documentation](/discover/portal/-/knowledge_base/7-0/using-workflow).

## Related Topics [](id=related-topics)

[Invoking Liferay Services From Scripts](/discover/portal/-/knowledge_base/7-0/invoking-liferay-services-from-scripts)

[Running Scripts From the Script Console](/discover/portal/-/knowledge_base/7-0/running-scripts-from-the-script-console)

[Using Liferay's Script Engine](/discover/portal/-/knowledge_base/7-0/using-liferays-script-engine)
