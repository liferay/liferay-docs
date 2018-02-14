# Workflow Definition Nodes [](id=workflow-definition-nodes)

After your definition's schema and metadata are in place, begin defining the
process. *Node* elements, with their sub-elements, are fundamental building
blocks making up workflow definitions.

**State Nodes** don't require user input. The workflow does whatever is specified
in the state node's `actions` tag (a notification and/or a custom script),
and then moves to the provided transition. Workflows start and end with a
state. The initial state node often only contains a transition: 

        <state>
            <name>created</name>
            <initial>true</initial>
            <transitions>
                <transition>
                    <name>Determine Branch</name>
                    <target>determine-branch</target>
                    <default>true</default>
                </transition>
            </transitions>
        </state>

If a notification or script is required in your state node, use an `actions`
tag. Here's an `action` element containing a Groovy script. This is found in
many terminal state nodes and marks the asset as approved in the workflow.

        <actions>
			<action>
				<name>Approve</name>
				<description>Approve</description>
				<script>
					<![CDATA[
                    com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.
                        updateStatus(com.liferay.portal.kernel.workflow.WorkflowConstants.     
                            getLabelStatus("approved"), workflowContext);]]>
				</script>
				<script-language>groovy</script-language>
				<execution-type>onEntry</execution-type>
			</action>
		</actions>

**Conditions** let you inspect the asset (or its execution context) and do
something, like send it to a particular transition.

Here's the `determine-branch` condition from the Category Specific Approval
workflow definition:

        <condition>
            <name>determine-branch</name>
            <script>
                <![CDATA[
                    import com.liferay.asset.kernel.model.AssetCategory;
                    import com.liferay.asset.kernel.model.AssetEntry;
                    import com.liferay.asset.kernel.model.AssetRenderer;
                    import com.liferay.asset.kernel.model.AssetRendererFactory;
                    import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
                    import com.liferay.portal.kernel.util.GetterUtil;
                    import com.liferay.portal.kernel.workflow.WorkflowConstants;
                    import com.liferay.portal.kernel.workflow.WorkflowHandler;
                    import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

                    import java.util.List;

                    String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

                    WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

                    AssetRendererFactory assetRendererFactory = workflowHandler.getAssetRendererFactory();

                    long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

                    AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);

                    AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetRendererFactory.getClassName(), assetRenderer.getClassPK());

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
            <transitions>
                <transition>
                    <name>Legal Review</name>
                    <target>legal-review</target>
                    <default>false</default>
                </transition>
                <transition>
                    <name>Content Review</name>
                    <target>content-review</target>
                    <default>false</default>
                </transition>
            </transitions>
        </condition>

This example checks the asset category to choose the processing path, whether to
transition to the *Legal Review* task or the *Content Review* task.

The `returnValue` variable points from the condition to a transition, and its
value must match a valid transition name. This script looks up the asset in
question, retrieves its 
[asset category](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories#defining-categories-for-content),
and sets an initial `returnValue`. Then it checks to see if the asset has been
marked with the *legal* category. If not it goes through *Content Review* (to
the content-review task in the workflow), and if it does it goes through *Legal
Review* (to the legal-review task in the workflow).

**Forks and Joins:** Forks split the workflow process, and joins bring the
process back to a unified branch. Processing must always be brought back using
a Join (or a Join XOR), and the number of forks and joins in a workflow
definition must be equal.

        <fork>
            <name>fork-1</name>
            <transitions>
                <transition>
                    <name>transition-1</name>
                    <target>task-1</target>
                    <default>true</default>
                </transition>
                <transition>
                    <name>transition-2</name>
                    <target>task-2</target>
                    <default>false</default>
                </transition>
            </transitions>
        </fork>
        <join>
            <name>join-1</name>
            <transitions>
                <transition>
                    <name>transition-4</name>
                    <target>EndNode</target>
                    <default>true</default>
                </transition>
            </transitions>
        </join>

The workflow doesn't move past the join until the asset transitions to it
from both of the forks. To fork the workflow process, but then allow the
processing to continue when only one fork is completed, use a Join XOR.

A Join XOR differs from a join in one important way: it removes the constraint
that both forks must be completed before processing can continue. The asset
must complete just one of the forks before processing continues.

        <join-xor>
            <name>join-xor</name>
            <transitions>
                <transition>
                    <name>transition3</name>
                    <target>EndNode</target>
                    <default>true</default>
                </transition>
            </transitions>
        </join-xor>

[**Task nodes**](/develop/tutorials/-/knowledge_base/7-1/workflow-task-nodes) 
are at the core of the workflow definition. They're the part where a user
interacts with the asset in some way. Tasks can also have sub-elements,
including notifications, assignments, and task timers.

Here's the `content-review` task from the Category Specific Approval
workflow, with some of the `role` assignment tags cut out for brevity:

        <task>
            <name>content-review</name>
            <actions>
                <notification>
                    <name>Review Notification</name>
                    <template>You have a new submission waiting for your review in the workflow.</template>
                    <template-language>text</template-language>
                    <notification-type>email</notification-type>
                    <notification-type>user-notification</notification-type>
                    <execution-type>onAssignment</execution-type>
                </notification>
            </actions>
            <assignments>
                <roles>
                    <role>
                        <role-type>organization</role-type>
                        <name>Organization Administrator</name>
                    </role>
                    ...
                </roles>
            </assignments>
            <task-timers>
                <task-timer>
                    <name></name>
                    <delay>
                        <duration>1</duration>
                        <scale>hour</scale>
                    </delay>
                    <blocking>false</blocking>
                    <timer-actions>
                        <timer-notification>
                            <name></name>
                            <template></template>
                            <template-language>text</template-language>
                            <notification-type>im</notification-type>
                        </timer-notification>
                    </timer-actions>
                </task-timer>
            </task-timers>
            <transitions>
                <transition>
                    <name>approve</name>
                    <target>approved</target>
                    <default>true</default>
                </transition>
                <transition>
                    <name>reject</name>
                    <target>update</target>
                    <default>false</default>
                </transition>
            </transitions>
        </task>

Learn more about workflow tasks in the next article.
