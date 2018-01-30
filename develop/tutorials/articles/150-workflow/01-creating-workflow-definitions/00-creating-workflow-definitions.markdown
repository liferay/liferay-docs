# Crafting XML Workflow Definitions

You don't need a fancy visual designer to build workflows in @product@. To be
clear, Kaleo Designer is really nice, and convenient, and will make you a faster
workflow designer. If you're going to be building lots of workflow processes,
get access to Kaleo Designer with a Digital Enterprise subscription. But with a
little copy and paste from existing workflows, and a little handcrafted XML,
build any workflow you might need and attain workflow wizardhood in the process.
Follow this set of tutorials to learn what elements you can put into your
definitions.

About those existing workflows.

## Existing Workflow Definitions in @product@

Only one workflow definition is installed by default: Single Approver. However,
several more are embedded in the source code of your @product@ installation. If
you're comfortable extracting the XML files from a JAR file embedded in an LPKG
file, navigate to 

    Liferay-Home/osgi/marketplace

and open (using an archive manager) `Liferay CE Forms and Workflow.lpkg`. Open
the JAR file named

    com.liferay.portal.workflow.kaleo.runtime.impl-[version].jar
 
In the JAR file, navigate to 

    META-INF/definitions/

and extract the four XML workflow definition files. These definitions provide
good reference material for many of the workflow features and elements described
in these articles. In fact, most of the XML snippets you see are lifted
directly from these definitions.

## The Parts of a Workflow Definitions 

A workflow definition is an XML file consisting of a schema definition,
metadata, and workflow nodes. 

### Schema

The XML structure of a workflow definition is defined in an XSD file,

    liferay-worklow-definition-7_0_0.xsd

Declare the schema at the top of the workflow definition file:

    <?xml version="1.0"?
    <workflow-definition
        xmlns="urn:liferay.com:liferay-workflow_7.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xsi:schemaLocation="urn:liferay.com:liferay-workflow_7.0.0 
            http://www.liferay.com/dtd/liferay-workflow-definition_7_0_0.xsd">

To read 464 lines of beautifully formatted XML that defines how to write more
XML (it's practically poetic), check out the XSD
[here](https://www.liferay.com/dtd/liferay-workflow-definition_7_0_0.xsd).
Otherwise, move on to entering the definition's metadata.

### Metadata

Give the definition a name, description, and version:

    <name>Category Specific Approval</name>
    <description>A single approver can approve a workflow content.</description>
	<version>1</version>

### Nodes

After your definition has its schema and metadata defined, tackle the guts of
the workflow definition. *Nodes*, with their sub-elements, are the fundamental
building blocks making up workflow definitions.

- State Nodes don't require user input. The workflow does whatever is specified
    in the state node's `actions` tag (a notification and/or a custom script) ,
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
    many terminal state nodes, and marks the asset as approved in the workflow.

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

- Conditions let you inspect the asset (or its execution context) and do
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

    You may be wondering what that `returnValue` variable is. It's the variable
    that points from the condition to a transition, and its value must match a
    valid transition name. This script looks up the asset in question, retrieves
    its [asset category](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories#defining-categories-for-content),
    and sets an initial `returnValue`. Then it checks to see if the asset has
    been marked with the *legal* category. If not it goes through *Content
    Review* (to the content-review task in the workflow), and if it does it goes
    through *Legal Review* (to the legal-review task in the workflow).

- Forks and Joins: Forks allow the workflow process to split, and joins bring
    the process back to a unified branch. Processing must always be brought
    back using a Join (or a Join XOR), and the number of forks and joins in a
    workflow definition must be equal.

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

- A [Join XOR](link to art. with joins) differs from a join in one
    important way: it removes the constraint that both forks must be completed
    before processing can continue. The asset must complete just one of the
    forks before processing continues.

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

- [Task nodes](LINK to art. on tasks) are at the core of the workflow
    definition. They're the part where a user interacts with the asset in some
    way. Beyond the user interaction, tasks can have several sub-elements,
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

To learn more about workflow tasks, read the next article.
