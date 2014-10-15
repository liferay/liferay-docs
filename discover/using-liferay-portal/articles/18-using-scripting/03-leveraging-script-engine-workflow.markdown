# Leveraging the Script Engine in Workflow [](id=leveraging-the-script-engine-in-workflow)

Liferay's Kaleo workflow engine provides a robust system for reviewing and
approving content in an enterprise environment. Just with the standard feature
set, it is a powerful and robust workflow solution. Adding scripting features
brings it to the next level.

The default workflow definition included with Kaleo gives you a quick look into
how the feature works. The final step in the workflow runs a script that makes
content available for use. As you can see in the snippet below, it uses
Javascript to access the Java class associated with the workflow to set the
status of the content to *approved*.

	<script>
	<![CDATA[Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus
		(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("approved"),workflowContext);]]>
	</script>
	<script-language>javascript</script-language>

At virtually any point in a workflow, you can use Liferay's scripting engine to
access workflow APIs or other APIs outside of workflow. There are a lot of
different ways you could use this, but some practical ones might be getting a
list of users with a specific workflow-related role; sending an email to the
designated content approver with a list of people to contact if he is unable to
review the content; or creating an alert to be displayed in the Alerts portlet
for any user assigned to approve content.

Of course, before you try any of this, you might want to know what the
appropriate syntax is for inserting a script into the workflow. In an XML
workflow definition, a script can be used in any XML type that can contain an
*<actions>* tag - those types being `<state>`, `<task>`, `<fork>` and `<join>`.
Inside of one of those types, format your script like so:

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
to be used with a `Condition` statement in Kaleo. It accesses Liferay's Asset
Framework to determine the category of an asset in the workflow so the correct
approval process can be automatically determined. 

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

				returnValue = "Content Review";

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

Within a workflow, the next task or state is chosen based on the what the method
returns.

The combination of Liferay's scripting and workflow engines is incredibly
powerful, but as it provides users with the ability to execute code, it can also
be very dangerous. When configuring your permissions, be aware of the potential
consequences of poorly, or maliciously, written scripts inside of a workflow
definition. For more information on creating definitions with Kaleo Workflow see
*Chapter 6: Workflow with Kaleo*.
